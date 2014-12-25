package com.selfsoft.business.action;

import java.util.Date;
import java.util.HashMap;
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
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TbStoreHouseCheck;
import com.selfsoft.business.model.TbStoreHouseCheckDetail;
import com.selfsoft.business.service.ITbStoreHouseCheckDetailService;
import com.selfsoft.business.service.ITbStoreHouseCheckService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;


public class TbStoreHouseCheckAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754261359976884034L;

	private HttpServletRequest request;

	private HttpServletResponse response;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbStoreHouseCheckService tbStoreHouseCheckService;
	@Autowired
	private ITbStoreHouseCheckDetailService tbStoreHouseCheckDetailService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	
	
	private TbStoreHouseCheck tbStoreHouseCheck;
	
	private Map<String,String> formMap = new HashMap<String,String>();
	
	public Map<String,String> getFormMap(){
      return formMap;
    }
    public void setFormMap(Map<String,String> _map){
      this.formMap = _map;
    }
    public void setFormValue(String key, String value){
      formMap.put(key, value);
    }
    public Object getFormValue(String key){
      return formMap.get(key);
    }
    
    private String houseids;
	
	private Long checkid;

	public Long getCheckid() {
		return checkid;
	}

	public void setCheckid(Long checkid) {
		this.checkid = checkid;
	}

	public String getHouseids() {
		return houseids;
	}

	public void setHouseids(String houseids) {
		this.houseids = houseids;
	}

	public TbStoreHouseCheck getTbStoreHouseCheck() {
		return tbStoreHouseCheck;
	}

	public void setTbStoreHouseCheck(TbStoreHouseCheck tbStoreHouseCheck) {
		this.tbStoreHouseCheck = tbStoreHouseCheck;
	}
	
	
	public String findTbStoreHouseCheck()throws Exception{
		
		List<TbStoreHouseCheck> tbStoreHouseChecks = tbStoreHouseCheckService.findByEntity(tbStoreHouseCheck);
		
		ActionContext.getContext().put("tbStoreHouseChecks", tbStoreHouseChecks);
		
		return Constants.SUCCESS;
	}
	
	public String deleteTbStoreHouseCheck()throws Exception{
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {

			boolean flag = tbStoreHouseCheckService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbStoreHouseCheckTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbStoreHouseCheckTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbStoreHouseCheckTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {

			tbStoreHouseCheck = tbStoreHouseCheckService.findById(Long.valueOf(id));
			TbStoreHouseCheckDetail tbStoreHouseCheckDetail = new TbStoreHouseCheckDetail();
			tbStoreHouseCheckDetail.setTbStoreHouseCheck(tbStoreHouseCheck);
			List<TbStoreHouseCheckDetail> details = tbStoreHouseCheckDetailService.findByEntity(tbStoreHouseCheckDetail);
			ActionContext.getContext().put("storeHouseCheckDetails", details);
			return Constants.EDITPAGE;
		}
		
		return Constants.ADDPAGE;
	}
	
	public String initAddTbStoreHouseCheck() throws Exception {
		List<TmStoreHouse> tmStoreHouseList = tmStoreHouseService.findByTmStoreHouse(null);

		ActionContext.getContext().put("tmStoreHouseList", tmStoreHouseList);
		
		return Constants.SUCCESS;
	}
	
	
	public String checkHousePartInfo() throws Exception {
		
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.checkHousePartInfo(houseids);
		
		ActionContext.getContext().put("tbPartInfoList", tbPartInfoList);
		
		TmUser tmUser =(TmUser) request.getSession().getAttribute("tmUser");
		
		String tmStoreHouseIds = "";
		String tmStoreHouseNames = "";
		if(StringUtils.isNotBlank(houseids)){
			 String[] params = houseids.split(";");
			 if(params != null && params.length >0){
				 for(String param : params){
					 Long houseId = new Long(param.split(",")[0]);
					 TmStoreHouse tmStoreHouse = tmStoreHouseService.findById(houseId);
					 tmStoreHouseIds += houseId + ",";
					 tmStoreHouseNames += tmStoreHouse.getHouseName() + ",";
				 }
			 }
		 }
		TbStoreHouseCheck storeCheck = new TbStoreHouseCheck();
		storeCheck.setCheckDate(new Date());
		storeCheck.setTmUser(tmUser);
		storeCheck.setStoreHouseIds(tmStoreHouseIds.substring(0, tmStoreHouseIds.length()-1));
		storeCheck.setStoreHouseNames(tmStoreHouseNames.substring(0, tmStoreHouseNames.length()-1));
		ActionContext.getContext().put("storeCheck", storeCheck);
		
		return Constants.SUCCESS;
	}
	
	public String insertStoreHouseCheck()throws Exception {
		String isConfirm = request.getParameter("isConfirm");
		tbStoreHouseCheck.setIsConfirm(new Long(isConfirm));
		tbStoreHouseCheckService.insertBatchStoreHouseCheck(formMap, tbStoreHouseCheck);
		ActionContext.getContext().put("msg","生成盘点单号:" + tbStoreHouseCheck.getCheckCode());
		return Constants.SUCCESS;
	}
	
	public String updateStoreHouseCheck()throws Exception {
		String isConfirm = request.getParameter("isConfirm");
		tbStoreHouseCheck.setIsConfirm(new Long(isConfirm));
		tbStoreHouseCheckService.updateBatchStoreHouseCheck(formMap, tbStoreHouseCheck);
		return Constants.SUCCESS;
		
	}
	
	public String viewStoreHouseCheck(){

		String id = request.getParameter("id");
		
		if (StringUtils.isNotBlank(id)) {

			tbStoreHouseCheck = tbStoreHouseCheckService.findById(Long.valueOf(id));
			TbStoreHouseCheckDetail tbStoreHouseCheckDetail = new TbStoreHouseCheckDetail();
			tbStoreHouseCheckDetail.setTbStoreHouseCheck(tbStoreHouseCheck);
			List<TbStoreHouseCheckDetail> details = tbStoreHouseCheckDetailService.findByEntity(tbStoreHouseCheckDetail);
			ActionContext.getContext().put("storeHouseCheckDetails", details);
		}
		
		return Constants.SUCCESS;
	}
	
	
	public String printStoreHouseCheck(){


		tbStoreHouseCheck = tbStoreHouseCheckService.findById(checkid);
		TbStoreHouseCheckDetail tbStoreHouseCheckDetail = new TbStoreHouseCheckDetail();
		tbStoreHouseCheckDetail.setTbStoreHouseCheck(tbStoreHouseCheck);
		List<TbStoreHouseCheckDetail> details = tbStoreHouseCheckDetailService.findByEntity(tbStoreHouseCheckDetail);
		ActionContext.getContext().put("storeHouseCheckDetails", details);
		
		return Constants.SUCCESS;
	}
	
	
	
}
