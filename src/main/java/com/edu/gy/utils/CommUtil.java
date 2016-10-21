package com.edu.gy.utils;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class CommUtil {
	//获得系统属性集   
    public static Properties props=System.getProperties();
	/**
	 * 时间long秒　加三位随机数
	 * @return
	 */
	public static String getRandom(){
		Random r = new Random();
		return System.currentTimeMillis()/1000 + "" + r.nextInt(10)+ ""+ r.nextInt(10)+ ""+ r.nextInt(10);
	}
	
	/**
	 * 时间long秒
	 * @return
	 */
	public static long getTime(){
		return System.currentTimeMillis()/1000;
	}
	
	/**
	 * 获取前分钟日期
	 * @param min 分钟
	 * @return
	 */
	public static Date getAgoMin(int min) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, -min);
		return calendar.getTime();
	}

	/**
	 * 获取前分钟日期
	 * @param min 分钟
	 * @return
	 */
	public static Date getAgoMin(int min,Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -min);
		return calendar.getTime();
	}
	
	/**
	 * 判断一个字符串不为空 和不为空字符
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		if(s==null || "".equals(s.trim()) || "null".equals(s) || "undefined".equals(s)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取数组中对象下标值，如果没值，或者数组超出返回空字符串
	 * @param lines  数组
	 * @param index  下标
	 * @return
	 */
	public static String getLine(String[] lines,int index){
		if(lines!=null && lines.length>index){
			return lines[index];
		}else{
			return "";
		}
	}
	
	
    /**
     * 获取window 本地ip地址
     * @return
     * @throws UnknownHostException
     */ 
    private static String getWinLocalIp(){ 
        InetAddress inet;       
		try {
			inet = InetAddress.getLocalHost();
			System.out.println("本机的ip=" + inet.getHostAddress()); 
			return inet.getHostAddress(); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}      
        return null;
    } 
    /**
     * 
     * 可能多多个ip地址只获取一个ip地址
     * 获取Linux 本地IP地址
     * @return
     * @throws SocketException 
     */ 
    public static InetAddress getUnixLocalIp() throws SocketException { 
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces(); 
            InetAddress ip = null;  
            while(netInterfaces.hasMoreElements())      
            {      
                NetworkInterface ni= (NetworkInterface)netInterfaces.nextElement();      
                ip=(InetAddress) ni.getInetAddresses().nextElement();      
                if( !ip.isSiteLocalAddress()      
                        && !ip.isLoopbackAddress()      
                        && ip.getHostAddress().indexOf(":")==-1)      
                {      
                    return ip; 
                }      
                else     
                {      
                    ip=null;      
                }      
            }    
        return null; 
    } 
    

	
	
	/**
	 * 获取机器域名
	 * @return
	 */
	public static String getHostName(){
		String hostName = null;
		try {
	         InetAddress addr = InetAddress.getLocalHost();// 创建本地主机IP地址对象
	         hostName = addr.getHostName();// 获取本地机器名
	         //System.out.println("本地机器名：" + hostName);
	         System.out.println("host name is : " + addr.getCanonicalHostName());
	     } catch (UnknownHostException e) {// 捕获未知主机异常
	         System.out.println("get name error:" + e.getMessage());
	     }
		return hostName;
	}
	
	/**
	 * 通过域名获取ip
	 * @param serverName
	 * @return
	 */
	public static String getIpByServerName(String serverName){
		String hostAddr = null;
		try {
	         InetAddress addr =  InetAddress.getByName(serverName);// 创建本地主机IP地址对象
	         hostAddr = addr.getHostAddress();// 获取IP地址
	         System.out.println("host ip is:" + hostAddr);
	     } catch (UnknownHostException e) {// 捕获未知主机异常
	         System.out.println("get host ip error" + e.getMessage());
	     }
		return hostAddr;
	}
	
	
    
    /**
     * 获取FTP的配置操作系统
     * @return
     */ 
    public static String getSystemOSName() { 
         //获得系统属性集   
        Properties props=System.getProperties(); 
        //操作系统名称 
        String osname=props.getProperty("os.name");   
        return osname; 
    } 
    /**
     * 获取属性的值
     * @param propertyName
     * @return
     */ 
    public static String getPropertery(String propertyName){ 
        return props.getProperty(propertyName); 
    } 
	
	
	
	

	/**
	 * 获取数组中对象下标值，如果没值，或者数组超出返回0
	 * @param lines  数组
	 * @param index  下标
	 * @return
	 */
	public static String getLineZero(String[] lines,int index){
		if(lines!=null && lines.length>index ){
			return lines[index].equals("")?"0":lines[index];
		}else{
			return "0";
		}
	}
	
	/**
	 * 字符串如果为null之类设置为空字符串
	 * @param str
	 * @return
	 */
	public static String getString(String str){
		if(CommUtil.isEmpty(str)){
			return "";
		}else{
			return str;
		}
	}
	
	/**
	 * 字符串如果为null之类返回默认字符串
	 * @param str
	 * @return
	 */
	public static String getString(String str,String defualtStr){
		if(CommUtil.isEmpty(str)){
			return defualtStr;
		}else{
			return str;
		}
	}
	/**
	 * 字符串如果为null之类设置为0
	 * @param str
	 * @return
	 */
	public static String getZeroString(String str){
		if(CommUtil.isEmpty(str)){
			return "0";
		}else{
			return str;
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str){
		try {
			Double d=Double.parseDouble(str);
			return d.intValue();
		}catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 判断两个字符串都不为空 和不为空字符
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s,String s1){
		if(CommUtil.isEmpty(s) || CommUtil.isEmpty(s1)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 传一个旧的list的Map值,更新到新的list对应indexParam的值里
	 * @param newList 新生成的list   
	 * @param oldList 旧的list 
	 * @param indexParam  从旧的list里的map获取得到坐标，该放到新的list的具体位置
	 * @param param   从旧的list里的Map获取值，放到新的list的Map里
	 * @return
	 */
	public List<Map<String,Object>> getNewList(List<Map<String,Object>> newList, List<Map<String,Object>> oldList,String indexParam,String... param){
		Map<String,Object> newMap ;
		if(newList == null){
			newList = new ArrayList<Map<String,Object>>();
		}
		if(oldList!=null && oldList.size()>0){
			for (Map<String, Object> map : oldList) {
				newMap = new HashMap<String, Object>();
				for(int i=0;i<param.length;i++){
					newMap.put(param[i], map.get(param[i]));
				}
				newList.set((Integer)map.get(indexParam),newMap);
			}
		}
		
		return newList;
	}
	
	/**
	 * 将javaBean转换成Map
	 * 
	 * @param javaBean
	 *            javaBean
	 * @return Map对象
	 */
	public static Map<String, String> toMap(Object javaBean) {
		Map<String, String> result = new HashMap<String, String>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					String value = (String) method.invoke(javaBean, (Object[]) null);					
					System.out.println(field+":"+value);
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
			}
		}

		return result;
	}


	
	/**
	 * 获取异常信息
	 * @param e
	 * @return
	 */
	public static String getExceptionInfo(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(baos));
		return baos.toString().replaceAll("\n", "\r\n");
	}
	
	public static void main(String[] args) {
		System.out.println(parseInt("2"));
	}
}
