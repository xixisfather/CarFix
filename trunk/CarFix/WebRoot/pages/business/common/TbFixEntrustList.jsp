<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>委托书列表</title>
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
		
		
		function tbFixEntrustTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbFixEntrustTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "tbFixEntrustSelectAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tbFixEntrustTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
				
					//父窗口所需要填值的对象
					var entrustId = parent.document.getElementById("${entrustId}");
					var entrustCode =  parent.document.getElementById("${entrustCode}");
					var fixDate = parent.document.getElementById("${fixDate}");
					var fixType = parent.document.getElementById("${fixType}");
					var licenseCode =  parent.document.getElementById("${licenseCode}");
					var customerId =  parent.document.getElementById("${customerId}");
					var customerName =  parent.document.getElementById("${customerName}");
					var customerCode =  parent.document.getElementById("${customerCode}");
					var stationCode = parent.document.getElementById("${stationCode}");
					
					//e3列值
					var e3entrustId = record.get("id");
					var e3entrustCode =  record.get("entrustCode");
					var e3fixDate = record.get("fixDate");
					var e3fixType = record.get("fixType");
					var e3licenseCode =  record.get("licenseCode");
					var e3customerId = record.get("customerId");
					var e3customerName =  record.get("customerName");
					var e3customerCode =  record.get("customerCode");
					var e3stationCode =record.get("modelName");
					
					//赋值
					if(entrustId != null)
						entrustId.value = e3entrustId;
					if(entrustCode != null)
						entrustCode.value = e3entrustCode;
					if(fixDate != null)
						fixDate.value = e3fixDate;
					if(fixType != null)
						fixType.value = e3fixType;
					if(licenseCode != null)
						licenseCode.value = e3licenseCode;
					if(licenseCode != null)
						licenseCode.value = e3licenseCode;
					if(customerName != null)
						customerName.value = e3customerName;
					if(customerId != null)
						customerId.value = e3customerId;
					if(customerCode != null)
						customerCode.value = e3customerCode;
					if(stationCode != null)
						stationCode.value = e3stationCode;
					
					
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
					
				});
		
		}
		
		
	</script>
	<body>
		<s:form action="tbFixEntrustSelectAction.action">
			<table>
				<tr>
					<td>
						<s:hidden name="tbFixEntrust.isvalid"></s:hidden>
						
						<s:hidden name="tbFixEntrust.entrustStatus"></s:hidden>
						
						<s:hidden name="tbFixEntrust.entrustStatusCollection"></s:hidden>
						
						<s:hidden name="tbFixEntrust.entrustType"></s:hidden>
						
					</td>
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
				</tr>
				<tr>
					<td>车牌号</td>
					<td><s:textfield id="licenseCode" name="tbFixEntrust.tbCarInfo.licenseCode"/></td>
					<td>修理日期</td>
					<td>
						<s:textfield id="fixDateStart" name="tbFixEntrust.fixDateStart"></s:textfield>
						<e3c:calendar for="fixDateStart" dataFmt="yyyy-MM-dd"/>
					</td>
					<td>
						至
					</td>
					<td>	
						<s:textfield id="fixDateEnd" name="tbFixEntrust.fixDateEnd"></s:textfield>
						<e3c:calendar for="fixDateEnd" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" onclick="tbFixEntrustTableQuery();"/>
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbFixEntrustTable" uri="tbFixEntrustSelectAction.action" var="tbFixEntrust"
			scope="request" items="tbFixEntrustList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
			height="320" caption="委托书">
			<e3t:column property="no" title="操作"
				sortable="false">
				
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
			<e3t:column property="customerName" beanProperty="tbCustomer.customerName" title="客户姓名" />
			<e3t:column property="licenseCode" title="车牌号" />
			<e3t:column property="oilMeter" title="油表指示" />
			<e3t:column property="enterStationKilo" title="进站里程" />
			<e3t:column property="outStationKilo" title="出站里程" />
			<e3t:column property="remindMaintainKilo" title="提醒保养公里数" />
			<e3t:column property="remindMaintainDate" title="提醒保养日期" width="120">
				<fmt:formatDate value="${tbFixEntrust.remindMaintainDate}" pattern="yyyy-MM-dd"/>
			</e3t:column>
			<e3t:column property="wrongDescribe" title="故障描述" />
			<e3t:column property="beforeFixState" title="送修症状" />
			<e3t:column property="checkResult" title="检查结果" />
			<e3t:column property="remark" title="备注" />
			<e3t:column property="userRealName" title="接待员" />
			<e3t:column property="isFinishShow" title="是否竣工" />
			<e3t:column property="customerCode" beanProperty="tbCustomer.customerCode" title="客户ID" />
			<e3t:column property="customerId" beanProperty="tbCustomer.id" title="客户ID" />
			<e3t:column property="stationCode" hidden="true" title="车型工位" />
			<e3t:column property="modelName" hidden="true" beanProperty="tbCarInfo.tmCarModelType.modelName" title="车型" />
			<e3t:column property="id" hidden="true" title="委托ID" />
		</e3t:table>
		
	</body> 
</html>
