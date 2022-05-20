package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseSell;
import com.ssafy.happyhouse.model.dto.PageBean;

@Mapper
public interface SellDAO {
	public List<HouseSell> 	list(PageBean bean);
	public HouseSell 		detail(String idx);
	public void 			add(HouseSell houseSell);
	public void 			update(HouseSell houseSell);
	public void 			remove(String idx);
	public int 				count(PageBean bean);
}
