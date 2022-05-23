package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HouseSell;
import com.ssafy.happyhouse.model.service.InterestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/interest")
@CrossOrigin("*")
@Api(tags = {"찜 컨트롤러 "})
public class InterestController {
	private final Logger logger = LoggerFactory.getLogger(AddressController.class);
	private static final String SUCCESS ="success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	private InterestService service;
	
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
	@ApiOperation(value="찜한 아파트 목록 조회 ", notes = "해당 userid의 아파트 찜 목록을 조회")
	@GetMapping("/aptlist")
	public ResponseEntity<?> aptList(@RequestBody String userid) throws Exception {
		List<HouseInfo> list = service.getApts(userid);
		return new ResponseEntity<List<HouseInfo>>( list,HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 찜하기  ", notes = "해당 userid의 아파트 찜 목록에 아파트 코드에 해당하는 아파트 추가 ")
	@PostMapping("/apt")
	public ResponseEntity<Void> aptAdd(@RequestBody Map<String,String> info) throws Exception {
		service.addApt(info.get("aptCode"), info.get("user_id"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 찜 삭제 ", notes = "해당 userid의 아파트 찜 목록에 아파트 코드에 해당하는 아파트 삭제 ")
	@PostMapping("/delete/apt")
	public ResponseEntity<Void>removeApt(@RequestBody Map<String,String> info) throws Exception {
		service.removeApt(info.get("aptCode"), info.get("user_id"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="찜한 매물 목록 조회 ", notes = "해당 userid의 매 찜 목록을 조회")
	@GetMapping("/selllist")
	public ResponseEntity<?> selltList(@RequestBody String userid) throws Exception {
		List<HouseSell> list = service.getSells(userid);
		return new ResponseEntity<List<HouseSell> >( list,HttpStatus.OK);
	}
	
	@ApiOperation(value="매물 찜하기  ", notes = "해당 userid의 매물 찜 목록에 아파트 코드에 해당하는 아파트 추가 ")
	@PostMapping("/sell")
	public ResponseEntity<Void> sellAdd(@RequestBody Map<String,String> info) throws Exception {
		service.addSell(info.get("aptCode"), info.get("user_id"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="매물 찜 삭제  ", notes = "해당 userid의 매물 찜 목록에 아파트 코드에 해당하는 아파트 삭제 ")
	@PostMapping("/delete/sell")
	public ResponseEntity<Void>removeSell(@RequestBody Map<String,String> info) throws Exception {
		service.removeSell(info.get("aptCode"), info.get("user_id"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/check/likeApt")
	public ResponseEntity<Map<String,String>>findLikeApt(@RequestBody Map<String,String> info) throws Exception {
		System.out.println("찜목록에 있는 아파트 인지 체크 "+info.get("code"));
		Map<String,String> resMap = new HashMap<String, String>() ;
		boolean res = service.checkLikedApt(info.get("code"), info.get("user_id"));
		if(res) {
			System.out.println("이미 찜목록에 있는 아파트"+info.get("code"));
			resMap.put("isLiked", "Y");
		}else {
			System.out.println("찜목록에 없는 아파트 "+info.get("code"));
			resMap.put("isLiked", "N");
		}
		return new ResponseEntity<Map<String,String>>(resMap,HttpStatus.OK);
	}
	@PostMapping("/check/likeSell")
	public ResponseEntity<Map<String,String>>findLikeSell(@RequestBody Map<String,String> info) throws Exception {
		Map<String,String> resMap = new HashMap<String, String>()  ;
		boolean res = service.checkLikedSell(info.get("code"), info.get("user_id"));
		if(res) {
			System.out.println("이미 찜목록에 있는 매물 "+info.get("code"));
			resMap.put("isLiked", "Y");
		}else {
			System.out.println("찜목록에 없는 매물 "+info.get("code"));
			resMap.put("isLiked", "N");
		}
		return new ResponseEntity<Map<String,String>>(resMap,HttpStatus.OK);
	}
	
	
	
}
