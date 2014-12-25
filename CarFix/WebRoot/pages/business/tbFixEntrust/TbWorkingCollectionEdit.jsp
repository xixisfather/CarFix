<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工位集修改</title>
		
		
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingCollection.js" charset="UTF-8"></script>
		
		<s:head theme="ajax"/>
	</head>

	<body>
		<s:form id="formId" action="tbWorkingCollectionUpdateAction.action">
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

						<s:textfield  id="workingCollectionCode" name="tbWorkingCollection.workingCollectionCode" />
					
						<s:hidden id="workingCollectionCodeCopy"></s:hidden>
					</td> 

					<td>
						工位集名称
					</td>
					<td>

						<s:textfield name="tbWorkingCollection.workingCollectionName" />

					</td>
					<td>
						车型工位
					</td>
					<td>
						<s:select id="tmCarStationTypeId" name="tbWorkingCollection.tmCarStationType.id" list="#request.tmCarStationTypeMap" listKey="key" listValue="value" disabled="true"></s:select>
						<s:hidden id="carStationTypeId" name="tbWorkingCollection.tmCarStationTypeId"></s:hidden>
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
						/>

					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="button" value="修改" onclick="sendWorkingCollectCodeAndTmCarStationTypeId('workingCollectionCode','carStationTypeId');" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
<script language="javascript">
	function optiontransferMoveRight()
	{
		moveSelectedOptions(document.getElementById('tbWorkingInfoIdLeft'), document.getElementById('tbWorkingInfoIdRight'), false, '', '');
	}
	
	function optiontransferMoveLeft()
	{
		moveSelectedOptions(document.getElementById('tbWorkingInfoIdRight'), document.getElementById('tbWorkingInfoIdLeft'), false, '', '');
	}
	
	function optiontransferselectAll()
	{
		selectAllOptionsExceptSome(document.getElementById('tbWorkingInfoIdLeft'), 'key', '');selectAllOptionsExceptSome(document.getElementById('tbWorkingInfoIdRight'), 'key', '');
	}
	
	function formSubmit()
	{
		optiontransferselectAll();
		
		var form = document.getElementById('formId');
	
		form.submit();
		
	}
	
	var workingCollectionCodeCopy = document.getElementById('workingCollectionCodeCopy');
	
	var workingCollectionCode = document.getElementById('workingCollectionCode');
	
	workingCollectionCodeCopy.value = workingCollectionCode.value;
	
	var flag ='edit';
</script>