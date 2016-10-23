package com.edu.gy.sign.vo;

public class SignVO {

	private String userid;
	private String nickname;
	private Long signtime;
	private String situation;
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
	public Long getSigntime() {
		return signtime;
	}
	public void setSigntime(Long signtime) {
		this.signtime = signtime;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public SignVO(String userid, String nickname, Long signtime,
			String situation) {
		super();
		this.userid = userid;
		this.nickname = nickname;
		this.signtime = signtime;
		this.situation = situation;
		if("0".equals(situation)){
			this.situation = "旷课";
		}else if("1".equals(situation)){
			this.situation = "迟到";
		}else if("2".equals(situation)){
			this.situation = "正常签到";
		}
	}
	public SignVO() {
		super();
	}
	
}
