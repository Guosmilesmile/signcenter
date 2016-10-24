package com.edu.gy.utils;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


public class QRcodeUtils {

	/**
	 * 
	 * @param filePath
	 * @param jObject
	 */
	public static void createQRcode(String filePath,JSONObject json,String fileName){
		try {
	        String content = json.toJSONString();// 内容  
	        int width = 300; // 图像宽度  
	        int height = 300; // 图像高度  
	        String format = "png";// 图像类型  
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
	                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
	        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
	        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 浏览器直接下载
	 * @param request
	 * @param response
	 * @param filePath
	 * @param jObject
	 * @param fileName
	 */
	public static void createQRcode(HttpServletRequest request,HttpServletResponse response,String filePath,JSONObject json,String fileName){
		try {
			System.out.println(filePath);
			File f = new File(filePath);
			if(!f.exists()){
				f.createNewFile();
			}
			fileName += ".png";
	        String content = json.toJSONString();// 内容  
	        int width = 300; // 图像宽度  
	        int height = 300; // 图像高度  
	        String format = "png";// 图像类型  
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
	                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
	        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
	        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
	        FileOperateUtil.download(path.toString(), fileName,response,"GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
