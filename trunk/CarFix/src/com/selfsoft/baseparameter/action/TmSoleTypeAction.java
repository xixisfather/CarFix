package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.framework.common.Constants;

public class TmSoleTypeAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 470100646698400216L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmSoleTypeService tmSoleTypeService;
	
	private TmSoleType tmSoleType;

	public TmSoleType getTmSoleType() {
		return tmSoleType;
	}

	public void setTmSoleType(TmSoleType tmSoleType) {
		this.tmSoleType = tmSoleType;
	}
	
	public String findTmSoleType() {
		List<TmSoleType> tmSoleTypeList = tmSoleTypeService
				.findAll();

		ActionContext.getContext().put("tmSoleTypeList", tmSoleTypeList);
		
		return Constants.SUCCESS;
	}

	public String insertTmSoleType() throws Exception{
		tmSoleTypeService.insert(tmSoleType);

		return Constants.SUCCESS;
	}

	public String updateTmSoleType() throws Exception{
		tmSoleTypeService.update(tmSoleType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmSoleType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmSoleTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmSoleTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmSoleTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmSoleTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmSoleType = tmSoleTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
