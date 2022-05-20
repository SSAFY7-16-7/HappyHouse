package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.util.DBUtil;

public class BaseAddressDAOImp implements BaseAddressDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public BaseAddress search(DongCode dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from baseaddress where dongCode = ?");
			stmt.setString(1, dongCode.getDongCode());
			rs = stmt.executeQuery();
			BaseAddress baseAddress;
			baseAddress = new BaseAddress(
						rs.getInt("no"),
						rs.getString("sidoName"),
						rs.getString("gugunName"),
						rs.getString("dongName"),
						rs.getString("dongCode"),
						rs.getString("lat"),
						rs.getString("lng"));
						
				return baseAddress;
		}finally {
			dbUtil.close(rs, stmt, con);
		}
	}

	@Override
	public List<BaseAddress> searchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(BaseAddress baseAddress) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbUtil.getConnection();
			String sql = "insert into baseaddress(sidoName,gugunName, dongName,dongCode,lat,lng) " +
					"values(?,?,?,?,?,?)";						 
			stmt = con.prepareStatement(sql);
			stmt.setString(2,baseAddress.getSidoName());
			stmt.setString(3,baseAddress.getGugunName());
			stmt.setString(4,baseAddress.getDongName());
			stmt.setString(5,baseAddress.getDongCode());
			stmt.setString(6,baseAddress.getLat());
			stmt.setString(7,baseAddress.getLng());
			stmt.executeUpdate();

		}finally {
			dbUtil.close(stmt, con);
		}

	}

	@Override
	public void update(BaseAddress baseAddress) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			
			String sql = "update baseaddress set sidoName=?,gugunName=?, dongName=?,lat=?,lng=? " + "where dongCode=?";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setString(idx++, baseAddress.getSidoName());
			stmt.setString(idx++, baseAddress.getGugunName());
			stmt.setString(idx++, baseAddress.getDongName());
			stmt.setString(idx++, baseAddress.getLat());
			stmt.setString(idx++, baseAddress.getLng());
			stmt.setString(idx++, baseAddress.getDongCode());
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}


	}

	@Override
	public void remove(DongCode dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from baseaddress where dongCode=?");
			stmt.setString(1, dongCode.getDongCode());
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

}
