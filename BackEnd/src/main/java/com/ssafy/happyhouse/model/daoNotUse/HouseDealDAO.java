package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;

public interface HouseDealDAO {
	public List<HouseDeal> search(int aptCode) throws SQLException;
	public List<HouseDeal> searchAll( ) throws SQLException;
	public void add(HouseDeal houseDeal)	 throws SQLException;
	public void update(HouseDeal houseDeal)throws SQLException;
	public void remove(int aptCode)    throws SQLException;
}
