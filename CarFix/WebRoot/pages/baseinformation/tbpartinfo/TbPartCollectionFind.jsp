<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>TbPartCollectionFind.jsp</title>
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
		function tbPartCollectionTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	
				text : '添加配件集',
				iconCls : 'addIcon',
				handler : function() {
					window.location.href = "<%= request.getContextPath() %>/initTbPartInfoCollectionAction.action";
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbPartCollectionTable();
				}
			} ];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartCollectionTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "tbPartCollectionFindAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tbPartCollectionTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					var e3CollectionCode = record.get("collectionCode");// 获取单元格值
					document.getElementById("collCode").value = e3CollectionCode;
					refresh_tbPartInfoTable();
				});
		
		}
		
		
		function deleteCollection(id, actionUrl) {
			Ext.MessageBox.confirm('信息', '您确定要删除？', function(btn) {
				if (btn == 'yes') {
					var date = new Date();
					var time = date.getTime();
					var url = actionUrl + '?id=' + id + '&time=' + time;
					var deleteAjax = new Ajax.Request(url, {
						method : 'post',
						onComplete : delCollectionCallback,
						asynchronous : true
					});
				}
			});
		}
	
		function delCollectionCallback(originalRequest) {
		
			var deleteInfo = originalRequest.responseText.split(',');
			if (deleteInfo[1] == 'success') {
				//Ext.MessageBox.alert('信息', '删除成功');
				
			} else if (deleteInfo[1] == 'failure') {
				Ext.MessageBox.alert('信息', '删除失败,刷新后请重试');
			} else {
				Ext.MessageBox.alert('信息', '操作异常,刷新后请重试');
			}
			eval('refresh_' + deleteInfo[0] + '()');
			refresh_tbPartInfoTable();
		}
		
		
		function deletePartRelation(id,actionUrl) {
			var code = document.getElementById("collCode");
			Ext.MessageBox.confirm('信息', '您确定要删除？', function(btn) {
				if (btn == 'yes') {
					var date = new Date();
					var url = actionUrl + '?id=' + id + '&code=' + code.value;
					var deleteAjax = new Ajax.Request(url, {
						method : 'post',
						onComplete : relationCallBack,
						asynchronous : true
					});
				}
			});
		}
	
		function relationCallBack(originalRequest) {
			var deleteInfo = originalRequest.responseText.split(',');
			
			if (deleteInfo[1] == 'success') {
				//Ext.MessageBox.alert('信息', '删除成功');
				
			} else if (deleteInfo[1] == 'failure') {
				Ext.MessageBox.alert('信息', '删除失败,刷新后请重试');
			} else {
				Ext.MessageBox.alert('信息', '操作异常,刷新后请重试');
			}
			eval('refresh_' + deleteInfo[0] + '()');
			refresh_tbPartCollectionTable();
		}
		
		
		
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "getPartInfoByCodeAction";
			pConfig.showLoadingMsg = true;
		}
	</script>
	<body>
		<s:form action="tbPartCollectionFindAction.action">
			
		</s:form>
		
		
		<e3t:table id="tbPartCollectionTable" uri="tbPartCollectionFindAction.action" var="collectionVo"
			scope="request" items="collctionList" mode="ajax"  caption="配件集"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" >
			<e3t:column property="collectionCode" title="配件集代码" />
			<e3t:column property="createDate"  title="创建日期" >
				<fmt:formatDate value="${collectionVo.createDate}" pattern="yyyy-MM-dd"/>
			</e3t:column>
			<e3t:column property="no" title="相关操作" sortable="false">
				<a
					href="javascript:deleteCollection('${collectionVo.collectionCode}','deleteTbPartCollectionAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
			</e3t:column>
		</e3t:table>
		
		
		
		
		
		<br>
		<br>		
		
		<s:form action="getPartInfoByCodeAction.action">
			<s:hidden name="collectionCode" id="collCode" ></s:hidden>
		</s:form>
		<e3t:table id="tbPartInfoTable" uri="findTbPartInfoAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" caption="配件"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="storeQuantity" title="库存" />
			<e3t:column property="quantity" title="配件数量" />
			
			<e3t:column property="no" title="相关操作" sortable="false">
  				<a
					href="javascript:deletePartRelation('${tbPartInfo.id}','deleteTbPartInfoRelationAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
			</e3t:column>
		</e3t:table>
	</body> 
</html>
