package com.edu.gy.course.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.edu.gy.course.dao.CourseDaoImpl;
import com.edu.gy.course.dao.ICourseDao;
import com.edu.gy.courseclass.dao.CourseClassDaoImpl;
import com.edu.gy.entity.CourseEntity;

/**
 * Servlet implementation class AddCourseDataServlet
 */
@WebServlet("/AddCourseDataServlet")
public class AddCourseDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICourseDao courseDao = new CourseDaoImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseDataServlet() {
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
		Integer id = (Integer) request.getSession().getAttribute("id");
		String rowstr = request.getParameter("rowstr");
		int insertData = 0;
		try {
			JSONObject json = new JSONArray(rowstr).getJSONObject(0);
			String coursename = json.getString("courseName");
			CourseEntity t = new CourseEntity(null, coursename, id);
			insertData = courseDao.insertData(t );
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().write(insertData+"");
	}

}
