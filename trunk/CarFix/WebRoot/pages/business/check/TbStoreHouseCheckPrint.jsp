<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>配件出库汇总报表</title>

<style type="text/css">
<!--
#apDiv1 {
	position:absolute;
	width:575px;
	height:30px;
	z-index:1;
	left: 75px;
	top: -1px;
	font-size: 16px;
	font-weight: bold;
	text-align: left;
}
#apDiv1 center {
	font-weight: bold;
	font-size: 24px;
}
#apDiv2 {
	position:absolute;
	z-index:2;
	left: 5px;
	top: 65px;
	font-size: 12px;
}
#apDiv3 {
	position:absolute;
	width:652px;
	height:31px;
	z-index:3;
	top: 30px;
	left: 4px;
	font-size: 12px;
}

@media   print   {.btn{display:   none;}} 



@CHARSET "UTF-8";
td{
	font-family:"宋体";
	font-size:12px;
}
th{
	font-family:"宋体";
	font-size:12px;
}
body{
	font-family:"宋体";
	font-size:12px;
}





-->
</style>

</head>

<body>

<div id="apDiv1">${companyName}盘点汇总报表&nbsp;&nbsp;<span class="btn"><font color="red" size="1PX">右键>打印预览>设置>打印</font></span></div>
<br>
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
  <table border="0">
    <tr>
    		<th width="30" >序号</th>
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
		</tr>
    <s:iterator value="#request.storeHouseCheckDetails" id="detail" status="idx">
	    <tr height="30">
	      	<td>${idx.index+1}</td>	
	        <td>${detail.tmStoreHouse.houseName }</td>
			<td>${detail.tbPartInfo.storeLocation }</td>
			<td>${detail.tbPartInfo.partCode }</td>
			<td>${detail.tbPartInfo.partName }</td>
			<td>${detail.tbPartInfo.tmUnit.unitName }</td>
			<td>${detail.tbPartInfo.costPrice }</td>
			<td>${detail.tbPartInfo.storeQuantity }</td>
			<td>${detail.tbPartInfo.factPrice }</td>
			<td>${detail.checkQuantity}</td>
			<td>${detail.checkPrice }</td>
			<td>${detail.divQuantity }</td>
			<td>${detail.divPrice }</td>
	    </tr>
    </s:iterator>
  </table>
  <p>&nbsp;</p>
</body>
</html>


