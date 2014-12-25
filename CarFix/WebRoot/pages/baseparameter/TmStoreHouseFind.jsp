<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title><s:text name="TM_STORE_HOUSE_TITLE" />
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
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmStoreHouse.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<body>
		<s:form action="tmStoreHouseFindAction.action">
			<table>
				<tr>
					<td>
						<s:text name="TM_STORE_HOUSE_CODE"/>
						<s:textfield name="tmStoreHouse.houseCode"/>
					</td>
					<td>
						<s:text name="TM_STORE_HOUSE_NAME"/>
						<s:textfield name="tmStoreHouse.houseName"/>
					</td>
					<td>
						<s:text name="TM_STORE_HOUSE_ISMIXED"/>
						<s:select name="tmStoreHouse.isMixed" list="#request.isNoMap" emptyOption="true" listKey="key" listValue="value"/>
						
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="<s:text name="TM_USER_BUTTON_QUERY"/>"
							onclick="tmStoreHouseTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>		
					</td>
				</tr>
			</table>
		</s:form>
		<c:set var="TM_STORE_HOUSE_CAPTION">
			<s:text name="TM_STORE_HOUSE_CAPTION" />
		</c:set>
		<c:set var="TM_STORE_HOUSE_CODE">
			<s:text name="TM_STORE_HOUSE_CODE" />
		</c:set>
		<c:set var="TM_STORE_HOUSE_NAME">
			<s:text name="TM_STORE_HOUSE_NAME" />
		</c:set>
		<c:set var="TM_STORE_HOUSE_ISMIXED">
			<s:text name="TM_STORE_HOUSE_ISMIXED" />
		</c:set>
		<c:set var="TM_STORE_HOUSE_REMARK">
			<s:text name="TM_STORE_HOUSE_REMARK" />
		</c:set>
		<c:set var="TM_STORE_HOUSE_OPERATE">
			<s:text name="TM_STORE_HOUSE_OPERATE" />
		</c:set>
		
		<e3t:table id="tmStoreHouseTable" uri="tmStoreHouseFindAction.action" var="tmStoreHouse"
			scope="request" items="tmStoreHouseList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="600"
			height="360" caption="${TM_STORE_HOUSE_CAPTION}">
			<e3t:column property="no" title="${TM_STORE_HOUSE_OPERATE}"
				sortable="false">
				<a href="javascript:editObject('${tmStoreHouse.id}','tmStoreHouseForwardPageAction!forwardPage.action',700,500);"><font color="blue"><s:text
							name="TM_STORE_HOUSE_HREF_UPDATE" />
				</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tmStoreHouse.id}','tmStoreHouseDeleteAction.action');"><font
					color="blue"><s:text name="TM_STORE_HOUSE_HREF_DELETE" />
				</font>
				</a>
			</e3t:column>
			<e3t:column property="houseCode" title="${TM_STORE_HOUSE_CODE}" />
			<e3t:column property="houseName" title="${TM_STORE_HOUSE_NAME}" />
			<e3t:column property="isMixedShow" title="${TM_STORE_HOUSE_ISMIXED}" />
			<e3t:column property="houseRemark" title="${TM_STORE_HOUSE_REMARK}" />
			

		</e3t:table>
	</body>
</html>
