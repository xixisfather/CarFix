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
    	
    	<s:form action="tbFixEntrustContentFindAction.action" >
    		<table>
    			<tr>
    				<td>
						修理工时优惠率    				
    				</td>
    				<td>
    					<input type="text" id="workingHourFavourRate" name="workingHourFavourRate" onblur="setWorkingHourFavourRate();" value="${request.workingHourFavourRate}"/>
    				</td>
    				<td>
						合计 				
    				</td>
    				<td>
    					<s:textfield id="XLGSF" name="tbFixEntrust.workingHourTotalAll" readonly="true"></s:textfield>
    				
    					<s:hidden id="XLGSF_back" name="tbFixEntrust.workingHourTotalAllOri"></s:hidden>
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
			<e3t:column property="freeSymbolShow" title="免费标识" style="background-color:#CCCCFF"/>
			<e3t:column property="projectType" title="维修项目类型"/>
			<e3t:column property="zl" title="帐类"/>
			<e3t:column property="xmlx" title="维修工单类型"/>
			<e3t:column property="wxlx" title="维修类型"/>
		</e3t:table>
  	</body>
</html>
	<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script language="javascript">
			
function tbFixEntrustContentTableConfigHandler(pConfig) {

	pConfig.tbar = [

	 ];

	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustContentTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustContentFindAction";
	pConfig.showLoadingMsg = true;
}
			
function setWorkingHourValue()
{
    
	var workingHourFavourRate_temp = document.getElementById('workingHourFavourRate');
	
	var workingHourFavourRate = parent.document.getElementById('workingHourFavourRate');
	
	var XLGSF_temp = document.getElementById('XLGSF');
	
	var XLGSF = parent.document.getElementById('XLGSF');
	
	workingHourFavourRate.value = workingHourFavourRate_temp.value;
	
	XLGSF.value = XLGSF_temp.value;
}

function setWorkingHourFavourRate()
{
	var workingHourFavourRate_temp = document.getElementById('workingHourFavourRate');
	
	var workingHourFavourRate = parent.document.getElementById('workingHourFavourRate');
	
	var XLGSF_temp = document.getElementById('XLGSF');
	
	var XLGSF = parent.document.getElementById('XLGSF');
	
	var XLGSF_back = document.getElementById('XLGSF_back');
	
	clearNoNum22(workingHourFavourRate_temp,0,1);
	
	if(isNumber(workingHourFavourRate_temp.value))
	{
		XLGSF_temp.value = formatFloat((1-workingHourFavourRate_temp.value)*XLGSF_back.value,2);
	}
	else
	{
		
		XLGSF_temp.value = XLGSF_back.value;
	}
	
	setWorkingHourValue();
}

setWorkingHourValue();


</script>