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
	width:646px;
	height:145px;
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

<div id="apDiv1">${companyName}仓库月度期间收发存统计报表&nbsp;&nbsp;<span class="btn"><font color="red" size="1PX">右键>打印预览>设置>打印</font></span></div>
<div id="apDiv3">
	查询日期 &nbsp;&nbsp; ${qryDate}
 </div>
<div id="apDiv2">
  <table width="800" border="0">
    <tr>
      <td>序号</td>
      <td>仓库</td>
      <td>出入库类型</td>
      <td>入库成本</td>
      <td>出库成本</td>
      <td>出库销售总金额</td>
      <td>期初</td>
      <td>期末</td>
    </tr>
    <s:iterator value="#request.results" id="tbPartInfo" status="idx">
	    <tr height="30">
	      <td>${idx.index+1}</td>	
	      <td>${tbPartInfo.storeHouseName }</td>
	      <td>${tbPartInfo.inOutTypeName }</td>
	      <td>${tbPartInfo.stockInCostPrice }</td>
	      <td>${tbPartInfo.stockOutCostPrice }</td>
	      <td>${tbPartInfo.sellCostPrice}</td>
	      <td>${tbPartInfo.qcPrice }</td>
	      <td>${tbPartInfo.qmPrice }</td>
	    </tr>
    </s:iterator>
  </table>
  <p>&nbsp;</p>
</body>
</html>


