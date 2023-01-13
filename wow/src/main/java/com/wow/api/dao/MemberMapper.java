package com.wow.api.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.wow.api.model.MemberModel;


@Mapper
public interface MemberMapper {
	
	
	public List<MemberModel> memberSearch(String userId);


}