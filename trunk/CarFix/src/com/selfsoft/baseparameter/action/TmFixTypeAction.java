package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.baseparameter.service.ITmBalanceService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmWorkingHourPriceService;
import com.selfsoft.framework.common.Constants;

public class TmFixTypeAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -139978415221650302L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Autowired
	private ITmFixTypeService tmFixTypeService;
	
	private TmFixType tmFixType;
	
	@Autowired
	private ITmWorkingHourPriceService tmWorkingHourPriceService;
	
	@Autowired
	private ITmBalanceService tmBalanceService;

	public TmFixType getTmFixType() {
		return tmFixType;
	}

	public void setTmFixType(TmFixType tmFixType) {
		this.tmFixType = tmFixType;
	}
	
	public String findTmFixType() {
		List<TmFixType> tmFixTypeList = tmFixTypeService
				.findAll();

		ActionContext.getContext().put("tmFixTypeList", tmFixTypeList);
		
		return Constants.SUCCESS;
	}

	public String insertTmFixType() throws Exception{
		tmFixTypeService.insert(tmFixType);

		return Constants.SUCCESS;
	}

	public String updateTmFixType() throws Exception{
		tmFixTypeService.update(tmFixType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmFixType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmFixTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmFixTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmFixTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmFixTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		ActionContext.getContext().put("tmBalanceMap", tmBalanceService.findAllTmBalanceMap());
		
		//ActionContext.getContext().put("tmWorkingHourPriceMap", tmWorkingHourPriceService.findAllTmWorkingHourPriceValueMap());
		
		if (null != id && !"".equals(id)) {

			tmFixType = tmFixTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
