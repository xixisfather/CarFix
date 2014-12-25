<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修理工工时统计</title>
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
			pConfig.form = "findTbPartInfoReFlowStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tbPartInfoTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
				var e3PartInfoId = record.get("id");
				document.getElementById("partInfoId").value = e3PartInfoId;
				document.getElementById("detailPartInfoId").value = e3PartInfoId;
				elementTypeTableQuery();
				document.getElementById("typeValue").value = "";
				tbFixShareDetailTableQuery();
			});
	
		}
		
		
		function elementTypeTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function elementTypeTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getSendHourGroupPersonStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		// 表格显示前,通常在这注册单击，双击事件
		function elementTypeTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
				var e3fixPersonId = record.get("fixPersonId");
				document.getElementById("userId").value = e3fixPersonId;
				tbFixShareDetailTableQuery();
			});
	
		}
		
		function tbFixShareDetailTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function tbFixShareDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getSendHourGroupPersonStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		
	</script>
	<body>
	
		
		
		<s:form action="getSendHourGroupPersonStatAction.action">
			<table>
				<tr>
					<td>工位代码:</td>
					<td><s:textfield  name="tbFixShare.tbFixEntrustContent.tbWorkingInfo.stationCode" ></s:textfield></td>
					<td>工位名称:</td>
					<td><s:textfield  name="tbFixShare.tbFixEntrustContent.tbWorkingInfo.stationName" ></s:textfield></td>
					<td>修理工:</td>
					<td>
						<s:select name="tbFixShare.tmUser.id" list="#request.userList" listKey="id" listValue="userName" emptyOption="true" ></s:select>
					</td>  
				</tr>
				<tr>
					<td>修理日期：</td>
					<td  >
						<s:textfield cssStyle="width:100px" id="beginDate" name="tbFixShare.tbFixEntrustContent.tbFixEntrust.fixDateStart" >
							<s:param name="value"><s:date name="tbFixShare.tbFixEntrustContent.tbFixEntrust.fixDateStart" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="beginDate" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield cssStyle="width:100px" id="endDate" name="tbFixShare.tbFixEntrustContent.tbFixEntrust.fixDateEnd" >
							<s:param name="value"><s:date name="tbFixShare.tbFixEntrustContent.tbFixEntrust.fixDateEnd" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="endDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>车牌号:</td>
					<td><s:textfield  name="tbFixShare.tbFixEntrustContent.tbFixEntrust.tbCarInfo.licenseCode" ></s:textfield></td>
				</tr>
				<tr>
					<td>结算日期：</td>
					<td >
						<s:textfield cssStyle="width:100px" id="balanceDateBegin" name="tbFixShare.tbFixEntrustContent.tbFixEntrust.balanceDateBegin" >
							<s:param name="value"><s:date name="tbFixShare.tbFixEntrustContent.tbFixEntrust.balanceDateBegin" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="balanceDateBegin" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield cssStyle="width:100px" id="balanceDateEnd" name="tbFixShare.tbFixEntrustContent.tbFixEntrust.balanceDateEnd" >
							<s:param name="value"><s:date name="tbFixShare.tbFixEntrustContent.tbFixEntrust.balanceDateEnd" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<e3c:calendar for="balanceDateEnd" dataFmt="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td>
						<!--  <input type="button" value="查询" onclick="tbFixShareDetailTableQuery();" />-->
						<input type="submit" value="查询" />
						&nbsp;&nbsp;
						<input type="button" value="打印" onclick="window.open('<%= request.getContextPath() %>/pages/business/statistics/StatFixPersonSendHourPrint.jsp')"/>
					</td>
				</tr>
			</table>
		</s:form>
		<table>
			<tr>
				<td id="statTd" ></td>
				<td id="detailTd" ></td>
			</tr>
		</table>
		<s:form action="getSendHourGroupPersonStatAction.action" ></s:form>
		<e3t:table id="elementTypeTable" uri="getSendHourGroupPersonStatAction.action" var="elementType" renderTo="statTd" toolbarPosition="false" toolbarShowPolicy="false"
		scope="request" items="groupResults" mode="ajax" skin="E3002" width="450" pageSize="10" caption="人员统计"
		height="300" >
			<e3t:column property="sumSendHour" title="派工时间合计" />
			<e3t:column property="fixPersonName" title="修理工" />
			<e3t:column property="sumWorkPriceD" title="派工费合计" />
			<e3t:column property="sumFixPriceD" title="修理工时费合计" />
			<e3t:column property="fixPersonId" hidden="true" title="修理工ID" />
		</e3t:table>
		<e3t:table id="tbFixShareDetailTable" uri="getSendHourGroupPersonStatAction.action" var="reflow" renderTo="detailTd"
		scope="request" items="results" mode="ajax" skin="E3002" width="850" pageSize="10" caption="派工明细"
		height="300" >
	
			<e3t:column width="70" property="sendHour" title="派工时间" />
			<e3t:column width="70" property="userRealName" beanProperty="tmUser.userRealName" title="修理员" />
			<e3t:column width="100" property="stationCode" beanProperty="tbFixEntrustContent.tbWorkingInfo.stationCode"  title="工位代码" />
			<e3t:column width="100" property="ratePrice" beanProperty="tbFixEntrustContent.stationName" title="工位名称" />
			<e3t:column width="60" property="fixHour" beanProperty="tbFixEntrustContent.fixHour" title="修理工时" />
			
			<e3t:column property="workPrice" title="派工费" />
			<e3t:column property="workingHourPrice" beanProperty="tbFixEntrustContent.workingHourPrice"  title="修理工时费" />
			<e3t:column width="120" property="fixDate" beanProperty="tbFixEntrustContent.tbFixEntrust.fixDate"  title="修理日期" >
				<fmt:formatDate value="${reflow.tbFixEntrustContent.tbFixEntrust.fixDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</e3t:column>
			<e3t:column width="100" property="licenseCode" beanProperty="tbFixEntrustContent.tbFixEntrust.tbCarInfo.licenseCode" title="车牌号" />
		</e3t:table>
			
			
		<table>
			<s:iterator value="#request.workTypeHourPriceVos" id="wt"  >
			<tr>
				<td>${wt.workTypeName }</td>
			</tr>
			<tr>
				<td>派工总工时：&nbsp;${wt.totalSendHourD }&nbsp;&nbsp;</td>
				<td>派工总额：&nbsp;${wt.totalSendPriceD }&nbsp;&nbsp;</td>
				<td>修理总工时：&nbsp;${wt.totalFixHourD }&nbsp;&nbsp;</td>
				<td>修理总额：&nbsp;${wt.totalFixPriceD }&nbsp;&nbsp;</td>
			</tr>
			</s:iterator>
			
		</table>
	</body> 
</html>
