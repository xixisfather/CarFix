package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
import com.selfsoft.framework.common.Constants;

public class TmPartTypeAction  extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 694881602073760857L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmPartTypeService tmPartTypeService;
	
	private TmPartType tmPartType;
	
	public TmPartType getTmPartType() {
		return tmPartType;
	}

	public void setTmPartType(TmPartType tmPartType) {
		this.tmPartType = tmPartType;
	}

	public String findTmPartType() throws Exception{
		
		List<TmPartType>tmPartTypeList = tmPartTypeService.findTmPartTypeList(tmPartType);
		
		ActionContext.getContext().put("tmPartTypeList", tmPartTypeList);
		
		return Constants.SUCCESS;
	}
	
	public String insertTmPartType() throws Exception{
		
		tmPartTypeService.saveTmPartType(tmPartType);

		return Constants.SUCCESS;
		
	}

	public String updateTmPartType() throws Exception{
		
		tmPartTypeService.updateTmPartType(tmPartType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmPartType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmPartTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmPartTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmPartTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmPartTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmPartType = tmPartTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
