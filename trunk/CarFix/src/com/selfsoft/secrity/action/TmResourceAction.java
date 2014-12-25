package com.selfsoft.secrity.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.UrlParamDecoder;
import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.service.ITmResourceService;
import com.selfsoft.secrity.service.impl.ExtTreeBuilderService;

public class TmResourceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmResourceService tmResourceService;
	@Autowired
	private ExtTreeBuilderService extTreeBuilderService;
	
	private TmResource tmResource;

	public TmResource getTmResource() {
		return tmResource;
	}

	public void setTmResource(TmResource tmResource) {
		this.tmResource = tmResource;
	}

	
	
	public void	loadsubTree() throws Exception{
		String parentID = request.getParameter("parentID");
		//取业务数据模型
		List<TmResource> list = tmResourceService.getChildrenTmResource(new Long(parentID),false);
		String treeScript = extTreeBuilderService.loadDynamicSubTree(request, list , "/tmResourceloadsubTreeAction.action?&parentID=");
		response.setBufferSize(1024*10);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(treeScript);
		response.flushBuffer();	
		return;
	}
	
	public String initManagerPage()throws Exception {
		TmResource root = tmResourceService.getRootTmResource();
		if(root == null) 
			throw new IllegalArgumentException("资源表尚未内容，需初始化");
		String treeScript = extTreeBuilderService.buildDynamicCommonTree(request, root, "请选择节点", "/tmResourceloadsubTreeAction.action?&parentID=");
		request.setAttribute("treeScript", treeScript);

		return Constants.SUCCESS;
	}
	/**
	 * 页面跳转
	 * @return
	 * @throws Exception
	 */
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {
			
			tmResource = tmResourceService.findById(new Long(id));
			
			return Constants.EDITPAGE;
		}
		request.setAttribute("parentId", request.getParameter("parentId"));
		request.setAttribute("nodeName", UrlParamDecoder.getParam(request,"nodeName"));
		return Constants.ADDPAGE;
	}
	/**
	 * 查看资源
	 * @return
	 * @throws Exception
	 */
	public String viewTmResource() throws Exception{
		String id = request.getParameter("id");
		
		tmResource = tmResourceService.findById(new Long(id));
		
		return Constants.VIEWPAGE;
	}
	/**
	 * 添加资源
	 * @return
	 * @throws Exception
	 */
	public String insertTmResource() throws Exception{
		boolean flag = tmResourceService.insert(tmResource);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	/**
	 * 修改资源
	 * @return
	 * @throws Exception
	 */
	public String editTmResource() throws Exception{
		boolean flag = tmResourceService.update(tmResource);
		return null;
	}
	
	/**
	 * 删除资源
	 * @return
	 * @throws Exception
	 */
	public String deleteTmResource() throws Exception{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmResourceService.deleteById(new Long(id));
			if(flag)
				response.getWriter().println("true");
			else
				response.getWriter().println("flase");
				
		}
		
		return null;
	}
}
