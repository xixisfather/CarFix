package com.selfsoft.baseinformation.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import com.selfsoft.baseinformation.model.TbCarStationWorkingRelation;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCarStationWorkingRelationService;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarBodyService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.service.ITbBookFixStationService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.service.ITmUserService;
import com.selfsoft.secrity.service.ITmWorkTypeService;

public class TbWorkingInfoAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -4077408788527093637L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;
	
	private TbWorkingInfo tbWorkingInfo;
	
	@Autowired
	private ITmWorkTypeService tmWorkTypeService;

	@Autowired
	private ITmCarBodyService tmCarBodyService;
	
	@Autowired
	private ITmCarStationTypeService tmCarStationTypeService;
	
	@Autowired
	private ITbCarStationWorkingRelationService tbCarStationWorkingRelationService;
	 
	private File fileXls;
	
	private String tmCarStationTypeId;
	
	@Autowired
	private ITmFixTypeService tmFixTypeService;

	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	@Autowired
	private ITmUserService tmUserService;
	
	@Autowired
	private ITbBookFixStationService tbBookFixStationService;
	
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	
	private TbBook tbBook;
	
	
	
	public TbBook getTbBook() {
		return tbBook;
	}

	public void setTbBook(TbBook tbBook) {
		this.tbBook = tbBook;
	}

	public String getTmCarStationTypeId() {
		return tmCarStationTypeId;
	}

	public void setTmCarStationTypeId(String tmCarStationTypeId) {
		this.tmCarStationTypeId = tmCarStationTypeId;
	}

	public File getFileXls() {
		return fileXls;
	}

	public void setFileXls(File fileXls) {
		this.fileXls = fileXls;
	}

	public TbWorkingInfo getTbWorkingInfo() {
		return tbWorkingInfo;
	}

	public void setTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		this.tbWorkingInfo = tbWorkingInfo;
	}
	
	public String findTbWorkingInfo() throws Exception{
		
		//初始化时选特定车型
		if(null==tbWorkingInfo){
			
			tbWorkingInfo = new TbWorkingInfo();
			
			List<TmCarStationType>tmCarStationTypeList = tmCarStationTypeService.findAll();
			
			if(null!=tmCarStationTypeList&&tmCarStationTypeList.size()>0){
				tbWorkingInfo.setTmCarStationTypeId(tmCarStationTypeList.get(0).getId());
			}
		}
		
		List<TbWorkingInfo>tbWorkingInfoList = tbWorkingInfoService.findByTbWorkingInfo(tbWorkingInfo);
		
		ActionContext.getContext().put("tbWorkingInfoList", tbWorkingInfoList);
		
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		//将List放入SESSION中，导出使用
		ActionContext.getContext().getSession().put("tbWorkingInfoListSession",tbWorkingInfoList);
		
		return Constants.SUCCESS;
	}
	
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	
	public String queryTbWorkingInfo() throws Exception{
		
		ActionContext.getContext().put("tmProjectTypeList",tmProjectTypeService.findAll());
		
		if(null!=tbWorkingInfo&&null!=tbWorkingInfo.getTmCarStationTypeId()){
			
			List<TbWorkingInfo>tbWorkingInfoList = tbWorkingInfoService.findByTbWorkingInfo(tbWorkingInfo);
			
			ActionContext.getContext().put("tbWorkingInfoList", tbWorkingInfoList);
			
			//将List放入SESSION中，导出使用
			ActionContext.getContext().getSession().put("tbWorkingInfoListSession",tbWorkingInfoList);
			
		}
		
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		
		//免费标识
		ActionContext.getContext().put("freeSymbolMap", Constants.getFreeSymbolMap());
		
		return Constants.SUCCESS;
	}
	
	public String insertTbWorkingInfo() throws Exception{
		
		if(null==tbWorkingInfo.getTmCarBody()||null==tbWorkingInfo.getTmCarBody().getId()){
			tbWorkingInfo.setTmCarBody(null);
		}
		if(null==tbWorkingInfo.getTmWorkType()||null==tbWorkingInfo.getTmWorkType().getId()){
			tbWorkingInfo.setTmWorkType(null);
		}
		
		tbWorkingInfoService.insertRelation(tbWorkingInfo);

		return Constants.SUCCESS;
		
	}

	public String updateTbWorkingInfo() throws Exception{
		
		if(null==tbWorkingInfo.getTmCarBody()||null==tbWorkingInfo.getTmCarBody().getId()){
			tbWorkingInfo.setTmCarBody(null);
		}
		if(null==tbWorkingInfo.getTmWorkType()||null==tbWorkingInfo.getTmWorkType().getId()){
			tbWorkingInfo.setTmWorkType(null);
		}
		
		tbWorkingInfoService.updateRelation(tbWorkingInfo);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbWorkingInfo() throws Exception{
		String id = request.getParameter("id");
		
		if (null != id && !"".equals(id)) {

			boolean flag = tbWorkingInfoService.deleteRelation(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbWorkingInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbWorkingInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbWorkingInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		@SuppressWarnings("unused")
		String tmCarStationTypId = request.getParameter("tmCarStationTypeId");
		//工种MAP
		ActionContext.getContext().put("tmWorkTypeMap", tmWorkTypeService.findAllTmWorkTypeMap());
		//车身部位MAP
		ActionContext.getContext().put("tmCarBodyMap", tmCarBodyService.findAllTmCarBodyMap());
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		if("importXlsPage".equals(flag)){
			
			this.tmCarStationTypeId = tmCarStationTypId;
			
			return Constants.IMPORTXLS;
		}
		else if("exportXlsPage".equals(flag)){
			
			this.tmCarStationTypeId = tmCarStationTypId;
			
			return Constants.EXPORTXLS;
		}
		if (null != id && !"".equals(id)) {

			tbWorkingInfo = tbWorkingInfoService.findById(Long.valueOf(id));
			
			//查询出车型工位Set
			TbCarStationWorkingRelation tbCarStationWorkingRelation = new TbCarStationWorkingRelation();
			
			tbCarStationWorkingRelation.setTbWorkingInfo(tbWorkingInfo);
			
			List<TbCarStationWorkingRelation>list = tbCarStationWorkingRelationService.findByTbCarStationWorkingRelation(tbCarStationWorkingRelation);
			
			if(null!=list&&list.size()>0){
				Set tmCarStationTypes = new HashSet();
				for(TbCarStationWorkingRelation t : list){
					if(null!=t.getTmCarStationType()){
						tmCarStationTypes.add(t.getTmCarStationType().getId());
					}
				}
				tbWorkingInfo.setTmCarStationTypes(tmCarStationTypes);
			}
			return Constants.EDITPAGE;
		}
		else{
			
			this.sendFindPageStationType(tmCarStationTypId);
		}
		return Constants.ADDPAGE;
	}
	
	@SuppressWarnings("unchecked")
	public String forwardPageWts() throws Exception {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		String tmCarStationTypId = request.getParameter("tmCarStationTypeId");
		System.out.println(request.getParameter("timeId"));
		//工种MAP
		ActionContext.getContext().put("tmWorkTypeMap", tmWorkTypeService.findAllTmWorkTypeMap());
		//车身部位MAP
		ActionContext.getContext().put("tmCarBodyMap", tmCarBodyService.findAllTmCarBodyMap());
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		if("importXlsPage".equals(flag)){
			
			this.tmCarStationTypeId = tmCarStationTypId;
			
			return Constants.IMPORTXLS;
		}
		else if("exportXlsPage".equals(flag)){
			
			this.tmCarStationTypeId = tmCarStationTypId;
			
			return Constants.EXPORTXLS;
		}
		if (null != id && !"".equals(id)) {

			tbWorkingInfo = tbWorkingInfoService.findById(Long.valueOf(id));
			
			//查询出车型工位Set
			TbCarStationWorkingRelation tbCarStationWorkingRelation = new TbCarStationWorkingRelation();
			
			tbCarStationWorkingRelation.setTbWorkingInfo(tbWorkingInfo);
			
			List<TbCarStationWorkingRelation>list = tbCarStationWorkingRelationService.findByTbCarStationWorkingRelation(tbCarStationWorkingRelation);
			
			if(null!=list&&list.size()>0){
				Set tmCarStationTypes = new HashSet();
				for(TbCarStationWorkingRelation t : list){
					if(null!=t.getTmCarStationType()){
						tmCarStationTypes.add(t.getTmCarStationType().getId());
					}
				}
				tbWorkingInfo.setTmCarStationTypes(tmCarStationTypes);
			}
			return Constants.EDITPAGE;
		}
		else{
			
			this.sendFindPageStationType(tmCarStationTypId);
		}
		return Constants.ADDPAGE;
	}
	
	
	
	
	public String findByStationCode() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String stationCode = request.getParameter("stationCode");
		
		List<TbWorkingInfo> list = tbWorkingInfoService.findByStationCode(stationCode);
		
		if(null!=list&&list.size()>0){
			
			TbWorkingInfo tbWorkingInfo = list.get(0);
			
			String responseString = "";
			
			responseString += tbWorkingInfo.getStationName() + ",";
			
			if(null!=tbWorkingInfo.getTmWorkType()){
				
				responseString += tbWorkingInfo.getTmWorkType().getId() + ",";
			
			}
			else{
				responseString += ",";
			}
			
			responseString += tbWorkingInfo.getPinyinCode() + ",";

			if(null!=tbWorkingInfo.getTmCarBody()){
				responseString += tbWorkingInfo.getTmCarBody().getId() + ",";
			}
			
			responseString += tbWorkingInfo.getFixHour() + ",";
			
			responseString += tbWorkingInfo.getSendHour() + ",";
			
			response.getWriter().print(responseString);
		}
		
		return null;
	}
	//提交时验证一个车型工位类型下的工位代码是不是已经存在，如果存在则提示已经存在 不能在做提交
	public String findByStationCodeAndCarStationTypeId() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String stationCode = request.getParameter("stationCode");
		
		Long carStationTypeId = Long.valueOf(request.getParameter("carStationTypeId"));
		
		List list = tbWorkingInfoService.findByStationCodeAndCarStationTypeId(stationCode, carStationTypeId);
		
		if(null!=list&&list.size()>0){
			
			response.getWriter().print("该车型工位下对应的工位代码已经存在,请重新选择车型工位");
		
		}
		return null;
	}
	
	/**
	 * 
	 *将查询页面的车型工位传到下个页面，使那个页面的车型工位默认选中的与查询页面相同
	 */
	private void sendFindPageStationType(String tmCarStationTypId){
		
		tbWorkingInfo = new TbWorkingInfo();
		
		Set tmCarStationTypes = new HashSet();
		
		tmCarStationTypes.add(tmCarStationTypId);
		
		tbWorkingInfo.setTmCarStationTypes(tmCarStationTypes);
	}
	
	public String tbWorkingInfoImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		String flag = tbWorkingInfoService.tbWorkingInfoImportXls(in,"/tbWorkingInfo_import_xls_tpl.properties", tmCarStationTypeId);
		
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
	
	public String tbWorkingInfoExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "工时工位";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();

		List<TbWorkingInfo> tbWorkingInfoList = (List<TbWorkingInfo>)ActionContext.getContext().getSession().get("tbWorkingInfoListSession");
		
		tbWorkingInfoService.tbWorkingInfoExportXls(os, "/tbWorkingInfo_export_xls_tpl.properties", tbWorkingInfoList);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	
	public String selectTbWorkingInfo() throws Exception{
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
		
		List<TbWorkingInfo> tbWorkingInfoList = new ArrayList<TbWorkingInfo>();
		
		if(null!=tbWorkingInfo&&null!=tbWorkingInfo.getTmCarStationTypeId()){
			tbWorkingInfoList = tbWorkingInfoService.findByTbWorkingInfo(tbWorkingInfo);
		}
			
		ActionContext.getContext().put("tbWorkingInfoList", tbWorkingInfoList);	
	
		return Constants.SUCCESS;
	}
	
	public String findByTmWorkingCollectionId() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String tbWorkingCollectionId = request.getParameter("tbWorkingCollectionId");
		
		String freeSymbolValue = request.getParameter("freeSymbolValue");
		
		String freeSymbolText = CommonMethod.TransferToEncoding(request.getParameter("freeSymbolText"), "ISO-8859-1", "GBK");
		
		List<TbWorkingInfo> tbWorkingInfoList = tbWorkingInfoService.findTbWorkingInfoListByTbWorkingCollectionId(Long.valueOf(tbWorkingCollectionId));
		
		String returnMsg = "";
		
		if(null!=tbWorkingInfoList&&tbWorkingInfoList.size()>0){
			
			for(TbWorkingInfo t : tbWorkingInfoList){
				returnMsg += t.getId() + "," + freeSymbolValue + "-" + freeSymbolText + "," + t.getStationCode() + "," + t.getStationName() +";";
			}
		}
		
		response.getWriter().print(returnMsg);
		
		return null;
	}
	
	public String showTbWorkingInfo() throws Exception{
		
		String carInfoId = request.getParameter("carInfoId");
		
		if(null!=carInfoId&&!"".equals(carInfoId)){
			
			tbWorkingInfo = new TbWorkingInfo();
			
			TbCarInfo tbCarInfo = tbCarInfoService.findById(Long.valueOf(carInfoId));
		
			TmCarStationType tmCarStationType = tmCarStationTypeService.findTmCarStationTypeByTmCarModelTypeId(tbCarInfo.getTmCarModelType().getId());
			
			tbWorkingInfo.setTmCarStationTypeId(tmCarStationType.getId());
			
			List<TbWorkingInfo>tbWorkingInfoList = tbWorkingInfoService.findByTbWorkingInfo(tbWorkingInfo);
			
			ActionContext.getContext().put("tbWorkingInfoList", tbWorkingInfoList);
			
		}
		else{
			/********************/
			//财务录入所加代码
			String tmCarModelTypeId = request.getParameter("tmCarModelTypeId");
			
			if(null!=tmCarModelTypeId&&!"".equals(tmCarModelTypeId)){
				
				if(null==tbWorkingInfo){
					tbWorkingInfo = new TbWorkingInfo();
				}
				
				TmCarStationType tmCarStationType = tmCarStationTypeService.findTmCarStationTypeByTmCarModelTypeId(Long.valueOf(tmCarModelTypeId));
				
				tbWorkingInfo.setTmCarStationTypeId(tmCarStationType.getId());
			
			}
			
			/*******************/
			List<TbWorkingInfo>tbWorkingInfoList = tbWorkingInfoService.findByTbWorkingInfo(tbWorkingInfo);
			
			ActionContext.getContext().put("tbWorkingInfoList", tbWorkingInfoList);
			
		}
		
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		
		
		return Constants.SUCCESS;
	}
}
