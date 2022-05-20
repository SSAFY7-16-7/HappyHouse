package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;

@Mapper
public interface MapDAO {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfo> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfo> getAptInDong(String dong) throws SQLException;
	
}
