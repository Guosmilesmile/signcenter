package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

@TableName(tablename="cs_classstudent")
public class ClassStudentEntity {

	private Integer id;
	private Integer classid;
	private Integer userid;
	@ColumnName(columnName="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ColumnName(columnName="classid")
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	@ColumnName(columnName="userid")
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public ClassStudentEntity(Integer id, Integer classid, Integer userid) {
		super();
		this.id = id;
		this.classid = classid;
		this.userid = userid;
	}
	public ClassStudentEntity() {
		super();
	}
	
}
