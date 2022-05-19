package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.SidoCode;

public interface SidoCodeDAO {
	public SidoCode search( String sidoCode) throws SQLException;
	public List<SidoCode> searchAll( ) throws SQLException;
	public void add(SidoCode sidoCode)	 throws SQLException;
	public void update(SidoCode sidoCode)throws SQLException;
	public void remove(String sidoCode)    throws SQLException;
}
