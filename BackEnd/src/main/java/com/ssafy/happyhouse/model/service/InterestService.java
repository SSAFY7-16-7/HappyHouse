package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HouseSell;

public interface InterestService {
	
	public List<HouseInfo> getApts (String userid);
	public void addApt(String aptCode,String userid);
	public void removeApt(String aptCode,String userid);
	public boolean checkLikedApt(String aptCode,String userid);
	
	
	public List<HouseSell> getSells (String userid);
	public void addSell(String sellCode,String userid);
	public void removeSell(String sellCode,String userid);
	public boolean checkLikedSell(String sellCode,String userid);

}
