package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.favRegion;
import com.ssafy.util.DBUtil;

public class favRegionDAOImp implements favRegionDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public List<favRegion> search(String id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from favregion where user_id=?");
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			List<favRegion> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new favRegion(rs.getInt("id"),rs.getString("user_id"),rs.getString("dongCode")));
			}
			return list;
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

	@Override
	public void add(favRegion favRegion) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			String sql = "insert into favregion (user_id,dongCode)" + "values(?,?)";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setString(idx++, favRegion.getUser_id());
			stmt.setString(idx++, favRegion.getDongCode());
	
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

	@Override
	public void remove(favRegion favRegion) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from user where id=? and dongCode =?");
			stmt.setString(1, favRegion.getUser_id());
			stmt.setString(2, favRegion.getDongCode());
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

}
