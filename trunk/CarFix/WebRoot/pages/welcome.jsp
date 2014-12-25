<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.selfsoft.secrity.model.TmUser"%><html>
	<title>
	</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<script type="text/javascript" >
		function hotMapClick(type){
			/*
			if(type == 'yy'){
				parent.addTab(parent.tabPanel,92,'维修预约','business/tbBook/tbBookFindAction.action',true);
			}
			if(type == 'kwts'){
				parent.addTab(parent.tabPanel,93,'委托书','business/tbFixEntrust/tbFixEntrustFindAction.action',true);
			}
			if(type == 'pjcx'){
				parent.addTab(parent.tabPanel,54,'配件管理','tbPartInfoFindAction.action',true);
			}
			if(type == 'zpgr'){
				parent.addTab(parent.tabPanel,93,'委托书','business/tbFixEntrust/tbFixEntrustFindAction.action',true);
			}
			if(type == 'wxfl'){
				parent.addTab(parent.tabPanel,69,'维修发料','findMaintainContentAction.action',true);
			}
			if(type == 'wxjg'){
				parent.addTab(parent.tabPanel,93,'委托书','business/tbFixEntrust/tbFixEntrustFindAction.action',true);
			}
			if(type == 'cwjs'){
				parent.addTab(parent.tabPanel,84,'结算处理','business/tbBusinessBalance/tbBusinessBalanceForwardPageAction!forwardPage.action',true);
			}
			if(type == 'xlcx'){
				parent.addTab(parent.tabPanel,116,'维修业务查询统计','tbFixEntrustStatisticsAction.action',true);
			}
			if(type == 'jscx'){
				parent.addTab(parent.tabPanel,117,'结算档案查询统计','tbBusinessBalanceFindAction.action',true);
			}
			*/
		}

		function sendPass(){
			var date = new Date();
			var time = date.getTime();
			var userName = document.getElementById('userName');
			var oldPass = document.getElementById('oldPass');
			var newPass = document.getElementById('newPass');
			var confirmPass = document.getElementById('confirmPass');
			
			if(null==newPass.value||''==newPass.value||null==confirmPass.value||''==confirmPass.value){
				alert('请输入新密码');
				return;
			}
			var url = 'tmUserResetPasswordAction.action'+ '?userName=' + userName.value + '&oldPass='+ oldPass.value + '&newPass=' + newPass.value + '&confirmPass=' + confirmPass.value + '&time=' + time;
			var passAjax = new Ajax.Request(url, {
				method : 'post',

				onComplete : acquirePass,

				asynchronous : true

			});
		}
		//返回拼音
		function acquirePass(originalRequest){
			
			var info = originalRequest.responseText.split(',');
			
			if('success' == info[0]){
				
				Ext.MessageBox.alert('信息', '密码修改成功');
			}
			else{
				if('passFail' == info[1]){
					
					Ext.MessageBox.alert('信息', '原密码不正确,修改失败');
				}

				else if('newPassFail' == info[1]){
					
					Ext.MessageBox.alert('信息', '新密码不一致,修改失败');
				}
				
			}
		} 
	</script>
	<body  >
	<div align="center" style="width:100%;align:center" >
		<img  src="<%=request.getContextPath() %>/images/indexpage/ywjd.png" usemap="#Map" >
		<map name="Map" id="Map">
		  <area shape="rect" coords="39,219,145,287" href="#" onclick="hotMapClick('xlcx');" />
		  <area shape="rect" coords="36,297,145,365" href="#" onclick="hotMapClick('jscx');"  />
		  <area shape="rect" coords="232,234,295,348" href="#" onclick="hotMapClick('cwjs');"  />
		  <area shape="rect" coords="371,246,476,334" href="#" onclick="hotMapClick('wxjg');"  />
		  <area shape="rect" coords="558,248,630,337" href="#" onclick="hotMapClick('wxfl');"  />
		  <area shape="rect" coords="553,55,623,160" href="#" onclick="hotMapClick('zpgr');"  />
		  <area shape="rect" coords="371,61,477,168" href="#" onclick="hotMapClick('pjcx');"  />
		  <area shape="rect" coords="209,56,296,165" href="#" onclick="hotMapClick('kwts');"  />
		  <area shape="rect" coords="42,57,137,155" href="#" onclick="hotMapClick('yy');" />
		</map>
	</div>
	<form>
		<%TmUser tmUser = (TmUser)session.getAttribute("tmUser"); %>
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" id="userName" readonly="readonly" value="<%=tmUser.getUserName() %>"/></td>
			</tr>
			<tr>
				<td>原密码</td>
				<td><input type="password" id="oldPass"></td>
			</tr>
			<tr>
				<td>新密码</td>
				<td><input type="password" id="newPass"></td>
			</tr>
			<tr>
				<td>确认新密码</td>
				<td><input type="password" id="confirmPass"></td>
			</tr>
			<tr>
				<td><input type="button" value="确认" onclick="sendPass();"></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>	
	</body>
</html>
