package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.GugunCode;
import com.ssafy.util.DBUtil;

public class GugunCodeDAOImp implements GugunCodeDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public GugunCode search(String gugunCode) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from guguncode where gugunCode = ?");
			stmt.setString(1, gugunCode);
			rs = stmt.executeQuery();
			GugunCode gugunCodeObj;
			gugunCodeObj = new GugunCode(
						rs.getString("gugunCode"),
						rs.getString("gugunName"));
						
				return gugunCodeObj;
		}finally {
			dbUtil.close(rs, stmt, con);
		}
	
	}

	@Override
	public List<GugunCode> searchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(GugunCode gugunCode) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbUtil.getConnection();
			String sql = "insert into guguncode(gugunCode ,gugunName, ) " +
					"values(?,?)";						 
			stmt = con.prepareStatement(sql);
			stmt.setString(1,gugunCode.getGugunCode());
			stmt.setString(2,gugunCode.getGugunName());
			stmt.executeUpdate();

		}finally {
			dbUtil.close(stmt, con);
		}

	

	}

	@Override
	public void update(GugunCode gugunCode) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			
			String sql = "update guguncode set gugunName=?" + "where gugunCode=?";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setString(idx++, gugunCode.getGugunName());
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}


	

	}

	@Override
	public void remove(String gugunCode) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from guguncode where gugunCode=?");
			stmt.setString(1, gugunCode);
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	

	}

}
