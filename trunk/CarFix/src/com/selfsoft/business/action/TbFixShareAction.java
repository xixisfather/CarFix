package com.selfsoft.business.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TbFixShareAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -816501989321984258L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private TbFixShare tbFixShare;
	
	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;
	
	@Autowired
	private ITmUserService tmUserService;

	public TbFixShare getTbFixShare() {
		return tbFixShare;
	}

	public void setTbFixShare(TbFixShare tbFixShare) {
		this.tbFixShare = tbFixShare;
	}
	
	public String workingInfoShare() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		String flag = request.getParameter("flag");
		
		String[] flagArray = flag.split(";");
		
		Double fixHour = Double.valueOf(flagArray[0]);
		
		Double workingHourTotal = Double.valueOf(flagArray[1]);
		
		Double sendHour = Double.valueOf(flagArray[2]);
		
		String fixPersons = CommonMethod.TransferToEncoding(flagArray[3],"ISO-8859-1","UTF-8");
		
		String fixPersonIds =  flagArray[4];
			
		Long index = Long.valueOf(flagArray[5]);
		
		TbWorkingInfo tbWorkingInfo = tbWorkingInfoService.findById(Long.valueOf(id));
		
		tbWorkingInfo.setFixHour(fixHour);
		
		tbWorkingInfo.setSendHour(sendHour);
		
		tbFixShare = new TbFixShare();
		
		tbFixShare.setTbWorkingInfo(tbWorkingInfo);
		
		tbFixShare.setWorkingHourTotal(workingHourTotal);
		
		tbFixShare.setIndex(index);
		
		tbFixShare.setFixPersonIds(fixPersonIds);
		
		tbFixShare.setFixPersons(fixPersons);
		
		TmUser tmUser = new TmUser();
		
		tmUser.setUserStatus("正常");
		
		ActionContext.getContext().put("tmUserList",/*tmUserService.findAll()*//*tmUserService.findValidUser(tmUser)*/tmUserService.findValidFixUser(tmUser));
		
		return Constants.SUCCESS;
	}
}
