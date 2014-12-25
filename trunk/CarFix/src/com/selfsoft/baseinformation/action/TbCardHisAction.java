package com.selfsoft.baseinformation.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.dao.ITbCardHisDao;
import com.selfsoft.baseinformation.model.TbCardHis;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.file.ReportFileFromStream;

public class TbCardHisAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -5097202056446038383L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private TbCardHis tbCardHis;

	public TbCardHis getTbCardHis() {
		return tbCardHis;
	}

	public void setTbCardHis(TbCardHis tbCardHis) {
		this.tbCardHis = tbCardHis;
	}
	
	@Autowired
	private ITbCardHisService tbCardHisService;
	
	public String findTbCardHis(){
		
		List<TbCardHis> tbCardHisList = tbCardHisService.findByTbCardHis(tbCardHis);
		
		ActionContext.getContext().put("tbCardHisList", tbCardHisList);
		
		ActionContext.getContext().getSession().put("tbCardHisPrintList", tbCardHisList);
		
		return Constants.SUCCESS;
	}
	
	public String printTbCardHis() throws Exception{
		
		List<TbCardHis> tbCardHisList = (List<TbCardHis>) request.getSession().getAttribute("tbCardHisPrintList");
		
		if(null == tbCardHisList){
			
			return null;
			
		}
		
		List<TbCardHis> tbCardHisListPrint = new ArrayList<TbCardHis>();
		
		for(TbCardHis t : tbCardHisList){
			
			t.setOriCardSavingD(t.getOriCardSaving() == null ? null :new BigDecimal(t.getOriCardSaving()));
			
			t.setAftCardSavingD(t.getAftCardSaving() == null ? null : new BigDecimal(t.getAftCardSaving()));
			
			t.setOriCardPointI(t.getOriCardPoint() == null ? null : t.getOriCardPoint().intValue());
			
			t.setAftCardPointI(t.getAftCardPoint() == null ? null : t.getAftCardPoint().intValue());
			
			tbCardHisListPrint.add(t);
		}
		
		Map map = tbCardHisService.putCardHisReportParamMap(tbCardHisListPrint, request);
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
		return null;
	}
}
