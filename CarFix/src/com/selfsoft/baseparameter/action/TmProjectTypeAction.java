package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmProjectType;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.framework.common.Constants;

public class TmProjectTypeAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = -5444730488804760403L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	
	private TmProjectType tmProjectType;

	public TmProjectType getTmProjectType() {
		return tmProjectType;
	}

	public void setTmProjectType(TmProjectType tmProjectType) {
		this.tmProjectType = tmProjectType;
	}
	
	
    public String findTmProjectType() throws Exception{
		
		List<TmProjectType>tmProjectTypeList = tmProjectTypeService.findAll();
		
		ActionContext.getContext().put("tmProjectTypeList", tmProjectTypeList);
		
		return Constants.SUCCESS;
	}
	
	public String insertTmProjectType() throws Exception{
		
		tmProjectTypeService.insert(tmProjectType);

		return Constants.SUCCESS;
		
	}

	public String updateTmProjectType() throws Exception{
		
		tmProjectTypeService.update(tmProjectType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmProjectType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmProjectTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmProjectTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmProjectTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmProjectTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmProjectType = tmProjectTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	
}
