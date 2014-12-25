package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbPriceAddRate;
import com.selfsoft.baseinformation.service.ITbPriceAddRateService;
import com.selfsoft.framework.common.Constants;

public class TbPriceAddRateAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1462343875626354733L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITbPriceAddRateService tbPriceAddRateService;
	
	private TbPriceAddRate tbPriceAddRate;
	
	public TbPriceAddRate getTbPriceAddRate() {
		return tbPriceAddRate;
	}

	public void setTbPriceAddRate(TbPriceAddRate tbPriceAddRate) {
		this.tbPriceAddRate = tbPriceAddRate;
	}

	public String findTbPriceAddRate() {
		
		List<TbPriceAddRate> tmPriceAddRateList = tbPriceAddRateService.findByEntity(tbPriceAddRate);

		ActionContext.getContext().put("tmPriceAddRateList", tmPriceAddRateList);
		
		return Constants.SUCCESS;
	}

	public String insertTbPriceAddRate() throws Exception{
		tbPriceAddRateService.insert(tbPriceAddRate);

		return Constants.SUCCESS;
	}

	public String updateTbPriceAddRate() throws Exception{
		tbPriceAddRateService.update(tbPriceAddRate);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbPriceAddRate() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbPriceAddRateService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPriceAddRateTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPriceAddRateTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPriceAddRateTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");


		if (null != id && !"".equals(id)) {

			tbPriceAddRate = tbPriceAddRateService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String synPriceAddRateSellPrice() throws Exception{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			tbPriceAddRateService.synTbPartInfoSellPrice(new Long(id));
			
			response.getWriter().print(Constants.SUCCESS);
		}
		return null;
	}
}
