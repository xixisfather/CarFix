package com.selfsoft.secrity.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.secrity.model.TmDepartment;
import com.selfsoft.secrity.service.ITmDepartmentService;
import com.selfsoft.framework.common.Constants;

public class TmDepartmentAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -4648564435102923115L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmDepartmentService tmDepartmentService;
	
	private TmDepartment tmDepartment;

	public TmDepartment getTmDepartment() {
		return tmDepartment;
	}

	public void setTmDepartment(TmDepartment tmDepartment) {
		this.tmDepartment = tmDepartment;
	}
	
	public String findTmDepartment() {
		List<TmDepartment> tmDepartmentList = tmDepartmentService
				.findAll();

		ActionContext.getContext().put("tmDepartmentList", tmDepartmentList);
		
		return Constants.SUCCESS;
	}

	public String insertTmDepartment() throws Exception{
		tmDepartmentService.insert(tmDepartment);

		return Constants.SUCCESS;
	}

	public String updateTmDepartment() throws Exception{
		tmDepartmentService.update(tmDepartment);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmDepartment() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmDepartmentService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmDepartmentTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmDepartmentTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmDepartmentTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmDepartment = tmDepartmentService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
