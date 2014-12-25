<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>配件增加
		</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>
	<script type="text/javascript">
		function editTbPartInfo(){
			var soleType = document.getElementsByName("soleType");
			var soleTypes = "";
			for(var i=0; i<soleType.length; i++){
				soleTypes += soleType[i].id+":"+soleType[i].value+",";
			}
			
			document.getElementById("soleTypes").value = soleTypes;
			document.forms("tbPartInfoUpdateAction").submit();
		}
		
		function convetRate(){
			var rateCostPrice = document.getElementById("rateCostPrice");
			var costPrice = document.getElementById("costPrice");
			rateCostPrice.value = formatFloat(costPrice.value*1.17,2);
		}
		
		function convetNoRate(){
			var rateCostPrice = document.getElementById("rateCostPrice");
			var costPrice = document.getElementById("costPrice");
			costPrice.value = formatFloat(rateCostPrice.value/1.17,2);
		}
	</script>
	<body>
	
		<s:form action="updateCostPriceAction.action">
			<s:hidden name="tbPartInfo.id"/>
			<table>
				
				<tr>
					<td>
						配件名称;
					</td>
					<td>
					 <input value="${tbPartInfo.partName }" readonly="readonly" />
					</td>
					
				</tr>
				
				<tr>
					<td>
						原成本价;
					</td>
					<td>
					 <input value="${tbPartInfo.costPrice }" readonly="readonly" />
					</td>
					
				</tr>
				<tr>
					<td>
						现成本价(含税);
					</td>
					<td>
						<s:textfield id="rateCostPrice"   name="tbPartInfo.rateCostPrice" onblur="convetNoRate()" ></s:textfield><font color="red">*</font>
					</td>
					
				</tr>
				<tr>
					<td>
						现成本价(不含税);
					</td>
					<td>
						<s:textfield id="costPrice"  name="tbPartInfo.costPrice" onblur="convetRate()" ></s:textfield><font color="red">*</font>
					</td>
					
				</tr>
				<tr>

					<td align="center">
						<input type="submit" 
							value="<s:text name="TM_FLAG_CAR_BUTTON_SUBMIT"/>" />
					</td>

				</tr>
			</table>
		</s:form>
		<SCRIPT type="text/javascript">
			document.getElementById("rateCostPrice").value = document.getElementById("costPrice").value ;
		</SCRIPT>
	</body>
</html>
