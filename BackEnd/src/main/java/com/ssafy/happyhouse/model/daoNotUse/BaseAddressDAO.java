package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.DongCode;

public interface BaseAddressDAO {
	public BaseAddress search( DongCode dongCode) throws SQLException;
	public List<BaseAddress> searchAll( ) throws SQLException;
	public void add(BaseAddress baseAddress)	 throws SQLException;
	public void update(BaseAddress baseAddress)throws SQLException;
	public void remove(DongCode dongCode)    throws SQLException;
}
