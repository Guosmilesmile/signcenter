package com.edu.gy.classstudent.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.classstudent.dao.ClassStudentDaoImpl;
import com.edu.gy.classstudent.dao.IClassStudentDao;

/**
 * Servlet implementation class removeClassStudentServlet
 */
@WebServlet("/removeClassStudentServlet")
public class removeClassStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IClassStudentDao classStudentDao = new ClassStudentDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeClassStudentServlet() {
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
		String ids = request.getParameter("ids");
		int deleteDatas = classStudentDao.deleteDatas(ids.split(","));
		response.getWriter().write(deleteDatas+"");
	}

}
