package com.edu.gy.classtime.vo;

public class ClassTimeCountEntity {
	private Integer id;
	private Integer index;
	public ClassTimeCountEntity(Integer id, Integer index) {
		super();
		this.id = id;
		this.index = index;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public ClassTimeCountEntity() {
		super();
	}
}
