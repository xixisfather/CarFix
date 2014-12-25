<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.selfsoft.baseinformation.service.ITbCarInfoService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.selfsoft.baseinformation.model.TbCarInfo"%>
<%@page import="com.selfsoft.framework.common.CommonMethod"%>
<%@page import="com.selfsoft.secrity.service.ITmCompanyService"%>
<%@page import="com.selfsoft.secrity.model.TmCompany"%>
<%@page import="com.selfsoft.framework.common.Constants"%><html>
<head>
<title>配件库存汇总报表</title>

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
<% 
ApplicationContext appContext = new ClassPathXmlApplicationContext(
"classpath:applicationContext*.xml");

ITmCompanyService tmCompanyService = (ITmCompanyService)appContext.getBean("tmCompanyService");

TmCompany t = tmCompanyService.findAll().get(0);

%>
<div id="apDiv1"><%=t.getCompanyName() %>配件库存汇总单&nbsp;&nbsp;<span class="btn"><font color="red" size="1PX">右键>打印预览>设置>打印</font></span></div>
<div id="apDiv2">
  <table width="800" height="76" border="0">
    <tr>
      <td>序号</td>	
      <td>仓库</td>
      <td>配件代码</td>
      <td>配件名称</td>
      <td>车辆类型</td>
      <td>库存</td>
      <td>借进量</td>
      <td>借出量</td>
      <td>成本价</td>
      <td>最后发生日期</td>
    </tr>
    <%List<TbPartInfo> tbPartInfoList = (List<TbPartInfo>)session.getAttribute("tbPartInfoList");
    
 		if(null != tbPartInfoList && tbPartInfoList.size() > 0){
 			
 			int i = 1 ;
 			
 			for(TbPartInfo tbPartInfo : tbPartInfoList){
 	%>
 				 <tr>
      				<td><%= i %></td>
     	 			<td><%= tbPartInfo.getHouseName()%></td>
      				<td><%= tbPartInfo.getPartCode()%></td>
      				<td><%= tbPartInfo.getPartName()%></td>
      				<td><%= tbPartInfo.getModelName() == null ? "" : tbPartInfo.getModelName()%></td>
      				<td><%= tbPartInfo.getStoreQuantity()%></td>
      				<td><%= tbPartInfo.getLianceQuantity()%></td>
      				<td><%= tbPartInfo.getLoanQuantity()%></td>
     	 			<td><%= tbPartInfo.getCostPrice()%></td>
      				<td><%= tbPartInfo.getLastModifyDate() == null ? "" : CommonMethod.parseDateToString(tbPartInfo.getLastModifyDate(),"yyyy-MM-dd")%></td>
      				
    </tr>
 	<%			
 			i++;
 			}
 		}
    %>
   
    
  </table>
  <p>库存金额总计:<%=session.getAttribute("totalStockMoney") %></p>
  <p>&nbsp;</p>
</div>
<%
	TbPartInfo tbPartInfo = (TbPartInfo)session.getAttribute("tbPartInfoReport");
%>
<div id="apDiv3">截止日期     &nbsp;&nbsp;&nbsp;&nbsp; 
	<%
	
	if(null != tbPartInfo){
	
	%>
		<%= tbPartInfo.getLastModifyDate() == null ? "" : tbPartInfo.getLastModifyDate()%>
	<%
		
	}
	%>
	
	
</div>
</body>

<%@page import="com.selfsoft.baseinformation.model.TbPartInfo"%></html>
