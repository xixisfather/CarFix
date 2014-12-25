<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>流失客户报警信息查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbCustomerRunOffFindAction.action">
			<table>
				<tr>
					<td>
						最后期限
					</td>
					<td>	
						<s:textfield id="fixDate" name="tbFixEntrust.fixDate">
						
							<s:param name="value"><s:date name="tbFixEntrust.fixDate" format="yyyy-MM-dd"/></s:param>
						
						</s:textfield>
						
						
						<e3c:calendar for="fixDate" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="查询"
							/>
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>			
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbCustomerTable" uri="tbCustomerRunOffFindAction.action" var="tbCustomer"
			scope="request" items="tbCustomerList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="360" caption="客户信息">	
			<e3t:column property="id" title="id" hidden="true"/>
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户名称"/>
			<e3t:column property="pinyinCode" title="拼音代码"/>
			<e3t:column property="phone" title="固定电话"/>
			<e3t:column property="telephone" title="手机号码"/>
			<e3t:column property="address" title="联系地址"/>
			<e3t:column property="zipCode" title="邮政编码"/>
			<e3t:column property="companyAccountCode" title="单位帐号"/>
			<e3t:column property="companyTaxCode" title="单位税号"/>
			<e3t:column property="lawPresent" title="法人代表"/>
			<e3t:column property="contractPerson" title="联系人"/>
			<e3t:column property="customerPropertyShow" title="客户类别"/>
			<e3t:column property="customerTypeShow" title="客户类型"/>
			<e3t:column property="soleTypeShow" title="销售类型"/>
			
			
			
		</e3t:table>
		
		
		
	</body>
</html>
<script>

	function tbCustomerTableE3ConfigHandler(pConfig) {
		
		pConfig.emptyReload = false;
		
		// 参数form,pConfig指定form的参数会提交到后台
		pConfig.form = "tbCustomerRunOffFindAction";
		
		pConfig.showLoadingMsg = true;
	}
</script>