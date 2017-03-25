package seckill;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"
	})
public class SeckillServiceTest {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
//	@Test
	public void testGetSecKillList() {
		List<Seckill> list=seckillService.getSecKillList();
		logger.info("list={}",list);
	}

//	@Test
	public void testGetById() {
		long seckillId=1000L;
		Seckill seckill=seckillService.getById(seckillId);
		logger.info("seckill={}",seckill);
	}

//	@Test
	public void testExportSeckillUrl() {
		long seckillId=1001L;
		Exposer exposer=seckillService.exportSeckillUrl(seckillId);
		logger.info("exposer={}",exposer);
		/**
		 * exposer=Exposer [exposed=true, md5=85b2614d4a31cc5c76274a7c1fe76601, seckillId=1001, now=0, start=0, end=0]
		 */
		
	}

//	@Test
	public void testExecuteSeckill() {
		long seckillId=1001L;
		String md5="85b2614d4a31cc5c76274a7c1fe76601";
		try {
			SeckillExecution execution=	seckillService.executeSeckill(seckillId, 15363498078L, md5);
			logger.info("result={}",execution);
		} 
		catch (RepeatKillException e) {
			logger.error(e.getMessage());
		}
		catch (SeckillCloseException e) {
			logger.error(e.getMessage());
		}
		catch (SeckillException e) {
			logger.error(e.getMessage());
		}
	}
//测试代码完整逻辑，注意可重复执行
	@Test
	public void testSeckillLogicl() {
		long seckillId = 1000L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5=exposer.getMd5();
			try {
				SeckillExecution execution=	seckillService.executeSeckill(seckillId, 15363498078L, md5);
				logger.info("result={}",execution);
			} 
			catch (RepeatKillException e) {
				logger.error(e.getMessage());
			}
			catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			}
			catch (SeckillException e) {
				logger.error(e.getMessage());
			}
		}
		else{
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
	}
	
	
}
