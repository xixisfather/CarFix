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
	<s:form action="updateStoreHouseCheckAction.action" >
		<s:hidden name="tbStoreHouseCheck.storeHouseIds" ></s:hidden>
		<s:hidden name="tbStoreHouseCheck.tmUser.id" ></s:hidden>
		<s:hidden name="tbStoreHouseCheck.id" ></s:hidden>
		<s:hidden name="tbStoreHouseCheck.checkCode" ></s:hidden>
		<table>
			<tr>
				<td>盘点日期：</td>
				<td><s:textfield id="checkDate" name="tbStoreHouseCheck.checkDate"  readonly="true"  >
						<s:param name="value"><s:date name="%{#request.tbStoreHouseCheck.checkDate}" format="yyyy-MM-dd"/></s:param>
					</s:textfield>
				</td>
				<td>经办人：</td>
				<td><s:textfield value="%{#request.tbStoreHouseCheck.tmUser.userRealName}"  readonly="true"  >
					</s:textfield>
				</td>
				<td>仓库：</td>
				<td ><s:textfield name="tbStoreHouseCheck.storeHouseNames" readonly="true" /></td>
				<td>扎帐日期：</td>
				<td><s:textfield id="zzDate" name="tbStoreHouseCheck.zzDate" readonly="true"  />
				</td>
			</tr>
			<tr>
				<td >备注：</td>
				<td colspan="7" ><s:textarea readonly="true" cols="110" rows="3"  name="tbStoreHouseCheck.memo" /></td>
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
		<s:iterator value="#request.storeHouseCheckDetails" id="detail" status="idx">
			<tr id="TR${detail.tbPartInfo.id}">
				<td>${detail.tmStoreHouse.houseName }</td>
				<td>${detail.tbPartInfo.storeLocation }</td>
				<td>${detail.tbPartInfo.partCode }</td>
				<td>${detail.tbPartInfo.partName }</td>
				<td>${detail.tbPartInfo.tmUnit.unitName }</td>
				<td>${detail.tbPartInfo.costPrice }</td>
				<td>${detail.tbPartInfo.storeQuantity }</td>
				<td>${detail.tbPartInfo.factPrice }</td>
				<td><input type="text" readonly="true" style="width:80px" value="${detail.checkQuantity }"   onchange="jsprice('${detail.tbPartInfo.id}');" id="checkquantity${detail.tbPartInfo.id}"  /></td>
				<td><span id="checkprice${detail.tbPartInfo.id}" >&nbsp;${detail.checkPrice}</span></td>
				<td><span id="divquantity${detail.tbPartInfo.id}" style="color:red" >&nbsp;${detail.divQuantity}</span></td>
				<td><span id="divprice${detail.tbPartInfo.id}" style="color:red" >&nbsp;${detail.divPrice}</span> </td>
				<td align="right" >
					<span id="op${detail.tbPartInfo.id}" style="cursor:pointer;color:blue"  >删除</span> 
					<input type="hidden" style="width:80px" id="partinfo${detail.tbPartInfo.id}" value="${detail.tbPartInfo.id},${detail.tbPartInfo.costPrice},${detail.tbPartInfo.storeQuantity}" />
				</td>
			</tr>
				<script language="javascript">
					
					var partinfovo = {};
					partinfovo.id = "${detail.tbPartInfo.id}";
					partinfovo.costPrice =  "${detail.tbPartInfo.costPrice}";
					partinfovo.storeQuantity =  "${detail.tbPartInfo.storeQuantity}";
					partArray.push(partinfovo);
				</script>
		</s:iterator>
	</table>
	<div id="fatherId" ></div>
	</s:form>
	<br>
	<br>
	</body>  
		
		
</html>
