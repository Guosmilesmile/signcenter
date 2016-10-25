package com.edu.gy.user.dao;

import java.util.List;

import com.edu.gy.base.BaseDao;
import com.edu.gy.entity.UserEntity;

public interface IUserDao extends BaseDao<UserEntity>{
	
	/**
	 * 分页获取用户
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List<UserEntity> getUserEntity(int start,int pagesize);
	
	/**
	 * 分页获取用户
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List<UserEntity> getUserEntity();
	
	/**
	 * 分页获取用户数量
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public Integer getUserEntityCount();
	
	/**
	 * 判断用户的账号密码是否正确
	 * @param userEntity
	 * @return
	 */
	public UserEntity AuthenUser(UserEntity userEntity);
	
	/**
	 * 通过userid获取对应的用户
	 * @param userid
	 * @return
	 */
	public UserEntity getUserByUserid(String userid);
}
