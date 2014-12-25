package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmLostDay;
import com.selfsoft.baseparameter.service.ITmLostDayService;
import com.selfsoft.framework.common.Constants;

public class TmLostDayAction extends ActionSupport implements
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
	private ITmLostDayService tmLostDayService;
	
	private TmLostDay tmLostDay;

	public TmLostDay getTmLostDay() {
		return tmLostDay;
	}

	public void setTmLostDay(TmLostDay tmLostDay) {
		this.tmLostDay = tmLostDay;
	}
	
	public String findTmLostDay(){
		
		List<TmLostDay> tmLostDayList = tmLostDayService.findAll();
		
		ActionContext.getContext().put("tmLostDayList", tmLostDayList);
		
		return Constants.SUCCESS;
	}
	
	public String updateTmLostDay(){
		
		tmLostDayService.updateTmLostDay(tmLostDay);
		
		return Constants.SUCCESS;
		
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmLostDay = tmLostDayService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
