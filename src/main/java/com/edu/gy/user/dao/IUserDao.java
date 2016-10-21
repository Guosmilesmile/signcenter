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
	 * 分页获取用户数量
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public Integer getUserEntityCount();
	
}
