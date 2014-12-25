<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>预付款查询</title>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/css/tableIcon.css" />
		<script type="text/javascript" src="../../../js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="../../../ext/js/ext-base.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-all.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="TbAnvancePay.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../../../js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbAnvancePayFindAction.action">
			<table>
				<tr>
					<td>单据号</td>
					<td>
						<s:textfield id="anvanceCode" name="tbAnvancePay.anvanceCode"></s:textfield>
					</td>
					<td>车牌号</td>
					<td>
						<s:textfield id="licenseCode" name="tbAnvancePay.tbCarInfo.licenseCode"></s:textfield>
					</td>
					<td>
						<input type="button" value="查询" onclick="tbAnvancePayTableQuery();"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbAnvancePayTable" uri="tbAnvancePayFindAction.action" var="tbAnvancePay"
			scope="request" items="tbAnvancePayList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" caption="预付款信息">
			<e3t:column property="anvanceCode" title="单据号" />
			<e3t:column property="licenseCode" title="车牌号" />
			<e3t:column property="payDay" title="支付日期" />
			<e3t:column property="payAmount" title="到款金额" />
			<e3t:column property="payPatternShow" title="支付方式" />
			<e3t:column property="chequeCode" title="支票号码" />
			<e3t:column property="remark" title="备注说明" />
			<e3t:column property="realName" title="收款人" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tbAnvancePay.id}','tbAnvancePayForwardPageAction!forwardPage.action',800,600);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbAnvancePay.id}','tbAnvancePayDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>

		</e3t:table>
	</body>
</html>
