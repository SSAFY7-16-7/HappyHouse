package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.DongCode;

public interface DongCodeDAO {
	public DongCode search(String dongCode) throws SQLException;
	public List<DongCode> searchAll( ) throws SQLException;
	public void add(DongCode dongCode)	 throws SQLException;
	public void update(DongCode dongCode)throws SQLException;
	public void remove(String id)    throws SQLException;
}
