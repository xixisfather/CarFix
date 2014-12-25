<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.selfsoft.baseparameter.model.TmCarModelType"%>
<%@page import="com.selfsoft.baseparameter.model.TmFixType"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>经营状况数据导入</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ext/css/tableIcon.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext/js/ext-base.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext/js/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext/js/ext-lang-zh_CN.js"
			charset="UTF-8"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js" charset="UTF-8"></script>
		<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 947px;
	height: 24px;
	z-index: 1;
	left: 109px;
}

#apDiv2 {
	position: absolute;
	width: 500px;
	height: 431px;
	z-index: 2;
	left: 11px;
	top: 41px;
}

#apDiv3 {
	position: absolute;
	width: 507px;
	height: 430px;
	z-index: 3;
	left: 514px;
	top: 41px;
}

#apDiv4 {
	position: absolute;
	width: 469px;
	height: 26px;
	z-index: 1;
	left: 28px;
	top: -2px;
}

#apDiv5 {
	position: absolute;
	width: 236px;
	height: 403px;
	z-index: 2;
	left: 29px;
	top: 26px;
}

#apDiv6 {
	position: absolute;
	width: 235px;
	height: 403px;
	z-index: 3;
	left: 266px;
	top: 26px;
}

#apDiv7 {
	position: absolute;
	width: 477px;
	height: 232px;
	z-index: 1;
	left: 27px;
	top: -1px;
}

#apDiv8 {
	position: absolute;
	width: 479px;
	height: 196px;
	z-index: 2;
	left: 27px;
	top: 232px;
}

#apDiv9 {
	position: absolute;
	width: 750px;
	height: 343px;
	z-index: 1;
	left: -268px;
	top: 406px;
}

#apDiv10 {
	position: absolute;
	width: 253px;
	height: 181px;
	z-index: 1;
	left: 748px;
	top: -2px;
}

#apDiv11 {
	position: absolute;
	width: 232px;
	height: 207px;
	z-index: 1;
	left: 27px;
	top: -1px;
}

#apDiv12 {
	position: absolute;
	width: 256px;
	height: 132px;
	z-index: 4;
	left: 765px;
	top: 687px;
}

#apDiv13 {
	position: absolute;
	width: 210px;
	height: 305px;
	z-index: 2;
	left: 27px;
	top: 1px;
}

#apDiv14 {
	position: absolute;
	width: 511px;
	height: 101px;
	z-index: 3;
	left: 239px;
	top: 1px;
}

#apDiv15 {
	position: absolute;
	width: 510px;
	height: 113px;
	z-index: 4;
	left: 239px;
	top: 103px;
}

#apDiv16 {
	position: absolute;
	width: 481px;
	height: 78px;
	z-index: 5;
	left: 266px;
	top: 224px;
}

#apDiv17 {
	position: absolute;
	width: 479px;
	height: 103px;
	z-index: 1;
	left: 28px;
	top: 0px;
}

#apDiv18 {
	position: absolute;
	width: 480px;
	height: 111px;
	z-index: 1;
	left: 28px;
	top: 4px;
}

#apDiv19 {
	position: absolute;
	width: 722px;
	height: 31px;
	z-index: 6;
	left: 27px;
	top: 308px;
}
#apDiv20 {
	position:absolute;
	width:288px;
	height:31px;
	z-index:5;
	left: 325px;
	top: 825px;
}
-->
</style>
	</head>

	<body>
		<s:form action="tbBusinessSpecialBalanceHandleFinanceUpdateAction.action">
		<div id="apDiv1">
			<table width="1000" border="0">
				<tr>
					<td width="90">
						<s:hidden name="tbBusinessSpecialBalanceHandleFinance.id"></s:hidden>
						<s:hidden name="tbBusinessSpecialBalanceHandleFinance.userId" value="%{#session.tmUser.id}"></s:hidden>
					</td>
					<td width="28">
						
					</td>
					<td width="611">
						统计月份<font color="red">*(每个月只能录入一次)</font>
						
						<s:textfield id="staticsDate" name="tbBusinessSpecialBalanceHandleFinance.staticsDate" readonly="true" size="15"/> 
						
					</td>
					<td width="243">
						金额单位:人民币元
					</td>
				</tr>
			</table>
		</div>
		<div id="apDiv2" style="border: 1px; border-style: solid;">
			<table width="28" height="427" border="0">
				<tr>
					<td>
						<p>
							<strong>修</strong>
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							<strong>理</strong>
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							<strong>情</strong>
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							<strong>况</strong>
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<div id="apDiv6">
				<table width="236" height="94" border="0">
					<tr>
						<td colspan="2">
							按车型统计辆次
						</td>
					</tr>
					
					<s:if test="#request.tmCarModelTypeList!=null">
						
							<%
						
						int i = 0;
						
						for(i = 0 ; i < ((List)request.getAttribute("tmCarModelTypeList")).size(); i++){
					
						
					%>
						<tr>
								<td width="95" height="26">
									<%=((List<TmCarModelType>)request.getAttribute("tmCarModelTypeList")).get(i).getModelCode() %>
								</td>
								<td width="124">
									<input type="text" id="tmCarModelType<%=i %>" name="tmCarModelType<%=i %>" size="3" value="<%=((List)request.getAttribute("staticCarModelTypeList")).get(i) %>"/>
								</td>
							</tr>
					<%	
							
						
						}
					 %>
					
					
				
					</s:if>
				
				</table>
				<div id="apDiv9" style="border: 1px; border-style: solid;">
					<div id="apDiv10">
						<table width="28" height="182" border="0">
							<tr>
								<td align="center" valign="top">
									<p>
										<strong>工</strong>
									</p>
									<p>
										<strong>时</strong>
									</p>
									<p>
										<strong>完</strong>
									</p>
									<p>
										<strong>成</strong>
									</p>
									<p>
										<strong>情</strong>
									</p>
									<p>
										<strong>况</strong>
									</p>
								</td>
							</tr>
						</table>
						<div id="apDiv11" style="border: 1px; border-style: solid;">
							<table width="224" height="212" border="0">
								<tr>
									<td width="113">
										未完工工时
									</td>
									<td width="101">
										<s:textfield id="unFixHour" name="tbBusinessSpecialBalanceHandleFinance.unFixHour" size="3" onkeyup="addElement('allFixHour,unFixHour,hasFixHour')"/>
									</td>
								</tr>
								<tr>
									<td>
										完工工时
									</td>
									<td>
										<s:textfield id="hasFixHour" name="tbBusinessSpecialBalanceHandleFinance.hasFixHour" size="3" onkeyup="addElement('allFixHour,unFixHour,hasFixHour')"/>
									</td>
								</tr>
								<tr>
									<td>
										总修理工时
									</td>
									<td>
										<s:textfield id="allFixHour" name="tbBusinessSpecialBalanceHandleFinance.allFixHour" size="3"/>
										<font color="blue">#</font>
									</td>
								</tr>
								<tr>
									<td>
										结算工时
									</td>
									<td>
										<s:textfield id="payedFixHour" name="tbBusinessSpecialBalanceHandleFinance.payedFixHour" size="3" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<table width="27" height="347" border="0">
						<tr>
							<td valign="top">
								<p>
									&nbsp;
								</p>
								<p>
									<strong>结</strong>
								</p>
								<p>
									<strong>算</strong>
								</p>
								<p>
									<strong>及</strong>
								</p>
								<p>
									<strong>付</strong>
								</p>
								<p>
									<strong>款</strong>
								</p>
								<p>
									<strong>情</strong>
								</p>
								<p>
									<strong>况</strong>
								</p>
							</td>
						</tr>
					</table>
					<div id="apDiv19">
						<table width="721" height="32" border="0">
							<tr>
								<td width="85">
									税后毛利润
								</td>
								<td width="33">
									&nbsp;
								</td>
								<td width="112">
									<s:textfield id="profitVal" name="tbBusinessSpecialBalanceHandleFinance.profitVal" size="8" readonly="true" />
									<font color="blue">#</font>
								</td>
								<td width="23">
									=
								</td>
								<td width="41">
									产值
								</td>
								<td width="30">
									&nbsp;
								</td>
								<td width="132">
									<s:textfield id="purchaseVal" name="tbBusinessSpecialBalanceHandleFinance.purchaseVal" size="8"  onkeyup="substractElement('profitVal','purchaseVal','partCoseVal')"/>/1.17
								</td>
								<td width="23">
									—
								</td>
								<td width="86">
									材料成本
								</td>
								<td width="114">
									<s:textfield id="partCoseVal" name="tbBusinessSpecialBalanceHandleFinance.partCoseVal" size="8"  onkeyup="substractElement('profitVal','purchaseVal','partCoseVal')"/>
								</td>
							</tr>
						</table>
					</div>
					<div id="apDiv16" style="border: 1px; border-style: solid;">
						<table width="478" height="73" border="0">
							<tr>
								<td width="17" height="31">
									&nbsp;
								</td>
								<td width="84">
									免费
								</td>
								<td width="110">
									<s:textfield id="numFree" name="tbBusinessSpecialBalanceHandleFinance.numFree" size="3" />
									笔
								</td>
								<td width="42">
									&nbsp;
								</td>
								<td width="107">
									&nbsp;
								</td>
								<td width="45">
									减免
								</td>
								<td width="75">
									<s:textfield id="freeCount" name="tbBusinessSpecialBalanceHandleFinance.freeCount" size="8" />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									共结算
								</td>
								<td>
									<s:textfield id="numHas" name="tbBusinessSpecialBalanceHandleFinance.numHas" size="3" />
									笔
								</td>
								<td>
									收款
								</td>
								<td>
									<s:textfield id="patedCount" name="tbBusinessSpecialBalanceHandleFinance.patedCount" size="8" />
								</td>
								<td>
									欠款
								</td>
								<td>
									<s:textfield id="oweCount" name="tbBusinessSpecialBalanceHandleFinance.oweCount" size="8" />
								</td>
							</tr>
						</table>
					</div>
					<div id="apDiv15">
						<table width="27" height="114" border="0">
							<tr>
								<td valign="top">
									<p>
										<strong>已</strong>
									</p>
									<p>
										<strong>付</strong>
									</p>
									<p>
										<strong>清</strong>
									</p>
								</td>
							</tr>
						</table>
						<div id="apDiv18" style="border: 1px; border-style: solid;">
							<table width="356" height="113" border="0">
								<tr>
									<td width="79">
										修理销售
									</td>
									<td width="108">
										<s:textfield id="hasPayFixSole" name="tbBusinessSpecialBalanceHandleFinance.hasPayFixSole" size="8" />
									</td>
									<td width="43">
										收款
									</td>
									<td width="108">
										<s:textfield id="hasPayFixSolePayed" name="tbBusinessSpecialBalanceHandleFinance.hasPayFixSolePayed" size="8" />
									</td>
								</tr>
								<tr>
									<td>
										单独销售
									</td>
									<td>
										<s:textfield id="hasPayUniSole" name="tbBusinessSpecialBalanceHandleFinance.hasPayUniSole" size="8" />
									</td>
									<td>
										收款
									</td>
									<td>
										<s:textfield id="hasPayUniSolePayed" name="tbBusinessSpecialBalanceHandleFinance.hasPayUniSolePayed" size="8" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div id="apDiv13" style="border: 1px; border-style: solid;">
						<table width="210" height="305" border="0">
							<tr>
								<td width="99">
									修理工时费
								</td>
								<td width="101">
									<s:textfield id="hasFixFee" name="tbBusinessSpecialBalanceHandleFinance.hasFixFee" size="8"  onkeyup="addElement('hasFeeCount,hasFixFee,hasPartFee,hasSoleFee')"/>
								</td>
							</tr>
							<tr>
								<td>
									修理材料费
								</td>
								<td>
									<s:textfield id="hasPartFee" name="tbBusinessSpecialBalanceHandleFinance.hasPartFee" size="8"  onkeyup="addElement('hasFeeCount,hasFixFee,hasPartFee,hasSoleFee')"/>
								</td>
							</tr>
							<tr>
								<td>
									配件销售额
								</td>
								<td>
									<s:textfield id="hasSoleFee" name="tbBusinessSpecialBalanceHandleFinance.hasSoleFee" size="8"  onkeyup="addElement('hasFeeCount,hasFixFee,hasPartFee,hasSoleFee')"/>
								</td>
							</tr>
							<tr>
								<td>
									结算总金额
								</td>
								<td>
									<s:textfield id="hasFeeCount" name="tbBusinessSpecialBalanceHandleFinance.hasFeeCount" size="8" />
									<font color="blue">#</font>
								</td>
							</tr>
						</table>
					</div>
					<div id="apDiv14">
						<table width="28" height="101" border="0">
							<tr>
								<td valign="top">
									<p>
										<strong>未</strong>
									</p>
									<p>
										<strong>付</strong>
									</p>
									<p>
										<strong>清</strong>
									</p>
								</td>
							</tr>
						</table>
						<div id="apDiv17" style="border: 1px; border-style: solid;">
							<table width="482" height="103" border="0">
								<tr>
									<td width="78">
										修理销售
									</td>
									<td width="105">
										<s:textfield id="unPayFixSole" name="tbBusinessSpecialBalanceHandleFinance.unPayFixSole" size="8" />
									</td>
									<td width="43">
										收款
									</td>
									<td width="107">
										<s:textfield id="unPayFixSolePayed" name="tbBusinessSpecialBalanceHandleFinance.unPayFixSolePayed" size="8" />
									</td>
									<td width="45">
										欠款
									</td>
									<td width="78">
										<s:textfield id="unPayFixSoleOwe" name="tbBusinessSpecialBalanceHandleFinance.unPayFixSoleOwe" size="8" />
									</td>
								</tr>
								<tr>
									<td>
										单独销售
									</td>
									<td>
										<s:textfield id="unPayUniSole" name="tbBusinessSpecialBalanceHandleFinance.unPayUniSole" size="8" />
									</td>
									<td>
										收款
									</td>
									<td>
										<s:textfield id="unPayUniSolePayed" name="tbBusinessSpecialBalanceHandleFinance.unPayUniSolePayed" size="8" />
									</td>
									<td>
										欠款
									</td>
									<td>
										<s:textfield id="unPayUniSoleOwe" name="tbBusinessSpecialBalanceHandleFinance.unPayUniSoleOwe" size="8" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="apDiv5" style="border: 1px; border-style: solid;">
				<table width="236" border="0">
					<tr>
						<td height="28" colspan="2">
							按修理类型统计辆次
						</td>
					</tr>
					
					<s:if test="#request.tmFixTypeList!=null">
						
						
								<%
						
						int i = 0;
						
						for(i = 0 ; i < ((List)request.getAttribute("tmFixTypeList")).size(); i++){
					
						
					%>
						<tr>
								<td width="95" height="26">
									<%=((List<TmFixType>)request.getAttribute("tmFixTypeList")).get(i).getFixType() %>
								</td>
								<td width="124">
									<input type="text" id="tmFixType<%=i %>" name="tmFixType<%=i %>" size="3" value="<%=((List)request.getAttribute("staticFixTypeList")).get(i) %>"/>
								</td>
							</tr>
					<%	
							
						
						}
					 %>
					
					
						
					</s:if>
					
				</table>
			</div>
			<div id="apDiv4" style="border: 1px; border-style: solid;">
				<table width="472" border="0">
					<tr>
						<td width="48">
							总计<font color="blue">#</font>
						</td>
						<td width="79">
							<s:textfield id="countAll" name="tbBusinessSpecialBalanceHandleFinance.countAll" readonly="true" size="3" />
							笔
							
						</td>
						<td width="83">
							修理销售
						</td>
						<td width="80">
							<s:textfield id="numFixSole" name="tbBusinessSpecialBalanceHandleFinance.numFixSole" size="3"  onkeyup="addElement('countAll,numFixSole,numUniSole')"/>
							笔
						</td>
						<td width="77">
							单独销售
						</td>
						<td width="79">
							<s:textfield id="numUniSole" name="tbBusinessSpecialBalanceHandleFinance.numUniSole" size="3"  onkeyup="addElement('countAll,numFixSole,numUniSole')"/>
							笔
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="apDiv3" style="border: 1px; border-style: solid;">
			<table width="28" height="427" border="0">
				<tr>
					<td width="21" height="227" valign="top">
						<p>
							<strong>未</strong>
						</p>
						<p>
							<strong>结</strong>
						</p>
						<p>
							<strong>算</strong>
						</p>
						<p>
							<strong>销</strong>
						</p>
						<p>
							<strong>售</strong>
						</p>
						<p>
							<strong>额</strong>
						</p>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<p>
							&nbsp;
						</p>
						<p>
							<strong>索</strong>
						</p>
						<p>
							<strong>保</strong>
						</p>
						<p>
							<strong>返</strong>
						</p>
						<p>
							<strong>工</strong>
						</p>
					</td>
				</tr>
			</table>
			<div id="apDiv8" style="border: 1px; border-style: solid;">
				<table width="471" height="198" border="0">
					<tr>
						<td width="97">
							（结算）
						</td>
						<td width="112">
							索 赔
						</td>
						<td width="133">
							免费保养
						</td>
						<td width="111">
							返 工
						</td>
					</tr>
					<tr>
						<td>
							工时费
						</td>
						<td>
							<s:textfield id="spFixFee" name="tbBusinessSpecialBalanceHandleFinance.spFixFee" size="8"  onkeyup="addElement('spFeeCount,spFixFee,spPartFee')"/>
						</td>
						<td>
							<s:textfield id="byFixFree" name="tbBusinessSpecialBalanceHandleFinance.byFixFree" size="8"  onkeyup="addElement('byFeeCount,byFixFree,byPartFee')"/>
						</td>
						<td>
							<s:textfield id="fgFixFee" name="tbBusinessSpecialBalanceHandleFinance.fgFixFee" size="8"  onkeyup="addElement('fgFeeCount,fgFixFee,fgPartFee')"/>
						</td>
					</tr>
					<tr>
						<td>
							材料费
						</td>
						<td>
							<s:textfield id="spPartFee" name="tbBusinessSpecialBalanceHandleFinance.spPartFee" size="8"  onkeyup="addElement('spFeeCount,spFixFee,spPartFee')"/>
						</td>
						<td>
							<s:textfield id="byPartFee" name="tbBusinessSpecialBalanceHandleFinance.byPartFee" size="8"  onkeyup="addElement('byFeeCount,byFixFree,byPartFee')"/>
						</td>
						<td>
							<s:textfield id="fgPartFee" name="tbBusinessSpecialBalanceHandleFinance.fgPartFee" size="8"  onkeyup="addElement('fgFeeCount,fgFixFee,fgPartFee')"/>
						</td>
					</tr>
					<tr>
						<td>
							合计
						</td>
						<td>
							<s:textfield id="spFeeCount" name="tbBusinessSpecialBalanceHandleFinance.spFeeCount" size="8" readonly="true" />
							<font color="blue">#</font>
						</td>
						<td>
							<s:textfield id="byFeeCount" name="tbBusinessSpecialBalanceHandleFinance.byFeeCount" size="8" readonly="true" />
							<font color="blue">#</font>
						</td>
						<td>
							<s:textfield id="fgFeeCount" name="tbBusinessSpecialBalanceHandleFinance.fgFeeCount" size="8" readonly="true" />
							<font color="blue">#</font>
						</td>
						
					</tr>
				</table>
			</div>
			<div id="apDiv7" style="border: 1px; border-style: solid;">
				<table width="471" height="233" border="0">
					<tr>
						<td height="45">
							修理销售
						</td>
						<td>
							<s:textfield id="unNumFixSole" name="tbBusinessSpecialBalanceHandleFinance.unNumFixSole" size="3"  onkeyup="addElement('unSoleCount,unNumFixSole,unNumUniSole')"/>
							笔
						</td>
						<td width="136">
							修理工时费
						</td>
						<td width="108">
							<s:textfield id="unFixFee" name="tbBusinessSpecialBalanceHandleFinance.unFixFee" size="8"  onkeyup="addElement('unFeeCount,unFixFee,unFixPartFee,unUniPartFee')"/>
						</td>
					</tr>
					<tr>
						<td height="38">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							修理材料费
						</td>
						<td>
							<s:textfield id="unFixPartFee" name="tbBusinessSpecialBalanceHandleFinance.unFixPartFee" size="8"  onkeyup="addElement('unFeeCount,unFixFee,unFixPartFee,unUniPartFee')"/>
						</td>
					</tr>
					<tr>
						<td height="42">
							单独销售
						</td>
						<td>
							<s:textfield id="unNumUniSole" name="tbBusinessSpecialBalanceHandleFinance.unNumUniSole" size="3"  onkeyup="addElement('unSoleCount,unNumFixSole,unNumUniSole')"/>
							笔
						</td>
						<td>
							配件销售额
						</td>
						<td>
							<s:textfield id="unUniPartFee" name="tbBusinessSpecialBalanceHandleFinance.unUniPartFee" size="8"  onkeyup="addElement('unFeeCount,unFixFee,unFixPartFee,unUniPartFee')"/>
						</td>
					</tr>
					<tr>
						<td height="39">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							合计
						</td>
						<td>
							<s:textfield id="unSoleCount" name="tbBusinessSpecialBalanceHandleFinance.unSoleCount" size="3"  readonly="true"/>
							笔
							<font color="blue">#</font>
						</td>
						<td>
							合计 金额
						</td>
						<td>
							<s:textfield id="unFeeCount" name="tbBusinessSpecialBalanceHandleFinance.unFeeCount" size="8"  readonly="true"/>
							<font color="blue">#</font>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="apDiv12" style="border: 1px; border-style: solid;">
			<table width="254" height="127" border="0">
				<tr>
					<td width="135" height="45">
						修理材料成本
					</td>
					<td width="109">
						<s:textfield id="fixPartCosr" name="tbBusinessSpecialBalanceHandleFinance.fixPartCosr" size="8"  onkeyup="addElement('fixPartCount,fixPartCosr,solePartCost')"/>
					</td>
				</tr>
				<tr>
					<td height="39">
						配件销售成本
					</td>
					<td>
						<s:textfield id="solePartCost" name="tbBusinessSpecialBalanceHandleFinance.solePartCost" size="8"  onkeyup="addElement('fixPartCount,fixPartCosr,solePartCost')"/>
					</td>
				</tr>
				<tr>
					<td height="35">
						材料成本合计
					</td>
					<td>
						<s:textfield id="fixPartCount" name="tbBusinessSpecialBalanceHandleFinance.fixPartCount" size="8" readonly="true" />
						<font color="blue">#</font>
					</td>
				</tr>
			</table>
		</div>
		<div id="apDiv20">
			<input type="submit" value="确定"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" value="重置"/>
		</div>
		</s:form>
	</body>
</html>
<script language="javascript">
	
	function addElement(ids){
		
		var sum = 0 ;
		
		var id = ids.split(',');
		
		for(var i = 1 ; i < id.length ; i ++){
			
			var obj = document.getElementById(id[i]);
			
			sum = sum + parseFloat(obj.value) ; 
		}
		
		document.getElementById(id[0]).value = sum ;
	}
	
	function substractElement(paramIdM,paramIdA,paramIdB){
		

		
		var value1 = document.getElementById(paramIdA);
		
		var value2 = document.getElementById(paramIdB);
		
		var result = formatFloat(formatFloat(parseFloat(value1.value)/1.17,2) - parseFloat(value2.value),2);
		
		document.getElementById(paramIdM).value = result ;
		
	}
	
	function validate(){
	
		var staticsDate = document.getElementById("staticsDate");
		
		if(null==staticsDate.value||''==staticsDate.value){
			
			alert('请选择统计日期');
			
			return false;
		}
	}
</script>
