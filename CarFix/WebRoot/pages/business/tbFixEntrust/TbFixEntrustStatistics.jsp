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
		<title>委托书统计查询</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrustStatistics.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbFixEntrustStatisticsAction.action">
			<table>
				<tr>
					<td><s:hidden name="tbFixEntrust.isvalid" value="1"/></td>
				</tr>
				<tr>
					<td>委托书号</td>
					<td><s:textfield id="entrustCode" name="tbFixEntrust.entrustCode"/></td>
					<td>客户号</td>
					<td><s:textfield id="customerCode" name="tbFixEntrust.tbCustomer.customerCode"/></td>
					<td>客户姓名</td>
					<td><s:textfield id="customerName" name="tbFixEntrust.tbCustomer.customerName"/></td>
					<td>手机号码</td>
					<td><s:textfield id="telephone" name="tbFixEntrust.tbCustomer.telephone"/></td>
					<td>服务顾问</td>
					<td><s:select name="tbFixEntrust.tmUser.id" list="#request.tmUserMap" listKey="key" listValue="value" emptyOption="true"></s:select></td>
				</tr>
				<tr>
					<td>车牌号</td>
					<td><s:textfield id="licenseCode" name="tbFixEntrust.tbCarInfo.licenseCode"/></td>
					<td>修理日期</td>
					<td>
						<s:textfield id="fixDateStart" name="tbFixEntrust.fixDateStart">
						
						    <s:param name="value"><s:date name="tbFixEntrust.fixDateStart" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="fixDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>	
						<s:textfield id="fixDateEnd" name="tbFixEntrust.fixDateEnd">
						
						    <s:param name="value"><s:date name="tbFixEntrust.fixDateEnd" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="fixDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						预竣工车辆查询
					</td>
					<td>
						<s:textfield id="minutes" name="tbFixEntrust.minutes" onblur="isPositiveNum();"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="submit" value="查询"/>
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="statisticsTable" uri="tbFixEntrustStatisticsAction.action" var="statisticsTbFixBusinessVo"
			scope="request" items="statisticsTbFixBusinessVoList" mode="ajax"
			skin="E3002" pageSize="10" width="600"
			height="200" toolbarPosition="false" toolbarShowPolicy="false"
			>
			<e3t:column property="modelTypeCode" title="车型代码" />
			<e3t:column property="modelTypeName" title="车型名称" />
			<e3t:column property="countNum" title="台次" />
		</e3t:table>
		
		<table>
			<tr>
				<td align="left" width="100">
					工时费:
				</td>
				<td align="left" width="100">
					${request.fixHourAll}
				</td>
				<td align="left" width="100">
					材料费:
				</td>
				<td align="left" width="100">
					${request.sellPriceAll}
				</td>
				<td align="left" width="100">
					材料成本:
				</td>
				<td align="left" width="100">
					${request.costPriceAll}
				</td>
			</tr>
		</table>
		<e3t:table id="tbFixEntrustTable" uri="tbFixEntrustStatisticsAction.action" var="tbFixEntrust"
			scope="request" items="tbFixEntrustList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" caption="委托书">
			<e3t:column property="no" title="操作" sortable="false">
				<a href="javascript:editObject('${tbFixEntrust.id}','tbFixEntrustViewAction.action',600,300);">
					<font color="blue">
						查看
					</font>
				</a>
			</e3t:column>
			<e3t:column property="entrustCode" title="委托书号" />
			<e3t:column property="fixDate" title="修理日期" width="120">
				<fmt:formatDate value="${tbFixEntrust.fixDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="estimateDate" title="预计竣工日期" width="120">
				<fmt:formatDate value="${tbFixEntrust.estimateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column property="fixType" title="修理类型" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="carId" title="" hidden="true"/>
			<e3t:column property="licenseCode" title="车牌号" />
			<e3t:column property="modelTypeName" title="车型" />
			<e3t:column property="oilMeter" title="油表指示" />
			<e3t:column property="enterStationKilo" title="进站里程" />
			<e3t:column property="outStationKilo" title="出站里程" />
			<e3t:column property="remindMaintainKilo" title="提醒保养公里数" />
			<e3t:column property="remindMaintainDate" title="提醒保养日期">
				<fmt:formatDate value="${tbFixEntrust.remindMaintainDate}" pattern="yyyy-MM-dd"/>
			</e3t:column>
			<e3t:column property="wrongDescribe" title="故障描述" />
			<e3t:column property="beforeFixState" title="送修症状" />
			<e3t:column property="checkResult" title="检查结果" />
			<e3t:column property="remark" title="备注" />
			<e3t:column property="userRealName" title="接待员" />
			<e3t:column property="isFinishShow" title="是否竣工" />
			

		</e3t:table>
		
		
	</body>
</html>
<script>
function tbFixEntrustTableConfigHandler(pConfig) {

	
	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustStatisticsAction";
	pConfig.showLoadingMsg = true;
}

function statisticsTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustStatisticsAction";
	pConfig.showLoadingMsg = true;
}	
	
	
	
	
	
	
	function isPositiveNum()
	{
		var minutes = document.getElementById('minutes');
		
		if(''!=trim(minutes.value))
		{
			if(!validatePositiveNum(minutes.value)){
				
				alert('请输入正确的时间');
			
				minutes.value='';
			}
		}
		
	}


</script>