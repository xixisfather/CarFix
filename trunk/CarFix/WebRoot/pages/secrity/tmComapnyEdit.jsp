<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.selfsoft.framework.common.CommonMethod"%><html>
	<head>
		<title>企业资料修改</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<s:form action="updateTmCompanyAction.action">
			<s:hidden name="tmCompany.id" ></s:hidden>
			<table>
				<tr>
					<td>
						公司名称:
					</td>
					<td>
						<s:textfield name="tmCompany.companyName"  maxlength="20"></s:textfield>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						公司法人:
					</td>
					<td>
						<s:textfield name="tmCompany.lawerPerson"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						公司地址:
					</td>
					<td>
						<s:textfield name="tmCompany.companyAddress"  maxlength="50"></s:textfield>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>
						公司邮编:
					</td>
					<td>
						<s:textfield name="tmCompany.companyZipCode"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						公司电话:
					</td>
					<td>
						<s:textfield name="tmCompany.companyPhone"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						公司传真:
					</td>
					<td>
						<s:textfield name="tmCompany.companyFax"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						公司税号:
					</td>
					<td>
						<s:textfield name="tmCompany.taxCode"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						公司账号:
					</td>
					<td>
						<s:textfield name="tmCompany.companyAccount"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						售后服务总监:
					</td>
					<td>
						<s:textfield name="tmCompany.serviceLeader"  ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						开户银行:
					</td>
					<td>
						<s:textfield name="tmCompany.bankName"  ></s:textfield>
					</td>
				</tr>
				<% 
					String companyNamePinYin = (String)request.getSession().getAttribute("companyNamePinYin");
				
					String companyAddressPinYin = (String)request.getSession().getAttribute("companyAddressPinYin");
				
					String macAddress = CommonMethod.getMACAddress();
				%>
				<tr>
					<td>
						公司名称拼音
					</td>
					
					<td>
						<input type="text" value="<%=companyNamePinYin %>" size="20" disabled="disabled"/>
					</td>
				</tr>
				
				<tr>
					<td>
						公司地址拼音
					</td>
					
					<td>
						<input type="text" value="<%=companyAddressPinYin %>" size="20" disabled="disabled"/>
					</td>
				</tr>
				
				<tr>
					<td>
						机器码
					</td>
					
					<td>
						<input type="text" value="<%=macAddress %>" size="20" disabled="disabled"/>
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
	</body>
</html>
