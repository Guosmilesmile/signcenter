package com.edu.gy.courseclass.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edu.gy.courseclass.dao.CourseClassDaoImpl;
import com.edu.gy.courseclass.dao.ICourseClassDao;
import com.edu.gy.entity.CourseClassEntity;

/**
 * Servlet implementation class UpdateClassDataServlet
 */
@WebServlet("/UpdateClassDataServlet")
public class UpdateClassDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICourseClassDao courseClassDao = new CourseClassDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClassDataServlet() {
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
		int updateData =0;
		try {
			JSONArray jsonArray = new JSONArray(rowstr);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			String classname = jsonObject.getString("classname");
			Integer id = jsonObject.getInt("id");
			CourseClassEntity courseClassEntity  = new CourseClassEntity(id,null,classname);
			updateData = courseClassDao.updateData(courseClassEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write(updateData+"");
	}

}
