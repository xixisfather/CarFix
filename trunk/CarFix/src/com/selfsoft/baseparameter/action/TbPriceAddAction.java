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
import com.selfsoft.baseinformation.model.TbPriceAdd;
import com.selfsoft.baseinformation.service.ITbPriceAddService;
import com.selfsoft.framework.common.Constants;

public class TbPriceAddAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7679474610077870271L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbPriceAddService tbPriceAddService;
	
	private TbPriceAdd tbPriceAdd;
	
	public String findtbPriceAdd() {
		
		List<TbPriceAdd> tmPriceAddList = tbPriceAddService.findByEntity(tbPriceAdd);

		ActionContext.getContext().put("tmPriceAddList", tmPriceAddList);
		
		return Constants.SUCCESS;
	}

	public String insertTbPriceAdd() throws Exception{
		tbPriceAddService.insert(tbPriceAdd);

		return Constants.SUCCESS;
	}

	public String updateTbPriceAdd() throws Exception{
		tbPriceAddService.update(tbPriceAdd);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbPriceAdd() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbPriceAddService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPriceAddTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPriceAddTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPriceAddTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");


		if (null != id && !"".equals(id)) {

			tbPriceAdd = tbPriceAddService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String synPriceAddSellPrice() throws Exception{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			tbPriceAddService.synTbPartInfoSellPrice(new Long(id));
			
			response.getWriter().print(Constants.SUCCESS);
		}
		return null;
	}

	public TbPriceAdd getTbPriceAdd() {
		return tbPriceAdd;
	}

	public void setTbPriceAdd(TbPriceAdd tbPriceAdd) {
		this.tbPriceAdd = tbPriceAdd;
	}

}
