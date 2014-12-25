package com.selfsoft.baseparameter.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.model.TmCardType;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;

public class TmCardTypeAction extends ActionSupport implements
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
	private ITmCardTypeService tmCardTypeService;
	
	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	
	private TmCardType tmCardType;

	public TmCardType getTmCardType() {
		return tmCardType;
	}

	public void setTmCardType(TmCardType tmCardType) {
		this.tmCardType = tmCardType;
	}
	
	public String findTmCardType() throws Exception{
		
		List<TmCardType>tmCardTypeList = tmCardTypeService.findAll();
		
		ActionContext.getContext().put("tmCardTypeList", tmCardTypeList);
		
		return Constants.SUCCESS;
	}
	
	public String insertTmCardType() throws Exception{
		
		try{
			
			tmCardTypeService.insert(tmCardType);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			ActionContext.getContext().put("msg","*号为必填项,必须填入整数");
			
			return Constants.FAILURE;
			
		}
		
		return Constants.SUCCESS;
		
	}

	public String updateTmCardType() throws Exception{
		
		tmCardTypeService.update(tmCardType);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmCardType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmCardTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmCardTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmCardTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmCardTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmCardType = tmCardTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String findByCZJE() throws Exception{
		
		String czje = request.getParameter("czje");
		
		String tbMembershipCardId = request.getParameter("tbMembershipCardId");
		
		try{
			
			TbMembershipCard t = tbMembershipCardService.findById(Long.valueOf(tbMembershipCardId));
			
			Integer cGiveMoney = tmCardTypeService.calcCGiveMoney(Double.valueOf(czje), t.getTmCardType().getId());
			
			Integer cGivePoint = tmCardTypeService.calcCGivePoint(Double.valueOf(czje), t.getTmCardType().getId());
		
			BigDecimal d_money = new BigDecimal("0.00");
			
			d_money = d_money.add(new BigDecimal(t.getCardSaving()));
			
			d_money = d_money.add(new BigDecimal(czje));
			
			d_money = d_money.add(new BigDecimal(tmCardTypeService.calcCGiveMoney(Double.valueOf(czje), t.getTmCardType().getId())));
			
			BigDecimal d_point = new BigDecimal("0");
			
			d_point = d_point.add(new BigDecimal(t.getCardPoint()));
			
			d_point = d_point.add(new BigDecimal(tmCardTypeService.calcCGivePoint(Double.valueOf(czje), t.getTmCardType().getId())));
			
			
			response.getWriter().print(cGiveMoney + "," + cGivePoint + "," + d_money.doubleValue() + "," + d_point.doubleValue());
		
		}catch(Exception e){
			
			//response.getWriter().print("0.00,0");
			
		}
		
		return null;
	}
	
	public String findByCJF() throws Exception{
		
		String czjf = request.getParameter("czjf");
		
		String tbMembershipCardId = request.getParameter("tbMembershipCardId");
		
		String flag = request.getParameter("flag");
		
		String pass = request.getParameter("pass");
		
		if(null == pass){
			
			pass = "";
			
		}
		try{
			
			TbMembershipCard tbMembershipCard = tbMembershipCardService.findById(Long.valueOf(tbMembershipCardId));
			
			if("sub".equals(flag)){
				
				if(!CommonMethod.encryptBASE64(pass.trim()).equals(tbMembershipCard.getCardPassword())){
					
					response.getWriter().print("passFail" + ",");
					
					return null;
				}
				
			}
			
			
			BigDecimal d = new BigDecimal("0");
			
			d = d.add(new BigDecimal(tbMembershipCard.getCardPoint()));
			
			if("add".equals(flag)){
				d = d.add(new BigDecimal(czjf));
			}
			else if("sub".equals(flag)){
				d = d.subtract(new BigDecimal(czjf));
			}
			
			
			response.getWriter().print(d.longValue() + ",");
		
		}catch(Exception e){
			
			//response.getWriter().print("0.00,0");
			
		}
		
		return null;
	}	
}
