<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script language="javascript">
  			function gotoLogin(){
  				parent.window.location.href = "<%= request.getContextPath() %>";
  			}
		</script>

	</head>

	<body>
		<font color="red" >${msg}</font>
		<a href="#" onclick="gotoLogin();" >确认</a>
		
		<s:if test="#request.updateSys!=null">
			<s:form action="operateTmSysRegeditAction.action">
			
			<s:hidden name="tmSysRegedit.id" value="-100"></s:hidden>
			
			<table>
				<tr>
					<td>
						序列号:
					</td>
					<td>
						<s:textfield name="tmSysRegedit.sysSn" size="30" maxlength="50"></s:textfield>
					</td>
				</tr>
				
				<tr>

					<td align="center" >
						<input type="submit" value="修改" />
					</td>
					
					<td align="center" >
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
		</s:form>
		</s:if>
	</body>
</html>
