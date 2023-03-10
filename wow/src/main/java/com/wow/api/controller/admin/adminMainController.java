package com.wow.api.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wow.api.dao.MainMapper;
import com.wow.api.dao.MemberMapper;
import com.wow.api.dao.SystemMapper;
import com.wow.api.dto.MainDto;
import com.wow.api.dto.adminBoardDto;
import com.wow.api.model.BoardModel;
import com.wow.api.model.MainModel;
import com.wow.api.model.MemberModel;
import com.wow.api.service.CustomerUserDetails;
import com.wow.api.service.StorageService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(path = "/")
public class adminMainController {
	
	@Autowired
	private MainMapper mainMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private StorageService storageService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@RequestMapping("/")
	public String layout() {
		System.out.println("index");
		return "index";	
	}
		
	@RequestMapping("/index")
	public String menu(Model model) {
		System.out.println("index");
		// ?????? ?????? ?????? 
		
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String loginId  = userDetails.getMember().loginId;
		String userName = userDetails.getMember().username;
		System.out.println("loginId : " + loginId);
		
		model.addAttribute("loginId", loginId);
		model.addAttribute("userName", userName);
		
		return "admin/table-datatable-basic";
	}
	
	@RequestMapping("/bootstrap")
	public String bootstrap(Model model) {

		System.out.println("bootstrap");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String loginId  = userDetails.getMember().loginId;
		String userName = userDetails.getMember().username;
		
		model.addAttribute("loginId", loginId);
		model.addAttribute("userName", userName);
		
		return "admin/table-bootstrap-basic";
	}
	
	
	
	@RequestMapping("/gridlist")
	public String gridlist(Model model) {

		System.out.println("gridlist");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String loginId  = userDetails.getMember().loginId;
		String userName = userDetails.getMember().username;
		
		
		model.addAttribute("loginId", loginId);
		model.addAttribute("userName", userName);
		
		return "admin/table-grid";
	}
	
	
	
   
	// ?????? ?????? ???????????? 
	//------------------------------------------------------------------------------------------
    // ?????? ????????????  map ?????? ??????????????????. 
	// public @ResponseBody MainModel mainSerch(@RequestParam HashMap<String,String> paramMap) {
	// String data = paramMap.get("testParam");
	//-------------------------------------------------------------------------------------------
	// ?????? ??? ??????????????? @PathVariable / ???????????????????????? 
	//@PostMapping("delete/{idx}")
	//@ResponseBody
	//public void testMethod(@PathVariable("idx") int id ){
	//--------------------------------------------------------------------------------------------
	//???????????????????????? 
	//public String viewName( @RequestParam("name") String name, @RequestParam("name2") String name2){
	//                        @RequestParam("name",required=false,defaultValue="") String name // required=false -> name ???????????? ?????? ?????? ??????  
	
	
	//public @ResponseBody MainModel mainSerch(Model model , @RequestParam("userId") String userId) {
	@RequestMapping("/main/order_sherch/{id}")
	public @ResponseBody MainModel mainSerch(Model model , @PathVariable("id") String id) {
		
		// ???????????? 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//?????????????????? ??????
		String userId = id.replaceAll("\"", "");
		
		System.out.println("order_sherch");
		
		// mapper ??????
		MainModel mmodel = mainMapper.mainOrder(userId);
		  
		System.out.println("toDayAmt:::: "+mmodel.toDayAmt);
		System.out.println("monthAmt:::: "+mmodel.monthAmt);
		  
		//model.addAttribute("toDayAmt", mmodel.toDayAmt);
		//model.addAttribute("monthAmt", mmodel.monthAmt);
			
		return mmodel;
	}
	
	@RequestMapping("/main/order_chart/{id}")
	public @ResponseBody List<Map<String, Object>>  mainChart1( Model model, @PathVariable("id") String id) {
		// ???????????? 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//?????????????????? ??????
		String userId = id.replaceAll("\"", "");
		System.out.println("mainChart");

		// mapper ??????
		List<Map<String, Object>>  model2 = mainMapper.mainChart(userId);
		  
		//model.addAttribute("toDayAmt", mmodel.toDayAmt);
		//model.addAttribute("monthAmt", mmodel.monthAmt);
			
		return model2;
	}
	
	@RequestMapping("/main/total_cnt/{id}")
	public @ResponseBody MainModel totalCnt(@PathVariable("id") String id) {
		// ???????????? 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//?????????????????? ??????
		String userId = id.replaceAll("\"", "");
		System.out.println("total_cnt");
		
		// mapper ??????
		MainModel model = mainMapper.mainRegCnt(userId);		
		return model;
	}
	

	@RequestMapping("/main/memberSearch/{id}")
	public @ResponseBody List<MemberModel> memberSearch(@PathVariable("id") String id) {
		
		// ???????????? 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		//?????????????????? ??????
		String userId = id.replaceAll("\"", "");
		
		System.out.println("memberSearch");

		// mapper ??????
		List<MemberModel>  model = memberMapper.memberSearch(userId);
		
		return model;
	}
	
	
	@RequestMapping("/main/memberGrid")
	public @ResponseBody List<MemberModel> memberGrid( ) {
		System.out.println("memberGrid");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth.getPrincipal() :"  + auth.getPrincipal());
		System.out.println("auth.getName() : " + auth.getName());
		
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String userId = userDetails.getMember().loginId;
		
		//?????????????????? ??????
		//String userId = id.replaceAll("\"", "");
		// mapper ??????
		List<MemberModel>  modelList = memberMapper.memberSearch(userId);
		// ????????? ??? 
		int cnt = 2; 
		//---------------------------------------------------------------
		//List<MemberModel>  result = memberMapper.memberSearch(userId);
		//JSONArray mapResult = JSONArray.fromObject(result);
		//model.addAttribute("mapResult", mapResult);
		//----------------------------------------------------------------
		
		
		return modelList;
	}
	
	
		
	@RequestMapping("/board")
	public String board(Model model) {
		System.out.println("board");
		// ?????? ?????? ?????? 
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth.getPrincipal() :"  + auth.getPrincipal());
		System.out.println("auth.getName() : " + auth.getName());
		
		CustomerUserDetails userDetails = (CustomerUserDetails) auth.getPrincipal();
		String loginId  = userDetails.getMember().loginId;
		String userName = userDetails.getMember().username;
		
		adminBoardDto adminBoardDto = new adminBoardDto();
		
		model.addAttribute("adminBoardDto",adminBoardDto);
		//adminBoardDto 
		
		
		model.addAttribute("loginId", loginId);
		model.addAttribute("userName", userName);
		
		return "admin/board";	
	}
	
	//CK????????? ????????? ?????? 
	@PostMapping("/image/upload")
	//public @ResponseBody String upload(@RequestPart MultipartFile upload, @RequestParam("CKEditorFuncNum") String callback, HttpServletRequest request) {
	public @ResponseBody JSONObject upload(@RequestPart MultipartFile upload, HttpServletRequest request) {
		
		//HashMap<String,Object>
		
		//String sourceName = upload.getOriginalFilename();
		String callback ="1";
		
		String fileName = storageService.store(upload, "ckeditor");
		//System.out.println("fileName : " + fileName);
		//String fileName ="1111";
		String imgUrl = "/uploads/ckeditor/" + fileName;
	
		// ????????? ?????? string ????????? json ????????? ?????????.
		JSONObject json = new JSONObject();
		 
		json.put("uploaded", 1);
		json.put("fileName", fileName);
		json.put("url", imgUrl);
		
		//System.out.println("json  ::: "+ json);
		
		//return sb.toString();
		return json;

	}	
	
	@RequestMapping(value = "/board/save/{kindCd}")
	public @ResponseBody BoardModel save(@ModelAttribute adminBoardDto adminBoardDto, MultipartHttpServletRequest mhsq, @PathVariable(name = "kindCd" ) String kindCd) throws Exception {
		System.out.println("?????? ?????? ");
		System.out.println("kindCd :: " + kindCd);

		//adminBoardDto.title
		//adminBoardDto.contents
		logger.debug("title    : "+ adminBoardDto.title);
		logger.debug("contents : "+ adminBoardDto.contents);
		
		
		
		
		BoardModel mmodel = new BoardModel(); 
		mmodel.retCode = "OK";		
		//model.addAttribute("retCode", "OK");
		return mmodel;
	}
	//admin/board/save
	
	
	
}
