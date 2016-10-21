package com.edu.gy.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.gy.base.BaseDaoImpl;
import com.edu.gy.course.viewentity.CourseVO;
import com.edu.gy.entity.CourseEntity;
import com.edu.gy.utils.DBUtil;

public class CourseDaoImpl extends BaseDaoImpl<CourseEntity> implements ICourseDao{

	@Override
	public List<CourseVO> getCourseEntities(int start, int pagesize,String userid) {
		if(null==userid){
			return null;
		}
		List<CourseVO> list = new ArrayList<CourseVO>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select c_course.id,c_course.name,u_user.nickname from u_user,c_course where ";
			if(!"-1".equals(userid)){
				sql += " u_user.id = c_course.userid and u_user.userid = "+ userid+" and ";
			}
			sql += " 1=1  limit "+start+","+pagesize;
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String nickname = resultSet.getString(3);
				CourseVO courseVO = new CourseVO(id,name,nickname);
				list.add(courseVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				resultSet.close();
				pre.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}

	@Override
	public Integer getCourseEntitiesCount(String userid) {
		if(null==userid){
			return null;
		}
		Integer total = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select count(*) from u_user,c_course where ";
			if("-1".equals(userid)){
				sql += " u_user.userid = c_course.userid and u_user.userid = "+ userid;
			}
			sql += " 1=1 ";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				total = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				resultSet.close();
				pre.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}

}
