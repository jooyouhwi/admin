package com.wow.api.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wow.api.dao.MainMapper;
import com.wow.api.dao.SystemMapper;
import com.wow.api.dto.MainDto;
import com.wow.api.model.MainModel;
import com.wow.api.service.CustomerUserDetails;

@Controller
@RequestMapping(path = "/")
public class adminMainController {
	
	@Autowired
	private MainMapper mainMapper;
	
	
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

	@RequestMapping("/main/order_sherch")
	public @ResponseBody MainModel mainSerch(Model model, MainDto dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String userId  = userDetails.getMember().userid;
		
		System.out.println("order_sherch");
		// his.memberService.memberInfor(comId, lang, id);
		
		System.out.println("userid : "+dto.userId);
		System.out.println("userid : "+userId);
		
		// mapper 호출
		MainModel mmodel = mainMapper.mainOrder(userId);
		  
		System.out.println("toDayAmt:::: "+mmodel.toDayAmt);
		System.out.println("monthAmt:::: "+mmodel.monthAmt);
		  
		model.addAttribute("toDayAmt", mmodel.toDayAmt);
		model.addAttribute("monthAmt", mmodel.monthAmt);
			
		return mmodel;
	}
	
	
	
	
}