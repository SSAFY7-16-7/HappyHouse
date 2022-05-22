package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Address;


public interface AddressService {

	List<String> getSido() throws Exception;
	List<String> getGugun(String sidoName) throws Exception;
//	List<Address> getDong(String gugunName) throws Exception;
	List<String> getDong(String gugunName) throws Exception;
	
}
