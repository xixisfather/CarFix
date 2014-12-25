package com.selfsoft.baseinformation.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;

public class TbMembershipCardAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 6974363894278524576L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private TbMembershipCard tbMembershipCard;
	

	public TbMembershipCard getTbMembershipCard() {
		return tbMembershipCard;
	}

	public void setTbMembershipCard(TbMembershipCard tbMembershipCard) {
		this.tbMembershipCard = tbMembershipCard;
	}

	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	
	@Autowired
	private ITmCardTypeService tmCardTypeService;
	
	@Autowired
	private ITbCardHisService tbCardHisService;
	
	public String findTbMembershipCard() throws Exception{
		
		Map<Long, String> tmCardTypeMap = tmCardTypeService.findAllTmCardTypeMap();
		
		Map<Long, String> cardStatusMap = Constants.getCardStatusMap();
		
		ActionContext.getContext().put("tmCardTypeMap",tmCardTypeMap);
		
		ActionContext.getContext().put("cardStatusMap",cardStatusMap);
		
		List<TbMembershipCard> tbMembershipCardList = tbMembershipCardService.findByTbMembershipCard(tbMembershipCard);
		
		ActionContext.getContext().put("tbMembershipCardList", tbMembershipCardList);
		
		return Constants.SUCCESS;
	}
	
	public String forwardPage() throws Exception{
		
		String id = request.getParameter("id");
		
		Map<Long, String> tmCardTypeMap = tmCardTypeService.findAllTmCardTypeMap();
		
		ActionContext.getContext().put("tmCardTypeMap",tmCardTypeMap);
		
		if (null != id && !"".equals(id)) {

			tbMembershipCard = tbMembershipCardService.findById(Long.valueOf(id));
			
			String flag = request.getParameter("flag");
			
			if("cz".equals(flag)){
				
				return "cz";
				
			}
			
			else if("cjf".equals(flag)){
				
				
				return "cjf";
			}
			else if("jfxf".equals(flag)){
				
				
				return "jfxf";
			}
			else if("hk".equals(flag)){
				
				return "hk";
			}
			
			else if("pass".equals(flag)){
				
				return "pass";
				
			}

			return Constants.EDITPAGE;
		}
		
		return Constants.ADDPAGE;
		
	}
	
	public String insertTbMembershipCard() throws Exception{
		
		tbMembershipCard.setCreateDate(new Date());
		
		tbMembershipCard.setCardStatus(Constants.CARD_VALID_STATUS);
		
		if(null!=tbMembershipCard.getTbCarInfo()){
			
			if(null != tbMembershipCardService.findByCarInfo(tbMembershipCard.getTbCarInfo())){
				
				ActionContext.getContext().put("msg","车牌号【 "+ tbMembershipCard.getLicenseCode() +"】已经办理了会员卡,卡号为【" +tbMembershipCardService.findByCarInfo(tbMembershipCard.getTbCarInfo()).getCardNo()+"】");
				
				return Constants.FAILURE;
				
			}
			
		}
		
		String pass = CommonMethod.encryptBASE64(tbMembershipCard.getCardPassword()).toString();
		
		tbMembershipCard.setCardPassword(pass);
		
		tbMembershipCard.setTmCardType(tmCardTypeService.findById(tbMembershipCard.getTmCardType().getId()));
		
		if(tbMembershipCardService.validateCarNo(tbMembershipCard)){
			
			tbMembershipCardService.insertKkTbMembershipCard(tbMembershipCard, (TmUser)request.getSession().getAttribute("tmUser"));
			
		}
		else{
			
			ActionContext.getContext().put("msg","会员卡内码错误.");
			
			return Constants.FAILURE;
			
		}
		
		
		return Constants.SUCCESS;
	}
	
	public String updateTbMembershipCard() throws Exception{
		
		if(null!=tbMembershipCard.getTbCarInfo()){
			
			if(null != tbMembershipCardService.findByCarInfo(tbMembershipCard.getTbCarInfo())){
				
				if(!tbMembershipCardService.findByCarInfo(tbMembershipCard.getTbCarInfo()).getId().equals(tbMembershipCard.getId())){
				
					ActionContext.getContext().put("msg","车牌号【 "+ tbMembershipCard.getLicenseCode() +"】已经办理了会员卡,卡号为【" +tbMembershipCardService.findByCarInfo(tbMembershipCard.getTbCarInfo()).getCardNo()+"】");
				
					return Constants.FAILURE;
				}
			}
			
		}
		
		TbMembershipCard _tbMembershipCard = tbMembershipCardService.findById(tbMembershipCard.getId());
		
		tbMembershipCard.setCardPassword(_tbMembershipCard.getCardPassword());
		
		if(tbMembershipCardService.validateCarNo(tbMembershipCard)){
			tbMembershipCardService.updateTbMembershipCard(tbMembershipCard);
		}else{
			
			ActionContext.getContext().put("msg","会员卡内码错误.");
			
			return Constants.FAILURE;
			
		}
		
		return Constants.SUCCESS;
	}
	
	public String deleteTbMembershipCard() throws Exception{
		
		String id = request.getParameter("id");
		
		String flags = request.getParameter("flag");

		if (null != id && !"".equals(id)) {

			tbMembershipCard = tbMembershipCardService.findById(Long.valueOf(id));
			
			if("gs".equals(flags)){
				
				tbMembershipCard.setCardStatus(Constants.CARD_LOST_STATUS);
				
			}
			
			else if("sx".equals(flags)){
				
				tbMembershipCard.setCardStatus(Constants.CARD_NO_VALID_STATUS);
				
			}
			
			
			
			boolean flag = false;
			
			try{
				
				tbMembershipCardService.updateTbMembershipCard(tbMembershipCard);
				
				flag = true;
				
			}catch(Exception e){
				
				flag = false;
				
				e.printStackTrace();
				
			}
			
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbMembershipCardTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbMembershipCardTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbMembershipCardTable," + Constants.EXCEPTION);
		}
		
		return null;
	}
	
	public String validTbMembershipCard() throws Exception{
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tbMembershipCard = tbMembershipCardService.findById(Long.valueOf(id));
			
			tbMembershipCard.setCardStatus(Constants.CARD_VALID_STATUS);
			
			boolean flag = false;
			
			try{
				
				tbMembershipCardService.updateTbMembershipCard(tbMembershipCard);
				
				flag = true;
				
			}catch(Exception e){
				
				flag = false;
				
				e.printStackTrace();
				
			}
			
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbMembershipCardTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbMembershipCardTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbMembershipCardTable," + Constants.EXCEPTION);
		}
		
		return null;
	}
	
	public String czTbMembershipCard() throws Exception{
		try{
			
			tbMembershipCardService.insertCzTbMembershipCard(tbMembershipCard, (TmUser)request.getSession().getAttribute("tmUser"));
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	public String cjfTbMembershipCard() throws Exception{
		
		try{
			
			tbMembershipCardService.insertCjfTbMembershipCard(tbMembershipCard, (TmUser)request.getSession().getAttribute("tmUser"));
		
		}catch(Exception e){
			
			e.printStackTrace();
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	public String xfjfTbMembershipCard() throws Exception{
		
		try{
			
			tbMembershipCardService.insertJfxfTbMembershipCard(tbMembershipCard, (TmUser)request.getSession().getAttribute("tmUser"));
		
		}catch(Exception e){
			
			e.printStackTrace();
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	public String hkTbMembershipCard() throws Exception{
		
		try{
			String currentCardNo = tbMembershipCard.getCurrentCardNo();
			
			if(null != tbMembershipCardService.findByCardNo(currentCardNo)){
				
				if(!tbMembershipCardService.findById(tbMembershipCard.getId()).getCardNo().equals(currentCardNo)){
					
					ActionContext.getContext().put("msg","会员卡号【" + currentCardNo + "】已经为车牌号【" + tbMembershipCardService.findById(tbMembershipCard.getId()).getTbCarInfo().getLicenseCode() + "】办理");
					
					return Constants.FAILURE;
					
				}
				
			}
			
			tbMembershipCard = tbMembershipCardService.findById(tbMembershipCard.getId());
			
			tbMembershipCard.setPreviousCardNo(tbMembershipCard.getCardNo());
			
			tbMembershipCard.setCardNo(currentCardNo);
			
			tbMembershipCardService.insertHkTbMembershipCard(tbMembershipCard, (TmUser)request.getSession().getAttribute("tmUser"));
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	public String findByCardNo() throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		
		String cardNo = request.getParameter("cardNo");
		
		tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
		
		if(null != tbMembershipCard){
			
			//String cardDesc = CommonMethod.TransferToEncoding(tbMembershipCard.getTmCardType().getCardDesc(), "ISO-8859-1", "UTF-8");
			
			response.getWriter().print("success," + tbMembershipCard.getId()+","+cardNo+","+tbMembershipCard.getTmCardType().getCardDesc()+","+tbMembershipCard.getCardSaving()+","+tbMembershipCard.getCardPoint()+","+tbMembershipCard.getTmCardType().getGsYhl()+","+tbMembershipCard.getTmCardType().getPjYhl());
			
		}
		else{
			
			response.getWriter().print("fail,");
		}
		
		return null;
	}
	
	public String resetPassword() throws Exception{

		String cardPassword = CommonMethod.encryptBASE64(tbMembershipCard.getCardPassword()).toString();
		
		tbMembershipCard = tbMembershipCardService.findById(tbMembershipCard.getId());
		
		tbMembershipCard.setCardPassword(cardPassword);
		
		tbMembershipCardService.updateTbMembershipCard(tbMembershipCard);
		
		return Constants.SUCCESS;
	}
	
	public String encryptPassword() throws Exception{
		
		String pass = request.getParameter("pass");
		
		try{
			
			
			response.getWriter().print("success," + CommonMethod.encryptBASE64(pass).toString());
			
		}catch(Exception e){
			
			response.getWriter().print("fail,");
			
			
		}
		
		return null;
	}
}
