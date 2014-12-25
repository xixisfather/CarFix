package com.selfsoft.business.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.TempFile;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;
import com.selfsoft.baseparameter.service.ITmBalanceService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.baseparameter.service.ITmWorkingHourPriceService;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbBusinessSpecialBalance;
import com.selfsoft.business.model.TbBusinessSpecialBalanceHandleFinance;
import com.selfsoft.business.model.TbBusinessSpecialBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbSpecialPartContent;
import com.selfsoft.business.model.TbSpecialWorkingContent;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITbAnvancePayService;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceHandleFinanceService;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceItemService;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITbSpecialPartContentService;
import com.selfsoft.business.service.ITbSpecialWorkingContentService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.file.ReportFileFromStream;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TbBusinessSpecialBalanceAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 694881602073760857L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITbBusinessSpecialBalanceService tbBusinessSpecialBalanceService;

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
	private ITbSpecialWorkingContentService tbSpecialWorkingContentService;

	@Autowired
	private ITbSpecialPartContentService tbSpecialPartContentService;

	@Autowired
	private ITbBusinessSpecialBalanceItemService tbBusinessSpecialBalanceItemService;

	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;

	@Autowired
	private ITmWorkingHourPriceService tmWorkingHourPriceService;

	@Autowired
	private ITbBusinessSpecialBalanceHandleFinanceService tbBusinessSpecialBalanceHandleFinanceService;

	private TbBusinessSpecialBalance tbBusinessSpecialBalance;

	private TbBusinessBalance tbBusinessBalance;

	private TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance;

	public TbBusinessSpecialBalanceHandleFinance getTbBusinessSpecialBalanceHandleFinance() {
		return tbBusinessSpecialBalanceHandleFinance;
	}

	public void setTbBusinessSpecialBalanceHandleFinance(
			TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance) {
		this.tbBusinessSpecialBalanceHandleFinance = tbBusinessSpecialBalanceHandleFinance;
	}

	public TbBusinessBalance getTbBusinessBalance() {
		return tbBusinessBalance;
	}

	public void setTbBusinessBalance(TbBusinessBalance tbBusinessBalance) {
		this.tbBusinessBalance = tbBusinessBalance;
	}

	public TbBusinessSpecialBalance getTbBusinessSpecialBalance() {
		return tbBusinessSpecialBalance;
	}

	public void setTbBusinessSpecialBalance(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {
		this.tbBusinessSpecialBalance = tbBusinessSpecialBalance;
	}

	/**
	 * 特殊结算单查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findTbBusinessSpecialBalance() throws Exception {

		if (null == tbBusinessSpecialBalance) {

			tbBusinessSpecialBalance = new TbBusinessSpecialBalance();

			Date d = new Date();

			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d)
					+ "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");

			Date e = CommonMethod.parseStringToDate(
					CommonMethod.getYear(d)
							+ "-"
							+ CommonMethod.getMonth(d)
							+ "-"
							+ CommonMethod.getMonthDays(
									CommonMethod.getYear(d),
									CommonMethod.getMonth(d)), "yyyy-MM-dd");

			tbBusinessSpecialBalance.setBananceDateStart(s);

			tbBusinessSpecialBalance.setBananceDateEnd(e);

			tbBusinessSpecialBalance.setSpecialType(1L);
		}

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceList = tbBusinessSpecialBalanceService
				.findByTbBusinessSpecialBalance(tbBusinessSpecialBalance);

		String licenseCode = null;

		if (null != tbBusinessSpecialBalance) {

			licenseCode = tbBusinessSpecialBalance.getLicenseCode();

		}
		/*
		 * List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceListPage = new
		 * ArrayList<TbBusinessSpecialBalance>();
		 * 
		 * if(null != tbBusinessSpecialBalanceList &&
		 * tbBusinessSpecialBalanceList.size() > 0){
		 * 
		 * for(TbBusinessSpecialBalance tbBusinessSpecialBalance :
		 * tbBusinessSpecialBalanceList){
		 * 
		 * if(null != tbBusinessSpecialBalance.getEntrustId()){
		 * 
		 * tbBusinessSpecialBalance.setEntrustCode(tbFixEntrustService.findById(
		 * tbBusinessSpecialBalance.getEntrustId()).getEntrustCode());
		 * 
		 * }
		 * 
		 * tbBusinessSpecialBalanceListPage.add(tbBusinessSpecialBalance); }
		 * 
		 * }
		 * 
		 * ActionContext.getContext().put("tbBusinessSpecialBalanceList",
		 * tbBusinessSpecialBalanceListPage);
		 */

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceListPage = new ArrayList<TbBusinessSpecialBalance>();

		BigDecimal d_total = new BigDecimal("0.00");

		if (null != tbBusinessSpecialBalanceList
				&& tbBusinessSpecialBalanceList.size() > 0) {

			for (TbBusinessSpecialBalance tbBusinessSpecialBalance : tbBusinessSpecialBalanceList) {

				TbCarInfo tbCarInfo = null;

				TbCustomer tbCustomer = null;

				if (null != tbBusinessSpecialBalance.getTbBusinessBalance()) {

					if (null != tbBusinessSpecialBalance.getTbBusinessBalance()
							.getTbFixEntrust()) {

						tbCarInfo = tbBusinessSpecialBalance
								.getTbBusinessBalance().getTbFixEntrust()
								.getTbCarInfo();

						if (null != tbCarInfo) {

							tbCustomer = tbCarInfo.getTbCustomer();

						}

					}

					else if (null != tbBusinessSpecialBalance
							.getTbBusinessBalance().getTmStockOut()) {

						tbCustomer = tbCustomerService
								.findById(tbBusinessSpecialBalance
										.getTbBusinessBalance().getTmStockOut()
										.getCustomerBill());

					}

				}

				else {

					if (null != tbBusinessSpecialBalance.getEntrustId()) {

						TbFixEntrust tbFixEntrust = tbFixEntrustService
								.findById(tbBusinessSpecialBalance
										.getEntrustId());

						if (null != tbFixEntrust) {

							tbBusinessSpecialBalance
									.setEntrustCode(tbFixEntrust
											.getEntrustCode());

							tbCarInfo = tbFixEntrust.getTbCarInfo();

							if (null != tbCarInfo) {

								tbCustomer = tbCarInfo.getTbCustomer();

							}

						}

					}

				}

				d_total = d_total.add(new BigDecimal(tbBusinessSpecialBalance
						.getBalanceTotalAll()));

				tbBusinessSpecialBalance.setTbCarInfo(tbCarInfo);

				tbBusinessSpecialBalance.setTbCustomer(tbCustomer);

				if (null != licenseCode && !"".equals(licenseCode)) {

					if (null != tbCarInfo) {
						if (licenseCode.equals(tbCarInfo.getLicenseCode())) {
							tbBusinessSpecialBalanceListPage
									.add(tbBusinessSpecialBalance);
						}
					}

				} else {
					tbBusinessSpecialBalanceListPage
							.add(tbBusinessSpecialBalance);
				}

			}

		}

		ActionContext.getContext().put("tbBusinessSpecialBalanceList",
				tbBusinessSpecialBalanceListPage);

		ActionContext.getContext().put(
				"totalSize",
				tbBusinessSpecialBalanceListPage == null ? 0
						: tbBusinessSpecialBalanceListPage.size());

		ActionContext.getContext().put(
				"totalAll",
				d_total.divide(new BigDecimal("1.00"), 2,
						BigDecimal.ROUND_HALF_UP));

		return Constants.SUCCESS;
	}

	public String staticsTbBusinessSpecialBalanceFinance() throws Exception {

		if (null == tbBusinessSpecialBalance) {

			tbBusinessSpecialBalance = new TbBusinessSpecialBalance();

		}

		tbBusinessSpecialBalance.setSpecialType(2L);

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceList = tbBusinessSpecialBalanceService
				.findByTbBusinessSpecialBalance(tbBusinessSpecialBalance);

		ActionContext.getContext().put("tbBusinessSpecialBalanceList",
				tbBusinessSpecialBalanceList);

		ActionContext
				.getContext()
				.getSession()
				.put("tbBusinessSpecialHandleBalance", tbBusinessSpecialBalance);

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceListPage = new ArrayList<TbBusinessSpecialBalance>();

		if (null != tbBusinessSpecialBalanceList
				&& tbBusinessSpecialBalanceList.size() > 0) {

			ActionContext.getContext().put("totalCount",
					tbBusinessSpecialBalanceList.size());

			BigDecimal d_total = new BigDecimal("0.00");

			BigDecimal d_fixHour = new BigDecimal("0.00");

			BigDecimal d_fixPart = new BigDecimal("0.00");

			BigDecimal d_costTotal = new BigDecimal("0.00");

			for (TbBusinessSpecialBalance t : tbBusinessSpecialBalanceList) {

				d_total = d_total.add(
						new BigDecimal(String.valueOf(t.getBalanceTotalAll())))
						.setScale(2);

				d_fixHour = d_fixHour.add(
						tbSpecialWorkingContentService
								.countTbSpecialWorkingContent(t.getId()))
						.setScale(2);

				d_fixPart = d_fixPart.add(
						tbSpecialPartContentService.countTbSpecialPartContent(t
								.getId())).setScale(2);

				d_costTotal = d_costTotal.add(tbSpecialPartContentService
						.countTbSpecialPartContentCost(t.getId()));

				TbCarInfo tbCarInfo = null;

				TbCustomer tbCustomer = null;

				if (null != t.getLicenseCode()
						&& !"".equals(t.getLicenseCode())) {

					tbCarInfo = tbCarInfoService.findTbCarInfoBylicenseCode(t
							.getLicenseCode());

					if (null == tbCarInfo) {

						tbCarInfo = new TbCarInfo();

						tbCarInfo.setLicenseCode(t.getLicenseCode());
					}

					else {

						tbCustomer = tbCarInfo.getTbCustomer();

					}
				}

				if (null != t.getCustomerName()
						&& !"".equals(t.getCustomerName())) {

					if (null == tbCustomer) {

						tbCustomer = new TbCustomer();

						tbCustomer.setCustomerName(t.getCustomerName());

					}

				}

				if (null != t.getTmCarModelTypeId()) {

					t.setModelCode(tmCarModelTypeService.findById(t
							.getTmCarModelTypeId()) == null ? ""
							: tmCarModelTypeService.findById(
									t.getTmCarModelTypeId()).getModelCode());

				}

				t.setTbCarInfo(tbCarInfo);

				t.setTbCustomer(tbCustomer);

				tbBusinessSpecialBalanceListPage.add(t);
			}

			ActionContext
					.getContext()
					.getSession()
					.put("tbBusinessSpecialBalanceHandleList",
							tbBusinessSpecialBalanceListPage);

			ActionContext
					.getContext()
					.getSession()
					.put("totalHandleSize",
							tbBusinessSpecialBalanceListPage.size());

			ActionContext.getContext().put("total", d_total);

			ActionContext.getContext().getSession().put("total", d_total);

			ActionContext.getContext().put("fixHour", d_fixHour);

			ActionContext.getContext().put("fixPart", d_fixPart);

			ActionContext.getContext().put("costTotal", d_costTotal);

		}

		return Constants.SUCCESS;
	}

	/**
	 * 财务录入查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findTbBusinessSpecialBalanceFinance() throws Exception {

		if (null == tbBusinessSpecialBalance) {

			tbBusinessSpecialBalance = new TbBusinessSpecialBalance();

		}

		tbBusinessSpecialBalance.setSpecialType(2L);

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceList = tbBusinessSpecialBalanceService
				.findByTbBusinessSpecialBalance(tbBusinessSpecialBalance);

		ActionContext.getContext().put("tbBusinessSpecialBalanceList",
				tbBusinessSpecialBalanceList);

		return Constants.SUCCESS;
	}

	/**
	 * 长沙版维修业务查询统计
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTbBusinessSpecialBalanceFinance() throws Exception {

		if (null == tbBusinessSpecialBalance) {

			tbBusinessSpecialBalance = new TbBusinessSpecialBalance();

		}

		tbBusinessSpecialBalance.setSpecialType(2L);

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceList = tbBusinessSpecialBalanceService
				.findByTbBusinessSpecialBalance(tbBusinessSpecialBalance);

		ActionContext.getContext().put("tbBusinessSpecialBalanceList",
				tbBusinessSpecialBalanceList);

		return Constants.SUCCESS;
	}

	/**
	 * 查看特殊编辑单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewTbBusinessSpecialBalance() throws Exception {

		if (null == tbBusinessSpecialBalance) {

			tbBusinessSpecialBalance = new TbBusinessSpecialBalance();

			Date d = new Date();

			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d)
					+ "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");

			Date e = CommonMethod.parseStringToDate(
					CommonMethod.getYear(d)
							+ "-"
							+ CommonMethod.getMonth(d)
							+ "-"
							+ CommonMethod.getMonthDays(
									CommonMethod.getYear(d),
									CommonMethod.getMonth(d)), "yyyy-MM-dd");

			tbBusinessSpecialBalance.setBananceDateStart(s);

			tbBusinessSpecialBalance.setBananceDateEnd(e);
		}

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceList = tbBusinessSpecialBalanceService
				.findByTbBusinessSpecialBalance(tbBusinessSpecialBalance);

		String licenseCode = null;

		if (null != tbBusinessSpecialBalance) {

			licenseCode = tbBusinessSpecialBalance.getLicenseCode();

		}

		List<TbBusinessSpecialBalance> tbBusinessSpecialBalanceListPage = new ArrayList<TbBusinessSpecialBalance>();

		BigDecimal d_total = new BigDecimal("0.00");

		if (null != tbBusinessSpecialBalanceList
				&& tbBusinessSpecialBalanceList.size() > 0) {

			for (TbBusinessSpecialBalance tbBusinessSpecialBalance : tbBusinessSpecialBalanceList) {

				TbCarInfo tbCarInfo = null;

				TbCustomer tbCustomer = null;

				if (null != tbBusinessSpecialBalance.getTbBusinessBalance()) {

					if (null != tbBusinessSpecialBalance.getTbBusinessBalance()
							.getTbFixEntrust()) {

						tbCarInfo = tbBusinessSpecialBalance
								.getTbBusinessBalance().getTbFixEntrust()
								.getTbCarInfo();

						if (null != tbCarInfo) {

							tbCustomer = tbCarInfo.getTbCustomer();

						}

					}

					else if (null != tbBusinessSpecialBalance
							.getTbBusinessBalance().getTmStockOut()) {

						tbCustomer = tbCustomerService
								.findById(tbBusinessSpecialBalance
										.getTbBusinessBalance().getTmStockOut()
										.getCustomerBill());

					}

				}

				else {

					if (null != tbBusinessSpecialBalance.getEntrustId()) {

						TbFixEntrust tbFixEntrust = tbFixEntrustService
								.findById(tbBusinessSpecialBalance
										.getEntrustId());

						if (null != tbFixEntrust) {

							tbBusinessSpecialBalance
									.setEntrustCode(tbFixEntrust
											.getEntrustCode());

							tbCarInfo = tbFixEntrust.getTbCarInfo();

							if (null != tbCarInfo) {

								tbCustomer = tbCarInfo.getTbCustomer();

							}

						}

					}

				}

				d_total = d_total.add(new BigDecimal(tbBusinessSpecialBalance
						.getBalanceTotalAll()));

				tbBusinessSpecialBalance.setTbCarInfo(tbCarInfo);

				tbBusinessSpecialBalance.setTbCustomer(tbCustomer);

				if (null != licenseCode && !"".equals(licenseCode)) {

					if (null != tbCarInfo) {
						if (licenseCode.equals(tbCarInfo.getLicenseCode())) {
							tbBusinessSpecialBalanceListPage
									.add(tbBusinessSpecialBalance);
						}
					}

				} else {
					tbBusinessSpecialBalanceListPage
							.add(tbBusinessSpecialBalance);
				}
			}

		}

		ActionContext.getContext().put("tbBusinessSpecialBalanceList",
				tbBusinessSpecialBalanceListPage);

		ActionContext
				.getContext()
				.getSession()
				.put("tbBusinessSpecialBalanceList",
						tbBusinessSpecialBalanceListPage);

		ActionContext.getContext().put(
				"totalSize",
				tbBusinessSpecialBalanceListPage == null ? 0
						: tbBusinessSpecialBalanceListPage.size());

		ActionContext.getContext().put("totalAll", d_total);

		ActionContext
				.getContext()
				.getSession()
				.put("totalSize",
						tbBusinessSpecialBalanceListPage == null ? 0
								: tbBusinessSpecialBalanceListPage.size());

		ActionContext
				.getContext()
				.getSession()
				.put("totalAll",
						d_total.divide(new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP));

		ActionContext.getContext().getSession()
				.put("tbBusinessSpecialBalance", tbBusinessSpecialBalance);

		return Constants.SUCCESS;
	}

	// 解析修理工时
	private Set parseWorkingContent(Integer count) {

		Set tbSpecialWorkingContents = new LinkedHashSet();

		String workingHourPrice = request.getParameter("workingHourPrice");

		for (int i = 0; i < count.intValue(); i++) {

			String tbWorkingInfoId = request
					.getParameter("tbWorkingInfoId" + i);

			if (null == tbWorkingInfoId || "".equals(tbWorkingInfoId)) {

				continue;

			}

			String freesymbol = request.getParameter("freesymbol" + i);

			String stationName = request.getParameter("stationName" + i);

			String fixHour = request.getParameter("fixHour" + i);

			String fixHourAll = request.getParameter("fixHourAll" + i);

			TbSpecialWorkingContent tbSpecialWorkingContent = new TbSpecialWorkingContent();

			TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();

			tbWorkingInfo.setId(Long.valueOf(tbWorkingInfoId));

			tbSpecialWorkingContent.setTbWorkingInfo(tbWorkingInfo);

			tbSpecialWorkingContent.setStationNameDB(stationName);

			tbSpecialWorkingContent.setFixHour(Double.valueOf(fixHour));

			tbSpecialWorkingContent.setWorkingHourPrice(Double
					.valueOf(workingHourPrice));

			tbSpecialWorkingContent.setFixHourAll(Double.valueOf(fixHourAll));

			if (null == freesymbol || "".equals(freesymbol)) {

				freesymbol = "0";

			} else {
				if ("on".equalsIgnoreCase(freesymbol)) {

					freesymbol = "1";

				}
			}
			tbSpecialWorkingContent.setFreesymbol(Long.valueOf(freesymbol));

			tbSpecialWorkingContents.add(tbSpecialWorkingContent);
		}

		return tbSpecialWorkingContents;
	}

	// 解析修理材料
	private Set parsePartContent(Integer count) {

		Set tbSpecialPartContents = new LinkedHashSet();

		for (int i = 0; i < count.intValue(); i++) {

			String tbPartInfoId = request.getParameter("tbPartInfoId" + i);

			if (null == tbPartInfoId || "".equals(tbPartInfoId)) {

				continue;

			}

			String partName = request.getParameter("partName" + i);

			String free = request.getParameter("free" + i);

			String price = request.getParameter("price" + i);

			String partQuantity = request.getParameter("partQuantity" + i);

			String total = request.getParameter("total" + i);

			TbSpecialPartContent tbSpecialPartContent = new TbSpecialPartContent();

			TbPartInfo tbPartInfo = new TbPartInfo();

			tbPartInfo.setId(Long.valueOf(tbPartInfoId));

			tbSpecialPartContent.setTbPartInfo(tbPartInfo);

			tbSpecialPartContent.setPartNameDB(partName);

			tbSpecialPartContent.setPartPrice(Double.valueOf(price));

			tbSpecialPartContent.setPartQuantity(Double.valueOf(partQuantity));

			tbSpecialPartContent.setPartTotal(Double.valueOf(total));

			if (null == free || "".equals(free)) {

				free = "0";

			} else {
				if ("on".equalsIgnoreCase(free)) {

					free = "1";

				}
			}

			tbSpecialPartContent.setIsFree(Long.valueOf(free));

			tbSpecialPartContents.add(tbSpecialPartContent);
		}

		return tbSpecialPartContents;
	}

	private Set parseTbBusinessSpecialBalanceItem(String[] params) {

		Set TbBusinessSpecialBalanceItems = new LinkedHashSet();

		if (null != params && params.length > 0) {

			for (int i = 0; i < params.length; i++) {

				String balanceItemCode = params[i];

				Double balanceItemTotal = Double.valueOf(request
						.getParameter(params[i]));

				TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem = new TbBusinessSpecialBalanceItem();

				tbBusinessSpecialBalanceItem
						.setBalanceItemCode(balanceItemCode);

				tbBusinessSpecialBalanceItem
						.setBalanceItemTotal(balanceItemTotal);

				TbBusinessSpecialBalanceItems.add(tbBusinessSpecialBalanceItem);
			}

		}

		return TbBusinessSpecialBalanceItems;
	}

	public String insertTbBusinessSpecialBalanceFinance() throws Exception {

		String wCount = request.getParameter("w_count");

		String fCount = request.getParameter("f_count");

		String[] params = request.getParameterValues("params");

		// 总金额
		String ZJE = request.getParameter("ZJE");

		Set tbSpecialWorkingContents = this.parseWorkingContent(Integer
				.valueOf(wCount));

		Set tbSpecialPartContents = this.parsePartContent(Integer
				.valueOf(fCount));

		Set tbBusinessSpecialBalanceItems = this
				.parseTbBusinessSpecialBalanceItem(params);

		tbBusinessSpecialBalance
				.setTbSpecialWorkingContents(tbSpecialWorkingContents);

		tbBusinessSpecialBalance
				.setTbSpecialPartContents(tbSpecialPartContents);

		tbBusinessSpecialBalance
				.setTbBusinessSpecialBalanceItems(tbBusinessSpecialBalanceItems);

		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessSpecialBalance.setBalanceTotalAll(Double.valueOf(ZJE));

		String fixPartFavourRate = request.getParameter("fixPartFavourRate");

		String workingHourFavourRate = request
				.getParameter("workingHourFavourRate");

		tbBusinessSpecialBalance.setWorkingHourFavourRate(Double
				.valueOf(workingHourFavourRate));

		tbBusinessSpecialBalance.setFixPartFavourRate(Double
				.valueOf(fixPartFavourRate));

		String editCode = tmDictionaryService
				.GenerateCode(StockTypeElements.TBBUSINESSSPECIALBALANCEFINANCE
						.getElementString());

		tbBusinessSpecialBalance.setEditCode(editCode);

		tbBusinessSpecialBalance.setTbBusinessBalance(tbBusinessBalance);

		// 特殊编辑标识
		tbBusinessSpecialBalance.setSpecialType(2L);

		tbBusinessSpecialBalanceService.insertAll(tbBusinessSpecialBalance);

		ActionContext.getContext().put("msg", "生成流水号：" + editCode);

		return Constants.SUCCESS;
	}

	public String insertTbBusinessSpecialBalance() throws Exception {

		String wCount = request.getParameter("w_count");

		String fCount = request.getParameter("f_count");

		String[] params = request.getParameterValues("params");

		// 总金额
		String ZJE = request.getParameter("ZJE");

		Set tbSpecialWorkingContents = this.parseWorkingContent(Integer
				.valueOf(wCount));

		Set tbSpecialPartContents = this.parsePartContent(Integer
				.valueOf(fCount));

		Set tbBusinessSpecialBalanceItems = this
				.parseTbBusinessSpecialBalanceItem(params);

		tbBusinessSpecialBalance
				.setTbSpecialWorkingContents(tbSpecialWorkingContents);

		tbBusinessSpecialBalance
				.setTbSpecialPartContents(tbSpecialPartContents);

		tbBusinessSpecialBalance
				.setTbBusinessSpecialBalanceItems(tbBusinessSpecialBalanceItems);

		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessSpecialBalance.setBalanceTotalAll(Double.valueOf(ZJE));

		String fixPartFavourRate = request.getParameter("fixPartFavourRate");

		String workingHourFavourRate = request
				.getParameter("workingHourFavourRate");

		tbBusinessSpecialBalance.setWorkingHourFavourRate(Double
				.valueOf(workingHourFavourRate));

		tbBusinessSpecialBalance.setFixPartFavourRate(Double
				.valueOf(fixPartFavourRate));

		String editCode = tmDictionaryService
				.GenerateCode(StockTypeElements.TBBUSINESSSPECIALBALANCE
						.getElementString());

		tbBusinessSpecialBalance.setEditCode(editCode);

		if (null == tbBusinessBalance || null == tbBusinessBalance.getId()) {

			tbBusinessSpecialBalance.setTbBusinessBalance(null);

		} else {

			tbBusinessSpecialBalance.setTbBusinessBalance(tbBusinessBalance);
		}

		String entrustId = request
				.getParameter("tbBusinessBalance.tbFixEntrust.id");

		if (null != entrustId && !"".equals(entrustId)) {

			tbBusinessSpecialBalance.setEntrustId(Long.valueOf(entrustId));

		}

		// 特殊编辑标识
		tbBusinessSpecialBalance.setSpecialType(1L);

		tbBusinessSpecialBalanceService.insertAll(tbBusinessSpecialBalance);

		ActionContext.getContext().put("msg", "生成流水号：" + editCode);

		return Constants.SUCCESS;

	}

	public String insertTbBusinessSpecialBalanceXsd() throws Exception {

		String fCount = request.getParameter("f_count");

		String[] params = request.getParameterValues("params");

		// 总金额
		String ZJE = request.getParameter("ZJE");

		Set tbSpecialPartContents = this.parsePartContent(Integer
				.valueOf(fCount));

		Set tbBusinessSpecialBalanceItems = this
				.parseTbBusinessSpecialBalanceItem(params);

		tbBusinessSpecialBalance
				.setTbSpecialPartContents(tbSpecialPartContents);

		tbBusinessSpecialBalance
				.setTbBusinessSpecialBalanceItems(tbBusinessSpecialBalanceItems);

		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessSpecialBalance.setBalanceTotalAll(Double.valueOf(ZJE));

		String fixPartFavourRate = request.getParameter("fixPartFavourRate");

		tbBusinessSpecialBalance.setFixPartFavourRate(Double
				.valueOf(fixPartFavourRate));

		String editCode = tmDictionaryService
				.GenerateCode(StockTypeElements.TBBUSINESSSPECIALBALANCE
						.getElementString());

		tbBusinessSpecialBalance.setEditCode(editCode);

		tbBusinessSpecialBalance.setTbBusinessBalance(tbBusinessBalance);

		// 特殊编辑标识
		tbBusinessSpecialBalance.setSpecialType(1L);

		tbBusinessSpecialBalanceService.insertAll(tbBusinessSpecialBalance);

		ActionContext.getContext().put("msg", "生成流水号：" + editCode);

		return Constants.SUCCESS;
	}

	/**
	 * 财务录入修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateTbBusinessSpecialBalanceFinance() throws Exception {

		String wCount = request.getParameter("w_count");

		String fCount = request.getParameter("f_count");

		String[] params = request.getParameterValues("params");

		// 总金额
		String ZJE = request.getParameter("ZJE");

		Set tbSpecialWorkingContents = this.parseWorkingContent(Integer
				.valueOf(wCount));

		Set tbSpecialPartContents = this.parsePartContent(Integer
				.valueOf(fCount));

		Set tbBusinessSpecialBalanceItems = this
				.parseTbBusinessSpecialBalanceItem(params);

		tbBusinessSpecialBalance
				.setTbSpecialWorkingContents(tbSpecialWorkingContents);

		tbBusinessSpecialBalance
				.setTbSpecialPartContents(tbSpecialPartContents);

		tbBusinessSpecialBalance
				.setTbBusinessSpecialBalanceItems(tbBusinessSpecialBalanceItems);

		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessSpecialBalance.setBalanceTotalAll(Double.valueOf(ZJE));

		String fixPartFavourRate = request.getParameter("fixPartFavourRate");

		String workingHourFavourRate = request
				.getParameter("workingHourFavourRate");

		tbBusinessSpecialBalance.setWorkingHourFavourRate(Double
				.valueOf(workingHourFavourRate));

		tbBusinessSpecialBalance.setFixPartFavourRate(Double
				.valueOf(fixPartFavourRate));

		tbBusinessSpecialBalance.setTbBusinessBalance(tbBusinessBalance);

		tbBusinessSpecialBalanceService.updateAll(tbBusinessSpecialBalance);

		return Constants.SUCCESS;

	}

	public String updateTbBusinessSpecialBalance() throws Exception {

		String wCount = request.getParameter("w_count");

		String fCount = request.getParameter("f_count");

		String[] params = request.getParameterValues("params");

		// 总金额
		String ZJE = request.getParameter("ZJE");

		Set tbSpecialWorkingContents = this.parseWorkingContent(Integer
				.valueOf(wCount));

		Set tbSpecialPartContents = this.parsePartContent(Integer
				.valueOf(fCount));

		Set tbBusinessSpecialBalanceItems = this
				.parseTbBusinessSpecialBalanceItem(params);

		tbBusinessSpecialBalance
				.setTbSpecialWorkingContents(tbSpecialWorkingContents);

		tbBusinessSpecialBalance
				.setTbSpecialPartContents(tbSpecialPartContents);

		tbBusinessSpecialBalance
				.setTbBusinessSpecialBalanceItems(tbBusinessSpecialBalanceItems);

		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessSpecialBalance.setBalanceTotalAll(Double.valueOf(ZJE));

		String fixPartFavourRate = request.getParameter("fixPartFavourRate");

		String workingHourFavourRate = request
				.getParameter("workingHourFavourRate");

		tbBusinessSpecialBalance.setWorkingHourFavourRate(Double
				.valueOf(workingHourFavourRate));

		tbBusinessSpecialBalance.setFixPartFavourRate(Double
				.valueOf(fixPartFavourRate));

		if (null == tbBusinessBalance || null == tbBusinessBalance.getId()) {

			tbBusinessSpecialBalance.setTbBusinessBalance(null);

		} else {

			tbBusinessSpecialBalance.setTbBusinessBalance(tbBusinessBalance);
		}

		String entrustId = request
				.getParameter("tbBusinessBalance.tbFixEntrust.id");

		if (null != entrustId && !"".equals(entrustId)) {

			tbBusinessSpecialBalance.setEntrustId(Long.valueOf(entrustId));

		}

		tbBusinessSpecialBalanceService.updateAll(tbBusinessSpecialBalance);

		return Constants.SUCCESS;

	}

	public String updateTbBusinessSpecialBalanceXsd() throws Exception {

		String fCount = request.getParameter("f_count");

		String[] params = request.getParameterValues("params");

		// 总金额
		String ZJE = request.getParameter("ZJE");

		Set tbSpecialPartContents = this.parsePartContent(Integer
				.valueOf(fCount));

		Set tbBusinessSpecialBalanceItems = this
				.parseTbBusinessSpecialBalanceItem(params);

		tbBusinessSpecialBalance
				.setTbSpecialPartContents(tbSpecialPartContents);

		tbBusinessSpecialBalance
				.setTbBusinessSpecialBalanceItems(tbBusinessSpecialBalanceItems);

		// 设置总金额---该字段为冗余字段 显示用
		tbBusinessSpecialBalance.setBalanceTotalAll(Double.valueOf(ZJE));

		String fixPartFavourRate = request.getParameter("fixPartFavourRate");

		tbBusinessSpecialBalance.setFixPartFavourRate(Double
				.valueOf(fixPartFavourRate));

		tbBusinessSpecialBalance.setTbBusinessBalance(tbBusinessBalance);

		tbBusinessSpecialBalanceService.updateAll(tbBusinessSpecialBalance);

		return Constants.SUCCESS;
	}

	public String deleteTbBusinessSpecialBalance() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbBusinessSpecialBalanceService.deleteAll(Long
					.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(
						"tbBusinessSpecialBalanceTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print(
						"tbBusinessSpecialBalanceTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print(
					"tbBusinessSpecialBalanceTable," + Constants.EXCEPTION);
		}
		return null;
	}

	/**
	 * 财务录入跳转
	 */
	public String forwardPageFinance() throws Exception {

		String id = request.getParameter("id");

		String flag = request.getParameter("flag");

		// 车型
		ActionContext.getContext().put("tmCarModelTypeMap",
				tmCarModelTypeService.findAllTmCarModelTypeMap());
		// 旧件处理
		ActionContext.getContext()
				.put("oldPartDealMap", Constants.getDealMap());
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		// 结算项目类型
		ActionContext.getContext().put("tmBalanceMap",
				tmBalanceService.findAllTmBalanceMap());
		// 免费标识
		ActionContext.getContext().put("freeSymbolMap",
				Constants.getFreeSymbolMap());
		// 用户列表
		ActionContext.getContext().put("tmUserMap",
				tmUserService.findAllTmUserMap());
		// 工时单价
		ActionContext.getContext().put("workingHourPrice",
				tmWorkingHourPriceService.findAll().get(0).getPrice());

		// 结算项目
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
				.findTmBalanceItemByTmBalanceId(tmBalanceService.findAll()
						.get(0).getId());

		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);

		// 修理工时总价
		Double workingHourTotalAll = 0.00D;
		// 修理材料总价
		Double fixPartTotalAll = 0.00D;
		// 销售总价
		Double solePartTotalAll = 0.00D;

		/*******************************************************************************/
		Map<String, BigDecimal> initMap = new LinkedHashMap<String, BigDecimal>();

		Map<String, BigDecimal> calcMap = new LinkedHashMap<String, BigDecimal>();

		Map<String, BigDecimal> calcSecondMap = new LinkedHashMap<String, BigDecimal>();

		Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

		if (null != id && !"".equals(id)) {

			tbBusinessSpecialBalance = tbBusinessSpecialBalanceService
					.findById(Long.valueOf(id));

			/**
			 * 结算单中工时工位
			 */
			List<TbSpecialWorkingContent> tbSpecialWorkingContentList = tbSpecialWorkingContentService
					.findBySpecialId(tbBusinessSpecialBalance.getId());

			ActionContext.getContext().put("tbSpecialWorkingContentList",
					tbSpecialWorkingContentList);

			/**
			 * 结算单中维修发料
			 */
			List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
					.findBySpecialId(tbBusinessSpecialBalance.getId());

			ActionContext.getContext().put("tbSpecialPartContentList",
					tbSpecialPartContentList);

			// 结算单中结算项目
			List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
					.findBySpecialId(tbBusinessSpecialBalance.getId());

			if (null != tbBusinessSpecialBalanceItemList) {

				for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

					calcMapReturn.put(
							tbBusinessSpecialBalanceItem.getBalanceItemCode(),
							new BigDecimal(String
									.valueOf(tbBusinessSpecialBalanceItem
											.getBalanceItemTotal())));

				}

			}

			ActionContext.getContext().getSession()
					.put("calcMapReturn", calcMapReturn);

			if ("view".equals(flag)) {

				return Constants.VIEWPAGE;

			}

			return Constants.EDITPAGE;
		}

		if (null != tmBalanceItemList && tmBalanceItemList.size() > 0) {

			// 初始化金额

			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				if (Constants.XLGSF.equals(tmBalanceItem.getItemName().trim())) {

					initMap.put(Constants.XLGSF,
							new BigDecimal(String.valueOf(workingHourTotalAll)));

				} else if (Constants.XLCLF.equals(tmBalanceItem.getItemName()
						.trim())) {

					initMap.put(Constants.XLCLF,
							new BigDecimal(String.valueOf(fixPartTotalAll))
									.add(new BigDecimal(String
											.valueOf(solePartTotalAll))));

				} else if (Constants.XSJE.equals(tmBalanceItem.getItemName()
						.trim())) {

					initMap.put(Constants.XSJE,
							new BigDecimal(String.valueOf("0.00")));

				} else {

					initMap.put(tmBalanceItem.getItemName().trim(),
							new BigDecimal("0.00"));

				}

			}

			// 计算各项明细金额
			for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

				BigDecimal itemValue = new BigDecimal(
						String.valueOf(tmBalanceItemService
								.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
										tmBalanceService.findAll().get(0)
												.getId(),
										tmBalanceItem.getItemName().trim(),
										initMap).get(0)));

				calcMap.put(tmBalanceItem.getItemName(),
						itemValue.setScale(2, BigDecimal.ROUND_HALF_UP));
			}

		}

		for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

			BigDecimal itemValue = new BigDecimal(
					String.valueOf(tmBalanceItemService
							.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
									tmBalanceService.findAll().get(0).getId(),
									tmBalanceItem.getItemName().trim(), calcMap)
							.get(0)));

			calcSecondMap.put(tmBalanceItem.getItemName(),
					itemValue.setScale(2, BigDecimal.ROUND_HALF_UP));
		}

		for (TmBalanceItem tmBalanceItem : tmBalanceItemList) {

			BigDecimal itemValue = new BigDecimal(
					String.valueOf(tmBalanceItemService
							.formulaCalculateByTmBalanceIdAndTmBalanceItemName(
									tmBalanceService.findAll().get(0).getId(),
									tmBalanceItem.getItemName().trim(),
									calcSecondMap).get(0)));

			calcMapReturn.put(tmBalanceItem.getItemCode(),
					itemValue.setScale(2, BigDecimal.ROUND_HALF_UP));
		}

		ActionContext.getContext().getSession()
				.put("calcMapReturn", calcMapReturn);

		ActionContext.getContext().getSession()
				.put("calcSecondMapName", calcSecondMap);

		return Constants.ADDPAGE;
	}

	public String forwardPage() throws Exception {

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
		// 免费标识
		ActionContext.getContext().put("freeSymbolMap",
				Constants.getFreeSymbolMap());

		if (null != id && !"".equals(id)) {

			tbBusinessSpecialBalance = tbBusinessSpecialBalanceService
					.findById(Long.valueOf(id));

			tbBusinessBalance = tbBusinessSpecialBalance.getTbBusinessBalance();

			if (null == tbBusinessBalance) {

				tbBusinessBalance = new TbBusinessBalance();

				if (null != tbBusinessSpecialBalance.getEntrustId()) {

					TbFixEntrust tbFixEntrust = tbFixEntrustService
							.findById(tbBusinessSpecialBalance.getEntrustId());

					tbBusinessBalance.setTbFixEntrust(tbFixEntrust);

				}
				tbBusinessSpecialBalance
						.setTbBusinessBalance(tbBusinessBalance);

			}

			// 委托书特殊结算
			if (null != tbBusinessSpecialBalance.getTbBusinessBalance()
					.getTbFixEntrust()) {

				TbFixEntrust tbFixEntrust = tbFixEntrustService
						.findById(tbBusinessSpecialBalance
								.getTbBusinessBalance().getTbFixEntrust()
								.getId());

				TbCarInfo tbCarInfo = tbCarInfoService
						.findTbCarInfoById(tbFixEntrust.getTbCarInfo().getId());

				TmUser tmUser_wts = tmUserService.findById(tbFixEntrust
						.getTmUser().getId());

				tbFixEntrust.setTbCarInfo(tbCarInfo);

				tbFixEntrust.setTmUser(tmUser_wts);

				/**
				 * 结算单中工时工位
				 */
				List<TbSpecialWorkingContent> tbSpecialWorkingContentList = tbSpecialWorkingContentService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				ActionContext.getContext().put("tbSpecialWorkingContentList",
						tbSpecialWorkingContentList);

				/**
				 * 结算单中维修发料
				 */
				List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				ActionContext.getContext().put("tbSpecialPartContentList",
						tbSpecialPartContentList);

				// 结算项目
				List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
						.findTmBalanceItemByTmBalanceId(tbFixEntrust
								.getTmFixType().getTmBalance().getId());

				ActionContext.getContext().put("tmBalanceItemList",
						tmBalanceItemList);

				// 结算单中结算项目
				List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

				if (null != tbBusinessSpecialBalanceItemList) {

					for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

						calcMapReturn.put(
								tbBusinessSpecialBalanceItem
										.getBalanceItemCode(),
								new BigDecimal(String
										.valueOf(tbBusinessSpecialBalanceItem
												.getBalanceItemTotal())));

					}

				}

				ActionContext.getContext().getSession()
						.put("calcMapReturn", calcMapReturn);

				return Constants.EDITPAGE;
			} else {

				TmStockOut tmStockOut = tmStockOutService
						.findById(tbBusinessBalance.getTmStockOut().getId());

				TbCustomer customer = tbCustomerService.findById(tmStockOut
						.getCustomerBill());

				request.setAttribute("customer", customer);
				// 默认销售类型
				TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();

				List<TmSoleType> tsyList = new ArrayList<TmSoleType>();

				tsyList.add(tsy);

				request.setAttribute("tsyList", tsyList);

				List<TmBalance> tmBalanceList = tmBalanceService.findAll();

				Long tmBalanceId = tmBalanceList.get(0).getId();

				ActionContext.getContext().put("tmBalanceId", tmBalanceId);

				List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				ActionContext.getContext().put("tbSpecialPartContentList",
						tbSpecialPartContentList);

				// 结算单中结算项目
				List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				// 结算项目
				List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
						.findTmBalanceItemByTmBalanceId(tmBalanceId);

				ActionContext.getContext().put("tmBalanceItemList",
						tmBalanceItemList);

				Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

				if (null != tbBusinessSpecialBalanceItemList) {

					for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

						calcMapReturn.put(
								tbBusinessSpecialBalanceItem
										.getBalanceItemCode(),
								new BigDecimal(String
										.valueOf(tbBusinessSpecialBalanceItem
												.getBalanceItemTotal())));

					}

				}

				ActionContext.getContext().getSession()
						.put("calcMapReturn", calcMapReturn);

				return Constants.OTHERPAGE;
			}
		}
		return null;
	}

	public String forwardViewPage() throws Exception {

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
		// 免费标识
		ActionContext.getContext().put("freeSymbolMap",
				Constants.getFreeSymbolMap());

		if (null != id && !"".equals(id)) {

			tbBusinessSpecialBalance = tbBusinessSpecialBalanceService
					.findById(Long.valueOf(id));

			tbBusinessBalance = tbBusinessSpecialBalance.getTbBusinessBalance();

			if (null == tbBusinessBalance) {

				tbBusinessBalance = new TbBusinessBalance();

				TbFixEntrust tbFixEntrust = tbFixEntrustService
						.findById(tbBusinessSpecialBalance.getEntrustId());

				tbBusinessBalance.setTbFixEntrust(tbFixEntrust);

				tbBusinessSpecialBalance
						.setTbBusinessBalance(tbBusinessBalance);
			}

			// 委托书特殊结算
			if (null != tbBusinessSpecialBalance.getTbBusinessBalance()
					.getTbFixEntrust()) {

				TbFixEntrust tbFixEntrust = tbFixEntrustService
						.findById(tbBusinessSpecialBalance
								.getTbBusinessBalance().getTbFixEntrust()
								.getId());

				TbCarInfo tbCarInfo = tbCarInfoService
						.findTbCarInfoById(tbFixEntrust.getTbCarInfo().getId());

				TmUser tmUser_wts = tmUserService.findById(tbFixEntrust
						.getTmUser().getId());

				tbFixEntrust.setTbCarInfo(tbCarInfo);

				tbFixEntrust.setTmUser(tmUser_wts);

				/**
				 * 结算单中工时工位
				 */
				List<TbSpecialWorkingContent> tbSpecialWorkingContentList = tbSpecialWorkingContentService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				ActionContext.getContext().put("tbSpecialWorkingContentList",
						tbSpecialWorkingContentList);

				/**
				 * 结算单中维修发料
				 */
				List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				ActionContext.getContext().put("tbSpecialPartContentList",
						tbSpecialPartContentList);

				// 结算项目
				List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
						.findTmBalanceItemByTmBalanceId(tbFixEntrust
								.getTmFixType().getTmBalance().getId());

				ActionContext.getContext().put("tmBalanceItemList",
						tmBalanceItemList);

				// 结算单中结算项目
				List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

				if (null != tbBusinessSpecialBalanceItemList) {

					for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

						calcMapReturn.put(
								tbBusinessSpecialBalanceItem
										.getBalanceItemCode(),
								new BigDecimal(String
										.valueOf(tbBusinessSpecialBalanceItem
												.getBalanceItemTotal())));

					}

				}

				ActionContext.getContext().getSession()
						.put("calcMapReturn", calcMapReturn);

				return Constants.EDITPAGE;
			} else {

				TmStockOut tmStockOut = tmStockOutService
						.findById(tbBusinessBalance.getTmStockOut().getId());

				TbCustomer customer = tbCustomerService.findById(tmStockOut
						.getCustomerBill());

				request.setAttribute("customer", customer);
				// 默认销售类型
				TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();

				List<TmSoleType> tsyList = new ArrayList<TmSoleType>();

				tsyList.add(tsy);

				request.setAttribute("tsyList", tsyList);

				List<TmBalance> tmBalanceList = tmBalanceService.findAll();

				Long tmBalanceId = tmBalanceList.get(0).getId();

				ActionContext.getContext().put("tmBalanceId", tmBalanceId);

				List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				ActionContext.getContext().put("tbSpecialPartContentList",
						tbSpecialPartContentList);

				// 结算单中结算项目
				List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
						.findBySpecialId(tbBusinessSpecialBalance.getId());

				// 结算项目
				List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService
						.findTmBalanceItemByTmBalanceId(tmBalanceId);

				ActionContext.getContext().put("tmBalanceItemList",
						tmBalanceItemList);

				Map<String, BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();

				if (null != tbBusinessSpecialBalanceItemList) {

					for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

						calcMapReturn.put(
								tbBusinessSpecialBalanceItem
										.getBalanceItemCode(),
								new BigDecimal(String
										.valueOf(tbBusinessSpecialBalanceItem
												.getBalanceItemTotal())));

					}

				}

				ActionContext.getContext().getSession()
						.put("calcMapReturn", calcMapReturn);

				return Constants.OTHERPAGE;
			}
		}
		return null;
	}

	public String printFinanceTbBusinessSpecialBalance() throws Exception {

		String id = request.getParameter("id");

		Map map = tbBusinessSpecialBalanceService
				.putFinanceSpecialBalanceReportParamMap(Long.valueOf(id),
						request);

		ReportFileFromStream.parsePdfFromStream(request, response, map);

		return null;

	}

	public String printWtsTbBusinessSpecialBalance() throws Exception {

		String id = request.getParameter("id");

		Map map = tbBusinessSpecialBalanceService
				.putEntrustSpecialBalanceReportParamMap(Long.valueOf(id),
						request);

		ReportFileFromStream.parsePdfFromStream(request, response, map);

		return null;

	}

	public String printXsdTbBusinessSpecialBalance() throws Exception {

		String id = request.getParameter("id");

		Map map = tbBusinessSpecialBalanceService.putXsdBalanceReportParamMap(
				Long.valueOf(id), request);

		ReportFileFromStream.parsePdfFromStream(request, response, map);

		return null;

	}

	/**
	 * 经营状况统计手工录入跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	public String forwardPageHandleFinance() throws Exception {

		// 修理类型
		ActionContext.getContext().put("tmFixTypeList",
				tmFixTypeService.findAll());
		// 车型
		ActionContext.getContext().put("tmCarModelTypeList",
				tmCarModelTypeService.findAll());

		String id = request.getParameter("id");

		String flag = request.getParameter("flag");

		if (null != id && !"".equals(id)) {

			tbBusinessSpecialBalanceHandleFinance = tbBusinessSpecialBalanceHandleFinanceService
					.findById(Long.valueOf(id));

			String[] staticFixType = tbBusinessSpecialBalanceHandleFinance
					.getStaticFixType().split(",");

			List staticFixTypeList = new ArrayList();

			for (int i = 0; i < staticFixType.length; i++) {

				staticFixTypeList.add(staticFixType[i]);
			}

			ActionContext.getContext().put("staticFixTypeList",
					staticFixTypeList);

			String[] staticCarModelType = tbBusinessSpecialBalanceHandleFinance
					.getStaticCarModelType().split(",");

			List staticCarModelTypeList = new ArrayList();

			for (int i = 0; i < staticCarModelType.length; i++) {

				staticCarModelTypeList.add(staticCarModelType[i]);
			}

			ActionContext.getContext().put("staticCarModelTypeList",
					staticCarModelTypeList);

			return Constants.EDITPAGE;
		}

		if ("view".equals(flag)) {

			return Constants.VIEWPAGE;

		}

		return Constants.ADDPAGE;
	}

	public String findTbBusinessSpecialBalanceHandleFinance() throws Exception {

		List<TbBusinessSpecialBalanceHandleFinance> tbBusinessSpecialBalanceHandleFinanceList = tbBusinessSpecialBalanceHandleFinanceService
				.findByTbBusinessSpecialBalanceHandleFinance(tbBusinessSpecialBalanceHandleFinance);

		List<TbBusinessSpecialBalanceHandleFinance> tbBusinessSpecialBalanceHandleFinanceListShow = null;

		if (null != tbBusinessSpecialBalanceHandleFinanceList
				&& tbBusinessSpecialBalanceHandleFinanceList.size() > 0) {

			tbBusinessSpecialBalanceHandleFinanceListShow = new ArrayList<TbBusinessSpecialBalanceHandleFinance>();

			for (TbBusinessSpecialBalanceHandleFinance t : tbBusinessSpecialBalanceHandleFinanceList) {

				if (null != t.getUserId()) {

					t.setUserRealName(tmUserService.findById(t.getUserId())
							.getUserRealName());
				}

				tbBusinessSpecialBalanceHandleFinanceListShow.add(t);
			}

		}

		ActionContext.getContext().put(
				"tbBusinessSpecialBalanceHandleFinanceList",
				tbBusinessSpecialBalanceHandleFinanceListShow);

		return Constants.SUCCESS;
	}

	public String viewTbBusinessSpecialBalanceHandleFinance() throws Exception {

		// 修理类型
		ActionContext.getContext().put("tmFixTypeList",
				tmFixTypeService.findAll());
		// 车型
		ActionContext.getContext().put("tmCarModelTypeList",
				tmCarModelTypeService.findAll());

		String staticsDateFrom = tbBusinessSpecialBalanceHandleFinance
				.getStaticsDateFrom();

		String staticsDateEnd = tbBusinessSpecialBalanceHandleFinance
				.getStaticsDateEnd();

		List<TbBusinessSpecialBalanceHandleFinance> tbBusinessSpecialBalanceHandleFinanceList = tbBusinessSpecialBalanceHandleFinanceService
				.findByTbBusinessSpecialBalanceHandleFinance(tbBusinessSpecialBalanceHandleFinance);

		if (null != tbBusinessSpecialBalanceHandleFinanceList
				&& tbBusinessSpecialBalanceHandleFinanceList.size() > 0) {

			BigDecimal countAll = new BigDecimal("0");

			BigDecimal numFixSole = new BigDecimal("0");

			BigDecimal numUniSole = new BigDecimal("0");

			BigDecimal unFixHour = new BigDecimal("0");

			BigDecimal hasFixHour = new BigDecimal("0");

			BigDecimal allFixHour = new BigDecimal("0");

			BigDecimal payedFixHour = new BigDecimal("0");

			BigDecimal profitVal = new BigDecimal("0");

			BigDecimal purchaseVal = new BigDecimal("0");

			BigDecimal partCoseVal = new BigDecimal("0");

			BigDecimal numFree = new BigDecimal("0");

			BigDecimal freeCount = new BigDecimal("0");

			BigDecimal numHas = new BigDecimal("0");

			BigDecimal patedCount = new BigDecimal("0");

			BigDecimal oweCount = new BigDecimal("0");

			BigDecimal hasPayFixSole = new BigDecimal("0");

			BigDecimal hasPayFixSolePayed = new BigDecimal("0");

			BigDecimal hasPayUniSole = new BigDecimal("0");

			BigDecimal hasPayUniSolePayed = new BigDecimal("0");

			BigDecimal hasFixFee = new BigDecimal("0");

			BigDecimal hasPartFee = new BigDecimal("0");

			BigDecimal hasSoleFee = new BigDecimal("0");

			BigDecimal hasFeeCount = new BigDecimal("0");

			BigDecimal unPayFixSole = new BigDecimal("0");

			BigDecimal unPayFixSolePayed = new BigDecimal("0");

			BigDecimal unPayFixSoleOwe = new BigDecimal("0");

			BigDecimal unPayUniSole = new BigDecimal("0");

			BigDecimal unPayUniSolePayed = new BigDecimal("0");

			BigDecimal unPayUniSoleOwe = new BigDecimal("0");

			BigDecimal spFixFee = new BigDecimal("0");

			BigDecimal byFixFree = new BigDecimal("0");

			BigDecimal fgFixFee = new BigDecimal("0");

			BigDecimal spPartFee = new BigDecimal("0");

			BigDecimal byPartFee = new BigDecimal("0");

			BigDecimal fgPartFee = new BigDecimal("0");

			BigDecimal spFeeCount = new BigDecimal("0");

			BigDecimal byFeeCount = new BigDecimal("0");

			BigDecimal fgFeeCount = new BigDecimal("0");

			BigDecimal unNumFixSole = new BigDecimal("0");

			BigDecimal unFixFee = new BigDecimal("0");

			BigDecimal unFixPartFee = new BigDecimal("0");

			BigDecimal unNumUniSole = new BigDecimal("0");

			BigDecimal unUniPartFee = new BigDecimal("0");

			BigDecimal unSoleCount = new BigDecimal("0");

			BigDecimal unFeeCount = new BigDecimal("0");

			BigDecimal fixPartCosr = new BigDecimal("0");

			BigDecimal solePartCost = new BigDecimal("0");

			BigDecimal fixPartCount = new BigDecimal("0");

			Integer temp_fix = 0;

			Integer temp_model = 0;

			List staticFixTypeList = new ArrayList();

			List staticCarModelTypeList = new ArrayList();

			for (TbBusinessSpecialBalanceHandleFinance t : tbBusinessSpecialBalanceHandleFinanceList) {

				countAll = countAll.add(new BigDecimal(t.getCountAll()
						.toString()));

				numFixSole = numFixSole.add(new BigDecimal(t.getNumFixSole()
						.toString()));

				numUniSole = numUniSole.add(new BigDecimal(t.getNumUniSole()
						.toString()));

				unFixHour = unFixHour.add(new BigDecimal(t.getUnFixHour()
						.toString()));

				hasFixHour = hasFixHour.add(new BigDecimal(t.getHasFixHour()
						.toString()));

				allFixHour = allFixHour.add(new BigDecimal(t.getAllFixHour()
						.toString()));

				payedFixHour = payedFixHour.add(new BigDecimal(t
						.getPayedFixHour().toString()));

				profitVal = profitVal.add(new BigDecimal(t.getProfitVal()
						.toString()));

				purchaseVal = purchaseVal.add(new BigDecimal(t.getPurchaseVal()
						.toString()));

				partCoseVal = partCoseVal.add(new BigDecimal(t.getPartCoseVal()
						.toString()));

				numFree = numFree
						.add(new BigDecimal(t.getNumFree().toString()));

				freeCount = freeCount.add(new BigDecimal(t.getFreeCount()
						.toString()));

				numHas = numHas.add(new BigDecimal(t.getNumHas().toString()));

				patedCount = patedCount.add(new BigDecimal(t.getPatedCount()
						.toString()));

				oweCount = oweCount.add(new BigDecimal(t.getOweCount()
						.toString()));

				hasPayFixSole = hasPayFixSole.add(new BigDecimal(t
						.getHasPayFixSole().toString()));

				hasPayFixSolePayed = hasPayFixSolePayed.add(new BigDecimal(t
						.getHasPayFixSolePayed().toString()));

				hasPayUniSole = hasPayUniSole.add(new BigDecimal(t
						.getHasPayUniSole().toString()));

				hasPayUniSolePayed = hasPayUniSolePayed.add(new BigDecimal(t
						.getHasPayUniSolePayed().toString()));

				hasFixFee = hasFixFee.add(new BigDecimal(t.getHasFixFee()
						.toString()));

				hasPartFee = hasPartFee.add(new BigDecimal(t.getHasPartFee()
						.toString()));

				hasSoleFee = hasSoleFee.add(new BigDecimal(t.getHasSoleFee()
						.toString()));

				hasFeeCount = hasFeeCount.add(new BigDecimal(t.getHasFeeCount()
						.toString()));

				unPayFixSole = unPayFixSole.add(new BigDecimal(t
						.getUnPayFixSole().toString()));

				unPayFixSolePayed = unPayFixSolePayed.add(new BigDecimal(t
						.getUnPayFixSolePayed().toString()));

				unPayFixSoleOwe = unPayFixSoleOwe.add(new BigDecimal(t
						.getUnPayFixSoleOwe().toString()));

				unPayUniSole = unPayUniSole.add(new BigDecimal(t
						.getUnPayUniSole().toString()));

				unPayUniSolePayed = unPayUniSolePayed.add(new BigDecimal(t
						.getUnPayUniSolePayed().toString()));

				unPayUniSoleOwe = unPayUniSoleOwe.add(new BigDecimal(t
						.getUnPayUniSoleOwe().toString()));

				spFixFee = spFixFee.add(new BigDecimal(t.getSpFixFee()
						.toString()));

				byFixFree = byFixFree.add(new BigDecimal(t.getByFixFree()
						.toString()));

				fgFixFee = fgFixFee.add(new BigDecimal(t.getFgFixFee()
						.toString()));

				spPartFee = spPartFee.add(new BigDecimal(t.getSpPartFee()
						.toString()));

				byPartFee = byPartFee.add(new BigDecimal(t.getByPartFee()
						.toString()));

				fgPartFee = fgPartFee.add(new BigDecimal(t.getFgPartFee()
						.toString()));

				spFeeCount = spFeeCount.add(new BigDecimal(t.getSpFeeCount()
						.toString()));

				byFeeCount = byFeeCount.add(new BigDecimal(t.getByFeeCount()
						.toString()));

				fgFeeCount = fgFeeCount.add(new BigDecimal(t.getFgFeeCount()
						.toString()));

				unNumFixSole = unNumFixSole.add(new BigDecimal(t
						.getUnNumFixSole().toString()));

				unFixFee = unFixFee.add(new BigDecimal(t.getUnFixFee()
						.toString()));

				unFixPartFee = unFixPartFee.add(new BigDecimal(t
						.getUnFixPartFee().toString()));

				unNumUniSole = unNumUniSole.add(new BigDecimal(t
						.getUnNumUniSole().toString()));

				unUniPartFee = unUniPartFee.add(new BigDecimal(t
						.getUnUniPartFee().toString()));

				unSoleCount = unSoleCount.add(new BigDecimal(t.getUnSoleCount()
						.toString()));

				unFeeCount = unFeeCount.add(new BigDecimal(t.getUnFeeCount()
						.toString()));

				fixPartCosr = fixPartCosr.add(new BigDecimal(t.getFixPartCosr()
						.toString()));

				solePartCost = solePartCost.add(new BigDecimal(t
						.getSolePartCost().toString()));

				fixPartCount = fixPartCount.add(new BigDecimal(t
						.getFixPartCount().toString()));

				String[] staticFixType = t.getStaticFixType().split(",");

				String[] staticCarModelType = t.getStaticCarModelType().split(
						",");

				if (tbBusinessSpecialBalanceHandleFinanceList.size() == 1) {

					for (int k = 0; k < staticFixType.length; k++) {

						temp_fix = Integer.valueOf(staticFixType[k]);

						staticFixTypeList.add(temp_fix);

					}

					for (int m = 0; m < staticCarModelType.length; m++) {

						temp_model = Integer.valueOf(staticCarModelType[m]);

						staticCarModelTypeList.add(temp_model);

					}

					ActionContext.getContext().put("staticFixTypeList",
							staticFixTypeList);

					ActionContext.getContext().put("staticCarModelTypeList",
							staticCarModelTypeList);
				}

				else {

					for (int k = 0; k < staticFixType.length; k++) {

						staticFixTypeList.add(staticFixType[k]);

					}

					for (int h = 0; h < staticCarModelType.length; h++) {

						staticCarModelTypeList.add(staticCarModelType[h]);

					}

				}

			}

			int fix_length = tmFixTypeService.findAll().size();

			int model_length = tmCarModelTypeService.findAll().size();

			List staticFixTypeList2 = new ArrayList();

			List staticCarModelTypeList2 = new ArrayList();

			for (int c = 0; c < staticFixTypeList.size(); c++) {

				int temp_add = Integer.valueOf(staticFixTypeList.get(c) + "");

				for (int d = c + fix_length; d < staticFixTypeList.size(); d = d
						+ fix_length) {

					temp_add += Integer.valueOf(staticFixTypeList.get(d) + "");

				}

				staticFixTypeList2.add(temp_add);

			}

			for (int c = 0; c < staticCarModelTypeList.size(); c++) {

				int temp_add = Integer.valueOf(staticCarModelTypeList.get(c)
						+ "");

				for (int d = c + model_length; d < staticCarModelTypeList
						.size(); d = d + model_length) {

					temp_add += Integer.valueOf(staticCarModelTypeList.get(d)
							+ "");

				}

				staticCarModelTypeList2.add(temp_add);

			}

			tbBusinessSpecialBalanceHandleFinance.setCountAll(countAll
					.intValue());

			tbBusinessSpecialBalanceHandleFinance.setNumFixSole(numFixSole
					.intValue());

			tbBusinessSpecialBalanceHandleFinance.setNumUniSole(numUniSole
					.intValue());

			tbBusinessSpecialBalanceHandleFinance.setUnFixHour(unFixHour
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setHasFixHour(hasFixHour
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setAllFixHour(allFixHour
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setPayedFixHour(payedFixHour
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setProfitVal(profitVal
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setPurchaseVal(purchaseVal
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setPartCoseVal(partCoseVal
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setNumFree(numFree.intValue());

			tbBusinessSpecialBalanceHandleFinance.setFreeCount(freeCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setNumHas(numHas.intValue());

			tbBusinessSpecialBalanceHandleFinance.setPatedCount(patedCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setOweCount(oweCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setHasPayFixSole(hasPayFixSole.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setHasPayFixSolePayed(hasPayFixSolePayed.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setHasPayUniSole(hasPayUniSole.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setHasPayUniSolePayed(hasPayUniSolePayed.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setHasFixFee(hasFixFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setHasPartFee(hasPartFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setHasSoleFee(hasSoleFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setHasFeeCount(hasFeeCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setUnPayFixSole(unPayFixSole
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setUnPayFixSolePayed(unPayFixSolePayed.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setUnPayFixSoleOwe(unPayFixSoleOwe.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setUnPayUniSole(unPayUniSole
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setUnPayUniSolePayed(unPayUniSolePayed.doubleValue());

			tbBusinessSpecialBalanceHandleFinance
					.setUnPayUniSoleOwe(unPayUniSoleOwe.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setSpFixFee(spFixFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setByFixFree(byFixFree
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setFgFixFee(fgFixFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setSpPartFee(spPartFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setByPartFee(byPartFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setFgPartFee(fgPartFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setSpFeeCount(spFeeCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setByFeeCount(byFeeCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setFgFeeCount(fgFeeCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setUnNumFixSole(unNumFixSole
					.intValue());

			tbBusinessSpecialBalanceHandleFinance.setUnFixFee(unFixFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setUnFixPartFee(unFixPartFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setUnNumUniSole(unNumUniSole
					.intValue());

			tbBusinessSpecialBalanceHandleFinance.setUnUniPartFee(unUniPartFee
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setUnSoleCount(unSoleCount
					.intValue());

			tbBusinessSpecialBalanceHandleFinance.setUnFeeCount(unFeeCount
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setFixPartCosr(fixPartCosr
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setSolePartCost(solePartCost
					.doubleValue());

			tbBusinessSpecialBalanceHandleFinance.setFixPartCount(fixPartCount
					.doubleValue());

			ActionContext.getContext().put("staticFixTypeList",
					staticFixTypeList2);

			ActionContext.getContext().put("staticCarModelTypeList",
					staticCarModelTypeList2);

		}

		tbBusinessSpecialBalanceHandleFinance
				.setStaticsDateFrom(staticsDateFrom);

		tbBusinessSpecialBalanceHandleFinance.setStaticsDateEnd(staticsDateEnd);

		return Constants.SUCCESS;
	}

	public String updateTbBusinessSpecialBalanceHandleFinance()
			throws Exception {

		String staticFixType = "";

		String staticCarModelType = "";

		for (int i = 0;; i++) {

			String fixType = request.getParameter("tmFixType" + i);

			if (null == fixType || "".equals(fixType)) {
				break;
			}

			staticFixType += fixType + ",";

		}

		for (int j = 0;; j++) {

			String modelCode = request.getParameter("tmCarModelType" + j);

			if (null == modelCode || "".equals(modelCode)) {
				break;
			}

			staticCarModelType += modelCode + ",";

		}

		tbBusinessSpecialBalanceHandleFinance.setStaticFixType(staticFixType);

		tbBusinessSpecialBalanceHandleFinance
				.setStaticCarModelType(staticCarModelType);

		tbBusinessSpecialBalanceHandleFinanceService
				.updateTbBusinessSpecialBalanceHandleFinance(tbBusinessSpecialBalanceHandleFinance);

		return Constants.SUCCESS;
	}

	public String createTbBusinessSpecialBalanceHandleFinance()
			throws Exception {

		String staticFixType = "";

		String staticCarModelType = "";

		for (int i = 0;; i++) {

			String fixType = request.getParameter("tmFixType" + i);

			if (null == fixType || "".equals(fixType)) {
				break;
			}

			staticFixType += fixType + ",";

		}

		for (int j = 0;; j++) {

			String modelCode = request.getParameter("tmCarModelType" + j);

			if (null == modelCode || "".equals(modelCode)) {
				break;
			}

			staticCarModelType += modelCode + ",";

		}

		tbBusinessSpecialBalanceHandleFinance.setStaticFixType(staticFixType);

		tbBusinessSpecialBalanceHandleFinance
				.setStaticCarModelType(staticCarModelType);

		if (null != tbBusinessSpecialBalanceHandleFinanceService
				.findByStaticsDate(tbBusinessSpecialBalanceHandleFinance
						.getStaticsDate())) {

			ActionContext.getContext().put(
					"msg",
					tbBusinessSpecialBalanceHandleFinance.getStaticsDate()
							+ " 已经存在数据");

			return Constants.FAILURE;

		}

		tbBusinessSpecialBalanceHandleFinanceService
				.insertTbBusinessSpecialBalanceHandleFinance(tbBusinessSpecialBalanceHandleFinance);

		return Constants.SUCCESS;
	}

	public String deleteTbBusinessSpecialBalanceHandleFinance()
			throws Exception {

		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbBusinessSpecialBalanceHandleFinanceService
					.deleteByID(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(
						"tbBusinessSpecialBalanceHandleFinanceTable,"
								+ Constants.SUCCESS);
			} else {
				response.getWriter().print(
						"tbBusinessSpecialBalanceHandleFinanceTable,"
								+ Constants.FAILURE);
			}
		} else {
			response.getWriter().print(
					"tbBusinessSpecialBalanceHandleFinanceTable,"
							+ Constants.EXCEPTION);
		}

		return null;
	}

	public void printTbBusinessSpecialBalanceCdjd() throws Exception {

		String id = request.getParameter("id");

		String companyName = request.getParameter("companyName");

		tbBusinessSpecialBalance = tbBusinessSpecialBalanceService
				.findById(Long.valueOf(id));
		

		TbFixEntrust tbFixEntrust = null;
		if (null != tbBusinessSpecialBalance.getTbBusinessBalance()) {
			tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessSpecialBalance.getTbBusinessBalance()
							.getTbFixEntrust().getId());
		} else {
			tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessSpecialBalance.getEntrustId());

		}

		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename="
				+ tbFixEntrust.getEntrustCode() + ".xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();

		tbBusinessSpecialBalanceService.printTbBusinessBalanceTemplate(os,
				"/cdjd_jsd.xls", Long.valueOf(tbBusinessSpecialBalance.getId()), companyName);

		os.flush();

		os.close();

	}
}
