package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AddressDAO {
	public List<String> 		getSido() throws SQLException;
	public List<String> 		getGugun(String sidoName) throws SQLException;
//	public List<Address> 		getDong(String gugunName) throws SQLException;
	public List<String> 		getDong(String gugunName) throws SQLException;
}
