package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseSell;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface SellService {
	public List<HouseSell> 	list(PageBean bean);
	public HouseSell 		detail(String idx);
	public void 			add(HouseSell houseSell);
	public void 			update(HouseSell houseSell);
	public void 			remove(String idx);
}
