package com.edu.gy.sign.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.gy.annotation.ColumnName;
import com.edu.gy.annotation.TableName;
import com.edu.gy.base.BaseDaoImpl;
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.entity.SignEntity;
import com.edu.gy.entity.UserEntity;
import com.edu.gy.sign.vo.SignChartVO;
import com.edu.gy.sign.vo.SignVO;
import com.edu.gy.utils.DBUtil;

public class SignDaoImpl extends BaseDaoImpl<SignEntity> implements ISignDao{

	@Override
	public List<SignVO> getSignVOs(int start, int pagesize, Integer classid,Integer countid) {
		if(null==classid){
			return null;
		}
		if(null==countid){
			return null;
		}
		List<SignVO> list = new ArrayList<SignVO>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select u.id,u.nickname,s.`timestamp`,s.situation  from cs_classstudent as cs "+
			" LEFT JOIN s_sign as s on cs.classid = s.classid and cs.userid = s.userid "+
			" left join u_user as u on cs.userid = u.id where  cs.classid=? and (s.indexs=? or s.classid is null )";
			sql += " limit "+start+","+pagesize;
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			pre.setInt(1, classid);
			pre.setInt(2, countid);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				String userid = resultSet.getString(1);
				String nickname = resultSet.getString(2);
				String signtime = resultSet.getString(3);
				String situation = resultSet.getInt(4)+"";
				SignVO item = new SignVO(userid, nickname, signtime, situation);
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
	public List<SignVO> getSignVOs(Integer classid,Integer countid) {
		if(null==classid){
			return null;
		}
		if(null==countid){
			return null;
		}
		List<UserEntity> userList = new ArrayList<UserEntity>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select u.id,u.userid,u.nickname from cs_classstudent as cs,u_user as u where classid = ? and u.id = cs.userid";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			pre.setInt(1, classid);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				Integer id = resultSet.getInt(1);
				String userid = resultSet.getString(2);
				String nickname = resultSet.getString(3);
				UserEntity item = new UserEntity(id, userid, null, nickname, null);
				userList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SignVO> signList = new ArrayList<SignVO>();
		try {
			for(UserEntity item : userList){
				String sql = "select situation,timestamp from s_sign as s where s.userid = ? and s.indexs = ?";
				System.out.println(sql);
				pre = con.prepareStatement(sql);
				pre.setInt(1, item.getId());
				pre.setInt(2, countid);
				resultSet = pre.executeQuery();
				if(resultSet.next()){
					String situation = resultSet.getString(1);
					String signtime = resultSet.getString(2);
					SignVO signVO = new SignVO(item.getUserId(), item.getNickName(), signtime, situation);
					signList.add(signVO);
				}else{
					String situation = 0+"";
					String signtime = 0+"";
					SignVO signVO = new SignVO(item.getUserId(), item.getNickName(), signtime, situation);
					signList.add(signVO);
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
		return signList;
	}

	@Override
	public Integer getSignVOsCount(Integer classid) {
		if(null==classid){
			return null;
		}
		Integer total = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select count(id) from cs_classstudent where classid = "+classid;
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
	public Integer updateSignData(Integer classid, Integer userid,Integer situation) {
		if(null==classid){
			return 0;
		}
		if(null==userid){
			return 0;
		}
		if(null==situation){
			return 0;
		}
		Connection con = null ;
		PreparedStatement pre =null;
		int isFinish = 0;
		try {
			String sql = "update s_sign set situation=? where userid=? and classid= ?";
			System.out.println(sql);
			con = DBUtil.openConnection();
			pre = con.prepareStatement(sql);
			pre.setInt(1, situation);
			pre.setInt(2, userid);
			pre.setInt(3, classid);
			pre.execute();
			isFinish = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				pre.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isFinish;
	}

	@Override
	public SignChartVO getChartVO(Integer classid, Integer countid) {
		if(null==classid){
			return null;
		}
		if(null==countid){
			return null;
		}
		List<SignVO> list = new ArrayList<SignVO>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = DBUtil.openConnection();
			String sql = "select u.id,u.nickname,s.`timestamp`,s.situation  from cs_classstudent as cs "+
			" LEFT JOIN s_sign as s on cs.classid = s.classid and cs.userid = s.userid "+
			" left join u_user as u on cs.userid = u.id where  cs.classid=? and (s.indexs=? or s.classid is null )";
			System.out.println(sql);
			pre = con.prepareStatement(sql);
			pre.setInt(1, classid);
			pre.setInt(2, countid);
			resultSet = pre.executeQuery();
			while(resultSet.next()){
				String userid = resultSet.getString(1);
				String nickname = resultSet.getString(2);
				String signtime = resultSet.getString(3);
				String situation = resultSet.getInt(4)+"";
				SignVO item = new SignVO(userid, nickname, signtime, situation);
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
		int array =0;
		int late = 0;
		int pass = 0;
		int across = 0;
		for(SignVO item : list){
			String situation = item.getSituation();
			if("旷课".equals(situation)){
				pass ++;
			}else if("迟到".equals(situation)){
				late++;
			}else if("正常签到".equals(situation)){
				array++;
			}else if("跨班上课".equals(situation)){
				across++;
			}
		}
		SignChartVO signChartVO = new SignChartVO(array, late, across, pass);
		return signChartVO;
	}

}
