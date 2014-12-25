<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
   		
   		
   		function formChecked(){
   			if(document.getElementById("userName").value == ""){
   				alert("温馨提示：用户名不能为空");
   				document.getElementById("userName").focus();
   				return false;
   			}
   			if(document.getElementById("password").value == ""){
   				alert("温馨提示：密码不能为空");
   				document.getElementById("password").focus();
   				return false;
   			}
   			if(document.getElementById("jobCode").value == ""){
   				alert("温馨提示：工号不能为空");
   				document.getElementById("jobCode").focus();
   				return false;
   			}
   			if(document.getElementById("userRealName").value == ""){
   				alert("温馨提示：姓名不能为空");
   				document.getElementById("userRealName").focus();
   				return false;
   			}
   			

			document.forms["tmUserInsertAction"].submit();
   		}
   		
   	</script>
  
  	<body>
    	<s:form action="tmUserInsertAction.action"  >
    		<table>
    			<tr>
    				<td>用户名：</td>
    				<td><s:textfield name="tmUser.userName" id="userName" /><font color="red">*</font></td>
    			
    				<td>密码：</td>
					<td><s:password name="tmUser.password" id="password" /><font color="red">*</font></td>
					
					<td>性别：</td>
					<td><s:select name="tmUser.sex" cssStyle="width:100" list="#request.sexMap" listKey="key" listValue="value"  ></s:select></td>
    			</tr>
    			<tr>
    				<td>工号：</td>
					<td><s:textfield name="tmUser.jobCode" id="jobCode" /><font color="red">*</font></td>
    				<td>姓名：</td>
					<td><s:textfield name="tmUser.userRealName" id="userRealName" /></td>
    				
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
					<td><s:hidden id="isFixPerson" name="tmUser.isFixPerson" value="0" ></s:hidden>
					<input type="checkbox" onclick="setChkVal(this);"  />
					</td>
    			</tr>
    			
    			<tr>
					<td>部门：</td>
					<td><s:select list="#request.depts" name="tmUser.tmDepartment.id" emptyOption="true"  listKey="id" listValue="deptName" ></s:select></td>
    				<td>工种：</td>
    				<td><s:select list="#request.workTypes" name="tmUser.tmWorkType.id"  listKey="id" emptyOption="true"  listValue="workName" ></s:select></td>
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
    				<td><s:select list="#request.roles" name="roleId"  listKey="id" listValue="roleName" ></s:select>
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
    			
    				<td align="center"><input type="button" onclick="formChecked();" value="<s:text name="TM_STORE_HOUSE_BUTTON_SUBMIT"  />"/></td>
    			</tr>
    		</table>
    		
    	</s:form>
  	</body>
</html>
