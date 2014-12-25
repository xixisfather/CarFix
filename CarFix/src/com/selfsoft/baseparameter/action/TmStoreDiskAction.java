package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmStoreDisk;
import com.selfsoft.baseparameter.service.ITmStoreDiskService;
import com.selfsoft.framework.common.Constants;

public class TmStoreDiskAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 504083578036238792L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmStoreDiskService tmStoreDiskService;
	
	private TmStoreDisk tmStoreDisk;

	public TmStoreDisk getTmStoreDisk() {
		return tmStoreDisk;
	}

	public void setTmStoreDisk(TmStoreDisk tmStoreDisk) {
		this.tmStoreDisk = tmStoreDisk;
	}
	
	public String findTmStoreDisk(){
		
		List<TmStoreDisk> tmStoreDiskList = tmStoreDiskService.findAll();
		
		ActionContext.getContext().put("tmStoreDiskList", tmStoreDiskList);
		
		return Constants.SUCCESS;
	}
	
	public String updateTmStoreDisk(){
		
		tmStoreDiskService.updateTmStoreDisk(tmStoreDisk);
		
		return Constants.SUCCESS;
		
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmStoreDisk = tmStoreDiskService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
