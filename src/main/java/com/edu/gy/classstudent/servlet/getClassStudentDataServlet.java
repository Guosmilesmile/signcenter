package com.edu.gy.classstudent.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.classstudent.dao.ClassStudentDaoImpl;
import com.edu.gy.classstudent.dao.IClassStudentDao;
import com.edu.gy.classstudent.vo.ClassStudentVO;
import com.edu.gy.entity.ClassStudentEntity;
import com.edu.gy.entity.CourseClassEntity;
import com.edu.gy.utils.FastJsonTool;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class getClassStudentDataServlet
 */
@WebServlet("/getClassStudentDataServlet")
public class getClassStudentDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IClassStudentDao classStudentDao = new ClassStudentDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getClassStudentDataServlet() {
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
		String temp  = request.getParameter("classid");
		if(TextUtils.isEmpty(temp)){
			response.getWriter().write("");
			return ;
		}
		int classid = Integer.parseInt(temp);
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassStudentVO> list = classStudentDao.getClassStudentEntities( (page-1)*pageSize, pageSize,classid);
		Integer total = classStudentDao.getClassStudentEntitiesCount(classid);
		map.put("rows", list);
		map.put("total", total);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
