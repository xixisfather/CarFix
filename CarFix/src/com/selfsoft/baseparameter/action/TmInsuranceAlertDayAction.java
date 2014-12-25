package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmInsuranceAlertDay;
import com.selfsoft.baseparameter.service.ITmInsuranceAlertDayService;
import com.selfsoft.framework.common.Constants;

public class TmInsuranceAlertDayAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 504083578036238792L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmInsuranceAlertDayService tmInsuranceAlertDayService;
	
	private TmInsuranceAlertDay tmInsuranceAlertDay;

	public TmInsuranceAlertDay getTmInsuranceAlertDay() {
		return tmInsuranceAlertDay;
	}

	public void setTmInsuranceAlertDay(TmInsuranceAlertDay tmInsuranceAlertDay) {
		this.tmInsuranceAlertDay = tmInsuranceAlertDay;
	}
	
	public String findTmInsuranceAlertDay(){
		
		List<TmInsuranceAlertDay> tmInsuranceAlertDayList = tmInsuranceAlertDayService.findAll();
		
		ActionContext.getContext().put("tmInsuranceAlertDayList", tmInsuranceAlertDayList);
		
		return Constants.SUCCESS;
	}
	
	public String updateTmInsuranceAlertDay(){
		
		tmInsuranceAlertDayService.updateTmInsuranceAlertDay(tmInsuranceAlertDay);
		
		return Constants.SUCCESS;
		
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmInsuranceAlertDay = tmInsuranceAlertDayService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
