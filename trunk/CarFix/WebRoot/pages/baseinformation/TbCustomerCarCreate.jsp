<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>客户增加</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCustomer.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCarInfo.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbCustomerCarInsertAction.action?listPage=true" onsubmit="return validateAll();">
			
			<table>
			<s:hidden id="carId"> </s:hidden>
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
						<!-- 
						commom.js中方法sendChn(中文输入框ID,拼音输入框ID);
						 -->
						<s:textfield id="customerName" name="tbCustomer.customerName" onblur="sendChn('customerName','pinyinCode');" maxlength="50"/>
					
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>固定电话</td>
					<td>
						<s:textfield name="tbCustomer.phone" maxlength="20"/>
					</td>
					<td>拼音代码</td>
					<td>
						<s:textfield id="pinyinCode" name="tbCustomer.pinyinCode" readonly="true"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td>
						<s:textfield id="telephone" name="tbCustomer.telephone" maxlength="20"/>
						<font color="red">*</font>
					</td>
					<td>联系地址</td>
					<td>
						<s:textfield name="tbCustomer.address" maxlength="50"/>
						
					</td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td>
						<s:textfield name="tbCustomer.zipCode" maxlength="6"/>
					</td>
					<td>单位帐号</td>
					<td>
						<s:textfield name="tbCustomer.companyAccountCode" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td>单位税号</td>
					<td>
						<s:textfield name="tbCustomer.companyTaxCode" maxlength="50"/>
					</td>
					<td>法人代表</td>
					<td>
						<s:textfield name="tbCustomer.lawPresent" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td>联系人</td>
					<td>
						<s:textfield id="contractPerson" name="tbCustomer.contractPerson" maxlength="10"/>
						<font color="red">*</font>
					</td>
					<td>销售类型</td>
					<td>
						<s:select name="tbCustomer.tmSoleType.id" list="#request.tmSoleTypeMap" emptyOption="true" listKey="key" listValue="value" />
					</td>
				</tr>
				<tr>
					<td>客户类别</td>
					<td>
						<s:select name="tbCustomer.customerProperty" list="#request.customerPropertyMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>客户类型</td>
					<td>
						<s:select name="tbCustomer.tmCustomerType.id" list="#request.customerTypeMap" emptyOption="true" listKey="key" listValue="value" />
					</td>
				</tr>
				
				<tr>
					<td colspan="4"><hr width="700"/></td>
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
