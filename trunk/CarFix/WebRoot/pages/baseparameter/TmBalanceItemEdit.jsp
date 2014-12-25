<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>结算项目明细修改</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseparameter/TmBalanceItem.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<s:head theme="ajax"/>
	</head>

	<body>
		<s:form id="formId" action="tmBalanceItemUpdateAction.action">
			<table>
				<tr>
					<s:hidden name="tmBalanceItem.id" />
				</tr>
				
				<tr>
					<td>结算项目</td>
					<td>
						<s:textfield id="tmBalanceName" name="tmBalanceItem.tmBalance.balanceName" disabled="true"></s:textfield>
						<s:hidden id="tmBalanceId" name="tmBalanceItem.tmBalance.id"/>
					</td>
				</tr>
				<tr>
					<td>明细名称</td>
					<td>
						<s:textfield id="itemName" name="tmBalanceItem.itemName" disabled="true"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>明细代码</td>
					<td>
						<s:textfield id="itemCode" name="tmBalanceItem.itemCode"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>是否手工输入</td>
					<td>
						<s:select id="allowHand" name="tmBalanceItem.allowHand" list="#request.isNoMap" listKey="key" listValue="value"></s:select>
					</td>
				</tr>
				<tr>
					<td>备注说明</td>
					<td>
						<s:textarea name="tmBalanceItem.itemRemark">
						
						</s:textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<s:updownselect 
							id="tmBalanceItemList"
							name="tmBalanceItemList" 
							list="#request.tmBalanceItemMap" 
							listKey="key" 
							listValue="value" 
							cssStyle="width:100;height:200;"
							allowMoveDown="false"
							allowMoveUp="false"
							allowSelectAll="false"
							multiple="false"
							ondblclick="combineFormula();"
						>
					
					</s:updownselect>
					</td>
				</tr>
				<tr>
					<td>计算公式</td>
					<td>
						<s:textarea id="formula" name="tmBalanceItem.formula" cols="30" rows="5"></s:textarea>
					</td>
				</tr>
				<tr>

					
					<td colspan="2" align="center">
						<input type="button" value="修改" id="btn" onclick="validateSubmit();"/>
					
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>

				</tr>
			</table>
		</s:form>
	</body>
</html>
