package com.selfsoft.business.action;

import java.util.List;

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
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.dao.ITmStockinDetailDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.service.ITmRemoveStockService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmDepartmentService;
import com.selfsoft.secrity.service.ITmUserService;
import com.selfsoft.secrity.service.ITmWorkTypeService;

public class CommChooseAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 4964065034898584290L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITmRemoveStockService tmRemoveStockService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmLianceRegisterService tmLianceRegisterService;
	@Autowired
	private ITmLoanRegisterService tmLoanRegisterService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	@Autowired
	private ITmStockinDetailDao tmStockinDetailDao;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	@Autowired
	private ITmPartTypeService tmPartTypeService;	
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmDepartmentService tmDepartmentService;
	@Autowired
	private ITmWorkTypeService tmWorkTypeService;
	
	private TbCustomer tbCustomer;
	
	private TbCarInfo tbCarInfo;
	
	private TbPartInfo tbPartInfo;
	
	private TbFixEntrust tbFixEntrust;
	
	private String properties;
	
	private String types;
	
	private String e3Table;
	
	private TmStockIn tmStockIn;
	
	private TmStoreHouse tmStoreHouse;
	
	private TmPartType tmPartType;
	
	private TmUser tmUser;
	
	public TmPartType getTmPartType() {
		return tmPartType;
	}

	public void setTmPartType(TmPartType tmPartType) {
		this.tmPartType = tmPartType;
	}

	public TmStockIn getTmStockIn() {
		return tmStockIn;
	}

	public void setTmStockIn(TmStockIn tmStockIn) {
		this.tmStockIn = tmStockIn;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getE3Table() {
		return e3Table;
	}

	public void setE3Table(String table) {
		e3Table = table;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	/**
	 * 所有移出单
	 * @return
	 */
	public String findAllRemoveStock(){
		List<TmRemStockVo> remStockVoList = tmRemoveStockService.getAllRemStockVos(Constants.CONFIRM , Constants.NOTVALIDVALUE , null,null);
		request.setAttribute("remStockVoList", remStockVoList);
		String props = request.getParameter("properties");
		String e3Table = request.getParameter("e3Table");
		if(StringUtils.isNotBlank(props)){
			String removeStockId = props.split(",")[0];
			String removeStockBill = props.split(",")[1];
			String removeDate = props.split(",")[2];
			String removeStoreHouseId  =props.split(",")[3];
			
			request.setAttribute("removeStockId", removeStockId);
			request.setAttribute("removeStockBill", removeStockBill);
			request.setAttribute("removeDate", removeDate);
			request.setAttribute("removeStoreHouseId", removeStoreHouseId);
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
			
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有客户（供应商）
	 */
	public String findAllTmTbCustomer(){
//		String types = request.getParameter("types");
//		String e3Table = request.getParameter("e3Table");
		List<TbCustomer> customerList = tbCustomerService.findByTbCustomer(tbCustomer,types);
		request.setAttribute("customerList", customerList);
		request.setAttribute("props", properties);
		
		if(StringUtils.isNotBlank(properties)){
			String customerId = properties.split(",")[0];
			String customerCode = properties.split(",")[1];
			String customerName = properties.split(",")[2];
			
			request.setAttribute("customerId", customerId);
			request.setAttribute("customerCode", customerCode);
			request.setAttribute("customerName", customerName);
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		
		//客户类别MAP
		ActionContext.getContext().put("customerPropertyMap", Constants.getCustomerPropertyMap());
		return Constants.SUCCESS;
	}
	
	
	/**
	 *查询所有销售出库单
	 * @Date      2010-6-9
	 * @Function  
	 * @return
	 */
	public String findAllTmStockOutBySell(){
		List<TmStockOutVo> sellVoList = tmStockOutService.getStockOutVos(Constants.CONFIRM+"",StockTypeElements.SELLSTOCKOUT.getElementValue(),null,null);
		request.setAttribute("sellVoList", sellVoList);
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			for(String p:props.split(",")){
				request.setAttribute(p, p);
			}
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有借进登记
	 * @Date      2010-6-9
	 * @Function  
	 * @return
	 */
	public String findAllTmLianceRegister(){
		List<TmLianceRegVo> lianceRegVos = tmLianceRegisterService.getLianceRegVo(Constants.CONFIRM , Constants.NOT_RETURN ,null,null);
		request.setAttribute("lianceRegVos", lianceRegVos);
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			String lianceRegId = props.split(",")[0];
			String lianceBill = props.split(",")[1];
			String lianceDate = props.split(",")[2];
			
			request.setAttribute("lianceRegId", lianceRegId);
			request.setAttribute("lianceBill", lianceBill);
			request.setAttribute("lianceDate", lianceDate);
		}
		
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有借出登记
	 * @return
	 */
	public String findAllTmLoanRegister(){
		List<TmLoanRegVo> loanRegVos = tmLoanRegisterService.getloanRegVo(Constants.CONFIRM , Constants.NOT_RETURN,null,null);
		request.setAttribute("loanRegVos", loanRegVos);
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			String loanRegId = props.split(",")[0];
			String loanBill = props.split(",")[1];
			String loanDate = props.split(",")[2];
			
			request.setAttribute("loanRegId", loanRegId);
			request.setAttribute("loanBill", loanBill);
			request.setAttribute("loanDate", loanDate);
			
		}
		
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询所有职工
	 * @return
	 */
	public String findAllTmUser(){
		//List<TmUser> tmUsers = tmUserService.findAll();
		request.setAttribute("depts", tmDepartmentService.findAll());
		request.setAttribute("workTypes",tmWorkTypeService.findAll());
		TmUser tmUser = new TmUser();
		tmUser.setUserStatus("正常");
		List<TmUser> tmUsers = tmUserService.findValidUser(tmUser);
		request.setAttribute("tmUsers", tmUsers);
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			String userId = props.split(",")[0];
			String userRealName = props.split(",")[1];
			
			request.setAttribute("userId", userId);
			request.setAttribute("userRealName", userRealName);
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有委托书
	 * @return
	 */
	public String findAllTbFixEntrust(){
		
		String entrustStatus = request.getParameter("entrustStatus");
		
		if(null!=entrustStatus&&!"".equals(entrustStatus))
		{
			tbFixEntrust = new TbFixEntrust();
			
			tbFixEntrust.setIsvalid(Constants.ISVALIDVALUE);
			
			tbFixEntrust.setEntrustStatus(Long.valueOf(entrustStatus));
		}
		
		List<TbFixEntrust> fixEntrusts = tbFixEntrustService.findTbMainFixEntrust(tbFixEntrust);
		request.setAttribute("fixEntrusts", fixEntrusts);
		
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
			String stationCode = props.split(",")[7];
			
			
			request.setAttribute("entrustId", entrustId);
			request.setAttribute("entrustCode", entrustCode);
			request.setAttribute("fixDate", fixDate);
			request.setAttribute("fixType", fixType);
			request.setAttribute("licenseCode", licenseCode);
			request.setAttribute("customerId", customerId);
			request.setAttribute("customerName", customerName);
			request.setAttribute("stationCode", stationCode);
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有车辆
	 * @return
	 */
	public String findAllCarInfo(){
		List<TbCarInfo> carInfoList = tbCarInfoService.findByTbCarInfo(tbCarInfo);
		request.setAttribute("carInfoList", carInfoList);
		
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			for(String p:props.split(",")){
				
				String carInfoId = props.split(",").length > 0?props.split(",")[0]:null;
				String licenseCode = props.split(",").length > 1?props.split(",")[1]:null;
				String factory = props.split(",").length > 2?props.split(",")[2]:null;
				String modelTypeName = props.split(",").length > 3?props.split(",")[3]:null;
				String engineType = props.split(",").length > 4?props.split(",")[4]:null;
				String changeBoxType = props.split(",").length > 5?props.split(",")[5]:null;
				String purchaseDate = props.split(",").length > 6?props.split(",")[6]:null;
				
				request.setAttribute("carInfoId", carInfoId);
				request.setAttribute("licenseCode", licenseCode);
				request.setAttribute("factory", factory);
				request.setAttribute("modelTypeName", modelTypeName);
				request.setAttribute("engineType", engineType);
				request.setAttribute("changeBoxType", changeBoxType);
				request.setAttribute("purchaseDate", purchaseDate);
				
				
			}
		}
		
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询所有采购入库单
	 * @return
	 */
	public String findAllStockInByStock(){
		
		//List<TmStockInVo> stockList = tmStockinDetailDao.getStockInVo(StockTypeElements.STOCK.getElementValue(),8002L,null,this.tmStockIn);
		//EDIT BY CCR
		List<TmStockInVo> stockList = tmStockinDetailDao.getStockInVo(null,8002L,null,this.tmStockIn);
		request.setAttribute("stockList", stockList);
		String types = Constants.SUPPLIERVAL + "," + Constants.CUSTOMERANDSUPPLIERVAL;
		List<TbCustomer> tbCustomers = tbCustomerService.findByTbCustomer(tbCustomer, types);
		
		ActionContext.getContext().put("tbCustomers", tbCustomers);
		
		
		String e3Table = request.getParameter("e3Table");
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			String stockInId = props.split(",")[0];
			String stockInCode = props.split(",")[1];
			String arriveDate = props.split(",")[2];
			String supplierName = props.split(",")[3];
			
			request.setAttribute("stockInId", stockInId);
			request.setAttribute("stockInCode", stockInCode);
			request.setAttribute("arriveDate", arriveDate);
			request.setAttribute("supplierName", supplierName);
		}
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询所有配件列表
	 * @return
	 */
	public String findAllTbPartInfo(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		
		String e3Table = request.getParameter("e3Table");
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		
		return Constants.SUCCESS;
	}
	
	public String findAllTmStoreHouse(){
		
		List<TmStoreHouse> tmStoreHouseList = tmStoreHouseService.findByTmStoreHouse(tmStoreHouse);

		ActionContext.getContext().put("tmStoreHouseList", tmStoreHouseList);
		
		String e3Table = request.getParameter("e3Table");
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询所有配件类型
	 * @return
	 */
	public String findCommTmPartType(){
		List<TmPartType> tmPartTypeList = tmPartTypeService.findTmPartTypeList(tmPartType);
		
		request.setAttribute("tmPartTypeList", tmPartTypeList);
		String props = request.getParameter("properties");
		if(StringUtils.isNotBlank(props)){
			String tmPartTypeId = props.split(",")[0];
			String partTypeName = props.split(",")[1];
			request.setAttribute("tmPartTypeId", tmPartTypeId);
			request.setAttribute("partTypeName", partTypeName);
		}
		String e3Table = request.getParameter("e3Table");
		if(StringUtils.isNotBlank(e3Table))
			request.setAttribute("e3Table", e3Table);
		
		return Constants.SUCCESS;
	}
	
	
	public String findMaintainTmUser() throws Exception {

		request.setAttribute("depts", tmDepartmentService.findAll());
		request.setAttribute("workTypes",tmWorkTypeService.findAll());
		tmUser.setUserStatus("正常");
		List<TmUser> tmUserList = tmUserService.findValidUser(tmUser);

		ActionContext.getContext().put("tmUsers", tmUserList);

		return Constants.SUCCESS;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}
}
