<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>到款登记减免查询</title>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	</head>
  
  	<body>
    	<s:form action="tbReceiveFreeFindAction.action" >
    		<table>
    			<tr>
    				<td>结算单号</td>
    				<td><s:textfield name="tbReceiveFree.balanceCode"/></td>
    				<td>客户单号</td>
    				<td><s:textfield name="tbReceiveFree.tbCustomer.customerCode"/></td>
    				<td>客户姓名</td>
    				<td><s:textfield name="tbReceiveFree.tbCustomer.customerName"/></td>
    				<td>费用类型</td>
    				<td>
    					<s:select name="tbReceiveFree.amountType" list="#request.amountTypeMap" listKey="key" listValue="value" emptyOption="true"></s:select>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="6" align="center">
    					<input type="button" value="查询"
							onclick="tbReceiveFreeTableQuery();" />
    					&nbsp;&nbsp;
    					<input type="reset" value="重置"/>
    				</td>
    			</tr>
    		</table>
    		
    	</s:form>
    	
    	<e3t:table id="tbReceiveFreeTable" uri="tbReceiveFreeFindAction.action" var="tbReceiveFree"
			scope="request" items="tbReceiveFreeList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320">
			<e3t:column property="no" title="操作" sortable="false" width="100">
				<a href="javascript:deleteObject('${tbReceiveFree.id}','tbReceiveFreeDeleteAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="balanceCode" title="结算单号" />
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="amountTypeShow" title="费用类型" />
			<e3t:column property="feeAmount" title="费用金额" />
			<e3t:column property="amountDate" title="操作日期" width="120">
				<fmt:formatDate value="${tbReceiveFree.amountDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="remark" title="备注" />
		</e3t:table>
  	</body>
</html>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script>
	function tbReceiveFreeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	 ];

	// pConfig.autoExpandColumn='no';
}

function tbReceiveFreeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbReceiveFreeFindAction";
	pConfig.showLoadingMsg = true;
}
	
</script>