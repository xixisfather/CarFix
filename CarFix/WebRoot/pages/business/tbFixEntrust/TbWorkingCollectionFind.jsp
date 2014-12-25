<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工位集查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingCollection.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingInfo.js" charset="UTF-8"></script>
		
	</head>

	<body>
		<s:form action="tbWorkingCollectionQueryAction.action">
			<table>
				<tr>
					<td>工位集代号</td>
					<td><s:textfield name="tbWorkingCollection.workingCollectionCode"></s:textfield></td>
					<td>工位集名称</td>
					<td><s:textfield name="tbWorkingCollection.workingCollectionName"></s:textfield> </td>
					<td>车型工位</td>
					<td>
						<s:select id="tmCarStationTypeId" name="tbWorkingCollection.tmCarStationTypeId" list="#request.tmCarStationTypeMap" listKey="key" listValue="value" disabled="true" emptyOption="true"></s:select>
						
						<s:hidden id="tmCarStationTypeIdSend" name="tbWorkingCollection.tmCarStationType.id"></s:hidden>	
					</td>
					<td>
						<input type="button" value="查询"
							onclick="tbWorkingCollectionTableQuery();" />
					</td>
				</tr>
				
			</table>
		</s:form>
		<e3t:table id="tbWorkingCollectionTable" uri="tbWorkingCollectionQueryAction.action" var="tbWorkingCollection"
			scope="request" items="tbWorkingCollectionList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" caption="工位集">
			<e3t:column property="id" title="ID" hidden="true"/>
			<e3t:column property="workingCollectionCode" title="工位集代号" />
			<e3t:column property="workingCollectionName" title="工位集名称"/>
			<e3t:column property="tmCarStationTypeName" title="车型工位"/>
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tbWorkingCollection.id}','tbWorkingCollectionForwardPageAction!forwardPage.action',800,600);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbWorkingCollection.id}','tbWorkingCollectionDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
				&nbsp;&nbsp;
				<a href="javascript:forwardPage('${tbWorkingCollection.id}','tbWorkingCollectionForwardPageAction!forwardPage.action','viewPage',800,600);">
					<font color="blue">
						查看
					</font>
				</a>
			</e3t:column>

		</e3t:table>
	</body>
</html>
<script>
	
	acquireStationTypeByCarModel();
</script>