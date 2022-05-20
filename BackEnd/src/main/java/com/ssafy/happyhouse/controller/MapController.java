package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.MapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
@Api(tags = {"주소조회 컨트롤러"})
public class MapController {
	
	private final Logger logger = LoggerFactory.getLogger(MapController.class);

	@Autowired
	private MapService haHouseMapService;
	
	@ApiOperation(value="시도 조회")
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value="시도로 구군 조회 ")
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value="구군으로 동 조회")
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfo>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfo>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value="동으로 아파트 정보 조회", notes="houseinfo, housedeal 정보 조인해서 가져옴")
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfo>> apt(@RequestParam("dong") String dong) throws Exception {
		System.out.println("apt요청");
		return new ResponseEntity<List<HouseInfo>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
}
