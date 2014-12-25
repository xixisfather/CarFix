package com.selfsoft.business.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustCost;
import com.selfsoft.business.service.ITbFixEntrustCostService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.framework.common.Constants;

public class TbFixEntrustCostAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 596786823146482065L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbFixEntrustCostService tbFixEntrustCostService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	private TbFixEntrustCost tbFixEntrustCost;
	
	public TbFixEntrustCost getTbFixEntrustCost() {
		return tbFixEntrustCost;
	}

	public void setTbFixEntrustCost(TbFixEntrustCost tbFixEntrustCost) {
		this.tbFixEntrustCost = tbFixEntrustCost;
	}

	public String findTbFixEntrustCost() throws Exception {
		
		List<TbFixEntrustCost> tbFixEntrustCostList = tbFixEntrustCostService.findByTbFixEntrustCost(tbFixEntrustCost);
		
		BigDecimal d1 = new BigDecimal("0.00");
		
		if(null != tbFixEntrustCostList && tbFixEntrustCostList.size() > 0){
			
			for(TbFixEntrustCost _tbFixEntrustCost : tbFixEntrustCostList){
				
				d1 = d1.add(new BigDecimal(_tbFixEntrustCost.getCostPrice() == null ? 0d : _tbFixEntrustCost.getCostPrice()));
				
			}
			
		}
		
		ActionContext.getContext().put("d1", d1);
		
		ActionContext.getContext().put("tbFixEntrustCostList", tbFixEntrustCostList);
		
		
		
		return Constants.SUCCESS;
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		//ActionContext.getContext().put("costTypeMap", Constants.getBalanceTypeMap());

		if (null != id && !"".equals(id)) {

			tbFixEntrustCost = tbFixEntrustCostService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		
		return Constants.ADDPAGE;
	}
	
	public String insertTbFixEntrustCost() {
		
		try{
			
			if(null != tbFixEntrustCost.getTbFixEntrust().getEntrustCode() && !"".equals(tbFixEntrustCost.getTbFixEntrust().getEntrustCode())){
				
				TbFixEntrust tbFixEntrust = tbFixEntrustService.findByEntrustCode(tbFixEntrustCost.getTbFixEntrust().getEntrustCode());
				
				if(null == tbFixEntrust){
					
					ActionContext.getContext().put("msg","请填写正确的委托书号");
					
					return Constants.FAILURE;
					
				}else{
					
					tbFixEntrustCost.setTbFixEntrust(tbFixEntrust);
					
				}
			}
			
			else {
				
				ActionContext.getContext().put("msg","委托书号不能为空");
				
				return Constants.FAILURE;
			}
			
			tbFixEntrustCost.setCostType("保险理赔");
			
			tbFixEntrustCostService.insertTbFixEntrustCost(tbFixEntrustCost);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			ActionContext.getContext().put("msg","请填写正确的成本信息");
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	
	public String updateTbFixEntrustCost() {
		
		try{
			
			if(null != tbFixEntrustCost.getTbFixEntrust().getEntrustCode() && !"".equals(tbFixEntrustCost.getTbFixEntrust().getEntrustCode())){
				
				TbFixEntrust tbFixEntrust = tbFixEntrustService.findByEntrustCode(tbFixEntrustCost.getTbFixEntrust().getEntrustCode());
				
				if(null == tbFixEntrust){
					
					ActionContext.getContext().put("msg","请填写正确的委托书号");
					
					return Constants.FAILURE;
					
				}else{
					
					tbFixEntrustCost.setTbFixEntrust(tbFixEntrust);
					
				}
			}
			
			else {
				
				ActionContext.getContext().put("msg","委托书号不能为空");
				
				return Constants.FAILURE;
			}
			
			tbFixEntrustCost.setCostType("保险理赔");
			
			tbFixEntrustCostService.updateTbFixEntrustCost(tbFixEntrustCost);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			ActionContext.getContext().put("msg","请填写正确的成本信息");
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	public String deleteTbFixEntrustCost() throws Exception{
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbFixEntrustCostService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbFixEntrustCostTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbFixEntrustCostTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbFixEntrustCostTable," + Constants.EXCEPTION);
		}
		return null;
	}
 
}
