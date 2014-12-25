package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.baseparameter.service.ITmUnitService;
import com.selfsoft.framework.common.Constants;

public class TmUnitAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 8024921582349240947L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmUnitService tmUnitService;
	
	private TmUnit tmUnit;

	public TmUnit getTmUnit() {
		return tmUnit;
	}

	public void setTmUnit(TmUnit tmUnit) {
		this.tmUnit = tmUnit;
	}
	
	public String findTmUnit() {
		List<TmUnit> tmUnitList = tmUnitService
				.findAll();

		ActionContext.getContext().put("tmUnitList", tmUnitList);
		
		return Constants.SUCCESS;
	}

	public String insertTmUnit() throws Exception{
		tmUnitService.insert(tmUnit);

		return Constants.SUCCESS;
	}

	public String updateTmUnit() throws Exception{
		tmUnitService.update(tmUnit);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmUnit() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmUnitService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmUnitTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmUnitTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmUnitTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmUnit = tmUnitService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
