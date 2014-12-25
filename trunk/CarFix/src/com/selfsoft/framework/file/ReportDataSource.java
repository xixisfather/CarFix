package com.selfsoft.framework.file;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ReportDataSource implements JRDataSource{

	private List<?> dataSourceList;
	
	private int index = -1 ; 
	
	private String dataSourceTpl;
	
	public ReportDataSource(){}
	
	public List<?> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<?> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDataSourceTpl() {
		return dataSourceTpl;
	}

	public void setDataSourceTpl(String dataSourceTpl) {
		this.dataSourceTpl = dataSourceTpl;
	}

	public Object getFieldValue(JRField field) throws JRException {
		// TODO Auto-generated method stub
		String fieldName=field.getName();
		
		try {
			PdfWriter pdfWriter = new PdfWriter(this.getClass().getResourceAsStream(this.dataSourceTpl));
		
			Object object = dataSourceList.get(index);
			
			return pdfWriter.getValue(object, fieldName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		
		index ++;
		
 		return index < dataSourceList.size();
	}

}
