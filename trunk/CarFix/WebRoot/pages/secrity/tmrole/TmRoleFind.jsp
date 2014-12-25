<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>
	</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<script type="text/javascript" >
		function tmRoleTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增',
				iconCls : 'addIcon',
				handler : function() {
					addObject('tmRoleForwardPageAction.action',800,450);
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tmRoleTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmRoleTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "tmRoleFindAction";
			pConfig.showLoadingMsg = true;
		}
	</script>
	<body>
		<s:form action="tmRoleFindAction.action">
			
		</s:form>
		
		
		<e3t:table id="tmRoleTable" uri="tmRoleFindAction.action" var="tmRole"
			scope="request" items="tmRoleList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" caption="所有角色列表">
			<e3t:column property="roleCode" title="角色代码" />
			<e3t:column property="roleName" title="角色名称" />
			<e3t:column property="roleNote" title="角色说明" />
			<e3t:column property="no" title="相关操作" 
				sortable="false">
				<a href="window.loaction.href = <%=request.getContextPath() %>/tmRoleResourceAction.action?&id=${tmRole.id}"><font color="blue">
				   权限维护
				</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmRole.id}','tmRoleDeleteAction.action');"><font
					color="blue">删除角色
				</font>
				</a>
			</e3t:column>

		</e3t:table>
	</body>
</html>
