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
		
		function addStoreHouse(){
			var flag = addValidate();
			if(flag == false){
				alert("已经存在相同的仓库，请重新选择...");
				return;
			}
			
			var mainTable = document.getElementById("mainTable");
		    var mainRow = mainTable.insertRow(); 
		    mainRow.id = "TR"+tmStoreHouse.id;
		    var houseCodeCell = mainRow.insertCell();
		    var houseNameCell = mainRow.insertCell();
		    var storeQuantityTypeCell = mainRow.insertCell();
		    var opCell = mainRow.insertCell();
		    var freeHtml= buildSelect("storetype"+tmStoreHouse.id);
		    storeQuantityTypeCell.innerHTML = freeHtml;
		    houseCodeCell.innerHTML="&nbsp;&nbsp;"+tmStoreHouse.houseCode;
		    houseNameCell.innerHTML="&nbsp;&nbsp;"+tmStoreHouse.houseName;
		    opCell.innerHTML = "<input type=button value=删除 onclick=\"deleteRow('"+tmStoreHouse.id+"');\" />";
		    
		    tbmainArray.push(tmStoreHouse);
		}
		
		var tmStoreHouse;
		
		var tbmainArray = new Array();
		
		function buildSelect(id){
			var html = "<select id= \""+id+"\"";
			html += ">";
			html+="<option selected=selected value=40 >大于零</option>";
			html+="<option selected=selected value=44 >等于零</option>";
			html+="<option selected=selected value=42 >小于零</option>";
			html+="</select>";
			return html;
		}
		
		function deleteRow(storeHouseId){
			var mainTable = document.getElementById("mainTable");
			var trRow = document.getElementById("TR"+storeHouseId);
			mainTable.deleteRow(trRow.rowIndex);
			
			var newArr = new Array();
			
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.id == storeHouseId){
					continue;
				}
				newArr.push(obj);
			}
			
			tbmainArray = newArr;
			
		}
		
		function addValidate(){
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.id == tmStoreHouse.id){
					return false;
				}
			}
			
			return true;
		}
		
		function nextTip(){
			var str = "";
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				var stype = document.getElementById("storetype"+obj.id);
				//alert("id : ="+obj.id+" \n "+"stype : = "+stype.value);
				
				str += obj.id + "," + stype.value + ";";
			}
			
			document.getElementById("houseids").value = str;
			document.forms[0].submit();
		}
	</script>
	<body  >
	<br>
	&nbsp;&nbsp;请选择您要盘点的仓库：
	<form action="checkHousePartInfoAction.action">
		<s:hidden name="houseids" id="houseids"></s:hidden>
	</form>
	<br>
	<br>
	&nbsp;&nbsp;
	<table id="mainTable"  >
			<tr>
				<th width="100" >仓库代码</th>
				<th width="100">仓库名称</th>
				<th width="100" align="center">库存情况</th>
				<th width="100" align="center" >操作</th>
			</tr>
		</table>
	<br>
	&nbsp;&nbsp;<input type="button" id="saveBtn" value="确认" onclick="nextTip()" />
	<br>
	<div id="tabPlaceHolder" ></div>
		<script language="javascript">
		Ext.onReady(function(){
			TabPanel.create('tabPlaceHolder',430,
			[
				{
					id:'tbPartInfoTab' , title:'仓库列表',disabled:false,url:'findAllTmStoreHouseAction.action'
					
				}
				
			]);
		});
		
	</script>
		
	</body>  
		
		
</html>
