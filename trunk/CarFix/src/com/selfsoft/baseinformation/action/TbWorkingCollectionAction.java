package com.selfsoft.baseinformation.action;

import java.util.ArrayList;
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
import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.model.TbWorkingRelation;
import com.selfsoft.baseinformation.service.ITbWorkingCollectionService;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.baseinformation.service.ITbWorkingRelationService;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.service.ITbBookFixStationService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.service.ITmUserService;

public class TbWorkingCollectionAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 3821887039559586508L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbWorkingCollectionService tbWorkingCollectionService;
	
	private TbWorkingCollection tbWorkingCollection;
	
	@Autowired
	private ITmCarStationTypeService tmCarStationTypeService;
	
	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;
	
	@Autowired
	private ITbWorkingRelationService tbWorkingRelationService;
	
	private Set<String> tbWorkingInfoIdLeft;
	
	private Set<String> tbWorkingInfoIdRight;
	
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
	
	private TbBook tbBook;
	
	public TbBook getTbBook() {
		return tbBook;
	}

	public void setTbBook(TbBook tbBook) {
		this.tbBook = tbBook;
	}
	
	public Set<String> getTbWorkingInfoIdLeft() {
		return tbWorkingInfoIdLeft;
	}

	public void setTbWorkingInfoIdLeft(Set<String> tbWorkingInfoIdLeft) {
		this.tbWorkingInfoIdLeft = tbWorkingInfoIdLeft;
	}

	public Set<String> getTbWorkingInfoIdRight() {
		return tbWorkingInfoIdRight;
	}

	public void setTbWorkingInfoIdRight(Set<String> tbWorkingInfoIdRight) {
		this.tbWorkingInfoIdRight = tbWorkingInfoIdRight;
	}

	public TbWorkingCollection getTbWorkingCollection() {
		return tbWorkingCollection;
	}

	public void setTbWorkingCollection(TbWorkingCollection tbWorkingCollection) {
		this.tbWorkingCollection = tbWorkingCollection;
	}
	
	public String findTbWorkingCollection() throws Exception{
		
		if(null==tbWorkingCollection){
			
			tbWorkingCollection = new TbWorkingCollection();
			
			List<TmCarStationType>tmCarStationTypeList = tmCarStationTypeService.findAll();
			
			if(null!=tmCarStationTypeList&&tmCarStationTypeList.size()>0){
				tbWorkingCollection.setTmCarStationType(tmCarStationTypeList.get(0));
			}
		}
		
		List<TbWorkingCollection>tbWorkingCollectionList = tbWorkingCollectionService.findByTbWorkingCollection(tbWorkingCollection);
		
		ActionContext.getContext().put("tbWorkingCollectionList", tbWorkingCollectionList);
		
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		
		return Constants.SUCCESS;
	}
	
	public String queryTbWorkingCollection() throws Exception{
		
		if(null!=tbWorkingCollection&&null!=tbWorkingCollection.getTmCarStationType()&&null!=tbWorkingCollection.getTmCarStationType().getId()){
			
			List<TbWorkingCollection>tbWorkingCollectionList = tbWorkingCollectionService.findByTbWorkingCollection(tbWorkingCollection);
			
			ActionContext.getContext().put("tbWorkingCollectionList", tbWorkingCollectionList);
		
		}
		
		
		
		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		
		return Constants.SUCCESS;
	}
	
	public String insertTbWorkingCollection() throws Exception{
		
		if(null==tbWorkingCollection.getTmCarStationType()){
			tbWorkingCollection.setTmCarStationType(null);
		}
		//因为页面上的SELECT被DISABLED，增加个属性tmCarStationTypeId用来给TmCarStationType赋值
		if(null!=tbWorkingCollection.getTmCarStationTypeId()){
			TmCarStationType tmCarStationType = new TmCarStationType();
			
			tmCarStationType.setId(tbWorkingCollection.getTmCarStationTypeId());
			
			tbWorkingCollection.setTmCarStationType(tmCarStationType);
		}
		
		tbWorkingCollection.setTbWorkingRelations(tbWorkingInfoIdRight);
		
		tbWorkingCollectionService.insertRelation(tbWorkingCollection);

		return Constants.SUCCESS;
		
	}

	public String updateTbWorkingCollection() throws Exception{
		
		if(null==tbWorkingCollection.getTmCarStationType()){
			tbWorkingCollection.setTmCarStationType(null);
		}
		//因为页面上的SELECT被DISABLED，增加个属性tmCarStationTypeId用来给TmCarStationType赋值
		if(null!=tbWorkingCollection.getTmCarStationTypeId()){
			TmCarStationType tmCarStationType = new TmCarStationType();
			
			tmCarStationType.setId(tbWorkingCollection.getTmCarStationTypeId());
			
			tbWorkingCollection.setTmCarStationType(tmCarStationType);
		}
		
		tbWorkingCollection.setTbWorkingRelations(tbWorkingInfoIdRight);
		
		tbWorkingCollectionService.updateRelation(tbWorkingCollection);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbWorkingCollection() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbWorkingCollectionService.deleteRelation(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbWorkingCollectionTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbWorkingCollectionTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbWorkingCollectionTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");
		
		String flag = request.getParameter("flag");
		
		String tmCarStationTypeId = request.getParameter("tmCarStationTypeId");

		//车型工位Map
		ActionContext.getContext().put("tmCarStationTypeMap", tmCarStationTypeService.findAllTmCarStationTypeMap());
		
		if (null != id && !"".equals(id)) {

			tbWorkingCollection = tbWorkingCollectionService.findById(Long.valueOf(id));
			//查找对应车型的所有TbWorkingInfoLIST
			List<TbWorkingInfo> wholeList = tbWorkingInfoService.findTbWorkingInfoListByTmCarStationTypeId(tbWorkingCollection.getTmCarStationType().getId());
			
			List<TbWorkingRelation> tbWorkingRelationList = tbWorkingRelationService.findTbWorkingRelationByTbWorkingCollectionId(tbWorkingCollection.getId());
			
			List<TbWorkingInfo> partList = new ArrayList<TbWorkingInfo>();
			
			//右边的工时工位MAP
			Map<Long,String> tbWorkingInfoRightMap = new LinkedHashMap<Long, String>();
			
			if(null!=tbWorkingRelationList){
				
				for(TbWorkingRelation tbWorkingRelation : tbWorkingRelationList){
					
					TbWorkingInfo tbWorkingInfo = tbWorkingInfoService.findById(tbWorkingRelation.getTbWorkingInfo().getId());
				
					partList.add(tbWorkingInfo);
					
					tbWorkingInfoRightMap.put(tbWorkingInfo.getId(), tbWorkingInfo.getStationCode() +"   |   "+tbWorkingInfo.getStationName());
				}
			}
			//左边的工时工位MAP = (所有个工时工位LIST - 工位集中LIST)转换的MAP
			Map<Long,String> tbWorkingInfoLeftMap = this.substractList(wholeList, partList);
			
			ActionContext.getContext().put("tbWorkingInfoLeftMap", tbWorkingInfoLeftMap);
			
			ActionContext.getContext().put("tbWorkingInfoRightMap", tbWorkingInfoRightMap);
			
			if(null!=tbWorkingCollection.getTmCarStationType()){
				tbWorkingCollection.setTmCarStationTypeId(tbWorkingCollection.getTmCarStationType().getId());
			}
			if("viewPage".equals(flag)){
				return Constants.VIEWPAGE;
			}
			return Constants.EDITPAGE;
		}
		else{
			
			//增加页面SELECT选择传过来的车型工位，HIDDEN赋值
			TmCarStationType tmCarStationType = new TmCarStationType();
			
			tmCarStationType.setId(Long.valueOf(tmCarStationTypeId));
			
			tbWorkingCollection = new TbWorkingCollection();
			
			tbWorkingCollection.setTmCarStationType(tmCarStationType);
			
			tbWorkingCollection.setTmCarStationTypeId(Long.valueOf(tmCarStationTypeId));

			//查找对应车型的工时工位MAP
			Map<Long,String> tbWorkingInfoMap = tbWorkingInfoService.findTbWorkingInfoMapByTmCarStationTypeId(Long.valueOf(tmCarStationTypeId));
			
			ActionContext.getContext().put("tbWorkingInfoMap", tbWorkingInfoMap);
		}
		return Constants.ADDPAGE;
	}
	
	private Map<Long,String> substractList(List<TbWorkingInfo> wholeList , List<TbWorkingInfo>partList){
		
		Map<Long,String> map = new LinkedHashMap<Long, String>();
		List<TbWorkingInfo> wholeListCopy = new ArrayList<TbWorkingInfo>();
		wholeListCopy.addAll(wholeList);
		if(null!=wholeList){
			
			for(TbWorkingInfo tbWorkingInfoWhole : wholeList){
				
				if(null!=partList){
					
					for(TbWorkingInfo tbWorkingInfoPart : partList){
						
						if(tbWorkingInfoWhole.getId().equals(tbWorkingInfoPart.getId())){
							
							wholeListCopy.remove(tbWorkingInfoWhole);
						
						}
						
					}
				}
				
			}
			
		}
		if(null!=wholeListCopy&&wholeListCopy.size()>0){
			for(TbWorkingInfo tbWorkingInfo : wholeListCopy){
				map.put(tbWorkingInfo.getId(), tbWorkingInfo.getStationCode() +"   |   "+tbWorkingInfo.getStationName());
			}
		}
		
		return map;
	}
	
	/**
	 * 验证对应车型工位下工位集代号是否唯一
	 */
	public String findTbWorkingCollectionByWorkingCollectionCodeAndTmCarStationTypeId() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String workingCollectionCode = request.getParameter("workingCollectionCode");
		
		Long tmCarStationTypeId = Long.valueOf(request.getParameter("tmCarStationTypeId"));
		
		List<TbWorkingCollection> tbWorkingCollectionList = tbWorkingCollectionService.findTbWorkingCollectionByWorkingCollectionCodeAndTmCarStationTypeId(workingCollectionCode, tmCarStationTypeId);
		
		if(null!=tbWorkingCollectionList&&tbWorkingCollectionList.size()>0){
			response.getWriter().print("该车型工位下对应的工位集代码已经存在,请重新选择车型工位");
		}
		return null;
	}
	
	public String selectTbWorkingCollection() throws Exception{
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
		
		List<TbWorkingCollection> tbWorkingCollectionList = new ArrayList<TbWorkingCollection>();
		
		if(null!=tbWorkingCollection&&null!=tbWorkingCollection.getTmCarStationType()&&null!=tbWorkingCollection.getTmCarStationType().getId()){
			tbWorkingCollectionList = tbWorkingCollectionService.findByTbWorkingCollection(tbWorkingCollection);
		}
			
		ActionContext.getContext().put("tbWorkingCollectionList", tbWorkingCollectionList);	
	
		return Constants.SUCCESS;
	}
	
	public String findTbWorkingCollectionDetail() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String tbWorkingCollectionId = request.getParameter("tbWorkingCollectionId");
		
		List<TbWorkingInfo> tbWorkingInfoList = tbWorkingInfoService.findTbWorkingInfoListByTbWorkingCollectionId(Long.valueOf(tbWorkingCollectionId));
		
		String responseString = "";
		
		if(null!=tbWorkingInfoList&&tbWorkingInfoList.size()>0){
			
			for(TbWorkingInfo tbWorkingInfo : tbWorkingInfoList){
				
				responseString += tbWorkingInfo.getId()+","+tbWorkingInfo.getStationCode()+","+tbWorkingInfo.getStationName()+","+tbWorkingInfo.getFixHour()+","+tbWorkingInfo.getSendHour()+";";
			
			}
			
			response.getWriter().print(responseString);
		}
		
		return null;
	}
}
