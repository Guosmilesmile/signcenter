package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

/**
 * 课程时间
 * @author Christy
 *
 */
@TableName(tablename="ct_classtime")
public class ClassTimeEntity {
	private Integer id;
	private Integer classid;
	private String classtime;//1_1_3   单双周_星期_开始节数
	private Integer index;//序列号，判断跨班上课
	private Integer count;//次数
	
	public ClassTimeEntity(Integer id, Integer classid, String classtime,
			Integer index, Integer count) {
		super();
		this.id = id;
		this.classid = classid;
		this.classtime = classtime;
		this.index = index;
		this.count = count;
	}
	public ClassTimeEntity() {
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
	@ColumnName(columnName="indexs")
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	@ColumnName(columnName="count")
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}	
