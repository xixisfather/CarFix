<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工位集查看</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<s:head theme="ajax"/>
	</head>

	<body>
		<s:form action="tbWorkingCollectionUpdateAction.action">
			<table>
				<tr>
					<td>
						<s:hidden name="tbWorkingCollection.id"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>
						工位集代号
					</td>
					<td>

						<s:textfield name="tbWorkingCollection.workingCollectionCode" disabled="true"/>
					</td>

					<td>
						工位集名称
					</td>
					<td>

						<s:textfield name="tbWorkingCollection.workingCollectionName" disabled="true"/>

					</td>
					<td>
						车型工位
					</td>
					<td>
						<s:select id="tmCarStationTypeId" name="tbWorkingCollection.tmCarStationType.id" list="#request.tmCarStationTypeMap" listKey="key" listValue="value" disabled="true"></s:select>
						<s:hidden name="tbWorkingCollection.tmCarStationTypeId"></s:hidden>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						
						<s:optiontransferselect
							id="tbWorkingInfoIdLeft"
							name="tbWorkingInfoIdLeft"
							leftTitle="工时工位信息" 
							rightTitle="包含于该工位集的工时工位"
							list="#request.tbWorkingInfoLeftMap"
							multiple="true" 
							addToLeftLabel="左移" 
							addAllToLeftLabel="全部左移"
							addAllToRightLabel="全部右移"
							addToRightLabel="右移" 
							allowSelectAll="false"
							allowAddAllToLeft="false"
							allowAddAllToRight="false"
							allowAddToLeft="false"
							allowAddToRight="false"
							selectAllLabel="全部选择"
							allowUpDownOnLeft="false"
							allowUpDownOnRight="false"
							headerKey=""
							listKey="key"
							listValue="value"
							headerValue="------------------------------------------------------------" 
							emptyOption="false"
							doubleList="#request.tbWorkingInfoRightMap"
							doubleId="tbWorkingInfoIdRight"
							doubleName="tbWorkingInfoIdRight" 
							doubleHeaderKey=""
							doubleHeaderValue="------------------------------------------------------------" 
							doubleEmptyOption="false"
							doubleMultiple="true" 
							doubleListKey="key"
							doubleListValue="value"
							ondblclick="optiontransferMoveRight();"
							doubleOndblclick="optiontransferMoveLeft();"
							disabled="true"
							doubleDisabled="true"
						/>

					</td>
				</tr>
				
			</table>
		</s:form>
	</body>
</html>
	