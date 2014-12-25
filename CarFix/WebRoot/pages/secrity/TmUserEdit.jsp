<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title></title>
   		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
  	</head>
  <script type="text/javascript">
   		function setChkVal(chk){
   			if(chk.checked)
   				document.getElementById("isFixPerson").value = 1;
   			else
   				document.getElementById("isFixPerson").value = 0;
   		}
   		
   	</script>
  	<body>
    	<s:form action="tmUserUpdateAction.action">
    					<s:hidden name="tmUser.id"/>
   						<s:hidden name="tmUserRole.id"/>
   						<s:hidden name="tmUserRole.userId"/>
   						<s:hidden name="tmUser.userStatus"></s:hidden>
    		<table>
    			<tr>
    				<td>用户名：</td>
    				<td><s:textfield name="tmUser.userName" /></td>
    			
    				<td>密码：</td>
					<td><s:textfield name="tmUser.password"  /></td>
					
					<td>性别：</td>
					<td><s:select name="tmUser.sex" cssStyle="width:100" list="#request.sexMap" listKey="key" listValue="value"  ></s:select></td>
    			</tr>
    			<tr>
    				<td>工号：</td>
					<td><s:textfield name="tmUser.jobCode" /></td>
    				<td>姓名：</td>
					<td><s:textfield name="tmUser.userRealName" /></td>
    				
					<td>学历：</td>
					<td><s:select name="tmUser.edu" cssStyle="width:100" list="#request.eduMap" listKey="key" listValue="value"  /></td>
    			</tr>
    			
    			<tr>
   					<td>手机：</td>
					<td><s:textfield name="tmUser.telephone" /></td>
    				<td>出生日期：</td>
					<td><s:textfield name="tmUser.birthday" id="birthday"/>
						<e3c:calendar for="birthday" dataFmt="yyyy-MM-dd" />
					</td>
    				
    				<td>是否为修理人员：</td>
					<td>
						<s:hidden id="isFixPerson" name="tmUser.isFixPerson" ></s:hidden>
						
						<s:if test="tmUser.isFixPerson == 1">
							<input type="checkbox" checked="checked" onclick="setChkVal(this);" />
						</s:if>
						<s:if test="tmUser.isFixPerson == 0">
							<input type="checkbox" onclick="setChkVal(this);" />
						</s:if>
					</td>
    			</tr>
    			
    			<tr>
					<td>部门：</td>
					<td><s:select list="#request.depts" name="tmUser.tmDepartment.id" emptyOption="true"  listKey="id" listValue="deptName" ></s:select></td>
    				<td>工种：</td>
    				<td><s:select list="#request.workTypes" name="tmUser.tmWorkType.id"  listKey="id" listValue="workName" ></s:select></td>
    				<td>基本工资：</td>
    				<td><s:textfield name="tmUser.salary" /></td>
    			</tr>
    			
    			<tr>
    				<td>邮编：</td>
					<td><s:textfield name="tmUser.zipCode" /></td>
					<td>技术等级：</td>
					<td><s:textfield name="tmUser.tecLevel" /></td>
    				<td>电话：</td>
    				<td><s:textfield name="tmUser.phone" /></td>
    			</tr>
    			<tr>
    				<td>工作日期：</td>
					<td>
						<s:textfield name="tmUser.startWorkTime" id="startWorkTime"/>
						<e3c:calendar for="startWorkTime" dataFmt="yyyy-MM-dd" />
					</td>
					<td>入站日期：</td>
					<td>
						<s:textfield name="tmUser.enterStationTime" id="enterStationTime"/>
						<e3c:calendar for="enterStationTime" dataFmt="yyyy-MM-dd" />
					</td>
    			</tr>
    			<tr>
					<td>住址：</td>
					<td><s:textfield name="tmUser.address" /></td>
    				<td>角色：</td>
    				<td>
    				<s:select list="#request.roleList" listKey="id" listValue="roleName" name="tmUserRole.roleId" headerKey="0" headerValue="请选择" >
						</s:select>
    				</td>
    			</tr>
    			<tr>
    				<td>简历：</td>
					<td colspan="6" ><s:textarea cols="83" rows="5" name="tmUser.cv" /></td>
    			</tr>
    			<tr>
    				<td>培训信息：</td>
					<td colspan="6"  > <s:textarea cols="83" rows="5" name="tmUser.trainInfo" /></td>
    			</tr>
    			<tr>
    			
    				<td align="center"><input type="submit" value="<s:text name="TM_STORE_HOUSE_BUTTON_SUBMIT"/>"/></td>
    			</tr>
    		</table>
						
    		
    	</s:form>
  	</body>
</html>
