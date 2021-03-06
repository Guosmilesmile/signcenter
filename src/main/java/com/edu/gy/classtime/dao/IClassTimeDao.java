package com.edu.gy.classtime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.gy.base.BaseDao;
import com.edu.gy.classstudent.vo.ClassStudentVO;
import com.edu.gy.classtime.vo.ClassTimeCountEntity;
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.utils.DBUtil;

/**
 * 上课时间
 * @author Christy
 *
 */
public interface IClassTimeDao extends BaseDao<ClassTimeEntity>{

	/**
	 * 根据课程获取上课时间
	 * @param start
	 * @param pagesize
	 * @param classid
	 * @return
	 */
	public List<ClassTimeEntity> getClassTimeEntities(int start,int pagesize, Integer classid);

	/**
	 * 获取时间总数
	 * @param classid
	 * @return
	 */
	public Integer getClassTimeEntitiesCount(Integer classid);
	
	
	/**
	 * 获取总上课次数
	 * @param start
	 * @param pagesize
	 * @param classid
	 * @return
	 */
	public List<ClassTimeCountEntity> getClassTimeCount(Integer ctid);

	/**
	 * 通过classid获取对应的信息
	 * @param classid
	 * @return
	 */
	public ClassTimeEntity getClassTimeEntityByClassid(Integer classid);
}
