package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.util.PageUtility;

@Service
public class HouseServiceImp implements HouseService {
	
	@Autowired
	private HouseDao dao;

	@Override
	public List<HouseInfoDeal> getList(PageBean bean) {
		int cnt = dao.count(bean);
		try {
			PageUtility page = new PageUtility(bean.getInterval(), cnt, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dao.getList(bean); 
	}
	
	@Override
	public List<HouseInfoDeal> getListRandom(PageBean bean) {
		int cnt = dao.countAll();
//		System.out.println("총 아파트 개수"+cnt);
		try {
			int randPageNo = (int)(Math.random()*cnt)+1;
//			System.out.println("랜덤페이지 : "+randPageNo);
			
			bean.setInterval(1);
			bean.setPageNo(randPageNo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dao.getList(bean); 
	}

	@Override
	public List<HouseDeal> detailList(int aptCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HouseDeal detail(int aptCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(HouseDeal houseDeal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(HouseDeal houseDeal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int aptCode) {
		// TODO Auto-generated method stub

	}

}
