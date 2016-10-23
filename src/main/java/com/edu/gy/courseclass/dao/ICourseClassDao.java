package com.edu.gy.courseclass.dao;

import java.util.List;

import com.edu.gy.base.BaseDao;
import com.edu.gy.entity.CourseClassEntity;

/**
 * 课程班级
 * @author Christy
 *
 */
public interface ICourseClassDao extends BaseDao<CourseClassEntity>{

	/**
	 * 根据课程id获取相应的班级信息
	 * @param courseid
	 * @return
	 */
	public List<CourseClassEntity> getClassEntitiesWithCourseid(int start, int pagesize,Integer courseid);
	/**
	 * 根据课程id获取相应的班级数量
	 * @param courseid
	 * @return
	 */
	public Integer getClassEntitiesWithCourseidCount(Integer courseid);
}
