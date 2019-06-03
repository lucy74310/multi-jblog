package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.UserService;



@Controller
@RequestMapping("/user/api")
public class UserAPIController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkid
	(@RequestParam("id") String id) 
	{
		Boolean result =userService.existId(id); 
		return JSONResult.success(result);
	} 
	
}
