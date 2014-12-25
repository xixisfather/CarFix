<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>委托销售列表</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function sellEntrustTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function sellEntrustTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findEntrustSellStockAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form action="TmSellEntrustFindAction.action">
			<table>
				<tr>
					<td>
						优惠金额
					</td>
					<td>
						<input type="text" id="fixPartFavourAmount" name="fixPartFavourAmount" value="<s:property value='#request.fixPartFavourAmount'/>" disabled="true"/>
					</td>
					<td>
						合计
					</td>
					<td>
						<input type="text" id="XLCLF" name="fixPartTotalAll" value="<s:property value="#request.XLCLF"/>" disabled="true">
						
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="sellEntrustTable" uri="findEntrustSellStockAction.action" var="sellStock"
			scope="request" items="maintianvos" mode="ajax"  caption="维修发料列表"
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="1100"
			height="350" >
			<c:choose>
				<c:when test="${sellStock.balanceId!=null}">
					<e3t:column property="no1" title="">
						已结算
					</e3t:column>
				</c:when>
				
				<c:otherwise>
					<e3t:column property="no1" title="">
						<font color="red">未结算</font>
					</e3t:column>
				</c:otherwise>
			</c:choose>
			<e3t:column property="maintainCode" title="发料单号" />
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="70" property="unitName" title="单位" />
			<e3t:column width="70" property="partQuantity" title="发料数量" />
			<e3t:column width="70" property="price" title="发料价" />
			<e3t:column width="70" property="total" title="发料金额" />
			<e3t:column width="70" property="userRealName" title="领用人" />
			<e3t:column width="70" property="freeName" title="免费标识" />
			<e3t:column width="80" property="zl" title="账类" />
			<e3t:column width="80" property="xmlx" title="项目类型" />
		</e3t:table>
		
	</body> 
</html>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/css/tableIcon.css" />
		<script type="text/javascript" src="../../../js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="../../../ext/js/ext-base.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-all.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../../../js/common.js" charset="UTF-8"></script>
		
<script language="javascript">

		
</script>
		
		