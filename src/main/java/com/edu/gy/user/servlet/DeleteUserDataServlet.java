package com.edu.gy.user.servlet;

import java.io.IOException;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.user.dao.UserDaoImpl;

/**
 * Servlet implementation class DeleteUserDataServlet
 */
@WebServlet("/DeleteUserDataServlet")
public class DeleteUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userdao = new UserDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserDataServlet() {
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
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html; charset=GBK");
		String ids = request.getParameter("ids");
		int deleteDatas = userdao.deleteDatas(ids.split(","));
		response.getWriter().write(deleteDatas+"");
	}

}
