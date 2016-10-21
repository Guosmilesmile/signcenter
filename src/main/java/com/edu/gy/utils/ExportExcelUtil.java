package com.edu.gy.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Excel导出 util 工具
 * 
 */
public class ExportExcelUtil {
	public static void main(String[] args) {
		System.out.println("ddfds,sdfsdf,dfdf".replace(",", "\r\n"));
	}
	
	public final static void downCsv(HttpServletRequest request,HttpServletResponse response,String fileName, String[] Title,
			List<Map<String, Object>> listContent, String[] keys){
		System.out.println("export max > 20000 !");
		if(fileName.contains(".xls")){
			fileName = fileName.replace(".xls", "");
		}
		//String ramdon = CommUtil.getRandom()+".csv";
		String path = request.getSession().getServletContext().getRealPath("/");
		try {
			System.out.println(path + fileName);
			File f = new File(path + fileName);
			if(!f.exists()){
				f.createNewFile();
			}
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<Title.length;i++){
				if(i==0){
					sb.append(Title[i]);
				}else{
					sb.append(",").append(Title[i]);
				}	
			}
			sb.append("\n");
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f),"GBK"); 
			BufferedWriter writer = new BufferedWriter(osw);
			int length = keys.length;
			for (Map<String, Object> map : listContent) {
				for (int j = 0; j < length; j++) {
					String value = CommUtil.getString(map.get(keys[j]).toString()).replace(",", "，");
					if(j == 0){
						sb.append(value);
					}else{
						sb.append(",").append(value);
					}
				}
				sb.append("\n");
			}
			writer.write(sb.toString());
			writer.close();	
			System.out.println("download csv : "+fileName);
			FileOperateUtil.download(path + "/" + fileName, fileName+".csv",response,"GBK");
			FileOperateUtil.delete(path + "/" + fileName);
	
			
		} catch (Exception e) {
			System.out.println("writer has problem!");
			e.printStackTrace();
		}
	}


	/**
	 * excel导出，超出20000行转csv导出
	 *    
	 * 2016-1-28 下午2:11:17  
	 * @param response    导出流
	 * @param fileName    文件名
	 * @param Title       头部信息
	 * @param listContent 导出数据内容
	 * @param keys        导出数据对应key
	 * @return
	 */
	public final static String exportExcel(HttpServletRequest request,HttpServletResponse response,String fileName, String[] Title,List<Map<String, Object>> listContent, String[] keys) {
		String result = "系统提示：Excel文件导出成功！";
		
		if(listContent!=null && listContent.size() > 200000){	
			ExportExcelUtil.downCsv(request,response, fileName, Title, listContent, keys);
			return result;
		}
		if(!fileName.contains(".xls")){
			fileName = fileName +".xls";
		}
		//specialExe(fileName, listContent);
		return realExport(response, fileName, Title, listContent, keys);
	}
	
	/**
	 * 特殊处理
	 *    
	 * 2016-3-30 上午10:07:38  
	 * @param fileName
	 * @param listContent
	 */
	public final static void specialExe(String fileName,List<Map<String, Object>> listContent){
		if(CommUtil.isEmpty(fileName)){
			return ;
		}
		if(fileName.startsWith("规划特殊配置")){
			System.out.println("规划特殊配置，范围详情特殊处理,转化为换行");
			if(listContent!=null){
				for (Map<String, Object> map : listContent) {
					String _tmp = CommUtil.getString((String) map.get("value5"));
					_tmp = _tmp.replace(",", "dfd,\r\ndfdsfd");
					map.put("value5", _tmp);
				}
			}
		}
	}


	/**
	 * excel导出，超出20000行转csv导出
	 *    
	 * 2016-1-28 下午2:11:17  
	 * @param response    导出流
	 * @param fileName    文件名
	 * @param Title       头部信息
	 * @param listContent 导出数据内容
	 * @param keys        导出数据对应key
	 * @return
	 */
	public final static String exportExcelNoCsv(HttpServletResponse response,String fileName, String[] Title,List<Map<String, Object>> listContent, String[] keys) {	
		if(!fileName.contains(".xls")){
			fileName = fileName +".xls";
		}
		return realExport(response, fileName, Title, listContent, keys);
	}

	
	private static String realExport(HttpServletResponse response,String fileName, String[] Title,List<Map<String, Object>> listContent, String[] keys){
		String result = "系统提示：Excel文件导出成功！";
		try {
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.reset();// 清空输出流
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="+ fileName);
			// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型
			OutputStream os = response.getOutputStream();// 取得输出流
			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);
					

			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行
					
			// 用于正文居右
			WritableCellFormat wcf_right = new WritableCellFormat();
			wcf_right.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_right.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_right.setAlignment(Alignment.RIGHT); // 文字水平对齐
			wcf_right.setWrap(false); // 文字是否换行
					
			// 用于正文居中
			WritableCellFormat wcf_centerz = new WritableCellFormat(NormalFont);
			wcf_centerz.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_centerz.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_centerz.setAlignment(Alignment.CENTRE); // 文字水平对齐		
			wcf_centerz.setWrap(false); // 文字是否换行

			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				//设置表格宽度
				sheet.setColumnView(i, 25);
				sheet.addCell(new Label(i, 0, Title[i], wcf_center));
			}
			/** ***************以下是EXCEL正文数据********************* */		
			if(listContent!=null && listContent.size()>0){
				int i = 1;
				for (Map<String, Object> map : listContent) {
					for (int j = 0; j < keys.length; j++) {
						Object obj = "";
						if (map.get(keys[j]) != null) {
							obj = map.get(keys[j]);
						}
						if(obj instanceof Long){
							Long d  = (Long) obj;
							sheet.addCell(new jxl.write.Number(j, i, d,wcf_right));
						}else if(obj instanceof Timestamp){
							Timestamp d  = (Timestamp) obj;
							sheet.addCell(new Label(j, i, d.toString(), wcf_left));
						}else if(obj instanceof Double){
							Double d  = (Double) obj;
							sheet.addCell(new jxl.write.Number(j, i, d,wcf_right));
						}else if(obj instanceof Integer){
							Integer inter  = (Integer) obj;
							sheet.addCell(new jxl.write.Number(j, i, inter,wcf_right));
						}else if(obj instanceof Date){
							Date d  = (Date) obj;
							sheet.addCell(new jxl.write.DateTime(j, i,d,wcf_left));
						}else if(obj instanceof Float){
							Float f  = (Float) obj;
							sheet.addCell(new jxl.write.Number(j, i, f,wcf_right));
						}else{
							String str =  CommUtil.getString((String) obj);			
							if(str.equals("是")||str.equals("否")){
								sheet.addCell(new Label(j, i, str, wcf_centerz));
							}else if(str.length()<14 && PatternUtil.isRealNumber(str)){
								Double d  = Double.parseDouble(str);
								sheet.addCell(new jxl.write.Number(j, i, d, wcf_right));
							}else{
								sheet.addCell(new Label(j, i, str, wcf_left));
							}			
						}
					}
					i++;
				}
			}
		    /** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			os.close();
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
	


}
