package org.seckill.service.impl;
import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDAO;
import org.seckill.dao.SuccessSeckillDAO;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessSeckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService{

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillDAO seckillDAO;
	
	@Autowired
	private SuccessSeckillDAO successSeckillDAO;
	
	public List<Seckill> getSecKillList() {
		
		return seckillDAO.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		
		return seckillDAO.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDAO.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),startTime.getTime(), endTime.getTime());
		}
		//转化特定字符串的过程，不可逆
		String md5 = getMD5(seckillId);

		return new Exposer(true, md5, seckillId);
	}
	
	// 用于混淆md5
	private final String slat = "$%&GRCDsfr928e23~!21efvh";

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite !");
		}
		// 执行秒杀逻辑：减库存+记录购买行为
		Date now = new Date();
		try {
			// 减库存
			int updateCount = seckillDAO.reduceNumber(seckillId, now);
			if (updateCount <= 0) {
				// 没有更新到记录、秒杀结束
				throw new SeckillCloseException("seckill is closed !");
			} else {
				// 减库存成功
				int insertCount = successSeckillDAO.insertSuccessSeckill(
						seckillId, userPhone);
				// 唯一：seckillId、 userPhone
				if (insertCount <= 0) {
					// 重复秒杀
					throw new RepeatKillException("seckill repeated");
				} else {
					SuccessSeckill successSeckill = successSeckillDAO.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS,successSeckill);
				}
			}
		} catch(RepeatKillException e1){
			throw e1;
		}
		catch(SeckillCloseException e2){
			throw e2;
		}
		catch (SeckillException e) {
			//所有编译期异常转化为运行期异常
			logger.error(e.getMessage());
			throw new SeckillException("seckill inner error"+e.getMessage());
		}

	}

	
}
