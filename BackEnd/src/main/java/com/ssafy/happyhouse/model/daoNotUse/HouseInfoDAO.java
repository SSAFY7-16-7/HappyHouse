package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseInfoDAO {
	public HouseInfo searchApt( String aptName) throws SQLException;
	public List<HouseInfo> searchDong(String dong ) throws SQLException;
	public void add(HouseInfo houseInfo)	 throws SQLException;
	public void update(HouseInfo houseInfo)throws SQLException;
	public void remove(String id)    throws SQLException;
}
