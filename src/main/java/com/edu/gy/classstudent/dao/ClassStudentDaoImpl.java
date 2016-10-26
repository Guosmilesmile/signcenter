package com.edu.gy.classstudent.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;
import com.edu.gy.base.BaseDaoImpl;
import com.edu.gy.classstudent.vo.ClassStudentVO;
import com.edu.gy.course.vo.CourseVO;
import com.edu.gy.entity.ClassStudentEntity;
import com.edu.gy.entity.CourseClassEntity;
import com.edu.gy.utils.DBUtil;

public class ClassStudentDaoImpl extends BaseDaoImpl<ClassStudentEntity> implements IClassStudentDao{

	@Override
	public List<ClassStudentVO> getClassStudentEntities(int start,int pagesize, Integer classid) {
		if(null==classid){
			return null;
		}
		List<ClassStudentVO> list = new ArrayList<ClassStudentVO>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select A.id,B.userid,B.nickname from cs_classstudent as A,u_user as B  where A.userid = B.id and A.classid = "+classid;
			sql += " and  1=1  limit "+start+","+pagesize;
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				Integer id = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String nickname = resultSet.getString(3);
				ClassStudentVO classStudentEntity = new ClassStudentVO(id,userid,nickname);
				list.add(classStudentEntity);
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
	public Integer getClassStudentEntitiesCount(Integer classid) {
		if(null==classid){
			return null;
		}
		Integer total = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select count(A.id)  from cs_classstudent as A,u_user as B  where A.userid = B.id and A.classid = "+classid;
			sql += " and  1=1 ";
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

	@Override
	public Integer getClassidWithClassidandUserid(Integer courseid,
			Integer userid, Integer classid) {
		String total = "";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select classid from cs_classstudent where userid = ? and classid in "
						+"(select id from cc_courseclass where courseid = ? and id != ?)";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			pre.setInt(1, userid);
			pre.setInt(2, courseid);
			pre.setInt(3, classid);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				total = resultSet.getString(1);
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
		if("".equals(total))
			total = classid+"";
		return Integer.parseInt(total);
	}

}
