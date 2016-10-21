package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

/**
 * 课程时间
 * @author Christy
 *
 */
@TableName(tablename="ct_coursetime")
public class CourseTimeEntity {
	private Integer id;
	private Integer classid;
	private String classtime;
	private Integer index;
	public CourseTimeEntity(Integer id, Integer classid, String classtime,
			Integer index) {
		super();
		this.id = id;
		this.classid = classid;
		this.classtime = classtime;
		this.index = index;
	}
	public CourseTimeEntity() {
		super();
	}
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
	@ColumnName(columnName="classtime")
	public String getClasstime() {
		return classtime;
	}
	public void setClasstime(String classtime) {
		this.classtime = classtime;
	}
	@ColumnName(columnName="index")
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
}	
