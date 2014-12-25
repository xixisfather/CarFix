<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title></title>
   		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	
   		<script type="text/javascript">
   			function addTmResource(){
   				var url = "<%= request.getContextPath() %>/tmResourceInsertAction.action?";
   				var formpars = Form.serialize($(tmResourceInsertAction));
   				var addAjax = new Ajax.Request(
   					url,
   					{method:'post',parameters: formpars, onComplete:responseFun}
   					);
   			}
   			
   			function responseFun(){
   				$(tmResourceInsertAction).reset();
   				parent.refreshNode();
   			}
   			
   		
   		</script>
  	</head>
  	<body>
  			<div>
  				在(${nodeName})节点下添加节点
  			</div>
  			<s:form action="tmResourceInsertAction.action">
  				<s:hidden name="tmResource.parentId"  value="%{#request.parentId}" ></s:hidden>
	    		<table>
	    			<tr>
	    				<td>资源名称：</td>
	    				<td><s:textfield  name="tmResource.resourceName" /></td>
	    			</tr>
	    			<tr>
	    				<td>资源链接：</td>
	    				<td><s:textfield cssStyle="width:1000" name="tmResource.resourcePath" /></td>
	    			</tr>
	    			<tr>
	    				<td>资源备注：</td>
	    				<td><s:textarea  name="tmResource.resourceDesc" /></td>
	    			</tr>
					
	    		</table>
	    		
	    		<input type="button" onclick="addTmResource();" value="保存" />
    		</s:form>
  	</body>
</html>
