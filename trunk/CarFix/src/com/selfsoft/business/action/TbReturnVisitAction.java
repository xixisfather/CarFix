package com.selfsoft.business.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbReturnVisit;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbReturnVisitService;
import com.selfsoft.framework.common.Constants;

public class TbReturnVisitAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = -394906593142445420L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbReturnVisitService tbReturnVisitService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	
	private TbReturnVisit tbReturnVisit;

	public TbReturnVisit getTbReturnVisit() {
		return tbReturnVisit;
	}

	public void setTbReturnVisit(TbReturnVisit tbReturnVisit) {
		this.tbReturnVisit = tbReturnVisit;
	}
	
	public String findTbReturnVisit() {
		
		if(null == tbReturnVisit){
			
			tbReturnVisit = new TbReturnVisit();
			
			String flag = request.getParameter("flag");
			
			tbReturnVisit.setReturnType(flag);
		}
		
		List<TbReturnVisit> tbReturnVisitList = tbReturnVisitService.findByTbReturnVisit(tbReturnVisit);
		
		List<TbReturnVisit> tbReturnVisitListPage = new ArrayList<TbReturnVisit>();
		
		if(null != tbReturnVisitList && tbReturnVisitList.size() > 0){
			
			for(TbReturnVisit t : tbReturnVisitList){
				
				if(null != t.getEntrustId()){
					
					TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(t.getEntrustId());
					
					t.setTbFixEntrust(tbFixEntrust);
				
				}
				
				tbReturnVisitListPage.add(t);
			}
			
		}
		
		ActionContext.getContext().put("tbReturnVisitList", tbReturnVisitListPage);
		
		return tbReturnVisit.getReturnType();
	}
	
	public String insertTbReturnVisit(){
		
		tbReturnVisit.setVisitDate(new Date());
		
		tbReturnVisitService.insertTbReturnVisit(tbReturnVisit);
		
		return Constants.SUCCESS;
	}
	
	public String updateTbReturnVisit(){
		
		tbReturnVisit.setVisitDate(new Date());
		
		tbReturnVisitService.updateTbReturnVisit(tbReturnVisit);
		
		return Constants.SUCCESS;
		
	}
	
	public String forwardPage(){
		
		String id = request.getParameter("id");
		
		String flag = request.getParameter("flag");
		
		String entrustId = request.getParameter("entrustId");
		
		String carId = request.getParameter("carId");
		
		String licenseCode = request.getParameter("licenseCode");
		
		ActionContext.getContext().put("flag", flag);
		
		ActionContext.getContext().put("entrustId", entrustId);
		
		ActionContext.getContext().put("carId", carId);
		
		if(null != carId){
			
			TbCarInfo tcr =tbCarInfoService.findById(Long.valueOf(carId));
			
			licenseCode = tcr.getLicenseCode();
		}
		
		ActionContext.getContext().put("licenseCode", licenseCode);
		
		if(null != id && !"".equals(id)){
			
			tbReturnVisit = tbReturnVisitService.findById(Long.valueOf(id));
			
			ActionContext.getContext().put("flag", tbReturnVisit.getReturnType());
			
			return Constants.EDITPAGE;
		}
		
		if(null != carId){
			
			TbCarInfo t = new TbCarInfo();
			
			t.setId(Long.valueOf(carId));
			
			t.setLicenseCode(licenseCode);
			
			tbReturnVisit = new TbReturnVisit();
			
			tbReturnVisit.setTbCarInfo(t);
			
			tbReturnVisit.setEntrustId(Long.valueOf(entrustId));
			
		}
		
		return Constants.ADDPAGE;
	}
	
	public String deleteTbReturnVisit() throws Exception{
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {
			
			boolean flag = tbReturnVisitService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbReturnVisitTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbReturnVisitTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbReturnVisitTable," + Constants.EXCEPTION);
		}
		return null;
		
	}
}
