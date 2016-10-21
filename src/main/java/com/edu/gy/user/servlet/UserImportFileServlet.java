package com.edu.gy.user.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
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

/**
 * Servlet implementation class UserImportFileServlet
 */
@WebServlet("/UserImportFileServlet")
public class UserImportFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserImportFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rootPath = this.getServletContext().getRealPath("/");
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
								for (int k = 0; k < cells.length; k++)
									sb.append(cells[k].getContents());
							}
						}
						fis.close();
						System.out.println(sb.toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}

}
