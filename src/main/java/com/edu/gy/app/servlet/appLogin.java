package com.edu.gy.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.edu.gy.app.vo.ResponseStateVO;
import com.edu.gy.entity.UserEntity;
import com.edu.gy.user.dao.UserDaoImpl;
import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.utils.FastJsonTool;
import com.edu.gy.utils.RSAUtils;
import com.edu.gy.utils.TextUtils;

/**
 * Servlet implementation class appLogin
 */
@WebServlet("/appLogin")
public class appLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appLogin() {
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
		ResponseStateVO responseStateVO = new ResponseStateVO();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		try {
			if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)){
				String encryptString = RSAUtils.decryptString(passWord);
				UserEntity userEntity = new UserEntity();
				userEntity.setUserId(userName);
				userEntity.setPassWord(encryptString);
				userEntity = userDao.AuthenUser(userEntity);
				if(BCrypt.checkpw(encryptString, userEntity.getPassWord())){
					responseStateVO.setMessage("登陆成功");
					responseStateVO.setStatus("success");
				}else{
					responseStateVO.setMessage("账号密码不匹配或者不存在");
					responseStateVO.setStatus("fail");
				}
			}else{
				responseStateVO.setMessage("账号密码不匹配或者不存在");
				responseStateVO.setStatus("fail");
			}
		} catch (Exception e) {
			responseStateVO.setMessage("账号密码不匹配或者不存在");
			responseStateVO.setStatus("fail");
		}
		response.getWriter().write(FastJsonTool.createJsonString(responseStateVO));
	}

}
