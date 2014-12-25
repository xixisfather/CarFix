/*
 * Copyright 2005 InfoService Corporation. All Rights Reserved.
 * 
 * WebSite: www.infoservice.com.cn
 * 
 * Author : Kevin
 * 
 * CrtDate: 2009-11-24
 *
 * Desc	  :  
 * 
 */
package com.selfsoft.test;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.selfsoft.framework.file.XlsReader;
import com.selfsoft.framework.file.XlsReader.XlsException;

public class TestXlsReader {
	Logger log = LogManager.getLogger(TestXlsReader.class);
	
	public void test1(String xls, String tpl) throws Exception{
		XlsReader reader = new XlsReader(this.getClass().getResourceAsStream(xls),this.getClass().getResourceAsStream(tpl));
		if (reader.isSuccess() ){
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream(tpl));
			Enumeration<?> enu= props.propertyNames();
			while( enu.hasMoreElements() ){
				String key = (String)enu.nextElement();
				if( key.startsWith("var.") ){
					System.out.println("get var "+key.substring(4)+" : "+ reader.getVar(key.substring(4)));
				}
			}
			//------------这里--------------
			List<?> li = reader.getBeans("CarInfo");
			if( li!=null ){
				System.out.println("========CarInfo=========");
				for(Object obj : li){
					System.out.println(obj);
					//数据库插入
				}
			}
			//------------这里----------------
			li = reader.getBeans("Order");
			if(li!=null){
				System.out.println("============Order=======");
				for(Object obj : li){
					System.out.println(obj);
				}
			}
		}else{
			List<XlsException> errs = reader.getExceptionStack();
			for(XlsException e: errs){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		TestXlsReader t = new TestXlsReader();
		//t.test1("/test.xls","/xls_tpl_1.properties");
		
		t.test1("/test.xls","/xls_tpl_2.properties");
	}

}
