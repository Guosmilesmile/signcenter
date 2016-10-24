package com.edu.gy.app.vo;

public class ResponseStateVO {
	
	private String status;//状态
	private String message;//信息
	public ResponseStateVO(String status, String message) {
		super();
		this.status = status;
		this.message = message;
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
