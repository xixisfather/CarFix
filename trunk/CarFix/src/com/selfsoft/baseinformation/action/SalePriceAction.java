package com.selfsoft.baseinformation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbSalePrice;
import com.selfsoft.baseinformation.service.ITbSalePriceService;
import com.selfsoft.framework.common.Constants;

public class SalePriceAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2258181983244668493L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Autowired
	private ITbSalePriceService tbSalePriceService;
	
	private TbSalePrice tbSalePrice;
	
	
	public TbSalePrice getTbSalePrice() {
		return tbSalePrice;
	}

	public void setTbSalePrice(TbSalePrice tbSalePrice) {
		this.tbSalePrice = tbSalePrice;
	}

	public String updateTbSalPrice() throws Exception{
		tbSalePriceService.update(tbSalePrice);

		return Constants.SUCCESS;
	}
	
	
	public String forwardPage() throws Exception {
	
		tbSalePrice = tbSalePriceService.findAll();
		ActionContext.getContext().put("tbSalePrice",tbSalePrice );
		
		return Constants.EDITPAGE;
	}
	

}
