package com.selfsoft.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.UrlParamDecoder;

public class CommonAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -4334658311607749803L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String transferToPinYin() throws Exception{
	
		String chn = CommonMethod.TransferToEncoding(request.getParameter("chn"), "ISO-8859-1", "GBK");
		
		String pinYinId = request.getParameter("pinYinId");
		
		String pinYin = CommonMethod.tranferToPinYin(chn);
		
		response.getWriter().print(pinYinId + "," + pinYin);
		
		return null;
	}
}
