package com.edu.gy.classstudent.dao;

import java.util.List;

import com.edu.gy.base.BaseDao;
import com.edu.gy.classstudent.vo.ClassStudentVO;
import com.edu.gy.entity.ClassStudentEntity;
/**
 * 班级学生名单
 * @author Christy
 *
 */
public interface IClassStudentDao extends BaseDao<ClassStudentVO>{

	/**
	 * 根据班级id获取学生名单
	 * @param classid
	 * @return
	 */
	public List<ClassStudentVO> getClassStudentEntities(int start,int pagesize, Integer classid);
	
	/**
	 * 根据班级id获取学生名单数量
	 * @param classid
	 * @return
	 */
	public Integer getClassStudentEntitiesCount(Integer classid);
}
