package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dto.Address;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

@Service
public class HouseServiceImp implements HouseService {
	
	@Autowired
	private HouseDao dao;

	@Override
	public List<HouseInfo> getDongList(String dongName) {
		return dao.getDongList(dongName);
	}

	@Override
	public List<HouseInfo> getAddressList(Address address) {
		return dao.getAddressList(address);
	}

	@Override
	public List<HouseDeal> getDealList(String aptCode) {
		return dao.getDealList(aptCode);
	}
}
