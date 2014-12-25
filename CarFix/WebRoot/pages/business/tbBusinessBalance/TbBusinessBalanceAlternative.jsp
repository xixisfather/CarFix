<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>结算操作选择</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		
			<table>
				<tr>
					<td>
						<input type="radio" name="js_choose" id="js_wts" value="1" checked="checked" onclick="showElement();"/>委托书结算
					</td>
				</tr>
				<tbody id="wts_tb">
				<tr>
					<td>
						<s:include value="tbFixEntrustChooseAction.action"></s:include>
					</td>
				</tr>
				</tbody>
				<tr>
					<td>
						<input type="radio" name="js_choose" id="js_xsd" value="2" onclick="showElement();"/>销售单结算
					</td>
				</tr>
				<tbody id="xsd_tb" style="display: none;">
				<tr>
					<td>
						<s:include value="findBalanceSellAction.action"></s:include>
					</td>
				</tr>
				</tbody>
				<tr>
					<td>
						<input type="button" value="下一步" onclick="sendValue();"/>
					</td>
				</tr>
			</table>
		
	</body>
</html>
<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbBusinessBalance.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/pages/business/tbBusinessBalance/TbFixEntrust.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
<script language="javascript">
	
   
    
</script>