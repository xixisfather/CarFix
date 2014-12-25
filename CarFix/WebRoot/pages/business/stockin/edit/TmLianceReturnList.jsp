<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>借进归还列表</title>
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
		
		
		function lianceReturnTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增借进归还单',
				iconCls : 'addIcon',
				handler : function() {
					//window.location.href = ctx+"/findLinaceRegDetailAction.action";
					addObject('findLinaceRegDetailAction.action',800,450);
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_lianceReturnTable();
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function lianceReturnTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findTmLianceReturnAction";
			pConfig.showLoadingMsg = true;
		}
		
	</script>
	<body>
		<s:form>
			<table>
				<tr>
					<td>借进归还单号：</td>
					<td><s:textfield  name="tmLianceReturn.lianceReturnBill" ></s:textfield></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="lianceReturnTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="lianceReturnTable" uri="findTmLianceReturnAction.action" var="lianceReturn"
			scope="request" items="lianceReturnList" mode="ajax"  caption="借进归还单列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="320" >
			<e3t:column property="lianceReturnBill" title="借进归还单号" />
			<e3t:column property="returnDate"  title="归还日期" >
				<fmt:formatDate value="${lianceReturn.returnDate}" />
			</e3t:column>
			<e3t:column property="createDate"  title="生成日期" >
				<fmt:formatDate value="${lianceReturn.createDate}" />
			</e3t:column>
			<e3t:column property="id" hidden="true" title="借进归还ID" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:editObject('${lianceReturn.id}','initEditLianceReturnPageAction.action',800,450)">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${lianceReturn.id}','deleteLianceReturnAction.action');">
					<font color="blue">删除
				    </font>
				</a>
				&nbsp;&nbsp;
				<a href="javascript:editObject('${lianceReturn.id}','printJJGHAction.action',600,300);">
					<font color="blue">
						打印
					</font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>
