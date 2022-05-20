package com.ssafy.happyhouse.model.dto;

public class HappyHouseException extends RuntimeException{
	public HappyHouseException() {
		super("처리 중 오류 발생 ");
	}
	public HappyHouseException(String msg) {
		super(msg);
	}
}
