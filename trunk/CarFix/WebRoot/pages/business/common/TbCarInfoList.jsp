<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>车辆列表</title>
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
		
		
		function tmDetTableConfigHandler(pConfig) {
			
			pConfig.tbar = [

			{
				text : '创建客户车辆',
				iconCls : 'addIcon',
				handler : function() {
					addObject('tbCustomerForwardPageAction!forwardPage.action?flag=wtsAdd',
							600, 300);
				}
			}, '', '-', '', 
			
			{
				text : '导入',
				iconCls : 'editIcon',
				handler : function() {
					
					var date = new Date();
				
					var time = date.getTime();
				
					addObject('tbCustomerForwardPageAction!forwardPage.action?flag=importXlsPage2'+'&timeId='+time,600, 300);
				}
			}

			];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllCarInfoAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var carInfoId = parent.document.getElementById("${carInfoId}");
					var licenseCode = parent.document.getElementById("${licenseCode}");
					var factory = parent.document.getElementById("${factory}");
					var modelTypeName = parent.document.getElementById("${modelTypeName}");
					var engineType = parent.document.getElementById("${engineType}");
					var changeBoxType = parent.document.getElementById("${changeBoxType}");
					var purchaseDate = parent.document.getElementById("${purchaseDate}");
					
					
					//e3列值
					var e3carInfoId = record.get("id");
					var e3licenseCode = record.get("licenseCode");		
					var e3factory = record.get("factory");
					var e3modelTypeName = record.get("modelTypeName");
					var e3engineType = record.get("engineType");
					var e3changeBoxType = record.get("changeBoxType");
					var e3purchaseDate = record.get("purchaseDate");		
					
					//赋值
					if(carInfoId != null)
						carInfoId.value = e3carInfoId;
					if(licenseCode != null)
						licenseCode.value = e3licenseCode;
					if(factory != null)
						factory.value = e3factory;
					if(modelTypeName != null)
						modelTypeName.value = e3modelTypeName;
					if(engineType != null)
						engineType.value = e3engineType;
					if(changeBoxType != null)
						changeBoxType.value = e3changeBoxType;
					if(purchaseDate != null)
						purchaseDate.value = e3purchaseDate;
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval(e3table+ '()');
					
				});
		
		}
		
		
	</script>
	<body>
	
		<s:form action="findAllCarInfoAction.action" >
			<table>
				<tr>
					<td>
						车牌号
					</td>
					<td>
						<s:textfield id="licenseCode" name="tbCarInfo.licenseCode"/>
					</td>
					
					<td>
						姓名
					</td>
					<td>
						<s:textfield id="customerName" name="tbCarInfo.tbCustomer.customerName"/>
					</td>
					
					<td>
						手机号
					</td>
					<td>
						<s:textfield id="telephone" name="tbCarInfo.tbCustomer.telephone"/>
					</td>
					
					<td>
						<td>
						<input type="button" value="查询"
							onclick="tmDetTableQuery();" />
					</td>
					</td> 
				</tr>
				
			</table>
		</s:form>
		<!-- 表名tmDetTable已使用 如需更改通知我 -->
		<e3t:table id="tmDetTable" uri="findAllCarInfoAction.action" var="remVo"
			scope="request" items="carInfoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320" >
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="licenseCode" title="牌照号" />
			<e3t:column property="factory" title="厂商" />
			<e3t:column property="modelTypeName" title="车型" />
			<e3t:column property="engineType" title="发动机型号" />
			<e3t:column property="changeBoxType"  title="变速箱型号" />
			<e3t:column property="purchaseDate"  title="购车日期" />
			
			<e3t:column property="id" hidden="true" title="车辆ID" />
		</e3t:table>
		
	</body> 
</html>
