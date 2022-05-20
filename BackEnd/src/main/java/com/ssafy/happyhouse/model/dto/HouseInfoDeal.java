package com.ssafy.happyhouse.model.dto;

public class HouseInfoDeal {
	int aptCode;
	String aptName;
	String dongName;
	String jibun;
	String lat;
	String lng;
	String area;
	String floor;
	String dealAmount;
	int dealYear;
	int dealMonth;
	int dealDay;
	
	public HouseInfoDeal(int aptCode, String aptName, String dongName, String jibun, String lat, String lng,
			String area, String floor, String dealAmount, int dealYear, int dealMonth, int dealDay) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.dongName = dongName;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.area = area;
		this.floor = floor;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	@Override
	public String toString() {
		return "HouseInfoDeal [aptCode=" + aptCode + ", aptName=" + aptName + ", dongName=" + dongName + ", jibun="
				+ jibun + ", lat=" + lat + ", lng=" + lng + ", area=" + area + ", floor=" + floor + ", dealAmount="
				+ dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + "]";
	}
	
	
}
