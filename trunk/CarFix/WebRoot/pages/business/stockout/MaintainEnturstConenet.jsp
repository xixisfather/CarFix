<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>修理工位显示</title>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script language="javascript">
			
function tbFixEntrustContentTableConfigHandler(pConfig) {

	pConfig.tbar = [

	 ];

	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustContentTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "findTbFixEntrustContentAction";
	pConfig.showLoadingMsg = true;
}
// 表格显示前,通常在这注册单击，双击事件
function tbFixEntrustContentTableRenderBeforeHandler(pGrid) {
	pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
		var record = pGrid.getStore().getAt(pRowIndex); // Get the Record

		addPartInfo(record);
	});

}
function addPartInfo(record){	
	var projectType = record.get("projectType");
	parent.synPartProjectType(projectType);
}
		
		</script>
  	</head>
  
  	<body>
    	
    	<s:form action="findTbFixEntrustContentAction.action" >
    		<s:hidden id="flag" name="flag" value="wts">
    		
    		</s:hidden>
    		
    		<s:hidden id="id" name="tbFixEntrust.id">
    		
    		</s:hidden>
    		
    	</s:form>
    	
    	工时费总计:${request.fixHourTotal}
    	
    	<e3t:table id="tbFixEntrustContentTable" uri="findTbFixEntrustContentAction.action" var="tbFixEntrustContent"
			scope="request" items="tbFixEntrustContentList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1100"
			height="320"  varStatus="status">
			<e3t:column property="id" title="ID" hidden="true"/>
			<e3t:column property="stationCode" title="工位代码" />
			<e3t:column property="stationName" title="工位名称"/>
			<e3t:column property="fixHour" title="修理工时"/>
			<e3t:column property="workingHourPrice" title="工时单价"/>
			<e3t:column property="fixHourAll" title="修理工时费"/>
			<e3t:column property="sendHour" title="派工工时"/>
			<e3t:column property="freeSymbolShow" title="免费标识"/>
			<e3t:column property="projectType" title="维修项目类型"/>
			<e3t:column property="zl" title="帐类"/>
			<e3t:column property="xmlx" title="维修工单类型"/>
			<e3t:column property="wxlx" title="维修类型"/>
		</e3t:table>
  	</body>
</html>
