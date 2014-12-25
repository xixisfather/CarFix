package com.selfsoft.secrity.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmSysRegedit;
import com.selfsoft.secrity.service.ITmSysRegeditService;

public class TmSysRegeditAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5351703475605788606L;

	/**
	 * 获取request,response对象
	 */
	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmSysRegeditService tmSysRegeditService;
	
	private TmSysRegedit tmSysRegedit;

	public TmSysRegedit getTmSysRegedit() {
		return tmSysRegedit;
	}

	public void setTmSysRegedit(TmSysRegedit tmSysRegedit) {
		this.tmSysRegedit = tmSysRegedit;
	}
	
	public String showTmSysRegedit(){
		
		tmSysRegedit = tmSysRegeditService.showUniqueSysRegedit();
		
		return Constants.SUCCESS;
	}
	
	public String insertOrUpdateTmSysRegedit(){
		
		if(null==tmSysRegedit.getId()){
			
			tmSysRegeditService.insert(tmSysRegedit);
			
		}
		else{
			
			if(tmSysRegedit.getId().equals(-100L)){
				
				if(null==tmSysRegeditService.findAll()||tmSysRegeditService.findAll().size()==0){
					
					tmSysRegedit.setId(null);
					
					tmSysRegeditService.insert(tmSysRegedit);
					
				}
				else{
					tmSysRegedit.setId(tmSysRegeditService.findAll().get(0).getId());
				
					tmSysRegeditService.update(tmSysRegedit);
				}
				return "loginPage";
				
			}
			
			tmSysRegeditService.update(tmSysRegedit);
		}
		
		
		return Constants.SUCCESS;
	}
}
