<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>竣工委托书</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script language="javascript">
			function validateForm(){
			
				var outStationKilo = document.getElementById('outStationKilo');
				
				if(!validateAllPositiveNum(outStationKilo.value)){
				
					alert('出站里程填写不正确');
					
					return false;
				}
				
			}
		</script>
	</head>

	<body>
		<s:form action="tbFixEntrustFinishAction.action" onsubmit="return validateForm();">
			<table>
				<td>
					<s:hidden name="tbFixEntrust.id"></s:hidden>
				</td>
				<tr>
					<td>出站里程</td>
					<td>
						
						<s:textfield id="outStationKilo" name="tbFixEntrust.outStationKilo" maxlength="9"/>
					</td>
					
				</tr>
				
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="确定" />
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
