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
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
@Api("QNA 컨트롤러 V1")
public class QnAController {
	
	@Autowired
	private QnAService service;
	
	private static final Logger logger  = LoggerFactory.getLogger(QnAController.class);
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

	@ApiOperation(value="QnA 리스트", notes = "검색조건, 페이징에 맞게 리스트 가져오기")
	@GetMapping("/list")
	public ResponseEntity<?> qnaList(PageBean bean) {
		logger.info("qnaList.....................{}", bean);
		bean.setInterval(10);
		List<QnA> qnalist =service.QnAList(bean);
		logger.info("qnaList:{}", qnalist);
		if(qnalist != null && !qnalist.isEmpty()) {
			return new ResponseEntity<List<QnA>>(qnalist, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<QnA>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="QnA 디테일", notes = "idx에 해당하는 게시물에 대한 정보 가져오기")
	@GetMapping("/detail/{idx}")
	public ResponseEntity<?> qnaDetail(@PathVariable("idx") String idx) {
		System.out.println(idx);
		QnA qna= service.QnADetail(idx);
		logger.info("qnaDetail.....................{}", qna);
		
		if(qna !=null) {
			return new ResponseEntity<QnA>(qna, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value="Q 등록", notes = "일반회원의 QnA 질문 등록")
	@PostMapping("/qregist")
	public ResponseEntity<String> qRegist(@RequestBody QnA qna) {
			logger.info("qRegist.....................{}", qna);
			service.QRegist(qna);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@ApiOperation(value="A 등록", notes = "관리자의 QnA 답변 등록")
	@PutMapping("/aregist")
	public ResponseEntity<String> aRegist(@RequestBody QnA qna) {
		logger.info("aRegist.....................{}", qna);
		service.ARegist(qna);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@ApiOperation(value="QnA 수정", notes = "key가 admin이면 answer수정, key가 일반회원이면 q수정")
	@PutMapping("/modify")
	public ResponseEntity<String> qnaModify(@RequestBody QnA qna) {
		logger.info("qnaModify.....................{}", qna);
		service.QnAModify(qna);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@ApiOperation(value="Q 삭제", notes = "qna 질문 삭제")
	@DeleteMapping("/qdelete/{idx}")
	public ResponseEntity<String> qDelete(@PathVariable String idx) {
		logger.info("qDelete.....................{}", idx);
		service.QDelete(idx);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@ApiOperation(value="A 삭제", notes = "qna 답변 삭제")
	@PutMapping("/adelete/{idx}")
	public ResponseEntity<String> aDelete(@PathVariable String idx) {
		logger.info("aDelete.....................{}", idx);
		service.ADelete(idx);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
}