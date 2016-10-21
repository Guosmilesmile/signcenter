package com.edu.gy.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.TextUI;
import javax.xml.soap.Text;

import com.edu.gy.entity.UserEntity;
import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.user.dao.UserDaoImpl;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static IUserDao userDao = new UserDaoImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userName);
		userEntity.setPassWord(passWord);
		if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)){
			userEntity = userDao.AuthenUser(userEntity);
			if(!TextUtils.isEmpty(userEntity.getId()+"")){
				request.getSession().setAttribute("userid", userName);
				request.getSession().setAttribute("role", userEntity.getRole()+"");
				response.sendRedirect("admin/main.jsp");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
		
	}

}
