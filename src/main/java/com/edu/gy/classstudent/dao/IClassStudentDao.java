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
public interface IClassStudentDao extends BaseDao<ClassStudentEntity>{

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
	
	/**
	 * 获取学生应该上课的对应班级。
	 * @param courseid 课程id
	 * @param userid 用户id
	 * @param classid 班级id
	 * @return
	 */
	public Integer getClassidWithClassidandUserid(Integer courseid,Integer userid,Integer classid);
}
