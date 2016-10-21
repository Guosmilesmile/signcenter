package com.edu.gy.utils;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *    
 * 类名称：FileOperateUtil   
 * 类描述：文件操作工具类
 * 备注：   
 * @version    
 *
 */
public class FileOperateUtil{
	protected static Logger logger = LoggerFactory.getLogger(FileOperateUtil.class);
	/**
	 * 判断文件是否存在
	 * @param pathFile  文件路径名
	 * @return
	 */
	public static boolean isExist(String pathFile) {
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在");
			return false;
		} else {
			logger.info("文件存在");
			return true;
		}
	}
	
	/**
	 * 删除文件
	 * @param dir
	 */
	public static void delete(String filePath) {
		File file = new File(filePath);
		// 如果不存在就返回
		if (!file.exists()) {
			return ;
		} else {
			logger.info("删除临时文件："+filePath);
			file.delete();
		}
	}

	/**
	 * 创建文件夹，如果文件夹不存在则会创建
	 * @param dir
	 */
	public static void makeDir(String dir) {
		File file = new File(dir);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			logger.info(dir+"//不存在,创建文件夹");
			file.mkdirs();
		} else {
			logger.info(dir+"//目录存在");
		}
	}
	
	/**
	 * 创建文件   
	 * 2015-7-7 下午4:41:30  
	 * @param filePath
	 */
	public static void createFile(String filePath){
		File file = new File(filePath);
		if (!file.exists() && !file.isDirectory()) {
			logger.info(filePath+"//不存在,创建文件夹");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}
	
	/**
	 * 读取文件 文件为一行一行数据，读取到string数组里
	 * @param pathFile  文件
	 * @param maxCount  最大行数
	 * @return
	 */
	public static String[] reader (String pathFile,int maxCount){
		if(maxCount<0){
			maxCount = 100000;
		}
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), "utf-8"));
			int loop = 0;
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				if(loop == maxCount){
					break;
				}
				sb.append(line);
				sb.append("\n");
				loop++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取异常 - {}",pathFile);
			return null;
		}
		String[] strs = {};
		if(sb.toString().trim().length() > 0){
			strs= sb.toString().split("\n");
		}
		return strs;
	}
	
	/**
	 * 读取文件 文件为一行一行数据，读取到List<String>里
	 * @param pathFile  文件
	 * @return
	 */
	public static List<String> fileToList(String pathFile) {
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return null;
		}
		
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile), "utf-8"));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取异常 - {}",pathFile);
			return null;
		}
		return list;
	}
	
	/**
	 * 读取文件 文件为一行一行数据，读取到List<String>里
	 * @param pathFile  文件
	 * @return
	 */
	public static List<Map<String,String>> fileToListMap(String pathFile,String suffix,int count) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return list;
		}
		try {
			BufferedReader brs        = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile), "utf-8"));
			String[] lines            = null;
			Map<String,String> newMap = null;
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				newMap = new HashMap<String, String>();
				lines = line.split(suffix);
				for (int i=0;i<count;i++) {
					newMap.put("value"+i, CommUtil.getLine(lines, i));
				}					
				list.add(newMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取一次 - {}",pathFile);
			return list;
		}
		return list;
	}
	
	/**
	 * 读取文件 文件为一行一行数据，读取到List<String>里
	 * @param pathFile  文件
	 * @return
	 */
	public static List<String> fileToList(String pathFile,int maxCount) {
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return null;
		}
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile), "utf-8"));
			int loop = 0;
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				if(loop == maxCount){
					break;
				}
				loop++;
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取异常 - {}",pathFile);
			return null;
		}
		return list;
	}
	

	/**
	 * 读取文件 文件为一行一行数据，读取到string数组里
	 * @param pathFile  文件
	 * @return
	 */
	public static String[] readerFileToStrings(String pathFile) {
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile), "utf-8"));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				sb.append(line);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取异常 - {}",pathFile);
			return null;
		}
		String[] strs = {};
		if(sb.toString().trim().length() > 0){
			strs= sb.toString().split("\n");
		}
		return strs;
	}
	public static String reader(String pathFile){
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件没找到-{}",pathFile);
			return "";
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), "utf-8"));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件没找到！");
			return "";
		}
		return sb.toString();
	}
	
	
	public static String reader(String pathFile,String suffix){
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件没找到！");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), "utf-8"));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				sb.append(line+suffix);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件没找到！");
			return "";
		}
		return sb.toString();
	}
	
	/**
	*增加参数：编码格式	
	 */
	public static String readerWithEncode(String pathFile,String suffix, String enCode){
		if(CommUtil.isEmpty(enCode)){
			enCode = "utf-8";
		}
		
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return "";
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), enCode));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				sb.append(line+suffix);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取异常- {}",pathFile);
			return "";
		}
		return sb.toString();
	}
	

	/**
	 * 读取文件 文件为一行一行数据，读取到string数组里
	 * @param pathFile
	 * @return
	 */
	public static String readerFileToString(String pathFile) {	
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件不存在 - {}",pathFile);
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), "utf-8"));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				sb.append(line);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件读取异常 - {}",pathFile);
			return "";
		}
		return sb.toString();
	}
	
	/**
	 * 写文件，加锁
	 * @param file_path  文件
	 * @param result     内容
	 */
	public static void writeLockToFile(String file_path, String result) {
		logger.info("写文件："+file_path);
		try {
			logger.info("writer file " + file_path);
			File f = new File(file_path);
			if(!f.exists()){
				f.createNewFile();
			}
			//对该文件加锁  
            RandomAccessFile out = new RandomAccessFile(f, "rw");  
            FileChannel fcout=out.getChannel();  
            FileLock flout=null;  
            while(true){    
                try {  
                    flout = fcout.tryLock();  
                    break;  
                } catch (Exception e) {  
                     System.out.println("有其他线程正在操作该文件，当前线程休眠1000毫秒");   
                     try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
                }         
            } 
            out.write(result.getBytes("utf-8"));  
            flout.release();  
            fcout.close();  
            out.close();  
            out=null;       
		} catch (IOException e) {
			logger.info("writer has problem!");
			e.printStackTrace();
		}
	}

	/**
	 * 写文件
	 * @param file_path  文件
	 * @param result     内容
	 */
	public static void writeToFile(String file_path, String result) {
		try {
			logger.info("writer file " + file_path);
			File f = new File(file_path);
			if(!f.exists()){
				f.createNewFile();
			}
			OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			out.write(result);
			out.close();
		} catch (IOException e) {
			logger.info("writer has problem!");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 文件追加 
	 * @param path
	 * @param content
	 */
	public static boolean fileAppend(String path, String content){
		try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(path, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}
	
	/**
	 * 从控制台接收用户输入的数据,并存储在磁盘上
	 * @param filePath
	 */
	static void userPrint(String filePath){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			//通过System.in返回一个InputStream对象用于构造一个InputStreamReader对象
			//再用来构造一个Buffered对象
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new FileWriter(filePath,true));   //true表示是否追加
			String str = br.readLine();	//接收用户输入
			while(!str.equals("exit")){	//如果用户输入exit则退出循环
				bw.write(str);			//将用户输入的字符串写入文件
				bw.newLine();			//换行
				bw.flush();		        //刷新缓冲区,将缓冲区的字符写入磁盘!
				str = br.readLine();	//继续接收输入
			}
		}
		catch(FileNotFoundException e){
			logger.info(e.getMessage());
		}
		catch(IOException e){
			logger.info(e.getMessage());
		}
		finally{
			try {
				bw.close();		//关闭对象前会调用bw.flush();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 控制打印信息输入到文件
	 * @param file
	 */
	public static void setSystemOut(String file){
		File f = new File(file);	
		try {
			if(!FileOperateUtil.isExist(file)){
				f.createNewFile();
			}
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(f,true);
			PrintStream printStream = new PrintStream(fileOutputStream);
			System.setOut(printStream);
			logger.info("默认输出到控制台的这一句，输出到了文件 out.txt");
		} catch (Exception e) {
			logger.info("输入打印实现失败！");
			e.printStackTrace();
		}

	}
	
	/**
	 * 下载文件
	 * @param path      文件路径
	 * @param response  输出流
	 * @return
	 * @throws Exception 
	 */
	public static HttpServletResponse download(String filePath, HttpServletResponse response) throws Exception {
		
        logger.info("下载文件:"+filePath);
		File file = new File(filePath);
		
		//long length = file.length();
		String filename = file.getName();
		// 清空response
		response.reset();	
		//response.setCharacterEncoding("utf-8");
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/octet-stream;");
		//response.setContentType("application/x-download; charset=utf-8");
		InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
		OutputStream toClient = new BufferedOutputStream( response.getOutputStream());
		//byte[] buffer = new byte[fis.available()];
		byte[] buffer = new byte[1024 * 1024 * 4];   
        int i = -1;
        while ((i = fis.read(buffer)) != -1) {   
            toClient.write(buffer, 0, i);  
        }   
		//fis.read(buffer);
		fis.close();	
		toClient.flush();
		toClient.close();
		return response;
    }
	
	/**
	 * 下载文件
	 * @param path      文件路径
	 * @param response  输出流
	 * @return
	 * @throws Exception 
	 */
	public static HttpServletResponse download(String filePath,String fileName,HttpServletResponse response,String code) throws Exception {
		
        logger.info("下载文件:"+filePath);
		File file = new File(filePath);
		// 清空response
		response.reset();	
		//response.setCharacterEncoding("utf-8");
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("GBK"),"iso8859-1")+"\"");
		response.addHeader("Content-Length", "" + file.length());
		//response.setContentType("application/octet-stream; charset="+code);
		response.setContentType("application/x-download; charset="+code);
		InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
		OutputStream toClient = new BufferedOutputStream( response.getOutputStream());
		//byte[] buffer = new byte[fis.available()];
		byte[] buffer = new byte[1024 * 1024 * 4];   
        int i = -1;
        while ((i = fis.read(buffer)) != -1) {   
            toClient.write(buffer, 0, i);  
        }   
		//fis.read(buffer);
		fis.close();	
		toClient.flush();
		toClient.close();
		return response;
    }

	/**
	 * 下载文件（本地文件）
	 * @param response
	 * @param fileName
	 * @throws FileNotFoundException
	 */
    public static void downloadLocal(HttpServletResponse response,String filePath) throws Exception {
        logger.info("下载文件:"+filePath);
    	// 读到流中
        InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载网络文件
     * @param response
     * @param urlStr    url地址
     * @param filePath  文件路径
     * @throws MalformedURLException
     */
    public static void downloadNet(HttpServletResponse response,String urlStr,String filePath) throws Exception {
    	logger.info("下载文件:"+filePath);
    	// 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        URL url = new URL(urlStr);
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            @SuppressWarnings("resource")
			FileOutputStream fs = new FileOutputStream(filePath);
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当前路径，是否为目录
     * @param path
     * @return
     */
    public static boolean isDir(String path){

    	File file = new File(path);
    	return file.isDirectory();
    }
    
    public static byte[] readerToBytes(String pathFile){
    	File f = new File(pathFile);
		if (!f.exists()) {
			return null;
		}
		try {
			FileInputStream inStream = new FileInputStream(f);
			 byte[] data = readInputStream(inStream); 
			 return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray(); 
    }  
    
    public static List reader2List(String pathFile){
    	List list = new ArrayList();
		File f = new File(pathFile);
		if (!f.exists()) {
			logger.info("文件没找到！");
			return list;
		}
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), "utf-8"));
			while (true) {
				String line = brs.readLine();
				if (line == null) {
					brs.close();
					break;
				}
				list.add(line);
				//sb.append(line+suffix);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件没找到！");
			return list;
		}
		return list;
	}
    
    public static void main(String[] args) {
    	logger.info("=======开始========");
		String result = "22222222222222222222你好的";
		for(int i=0;i<10;i++){
			FileOperateUtil.writeLockToFile("E://test.txt", result);
		}
		
	}

}
