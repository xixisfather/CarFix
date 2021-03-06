<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工位集增加</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbWorkingCollection.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<s:head theme="ajax"/>
	</head>

	<body>
		<s:form id="formId" action="tbWorkingCollectionInsertAction.action">
			<table>
				<tr>
					<td>
						工位集代号
					</td>
					<td>

						<s:textfield id="workingCollectionCode" name="tbWorkingCollection.workingCollectionCode" /><font color="red">*</font>
					</td>

					<td>
						工位集名称
					</td>
					<td>

						<s:textfield id="workingCollectionName" name="tbWorkingCollection.workingCollectionName" /><font color="red">*</font>

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
							list="#request.tbWorkingInfoMap"
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
							doubleList=""
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
						<input type="button" value="增加" onclick="sendWorkingCollectCodeAndTmCarStationTypeId('workingCollectionCode','carStationTypeId');" />
					
						&nbsp;&nbsp;
						
						<input type="reset" value="重置"/>
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
		//selectAllOptionsExceptSome(document.getElementById('tbWorkingInfoIdLeft'), 'key', '');
		
		selectAllOptionsExceptSome(document.getElementById('tbWorkingInfoIdRight'), 'key', '');
	}
	
	function formSubmit()
	{
		optiontransferselectAll();
		
		var form = document.getElementById('formId');
	
		form.submit();
		
	}
	
	
</script>