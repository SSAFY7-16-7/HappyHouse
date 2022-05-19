package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.SidoCode;
import com.ssafy.util.DBUtil;

public class SidoCodeDAOImp implements SidoCodeDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public SidoCode search(String sidoCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from sidocode where sidoCode = ?");
			stmt.setString(1, sidoCode);
			rs = stmt.executeQuery();
			SidoCode sidoCodeObj;
			sidoCodeObj = new SidoCode(
						rs.getString("sidoCode"),
						rs.getString("sidoName"));
						
				return sidoCodeObj;
		}finally {
			dbUtil.close(rs, stmt, con);
		}
	}

	@Override
	public List<SidoCode> searchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(SidoCode sidoCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbUtil.getConnection();
			String sql = "insert into sidocode(sidoCode, sidoName) " +
					"values(?,?)";						 
			stmt = con.prepareStatement(sql);
			stmt.setString(1,sidoCode.getSidoCode());
			stmt.setString(2,sidoCode.getSidoName());
			stmt.executeUpdate();

		}finally {
			dbUtil.close(stmt, con);
		}

	}

	@Override
	public void update(SidoCode sidoCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			
			String sql = "update sidocode set sidoName=?" + "where sidoCode=?";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setString(idx++, sidoCode.getSidoName());
			stmt.setString(idx++, sidoCode.getSidoCode());
			
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}


	}

	@Override
	public void remove(String sidoCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from sidocode where sidoCode=?");
			stmt.setString(1, sidoCode);
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

}
