<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改维修发料</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="ext/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		
		
		var tbmainArray = new Array();
		var projectArray = new Array();
		function tmDetTableConfigHandler(pConfig) {
			// pConfig.autoExpandColumn='no';
		}
		
		function tmDetTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "findAllTbFixEntrustAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		function openWin(){
			var props = "entrustId,entrustCode,fixDate,fixType,licenseCode,null,customerName,stationCode&entrustStatus=0";
			showCommonWin('findAllTbFixEntrustAction.action','委托书列表',720,355,props,null);
			
		}
		
		function openPeoWin(userId,userRealName){
			var props = userId+","+userRealName;
			showCommonWin('findAllTmUserAction.action','职工列表',575,355,props,null);
		}
		
	
		function jsprice(index){
		
			var costPrice = document.getElementById("price"+index);
			var partQuantity = document.getElementById("partQuantity"+index);
			var xjVal = parseFloat(costPrice.value) * parseFloat(partQuantity.value);
			var xj = document.getElementById("xj"+index);
			var isFree = document.getElementById("isFree"+index);
			xj.innerHTML = xjVal;
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.index == index){
					obj.partQuantity = partQuantity.value;
					obj.price = costPrice.value;
					obj.totalPrice = xjVal;
					obj.isFree = isFree.value;
				}
			}

			jsTotalPrice();
		}
		
		
		function addTbMaintain(){
			var flag = validate();
			if(!flag)return;
			
			
			var str = "";
			var totalPrice = 0;
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.type == 'old' ) {
					if(obj.isFree == 1)
						totalPrice  += parseFloat(obj.totalPrice);
					var isprint = document.getElementById("isprint"+obj.vvindex);
					var isprintVal = isprint.checked ? "Y":"N";
					str += obj.tbMaintainId + ":old:"+isprintVal+",";
					continue;
				}
				var cliamPartPersonId = document.getElementById("phi"+obj.index);
				if(obj.isFree == 1)
					totalPrice = totalPrice + parseFloat(obj.totalPrice);
			
				//totalPrice = totalPrice + parseFloat(obj.partQuantity * obj.price );
				var projecttype = document.getElementById("projecttype"+obj.index);
				var projecttypeVal = projecttype.value != ""?projecttype.value:'null';
				var isprint = document.getElementById("isprint"+obj.index);
				var isprintVal = isprint.checked ? "Y":"N";
				str += obj.tbPartInfoId + ":" + obj.partQuantity + ":" + obj.price + ":"+cliamPartPersonId.value+ ":"+ obj.isFree +":";
				str += projecttypeVal+":"+isprintVal+",";
			}
			var tp = document.getElementById("totalPrice");
			tp.value = totalPrice;
			var partCol = document.getElementById("partCol");
			partCol.value = str;
			alert(partCol.value);
			document.forms[0].submit();
			
		}
		
		
	
		
		
		var maintainCode = "";
		
		
		function validate(){
			var pirces = document.getElementsByName("pirces");
			var quantities = document.getElementsByName("quantities");
			var peoples = document.getElementsByName("peoples");
			for(var i=0; i<pirces.length; i++){
				var pce = pirces[i];
				if(!isNumber(pce.value) || parseFloat(pce.value) <=0 || pce.value == ""){
					pce.focus();
					Ext.MessageBox.alert('温馨提示：', '配件金额输入有误!', null);
					return false;
				} 
			}
			
			for(var i=0; i<quantities.length; i++){
				var pce = quantities[i];
				if(!isNumber(pce.value) || parseFloat(pce.value) <=0 || pce.value == ""){
					pce.focus();
					Ext.MessageBox.alert('温馨提示：', '配件数量输入有误!', null);
					return false;
				} 
			}
			
			
			var partInfo_quantity = new Array();
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				var isAdd = true;
				for(var j=0; j<partInfo_quantity.length; j++){
					if(obj.tbPartInfoId == partInfo_quantity[j].tbPartInfoId){
						partInfo_quantity[j].partQuantity = parseFloat(obj.partQuantity) + parseFloat(partInfo_quantity[j].partQuantity);
						isAdd = false;
						break;
					}
				}
				
				if(isAdd){
					var partObj = {}
					partObj.tbPartInfoId = obj.tbPartInfoId;
					partObj.partQuantity = obj.partQuantity;
					partInfo_quantity.push(partObj);
				}
				
			}
			
			for(var i=0;i<partInfo_quantity.length;i++){
				var obj = partInfo_quantity[i]
				if(parseFloat(obj.partQuantity) < 0 ){
					Ext.MessageBox.alert('温馨提示：', '	您退的料超出已发数量请核对！', null);
					return false;
				}
			}
			
			
			/*
			for(var i=0; i<peoples.length; i++){
				if(peoples[i].value == "" || pce.value == ""){
					Ext.MessageBox.alert('温馨提示：', '领料人不能为空!', null);
					return false;
				}
			}
			*/
			
			
			
			
			return true;
		}
		
		
		var tbPartInfoObj = {};
		function partReturn(tbPartInfoId,houseName,partName,unitName,userRealName,partQuantity,price,cliamPartPersonId,isFree,projectType){
		 
		 	
		 	
		 	var newPartQuantity = parseFloat("-"+partQuantity);
		 	var newObj = {};
		 	newObj.index = gobalIndex;
		 	newObj.tbPartInfoId = tbPartInfoId;
		 	newObj.houseName = houseName;
		 	newObj.partName = partName;
		 	newObj.unitName = unitName;
		 	newObj.userRealName = userRealName;
		 	newObj.cliamPartPersonId = cliamPartPersonId;
		 	newObj.partQuantity = newPartQuantity;
		 	newObj.price = price;
		 	newObj.totalPrice = newPartQuantity*price;
		 	newObj.xsxj = formatFloat(price*newPartQuantity,2)
		 	newObj.isFree = isFree;
		 	newObj.addType = "return";
		 	newObj.projectType = projectType;
		 	var flag = validateIsReturn(tbPartInfoId);
		 	if(flag){
			 	tbmainArray.push(newObj);
		 		addPartInfo(newObj,true);
		 	}
		 	
		 
		 }
		 
		 function addPartInfo(tbmainObj,readonlyFlag){
			var mainTable = document.getElementById("mainTable");
			var rowIdx = mainTable.rows.length;
			if(tbmainObj.index == undefined )
		  		tbmainObj.index	= gobalIndex;
		    var mainRow = mainTable.insertRow(); 
		    mainRow.id = "TR"+tbmainObj.tbPartInfoId;
		    var chkCell = mainRow.insertCell();
		    var rowIdxCell = mainRow.insertCell();
		    var houseNameCell = mainRow.insertCell();
		    var partNameCell = mainRow.insertCell();
		    var unitNameCell = mainRow.insertCell();
		    var costPriceCell = mainRow.insertCell();
		    var quantityCell = mainRow.insertCell();
		    var stockoutQuantityCell = mainRow.insertCell();
		    stockoutQuantityCell.id = "stockoutQuantityCell"+tbmainObj.index;
		    getStockoutSum(tbmainObj.tbPartInfoId,tbmainObj.index);
		    var peopleCell = mainRow.insertCell();
		    var xjCell = mainRow.insertCell();
		    xjCell.id = "xj"+tbmainObj.index;
		    var freeCell = mainRow.insertCell();
		   //freeCell.align = "center";
		    var projectTypeCell = mainRow.insertCell();
		    projectTypeCell.align = "center";
		    var printCell = mainRow.insertCell();
		    var opCell = mainRow.insertCell();
    		opCell.align = "center";
    		
    		chkCell.innerHTML = buildCheckBox(tbmainObj.index);	
		    rowIdxCell.innerHTML = rowIdx;
		    houseNameCell.innerHTML = tbmainObj.houseName;
		    partNameCell.innerHTML = tbmainObj.partName;
		    unitNameCell.innerHTML = tbmainObj.unitName;
		    
		    if(readonlyFlag){
		    	costPriceCell.innerHTML = "<input  style=width:60px  readonly=readonly id='price"+tbmainObj.index+"' name=pirces value="+tbmainObj.price+" onchange=\"jsprice("+tbmainObj.index+");\" />";
		    }else{
			    costPriceCell.innerHTML = "<input  style=width:60px  id='price"+tbmainObj.index+"' name=pirces value="+tbmainObj.price+" onchange=\"jsprice("+tbmainObj.index+");\" />";
		    }
		    quantityCell.innerHTML = "<input  style=width:60px  value="+tbmainObj.partQuantity+" id='partQuantity"+tbmainObj.index+"' name=returnQuantity"+tbmainObj.tbPartInfoId+" onchange=\"jsprice("+tbmainObj.index+");\"  />";
		   
		   if(tbmainObj.userRealName != undefined)
		   	 	peopleCell.innerHTML = "<input  style=width:60px  readonly=true name=peoples value="+tbmainObj.userRealName+" /><input id='phi"+tbmainObj.index+"'  value="+tbmainObj.cliamPartPersonId+" type=hidden  />";
		   else 
		    	peopleCell.innerHTML = "<input  style=width:60px  id='pep"+tbmainObj.index+"' name=peoples onfocus=\"openPeoWin('phi"+tbmainObj.index+"' , this.id)\" /><input id='phi"+tbmainObj.index+"' value='"+tbmainObj.cliamPartPersonId+"' type=hidden  />";
		   if(tbmainObj.xsxj != undefined)
		   		xjCell.innerHTML = tbmainObj.xsxj;
		   else
		    	xjCell.innerHTML = "0.00";
		    
		   var disable = tbmainObj.addType == "newAdd" ?false:true;
		   var freeHtml  = buildFreeFlagSelect("isFree"+tbmainObj.index,"jsprice('"+tbmainObj.index+"')",tbmainObj.isFree,disable);
		   freeCell.innerHTML = freeHtml;
		   
		   /*
		   if(tbmainObj.isFree == 0)			//不免费(退料情况)
		    	freeCell.innerHTML = "<input disabled=true type=checkbox onclick=jsprice('"+tbmainObj.index+"') id=\"isFree"+tbmainObj.index+"\" value=0 />";
		   else if(tbmainObj.isFree == 1)		//免费(退料情况)
		    	freeCell.innerHTML = "<input disabled=true checked=true type=checkbox onclick=jsprice('"+tbmainObj.index+"') id=\"isFree"+tbmainObj.index+"\" value=1 />";
		   else									//默认不免费(新增配件情况)
		   		freeCell.innerHTML = "<input type=checkbox onclick=jsprice('"+tbmainObj.index+"') id=\"isFree"+tbmainObj.index+"\" value=1 />";
		   	*/
		   
		   if(readonlyFlag){
		   		projectTypeCell.innerHTML = buildProjectType(tbmainObj.index,true,tbmainObj.projectType);
		   }else{
		   		projectTypeCell.innerHTML = buildProjectType(tbmainObj.index,false,null);
		   }
		    printCell.innerHTML = buildPrint(tbmainObj.index);
		   opCell.innerHTML = "<input type=button value=删除 onclick=\"deleteRow('"+tbmainObj.tbPartInfoId+"');\" />";
		   
		    
		    
		    gobalIndex ++;

		    jsTotalPrice();
		}
		
		
		function deleteRow(partInfoId){
			var mainTable = document.getElementById("mainTable");
			var trRow = document.getElementById("TR"+partInfoId);
			mainTable.deleteRow(trRow.rowIndex);
			
			var newArr = new Array();
			
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.tbPartInfoId == partInfoId && obj.addType != undefined){
					continue;
				}
				newArr.push(obj);
			}
			
			tbmainArray = newArr;

			jsTotalPrice();
			
			for( var i=1 ; i <= mainTable.rows.length ; i++){
				mainTable.rows[i].cells[0].innerHTML = i;
			}
		}
		
		function validateIsReturn(partInfoId){
		 	var returnQuantity = document.getElementsByName("returnQuantity"+partInfoId);
		 	var result = 0;
		 	for(var i=0; i<returnQuantity.length; i++){
		 		result += parseFloat(returnQuantity[i].value);
		 	}
		 	if(parseFloat(result) <= 0 )
		 		return false;
		 		
		 	return true;
		 }
		 
		 var gobalIndex = 1;
		 var beforeArray = new Array();


		 function jsTotalPrice(){
			 var totalPrice = 0;
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.type == 'old' ) {
					if(obj.isFree == 1)
						totalPrice  += parseFloat(obj.totalPrice);
					continue;
				}
				if(obj.isFree == 1)
					totalPrice = totalPrice + parseFloat(obj.totalPrice);
			
				
				
			}
			var tp = document.getElementById("totalpricespan");
			tp.innerHTML = totalPrice;
		 }
		 
		 
		 function buildProjectType(index,readonly,selectVal){
			var projectTypeHtml = "";
			if(readonly){
				projectTypeHtml= "<select disabled=disabled id='projecttype"+index+"' >";
			}else{
				projectTypeHtml= "<select id='projecttype"+index+"' >";
			}
			
			projectTypeHtml += "<option value='' ></option>";
			
			for(var i=0 ; i< projectArray.length; i++){
				
				if(false){
					projectTypeHtml += "<option value='"+projectArray[i].id+"'>"+projectArray[i].projectType+"</option>";
				}else{
					if(selectVal == projectArray[i].projectType){
						projectTypeHtml += "<option selected='selected' value='"+projectArray[i].id+"'>"+projectArray[i].projectType+"</option>";
					}else{
						projectTypeHtml += "<option value='"+projectArray[i].id+"'>"+projectArray[i].projectType+"</option>";
					}
				}
			}
			
			projectTypeHtml += "</select>";
			return projectTypeHtml;
		}
		
		function buildPrint(index){
			var printHtml =  "<input type=checkbox name=isprint checked=true id='isprint"+index+"' >";
			printHtml +="</input>";
			return printHtml;
			
		}
		
		function selectAllPrint(chk){
			var isprint  = document.getElementsByName("isprint");
			for(var i=0 ; i<isprint.length; i++){
				//if(isprint[i].disabled == true)continue;
			
				if(chk.checked ){
					isprint[i].checked = true;
				}else{
					isprint[i].checked = false;
				}
			}
		}
		
		function buildCheckBox(chkboxId){
			 var checkboxHtml = "<input type='checkbox' name=partchk id='"+chkboxId+"' />"
			 return checkboxHtml;
		}
		
		function synPartProjectType(partTypeVal){
			var projectId ;
			for(var j=0 ; j< projectArray.length; j++){
				if(projectArray[j].projectType == partTypeVal){
					projectId = projectArray[j].id;
					break;
				}  
			}
			var partchks = document.getElementsByName("partchk");
			for(var i=0;i<partchks.length;i++){
				if(partchks[i].checked){
					var partRowId = partchks[i].id;
					var projecttype = document.getElementById("projecttype"+partRowId);
					projecttype.value = projectId;
				}
			}
			
		}
		
		function checkAll(){
			var partchks = document.getElementsByName("partchk");
			var chkall = document.getElementById("chkall");
			for(var i=0;i<partchks.length;i++ ){
				partchks[i].checked = chkall.checked;
			}
		}
		
		function getStockoutSum(partId,indexNum) {
			var url = "getSumPartInfoByStockoutAction.action?partId=" + partId ;
			var deleteAjax = new Ajax.Request(url, {
				method : 'post',
				onComplete : function(originalRequest){    //提交成功回调 
                    var stockoutQuantityCell = document.getElementById("stockoutQuantityCell"+indexNum);
					var responseText = originalRequest.responseText;
					stockoutQuantityCell.innerText = responseText;
                },
				asynchronous : true
			});
		} 
	</script>
	<body onload="jsTotalPrice();" >
		<s:form action="updateConfirmMaintainContentAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="entrustId" name="entrustId"  />
			<s:hidden name="tbMaintainPartContent.isConfirm"  />
			<table>
				<tr>
					<td>维修发料单号：</td>
					<td><s:textfield   cssStyle="color:#FF0000" name="tbMaintainPartContent.maintainCode" readonly="true" /></td>
					<td>委托书号：</td>
					<td><s:textfield disabled="true" id="entrustCode" name="#request.fixEntrust.entrustCode" onfocus="openWin();" /></td>
					<td>牌照号：</td>
					<td><s:textfield disabled="true" id="licenseCode" name="#request.fixEntrust.licenseCode" /></td>
					<td>修理日期：</td>
					<td><s:textfield disabled="true" id="fixDate"  name="#request.fixEntrust.fixDate" readonly="true" >
							<s:param name="value"><s:date name="#request.fixEntrust.fixDate" format="yyyy-MM-dd hh:mm:ss"/></s:param>
						</s:textfield>
						<e3c:calendar for="fixDate" />
					</td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
				<tr>
					<td >客户名称：</td>
					<td ><s:textfield disabled="true" id="customerName" name="#request.fixEntrust.tbCustomer.customerName" /></td>
					<td>修理类型：</td>
					<td><s:textfield disabled="true" id="fixType" name="#request.fixEntrust.tmFixType.fixType" /></td>
					<td>车型：</td>
					<td><s:textfield disabled="true" id="stationCode" name="#request.fixEntrust.stationCode" /></td>
				</tr>
			</table>
		</s:form>
		<br>
		<table id="mainTable"  >
			<tr>
				<th width="30" >选择 <input name="chkall" id="chkall" type="checkbox" onclick="checkAll()" /></th>
				<th width="30" >序号</th>
				<th width="100" >仓库</th>
				<th width="120">配件名称</th>
				<th width="70">单位</th>
				<th width="60">发料单价</th>
				<th width="60">数量</th>
				<th width="60">出库数量</th>
				<th width="60">领料人</th>
				<th width="60">小计</th>
				<th width="70" align="center">  免费标志</th>
				<th width="100" align="center"> 维修项目类型</th> 
				<th width="80" align="center"> 是否打印<input type="checkbox" onclick="selectAllPrint(this)" /></th>
				<th width="60" align="center" >操作</th>
			</tr>
			<s:iterator value="#request.maintainVoDetails" id="maintain" status="vv" >
				<tr>
					<td><input type='checkbox' name=partchktt disabled="disabled" /></td>
					<td>${vv.index+1}</td>
					<td>${maintain.houseName }</td>
					<td>${maintain.partName}(${maintain.partCode })</td>
					<td>${maintain.unitName }</td>
					<td><input disabled="true" style="width:80px"  name=pirces value="${maintain.price }" onchange="jsprice('${maintain.partId}');" /></td>
					<td><input disabled="true" style="width:80px"   value="${maintain.partQuantity }" name="returnQuantity${maintain.partId}" onchange="jsprice('${maintain.partId}');"  /></td>
					<td id="stockoutQuantityCell${vv.index}"></td>		
					<td>
						<input disabled="true"   style="width:80px"   value="${maintain.userRealName }" /><input id="phi${maintain.partId}" value="${maintain.cliamPartPersonId }" type=hidden  />
					</td>
					<td >
						<fmt:formatNumber maxFractionDigits="2" pattern="########" value="${maintain.partQuantity * maintain.price }"  ></fmt:formatNumber>
					</td>		
					<td>
						<!--  
						<s:if test="#maintain.isFree  == 0">
							<input disabled="disabled" type=checkbox value=0 onclick="jsprice('${maintain.partId}');" />
						</s:if>
						<s:if test="#maintain.isFree  == 1">
							<input disabled="disabled" type=checkbox value=1 checked="checked" onclick="jsprice('${maintain.partId}');" />
						</s:if>
						-->
						
						<select disabled="disabled"  onchange="jsprice('${maintain.partId}');"  >
							<s:iterator id="free" value="#request.freeFlagTypes">
								<c:if test="${ maintain.isFree == free.key }">
									<option selected="selected" value="${free.key}">
										${free.value }
									</option>
								</c:if>
								<c:if test="${ maintain.isFree != free.key }">
									<option value="${free.key}">
										${free.value }
									</option>
								</c:if>
							</s:iterator>
						</select>
					</td>
					<td>
						<s:select disabled="true" id="projecttype%{#vv.index}" emptyOption="true"  list="#request.tmprojectTypes" listKey="id" listValue="projectType" ></s:select>
					</td>
					<td>
						<input type="checkbox" name="isprint" id="isprint${vv.index}"  /> 
					</td>	
					<td align="center" >
						<s:if test="#maintain.partQuantity > 0 ">
							<input type="button" value="退料" onclick="partReturn('${maintain.partId}','${maintain.houseName}','${maintain.partName}','${maintain.unitName}','${maintain.userRealName }','${maintain.partQuantity}','${maintain.price}','${maintain.cliamPartPersonId}','${maintain.isFree}','${maintain.projectType}')" />
						</s:if>
					</td>
				</tr>
				
				<script type="text/javascript">
					getStockoutSum("${maintain.partId}","${vv.index}");
					var tbmainObj = {};
				    tbmainObj.tbPartInfoId = "${maintain.partId}";
				    tbmainObj.partQuantity = "${maintain.partQuantity}";
				    tbmainObj.price = "${maintain.price}";
				    tbmainObj.cliamPartPersonId = "${maintain.cliamPartPersonId }";
				    tbmainObj.totalPrice = tbmainObj.partQuantity*tbmainObj.price;
				    tbmainObj.type = 'old';
				    tbmainObj.isFree = "${maintain.isFree}";
				    tbmainObj.isPrint = "${maintain.isPrint}";
				    tbmainObj.vvindex = "${vv.index}";
				    tbmainObj.tbMaintainId = "${maintain.tbMaintainId}";
				    tbmainArray.push(tbmainObj);
		    		
		    		gobalIndex ++;
		    		
		    		 //处理维修项目下拉选择 
			    	 var parojecttypeSelect = document.getElementById("projecttype"+"${vv.index}");
			    	 for(var i=1;i<parojecttypeSelect.length;i++){
			    	 	if(parojecttypeSelect.options[i].text == "${maintain.projectType }"){
			    	 		parojecttypeSelect.options[i].selected = true;
			    	 	}
			    	 }
			    	 
			    	var isPrint = document.getElementById("isprint"+"${vv.index}");
			    	if("${maintain.isPrint}" == "Y"){
			    		isPrint.checked = true;
			    	}
		    	</script>
				
			</s:iterator>
		</table>
		<input type="button" id="saveBtn" value="保存单据" onclick="addTbMaintain();" />
		<br>
		合计金额： <SPAN id="totalpricespan" ></SPAN>
		<div id="partinfocollectionDiv" ></div>
		<br>
		<div id='tabPlaceHolder'></div>
		<s:iterator id="det" value="#request.tmprojectTypes" >
			<script type="text/javascript">
				var projectTypeObj = {};
				projectTypeObj.id = "${det.id}";
				projectTypeObj.projectType = "${det.projectType}";
				projectArray.push(projectTypeObj);
			</script>
		</s:iterator>
	<br>
	<br>
	<br>
	
	
	</body> 
</html>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>


<script language="javascript">
var entrustId = document.getElementById("entrustId").value;
		Ext.onReady(function(){
			TabPanel.create('tabPlaceHolder',430,
			[
				{
					id:'tbPartInfoTab' , title:'配件列表',disabled:false,url:'tabFindPartInfoAction.action'
					
				},
				{
					id:'tbFixEntrustContent' ,title:'委托修理工位',disabled:false,url:'findTbFixEntrustContentAction.action?tbFixEntrustId='+entrustId 
					
				}
				
			]);
		});
		
		Ext.get('tabPlaceHolder').on('click',function(){
			var entrustId = document.getElementById('entrustId').value
			if(null!=entrustId && ""!=entrustId){	
				document.getElementById('tbFixEntrustContent').contentWindow.location.href='findTbFixEntrustContentAction.action?tbFixEntrustId='+entrustId;
			}
				
		});
	</script>