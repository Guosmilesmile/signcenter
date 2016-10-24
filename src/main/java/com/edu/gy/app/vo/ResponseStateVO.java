package com.edu.gy.app.vo;

public class ResponseStateVO {
	
	private String state;//状态
	private String message;//信息
	public ResponseStateVO(String state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseStateVO() {
		super();
	}
	
}
