<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改仓库期间收发处</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<style>
		input.text{
			width:120px;
			height:24px;
		}
		
		#tab1 {
			border-collapse:collapse;
			
		}
		#tab1 td {border:#87a3c1 1px solid;}
	</style>
	
	<script type="text/javascript">
		
		
	</script>
	<body>
		
			<s:form action="updateStStoreHouseReceiverAction.action" >
			<input type="hidden" value="${param.id }" id="dateStr" name="dateStr" />
			<table width="50%" id="tab1" cellspacing="0" cellpadding="0"  >
				<s:iterator value="tmStoreHouseList" id="storeHouse" >
					<c:set var="qc" value="qc_${storeHouse.id}" ></c:set>
					<c:set var="qm" value="qm_${storeHouse.id}" ></c:set>
					<c:set var="rkxj" value="rkxj_${storeHouse.id}" ></c:set>
					<c:set var="ckxj" value="ckxj_${storeHouse.id}" ></c:set>
					
					<c:set var="CG" value="CG_${storeHouse.id}" ></c:set>
					<c:set var="ALLOTIN" value="ALLOTIN_${storeHouse.id}" ></c:set>
					<c:set var="OVERFLOW" value="OVERFLOW_${storeHouse.id}" ></c:set>
					<c:set var="REMOVESTOCK" value="REMOVESTOCK_${storeHouse.id}" ></c:set>
					<c:set var="OTHERSTOCKIN" value="OTHERSTOCKIN_${storeHouse.id}" ></c:set>
					
					
					
					<c:set var="MAINTAIN" value="MAINTAIN_${storeHouse.id}" ></c:set>
					<c:set var="STOCKOUT" value="STOCKOUT_${storeHouse.id}" ></c:set>
					<c:set var="ALLOTSTOCKOUT" value="ALLOTSTOCKOUT_${storeHouse.id}" ></c:set>
					<c:set var="SHATTERSTOCKOUT" value="SHATTERSTOCKOUT_${storeHouse.id}" ></c:set>
					<c:set var="SHIFTSTOCK" value="SHIFTSTOCK_${storeHouse.id}" ></c:set>
					<c:set var="UQFL" value="UQFL_${storeHouse.id}" ></c:set>
					<c:set var="STOCKRETURN" value="STOCKRETURN_${storeHouse.id}" ></c:set>
					<c:set var="OTHERSTOCKOUT" value="OTHERSTOCKOUT_${storeHouse.id}" ></c:set>
					
					
					<c:set var="MAINTAINsell" value="MAINTAINsell_${storeHouse.id}" ></c:set>
					<c:set var="STOCKOUTsell" value="STOCKOUTsell_${storeHouse.id}" ></c:set>
					<c:set var="ALLOTSTOCKOUTsell" value="ALLOTSTOCKOUTsell_${storeHouse.id}" ></c:set>
					<c:set var="SHATTERSTOCKOUTsell" value="SHATTERSTOCKOUTsell_${storeHouse.id}" ></c:set>
					<c:set var="SHIFTSTOCKsell" value="SHIFTSTOCKsell_${storeHouse.id}" ></c:set>
					<c:set var="UQFLsell" value="UQFLsell_${storeHouse.id}" ></c:set>
					<c:set var="STOCKRETURNsell" value="STOCKRETURNsell_${storeHouse.id}" ></c:set>
					<c:set var="OTHERSTOCKOUTsell" value="OTHERSTOCKOUTsell_${storeHouse.id}" ></c:set>
					
					<tr>
						<td><font color="blue" >${storeHouse.houseName }</font></td>
						<td width="120" >&nbsp;</td>
						<td width="50" align="center" ><font color="red" >期初</font></td>
						<td width="120" ><input type="text" name="formMap.qc_${storeHouse.id}" value="${formMap[qc]}"   /></td>
						<td width="120" align="center" ><font color="red" >期末</font></td>
						<td width="120" ><input type="text" name="formMap.qm_${storeHouse.id}" value="${formMap[qm]}" /></td>	
					</tr>
					
					<tr>
						<td height="25" >入库类型</td>
						<td >&nbsp;入库成本</td>
						<td>&nbsp;</td>
						<td>&nbsp;出库类型</td>
						<td>&nbsp;出库成本</td>
						<td>&nbsp;出库销售金额</td>	
					</tr>
					
					<tr>
						<td>采购入库</td>
						<td><input type="text" name="formMap.CG_${storeHouse.id}" value="${formMap[CG]}"  /></td>
						<td>&nbsp;</td>
						<td>维修发料</td>
						<td><input type="text" name="formMap.MAINTAIN_${storeHouse.id}" value="${formMap[MAINTAIN]}" /></td>
						<td><input type="text" name="formMap.MAINTAINsell_${storeHouse.id}"value="${formMap[MAINTAINsell]}" /></td>	
					</tr>
					<tr>
						<td>调拨入库</td>
						<td><input type="text" name="formMap.ALLOTIN_${storeHouse.id}" value="${formMap[ALLOTIN]}"  /></td>
						<td>&nbsp;</td>
						<td>配件销售</td>
						<td><input type="text" name="formMap.STOCKOUT_${storeHouse.id}" value="${formMap[STOCKOUT]}" /></td>
						<td><input type="text" name="formMap.STOCKOUTsell_${storeHouse.id}" value="${formMap[STOCKOUTsell]}" /></td>	
					</tr>
					<tr>
						<td>配件报溢</td>
						<td><input type="text" name="formMap.OVERFLOW_${storeHouse.id}"  value="${formMap[OVERFLOW]}" /></td>
						<td>&nbsp;</td>
						<td>调拨出库</td>
						<td><input type="text" name="formMap.ALLOTSTOCKOUT_${storeHouse.id}" value="${formMap[ALLOTSTOCKOUT]}" /></td>
						<td><input type="text" name="formMap.ALLOTSTOCKOUTsell_${storeHouse.id}" value="${formMap[ALLOTSTOCKOUTsell]}" /></td>	
					</tr>
					<tr>
						<td>移库进仓</td>
						<td><input type="text" name="formMap.REMOVESTOCK_${storeHouse.id}"  value="${formMap[REMOVESTOCK]}" /></td>
						<td>&nbsp;</td>
						<td>配件报损</td>
						<td><input type="text" name="formMap.SHATTERSTOCKOUT_${storeHouse.id}" value="${formMap[SHATTERSTOCKOUT]}" /></td>
						<td><input type="text" name="formMap.SHATTERSTOCKOUTsell_${storeHouse.id}" value="${formMap[SHATTERSTOCKOUTsell]}" /></td>	
					</tr>
					<tr>
						<td>其它入库</td>
						<td><input type="text" name="formMap.OTHERSTOCKIN_${storeHouse.id}" value="${formMap[OTHERSTOCKIN]}" /></td>
						<td>&nbsp;</td>
						<td>移库出仓</td>
						<td><input type="text" name="formMap.SHIFTSTOCK_${storeHouse.id}"  value="${formMap[SHIFTSTOCK]}" /></td>
						<td><input type="text" name="formMap.SHIFTSTOCKsell_${storeHouse.id}" value="${formMap[SHIFTSTOCKsell]}" /></td>	
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>油漆辅料</td>
						<td><input type="text" name="formMap.UQFL_${storeHouse.id}" value="${formMap[UQFL]}" /></td>
						<td><input type="text" name="formMap.UQFLsell_${storeHouse.id}" value="${formMap[UQFLsell]}" /></td>	
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>采购退货</td>
						<td><input type="text" name="formMap.STOCKRETURN_${storeHouse.id}" value="${formMap[STOCKRETURN]}" /></td>
						<td><input type="text" name="formMap.STOCKRETURNsell_${storeHouse.id}"  value="${formMap[STOCKRETURNsell]}" /></td>	
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>其它出库</td>
						<td><input type="text" name="formMap.OTHERSTOCKOUT_${storeHouse.id}" value="${formMap[OTHERSTOCKOUT]}" /></td>
						<td><input type="text" name="formMap.OTHERSTOCKOUTsell_${storeHouse.id}" value="${formMap[OTHERSTOCKOUTsell]}" /></td>	
					</tr>
					<tr>
						<td><font color="red" >入库小计</font></td>
						<td><input type="text" name="formMap.rkxj_${storeHouse.id}" value="${formMap[rkxj]}" /></td>
						<td>&nbsp;</td>
						<td><font color="red" >出库小计</font></td>
						<td>&nbsp;</td>
						<td><input type="text" name="formMap.ckxj_${storeHouse.id}" value="${formMap[ckxj]}"  /></td>	
					</tr>
				</s:iterator>
			</table>
			
						<s:submit value="提交" ></s:submit>
		</s:form>
	</body> 
</html>
