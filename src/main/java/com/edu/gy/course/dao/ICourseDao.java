package com.edu.gy.course.dao;

import java.util.List;

import com.edu.gy.base.BaseDao;
import com.edu.gy.course.viewentity.CourseVO;
import com.edu.gy.entity.CourseEntity;

/**
 * 课程管理
 * @author Christy
 *
 */
public interface ICourseDao extends BaseDao<CourseEntity>{
	
	/**
	 * 获取每个老师的课程管理信息,管理员获取全部信息
	 * @return
	 */
	public List<CourseVO> getCourseEntities(int start, int pagesize,String userid);
	

	/**
	 * 获取每个老师的课程管理信息,管理员获取全部信息的数量
	 * @return
	 */
	public Integer getCourseEntitiesCount(String userid);
}
