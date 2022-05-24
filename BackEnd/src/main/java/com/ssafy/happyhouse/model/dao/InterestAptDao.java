package com.ssafy.happyhouse.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.InterestApt;
import com.ssafy.happyhouse.model.dto.LikeCount;

@Mapper
public interface InterestAptDao {
	public List<HouseInfo> interestApts(String user_id);
	public void addInterest(InterestApt info);
	public void remove(InterestApt info);
	public List<LikeCount> likeCountList();


}
