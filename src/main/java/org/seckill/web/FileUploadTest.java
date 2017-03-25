package org.seckill.web;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/seckill")
public class FileUploadTest {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/doupload",method=RequestMethod.POST)
	public String doUpload(@RequestParam("file") MultipartFile file) throws IOException{
		if(!file.isEmpty()){
			logger.info("file={}",file);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\Program Files", System.currentTimeMillis()+file.getOriginalFilename()));
		}
		return "success";
	}
}
