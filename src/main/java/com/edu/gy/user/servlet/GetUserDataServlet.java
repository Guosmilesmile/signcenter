package com.edu.gy.user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.entity.UserEntity;
import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.user.dao.UserDaoImpl;
import com.edu.gy.utils.FastJsonTool;

/**
 * Servlet implementation class GetUserDataServlet
 */
@WebServlet("/GetUserDataServlet")
public class GetUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserDataServlet() {
        super();
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
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html; charset=GBK");
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserEntity> list = userDao.getUserEntity( (page-1)*pageSize, pageSize);
		Integer total = userDao.getUserEntityCount();
		map.put("rows", list);
		map.put("total", total);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
