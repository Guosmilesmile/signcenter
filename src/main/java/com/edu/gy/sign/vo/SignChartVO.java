package com.edu.gy.sign.vo;

public class SignChartVO {

	private Integer arraynumber;//正常
	private Integer latenumber;//迟到
	private Integer acrossnumber;//跨班
	private Integer passnumber;//旷课
	public Integer getArraynumber() {
		return arraynumber;
	}
	public void setArraynumber(Integer arraynumber) {
		this.arraynumber = arraynumber;
	}
	public Integer getLatenumber() {
		return latenumber;
	}
	public void setLatenumber(Integer latenumber) {
		this.latenumber = latenumber;
	}
	public Integer getAcrossnumber() {
		return acrossnumber;
	}
	public void setAcrossnumber(Integer acrossnumber) {
		this.acrossnumber = acrossnumber;
	}
	public Integer getPassnumber() {
		return passnumber;
	}
	public void setPassnumber(Integer passnumber) {
		this.passnumber = passnumber;
	}
	public SignChartVO(Integer arraynumber, Integer latenumber,
			Integer acrossnumber, Integer passnumber) {
		super();
		this.arraynumber = arraynumber;
		this.latenumber = latenumber;
		this.acrossnumber = acrossnumber;
		this.passnumber = passnumber;
	}
	public SignChartVO() {
		super();
	}
	
	
}
