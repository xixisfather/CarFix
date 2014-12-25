<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>提前提醒天数定义修改</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmStoreDisk.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tmStoreDiskUpdateAction.action">
			<table>
				<tr>
					<td><s:hidden name="tmStoreDisk.id"/></td>
				</tr>
				<tr>
					<td>
						备份盘地址
						<s:textfield id="StoreDisk" name="tmStoreDisk.diskPath" />
						<font color="red">*(硬盘:\文件夹\文件夹....)</font>
					</td>
				</tr>	
				<tr>

					<td align="center">
						<input type="submit"
							value="<s:text name="TM_USER_BUTTON_SUBMIT"/>" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>		
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
