package com.edu.gy.classstudent.vo;

public class ClassStudentVO {

	private Integer id;
	private String userid;
	private String nickname;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public ClassStudentVO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ClassStudentVO(Integer id, String userid, String nickname) {
		super();
		this.id = id;
		this.userid = userid;
		this.nickname = nickname;
	}
	
}
