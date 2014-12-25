<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>结算定义</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmBalanceItem.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmBalanceFindAction.action">
		
		</s:form>
		<e3t:table id="tmBalanceTable" uri="tmBalanceFindAction.action" var="tmBalance"
			scope="request" items="tmBalanceList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="360" caption="结算代码信息">
			<e3t:column property="id" hidden="true"/>
			<e3t:column property="balanceName" title="项目名称" />
			<e3t:column property="balanceTypeShow" title="项目类型" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tmBalance.id}','tmBalanceForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmBalance.id}','tmBalanceDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>

		</e3t:table>
		
		<s:form action="tmBalanceItemFindAction.action">
			<s:hidden id="tmBalanceId" name="tmBalanceItem.tmBalance.id"/>
		</s:form>
		<e3t:table id="tmBalanceItemTable" uri="tmBalanceItemFindAction.action" var="tmBalanceItem"
			scope="request" items="tmBalanceItemList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320" caption="结算代码明细信息">
			<e3t:column property="balanceName" title="结算项目" />
			<e3t:column property="itemName" title="明细名称" />
			<e3t:column property="itemCode" title="明细代码" />
			<e3t:column property="formula" title="公式" />
			<e3t:column property="itemRemark" title="备注说明" />
			<e3t:column property="allowHandShow" title="是否手工输入" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${tmBalanceItem.id}','tmBalanceItemForwardPageAction!forwardPage.action',800,500);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmBalanceItem.id}','tmBalanceItemDeleteAction.action');">
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
		</e3t:table>
	</body>
</html>
<script>
		
		Ext.onReady(function(){
			Ext.getCmp('addTmBalanceItem').disable();
		});
</script>