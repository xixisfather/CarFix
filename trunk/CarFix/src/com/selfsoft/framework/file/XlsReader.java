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
package com.selfsoft.framework.file;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class XlsReader {
	protected static Logger log = LogManager.getLogger(XlsReader.class);
	private Sheet sheet =null;
	private LinkedList<XlsException> errList = new LinkedList<XlsException>();
	private HashMap<String,XlsVar> vars = new HashMap<String,XlsVar>();
	private HashMap<String,XlsBean> beans = new HashMap<String,XlsBean>();
	
	public XlsReader(InputStream xlsIn, InputStream tplIn){
		try{
			this.parseTplConf(tplIn);
		}catch(Exception e){
			errList.addLast(new XlsException("parse xls template config file error!",e));
			return ;
		}
		
		try{
			this.sheet = Workbook.getWorkbook(xlsIn).getSheet(0);
		}catch(Exception e){
			errList.addLast(new XlsException("parse xls inputstream error!",e));
			return ;
		}
		
		for( XlsVar var : vars.values() ){
			this.parseVar(this.sheet, var);
		}
		
		for( XlsBean bean : beans.values() ){
			this.parseBean(this.sheet, bean);
		}
	}
	
	public Object getVar(String varName) {
		if( this.vars.containsKey(varName) ){
			return this.vars.get(varName).value;
		}else{
			return null;
		}
	}
	
	public List<?> getBeans(String beanName) {
		if( this.beans.containsKey(beanName) ){
			return this.beans.get(beanName).value;
		}else{
			return null;
		}
	}
	
	public boolean isSuccess(){
		return this.errList.size()==0?true:false;
	}

	public List<XlsException> getExceptionStack(){
		return this.errList;
	}
	
	
	//==============================================================
	private String getCell(Sheet sht ,int row ,int col){
		try{
			return sht.getCell(col, row).getContents();
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	
	private void parseBean(Sheet sht, XlsBean bean){
		//找出bean的主键。
		XlsField pkf = null;
		for(XlsField field : bean.fields ){
			if( bean.pk.equals(field.name) ){
				pkf = field;
				break;
			}
		}
		
		String tmp =null;
		if( "row".equals(bean.form) ){
			LinkedList<Object> ret = new LinkedList<Object>();
			for(int row=bean.stRow-1;row<bean.stRow-1+bean.max;row++){
				//找出当前行主键是否为空，如为空则终止。
				tmp = this.getCell(sheet, row, bean.stCol-1+pkf.idx);
				if( tmp==null || tmp.trim().length()==0 ) continue;
				
				//创建对象bean
				Object obj=null;
				try{
					obj = bean.clz.newInstance();
				}catch(Exception e){
					this.errList.addLast(new XlsException("create "+bean.clz+" instance error!",e));
					break;
				}
				
				//取每列值，并设置到对象bean中。
				for(int m=0;m<bean.fields.size();m++){
					try{
						tmp = this.getCell(sheet, row, bean.stCol-1+bean.fields.get(m).idx);
						parseField(obj,bean, bean.fields.get(m), tmp);
					}catch(Exception e){
						this.errList.addLast(new XlsException(row+1,bean.stCol+bean.fields.get(m).idx, e));
					}
				}
				ret.addLast(obj);
			}
			bean.value = ret;
			
		}else if("col".equals(bean.form)){
			LinkedList<Object> ret = new LinkedList<Object>();
			for(int col=bean.stCol-1;col<bean.stCol-1+bean.max;col++){
				//找出当前行主键是否为空，如为空则终止。
				tmp = this.getCell(sheet, bean.stRow-1+pkf.idx, col); 
				if( tmp==null || tmp.trim().length()==0 ) break;
				
				//创建对象bean
				Object obj=null;
				try{
					obj = bean.clz.newInstance();
				}catch(Exception e){
					this.errList.addLast(new XlsException("create "+bean.clz+" instance error!",e));
					break;
				}
				
				//取每列值，并设置到对象bean中。
				for(int m=0;m<bean.fields.size();m++){
					try{
						tmp = this.getCell(sheet,  bean.stRow-1+bean.fields.get(m).idx , col); 
						parseField(obj, bean, bean.fields.get(m), tmp);
					}catch(Exception e){
						this.errList.addLast(new XlsException(bean.stRow+bean.fields.get(m).idx,col+1, e));
					}
				}
				ret.addLast(obj);				
			}
			bean.value = ret;
		}
	}
	
	private void parseField(Object obj, XlsBean bean, XlsField field , String tmp) throws Exception{
		if( tmp==null ) return ;
		
		Object val=null;
		if( String.class.equals(field.clz) ){
			val = tmp;
		}else if(Integer.class.equals(field.clz) ){
			val = Integer.parseInt(tmp);
		}else if(Long.class.equals(field.clz) ){
			val = Long.parseLong(tmp);
		}else if(Float.class.equals(field.clz) ){
			val = Float.parseFloat(tmp);
		}else if(Double.class.equals(field.clz)){
			val = Double.parseDouble(tmp);
		}else if(Date.class.equals(field.clz)){
			SimpleDateFormat sdf = new SimpleDateFormat(field.fmt); 
			val = sdf.parse(tmp);
		}
		
		if( obj instanceof HashMap){
			((HashMap)obj).put(field.name, val);
		}else{
			String mName = "set"+field.name.substring(0,1).toUpperCase()+field.name.substring(1);
			Method m = obj.getClass().getMethod(mName, new Class[]{field.clz});
			m.invoke(obj, val);
		}
	}
	
	private void parseVar(Sheet sht, XlsVar var){
		try{
			String tmp = this.getCell(sht, var.row-1, var.col-1);
			if( tmp ==null ) return ;
			
			if( String.class.equals(var.clz) ){
				var.value = tmp;
			}else if(Integer.class.equals(var.clz) ){
				var.value = Integer.parseInt(tmp);
			}else if(Long.class.equals(var.clz) ){
				var.value = Long.parseLong(tmp);
			}else if(Float.class.equals(var.clz) ){
				var.value = Float.parseFloat(tmp);
			}else if(Double.class.equals(var.clz)){
				var.value = Double.parseDouble(tmp);
			}else if(Date.class.equals(var.clz)){
				SimpleDateFormat sdf = new SimpleDateFormat(var.fmt); 
				var.value = sdf.parseObject(tmp);
			}
		}catch(Throwable e){
			this.errList.addLast(new XlsException(var.row,var.col,e));
		}
	}
	
	private void parseTplConf(InputStream in) throws Exception{
		Properties prop = new Properties();
		prop.load(in);
		Enumeration<?> enu = prop.propertyNames();
		
		String key =null;
		String[] tmp =null;
		while( enu.hasMoreElements() ){
			key = (String)enu.nextElement();
			if( key.startsWith("var.") ){
				XlsVar var = new XlsVar(key, prop.getProperty(key));
				log.debug("add Var Conf: "+ var);
				this.vars.put(var.name, var);
			}else if( key.startsWith("bean.") ){
				tmp = key.split("\\.");
				if( !this.beans.containsKey(tmp[1]) ){
					XlsBean bean = new XlsBean(	tmp[1],
												prop.getProperty("bean."+tmp[1]+".zero"),
												prop.getProperty("bean."+tmp[1]+".form"),
												prop.getProperty("bean."+tmp[1]+".class"),
												prop.getProperty("bean."+tmp[1]+".max"),
												prop.getProperty("bean."+tmp[1]+".pk"));
					log.debug("add Bean Conf: "+ bean);
					this.beans.put(bean.name, bean);
				}
				if( "field".equals(tmp[2]) ){
					XlsField field = new XlsField(tmp[3], prop.getProperty(key));
					log.debug("add Bean Filed : "+ tmp[1]+"-->"+field);
					this.beans.get(tmp[1]).fields.add(field);
				}				
			}
		}
	}
	
	//=====================================================================
	private class XlsBean{
		String name ;
		Integer stRow;
		Integer stCol;
		String form ;
		Class<?> clz;
		Integer max ;
		String pk;
		List<XlsField> fields ;
		List<?> value;
		public XlsBean(String beanName, String zero, String form, String clz,String max,String pk) throws Exception{
			this.name = beanName;
			String[] tmp = zero.split(",");
			this.stRow = Integer.parseInt(tmp[0]);
			this.stCol = Integer.parseInt(tmp[1]);
			this.form = form;
			this.clz = Class.forName(clz);
			this.max = Integer.parseInt(max);
			this.pk = pk;
			this.fields = new LinkedList<XlsField>();
		}
		public String toString(){
			StringBuilder sbd = new StringBuilder();
			sbd.append(String.format("XlsBean@"+hashCode()+"[name=%1$s; zero=%2$s,%3$s; form=%4$s; class=%5$s; max=%6$s; pk=%7$s]",name,stRow,stCol,form,clz,max,pk));
			return sbd.toString();
		}
	}
	private class XlsField{
		Integer idx ;
		String name ;
		Class<?> clz;
		String fmt;
		public XlsField(String idx ,String val) throws Exception{
			this.idx = Integer.parseInt(idx);
			String[] tmp = val.split(" ");
			this.name = tmp[0];
			this.clz = Class.forName(tmp[1]);
			if( tmp.length==3 ) this.fmt = tmp[2];
		}
		public String toString(){
			return String.format("XlsField@"+hashCode()+"[idx=%1$s; name=%2$s; class=%3$s; format=%4$s]",idx,name,clz,fmt);
		}
	}
	
	private class XlsVar{
		String name ;
		Integer row ;
		Integer col ;
		Class<?> clz ;
		String fmt ;
		Object value;
		public XlsVar(String key, String conf) throws Exception{
			name = key.substring("var.".length());
			String[] tmp = conf.split(" ");
			this.row = Integer.parseInt(tmp[0].substring(0, tmp[0].indexOf(",")));
			this.col = Integer.parseInt(tmp[0].substring(tmp[0].indexOf(",")+1));
			this.clz = Class.forName(tmp[1]);
			if( tmp.length==3 ) this.fmt = tmp[2];
		}
		public String toString(){
			return String.format("XlsVar@"+hashCode()+"[name=%1$s; point=%2$s,%3$s; class=%4$s; format=%5$s]",name,row,col,clz,fmt);
		}
	}
	
	public final class XlsException extends Exception{
		private static final long serialVersionUID = -5520675871822945703L;
		private Integer row ;
		private Integer col ;
		
		public XlsException(String msg, Throwable t){
			super(msg,t);
		}		
		public XlsException(Integer row, Integer col, Throwable t){
			super(t);
			this.row = row;
			this.col = col;
		}		
		public Integer getRow(){
			return this.row;
		}		
		public Integer getCol(){
			return this.col;
		}		
		public String toString(){
			if( row!=null || row!=null )
				return "["+row+","+col+"] error : "+ super.toString();
			else
				return super.toString();
		}		
	}

}
