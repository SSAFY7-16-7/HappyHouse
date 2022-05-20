package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/showDeals")
@CrossOrigin("*")
@Api(tags = {"실거래가 아파트 컨트롤러"})
public class HouseController {
	@Autowired
	private HouseService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	private static final String SUCCESS ="success";
	
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
	
	@ApiOperation(value="실거래가 아파트 목록 조회 + 페이지 이동", notes = "페이징 처리+검색조건")
	@GetMapping("/list")
	public String getList(PageBean bean, Model model) throws Exception {
		logger.info("houseList+move....................{}", bean.getKey()+","+bean.getWord());
		List<HouseInfoDeal> list = service.getList(bean);
		model.addAttribute("deals", list);
		logger.info("houseList+move: {}", list);
		return "showDeals";
	}
	
	@ApiOperation(value="실거래가 아파트 목록 조회", notes = "페이징 처리+검색조건")
	@GetMapping("/searchList")
	@ResponseBody
	public ResponseEntity<?> searchList(PageBean bean, Model model) throws Exception {
		logger.info("houseList....................{}", bean.getKey()+","+bean.getWord());
		List<HouseInfoDeal> list = service.getList(bean);
		model.addAttribute("deals", list);
		logger.info("houseList: {}", list);
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}


}
