<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>新增仓库期间收发处</title>
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
		
			<s:form action="addStoreHouseReceiverAction.action" >
			<table width="50%"  id="tab1" cellspacing="0" cellpadding="0"  >
					<s:iterator value="tmStoreHouseList" id="storeHouse" >
					<tr>
						<td><font color="blue" >${storeHouse.houseName }</font></td>
						<td width="120" >&nbsp;</td>
						<td width="50" align="center" ><font color="red" >期初</font></td>
						<td width="120" ><input type="text" name="formMap.qc_${storeHouse.id}" /></td>
						<td width="120" align="center" ><font color="red" >期末</font></td>
						<td width="120" ><input type="text" name="formMap.qm_${storeHouse.id}" /></td>	
					</tr>
					
					<tr>
						<td height="25" >入库类型</td>
						<td>&nbsp;入库成本</td>
						<td>&nbsp;</td>
						<td>&nbsp;出库类型</td>
						<td>&nbsp;出库成本</td>
						<td>&nbsp;出库销售金额</td>	
					</tr>
					
					<tr>
						<td>采购入库</td>
						<td><input type="text" name="formMap.CG_${storeHouse.id}" /></td>
						<td>&nbsp;</td>
						<td>维修发料</td>
						<td><input type="text" name="formMap.MAINTAIN_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.MAINTAINsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>调拨入库</td>
						<td><input type="text" name="formMap.ALLOTIN_${storeHouse.id}" /></td>
						<td>&nbsp;</td>
						<td>配件销售</td>
						<td><input type="text" name="formMap.STOCKOUT_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.STOCKOUTsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>配件报溢</td>
						<td><input type="text" name="formMap.OVERFLOW_${storeHouse.id}" /></td>
						<td>&nbsp;</td>
						<td>调拨出库</td>
						<td><input type="text" name="formMap.ALLOTSTOCKOUT_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.ALLOTSTOCKOUTsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>移库进仓</td>
						<td><input type="text" name="formMap.REMOVESTOCK_${storeHouse.id}" /></td>
						<td>&nbsp;</td>
						<td>配件报损</td>
						<td><input type="text" name="formMap.SHATTERSTOCKOUT_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.SHATTERSTOCKOUTsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>其它入库</td>
						<td><input type="text" name="formMap.OTHERSTOCKIN_${storeHouse.id}" /></td>
						<td>&nbsp;</td>
						<td>移库出仓</td>
						<td><input type="text" name="formMap.SHIFTSTOCK_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.SHIFTSTOCKsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>油漆辅料</td>
						<td><input type="text" name="formMap.UQFL_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.UQFLsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>采购退货</td>
						<td><input type="text" name="formMap.STOCKRETURN_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.STOCKRETURNsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>其它出库</td>
						<td><input type="text" name="formMap.OTHERSTOCKOUT_${storeHouse.id}" /></td>
						<td><input type="text" name="formMap.OTHERSTOCKOUTsell_${storeHouse.id}" /></td>	
					</tr>
					<tr>
						<td><font color="red" >入库小计</font></td>
						<td><input type="text" name="formMap.rkxj_${storeHouse.id}" /></td>
						<td>&nbsp;</td>
						<td><font color="red" >出库小计</font></td>
						<td>&nbsp;</td>
						<td><input type="text" name="formMap.ckxj_${storeHouse.id}" /></td>	
					</tr>
				</s:iterator>
			</table>
			
			<table>
				<tr>
					<td>
						<s:submit value="提交" ></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
	</body> 
</html>
