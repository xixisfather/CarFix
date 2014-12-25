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
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.business.model.TbBespokePartContent;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITbBespokePartContentService;
import com.selfsoft.business.service.ITbBookService;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmLoanRegisterDetailService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.service.ITmUserService;

public class TmEntrustSellStockAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -8222571829690983781L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmStockOutService tmStockOutService;
	
	@Autowired
	private ITmStockoutDetailService tmStockoutDetailService;
	
	@Autowired
	private ITbBusinessBalanceItemService tbBusinessBalanceItemService;
	
	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;
	
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	
	@Autowired
	private ITbBespokePartContentService tbBespokePartContentService;
	
	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	private TmStockOut tmStockOut;
	
	private String entrustId;
	
	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public TmStockOut getTmStockOut() {
		return tmStockOut;
	}

	public void setTmStockOut(TmStockOut tmStockOut) {
		this.tmStockOut = tmStockOut;
	}
 
	private TbMembershipCard tbMembershipCard;
	
	public TbMembershipCard getTbMembershipCard() {
		return tbMembershipCard;
	}

	public void setTbMembershipCard(TbMembershipCard tbMembershipCard) {
		this.tbMembershipCard = tbMembershipCard;
	}

	/**
	 * 查询委托单号下所有维修发料明细
	 * @Date      2010-6-24
	 * @Function  
	 * @return
	 */
	public String findEntrustSellStock(){
		
		String entrustId = request.getParameter("entrustId");
		
		String cardNo = request.getParameter("cardNo");

		if(null != cardNo && !"".equals(cardNo)){
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
		}
		
		//tbMembershipCard = tbMembershipCardService.findByCarInfo(tbFixEntrustService.findById(Long.valueOf(entrustId)).getTbCarInfo());
		
		List<TbMaintianVo> maintianvos = tbMaintainPartContentService.getTbMaintianDetailVosByEntrustId(Long.valueOf(entrustId),Constants.BALANCE_ALL);
			
		request.setAttribute("maintianvos",maintianvos);
			
		Double fixPartTotalAll = tbMaintainPartContentService.getTotalPriceByBalanceId(Long.valueOf(entrustId),null);
			
		Double pjYhl = 0.00d;
		
		if(null != tbMembershipCard){
			
			pjYhl = tbMembershipCard.getTmCardType().getPjYhl();
			
		}
		
		request.setAttribute("fixPartFavourRate", new BigDecimal(pjYhl).setScale(2,BigDecimal.ROUND_HALF_UP));
		
		request.setAttribute("fixPartTotalAll",new BigDecimal(fixPartTotalAll).multiply(new BigDecimal(1 - pjYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		
		request.setAttribute("fixPartTotalAllOri",fixPartTotalAll);
		
		return Constants.SUCCESS;
	}

	/**
	 * 查看结算单下所有发料明细
	 * @Date
	 * @Function  
	 * @return
	 */
	public String viewEntrustSellStock(){
		
		String entrustId = request.getParameter("entrustId");
		//结算单ID
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		List<TbMaintianVo> maintianvos =tbMaintainPartContentService.getMaintainContentsByBalanceId(Long.valueOf(entrustId),Long.valueOf(tbBusinessBalanceId));
		
		request.setAttribute("maintianvos",maintianvos);
			
		Double fixPartTotalAll = tbMaintainPartContentService.getTotalPriceByBalanceId(Long.valueOf(entrustId),Long.valueOf(tbBusinessBalanceId));
			
		request.setAttribute("fixPartTotalAll",fixPartTotalAll);
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
		
		ActionContext.getContext().put("tbBusinessBalance", tbBusinessBalance);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		return Constants.SUCCESS;
	}
	
	//修理材料汇总
	public String viewEntrustSellStockGroup() throws Exception{
		
		String entrustId = request.getParameter("entrustId");
		//结算单ID
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		List<TbMaintianVo> maintianvos =tbMaintainPartContentService.getTbMaintianDetailVosByEntrustId(Long.valueOf(entrustId),Constants.BALANCE_ALL);
		
		request.setAttribute("maintianvos",maintianvos);
			
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		ActionContext.getContext().put("fixPartFavourAmount", tbBusinessBalanceService.calcItemFavourAmount(tbBusinessBalance,"XLCLF"));
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询委托书下的销售明细
	 */
	public String findEntrustSellDetail() throws Exception{
		
		String trustBill = request.getParameter("trustBill");
		
		String cardNo = request.getParameter("cardNo");

		if(null != cardNo && !"".equals(cardNo)){
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
		}
		
		//tbMembershipCard = tbMembershipCardService.findByCarInfo(tbFixEntrustService.findByEntrustCode(trustBill).getTbCarInfo());
		
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService.getSellDetailByEntrustCode(trustBill,Constants.BALANCE_ALL);

		ActionContext.getContext().put("tmStockOutDetVos", tmStockOutDetVos);
		
		Double pjYhl = 0.00d;
		
		if(null != tbMembershipCard){
			
			pjYhl = tbMembershipCard.getTmCardType().getPjYhl();
			
		}
			
		request.setAttribute("solePartFavourRate", new BigDecimal(pjYhl).setScale(2,BigDecimal.ROUND_HALF_UP));
		
		//销售材料总价
		Double solePartTotalAll = tmStockOutService.getSellTotalPriceByBalance(trustBill,null);
		
		ActionContext.getContext().put("solePartTotalAll", new BigDecimal(solePartTotalAll).multiply(new BigDecimal(1 - pjYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		
		ActionContext.getContext().put("solePartTotalAllOri", solePartTotalAll);
		
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 查看委托书下的销售明细
	 */
	public String viewEntrustSellDetail() throws Exception{
		
		//结算单ID
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String trustBill = request.getParameter("trustBill");
		
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService. getSellDetailByBalanceId(trustBill,Long.valueOf(tbBusinessBalanceId));

		ActionContext.getContext().put("tmStockOutDetVos", tmStockOutDetVos);
		//销售材料总价
		Double solePartTotalAll = tmStockOutService.getSellTotalPriceByBalance(trustBill,Long.valueOf(tbBusinessBalanceId));
		
		ActionContext.getContext().put("solePartTotalAll", solePartTotalAll);
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
		
		ActionContext.getContext().put("tbBusinessBalance", tbBusinessBalance);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查看委托书下的销售明细汇总
	 */
	public String viewEntrustSellDetailGroup() throws Exception{
		
		//结算单ID
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String trustBill = request.getParameter("trustBill");
		
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService.getSellDetailByEntrustCode(trustBill,Constants.BALANCE_ALL);

		ActionContext.getContext().put("tmStockOutDetVos", tmStockOutDetVos);
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		ActionContext.getContext().put("solePartFavourAmount", tbBusinessBalanceService.calcItemFavourAmount(tbBusinessBalance,"XSJE"));
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询客户下所有销售明细
	 * @Date      2010-6-24
	 * @Function  
	 * @return
	 */
	public String findEntrustCustomer(){
		
		String trustBill = request.getParameter("trustBill");
		
		List<TmStockOutVo> tmStockOutDetVos = tmStockOutService.getCustomerSell();
		
		//销售材料总价
		Double solePartTotalAll = tmStockOutService.getTotalPriceByEntrustCode(trustBill);
		
		request.setAttribute("solePartTotalAll",solePartTotalAll);
		
		ActionContext.getContext().put("tmStockOutDetVos", tmStockOutDetVos);
		
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询结算单下客户下所有销售明细
	 * @Date
	 * @Function  
	 * @return
	 */
	public String viewEntrustCustomer(){
		
		//String customerId = request.getParameter("customerId");
		
		//结算单ID
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		List<TmStockOutVo> tmStockOutDetVos = tmStockOutService.getCustomerSell();
		
		ActionContext.getContext().put("tmStockOutDetVos", tmStockOutDetVos);
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
		
		ActionContext.getContext().put("tbBusinessBalance", tbBusinessBalance);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有未结算的销售单
	 * @return
	 */
	public String findBalanceSell(){
		if(tmStockOut == null)
			tmStockOut = new TmStockOut();
		
		tmStockOut.setSellBalance(Constants.NOTTRUE);
		tmStockOut.setSellType(Constants.SELLCUSTOMER);
		String isConfirms =  Constants.CONFIRM+","+Constants.NOT_CONFIRM+","+Constants.RE_BALANCE;
		List<TmStockOutVo> stockOutList = tmStockOutService.getStockOutVos(isConfirms, StockTypeElements.SELLSTOCKOUT.getElementValue(),tmStockOut,null);
		
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询单独结算单明细
	 */
	public String showTmStockOutDetail(){
		
		String tmStockOutId = request.getParameter("tmStockOutId");
		
		String cardNo = request.getParameter("cardNo");
		
		if(null != cardNo && !"".equals(cardNo)){
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
		}
		
		List<TmStockOutDetVo> detVos = tmStockOutService.getCustomerSellDetailByTmStockOutId(Long.valueOf(tmStockOutId),Constants.BALANCE_ALL);
		
		Double solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(Long.valueOf(tmStockOutId), null);
		
		ActionContext.getContext().put("solePartTotalAllOri", solePartTotalAll);
		
		Double pjYhl = 0.00D;
		
		if(null != tbMembershipCard){
			
			pjYhl = tbMembershipCard.getTmCardType().getPjYhl();
			
			solePartTotalAll = new BigDecimal(solePartTotalAll).multiply(new BigDecimal(1 - pjYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			
		}
		
		request.setAttribute("solePartFavourRate",pjYhl);
		
		request.setAttribute("solePartTotalAll",solePartTotalAll);
			
		request.setAttribute("detVos", detVos);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查看单独结算单明细
	 */
	public String viewTmStockOutDetail() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
	
		ActionContext.getContext().put("tbBusinessBalance", tbBusinessBalance);
		
		String tmStockOutId = request.getParameter("tmStockOutId");
		
		List<TmStockOutDetVo> detVos = tmStockOutService.getCustomerSellDetailByBalanceId(Long.valueOf(tmStockOutId),Long.valueOf(tbBusinessBalanceId));
		
		Double solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(Long.valueOf(tmStockOutId), Long.valueOf(tbBusinessBalanceId));
		
		request.setAttribute("solePartTotalAll",solePartTotalAll);
			
		request.setAttribute("detVos", detVos);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查看单独结算单明细汇总
	 */
	public String viewTmStockOutDetailGroup() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(tbBusinessBalanceId));
	
		String tmStockOutId = request.getParameter("tmStockOutId");
		
		List<TmStockOutDetVo> detVos = tmStockOutService.getCustomerSellDetailByTmStockOutId(Long.valueOf(tmStockOutId),Constants.BALANCE_ALL);
		
		request.setAttribute("detVos", detVos);
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
				
				ActionContext.getContext().put(tbBusinessBalanceItem.getBalanceItemCode(),tbBusinessBalanceItem.getBalanceItemTotal());
				
			}
		}
		
		ActionContext.getContext().put("solePartFavourAmount", tbBusinessBalanceService.calcItemFavourAmount(tbBusinessBalance,"XSJE"));
		
		return Constants.SUCCESS;
	}
	
	
	public String findEnstrustTbMaintainDetail(){
//		String entrustId = request.getParameter("entrustId");
//		TbMaintainPartContent queryEntity = new TbMaintainPartContent();
//		queryEntity.setEntrustId(new Long(entrustId));
//		List<TbMaintainPartContent> result = tbMaintainPartContentService.findByEntity(queryEntity);
		List<TbMaintainPartContent> result = tbMaintainPartContentService.getViewEntrustMaintianContent(new Long(entrustId));
		List<TbMaintianVo> maintianvos  = null;
		Double maintianTotalPrice = 0D;
		if(result != null && result.size()>0){
			maintianvos  = tbMaintainPartContentService.getTbMaintianDetailVos(result.get(0).getMaintainCode());
			for(TbMaintianVo mvo : maintianvos){
				maintianTotalPrice += mvo.getPartQuantity() * mvo.getPrice();
			}
			
		}
		request.setAttribute("maintianTotalPrice",maintianTotalPrice);
		request.setAttribute("maintianvos", maintianvos);
		return Constants.SUCCESS;
	}
	
	
	public String findBespokePartContent(){
		String entrustId = request.getParameter("entrustId");
		TbBespokePartContent queryEntity = new TbBespokePartContent();
		TbFixEntrust tbFixEntrust = new TbFixEntrust();
		tbFixEntrust.setId(new Long(entrustId));
		queryEntity.setTbFixEntrust(tbFixEntrust);
		List<TbBespokePartContent> bespokePartContents = tbBespokePartContentService.findByEntity(queryEntity);
		request.setAttribute("bespokePartContents", bespokePartContents);
		return Constants.SUCCESS;
	}
}
