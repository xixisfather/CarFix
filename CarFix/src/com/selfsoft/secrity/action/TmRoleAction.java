package com.selfsoft.secrity.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.model.TmRole;
import com.selfsoft.secrity.service.ITmRoleResourceService;
import com.selfsoft.secrity.service.ITmRoleService;
import com.selfsoft.secrity.service.ITmUserRoleService;
import com.selfsoft.secrity.service.impl.ExtTreeBuilderService;

public class TmRoleAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware {


	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Autowired
	private ITmRoleService tmRoleService;
	
	@Autowired
	private ExtTreeBuilderService extTreeBuilderService;
	
	@Autowired
	private ITmRoleResourceService tmRoleResourceService;
	
	@Autowired
	private ITmUserRoleService tmUserRoleService;
	
	private TmRole tmRole;
	
	public TmRole getTmRole() {
		return tmRole;
	}

	public void setTmRole(TmRole tmRole) {
		this.tmRole = tmRole;
	}

	/**
	 * 添加角色
	 * @return
	 * @throws Exception
	 */
	public String insertTmRole() throws Exception{
		boolean flag = tmRoleService.insert(tmRole);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	/**
	 * 修改角色
	 * @return
	 * @throws Exception
	 */
	public String updateTmRole() throws Exception{
		boolean flag = tmRoleService.update(tmRole);
		if(flag)
			response.getWriter().println("true");
		else
			response.getWriter().println("flase");
		return null;
	}
	
	/**
	 * 页面跳转
	 * @return
	 * @throws Exception
	 */
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {
			
			tmRole = tmRoleService.findById(new Long(id));
			
			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String deleteTmRole() throws IOException{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmRoleService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmRoleTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmRoleTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmRoleTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	
	public String findTmRole()throws Exception{
		
		List<TmRole> tmRoleList = tmRoleService.findByTmRole(tmRole);

		ActionContext.getContext().put("tmRoleList", tmRoleList);
		
		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());
		
		return Constants.SUCCESS;
	}
	
	public String getRoleResource()throws Exception{
		
		String id = request.getParameter("id");
		
		if(StringUtils.isNotBlank(id))
			tmRole = tmRoleService.findById(new Long(id));
		
		List<TmResource> resourceTreeList = tmRoleResourceService.getRoleResource(new Long(id));
		String treeScript = extTreeBuilderService.buildCheckTree(request, resourceTreeList, "菜单资源", true, false);
		request.setAttribute("treeScript", treeScript);
		return Constants.SUCCESS;
	}
	
	public String insertRoleResource() throws Exception{
		
		
		String topLevelIds = request.getParameter("topLevelIds");
		String levelIds = request.getParameter("levelIds");
		String roleId = request.getParameter("roleId");
		boolean flag = tmRoleResourceService.insertTmRoleResource(topLevelIds, levelIds, roleId);
		return findTmRole();
	}
	
	public String getUserResource() throws Exception{
		Long userId = (Long) request.getSession().getAttribute("userId");
		Long roleId = tmUserRoleService.getUserRoleRelationByUserId(userId);
		List<TmResource> resourceList = tmRoleResourceService.findTmResourceByRoleId(roleId);
		String treeScript = extTreeBuilderService.buildCommonTree(request, resourceList);
		request.setAttribute("treeScript", treeScript);
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分ss秒 ");
		String today = sdf.format(new Date());
		request.setAttribute("today", today);
		return Constants.SUCCESS;
	}
}
