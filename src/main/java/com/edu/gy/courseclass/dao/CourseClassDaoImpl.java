package com.edu.gy.courseclass.dao;

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
import com.edu.gy.entity.CourseClassEntity;
import com.edu.gy.utils.DBUtil;

public class CourseClassDaoImpl extends BaseDaoImpl<CourseClassEntity> implements ICourseClassDao{

	@Override
	public List<CourseClassEntity> getClassEntitiesWithCourseid(int start,int pagesize, Integer courseid) {
		if(null==courseid){
			return null;
		}
		List<CourseClassEntity> list = new ArrayList<CourseClassEntity>();
		Class  clzz = CourseClassEntity.class;
		TableName annotation = (TableName) clzz.getAnnotation(TableName.class);
		Method[] methods = clzz.getMethods();
		String tableName = annotation.tablename();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select * from "+tableName+" where courseid = "+courseid;
			sql += " and  1=1  limit "+start+","+pagesize;
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				CourseClassEntity CourseClassEntity = new CourseClassEntity();
				for(Method method : methods){
					if(method.isAnnotationPresent(ColumnName.class)){
						ColumnName columnName = method.getAnnotation(ColumnName.class);
						String str = resultSet.getString(columnName.columnName());
						if(null==str){
							str = "";
						}
						String getName= method.getName();
						String setName = "set"+getName.substring(3,getName.length());
						Method setMethod = clzz.getDeclaredMethod(setName,method.getReturnType());
						Constructor cons = method.getReturnType().getConstructor(String.class);
						Object setObject = cons.newInstance(str);
						setMethod.invoke(CourseClassEntity,setObject);
					}
				}
				list.add(CourseClassEntity);
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
	public Integer getClassEntitiesWithCourseidCount(Integer courseid) {
		if(null==courseid){
			return null;
		}
		Integer total = 0;
		Class  clzz = CourseClassEntity.class;
		TableName annotation = (TableName) clzz.getAnnotation(TableName.class);
		Method[] methods = clzz.getMethods();
		String tableName = annotation.tablename();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select count(id) from "+tableName+" where courseid = "+courseid;
			sql += " and  1=1  ";
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
