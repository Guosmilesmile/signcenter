package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

/**
 * 课程实体
 * @author Christy
 *
 */
@TableName(tablename="c_course")
public class CourseEntity {

	private Integer id;
	private String name;
	private Integer userid;
	public CourseEntity(Integer id, String name, Integer userid) {
		super();
		this.id = id;
		this.name = name;
		this.userid = userid;
	}
	public CourseEntity() {
		super();
	}
	@ColumnName(columnName="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ColumnName(columnName="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ColumnName(columnName="userid")
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
}
