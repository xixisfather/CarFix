package com.selfsoft.test;

import java.io.IOException;

import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.framework.file.PdfWriter;


public class TestPdfReader {
	
	public static void main(String[] args) throws Exception{
		
		TestPdfReader t = new TestPdfReader();
		
		t.pdfReader();
	}
	
	private void pdfReader() throws Exception{
		
		PdfWriter pdfWriter = new PdfWriter(this.getClass().getResourceAsStream("/tbFixEntrust_pdf_tpl.properties"));
	
		TbFixEntrust tbFixEntrust = new TbFixEntrust();
		
		tbFixEntrust.setId(1L);
		
		tbFixEntrust.setEntrustCode("10000001");
		
		Object id = pdfWriter.getValue(tbFixEntrust, "id");
		
		Object entrust = pdfWriter.getValue(tbFixEntrust, "entrustCode");
		
		System.out.println(id);
		
		System.out.println(entrust);
	}
}
