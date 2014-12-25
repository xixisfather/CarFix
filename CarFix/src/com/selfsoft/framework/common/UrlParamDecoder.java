package com.selfsoft.framework.common;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class UrlParamDecoder.
 * 
 * @author 柏俊翔
 * 对于页面使用了encodeURIComponent编码的中文参数，进行解码
 * 注意： 如果服务器的uri编码是UTF-8，则不需要采用本类进行解码
 */
public class UrlParamDecoder {
	
	/**
	 * Gets the param.
	 * 
	 * @param request the request
	 * @param name the name
	 * 
	 * @return the param
	 */
	public static String getParam(HttpServletRequest request, String name){
		String value = request.getParameter(name);
		
		if(StringUtils.isNotEmpty(value)){
			String submitType = request.getMethod();
			
			if("get".equalsIgnoreCase(submitType)){
				try {
					value = new String(value.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		
		return value;
	}
}
