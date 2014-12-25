<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>派工</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbFixEntrustShareCreateAction.action">
			<table>
				<tr>
					<s:hidden id="index" name="tbFixShare.index"/>
					<s:hidden id="fixPersonIds_edit" name="tbFixShare.fixPersonIds"></s:hidden>
					<s:hidden id="fixPersons_edit" name="tbFixShare.fixPersons"></s:hidden>
				</tr>
				<tr>
					<td>
						修理工位
					</td>
					<td>	
						<s:textfield id="stationCode" name="tbFixShare.tbWorkingInfo.stationCode" readonly="true"></s:textfield>
					</td>
					<td>
						修理内容
					</td>
					<td>
						<s:textfield id="stationName" name="tbFixShare.tbWorkingInfo.stationName" readonly="true"></s:textfield>
					</td>
					
				</tr>
				
				<tr>
					<td>
						修理工时
					</td>
					<td>	
						<s:textfield id="fixHour" name="tbFixShare.tbWorkingInfo.fixHour" readonly="true"></s:textfield>
					</td>
					<td>
						派工工时
					</td>
					<td>	
						<s:textfield id="sendHour" name="tbFixShare.tbWorkingInfo.sendHour" readonly="true"></s:textfield>
					</td>
				</tr>
				
			</table>
			
			<table id="timeTable">
				<tr>
					<td><input type="button" value="添加修理人员" onclick="appendItem();"/></td>
					<td>
						<input type="hidden" id="deleteCount">
						
					</td>
				</tr>
				<tr>
					<td>修理人员</td>
					<td>工时</td>
				</tr>
				<tr>
					<td>
						<s:select id="tmUserId0" name="tbFixShare.tmUserList"  list="#request.tmUserList" listKey="id" listValue="userRealName"></s:select>
						
					</td>
					<td><input id="time0" name="time0"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<input type="button" value="确定" onclick="setParentWin();"/>
						&nbsp;&nbsp;
						<input type="button" value="退出" onclick="closeWindow();"/>
					</td>
				</tr>
			</table>
			
		</s:form>
	</body>
</html>

<script>

	var i = 0;
	
	function appendItem()
	{
		i++;
		
		var table = document.getElementById('timeTable');
		
		var tbody_1 = document.createElement("tbody");
		
		var tr_1= document.createElement("tr");
			            
		var td_1_0= document.createElement("td");
		
		var td_1_1= document.createElement("td");
		
		td_1_0.innerHTML = "<select id='tmUserId"+i+"'></select>";
		
		td_1_1.innerHTML = "<input type='text' id='time"+i+"' name='time"+i+"'/><font color='blue' onclick='deleteItem(this);' id='"+i+"'>删除</font>";
	
		table.appendChild(tbody_1);
		
		tbody_1.appendChild(tr_1);
		
		tr_1.appendChild(td_1_0);
		
		tr_1.appendChild(td_1_1);
		
		jsSelectSetAll(document.getElementById('tmUserId0'),document.getElementById('tmUserId'+i));
	
	}
	
	function deleteItem(obj)
	{
		if(confirm('确定删除这行记录？')){
			obj.parentNode.parentNode.removeNode(true);
		}
		var deleteRowId = event.srcElement.id;
		
		var deleteCount = document.getElementById('deleteCount');
					
		deleteCount.value += deleteRowId + ',';
	}
	
	function setParentWin()
	{
		var index = document.getElementById('index').value;
		
		var fixPersonIds = parent.document.getElementById('fixPersonIds'+index);
		
		var fixPersons = parent.document.getElementById('fixPersons'+index);
		
		var deleteCount = document.getElementById('deleteCount');
	
		var ids = '';
		
		var names = '';
		
		for(var p = 0 ; p <=i ; p++){
			
			var tmUser = document.getElementById('tmUserId'+p);
			
			var time = document.getElementById('time'+p);
			
			if(isInString(p,deleteCount.value))
			{
				continue;
			}
			
			if(!validateAllPositiveNum(time.value)){
				
				alert('工时填写错误!');
				
				return ;
			}
			
			var tmUserId = tmUser.value;
			
			var tmUserValue = tmUser.options[tmUser.selectedIndex].text;
			
			ids += tmUserId + '-' + time.value + ',';
			
			names += tmUserValue + '-' + time.value +','; 
		}
		
		fixPersonIds.value = ids;
		
		fixPersons.value = names;
		
		parent.win.hide();
	}
	
	function isInString(str1,str2){

		var strArray = str2.split(',');
					
		for(var ix = 0 ; strArray.length-1 > ix; ix++)
		{
					
			if(str1 == strArray[ix])
			{
				return true;
			}
						
		}
			return false;
	}
	
	//初始化
	function init()
	{
		var fixPersonIds = document.getElementById('fixPersonIds_edit');
		
		var fixPersons = document.getElementById('fixPersons_edit');
		
		if(null!=fixPersons.value&&''!=fixPersons.value)
		{
			var names = fixPersons.value.split(',');
			
			for(var p = 1 ; p < names.length-1 ; p ++)
			{
				appendItem();
			}
			
			for(var p = 0 ; p < names.length -1; p ++)
			{
				var namesArray = names[p].split('-');
				
				jsSelectItemByValue(document.getElementById('tmUserId'+p),namesArray[0]);
				
				document.getElementById('time'+p).value = namesArray[1];
			}
		}
		
	}
	
	init();
	
</script>