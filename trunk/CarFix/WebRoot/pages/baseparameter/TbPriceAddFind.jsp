<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>加价金额查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
		<SCRIPT type="text/javascript">
			function tbPriceAddTableConfigHandler(pConfig) {

				pConfig.tbar = [
			
				{	
					
					text : '新增',
					iconCls : 'addIcon',
					handler : function() {
						addObject('tbPriceAddForwardPageAction!forwardPage.action',600,300);
					}
				}, '', '-', '', {
					text : '刷新',
					iconCls : 'refreshIcon',
					handler : function() {
						refresh_tbPriceAddTable();
					}
				} ];
			
				// pConfig.autoExpandColumn='no';
			}
			
			function tbPriceAddTableE3ConfigHandler(pConfig) {
				pConfig.emptyReload = false;
				// 参数form,pConfig指定form的参数会提交到后台
				pConfig.form = "findTbPriceAddAction";
				pConfig.showLoadingMsg = true;
			}
			
			function synPartInfoSellPrcie(id,actionUrl){
				Ext.MessageBox.confirm('信息', '您确定要同步配件销售价?', function(btn) {
					if (btn == 'yes') {
						var url = actionUrl+"?id="+id ;
						var deleteAjax = new Ajax.Request(url, {
							method : 'post',
							onComplete : getResInfo,
							asynchronous : true
						});
					}
				});
			}
			
			function getResInfo(originalRequest) {

				var info = originalRequest.responseText;
			
				if (info == 'success') {
					Ext.MessageBox.alert('信息', '同步配件销售价成功！');
				}else{
					Ext.MessageBox.alert('信息', '同步失败，请参看相关失败日志');
				}
				
			}
		</SCRIPT>
	</head>

	<body>
		<s:form action="findTbPriceAddAction.action">
		
		</s:form>
		<e3t:table id="tbPriceAddTable" uri="findTbPriceAddAction.action" var="tbPriceAdd"
			scope="request" items="tmPriceAddList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="360" caption="加价金额">
			<e3t:column property="schemeName" title="方案名称" />
			<e3t:column property="priceRegion" title="价格区间" />
			<e3t:column property="price" title="加价金额" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a
					href="javascript:synPartInfoSellPrcie('${tbPriceAdd.id}','synPriceAddSellPriceAction.action');">
					<font color="blue">同步配件销售价
				    </font>
				</a>
				 &nbsp;&nbsp;
				<a href="javascript:editObject('${tbPriceAdd.id}','tbPriceAddForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbPriceAdd.id}','deleteTbPriceAddAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			
			
			
		</e3t:table>
	</body>
</html>
