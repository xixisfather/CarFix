package com.selfsoft.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginIntercepter extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3212777791641300886L;

	/**
	 * 判断用户是否有操作权限
	 */
	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		
		String actionName = invo.getProxy().getActionName();
		
		
		if(actionName.equals("tmUserLoginAction") || actionName.equals("tmResourceInitAction"))
			return invo.invoke();
		
		Map session = invo.getInvocationContext().getSession();
			
		Object tmUser = session.get("tmUser");
		if(tmUser == null){
			session.put("msg", "用户登陆超时。。请重新登陆");
			return "permissionFailed";
		}
		return invo.invoke();
	}

}
