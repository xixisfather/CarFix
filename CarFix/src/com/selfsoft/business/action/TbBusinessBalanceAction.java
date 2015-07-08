package com.selfsoft.business.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;
import com.selfsoft.baseparameter.service.ITmBalanceService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbFixEntrustCost;
import com.selfsoft.business.model.TbReceiveFree;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.business.service.IStatisticsWorkBusinessService;
import com.selfsoft.business.service.ITbAnvancePayService;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustCostService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITbReceiveFreeService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.BusinessBalanceCostPriceVo;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.file.ReportFileFromStream;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TbBusinessBalanceAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 7830186376262412685L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	private TbBusinessBalance tbBusinessBalance;

	private final String WTSPAGE = "wtsPage";

	private final String XSDPAGE = "xsdPage";

	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;

	@Autowired
	private ITmFixTypeService tmFixTypeService;

	@Autowired
	private ITbCarInfoService tbCarInfoService;

	@Autowired
	private ITmUserService tmUserService;

	@Autowired
	private ITmDictionaryService tmDictionaryService;

	@Autowired
	private ITmBalanceService tmBalanceService;

	@Autowired
	private ITbFixEntrustContentService tbFixEntrustContentService;

	@Autowired
	private ITmBalanceItemService tmBalanceItemService;

	@Autowired
	private ITbAnvancePayService tbAnvancePayService;

	@Autowired
	private ITmStockOutService tmStockOutService;

	@Autowired
	private ITbBusinessBalanceItemService tbBusinessBalanceItemService;

	@Autowired
	private ITbCustomerService tbCustomerService;

	@Autowired
	private ITmSoleTypeService tmSoleTypeService;

	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	
	@Autowired
	private IStatisticsWorkBusinessService statisticsWorkBusinessService;
	
	@Autowired
	private IStatisticsStockInOutService statisticsStockInOutService;
	
	private TbReceiveFree tbReceiveFree;
	
	@Autowired
	private ITbReceiveFreeService tbReceiveFreeService;
	
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	@Autowired
	private ITbFixEntrustCostService tbFixEntrustCostService;
	
	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	
	@Autowired
	private ITmCardTypeService tmCardTypeService;
	
	private TbFixEntrust tbFixEntrust;

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public TbReceiveFree getTbReceiveFree() {
		return tbReceiveFree;
	}

	public void setTbReceiveFree(TbReceiveFree tbReceiveFree) {
		this.tbReceiveFree = tbReceiveFree;
	}

	public TbBusinessBalance getTbBusinessBalance() {
		return tbBusinessBalance;
	}

	public void setTbBusinessBalance(TbBusinessBalance tbBusinessBalance) {
		this.tbBusinessBalance = tbBusinessBalance;
	}

	public String findTbBusinessBalance() {
		
		if(null == tbBusinessBalance){
			
			tbBusinessBalance = new TbBusinessBalance();
			
			Date d = new Date();
			
			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");
			
			Date e = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-" + CommonMethod.getMonthDays(CommonMethod.getYear(d), CommonMethod.getMonth(d)), "yyyy-MM-dd");
			
			tbBusinessBalance.setBananceDateStart(s);
			
			tbBusinessBalance.setBananceDateEnd(e);
		}
		
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
				.findByTbBusinessBalance(tbBusinessBalance);
		
		List<TbBusinessBalance> tbBusinessBalanceListPage = new ArrayList<TbBusinessBalance>();
		
		BigDecimal costTotal = new BigDecimal("0.00");
		
		if(null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0){
			
			for(TbBusinessBalance ti : tbBusinessBalanceList){
			
				TbCustomer tbCustomer = null;
				
				if (null != ti.getTbFixEntrust()) {

					tbCustomer = ti.getTbFixEntrust().getTbCarInfo()
							.getTbCustomer();

					ti.setLicenseCode(ti.getTbFixEntrust()
							.getTbCarInfo().getLicenseCode());
					
					ti.setUserRealNameServer(ti.getTbFixEntrust().getTmUser().getUserRealName());
					
					BigDecimal djCost = tbFixEntrustCostService.sumTbFixEntrustCostByTbFixEntrustId(ti.getTbFixEntrust().getId());
					
					ti.setDjCost(djCost.divide(new BigDecimal("1.00"), 2,
							BigDecimal.ROUND_HALF_UP));
					
					
					
					
				}else {
					
					tbCustomer = tbCustomerService.findById(ti
							.getTmStockOut().getCustomerBill());
					
				}
				
				ti.setTbCustomer(tbCustomer);
				
				Double pjCost = statisticsStockInOutService.getMaintainCostPriceByBalanceId(ti.getId());
				
				ti.setPjCost(new BigDecimal(pjCost).divide(new BigDecimal("1.00"), 2,
						BigDecimal.ROUND_HALF_UP));
				
				costTotal = costTotal.add(ti.getPjCost());
				
				
				
				
				
				tbBusinessBalanceListPage.add(ti);
			}
			
		}
		

		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceListPage);
		
		ActionContext.getContext().getSession().put("tbBusinessBalance",
				tbBusinessBalance);
		
		ActionContext.getContext().getSession().put("tbBusinessBalanceList",
				tbBusinessBalanceListPage);
		
		ActionContext.getContext().put("totalCount",tbBusinessBalanceListPage.size());
		
		ActionContext.getContext().getSession().put("totalCount",tbBusinessBalanceListPage.size());
		
		Map<String,BigDecimal> map = tbBusinessBalanceService.sumGroupList(tbBusinessBalanceListPage);
		
		for(String s : map.keySet()){
			
			ActionContext.getContext().put(s,map.get(s));
			
			ActionContext.getContext().getSession().put(s,map.get(s));
			
		}
		
			
		ActionContext.getContext().put("costTotal",costTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
		
		ActionContext.getContext().getSession().put("costTotal",costTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
			
		Double benifit = new BigDecimal(String.valueOf(ActionContext.getContext().get("payed")==null?"0.00":ActionContext.getContext().get("payed"))).subtract(costTotal).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		ActionContext.getContext().put("benifit",new BigDecimal(benifit).subtract(new BigDecimal(map.get("djcb").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		
		ActionContext.getContext().put("tmUserMap",tmUserService.findAllTmUserMap());
		

		return Constants.SUCCESS;
	}
	//结算单汇总查询
	public String findGroupTbBusinessBalance() throws Exception{
		
		if(null == tbBusinessBalance){
			
			tbBusinessBalance = new TbBusinessBalance();
			
			Date d = new Date();
			
			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");
			
			Date e = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-" + CommonMethod.getMonthDays(CommonMethod.getYear(d), CommonMethod.getMonth(d)), "yyyy-MM-dd");
			
			tbBusinessBalance.setBananceDateStart(s);
			
			tbBusinessBalance.setBananceDateEnd(e);
		}
		
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
						tmFixTypeService.findAllTmFixTypeMap());
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
		.findTbBusinessBalanceToGroup(tbBusinessBalance);
		
		ActionContext.getContext().getSession().put("tbBusinessBalance",
				tbBusinessBalance);
		
		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceList);
		
		ActionContext.getContext().getSession().put("tbBusinessBalanceList",
				tbBusinessBalanceList);
		
		ActionContext.getContext().put("totalCount",tbBusinessBalanceList.size());
		
		ActionContext.getContext().getSession().put("totalCount",tbBusinessBalanceList.size());
		
		Map<String,BigDecimal> map = tbBusinessBalanceService.sumGroupList(tbBusinessBalanceList);
		
		BusinessBalanceCostPriceVo businessBalanceCostPriceVo = null;
		 
		if(null!=tbBusinessBalance){
			
			businessBalanceCostPriceVo = new BusinessBalanceCostPriceVo();
			
			businessBalanceCostPriceVo.setBalanceCode(tbBusinessBalance.getBalanceCode());
			
			businessBalanceCostPriceVo.setEntrustCode(tbBusinessBalance.getEntrustCode());
			
			if(null!=tbBusinessBalance.getTmUser()&&null!=tbBusinessBalance.getTmUser().getId())
				
				businessBalanceCostPriceVo.setUserId(tbBusinessBalance.getTmUser().getId());
			
			if(null!=tbBusinessBalance&&null!=tbBusinessBalance.getUserId())
				businessBalanceCostPriceVo.setServiceUserId(tbBusinessBalance.getUserId());
			
			if(null!=tbBusinessBalance.getBananceDateStart()){
				
				businessBalanceCostPriceVo.setBeginBalanceDeadTime(CommonMethod.parseDateToString(tbBusinessBalance.getBananceDateStart(), "yyyy-MM-dd"));
				
			}
			
			if(null!=tbBusinessBalance.getBananceDateEnd()){
				
				businessBalanceCostPriceVo.setEndBalanceDeadTime(CommonMethod.parseDateToString(tbBusinessBalance.getBananceDateEnd(), "yyyy-MM-dd"));
				
			}
			
			if(null!=tbBusinessBalance.getLicenseCode()){
				
				businessBalanceCostPriceVo.setLicenseCode(tbBusinessBalance.getLicenseCode());
				
			}
			
			
		}
		
		//Double costTotal = statisticsWorkBusinessService.getTotalCostPriceBusinessBalance(businessBalanceCostPriceVo);
		Double costTotal = 0d;
		
		if(null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0){
			
			costTotal = tbBusinessBalanceList.get(tbBusinessBalanceList.size() -  1).getPjCostAll().doubleValue();
			
		}
		
		if(null!=costTotal){
		
			ActionContext.getContext().put("costTotal",new BigDecimal(String.valueOf(costTotal)).setScale(2,BigDecimal.ROUND_HALF_UP));
		
			ActionContext.getContext().getSession().put("costTotal",new BigDecimal(String.valueOf(costTotal)).setScale(2,BigDecimal.ROUND_HALF_UP));
			
		}
		else{
			
			costTotal = 0.00d;
		}
		for(String s : map.keySet()){
			
			ActionContext.getContext().put(s,map.get(s));
			
			ActionContext.getContext().getSession().put(s,map.get(s));
			
		}
		
		Double benifit = new BigDecimal(String.valueOf(ActionContext.getContext().get("payed")==null?"0.00":ActionContext.getContext().get("payed"))).subtract(new BigDecimal(costTotal)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		ActionContext.getContext().put("benifit",new BigDecimal(benifit).subtract(new BigDecimal(map.get("djcb").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		ActionContext.getContext().getSession().put("benifit",new BigDecimal(benifit).subtract(new BigDecimal(map.get("djcb").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		ActionContext.getContext().put("tmUserMap",tmUserService.findAllTmUserMap());
		
		ActionContext.getContext().put("payMap",Constants.getPayMap());
		
		return Constants.SUCCESS;
	}

	public String insertTbBusinessBalanceWts() throws Exception {

		if(null==tbBusinessBalance.getTmUser()||null==tbBusinessBalance.getTmUser().getId()){
			
			ActionContext.getContext().put("msg","登陆超时,请重新登陆");
			
			return Constants.FAILURE;
		}
		
		Set tbBusinessBalanceItems = new HashSet();

		// 总金额
		String ZJE = request.getParameter("ZJE");
		
		if(ZJE==null||"".equals(ZJE)){
			ZJE = "0.00";
		}
		//修理工时费
		String XLGSF = request.getParameter("XLGSF");
		
		if(XLGSF==null||"".equals(XLGSF)){
			XLGSF = "0.00";
		}
		//修理材料费
		String XLCLF = request.getParameter("XLCLF");
		
		if(XLCLF==null||"".equals(XLCLF)){
			XLCLF = "0.00";
		}
		//销售金额
		String XSJE = request.getParameter("XSJE");
		
		if(XSJE==null||"".equals(XSJE)){
			XSJE = "0.00";
		}
		
		String[] params = request.getParameterValues("params");
		
		Double yhMoney = 0d;

		if (null != params && params.length > 0) {

			for (int i = 0; i < params.length; i++) {

				String balanceItemCode = params[i];

				Double balanceItemTotal = Double.valueOf(request
						.getParameter(params[i]));
				
				if("QL".equals(balanceItemCode)) {
					yhMoney = balanceItemTotal;
				}

				TbBusinessBalanceItem tbBusinessBalanceItem = new TbBusinessBalanceItem();

				tbBusinessBalanceItem.setBalanceItemCode(balanceItemCode);

				tbBusinessBalanceItem.setBalanceItemTotal(balanceItemTotal);

				tbBusinessBalanceItems.add(tbBusinessBalanceItem);
			}
		}

		tbBusinessBalance.setTbBusinessBalanceItems(tbBusinessBalanceItems);

		String balanceCode = "";
		
		TbBusinessBalance historyBalance = tbBusinessBalanceService.findByEntrustId(tbBusinessBalance.getTbFixEntrust().getId());
		
		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessBalance.setBalanceTotalAll(Double.valueOf(ZJE));
		//设置修理工时费
		tbBusinessBalance.setWorkingHourTotalAll(Double.valueOf(XLGSF));
		//设置修理材料费
		tbBusinessBalance.setFixPartTotalAll(Double.valueOf(XLCLF));
		//设置销售金额
		tbBusinessBalance.setSolePartTotalAll(Double.valueOf(XSJE));
		
		//tbBusinessBalance.setBalanceCode(balanceCode);

		// 设置结算单状态----已结算
		tbBusinessBalance.setBalanceStatus(Constants.JSDHASBALANCE);
		//设置已付款金额
		tbBusinessBalance.setPayedAmount(new BigDecimal(String.valueOf(tbBusinessBalance.getPayedAmount())).add(new BigDecimal(tbBusinessBalance.getShouldPayAmount())).doubleValue());

		TbFixEntrust tbFixEntrust = tbFixEntrustService
				.findById(tbBusinessBalance.getTbFixEntrust().getId());

		if (Constants.ISBALANCE.equals(tbFixEntrust.getEntrustStatus())) {

			ActionContext.getContext().put("msg", "该委托书已被结算!");

			return Constants.FAILURE;
		}

		if(null==historyBalance){
			balanceCode = tmDictionaryService
				.GenerateCode(StockTypeElements.TBBUSINESSBALANCE
						.getElementString());
			tbBusinessBalance.setSnNo(1);
		}
		else{
			
			balanceCode = historyBalance.getBalanceCode();
			
			tbBusinessBalance.setSnNo(historyBalance.getSnNo()+1);
		}
		
		tbBusinessBalance.setBalanceCode(balanceCode);
		
		//TbMembershipCard tbMembershipCard = tbMembershipCardService.findByCarInfo(tbFixEntrust.getTbCarInfo());
		
		String cardNo = request.getParameter("tbMembershipCard.cardNo");
		
		TbMembershipCard tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
		
		if(null != tbMembershipCard){
			
			/**
			 * 卡内支付金额
			 */
			Double cardZFJE = new BigDecimal(tbBusinessBalance.getCardZFJE() == null ? 0.00d : tbBusinessBalance.getCardZFJE()).doubleValue();
			/**
			 * 工时金额
			 */
			Double gsMoney = Double.valueOf(XLGSF);
			/**
			 * 配件金额
			 */
			Double pjMoney =  new BigDecimal(Double.valueOf(XLCLF)).add(new BigDecimal(Double.valueOf(XSJE))).doubleValue();
			/**
			 * 工时返回金额
			 */
			Integer gsGiveMoney = tmCardTypeService.calcGsGiveMoney(gsMoney, tbMembershipCard.getTmCardType().getId(), tbFixEntrust.getTmFixType().getFixType());
		
			/**
			 * 配件返回金额
			 */
			Integer pjGiveMoney = tmCardTypeService.calcPjGiveMoney(pjMoney, tbMembershipCard.getTmCardType().getId(),tbFixEntrust.getTmFixType().getFixType());
		
			/**
			 * 工时返回积分
			 */
			Integer gsGivePoint = tmCardTypeService.calcGsGivePoint(gsMoney, tbMembershipCard.getTmCardType().getId(),tbFixEntrust.getTmFixType().getFixType());
			
			/**
			 * 配件返回积分
			 */
			Integer pjGivePoint = tmCardTypeService.calcPjGivePoint(pjMoney, tbMembershipCard.getTmCardType().getId(),tbFixEntrust.getTmFixType().getFixType());
		
			/**
			 * 优惠扣除积分
			 */
			Integer yhMinusPoint = tmCardTypeService.calcYhMinusPoint(yhMoney, tbMembershipCard.getTmCardType().getId());
			
			/**
			 * 积分扣除积分
			 */
			Integer dhMinusPoint = tmCardTypeService.calcYhMinusPoint(Double.valueOf(tbBusinessBalance.getDhMoney()), tbMembershipCard.getTmCardType().getId());
			
			/**
			 * 消费后金额
			 */
			Double cardSaving = new BigDecimal(tbMembershipCard.getCardSaving()).add(new BigDecimal(gsGiveMoney)).add(new BigDecimal(pjGiveMoney)).subtract(new BigDecimal(cardZFJE)).doubleValue();
		
			/**
			 * 消费后积分
			 */
			Long cardPoint = 0L;
			
			if(!tbBusinessBalance.getDhMoney().equals(0)) {
				
				Integer calcDHPint = tbMembershipCardService.calcDhJF(cardNo, tbBusinessBalance.getDhMoney());
				
				cardPoint = new BigDecimal(tbMembershipCard.getCardPoint()).subtract(new BigDecimal(calcDHPint)).add(new BigDecimal(gsGivePoint)).add(new BigDecimal(pjGivePoint)).subtract(new BigDecimal(yhMinusPoint)).subtract(new BigDecimal(dhMinusPoint)).longValue();
				
			}
			
			else {
				
				cardPoint = new BigDecimal(tbMembershipCard.getCardPoint()).add(new BigDecimal(gsGivePoint)).add(new BigDecimal(pjGivePoint)).subtract(new BigDecimal(yhMinusPoint)).longValue();
				
			}
					
					
			tbMembershipCard.setCardZFJE(cardZFJE);
			
			tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
			
			tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
			
			tbMembershipCard.setCardSaving(cardSaving);
			
			tbMembershipCard.setCardPoint(cardPoint);
			
			tbMembershipCard.setGsGiveMoney(gsGiveMoney);
			
			tbMembershipCard.setPjGiveMoney(pjGiveMoney);
			
			tbMembershipCard.setGsGivePoint(gsGivePoint);
			
			tbMembershipCard.setPjGivePoint(pjGivePoint);
			
			tbMembershipCard.setYhMoney(yhMoney + tbBusinessBalance.getDhMoney());
			
			tbMembershipCard.setYhMinusPoint(yhMinusPoint + dhMinusPoint);
			
			tbMembershipCard.setGiveMoney(gsGiveMoney + pjGiveMoney);
			
			tbMembershipCard.setGivePoint(gsGivePoint + pjGivePoint - yhMinusPoint - dhMinusPoint);
			
			tbMembershipCard.setJexf(Double.valueOf(ZJE));
			
			tbMembershipCard.setGsJexf(gsMoney);
			
			tbMembershipCard.setPjJexf(pjMoney);
			
			//tbMembershipCard.setTbCustomer(tbFixEntrust.getTbCarInfo().getTbCustomer());
			tbMembershipCard.setPayTbCustomer(tbFixEntrust.getTbCarInfo().getTbCustomer());
			
			tbBusinessBalance.setTbMembershipCard(tbMembershipCard);
			
//			if(!tbBusinessBalance.getDhMoney().equals(0)) {
//				tbMembershipCard.setCardPoint(0L);
//				
//			}
			tbMembershipCard.setDhMoney(Long.valueOf(tbBusinessBalance.getDhMoney()));
		}
		
		
		
		tbBusinessBalanceService.insertAll(tbBusinessBalance);

		ActionContext.getContext().put("msg", "生成结算单号:" + tbBusinessBalance.getBalanceCode());

		return Constants.SUCCESS;
	}

	public String insertTbBusinessBalanceXsd() throws Exception {

		if(null==tbBusinessBalance.getTmUser()||null==tbBusinessBalance.getTmUser().getId()){
			
			ActionContext.getContext().put("msg","登陆超时,请重新登陆");
			
			return Constants.FAILURE;
		}
		
		Set tbBusinessBalanceItems = new HashSet();

		// 总金额
		String ZJE = request.getParameter("ZJE");
		
		if(ZJE==null||"".equals(ZJE)){
			ZJE = "0.00";
		}
		//修理工时费
		String XLGSF = request.getParameter("XLGSF");
		
		if(XLGSF==null||"".equals(XLGSF)){
			XLGSF = "0.00";
		}
		//修理材料费
		String XLCLF = request.getParameter("XLCLF");
		
		if(XLCLF==null||"".equals(XLCLF)){
			XLCLF = "0.00";
		}
		//销售金额
		String XSJE = request.getParameter("XSJE");
		
		if(XSJE==null||"".equals(XSJE)){
			XSJE = "0.00";
		}

		String[] params = request.getParameterValues("params");
		
		Double yhMoney = 0d;

		if (null != params && params.length > 0) {

			for (int i = 0; i < params.length; i++) {

				String balanceItemCode = params[i];

				Double balanceItemTotal = Double.valueOf(request
						.getParameter(params[i]));
				
				if("QL".equals(balanceItemCode)) {
					yhMoney = balanceItemTotal;
				}

				TbBusinessBalanceItem tbBusinessBalanceItem = new TbBusinessBalanceItem();

				tbBusinessBalanceItem.setBalanceItemCode(balanceItemCode);

				tbBusinessBalanceItem.setBalanceItemTotal(balanceItemTotal);

				tbBusinessBalanceItems.add(tbBusinessBalanceItem);
			}
		}

		tbBusinessBalance.setTbBusinessBalanceItems(tbBusinessBalanceItems);
		
		String balanceCode = "";
		
		TbBusinessBalance historyBalance = tbBusinessBalanceService.findByStockOutId(tbBusinessBalance.getTmStockOut().getId());
		
		tbBusinessBalance.setBalanceCode(balanceCode);

		
		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessBalance.setBalanceTotalAll(Double.valueOf(ZJE));
		//设置修理工时费
		tbBusinessBalance.setWorkingHourTotalAll(Double.valueOf(XLGSF));
		//设置修理材料费
		tbBusinessBalance.setFixPartTotalAll(Double.valueOf(XLCLF));
		//设置销售金额
		tbBusinessBalance.setSolePartTotalAll(Double.valueOf(XSJE));

		// 设置结算单状态----已结算
		tbBusinessBalance.setBalanceStatus(Constants.JSDHASBALANCE);
		
		//设置已付款金额
		tbBusinessBalance.setPayedAmount(new BigDecimal(String.valueOf(tbBusinessBalance.getPayedAmount())).add(new BigDecimal(tbBusinessBalance.getShouldPayAmount())).doubleValue());
		
		TmStockOut tmStockOut = tmStockOutService.findById(tbBusinessBalance
				.getTmStockOut().getId());

		if (Constants.FINSH_BALANCE.equals(tmStockOut.getIsConfirm())) {

			ActionContext.getContext().put("msg", "该销售单已被结算!");

			return Constants.FAILURE;
		}
		
		if(null==historyBalance){
			balanceCode = tmDictionaryService
				.GenerateCode(StockTypeElements.TBBUSINESSBALANCE
						.getElementString());
			tbBusinessBalance.setSnNo(1);
		}
		else{
			
			balanceCode = historyBalance.getBalanceCode();
			
			tbBusinessBalance.setSnNo(historyBalance.getSnNo()+1);
		}
		
		String cardNo = request.getParameter("tbMembershipCard.cardNo");
		
		TbMembershipCard tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
		
		if(null != tbMembershipCard){
			
			/**
			 * 卡内支付金额
			 */
			Double cardZFJE = new BigDecimal(tbBusinessBalance.getCardZFJE() == null ? 0.00d : tbBusinessBalance.getCardZFJE()).doubleValue();
			/**
			 * 工时金额
			 */
			Double gsMoney = Double.valueOf(XLGSF);
			/**
			 * 配件金额
			 */
			Double pjMoney =  new BigDecimal(Double.valueOf(XLCLF)).add(new BigDecimal(Double.valueOf(XSJE))).doubleValue();
			/**
			 * 工时返回金额
			 */
			Integer gsGiveMoney = tmCardTypeService.calcGsGiveMoney(gsMoney, tbMembershipCard.getTmCardType().getId(),"");
		
			/**
			 * 配件返回金额
			 */
			Integer pjGiveMoney = tmCardTypeService.calcPjGiveMoney(pjMoney, tbMembershipCard.getTmCardType().getId(),"");
		
			/**
			 * 工时返回积分
			 */
			Integer gsGivePoint = tmCardTypeService.calcGsGivePoint(gsMoney, tbMembershipCard.getTmCardType().getId(),"");
			
			/**
			 * 配件返回积分
			 */
			Integer pjGivePoint = tmCardTypeService.calcPjGivePoint(pjMoney, tbMembershipCard.getTmCardType().getId(),"");
		
			/**
			 * 优惠扣除积分
			 */
			Integer yhMinusPoint = tmCardTypeService.calcYhMinusPoint(yhMoney, tbMembershipCard.getTmCardType().getId());
			
			/**
			 * 积分扣除积分
			 */
			Integer dhMinusPoint = tmCardTypeService.calcYhMinusPoint(Double.valueOf(tbBusinessBalance.getDhMoney()), tbMembershipCard.getTmCardType().getId());
			
			
			/**
			 * 消费后金额
			 */
			Double cardSaving = new BigDecimal(tbMembershipCard.getCardSaving()).add(new BigDecimal(gsGiveMoney)).add(new BigDecimal(pjGiveMoney)).subtract(new BigDecimal(cardZFJE)).doubleValue();
		
			/**
			 * 消费后积分
			 */
			/**
			 * 消费后积分
			 */
			Long cardPoint = 0L;
			
			if(!tbBusinessBalance.getDhMoney().equals(0)) {
				
				Integer calcDHPint = tbMembershipCardService.calcDhJF(cardNo, tbBusinessBalance.getDhMoney());
				
				cardPoint = new BigDecimal(tbMembershipCard.getCardPoint()).subtract(new BigDecimal(calcDHPint)).add(new BigDecimal(gsGivePoint)).add(new BigDecimal(pjGivePoint)).subtract(new BigDecimal(yhMinusPoint)).subtract(new BigDecimal(dhMinusPoint)).longValue();
				
			}
			
			else {
				
				cardPoint = new BigDecimal(tbMembershipCard.getCardPoint()).add(new BigDecimal(gsGivePoint)).add(new BigDecimal(pjGivePoint)).subtract(new BigDecimal(yhMinusPoint)).longValue();
				
			}
			
			tbMembershipCard.setCardZFJE(cardZFJE);
			
			tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
			
			tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
			
			tbMembershipCard.setCardSaving(cardSaving);
			
			tbMembershipCard.setCardPoint(cardPoint);
			
			tbMembershipCard.setGsGiveMoney(gsGiveMoney);
			
			tbMembershipCard.setPjGiveMoney(pjGiveMoney);
			
			tbMembershipCard.setGsGivePoint(gsGivePoint);
			
			tbMembershipCard.setPjGivePoint(pjGivePoint);
			
			tbMembershipCard.setYhMoney(yhMoney + tbBusinessBalance.getDhMoney());
			
			tbMembershipCard.setYhMinusPoint(yhMinusPoint + dhMinusPoint);
			
			tbMembershipCard.setGiveMoney(gsGiveMoney + pjGiveMoney);
			
			tbMembershipCard.setGivePoint(gsGivePoint + pjGivePoint - yhMinusPoint- dhMinusPoint);
			
			tbMembershipCard.setJexf(Double.valueOf(ZJE));
			
			tbMembershipCard.setGsJexf(gsMoney);
			
			tbMembershipCard.setPjJexf(pjMoney);
			
//			if(!tbBusinessBalance.getDhMoney().equals(0)) {
//				tbMembershipCard.setCardPoint(0L);
//				
//			}
			tbMembershipCard.setDhMoney(Long.valueOf(tbBusinessBalance.getDhMoney()));
			
			tbMembershipCard.setPayTbCustomer(tbCustomerService.findById(tmStockOut.getCustomerBill()));
			
			tbBusinessBalance.setTbMembershipCard(tbMembershipCard);
		}

		tbBusinessBalance.setBalanceCode(balanceCode);
		
		tbBusinessBalanceService.insertAll(tbBusinessBalance);

		ActionContext.getContext().put("msg", "生成结算单号:" + balanceCode);

		return Constants.SUCCESS;
	}

	public String updateTbBusinessBalance() throws Exception {
		tbBusinessBalanceService.update(tbBusinessBalance);

		return Constants.SUCCESS;

	}

	public String deleteTbBusinessBalance() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbBusinessBalanceService
					.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(
						"tbBusinessBalanceTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print(
						"tbBusinessBalanceTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print(
					"tbBusinessBalanceTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tbBusinessBalance = tbBusinessBalanceService.findById(Long
					.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}

	public String chooseTbBusinessBalance() throws Exception {

		// 清除SESSION中明细金额
		ActionContext.getContext().getSession().remove("calcSecondMapName");

		String flag = request.getParameter("flag");

		String objectId = request.getParameter("objectId");
		
		String cardNo = request.getParameter("cardNo");
		
		String cardPassword = request.getParameter("pass");
		
		tbBusinessBalance = new TbBusinessBalance();

		if(null != cardNo && !"".equals(cardNo)){
			
			tbBusinessBalance.setPayPattern(Constants.PAYMEMBERCARD);
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
			
			
			if(null != tbMembershipCard){
				
				//Long dhMoney = tbMembershipCardService.calcDhMoney(cardNo);
				
				//tbMembershipCard.setDhMoney(dhMoney);
				
				if(!cardPassword.equals(tbMembershipCard.getCardPassword().trim())){
					
					tbMembershipCard = null;
					
					request.setAttribute("pass", "false");
				}
				
				else{
					
					if(!Constants.CARD_VALID_STATUS.equals(tbMembershipCard.getCardStatus())){
						
						tbMembershipCard = null;
						
						request.setAttribute("pass", "notValid");
						
					}
					
					else{
						
						request.setAttribute("pass", "");
					}
					
					
				}
				
			}
			
			else{
				
				request.setAttribute("pass", "");
			}
			
		}
		
		// 旧件处理
		ActionContext.getContext()
				.put("oldPartDealMap", Constants.getDealMap());
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		// 结算项目类型
		ActionContext.getContext().put("tmBalanceMap",
				tmBalanceService.findAllTmBalanceMap());

		if ("entrust".equals(flag)) {
			
			TbBusinessBalance historyBalance = tbBusinessBalanceService.findByEntrustId(Long
					.valueOf(objectId));
			
			if(null!=historyBalance){
				tbBusinessBalance.setBalanceCode(historyBalance.getBalanceCode());
				
			}
			
			tbBusinessBalance.setPayedAmount(tbBusinessBalanceService.findEntrustHasPayedAmount(Long.valueOf(objectId)));

			TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(Long
					.valueOf(objectId));

			TbCarInfo tbCarInfo = tbCarInfoService
					.findTbCarInfoById(tbFixEntrust.getTbCarInfo().getId());

			//tbMembershipCard = tbMembershipCardService.findByCarInfo(tbCarInfo); 
			
			TmUser tmUser_wts = tmUserService.findById(tbFixEntrust.getTmUser()
					.getId());

			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
					.findTmBalanceItemByTmBalanceId(tbFixEntrust.getTmFixType()
							.getTmBalance().getId());

			tbFixEntrust.setTbCarInfo(tbCarInfo);

			tbFixEntrust.setTmUser(tmUser_wts);

			tbBusinessBalance.setTbFixEntrust(tbFixEntrust);

			// 工时总价
			Double workingHourTotalAll = tbFixEntrustContentService
					.countTbFixEnTrustContentByTbFixEntrustIdAndBalanceIdIsNull(tbFixEntrust
							.getId());

			Double gsYhl = 0.00d;
			
			if(null != tbMembershipCard){
				
				if(tbFixEntrust.getTmFixType().getFixType().indexOf("保险") == -1) {
					
					gsYhl = tbMembershipCard.getTmCardType().getGsYhl();
				}
				
				else {
					
					gsYhl = tbMembershipCard.getTmCardType().getGsBxYhl();
				}
				
				
				
				workingHourTotalAll = new BigDecimal(workingHourTotalAll).multiply(new BigDecimal(1 - gsYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
			}
			
			Double pjYhl = 0.00d;
			
			// 修理材料总价
			Double fixPartTotalAll = tbMaintainPartContentService
					.getTotalPriceByBalanceId(tbFixEntrust.getId(), null);

			// 销售材料总价
			Double solePartTotalAll = tmStockOutService
					.getSellTotalPriceByBalance(tbFixEntrust.getEntrustCode(),
							null);
			if(null != tbMembershipCard){
				
				if(tbFixEntrust.getTmFixType().getFixType().indexOf("保险") == -1) {
					
					pjYhl = tbMembershipCard.getTmCardType().getPjYhl();
				}	
				else {
					
					pjYhl = tbMembershipCard.getTmCardType().getPjBxYhl();
				}
				
				
				fixPartTotalAll = new BigDecimal(fixPartTotalAll).multiply(new BigDecimal(1 - pjYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				solePartTotalAll = new BigDecimal(solePartTotalAll).multiply(new BigDecimal(1 - pjYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			
			}
			
			/*ActionContext.getContext().put("workingHourFavourRate", new BigDecimal(gsYhl).setScale(2,BigDecimal.ROUND_HALF_UP));
			
			request.setAttribute("fixPartFavourRate", new BigDecimal(pjYhl).setScale(2,BigDecimal.ROUND_HALF_UP));
			
			request.setAttribute("solePartFavourRate", new BigDecimal(pjYhl).setScale(2,BigDecimal.ROUND_HALF_UP));
			*/
			
			
			tbBusinessBalance.setWorkingHourFavourRate(gsYhl);
			
			tbBusinessBalance.setFixPartFavourRate(pjYhl);
			
			tbBusinessBalance.setSolePartFavourRate(pjYhl);
			
			Map<String, BigDecimal> initMap = new LinkedHashMap<String, BigDecimal>();

			Map<String, BigDecimal> calcMap = new LinkedHashMap<String, BigDecimal>();

			Map<String, BigDecimal> calcSecondMap = new LinkedHashMap<String, BigDecimal>();

			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

			if (null != tmBalanceItemList && tmBalanceItemList.size() > 0) {

				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					if (Constants.XLGSF.equals(tmBalanceItem.getItemName()
							.trim())) {

						initMap.put(Constants.XLGSF, new BigDecimal(String
								.valueOf(workingHourTotalAll)));

					} else if (Constants.XLCLF.equals(tmBalanceItem
							.getItemName().trim())) {

						initMap.put(Constants.XLCLF, new BigDecimal(String
								.valueOf(fixPartTotalAll)));

					} else if (Constants.XSJE.equals(tmBalanceItem
							.getItemName().trim())) {

						initMap.put(Constants.XSJE, new BigDecimal(String
								.valueOf(solePartTotalAll)));

					} else {

						initMap.put(tmBalanceItem.getItemName().trim(),
								new BigDecimal("0.00"));

					}
				}

				// 计算各项明细金额
				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					BigDecimal itemValue = new BigDecimal(
							String
									.valueOf(tmBalanceItemService
											.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
													Long.valueOf(tbFixEntrust
															.getTmFixType()
															.getTmBalance()
															.getId()),
													tmBalanceItem.getItemName()
															.trim(), initMap)
											.get(0)));

					calcMap.put(tmBalanceItem.getItemName(), itemValue
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					BigDecimal itemValue = new BigDecimal(
							String
									.valueOf(tmBalanceItemService
											.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
													Long.valueOf(tbFixEntrust
															.getTmFixType()
															.getTmBalance()
															.getId()),
													tmBalanceItem.getItemName()
															.trim(), calcMap)
											.get(0)));

					calcSecondMap.put(tmBalanceItem.getItemName(), itemValue
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					BigDecimal itemValue = new BigDecimal(
							String
									.valueOf(tmBalanceItemService
											.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
													Long.valueOf(tbFixEntrust
															.getTmFixType()
															.getTmBalance()
															.getId()),
													tmBalanceItem.getItemName()
															.trim(),
													calcSecondMap).get(0)));

					calcMapReturn.put(tmBalanceItem.getItemCode(), itemValue
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

			}

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);

			//request.setAttribute("tbMembershipCard", tbMembershipCard);
			
			return this.WTSPAGE;
		} else if ("stockOut".equals(flag)) {
			
			TmStockOut tmStockOut = tmStockOutService.findById(new Long(
					objectId));

			TbBusinessBalance historyBalance = tbBusinessBalanceService.findByStockOutId(Long
					.valueOf(objectId));
			
			if(null!=historyBalance){
				tbBusinessBalance.setBalanceCode(historyBalance.getBalanceCode());
			}
			
			tbBusinessBalance.setPayedAmount(tbBusinessBalanceService.findStockOutHasPayedAmout(Long.valueOf(objectId)));
			
			TbCustomer customer = tbCustomerService.findById(tmStockOut
					.getCustomerBill());

			/*List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(
					Constants.CONFIRM, tmStockOut.getId(),
					StockTypeElements.SELLSTOCKOUT.getElementValue());
*/
			List<TmStockOutDetVo> detVos = tmStockOutService.getCustomerSellDetailByTmStockOutId(Long.valueOf(objectId),Constants.BALANCE_ALL);
			
			List<TmBalance> tmBalanceList = tmBalanceService.findAll();

			Long tmBalanceId = tmBalanceList.get(0).getId();

			ActionContext.getContext().put("tmBalanceId", tmBalanceId);

			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
					.findTmBalanceItemByTmBalanceId(tmBalanceId);

			request.setAttribute("detVos", detVos);

			request.setAttribute("customer", customer);
			// 默认销售类型
			TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();

			List<TmSoleType> tsyList = new ArrayList<TmSoleType>();

			tsyList.add(tsy);

			request.setAttribute("tsyList", tsyList);

			tbBusinessBalance.setTmStockOut(tmStockOut);

			// 工时总价
			Double workingHourTotalAll = 0D;

			// 修理材料总价
			Double fixPartTotalAll = 0D;

			// 销售材料总价
			Double solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(Long.valueOf(objectId), null);

			Double pjYhl = 0.00D;
			
			if(null != tbMembershipCard){
				
				pjYhl = tbMembershipCard.getTmCardType().getPjYhl();
				
				solePartTotalAll = new BigDecimal(solePartTotalAll).multiply(new BigDecimal(1 - pjYhl)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
			}
			
			tbBusinessBalance.setSolePartFavourRate(pjYhl);
			
			Map<String, BigDecimal> initMap = new LinkedHashMap<String, BigDecimal>();

			Map<String, BigDecimal> calcMap = new LinkedHashMap<String, BigDecimal>();

			Map<String, BigDecimal> calcSecondMap = new LinkedHashMap<String, BigDecimal>();

			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

			if (null != tmBalanceItemList && tmBalanceItemList.size() > 0) {

				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					if (Constants.XLGSF.equals(tmBalanceItem.getItemName()
							.trim())) {

						initMap.put(Constants.XLGSF, new BigDecimal(String
								.valueOf(workingHourTotalAll)));

					} else if (Constants.XLCLF.equals(tmBalanceItem
							.getItemName().trim())) {

						initMap.put(Constants.XLCLF, new BigDecimal(String
								.valueOf(fixPartTotalAll)));

					} else if (Constants.XSJE.equals(tmBalanceItem
							.getItemName().trim())) {

						initMap.put(Constants.XSJE, new BigDecimal(String
								.valueOf(solePartTotalAll)));

					} else {

						initMap.put(tmBalanceItem.getItemName().trim(),
								new BigDecimal("0.00"));

					}
				}

				// 计算各项明细金额
				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					BigDecimal itemValue = new BigDecimal(
							String
									.valueOf(tmBalanceItemService
											.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
													tmBalanceId,
													tmBalanceItem.getItemName()
															.trim(), initMap)
											.get(0)));

					calcMap.put(tmBalanceItem.getItemName(), itemValue
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					BigDecimal itemValue = new BigDecimal(
							String
									.valueOf(tmBalanceItemService
											.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
													tmBalanceId,
													tmBalanceItem.getItemName()
															.trim(), calcMap)
											.get(0)));

					calcSecondMap.put(tmBalanceItem.getItemName(), itemValue
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

				for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

					BigDecimal itemValue = new BigDecimal(
							String
									.valueOf(tmBalanceItemService
											.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
													tmBalanceId,
													tmBalanceItem.getItemName()
															.trim(),
													calcSecondMap).get(0)));

					calcMapReturn.put(tmBalanceItem.getItemCode(), itemValue
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

			}

			// tbBusinessBalance.setWorkingHourTotalAll(workingHourTotalAll);

			// ActionContext.getContext().put("tmBalanceItemList",
			// tmBalanceItemList);

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);
			
			request.setAttribute("tbMembershipCard", tbMembershipCard);

			return this.XSDPAGE;
		}

		return null;
	}

	// Ajax结算各个项目明细
	public String calcTbBusinessBalance() throws Exception {

		String tmBalanceId = request.getParameter("tmBalanceId");

		String tbFixEntrustId = request.getParameter("tbFixEntrustId");

		String itemCode = request.getParameter("itemCode");

		String itemVal = request.getParameter("itemVal");

		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
				.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));

		Map<String, BigDecimal> calcMap = (LinkedHashMap<String, BigDecimal>) request
				.getSession().getAttribute("calcMapReturn");

		Map<String, BigDecimal> calcMapName = new LinkedHashMap<String, BigDecimal>();

		Map<String, BigDecimal> calcSecondMapName = new LinkedHashMap<String, BigDecimal>();

		if (null != tmBalanceItemList && tmBalanceItemList.size() > 0) {

			// 初始化金额
			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				if (itemCode.equals(tmBalanceItem.getItemCode().trim())) {

					calcMap.put(itemCode, new BigDecimal(itemVal));

				}

				calcMapName.put(tmBalanceItem.getItemName().trim(), calcMap
						.get(tmBalanceItem.getItemCode()));

			}

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String
								.valueOf(tmBalanceItemService
										.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
												Long.valueOf(tmBalanceId),
												tmBalanceItem.getItemName()
														.trim(), calcMapName)
										.get(0)));

				calcSecondMapName.put(tmBalanceItem.getItemName(), itemValue
						.setScale(2, BigDecimal.ROUND_HALF_UP));
			}

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String
								.valueOf(tmBalanceItemService
										.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
												Long.valueOf(tmBalanceId),
												tmBalanceItem.getItemName()
														.trim(),
												calcSecondMapName).get(0)));

				calcMap.put(tmBalanceItem.getItemCode(), itemValue.setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
		}

		String str = "";

		for (Object object : calcMap.keySet()) {

			String itemCodeReturn = (String) object;

			String itemValueReturn = new BigDecimal(String.valueOf(calcMap
					.get(itemCodeReturn))).toString();

			str = str + itemCodeReturn + ":" + itemValueReturn + ",";

		}

		response.getWriter().print(str);

		return null;
	}

	// Ajax结算各个项目明细
	public String calcXsdTbBusinessBalance() throws Exception {

		String tmBalanceId = request.getParameter("tmBalanceId");

		String tmStockOutId = request.getParameter("tmStockOutId");

		String itemCode = request.getParameter("itemCode");

		String itemVal = request.getParameter("itemVal");

		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
				.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));

		Map<String, BigDecimal> calcMap = (LinkedHashMap<String, BigDecimal>) request
				.getSession().getAttribute("calcMapReturn");

		Map<String, BigDecimal> calcMapName = new LinkedHashMap<String, BigDecimal>();

		Map<String, BigDecimal> calcSecondMapName = new LinkedHashMap<String, BigDecimal>();

		if (null != tmBalanceItemList && tmBalanceItemList.size() > 0) {

			// 初始化金额
			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				if (itemCode.equals(tmBalanceItem.getItemCode().trim())) {

					calcMap.put(itemCode, new BigDecimal(itemVal));

				}

				calcMapName.put(tmBalanceItem.getItemName().trim(), calcMap
						.get(tmBalanceItem.getItemCode()));

			}

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String
								.valueOf(tmBalanceItemService
										.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
												Long.valueOf(tmBalanceId),
												tmBalanceItem.getItemName()
														.trim(), calcMapName)
										.get(0)));

				calcSecondMapName.put(tmBalanceItem.getItemName(), itemValue
						.setScale(2, BigDecimal.ROUND_HALF_UP));
			}

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String
								.valueOf(tmBalanceItemService
										.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
												Long.valueOf(tmBalanceId),
												tmBalanceItem.getItemName()
														.trim(),
												calcSecondMapName).get(0)));

				calcMap.put(tmBalanceItem.getItemCode(), itemValue.setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
		}

		String str = "";

		for (Object object : calcMap.keySet()) {

			String itemCodeReturn = (String) object;

			String itemValueReturn = new BigDecimal(String.valueOf(calcMap
					.get(itemCodeReturn))).toString();

			str = str + itemCodeReturn + ":" + itemValueReturn + ",";

		}

		response.getWriter().print(str);

		return null;
	}

	// Ajax结算各个项目明细(财务录入)
	public String calcTbBusinessBalanceFinance() throws Exception {

		//获取唯一的结算ID
		String tmBalanceId = String.valueOf(tmBalanceService.findAll().get(0).getId()); 
		
		String itemCode = request.getParameter("itemCode");

		String itemVal = request.getParameter("itemVal");

		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
				.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));

		Map<String, BigDecimal> calcMap = (LinkedHashMap<String, BigDecimal>) request
				.getSession().getAttribute("calcMapReturn");

		Map<String, BigDecimal> calcMapName = new LinkedHashMap<String, BigDecimal>();

		Map<String, BigDecimal> calcSecondMapName = new LinkedHashMap<String, BigDecimal>();

		if (null != tmBalanceItemList && tmBalanceItemList.size() > 0) {

			// 初始化金额
			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				if (itemCode.equals(tmBalanceItem.getItemCode().trim())) {

					calcMap.put(itemCode, new BigDecimal(itemVal));

				}

				calcMapName.put(tmBalanceItem.getItemName().trim(), calcMap
						.get(tmBalanceItem.getItemCode()));

			}

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String
								.valueOf(tmBalanceItemService
										.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
												Long.valueOf(tmBalanceId),
												tmBalanceItem.getItemName()
														.trim(), calcMapName)
										.get(0)));

				calcSecondMapName.put(tmBalanceItem.getItemName(), itemValue
						.setScale(2, BigDecimal.ROUND_HALF_UP));
			}

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String
								.valueOf(tmBalanceItemService
										.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
												Long.valueOf(tmBalanceId),
												tmBalanceItem.getItemName()
														.trim(),
												calcSecondMapName).get(0)));

				calcMap.put(tmBalanceItem.getItemCode(), itemValue.setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
		}

		String str = "";

		for (Object object : calcMap.keySet()) {

			String itemCodeReturn = (String) object;

			String itemValueReturn = new BigDecimal(String.valueOf(calcMap
					.get(itemCodeReturn))).toString();

			str = str + itemCodeReturn + ":" + itemValueReturn + ",";

		}

		response.getWriter().print(str);

		return null;
	}
	
	private TbMembershipCard tbMembershipCard;
	
	public TbMembershipCard getTbMembershipCard() {
		return tbMembershipCard;
	}

	public void setTbMembershipCard(TbMembershipCard tbMembershipCard) {
		this.tbMembershipCard = tbMembershipCard;
	}

	public String findTbBusinessBalanceTotal() throws Exception {

		String carInfoId = request.getParameter("carInfoId");

		String cardNo = request.getParameter("cardNo");

		String passFlag = request.getParameter("passFlag");
		
		if(null != passFlag){
			
			request.setAttribute("pass", passFlag);
			
		}
		
		if(null != cardNo && !"".equals(cardNo)){
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
			tbMembershipCard.setDhMoney(tbMembershipCardService.calcDhMoney(cardNo));
			
		}
		
		//tbMembershipCard = tbMembershipCardService.findByCarInfo(tbCarInfoService.findById(Long.valueOf(carInfoId)));
		
		if(null != tbMembershipCard){
			
			tbMembershipCard.setAftCardSaving(tbMembershipCard.getCardSaving());
			
		}
		// Double saveAmount =
		// tbAnvancePayService.acquireCustomerTotalAnvancePay(Long.valueOf(carInfoId));

		tbBusinessBalance = new TbBusinessBalance();

		// tbBusinessBalance.setSaveAmount(saveAmount);

		ActionContext.getContext().put("payMap", Constants.getPayMap());

		return Constants.SUCCESS;
	}

	public String viewTbBusinessBalanceTotal() throws Exception {

		String tbBusinessBalanceId = request
				.getParameter("tbBusinessBalanceId");

		// Double saveAmount =
		// tbAnvancePayService.acquireCustomerTotalAnvancePay(Long.valueOf(carInfoId));
		if(null != tbBusinessBalanceId && !"".equals(tbBusinessBalanceId))
			tbBusinessBalance = tbBusinessBalanceService.findById(Long
				.valueOf(tbBusinessBalanceId));

		// tbBusinessBalance.setSaveAmount(saveAmount);

		ActionContext.getContext().put("payMap", Constants.getPayMap());

		return Constants.SUCCESS;
	}
	
	public String viewTbBusinessBalanceTotalGroup() throws Exception {

		String tbBusinessBalanceId = request
				.getParameter("tbBusinessBalanceId");

		// Double saveAmount =
		// tbAnvancePayService.acquireCustomerTotalAnvancePay(Long.valueOf(carInfoId));

		TbBusinessBalance tbBusinessBalanceTemp = new TbBusinessBalance();

		tbBusinessBalanceTemp.setId(Long.valueOf(tbBusinessBalanceId));
		
		List<TbBusinessBalance> list = tbBusinessBalanceService.findTbBusinessBalanceToGroup(tbBusinessBalanceTemp);
		
		if(null!=list&&list.size()>0){
			
			tbBusinessBalance = list.get(0);
			
		}
		/*tbBusinessBalance = tbBusinessBalanceService.findById(Long
				.valueOf(tbBusinessBalanceId));
		 */
		// tbBusinessBalance.setSaveAmount(saveAmount);

		ActionContext.getContext().put("payMap", Constants.getPayMap());

		return Constants.SUCCESS;
	}

	public String findTbBusinessBalanceTotalXsd() throws Exception {

		String carInfoId = request.getParameter("carInfoId");

		String cardNo = request.getParameter("cardNo");
		
		String passFlag = request.getParameter("passFlag");
		
		if(null != passFlag){
			
			request.setAttribute("pass", passFlag);
			
		}
		
		if(null != cardNo && !"".equals(cardNo)){
			
			tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
			
			tbMembershipCard.setDhMoney(tbMembershipCardService.calcDhMoney(cardNo));
		}
		
		if(null != tbMembershipCard){
			
			tbMembershipCard.setAftCardSaving(tbMembershipCard.getCardSaving());
			
		}
		// Double saveAmount =
		// tbAnvancePayService.acquireCustomerTotalAnvancePay(Long.valueOf(carInfoId));

		tbBusinessBalance = new TbBusinessBalance();

		// tbBusinessBalance.setSaveAmount(saveAmount);

		ActionContext.getContext().put("payMap", Constants.getPayMap());

		return Constants.SUCCESS;
	}

	public String viewTbBusinessBalanceTotalXsd() throws Exception {

		String tbBusinessBalanceId = request
				.getParameter("tbBusinessBalanceId");

		// Double saveAmount =
		// tbAnvancePayService.acquireCustomerTotalAnvancePay(Long.valueOf(carInfoId));

		tbBusinessBalance = tbBusinessBalanceService.findById(Long
				.valueOf(tbBusinessBalanceId));

		// tbBusinessBalance.setSaveAmount(saveAmount);

		ActionContext.getContext().put("payMap", Constants.getPayMap());

		return Constants.SUCCESS;
	}

	public String viewTbBusinessBalanceTotalXsdGroup() throws Exception{
		
		String tbBusinessBalanceId = request
		.getParameter("tbBusinessBalanceId");

		// Double saveAmount =
		// tbAnvancePayService.acquireCustomerTotalAnvancePay(Long.valueOf(carInfoId));

		TbBusinessBalance tbBusinessBalanceTemp = new TbBusinessBalance();

		tbBusinessBalanceTemp.setId(Long.valueOf(tbBusinessBalanceId));
		
		List<TbBusinessBalance> list = tbBusinessBalanceService.findTbBusinessBalanceToGroup(tbBusinessBalanceTemp);
		
		if(null!=list&&list.size()>0){
			
			tbBusinessBalance = list.get(0);
			
		}
		// tbBusinessBalance.setSaveAmount(saveAmount);

		ActionContext.getContext().put("payMap", Constants.getPayMap());
		
		return Constants.SUCCESS;
	}
	public String viewTbBusinessBalance() throws Exception {

		String id = request.getParameter("id");

		// 旧件处理
		ActionContext.getContext()
				.put("oldPartDealMap", Constants.getDealMap());
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		// 结算项目类型
		ActionContext.getContext().put("tmBalanceMap",
				tmBalanceService.findAllTmBalanceMap());

		tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(id));

		if (null != tbBusinessBalance.getTbFixEntrust()) {
			TbFixEntrust tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessBalance.getTbFixEntrust().getId());

			TbCarInfo tbCarInfo = tbCarInfoService
					.findTbCarInfoById(tbFixEntrust.getTbCarInfo().getId());

			TmUser tmUser_wts = tmUserService.findById(tbFixEntrust.getTmUser()
					.getId());

			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
					.findTmBalanceItemByTmBalanceId(Long.valueOf(tbFixEntrust
							.getTmFixType().getTmBalance().getId()));

			tbFixEntrust.setTbCarInfo(tbCarInfo);

			tbFixEntrust.setTmUser(tmUser_wts);

			tbBusinessBalance.setTbFixEntrust(tbFixEntrust);

			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
					.findTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance
							.getId());

			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

			if (null != tbBusinessBalanceItemList
					&& tbBusinessBalanceItemList.size() > 0) {

				for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

					calcMapReturn.put(tbBusinessBalanceItem
							.getBalanceItemCode(), new BigDecimal(String
							.valueOf(tbBusinessBalanceItem
									.getBalanceItemTotal())));

				}

			}

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);

			
			return Constants.VIEWPAGE;
		} else {

			TmStockOut tmStockOut = tmStockOutService.findById(tbBusinessBalance.getTmStockOut().getId());
			
			TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
			
			request.setAttribute("customer", customer);
			//默认销售类型
			TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
			
			List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
			
			tsyList.add(tsy);
			
			request.setAttribute("tsyList", tsyList);
			
			List<TmStockOutDetVo> detVos = tmStockOutService.getCustomerSellDetailByBalanceId(tbBusinessBalance.getTmStockOut().getId(),tbBusinessBalance.getId());
			
			Double solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(tbBusinessBalance.getTmStockOut().getId(),tbBusinessBalance.getId());
			
			request.setAttribute("solePartTotalAll",solePartTotalAll);
				
			request.setAttribute("detVos", detVos);
			
			List<TmBalance> tmBalanceList = tmBalanceService.findAll();
			
			Long tmBalanceId = tmBalanceList.get(0).getId();
			
			ActionContext.getContext().put("tmBalanceId", tmBalanceId);
			
			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance.getId());
			
			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
			
			if (null != tbBusinessBalanceItemList
					&& tbBusinessBalanceItemList.size() > 0) {

				for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

					calcMapReturn.put(tbBusinessBalanceItem
							.getBalanceItemCode(), new BigDecimal(String
							.valueOf(tbBusinessBalanceItem
									.getBalanceItemTotal())));

				}

			}

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);

			return Constants.OTHERPAGE;
		}

	}
	
	public String viewGroupTbBusinessBalance() throws Exception{
		
		String id = request.getParameter("id");

		// 旧件处理
		ActionContext.getContext()
				.put("oldPartDealMap", Constants.getDealMap());
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		// 结算项目类型
		ActionContext.getContext().put("tmBalanceMap",
				tmBalanceService.findAllTmBalanceMap());

		tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(id));

		if (null != tbBusinessBalance.getTbFixEntrust()) {
			TbFixEntrust tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessBalance.getTbFixEntrust().getId());

			TbCarInfo tbCarInfo = tbCarInfoService
					.findTbCarInfoById(tbFixEntrust.getTbCarInfo().getId());

			TmUser tmUser_wts = tmUserService.findById(tbFixEntrust.getTmUser()
					.getId());

			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
					.findTmBalanceItemByTmBalanceId(Long.valueOf(tbFixEntrust
							.getTmFixType().getTmBalance().getId()));

			tbFixEntrust.setTbCarInfo(tbCarInfo);

			tbFixEntrust.setTmUser(tmUser_wts);

			tbBusinessBalance.setTbFixEntrust(tbFixEntrust);

			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
					.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance
							.getId());

			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

			if (null != tbBusinessBalanceItemList
					&& tbBusinessBalanceItemList.size() > 0) {

				for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

					calcMapReturn.put(tbBusinessBalanceItem
							.getBalanceItemCode(), new BigDecimal(String
							.valueOf(tbBusinessBalanceItem
									.getBalanceItemTotal())));

				}

			}

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);

			
			return Constants.VIEWPAGE;
		} else {

			TmStockOut tmStockOut = tmStockOutService.findById(tbBusinessBalance.getTmStockOut().getId());
			
			TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
			
			request.setAttribute("customer", customer);
			//默认销售类型
			TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
			
			List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
			
			tsyList.add(tsy);
			
			request.setAttribute("tsyList", tsyList);
			
			List<TmStockOutDetVo> detVos = tmStockOutService.getCustomerSellDetailByBalanceId(tbBusinessBalance.getTmStockOut().getId(),tbBusinessBalance.getId());
			
			Double solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(tbBusinessBalance.getTmStockOut().getId(),tbBusinessBalance.getId());
			
			request.setAttribute("solePartTotalAll",solePartTotalAll);
				
			request.setAttribute("detVos", detVos);
			
			List<TmBalance> tmBalanceList = tmBalanceService.findAll();
			
			Long tmBalanceId = tmBalanceList.get(0).getId();
			
			ActionContext.getContext().put("tmBalanceId", tmBalanceId);
			
			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance.getId());
			
			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
			
			if (null != tbBusinessBalanceItemList
					&& tbBusinessBalanceItemList.size() > 0) {

				for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

					calcMapReturn.put(tbBusinessBalanceItem
							.getBalanceItemCode(), new BigDecimal(String
							.valueOf(tbBusinessBalanceItem
									.getBalanceItemTotal())));

				}

			}

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);

			return Constants.OTHERPAGE;
		}
	}

	public String editTbBusinessBalance() throws Exception{
		
		String flag = request.getParameter("flag");
		
		String id = request.getParameter("id");
		
		String entrustCode = request.getParameter("entrustCode");
		
		//旧件处理
		ActionContext.getContext().put("oldPartDealMap",Constants.getDealMap());
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		//结算项目类型
		ActionContext.getContext().put("tmBalanceMap", tmBalanceService.findAllTmBalanceMap());
		//免费标识
		ActionContext.getContext().put("freeSymbolMap", Constants.getFreeSymbolMap());
		
		if("wts".equals(flag)){
			if(null != id)
				tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));
			
			if(null != entrustCode && !"".equals(entrustCode)){
				tbFixEntrust = tbFixEntrustService.findByEntrustCode(entrustCode);
			}
			
			tbBusinessBalance = new TbBusinessBalance();
			
			
			tbBusinessBalance.setTbFixEntrust(tbFixEntrust);
		}
		
		else{
			
			if(null != id){
				tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(id));
			}
			else {
				
				tbBusinessBalance = new TbBusinessBalance();
			}
		}
		
		
		
		if(null!=tbBusinessBalance.getTbFixEntrust()){
			
			TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(tbBusinessBalance.getTbFixEntrust().getId());
			
			TbCarInfo tbCarInfo = tbCarInfoService.findTbCarInfoById(tbFixEntrust.getTbCarInfo().getId());
			
			TmUser tmUser_wts = tmUserService.findById(tbFixEntrust.getTmUser().getId());
			
			tbFixEntrust.setTbCarInfo(tbCarInfo);
			
			tbFixEntrust.setTmUser(tmUser_wts);
			
			
			/**
			 * 结算单中工时工位
			 */
			List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrust.getId());
			
			ActionContext.getContext().put("tbFixEntrustContentList", tbFixEntrustContentList);
			
		
			/**
			 * 结算单中维修发料
			 */
			List<TbMaintianVo> maintianvos =tbMaintainPartContentService.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),Constants.BALANCE_ALL);
			
			/**
			 *结算单中维修销售 
			 */
			List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),Constants.BALANCE_ALL);
			
			/**
			 * 将配件销售汇总到维修发料中
			 */
			
			List<TbMaintianVo> partAll =  new ArrayList<TbMaintianVo>();
			
			if(null!=maintianvos&&maintianvos.size()>0){
				
				for(TbMaintianVo tbMaintianVo : maintianvos){
					
					partAll.add(tbMaintianVo);
				
				}
			}
			
			if(null!=tmStockOutDetVos&&tmStockOutDetVos.size()>0){
				
				for(TmStockOutDetVo tmStockOutDetVo : tmStockOutDetVos){
					
					TbMaintianVo tbMaintianVo = new TbMaintianVo();
					
					tbMaintianVo.setPartId(tmStockOutDetVo.getPartinfoId());
					
					tbMaintianVo.setHouseName(tmStockOutDetVo.getHouseName());
					
					tbMaintianVo.setPartCode(tmStockOutDetVo.getPartCode());
					
					tbMaintianVo.setPartName(tmStockOutDetVo.getPartName());
					
					tbMaintianVo.setUnitName(tmStockOutDetVo.getUnitName());
					
					tbMaintianVo.setPrice(tmStockOutDetVo.getPrice());
					
					tbMaintianVo.setPartQuantity(tmStockOutDetVo.getQuantity());
					
					tbMaintianVo.setTotal(tmStockOutDetVo.getTotal());
					
					tbMaintianVo.setIsFree(tmStockOutDetVo.getIsFree());
					
					partAll.add(tbMaintianVo);
 				}
			}
			
			request.setAttribute("partAll",partAll);
			
			//修理工时总价
			Double workingHourTotalAll = 0.00D;
			//修理材料总价
			Double fixPartTotalAll = 0.00D;
			//销售总价
			Double solePartTotalAll = 0.00D;
			
			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = null;
			
			if("jsd".equals(flag))
				
				tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(id));
			
			
			
			
			if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
				
				for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
					
					if("XLGSF".equals(tbBusinessBalanceItem.getBalanceItemCode())){
						workingHourTotalAll = tbBusinessBalanceItem.getBalanceItemTotal();
					}
					if("XLCLF".equals(tbBusinessBalanceItem.getBalanceItemCode())){
						fixPartTotalAll = tbBusinessBalanceItem.getBalanceItemTotal();
					}
					if("XSJE".equals(tbBusinessBalanceItem.getBalanceItemCode())){
						solePartTotalAll = tbBusinessBalanceItem.getBalanceItemTotal();
					}
				}
			}
			
			//结算项目
			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(tbFixEntrust.getTmFixType().getTmBalance().getId());
			
			ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
			
			/*******************************************************************************/
			Map<String,BigDecimal> initMap = new LinkedHashMap<String, BigDecimal>();
			
			Map<String,BigDecimal> calcMap = new LinkedHashMap<String, BigDecimal>();
			
			Map<String,BigDecimal> calcSecondMap = new LinkedHashMap<String, BigDecimal>();
			
			Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
			
			if(null!=tmBalanceItemList&&tmBalanceItemList.size()>0){
				
				//初始化金额
				
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
						
						
						if(Constants.XLGSF.equals(tmBalanceItem.getItemName().trim())){
							
							initMap.put(Constants.XLGSF, new BigDecimal(String.valueOf(workingHourTotalAll)));
						
						}
						else if(Constants.XLCLF.equals(tmBalanceItem.getItemName().trim())){
							
							initMap.put(Constants.XLCLF, new BigDecimal(String.valueOf(fixPartTotalAll)).add(new BigDecimal(String.valueOf(solePartTotalAll))));
							
						}
						else if(Constants.XSJE.equals(tmBalanceItem.getItemName().trim())){
							
							initMap.put(Constants.XSJE, new BigDecimal(String.valueOf("0.00")));
							
						}
						else{
							
							initMap.put(tmBalanceItem.getItemName().trim(), new BigDecimal("0.00"));
							
						}
					
					}
					
					//计算各项明细金额
					for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
						
						BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(tbFixEntrust.getTmFixType().getTmBalance().getId(),tmBalanceItem.getItemName().trim(),initMap).get(0))); 
					
						calcMap.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
					}
					
					
					
				}
				
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
					
					BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(tbFixEntrust.getTmFixType().getTmBalance().getId(),tmBalanceItem.getItemName().trim(),calcMap).get(0))); 
				
					calcSecondMap.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
				}
				
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
					
					BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(tbFixEntrust.getTmFixType().getTmBalance().getId(),tmBalanceItem.getItemName().trim(),calcSecondMap).get(0))); 
				
					calcMapReturn.put(tmBalanceItem.getItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
				}

			ActionContext.getContext().getSession().put("calcMapReturn", calcMapReturn);
			
			ActionContext.getContext().getSession().put("calcSecondMapName", calcSecondMap);
			/*******************************************************************************/

			return Constants.VIEWPAGE;
		}
		else{
			
			/*TmStockOut tmStockOut = tmStockOutService.findById(tbBusinessBalance.getTmStockOut().getId());
			
			TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
			
			List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, tmStockOut.getId(), StockTypeElements.SELLSTOCKOUT.getElementValue());
			
			List<TmBalance> tmBalanceList = tmBalanceService.findAll();
			
			Long tmBalanceId = tmBalanceList.get(0).getId();
			
			ActionContext.getContext().put("tmBalanceId", tmBalanceId);
			
			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(tmBalanceId);
			
			ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
			
			request.setAttribute("detVos", detVos);
			
			request.setAttribute("customer", customer);
			//默认销售类型
			TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
			
			List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
			
			tsyList.add(tsy);
			
			request.setAttribute("tsyList", tsyList);
			
			request.setAttribute("insertAction", "updateSellAction");
			
			request.setAttribute("initPageAction", "findSellAction");
			
			request.setAttribute("updateAction", "updateSellStateAction");
			
			tbBusinessBalance.setTmStockOut(tmStockOut);
			
			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance.getId());
			
			Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
			
			if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
				
				for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
					
					calcMapReturn.put(tbBusinessBalanceItem.getBalanceItemCode(), new BigDecimal(String.valueOf(tbBusinessBalanceItem.getBalanceItemTotal())));
				
				}
				
			}
			
			ActionContext.getContext().getSession().put("calcMapReturn", calcMapReturn);
			*/
			
			TmStockOut tmStockOut = null;
			
			if(null != request.getParameter("tmStockOutId")) {
				
				tmStockOut =tmStockOutService.findById(Long.valueOf(request.getParameter("tmStockOutId")));
				tbBusinessBalance.setTmStockOut(tmStockOut);
			}
			
			else {
					
				tmStockOut =tmStockOutService.findById(tbBusinessBalance.getTmStockOut().getId());
			}
			
			TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
			
			request.setAttribute("customer", customer);
			//默认销售类型
			TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
			
			List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
			
			tsyList.add(tsy);
			
			request.setAttribute("tsyList", tsyList);
			
			List<TmStockOutDetVo> detVos = null;
			
			Double solePartTotalAll = 0.0d;
			
			if(null != request.getParameter("tmStockOutId")) {
				
				detVos = tmStockOutService.getCustomerSellDetailByTmStockOutId(Long.valueOf(request.getParameter("tmStockOutId")), Constants.BALANCE_ALL);
			
				for(TmStockOutDetVo vo : detVos){
					if(vo.getIsFree().equals(Constants.FREESYMBOL_NO)){
						
						solePartTotalAll += vo.getPrice() * vo.getQuantity();
					}
				}
				
				BigDecimal   b   =   new   BigDecimal(solePartTotalAll);  
				
				solePartTotalAll   =   b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();
				
			}
			else {
					
				detVos = tmStockOutService.getCustomerSellDetailByBalanceId(tbBusinessBalance.getTmStockOut().getId(),tbBusinessBalance.getId());
			
				solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(tbBusinessBalance.getTmStockOut().getId(),tbBusinessBalance.getId());
				
			}
			
			
			
			
					
					
			request.setAttribute("solePartTotalAll",solePartTotalAll);
				
			request.setAttribute("detVos", detVos);
			
			List<TmBalance> tmBalanceList = tmBalanceService.findAll();
			
			Long tmBalanceId = tmBalanceList.get(0).getId();
			
			ActionContext.getContext().put("tmBalanceId", tmBalanceId);
			
			//结算项目
			List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(tmBalanceId);
			
			ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
			
			List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance.getId());
			
			Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
			
			if (null != tbBusinessBalanceItemList
					&& tbBusinessBalanceItemList.size() > 0) {

				for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

					calcMapReturn.put(tbBusinessBalanceItem
							.getBalanceItemCode(), new BigDecimal(String
							.valueOf(tbBusinessBalanceItem
									.getBalanceItemTotal())));

				}

			}
			else {
				
				for(TmBalanceItem  tmi : tmBalanceItemList) {
					
					calcMapReturn.put(tmi.getItemCode(), new BigDecimal(0.0d));
					
				}
				
			}

			ActionContext.getContext().getSession().put("calcMapReturn",
					calcMapReturn);

			return Constants.OTHERPAGE;
		}
	}

	public String findTbBusinessReBalance() throws Exception {

		// 只查结算状态下结算单
		if (null == tbBusinessBalance) {
			tbBusinessBalance = new TbBusinessBalance();
			tbBusinessBalance.setBalanceStatus(Constants.JSDHASBALANCE);
		}

		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
				.findByTbBusinessBalance(tbBusinessBalance);

		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceList);

		return Constants.SUCCESS;
	}

	public String reBalanceTbBusinessBalance() throws Exception {

		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbBusinessBalanceService
					.reBabanceTbBusinessBalance(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(
						"tbBusinessBalanceTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print(
						"tbBusinessBalanceTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print(
					"tbBusinessBalanceTable," + Constants.EXCEPTION);
		}

		return null;
	}
	
	public String rebackTbBusinessBalance() throws Exception {

		String balanceCode = request.getParameter("balanceCode");

		if (null != balanceCode && !"".equals(balanceCode)) {

			boolean flag = tbBusinessBalanceService.updateTbBusinessBalanceReback(balanceCode);
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(
						"tbBusinessBalanceTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print(
						"tbBusinessBalanceTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print(
					"tbBusinessBalanceTable," + Constants.EXCEPTION);
		}

		return null;
	}

	public String selectTbBusinessBalance() throws Exception {

		
		if(null == tbBusinessBalance){
			
			tbBusinessBalance = new TbBusinessBalance();
			
			Date d = new Date();
			
			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");
			
			Date e = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-" + CommonMethod.getMonthDays(CommonMethod.getYear(d), CommonMethod.getMonth(d)), "yyyy-MM-dd");
			
			tbBusinessBalance.setBananceDateStart(s);
			
			tbBusinessBalance.setBananceDateEnd(e);
		}
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
				.findTbBusinessBalanceToGroup(tbBusinessBalance);

		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceList);

		return Constants.SUCCESS;
	}
	
	public String printWtsTbBusinessBalance() throws Exception{
		
		String id = request.getParameter("id");
			
		Map map = tbBusinessBalanceService.putEntrustBalanceReportParamMap(Long.valueOf(id),request);
			
		ReportFileFromStream.parsePdfFromStream(request, response,map);
			
		return null;
		
	}
	
	public String printXsdTbBusinessBalance() throws Exception{
		
		String id = request.getParameter("id");
			
		Map map = tbBusinessBalanceService.putXsdBalanceReportParamMap(Long.valueOf(id),request);
			
		ReportFileFromStream.parsePdfFromStream(request, response,map);
			
		return null;
		
	}
	
	public String findTbBusinessBalanceOrder() throws Exception{
		
		String orderCode = request.getParameter("orderCode");
		
		String orderType = "customer";
		
		if(null==orderCode||"".equals(orderCode)){
			
			orderCode = "fixCount";
		
		}
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService.fixBusinessBalanceOrder(orderCode,orderType);
		
		ActionContext.getContext().put("tbBusinessBalanceList", tbBusinessBalanceList);
		
		return Constants.SUCCESS;
	}
	
	public String findTbBusinessBalanceCarOrder() throws Exception{
		
		String orderCode = request.getParameter("orderCode");
		
		String orderType = "car";
		
		if(null==orderCode||"".equals(orderCode)){
			
			orderCode = "fixCount";
		
		}
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService.fixBusinessBalanceOrder(orderCode,orderType);
		
		ActionContext.getContext().put("tbBusinessBalanceList", tbBusinessBalanceList);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 欠款结算单查询
	 */
	public String findOweGroupTbBusinessBalance() throws Exception{
		
		if(null == tbBusinessBalance){
			
			tbBusinessBalance = new TbBusinessBalance();
			
			Date d = new Date();
			
			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");
			
			Date e = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-" + CommonMethod.getMonthDays(CommonMethod.getYear(d), CommonMethod.getMonth(d)), "yyyy-MM-dd");
			
			tbBusinessBalance.setBananceDateStart(s);
			
			tbBusinessBalance.setBananceDateEnd(e);
		}
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
		.findTbBusinessBalanceOweGroup(tbBusinessBalance);
		
		if(null!=tbBusinessBalanceList&&tbBusinessBalanceList.size()>0){
			
			if(null!=tbBusinessBalanceList&&tbBusinessBalanceList.size()>0){
				
				BigDecimal d = new BigDecimal("0.00");
				
				for(TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList){
					d = d.add(new BigDecimal(String.valueOf(tbBusinessBalance.getOweAmount()))).setScale(2,BigDecimal.ROUND_HALF_UP);
				}
				
				ActionContext.getContext().put("totalQK",d.doubleValue());
			}
			
		}
		
		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceList);
		
		ActionContext.getContext().getSession().put("tbBusinessBalanceGroupOweList",
				tbBusinessBalanceList);
		
		return Constants.SUCCESS;
	}
	
	public String tbBusinessBalanceGroupOweExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "到款登记核销";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();
		
		List<TbBusinessBalance> tbBusinessBalanceList = (List<TbBusinessBalance>) ActionContext.getContext().getSession().get("tbBusinessBalanceGroupOweList");
	
		tbBusinessBalanceService.exportOweXls(os, "/owe_export.properties", tbBusinessBalanceList);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	 
	
	/**
	 * 到款登记费用减免
	 */
	public String receiveTbBusinessBalance() throws Exception{
		
		String flag = request.getParameter("flag");
		
		String tbBusinessBalanceId = request.getParameter("id");
		
		TbBusinessBalance tbBusinessBalanceTemp = new TbBusinessBalance();

		tbBusinessBalanceTemp.setId(Long.valueOf(tbBusinessBalanceId));

		List<TbBusinessBalance> list = tbBusinessBalanceService.findTbBusinessBalanceToGroup(tbBusinessBalanceTemp);

		if(null!=list&&list.size()>0){
	
			tbBusinessBalance = list.get(0);
			
			TbCustomer tbCustomer = null;
			
			if(null!=tbBusinessBalance.getTbFixEntrust()){
				
				tbCustomer = tbBusinessBalance.getTbFixEntrust().getTbCarInfo().getTbCustomer();
			}
			else{
				
				TmStockOut tmStockOut = tmStockOutService.findById(tbBusinessBalance.getTmStockOut().getId());
				
				tbCustomer = tbCustomerService.findById(tmStockOut.getCustomerBill());
				
			}
	
			ActionContext.getContext().put("tbCustomer",tbCustomer);
		}

		ActionContext.getContext().put("payMap", Constants.getPayMap());

		if("receive".equals(flag)){
			
			if(null==tbReceiveFree){
				
				tbReceiveFree = new TbReceiveFree();
			
			}
			tbReceiveFree.setAmountType(Constants.AMOUNTR);
		}
		else{
			if(null==tbReceiveFree){
				
				tbReceiveFree = new TbReceiveFree();
				
			}
			tbReceiveFree.setAmountType(Constants.AMOUNTS);
		}
		
		return Constants.VIEWPAGE;
	}
	
	public String insertTbReceiveFree() throws Exception{
		
		String customerId = request.getParameter("customerId");
		
		TbCustomer tbCustomer = new TbCustomer();
		
		tbCustomer.setId(Long.valueOf(customerId));
		
		tbReceiveFree.setTbCustomer(tbCustomer);
		
		Double feeAmount = tbReceiveFree.getFeeAmount();
		
		tbReceiveFree.setAmountDate(new Date());
		
		tbBusinessBalance = tbBusinessBalanceService.findById(tbBusinessBalance.getId());
		
		Double shouldPayAmount = tbBusinessBalance.getShouldPayAmount();
		
		/**
		 * add if 2011-07-05
		 */
		if(Constants.AMOUNTR.equals(tbReceiveFree.getAmountType())){
			
			tbBusinessBalance.setShouldPayAmount(this.sumAmount(feeAmount, shouldPayAmount));
			
			
		}
		
		
		tbReceiveFree.setTbBusinessBalance(tbBusinessBalance);
		
		tbBusinessBalanceService.registerReceiveFee(tbReceiveFree);
		
		return Constants.SUCCESS;
	}
	
	public String findTbReceiveFree() throws Exception{
		
		List<TbReceiveFree> tbReceiveFreeList = tbReceiveFreeService.findByTbReceiveFree(tbReceiveFree);
		
		ActionContext.getContext().put("amountTypeMap", Constants.getAmountTypeMap());
		
		ActionContext.getContext().put("tbReceiveFreeList", tbReceiveFreeList);
		
		return Constants.SUCCESS;
	}
	
	public String deleteTbReceiveFree() throws Exception{
		
		String id = request.getParameter("id");
		
		tbReceiveFree = tbReceiveFreeService.findById(Long.valueOf(id));
		
		tbBusinessBalance = tbBusinessBalanceService.findById(tbReceiveFree.getTbBusinessBalance().getId());
		
		/**
		 * add if 2011-07-05
		 */
		if(Constants.AMOUNTR.equals(tbReceiveFree.getAmountType())){
		
			tbBusinessBalance.setShouldPayAmount(this.sumAmount(tbBusinessBalance.getShouldPayAmount(),0D-tbReceiveFree.getFeeAmount()));
			
		}
		
		tbReceiveFree.setTbBusinessBalance(tbBusinessBalance);
		
		tbBusinessBalanceService.deleteReceiveFee(tbReceiveFree);
		
		response.getWriter().print("tbReceiveFreeTable," + Constants.SUCCESS);
		
		return null;
	}
	
	public String findGroupTbBusinessBalanceSumAmount() throws Exception{
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
		.findTbBusinessBalanceToGroup(tbBusinessBalance);
		
		if(null!=tbBusinessBalanceList&&tbBusinessBalanceList.size()>0){
			
			BigDecimal d = new BigDecimal("0.00");
			
			for(TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList){
				d = d.add(new BigDecimal(String.valueOf(tbBusinessBalance.getShouldPayAmount()))).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			ActionContext.getContext().put("totalSJ",d.doubleValue());
		}
		
		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceList);
		
		ActionContext.getContext().put("payMap",Constants.getPayMap());
		
		return Constants.SUCCESS;
	}
	
	//优惠金额查询
	public String findGroupTbBusinessBalanceFavour() throws Exception{
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
		.findTbBusinessBalanceToGroupReceiveFree(tbBusinessBalance);
		
		if(null!=tbBusinessBalanceList&&tbBusinessBalanceList.size()>0){
			
			BigDecimal totalXLGSF = new BigDecimal("0.00");
			
			BigDecimal totalXLCLF = new BigDecimal("0.00");
			
			BigDecimal totalXSJE = new BigDecimal("0.00");
			
			BigDecimal total = new BigDecimal("0.00");
			
			BigDecimal totalQl = new BigDecimal("0.00");
			
			for(TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList){
				
				totalXLGSF = totalXLGSF.add(new BigDecimal(String.valueOf(tbBusinessBalance.getXlgsfFavourAmount())));
				
				totalXLCLF = totalXLCLF.add(new BigDecimal(String.valueOf(tbBusinessBalance.getXlclfFavourAmount())));
				
				totalXSJE = totalXSJE.add(new BigDecimal(String.valueOf(tbBusinessBalance.getXsjeFavourAmount())));
				
				total = total.add(new BigDecimal(String.valueOf(tbBusinessBalance.getTotalFavourAmount())));
				
				totalQl = totalQl.add(new BigDecimal(String.valueOf(tbBusinessBalance.getQlAmount())));
				
			}
			
			ActionContext.getContext().put("totalXLGSF", totalXLGSF.setScale(2,BigDecimal.ROUND_HALF_UP));
			
			ActionContext.getContext().put("totalXLCLF", totalXLCLF.setScale(2,BigDecimal.ROUND_HALF_UP));
			
			ActionContext.getContext().put("totalXSJE", totalXSJE.setScale(2,BigDecimal.ROUND_HALF_UP));
			
			ActionContext.getContext().put("total", total.setScale(2,BigDecimal.ROUND_HALF_UP));
			
			ActionContext.getContext().put("totalQl", totalQl.setScale(2,BigDecimal.ROUND_HALF_UP));
			
		}
		
		ActionContext.getContext().put("tbBusinessBalanceList",
				tbBusinessBalanceList);
		
		return Constants.SUCCESS;
	}
	
	public String analyseBusiness() throws Exception{
		
		//车型MAP
		ActionContext.getContext().put("tmCarModelTypeMap", tmCarModelTypeService.findAllTmCarModelTypeMap());
		
		
		return Constants.SUCCESS;
	}
	
	public String analyseTbBusinessItem() throws Exception{
		
		String fixDateStart = request.getParameter("fixDateStart");
		
		String fixDateEnd = request.getParameter("fixDateEnd");
		
		String tmCarModelTypeId = request.getParameter("tmCarModelTypeId");
		
		tbBusinessBalance = new TbBusinessBalance();
		
		String format = "yyyy-MM-dd";
		
		tbBusinessBalance.setBananceDateStart_s(fixDateStart);
		
		tbBusinessBalance.setBananceDateEnd_s(fixDateEnd);
		
		tbBusinessBalance.setBananceDateStart(CommonMethod.parseStringToDate(fixDateStart, format));
		
		tbBusinessBalance.setBananceDateEnd(CommonMethod.parseStringToDate(fixDateEnd, format));
		
		if(null!=tmCarModelTypeId&&!"".equals(tmCarModelTypeId)){
			
			tbBusinessBalance.setTmModelTypeId(Long.valueOf(tmCarModelTypeId));
			
		}
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_balanceItem = tbBusinessBalanceItemService.staticsBalanceItem(tbBusinessBalance);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_balanceItem", statisticsTbFixBusinessVoList_balanceItem);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_noOwe = tbBusinessBalanceService.statisticsPayed(tbBusinessBalance, 1L);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_owe = tbBusinessBalanceService.statisticsPayed(tbBusinessBalance, 2L);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_noOwe", statisticsTbFixBusinessVoList_noOwe);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_owe", statisticsTbFixBusinessVoList_owe);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_all = tbBusinessBalanceService.statisticsAll(tbBusinessBalance);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_all", statisticsTbFixBusinessVoList_all);
		
		return Constants.SUCCESS;
	}
	
	private Double sumAmount(Double d1 , Double d2){
		
		if(null==d1){
			d1 = 0.00D;
		}
		if(null==d2){
			d2 = 0.00D;
		}
		
		BigDecimal bd = new BigDecimal(String.valueOf(d1));
		
		bd = bd.add(new BigDecimal(String.valueOf(d2))).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		return bd.doubleValue();
	}
	
	public void printTbBusinessBalanceTemplate() throws Exception {
		
		String id = request.getParameter("id");
		
		String companyName = request.getParameter("companyName");
		
		tbBusinessBalance = tbBusinessBalanceService.findById(Long.valueOf(id));
		
		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename=" + tbBusinessBalance.getBalanceCode()
				 + ".xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();
		
		tbBusinessBalanceService.printTbBusinessBalanceTemplate(os, "/cdjd_jsd.xls", Long.valueOf(id),companyName);
		
		os.flush();
		
		os.close();
	} 
	
	
	public void exportTbBusinessBalanceGroupXls() throws Exception {
		 
		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename=jsd.xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();
		
		tbBusinessBalanceService.exportTbBusinessBalanceGroupXls(os, "/jsd_export.properties");
		
		os.flush();
		
		os.close();
		    
	}
}
