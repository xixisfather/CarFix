package com.selfsoft.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;

public class TestJava {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		/*String str = "[管理费]+[销售金额]+[修理材料费]+[修理工时费]+[修理工时]+[税金]+[总价]";
		Map<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
		int k = -1 ;
		int p = -1 ;
		for(int i = 0 ; i < str.length() ; i++){
			char c = str.charAt(i);
			if('['==c){
				k=i;
			}
			if(']'==c){
				p=i;
			}
			if(k!=-1&&p!=-1){
				map.put(k, p);
				k=-1;
				p=-1;
			}
		}
		
		List<String>list = new ArrayList<String>();
		
		for(Integer indexFrom : map.keySet()){
			
			//System.out.println(indexFrom + " " + map.get(indexFrom));
			
			//System.out.println(str.substring(indexFrom+1, map.get(indexFrom)));
			
			list.add(str.substring(indexFrom+1, map.get(indexFrom)));
			
		}
		
		for(String s : list){
			str=str.replace(s,"1" );
			str=str.replace("[","");
			str=str.replace("]","");
		}
		System.out.println(str);
	
		System.out.println(new Date());*/
		
		/*Date date = new Date();
		
		String d = CommonMethod.parseDateToString(date, "yyyy");
		
		System.out.println(d);*/
		/*Date sb=new Date(); 
		System.out.println(sb);
		 java.util.Calendar   cal   =  java.util.Calendar.getInstance();   
	        cal.setTime(sb);
	           cal.add(java.util.Calendar.MINUTE,25); 
	     
	          

	           System.out.println(cal.getTime());

*/		/*String s = "1234567890";

		System.out.println(s.substring(0, 4));*/
		
		//System.out.println(CommonMethod.isSameDate(CommonMethod.parseStringToDate("2010-01-01 23:23:12", "yyyy-MM-dd HH:ss:mm"), CommonMethod.parseStringToDate("2010-01-02 11:23:12", "yyyy-MM-dd HH:ss:mm")));
		
		
		/*Date d = new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		
		System.out.println(sdf.format(d));
		
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		
		System.out.println(sdf.format(d));*/

		/* String value = "123456";            
		 // 需要加密的内容         
		 System.out.println("原字符串为：" + value);          
		 // 加密         
		 byte[] encryptResult = encrypt(value);          
		 
		 System.out.println("加密后的值：" + encryptResult.toString());          
		 // 解密         
		 String uncryptResult = uncrypt(encryptResult);          
		 
		 System.out.println("解密后的值：" + uncryptResult); */ 

		
		//for(int i = 100000 ; i < 999999 ; i++){
			
//			String inputStr = "021";   
//			System.err.println("原文:\n" + inputStr);   
//			
//			byte[] inputData = inputStr.getBytes();   
//			String code = encryptBASE64(inputData);   
//			
//			System.err.println("BASE64加密后:\n" + code);   
//		
//			String outputStr = decryptBASE64(code);   
//			
//			System.err.println("BASE64解密后:\n" + outputStr);
			
		//}
			
//			String s = "1234567890";
//
//			System.out.println(s.substring(0, 4));
		
		String  ssss = "111?222?333";
		
		System.out.println(ssss.split("\\?")[0]);
		  
	}
	
	public static byte[] encrypt(String value) {  
		
		 byte[] bt = value.getBytes(); 
		 
		 return bt ;   
	}
	
	public static String uncrypt(byte[] bt) {  
		
		 return new String(bt,0,bt.length); 
	}
	
	 
	public static String decryptBASE64(String key) throws Exception {   
		return new String((new BASE64Decoder()).decodeBuffer(key));   
	}   

	 
	public static String encryptBASE64(byte[] key) throws Exception {   
	    return (new BASE64Encoder()).encodeBuffer(key);   
	}
}
