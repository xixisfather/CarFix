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
		<title>结算单优惠汇总查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbBusinessBalanceGroupFavourFindAction.action">
			<table>
				<tr>
					<td>
						结算单号
					</td>
					<td>
						<s:textfield name="tbBusinessBalance.balanceCode"></s:textfield>
					</td>
					<td>
						委托书号
					</td>
					<td>	
						<s:textfield name="tbBusinessBalance.entrustCode"></s:textfield>
					</td>
					<td>
						结算日期
					</td>
					<td>
						<s:textfield id="bananceDateStart" name="tbBusinessBalance.bananceDateStart">
						
							<s:param name="value"><s:date name="tbBusinessBalance.bananceDateStart" format="yyyy-MM-dd"/></s:param>
						
						</s:textfield>
						
						
						
						<e3c:calendar for="bananceDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>
						<s:textfield id="bananceDateEnd" name="tbBusinessBalance.bananceDateEnd">
						
							<s:param name="value"><s:date name="tbBusinessBalance.bananceDateEnd" format="yyyy-MM-dd"/></s:param>
						
						
						</s:textfield>
						
						
						
						
						<e3c:calendar for="bananceDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="submit" value="查询"/>
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessBalanceTable" uri="tbBusinessBalanceGroupFavourFindAction.action" var="tbBusinessBalance"
			scope="request" items="tbBusinessBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" caption="结算单">
			<e3t:column property="no" title="操作"
				sortable="false" width="150">
				<a href="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceGroupViewAction.action',600,300);">
					<font color="blue">
						结算明细
					</font>
				</a>
			</e3t:column>
			<e3t:column property="balanceCode" title="结算单号" />
			<e3t:column property="entrustCode" title="委托书号" />
			<e3t:column property="stockOutCode" title="销售单号" />
			<e3t:column property="xlgsfFavourAmount" title="工时费优惠" />
			<e3t:column property="xlclfFavourAmount" title="材料优惠" />
			<e3t:column property="xsjeFavourAmount" title="销售优惠" />
			<e3t:column property="totalFavourAmount" title="总计优惠" />
			<e3t:column property="qlAmount" title="去零" />
		</e3t:table>
		<table>
			<tr>
				<td align="left" width="90">工时费优惠合计:</td>
				<td align="left" width="70">${request.totalXLGSF}</td>
				<td align="left" width="90">材料费优惠合计:</td>
				<td align="left" width="70">${request.totalXLCLF}</td>
				<td align="left" width="90">销售优惠合计:</td>
				<td align="left" width="70">${request.totalXSJE}</td>
				<td align="left" width="90">优惠总计:</td>
				<td align="left" width="70">${request.total}</td>
			</tr>
			<tr>
				<td align="left" width="90">去零总计:</td>
				<td align="left" width="70">${request.totalQl}</td>
			</tr>
		</table>
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
		
		<script language="javascript">
function tbBusinessBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBusinessBalanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessBalanceGroupFavourFindAction";
	pConfig.showLoadingMsg = true;
}
			
		</script>
