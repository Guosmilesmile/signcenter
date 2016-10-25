package com.edu.gy.classstudent.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.edu.gy.classstudent.dao.ClassStudentDaoImpl;
import com.edu.gy.classstudent.dao.IClassStudentDao;
import com.edu.gy.entity.ClassStudentEntity;
import com.edu.gy.entity.UserEntity;
import com.edu.gy.user.dao.IUserDao;
import com.edu.gy.user.dao.UserDaoImpl;

/**
 * Servlet implementation class importClassStudentServlet
 */
@WebServlet("/importClassStudentServlet")
public class importClassStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();
    private IClassStudentDao classStudentDao = new ClassStudentDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public importClassStudentServlet() {
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
		String rootPath = this.getServletContext().getRealPath("/");
		String classid =  request.getParameter("classid");
		String courseid =  request.getParameter("courseid");
		List<ClassStudentEntity> list = new ArrayList<ClassStudentEntity>();
		System.out.println(rootPath);
		rootPath += "/upload";
		File file = new File(rootPath);
		if (!file.exists()) {
			file.mkdir();
		}
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查输入请求是否为multipart表单数据。

		if (isMultipart == true) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {// 如果是普通表单项目，显示表单内容。
						if("classid".equals(item.getFieldName())){
							classid = item.getString();
						}else if("courseid".equals(item.getFieldName())){
							courseid = item.getString();
						}
					} else {// 如果是上传文件，显示文件名。
						String name = item.getName();
						String suffx = name.substring(
								name.lastIndexOf(".") + 1, name.length());
						File outFile = new File(rootPath + "/"
								+ System.currentTimeMillis() + "." + suffx);
						item.write(outFile);
						FileInputStream fis = new FileInputStream(outFile);
						StringBuilder sb = new StringBuilder();  
						Workbook rwb = Workbook.getWorkbook(fis);
						Sheet[] sheet = rwb.getSheets();
						for (int i = 0; i < sheet.length; i++) {
							Sheet rs = rwb.getSheet(i);
							for (int j = 0; j < rs.getRows(); j++) {
								Cell[] cells = rs.getRow(j);
								for (int k = 0; k < cells.length; k++){
									sb.append(cells[k].getContents());
									UserEntity temp = userDao.getUserByUserid(cells[k].getContents());
									if(null == temp.getId()){
										throw new Exception();
									}
									list.add(new ClassStudentEntity(null,Integer.parseInt(classid),temp.getId()));
								}
							}
						}
						for(ClassStudentEntity t : list)
							classStudentDao.insertData(t);
						fis.close();
						System.out.println(sb.toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				String url  = "admin/manageclassstudent.jsp?classid="+classid+"&courseid="+courseid+"&mess=2";
				System.out.println(url);
				//request.getRequestDispatcher(url).forward(request,response);
				response.sendRedirect(url);
				return ;
			}
		} else {

		}
		String url  = "admin/manageclassstudent.jsp?classid="+classid+"&courseid="+courseid+"&mess=1";
		System.out.println(url);
		response.sendRedirect(url);
		//request.getRequestDispatcher(url).forward(request,response);
	}

}
