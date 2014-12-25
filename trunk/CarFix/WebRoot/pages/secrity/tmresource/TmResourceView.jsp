<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title></title>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	</head>
  
  	<body>
    		<table>
    			<tr>
    				<td>资源名称：</td>
    				<td><s:textfield readonly="true" name="tmResource.resourceName" /></td>
    			</tr>
    			<tr>
    				<td>资源链接：</td>
    				<td><s:textfield readonly="true" name="tmResource.resourcePath" /></td>
    			</tr>
    			<tr>
    				<td>资源备注：</td>
    				<td><s:textarea readonly="true" name="tmResource.resourceDesc" /></td>
    			</tr>
				<tr>
    				<td>是否叶子节点：</td>
    				<td>
	    				<s:if test="tmResource.isLeaf == 1">
	    					是
	    				</s:if>
	    				<s:if test="tmResource.isLeaf == 0">
	    					否
	    				</s:if>
    				</td>
    			</tr>	
				
    			
    		</table>
    		
  	</body>
</html>
