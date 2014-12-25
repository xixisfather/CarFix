<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>修理工位显示</title>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	</head>
  
  	<body>
    	
    	<s:form action="tbFixEntrustContentViewAction.action" >
    		<table>
    			<tr>
    				<td>
						优惠金额			
    				</td>
    				<td>
    					<input type="text" id="fixContentFavourAmount" name="fixContentFavourAmount" value="<s:property value='#request.fixContentFavourAmount'/>" disabled="true"/>
    				</td>
    				<td>
						合计 				
    				</td>
    				<td>
    					<input id="XLGSF" name="tbFixEntrust.workingHourTotalAll" readonly="true" value="<s:property value='#request.XLGSF'/>" disabled="true"/>
    				</td>
    			</tr>
    		</table>
    	</s:form>
    	
    	<e3t:table id="tbFixEntrustContentTable" uri="tbFixEntrustContentFindAction.action" var="tbFixEntrustContent"
			scope="request" items="tbFixEntrustContentList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="500" width="900"
			height="320"  varStatus="status">
			<c:choose>
				<c:when test="${tbFixEntrustContent.balanceId!=null}">
					<e3t:column property="no1" title="">
						已结算
					</e3t:column>
				</c:when>
				
				<c:otherwise>
					<e3t:column property="no1" title="">
						<font color="red">未结算</font>
					</e3t:column>
				</c:otherwise>
			</c:choose>
			
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
	<link rel="stylesheet" type="text/css"
			href="../../../ext/css/tableIcon.css" />
		<script type="text/javascript" src="../../../js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="../../../ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="../../../ext/js/ext-base.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-all.js"></script>
		<script type="text/javascript" src="../../../ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../../../js/common.js" charset="UTF-8"></script>
		
<script language="javascript">
			
function tbFixEntrustContentTableConfigHandler(pConfig) {

	pConfig.tbar = [

	 ];

	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustContentTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustContentViewAction";
	pConfig.showLoadingMsg = true;
}

</script>