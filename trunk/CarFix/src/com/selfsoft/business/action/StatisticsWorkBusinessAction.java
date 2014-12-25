package com.selfsoft.business.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.service.IStatisticsWorkBusinessService;
import com.selfsoft.business.vo.WorkTypeHourPriceVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class StatisticsWorkBusinessAction extends ActionSupport implements
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
	private IStatisticsWorkBusinessService statisticsWorkBusinessService;
	@Autowired
	private ITmUserService tmUserService;
	

	
	private TbFixShare tbFixShare;

	public TbFixShare getTbFixShare() {
		return tbFixShare;
	}

	public void setTbFixShare(TbFixShare tbFixShare) {
		this.tbFixShare = tbFixShare;
	}

	
	
	public String getSendHourGroupPersonStat(){
		
		List<TbFixShare> groupResults = statisticsWorkBusinessService.getTbFixShareByGroupFixPerson(tbFixShare);
		
		ActionContext.getContext().getSession().put("tbFixShareInSession", tbFixShare);
		
		ActionContext.getContext().put("groupResults", groupResults);
		
		ActionContext.getContext().getSession().put("groupResultsInSession",groupResults);
		
		List<TbFixShare> results = statisticsWorkBusinessService.getFixShareDetail(tbFixShare);
		
		ActionContext.getContext().put("results", results);
		
		ActionContext.getContext().getSession().put("resultsInSession", results);
		
		
		List<TmUser> useList = tmUserService.findAll();
		
		ActionContext.getContext().put("userList", useList);
		
		
		List<WorkTypeHourPriceVo> workTypeHourPriceVos = statisticsWorkBusinessService.getWorkTypeStat(tbFixShare);
		
		ActionContext.getContext().put("workTypeHourPriceVos", workTypeHourPriceVos);
		
		ActionContext.getContext().getSession().put("workTypeHourPriceVosInSession", workTypeHourPriceVos);
		
		return Constants.SUCCESS;
	}
	
	
	public String getTbFixShareDetailStat(){
		
		List<TbFixShare> results = statisticsWorkBusinessService.getFixShareDetail(tbFixShare);
		
		ActionContext.getContext().put("results", results);
		
		List<TmUser> useList = tmUserService.findAll();
		
		ActionContext.getContext().put("userList", useList);
		
		return Constants.SUCCESS;
	}
}
