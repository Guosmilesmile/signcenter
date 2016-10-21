package com.edu.gy.utils;


/**
 * 文本操作工具类
 * 
 * @author guoy1
 * 
 */
public class TextUtils {

	/**
	 * 判断是否为空
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		if (null == text || "".equals(text)) {
			return true;
		}
		return false;
	}
}
