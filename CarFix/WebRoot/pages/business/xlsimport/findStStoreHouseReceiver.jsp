<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库期间收发存</title>
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
		function stStoreHouseReceiverTableConfigHandler(pConfig) {
			pConfig.tbar = [
			        		
    			{	id:'savdj',
    				text : '数据录入',
    				iconCls : 'addIcon',
    				handler : function() {
    					//addObject('stHouseRecforwardPageAction.action',600,300);
						var url = "hasAddStStoreHouseReceiverAction.action";
    					var hasAddAjax = new Ajax.Request(url, {
    						method : 'post',
    						onComplete : hasAddAjaxCallBack,
    						asynchronous : true

    					});
    				}
    			}, '', '-', '',{	
    				text : '刷新',
    				iconCls : 'refreshIcon',
    				handler : function() {
    					refresh_stStoreHouseReceiverTable();
    				}
    			}];
			// pConfig.autoExpandColumn='no';
		}
		
		function stStoreHouseReceiverTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findStStoreHouseReceiverAction";
			pConfig.showLoadingMsg = true;
		}
		
		function hasAddAjaxCallBack(originalRequest) {

			var deleteInfo = originalRequest.responseText.split(',');

			if (deleteInfo[1] == 'success') {
				addObject('stHouseRecforwardPageAction.action',600,300);
			} else {
				Ext.MessageBox.alert('信息', '该月已经添加，只能进行修改');
			}
		}
		
	</script>
	<body>
		
			
		<s:form action="findStStoreHouseReceiverAction.action">
			<table>
				<tr>	
					
					<td>查询日期：</td>
					<td  ><s:textfield cssStyle="width:100px" id="beginDate" name="stStorehouseReceiver.searchBeginDate" />
							<e3c:calendar for="beginDate" dataFmt="yyyy-MM" />
					</td>
					<td>仓库：</td>
					<td><s:select  name="stStorehouseReceiver.tmStoreHouse.id" list="#request.tmStoreHouseList" emptyOption="true" listKey="id" listValue="houseName"/> </td>
				</tr>
				
				<tr>
					<td>
						<input type="button" value="查询" onclick="stStoreHouseReceiverTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
			
		
		
		<e3t:table id="stStoreHouseReceiverTable" uri="findStStoreHouseReceiverAction.action" var="sHouseReceiver"
			scope="request" items="stStoreHouseReceiverList" mode="ajax" caption="仓库期间收发存数据清单" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="920"
			height="320" >
			<e3t:column property="houseName" beanProperty="tmStoreHouse.houseName" title="仓库" />
			<e3t:column property="stockinCostPrice" title="入库成本" />
			<e3t:column property="stockoutCostPrice" title="出库成本" />
			<e3t:column property="sellTotalPrice" title="出库销售金额" />
			<e3t:column property="stockoutTypeName" title="出入库类型" />
			<e3t:column property="QStart" title="期初" />
			<e3t:column property="QFinal" title="期末" />
			<e3t:column  width="60" property="createDate" title="日期" >
				<fmt:formatDate pattern="yyyy-MM"  value="${sHouseReceiver.createDate}" />
			</e3t:column>
			
			<e3t:column property="no" title="操作" sortable="false">
				<fmt:formatDate var="fmtDate"  pattern="yyyy-MM" value="${sHouseReceiver.createDate}"/>
			
				<a href="javascript:editObject('${fmtDate}','stHouseRecforwardPageAction.action',800,500);">
					<font color="blue">
						修改
					</font>
				</a>
				 
			</e3t:column>
		</e3t:table>
	</body> 
</html>
