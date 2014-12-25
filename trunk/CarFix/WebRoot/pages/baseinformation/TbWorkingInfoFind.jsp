<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工时工位查询</title>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbWorkingInfo.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>
   
	<body>
		<s:form action="tbWorkingInfoFindAction.action">
			<table>
				<tr>
					<td>工位代码</td>
					<td>
						<s:textfield name="tbWorkingInfo.stationCode"/>
					</td>
					<td>工位名称</td>
					<td>
						<s:textfield name="tbWorkingInfo.stationName"/>
					</td>
					<td>拼音代码</td>
					<td>
						<s:textfield name="tbWorkingInfo.pinyinCode"/>
					</td>
					<td>车型工位</td>
					<td>
						<s:select id="tmCarStationTypeId" name="tbWorkingInfo.tmCarStationTypeId" list="#request.tmCarStationTypeMap" listKey="key" listValue="value"></s:select>
					</td>
				</tr>
				<tr>	
					<td colspan="8" align="center">
						<input type="button" value="查询"
							onclick="tbWorkingInfoTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>	
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbWorkingInfoTable" uri="tbWorkingInfoFindAction.action" var="tbWorkingInfo"
			scope="request" items="tbWorkingInfoList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="360" caption="工时工位">
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tbWorkingInfo.id}','tbWorkingInfoForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbWorkingInfo.id}','tbWorkingInfoDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="id" title="ID" hidden="true"/>
			<e3t:column property="stationCode" title="工位代码" />
			<e3t:column property="stationName" title="工位名称"/>
			<e3t:column property="pinyinCode" title="拼音代码"/>
			<e3t:column property="workName" title="工种"/>
			<e3t:column property="bodyName" title="车身部位"/>
			<e3t:column property="fixHour" title="修理工时"/>
			<e3t:column property="sendHour" title="派工工时"/>
			<e3t:column property="tmCarStationNames" title="车型工位"/>
			

		</e3t:table>
	</body>
</html>
