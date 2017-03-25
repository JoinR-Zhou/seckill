package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessSeckill;

public interface SuccessSeckillDAO {

	/**
	 * 插入购买明细，可过滤重复秒杀
	 * @param seckillId
	 * @param userPhone
	 * @return
	 * 插入的行数
	 */
	int insertSuccessSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	/**
	 * 根据id查询SuccessSeckill并携带秒杀产品对象实体
	 * @param seckillId
	 * @return
	 */
	SuccessSeckill queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
	
}
