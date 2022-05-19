package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.User;

@Mapper
public interface UserDAO {
	public User search( String id) ;
	public User login( String id,String password);
	public List<User> searchAll() ;
	public void add(User member);
	public void update(User member);
	public void remove(String id) ;
	public List<User> keySearch(String key,String word);
}












