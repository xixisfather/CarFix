package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmCardCheck;
import com.selfsoft.baseparameter.service.ITmCardCheckService;
import com.selfsoft.framework.common.Constants;

public class TmCardCheckAction extends ActionSupport implements
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
	private ITmCardCheckService tmCardCheckService;
	
	private TmCardCheck tmCardCheck;

	public TmCardCheck getTmCardCheck() {
		return tmCardCheck;
	}

	public void setTmCardCheck(TmCardCheck tmCardCheck) {
		this.tmCardCheck = tmCardCheck;
	}
	
	public String findTmCardCheck() {
		List<TmCardCheck> tmCardCheckList = tmCardCheckService
				.findAll();

		ActionContext.getContext().put("tmCardCheckList", tmCardCheckList);
		
		return Constants.SUCCESS;
	}

	public String insertTmCardCheck() throws Exception{
		tmCardCheckService.insert(tmCardCheck);

		return Constants.SUCCESS;
	}

	public String updateTmCardCheck() throws Exception{
		tmCardCheckService.update(tmCardCheck);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmCardCheck() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmCardCheckService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmCardCheckTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmCardCheckTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmCardCheckTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmCardCheck = tmCardCheckService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
