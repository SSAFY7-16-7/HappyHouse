package com.ssafy.happyhouse.model.service;

import java.util.List;


public interface AddressService {

	List<String> getSido() throws Exception;
	List<String> getGugun(String sidoName) throws Exception;
	List<String> getDong(String gugunName) throws Exception;
	
}
