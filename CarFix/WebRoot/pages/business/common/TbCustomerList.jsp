<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>客户列表</title>
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
		
		
		function tmCustomerTableConfigHandler(pConfig) {
			pConfig.tbar = [
			{	
				text : '新增',
				iconCls : 'addIcon',
				handler : function() {
					var url = "tbCustomerForwardPageAction!forwardPage.action";
					url += "?types=${param.types}&properties=${param.properties}&e3Table=${e3Table}";
					window.location.href = url;
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbMaintainTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmCustomerTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllTmTbCustomerAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmCustomerTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					/*
					//父窗口所需要填值的对象
					var customerCode = parent.document.getElementById("${customerCode}");
					var customerName = parent.document.getElementById("${customerName}");
					var customerId = parent.document.getElementById("${customerId}");
					*/
					
					
					var props = "${props}";
					
					for(var i=0; i<props.split(",").length; i++){
						var pro = props.split(",")[i];
						switch(pro){
				   		case "customerCode":
					   		var e3customerCode = record.get(pro);
					   		var customerCode = parent.document.getElementById(pro);
					   		if(customerCode != null)
								customerCode.value = e3customerCode;
							continue;
					   	case "customerName":
					   		var e3customerName = record.get(pro);
					   		var customerName = parent.document.getElementById(pro);
					   		if(customerName != null)
								customerName.value = e3customerName;
								continue;
						case "customerId":
							var e3customerId = record.get("id");
							var customerId = parent.document.getElementById(pro);
							if(customerId != null)
								customerId.value = e3customerId;
								continue;
						case "phone":
							var e3phone = record.get(pro);
							var phone = parent.document.getElementById(pro);
							if(phone != null)
								phone.value = e3phone;
								continue;
						case "telephone":
							var e3telephone = record.get(pro);
							var telephone = parent.document.getElementById(pro);
							if(telephone != null)
								telephone.value = e3telephone;
								continue;
						case "contractPerson":
							var e3contractPerson = record.get(pro);
							var contractPerson = parent.document.getElementById(pro);
							if(contractPerson != null)
								contractPerson.value = e3contractPerson;
								continue;
						default:		
						}
					
					}
					/*
					//e3列值
					var e3customerCode = record.get("customerCode");		
					var e3customerName = record.get("customerName");
					var e3customerId = record.get("id");		
					
					//赋值
					if(customerCode != null)
						customerCode.value = e3customerCode;
					if(customerName != null)
						customerName.value = e3customerName;
					if(customerId != null)
						customerId.value = e3customerId;
						
						*/
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
					
				});
		
		}
		
		
	</script>
	<body>
		<s:form action="findAllTmTbCustomerAction.action">
			<s:hidden name="types" ></s:hidden>
			<table>
				<tr>
					<td>
						客户号
					</td>
					<td>	
						<s:textfield name="tbCustomer.customerCode"/>
					</td>
					<td>
						客户姓名
					</td>
					<td>	
						<s:textfield name="tbCustomer.customerName"/>
					</td>
					<td>
						手机号码
					</td>
					<td>	
						<s:textfield name="tbCustomer.telephone"/>
					</td>
					
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="button" value="查询"
							onclick="tmCustomerTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>			
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tmCustomerTable" uri="findAllTmTbCustomerAction.action" var="remVo"
			scope="request" items="customerList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="20" width="800"
			height="320" >
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户名称" />
			<e3t:column property="customerPropertyShow" title="客户类别" />
			<e3t:column property="phone"  title="固定电话" />
			<e3t:column property="telephone"  title="手机号码" />
			<e3t:column property="contractPerson"  title="联系人" />
			<e3t:column property="id" hidden="true" title="客户ID" />
		</e3t:table>
		
	</body> 
</html>
