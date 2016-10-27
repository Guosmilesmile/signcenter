package com.edu.gy.sign.dao;

import java.util.List;

import com.edu.gy.base.BaseDao;
import com.edu.gy.entity.SignEntity;
import com.edu.gy.sign.vo.SignChartVO;
import com.edu.gy.sign.vo.SignVO;

public interface ISignDao extends BaseDao<SignEntity>{

	/**
	 * 根据班级id和课时id获取签到情况
	 * @param start
	 * @param pagesize
	 * @param classid
	 * @param countid
	 * @return
	 */
	public List<SignVO> getSignVOs(int start,int pagesize,Integer classid,Integer countid);
	
	/**
	 * 根据班级id和课时id获取签到情况
	 * @param start
	 * @param pagesize
	 * @param classid
	 * @param countid
	 * @return
	 */
	public List<SignVO> getSignVOs(Integer classid,Integer countid);
	
	
	/**
	 * 根据班级id和课时id获取签到情况
	 * @param start
	 * @param pagesize
	 * @param classid
	 * @param countid
	 * @return
	 */
	public SignChartVO getChartVO(Integer classid,Integer countid);
	
	
	/**
	 * 根据班级id和课时id获取签到总数
	 * @param start
	 * @param pagesize
	 * @param classid
	 * @param countid
	 * @return
	 */
	public Integer getSignVOsCount(Integer classid);
	
	
	/**
	 * 根据用户id与课程id，修改签到状态
	 * @param classid
	 * @param userid
	 * @param situation
	 * @return
	 */
	public Integer updateSignData(Integer classid,Integer userid,Integer situation);
}
