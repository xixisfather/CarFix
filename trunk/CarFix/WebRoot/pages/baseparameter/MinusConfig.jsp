<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>负出库配置</title>
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
		function updateMinus(){
			var pvalue = document.getElementById('pvalue');
			if(document.getElementsByName('minusradio')[0].checked){
				pvalue.value = -1;
			}else{
				pvalue.value = 1;
			}
			return true;
		}
	</script>
	<body>
		<s:form action="updateTmDictionaryAction.action" onsubmit="updateMinus();" >
			<s:hidden name="tmDictionary.id" ></s:hidden>
			<s:hidden name="tmDictionary.paramtype" ></s:hidden>
			<s:hidden name="tmDictionary.paramvalue" id="pvalue" ></s:hidden>
			<s:radio name="minusradio"  listKey="key" listValue="value" list="#request.minusMap" theme="simple"/>  
			<s:submit value="提交" ></s:submit>
		</s:form>
		
		<SCRIPT type="text/javascript">
			if(document.getElementById('pvalue').value == -1 ){
				document.getElementsByName('minusradio')[0].checked = true;
			}else{
				document.getElementsByName('minusradio')[1].checked = true;
			}
		</SCRIPT>
	</body>
</html>
