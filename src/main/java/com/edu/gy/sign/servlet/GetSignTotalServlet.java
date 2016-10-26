package com.edu.gy.sign.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.sign.dao.ISignDao;
import com.edu.gy.sign.dao.SignDaoImpl;
import com.edu.gy.sign.vo.SignChartVO;
import com.edu.gy.utils.FastJsonTool;

/**
 * Servlet implementation class GetSignTotalServlet
 */
@WebServlet("/GetSignTotalServlet")
public class GetSignTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISignDao signDao = new SignDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSignTotalServlet() {
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
		String classid = request.getParameter("classid");
		String countid = request.getParameter("countid");
		SignChartVO chartVO = signDao.getChartVO(Integer.parseInt(classid), Integer.parseInt(countid));
		response.getWriter().write(FastJsonTool.createJsonString(chartVO));
	}

}
