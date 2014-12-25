<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>仓库列表</title>
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
		function tmPartTypeTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function tmPartTypeTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findCommTmPartTypeAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tmPartTypeTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record

				addPartInfo(record);
			});
	
		}
		function addPartInfo(record){
			
			//处理数据
			var e3typeId = record.get("id");
			var e3TypeCode = record.get("typeCode");
			var e3TypeName = record.get("typeName");

			var tmPartTypeId = parent.document.getElementById("${tmPartTypeId}");
			var partTypeName = parent.document.getElementById("${partTypeName}");
			if(tmPartTypeId != null)
				tmPartTypeId.value = e3typeId;
			if(partTypeName != null)
				partTypeName.value = e3TypeName;
			if(parent.extWindow != null)
				parent.extWindow.hide();
					
			var e3table = "${e3Table}";
			if(e3table != 'null' && e3table != ""  )
				parent.eval(e3table+ '()');
				
			parent.addStoreHouse();
			
		}
		
		
	</script>
	<body  >
		<s:form action="findCommTmPartTypeAction.action">
			<table>
				<tr>	
					<td>
						类型代码
						<s:textfield name="tmPartType.typeCode"/>
					</td>
					<td>
						类型名称
						<s:textfield name="tmPartType.typeName"/>
					</td>				
				</tr>
				
				<tr>
					<td>
						<input type="button" value="查询" onclick="tmPartTypeTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		
		<e3t:table id="tmPartTypeTable" uri="findCommTmPartTypeAction.action" var="tmPartType"
			scope="request" items="tmPartTypeList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="650"
			height="320" >
		<e3t:column property="typeCode" title="类型代码" />
			<e3t:column property="typeName" title="类型名称"/>
			<e3t:column property="typeRemark" title="类型说明"/>
			<e3t:column property="id" hidden="true" title="配件类型id" />
		</e3t:table>
		
		
	</body>  
		
		
</html>
