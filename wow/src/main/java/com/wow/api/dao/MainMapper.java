package com.wow.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wow.api.model.MainModel;


@Mapper
public interface MainMapper {
	
	public MainModel mainOrder(String userId);

}