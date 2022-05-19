package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.userService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@Api(tags = {"관리자 컨트롤러"})
public class AdminController {
	@Autowired
	private userService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private static final String SUCCESS ="success";
	
	@ApiOperation(value="관리자 회원정보 수정")
	@PutMapping("/update")
	public ResponseEntity<?> userUpdate(@RequestBody User user,HttpServletRequest request) {
		try {
			service.updateUser(user);
			logger.info("admin회원정보 수정..................id:{}",user.getId());

			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}
	
	@ApiOperation(value="관리자 회원정보 삭제")
	@DeleteMapping("/remove")
	public ResponseEntity<?> userRemove(@RequestBody String id,HttpServletRequest request) {
		try {
			logger.info("admin회원정보 삭제..................id:{}",id);
			service.deleteUser(id);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
}
