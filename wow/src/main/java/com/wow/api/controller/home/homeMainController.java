package com.wow.api.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class homeMainController {
	
	@RequestMapping("/")
	public String layout() {
		System.out.println("index");
		return "index";	
	}
	
	
	@RequestMapping("/home")
	public String home() {
		System.out.println("home");
		return "index";	
	}
	
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		System.out.println("menu");
		// 기본 정보 조회 
		model.addAttribute("userName", "Test");
		return "sample/menu";	
	}
}