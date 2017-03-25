package seckill;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SuccessSeckillDAO;
import org.seckill.entity.SuccessSeckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})
public class SuccessSeckillDAOTest {

	@Resource
	private SuccessSeckillDAO successSeckillDAO;
	
	@Test
	public void testInsertSuccessSeckill(){
		int insertCount=successSeckillDAO.insertSuccessSeckill(1000L, 18933538569L);
		System.out.println(insertCount);
	}
//	@Test
	public void testQueryByIdWithSeckill(){
		SuccessSeckill successSeckill=	successSeckillDAO.queryByIdWithSeckill(1000L,18933538569L);
		System.out.println(successSeckill);
		System.out.println(successSeckill.getSeckill());
		
	}
}
