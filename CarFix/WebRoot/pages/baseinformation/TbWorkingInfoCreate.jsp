<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工时工位增加</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbWorkingInfo.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	
	</head>

	<body>
		<s:form id="formId" action="tbWorkingInfoInsertAction.action">
			<table>
				<tr>
					<td>工位代号</td>
					<td>
						
						<s:textfield id="stationCode" name="tbWorkingInfo.stationCode" onblur="sendStationCode('stationCode')"/>
						<font color="red">*</font>
					</td>
					
					<td>工位名称</td>
					<td>
						
						<s:textfield id="stationName" name="tbWorkingInfo.stationName" onblur="sendChn('stationName','pinyinCode');"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>工种</td>
					<td>
						<s:select id="tmWorkTypeId" name="tbWorkingInfo.tmWorkType.id" list="#request.tmWorkTypeMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>拼音代码</td>
					<td>
						
						<s:textfield id="pinyinCode" name="tbWorkingInfo.pinyinCode"/>
					</td>
					
				</tr>
				<tr>
					<td>车身部位</td>
					<td>
						<s:select id="tmCarBodyId" name="tbWorkingInfo.tmCarBody.id" list="#request.tmCarBodyMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>修理工时</td>
					<td>
						<s:textfield id="fixHour" name="tbWorkingInfo.fixHour" value="0.00"/>
						<font color="red">*</font>
					</td>
					
				</tr>
				<tr>
					<td>派工工时</td>
					<td>
						<s:textfield id="sendHour" name="tbWorkingInfo.sendHour" value="0.00"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>选择车型工位</td>
					<td>
						<s:select id="tmCarStationTypeId" name="tbWorkingInfo.tmCarStationTypes" list="#request.tmCarStationTypeMap" listKey="key" listValue="value"></s:select>
					</td>
					
				</tr>	
				<tr>

					<td align="center" colspan="4">
						<input type="button" value="增加" onclick="sendStationCodeAndCarTypeId('stationCode','tmCarStationTypeId');"/>
						
						&nbsp;&nbsp;
						
						<input type="reset" value="重置"/>
					</td>
					
				</tr>
			</table>
		</s:form>
	</body>
</html>
