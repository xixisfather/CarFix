package com.selfsoft.business.action;

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
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.model.TbAnvancePay;
import com.selfsoft.business.service.ITbAnvancePayService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TbAnvancePayAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = -900549578909364466L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbAnvancePayService tbAnvancePayService;
	
	private TbAnvancePay tbAnvancePay;
	@Autowired
	private ITmDictionaryService tmDictionaryService;

	@Autowired
	private ITbCarInfoService tbCarInfoService;
	
	public TbAnvancePay getTbAnvancePay() {
		return tbAnvancePay;
	}

	public void setTbAnvancePay(TbAnvancePay tbAnvancePay) {
		this.tbAnvancePay = tbAnvancePay;
	}
	
	public String findTbAnvancePay() {
		List<TbAnvancePay> tbAnvancePayList = tbAnvancePayService.findByTbAnvancePay(tbAnvancePay);
		ActionContext.getContext().put("tbAnvancePayList", tbAnvancePayList);
		
		return Constants.SUCCESS;
	}

	public String insertTbAnvancePay() throws Exception{
		
		String anvanceCode = tmDictionaryService.GenerateCode(StockTypeElements.TBANVANCEPAY.getElementString());
		
		tbAnvancePay.setAnvanceCode(anvanceCode);
		
		tbAnvancePay.setPayDay(new Date());
		
		tbAnvancePayService.insert(tbAnvancePay);
		
		ActionContext.getContext().put("msg","生成单据号:" + anvanceCode);

		return Constants.SUCCESS;
	}

	public String updateTbAnvancePay() throws Exception{
		tbAnvancePayService.update(tbAnvancePay);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbAnvancePay() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbAnvancePayService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbAnvancePayTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbAnvancePayTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbAnvancePayTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("payMap", Constants.getPayMap());
		
		if (null != id && !"".equals(id)) {

			tbAnvancePay = tbAnvancePayService.findById(Long.valueOf(id));
			
			TbCarInfo tbCarInfo = tbCarInfoService.findTbCarInfoBylicenseCode(tbAnvancePay.getTbCarInfo().getLicenseCode());

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
