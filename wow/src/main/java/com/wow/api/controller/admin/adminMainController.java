package com.wow.api.controller.admin;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wow.api.service.CustomerUserDetails;

@Controller
@RequestMapping(path = "/")
public class adminMainController {
	
	@RequestMapping("/")
	public String layout() {
		System.out.println("index");
		return "index";	
	}
	
	@RequestMapping("/index")
	public String menu(Model model) {
		System.out.println("index");
		// 기본 정보 조회 
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth.getPrincipal() :"  + auth.getPrincipal());
		System.out.println("auth.getName() : " + auth.getName());
		
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String loginId  = userDetails.getMember().loginId;
		String userName = userDetails.getMember().username;
		
		model.addAttribute("loginId", loginId);
		model.addAttribute("userName", userName);
		
		return "index";	
	}
	
	@RequestMapping("/dblist")
	public String dblist(Model model) {

		System.out.println("dblist");
		
		return "admin/table-datatable-basic";
	}
	
	
	
	
}