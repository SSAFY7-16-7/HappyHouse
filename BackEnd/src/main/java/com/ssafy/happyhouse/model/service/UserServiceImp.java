package com.ssafy.happyhouse.model.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.InterestAptDao;
import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.InterestApt;
import com.ssafy.happyhouse.model.dto.LikeCount;
import com.ssafy.happyhouse.model.dto.User;


@Service
public class UserServiceImp implements userService {
	@Autowired
	UserDAO dao ;
	
	@Autowired
	InterestAptDao likeDao;
	
	@Transactional
	@Override
	public void registerUser(User user)  {
		User  find = dao.search(user.getId());
		if(find!=null) {
			throw new HappyHouseException("이미 등록된 id");
		}
		dao.add(user);
		
	}

	@Override
	public User login(String id, String password){
		
		User  find = dao.search(id);
		if(find==null) {
			throw new HappyHouseException("해당 id의 회원이 존재하지 않습니다.");
		}else {
			find = dao.login(id, password);	
		}
		if(find==null) {
			throw new HappyHouseException("비밀번호가 일치하지 않습니다.");
		}
		
		return dao.login(id, password);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		User  find = dao.search(user.getId());
		if(find==null) {
			throw new HappyHouseException("등록되지 않은 user 변경 불가 ");
		}
		dao.update(user);
	}

	@Override
	public void deleteUser(String id) {
		User  find = dao.search(id);
		if(find==null) {
			throw new HappyHouseException("해당 id의 회원이 존재하지 않습니다.");
		}
		dao.remove(id);
	}

	@Override
	public User infoUser(String id) {
		User  find = dao.search(id);
		if(find==null) {
			throw new HappyHouseException("해당 id의 회원이 존재하지 않습니다.");
		}
		
		return dao.search(id);
	}

	@Override
	public List<User> listUser() {
		return dao.searchAll();
	}
	
	public List<User> findByKey(String key,String word) {
		List<User> list= dao.keySearch(key, word);
		return list;
	}

	@Override
	public List<HouseInfo> likeList(String user_id) {
		User find = dao.search(user_id);
		if(find==null) {
			throw new HappyHouseException("존재하지 않는 회원");
		}
		return likeDao.interestApts(user_id);
	}

	@Override
	public void addLike(InterestApt info) {
		User find = dao.search(info.getUserId());
		if(find==null) {
			throw new HappyHouseException("존재하지 않는 회원");
		}
		likeDao.addInterest(info);
		
	}

	@Override
	public void removeLike(InterestApt info) {
		likeDao.remove(info);
		
	}

	@Override
	public List<LikeCount> getLikeCntList() {
		List<LikeCount> list = likeDao.likeCountList();
		Collections.sort(list,(a,b)->b.getCnt()-a.getCnt());
		return list;
	}

	@Override
	public String changePassword(User user) {
		char[] newPass = new char[8];
		
		User originUser = dao.search(user.getId());
	
		if(originUser==null ) {
			throw new HappyHouseException("존재하지 않는 회원");
		}
		if(originUser.getEmail().equals(user.getEmail())&& originUser.getName().equals(user.getName())) {
		
			for(int i=0;i<8;i++) {
				newPass[i] = (char)(int)(Math.random()*26);
			}
			System.out.println("임시 비밀번호 발급"+newPass.toString());
			user.setPassword(newPass.toString());
			dao.setNewPassword(user);
			
		}else {
			throw new HappyHouseException("틀린 유저 정보 ");
		}
		return newPass.toString();
	}

}
