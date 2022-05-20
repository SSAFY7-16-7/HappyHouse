package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.InterestApt;
import com.ssafy.happyhouse.model.dto.LikeCount;
import com.ssafy.happyhouse.model.dto.User;

public interface userService {
	
	void registerUser(User userDto) ;
	User login(String id, String pass) ;
	void updateUser(User UserDti) ;
	void deleteUser(String id) ;
	User infoUser(String id) ;
	public List<User> listUser();
	public List<HouseInfo> likeList(String user_id);
	public void addLike(InterestApt info);
	void removeLike(InterestApt info);
	public List<User> findByKey(String key,String word);
	public List<LikeCount> getLikeCntList();
}
