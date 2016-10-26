package com.edu.gy.course.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edu.gy.course.dao.CourseDaoImpl;
import com.edu.gy.course.dao.ICourseDao;
import com.edu.gy.entity.CourseEntity;

/**
 * Servlet implementation class UpdataeCourseDataServlet
 */
@WebServlet("/UpdataeCourseDataServlet")
public class UpdataeCourseDataServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
    private ICourseDao courseDao = new CourseDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataeCourseDataServlet() {
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
		String rowstr = request.getParameter("rowstr");
		System.out.println(rowstr);
		int updateData =  0;
		try {
			
			JSONArray jsonArray = new JSONArray(rowstr);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			String id  = jsonObject.getString("id");
			String coursename = jsonObject.getString("courseName");
			CourseEntity t = new CourseEntity(Integer.parseInt(id),coursename, null);
			updateData = courseDao.updateData(t );
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write(updateData+"");
	}

}
