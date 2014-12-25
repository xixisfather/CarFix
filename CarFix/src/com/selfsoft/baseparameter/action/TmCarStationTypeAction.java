package com.selfsoft.baseparameter.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.framework.common.Constants;


public class TmCarStationTypeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware   {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 获取request,response对象
	 */
	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmCarStationTypeService tmCarStationTypeService;
	
	private TmCarStationType tmCarStationType;

	public TmCarStationType getTmCarStationType() {
		return tmCarStationType;
	}

	public void setTmCarStationType(TmCarStationType tmCarStationType) {
		this.tmCarStationType = tmCarStationType;
	}

	public String saveTmCarStationType(){
		tmCarStationTypeService.saveTmCarStationType(tmCarStationType);
		
		return Constants.SUCCESS;
	}
	
	public String updateTmCarStationType(){
		tmCarStationTypeService.updateTmCarStationType(tmCarStationType);
		return Constants.SUCCESS;
	}
	
	public String deleteTmCarStationType() throws IOException{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmCarStationTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmCarStationTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmCarStationTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmCarStationTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	public String findTmCarStationType(){
		List<TmCarStationType> tmCarStationTypeList = tmCarStationTypeService.findTmCarStationTypeList(tmCarStationType);

		ActionContext.getContext().put("tmCarStationTypeList", tmCarStationTypeList);
		
		return Constants.SUCCESS;
	}
	
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmCarStationType = tmCarStationTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String findTmCarStationTypeByCarModelId() throws Exception{
		
		String tmCarModelTypeId = request.getParameter("tmCarModelTypeId");
		
		TmCarStationType tmCarStationType = tmCarStationTypeService.findTmCarStationTypeByTmCarModelTypeId(Long.valueOf(tmCarModelTypeId));
		
		if(null!=tmCarStationType){
			response.getWriter().print("success,"+tmCarStationType.getId());
		}
		else{
			response.getWriter().print("fail,"+(-1L));
		}
		
		return null;
	}
	
}
