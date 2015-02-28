<%@page import="com.selfsoft.framework.common.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>结算单汇总查询</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/global.css" />
<script language="javascript">
			function clearForm(){

				var balanceCode = document.getElementById('tbBusinessBalanceGroupFindAction_tbBusinessBalance_balanceCode');

				var entrustCode = document.getElementById('tbBusinessBalanceGroupFindAction_tbBusinessBalance_entrustCode');

				var licenseCode = document.getElementById('tbBusinessBalanceGroupFindAction_tbBusinessBalance_licenseCode');

				var tmUserId = document.getElementById('tbBusinessBalanceGroupFindAction_tbBusinessBalance_tmUser_id');

				var bananceDateStart = document.getElementById('bananceDateStart');

				var bananceDateEnd = document.getElementById('bananceDateEnd');

				balanceCode.value = '';

				entrustCode.value = '';

				licenseCode.value = '';

				tmUserId.value = '';

				bananceDateStart.value = '';

				bananceDateEnd.value = '';
			}
		</script>
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
	<s:form action="tbBusinessBalanceGroupFindAction.action">
		<table>
			<tr>
				<td>结算单号</td>
				<td><s:textfield name="tbBusinessBalance.balanceCode"></s:textfield>
				</td>
				<td>委托书号</td>
				<td><s:textfield name="tbBusinessBalance.entrustCode"></s:textfield>
				</td>
				<td>车牌号</td>
				<td><s:textfield name="tbBusinessBalance.licenseCode"></s:textfield>
				</td>
				<td>结算员</td>
				<td><s:select name="tbBusinessBalance.tmUser.id"
						list="#request.tmUserMap" listKey="key" listValue="value"
						emptyOption="true"></s:select></td>
			</tr>
			<tr>
				<td>服务顾问</td>
				<td><s:select name="tbBusinessBalance.userId"
						list="#request.tmUserMap" listKey="key" listValue="value"
						emptyOption="true"></s:select></td>
				<td>结算日期</td>
				<td><s:textfield id="bananceDateStart"
						name="tbBusinessBalance.bananceDateStart">
						<s:param name="value">
							<s:date name="tbBusinessBalance.bananceDateStart"
								format="yyyy-MM-dd" />
						</s:param>
					</s:textfield> <e3c:calendar for="bananceDateStart" dataFmt="yyyy-MM-dd" /></td>
				<td>至</td>
				<td><s:textfield id="bananceDateEnd"
						name="tbBusinessBalance.bananceDateEnd">
						<s:param name="value">
							<s:date name="tbBusinessBalance.bananceDateEnd"
								format="yyyy-MM-dd" />
						</s:param>
					</s:textfield> <e3c:calendar for="bananceDateEnd" dataFmt="yyyy-MM-dd" /></td>
			
				<td>结算方式</td>
				<td>
					<s:select id="payPattern" name="tbBusinessBalance.payPattern" list="#request.payMap" listKey="key" listValue="value" emptyOption="true"></s:select>
				</td>
			
			</tr>
			<tr>
				<td colspan="8" align="center"><input type="submit" value="查询" />
					<input type="button" value="重置" onclick="clearForm();" /></td>
			</tr>
		</table>
	</s:form>
	<e3t:table id="tbBusinessBalanceTable"
		uri="tbBusinessBalanceGroupFindAction.action" var="tbBusinessBalance"
		scope="request" items="tbBusinessBalanceList" mode="ajax"
		toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
		height="320" caption="结算单">
		<e3t:column property="no" title="操作" sortable="false" width="150">
			<a
				href="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceGroupViewAction.action',600,300);">
				<font color="blue"> 结算明细 </font>
			</a>
				&nbsp;&nbsp;
				<c:choose>
				<c:when test="${tbBusinessBalance.tbFixEntrust!=null}">



					<c:choose>
						<c:when test="${request.comName=='dfbz'}">
							<a href="#" onclick="javascript:window.open('tbBusinessBalanceTemplatePrintAction.action?id=${tbBusinessBalance.id}&companyName=dfbz')"><font color="blue">打印东风标致</font></a>
						</c:when>
						<c:when test="${request.comName=='xtl'}">
							<a href="#" onclick="javascript:window.open('tbBusinessBalanceTemplatePrintAction.action?id=${tbBusinessBalance.id}&companyName=xtl')"><font color="blue">打印雪条龙</font></a>
						</c:when>
						<c:otherwise>
							<a
								href="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceWtsPrintAction.action',600,300);">
								<font color="blue"> 打印 </font>
							</a>
						</c:otherwise>
					</c:choose>


				</c:when>
				<c:otherwise>
					<a
						href="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceXsdPrintAction.action',600,300);">
						<font color="blue"> 打印 </font>
					</a>
				</c:otherwise>
			</c:choose>
		</e3t:column>
		<e3t:column property="balanceCode" title="结算单号" />
		<e3t:column property="entrustCode" title="委托书号" />
		<e3t:column property="stockOutCode" title="销售单号" />

		<e3t:column property="licenseCode" title="车牌号" />
		<e3t:column property="customerName" title="客户名称" />


		<e3t:column property="bananceDate" title="结算日期" width="120">
			<fmt:formatDate value="${tbBusinessBalance.bananceDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</e3t:column>
		<e3t:column property="balanceTotalAll" title="结算金额" />
		<e3t:column property="shouldPayAmount" title="收款金额" />
		<e3t:column property="oweAmount" title="欠款金额" />
		<e3t:column property="workingHourTotalAll" title="修理工时费" />
		<e3t:column property="fixPartTotalAll" title="修理材料费" />
		<e3t:column property="solePartTotalAll" title="销售金额" />
		<e3t:column property="qtfy" title="其他费用" />
		<e3t:column property="djCost" title="单据成本" />
		<e3t:column property="pjCost" title="材料成本" />
		<e3t:column property="userRealNameServer" title="服务顾问" />
		<e3t:column property="userRealName" title="结算员" />
		<e3t:column property="remark" title="备注" />
	</e3t:table>


	<table border="1">
		<tr>
			<td width="800" height="80" valign="middle">


				<table>

					<tr>
						<td align="left" width="70">业务笔数：</td>
						<td align="left" width="70">${request.totalCount}</td>
						<td align="left" width="70">总金额:</td>
						<td align="left" width="70">${request.total}</td>
						<td align="left" width="70">收款金额:</td>
						<td align="left" width="70">${request.payed}</td>

						<td align="left" width="70">工时费:</td>
						<td align="left" width="70">${request.fixHour}</td>
					</tr>
					<tr>
						<td align="left" width="70">材料费:</td>
						<td align="left" width="70">${request.fixPart}</td>
						<td align="left" width="70">销售费:</td>
						<td align="left" width="70">${request.solePart}</td>

						<td align="left" width="70">其他费:</td>
						<td align="left" width="70">${request.other}</td>
						<td align="left" width="70">欠款金额:</td>
						<td align="left" width="70">${request.owe}</td>
					</tr>

					<tr>
						<td align="left" width="70">材料成本费:</td>
						<td align="left" width="70">${request.costTotal}</td>

						<td>
						<td align="left" width="70">单据成本费:</td>
						<td align="left" width="70">${request.djcb}</td>
						</td>
					</tr>
					<tr>
						<td align="left" width="70">毛利:</td>
						<td align="left" width="70">${request.benifit}</td>
					</tr>
				</table>




			</td>
		</tr>
	</table>
</body>
</html>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
function tbBusinessBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '打印汇总报表',
		iconCls : 'editIcon',
		handler : function() {
			//addObject('<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalanceGroupPrint.jsp',600,300);

			window.open('<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalanceGroupPrint.jsp');
		}
	}, '', '-', '', 
	{	
		text : '导出汇总报表',
		iconCls : 'viewIcon',
		handler : function() {
			
			var date = new Date();
			
			var time = date.getTime();
			
			window.open('tbBusinessBalanceGroupExportXlsAction.action?timeId='+time,'_blank');
		}
	}, '', '-', '', 
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
	pConfig.form = "tbBusinessBalanceGroupFindAction";
	pConfig.showLoadingMsg = true;
}
			
		</script>
