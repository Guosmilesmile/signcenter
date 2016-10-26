package com.edu.gy.courseclass.servlet;

import java.io.IOException;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.courseclass.dao.ICourseClassDao;
import com.edu.gy.courseclass.dao.CourseClassDaoImpl;

/**
 * Servlet implementation class DeleteCourseClassDataServlet
 */
@WebServlet("/DeleteCourseClassDataServlet")
public class DeleteCourseClassDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICourseClassDao courseclassdao = new CourseClassDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCourseClassDataServlet() {
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
		int deleteDatas = courseclassdao.deleteDatas(ids.split(","));
		response.getWriter().write(deleteDatas+"");
	}

}
