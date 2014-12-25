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
		function editTbPartInfo(){
			var soleType = document.getElementsByName("soleType");
			var soleTypes = "";
			for(var i=0; i<soleType.length; i++){
				soleTypes += soleType[i].id+":"+soleType[i].value+",";
			}
			
			document.getElementById("soleTypes").value = soleTypes;
			
			var flag = checkQuantity();
			if(!flag)
				return false;
			document.forms("tbPartInfoUpdateAction").submit();
		}
		
		function openWin(){
			var props = "tmPartTypeId,partTypeName";
			showCommonWin('findCommTmPartTypeAction.action','配件类型列表',575,355,props,null);
		}
		
		function checkQuantity(){
			var storeQuantity = document.getElementById("storeQuantity");
			var maxStoreQuantity = document.getElementById("maxStoreQuantity");
			var minStoreQuantity = document.getElementById("minStoreQuantity");
			if( storeQuantity.value != null && storeQuantity.value != ""){
				if(storeQuantity.value == "0.0")
					return true;
			
				if( maxStoreQuantity.value != null && maxStoreQuantity.value != ""){
					
					if(formatFloat(storeQuantity.value,2) > formatFloat(maxStoreQuantity.value,2)){
						alert("最大安全库存输入有误：最大安全库存为："+maxStoreQuantity.value+",当前库存为："+storeQuantity.value);
						return false;
					}
				}
				
				if(minStoreQuantity.value != null && minStoreQuantity.value != ""){
					if(formatFloat(storeQuantity.value,2) < formatFloat(minStoreQuantity.value,2)){
						alert("最小安全库存输入有误：最小安全库存为："+minStoreQuantity.value+",当前库存为："+storeQuantity.value);
						return false;
					}
				}
			
			}
			
			return true;
		}
	</script>
	<body>
	
		<s:form action="tbPartInfoUpdateAction.action" >
			<s:hidden name="tbPartInfo.id"/>
			<s:hidden name="tbPartInfo.tmStoreHouse.id"/>
			<s:hidden name="tbPartInfo.stockMoney"/>
			<s:hidden name="tbPartInfo.lastModifyDate" />
			<s:hidden id="tmPartTypeId"  name="tbPartInfo.tmPartType.id" ></s:hidden>
			<table>
				<tr>
					<td>
						仓库
					</td>
					<td>	
						<s:textfield readonly="true" name="tbPartInfo.tmStoreHouse.houseName" ></s:textfield>
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
						<s:textfield  name="tbPartInfo.partCode" cssStyle="text-transform:uppercase;" ></s:textfield><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						配件名称
					</td>
					<td>	
						<s:textfield id="partName" name="tbPartInfo.partName" onblur="sendChn('partName','pinyinCode');" ></s:textfield><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						拼音名称
					</td>
					<td>	
						<s:textfield id="pinyinCode"   name="tbPartInfo.pinyinCode" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						计量单位
					</td>
					<td>	
						<s:select name="tbPartInfo.tmUnit.id" list="#request.unitList" emptyOption="true" listKey="id" listValue="unitName"/>
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
						 <s:textfield id="partTypeName" name="tbPartInfo.tmPartType.typeName" onfocus="openWin('tmPartTypeId','partTypeName')" ></s:textfield>
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
						<s:textfield id="maxStoreQuantity"  name="tbPartInfo.maxStoreQuantity" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						最小库存
					</td>
					<td>	
						<s:textfield id="minStoreQuantity" name="tbPartInfo.minStoreQuantity" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						库存
					</td>
					<td>	
						<s:textfield id="storeQuantity" readonly="true" name="tbPartInfo.storeQuantity" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						借进量;
					</td>
					<td>
						<s:textfield readonly="true"  name="tbPartInfo.lianceQuantity" ></s:textfield>
					</td>
					
				</tr>
				<tr>
					<td>
						借出量
					</td>
					<td>
						<s:textfield readonly="true"  name="tbPartInfo.loanQuantity" ></s:textfield>
					</td>
					
				</tr>
				<tr>
					<td>
						成本价;
					</td>
					<td>
						<s:textfield readonly="true"  name="tbPartInfo.costPrice" ></s:textfield>
					</td>
					
				</tr>
				<tr>
					<td>
						进货价;
					</td>
					<td>
						<s:textfield readonly="true"  name="tbPartInfo.stockPrice" ></s:textfield>
					</td>
					
				</tr>
				
				<tr>
					<s:iterator id="sp" value="#request.partSolePriceMap" >
						<td>${sp.value }</td>
						<td><input id="${sp.key.id}" name="soleType" value="${sp.key.solePrice}" type="text" /></td>
					</s:iterator>
					<s:hidden name="soleTypes" ></s:hidden>
				</tr>
				<tr>

					<td align="center">
						<input type="button" onclick="editTbPartInfo();"
							value="<s:text name="TM_FLAG_CAR_BUTTON_SUBMIT"/>" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
