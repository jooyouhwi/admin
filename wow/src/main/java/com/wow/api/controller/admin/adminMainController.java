package com.wow.api.controller.admin;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
   
	// 단건 호출 테스트용 
	//------------------------------------------------------------------------------------------
    // 여러 파라메터  map 으로 받아올수있음. 
	// public @ResponseBody MainModel mainSerch(@RequestParam HashMap<String,String> paramMap) {
	// String data = paramMap.get("testParam");
	//-------------------------------------------------------------------------------------------
	// 구분 자 사용할경우 @PathVariable / 사용하지않을경우 
	//@PostMapping("delete/{idx}")
	//@ResponseBody
	//public void testMethod(@PathVariable("idx") int id ){
	//--------------------------------------------------------------------------------------------
	//사용하지않을경우 
	//public String viewName( @RequestParam("name") String name, @RequestParam("name2") String name2){
	//                        @RequestParam("name",required=false,defaultValue="") String name // required=false -> name 없을경우 에러 발생 방지  
	
	
	//public @ResponseBody MainModel mainSerch(Model model , @RequestParam("userId") String userId) {
	@RequestMapping("/main/order_sherch/{id}")
	public @ResponseBody MainModel mainSerch(Model model , @PathVariable("id") String id) {
		
		// 인증정보 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//더블퀘테이션 제거
		String userId = id.replaceAll("\"", "");
		
		System.out.println("order_sherch");
		
		// mapper 호출
		MainModel mmodel = mainMapper.mainOrder(userId);
		  
		System.out.println("toDayAmt:::: "+mmodel.toDayAmt);
		System.out.println("monthAmt:::: "+mmodel.monthAmt);
		  
		//model.addAttribute("toDayAmt", mmodel.toDayAmt);
		//model.addAttribute("monthAmt", mmodel.monthAmt);
			
		return mmodel;
	}
	
	@RequestMapping("/main/order_chart/{id}")
	public @ResponseBody List<Map<String, Object>>  mainChart1( Model model, @PathVariable("id") String id) {
		
		// 인증정보 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//더블퀘테이션 제거
		String userId = id.replaceAll("\"", "");
		
		System.out.println("mainChart");

		// mapper 호출
		List<Map<String, Object>>  model2 = mainMapper.mainChart(userId);
		  
		
		/*
		 * System.out.println("toDayAmt:::: "+mmodel.toDayAmt);
		 * System.out.println("monthAmt:::: "+mmodel.monthAmt);
		 */
		 
		  
		//model.addAttribute("toDayAmt", mmodel.toDayAmt);
		//model.addAttribute("monthAmt", mmodel.monthAmt);
			
		return model2;
	}
	
	@RequestMapping("/main/total_cnt/{id}")
	public @ResponseBody MainModel totalCnt(@PathVariable("id") String id) {
		
		// 인증정보 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//더블퀘테이션 제거
		String userId = id.replaceAll("\"", "");
		
		System.out.println("total_cnt");
		
		// mapper 호출
		MainModel model = mainMapper.mainRegCnt(userId);


			
		return model;
	}
	
	
	
}