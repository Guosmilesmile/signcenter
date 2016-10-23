package com.edu.gy.classtime.dao;

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
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.entity.CourseClassEntity;
import com.edu.gy.utils.DBUtil;

public class ClassTimeDaoImpl extends BaseDaoImpl<ClassTimeEntity> implements IClassTimeDao{

	@Override
	public List<ClassTimeEntity> getClassTimeEntities(int start,int pagesize, Integer classid) {
		if(null==classid){
			return null;
		}
		List<ClassTimeEntity> list = new ArrayList<ClassTimeEntity>();
		Class  clzz = ClassTimeEntity.class;
		TableName annotation = (TableName) clzz.getAnnotation(TableName.class);
		Method[] methods = clzz.getMethods();
		String tableName = annotation.tablename();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select * from "+tableName+" where classid = "+classid;
			sql += " and  1=1  limit "+start+","+pagesize;
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				ClassTimeEntity item = new ClassTimeEntity();
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
						setMethod.invoke(item,setObject);
					}
				}
				list.add(item);
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
	public Integer getClassTimeEntitiesCount(Integer classid) {
		if(null==classid){
			return null;
		}
		Integer total = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		Class  clzz = ClassTimeEntity.class;
		TableName annotation = (TableName) clzz.getAnnotation(TableName.class);
		String tableName = annotation.tablename();
		try {
			con = DBUtil.openConnection();
			String sql = "select count(id) from "+tableName+" where classid = "+classid;
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
