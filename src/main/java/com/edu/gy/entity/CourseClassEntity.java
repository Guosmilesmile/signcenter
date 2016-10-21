package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

/**
 * 班级实体
 * @author Christy
 *
 */
@TableName(tablename="cc_courseclass")
public class CourseClassEntity {
	private Integer id;
	private Integer courseid;
	private String classname;
	
	public CourseClassEntity() {
		super();
	}
	public CourseClassEntity(Integer id, Integer courseid, String classname) {
		super();
		this.id = id;
		this.courseid = courseid;
		this.classname = classname;
	}
	@ColumnName(columnName="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ColumnName(columnName="courseid")
	public Integer getCourseid() {
		return courseid;
	}
	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}
	@ColumnName(columnName="classname")
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
