package com.selfsoft.business.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.service.ITbBookService;
import com.selfsoft.business.service.ITmLoanRegisterDetailService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.service.ITmUserService;

public class TmLoanRegisterDetailAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -8222571829690983781L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmLoanRegisterDetailService tmLoanRegisterDetailService;
}
