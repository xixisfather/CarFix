package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.baseparameter.service.ITmBalanceService;
import com.selfsoft.framework.common.Constants;

public class TmBalanceAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {
	
	private static final long serialVersionUID = -1625160730825128350L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmBalanceService tmBalanceService;
	
	private TmBalance tmBalance;

	public TmBalance getTmBalance() {
		return tmBalance;
	}

	public void setTmBalance(TmBalance tmBalance) {
		this.tmBalance = tmBalance;
	}
	
	public String findTmBalance() {
		List<TmBalance> tmBalanceList = tmBalanceService
				.findAll();

		ActionContext.getContext().put("tmBalanceList", tmBalanceList);
		
		return Constants.SUCCESS;
	}

	public String insertTmBalance() throws Exception{
		tmBalanceService.insert(tmBalance);

		return Constants.SUCCESS;
	}

	public String updateTmBalance() throws Exception{
		tmBalanceService.update(tmBalance);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmBalance() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmBalanceService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmBalanceTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmBalanceTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmBalanceTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("balanceTypeMap", Constants.getBalanceTypeMap());

		if (null != id && !"".equals(id)) {

			tmBalance = tmBalanceService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
