package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

/**
 * 签到实体
 * @author Christy
 *
 */
@TableName(tablename="s_sign")
public class SignEntity {
	private Integer id;
	private Integer userid;
	private Integer classid;
	private Long timestamp;
	private Integer situation;
	@ColumnName(columnName="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ColumnName(columnName="userid")
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@ColumnName(columnName="classid")
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	@ColumnName(columnName="timestamp")
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	@ColumnName(columnName="situation")
	public Integer getSituation() {
		return situation;
	}
	public void setSituation(Integer situation) {
		this.situation = situation;
	}
	public SignEntity(Integer id, Integer userid, Integer classid,
			Long timestamp, Integer situation) {
		super();
		this.id = id;
		this.userid = userid;
		this.classid = classid;
		this.timestamp = timestamp;
		this.situation = situation;
	}
	public SignEntity() {
		super();
	}
	
}
