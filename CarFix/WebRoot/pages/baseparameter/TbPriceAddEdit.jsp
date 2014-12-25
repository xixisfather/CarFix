<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修改价格金额</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<script type="text/javascript">
	
		function formValidate(){
		
			if(document.getElementById("schemeName").value == ""){
				alert("方案名称不能为空");
				document.getElementById("schemeName").focus();
				return false;
			}
			if(document.getElementById("minPrice").value == ""){
				alert("最小价格不能为空");
				document.getElementById("minPrice").focus();
				return false;
			}
			if(document.getElementById("maxPrice").value == ""){
				alert("最大价格不能为空");
				document.getElementById("maxPrice").focus();
				return false;
			}
			if(document.getElementById("price").value == ""){
				alert("加价金额不能为空");
				document.getElementById("price").focus();
				return false;
			}
			
		}
	</script>
	<body>
		<s:form action="updateTbPriceAddAction.action" onsubmit="return formValidate()">
		
			<s:hidden name="tbPriceAdd.id" ></s:hidden>
			<table>
				
				<tr>
					<td>方案名称</td>
					<td>
						<s:textfield id="schemeName" name="tbPriceAdd.schemeName" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>最小价格</td>
					<td>
						<s:textfield id="minPrice" name="tbPriceAdd.minPrice" />
						<font color="red">*</font>
					</td>
				</tr>
					<tr>
					<td>最大价格</td>
					<td>
						<s:textfield id="maxPrice" name="tbPriceAdd.maxPrice" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>加价金额</td>
					<td>
						<s:textfield id="rate" name="tbPriceAdd.price" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="submit" value="修改" />
						
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>
						
				</tr>
			</table>
		</s:form>
	</body>
</html>
