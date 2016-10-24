package com.edu.gy.courseclass.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.course.vo.CourseVO;
import com.edu.gy.courseclass.dao.CourseClassDaoImpl;
import com.edu.gy.courseclass.dao.ICourseClassDao;
import com.edu.gy.entity.CourseClassEntity;
import com.edu.gy.utils.FastJsonTool;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class GetClassDataServlet
 */
@WebServlet("/GetClassDataServlet")
public class GetClassDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICourseClassDao courseClassDao = new CourseClassDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClassDataServlet() {
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
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		String temp  = request.getParameter("courseid");
		if(TextUtils.isEmpty(temp)){
			response.getWriter().write("");
			return ;
		}
		int courseid = Integer.parseInt(temp);
		Map<String, Object> map = new HashMap<String, Object>();
		List<CourseClassEntity> list = courseClassDao.getClassEntitiesWithCourseid( (page-1)*pageSize, pageSize,courseid);
		Integer total = courseClassDao.getClassEntitiesWithCourseidCount(courseid);
		map.put("rows", list);
		map.put("total", total);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
