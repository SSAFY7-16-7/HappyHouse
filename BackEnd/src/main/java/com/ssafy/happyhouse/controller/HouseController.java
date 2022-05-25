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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Address;
import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/deal")
@CrossOrigin("*")
@Api(tags = {"실거래가 아파트 컨트롤러"})
public class HouseController {
	@Autowired
	private HouseService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
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
	
	@ApiOperation(value="동으로 아파트 목록 조회", notes = "dongName로 houseinfo 검색")
	@GetMapping("/dong")
	public ResponseEntity<?> getDongList(@RequestParam String dongName) throws Exception {
		logger.info("dongList....................{}", dongName);
		List<HouseInfo> list = service.getDongList(dongName);
		logger.info("dongList: {}", list);
		return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="좌표로 아파트 목록 조회", notes = "남서, 북동 좌표로 houseinfo 검색")
	@GetMapping("/address")
	public ResponseEntity<?> getAddressList(@RequestParam String qa,@RequestParam String pa,@RequestParam String ha,@RequestParam String oa) throws Exception {
		Address address = new Address(null, null, null, null, qa, pa, ha, oa);
		logger.info("addressList....................{}",address);
		List<HouseInfo> list = service.getAddressList(address);
		logger.info("addressLisst: {}", list);
		return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 이름으로 아파트 목록 조회", notes = "apartmentName로 houseinfo 검색")
	@GetMapping("/name")
	public ResponseEntity<?> getNameList(@RequestParam String apartmentName) throws Exception {
		logger.info("nameList....................{}", apartmentName);
		List<HouseInfo> list = service.getNameList(apartmentName);
		logger.info("nameList: {}", list);
		return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 거래내역 조회", notes = "aptCode로 housedeal 검색")
	@GetMapping("/deal")
	public ResponseEntity<?> getDealList(@RequestParam String aptCode) throws Exception {
		logger.info("addressList....................{}");
		List<HouseDeal> list = service.getDealList(aptCode);
		logger.info("addressList: {}", list);
		return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="kaptcode 조회", notes = "houseinfo 도로명주소로 kaptcode 조회")
	@GetMapping("/kapt")
	public ResponseEntity<?> getKapt(HouseInfo houseinfo) throws Exception {
		logger.info("getKapt....................{}",houseinfo);
		String kapt = service.getKapt(houseinfo);
		logger.info("getKapt Result....................{}",kapt);
		if(kapt == null) {
			return new ResponseEntity<String>("NO", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<String>(kapt, HttpStatus.OK);
		}
	}
}
