package com.edu.gy.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.edu.gy.entity.UserEntity;
import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.user.dao.UserDaoImpl;
import com.edu.gy.utils.FastJsonTool;

/**
 * Servlet implementation class InserUserDataServlet
 */
@WebServlet("/InserUserDataServlet")
public class InserUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userdao = new UserDaoImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserUserDataServlet() {
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
		String rowstr = request.getParameter("rowstr");
		List<UserEntity> list = FastJsonTool.getObjectList(rowstr, UserEntity.class);
		UserEntity userEntity = null;
		if(list.size()>0){
			userEntity = list.get(0);
		}
		String hashed = BCrypt.hashpw(userEntity.getPassWord(), BCrypt.gensalt());
		userEntity.setPassWord(hashed);
		int insertData = userdao.insertData(userEntity);
		response.getWriter().write(insertData+"");
	}

}
