package com.selfsoft.secrity.service.impl;

import java.util.Map;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.WebTreeNode;

import com.selfsoft.framework.common.ImpermissionElements;
import com.selfsoft.secrity.model.TmResource;
/**
 * 
 * @author BaiJX  Jan 13, 2010
 * Tree模型构造器，用于生成树模型
 */
public class WebTreeModelCreatorImpl extends AbstractWebTreeModelCreator {

	//叶子节点图标url
	public static final String LEFANODEICON = "/images/icons/page.gif";
	//打开节点图标url
	public static final String OPENICON = "/images/icons/folderopen.gif";
	//默认节点图标url
	public static final String ICON = "/images/icons/folder.gif";
	
	@Override
	protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
		TmResource tmResource = (TmResource)pUserData;
		String str = "";
		if(tmResource.getParentId() != null){
			str = getPermissionDomStr(tmResource.getId());
		}
		WebTreeNode result = new WebTreeNode(tmResource.getResourceName()+str, "resource" + tmResource.getId());
		result.setAttribute("resourcePath", tmResource.getResourcePath());
		result.setAttribute("parentId",tmResource.getParentId()+"");
		result.setAttribute("id",tmResource.getId()+"");
		result.setAttribute("isLeaf",tmResource.getIsLeaf()+"");
		result.setAttribute("resourceDesc", tmResource.getResourceDesc());
		result.setAttribute("resourceName", tmResource.getResourceName());
		result.setOpenIcon(this.getUrl(OPENICON));
		
		if(tmResource.getIsLeaf() == 1)
			result.setIcon(this.getUrl(LEFANODEICON));
		else
			result.setIcon(this.getUrl(ICON));
		
		if(tmResource.isChecked())
			result.setSelected(true);
		result.setAttribute("level",tmResource.getLevel()+"");
		return result;
	}
	
	public String getPermissionDomStr(Long resoourceId){
		StringBuilder sb = new StringBuilder();
		Map<String, String> map = ImpermissionElements.getMap();
		sb.append("<span style=display:none id=span"+resoourceId+" >");
		for(String key : map.keySet()){
			String value = map.get(key);
			sb.append("<input type=checkbox checked=true; name=\"im"+resoourceId+"\" value="+key+"  />"+value);
		}
		sb.append("</span>");
		return sb.toString();
	}
}
