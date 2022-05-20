package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseSell;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.SellService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sell")
@CrossOrigin("*")
@Api(tags = {"직접 매물거래 컨트롤러"})
public class SellController {

	@Autowired
	private SellService service;
	
	private static final Logger logger = LoggerFactory.getLogger(SellController.class);
	private static final String SUCCESS = "success";
	
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
	
	@ApiOperation(value = "매물거래 글목록", notes = "검색조건, 페이징에 맞게 리스트 가져오기", response = List.class)
	@GetMapping
	public ResponseEntity<List<HouseSell>> list(PageBean pagebean) {
		logger.info("매물거래 list - 호출", pagebean);
		pagebean.setInterval(10);
		return new ResponseEntity<List<HouseSell>>(service.list(pagebean), HttpStatus.OK);
	}

    @ApiOperation(value = "매물거래 글보기", notes = "idx에 해당하는 글의 정보를 반환한다.", response = HouseSell.class)    
	@GetMapping("{idx}")
	public ResponseEntity<HouseSell> detail(@PathVariable String idx) {
		logger.info("매물거래 detail - 호출");
		return new ResponseEntity<HouseSell>(service.detail(idx), HttpStatus.OK);
	}

    @ApiOperation(value = "매물거래 글등록", notes = "새로운 게시글 정보를 입력한다. 성공하면 success 리턴")
	@PostMapping
	public ResponseEntity<String> add(@RequestBody HouseSell houseSell) {
		logger.info("매물거래 add - 호출");
		service.add(houseSell);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

    @ApiOperation(value = "매물거래 글정보 수정", notes = "idx에 해당하는 게시글의 정보를 수정한다. 성공하면 'success' 문자열을 반환한다.")
	@PutMapping
	public ResponseEntity<String> update(@RequestBody HouseSell houseSell) {
		logger.info("매물거래 update- 호출");
		
		service.update(houseSell);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

    @ApiOperation(value = "매물거래 글삭제", notes = "idx에 해당하는 게시글의 정보를 삭제한다. 성공하면'success' 문자열을 반환한다.")
	@DeleteMapping("{idx}")
	public ResponseEntity<String> remove(@PathVariable String idx) {
		logger.info("매물거래  remove - 호출");
		service.remove(idx);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
