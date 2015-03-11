<%@page import="com.selfsoft.baseinformation.model.TmCardTypeService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>卡种修改</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TmCardType.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		
	</head>

	<body>
		<s:form action="tmCardTypeUpdateAction.action" onsubmit="return formValidate()">
			<table>
				<tr>
					<td><s:hidden name="tmCardType.id"/></td>
				</tr>
				<tr>
					<td>
						卡种名称
					
						
						<s:textfield id="cardDesc" name="tmCardType.cardDesc" />
						<font color="red">*</font>
					</td>
					
				</tr>
				
				<tr>
					<td><hr/></td>
				</tr>
				
				<tr>
					<td>A1.一&nbsp;般&nbsp;消&nbsp;费(修理类型中没有【保险】关键字的)&nbsp;奖&nbsp;励&nbsp;规&nbsp;则:(<font color="red">满100返回3元,如果消费530元,那么返还  500/100 * 3 = 5*3 = 15元</font>)</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;1.工&nbsp;时&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="gsMFullMoney" name="tmCardType.gsMFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="gsMGiveMoney" name="tmCardType.gsMGiveMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;2.配&nbsp;件&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="pjMFullMoney" name="tmCardType.pjMFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="pjMGiveMoney" name="tmCardType.pjMGiveMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;3.工&nbsp;时&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="gsPFullMoney" name="tmCardType.gsPFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="gsPGivePoint" name="tmCardType.gsPGivePoint" size="4"/><font color="red">*</font>
						
						&nbsp;积分
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;4.配&nbsp;件&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="pjPFullMoney" name="tmCardType.pjPFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="pjPGivePoint" name="tmCardType.pjPGivePoint" size="4"/><font color="red">*</font>
						
						&nbsp;积分
					</td>
				</tr>
				
				<tr>
					<td><hr/></td>
				</tr>
				
				
				
				<tr>
					<td>A2.保&nbsp;险&nbsp;消&nbsp;费(修理类型中有【保险】关键字的)&nbsp;奖&nbsp;励&nbsp;规&nbsp;则:(<font color="red">满100返回3元,如果消费530元,那么返还  500/100 * 3 = 5*3 = 15元</font>)</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;1.工&nbsp;时&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="gsBxMFullMoney" name="tmCardType.gsBxMFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="gsBxMGiveMoney" name="tmCardType.gsBxMGiveMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;2.配&nbsp;件&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="pjBxMFullMoney" name="tmCardType.pjBxMFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="pjBxMGiveMoney" name="tmCardType.pjBxMGiveMoney" size="4" /><font color="red">*</font>
						
						&nbsp;元
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;3.工&nbsp;时&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="gsBxPFullMoney" name="tmCardType.gsBxPFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="gsBxPGivePoint" name="tmCardType.gsBxPGivePoint" size="4" /><font color="red">*</font>
						
						&nbsp;积分
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				
				
				
				
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;4.配&nbsp;件&nbsp;消&nbsp;费&nbsp;满&nbsp;
						
						<s:textfield id="pjBxPFullMoney" name="tmCardType.pjBxPFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;返&nbsp;还&nbsp;
						
						<s:textfield id="pjBxPGivePoint" name="tmCardType.pjBxPGivePoint" size="4" /><font color="red">*</font>
						
						&nbsp;积分
					</td>
				</tr>
				
				
				<tr>
					<td><hr/></td>
				</tr>
				
				<tr>
					<td>A3.结&nbsp;算&nbsp;优&nbsp;惠&nbsp;扣&nbsp;积&nbsp;分&nbsp;规&nbsp;则:(<font color="red">结算优惠满100元扣3积分,如果结算优惠530元,那么扣除  500/100 * 3 = 5*3 = 15积分</font>)</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
					
						&nbsp;&nbsp;&nbsp;&nbsp;1.结&nbsp;算&nbsp;优&nbsp;惠&nbsp;满&nbsp;
						
						<s:textfield id="yhMFullMoney" name="tmCardType.yhMFullMoney" size="4"/><font color="red">*</font>
						
						&nbsp;元&nbsp;扣&nbsp;除&nbsp;
						
						<s:textfield id="yhMMinusPoint" name="tmCardType.yhMMinusPoint" size="4"/><font color="red">*</font>
						
						&nbsp;积分
					</td>
				</tr>
				
				
				
				
				
				
				<tr>
					<td><hr/></td>
				</tr>
				
				
				
				<tr>
					<td>B.充&nbsp;值&nbsp;奖&nbsp;励&nbsp;规&nbsp;则:(<font color="red">满100送3元10积分,如果充值530元,奖励   5*3 = 15元, 5*10 = 50积分</font>)</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.一&nbsp;次&nbsp;性&nbsp;充&nbsp;值&nbsp;<s:textfield id="cFullMoney" name="tmCardType.cFullMoney" size="4"/><font color="red">*</font>&nbsp;元&nbsp;送&nbsp;<s:textfield id="cGiveMoney" name="tmCardType.cGiveMoney" size="4"/><font color="red">*</font>&nbsp;元&nbsp;<s:textfield id="cGivePoint" name="tmCardType.cGivePoint" size="4"/><font color="red">*</font>&nbsp;积&nbsp;分
					</td>
				</tr>
				
				<tr>
					<td><hr/></td>
				</tr>
				
				<tr>
					<td>
						C1.一&nbsp;般&nbsp;消&nbsp;费&nbsp;优&nbsp;惠&nbsp;率&nbsp;设&nbsp;定&nbsp;规&nbsp;则:(<font color="red">原价=0.00&nbsp;&nbsp;95折=0.05&nbsp;&nbsp;9折=0.10&nbsp;&nbsp;8折=0.2&nbsp;&nbsp;依次类推</font>)
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;1.工&nbsp;时&nbsp;优&nbsp;惠&nbsp;率<s:textfield id="gsYhl" name="tmCardType.gsYhl" size="4" onkeyup="clearNoNum22(this,0.01,1.00)"/><font color="red">*</font></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;2.配&nbsp;件&nbsp;优&nbsp;惠&nbsp;率<s:textfield id="pjYhl" name="tmCardType.pjYhl" size="4" onkeyup="clearNoNum22(this,0.01,1.00)"/><font color="red">*</font></td>
				</tr>
				
				<tr>
					<td><hr/></td>
				</tr>
				
				
				<tr>
					<td>
						C2.保&nbsp;险&nbsp;消&nbsp;费&nbsp;优&nbsp;惠&nbsp;率&nbsp;设&nbsp;定&nbsp;规&nbsp;则:(<font color="red">原价=0.00&nbsp;&nbsp;95折=0.05&nbsp;&nbsp;9折=0.10&nbsp;&nbsp;8折=0.2&nbsp;&nbsp;依次类推</font>)
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;1.工&nbsp;时&nbsp;优&nbsp;惠&nbsp;率<s:textfield id="gsBxYhl" name="tmCardType.gsBxYhl" size="4" onkeyup="clearNoNum22(this,0.01,1.00)"/><font color="red">*</font></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;2.配&nbsp;件&nbsp;优&nbsp;惠&nbsp;率<s:textfield id="pjBxYhl" name="tmCardType.pjBxYhl" size="4" onkeyup="clearNoNum22(this,0.01,1.00)"/><font color="red">*</font></td>
				</tr>
				
				<tr>
					<td><hr/></td>
				</tr>
				
				
				
				
				
				
				
				<tr>
					<td>D.积&nbsp;分&nbsp;兑&nbsp;换&nbsp;规&nbsp;则:(<font color="red">100积分兑换1元，如果250积分则兑换2元，依次类推</font>)</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="dhFullPoint" name="tmCardType.dhFullPoint" size="4"/><font color="red">*</font>&nbsp;积&nbsp;分&nbsp;兑&nbsp;换<s:textfield id="dhFullMoney" name="tmCardType.dhFullMoney" size="4"/><font color="red">*</font>&nbsp;元
					</td>
				</tr>
				
				<tr>
					<td><hr/></td>
				</tr>
				
				<tr>
					<td>D.增&nbsp;值&nbsp;服&nbsp;务&nbsp;定&nbsp;义:(<font color="red">定义刷卡结算的服务,如果无限次,尽量填写大数字如9999999</font>)</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				
				<%
				
				List<TmCardTypeService> tmCardTypeServiceList = (List<TmCardTypeService>)request.getAttribute("tmCardTypeServiceList");
			
				int size = 0;
				
				if(null != tmCardTypeServiceList) {
					
					size = tmCardTypeServiceList.size();
					
					int j = 0;
					
					for(TmCardTypeService tmCardTypeService : tmCardTypeServiceList) {
				%>
					
					
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="serviceName<%= j%>" name="serviceName<%= j%>" size="4" value="<%= tmCardTypeService.getServiceName()%>"/>服务<font color="red">*</font>&nbsp;<input type="text" id="serviceCount<%=j %>" name="serviceCount<%=j %>" size="4" value="<%= tmCardTypeService.getServiceCount()%>"/><font color="red">*</font>&nbsp;次
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
					
				
				<%		
				
						j++;
					}
				}
				
				%>
				
				
				<%for(int i = size ; i< 20; i++){%>
				
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="serviceName<%= i%>" name="serviceName<%= i%>" size="4" value=""/>服务<font color="red">*</font>&nbsp;<input type="text" id="serviceCount<%=i %>" name="serviceCount<%=i %>" size="4" value=""/><font color="red">*</font>&nbsp;次
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<%}%>
				
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
