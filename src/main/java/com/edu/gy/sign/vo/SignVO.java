package com.edu.gy.sign.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class SignVO {

	private String userid;
	private String nickname;
	private String signtime;
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
	public String getSigntime() {
		return signtime;
	}
	public void setSigntime(String signtime) {
		this.signtime = signtime;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public SignVO(String userid, String nickname, String signtime,
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
		if("0".equals(signtime) || null == signtime){
			this.signtime = "0";
		}else{
			this.signtime = (new Timestamp(Long.parseLong(signtime)*1000)).toString();
		}
	}
	public SignVO() {
		super();
	}
	
}
