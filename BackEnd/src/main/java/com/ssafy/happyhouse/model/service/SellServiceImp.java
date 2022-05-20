package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.SellDAO;
import com.ssafy.happyhouse.model.dto.HouseSell;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.util.PageUtility;

@Service
public class SellServiceImp implements SellService {
	@Autowired
	private SellDAO dao;

	@Override
	public List<HouseSell> list(PageBean bean) {
		int cnt = dao.count(bean);
		
		try {
			PageUtility page = new PageUtility(bean.getInterval(), cnt, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dao.list(bean); 
	}

	@Override
	public HouseSell detail(String idx) {
		return dao.detail(idx);
	}

	@Override
	@Transactional
	public void add(HouseSell houseSell) {
		dao.add(houseSell);
	}

	@Override
	@Transactional
	public void update(HouseSell houseSell) {
		dao.update(houseSell);
	}

	@Override
	@Transactional
	public void remove(String idx) {
		dao.remove(idx);
	}
}
