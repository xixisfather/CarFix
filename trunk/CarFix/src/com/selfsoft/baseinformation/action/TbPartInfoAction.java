package com.selfsoft.baseinformation.action;
	/** full constructor */


	// Property accessorspackage com.selfsoft.baseinformation.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbPartCollectionService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseinformation.service.ITbPartSolePriceService;
import com.selfsoft.baseinformation.vo.TbPartInfoVo;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.baseparameter.service.ITmUnitService;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.service.ITbBookFixStationService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.xls.IStockXLSImportService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.UrlParamDecoder;
import com.selfsoft.secrity.service.ITmUserService;

public class TbPartInfoAction extends ActionSupport implements
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
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	@Autowired
	private ITmUnitService tmUnitService;
	@Autowired
	private ITmPartTypeService tmPartTypeService;
	@Autowired
	private ITbPartCollectionService tbPartCollectionService;
	@Autowired
	private ITbPartSolePriceService tbPartSolePriceService;
	@Autowired
	private ITmSoleTypeService tmSoleTypeService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private IStockXLSImportService stockXLSImportService;
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	
	private TbPartInfo tbPartInfo;
	
	private String collectionCode;
	
	private String soleTypes;
	
	private File fileXls;
	
	public File getFileXls() {
		return fileXls;
	}

	public void setFileXls(File fileXls) {
		this.fileXls = fileXls;
	}
	
	public String getSoleTypes() {
		return soleTypes;
	}

	public void setSoleTypes(String soleTypes) {
		this.soleTypes = soleTypes;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}
	/**
	 * 管理配件列表
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public String findTbPartInfo(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		//将List放入SESSION中，导出使用
		ActionContext.getContext().getSession().put("tbPartInfoListSession",tbPartInfoList);
		return Constants.SUCCESS;
		
	}
	/**
	 * 选择配件列表                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public String chooseTbPartInfo(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		//request.setAttribute("sl", request.getParameter("sl"));
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 报溢选择配件列表                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public String chooseTbPartInfoOverFLow(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 借进(出)登记选择配件列表                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public String chooseTbPartInfoLiance(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		request.setAttribute("typeName", UrlParamDecoder.getParam(request, "typeName"));
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 新增配件
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public String addTbPartInfo(){
		//配件代码默认设置为大写
		tbPartInfo.setPartCode(tbPartInfo.getPartCode().toUpperCase());
		tbPartInfo.setStoreQuantity(0D);
		tbPartInfoService.insert(soleTypes,tbPartInfo);
		
		return Constants.SUCCESS;
	}
	/**
	 * 修改配件
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	
	public String updateTbPartInfo(){
		//配件代码默认设置为大写
		tbPartInfo.setPartCode(tbPartInfo.getPartCode().toUpperCase());
		tbPartInfoService.update(soleTypes, tbPartInfo);
		
		return Constants.SUCCESS;
	}
	/**
	 * 删除配件
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 * @throws IOException
	 */
	public String deleteTbPartInfo() throws IOException{
		
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbPartInfoService.deleteById(new Long(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPartInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPartInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPartInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	/**
	 * 配件管理新增修改跳转
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 * @throws Exception
	 */
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");
		
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmUnit> unitList = tmUnitService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		List<TmSoleType> soleTypeList = tmSoleTypeService.findAll();
		
		request.setAttribute("stroeHouseList", stroeHouseList);
		request.setAttribute("carModelTypeList", carModelTypeList);
		request.setAttribute("unitList", unitList);
		request.setAttribute("partTypeList", partTypeList);
		request.setAttribute("soleTypeList", soleTypeList);
		
		if (null != id && !"".equals(id)) {

			tbPartInfo = tbPartInfoService.findById(new Long(id));
			
			Map<TbPartSolePrice, String>  partSolePriceMap = tbPartSolePriceService.getSolePriceMap(new Long(id));
			request.setAttribute("partSolePriceMap", partSolePriceMap);
			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	
	public String getPartInfoByCode(){
		List<TbPartInfo> tpis = tbPartInfoService.getTbPartInfoByCollectionCode(collectionCode);
		request.setAttribute("tbPartInfoList", tpis);
		return Constants.SUCCESS;
		
	}
	
	public String deleteTbPartInfoRelation() throws IOException{
		String partInfoId = request.getParameter("id");
		String collectionCode = request.getParameter("code");
		TbPartCollection tpc = new TbPartCollection();
		tpc.setCollectionCode(collectionCode);
		tpc.setPartInfoId(Long.valueOf(partInfoId));
		List<TbPartCollection> tbList = this.tbPartCollectionService.findByEntity(tpc);
		if(tbList!=null && tbList.size()>0){
			this.tbPartCollectionService.deleteCollectionRelation(tbList.get(0));
			response.getWriter().print("tbPartInfoTable," + Constants.SUCCESS);
		}else{
			response.getWriter().print("tbPartInfoTable," + Constants.FAILURE);
		}
		
		return null;
	}
	
	/**
	 * 选择配件列表(出库)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @Date      2010-6-02
	 * @Function  
	 * @return
	 */
	public String chooseTbPartInfoStockOut(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		request.setAttribute("typeName", UrlParamDecoder.getParam(request, "typeName"));
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 选择配件列表(销售)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @Date      2010-6-02
	 * @Function  
	 * @return
	 */
	public String chooseTbPartInfoSell(){
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmPartType> partTypeList = tmPartTypeService.findAll();
		
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("partTypeList", partTypeList);
		
		String customerId = request.getParameter("customerId");
		List<TbPartInfoVo> tbPartInfoList = tbPartInfoService.getTbPartInfoVoByCustomerId(tbPartInfo,customerId);
		request.setAttribute("tbPartInfoList", tbPartInfoList);
//			List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
//			request.setAttribute("tbPartInfoList", tbPartInfoList);
		
		request.setAttribute("typeName", UrlParamDecoder.getParam(request, "typeName"));
		request.setAttribute("freeFlagTypes", tmStockOutService.getAllFreeFlagType());
		request.setAttribute("zlMap", Constants.zlMap());
		request.setAttribute("xmlxMap", Constants.xmlxMap());
		request.setAttribute("tmprojectTypes", tmProjectTypeService.findAll());
		return Constants.SUCCESS;
		
	}
	
	public String chooseTbPartInfoRemStock(){
		String storeId = request.getParameter("storeId");
		
		TbPartInfo tpi = new TbPartInfo();
		if(StringUtils.isNotBlank(storeId)){
			TmStoreHouse sh = new TmStoreHouse();
			sh.setId(new Long(storeId));
			tpi.setTmStoreHouse(sh);
		}
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tpi);
		
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 
	 * the follows added by ccr
	 */
	
	@Autowired
	private ITmFixTypeService tmFixTypeService;

	@Autowired
	private ITmUserService tmUserService;
	
	@Autowired
	private ITbBookFixStationService tbBookFixStationService;
	
	private TbBook tbBook;
	
	public TbBook getTbBook() {
		return tbBook;
	}

	public void setTbBook(TbBook tbBook) {
		this.tbBook = tbBook;
	}
	
	
	public String selectTbPartInfo() throws Exception{
		
		//修理类型
		ActionContext.getContext().put("tmFixTypeMap",tmFixTypeService.findAllTmFixTypeMap());
		//车型
		ActionContext.getContext().put("tmCarModelTypeMap",tmCarModelTypeService.findAllTmCarModelTypeMap());
		//服务顾问
		ActionContext.getContext().put("tmUserMap",tmUserService.findAllTmUserMap());
		//免费标识
		ActionContext.getContext().put("freeSymbolMap", Constants.getFreeSymbolMap());
		//仓库LIST
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		//处理方式
		ActionContext.getContext().put("dealTypeMap", Constants.getDealTypeMap());
		//选中工位工时
		Map<String,String> tbBookFixStationMap = new LinkedHashMap<String, String>();
		//选中修理配件
		Map<String,String> tbBookFixPartMap = new LinkedHashMap<String, String>();
		
		if(null!=tbBook){
			tbBookFixStationMap = tbBookFixStationService.findTbBookFixStationMapByTbBookId(tbBook.getId());
		}
			
		ActionContext.getContext().put("tbBookFixStationMap", tbBookFixStationMap);
		ActionContext.getContext().put("tbBookFixPartMap", tbBookFixPartMap);
		
		List<TbPartInfo> tbPartInfoList = new ArrayList<TbPartInfo>();
		
		if(null!=tbPartInfo&&null!=tbPartInfo.getTmCarModelType()&&null!=tbPartInfo.getTmCarModelType().getId()){
			
			tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		}
		
		ActionContext.getContext().put("tbPartInfoList", tbPartInfoList);
		
		return Constants.SUCCESS;
	}
	
	
	public String updateCostPrice() throws IOException{

		boolean flag = tbPartInfoService.updateCostPrice(this.tbPartInfo.getId(), this.getTbPartInfo().getCostPrice());


		// 回传时字符格式为 E3表的id,操作标示
		if (flag) {
			return Constants.SUCCESS;
		}	
		return Constants.FAILURE;
		
	}
	
	public String initUpdateCostPircePage(){
		String id = request.getParameter("id");
		
		tbPartInfo = tbPartInfoService.findById(new Long(id));
		
		return Constants.SUCCESS;
	}
	
	/**
	 * ajax新增配件
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public void addTbPartInfoAjax(){
		//配件代码默认设置为大写
		tbPartInfo.setPartCode(tbPartInfo.getPartCode().toUpperCase());
		tbPartInfo.setStoreQuantity(0D);
		tbPartInfoService.insert(soleTypes,tbPartInfo);
		
	}
	
	
	
	/**
	 * 初始化配件信息xls导入页面
	 * @return
	 * @throws Exception
	 */
	public String initPjxxImportXlsPage() throws Exception{
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件信息xls导入
	 * @return
	 * @throws Exception
	 */
	public String pjxxImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		String flag = stockXLSImportService.pjxxImportXls(in, "/stockxlsimport/TbPartInfoImportVo_import_xls_tpl.properties");
		
		if(null!=flag){
			
			String xlsMessage[] = flag.split("-");
			List<String> xlsMessageList = null;
			if("success".equals(xlsMessage[0])){
				
				String[] successMessage = xlsMessage[1].split(",");
				
				
				ActionContext.getContext().put("xlsMessage", successMessage[0]);
				
				if(successMessage.length==2){
					
					xlsMessageList = new ArrayList<String>();
					
					String[] repeatMessage = successMessage[1].split(";");
					
					for(int i = 0 ; i < repeatMessage.length ; i++){
						xlsMessageList.add(repeatMessage[i]);
					}
					ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				}
				return Constants.SUCCESS;
			}
			else if("failError".equals(xlsMessage[0])){
				xlsMessageList = new ArrayList<String>();
				String[] errorMessage = xlsMessage[1].split(",");
				for(int i = 0 ; i < errorMessage.length ; i++){
					xlsMessageList.add(errorMessage[i]);
				}
				ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				return Constants.FAILURE;
			}
			else{
				ActionContext.getContext().put("xlsMessage", xlsMessage[1]);
				return Constants.FAILURE;
			}
			
			
		}
		
		ActionContext.getContext().put("xlsMessage", "文件中数据类型不符合要求");
		
		return Constants.FAILURE;
		
	}
	
	
	/**
	 * 配件信息导出
	 * @return
	 * @throws Exception
	 */
	public String pjxxExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "配件信息";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();

		List<TbPartInfo> tbPartInfoList = (List<TbPartInfo>)ActionContext.getContext().getSession().get("tbPartInfoListSession");
		
//		tbWorkingInfoService.tbWorkingInfoExportXls(os, "/tbWorkingInfo_export_xls_tpl.properties", tbWorkingInfoList);
		
		stockXLSImportService.pjxxExportXls(os, "/stockxlsimport/TbPartInfo_export_xls_tpl.properties", tbPartInfoList);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	
}	
