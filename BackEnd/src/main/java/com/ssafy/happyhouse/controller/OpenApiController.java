package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
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

import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.SellService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Api(tags = { "openapi 컨트롤" })
public class OpenApiController {

	@Autowired
	private SellService service;

	private static final Logger logger = LoggerFactory.getLogger(OpenApiController.class);
	private static final String SUCCESS = "success";

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.info("ErrorHandler.......................................");
		logger.info("ErrorMessage.......................................{}", e.getMessage());
		e.printStackTrace();

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		if (e instanceof HappyHouseException) {
			return new ResponseEntity<String>(e.getMessage(), resHeaders, HttpStatus.FAILED_DEPENDENCY);
		} else {
			return new ResponseEntity<String>("처리 중 오류 발생", resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@ApiOperation(value = "공공 API 1 ", notes = "detail 1 ", response = List.class)
	@GetMapping("/detail1")
	public ResponseEntity<?> list(@RequestParam String kaptCode) throws IOException {
		System.out.println("공공 api detail1 " + kaptCode);
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusBassInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=%2BERelxkpcuje7NJLgLdEDeimdjhWgfOhmG3W7Fz9tWihQjyffyBvz1pbHDk2Wi5hUkv9DDYDlrxwGEm887GYLw%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder.append(
				"&" + URLEncoder.encode("kaptCode", "UTF-8") + "=" + URLEncoder.encode(kaptCode, "UTF-8")); /* 단지코드 */
		URL url = new URL(urlBuilder.toString());
//		System.out.println(url.toString());
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line).append('\n');
		}
		rd.close();
		conn.disconnect();
		JSONObject jsonObj = XML.toJSONObject(sb.toString());
		String json = jsonObj.toString();
//		System.out.println(json);

		return new ResponseEntity<String>(json, HttpStatus.OK);

	}
	@ApiOperation(value = "공공 API 2 ", notes = "detail 1 ", response = List.class)
	@GetMapping("/detail2")
	public ResponseEntity<?> list2(@RequestParam String kaptCode) throws IOException {
		System.out.println("공공 api detail2 " + kaptCode);
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusDtlInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=%2BERelxkpcuje7NJLgLdEDeimdjhWgfOhmG3W7Fz9tWihQjyffyBvz1pbHDk2Wi5hUkv9DDYDlrxwGEm887GYLw%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder.append(
				"&" + URLEncoder.encode("kaptCode", "UTF-8") + "=" + URLEncoder.encode(kaptCode, "UTF-8")); /* 단지코드 */
		URL url = new URL(urlBuilder.toString());
//		System.out.println(url.toString());
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line).append('\n');
		}
		rd.close();
		conn.disconnect();
		JSONObject jsonObj = XML.toJSONObject(sb.toString());
		String json = jsonObj.toString();
//		System.out.println(json);

		return new ResponseEntity<String>(json, HttpStatus.OK);

	}
	
	@ApiOperation(value = "news API ", notes = " 외부 api  ", response = List.class)
	@GetMapping("/news")
	public ResponseEntity<?> news() throws IOException {
		System.out.println("뉴스 요청 api");
		StringBuilder urlBuilder = new StringBuilder(
				"https://serpapi.com/search.json?engine=naver&query=%08%EB%B6%80%EB%8F%99%EC%82%B0&where=news"); /* URL */
		urlBuilder.append("&" + URLEncoder.encode("api_key", "UTF-8")
				+ "=d783a2fb69f65d8930131d49e6cfdbdad3d3bb75da7523feec22f3f53650c60a"); /*
																														 * Service
																														 * Key
																														 */
		
		URL url = new URL(urlBuilder.toString());
//		System.out.println(url.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line).append('\n');
		}
		rd.close();
		conn.disconnect();
//		JSONObject jsonObj = XML.toJSONObject(sb.toString());
//		String json = jsonObj.toString();
//		System.out.println(json);

		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);

	}

}
