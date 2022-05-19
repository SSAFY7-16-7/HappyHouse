package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.util.DBUtil;

public class DongCodeDAOImp implements DongCodeDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public DongCode search(String dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from dongcode where dongCode = ?");
			stmt.setString(1, dongCode);
			rs = stmt.executeQuery();
			DongCode dongCodeObj;
			dongCodeObj = new DongCode(
						rs.getString("sidoName"),
						rs.getString("gugunName"),
						rs.getString("dongName"),
						rs.getString("dongCode"));
						
				return dongCodeObj;
		}finally {
			dbUtil.close(rs, stmt, con);
		}
	}

	@Override
	public List<DongCode> searchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(DongCode dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbUtil.getConnection();
			String sql = "insert into dongcode(dongCode, sidoName,gugunName, dongName) " +
					"values(?,?,?,?)";						 
			stmt = con.prepareStatement(sql);
			stmt.setString(1,dongCode.getDongCode());
			stmt.setString(2,dongCode.getSidoName());
			stmt.setString(3,dongCode.getGugunName());
			stmt.setString(4,dongCode.getDongName());
			stmt.executeUpdate();

		}finally {
			dbUtil.close(stmt, con);
		}

	}

	@Override
	public void update(DongCode dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			
			String sql = "update  set sidoName=?,gugunName=?, dongName=? " + "where dongCode=?";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setString(idx++, dongCode.getSidoName());
			stmt.setString(idx++, dongCode.getGugunName());
			stmt.setString(idx++, dongCode.getDongName());
			stmt.setString(idx++, dongCode.getDongCode());
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}


	}

	@Override
	public void remove(String dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from dongcode where dongCode=?");
			stmt.setString(1, dongCode);
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

}
