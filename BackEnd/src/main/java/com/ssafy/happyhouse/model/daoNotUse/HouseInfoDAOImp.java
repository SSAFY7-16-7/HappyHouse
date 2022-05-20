package com.ssafy.happyhouse.model.daoNotUse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.util.DBUtil;

public class HouseInfoDAOImp implements HouseInfoDAO {
	private DBUtil dbUtil = DBUtil.getInstance();
	@Override
	public HouseInfo searchApt(String aptName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from houseinfo where aptName=?");

			stmt.setString(1, aptName);
			rs = stmt.executeQuery();
			while(rs.next()) {
				return new HouseInfo(rs.getInt("aptCode"),rs.getString("aptName"),rs.getString("dongCode"),
						rs.getString("dongName"),rs.getInt("buildYear"),rs.getString("jibun"),
						rs.getString("lat"),rs.getString("lng"),rs.getString("img"));
			}
		
			

		} finally {
			dbUtil.close(rs, stmt, con);
		}
		
		return null;
	}

	@Override
	public List<HouseInfo> searchDong(String dong) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("select * from houseinfo where dongCode = ?");

			stmt.setString(1, dong);
			rs = stmt.executeQuery();
			List<HouseInfo> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new HouseInfo(rs.getInt("aptCode"),rs.getString("aptName"),rs.getString("dongCode"),
						rs.getString("dongName"),rs.getInt("buildYear"),rs.getString("jibun"),
						rs.getString("lat"),rs.getString("lng"),rs.getString("img")));
			}
			return list;

		} finally {
			dbUtil.close(rs, stmt, con);
		}

	}


	@Override
	public void add(HouseInfo houseInfo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			String sql = "insert into houseinfo (aptCode,aptName,dongCode,dongName,buildYear,jibun,lat,lng,img)" 
						+ "values(?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			stmt.setInt(idx++, houseInfo.getAptCode());
			stmt.setString(idx++, houseInfo.getAptName());
			stmt.setString(idx++, houseInfo.getDongCode());
			stmt.setString(idx++, houseInfo.getDongName());
			stmt.setInt(idx++, houseInfo.getBuildYear());
			stmt.setString(idx++, houseInfo.getJibun());
			stmt.setString(idx++, houseInfo.getLat());
			stmt.setString(idx++, houseInfo.getLng());
			stmt.setString(idx++, houseInfo.getImg());
			stmt.executeQuery();

		} finally {
			dbUtil.close(rs, stmt, con);
		}

	}

	@Override
	public void update(HouseInfo houseInfo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			String sql = "update houseinfo set aptCode =?,aptName=?,dongCode=?,dongName=?, buildYear =? , jibun=?,lat=?,lng=?,img=?" + "where id=?";
			stmt = con.prepareStatement(sql);

			int idx = 1;

			
			stmt.setString(idx++, houseInfo.getAptName());
			stmt.setString(idx++, houseInfo.getDongCode());
			stmt.setString(idx++, houseInfo.getDongName());
			stmt.setInt(idx++, houseInfo.getBuildYear());
			stmt.setString(idx++, houseInfo.getJibun());
			stmt.setString(idx++, houseInfo.getLat());
			stmt.setString(idx++, houseInfo.getLng());
			stmt.setString(idx++, houseInfo.getImg());
			stmt.setInt(idx++, houseInfo.getAptCode());
			stmt.executeQuery();


		} finally {
			dbUtil.close(rs, stmt, con);
		}

	}

	@Override
	public void remove(String id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			stmt = con.prepareStatement("delete from houseinfo where id=?");
			stmt.setString(1, id);
			stmt.executeUpdate();
		} finally {
			dbUtil.close(rs, stmt, con);
		}
	}




}
