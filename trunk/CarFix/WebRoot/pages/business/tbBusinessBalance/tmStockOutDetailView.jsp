<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>销售出库</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
	</script>
	<body>
		<s:form action="tmStockOutDetailShowAction.action" >
			<table>
				<tr>
					<td>
						销售材料优惠率
					</td>
					<td>
						<input type="text" id="solePartFavourRate" name="solePartFavourRate" value="<s:property value='#request.tbBusinessBalance.solePartFavourRate'/>" disabled="true"/>
					</td>
					<td>
						合计
					</td>
					<td>
						
						<input type="text" id="XSJE" name="solePartTotalAll" value="<s:property value="#request.XSJE"/>" disabled="true"/>
						
					</td>
				</tr>
			</table>
		</s:form>
		
		
		<e3t:table id="tmStockOutDetailTable" uri="tmStockOutDetailShowAction.action" var="detVo"
			scope="request" items="detVos" mode="ajax" caption="采购出库明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="880"
			height="320" >
			<c:choose>
				<c:when test="${detVo.balanceId!=null}">
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
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="userQuantity"  title="出库数量" >
				${detVo.quantity}
			</e3t:column>
			<e3t:column property="costPrice"  title="成本价" >
				<span id="spanCostPrice${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2"  value="${detVo.costPrice}"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="cbxj"  title="成本小计" >
				<span id="spanCbxj${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2"  value="${detVo.costPrice * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="sellPrice"  title="销售价" >
				${detVo.price}
			</e3t:column>
			<e3t:column property="xsxj"  title="销售小计" >
				<span id="spanXsxj${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2"  value="${detVo.price * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column property="partinfoId"  hidden="true"  title="配件ID" />
			<e3t:column property="stockOutDetailId" hidden="true" title="入库明细ID" />
			<e3t:column property="freeName" title="免费标识" />
		</e3t:table>
		
	</body> 
	

</html>
<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		
<script language="javascript">

</script>