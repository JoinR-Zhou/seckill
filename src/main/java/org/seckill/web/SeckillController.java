package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSecKillList();
		model.addAttribute("list",list);
		return "list";
	}
	@RequestMapping(value="/{seckillId}/detail" , method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model){
		if(seckillId==null){
			return "redirect:/seckill/list";
		}
		Seckill seckill=seckillService.getById(seckillId);
		if(seckill==null){
			return "forward:/seckill/list";
		}
		model.addAttribute(seckill);
		return "detail";
	}

	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		
		SeckillResult<Exposer> result;
		try {
			Exposer exposer=seckillService.exportSeckillUrl(seckillId);
			result=new SeckillResult<Exposer>(true, exposer);
		} catch (SeckillException e) {
			logger.error(e.getMessage(),e);
			result=new SeckillResult<Exposer>(false, e.getMessage());
		}
		
		return result;
	}
	@RequestMapping(value="/{seckillId/{md5}/execution",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execution(@PathVariable("seckillId") Long seckillId ,
			@PathVariable("md5") String md5,
			@CookieValue(value="userPhone",required=false ) Long userPhone){
		if(userPhone==null){
			return new SeckillResult<SeckillExecution>(false, "未注册！");
		}
		try {
			SeckillExecution seckillExecution=seckillService.executeSeckill(seckillId, userPhone, md5); 
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e) {
			SeckillExecution seckillExecution=new SeckillExecution(seckillId, SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
		 catch (RepeatKillException e) {
				SeckillExecution seckillExecution=new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
				return new SeckillResult<SeckillExecution>(true, seckillExecution);
			}
		catch (SeckillException e) {
			logger.error(e.getMessage(),e);
			SeckillExecution seckillExecution=new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
	}
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> nowTime(){
		Date now =new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
	
}
