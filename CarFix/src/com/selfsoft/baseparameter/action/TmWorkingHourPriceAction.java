package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmWorkingHourPrice;
import com.selfsoft.baseparameter.service.ITmWorkingHourPriceService;
import com.selfsoft.framework.common.Constants;

public class TmWorkingHourPriceAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = -4567954846239278805L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmWorkingHourPriceService tmWorkingHourPriceService;
	
	private TmWorkingHourPrice tmWorkingHourPrice;

	public TmWorkingHourPrice getTmWorkingHourPrice() {
		return tmWorkingHourPrice;
	}

	public void setTmWorkingHourPrice(TmWorkingHourPrice tmWorkingHourPrice) {
		this.tmWorkingHourPrice = tmWorkingHourPrice;
	}
	
	public String findAll(){
		
		List<TmWorkingHourPrice>tmWorkingHourPriceList = tmWorkingHourPriceService.findAll();
		
		ActionContext.getContext().put("tmWorkingHourPriceList", tmWorkingHourPriceList);
		
		return Constants.SUCCESS;
	}

	public String insertTmWorkingHourPrice() {
		boolean flag = tmWorkingHourPriceService.insert(tmWorkingHourPrice);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	
	public String updateTmWorkingHourPrice() throws Exception{
		boolean flag = tmWorkingHourPriceService.update(tmWorkingHourPrice);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmWorkingHourPrice = tmWorkingHourPriceService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String deleteTmWorkingHourPrice() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmWorkingHourPriceService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmWorkingHourPriceTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmWorkingHourPriceTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmWorkingHourPriceTable," + Constants.EXCEPTION);
		}
		return null;
	}
}
