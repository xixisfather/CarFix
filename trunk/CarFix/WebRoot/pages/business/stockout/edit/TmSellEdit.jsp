<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>修改销售出库</title>
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
		var gtitle = "${gtitle}";						//grid标题
		var projectArray = new Array();
		
	
		function openWin(){
			var props = "customerId,customerCode,customerName";
			showCommonWin('findAllTmTbCustomerAction.action','供应商列表',575,355,props,null);
		}
		
				
		function tmStockOutDetailTableConfigHandler(pConfig) {
			pConfig.tbar = [
			{	
				id : 'savdj',
				text : '保存',
				iconCls : 'addIcon',
				handler : function() {
					updateTmStockInDet('8000');
				}
			}, '', '-', '', {
				text : '确认出库',
				iconCls : 'addIcon',
				handler : function() {
					updateTmStockInDet('8002');
				}
			} ];
			// pConfig.autoExpandColumn='no';
		}
		
		function tmStockOutDetailTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "initEditStockInPageAction";
			pConfig.showLoadingMsg = true;
		}
		
		
		function handlerE3Table(){
		
			var totalnumspan = document.getElementById("totalnumspan");
			var totalpricespan = document.getElementById("totalpricespan");
			
			var totalNum = 0;
			var totalprice = 0;
			
			for(var j=0 ; j<tmStockOutDetailTableE3ExtStore.getCount(); j++){
				//每行的record对象
				var record = tmStockOutDetailTableE3ExtStore.getAt(j);
				var partinfoId = record.get("partinfoId");											//配件ID
				var userQuantity = document.getElementById("userQuantity"+partinfoId);				//数量
				var spanXsxj = document.getElementById("spanXsxj"+partinfoId);						//出库小计
				//数量合计
				totalNum = parseFloat(userQuantity.value) + totalNum;
				
				var isfree = document.getElementById("isFree"+partinfoId);	
				if(isfree.value == 1){
					//出库合计
					totalprice = parseFloat(spanXsxj.innerHTML) + totalprice;
				}
			
			}
			
			totalnumspan.innerHTML = Math.round(totalNum*100)/100;
			totalpricespan.innerHTML = Math.round(totalprice*100)/100;
			
			
		}
		
		 var tbPartInfoObj = {};
		 var stockInId = "";							//入库单ID
		 
		/**计算单价，小计**/
		function jsRecord(partinfoId){
			var userQuantity = document.getElementById("userQuantity"+partinfoId);					//数量
			var sellPrice = document.getElementById("sellPrice"+partinfoId);						//出库价
			var spanXsxj = document.getElementById("spanXsxj"+partinfoId);							//出库小计
			var spanCostPrice = document.getElementById("spanCostPrice"+partinfoId);				//成本价
			var spanCbxj = document.getElementById("spanCbxj"+partinfoId);							//成本小计
			
			spanXsxj.innerHTML = Math.round(parseFloat(userQuantity.value)* parseFloat(sellPrice.value)*100)/100;
			spanCbxj.innerHTML = Math.round(parseFloat(userQuantity.value)* parseFloat(spanCostPrice.innerHTML)*100)/100;
			
			handlerE3Table();
		}
		/**e3Table 删除行事件**/
		function delRecord(){
			/*当前选择行，没有选择则不处理*/
			var selectedRow = tmStockOutDetailTableE3ExtGrid.getSelectionModel().getSelected();
			if (selectedRow == undefined )	return ;
			/*移除行*/
			tmStockOutDetailTableE3ExtStore.remove(selectedRow);
			
			handlerE3Table();
		}	
		
		/**e3Table 增加行事件 **/
		function addRecord(){
			/**验证合法性**/
			if(tbPartInfoObj.partQuantity == undefined) return;
			if(parseFloat(tbPartInfoObj.partQuantity) <= 0  || parseFloat(tbPartInfoObj.price) <= 0){
				alert("数量，金额输入有误！");
				return;
			}
			for(var i=0; i<tmStockOutDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmStockOutDetailTableE3ExtStore.getAt(i);
				var e3partInfoId = record.get("partinfoId");
				if(e3partInfoId == tbPartInfoObj.tbPartInfoId){
					alert("已有配件，不能重复添加");
					return;
				}
			}
			/**验证合法性**/
			
			var idx = tmStockOutDetailTableE3ExtStore.getCount();
		
			var TopicRecord = Ext.data.Record.create([
				
			    {name: 'houseName', mapping: 'houseName'},
			    {name: 'partName', mapping: 'partName'},
			    {name: 'userQuantity', mapping: 'userQuantity'},
			    {name: 'costPrice', mapping: 'costPrice'},
			    {name: 'cbxj', mapping: 'cbxj'},
			    {name: 'sellPrice', mapping: 'sellPrice'},
			    {name: 'xsxj', mapping: 'xsxj'},
			    {name: 'partinfoId', mapping: 'partinfoId'},
			    {name: 'freeName', mapping: 'freeName'},
			    {name: 'projectTypeShow', mapping: 'projectTypeShow'},
			    {name: 'no', mapping: 'no'}
			    
			    
			    
			]);
			
			var freeHtml  = buildFreeFlagSelect("isFree"+tbPartInfoObj.tbPartInfoId,"handlerE3Table()",null,false);
		    var projectTypeHTML = buildProjectType(tbPartInfoObj.tbPartInfoId); 
			var myNewRecord = new TopicRecord({	
			    houseName: tbPartInfoObj.houseName,
			    partName: tbPartInfoObj.partName,
			    userQuantity: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"userQuantity"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.partQuantity+"\"  />",
			    costPrice: "<span id=\"spanCostPrice"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.costPrice+"</span>",
			    cbxj: "<span id=\"spanCbxj"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.cbxj+"</span>",
			    sellPrice: "<input onchange=\"jsRecord("+tbPartInfoObj.tbPartInfoId+")\" id=\"sellPrice"+tbPartInfoObj.tbPartInfoId+"\" type=text value=\""+tbPartInfoObj.price+"\"  />",
			    xsxj: "<span id=\"spanXsxj"+tbPartInfoObj.tbPartInfoId+"\" >"+tbPartInfoObj.xsxj+"</span>",
			    freeName:freeHtml,
			    projectTypeShow:projectTypeHTML,
			    no : '&nbsp;&nbsp;<a href=# onclick="delRecord();">删除</a>',
			    partinfoId: tbPartInfoObj.tbPartInfoId
			});
			
			tmStockOutDetailTableE3ExtStore.insert(idx,myNewRecord);
			
			handlerE3Table();
		}
		
		/**选择配件**/
		function openPartWin(){
			var props = "";
			showCommonWin('findAllTbPartInfoAction.action','配件列表',730,450,props,'addCallBack');
		}
		
		
		 /**选择配件之后的回调处理**/
		 function addCallBack(){
		    
		    var houseNameTD = document.getElementById("houseNameTD");
		    var partNameTD = document.getElementById("partNameTD");
		    var unitNameTD = document.getElementById("unitNameTD");
		    var quantityTD = document.getElementById("quantityTD");
		    var costPriceTD = document.getElementById("costPriceTD");
		    var cbxjTD = document.getElementById("cbxjTD");
		    var sellPriceTD = document.getElementById("sellPriceTD");
		    var xsxjTD = document.getElementById("xsxjTD");
		   
		   
		    
		    houseNameTD.innerHTML = tbPartInfoObj.houseName;
		    partNameTD.innerHTML = tbPartInfoObj.partName;
		    unitNameTD.innerHTML = tbPartInfoObj.unitName;
		    quantityTD.innerHTML = "<input id=quanInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.partQuantity+" />";
		    costPriceTD.innerHTML = tbPartInfoObj.price;
		    cbxjTD.innerHTML  =  "0.00";
		    sellPriceTD.innerHTML  =  "<input id=pceInp style=width:80 onchange=objHanlderVal(); value="+tbPartInfoObj.price+" />";;
		    xsxjTD.innerHTML  = "0.00";
		      
		 }
		 
		 /**计算全局配件对象中单价，小计**/
		 function objHanlderVal(){
		 
		 	var costPriceTD = document.getElementById("costPriceTD");
		    var cbxjTD = document.getElementById("cbxjTD");
		    var sellPriceTD = document.getElementById("sellPriceTD");
		    var xsxjTD = document.getElementById("xsxjTD");
		 	var quanInp =  document.getElementById("quanInp");
		 	var pceInp =  document.getElementById("pceInp");
		 	//出库小计
		 	var  xsxj = Math.round((parseFloat(quanInp.value) * parseFloat(pceInp.value))*100)/100;
		 	//成本小计
		 	var cbxj = Math.round((parseFloat(quanInp.value) * parseFloat(costPriceTD.innerHTML))*100)/100;
		 	
		 	tbPartInfoObj.partQuantity = quanInp.value;
		 	tbPartInfoObj.price = pceInp.value;
		 	tbPartInfoObj.costPrice = parseFloat(costPriceTD.innerHTML);
			tbPartInfoObj.cbxj = cbxj;	 
			tbPartInfoObj.xsxj = xsxj;
		 	
		 	cbxjTD.innerHTML = cbxj;
		 	xsxjTD.innerHTML = xsxj;
		 }
		 
		 /** 修改配件入库**/
		 function updateTmStockInDet(confirm){
		 	if(tmStockOutDetailTableE3ExtStore.getCount() == 0){
		 		Ext.MessageBox.alert('温馨提示：', '配件内容不能为空.', null);
		 		return;
		 	}
		 	var str = "";
	 		for(var i=0; i<tmStockOutDetailTableE3ExtStore.getCount();i++){
				//每行的record对象
				var record = tmStockOutDetailTableE3ExtStore.getAt(i);
				
				var partinfoId = record.get("partinfoId");
				var userQuantity = document.getElementById("userQuantity"+partinfoId);			//数量
				var sellPrice = document.getElementById("sellPrice"+partinfoId);				//税前单价
				var isFree = document.getElementById("isFree"+partinfoId);						//免费标志
				var projectType = document.getElementById("projectType"+partinfoId);			//维修项目类型
				var projectTypeVal = projectType.value!=""?projectType.value:"null";
				
				
				str += partinfoId + ":" + userQuantity.value + ":" + sellPrice.value + ":true:" + isFree.value+":"+projectTypeVal+",";
			}
			
			
			var partCol = document.getElementById("partCol");
			var totalQuantity = document.getElementById("totalQuantity");
			var totalPrice = document.getElementById("totalPrice");
			partCol.value = str;
			totalQuantity.value = document.getElementById("totalnumspan").innerHTML;
			totalPrice.value = document.getElementById("totalpricespan").innerHTML;
			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
			
		
		 
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
	</script>
	<body onload="handlerE3Table();" >
		<s:form action="updateSellAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="tmStockOut.totalQuantity" id="totalQuantity" ></s:hidden>
			<s:hidden name="tmStockOut.totalPrice" id="totalPrice" ></s:hidden>
			<s:hidden id="customerId" name="tmStockOut.customerBill" ></s:hidden>
			
			
			<s:hidden name="tmStockOut.id" ></s:hidden>
			<s:hidden name="tmStockOut.userId" ></s:hidden>
			<s:hidden name="tmStockOut.createDate" ></s:hidden>
			<s:hidden name="tmStockOut.outType" ></s:hidden>
			<table>
				<tr>
					<td>销售单号：</td>
					<td><s:textfield cssStyle="color:#FF0000" name="tmStockOut.stockOutCode" readonly="true" /></td>
					<td>委托书号：</td>
					<td><s:textfield name="tmStockOut.trustBill" /></td>
					<td>销售日期：</td>
					<td><s:textfield id="stockOutDate" name="tmStockOut.stockOutDate" readonly="true" >
							<s:param name="value"><s:date name="tmStockOut.stockOutDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
						<font color="red">*</font>
						<e3c:calendar for="stockOutDate" dataFmt="yyyy-MM-dd"  />
					</td>
					<td>销售类型：</td>
					<td><s:select name="tmStockOut.soleTypeId" list="#request.tsyList" emptyOption="false" listKey="id" listValue="soleName"/></td>
				</tr>
				<tr>
					<td>客户号：</td>
					<td><s:textfield id="customerCode" name="#request.customer.customerCode" onfocus="openWin();"  /><font color="red">*</font></td>
					<td>客户名称：</td>
					<td><s:textfield id="customerName" name="#request.customer.customerName" /></td>
					<td>操作员：</td>
					<td>${tmUser.userRealName }</td>
				</tr>
			</table>
		</s:form>
		 <br>
		 <table id="mainTable"  >
			<tr>
				<th width="100" >仓库</th>
				<th width="120">配件名称</th>
				<th width="70">单位</th>
				<th width="80">数量</th>
				<th width="80">成本价</th>
				<th width="80">成本小计</th>
				<th width="80">销售价</th>
				<th width="80">销售小计</th>
				<th colspan="2" width="80">操作</th>
			</tr>
			<tr>
				<td id="houseNameTD"  ></td>
				<td id="partNameTD" ></td>
				<td id="unitNameTD"  ></td>
				<td id="quantityTD" ></td>
				<td id="costPriceTD"></td>
				<td id="cbxjTD"></td>
				<td id="sellPriceTD"></td>
				<td id="xsxjTD"></td>
				
				<td><input type="button" value="选择配件" onclick="openPartWin();"  /></td>
				<td><input type="button" value="增加" onclick="addRecord();"  /></td>
			</tr>
		</table>
		 
		 <e3t:table id="tmStockOutDetailTable" uri="initEditSellPageAction.action" var="detVo"
			scope="request" items="detVos" mode="ajax" caption="采购出库明细" statusVar="vv"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1100"
			height="320" >
			<e3t:column property="no" width="60" title="操作" sortable="false">
				&nbsp;
  				<a href="#" onclick="delRecord();" >
					<font color="blue">删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="freeName" title="免费标志" >
				<select onchange="handlerE3Table();" id="isFree${detVo.partinfoId}"  >
					<s:iterator id="free" value="#request.freeFlagTypes">
						<c:if test="${ detVo.isFree == free.key }">
							<option selected="selected" value="${free.key}">
								${free.value }
							</option>
						</c:if>
						<c:if test="${ detVo.isFree != free.key }">
							<option value="${free.key}">
								${free.value }
							</option>
						</c:if>
					</s:iterator>
				</select>
			</e3t:column>
			<e3t:column property="projectTypeShow" title="项目维修类型" >
				<select onchange="handlerE3Table();"  id="projectType${detVo.partinfoId}"  >
					<option />
					<s:iterator id="obj" value="#request.tmprojectTypes">
						<c:if test="${ detVo.projectType == obj.projectType }">
							<option selected="selected" value="${obj.id}">
								${obj.projectType }
							</option>
						</c:if>
						<c:if test="${ detVo.projectType != obj.projectType }">
							<option value="${obj.id}">
								${obj.projectType }
							</option>
						</c:if>
					</s:iterator>
				</select>
			</e3t:column>
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="userQuantity"  title="出库数量" >
				<input onchange="jsRecord('${detVo.partinfoId}')" id="userQuantity${detVo.partinfoId}" type="text" value="${detVo.quantity}"  style="width:80px" />
			</e3t:column>
			<e3t:column width="80" property="costPrice"  title="成本价" >
				<span id="spanCostPrice${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2" pattern="#######"  value="${detVo.costPrice}"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column width="80" property="cbxj"  title="成本小计" >
				<span id="spanCbxj${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2" pattern="#######"   value="${detVo.costPrice * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			<e3t:column width="80" property="sellPrice"  title="销售价" >
				<input onchange="jsRecord('${detVo.partinfoId}')" id="sellPrice${detVo.partinfoId}" type="text" value="${detVo.price}" style="width:80px"  />
			</e3t:column>
			<e3t:column width="80" property="xsxj"  title="销售小计" >
				<span id="spanXsxj${detVo.partinfoId}" >
					<fmt:formatNumber  maxFractionDigits="2" pattern="#######"   value="${detVo.price * detVo.quantity }"  ></fmt:formatNumber>
				</span>
			</e3t:column>
			
			<e3t:column property="isFree"  hidden="true" title="免费标志" sortable="false" />
			<e3t:column property="partinfoId"  hidden="true"  title="配件ID" />
			<e3t:column property="stockOutDetailId" hidden="true" title="入库明细ID" />
		</e3t:table>
		
		<div id="partinfocollectionDiv" ></div>
		 &nbsp;合计数量： <span id="totalnumspan" > </span> &nbsp;&nbsp;销售合计： <SPAN id="totalpricespan" ></SPAN>&nbsp;&nbsp;
		
		
		<s:iterator id="det" value="#request.tmprojectTypes" >
			<script type="text/javascript">
				var projectTypeObj = {};
				projectTypeObj.id = "${det.id}";
				projectTypeObj.projectType = "${det.projectType}";
				projectArray.push(projectTypeObj);
			</script>
		</s:iterator>
	</body> 
	

</html>
