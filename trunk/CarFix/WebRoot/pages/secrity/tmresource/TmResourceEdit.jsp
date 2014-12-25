<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title></title>
   		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	
   		<script type="text/javascript">
   			function editTmResource(){
   				var url = "<%= request.getContextPath() %>/tmResourceUpdateAction.action?";
   				var formpars = Form.serialize($(tmResourceUpdateAction));
   				var addAjax = new Ajax.Request(
   					url,
   					{method:'post',parameters: formpars, onComplete:responseFun}
   					);
   			}
   			
   			function responseFun(){
   				parent.refreshParentNode();
   			}
   			
   		
   		</script>
  	</head>
  
  	<body>
  	
  		<s:form action="tmResourceUpdateAction.action" >
    		<table>
    			<s:hidden name="tmResource.id" ></s:hidden>
    			<s:hidden name="tmResource.parentId" ></s:hidden>
    			<s:hidden name="tmResource.isLeaf" ></s:hidden>
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
    				<td><s:textarea name="tmResource.resourceDesc" /></td>
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
    		
    		<input type="button" onclick="editTmResource();" value="修改" />
    		<s:reset value="重置" ></s:reset>
    	</s:form>
    		
  	</body>
</html>
