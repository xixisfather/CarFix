<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>回访信息增加</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/visit/TbReturnVisit.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbReturnVisitInsertAction.action" onsubmit="return validateReturn();">
			<table>
				<input type="hidden" name="tbReturnVisit.returnType" value="<s:property value="#request.flag"/>"/>
				<tr>
					<td>回访车辆</td>
					<s:if test="#request.carId!=null">
					<td>
						
						<s:hidden id="tbCarInfoId" name="tbReturnVisit.tbCarInfo.id"></s:hidden>
						
						<s:textfield id="licenseCode" name="tbReturnVisit.tbCarInfo.licenseCode" readonly="true"/>
						
						<s:hidden id="entrustId" name="tbReturnVisit.entrustId"></s:hidden>
					
					</td>
					</s:if>
					
					<s:if test="#request.carId==null">
					<td>
						<s:hidden id="tbCarInfoId" name="tbReturnVisit.tbCarInfo.id"></s:hidden>
						
						<s:textfield id="licenseCode" name="tbReturnVisit.tbCarInfo.licenseCode" onblur="getInfoByCarLicense();"/>
						
						<img src="<%= request.getContextPath() %>/images/icons/find.gif" style="cursor: pointer;" onclick="openWin();"/>
						
						<font color="red">*</font>
					</td>
					</s:if>
				</tr>
				<tr>	
					<td>
						回访记录
					</td>
					<td>
						<s:textarea id="visitRemark" name="tbReturnVisit.visitRemark" cols="50" rows="5">
						
						</s:textarea>
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2">
						<input type="submit" value="确定" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>

				</tr>
			</table>
		</s:form>
		
		<script language="javascript">
			function validateReturn(){

				var car = document.getElementById('tbCarInfoId');

				if(null == car.value || '' == car.value){

					alert('请选择车辆');
					
					return false;
				}

				return true;
			}
		</script>
	</body>
</html>
