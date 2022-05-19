package com.ssafy.happyhouse.model.dto;

public class favRegion {

	private int id;
	private String user_id;
	private String dongCode;
	public favRegion(int id, String user_id, String dongCode) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.dongCode = dongCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	
	
	

}
