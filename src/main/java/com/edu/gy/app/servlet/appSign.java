package com.edu.gy.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.app.vo.ResponseStateVO;
import com.edu.gy.sign.dao.ISignDao;
import com.edu.gy.sign.dao.SignDaoImpl;
import com.edu.gy.sign.service.ISignService;
import com.edu.gy.sign.service.SignServiceImpl;
import com.edu.gy.utils.FastJsonTool;

/**
 * Servlet implementation class appSign
 */
@WebServlet("/appSign")
public class appSign extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISignDao signDao = new SignDaoImpl();
    private ISignService signService = new SignServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appSign() {
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
		String useruserid = request.getParameter("userid");
		String createTimeParam = request.getParameter("createtime");
		String signTimeParam = request.getParameter("signTime");
		String courseid = request.getParameter("courseid");
		int addSign = signService.addSign(Integer.parseInt(classid), useruserid, Integer.parseInt(countid),
				Integer.parseInt(courseid), Long.parseLong(createTimeParam), Long.parseLong(signTimeParam));
		ResponseStateVO responseStateVO = new ResponseStateVO();
		if(addSign==0){
			responseStateVO.setMessage("账号密码不匹配或者不存在");
			responseStateVO.setStatus("fail");
		}else{
			responseStateVO.setMessage("登陆成功");
			responseStateVO.setStatus("success");
		}
		response.getWriter().write(FastJsonTool.createJsonString(responseStateVO));
		
	}

}
