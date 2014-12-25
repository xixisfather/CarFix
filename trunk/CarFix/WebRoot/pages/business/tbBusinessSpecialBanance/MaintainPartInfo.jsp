<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title></title>
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
		function tbPartInfoTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
			pConfig.tbar = [
			{	
				text : '导入',
				iconCls : 'editIcon',
				handler : function() {
				
					var date = new Date();
				
					var time = date.getTime();
					
					addObject('initPjxxImportXlsPageAction.action?timeId='+time,600,300);
				
				}
			}];
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "tabShowInfoAction";
			pConfig.showLoadingMsg = true;
		}
		
		// 表格显示前,通常在这注册单击，双击事件
		function tbPartInfoTableRenderBeforeHandler(pGrid) {
			pGrid.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the Record

				addPartInfo(record);
				
				parent.initValue();
			});
	
		}
		function addPartInfo(record){
			
			//处理数据
			var e3partId = record.get("id");
			
			var e3houseName = record.get("houseName");
			
			var e3partCode = record.get('partCode');
			
			var e3partName= record.get("partName");
			
			var e3storeLocation = record.get("storeLocation");
			
			var e3unitName = record.get("unitName"); 
			
			var e3storeQuantity = record.get("storeQuantity");
			
			var e3costPrice = record.get("sellPrice");
			 
	var index = parent.document.getElementById('f_count').value;
		    
	var tbPartInfoTable = parent.document.getElementById('partInfoTable');
	
	var tbody_1 = document.createElement("tbody");
	
	var tr_1= document.createElement("tr");
        
    var td_1_0= document.createElement("td");
  
    td_1_0.innerHTML="<input type='hidden' id='tbPartInfoId"+index+"' name='tbPartInfoId"+index+"' value='"+e3partId+"'/>"+"<font color=\"blue\" onclick=\"deleteTd(this);\" style=\"cursor: pointer;\">删除</font>";

    var td_1_1= document.createElement("td");
    
    td_1_1.innerHTML = "<input type='checkbox' id='free"+index+"' name='free"+index+"' onclick='initValue();'/>";
    
    var td_1_2= document.createElement("td");
    
    td_1_2.innerHTML = e3houseName;	
    
    var td_1_3= document.createElement("td");
    
    td_1_3.innerHTML = e3partCode;
    
    var td_1_4= document.createElement("td");
    
    //td_1_4.innerHTML = e3partName;
    td_1_4.innerHTML = "<input type='text' id='partName"+index+"' name='partName"+index+"' value='" + e3partName+ "'/>";
    
    var td_1_5= document.createElement("td");
    
    td_1_5.innerHTML = e3unitName;
    
    var td_1_6 = document.createElement("td");
    
    td_1_6 .innerHTML = "<input type='text' id='price"+index+"' name='price"+index+"' value='"+e3costPrice+"' onblur='initValue();'/>";
    
    var td_1_7 = document.createElement("td");
    
    td_1_7.innerHTML = "<input type='text' id='partQuantity"+index+"' name='partQuantity"+index+"' value='1.00' onblur='initValue();'/>";
   
    var td_1_8 = document.createElement("td");
    
    td_1_8.innerHTML = "<input type='text' id='total"+index+"' name='total"+index+"' value='"+formatFloat(parseFloat(1.00)*parseFloat(e3costPrice),2)+"' readonly='true'/>";
   
    tbPartInfoTable.appendChild(tbody_1);
    
    tbody_1.appendChild(tr_1);
    
    tr_1.appendChild(td_1_0);
    
    tr_1.appendChild(td_1_1);
    
    tr_1.appendChild(td_1_2);
    
    tr_1.appendChild(td_1_3);
    
    tr_1.appendChild(td_1_4);
    
    tr_1.appendChild(td_1_5);
    
    tr_1.appendChild(td_1_6);
    
    tr_1.appendChild(td_1_7);

    tr_1.appendChild(td_1_8);
       
    index++;
    
    var count = parent.document.getElementById('f_count');
    
    count.value = index;
	
	}
		
function deleteTd(obj)
{
	if(confirm('确定删除该配件？')){
		
		obj.parentNode.parentNode.removeNode(true);

		parent.initValue();
	}
}		
	</script>
	<body  >
	<s:form action="tabShowInfoAction.action">
		<table>
			<tr>	
				<td>仓库</td>
				<td><s:select name="tbPartInfo.tmStoreHouse.id" list="#request.stroeHouseList" emptyOption="true" listKey="id" listValue="houseName"/></td>
				<td>车辆类型</td>
				<td><s:select name="tbPartInfo.tmCarModelType.id" list="#request.carModelTypeList" emptyOption="true" listKey="id" listValue="modelName"/></td>
				<td>配件名称</td>
				<td><s:textfield name="tbPartInfo.partName" /></td>
			</tr>
			
			<tr>
				<td>配件代码</td>
				<td><s:textfield  name="tbPartInfo.partCode" ></s:textfield></td>
				<td>拼音名称</td>
				<td><s:textfield  name="tbPartInfo.pinyinCode" ></s:textfield></td>
				<td>配件类型</td>
				<td><s:select name="tbPartInfo.tmPartType.id" list="#request.partTypeList" emptyOption="true" listKey="id" listValue="typeName"/></td>
			</tr>
			<tr>
				<td>
					<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
				</td>
			</tr>
		</table>
	</s:form>
		
		<e3t:table id="tbPartInfoTable" uri="tabShowInfoAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" caption="配件"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="750"
			height="320" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode"	 title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="storeLocation" title="仓位" />
			<e3t:column property="unitName" title="计量单位" />
			<e3t:column property="storeQuantity" title="库存" />
			<e3t:column property="sellPrice" title="销售价" />
			<e3t:column property="id" hidden="true" title="配件id" />
		</e3t:table>
	</body> 
		
		
</html>
