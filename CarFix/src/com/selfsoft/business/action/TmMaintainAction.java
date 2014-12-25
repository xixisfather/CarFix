package com.selfsoft.business.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;

public class TmMaintainAction extends ActionSupport implements
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
	
	
	
	private Long tbFixEntrustId;
	
	private TbPartInfo tbPartInfo;
	
	private Double totalPrice;
	
	private Long entrustId;
	
	private String partCol;
	
	private Long partId;
	
	private TbMaintainPartContent tbMaintainPartContent;
	
	
	public TbMaintainPartContent getTbMaintainPartContent() {
		return tbMaintainPartContent;
	}

	public void setTbMaintainPartContent(TbMaintainPartContent tbMaintainPartContent) {
		this.tbMaintainPartContent = tbMaintainPartContent;
	}

	public String getPartCol() {
		return partCol;
	}

	public void setPartCol(String partCol) {
		this.partCol = partCol;
	}

	public Long getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(Long entrustId) {
		this.entrustId = entrustId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}
	
	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}
	
	@Autowired
	private ITbFixEntrustContentService tbFixEntrustContentService;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	@Autowired
	private ITmPartTypeService tmPartTypeService;	
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	/**
	 * 进入维修发料页面
	 * @return
	 */
	public String initMaintainPage(){
		request.setAttribute("insertAction", "insertMaintainAction");
		request.setAttribute("initPageAction", "");
		request.setAttribute("updateAction", "updateMaintainStateAction");
		request.setAttribute("tmprojectTypes", tmProjectTypeService.findAll());
		return Constants.SUCCESS;
	}

	/**
	 * 根据委托书查询修理内容
	 * @return
	 */
	public String findFixEntrustContent(){
		List<TbFixEntrustContent> tbFixEntrustContentList = null;
		if(tbFixEntrustId != null)
			tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId);
		request.setAttribute("tbFixEntrustContentList", tbFixEntrustContentList);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询配件列表
	 * @return
	 */
	public String tabFindPartInfo(){
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

	public String tabShowPartInfo() throws Exception{
		
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
	
	public String showPartSell() throws Exception{
		
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
	 * 新增维修发料
	 * @return
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	public String insertMaintain() throws NumberFormatException, IOException{
		
		TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
		boolean hasSendPart = tbFixEntrustService.isTbFixEntrustHasSendPart(tbFixEntrust.getEntrustCode());
		if(hasSendPart){
			
			ActionContext.getContext().put("msg","该委托书已经发料!");
			
			return Constants.FAILURE;
		}

		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm  = request.getParameter("isConfirm");
		//批量保存维修发料
		String code;
		try {
			code = tbMaintainPartContentService.batchInsertMaintain(partCol, totalPrice,entrustId,new Long(isConfirm),userId);
			ActionContext.getContext().put("msg","生成维修发料单号:" + code);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		
//		response.getWriter().println(code+","+isConfirm);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 更新维修发料单下所有配件数量
	 * @throws MinusException 
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateMaintainState() throws MinusException{
		String maintainCode =request.getParameter("maintainCode");
		
		tbMaintainPartContentService.updateMaintainState(maintainCode);
	}
	
	/**
	 * 维修发料列表
	 * @Date      2010-6-22
	 * @Function  
	 * @return
	 */
	public String findMaintainContent(){
		String isConfirms = Constants.CONFIRM+","+Constants.NOT_CONFIRM+","+Constants.RE_BALANCE;
		List<TbMaintianVo> maintianVos = tbMaintainPartContentService.getTbMaintianVos(isConfirms, tbMaintainPartContent);
		request.setAttribute("maintianVos", maintianVos);
		
		return Constants.SUCCESS;
	}
	
	
	public String initMaintainContentPage(){
		String maintainCode = request.getParameter("maintainCode");
		String entrustId = request.getParameter("entrustId");
		List<TbMaintianVo> maintainVoDetails = tbMaintainPartContentService.getTbMaintianDetailVos(maintainCode);
		TbFixEntrust fixEntrust = tbFixEntrustService.findById(new Long(entrustId));
		if(tbMaintainPartContent == null) tbMaintainPartContent = new TbMaintainPartContent();
		tbMaintainPartContent.setMaintainCode(maintainCode);
		request.setAttribute("maintainVoDetails", maintainVoDetails);
		request.setAttribute("fixEntrust", fixEntrust);
		
		
		request.setAttribute("insertAction", "updateMaintainContentAction");
		request.setAttribute("initPageAction", "findMaintainContentAction");
		request.setAttribute("updateAction", "updateMaintainStateAction");
		
		request.setAttribute("freeFlagTypes", tmStockOutService.getAllFreeFlagMapType());
		request.setAttribute("tmprojectTypes", tmProjectTypeService.findAll());
		return Constants.EDITPAGE;
	}
	
	public String updateMaintainContent() throws IOException, NumberFormatException{
		TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
		boolean hasSendPart = tbFixEntrustService.isTbFixEntrustHasSendPart(tbFixEntrust.getEntrustCode());
		if(hasSendPart){
			
			ActionContext.getContext().put("msg","该委托书已经发料!");
			
			return Constants.FAILURE;
		}
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		try {
			tbMaintainPartContentService.updateMaintain(tbMaintainPartContent.getMaintainCode(), partCol, totalPrice, entrustId, new Long(isConfirm),userId);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
//		response.getWriter().println(tbMaintainPartContent.getMaintainCode()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	public void deleteMaintainContent() throws IOException{
		String maintainCode = request.getParameter("id");
		if(StringUtils.isNotBlank(maintainCode)){
			boolean flag = tbMaintainPartContentService.deleteTbMaintain(maintainCode);
			
			if(flag)
				response.getWriter().print("tbMaintainTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("tbMaintainTable," + Constants.FAILURE);
		}
	}
	
	/********************  **************************/
	
	/**
	 * 再结算，确认出库状态下修改维修发料 进入页面初始化
	 */
	public String initConfirmMaintainContentPage(){
		String maintainCode = request.getParameter("maintainCode");
		String entrustId = request.getParameter("entrustId");
		String isConfirm = request.getParameter("isConfirm");
		List<TbMaintianVo> maintainVoDetails = tbMaintainPartContentService.getTbMaintianDetailVos(maintainCode);
		TbFixEntrust fixEntrust = tbFixEntrustService.findById(new Long(entrustId));
		if(tbMaintainPartContent == null) tbMaintainPartContent = new TbMaintainPartContent();
		tbMaintainPartContent.setMaintainCode(maintainCode);
		tbMaintainPartContent.setIsConfirm(new Long(isConfirm));
		request.setAttribute("maintainVoDetails", maintainVoDetails);
		request.setAttribute("fixEntrust", fixEntrust);
		
		
		request.setAttribute("insertAction", "updateMaintainContentAction");
		request.setAttribute("initPageAction", "findMaintainContentAction");
		request.setAttribute("updateAction", "updateMaintainStateAction");
		request.setAttribute("freeFlagTypes", tmStockOutService.getAllFreeFlagMapType());
		request.setAttribute("zlMap", Constants.zlMap());
		request.setAttribute("xmlxMap", Constants.xmlxMap());
		request.setAttribute("tmprojectTypes", tmProjectTypeService.findAll());
		return Constants.EDITPAGE;
	}
	
	
	public String updateConfirmMaintainContent() throws IOException, NumberFormatException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		try {
			tbMaintainPartContentService.updateConfirmMaintain(tbMaintainPartContent.getMaintainCode(), partCol, totalPrice, entrustId,tbMaintainPartContent.getIsConfirm(),userId);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
 	 * 若销售价为0时则取配件的默认销售价
	 * @throws IOException
	 */
	public void getCustomerSellprice() throws IOException{
	
		Double sellPrice = tbMaintainPartContentService.getCustomerSellPriceByPartId(partId, entrustId);
		
		response.getWriter().print(sellPrice);
	}

	
	public String isMaintainEdit() throws Exception{
		String maintainCode = request.getParameter("maintainCode");

		if (StringUtils.isNotBlank(maintainCode)) {

			boolean flag = tbMaintainPartContentService.isMaintainEdit(maintainCode);
			
			// 回传时字符格式为 E3表的id,操作标示
			response.getWriter().print(flag);
		} 
		return null;
	}
	
	
	public String findTbFixEntrustContent() throws Exception{
		
		String tbFixEntrustId = request.getParameter("tbFixEntrustId");
		
		if(StringUtils.isBlank(tbFixEntrustId)){
			return null;
		}
		
		List<TbFixEntrustContent> tbFixEntrustContentList = null; 
			
			
		tbFixEntrustContentList = tbFixEntrustContentService.findTbFixEnTrustContentListByTbFixEntrustId(Long.valueOf(tbFixEntrustId));
		
		ActionContext.getContext().put("fixHourTotal", new BigDecimal(tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustId(Long.valueOf(tbFixEntrustId))).setScale(2,BigDecimal.ROUND_HALF_UP));
			
		
		ActionContext.getContext().put("tbFixEntrustContentList", tbFixEntrustContentList);
		
		return Constants.SUCCESS;
	}
	
	
	public String getSumPartInfoByStockout() throws Exception{
		String partId = request.getParameter("partId");

		if (StringUtils.isNotBlank(partId)) {

			Double stockQuantity= tbMaintainPartContentService.getAllStockOutNum(new Long(partId));
			
			response.getWriter().print(stockQuantity);
		} 
		return null;
	} 
}
