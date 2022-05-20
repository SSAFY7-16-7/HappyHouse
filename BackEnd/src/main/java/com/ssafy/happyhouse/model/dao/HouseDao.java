package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.model.dto.PageBean;

@Mapper
public interface HouseDao {
	public List<HouseInfoDeal> 	getList(PageBean bean);
	public List<HouseDeal> 	detailList(int aptCode);
	public HouseDeal 		detail(int aptCode);
	public void 			add(HouseDeal houseDeal);
	public void 			update(HouseDeal houseDeal);
	public void 			remove(int aptCode);
	public int 				count(PageBean bean);
	public int 				countAll();
}
