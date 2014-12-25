package com.selfsoft.framework.file;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class PdfWriter {
	
	private Map<String,PdfBean> beanMap = new HashMap<String, PdfBean>(); 
	
	public Object getValue(Object object , String fieldName) {
		
		Object val = null;
		try{
			for(PdfBean pdfBean : beanMap.values()){
				
			val = this.parseBean(object, fieldName, pdfBean);
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}
	
	public PdfWriter(InputStream tplIn) throws Exception{
		
		this.parseTplConf(tplIn);
	}
	
	private Object parseBean(Object object,String fieldName,PdfBean pdfBean) throws Exception{
		
		Object val = null;
		
		for(int row = 0 ; row < pdfBean.pdfFieldList.size() ; row ++){
			
			if(fieldName.equals(pdfBean.pdfFieldList.get(row).varName)){
				
				val = parseField(object,pdfBean.pdfFieldList.get(row));
				
				break;
			}
			
		}
		return val;
	}
	
	private Object parseField(Object object , PdfField pdfField) throws Exception{
		
		String methodName = "get" + pdfField.varName.substring(0,1).toUpperCase() + pdfField.varName.substring(1);
		
		Method method = object.getClass().getMethod(methodName);
		
		return method.invoke(object);
	}
	
	private void parseTplConf(InputStream in) throws Exception{
		
		Properties properties = new Properties();
		
		properties.load(in);
		
		Enumeration<?> enu = properties.propertyNames();
		
		String key = null;
		
		String[] temp = null;
		
		while(enu.hasMoreElements()){
			
			key = (String) enu.nextElement();
			
			if(key.startsWith("bean.")){
				
				temp = key.split("\\.");
			
				if(!this.beanMap.containsKey(temp[1])){
					
					PdfBean pdfBean = new PdfBean(temp[1],properties.getProperty("bean." + temp[1] + ".class"));
					
					this.beanMap.put(pdfBean.beanName, pdfBean);
				}
				if("field".equals(temp[2])){
					
					PdfField pdfField = new PdfField(temp[3], properties.getProperty(key));
					
					this.beanMap.get(temp[1]).pdfFieldList.add(pdfField);
				}
				
			}
		}
		
	}
	
	
	private class PdfBean{
		
		String beanName;
		
		Class<?> clz;
		
		List<PdfField> pdfFieldList;
		
		public PdfBean(String beanName,String clz) throws Exception{
			
			this.beanName = beanName;
			
			this.clz = Class.forName(clz);
			
			this.pdfFieldList = new LinkedList<PdfField>();
		}
	}
	
	
	private class PdfField{
		
		Integer index;
		
		String varName;
		
		public PdfField(String index , String varValue){
			
			this.index = Integer.valueOf(index);
			
			this.varName = varValue;
		}
	}
}
