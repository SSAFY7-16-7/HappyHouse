package com.ssafy.happyhouse.model.dto;

public class InterestApt {
	private String userId;
	private int aptCode;
	public InterestApt(String userId, int aptCode) {
		super();
		this.userId = userId;
		this.aptCode = aptCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	@Override
	public String toString() {
		return "InterestApt [userId=" + userId + ", aptCode=" + aptCode + "]";
	}
	

}
