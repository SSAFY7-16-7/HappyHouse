package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Address;
import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
@Api(tags = {"주소조회 컨트롤러"})
public class AddressController {
	private final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService service;
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.info("ErrorHandler.......................................");
		logger.info("ErrorMessage.......................................{}",e.getMessage());
		e.printStackTrace();
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		if( e instanceof HappyHouseException) {
			return new ResponseEntity<String>(e.getMessage(), resHeaders,HttpStatus.FAILED_DEPENDENCY);
		}else {
			return new ResponseEntity<String>("처리 중 오류 발생", resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@ApiOperation(value="시도 조회")
	@GetMapping("/sido")
	public ResponseEntity<List<String>> sido() throws Exception {
		logger.debug("sido : {}", service.getSido());
		return new ResponseEntity<List<String>>(service.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value="구군 조회", notes="sidoName으로 구군 조회")
	@GetMapping("/gugun")
	public ResponseEntity<List<String>> gugun(@RequestParam String sidoName) throws Exception {
		logger.debug("gugun : {}", service.getGugun(sidoName));
		return new ResponseEntity<List<String>>(service.getGugun(sidoName), HttpStatus.OK);
	}
	
//	@ApiOperation(value="동 조회", notes="gugnName으로 동 조회")
//	@GetMapping("/dong")
//	public ResponseEntity<List<Address>> dong(@RequestParam String gugunName) throws Exception {
//		logger.debug("dong : {}", service.getDong(gugunName));
//		return new ResponseEntity<List<Address>>(service.getDong(gugunName), HttpStatus.OK);
//	}
	@ApiOperation(value="동 조회", notes="gugnName으로 동 조회")
	@GetMapping("/dong")
	public ResponseEntity<List<String>> dong(@RequestParam String gugunName) throws Exception {
		logger.debug("dong : {}", service.getDong(gugunName));
		return new ResponseEntity<List<String>>(service.getDong(gugunName), HttpStatus.OK);
	}
	

}
