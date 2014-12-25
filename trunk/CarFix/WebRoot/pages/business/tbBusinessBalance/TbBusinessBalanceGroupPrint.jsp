<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.selfsoft.business.model.TbBusinessBalance"%>
<%@page import="com.selfsoft.baseinformation.service.ITbCarInfoService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.selfsoft.baseinformation.model.TbCarInfo"%>
<%@page import="com.selfsoft.framework.common.CommonMethod"%>
<%@page import="com.selfsoft.secrity.service.ITmCompanyService"%>
<%@page import="com.selfsoft.secrity.model.TmCompany"%><html>
<head>
<title>结算财务汇总报表</title>

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


TbBusinessBalance _tbBusinessBalance = (TbBusinessBalance)session.getAttribute("tbBusinessBalance");

%>

<div id="apDiv1"><%=t.getCompanyName() %>结算财务汇总报表&nbsp;&nbsp;<span class="btn"><font color="red" size="1PX">右键>打印预览>设置>打印</font></span></div>
<div id="apDiv2">
  <table width="800" border="0">
    <tr>
      <td>序号</td>
      <td>结算单号</td>
      <td>单据号</td>
      <td>客户名</td>
      <td>车牌号</td>
      <td>车型</td>
      <td>底盘号</td>
      <td>联系方式</td>
      <td>金额</td>
      <td>结算日期</td>
      <td>接待员</td>
    </tr>
    <% 
    
    List<TbBusinessBalance> tbBusinessBalanceList = (List<TbBusinessBalance>)session.getAttribute("tbBusinessBalanceList");
    
    if(null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0){
    	int i=1;
    	for(TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList){
    		
    		String djh = tbBusinessBalance.getEntrustCode() == null ? tbBusinessBalance.getStockOutCode() : tbBusinessBalance.getEntrustCode();
    
    		String customerName = tbBusinessBalance.getCustomerName();
    		
    		if(null != customerName && customerName.length() > 5){
    			
    			customerName = customerName.substring(0,5);
    			
    		}
    %>
    <tr height="30">
      <td><%=i %></td>	
      <td><%=tbBusinessBalance.getBalanceCode()%></td>
      <td><%=djh%></td>
      <td><%=customerName%></td>
      <td><%=tbBusinessBalance.getLicenseCode() == null ? "" : tbBusinessBalance.getLicenseCode()%></td>
      <td><%=tbBusinessBalance.getTbFixEntrust() == null ? "" : tbBusinessBalance.getTbFixEntrust().getTbCarInfo().getTmCarModelType().getModelCode()%></td>
      <td><%=tbBusinessBalance.getTbFixEntrust() == null ? "" : tbBusinessBalance.getTbFixEntrust().getTbCarInfo().getChassisCode()%></td>
      <td><%=tbBusinessBalance.getTbCustomer().getTelephone()%></td>
      <td><%=tbBusinessBalance.getBalanceTotalAll()%></td>
      <td><%=CommonMethod.parseDateToString(tbBusinessBalance.getBananceDate(),"yyyy-MM-dd HH:ss:mm") %></td>
      <td><%=tbBusinessBalance.getUserRealName()%></td>
    </tr>
    <%		
    	i++;
    	}
    	
    }
    %>
    
    
  </table>
  <p>&nbsp;</p>
  <table width="641" border="0">
    <tr>
      <td width="73" align="right">业务笔数：</td>
      <td width="73"><%=session.getAttribute("totalCount") %></td>
      <td width="73" align="right">总金额：</td>
      <td width="75"><%=session.getAttribute("total") %></td>
      <td width="71" align="right">收款金额：</td>
      <td width="73"><%=session.getAttribute("payed") %></td>
      <td width="73" align="right">工时费：</td>
      <td width="78"><%=session.getAttribute("fixHour") %></td>
    </tr>
    <tr>
      <td align="right">材料费：</td>
      <td><%=session.getAttribute("fixPart") %></td>
      <td align="right">销售额：</td>
      <td><%=session.getAttribute("solePart") %></td>
      <td align="right">其他费：</td>
      <td><%=session.getAttribute("other") %></td>
      <td align="right">欠款金额：</td>
      <td><%=session.getAttribute("owe") %></td>
    </tr>
    <tr>
      <td align="right">材料成本费：</td>
      <td><%=session.getAttribute("costTotal") %></td>
      <td align="right">毛利：</td>
      <td><%=session.getAttribute("djcb") %></td>
      <td align="right">&nbsp;</td>
      <td>&nbsp;</td>
      <td align="right">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
</div>
<div id="apDiv3">
	结算日期 &nbsp;&nbsp;    
	<%if(null!=_tbBusinessBalance){ %>
		<%= CommonMethod.parseDateToString(_tbBusinessBalance.getBananceDateStart(),"yyyy-MM-dd")%>&nbsp;&nbsp;至&nbsp;&nbsp;  <%= CommonMethod.parseDateToString(_tbBusinessBalance.getBananceDateEnd(),"yyyy-MM-dd")%>
	<%} %>
 </div>
</body>
</html>


