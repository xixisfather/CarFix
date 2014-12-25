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
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.framework.common.Constants;

public class TbFixEntrustContentAction extends ActionSupport implements
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
	private ITbFixEntrustContentService tbFixEntrustContentService;
	
	private TbFixEntrustContent tbFixEntrustContent;
	
	private final String WTSPAGE = "wtsPage";
	
	private final String JSDPAGE = "jsdPage";

	private TbFixEntrust tbFixEntrust;
	
	@Autowired
	private ITbBusinessBalanceItemService tbBusinessBalanceItemService;
	
	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;
	
	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public TbFixEntrustContent getTbFixEntrustContent() {
		return tbFixEntrustContent;
	}

	public void setTbFixEntrustContent(TbFixEntrustContent tbFixEntrustContent) {
		this.tbFixEntrustContent = tbFixEntrustContent;
	}
	
	private TbMembershipCard tbMembershipCard;
	
	public TbMembershipCard getTbMembershipCard() {
		return tbMembershipCard;
	}

	public void setTbMembershipCard(TbMembershipCard tbMembershipCard) {
		this.tbMembershipCard = tbMembershipCard;
	}

	public String findTbFixEntrustContentListByTbFixEntrustId() throws Exception{
		
		String flag = request.getParameter("flag");
		
		String tbFixEntrustId = request.getParameter("tbFixEntrustId");
		
		String cardNo = request.getParameter("cardNo");

		if(null != cardNo && !"".equals(cardNo)){
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
		}
		
		if(null==tbFixEntrustId){
			tbFixEntrustId = request.getParameter("tbFixEntrust.id");
		}
		
		List<TbFixEntrustContent> tbFixEntrustContentList = null; 
			
		if("wts".equals(flag)){
			
			tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustId(Long.valueOf(tbFixEntrustId));
			
			ActionContext.getContext().put("fixHourTotal", new BigDecimal(tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustId(Long.valueOf(tbFixEntrustId))).setScale(2,BigDecimal.ROUND_HALF_UP));
			
		}
		else if("jsd".equals(flag)){
			//改成显示所有 已经结算的将标志为已经结算
			//tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceIdIsNull(Long.valueOf(tbFixEntrustId));
			tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustId(Long.valueOf(tbFixEntrustId));
		}
		
		ActionContext.getContext().put("tbFixEntrustContentList", tbFixEntrustContentList);
		//工时总价
		Double workingHourTotalAll = tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustIdAndBalanceIdIsNull(Long.valueOf(tbFixEntrustId));
		
		tbFixEntrust = new TbFixEntrust();
		
		tbFixEntrust.setId(Long.valueOf(tbFixEntrustId));
		
		//TbMembershipCard tbMembershipCard = tbMembershipCardService.findByCarInfo(tbFixEntrustService.findById(Long.valueOf(tbFixEntrustId)).getTbCarInfo());
		
		Double gsYhl = 0.00D;
		
		if(null != tbMembershipCard){
			
			gsYhl = tbMembershipCard.getTmCardType().getGsYhl();
			
		}
			
		request.setAttribute("workingHourFavourRate", new BigDecimal(gsYhl).setScale(2,BigDecimal.ROUND_HALF_UP));
		
		/**
		 * 原价
		 */
		tbFixEntrust.setWorkingHourTotalAllOri(workingHourTotalAll);
		
		tbFixEntrust.setWorkingHourTotalAll(new BigDecimal(workingHourTotalAll).multiply(new BigDecimal(1 - gsYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		
		if("wts".equals(flag)){
			return this.WTSPAGE;
		}
		else if("jsd".equals(flag)){
			return this.JSDPAGE;
		}
		
		return null;
	}
	
	public String viewBusinessBalanceTbFixEntrustContent() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String tbFixEntrustId = request.getParameter("tbFixEntrustId");
		
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceId(Long.valueOf(tbFixEntrustId),Long.valueOf(tbBusinessBalanceId));
		
		ActionContext.getContext().put("tbFixEntrustContentList", tbFixEntrustContentList);
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
	
		ActionContext.getContext().put("tbBusinessBalance", tbBusinessBalance);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		
		return Constants.VIEWPAGE;
	}
	
	//汇总的工时查询
	public String viewBusinessBalanceTbFixEntrustContentGroup() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String tbFixEntrustId = request.getParameter("tbFixEntrustId");
		
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustId(Long.valueOf(tbFixEntrustId));
		
		ActionContext.getContext().put("tbFixEntrustContentList", tbFixEntrustContentList);
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
	
		ActionContext.getContext().put("tbBusinessBalance", tbBusinessBalance);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		ActionContext.getContext().put("fixContentFavourAmount", tbBusinessBalanceService.calcItemFavourAmount(tbBusinessBalance,"XLGSF"));
		
		return Constants.VIEWPAGE;
	}
}
