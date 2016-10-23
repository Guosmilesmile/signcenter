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

import com.edu.gy.classtime.dao.ClassTimeDaoImpl;
import com.edu.gy.classtime.dao.IClassTimeDao;
import com.edu.gy.classtime.vo.ClassTimeCountEntity;
import com.edu.gy.entity.ClassTimeEntity;
import com.edu.gy.utils.FastJsonTool;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class getClassTimeCountDataServlet
 */
@WebServlet("/getClassTimeCountDataServlet")
public class getClassTimeCountDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IClassTimeDao classTimeDao = new ClassTimeDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getClassTimeCountDataServlet() {
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
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html; charset=GBK");
		String temp  = request.getParameter("ctid");
		if(TextUtils.isEmpty(temp)){
			response.getWriter().write("");
			return ;
		}
		int ctid = Integer.parseInt(temp);
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClassTimeCountEntity> list = classTimeDao.getClassTimeCount(ctid);
		map.put("rows", list);
		response.getWriter().write(FastJsonTool.createJsonString(map));
	}

}
