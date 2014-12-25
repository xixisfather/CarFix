package com.selfsoft.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmPermission;
import com.selfsoft.secrity.model.TmRoleResource;
import com.selfsoft.secrity.service.ITmRoleResourceService;
import com.selfsoft.secrity.service.ITmUserRoleService;

public class CheckPermissionIntercepter extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = -953811867851064298L;
	
	
	@Autowired
	private ITmUserRoleService tmUserRoleService;
	@Autowired
	private ITmRoleResourceService tmRoleResourceService;
	
	/**
	 * 判断用户是否有操作权限
	 */
	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		System.out.println("自定义拦截器,判断用户是否有操作权限");
		Map reqParams = invo.getInvocationContext().getParameters();
		ActionContext actionContext = invo.getInvocationContext(); 
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		Map session = invo.getInvocationContext().getSession();
		String permissionType = request.getParameter("permissionType");
		String resourceId = (String) reqParams.get("resourceId");
		String userId = (String) session.get("userId");
		
		
		userId = "2";
		resourceId = "32";
		//permissionType = "ADD";
		
		
		if(StringUtils.isBlank(permissionType))
			return invo.invoke();
		
		//获得角色ID
		Long roleId = tmUserRoleService.getUserRoleRelationByUserId(new Long(userId));
		TmRoleResource trr = new TmRoleResource();
		trr.setRoleId(roleId);
		trr.setResourceId(new Long(resourceId));
		boolean flag = false;
		//角色资源IDw
		List<TmRoleResource> roleResourceList = tmRoleResourceService.findByTmRoleResource(trr);
		if(roleResourceList != null && roleResourceList.size() >0){
			TmRoleResource roleResource = roleResourceList.get(0);
			for( TmPermission permission :roleResource.getPermissions()){
				//用户拥护该权限
				if(permission.getPermissionType().equals(permissionType)){
					flag = true;
					break;
					
				}
			}
			
		}


		if(flag)
			return invo.invoke();
		else 
			return Constants.PERMISSIONFAILED;
	}

}
