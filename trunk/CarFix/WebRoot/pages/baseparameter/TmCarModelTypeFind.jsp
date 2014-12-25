<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆类型</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmCarModelType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmCarModelTypeFindAction.action">
			<table>
				<tr>
					<td>
						车型代码
						<s:textfield name="tmCarModelType.modelCode"/>
					</td>
					<td>
						车型名称
						<s:textfield name="tmCarModelType.modelName"/>
					</td>
					<td>
						车型工位标志
						<s:select name="tmCarModelType.tmCarStationType.id" list="#request.tmCarStationTypeMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						<input type="button" value="查询"
							onclick="tmCarModelTypeTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tmCarModelTypeTable" uri="tmCarModelTypeFindAction.action" var="tmCarModelType"
			scope="request" items="tmCarModelTypeList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="360" caption="车辆类型">
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tmCarModelType.id}','tmCarModelTypeForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmCarModelType.id}','tmCarModelTypeDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="modelCode" title="车型代码" />
			<e3t:column property="modelName" title="车型名称"/>
			<e3t:column property="stationCode" title="车型工位代码"/>
			<e3t:column property="stationRemark" title="车型工位说明"/>
			<e3t:column property="price" title="工时单价"/>
			<e3t:column property="priceRemark" title="工时单价说明"/>
			
		</e3t:table>	
	</body>
</html>
