package com.selfsoft.secrity.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmWorkType;
import com.selfsoft.secrity.service.ITmWorkTypeService;

public class TmWorkTypeAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = 5541866761664098082L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmWorkTypeService tmWorkTypeService;
	
	private TmWorkType tmWorkType;

	public TmWorkType getTmWorkType() {
		return tmWorkType;
	}

	public void setTmWorkType(TmWorkType tmWorkType) {
		this.tmWorkType = tmWorkType;
	}
	
	public String findTmWorkType() {
		List<TmWorkType> tmWorkTypeList = tmWorkTypeService
				.findAll();

		ActionContext.getContext().put("tmWorkTypeList", tmWorkTypeList);
		
		return Constants.SUCCESS;
	}

	public String insertTmWorkType() throws Exception{
		tmWorkTypeService.insert(tmWorkType);

		return Constants.SUCCESS;
	}

	public String updateTmWorkType() throws Exception{
		tmWorkTypeService.update(tmWorkType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmWorkType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmWorkTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmWorkTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmWorkTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmWorkTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmWorkType = tmWorkTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
