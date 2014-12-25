package com.selfsoft.business.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.vo.TbFixEntrustVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.JsonUtil;
import com.selfsoft.framework.common.UrlParamDecoder;

/**
 * 
 * @author 柏俊翔
 * 搜索快速定位
 */
public class AutocompleteAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	private TbFixEntrust tbFixEntrust;
	
	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	/**
	 * 树节点搜索提示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String mainTainEntrustCodePrompt() throws Exception{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String entrustCode=UrlParamDecoder.getParam(request, "q");			//用户提交的关键字
		
		if(tbFixEntrust==null)tbFixEntrust = new TbFixEntrust();
		tbFixEntrust.setEntrustCode(entrustCode);
		tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
		tbFixEntrust.setEntrustStatusCollection("0,1,2,4,5");
		tbFixEntrust.setEntrustType(new Long(1));
		
		entrustHandler(out);
	
		return null;
	}
	
	public String stockOutEntrustCodePrompt() throws Exception{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String entrustCode=UrlParamDecoder.getParam(request, "q");			//用户提交的关键字
		
		if(tbFixEntrust==null)tbFixEntrust = new TbFixEntrust();
		tbFixEntrust.setEntrustCode(entrustCode);
		tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
		tbFixEntrust.setEntrustStatusCollection("0,1,2,4,5");
		tbFixEntrust.setEntrustType(new Long(2));
		
		entrustHandler(out);
	
		return null;
	}
	
	private void entrustHandler(PrintWriter out){
		
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService.findTbMainFixEntrust(tbFixEntrust);
		List<TbFixEntrustVo> voList = new ArrayList<TbFixEntrustVo>();
		for(TbFixEntrust tbFixEntrust : tbFixEntrustList){
			TbFixEntrustVo vo = new TbFixEntrustVo();
			vo.setEntrustId(tbFixEntrust.getId());
			vo.setEntrustCode(tbFixEntrust.getEntrustCode());
			vo.setLicenseCode(tbFixEntrust.getTbCarInfo().getLicenseCode());
			vo.setFixDate(tbFixEntrust.getFixDate());
			vo.setFixType(tbFixEntrust.getFixType());
			vo.setStationCode(tbFixEntrust.getTbCarInfo().getTmCarModelType().getModelName());
			vo.setCustomerName(tbFixEntrust.getCustomerName());
			vo.setCustomerCode(tbFixEntrust.getTbCustomer().getCustomerCode());
			vo.setCustomerId(tbFixEntrust.getTbCustomer().getId());
			voList.add(vo);
		}
		
		
		Gson gson = new Gson();
		String str = gson.toJson(voList);
	
		
		out.println(str);
		out.flush();
		out.close();
		
	}
	
}
