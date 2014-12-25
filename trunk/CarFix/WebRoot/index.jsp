<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title></title>
   
  	</head>
  
  	<body>
    	<s:form action="tmUserLoginAction.action" >
    		<s:textfield id="userName1" name="tmUser.userName" ></s:textfield>
    		<s:password name="tmUser.password" ></s:password>
    		<s:submit value="登陆" ></s:submit>
    	</s:form>
    	
    	<input type="button" value="获取父页面控件" onclick="test();"/>
  	</body>
</html>
<script>
	function test(){
	 var test  = parent.document.getElementById('userName').value;
	 
	 alert(test);
	}
</script>