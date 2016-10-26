package com.edu.gy.course.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.course.dao.CourseDaoImpl;
import com.edu.gy.course.dao.ICourseDao;
import com.edu.gy.course.vo.CourseVO;
import com.edu.gy.utils.FastJsonTool;

/**
 * Servlet implementation class GetCourseDataServlet
 */
@WebServlet("/GetCourseDataServlet")
public class GetCourseDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICourseDao courseDao = new CourseDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseDataServlet() {
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
		Map<String, Object> map = new HashMap<String, Object>();
		Object sessUserid = request.getSession().getAttribute("userid");
		Object sessRole =  request.getSession().getAttribute("role");
		String userid = null;
		if(null==sessUserid || null== sessRole){
			
		}else{
			if("0".equals(sessRole)){
				userid= "-1";
			}else{
				userid = sessUserid+"";
			}
		}
		List<CourseVO> list = courseDao.getCourseEntities( (page-1)*pageSize, pageSize,userid);
		Integer total = courseDao.getCourseEntitiesCount(userid);
		map.put("rows", list);
		map.put("total", total);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
