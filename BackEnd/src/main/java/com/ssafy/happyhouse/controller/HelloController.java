package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@CrossOrigin("*")
@Api(tags = {"인덱스 컨트롤러"})
public class HelloController {
	
	@ApiOperation(value="인덱스로 이동")
	@GetMapping("/happyhouse")
	public String index() throws Exception {
		return "index";
	}
	
}