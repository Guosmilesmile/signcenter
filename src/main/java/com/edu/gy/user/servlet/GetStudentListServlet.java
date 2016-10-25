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
 * Servlet implementation class GetStudentListServlet
 */
@WebServlet("/GetStudentListServlet")
public class GetStudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentListServlet() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		List<UserEntity> list = userDao.getUserEntity();
		response.getWriter().write(FastJsonTool.createJsonString(list));
	}

}
