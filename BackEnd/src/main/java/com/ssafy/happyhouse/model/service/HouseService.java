package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface HouseService {
	public List<HouseInfoDeal> 	getList(PageBean bean);
	public List<HouseInfoDeal> 	getListRandom(PageBean bean);
	public List<HouseDeal> 	detailList(int aptCode);
	public HouseDeal 		detail(int aptCode);
	public void 			add(HouseDeal houseDeal);
	public void 			update(HouseDeal houseDeal);
	public void 			remove(int aptCode);
}
