<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>配件类型</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmPartType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmPartTypeFindAction.action">
			<table>
				<tr>
					<td>
						类型代码
						<s:textfield name="tmPartType.typeCode"/>
					</td>
					<td>
						类型名称
						<s:textfield name="tmPartType.typeName"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="查询"
							onclick="tmPartTypeTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tmPartTypeTable" uri="tmPartTypeFindAction.action" var="tmPartType"
			scope="request" items="tmPartTypeList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="360" caption="配件类型">
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tmPartType.id}','tmPartTypeForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmPartType.id}','tmPartTypeDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="typeCode" title="类型代码" />
			<e3t:column property="typeName" title="类型名称"/>
			<e3t:column property="typeRemark" title="类型说明"/>
			
		</e3t:table>	
	</body>
</html>
