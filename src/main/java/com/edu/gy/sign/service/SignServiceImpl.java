package com.edu.gy.sign.service;

import com.edu.gy.classstudent.dao.ClassStudentDaoImpl;
import com.edu.gy.classstudent.dao.IClassStudentDao;
import com.edu.gy.classtime.dao.ClassTimeDaoImpl;
import com.edu.gy.classtime.dao.IClassTimeDao;
import com.edu.gy.courseclass.dao.CourseClassDaoImpl;
import com.edu.gy.courseclass.dao.ICourseClassDao;
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.entity.CourseClassEntity;
import com.edu.gy.entity.SignEntity;
import com.edu.gy.sign.dao.ISignDao;
import com.edu.gy.sign.dao.SignDaoImpl;
import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.user.dao.UserDaoImpl;

public class SignServiceImpl implements ISignService{

	private ISignDao signDao = new SignDaoImpl();
	private IUserDao userDao = new UserDaoImpl();
	private IClassTimeDao classTimeDao = new ClassTimeDaoImpl();
	private IClassStudentDao classStudentDao = new ClassStudentDaoImpl();
	@Override
	public int addSign(Integer classid, String user_id, Integer countid,Integer courseid,
			Long createTime, Long signTime) {
		Integer situation = 2;
		
		Integer userid = userDao.getUserByUserid(user_id).getId();
		//判断situtation。是否跨班和是否迟到   通过获取的classid获取相应的所有班级，找出该生对应的班级id，
		Integer finalclassid = classStudentDao.getClassidWithClassidandUserid(courseid, userid, classid);
		if(finalclassid == classid){
			if((signTime - createTime) > 30*60){
				situation = 1;
			}else{
				situation = 2;
			}
		}else{
			situation = 3;
		}
		SignEntity signEntity = new SignEntity(null, userid, finalclassid, signTime, situation, countid);
		int insertData = signDao.insertData(signEntity);
		return insertData;
	}

}
