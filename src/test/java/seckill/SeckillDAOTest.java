package seckill;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDAO;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDAOTest {
	@Resource
	private SeckillDAO seckillDao ;
	
	@Test
	public void testReduceNumber()throws Exception{
		Date killTime=new Date();
		int updateCount=seckillDao.reduceNumber(1000L, killTime);
		System.out.println("updateCount = "+updateCount);
	}
//	@Test
	public void testQueryById()throws Exception{
		Seckill seckill=seckillDao.queryById(1000);
		System.out.println(seckill.getSeckillName());
		System.out.println(seckill);
		
	}
//	@Test
	public void testQueryAll()throws Exception{
		List<Seckill> seckills=seckillDao.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			
			System.out.println(seckill.getSeckillName());
		}
	}
}
