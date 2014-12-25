package com.selfsoft.framework.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.LinkLoopException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class XlsWriter {
	
	protected static Logger log = LogManager.getLogger(XlsWriter.class);
	
	private Map<String,XlsBean> beanMap = new HashMap<String, XlsBean>(); 
	
	private List<XlsException>errorList = new LinkedList<XlsException>();
	
	private LinkedList<XlsException> errList = new LinkedList<XlsException>();
	
	public XlsWriter(InputStream tplIn){
		try{
			this.parseTplConf(tplIn);
		}catch(Exception e){
			e.printStackTrace();
			errList.addLast(new XlsException("parse xls template config file error!",e));
			return ;
		}
	}
	
	public boolean isSuccess(){
		
		return this.errList.size()==0?true:false;
	
	}
	
	public List<XlsException> getExceptionStack(){
		
		return this.errList;
	
	}
	
	public WritableSheet exportXls(WritableSheet ws , List<?>list){
		
		try{
			for(XlsBean xlsBean : beanMap.values()){
				
				ws = this.parseBeanTitle(ws , xlsBean);
				
				ws = this.parseBean(ws, list, xlsBean);
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
			errList.addLast(new XlsException("export xls failed!",e));
		}
		
		return ws;
		
	}
	
	private WritableSheet parseBeanTitle(WritableSheet ws , XlsBean xlsBean) throws Exception{
		
		Label label = null; 
		
		for(int row = 0 ; row < xlsBean.xlsFieldList.size() ; row ++){
			
			ws.setColumnView(xlsBean.col + row, xlsBean.xlsFieldList.get(row).columnWidth);
			
			label = new Label(xlsBean.col + row,xlsBean.row, String.valueOf(xlsBean.xlsFieldList.get(row).varTitle));
			
			ws.addCell(label);
		}
		
		return ws;
		
	}
	
	private WritableSheet parseBean(WritableSheet ws , List<?>list , XlsBean xlsBean) throws Exception{
		
		int max = list.size() < xlsBean.max ? list.size() : xlsBean.max;
		
		for(int i = 0 ; i < max ; i++){
			
			Label label = null; 
			
			for(int row = 0 ; row < xlsBean.xlsFieldList.size() ; row ++){
				
				label = new Label(xlsBean.col + row,xlsBean.row + i + 1, String.valueOf(this.parseField(list.get(i), xlsBean.xlsFieldList.get(row)) == null ? "" : this.parseField(list.get(i), xlsBean.xlsFieldList.get(row))));
				
				ws.addCell(label);
			}
			
			
		}
		
		return ws;
		
	}
	
	private Object parseField(Object object , XlsField xlsField) throws Exception{
		
		String methodName = "get" + xlsField.varName.substring(0,1).toUpperCase() + xlsField.varName.substring(1);
		
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
					
					XlsBean xlsBean = new XlsBean(temp[1],properties.getProperty("bean." + temp[1] + ".zero"),properties.getProperty("bean."+temp[1]+".class"),properties.getProperty("bean."+temp[1]+".max"));
					
					log.debug("add Bean Conf: " + xlsBean);
					
					this.beanMap.put(xlsBean.beanName, xlsBean);
				}
				if("field".equals(temp[2])){
					
					XlsField xlsField = new XlsField(temp[3], properties.getProperty(key));
					
					log.debug("add Bean Filed : "+ temp[1]+"-->"+xlsField);
					
					this.beanMap.get(temp[1]).xlsFieldList.add(xlsField);
				}
				
				Collections.sort(this.beanMap.get(temp[1]).xlsFieldList);
			}
		}
		
	}
	
	private class XlsBean{
		String beanName;
		
		Integer row;
		
		Integer col;
		
		Class<?>cls;
		
		Integer max;
		
		List<XlsField>xlsFieldList;
		
		public XlsBean(String beanName , String zero, String cls , String max) throws Exception{
			this.beanName = beanName;
			
			String[] temp = zero.split(",");
			
			this.row = Integer.parseInt(temp[0]);
			
			this.col = Integer.parseInt(temp[1]);
			
			this.cls = Class.forName(cls);
			
			this.max = Integer.parseInt(max);
			
			this.xlsFieldList = new LinkedList<XlsField>();
		}

		public String toString() {
			
			return String.format("xlsBean@"+this.hashCode()+"[beanName=%1$s; zero=%2$s,%3$s; cls=%4$s; max=%5$s]", beanName,row,col,cls,max);
		}
		
		
	}
	
	private class XlsField implements Comparable{
		Integer index;
		
		String varName;
		
		String varTitle;
		
		Integer columnWidth;
		
		public XlsField(String index , String varValue) throws Exception{
			
			this.index = Integer.parseInt(index);
			
			String[] temp = varValue.split(" ");
			
			this.varName = temp[0];
			
			this.varTitle = temp[1];
			
			this.columnWidth = Integer.parseInt(temp[2]);
		}

		public String toString() {
			
			return String.format("xlsField@"+this.hashCode()+"[index=%1$s; varName=%2$s;]",index,varName);
			
		}

		public int compareTo(Object o) {
			
			XlsField xlsField = (XlsField) o;
			
			return index > xlsField.index ? 1:(index == xlsField.index ? 0 : -1);
		}
		
		
	}
	
	
	
	
	
	
	public final class XlsException extends Exception{
		private Integer row;
		
		private Integer col;

		public XlsException(String msg, Throwable t){
			super(msg,t);
		}
		
		public XlsException(Integer row, Integer col, Throwable t){
			super(t);
			this.row = row;
			this.col = col;
		}
		
		public Integer getRow() {
			return row;
		}

		public void setRow(Integer row) {
			this.row = row;
		}

		public Integer getCol() {
			return col;
		}

		public void setCol(Integer col) {
			this.col = col;
		}

		public String toString() {
			if( row!=null || row!=null )
				return "["+row+","+col+"] error : "+ super.toString();
			else
				return super.toString();
		}
		
		
	}
}
