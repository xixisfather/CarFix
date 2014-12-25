package com.selfsoft.business.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.business.model.TbAnvancePay;
import com.selfsoft.business.model.TmSpecialCarAlert;
import com.selfsoft.business.service.ITmSpecialCarAlertService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmSpecialCarAlertAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -7416419980236505005L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmSpecialCarAlertService tmSpecialCarAlertService;
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	
	
	
	private TmSpecialCarAlert tmSpecialCarAlert;
	
	private TbCarInfo tbCarInfo;

	public TmSpecialCarAlert getTmSpecialCarAlert() {
		return tmSpecialCarAlert;
	}

	public void setTmSpecialCarAlert(TmSpecialCarAlert tmSpecialCarAlert) {
		this.tmSpecialCarAlert = tmSpecialCarAlert;
	}
	
	
	public String findTmSpecialCarAlert() {
		ActionContext.getContext().put("alertCountMap", tmSpecialCarAlertService.getAllAlertCount());
		ActionContext.getContext().put("alertRangeMap", tmSpecialCarAlertService.getAllAlertRange());
		List<TmSpecialCarAlert> tmSpecialCarAlertList = tmSpecialCarAlertService.findByEntity(tmSpecialCarAlert);
		ActionContext.getContext().put("tmSpecialCarAlertList", tmSpecialCarAlertList);
		
		return Constants.SUCCESS;
	}
	
	
	
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("alertCountMap", tmSpecialCarAlertService.getAllAlertCount());
		ActionContext.getContext().put("alertRangeMap", tmSpecialCarAlertService.getAllAlertRange());
		
		if (StringUtils.isNotBlank(id)) {

			tmSpecialCarAlert = tmSpecialCarAlertService.findById(Long.valueOf(id));
			
			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	
	public String insertTmSpecialCarAlert() throws Exception{
		
		TmSpecialCarAlert  sca = tmSpecialCarAlertService.getTmSpecialCarAlertByCarId(Long.valueOf(tmSpecialCarAlert.getTbCarInfo().getId()));
		if(sca!= null){
			ActionContext.getContext().put("msg","该车辆已有提醒内容");
			
			return Constants.FAILURE;
		}
		
		tmSpecialCarAlertService.insert(tmSpecialCarAlert);

		return Constants.SUCCESS;
		
	}
	
	public String updateTmSpecialCarAlert() throws Exception{
		
		tmSpecialCarAlertService.update(tmSpecialCarAlert);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmSpecialCarAlert() throws Exception{
		String id = request.getParameter("id");
		
		
		if (null != id && !"".equals(id)) {
			boolean flag = tmSpecialCarAlertService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmSpecialCarAlertTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmSpecialCarAlertTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmSpecialCarAlertTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	public void getCarAlertContent() throws IOException{
		
		String carInfoId = request.getParameter("carInfoId");
		String alertType = request.getParameter("alertType");
		response.setCharacterEncoding("utf-8");
		boolean flag = false;
		if(StringUtils.isNotBlank(carInfoId) && StringUtils.isNotBlank(alertType))
			flag = tmSpecialCarAlertService.hasCarAlertContent(new Long(carInfoId), new Long(alertType));
		
		
		if(flag){
			tmSpecialCarAlert = tmSpecialCarAlertService.getTmSpecialCarAlertByCarId(Long.valueOf(carInfoId));
			response.getWriter().print(tmSpecialCarAlert.getAlertContent());
		}
	}
	
	/**
	 * 车辆年检回访
	 * @Date      2010-7-27
	 * @Function  
	 * @return
	 */
	public String getCarInfoCheckYearBack(){
		tbCarInfo =  new TbCarInfo();
		tbCarInfo.setLicenseMonth("4");
		List<TbCarInfo> tbCarInfoList = tbCarInfoService.findByTbCarInfo(tbCarInfo);
		ActionContext.getContext().put("tbCarInfoList",tbCarInfoList);
		return null;
	}
	
}
