package com.ssafy.happyhouse.model.dto;

import java.io.Serializable;

public class HouseSell implements Serializable{
	private int idx;
	private String id;
	private String title;
	private String date;
	private String type;
	private String price;
	private String admin_price;
	private String admin_desc;
	private String room_type;
	private int floor_now;
	private int floor_tot;
	private double size;
	private String content;
	private String image;
	private String keyword;
	private String address;
	private String dong;
	private String lat;
	private String lng;
	
	public HouseSell() {
		super();
	}

	public HouseSell(int idx, String id, String title, String date, String type, String price, String admin_price,
			String admin_desc, String room_type, int floor_now, int floor_tot, double size, String content,
			String image, String keyword, String address, String dong, String lat, String lng) {
		super();
		this.idx = idx;
		this.id = id;
		this.title = title;
		this.date = date;
		this.type = type;
		this.price = price;
		this.admin_price = admin_price;
		this.admin_desc = admin_desc;
		this.room_type = room_type;
		this.floor_now = floor_now;
		this.floor_tot = floor_tot;
		this.size = size;
		this.content = content;
		this.image = image;
		this.keyword = keyword;
		this.address = address;
		this.dong = dong;
		this.lat = lat;
		this.lng = lng;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAdmin_price() {
		return admin_price;
	}

	public void setAdmin_price(String admin_price) {
		this.admin_price = admin_price;
	}

	public String getAdmin_desc() {
		return admin_desc;
	}

	public void setAdmin_desc(String admin_desc) {
		this.admin_desc = admin_desc;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public int getFloor_now() {
		return floor_now;
	}

	public void setFloor_now(int floor_now) {
		this.floor_now = floor_now;
	}

	public int getFloor_tot() {
		return floor_tot;
	}

	public void setFloor_tot(int floor_tot) {
		this.floor_tot = floor_tot;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
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

	@Override
	public String toString() {
		return "HouseSell [idx=" + idx + ", id=" + id + ", title=" + title + ", date=" + date + ", type=" + type
				+ ", price=" + price + ", admin_price=" + admin_price + ", admin_desc=" + admin_desc + ", room_type="
				+ room_type + ", floor_now=" + floor_now + ", floor_tot=" + floor_tot + ", size=" + size + ", content="
				+ content + ", image=" + image + ", keyword=" + keyword + ", address=" + address + ", dong=" + dong
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}


	
}
