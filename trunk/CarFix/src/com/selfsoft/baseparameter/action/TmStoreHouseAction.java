package com.selfsoft.baseparameter.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.framework.common.Constants;

public class TmStoreHouseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -2833968995789301918L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmStoreHouseService tmStoreHouseService;

	private TmStoreHouse tmStoreHouse;

	public TmStoreHouse getTmStoreHouse() {
		return tmStoreHouse;
	}

	public void setTmStoreHouse(TmStoreHouse tmStoreHouse) {
		this.tmStoreHouse = tmStoreHouse;
	}

	public String findTmStoreHouse() {
		List<TmStoreHouse> tmStoreHouseList = tmStoreHouseService
				.findByTmStoreHouse(tmStoreHouse);

		ActionContext.getContext().put("tmStoreHouseList", tmStoreHouseList);

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		return Constants.SUCCESS;
	}

	public String insertTmStoreHouse() throws Exception{
		boolean flag = tmStoreHouseService.insert(tmStoreHouse);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}

	public String updateTmStoreHouse() throws Exception{
		boolean flag = tmStoreHouseService.update(tmStoreHouse);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	
	public String deleteTmStoreHouse() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmStoreHouseService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmStoreHouseTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmStoreHouseTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmStoreHouseTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());

		if (null != id && !"".equals(id)) {

			tmStoreHouse = tmStoreHouseService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
}
