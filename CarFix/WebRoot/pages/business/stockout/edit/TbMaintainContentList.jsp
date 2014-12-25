<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>维修发料列表</title>
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
		
		
		function tbMaintainTableConfigHandler(pConfig) {
			pConfig.tbar = [

			{	
				
				text : '新增发料单',
				iconCls : 'addIcon',
				handler : function() {
					//window.location.href = ctx+"/initMaintainPageAction.action";
					addObject('initMaintainPageAction.action',800,450);
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
		
		function tbMaintainTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findMaintainContentAction";
			pConfig.showLoadingMsg = true;
		}
		
		function editPage(maintainCode,entrustId,isConfirm){
			var editUrl = "";
			if(isConfirm == 8000)
				editUrl = "initMaintainContentPageAction.action";
			else
				editUrl = "initConfirmMaintainContentPageAction.action";
				
			editObject('null&isConfirm='+isConfirm+'&maintainCode='+maintainCode+'&entrustId='+entrustId,editUrl,800,450);
		}
		
		function isMaintainEdit(maintainCode,entrustId,isConfirm){
			var url = "isMaintainEditAction.action?maintainCode="+maintainCode;
			var deleteAjax = new Ajax.Request(url, {
				method : 'post',
				onComplete :function getresponinfo(originalRequest){
								var info = originalRequest.responseText;
								if(info == "true"){
									editPage(maintainCode,entrustId,isConfirm);
								}else{
									alert("该发料单已经结算，不能修改！");
								}
							},
				asynchronous : true

			});
		}
		
		
		
	</script>
	<body>
		<s:form action="findMaintainContentAction.action" >
			<table>
				<tr>
					<td>维修发料单号：</td>
					<td><s:textfield  name="tbMaintainPartContent.maintainCode" ></s:textfield></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tbMaintainTableQuery();" />
					</td>
				</tr>
			</table>
		
		</s:form>
		<e3t:table id="tbMaintainTable" uri="findMaintainContentAction.action" var="maintain"
			scope="request" items="maintianVos" mode="ajax" caption="维修发料列表"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="800"
			height="320" >
			<e3t:column property="maintainCode" title="维修发料单号" />
			<e3t:column property="entrustCode" title="委托书号" />
			<e3t:column property="licenseCode" title="车牌号" />
			<e3t:column property="customerName" title="客户姓名" />
			<e3t:column property="totalPrice" title="金额合计" />
			<e3t:column property="confirmName"  title="状态" />
			<e3t:column property="no" title="操作"
				sortable="false">
				<a href="javascript:isMaintainEdit('${maintain.maintainCode}','${maintain.entrustId}','${maintain.isConfirm}');">
					<font color="blue">
						修改
					</font>
					
					<!-- 
						window.location.href='initMaintainContentPageAction.action?maintainCode=${maintain.maintainCode}&entrustId=${maintain.entrustId}'
					 -->
					
				</a>
				  &nbsp;&nbsp;
				  
				 <c:if test="${maintain.isConfirm == 8000 }">
	  				<a
						href="javascript:deleteObject('${maintain.maintainCode}','deleteMaintainContentAction.action');">
						<font color="blue">删除
					    </font>
					</a>
				</c:if>
				
				&nbsp;&nbsp;
				<a href="javascript:editObject('${maintain.maintainCode}','printWXFLAction.action',600,300);">
					<font color="blue">
						打印
					</font>
				</a>
			</e3t:column>
		</e3t:table>
		
	</body> 
</html>
