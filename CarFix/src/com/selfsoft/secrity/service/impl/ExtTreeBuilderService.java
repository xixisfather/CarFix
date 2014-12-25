package com.selfsoft.secrity.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.ext.ExtLoadTreeBuilder;
import net.jcreate.e3.tree.ext.ExtSubTreeBuilder;
import net.jcreate.e3.tree.ext.ExtTreeBuilder;
import net.jcreate.e3.tree.ext.OutlookExtTreeBuilder;
import net.jcreate.e3.tree.ext.PrvCheckExtTreeBuilder;
import net.jcreate.e3.tree.support.AbstractWebTreeBuilder;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.DefaultNodeComparator;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.DefaultTreeModel;
import net.jcreate.e3.tree.support.RequestUtil;
import net.jcreate.e3.tree.support.ReverseComparator;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeDynamicNode;
import net.jcreate.e3.tree.support.WebTreeNode;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.model.TmBackMenu;
import com.selfsoft.secrity.model.TmResource;
@Service("extTreeBuilderService")
public class ExtTreeBuilderService {
	
	/**
	 * 构建Ext复选树
	 * @param request
	 * @param list    	   业务数据模型
	 * @param title		   标题
	 * @param cascadeAll   选中节点是否级联儿子们和父辈
	 * @return
	 */
	public String buildCheckTree(HttpServletRequest request,List<TmResource> list,String title,Boolean cascadeChild,Boolean cascadeParent){
		
		//业务数据解码器，从业务数据中分解出id和parentid
		UserDataUncoder uncoder = new UserDataUncoderImpl();
		//Tree模型构造器，用于生成树模型
		AbstractWebTreeModelCreator treeModelCreator = new WebTreeModelCreatorImpl(); 
		treeModelCreator.init(request);
		
		TreeModel treeModel = treeModelCreator.create(list,uncoder);
		TreeDirector director = new DefaultTreeDirector();
		//构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator() ));
		ExtTreeBuilder treeBuilder = new PrvCheckExtTreeBuilder();
		//构造树Builder
		treeBuilder.init(request);
		
		if(StringUtils.isNotBlank(title)) treeBuilder.setTitle(title);
		if(cascadeChild != null ) treeBuilder.setCascadeChild(cascadeChild);
		if(cascadeParent != null ) treeBuilder.setCascadeParent(cascadeParent);
		//执行构造
		director.build(treeModel, treeBuilder);		
		return treeBuilder.getTreeScript();//获取构造树的脚本
	}
	
	
	/**
	 * 构建Ext普通树
	 * @param request
	 * @param list		业务数据模型
	 * @return
	 */
	public String buildCommonTree(HttpServletRequest request,List<TmResource> list){
		//业务数据解码器，从业务数据中分解出id和parentid
		UserDataUncoder uncoder = new UserDataUncoderImpl();
		//Tree模型构造器，用于生成树模型
		AbstractWebTreeModelCreator treeModelCreator = new WebTreeModelCreatorImpl(); 
		treeModelCreator.init(request);
		
		TreeModel treeModel = treeModelCreator.create(list,uncoder);
		TreeDirector director = new DefaultTreeDirector();
		//构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator() ));
		AbstractWebTreeBuilder treeBuilder = new ExtTreeBuilder();
		//构造树Builder
		treeBuilder.init(request);
		//执行构造	
		director.build(treeModel, treeBuilder);	
		
		return treeBuilder.getTreeScript();
	}
	
	
	/**
	 * 构建动态Ext普通树
	 * @param request	
	 * @param root 			根部业务数据模型
	 * @param title			标题
	 * @param actionUrl		请求儿子数据url
	 * @return
	 */
	public String buildDynamicCommonTree(HttpServletRequest request,TmResource root,String title,String actionUrl){
		
		WebTreeDynamicNode rootNode = new WebTreeDynamicNode(root.getResourceName(), "resource" + root.getId());
		rootNode.setSubTreeURL(RequestUtil.getUrl(actionUrl + root.getId(), request));
		rootNode.setIcon(request.getContextPath()+WebTreeModelCreatorImpl.ICON);
		rootNode.setOpenIcon(request.getContextPath()+WebTreeModelCreatorImpl.OPENICON);
		rootNode.setAttribute("resourcePath",root.getResourcePath());
		rootNode.setAttribute("parentId",root.getParentId()+"");
		rootNode.setAttribute("id",root.getId()+"");
		rootNode.setAttribute("isLeaf",root.getIsLeaf()+"");
		rootNode.setAttribute("resourceDesc", root.getResourceDesc());
		rootNode.setAttribute("resourceName", root.getResourceName());
		
		DefaultTreeModel treeModel = new DefaultTreeModel();
		treeModel.addRootNode(rootNode);		
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new DefaultNodeComparator());
		ExtTreeBuilder treeBuilder = new ExtLoadTreeBuilder();
		treeBuilder.init(request);	
		if(StringUtils.isNotBlank(title)) treeBuilder.setTitle(title);
		director.build(treeModel, treeBuilder);		
		
		return treeBuilder.getTreeScript();
	}
	
	/**
	 * 动态加载儿子节点
	 * @param request
	 * @param list
	 * @param actionUrl
	 * @return
	 */
	public String loadDynamicSubTree(HttpServletRequest request,List<TmResource> list,final String actionUrl){
		UserDataUncoder uncoder = new UserDataUncoderImpl();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				TmResource tmResource = (TmResource)pUserData;
				WebTreeDynamicNode result = new WebTreeDynamicNode(tmResource.getResourceName(), "resource" +tmResource.getId());
				result.setAttribute("resourcePath",tmResource.getResourcePath());
				result.setAttribute("parentId",tmResource.getParentId()+"");
				result.setAttribute("id",tmResource.getId()+"");
				result.setAttribute("isLeaf",tmResource.getIsLeaf()+"");
				result.setAttribute("resourceDesc", tmResource.getResourceDesc());
				result.setAttribute("resourceName", tmResource.getResourceName());
				
				result.setSubTreeURL(
						getUrl(actionUrl + tmResource.getId()));
				result.setOpenIcon(this.getUrl(WebTreeModelCreatorImpl.OPENICON));
				if(tmResource.getIsLeaf() == 1)
					result.setIcon(this.getUrl(WebTreeModelCreatorImpl.LEFANODEICON));
				else
					result.setIcon(this.getUrl(WebTreeModelCreatorImpl.ICON));
				
				result.setValue(tmResource.getId()+"");			
				return result;
			}
		};
		treeModelCreator.init(request);
		
		TreeModel treeModel = treeModelCreator.create(list,uncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new DefaultNodeComparator());
		WebTreeBuilder treeBuilder = new ExtSubTreeBuilder();
		treeBuilder.init(request);		
		director.build(treeModel, treeBuilder);		
		return treeBuilder.getTreeScript();
		
	}
	
	public String buildBackMenuOutLookTree(HttpServletRequest request,List<TmBackMenu> list){

		//业务数据解码器，从业务数据中分解出id和parentid
		UserDataUncoder orgUncoder = new UserDataUncoder(){
			public Object getID(Object pUserData) throws UncodeException {
				TmBackMenu org = (TmBackMenu)pUserData;
				return org.getId();
			} 
			public Object getParentID(Object pUserData) throws UncodeException {
				TmBackMenu org = (TmBackMenu)pUserData;
				return org.getParentId();
			}
		};
		
		//Tree模型构造器，用于生成树模型
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			//该方法负责将业务数据映射到树型节点
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				TmBackMenu org = (TmBackMenu)pUserData;
				WebTreeNode result = new WebTreeNode(org.getTreeName(), "node" + org.getId());
				result.setAttribute("parentId",org.getParentId()+"");
				result.setAttribute("id",org.getId()+"");
				result.setAttribute("url", org.getUrl());
				result.setAction("javascript:openUrl('"+RequestUtil.getUrl(org.getUrl(), request)+"')");
				return result;
			}
		};
		treeModelCreator.init(request);
		
		TreeModel treeModel = treeModelCreator.create(list,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();//构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator() ));
		AbstractWebTreeBuilder treeBuilder = new OutlookExtTreeBuilder();//构造树Builder
		treeBuilder.init(request);
		director.build(treeModel, treeBuilder);//执行构造		
		return treeBuilder.getTreeScript();//获取构造树的脚本
	}

}
