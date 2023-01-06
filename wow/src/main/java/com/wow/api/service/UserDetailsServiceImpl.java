package com.wow.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wow.api.conf.InfoPcClientUtil;
import com.wow.api.dao.SystemMapper;
import com.wow.api.model.MemberModel;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	/*
	@Autowired
	private SmWownetPgService systemService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private SmWownetServices smWownetServices;
	*/
	@Autowired
	private SystemMapper memberMapper;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("loginId : " + userName);
		
		MemberModel member = memberMapper.findMemberLogin(userName);
				
		if(member ==  null) {
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		
		//String lang = Constant.DEFAULT_LANG;
		String lang = "KR";
		if (member.ntCd != null) {
			lang = member.ntCd.toUpperCase();
		}
		
		String device = InfoPcClientUtil.getDevice();
		
		//SmWownetModel smWownetModel = smWownetServices.getSmWownetById(member.getComId(), lang);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(member.loginId));
		
		//String passwd = String.format("%s%s%s", member.getComId(), String.valueOf(Character.LINE_SEPARATOR), member.getPasswd());
		//String passwd = String.format("%s%s%s", String.valueOf(Character.LINE_SEPARATOR), member.passwd);
		String passwd = member.passwd;
		System.out.println("grantedAuthorities : "+grantedAuthorities);
		CustomerUserDetails userDetails = new CustomerUserDetails( //
		passwd, member.loginId, true, true, true, true, grantedAuthorities);
		
		//CompanyModel companyModel = companyService.getCompany(member.getComId());
		
		userDetails.setMember(member);
		//userDetails.setCompany(companyModel);
		//userDetails.setSmWownet(smWownetModel);
		userDetails.setDeviceName(device);
		userDetails.setIpAddress(InfoPcClientUtil.getIpAddress());
	    userDetails.setBrowserName(InfoPcClientUtil.getBrowserName());
	    userDetails.setBrowserVersion(InfoPcClientUtil.getBrowserVersion());
	    userDetails.setMacAddress("");
	    //userDetails.setCntCd(companyModel.getComCd() + "001");
	    
		return userDetails;
	}

}
