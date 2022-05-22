package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Address;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseService {
	public List<HouseInfo> 	getDongList(String dongName);
	public List<HouseInfo> 	getAddressList(Address address);
	public List<HouseInfo> 	getNameList(String apartmentName);
	public List<HouseDeal> 	getDealList(String aptCode);
}
