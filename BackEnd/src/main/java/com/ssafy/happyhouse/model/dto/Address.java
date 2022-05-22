package com.ssafy.happyhouse.model.dto;

import java.io.Serializable;

public class Address implements Serializable{
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	private String qa;	//lat 남서
	private String pa;	//lng 남서	
	private String ha;	//lat 북동
	private String oa;	//lng 북동
	
	public Address() {
		super();
	}
	public Address(String dongCode, String sidoName, String gugunName, String dongName) {
		super();
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	
	public Address(String dongCode, String sidoName, String gugunName, String dongName, String qa, String pa, String ha,
			String oa) {
		super();
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.qa = qa;
		this.pa = pa;
		this.ha = ha;
		this.oa = oa;
	}
	
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	
	public String getQa() {
		return qa;
	}
	public void setQa(String qa) {
		this.qa = qa;
	}
	public String getPa() {
		return pa;
	}
	public void setPa(String pa) {
		this.pa = pa;
	}
	public String getHa() {
		return ha;
	}
	public void setHa(String ha) {
		this.ha = ha;
	}
	public String getOa() {
		return oa;
	}
	public void setOa(String oa) {
		this.oa = oa;
	}
	@Override
	public String toString() {
		return "Address [dongCode=" + dongCode + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName="
				+ dongName + "]";
	}

}
