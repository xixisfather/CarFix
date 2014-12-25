<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆信息增加</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCustomer.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCarInfo.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body onload="document.getElementById('licenseCode').focus();">
		<s:form action="tbCarInfoInsertAction.action" onsubmit="return carInfoFormValidate()">
			<table>
				<tr>
					<td>
						<s:hidden name="tbCustomer.id"/>
						<s:hidden id="carId"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>客户号</td>
					<td>
						<s:textfield name="tbCustomer.customerCode" readonly="true"/>
						<font color="blue">#</font>
					</td>
					<td>
						客户姓名
					</td>
					<td>
						<s:textfield name="tbCustomer.customerName" readonly="true"/>
						<font color="blue">#</font>
					</td>
				</tr>
				<tr>
					<td>牌照号</td>
					<td>
						<s:textfield id="licenseCode" name="tbCarInfo.licenseCode" maxlength="10" onblur="carInfoValidation();" />
						<font color="red">*</font>
					</td>
					<td>底盘号</td>
					<td>
						<s:textfield id="chassisCode" name="tbCarInfo.chassisCode" maxlength="30" onblur="carInfoValidation();" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>保修卡号</td>
					<td>
						<s:textfield id="insureCardCode" name="tbCarInfo.insureCardCode" maxlength="30"/>
					</td>
					<td>厂商</td>
					<td>
						<s:textfield name="tbCarInfo.factory" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td>车型</td>
					<td>
						<s:select name="tbCarInfo.tmCarModelType.id" list="#request.tmCarModelTypeMap" listKey="key" listValue="value"/>
					</td>
					<td>颜色</td>
					<td>
						<s:textfield name="tbCarInfo.color" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td>发动机号</td>
					<td>
						<s:textfield name="tbCarInfo.engineCode" maxlength="30"/>
					</td>
					<td>发动机型号</td>
					<td>
						<s:textfield name="tbCarInfo.engineType" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td>变速箱型号</td>
					<td>
						<s:textfield name="tbCarInfo.changeBoxType" maxlength="30"/>
					</td>
					<td>购车日期(yyyy-MM-dd)</td>
					<td>
						<s:textfield id="purchaseDate" name="tbCarInfo.purchaseDate"/>
						<e3c:calendar for="purchaseDate" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>生产日期(yyyy-MM-dd)</td>
					<td>
						<s:textfield id="productDate" name="tbCarInfo.productDate"/>
						<e3c:calendar for="productDate" dataFmt="yyyy-MM-dd"/>
					</td>
					
					<td>保险到期(yyyy-MM-dd)</td>
					<td>
						<s:textfield id="insureDeadlineDate" name="tbCarInfo.insureDeadlineDate"/>
						<e3c:calendar for="insureDeadlineDate" dataFmt="yyyy-MM-dd"/>
					</td>
				
				</tr>
				<tr>
					<td>公里数</td>
					<td>
						<s:textfield name="tbCarInfo.kilo" maxlength="10"/>公里
					</td>
				
					<td>年检到期(yyyy-MM-dd)</td>
					<td>
						<s:textfield id="yearCheckDeadline" name="tbCarInfo.yearCheckDeadline"/>
						<e3c:calendar for="yearCheckDeadline" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>	
					<td>首次故障里程</td>
					<td>
						<s:textfield name="tbCarInfo.firstWrongKilo" maxlength="10"/>
					</td>
					
					<td>经销单位</td>
					<td>
						<s:textfield name="tbCarInfo.soleCompany" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td>中桥生产厂</td>
					<td>
						<s:textfield name="tbCarInfo.middleFactory" maxlength="20"/>
					</td>
					
					<td>后桥生产厂</td>
					<td>
						<s:textfield name="tbCarInfo.backFactory" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td>是否做过首保</td>
					<td>
						<s:select name="tbCarInfo.hasInsured" list="#request.isNoMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					
					<td>是否做过索赔</td>
					<td>
						<s:select name="tbCarInfo.hasCliam" list="#request.isNoMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
				</tr>
				<tr>	
					<td>是否做过修理</td>
					<td>
						<s:select name="tbCarInfo.hasFixed" list="#request.isNoMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>上牌日期(yyyy-MM-dd)</td>
					<td>
						<s:textfield id="licenseDate" name="tbCarInfo.licenseDate"/>
						<e3c:calendar for="licenseDate" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>车辆性质</td>
					<td>
						<s:select name="tbCarInfo.carProperty" list="#request.carPropertyMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>车辆用途</td>
					<td>
						<s:select name="tbCarInfo.carUsed" list="#request.carUsedMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="submit" value="增加" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
