<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增特殊车辆提醒</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script type="text/javascript">
			function openWin(){
	
				var props = "carInfoId,licenseCode,null,null,null,null,null";
				
				showCommonWin('findAllCarInfoAction.action','车辆列表',575,355,props,"licenseCodeFocus");
			}
		</script>
	</head>
	<body>
		<s:form action="updateTmSpecialCarAlertAction.action">
			<s:hidden id="carInfoId" name="tmSpecialCarAlert.tbCarInfo.id" ></s:hidden>
			<s:hidden name="tmSpecialCarAlert.id" ></s:hidden>
			<s:hidden name="tmSpecialCarAlert.isAlert" value="0" ></s:hidden>
			<table>
				<tr>
					<td>
						牌照号
					</td>
					<td>
						<s:textfield id="licenseCode" name="tmSpecialCarAlert.tbCarInfo.licenseCode" readonly="true" onfocus="openWin();" ></s:textfield>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						开始日期
					</td>
					<td>
						<s:textfield id="beginDate" name="tmSpecialCarAlert.beginDate">
							<s:param name="value"><s:date name="tmSpecialCarAlert.beginDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" /><font color="red">*</font>
						
					</td>
				</tr>
				<tr>
					<td>
						结束日期
					</td>
					<td>
						<s:textfield id="endDate" name="tmSpecialCarAlert.endDate">
							<s:param name="value"><s:date name="tmSpecialCarAlert.endDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" /><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>提醒次数</td>
					<td>
						<s:select id="alertCount" name="tmSpecialCarAlert.alertCount" list="#request.alertCountMap" key="key" value="value" ></s:select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>提醒范围</td>
					<td>
						<s:checkboxlist list="#request.alertRangeMap" name="tmSpecialCarAlert.alertRange" key="key"  value="value"></s:checkboxlist>
						<font color="red">*</font>
						<script type="text/javascript">
							var alertRange = document.getElementsByName("tmSpecialCarAlert.alertRange")
							for(var i=0;i<alertRange.length;i++){
								if("${tmSpecialCarAlert.alertRange}".indexOf(alertRange[i].value) != -1)
									alertRange[i].checked = true;
							}
							
							
							var alertCount = document.getElementById("alertCount");
							alertCount.value = "${tmSpecialCarAlert.alertCount}";
						</script>
					</td>
				</tr>
				<tr>
					<td>提醒内容</td>
					<td>
						<s:textarea cols="30" rows="10" name="tmSpecialCarAlert.alertContent"></s:textarea><font color="red">*&nbsp;&nbsp;&nbsp;注意：提醒内容必须小于100个字符</font>
					</td>
				</tr>
				<tr>

					<td align="center" >
						<input type="submit" value="修改" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
