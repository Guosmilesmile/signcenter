package com.edu.gy.utils;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 *    
 * 类名称：ShellUtil <br/>
 * 类描述：调用shell命令等工具类  <br/>
 * 备注：   <br/> 
 * @version v1.0   
 *
 */
public class ShellUtil{
	
	//执行脚本
	public static String execShell(String cmds){
		String result =  runShellToString(cmds);
		return result;
	}
	
	/**
	 * 运行shell 	直接服务器上调用
	 * @param shStr 需要执行的shell
	 * @return
	 * @throws IOException
	 * 注:如果sh中含有awk,一定要按new String[]{"/bin/sh","-c",shStr}写,才可以获得流
	 */
	public static String runShellToString(String shStr) {
		System.out.println("do shell：：：>>>>"+shStr);
		try{
		Process process;
		process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr},null,null);
		InputStreamReader ir = new InputStreamReader(process.getInputStream(),"utf-8");
		LineNumberReader input = new LineNumberReader(ir);
		String line;		
		StringBuffer sb = new StringBuffer();
		while ((line = input.readLine()) != null){
			sb.append(line);
			sb.append("\n");
		}	
		System.out.println("exec result:"+sb.toString());
		process.waitFor();
		return sb.toString(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static void main(String[] args) throws IOException {
		runShellToString("sh /var/ROAS/process/resource/roas_backup_sheme/roas_backup_sheme_lock.sh 'jyw_xb_lz_zu1,jyw_xb_lz_zu2' 1 0 1234qq.paogao 1");
		System.out.println("为中华民族伟大复兴敲代码！");
	}





}
