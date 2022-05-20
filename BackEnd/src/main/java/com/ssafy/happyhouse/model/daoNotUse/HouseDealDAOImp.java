package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.util.DBUtil;

public class HouseDealDAOImp implements HouseDealDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public List<HouseDeal> search(int aptCode) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<HouseDeal> houseDealList = new ArrayList<>();
		try { 
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from housedeal where aptCode = ?");
			stmt.setInt(1, aptCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				houseDealList.add(new HouseDeal(
						rs.getInt("no"),
						rs.getInt("aptCode"),
						rs.getString("dealAmount"),
						rs.getInt("dealYear"),
						rs.getInt("dealMonth"),
						rs.getInt("dealDay"),
						rs.getString("area"),
						rs.getString("floor"),
						rs.getString("type"),
						rs.getString("rentMoney")));
			}
			
				return houseDealList;
		}finally {
			dbUtil.close(rs, stmt, con);
		}
	
	}

	@Override
	public List<HouseDeal> searchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(HouseDeal houseDeal) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbUtil.getConnection();

			String sql = "insert into housedeal(aptCode,dealAmout, dealYear,dealMonth,dealDay,area,floor,type,rentMoney) " +
					"values(?,?,?,?,?,?,?,?,?)";						 
			stmt = con.prepareStatement(sql);
			stmt.setInt(2,houseDeal.getAptCode());
			stmt.setString(3,houseDeal.getDealAmout());
			stmt.setInt(4,houseDeal.getDealYear());
			stmt.setInt(5,houseDeal.getDealMonth());
			stmt.setInt(6,houseDeal.getDealDay());
			stmt.setString(7,houseDeal.getArea());
			stmt.setString(7,houseDeal.getFloor());
			stmt.setString(7,houseDeal.getType());
			stmt.setString(7,houseDeal.getRentMoney());
			stmt.executeUpdate();

		}finally {
			dbUtil.close(stmt, con);
		}

	}

	@Override
	public void update(HouseDeal houseDeal) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			
			String sql = "update housedeal set dealAmout=?,dealYear=?, dealMonth=?,dealDay=?,area=? ,floor=?,type=?,rentMoney=?" + "where aptCode=?";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setString(idx++,houseDeal.getDealAmout());
			stmt.setInt(idx++,houseDeal.getDealYear());
			stmt.setInt(idx++,houseDeal.getDealMonth());
			stmt.setInt(idx++,houseDeal.getDealDay());
			stmt.setString(idx++,houseDeal.getArea());
			stmt.setString(idx++,houseDeal.getFloor());
			stmt.setString(idx++,houseDeal.getType());
			stmt.setString(idx++,houseDeal.getRentMoney());
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}


	}

	@Override
	public void remove(int aptCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from housedeal where aptCode=?");
			stmt.setInt(1, aptCode);
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}

}
