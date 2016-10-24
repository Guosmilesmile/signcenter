package com.edu.gy.sign.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.entity.SignEntity;
import com.edu.gy.entity.UserEntity;
import com.edu.gy.sign.dao.ISignDao;
import com.edu.gy.sign.dao.SignDaoImpl;
import com.edu.gy.utils.FastJsonTool;

/**
 * Servlet implementation class updateSignDataServlet
 */
@WebServlet("/updateSignDataServlet")
public class updateSignDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISignDao signDao  = new SignDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateSignDataServlet() {
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
		String classid = request.getParameter("classid");
		List<SignEntity> list = FastJsonTool.getObjectList(rowstr, SignEntity.class);
		SignEntity signEntity = null;
		if(list.size()>0){
			signEntity = list.get(0);
		}
		int updateData = signDao.updateSignData(Integer.parseInt(classid), signEntity.getUserid(), signEntity.getSituation());
		response.getWriter().write(updateData+"");
	}

}
