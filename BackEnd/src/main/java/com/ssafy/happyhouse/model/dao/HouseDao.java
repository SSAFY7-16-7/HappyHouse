package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Address;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

@Mapper
public interface HouseDao {
	public List<HouseInfo> 	getDongList(String dongName);
	public List<HouseInfo> 	getAddressList(Address address);
	public List<HouseDeal> 	getDealList(String aptCode);
}
