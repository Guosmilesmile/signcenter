package com.edu.gy.classtime.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edu.gy.classtime.dao.ClassTimeDaoImpl;
import com.edu.gy.classtime.dao.IClassTimeDao;
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.entity.CourseClassEntity;

/**
 * Servlet implementation class UpdateClassTimeServlet
 */
@WebServlet("/UpdateClassTimeServlet")
public class UpdateClassTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClassTimeDao classTimeDao = new ClassTimeDaoImpl();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClassTimeServlet() {
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
			Integer index = jsonObject.getInt("index");
			String classtime = jsonObject.getString("classtime");
			Integer count = jsonObject.getInt("count");
			Integer id = jsonObject.getInt("id");
			ClassTimeEntity classTimeEntity = new ClassTimeEntity(id, null, classtime, index, count);
			updateData = classTimeDao.updateData(classTimeEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write(updateData+"");
	}
}
