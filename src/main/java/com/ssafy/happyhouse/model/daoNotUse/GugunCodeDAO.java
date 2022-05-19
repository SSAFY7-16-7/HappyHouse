package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.GugunCode;

public interface GugunCodeDAO {
	public GugunCode search( String gugunCode) throws SQLException;
	public List<GugunCode> searchAll( ) throws SQLException;
	public void add(GugunCode gugunCode)	 throws SQLException;
	public void update(GugunCode gugunCode)throws SQLException;
	public void remove(String gugunCode)    throws SQLException;
}
