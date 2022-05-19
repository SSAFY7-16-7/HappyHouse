package com.ssafy.happyhouse.model.dto;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String password;
	private String email;
	private String name;
	private int age;
	
	public User() {};
	public User(String id, String password, String email, String name) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;

	}
	public User(String id, String email, String name, int age) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.age = age;
	}
	
	public User(String id, String password, String email, String name, int age) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", name=" + name + ", age=" + age
				+ "]";
	}
	
	
}
