package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseSell;

@Mapper
public interface InterestSellDao {
	public List<HouseSell> list(String userid);
	public void add(String sellCode,String userid);
	public void remove(String sellCode,String userid);
	
	
}
