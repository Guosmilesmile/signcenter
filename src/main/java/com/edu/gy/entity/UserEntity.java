package com.edu.gy.entity;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;

/**
 * 用户实体类
 * @author Christy
 *
 */
@TableName(tablename="u_user")
public class UserEntity {

	private Integer id;
	private String userId;
	private String passWord;
	private String nickName;
	private Integer role;//0-管理员，1-学生，2-老师
	
	public UserEntity() {
		super();
	}
	public UserEntity(Integer id, String userId, String passWord,
			String nickName, Integer role) {
		super();
		this.id = id;
		this.userId = userId;
		this.passWord = passWord;
		this.nickName = nickName;
		this.role = role;
	}
	@ColumnName(columnName="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ColumnName(columnName="userid")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@ColumnName(columnName="password")
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@ColumnName(columnName="nickname")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@ColumnName(columnName="role")
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
}
