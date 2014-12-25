package com.selfsoft.framework.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

public class ReportFileFromStream {
	/**
	 * MAP参数说明
	 * 1.dataSourceList   打印列表
	 * 2.jrxmlPath        JASPERREPORT XML路径
	 * 3.reportTpl        配置文件路径
	 * 4.reportParameters 报表参数
	 */
	public static void parsePdfFromStream(HttpServletRequest request,HttpServletResponse response,Map params) throws Exception{
		
		//打印的List
		List dataSourceList = (List) params.get("dataSourceList");
		//JASPERREPORT的路径
		String jrxmlPath = (String) params.get("jrxmlPath");
			
		String filePath = request.getRealPath(jrxmlPath); 
		//JASPERREPORT文件	
		File reportFile = new File(filePath);
		
		if(!reportFile.exists()){
			 
			throw new Exception("file is not found");
		
		}
		//编译XML文件
		JasperCompileManager.compileReportToFile(filePath);

		InputStream input = new FileInputStream(reportFile);

		JasperDesign design = JRXmlLoader.load(input);

		JasperReport jasperReport = JasperCompileManager
				.compileReport(design);
		//打印列表
		ReportDataSource reportDataSource = new ReportDataSource();
		
		reportDataSource.setDataSourceList(dataSourceList);
		//获取打印配置模板
		String reportTpl = (String) params.get("reportTpl");
		
		reportDataSource.setDataSourceTpl(reportTpl);
		
		//获取打印报表参数
		Map reportParameters = (Map) params.get("reportParameters");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasperReport, reportParameters , reportDataSource);
		
		request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint);
	
		List jasperPrintList = BaseHttpServlet.getJasperPrintList(request);

		
		if (jasperPrintList == null) {
			throw new ServletException("No JasperPrint documents found on the HTTP session.");
		}
		
		Boolean isBuffered = Boolean.valueOf(request.getParameter(BaseHttpServlet.BUFFERED_OUTPUT_REQUEST_PARAMETER));
	
		if (isBuffered.booleanValue()) {
			JRPdfExporter exporter = new JRPdfExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,baos);
			
			exporter.exportReport();
			
			byte[] bytes = baos.toByteArray();
			
			if (bytes != null && bytes.length > 0) {
				
				response.setContentType("application/pdf");
				
				response.setContentLength(bytes.length);
				
				ServletOutputStream ouputStream = response.getOutputStream();
				
				try {
					exporter.exportReport();
				} catch (JRException e) {
					throw new ServletException(e);
				} finally {
					if (ouputStream != null) {
						try {
							ouputStream.close();
						} catch (IOException ex) {
						}
					}
				}
			}
		} else {
			response.setContentType("application/pdf");

			JRPdfExporter exporterJR = new JRPdfExporter();
			
			exporterJR.setParameter(JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList);

			OutputStream ouputStream = response.getOutputStream();
			
			exporterJR.setParameter(JRExporterParameter.OUTPUT_STREAM,ouputStream);

			try {
				exporterJR.exportReport();
			} catch (JRException e) {
				throw new ServletException(e);
			} finally {
				if (ouputStream != null) {
					try {
						ouputStream.close();
					} catch (IOException ex) {
					}
				}
			}
		}
	
	}
}
