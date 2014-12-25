package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmCustomerType;
import com.selfsoft.baseparameter.service.ITmCustomerTypeService;
import com.selfsoft.framework.common.Constants;

public class TmCustomerTypeAction extends ActionSupport implements
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
	private ITmCustomerTypeService tmCustomerTypeService;
	
	private TmCustomerType tmCustomerType;

	public TmCustomerType getTmCustomerType() {
		return tmCustomerType;
	}

	public void setTmCustomerType(TmCustomerType tmCustomerType) {
		this.tmCustomerType = tmCustomerType;
	}
	
	public String findTmCustomerType() throws Exception{
		
		List<TmCustomerType>tmCustomerTypeList = tmCustomerTypeService.findAll();
		
		ActionContext.getContext().put("tmCustomerTypeList", tmCustomerTypeList);
		
		return Constants.SUCCESS;
	}
	
	public String insertTmCustomerType() throws Exception{
		
		tmCustomerTypeService.insert(tmCustomerType);

		return Constants.SUCCESS;
		
	}

	public String updateTmCustomerType() throws Exception{
		
		tmCustomerTypeService.update(tmCustomerType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmCustomerType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmCustomerTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmCustomerTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmCustomerTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmCustomerTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmCustomerType = tmCustomerTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
