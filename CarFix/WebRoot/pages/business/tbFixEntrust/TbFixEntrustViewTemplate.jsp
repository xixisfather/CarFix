<%@page import="com.selfsoft.framework.common.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.selfsoft.business.model.TbFixEntrustContent"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理委托书查看</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
	<% 
	
			
	      Map<String,String> companyMap = Constants.getCompanyMap();
	
	      if(companyMap.get("xtlName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	
	    	  request.setAttribute("comName", "xtl");
	    	  
	      }
	      
	      else if(companyMap.get("dfbzName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	  
	    	  request.setAttribute("comName", "dfbz");
	    	  
	      }
	      
	      else{
	    	  
	    	  request.setAttribute("comName", "other");
	      }
	      
	   // request.setAttribute("comName", "dfbz");
	    
	    %>
		<s:form action="tbFixEntrustViewAction.action">
			<table>
				<tr>
					<td>
						<s:hidden id="tbFixEntrustId" name="tbFixEntrust.id"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>委托书号</td>
					<td>
						<s:textfield name="tbFixEntrust.entrustCode" readonly="true" size="10"/>
					</td>
					<td>修理类型</td>
					<td>
						<s:select id="tmFixTypeId" name="tbFixEntrust.tmFixType.id"
							list="#request.tmFixTypeMap" listKey="key" listValue="value" cssStyle="width:100;"/>
					</td>
					<td>
						接待员
					</td>
					<td>
						<s:textfield id="userName" name="tbFixEntrust.tmUser.userRealName" readonly="true" size="10"></s:textfield>
						<s:hidden id="userId" name="tbFixEntrust.tmUser.id"></s:hidden>
					</td>
				
					<td>修理时间</td>
					<td>
						
						<s:textfield id="fixDate" name="tbFixEntrust.fixDate" size="15">
							<s:param name="value"><s:date name="tbFixEntrust.fixDate" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
						<font color="blue">#</font>
					</td>
				</tr>
				<tr>	
					<td>预计竣工时间</td>
					<td>
						
						<s:textfield id="estimateDate" name="tbFixEntrust.estimateDate" size="15"> 
							<s:param name="value"><s:date name="tbFixEntrust.estimateDate" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
					</td>
				
					<td>车牌号</td>
					<td>
						<s:hidden id="tbCarInfoId" name="tbFixEntrust.tbCarInfo.id"></s:hidden>
						<s:textfield id="licenseCode" name="tbFixEntrust.tbCarInfo.licenseCode" onblur="getInfoByCarLicense();" size="10"/>
						<font color="red">*</font>
						
					</td>
					<td>底盘号</td>
					<td>
						<s:textfield id="chassisCode" name="tbFixEntrust.tbCarInfo.chassisCode" readonly="true" size="17"/>
					</td>
					<td>车型</td>
					<td>
						<s:select id="tmCarModelTypeId" name="tbFixEntrust.tbCarInfo.tmCarModelType.id"
							list="#request.tmCarModelTypeMap" listKey="key" listValue="value"
							emptyOption="true" disabled="true" cssStyle="width:100;"/>
					</td>
				</tr>
				<tr>	
					<td>购车日期</td>
					<td>
						
						<s:textfield id="purchaseDate" name="tbFixEntrust.tbCarInfo.purchaseDate" readonly="true" size="10">
							<s:param name="value"><s:date name="tbFixEntrust.tbCarInfo.purchaseDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
					</td>
					<td>发动机号</td>
					<td>
						<s:textfield id="engineCode" name="tbFixEntrust.tbCarInfo.engineCode" readonly="true" size="15"></s:textfield>
					</td>
				
					<td>客户号</td>
					<td>
						<s:hidden id="tbCustomerId" name="tbFixEntrust.tbCustomer.id"></s:hidden>
						<s:textfield id="customerCode" name="tbFixEntrust.tbCustomer.customerCode" readonly="true" size="10"/>
					</td>
					<td>车主</td>
					<td>
						<s:textfield id="customerName" name="tbFixEntrust.tbCustomer.customerName" readonly="true" size="10"></s:textfield>
					</td>
				</tr>
				<tr>	
					<td>
						地址
					</td>
					<td>
						<s:textfield id="address" name="tbFixEntrust.tbCustomer.address" readonly="true" size="15"></s:textfield>
					</td>
					<td>电话</td>
					<td>
						<s:textfield id="phone" name="tbFixEntrust.tbCustomer.phone" readonly="true" size="10"></s:textfield>
					</td>
					<td>手机</td>
					<td>
						<s:textfield id="telphone" name="tbFixEntrust.tbCustomer.telephone" readonly="true" size="11"></s:textfield>
					</td>
					<td>油表指示</td>
					
					<td>
						<s:textfield id="oilMeter" name="tbFixEntrust.oilMeter" size="10" maxlength="10"  disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>	
					<td>进站里程</td>
					<td>
						<s:textfield id="enterStationKilo" name="tbFixEntrust.enterStationKilo" size="10" maxlength="10"  disabled="true"></s:textfield>
					</td>
					<td>提醒保养公里数</td>
					<td>
						<s:textfield id="remindMaintainKilo" name="tbFixEntrust.remindMaintainKilo" size="10" maxlength="10"  disabled="true"></s:textfield>
					</td>
					<td>提醒保养日期</td>
					<td>
						<s:textfield id="remindMaintainDate" name="tbFixEntrust.remindMaintainDate"  disabled="true" size="10">
							<s:param name="value"><s:date name="tbFixEntrust.remindMaintainDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						
					</td>
					<td>提醒保险日期</td>
					<td>
						<s:textfield id="remindInsuranceDate" name="tbFixEntrust.remindInsuranceDate" readonly="true" size="10"></s:textfield>
						<e3c:calendar for="remindInsuranceDate" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<c:choose>
				<c:when test="${request.comName=='dfbz'}">
				<tr>
					<td>备件组织号</td>
				    <td><s:textfield id="bjzzh" name="tbFixEntrust.bjzzh" size="10"></s:textfield></td>
				    <td>首保日期</td>
					<td>
						<s:textfield id="sbrq" name="tbFixEntrust.sbrq" size="10"></s:textfield>
						<e3c:calendar for="sbrq" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				</c:when>
				</c:choose>
				<tr>
					<td>客户故障描述</td>
					<td>
						<s:textarea id="wrongDescribe" name="tbFixEntrust.wrongDescribe" rows="2" cols="15" disabled="true"></s:textarea>
					</td>
					<td>送修症状</td>
					<td>
						<s:textarea id="beforeFixState" name="tbFixEntrust.beforeFixState" rows="2" cols="15" disabled="true"></s:textarea>
					</td>
					<td>检修结果</td>
					<td>
						<s:textarea id="checkResult" name="tbFixEntrust.checkResult" rows="2" cols="15" disabled="true"></s:textarea>
					</td>
					<td>备注</td>
					<td>
						<s:textarea id="remark" name="tbFixEntrust.remark" rows="2" cols="15" disabled="true"></s:textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="退出" onclick="closeWindow();"/>
					</td>
				</tr>
			</table>
		</s:form>
		<div id='tabPlaceHolder'></div>
	</body>
</html>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingInfo.js" charset="UTF-8"></script>
