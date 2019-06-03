package com.cafe24.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() 
	{
		return "user/login";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute("userVo") UserVo userVo) 
	{
		return "user/join";
	}
	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join
	(
		@ModelAttribute("userVo") @Valid UserVo userVo,
		BindingResult result,
		Model model,
		HttpServletRequest request
	) 
	{	
		if( result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			model.addAllAttributes(result.getModel());// 여러개 모아서 맵으로 세팅
			
			for (ObjectError err : list) {
				System.out.println(err);
			}
			
			return "/user/join";
			
			
		}
		
		Boolean exist =userService.existId(userVo.getId());
		
		if(!exist) {
			String url = "/assets/images/logo/default.jpg";
			userService.join(userVo, url);
			return "user/joinsuccess";
		} else {
			return "redirect:/user/join";
		}
	}
	
	
	
}
