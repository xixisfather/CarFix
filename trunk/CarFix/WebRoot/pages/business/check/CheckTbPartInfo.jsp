<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
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
	<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/check/storehousecheck.js"></script>
	
	<head>
	<script type="text/javascript">
		
	</script>
	</head>
	<body  >
	<br>
	<s:form action="insertStoreHouseCheckAction.action" >
		<s:hidden name="tbStoreHouseCheck.storeHouseIds" value="%{#request.storeCheck.storeHouseIds}" ></s:hidden>
		<s:hidden name="tbStoreHouseCheck.tmUser.id" value="%{#request.storeCheck.tmUser.id}" ></s:hidden>
		<table>
			<tr>
				<td>盘点日期：</td>
				<td><s:textfield id="checkDate" name="tbStoreHouseCheck.checkDate"  readonly="true"  >
						<s:param name="value"><s:date name="%{#request.storeCheck.checkDate}" format="yyyy-MM-dd"/></s:param>
					</s:textfield>
				</td>
				<td>经办人：</td>
				<td><s:textfield value="%{#request.storeCheck.tmUser.userRealName}"  readonly="true"  >
					</s:textfield>
				</td>
				<td>仓库：</td>
				<td ><s:textfield name="tbStoreHouseCheck.storeHouseNames" value="%{#request.storeCheck.storeHouseNames}" readonly="true" /></td>
				<td>扎帐日期：</td>
				<td><s:textfield id="zzDate" name="tbStoreHouseCheck.zzDate" readonly="true"  />
				</td>
			</tr>
			<tr>
				<td >备注：</td>
				<td colspan="7" ><s:textarea cols="110" rows="3"  name="tbStoreHouseCheck.memo" /></td>
			</tr>
		</table>
	<br>
	<br>
	&nbsp;&nbsp;
	<table id="mainTable"  >
	
		<tr>
			<th width="50" >仓库</th>
			<th width="50" >仓位</th>
			<th>配件代码</th>
			<th>配件名称</th>
			<th width="50">单位</th>
			<th width="80">成本价</th>
			<th width="80">账面库存</th>
			<th width="80">实际账面金额</th>
			<th width="80">盘点数量</th>
			<th width="80">盘点金额</th>
			<th width="80">差异数量</th>
			<th width="80">差异金额</th>
			<th width="30">操作</th>
		</tr>
		<s:iterator value="#request.tbPartInfoList" id="part" status="idx">
			<tr id="TR${part.id}" >
				<td>${part.tmStoreHouse.houseName }</td>
				<td>${part.storeLocation }</td>
				<td>${part.partCode }</td>
				<td>${part.partName }</td>
				<td>${part.tmUnit.unitName }</td>
				<td>${part.costPrice }</td>
				<td>${part.storeQuantity }</td>
				<td>${part.factPrice }</td>
				<td><input type="text" style="width:80px" onchange="jsprice('${part.id}');" id="checkquantity${part.id}"  /></td>
				<td><span id="checkprice${part.id}" >&nbsp;</span></td>
				<td><span id="divquantity${part.id}" style="color:red" >&nbsp;</span></td>
				<td><span id="divprice${part.id}" style="color:red" >&nbsp;</span> </td>
				<td align="right" >
					<span id="op${part.id}" style="cursor:pointer;color:blue" onclick="delcheckrow('${part.id}')" >删除</span> 
					<input type="hidden" style="width:80px" id="partinfo${part.id}" value="${part.id},${part.costPrice},${part.storeQuantity}" />
				</td>
			</tr>
				<script language="javascript">
					
					var partinfovo = {};
					partinfovo.id = "${part.id}";
					partinfovo.costPrice =  "${part.costPrice}";
					partinfovo.storeQuantity =  "${part.storeQuantity}";
					partArray.push(partinfovo);
				</script>
		</s:iterator>
	</table>
	<div id="fatherId" ></div>
	</s:form>
	<br>
	&nbsp;&nbsp;<input type="button" id="saveBtn" value="保存" onclick="savecheck(60)" />
	&nbsp;&nbsp;<input type="button" id="saveBtn" value="盘点确认" onclick="savecheck(62)" />
	&nbsp;&nbsp;<input type="button" id="saveBtn" value="添加商品" onclick="openWin()" />
	<br>
	</body>  
		
		
</html>
