package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmMaintainAlertDay;
import com.selfsoft.baseparameter.service.ITmMaintainAlertDayService;
import com.selfsoft.framework.common.Constants;

public class TmMaintainAlertDayAction extends ActionSupport implements
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
	private ITmMaintainAlertDayService tmMaintainAlertDayService;
	
	private TmMaintainAlertDay tmMaintainAlertDay;

	public TmMaintainAlertDay getTmMaintainAlertDay() {
		return tmMaintainAlertDay;
	}

	public void setTmMaintainAlertDay(TmMaintainAlertDay tmMaintainAlertDay) {
		this.tmMaintainAlertDay = tmMaintainAlertDay;
	}
	
	public String findTmMaintainAlertDay(){
		
		List<TmMaintainAlertDay> tmMaintainAlertDayList = tmMaintainAlertDayService.findAll();
		
		ActionContext.getContext().put("tmMaintainAlertDayList", tmMaintainAlertDayList);
		
		return Constants.SUCCESS;
	}
	
	public String updateTmMaintainAlertDay(){
		
		tmMaintainAlertDayService.updateTmMaintainAlertDay(tmMaintainAlertDay);
		
		return Constants.SUCCESS;
		
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmMaintainAlertDay = tmMaintainAlertDayService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
