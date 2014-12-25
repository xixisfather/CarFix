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
<title>结算单查询</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css" />
</head>

<body>
<table>
	<tr>
		<td>
			<input type="hidden" id="objVal"/>
			
			<input type="hidden" id="objType"/>
		</td>
	</tr>	
	<tr>
		<td><input type="radio" name="js_choose" id="js_jsd" value="2"
			checked="checked" onclick="showElement();" />结算单特殊结算</td>
	</tr>
	<tbody id="jsd_tb">
	<tr>
		<td>
			<s:form action="tbBusinessBalanceSelectAction.action">
	<table>
		<tr>
			<td>结算单号</td>
			<td><s:textfield name="tbBusinessBalance.balanceCode"></s:textfield>
			</td>
			<td>委托书号</td>
			<td><s:textfield name="tbBusinessBalance.entrustCode"></s:textfield>
			</td>
			<td>结算日期</td>
			<td><s:textfield id="bananceDateStart"
				name="tbBusinessBalance.bananceDateStart">
				
				<s:param name="value"><s:date name="tbBusinessBalance.bananceDateStart" format="yyyy-MM-dd"/></s:param>
				
				</s:textfield>
			<e3c:calendar for="bananceDateStart" dataFmt="yyyy-MM-dd" /></td>
			<td>至</td>
			<td><s:textfield id="bananceDateEnd"
				name="tbBusinessBalance.bananceDateEnd">
				
				<s:param name="value"><s:date name="tbBusinessBalance.bananceDateEnd" format="yyyy-MM-dd"/></s:param>
				</s:textfield>
			<e3c:calendar for="bananceDateEnd" dataFmt="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td colspan="8" align="center"><input type="button" value="查询"
				onclick="tbBusinessBalanceTableQuery();" /> <input type="reset"
				value="重置"></td>
		</tr>
	</table>
</s:form>
<e3t:table id="tbBusinessBalanceTable"
	uri="tbBusinessBalanceSelectAction.action" var="tbBusinessBalance"
	scope="request" items="tbBusinessBalanceList" mode="ajax"
	toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
	height="320" caption="结算单" varStatus="status">
	<e3t:column property="checkBalanceId" style="width:25px" title=""
		sortable="false">
		<input type="radio" id="balanceId${status.globalCount}"
			value="${tbBusinessBalance.id}" name="checkBalanceId" onclick="setV('${tbBusinessBalance.id}')"/>
	</e3t:column>

	<e3t:column property="no" title="操作" sortable="false" width="150">
		<a
			href="javascript:editObject('${tbBusinessBalance.id}','tbBusinessBalanceGroupViewAction.action',600,300);">
		<font color="blue"> 结算明细 </font> </a>
	</e3t:column>
	<e3t:column property="balanceCode" title="结算单号" />
	<e3t:column property="entrustCode" title="委托书号" />
	<e3t:column property="stockOutCode" title="销售单号" />
	<e3t:column property="bananceDate" title="结算日期" width="120">
		<fmt:formatDate value="${tbBusinessBalance.bananceDate}"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</e3t:column>
	<e3t:column property="balanceTotalAll" title="结算金额" />
	<e3t:column property="userRealName" title="结算员" />
	<e3t:column property="remark" title="备注" />
</e3t:table>
			
		
		</td>
	</tr>
		
	</tbody>
	
	
	
	
	
	
	
	
	
	
	<tr>
		<td><input type="radio" name="js_choose" id="js_wts" value="1"
			onclick="showElement();" />委托书特殊结算</td>
	</tr>
	<tbody id="wts_tb" style="display: none;">
	<tr>
		<td>
			<s:include value="tbFixEntrustChooseSpecialAction.action"></s:include>
		
		</td>
	</tr>
		
	</tbody>
	
	<tr>
		<td><input type="button" value="下一步" onclick="nextMethod();" /></td>
	</tr>
</table>


<table>
	
</table>
</body>
</html>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ext/css/tableIcon.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ext/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ext/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ext/js/ext-lang-zh_CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/business/tbBusinessSpecialBanance/TbBusinessBalance.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common.js" charset="UTF-8"></script>

<script language="javascript"><!--
	<%List tbBusinessBalanceList = (List) request
					.getAttribute("tbBusinessBalanceList");

			int totalCount = 0;

			if (null != tbBusinessBalanceList) {

				totalCount = tbBusinessBalanceList.size();

			}%>

	<%
	
		List tbFixEntrustList = (List)request.getAttribute("tbFixEntrustList");
	
		int wtsTotalCount = 0;
		
		if(null != tbFixEntrustList){
			
			wtsTotalCount = tbFixEntrustList.size();
			
		}
	%>
			
	function nextMethod()
	{

		//var js_wts=document.getElementById('js_wts');
		
		//var js_jsd=document.getElementById('js_jsd');

		//if(js_jsd.checked){


		//	var selectValue = '';
			
		//	for(var i=1; i <= <%=totalCount%> ;i++)
		//	{
		//		var selectObject = document.getElementById( 'balanceId' + '' + i);
				
		//		if(null==selectObject){
				
		//			continue;
		//		}
				
		//		if(selectObject.checked)
		//		{
		//			selectValue = selectObject.value;
					
		//			break;
		//		}
				
				
		//	}
			
		//	if(''==selectValue)
		//	{
		//		alert('请选择要编辑的结算单');
					
		//		return ;
		//	}
				
			
		//	var date = new Date();
			
		//	var time = date.getTime();
			
		//	window.location.href = 'tbBusinessBalanceEditAction.action?id=' +selectValue+'&flag=jsd'+ '&time='+time;
		//}


		//else{


		//	var selectValue = '';
			
		//	for(var i=1; i <= <%=wtsTotalCount%> ;i++)
		//	{
		//		var selectObject = document.getElementById( 'entrust' + '' + i);
				
		//		if(null==selectObject){
				
		//			continue;
		//		}
				
		//		if(selectObject.checked)
		//		{
		//			selectValue = selectObject.value;
					
		//			break;
		//		}
				
				
		//	}
			
		//	if(''==selectValue)
		//	{
		//		alert('请选择要编辑的委托书');
					
		//		return ;
		//	}
				
			
		//	var date = new Date();
			
		//	var time = date.getTime();
			
		

		//}

		var objVal = document.getElementById('objVal');
		
		var objType = document.getElementById('objType');

		if('' == objVal.value){

			alert('请选择要编辑的结算单或委托书');
			
			return ;
		}


			var date = new Date();
			
			var time = date.getTime();
		
		window.location.href = 'tbBusinessBalanceEditAction.action?id=' +objVal.value+'&flag='+ objType.value+ '&time='+time;
		
		
	
	} 


	function showElement(){

		var wts_tb=document.getElementById('wts_tb');
		
		var jsd_tb=document.getElementById('jsd_tb');
		
		var js_wts=document.getElementById('js_wts');
		
		var js_jsd=document.getElementById('js_jsd');

		var objVal = document.getElementById('objVal');
		
		var objType = document.getElementById('objType');
		
		if(js_wts.checked)
		{
			wts_tb.style.display = "block";
			
			jsd_tb.style.display = "none";

			if('jsd' == objType.value){

				objVal.value = '';

				objType.value = 'wts';
			}
		}
		
		else if(js_jsd.checked)
		{
			wts_tb.style.display = "none";
			
			jsd_tb.style.display = "block";

			if('wts' == objType.value){

				objVal.value = '';

				objType.value = 'jsd';
			}
		}

	}

	function tbBusinessBalanceTableE3ConfigHandler(pConfig) {
		pConfig.emptyReload = false;
		// 参数form,pConfig指定form的参数会提交到后台
		pConfig.form = "tbBusinessBalanceSelectAction";
		pConfig.showLoadingMsg = true;
	}

	function setV(val){

		var js_wts=document.getElementById('js_wts');
		
		var js_jsd=document.getElementById('js_jsd');

		var objVal = document.getElementById('objVal');
		
		var objType = document.getElementById('objType');

		objVal.value = val;

		if(js_wts.checked){

			objType.value = 'wts';
		}

		else{

			objType.value = 'jsd';
			}
	}
</script>