package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@CrossOrigin("*")
@Api(tags = {"인덱스 컨트롤러"})
public class HelloController {
	
	@Autowired
	private HouseService service;
	
	@ApiOperation(value="랜덤 아파트 조회+인덱스로 이동")
	@GetMapping("/happyhouse")
	public String index(PageBean bean, Model model) throws Exception {
		model.addAttribute("randList", service.getListRandom(bean).get(0));
		System.out.println(service.getListRandom(bean).get(0));
		return "index";
	}
	
	@ApiOperation(value="랜덤 아파트 조회")
	@GetMapping("/random")
	public ResponseEntity<?> random(PageBean bean, Model model) throws Exception {
		List<HouseInfoDeal> list = service.getListRandom(bean);
		model.addAttribute("randList", list.get(0));
		return new ResponseEntity<HouseInfoDeal>(list.get(0), HttpStatus.OK);
	}
	
}