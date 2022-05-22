package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseInfo;


@Mapper
public interface InterAptDao {
	public List<HouseInfo> list (String userid);
	public void add(String aptCode, String userid);
	public void remove (String aptCode, String userid );

}
