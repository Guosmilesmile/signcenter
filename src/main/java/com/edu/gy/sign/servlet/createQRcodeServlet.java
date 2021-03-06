package com.edu.gy.sign.servlet;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.edu.gy.utils.QRcodeUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Servlet implementation class createQRcodeServlet
 */
@WebServlet("/createQRcodeServlet")
public class createQRcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createQRcodeServlet() {
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
		String courseid = request.getParameter("courseid");
		Long timestamp = System.currentTimeMillis()/1000;
		JSONObject json = new JSONObject();
		json.put("classid", classid);
		json.put("countid", countid);
		json.put("time", timestamp);
		json.put("courseid", courseid);
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		QRcodeUtils.createQRcode(request, response, path, json, timestamp+"");
	}

}
