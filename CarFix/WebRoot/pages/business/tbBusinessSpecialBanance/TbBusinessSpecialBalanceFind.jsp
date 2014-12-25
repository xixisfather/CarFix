<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.selfsoft.framework.common.Constants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>特殊结算单查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessSpecialBanance/TbBusinessSpecialBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
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
	    
	      //request.setAttribute("comName", "xtl");
	    %>
	
	
		<s:form action="tbBusinessSpecialBalanceFindAction.action">
			<table>
				<tr>
					<td><s:hidden name="tbBusinessSpecialBalance.specialType" value="1"></s:hidden> </td>
				</tr>
				<tr>
					
					<td>
						流水号
					</td>
					<td>
						<s:textfield name="tbBusinessSpecialBalance.editCode"></s:textfield>
					</td>
					<!--<td>
						结算单号
					</td>
					<td>
						<s:textfield name="tbBusinessSpecialBalance.tbBusinessBalance.balanceCode"></s:textfield>
					</td>
					--><td>
						委托书号
					</td>
					<td>	
						<s:textfield name="tbBusinessSpecialBalance.entrustCode"></s:textfield>
					</td>
				</tr>	
				<tr>	
					<td>
						结算日期
					</td>
					<td>
						<s:textfield id="bananceDateStart" name="tbBusinessSpecialBalance.bananceDateStart">
						<s:param name="value">
							<s:date name="tbBusinessSpecialBalance.bananceDateStart"
								format="yyyy-MM-dd" />
						</s:param>
						</s:textfield>
						<e3c:calendar for="bananceDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>
						<s:textfield id="bananceDateEnd" name="tbBusinessSpecialBalance.bananceDateEnd">
						<s:param name="value">
							<s:date name="tbBusinessSpecialBalance.bananceDateStart"
								format="yyyy-MM-dd" />
						</s:param>
						</s:textfield>
						<e3c:calendar for="bananceDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						车牌号
					</td>
					<td>
						<s:textfield id="licenseCode" name="tbBusinessSpecialBalance.licenseCode"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" value="查询" onclick="tbBusinessSpecialBalanceTableQuery();"/>
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbBusinessSpecialBalanceTable" uri="tbBusinessSpecialBalanceFindAction.action" var="tbBusinessSpecialBalance"
			scope="request" items="tbBusinessSpecialBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" caption="特殊编辑单">
			<e3t:column property="no" title="操作" sortable="false" width="200">
			
				<a href="javascript:editObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
				  
				<a
					href="javascript:deleteObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceDeleteAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
				&nbsp;&nbsp;
				<a href="javascript:editObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceForwardViewPageAction!forwardPage.action',600,300);">
					<font color="blue">
						结算明细
					</font>
				</a> 
				&nbsp;&nbsp;
				<c:choose>
					<c:when test="${tbBusinessSpecialBalance.entrustCode!=null}">
					
						<c:choose>
						    <c:when test="${request.comName=='dfbz'}">
							    <a href="#" onclick="javascript:window.open('tbBusinessSpecialBalanceTemplatePrintAction.action?id=${tbBusinessSpecialBalance.id}&companyName=dfbz')"><font color="blue">打印东风标致</font></a>
						    </c:when>
						    
						    <c:when test="${request.comName=='xtl'}">
							    <a href="#" onclick="javascript:window.open('tbBusinessSpecialBalanceTemplatePrintAction.action?id=${tbBusinessSpecialBalance.id}&companyName=xtl')"><font color="blue">打印雪条龙</font></a>
						    </c:when>
						     
						    <c:otherwise>
						         <a href="javascript:editObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceWtsPrintAction.action',600,300);">
							     <font color="blue">
								      打印
							     </font>
							     </a>
						    
						    </c:otherwise> 
						
						
						</c:choose>
						
						
						
						  
					</c:when>
						
					<c:otherwise>
						<a href="javascript:editObject('${tbBusinessSpecialBalance.id}','tbBusinessSpecialBalanceXsdPrintAction.action',600,300);">
							<font color="blue">
								打印
							</font>
						</a> 
					</c:otherwise>
				</c:choose>
			</e3t:column>
		<e3t:column property="editCode" title="流水号" />
			<e3t:column property="licenseCode" beanProperty="tbCarInfo.licenseCode" title="车牌号" />
			<e3t:column property="customerName" beanProperty="tbCustomer.customerName" title="客户姓名" />
			<e3t:column property="balanceCode" title="结算单号" />
			<e3t:column property="entrustCode" title="委托书号" />
			<e3t:column property="stockOutCode" title="销售单号" />
			<e3t:column property="bananceDate" title="结算日期" width="120">
				<fmt:formatDate value="${tbBusinessSpecialBalance.bananceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>	
			<e3t:column property="balanceTotalAll" title="结算金额" />
			<e3t:column property="userRealName" title="结算员" />
			
		</e3t:table>
		
		<table>
			<tr>
				<td width="70">
					业务笔数:
				</td>
				
				<td width="70">
				${request.totalSize}
				</td>
				
				<td width="70">
					总金额:
				</td>
				<td width="70">
				${request.totalAll}
				</td>
			</tr>
		</table>
	</body>
</html>
