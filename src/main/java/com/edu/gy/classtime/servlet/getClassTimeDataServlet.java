package com.edu.gy.classtime.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.classstudent.vo.ClassStudentVO;
import com.edu.gy.classtime.dao.ClassTimeDaoImpl;
import com.edu.gy.classtime.dao.IClassTimeDao;
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.utils.FastJsonTool;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class getClassTimeDataServlet
 */
@WebServlet("/getClassTimeDataServlet")
public class getClassTimeDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IClassTimeDao classTimeDao = new ClassTimeDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getClassTimeDataServlet() {
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
		List<ClassTimeEntity> list = classTimeDao.getClassTimeEntities( (page-1)*pageSize, pageSize,classid);
		Integer total = classTimeDao.getClassTimeEntitiesCount(classid);
		map.put("rows", list);
		map.put("total", total);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
