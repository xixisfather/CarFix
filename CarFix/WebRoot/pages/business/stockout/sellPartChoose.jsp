<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>TbPartInfoChoose.jsp</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	<script type="text/javascript">
		function tbPartInfoTableConfigHandler(pConfig) {
			
			pConfig.tbar = [
		
			{	
				text : '确定添加',
				iconCls : 'addIcon',
				handler : function() {
					addPartInfo();
				}
			}, '', '-', '', {
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbPartInfoTable();
				}
			}, '', '-', '', {
				text : '新增配件',
				iconCls : 'addIcon',
				handler : function() {
					window.location.href = '<%= request.getContextPath() %>/tbPartInfoForwardAction.action?submit=ajax';	
				}
			} ];
			
			// pConfig.autoExpandColumn='no';
		}
		
		function tbPartInfoTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "chooseTbPartInfoSellAction";
			pConfig.showLoadingMsg = true;
		}
		
		function addPartInfo(){
		
		
			var chpartinfo = document.getElementsByName("chpartinfo");
			
			for(var i=0; i<tbPartInfoTableE3Grid.getStore().getCount(); i++){
			
				var record = tbPartInfoTableE3Grid.getStore().getAt(i); // Get the Record
				var partInfoId = record.get("id");
				//仓库
				var houseName =  record.get("houseName");//document.getElementById(partInfoId+"tsh");
				//仓库id
				var storeHoseId = record.get("tmStoreHouseId");//document.getElementById(partInfoId+"tshId");
				//配件名称
				var partName = record.get("partName");//document.getElementById(partInfoId+"pn");
				//配件类型
				var typeName = record.get("typeName");//document.getElementById(partInfoId+"tn");
				//库存数量
				var storeQuantity = record.get("storeQuantity");//document.getElementById(partInfoId+"sq");
				//配件数量
				var numDom = document.getElementById(partInfoId+"num");
				//配件成本价
				var costprice = record.get("costPrice");//document.getElementById(partInfoId+"costprice");
				//配件出库价
				var price = document.getElementById(partInfoId+"price");
				//配件销售价
				var sellPrice = record.get("sellPrice");//document.getElementById(partInfoId+"sellPrice");
				//免费标志
				var isfree = document.getElementById(partInfoId+"isfree");
				//维修项目类型
				var projectType = document.getElementById(partInfoId+"projectType");
				
				if(numDom.value == "" || parseFloat(numDom.value) <= 0)
					continue;
				if(price != null){
					if(!parseFloat(price.value)){
						price.value = "";
						price.focus();
						alert("请正确填写价格");
						return;
					}
				}
				
				var flag = false;
				/*如果此配件在容器已存在的话，则只累加数量*/
				
				for(var j=0; j<parent.partInfoArray.length; j++){
					if(parent.partInfoArray[j][0] == partInfoId){
						//之前所定义的配件数量
						var oldNum = parseFloat(parent.partInfoArray[j][parent.partInfoArray[j].length - 8]);
						//现所定义的配件数量
						var newNum = parseFloat(numDom.value);
						//配件数量累加
						parent.partInfoArray[j][parent.partInfoArray[j].length - 8] = Math.round((oldNum+newNum)*100)/100;
						//计算配件税前小计
						var cop = parent.partInfoArray[j][parent.partInfoArray[j].length - 7];
						parent.partInfoArray[j][parent.partInfoArray[j].length - 6] = Math.round(((oldNum+newNum)*cop)*100)/100;
						if(price != null){
							//计算出库价格小计
							var pricetotal = parseFloat(price.value*(oldNum+newNum));
							parent.partInfoArray[j][parent.partInfoArray[j].length - 4] =  Math.round(pricetotal*100)/100;
						}
					
						flag = true;
						
						 
					}
				}
				/*配件不存在容器中，则直接添加配件 格式为Array */
				if(!flag){
					//税前小计
					var costtotal = parseFloat(numDom.value) * parseFloat(costprice);
					if(price !=null){
						//报溢小计
						var pricetotal = parseFloat(numDom.value) * (parseFloat(price.value));
					}
					
					
					var dataCollection = new Array();
					dataCollection.push(partInfoId);
					dataCollection.push(storeHoseId);
					dataCollection.push(houseName);
					dataCollection.push(partName);
					dataCollection.push(typeName);
					dataCollection.push(storeQuantity);
					dataCollection.push(Math.round(numDom.value*100)/100);
					dataCollection.push(Math.round(costprice*100)/100);
					dataCollection.push(Math.round(costtotal*100)/100);
					if(price != null){
						dataCollection.push(Math.round(price.value*100)/100);
						dataCollection.push(Math.round(pricetotal*100)/100);
					}else{
						dataCollection.push('0.00');
						dataCollection.push('0.00');
					}
					/* 销售出库特殊字段（是否免费）*/
					//var freeVal = isfree.checked == true ? '是':'否';
					var projecttypeVal = projectType.value != ""?projectType.value:'null';
					var projecttypeShow = "";
					//处理维修项目下拉选择 
			    	 for(var i=0;i<projectType.length;i++){
			    	 	if(projectType.options[i].selected){
			    	 		projecttypeShow = projectType.options[i].innerText;
			    	 	}
			    	 }
					dataCollection.push(projecttypeShow);
					dataCollection.push(projecttypeVal);
					dataCollection.push(isfree.value);
					parent.partInfoArray.push(dataCollection);
					
					document.getElementById(partInfoId+"num").value = "";
				}
			}
			
			
			//计算总数量和总金额
			var totalnum = 0 ;			//合计数量
			var totalprice = 0;			//合计金额
			for(var i=0; i<parent.partInfoArray.length; i++){
				var pi = parent.partInfoArray[i];
				//配件数量
				var num = parseFloat(parent.partInfoArray[i][pi.length-8]);
				//累加配件数量
				totalnum =parseFloat(totalnum)+ num;
				//免费标志则不进行总金额的累加
				if(parent.partInfoArray[i][pi.length-1] == '无'){
					//报出库价格小计累加
					totalprice = parseFloat(parent.partInfoArray[i][pi.length-4])+totalprice;
				
				}
				
			}
			parent.document.getElementById("totalnumspan").innerHTML = Math.round(totalnum*100)/100;
			parent.document.getElementById("totalpricespan").innerHTML = Math.round(totalprice*100)/100;
			
			/*将合计数量，出库价格合计 放入隐藏域*/
			if(parent.document.getElementById("totalQuantity")!=null)
				parent.document.getElementById("totalQuantity").value= Math.round(totalnum*100)/100;
			if(parent.document.getElementById("totalPrice")!=null)
				parent.document.getElementById("totalPrice").value= Math.round(totalprice*100)/100;
			
			
			parent.createGrid();
			parent.win.hide();
			
			
		}
		
		
		function handlerBeforeAddPartInfo(){
			var customerId = parent.document.getElementById("customerId").value;
			
			var strs = "customerId="+customerId+"&partIds=";
			
			for(var k=0;k<parent.partInfoArray.length;k++){
				var parArr  = parent.partInfoArray[k];
				var partId =  parArr[0];
				var sellprice = parArr[parArr.length-5];
				var totalsellprice = parArr[parArr.length-4];
				var num = parArr[parArr.length-8];
				
				strs += partId + ",";
			}
			
			return strs;
		}
		
		
		function handlerAfterAddPartInfo(responseText){
			var customerId = parent.document.getElementById("customerId").value;
			for(var k=0;k<parent.partInfoArray.length;k++){
				var parArr  = parent.partInfoArray[k];
				var partId =  parArr[0];
				var sellprice = parArr[parArr.length-4];
				var totalsellprice = parArr[parArr.length-3];
				var num = parArr[parArr.length-7];
				for(var i=0;i<responseText.split(",").length;i++){
					var strs = responseText.split(",")[i];
					if(strs != ""){
						var partInfoId = strs.split(":")[0];
						var cuSellPrice = strs.split(":")[1];
						if(partId == partInfoId){
							parArr[parArr.length-3] = cuSellPrice;
							newTotalPrice = formatFloat(num*cuSellPrice,2);
							parArr[parArr.length-2] = newTotalPrice;
						}
					}
				}
	
			}
			parent.createGrid();
			parent.win.hide();
			
		}
		
		function checkedPartInfo(chDom,id){
			if(chDom.checked == false) return;
			
			var numDom = document.getElementById(id+"num");
			if(!parseFloat(numDom.value)){
				numDom.value = "";
				numDom.focus();
				alert("请正确填写配件数量");
				return;
			}
			
		}
		
		function requestSellPrice(partId,entrustId){
			
			var url = "getStockOutCustomerSellpriceAction.action";
			
			var param = handlerBeforeAddPartInfo();
			
			
			var ajax = new Ajax.Request(url, {
				method : 'get',
	
				parameters:param,
				
				onComplete : getSellpriceInfo,
	
				asynchronous : true
	
			});
		}
		
		function getSellpriceInfo(originalRequest){
		
			var reponseInfo = originalRequest.responseText;
			
			handlerAfterAddPartInfo(reponseInfo);
		}
	</script>
	<body  >
		<s:form action="chooseTbPartInfoSellAction.action">
			<s:hidden name="typeName" value="%{#request.typeName}" ></s:hidden>
			<table>
				<tr>	
					<td>仓库</td>
					<td><s:select name="tbPartInfo.tmStoreHouse.id" list="#request.stroeHouseList" emptyOption="true" listKey="id" listValue="houseName"/></td>
					<td>车辆类型</td>
					<td><s:select name="tbPartInfo.tmCarModelType.id" list="#request.carModelTypeList" emptyOption="true" listKey="id" listValue="modelName"/></td>
					<td>配件名称</td>
					<td><s:textfield name="tbPartInfo.partName" /></td>
				</tr>
				
				<tr>
					<td>配件代码</td>
					<td><s:textfield  name="tbPartInfo.partCode" ></s:textfield></td>
					<td>拼音名称</td>
					<td><s:textfield  name="tbPartInfo.pinyinCode" ></s:textfield></td>
					<td>配件类型</td>
					<td><s:select name="tbPartInfo.tmPartType.id" list="#request.partTypeList" emptyOption="true" listKey="id" listValue="typeName"/></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询" onclick="tbPartInfoTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbPartInfoTable" uri="chooseTbPartInfoSellAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1100"
			height="400" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partCode" title="配件代码" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column width="60" property="storeQuantity" title="库存" />
			<e3t:column width="40" property="unitName" title="单位" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="num" title="配件数量" >
				<input type="text" maxlength="80"  id="${tbPartInfo.id}num" style="width:80px" />
			</e3t:column>
			<e3t:column property="price" title="${typeName}价" >
				<s:if test="#request.typeName == '销售'">
					<input type="text"  maxlength="80" id="${tbPartInfo.id}price" value="${tbPartInfo.sellPrice}"  style="width:80px"  />
				</s:if>
				<s:if test="#request.typeName == '领用'">
					<input type="text" maxlength="80" id="${tbPartInfo.id}price" value="${tbPartInfo.costPrice}" style="width:80px"  />
				</s:if>
				
				<s:if test="#request.typeName == '报损'">
					<input type="text"  maxlength="80" id="${tbPartInfo.id}price" value="${tbPartInfo.costPrice}" style="width:80px"  />
				</s:if>
				<s:if test="#request.typeName == '调出'">
					<input type="text"  maxlength="80" id="${tbPartInfo.id}price"  value="${tbPartInfo.sellPrice}" style="width:80px" />
				</s:if>
			</e3t:column>
			<s:if test="#request.typeName == '销售' ">
				<e3t:column width="100"  property="isfree" title="免费标志" sortable="false">
					<select id="${tbPartInfo.id}isFree">
						<s:iterator id="freeKey" value="#request.freeFlagTypes">
							<option value="${freeKey}">
								${freeKey }
							</option>
						</s:iterator>
					</select>
				</e3t:column>
				<e3t:column width="100"  property="projectType" title="维修项目类型" sortable="false">
					<select id="${tbPartInfo.id}projecttype">
						<option value="">&nbsp;</option>
						<s:iterator id="obj" value="#request.tmprojectTypes">
							<option value="${obj.id}">
								${obj.projectType}
							</option>
						</s:iterator>
					</select>
				</e3t:column>
			</s:if>
			<e3t:column property="id" hidden="true" title="配件id" />
			<e3t:column property="tmStoreHouseId" hidden="true" title="仓库id" />
			<e3t:column property="typeName" hidden="true" title="配件类型 " />
			<e3t:column property="sellPrice" hidden="true" title="配件类型 " />
		</e3t:table>
		
	</body> 
</html>
