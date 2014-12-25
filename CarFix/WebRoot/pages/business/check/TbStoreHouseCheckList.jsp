<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>盘点列表</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<s:head theme="ajax" /> 
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	

	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		
		
		function tbStoreHouseCheckTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '扎帐',
				iconCls : 'addIcon',
				handler : function() {
					addObject('initAddTbStoreHouseCheckAction.action',800,450);
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbStoreHouseCheckTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tbStoreHouseCheckTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findTbStoreHouseCheckAction";
			pConfig.showLoadingMsg = true;
		}
		function checkprint(checkid){
			document.getElementById("checkid").value=checkid;
			document.forms["printStoreHouseCheckAction"].submit();
		}
	</script>
	<body>
		<s:form action="printStoreHouseCheckAction.action" target="_ blank" >
			<s:hidden name="checkid" id="checkid" ></s:hidden>
		</s:form>
		<s:form>
			<table>
				<tr>
					<td>盘点单号：</td>
					<td><s:textfield  name="tbStoreHouseCheck.checkCode" ></s:textfield></td>
					<td>盘点日期：</td>
					<td><s:textfield cssStyle="width:100px" id="beginCheckDate" name="tbStoreHouseCheck.beginCheckDate" />
						<e3c:calendar for="beginCheckDate" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield cssStyle="width:100px" id="endCheckDate" name="tbStoreHouseCheck.endCheckDate" />
						<e3c:calendar for="endCheckDate" dataFmt="yyyy-MM-dd" />
					</td>
					<td>扎帐日期：</td>
					<td><s:textfield cssStyle="width:100px" id="beginZzDate" name="tbStoreHouseCheck.beginZzDate" />
						<e3c:calendar for="beginZzDate" dataFmt="yyyy-MM-dd" />
						至
						<s:textfield cssStyle="width:100px" id="endZzDate" name="tbStoreHouseCheck.endZzDate" />
						<e3c:calendar for="endZzDate" dataFmt="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" 
							onclick="tbStoreHouseCheckTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tbStoreHouseCheckTable" uri="findTbStoreHouseCheckAction.action" var="tbStoreHouseCheck"
			scope="request" items="tbStoreHouseChecks" mode="ajax" caption="盘点单列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" >
			<e3t:column property="checkCode" title="盘点单号" />
			<e3t:column property="checkDate"  title="盘点日期" >
				<fmt:formatDate value="${tbStoreHouseCheck.checkDate}" />
			</e3t:column>
			<e3t:column property="storeHouseNames"  title="盘点仓库" />
			<e3t:column property="memo"  title="备注" />
			<e3t:column property="userRealName" beanProperty="tbStoreHouseCheck.tmUser.userRealName" title="经办人" />
			<e3t:column property="zzDate"  title="扎帐日期" >
				<fmt:formatDate value="${tbStoreHouseCheck.zzDate}" />
			</e3t:column>
			<e3t:column property="confirmShowName"  title="单据状态" />
			<e3t:column property="id" hidden="true" title="盘点ID" />
			<e3t:column property="no" title="操作" width="80" sortable="false">
				<c:if test="${tbStoreHouseCheck.isConfirm == 60}">
	  				<a
						href="javascript:deleteObject('${tbStoreHouseCheck.id}','deleteTbStoreHouseCheckAction.action');">
						<font color="blue">删除
					    </font>
					</a>
					&nbsp;&nbsp;
					<a href="javascript:editObject('${tbStoreHouseCheck.id}','tbStoreHouseCheckForwardPageAction.action',600,300);">
						<font color="blue">
							修改
						</font>
					</a>
					&nbsp;&nbsp;
					<a href="javascript:editObject('${tbStoreHouseCheck.id}','viewStoreHouseCheckAction.action',600,300);">
						<font color="blue">
							明细
						</font>
					</a>
					
				</c:if>
				&nbsp;&nbsp;
				<a href="javascript:checkprint('${tbStoreHouseCheck.id }');">
					<font color="blue">
						打印
					</font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>
