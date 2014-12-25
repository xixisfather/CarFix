<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>车型工位
	</title>
	
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmCarStationType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
	<body>
		<s:form action="tmCarStationTypeFindAction.action">
			<table>
				<tr>
					<td>
						<s:text name="TM_FLAG_CAR_CODE"/>
						<s:textfield name="tmCarStationType.stationCode"/>
					</td>
					
				</tr>
				<tr>
					<td align="center">
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmCarStationTypeTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>		
					</td>
				</tr>
			</table>
		</s:form>
		<c:set var="TM_FLAG_CAR_CAPTION">
			<s:text name="TM_FLAG_CAR_CAPTION" />
		</c:set>
		<c:set var="TM_FLAG_CAR_CODE">
			<s:text name="TM_FLAG_CAR_CODE" />
		</c:set>
	
		<c:set var="TM_FLAG_CAR_REMARK">
			<s:text name="TM_FLAG_CAR_REMARK" />
		</c:set>
		<c:set var="TM_STORE_HOUSE_OPERATE">
			<s:text name="TM_STORE_HOUSE_OPERATE" />
		</c:set>
		
		<e3t:table id="tmCarStationTypeTable" uri="tmCarStationTypeFindAction.action" var="tmCarStationType"
			scope="request" items="tmCarStationTypeList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="360" caption="${TM_FLAG_CAR_CAPTION}">
			<e3t:column property="no" title="${TM_STORE_HOUSE_OPERATE}"
				sortable="false">
				<a href="javascript:editObject('${tmCarStationType.id}','tmCarStationTypeForwardPageAction!forwardPage.action',600,300);"><font color="blue"><s:text
							name="TM_STORE_HOUSE_HREF_UPDATE" />
				</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmCarStationType.id}','tmCarStationTypeDeleteAction.action');"><font
					color="blue"><s:text name="TM_STORE_HOUSE_HREF_DELETE" />
				</font>
				</a>
			</e3t:column>
			
			<e3t:column property="stationCode" title="${TM_FLAG_CAR_CODE}" />
			<e3t:column property="stationRemark" title="说明" />
			
		</e3t:table>
	</body>
</html>
