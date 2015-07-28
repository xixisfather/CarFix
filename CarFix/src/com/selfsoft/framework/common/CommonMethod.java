package com.selfsoft.framework.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class CommonMethod {
	/**
	 * 中文转拼音 返回拼音首字母 如 中华人民共和国 返回 ZHRMGHG
	 * @param str
	 * @return 拼音
	 */
	public static String tranferToPinYin(String str){
		if(null!=str&&!"".equals(str)){
			StringBuffer sb = new StringBuffer();
			HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
			outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
			outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	        char[] strChar = str.toCharArray();
	        for(int i = 0 ; i < strChar.length ; i++){
	        	try {
					
	        		String[] transferChar = PinyinHelper.toHanyuPinyinStringArray(strChar[i], outputFormat);
				    if(null==transferChar){
				    	sb.append(strChar[i]);
				    }
				    else{
				    	if(null!=transferChar[0]){
				    		sb.append(transferChar[0].substring(0,1));
				    	}
				    	
				    }
	        	} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        return sb.toString();
		}
		
		return null;
	
	}
	/**
	 * 字符转码 
	 * @param str 要转码的字符
 	 * @param fromEncoding 从字符码
	 * @param toEncoding   转到字符码
	 * @return
	 */
	public static String TransferToEncoding(String str,String fromEncoding,String toEncoding){
		
		if(null!=str)
		{
			try {
				str = new String(str.getBytes(fromEncoding),toEncoding);
				
				return str;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * 字符串替换 将需要替换的字符替换成指定的字符
	 * @param str 要操作的字符串
	 * @param fromStr 要替换的字符串
	 * @param toStr 替换成的字符串
	 */
	public static String replaceAll(String str,char fromStr , char toStr){
		
		String returnStr = "";
		
		for(int i = 0 ; i < str.length() ; i++){
			
			char c = str.charAt(i);
			
			if(c == fromStr){
				
				c = toStr;
			
			}
			
			returnStr += c;
			
		}
		return returnStr;
	}
	/**
	 * 
	 * @param date
	 * @param format yyyy-MM-dd HH:ss:mm
	 * @return
	 */
	public static String parseDateToString(Date date,String format) {
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat(format);
		String dateString = "";
		try {

			if (date != null) {
				dateString = simpleDateFormate.format(date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;

	}
	
	public static Date parseStringToDate(String date,String format) {
		if (null != date&&!"".equals(date)) {
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			
			try {
				
				return sdf.parse(date);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Date addDate(Date date, int addDays) {
		if (null != date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, addDays);
			return c.getTime();
		}
		return null;
	}
	
	public static Date addMinute(Date date , int addMinutes){
		
		if (null != date) {
			
			Calendar c = Calendar.getInstance();
			
			c.setTime(date);
			
			c.add(Calendar.MINUTE, addMinutes);
			
			return c.getTime();
		}
		
		return null;
	}
	
	/**
	 * 保留n位小数
	 * @param sourceVal
	 * @param level			保留多少位
	 * @return
	 */
	public static Double convertRadixPoint(Double sourceVal , int level){
		BigDecimal   b   =   new   BigDecimal(sourceVal);  
		Double result   =   b.setScale(level,  BigDecimal.ROUND_HALF_UP).doubleValue();  
		return result;
	}
	
	/**
	 * 判断是否是同一天
	 */
	public static Boolean isSameDate(Date d1,Date d2){
		 Calendar c1 = Calendar.getInstance();   
		 Calendar c2 = Calendar.getInstance();   
		 c1.setTime(d1);   
		 c2.setTime(d2);   
		 return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))   
		 && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))   
		 && (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));   
	}
	
	/**
	 * 日期比较
	 */
	
	public static int compareDate(Date dt1,Date dt2){
		
		  if (dt1.getTime() > dt2.getTime()) { 
              
              return 1; 
              
          } else if (dt1.getTime() < dt2.getTime()) { 
             
              return -1; 
              
          } else { 
        	  
              return 0; 
          } 

	}
	
	/**
	 * 
	 */
	public static String getPropertiesValue(InputStream in , String prop_key){
		
		Properties properties = new Properties();
		
		try {
			
			properties.load(in);
			
			Enumeration<?> enu = properties.propertyNames();
			
			String prop_val = "";
			
			while(enu.hasMoreElements()){
				
				String key = (String) enu.nextElement();
				
				if(prop_key.equals(key)){
					
					prop_val = properties.getProperty(key);
					
					break;
					
				}
			}
			
			return prop_val;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String getMACAddress() {

		String address = "";

		String os = System.getProperty("os.name");

		System.out.println(os);

		if (os != null) {

			if (os.startsWith("Windows")) {

				try {

					ProcessBuilder pb = new ProcessBuilder("ipconfig", "/all");

					Process p = pb.start();

					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream()));

					String line;

					while (/*(line = TransferToEncoding(br.readLine(), "UTF-8", "GBk"))*/(line = br.readLine()) != null) {
						//System.out.println(line = TransferToEncoding(br.readLine(), "UTF-8", "GBk"));
						if (line.indexOf("Physical Address") != -1) {

							int index = line.indexOf(":");

							address = line.substring(index + 1);

							break;

						}

					}

					br.close();

					//return address.trim();

				} catch (IOException e) {

				}

			} else if (os.startsWith("Linux")) {

				try {

					ProcessBuilder pb = new ProcessBuilder("ifconfig");

					Process p = pb.start();

					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream()));

					String line;

					while ((line = br.readLine()) != null) {

						int index = line.indexOf("硬件地址");

						if (index != -1) {

							address = line.substring(index + 4);

							break;

						}

					}

					br.close();

					//return address.trim();

				} catch (IOException ex) {

					Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE,
							null, ex);

				}

			}

		}

		String address_send = "";
		
		if(null!=address&&!"".equals(address)){
			
			address_send = "";
			
			String[] addresses = address.split("-");
			
			for(String s : addresses){
				
					
				address_send += s;
				
			}
			
		}
		
		if(null!=address_send){
			
			address_send = address_send.trim();
			
		}
		
		return address_send;

	}
	
	public static String decryptBASE64(String key) throws Exception {   
		return new String((new BASE64Decoder()).decodeBuffer(key));   
	}   

	 
	public static String encryptBASE64(String key) throws Exception {   
	    return (new BASE64Encoder()).encodeBuffer(key.getBytes());   
	}
	
	public static int getYear(Date d){
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(d);
		
		return c.get(Calendar.YEAR);
		
	}
	
	public static int getMonth(Date d){
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(d);
		
		return c.get(Calendar.MONTH) + 1;
		
	}
	
	
	public static int getMonthDays(int year, int month) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);

		return cal.getActualMaximum(Calendar.DATE);
	}


	public static void main(String[] args) throws Exception{
		
		/*String encoding=System.getProperty("file.encoding");

		System.out.println("Default System Encoding: " + encoding);


		System.out.println(getMACAddress());*/
	
		//System.out.println(getMonthDays(2012, 7));
		
		System.out.println(encryptBASE64("112233"));
	}
	
	
}
