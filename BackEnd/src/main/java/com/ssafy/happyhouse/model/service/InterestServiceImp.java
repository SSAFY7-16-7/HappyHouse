package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.InterAptDao;
import com.ssafy.happyhouse.model.dao.InterestSellDao;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HouseSell;

@Service
public class InterestServiceImp implements InterestService {
	
	@Autowired
	InterestSellDao sellDao ;
	
	@Autowired
	InterAptDao aptDao;

	@Override
	public List<HouseInfo> getApts(String userid) {
		
		return  aptDao.list(userid);
	}

	@Override
	public void addApt(String aptCode, String userid) {
		aptDao.add(aptCode, userid);
	}

	@Override
	public void removeApt(String aptCode, String userid) {
		aptDao.remove(aptCode, userid);
	}

	@Override
	public List<HouseSell> getSells(String userid) {
		return sellDao.list(userid);
	}

	@Override
	public void addSell(String sellCode, String userid) {
		sellDao.add(sellCode, userid);
	}

	@Override
	public void removeSell(String sellCode, String userid) {
		sellDao.remove(sellCode, userid);
	}

	@Override
	public boolean checkLikedApt(String aptCode, String userid) {
		
		List<HouseInfo> list = aptDao.list(userid);
		for(HouseInfo info : list) {
			if(info.getAptCode().equals(aptCode)) return true;
		}
		return false;
	}

	@Override
	public boolean checkLikedSell(String sellCode, String userid) {
		List<HouseSell> list = sellDao.list(userid);
		for(HouseSell info:list) {
			if(info.getIdx()==Integer.parseInt(sellCode)) return true;
		}
		return false; 
	}

}
