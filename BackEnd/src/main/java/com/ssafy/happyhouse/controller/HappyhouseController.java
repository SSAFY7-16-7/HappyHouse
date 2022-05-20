package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.happyhouse.model.dto.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@CrossOrigin("*")
@Api(tags = {"메뉴바 컨트롤러"})
public class HappyhouseController {
	
	@ApiOperation(value="로그인 페이지로 이동")
	@GetMapping("/loginPage")
	public String loginPage() {
		return "loginPage";
	}
	
	@ApiOperation(value="실거래가 페이지로 이동")
	@GetMapping("/showDeals")
	public String showDeals() {
		return "showDeals";
	}
	
	@ApiOperation(value="회원가입 페이지로 이동")
	@GetMapping("/signUp")
	public String signUp() {
		return "register";
	}
	
	@ApiOperation(value="마이페이지로 이동")
	@GetMapping("/myPage")
	public String mypage(){
		return "myPage";
	}
	
	@ApiOperation(value="로그아웃 후 인덱스로 이동")
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User usr = (User)session.getAttribute("userInfo");
		System.out.println("로그아웃 -id:"+usr.getId());
		session.invalidate();
		return "redirect:happyhouse";
	}
	
	@ApiOperation(value="회원목록 페이지로 이동")
	@GetMapping("/userList")
	public String userList() {
		
		return "list";
	}
	
	@ApiOperation(value="관심아파트 페이지로 이동")
	@GetMapping("/likeList")
	public String likeList() {
		
		return "likeAptList";
	}
}
