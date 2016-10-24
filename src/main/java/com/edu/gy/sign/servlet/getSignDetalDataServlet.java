package com.edu.gy.sign.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.sign.dao.ISignDao;
import com.edu.gy.sign.dao.SignDaoImpl;
import com.edu.gy.sign.vo.SignVO;
import com.edu.gy.utils.FastJsonTool;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class getSignDetalDataServlet
 */
@WebServlet("/getSignDetalDataServlet")
public class getSignDetalDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISignDao signDao = new SignDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSignDetalDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		String temp  = request.getParameter("classid");
		if(TextUtils.isEmpty(temp)){
			response.getWriter().write("");
			return ;
		}
		int classid = Integer.parseInt(temp);
		String temp1  = request.getParameter("countid");
		if(TextUtils.isEmpty(temp1)){
			response.getWriter().write("");
			return ;
		}
		int countid = Integer.parseInt(temp1);
		Map<String, Object> map = new HashMap<String, Object>();
		List<SignVO> list = signDao.getSignVOs( (page-1)*pageSize, pageSize,classid, countid);
		Integer total = signDao.getSignVOsCount(classid);
		map.put("rows", list);
		map.put("total", total);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
