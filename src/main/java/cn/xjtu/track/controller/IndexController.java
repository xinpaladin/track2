package cn.xjtu.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xjtu.track.common.Result;
import cn.xjtu.track.service.DataDetailService;
import cn.xjtu.track.service.DataItemService;
import cn.xjtu.track.service.DataOriginSerivice;

@Controller
@RequestMapping
public class IndexController {

	
	@Autowired
	private DataItemService dataItemService;
	
	@Autowired
	private DataDetailService dataDetailService;
	
	@Autowired 
	private DataOriginSerivice dataOriginSerivice;
	
	@RequestMapping("/index")
	public String getIndex(){
		
		return "index";
	}
	
	
}
