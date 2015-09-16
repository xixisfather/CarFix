<%@page import="com.selfsoft.framework.common.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>委托书查询</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js"
	charset="UTF-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/global.css" />
</head>

<body>
	<% 
	
			
	      Map<String,String> companyMap = Constants.getCompanyMap();
	
	      if(companyMap.get("xtlName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	
	    	  request.setAttribute("comName", "xtl");
	    	  
	      }
	      
	      else if(companyMap.get("dfbzName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	  
	    	  request.setAttribute("comName", "dfbz");
	    	  
	      }
	      
	      else{
	    	  
	    	  request.setAttribute("comName", "other");
	      }
	      
	   // request.setAttribute("comName", "dfbz");
	    
	    %>
	<s:form action="tbFixEntrustFindAction.action">
		<table>
			<tr>
				<td><s:hidden name="tbFixEntrust.isvalid" value="1" /></td>
			</tr>
			<tr>
				<td>委托书号</td>
				<td><s:textfield id="entrustCode"
						name="tbFixEntrust.entrustCode" /></td>
				<td>客户号</td>
				<td><s:textfield id="customerCode"
						name="tbFixEntrust.tbCustomer.customerCode" /></td>
				<td>客户姓名</td>
				<td><s:textfield id="customerName"
						name="tbFixEntrust.tbCustomer.customerName" /></td>
				<td>手机号码</td>
				<td><s:textfield id="telephone"
						name="tbFixEntrust.tbCustomer.telephone" /></td>
			</tr>
			<tr>
				<td>车牌号</td>
				<td><s:textfield id="licenseCode"
						name="tbFixEntrust.tbCarInfo.licenseCode" /></td>
				<td>修理日期</td>
				<td><s:textfield id="fixDateStart"
						name="tbFixEntrust.fixDateStart">

						<s:param name="value">
							<s:date name="tbFixEntrust.fixDateStart" format="yyyy-MM-dd" />
						</s:param>

					</s:textfield> <e3c:calendar for="fixDateStart" dataFmt="yyyy-MM-dd" /></td>
				<td>至</td>
				<td><s:textfield id="fixDateEnd" name="tbFixEntrust.fixDateEnd">

						<s:param name="value">
							<s:date name="tbFixEntrust.fixDateEnd" format="yyyy-MM-dd" />
						</s:param>


					</s:textfield> <e3c:calendar for="fixDateEnd" dataFmt="yyyy-MM-dd" /></td>
				<td>预竣工车辆查询</td>
				<td><s:textfield id="minutes" name="tbFixEntrust.minutes"
						onblur="isPositiveNum();" /></td>
			</tr>
			<tr>
				<td>竣工状况</td>
				<td>
				    <select  name="wjg">
				    	<option value=""></option>
				    	<option value="wjg">未竣工</option>
				    	<option value="yjg">已竣工</option>
				    </select>
				
				</td>
				
				<td>
					接待员
				</td>
				
				<td><s:select name="tbFixEntrust.tmUser.id"
						list="#request.tmUserMap" listKey="key" listValue="value"
						emptyOption="true"></s:select></td>
			</tr>

			<tr>
				<td colspan="8" align="center"><input type="submit" value="查询"
					/> &nbsp;&nbsp; <input
					type="reset" value="重置" /></td>
			</tr>
		</table>
		
	</s:form>
	<font color="red">重要：操作现有委托书前请先按下面的刷新按钮</font><br/>
	<e3t:table id="tbFixEntrustTable" uri="tbFixEntrustFindAction.action"
		var="tbFixEntrust" scope="request" items="tbFixEntrustList"
		mode="ajax" toolbarPosition="bottom" skin="E3002" pageSize="10"
		width="1100" height="340" caption="委托书(操作现有委托书前请先按下面的刷新按钮)">
		<e3t:column property="no" title="操作(操作现有委托书前请先按上面的刷新按钮)" sortable="false" width="460">
			<c:if
				test="${tbFixEntrust.entrustStatus==1||tbFixEntrust.entrustStatus==0||tbFixEntrust.entrustStatus==4||tbFixEntrust.entrustStatus==5}">
				<a
					href="javascript:forwardPage2('${tbFixEntrust.id}','${tbFixEntrust.carId}','tbFixEntrustForwardPageAction!forwardPage.action','finish',600,300);">
					<font color="blue"> 竣工 </font>
				</a>
			</c:if>

			<c:if test="${tbFixEntrust.entrustStatus==2}"> 
				&nbsp;&nbsp; 
  				<a href="javascript:noFinishEntrust('${tbFixEntrust.id}');"> <font
					color="blue">取消竣工 </font>
				</a>
			</c:if>
				&nbsp;&nbsp;
				
				<a
				href="javascript:editObject('${tbFixEntrust.id}','tbFixEntrustViewAction.action',600,300);">
				<font color="blue"> 查看 </font>
			</a>
				&nbsp;&nbsp;
			<c:choose>	
				<c:when test="${request.comName=='dfbz'||request.comName=='xtl'}">
				
				<c:if test="${tbFixEntrust.entrustStatus!=3}"> 
					<a
					href="javascript:forwardPage('${tbFixEntrust.id}','tbFixEntrustForwardPageAction!forwardPage.action','editPerson',600,300);">
					<font color="blue"> 修改派工 </font>
					</a>
				
				</c:if>
				
				</c:when>
				
				
				<c:otherwise>
				
					<a
					href="javascript:forwardPage('${tbFixEntrust.id}','tbFixEntrustForwardPageAction!forwardPage.action','editPerson',600,300);">
					<font color="blue"> 修改派工 </font>
					</a>
				</c:otherwise>
				
			</c:choose>	

			<c:choose>
				<c:when test="${request.comName=='dfbz'}">
				    &nbsp;&nbsp;
				<a href="#"
						onclick="javascript:window.open('tbFixEntrustPrintTemplateAction.action?id=${tbFixEntrust.id}')">
						<font color="blue"> 打印东风标致模板 </font>
					</a>
				
				&nbsp;&nbsp;
				<a href="#"
						onclick="javascript:window.open('tbFixEntrustPrintTemplateBlankAction.action?id=${tbFixEntrust.id}');">
						<font color="blue"> 套打东风标致模板 </font>
					</a>
				</c:when>

				<c:when test="${request.comName=='xtl'}">
				    &nbsp;&nbsp;
				<a href="#"
						onclick="javascript:window.open('tbFixEntrustPrintTemplateXTLAction.action?id=${tbFixEntrust.id}');">
						<font color="blue"> 打印雪铁龙模板 </font>
					</a>
				
				&nbsp;&nbsp;
				<a href="#"
						onclick="javascript:window.open('tbFixEntrustPrintTemplateBlankXTLAction.action?id=${tbFixEntrust.id}');">
						<font color="blue"> 套打雪铁龙模板 </font>
					</a>

				</c:when>

				<c:otherwise>
				    
				        &nbsp;&nbsp;
				        <a
						href="javascript:editObject('${tbFixEntrust.id}','tbFixEntrustPrintAction.action',600,300);">
						<font color="blue"> 打印委托书 </font>
					</a>
					
					&nbsp;&nbsp;
				        <a
						href="javascript:editObject('${tbFixEntrust.id}','tbFixEntrustPrePrintAction.action',600,300);">
						<font color="blue"> 打印预结算 </font>
					</a>

				</c:otherwise>
			</c:choose>

			<!--
				&nbsp;&nbsp;
				<a href="#" onclick="javascript:window.open('tbFixEntrustPrintTemplateAction.action?id=${tbFixEntrust.id}')">
					<font color="blue">
						打印东风标致模板
					</font>
				</a>
				
				&nbsp;&nbsp;
				<a href="#" onclick="javascript:window.open('tbFixEntrustPrintTemplateBlankAction.action?id=${tbFixEntrust.id}');">
					<font color="blue">
						套打东风标致模板
					</font>
				</a>
				
				&nbsp;&nbsp;
				<a href="#" onclick="javascript:window.open('tbFixEntrustPrintTemplateXTLAction.action?id=${tbFixEntrust.id}');">
					<font color="blue">
						打印雪铁龙模板
					</font>
				</a>
				
				&nbsp;&nbsp;
				<a href="#" onclick="javascript:window.open('tbFixEntrustPrintTemplateBlankXTLAction.action?id=${tbFixEntrust.id}');">
					<font color="blue">
						套打雪铁龙模板
					</font>
				</a>
				-->
				&nbsp;&nbsp;
				<a
				href="javascript:editObject('${tbFixEntrust.id}','tbFixEntrustSendPrintAction.action',600,300);">
				<font color="blue"> 打印派工单 </font>
			</a>

			<c:if
				test="${tbFixEntrust.entrustStatus==0||tbFixEntrust.entrustStatus==1||tbFixEntrust.entrustStatus==4||tbFixEntrust.entrustStatus==5}">
				 &nbsp;&nbsp;
				<a
					href="javascript:editObject('${tbFixEntrust.id}','tbFixEntrustForwardPageAction!forwardPage.action',600,300);">
					<font color="blue"> 修改 </font>
				</a>

			</c:if>

			<c:if test="${tbFixEntrust.entrustStatus==0}"> 
				 &nbsp;&nbsp; 
  				<a
					href="javascript:deleteObject('${tbFixEntrust.id}','tbFixEntrustDeleteAction.action');">
					<font color="blue">作废 </font>
				</a>
			</c:if>

			<c:if test="${session.tmUser.userName=='admin'}"> 
				 &nbsp;&nbsp; 
  				<a
					href="javascript:deleteObject('${tbFixEntrust.id}&flag=qz','tbFixEntrustDeleteAction.action');">
					<font color="blue">强制作废 </font>
				</a>
			</c:if>
				 &nbsp;&nbsp;
				<a href="#" onclick="alert('定制4S店打印模板,请联系荣经理,手机号码:18116345709');"><font
				color="blue">4S店打印模板</font></a>
		</e3t:column>
		<e3t:column property="entrustCode" title="委托书号" />
		<e3t:column property="fixDate" title="修理日期" width="120">
			<fmt:formatDate value="${tbFixEntrust.fixDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</e3t:column>
		<e3t:column property="estimateDate" title="预计竣工日期" width="120">
			<fmt:formatDate value="${tbFixEntrust.estimateDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</e3t:column>
		<e3t:column property="fixType" title="修理类型" />
		<e3t:column property="customerCode"
			beanProperty="tbCustomer.customerCode" title="客户号" />
		<e3t:column property="customerName" title="客户姓名" />
		<e3t:column property="carId" title="" hidden="true" />
		<e3t:column property="licenseCode" title="车牌号" />
		<e3t:column property="allTotal" title="总金额" />
		<e3t:column property="fixHourTotal" title="工时费总计" />
		<e3t:column property="stockOutPartTotal" title="发料费总计" />
		<e3t:column property="solePartTotal" title="销售费总计" />
		<e3t:column property="pjcb" title="配件成本" />
		<e3t:column property="modelTypeName" title="车型" />
		<e3t:column property="oilMeter" title="油表指示" />
		<e3t:column property="enterStationKilo" title="进站里程" />
		<e3t:column property="outStationKilo" title="出站里程" />
		<e3t:column property="remindMaintainKilo" title="提醒保养公里数" />
		<e3t:column property="remindMaintainDate" title="提醒保养日期" width="120">
			<fmt:formatDate value="${tbFixEntrust.remindMaintainDate}"
				pattern="yyyy-MM-dd" />
		</e3t:column>
		<e3t:column property="remindInsuranceDate" title="提醒保险日期" width="120">
			<fmt:formatDate value="${tbFixEntrust.remindInsuranceDate}"
				pattern="yyyy-MM-dd" />
		</e3t:column>
		<e3t:column property="wrongDescribe" title="故障描述" />
		<e3t:column property="beforeFixState" title="送修症状" />
		<e3t:column property="checkResult" title="检查结果" />
		<e3t:column property="remark" title="备注" />
		<e3t:column property="userRealName" title="接待员" />
		<e3t:column property="isFinishShow" title="是否竣工" />


	</e3t:table>
	
	配件成本合计：${request.pjcbTotal}
</body>
</html>
<script>
	function isPositiveNum() {
		var minutes = document.getElementById('minutes');

		if ('' != trim(minutes.value)) {
			if (!validatePositiveNum(minutes.value)) {

				alert('请输入正确的时间');

				minutes.value = '';
			}
		}

	}

	
</script>