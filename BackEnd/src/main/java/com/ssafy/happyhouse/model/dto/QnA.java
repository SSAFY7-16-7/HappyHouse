package com.ssafy.happyhouse.model.dto;

import java.io.Serializable;

public class QnA implements Serializable {
	private String idx;
	private String userid;
	private String title;
	private String content;
	private String date;
	private String answer;
	private String answer_date;
	
	private String key;
	
	public QnA() {
	}

	public QnA(String idx, String userid, String title, String content, String date, String answer, String answer_date,
			String key) {
		super();
		this.idx = idx;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.date = date;
		this.answer = answer;
		this.answer_date = answer_date;
		this.key = key;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(String answer_date) {
		this.answer_date = answer_date;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
