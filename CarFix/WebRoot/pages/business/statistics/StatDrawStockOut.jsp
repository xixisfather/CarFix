<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>内部领用查询</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getDrawStockOutStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function tmDrawGroupTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getDrawStockOutStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function query(){
			tmDrawGroupTableQuery();
			tbPartInfoTableQuery();
		}
		
	</script>
	<body>
		
			<table>
				<tr>
					<td valign="top" >
						<s:form action="getDrawStockOutStatAction.action">
						<table>
							<tr>	
								<td>领用日期：</td>
								<td  ><s:textfield cssStyle="width:100px" id="beginDate" name="tmDrawStatVo.beginDate" />
									<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
									至
									<s:textfield cssStyle="width:100px" id="endDate" name="tmDrawStatVo.endDate" />
									<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td><input type="radio" name="tmDrawStatVo.querySelect" value="0" checked="checked" /> 领用人：</td>
								<td><s:select name="tmDrawStatVo.drawPeopleId" list="#request.tmusers" headerKey="" headerValue="所有" listKey="id" listValue="userRealName"/></td>
							</tr>
							<tr>
								<td><input type="radio" name="tmDrawStatVo.querySelect" value="1" /> 部门：</td>
								<td><s:select name="tmDrawStatVo.deptId" list="#request.tmDepartments" headerKey="" headerValue="所有" listKey="id" listValue="deptName"/></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="查询" onclick="query();" />

								</td>
							</tr>
						</table>
						</s:form>
					</td  >
					
					<td id="tt" valign="top" >
					
					</td>
				</tr>
			</table>
		
		
					<e3t:table id="tmDrawGroupTable" uri="getDrawStockOutStatAction.action" var="tbPartInfo" renderTo="tt"
						scope="request" items="groupResults" mode="ajax"  caption=""
						toolbarPosition="bottom" skin="E3002" pageSize="10" width="550"
						height="200" >
						<e3t:column property="deptName" title="部门" />
						<e3t:column property="drawPeopleName" title="领用人" />
						<e3t:column property="costPrice" title="成本金额" />
						<e3t:column property="sellPrice" title="销售金额" />
					</e3t:table>
		
		<e3t:table id="tbPartInfoTable" uri="getDrawStockOutStatAction.action" var="tbPartInfo"
			scope="request" items="results" mode="ajax" caption="配件领用" varStatus="userStatus"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="920"
			height="320" >
			<e3t:column property="stockOutCode" title="领用单" />
			<e3t:column  width="60" property="stockOutDate" title="领用日期" >
				<fmt:formatDate value="${tbPartInfo.stockOutDate}" />
			</e3t:column>
			<e3t:column property="houseName" title="仓库" />
			<e3t:column width="60" property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="50" property="unitName" title="单位" />
			<e3t:column width="70" property="costPrice" title="成本价" />
			<e3t:column width="70" property="sellPrice" title="销售价" />
			<e3t:column property="quantity" title="领用数量" />
			<e3t:column width="60" property="drawPeopleName" title="领用人" />
			<e3t:column property="deptName" title="部门" />
			
			<c:if test="${userStatus.last}">
				<e3t:appendRow>
		      	<e3t:attribute name="style" value="background-color:#CCCCFF"/>
		      		<e3t:addCell>
		        		<font color="blue" >成本金额合计:</font>
		     	 	</e3t:addCell>
		   			<e3t:addCell>
		   				${countVo.totalCostPrice }
				    </e3t:addCell>
		     	 	<e3t:addCell />
		     	 	<e3t:addCell />
			      	<e3t:addCell>
			       		<font color="blue" >销售金额合计:</font>
			      	</e3t:addCell>
				    <e3t:addCell>
				    	${countVo.totalSellPrice }
				    </e3t:addCell>
			    </e3t:appendRow>
		  	 </c:if>
		</e3t:table>
		
		
	</body> 
</html>
