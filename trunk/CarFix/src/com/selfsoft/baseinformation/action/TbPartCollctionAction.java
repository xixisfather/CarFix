package com.selfsoft.baseinformation.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.service.ITbPartCollectionService;
import com.selfsoft.baseinformation.vo.TbPartCollectionVo;
import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.DictionaryTypeElements;

public class TbPartCollctionAction extends ActionSupport implements
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
	private ITbPartCollectionService tbPartCollectionService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;

	private String partCol;
	
	private String code;
	
	public String getPartCol() {
		return partCol;
	}

	public void setPartCol(String partCol) {
		this.partCol = partCol;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 初始化新增配件集
	 * @Date      2010-5-25
	 * @Function  
	 * @return
	 */
	public String initTbPartInfoCollctionMrg(){
		List<TmDictionary> rates = tmDictionaryService.getRateTypes();
		request.setAttribute("rates", rates);
		return Constants.SUCCESS;
	}
	
	
	public String findTbPartCollection(){
		List<TbPartCollectionVo> result = tbPartCollectionService.getTbPartCollection();
		request.setAttribute("collctionList", result);
		return Constants.SUCCESS;
	}
	/**
	 * 添加配件集
	 * @return
	 */
	public String insertPartCollection(){
		//String partCol = request.getParameter("partCol");
		//String collectionCode = UrlParamDecoder.getParam(request, "collectionCode");
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			Date date = new Date();
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];
				String tmStoreHouseId  = parts.split(":")[1];
				String num = parts.split(":")[2];
				TbPartCollection tbPartCollection = new TbPartCollection();
				tbPartCollection.setPartInfoId(new Long(tbPartInfoId));
				tbPartCollection.setStoreHouseId(new Long(tmStoreHouseId));
				tbPartCollection.setPartQuantity(new Double(num));
				tbPartCollection.setCollectionCode(code);
				tbPartCollection.setCreateDate(date);
				/*
				//更新库存数量
				tbPartInfoService.updateTbPartInfoStoreQuantity(new Long(tbPartInfoId), new Double(num));
				*/
				//配件集入库
				tbPartCollectionService.insertTbPartCollection(tbPartCollection);
			}
			
		}
		
		return Constants.SUCCESS;
		
	}
	
	public String isUniquecollectionCode() throws IOException{response.setContentType("text/xml; charset=UTF-8");
		String collectionCode = request.getParameter("collectionCode");
		boolean flag = tbPartCollectionService.isUniquecollectionCode(collectionCode);
		if(flag)
			response.getWriter().println("代码可以使用");
		else
			response.getWriter().println("代码重复,请更换");
		
		return null;
		
	}
	
	public String deleteTbPartCollection() throws IOException{
		String collectionCode = request.getParameter("id");
		this.tbPartCollectionService.deleteByCollectionCode(collectionCode);
		response.getWriter().print("tbPartCollectionTable," + Constants.SUCCESS);
		return null;
	}
	
}
