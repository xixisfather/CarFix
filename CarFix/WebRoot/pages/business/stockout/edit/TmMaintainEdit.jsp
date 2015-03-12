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
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<s:head theme="ajax" /> 


	<script type="text/javascript">
		var ctx = "<%=request.getContextPath() %>";		//项目路径
		var insertAction = "${insertAction}";			//入库FromId
		var initPageAction = "${initPageAction}";		//进入入库页面
		var updateAction = "${updateAction}";			//更新入库单FromId
		var projectArray = new Array();
		
		var tbmainArray = new Array();
		
		var idx = 0;
		
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
			var props = "entrustId,entrustCode,fixDate,fixType,licenseCode,null,customerName,null,stationCode&entrustStatusCollection=0,4&entrustType=1";
			showCommonWin('findAllTbFixEntrustAction.action','委托书列表',720,355,props,null);
			
		}
		
		function openPeoWin(userId,userRealName){
			var props = userId+","+userRealName;
			showCommonWin('findAllTmUserAction.action','职工列表',575,355,props,null);
		}
		
	
		function jsprice(partId){
			var costPrice = document.getElementById("price"+partId);
			var partQuantity = document.getElementById("partQuantity"+partId);
			var xjVal = formatFloat(parseFloat(costPrice.value) * parseFloat(partQuantity.value),2);
			var xj = document.getElementById("xj"+partId);
			var isFree = document.getElementById("isFree"+partId);
			var totalPrice = 0;
			xj.innerHTML = xjVal;
			
			
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.idx == partId){
					obj.partQuantity = partQuantity.value;
					obj.price = costPrice.value;
					obj.totalPrice = xjVal;
					obj.isFree = isFree.value;
				}
				var isFree = document.getElementById("isFree"+obj.idx);
				if(isFree.value == 1)
					totalPrice = totalPrice + parseFloat(obj.totalPrice);
			}
			
			//合计金额
			var totalpricespan = document.getElementById("totalpricespan");
			totalpricespan.innerHTML =  formatFloat(totalPrice,2);
		}
		
		
		function addTbMaintain(confirm){
			var flag = validate();
			if(!flag)return;
		
			var str = "";
			var totalPrice = 0;
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				var cliamPartPersonId = document.getElementById("phi"+obj.idx);
				var isFree = document.getElementById("isFree"+obj.idx);
				if(isFree.value == 1)
					totalPrice = totalPrice + parseFloat(obj.totalPrice);
					
				var zl = document.getElementById("zl"+obj.idx);
				var xmlx = document.getElementById("xmlx"+obj.idx);
				var projecttype = document.getElementById("projecttype"+obj.idx);
				var projecttypeVal = projecttype.value != ""?projecttype.value:'null';
				var isprint = document.getElementById("isprint"+obj.idx);
				var isprintVal = isprint.checked ? "Y":"N";
				str += obj.tbPartInfoId + ":" + obj.partQuantity + ":" + obj.price + ":" + cliamPartPersonId.value + ":" + isFree.value +":";
				str += projecttypeVal+":"+isprintVal+",";
			}
			
			var tp = document.getElementById("totalPrice");
			tp.value = totalPrice;
			var partCol = document.getElementById("partCol");
			partCol.value = str;
			document.forms[0].action += "?isConfirm="+confirm;
			alert(str);
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
		
		function addPartInfo(tbmainObj){
			 idx++;
		
			var mainTable = document.getElementById("mainTable");
			var rowIdx = mainTable.rows.length;
		    var mainRow = mainTable.insertRow(); 
		    mainRow.id = "TR"+idx;
		    var chkCell = mainRow.insertCell();
		    var rowIdxCell = mainRow.insertCell();
		    var houseNameCell = mainRow.insertCell();
		    var partNameCell = mainRow.insertCell();
		    var unitNameCell = mainRow.insertCell();
		    var costPriceCell = mainRow.insertCell();
		    var quantityCell = mainRow.insertCell();
		    var stockoutQuantityCell = mainRow.insertCell();
		    stockoutQuantityCell.id = "stockoutQuantityCell"+idx;
		    getStockoutSum(tbmainObj.tbPartInfoId,idx);
		    var peopleCell = mainRow.insertCell();
		    var xjCell = mainRow.insertCell();
		    xjCell.id = "xj"+idx;
		    var freeCell = mainRow.insertCell();
		    var projectTypeCell = mainRow.insertCell();
		    projectTypeCell.align = "center";
		    var printCell = mainRow.insertCell();
			var opCell  = mainRow.insertCell();
			
			var freeHtml  = buildFreeFlagSelect("isFree"+idx,"jsprice('"+idx+"')",null,false);
			
			chkCell.innerHTML = buildCheckBox(idx);			    
			rowIdxCell.innerHTML = rowIdx;						    
		    houseNameCell.innerHTML = tbmainObj.houseName;
		    partNameCell.innerHTML = tbmainObj.partName+"("+tbmainObj.partCode+")";
		    unitNameCell.innerHTML = tbmainObj.unitName;
		    costPriceCell.innerHTML = "<input style=width:80px id='price"+idx+"' name=pirces value="+tbmainObj.price+" onchange=\"jsprice("+idx+");\" />";
		    quantityCell.innerHTML = "<input style=width:80px id='partQuantity"+idx+"' name=quantities onchange=\"jsprice("+idx+");\"  />";
		    peopleCell.innerHTML = "<input style=width:80px id='pep"+idx+"' name=peoples onfocus=\"openPeoWin('phi"+idx+"' , this.id)\" /><input id='phi"+idx+"' type=hidden  />";
		    xjCell.innerHTML = "0.00";
		    freeCell.innerHTML = freeHtml;
		    projectTypeCell.innerHTML = buildProjectType(idx);
		    printCell.innerHTML = buildPrint(idx);
		    opCell.innerHTML = "<input type=button value=删除 onclick=\"deleteRow('"+idx+"');\" />";
		    
		    
		    tbmainObj.idx = idx;
		   
		}
		
		function deleteRow(partInfoId){
			var mainTable = document.getElementById("mainTable");
			var trRow = document.getElementById("TR"+partInfoId);
			mainTable.deleteRow(trRow.rowIndex);
			
			var newArr = new Array();
			
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				if(obj.idx == partInfoId){
					continue;
				}
				newArr.push(obj);
			}
			
			tbmainArray = newArr;
			
			//计算总金额
			jsTotal();
			
			for( var i=1 ; i <= mainTable.rows.length ; i++){
				mainTable.rows[i].cells[0].innerHTML = i;
			}
		}
		
		function jsTotal(){
			/** 计算总金额 **/
			var totalPrice = 0;
			for(var i=0; i<tbmainArray.length; i++){
				var obj = tbmainArray[i];
				var isFree = document.getElementById("isFree"+obj.idx);
				if(isFree.value == 1)
					totalPrice = totalPrice + parseFloat(obj.totalPrice);
			}
			var totalpricespan = document.getElementById("totalpricespan");
			totalpricespan.innerHTML =  formatFloat(totalPrice,2);
			/** 计算总金额 **/
		}
		
		function buildProjectType(index){
			
			var projectTypeHtml = "<select id='projecttype"+index+"' >";
			
			projectTypeHtml += "<option value='' ></option>";
			
			for(var i=0 ; i< projectArray.length; i++){
				
				projectTypeHtml += "<option value='"+projectArray[i].id+"'>"+projectArray[i].projectType+"</option>";
			}
			
			projectTypeHtml += "</select>";
			return projectTypeHtml;
		}
		
		function buildPrint(index){
			var printHtml =  "<input type=checkbox  id='isprint"+index+"' >";
			
			printHtml +="</input>";
			
			return printHtml;
			
		}
		
		function selectAllPrint(chk){
			var isprint  = document.getElementsByName("isprint");
			for(var i=0 ; i<isprint.length; i++){
			
				if(chk.checked){
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
	<body onload="jsTotal();" >
		<s:form action="updateMaintainContentAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="entrustId" name="entrustId"  />
			<table>
				<tr>
					<td>维修发料单号：</td>
					<td><s:textfield  cssStyle="color:#FF0000" name="tbMaintainPartContent.maintainCode" readonly="true" /></td>
					<td>委托书号：</td>
					<td><s:textfield id="entrustCode" name="#request.fixEntrust.entrustCode" onfocus="openWin();" /></td>
					<td>牌照号：</td>
					<td><s:textfield id="licenseCode" name="#request.fixEntrust.licenseCode" /></td>
					<td>修理日期：</td>
					<td><s:textfield id="fixDate"  name="#request.fixEntrust.fixDate" readonly="true" />
						<e3c:calendar for="fixDate" />
					</td>
					<td>操作员：</td>
					<td>${tmUseruserRealName }</td>
				</tr>
				<tr>
					<td >客户名称：</td>
					<td ><s:textfield id="customerName" name="#request.fixEntrust.tbCustomer.customerName" /></td>
					<td>修理类型：</td>
					<td><s:textfield id="fixType" name="#request.fixEntrust.tmFixType.fixType" /></td>
					<td>车型：</td>
					<td><s:textfield id="stationCode" name="#request.fixEntrust.stationCode" /></td>
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
				<th width="100">发料单价</th>
				<th width="100">数量</th>
				<th width="60">出库数量</th>
				<th width="100">领料人</th>
				<th width="100">小计</th>
				<th width="70" align="center">  免费标志</th>
				<th width="100" align="center"> 维修项目类型</th>
				<th width="80" align="center"> 是否打印<input type="checkbox" onclick="selectAllPrint(this)" /></th>
				<th width="100" align="center" >操作</th>
			</tr>
			<s:iterator value="#request.maintainVoDetails" id="maintain" status="idx">
				<tr  id="TR${idx.index}">
					<td><input type='checkbox' name=partchk id="${idx.index}" /></td>
					<td>${idx.index+1}</td>
					<td>${maintain.houseName }</td>
					<td>${maintain.partName }(${maintain.partCode })</td>
					<td>${maintain.unitName }</td>
					<td><input style="width:80px" id="price${idx.index}" name=pirces value="${maintain.price }" onchange="jsprice('${idx.index}');" /></td>
					<td><input style="width:80px" id="partQuantity${idx.index}" value="${maintain.partQuantity }" name=quantities onchange="jsprice('${idx.index}');"  /></td>		
					<td id="stockoutQuantityCell${idx.index}"></td>
					<td>
						<input style="width:80px" id="pep${idx.index}" value="${maintain.userRealName }" name=peoples onfocus="openPeoWin('phi${idx.index}',this.id)" /><input id="phi${idx.index}" value="${maintain.cliamPartPersonId }" type=hidden  />
					</td>
					<td id="xj${idx.index}" >
						<fmt:formatNumber maxFractionDigits="2"  value="${maintain.partQuantity * maintain.price }"  ></fmt:formatNumber>
					</td>	
					<td>
					<!-- 
						<s:if test="#maintain.isFree  == 0">
							<input type=checkbox value=0 id="isFree${idx.index}" onclick="jsprice('${idx.index}');" />
						</s:if>
						<s:if test="#maintain.isFree  == 1">
							<input type=checkbox value=1 checked="checked" id="isFree${idx.index}" onclick="jsprice('${idx.index}');" />
						</s:if>
						 -->
						<select onchange="jsprice('${idx.index}');" id="isFree${idx.index}"  >
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
						<s:select  id="projecttype%{#idx.index}" value="%{#maintain.id}" emptyOption="true" list="#request.tmprojectTypes" listKey="id" listValue="projectType" ></s:select>
					</td>
					<td>
						<input type="checkbox" name="isprint" id="isprint${idx.index}"  /> 
					</td>
					<td>
						<input type="button" value="删除" onclick="deleteRow('${idx.index}');" />
					</td>
				</tr>
				
				<script type="text/javascript">
				
				var tbmainObj = {};
			    tbmainObj.tbPartInfoId = "${maintain.partId}";
			    tbmainObj.partQuantity = "${maintain.partQuantity}";
			    tbmainObj.price = "${maintain.price}";
			    tbmainObj.cliamPartPersonId = "${maintain.cliamPartPersonId }";
			    tbmainObj.totalPrice = parseFloat(document.getElementById("xj${idx.index}").innerHTML);
			    tbmainObj.idx="${idx.index}";
			    tbmainArray.push(tbmainObj);
		    	
		    	idx = "${idx.index}";
		    	 
		    	 //处理维修项目下拉选择 
		    	 var parojecttypeSelect = document.getElementById("projecttype"+idx);
		    	 for(var i=1;i<parojecttypeSelect.length;i++){
		    	 	if(parojecttypeSelect.options[i].text == "${maintain.projectType }"){
		    	 		parojecttypeSelect.options[i].selected = true;
		    	 	}
		    	 }
		    	 
		    	var isPrint = document.getElementById("isprint"+idx);
		    	if("${maintain.isPrint}" == "Y"){
		    		isPrint.checked = true;
		    	}
		    	
		    	 getStockoutSum("${maintain.partId}","${idx.index}");
		    	</script>
			</s:iterator>
			
			<s:iterator id="det" value="#request.tmprojectTypes" >
				<script type="text/javascript">
					var projectTypeObj = {};
					projectTypeObj.id = "${det.id}";
					projectTypeObj.projectType = "${det.projectType}";
					projectArray.push(projectTypeObj);
				</script>
			</s:iterator>
		</table>
		<input type="button" id="saveBtn" value="保存单据" onclick="addTbMaintain(8000);" />
		<input type="button" id="confirmBtn" value="确认入账" onclick="addTbMaintain(8002);" />
		<br>
		合计金额： <SPAN id="totalpricespan" ></SPAN>
		<div id="partinfocollectionDiv" ></div>
		<div id='tabPlaceHolder'></div>
		
		<script type="text/javascript">
			
		
		</script>
	<br>
	<br>
	<br>
	
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
	</body> 
</html>
