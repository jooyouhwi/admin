package com.wow.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wow.api.model.MainModel;


@Mapper
public interface MainMapper {
	
	public MainModel mainOrder(String userId);
	
	public  List<Map<String, Object>> mainChart(String userId);
	
	public MainModel mainRegCnt(String userId);

}