package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDAO {
/**
 * 减库存，
 * @param seckillId
 * @param killTime
 * @return 如果影响行数>1，表示更新的记录行数
 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime")  Date killTime);
	/**
	 * 根据id查询秒杀对象
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	/**
	 * 根据偏移量查找商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")Integer offset,@Param("limit")Integer limit);
	
	
	
	
}
