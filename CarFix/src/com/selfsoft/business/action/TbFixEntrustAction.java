package com.selfsoft.business.action;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmProjectType;
import com.selfsoft.baseparameter.model.TmWorkingHourPrice;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.baseparameter.service.ITmWorkingHourPriceService;
import com.selfsoft.business.model.TbBespokePartContent;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.business.service.ITbBespokePartContentService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbFixShareService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.impl.TbBespokePartContentServiceImpl;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.file.ReportFileFromStream;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TbFixEntrustAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -6047857232408510818L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITbFixEntrustService tbFixEntrustService;

	private TbFixEntrust tbFixEntrust;

	@Autowired
	private ITmFixTypeService tmFixTypeService;

	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;

	@Autowired
	private ITbCustomerService tbCustomerService;

	@Autowired
	private ITbCarInfoService tbCarInfoService;

	@Autowired
	private ITmWorkingHourPriceService tmWorkingHourPriceService;

	@Autowired
	private ITbFixEntrustContentService tbFixEntrustContentService;

	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;

	@Autowired
	private ITbFixShareService tbFixShareService;

	@Autowired
	private ITmUserService tmUserService;
	
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	@Autowired
	private ITbBespokePartContentService tbBespokePartContentService;
	
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	
	@Autowired
	private ITmStockOutService tmStockOutService;
	
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	
	@Autowired
	private IStatisticsStockInOutService statisticsStockInOutService;
	
	public final String REEDIEPAGE = "reEditPage";
	
	public final String EDITPERSONPAGE = "editPersonPage";
	
	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public String findTbFixEntrust() {
		if(null==tbFixEntrust){
			
			tbFixEntrust = new TbFixEntrust();
			
			tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
			
			tbFixEntrust.setFixDateStart(CommonMethod.parseStringToDate(CommonMethod.parseDateToString(CommonMethod.addDate(new Date(), -14), "yyyy-MM-dd"), "yyyy-MM-dd"));
			
			tbFixEntrust.setFixDateEnd(CommonMethod.parseStringToDate(CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd"));
		}
		
		String wjg = request.getParameter("wjg");
		
		tbFixEntrust.setWjg(wjg);
		
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService
				.findByTbFixEntrust(tbFixEntrust);
		
		List<TbFixEntrust> tbFixEntrustListPage = new ArrayList<TbFixEntrust>();
		
		BigDecimal pjcbTotal = new BigDecimal("0.00");
		
		if(null!=tbFixEntrustList&&tbFixEntrustList.size()>0){
			
			for(TbFixEntrust _tbFixEntrust : tbFixEntrustList){
				
				_tbFixEntrust.setFixHourTotal(new BigDecimal(tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustId(_tbFixEntrust.getId())).setScale(2,BigDecimal.ROUND_HALF_UP));
				
				List<TbMaintainPartContent> tcList =  tbMaintainPartContentService.getViewEntrustMaintianContent(_tbFixEntrust.getId());
				
				if(null!=tcList&&tcList.size()>0){
					
					_tbFixEntrust.setStockOutPartTotal(new BigDecimal(tcList.get(0).getTotalPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
					
				}
				
				
				_tbFixEntrust.setPjcb(new BigDecimal(statisticsStockInOutService.sumStockDetailByEntrustId(_tbFixEntrust.getId())).divide(
						new BigDecimal("1.00"), 2,
						BigDecimal.ROUND_HALF_UP));
				
				pjcbTotal = pjcbTotal.add(_tbFixEntrust.getPjcb());
				
				_tbFixEntrust.setSolePartTotal(new BigDecimal(tmStockOutService.getTotalPriceByEntrustCode(_tbFixEntrust.getEntrustCode())).setScale(2,BigDecimal.ROUND_HALF_UP));
				
				_tbFixEntrust.setAllTotal(_tbFixEntrust.getFixHourTotal().add(_tbFixEntrust.getStockOutPartTotal().add(_tbFixEntrust.getSolePartTotal())));
				
				tbFixEntrustListPage.add(_tbFixEntrust);
				
			}
			
		}
		
		ActionContext.getContext().put("pjcbTotal", pjcbTotal.divide(
				new BigDecimal("1.00"), 2,
				BigDecimal.ROUND_HALF_UP));

		ActionContext.getContext().put("tbFixEntrustList", tbFixEntrustListPage);

		return Constants.SUCCESS;
	}
	
	public String findTbFixEntrustNotValid() {
		
		if(null==tbFixEntrust){
			
			tbFixEntrust = new TbFixEntrust();
			
			tbFixEntrust.setIsvalid(Constants.NOTVALIDVALUE);
		}
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService
				.findByTbFixEntrust(tbFixEntrust);

		ActionContext.getContext().put("tbFixEntrustList", tbFixEntrustList);

		return Constants.SUCCESS;
	}

	private TbFixEntrust parseTbFixEntrustInfo(Integer count) {

		Set tbFixEntrustContents = new LinkedHashSet();

		for (int i = 0; i < count.intValue(); i++) {
			
			String tbFixEntrustContentId = request.getParameter("tbFixEntrustContentId"+i);
			
			if(null!=tbFixEntrustContentId&&!"".equals(tbFixEntrustContentId)){
				continue;
			}
			
			String tbWorkingInfoId = request
					.getParameter("tbWorkingInfoId" + i);
			
			String stationName = request.getParameter("stationName" + i);

			String freeSymbol = request.getParameter("freeSymbol" + i);

			String fixHour = request.getParameter("fixHour" + i);

			String workingHourTotal = request.getParameter("workingHourTotal"
					+ i);

			String sendHour = request.getParameter("sendHour" + i);

			String fixPersonIds = request.getParameter("fixPersonIds" + i);

			String fixPersons = request.getParameter("fixPersons" + i);
			
			String projectType = request.getParameter("projectType" + i);
			
			String wxlx = request.getParameter("wxlx" + i);
			
			String pv = "";
			
			String zl = "";
			
			String xmlx = "";
			
			if(null != projectType && !"".equals(projectType)){
				
				TmProjectType tp = tmProjectTypeService.findById(Long.valueOf(projectType));
				
				pv = tp.getProjectType();
				
				zl = tp.getZl();
				
				xmlx = tp.getXmlx();
				
			}
			
			//add the balanceId 2010-12-19
			String balanceId = request.getParameter("balanceId" + i);

			if (null == tbWorkingInfoId || "".equals(tbWorkingInfoId)) {
				continue;
			}

			TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();

			tbWorkingInfo.setId(Long.valueOf(tbWorkingInfoId));

			TbFixEntrustContent tbFixEntrustContent = new TbFixEntrustContent();

			tbFixEntrustContent.setTbWorkingInfo(tbWorkingInfo);
			
			tbFixEntrustContent.setStationName(stationName);

			tbFixEntrustContent.setFixHour(Double.valueOf(fixHour));

			tbFixEntrustContent.setWorkingHourPrice(tbFixEntrust
					.getWorkingHourPrice());

			if(null!=workingHourTotal&&!"".equals(workingHourTotal)){
				
				tbFixEntrustContent.setFixHourAll(Double.valueOf(workingHourTotal));
				
			}
			else{
				
				tbFixEntrustContent.setFixHourAll(0.00d);
				
			}
			
			
			if(null!=sendHour&&!"".equals(sendHour)){
				
				tbFixEntrustContent.setSendHour(Double.valueOf(sendHour));
				
			}
			else{
				
				tbFixEntrustContent.setSendHour(0.00d);
			}
			
			

			tbFixEntrustContent.setFreesymbol(Long.valueOf(freeSymbol));
			
			tbFixEntrustContent.setBalanceId(((balanceId == null|| "".equals(balanceId) || "null".equals(balanceId))?null:Long.valueOf(balanceId)));

			tbFixEntrustContent.setProjectType(pv);
			
			tbFixEntrustContent.setZl(zl);
			
			tbFixEntrustContent.setXmlx(xmlx);
			
			tbFixEntrustContent.setWxlx(wxlx);
			
			if(null!=fixPersonIds&&!"".equals(fixPersonIds)){
				
				String[] fixPersonIdsArray = fixPersonIds.split(",");

				Set tbFixShares = new HashSet();

				for (int j = 0; j < fixPersonIdsArray.length; j++) {

					String[] fixPersonInfo = fixPersonIdsArray[j].split("-");

					TbFixShare tbFixShare = new TbFixShare();

					TmUser tmUser = new TmUser();

					tmUser.setId(Long.valueOf(fixPersonInfo[0]));

					tbFixShare.setTmUser(tmUser);

					tbFixShare.setSendHour(Double.valueOf(fixPersonInfo[1]));

					tbFixShares.add(tbFixShare);
				}
				
				tbFixEntrustContent.setTbFixShares(tbFixShares);

			}
			
			tbFixEntrustContents.add(tbFixEntrustContent);

			tbFixEntrust.setTbFixEntrustContents(tbFixEntrustContents);

		}

		return tbFixEntrust;
	}

	public String insertTbFixEntrust() throws Exception {

		Integer count = Integer.valueOf(request.getParameter("count"));
		String partCol = request.getParameter("partCol");
		String totalPrice = request.getParameter("totalPrice");

		if (null != count) {

			if(null==tbFixEntrust.getTmUser()||null==tbFixEntrust.getTmUser().getId()){
				
				ActionContext.getContext().put("msg","登陆超时,请重新登陆");
				
				return Constants.FAILURE;
			}
			
			tbFixEntrust = this.parseTbFixEntrustInfo(count);

			String entrustCode = tmDictionaryService.GenerateCode(StockTypeElements.TBENTRUST.getElementString());
			
			tbFixEntrust.setEntrustCode(entrustCode);
			
			ActionContext.getContext().put("msg","生成委托书号:" + entrustCode);
			
			tbFixEntrustService.insertAll(tbFixEntrust,partCol,new Double(totalPrice));
		} else {

			return Constants.FAILURE;
		}

		return Constants.SUCCESS;
	}

	public String updateTbFixEntrust() throws Exception {

		Integer count = Integer.valueOf(request.getParameter("count"));
		String partCol = request.getParameter("partCol");
		String totalPrice = request.getParameter("totalPrice");

		if (null != count) {

			tbFixEntrust = this.parseTbFixEntrustInfo(count);

			tbFixEntrustService.updateAll(tbFixEntrust,partCol,new Double(totalPrice));

		} else {

			return Constants.FAILURE;
		}

		return Constants.SUCCESS;
	}

	public String updateTbFixEntrustReBalance() throws Exception{
		
		Integer count = Integer.valueOf(request.getParameter("count"));
		
		if (null != count) {

			tbFixEntrust = this.parseTbFixEntrustInfo(count);

			tbFixEntrustService.updateTbFixEntrustReBalance(tbFixEntrust);

		} else {

			return Constants.FAILURE;
		}

		return Constants.SUCCESS;
	}
	
	public String deleteTbFixEntrust() throws Exception {
		
		String id = request.getParameter("id");
		
		String flagD = request.getParameter("flag");

		if (null != id && !"".equals(id)) {

			if("qz".equals(flagD)){
			
				boolean flag = tbFixEntrustService.updateTbFixEntrustNotValid(Long
						.valueOf(id));
				// 回传时字符格式为 E3表的id,操作标示
				if (flag) {
					response.getWriter().print(
							"tbFixEntrustTable," + Constants.SUCCESS);
				} else {
					response.getWriter().print(
							"tbFixEntrustTable," + Constants.FAILURE);
				}
				
				return null;
				
			}
			
			if(tbFixEntrustService.findById(Long.valueOf(id)).getEntrustStatus()!=0){
				
				response.getWriter().print(
						"tbFixEntrustTable," + Constants.FAILURE);
				
				return null;
			}
			
			boolean flag = tbFixEntrustService.updateTbFixEntrustNotValid(Long
					.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(
						"tbFixEntrustTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print(
						"tbFixEntrustTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print(
					"tbFixEntrustTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {

		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		// 车型
		ActionContext.getContext().put("tmCarModelTypeMap",
				tmCarModelTypeService.findAllTmCarModelTypeMap());
		
		ActionContext.getContext().put("tmProjectTypeList",tmProjectTypeService.findAll());

		String id = request.getParameter("id");

		String flag = request.getParameter("flag");
		
		if (null != id && !"".equals(id)) {

			tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));

			TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
					.getTbCustomer().getId());

			TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
					.getTbCarInfo().getId());

			tbFixEntrust.setTbCustomer(tbCustomer);

			tbFixEntrust.setTbCarInfo(tbCarInfo);

			List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
					.findTbFixEnTrustContentListByTbFixEntrustId(Long
							.valueOf(id));

			List<TbFixEntrustContent> tbFixEntrustContentListEdit = new ArrayList<TbFixEntrustContent>();

			if (null != tbFixEntrustContentList) {

				for (TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList) {

					TbWorkingInfo tbWorkingInfo = tbWorkingInfoService
							.findById(tbFixEntrustContent.getTbWorkingInfo()
									.getId());
					
					tbWorkingInfo.setStationName(tbFixEntrustContent.getStationName());
					//tbWorkingInfo.setWorkName(tbFixEntrustContent.getStationName());
					
					tbWorkingInfo.setFixHour(tbFixEntrustContent.getFixHour());
					
					tbWorkingInfo.setSendHour(tbFixEntrustContent.getSendHour());

					tbFixEntrustContent.setTbWorkingInfo(tbWorkingInfo);

					List<TbFixShare> tbFixShareList = tbFixShareService
							.findTbFixShareListByTbFixEntrustContentId(tbFixEntrustContent
									.getId());

					String fixPersons = "";

					String fixPersonIds = "";
					
					if (null != tbFixShareList && tbFixShareList.size() > 0) {
						
						for (TbFixShare tbFixShare : tbFixShareList) {

							TmUser tmUser = tmUserService.findById(tbFixShare
									.getTmUser().getId());

							fixPersons += tmUser.getUserRealName() + "-"
									+ tbFixShare.getSendHour() + ",";

							fixPersonIds += tmUser.getId() + "-"
									+ tbFixShare.getSendHour() + ",";
						}

						
					}

					tbFixEntrustContent.setFixPersonIds(fixPersonIds);

					tbFixEntrustContent.setFixPersons(fixPersons);
					
					tbFixEntrustContentListEdit.add(tbFixEntrustContent);
				}

			}
			ActionContext.getContext().put("tbFixEntrustContentList",
					tbFixEntrustContentListEdit);

			/**add by bjx**/
			TbBespokePartContent queryEntity = new TbBespokePartContent();
			
			queryEntity.setTbFixEntrust(tbFixEntrust);
			
			List<TbBespokePartContent> partConents = tbBespokePartContentService.findByEntity(queryEntity);
			
			ActionContext.getContext().put("partConents",partConents);
			/**add by bjx**/
			
			if("finish".equals(flag)){
				
				/**
				 * 默认出站里程等于进站里程
				 */
				if(null!=tbFixEntrust.getEnterStationKilo()&&null==tbFixEntrust.getOutStationKilo()){
					
					tbFixEntrust.setOutStationKilo(tbFixEntrust.getEnterStationKilo());
					
					
				}
				
				
				return Constants.OTHERPAGE;
				
			}
			else if("editPerson".equals(flag)){
				
				return EDITPERSONPAGE;
			}
			else if(Constants.REBALANCE.equals(tbFixEntrust.getEntrustStatus())||Constants.NOTMAINTAINREBALANCE.equals(tbFixEntrust.getEntrustStatus())){
				
				return REEDIEPAGE;
			}
			
			return Constants.EDITPAGE;
		}

		tbFixEntrust = new TbFixEntrust();

		tbFixEntrust.setFixDate(new Date());

		List<TmWorkingHourPrice> tmWorkingHourPriceList = tmWorkingHourPriceService
				.findAll();

		if (null != tmWorkingHourPriceList && tmWorkingHourPriceList.size() > 0)

			tbFixEntrust.setWorkingHourPrice(tmWorkingHourPriceList.get(0)
					.getPrice());

		return Constants.ADDPAGE;
	}

	public String viewTbFixEntrust() throws Exception {
		
		String flag = request.getParameter("flag");
		// 修理类型
		ActionContext.getContext().put("tmFixTypeMap",
				tmFixTypeService.findAllTmFixTypeMap());
		// 车型
		ActionContext.getContext().put("tmCarModelTypeMap",
				tmCarModelTypeService.findAllTmCarModelTypeMap());
		
		String id = request.getParameter("id");

		tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));

		TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
				.getTbCustomer().getId());

		TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
				.getTbCarInfo().getId());

		tbFixEntrust.setTbCustomer(tbCustomer);

		tbFixEntrust.setTbCarInfo(tbCarInfo);
/*
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(Long.valueOf(id));

		List<TbFixEntrustContent> tbFixEntrustContentListEdit = new ArrayList<TbFixEntrustContent>();

		if (null != tbFixEntrustContentList) {

			for (TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList) {

				TbWorkingInfo tbWorkingInfo = tbWorkingInfoService
						.findById(tbFixEntrustContent.getTbWorkingInfo()
								.getId());

				tbFixEntrustContent.setTbWorkingInfo(tbWorkingInfo);

				List<TbFixShare> tbFixShareList = tbFixShareService
						.findTbFixShareListByTbFixEntrustContentId(tbFixEntrustContent
								.getId());

				if (null != tbFixShareList && tbFixShareList.size() > 0) {
					String fixPersons = "";

					String fixPersonIds = "";
					for (TbFixShare tbFixShare : tbFixShareList) {

						TmUser tmUser = tmUserService.findById(tbFixShare
								.getTmUser().getId());

						fixPersons += tmUser.getUserRealName() + "-"
								+ tbFixShare.getSendHour() + ",";

						fixPersonIds += tmUser.getId() + "-"
								+ tbFixShare.getSendHour() + ",";
					}

					tbFixEntrustContent.setFixPersonIds(fixPersonIds);

					tbFixEntrustContent.setFixPersons(fixPersons);
				}

				tbFixEntrustContentListEdit.add(tbFixEntrustContent);
			}

		}*/
		if("wxhf".equals(flag)){
			
			String carId = request.getParameter("carId");
			
			String licenseCode = request.getParameter("licenseCode");
			
			String returnVisitId = request.getParameter("returnVisitId");
			
			request.setAttribute("returnVisitId", returnVisitId);
			
			request.setAttribute("carId", carId);
			
			request.setAttribute("licenseCode", licenseCode);
			
			return "wxhf";
			
		}
		
		return Constants.SUCCESS;
	}
	
	public String chooseTbFixEntrust() throws Exception{
		
		if(null==tbFixEntrust){
			
			tbFixEntrust = new TbFixEntrust();
			
			tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
			
			
			tbFixEntrust.setFixDateEnd(CommonMethod.parseStringToDate(CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd"));
		}
		
		tbFixEntrust.setIsFinish(Constants.ISFINISH);
		
		tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
		
		tbFixEntrust.setEntrustStatus(Constants.ISFINISH);
		
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService
		.findByTbFixEntrust(tbFixEntrust);

		ActionContext.getContext().put("tbFixEntrustList", tbFixEntrustList);

		return Constants.SUCCESS;
	}
	
	public String chooseTbFixEntrustSpecial() throws Exception{
		
		if(null==tbFixEntrust){
			
			tbFixEntrust = new TbFixEntrust();
			
			
			//tbFixEntrust.setEntrustCode(CommonMethod.getYear(new Date()) + "" + "" + (CommonMethod.getMonth(new Date()) < 10 ? ("0" + CommonMethod.getMonth(new Date())) : CommonMethod.getMonth(new Date())));
			tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
			
			Date d = new Date();
			
			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");
			
			Date e = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-" + CommonMethod.getMonthDays(CommonMethod.getYear(d), CommonMethod.getMonth(d)), "yyyy-MM-dd");
			
			tbFixEntrust.setFixDateStart(s);
			
			tbFixEntrust.setFixDateEnd(e);
		}
		
		/*tbFixEntrust.setIsFinish(Constants.ISFINISH);
		
		tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
		
		tbFixEntrust.setEntrustStatus(Constants.ISFINISH);
		*/
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService
		.findByTbFixEntrust(tbFixEntrust);

		ActionContext.getContext().put("tbFixEntrustList", tbFixEntrustList);

		return Constants.SUCCESS;
	}
	
	
	public String finishTbFixEntrust() throws Exception{
		
		TbFixEntrust tbFixEntrustUpdate = tbFixEntrustService.findById(tbFixEntrust.getId());
		
		if(Constants.ISFINISH.equals(tbFixEntrustUpdate.getEntrustStatus())){
			
			ActionContext.getContext().put("msg", "该委托书已竣工");
			
			return Constants.FAILURE;
		}
		
		
		tbFixEntrustUpdate.setIsFinish(Constants.ISFINISH);
			
		tbFixEntrustUpdate.setEntrustStatus(Constants.ISFINISH);
			
		tbFixEntrustUpdate.setTransferCarDate(new Date());
			
		tbFixEntrustUpdate.setOutStationKilo(tbFixEntrust.getOutStationKilo());
		
		tbFixEntrustService.update(tbFixEntrustUpdate);
		
		return Constants.SUCCESS;
		
	}
	
	public String selectTbFixEntrust() throws Exception{
		
		//传进 0-未竣工(可以发料) 传进1-已发料    2 -已竣工  3-已经结算 4-在结算 5- 在结算未发料的委托书
		String entrustStatusCollection = request.getParameter("entrustStatusCollection");
		
		//委托书类型  1-维修发料  2-委托书销售
		String entrustType = request.getParameter("entrustType");
		
		
		if(null!=entrustStatusCollection&&!"".equals(entrustStatusCollection))
		{
			tbFixEntrust = new TbFixEntrust();
			
			tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
			
//			tbFixEntrust.setEntrustStatus(Long.valueOf(entrustStatus));
			
			tbFixEntrust.setEntrustStatusCollection(entrustStatusCollection);
			
			tbFixEntrust.setEntrustType(new Long(entrustType));
		}
		
//		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService
//		.findByTbFixEntrust(tbFixEntrust);
		
		/** update by bajx**/
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService.findTbMainFixEntrust(tbFixEntrust);
		ActionContext.getContext().put("tbFixEntrustList", tbFixEntrustList);
		
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			String entrustId = props.split(",")[0];
			String entrustCode = props.split(",")[1];
			String fixDate = props.split(",")[2];
			String fixType = props.split(",")[3];
			String licenseCode = props.split(",")[4];
			String customerId = props.split(",")[5];
			String customerName = props.split(",")[6];
			String customerCode = props.split(",")[7];
			String stationCode = props.split(",")[8];
			
			
			request.setAttribute("entrustId", entrustId);
			request.setAttribute("entrustCode", entrustCode);
			request.setAttribute("fixDate", fixDate);
			request.setAttribute("fixType", fixType);
			request.setAttribute("licenseCode", licenseCode);
			request.setAttribute("customerId", customerId);
			request.setAttribute("customerName", customerName);
			request.setAttribute("customerCode", customerCode);
			request.setAttribute("stationCode", stationCode);
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);

		return Constants.SUCCESS;
	}
	
	public String cancelTbFixEntrustFinish() throws Exception{
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));
			
			if(Constants.ISBALANCE.equals(tbFixEntrust.getEntrustStatus())){
				
				response.getWriter().print(Constants.FAILURE);
				
				return null;
			}
			
			boolean flag = tbFixEntrustService.cancelTbFixEntrustFinish(Long
					.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print(Constants.SUCCESS);
			} else {
				response.getWriter().print(Constants.FAILURE);
			}
		} else {
			response.getWriter().print(Constants.EXCEPTION);
		}
		return null;
	}
	
	
	
	public void getTbFixEntrustInfo() throws IOException{
		
		String entrustCode = request.getParameter("entrustCode");
		
		TbFixEntrust tbFixEntrust = tbFixEntrustService.findByEntrustCode(entrustCode);
		
		response.setCharacterEncoding("utf-8");
		
		if(tbFixEntrust == null){
			
			response.getWriter().print(Constants.FAILURE +",");
			
		}else{
			
			StringBuilder result = new StringBuilder();
			
			result.append(tbFixEntrust.getId()).append(",");
			result.append(tbFixEntrust.getLicenseCode()).append(",");
			result.append(CommonMethod.parseDateToString(tbFixEntrust.getFixDate(), "yyyy-MM-dd HH:mm:ss")).append(",");
			result.append(tbFixEntrust.getTbCustomer().getCustomerName()).append(",");
			result.append(tbFixEntrust.getFixType()).append(",");
			result.append(tbFixEntrust.getStationCode());
			
			
			response.getWriter().print(Constants.SUCCESS +","+ result.toString());
			
		}
	}
	
	public String printTbFixEntrust() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = tbFixEntrustService.putReportParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
		return null;
	}
	
	public String sendPrintTbFixEntrust() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = tbFixEntrustService.putSendPersonReportParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
		return null;
	}
	
	public String statisticsTbFixEntrust() throws Exception{
		
		ActionContext.getContext().put("tmUserMap",tmUserService.findAllTmUserMap());
		
		if(null==tbFixEntrust){
			
			tbFixEntrust = new TbFixEntrust();
			
			tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
			
			Date d = new Date();
			
			Date s = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-01", "yyyy-MM-dd");
			
			Date e = CommonMethod.parseStringToDate(CommonMethod.getYear(d) + "-" + CommonMethod.getMonth(d) + "-" + CommonMethod.getMonthDays(CommonMethod.getYear(d), CommonMethod.getMonth(d)), "yyyy-MM-dd");
			
			tbFixEntrust.setFixDateStart(s);
			
			tbFixEntrust.setFixDateEnd(e);
		}
		
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService
				.findByTbFixEntrust(tbFixEntrust);

		ActionContext.getContext().put("tbFixEntrustList", tbFixEntrustList);
		
		if(null!=tbFixEntrustList&&tbFixEntrustList.size()>0){
			
			BigDecimal d_fixHourAll = new BigDecimal("0.00");
			
			for(TbFixEntrust tbFixEntrust : tbFixEntrustList){
				
				Double fixHourAll = tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustId(tbFixEntrust.getId());
			
				d_fixHourAll = d_fixHourAll.add(new BigDecimal(String.valueOf(fixHourAll==null?0.00d:fixHourAll))).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			ActionContext.getContext().put("fixHourAll", d_fixHourAll);
		}

		//ActionContext.getContext().put("sellPriceAll",new BigDecimal(tbFixEntrustService.getTotalSellPriceByEntrustList(tbFixEntrustList)).setScale(2));
		
		//ActionContext.getContext().put("costPriceAll",new BigDecimal(tbFixEntrustService.getTotalCostPriceByEntrustList(tbFixEntrustList)).setScale(2));
		
		ActionContext.getContext().put("sellPriceAll",tbFixEntrustService.getTotalSellPriceByEntrustList(tbFixEntrustList));
		
		ActionContext.getContext().put("costPriceAll",tbFixEntrustService.getTotalCostPriceByEntrustList(tbFixEntrustList));
		
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = tbFixEntrustService.statisticsTbFixEntrust(tbFixEntrust);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList", statisticsTbFixBusinessVoList);
		
		return Constants.SUCCESS;
		
		
	}
	
	public String analyseTbFixEntrust() throws Exception{
		
		String fixDateStart = request.getParameter("fixDateStart");
		
		String fixDateEnd = request.getParameter("fixDateEnd");
		
		String tmCarModelTypeId = request.getParameter("tmCarModelTypeId");
		
		String format = "yyyy-MM-dd";
		
		tbFixEntrust = new TbFixEntrust();
		
		tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
		
		tbFixEntrust.setFixDateStart_s(fixDateStart);
		
		tbFixEntrust.setFixDateEnd_s(fixDateEnd);
		
		tbFixEntrust.setFixDateStart(CommonMethod.parseStringToDate(fixDateStart, format));
		
		tbFixEntrust.setFixDateEnd(CommonMethod.addDate(CommonMethod.parseStringToDate(fixDateEnd, format),1));
		
		if(null!=tmCarModelTypeId&&!"".equals(tmCarModelTypeId)){
			
			TbCarInfo tbCarInfo = new TbCarInfo();
			
			TmCarModelType tmCarModelType = new TmCarModelType();
			
			tmCarModelType.setId(Long.valueOf(tmCarModelTypeId));
			
			tbCarInfo.setTmCarModelType(tmCarModelType);
			
			tbFixEntrust.setTbCarInfo(tbCarInfo);
			
		}
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = tbFixEntrustService.statisticsTbFixEntrust(tbFixEntrust);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList", statisticsTbFixBusinessVoList);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_FixType = tbFixEntrustService.statisticsFixType(tbFixEntrust);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_FixType", statisticsTbFixBusinessVoList_FixType);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_Content = tbFixEntrustContentService.staticsTbFixEntrustContentShow(tbFixEntrust);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_Content", statisticsTbFixBusinessVoList_Content);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList_soleType = tbFixEntrustService.statisticsBalanceShow(tbFixEntrust);
		
		ActionContext.getContext().put("statisticsTbFixBusinessVoList_soleType", statisticsTbFixBusinessVoList_soleType);
		
		return Constants.SUCCESS;
		
	}
	
	public void printTbEntrustTemplate() throws Exception {
		
		String id = request.getParameter("id");
		
		tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));
		
		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename=" + tbFixEntrust.getEntrustCode()
				 + ".xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();
		
		tbFixEntrustService.printTbFixEntrustTemplate(os, "/cdjd_dfbz.xls", Long.valueOf(id));
		
		os.flush();
		
		os.close();
	}
	
	public void printTbEntrustTemplateBlank() throws Exception {
		
		String id = request.getParameter("id");
		
		tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));
		
		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename=" + tbFixEntrust.getEntrustCode()
				 + ".xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();
		
		tbFixEntrustService.printTbFixEntrustTemplateBlank(os, "/cdjd_dfbz_blank.xls", Long.valueOf(id));
		
		os.flush();
		
		os.close();
	} 
	
	public void printTbEntrustTemplateXTL() throws Exception {
		
		String id = request.getParameter("id");
		
		tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));
		
		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename=" + tbFixEntrust.getEntrustCode()
				 + ".xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();
		
		tbFixEntrustService.printTbFixEntrustTemplateXTL(os, "/cdjd_xtl.xls", Long.valueOf(id));
		
		os.flush();
		
		os.close();
	} 
	
	public void printTbEntrustTemplateBlankXTL() throws Exception {
		
		String id = request.getParameter("id");
		
		tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(id));
		
		response.reset();

		response.setHeader("Content-Disposition", "attachment;filename=" + tbFixEntrust.getEntrustCode()
				 + ".xls");

		response.setContentType("application/vnd.ms-excel");

		OutputStream os = response.getOutputStream();
		
		tbFixEntrustService.printTbFixEntrustTemplateBlankXTL(os, "/cdjd_xtl_blank.xls", Long.valueOf(id));
		
		os.flush();
		
		os.close();
	} 
}
