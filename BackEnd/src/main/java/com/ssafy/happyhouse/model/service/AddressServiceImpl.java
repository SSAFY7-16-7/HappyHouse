package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.AddressDAO;
import com.ssafy.happyhouse.model.dto.Address;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDAO dao;

	@Override
	public List<String> getSido() throws Exception {
		return dao.getSido();
	}

	@Override
	public List<String> getGugun(String sidoName) throws Exception {
		return dao.getGugun(sidoName);
	}

	@Override
	public List<String> getDong(String gugunName) throws Exception {
		return dao.getDong(gugunName);
	}
}
