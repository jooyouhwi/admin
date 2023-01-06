package com.wow.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wow.api.model.MemberModel;
import com.wow.api.model.SystemModel;


@Mapper
public interface SystemMapper {
	public String system_encrypt(String passwd);
	
	public MemberModel findMemberLogin(String loginId);

}