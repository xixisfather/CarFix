<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>职工列表</title>
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
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tmDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findMaintainTmUserAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmDetTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
					//父窗口所需要填值的对象
					var userId = parent.document.getElementById("${userId}");
					var userRealName = parent.document.getElementById("${userRealName}");
					
					//e3列值
					var e3userId = record.get("id");		
					var e3userRealName = record.get("userRealName");
					
					//赋值
					if(userId != null)
						userId.value = e3userId;
					if(userRealName != null)
						userRealName.value = e3userRealName;
					parent.extWindow.hide();
					
					var e3table = "${e3Table}";
					if(e3table != 'null' && e3table != ""  )
						parent.eval('refresh_' +e3table+ '()');
					
				});
		
		}
		
		
	</script>
	<body>
	
		<s:form action="findMaintainTmUserAction.action">
			<table>
				<tr>
					<td>职工名称：</td>
					<td><s:textfield  name="tmUser.userName"></s:textfield>  </td>
					<td>部门：</td>
					<td><s:select list="#request.depts" name="tmUser.tmDepartment.id" emptyOption="true"  listKey="id" listValue="deptName" ></s:select></td>
    				<td>工种：</td>
    				<td><s:select list="#request.workTypes" name="tmUser.tmWorkType.id"  listKey="id" emptyOption="true"  listValue="workName" ></s:select></td>
					
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmDetTableQuery();" />
					</td>
				</tr>


			</table>


		</s:form>	
		<e3t:table id="tmDetTable" uri="findMaintainTmUserAction.action" var="remVo"
			scope="request" items="tmUsers" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="800"
			height="320" >
			<e3t:column property="userRealName" title="职工" width="150"/>
			<e3t:column property="deptName"  title="部门" width="150"/>
			<e3t:column property="telephone" title="手机" width="150"/>
			<e3t:column property="tecLevel" title="技术等级" width="150"/>
			<e3t:column property="workName"  title="工种" width="150"/>
			<e3t:column property="id" hidden="true" title="用户ID" width="150"/>
		</e3t:table>
		
	</body> 
</html>
