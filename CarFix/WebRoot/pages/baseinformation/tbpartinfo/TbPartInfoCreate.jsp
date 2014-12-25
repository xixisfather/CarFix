<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>配件增加
		</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>
	<script type="text/javascript">
	
		function addTbPartInfo(){
			if(!isvalide()) return;
		
			var soleType = document.getElementsByName("soleType");
			var soleTypes = "";
			for(var i=0; i<soleType.length; i++){
				soleTypes += soleType[i].id+":"+soleType[i].value+",";
			}
			
			document.getElementById("soleTypes").value = soleTypes;
			if("${param.submit}" == "ajax"){
				document.forms("tbPartInfoInsertAction").ation = "addTbPartInfoAjaxAction.action";
				savePartInfoAjax();
			}else{
				document.forms("tbPartInfoInsertAction").submit();
			}
		}
		
		function savePartInfoAjax(){
			var url =  "<%= request.getContextPath() %>/addTbPartInfoAjaxAction.action?";
			var pars = Form.serialize($("tbPartInfoInsertAction"));
			var addAjax = new Ajax.Request(
			url,
			{method:'post',parameters: pars, onComplete:savecallback}
			);
			
		}
		
		function savecallback(originalRequest){
			if(parent.partInfoChooseUrl == undefined ){
				window.location.href = "<%= request.getContextPath() %>/initMaintainPageAction.action";
			}else{
				window.location.href = "<%= request.getContextPath() %>/"+parent.partInfoChooseUrl;
			}
		}
		
		function isvalide(){
			var tmStockHouse = document.getElementById("tmStockHouse");
			if(tmStockHouse.value == ""){
				Ext.MessageBox.alert('温馨提示：', '选择一个仓库！', null);
				return false;
			}
			var partCode = document.getElementById("partCode");
			if(partCode.value == ""){
				Ext.MessageBox.alert('温馨提示：', '配件代码不能为空！', null);
				return false;
			}
			var partName = document.getElementById("partName");
			if(partName.value == ""){
				Ext.MessageBox.alert('温馨提示：', '配件名称不能为空！', null);
				return false;
			}
			var unit = document.getElementById("unit");
			if(unit.value == ""){
				Ext.MessageBox.alert('温馨提示：', '请选择计量单位！', null);
				return false;
			}	
			
			var costPrice = document.getElementById("costPrice");
			if(costPrice.value == ""){
				Ext.MessageBox.alert('温馨提示：', '成本价不能为空！', null);
				return false;
			}	
			
			return true;
		}
		
		
		function openWin(){
			var props = "tmPartTypeId,partTypeName";
			showCommonWin('findCommTmPartTypeAction.action','配件类型列表',575,355,props,null);
		}
	
		function convetRate(){
			var rateCostPrice = document.getElementById("rateCostPrice");
			var costPrice = document.getElementById("costPrice");
			rateCostPrice.value = formatFloat(costPrice.value*1.17,2);
		}
		
		function convetNoRate(){
			var rateCostPrice = document.getElementById("rateCostPrice");
			var costPrice = document.getElementById("costPrice");
			costPrice.value = formatFloat(rateCostPrice.value/1.17,2);
			var soleType = document.getElementById("soleType");
			soleType.value=rateCostPrice.value*1.6;
		}
	</script>
	<body>
	
		<s:form action="tbPartInfoInsertAction.action">
			<s:hidden id="tmPartTypeId"  name="tbPartInfo.tmPartType.id" ></s:hidden>
			<table>
				<tr>
					<td>
						仓库
					</td>
					<td>	
						<select name="tbPartInfo.tmStoreHouse.id" id="tmStockHouse" >
						    <option value=""></option>
						    <s:iterator  value="#request.stroeHouseList" id="house" >
						    	 <option value="${house.id}">${house.houseCode }&nbsp;&nbsp;${house.houseName }</option>
						    </s:iterator>
						 </select>
						<font color="red">*</font>
					</td>
				
				</tr>
				<tr>
					<td>
						车辆类型
					</td>
					<td>	
						<s:select name="tbPartInfo.tmCarModelType.id" list="#request.carModelTypeList" emptyOption="true" listKey="id" listValue="modelName"/>
					</td>
				</tr>
				<tr>
					<td>
						配件代码
					</td>
					<td>	
						<s:textfield id="partCode"  name="tbPartInfo.partCode" cssStyle="text-transform:uppercase;" ></s:textfield><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						配件名称
					</td>
					<td>	
						<s:textfield id="partName"  name="tbPartInfo.partName" onblur="sendChn('partName','pinyinCode');" ></s:textfield><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						拼音名称
					</td>
					<td>	
						<s:textfield id="pinyinCode"  name="tbPartInfo.pinyinCode" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						计量单位
					</td>
					<td>	
						<s:select id="unit" name="tbPartInfo.tmUnit.id" list="#request.unitList" emptyOption="true" listKey="id" listValue="unitName"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						配件类型
					</td>
					<td>	
						<!-- 
						<s:select name="tbPartInfo.tmPartType.id" list="#request.partTypeList" emptyOption="true" listKey="id" listValue="typeName"/>
						 -->
						<s:textfield id="partTypeName" onfocus="openWin('tmPartTypeId','partTypeName')" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						配件大类
					</td>
					<td>	
						<s:textfield  name="tbPartInfo.partBroadType" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						仓位
					</td>
					<td>	
						<s:textfield  name="tbPartInfo.storeLocation" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						制造厂号
					</td>
					<td>	
						<s:textfield  name="tbPartInfo.factoryCode" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						危险品号
					</td>
					<td>	
						<s:textfield  name="tbPartInfo.dangerCode" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						最大库存
					</td>
					<td>	
						<s:textfield  name="tbPartInfo.maxStoreQuantity" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						最小库存
					</td>
					<td>	
						<s:textfield  name="tbPartInfo.minStoreQuantity" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						成本价(含税);
					</td>
					<td>
						<s:textfield id="rateCostPrice"  name="tbPartInfo.rateCostPrice" onblur="convetNoRate()" ></s:textfield><font color="red">*</font>
					</td>
					
				</tr>
				<tr>
					<td>
						成本价(不含税);
					</td>
					<td>
						<s:textfield id="costPrice"  name="tbPartInfo.costPrice" onblur="convetRate()" ></s:textfield><font color="red">*</font>
					</td>
					
				</tr>
				<tr>
					<td>
						借进量;
					</td>
					<td>
						<s:textfield readonly="true"  name="tbPartInfo.lianceQuantity" value="0.0" ></s:textfield>
					</td>
					
				</tr>
				<tr>
					<td>
						借出量
					</td>
					<td>
						<s:textfield readonly="true"  name="tbPartInfo.loanQuantity" value="0.0" ></s:textfield>
					</td>
					
				</tr>
				<tr>
				
					<s:iterator id="sp" value="#request.soleTypeList" >
					<td>${sp.soleName }</td>
					<td><input id="${sp.id}" name="soleType" value="0.00" type="text" /></td>
					</s:iterator>
					<s:hidden name="soleTypes" id="soleTypes" ></s:hidden>
				</tr>
				<tr>

					<td align="center">
						<input type="button" onclick="addTbPartInfo();"
							value="<s:text name="TM_FLAG_CAR_BUTTON_SUBMIT"/>" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>

