package com.edu.gy.user.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Request;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;
import com.edu.gy.base.BaseDaoImpl;
import com.edu.gy.entity.UserEntity;
import com.edu.gy.utils.DBUtil;

public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements IUserDao{

	@Override
	public List<UserEntity> getUserEntity(int start, int pagesize) {
		List<UserEntity> list = new ArrayList<>();
		Class clz = UserEntity.class;
		TableName anotation = (TableName) clz.getAnnotation(TableName.class);
		String tableName = anotation.tablename();
		Method[] methods = clz.getMethods();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			//System.out.println("start connect"+new Date(System.currentTimeMillis()).toString());
			con = DBUtil.openConnection();
			//System.out.println("connect"+new Date(System.currentTimeMillis()).toString());
			String sql = "select * from "+tableName+" where";
			sql+=" 1=1 limit "+start+","+pagesize;
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				UserEntity userEntity = new UserEntity();
				for(Method method : methods){
					if(method.isAnnotationPresent(ColumnName.class)){
						ColumnName columnName = method.getAnnotation(ColumnName.class);
						String str = resultSet.getString(columnName.columnName());//以string的形式获取数据
						if(null==str){
							str = "";
						}
						String getName= method.getName();
						String setName = "set"+getName.substring(3,getName.length());
						Method setMethod = clz.getDeclaredMethod(setName,method.getReturnType());
						Constructor cons = method.getReturnType().getConstructor(String.class);//构造类似 xxx(String)的构造函数 ,xx由get的返回类型决定
						Object setObject = cons.newInstance(str);
						setMethod.invoke(userEntity,setObject);
					}
				}
				list.add(userEntity);
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
	public Integer getUserEntityCount() {
		Integer total = 0;
		Class clz = UserEntity.class;
		TableName anotation = (TableName) clz.getAnnotation(TableName.class);
		String tableName = anotation.tablename();
		Method[] methods = clz.getMethods();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select count(id) from "+tableName+" where";
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


	@Override
	public UserEntity AuthenUser(UserEntity userEntity) {
		Integer total = 0;
		Class clzz = userEntity.getClass();
		TableName anotation = (TableName) clzz.getAnnotation(TableName.class);
		String tableName = anotation.tablename();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		UserEntity result = new UserEntity();
		try {
			con = DBUtil.openConnection();
			String sql = "select id,role,password from "+tableName+" where userid =?";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			pre.setString(1, userEntity.getUserId());
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				Integer id = resultSet.getInt(1);
				Integer role = resultSet.getInt(2);
				String password = resultSet.getString(3);
				userEntity.setId(id);
				userEntity.setRole(role);
				userEntity.setPassWord(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return userEntity;
		}finally{
			try {
				resultSet.close();
				pre.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userEntity;
	}


	@Override
	public UserEntity getUserByUserid(String userid) {
		UserEntity userEntity = new UserEntity();
		Class clz = UserEntity.class;
		TableName anotation = (TableName) clz.getAnnotation(TableName.class);
		String tableName = anotation.tablename();
		Method[] methods = clz.getMethods();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select * from "+tableName+" where userid = ?";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			pre.setString(1, userid);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				for(Method method : methods){
					if(method.isAnnotationPresent(ColumnName.class)){
						ColumnName columnName = method.getAnnotation(ColumnName.class);
						String str = resultSet.getString(columnName.columnName());//以string的形式获取数据
						if(null==str){
							str = "";
						}
						String getName= method.getName();
						String setName = "set"+getName.substring(3,getName.length());
						Method setMethod = clz.getDeclaredMethod(setName,method.getReturnType());
						Constructor cons = method.getReturnType().getConstructor(String.class);//构造类似 xxx(String)的构造函数 ,xx由get的返回类型决定
						Object setObject = cons.newInstance(str);
						setMethod.invoke(userEntity,setObject);
					}
				}
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
		return userEntity;
	}


	@Override
	public List<UserEntity> getUserEntity() {
		List<UserEntity> list = new ArrayList<>();
		Class clz = UserEntity.class;
		TableName anotation = (TableName) clz.getAnnotation(TableName.class);
		String tableName = anotation.tablename();
		Method[] methods = clz.getMethods();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select * from "+tableName+" where";
			sql+=" 1=1 ";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				UserEntity userEntity = new UserEntity();
				for(Method method : methods){
					if(method.isAnnotationPresent(ColumnName.class)){
						ColumnName columnName = method.getAnnotation(ColumnName.class);
						String str = resultSet.getString(columnName.columnName());//以string的形式获取数据
						if(null==str){
							str = "";
						}
						String getName= method.getName();
						String setName = "set"+getName.substring(3,getName.length());
						Method setMethod = clz.getDeclaredMethod(setName,method.getReturnType());
						Constructor cons = method.getReturnType().getConstructor(String.class);//构造类似 xxx(String)的构造函数 ,xx由get的返回类型决定
						Object setObject = cons.newInstance(str);
						setMethod.invoke(userEntity,setObject);
					}
				}
				userEntity.setPassWord("");
				list.add(userEntity);
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

}
