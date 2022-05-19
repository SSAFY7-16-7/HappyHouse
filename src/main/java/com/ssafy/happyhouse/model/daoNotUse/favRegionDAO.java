package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.favRegion;

public interface favRegionDAO {
	public List<favRegion> search(String id)  throws SQLException;
	public void add(favRegion favRegion) throws SQLException;
	public void remove(favRegion favRegion) throws SQLException;
	
}
