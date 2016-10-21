package com.edu.gy.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式处理工具
 */
public class PatternUtil {
	
	/**
	 * 匹配正则表达式，匹配成功返回true，失败返回false
	 * @param regEx   正则表达式
	 * @param content 匹配字符串
	 * @return
	 */
	public static boolean patternMatch (String regEx,String content){
		Pattern pat=Pattern.compile(regEx);
		Matcher mat = pat.matcher(content);		
		return mat.find();
	}
	
	
	private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher source = pattern.matcher(orginal);  
        return source.matches();  
    }  
  
    public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);  
    }  
  
    public static boolean isNegativeInteger(String orginal) {  
        return isMatch("^-[1-9]\\d*", orginal);  
    }  
  
    public static boolean isWholeNumber(String orginal) {  
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);  
    }  
      
    public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isNegativeDecimal(String orginal){  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isDecimal(String orginal){  
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);  
    }  
      
    public static boolean isRealNumber(String orginal){  
        return isWholeNumber(orginal) || isDecimal(orginal);  
    }  
    
    public static boolean isIp(String orginal){
    	return isMatch( "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$", orginal);
    }
    public static void main(String[] args) {
    	System.out.println(PatternUtil.isWholeNumber("-192.6"));
	}

}
