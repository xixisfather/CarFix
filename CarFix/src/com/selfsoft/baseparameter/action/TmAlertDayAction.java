package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmAlertDay;
import com.selfsoft.baseparameter.service.ITmAlertDayService;
import com.selfsoft.framework.common.Constants;

public class TmAlertDayAction extends ActionSupport implements
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
	private ITmAlertDayService tmAlertDayService;
	
	private TmAlertDay tmAlertDay;

	public TmAlertDay getTmAlertDay() {
		return tmAlertDay;
	}

	public void setTmAlertDay(TmAlertDay tmAlertDay) {
		this.tmAlertDay = tmAlertDay;
	}
	
	public String findTmAlertDay(){
		
		List<TmAlertDay> tmAlertDayList = tmAlertDayService.findAll();
		
		ActionContext.getContext().put("tmAlertDayList", tmAlertDayList);
		
		return Constants.SUCCESS;
	}
	
	public String updateTmAlertDay(){
		
		tmAlertDayService.updateTmAlertDay(tmAlertDay);
		
		return Constants.SUCCESS;
		
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmAlertDay = tmAlertDayService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
