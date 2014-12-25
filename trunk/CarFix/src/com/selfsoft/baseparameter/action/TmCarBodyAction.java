package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmCarBody;
import com.selfsoft.baseparameter.service.ITmCarBodyService;
import com.selfsoft.framework.common.Constants;

public class TmCarBodyAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 506216537328796527L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Autowired
	private ITmCarBodyService tmCarBodyService;
	
	private TmCarBody tmCarBody;

	public TmCarBody getTmCarBody() {
		return tmCarBody;
	}

	public void setTmCarBody(TmCarBody tmCarBody) {
		this.tmCarBody = tmCarBody;
	}
	
	public String findTmCarBody() throws Exception{
		
		List<TmCarBody>tmCarBodyList = tmCarBodyService.findAll();
		
		ActionContext.getContext().put("tmCarBodyList", tmCarBodyList);
		
		return Constants.SUCCESS;
	}
	
	public String insertTmCarBody() throws Exception{
		
		tmCarBodyService.insert(tmCarBody);

		return Constants.SUCCESS;
		
	}

	public String updateTmCarBody() throws Exception{
		
		tmCarBodyService.update(tmCarBody);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmCarBody() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmCarBodyService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmCarBodyTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmCarBodyTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmCarBodyTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmCarBody = tmCarBodyService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}

