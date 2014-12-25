<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
				text : '添加配件',
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
			pConfig.form = "chooseTbPartInfoRemStockAction";
			pConfig.showLoadingMsg = true;
		}
		
		function addPartInfo(){
			var chpartinfo = document.getElementsByName("chpartinfo");
			for(var i=0; i<chpartinfo.length; i++){
				
				//仓库
				var houseName = document.getElementById(chpartinfo[i].value+"tsh");
				//仓库id
				var storeHoseId = document.getElementById(chpartinfo[i].value+"tshId");
				//配件名称
				var partName = document.getElementById(chpartinfo[i].value+"pn");
				//配件类型
				var typeName = document.getElementById(chpartinfo[i].value+"tn");
				//库存数量
				var storeQuantity = document.getElementById(chpartinfo[i].value+"sq");
				//配件数量
				var numDom = document.getElementById(chpartinfo[i].value+"num");
				//配件成本价
				var costprice = document.getElementById(chpartinfo[i].value+"costprice");
				//配件出库价
				var price = document.getElementById(chpartinfo[i].value+"price");
				//配件销售价
				var sellPrice = document.getElementById(chpartinfo[i].value+"sellPrice");

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
					if(parent.partInfoArray[j][0] == chpartinfo[i].value){
						//之前所定义的配件数量
						var oldNum = parseFloat(parent.partInfoArray[j][parent.partInfoArray[j].length - 5]);
						//现所定义的配件数量
						var newNum = parseFloat(numDom.value);
						
						//配件数量累加
						parent.partInfoArray[j][parent.partInfoArray[j].length - 5] = Math.round((oldNum+newNum)*100)/100;
						//计算配件税前小计
						var cop = parent.partInfoArray[j][parent.partInfoArray[j].length - 4];
						parent.partInfoArray[j][parent.partInfoArray[j].length - 3] = Math.round(((oldNum+newNum)*cop)*100)/100;
						if(price != null){
							//计算出库价格小计
							var pricetotal = parseFloat(price.value*(oldNum+newNum));
							parent.partInfoArray[j][parent.partInfoArray[j].length - 1] =  Math.round(pricetotal*100)/100;
						}
					
						
						flag = true;
						 
					}
				}
				/*配件不存在容器中，则直接添加配件 格式为Array */
				if(!flag){
					//税前小计
					var costtotal = parseFloat(numDom.value) * parseFloat(costprice.value);
					if(price !=null){
						//报溢小计
						var pricetotal = parseFloat(numDom.value) * (parseFloat(price.value));
					}
					
					
					var dataCollection = new Array();
					dataCollection.push(chpartinfo[i].value);
					dataCollection.push(storeHoseId.value);
					dataCollection.push(houseName.value);
					dataCollection.push(partName.value);
					dataCollection.push(typeName.value);
					dataCollection.push(storeQuantity.value);
					dataCollection.push(Math.round(numDom.value*100)/100);
					dataCollection.push(Math.round(costprice.value*100)/100);
					dataCollection.push(Math.round(costtotal*100)/100);
					if(price != null){
						dataCollection.push(Math.round(price.value*100)/100);
						dataCollection.push(Math.round(pricetotal*100)/100);
					}else{
						dataCollection.push('0.00');
						dataCollection.push('0.00');
					}
					parent.partInfoArray.push(dataCollection);
				}
			}
			
			
			//计算总数量和总金额
			var totalnum = 0 ;			//合计数量
			var totalprice = 0;			//报溢合计金额
			for(var i=0; i<parent.partInfoArray.length; i++){
				var pi = parent.partInfoArray[i];
				//配件数量
				var num = parseFloat(parent.partInfoArray[i][pi.length-5]);
				//累加配件数量
				totalnum =parseFloat(totalnum)+ num;
				
				//报出库价格小计累加
				totalprice = parseFloat(parent.partInfoArray[i][pi.length-1])+totalprice;
				
				/*移库出仓特殊处理*/	
				if(price == null){
					totalprice +=  parseFloat(parent.partInfoArray[i][pi.length-3]);
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
		
		
	</script>
	<body  >
		<s:form action="chooseTbPartInfoRemStockAction.action" ></s:form>
		<e3t:table id="tbPartInfoTable" uri="chooseTbPartInfoRemStockAction.action" var="tbPartInfo"
			scope="request" items="tbPartInfoList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="700"
			height="400" >
			<e3t:column property="houseName" title="仓库" />
			<e3t:column property="partName" title="配件名称" />
			<e3t:column property="typeName" title="配件类型" />
			<e3t:column property="storeQuantity" title="库存" />
			<e3t:column width="40" property="unitName" title="单位" />
			<e3t:column property="costPrice" title="成本价" />
			<e3t:column property="num" title="配件数量" >
				<input type="text" maxlength="80" id="${tbPartInfo.id}num" style="width:80px" />
			</e3t:column>
			<s:if test="request.typeName != '领用'">
			
				<e3t:column property="price" title="${typeName}价" >
					<s:if test="#request.typeName == '销售'">
						<input type="text"  maxlength="80" id="${tbPartInfo.id}price" value="${tbPartInfo.sellPrice}"   />
					</s:if>
					<s:if test="#request.typeName == '领用'">
						<input type="text" disabled="disabled" maxlength="80" id="${tbPartInfo.id}price" value="${tbPartInfo.costPrice}"   />
					</s:if>
					
					<s:if test="#request.typeName == '报损'">
						<input type="text"  maxlength="80" id="${tbPartInfo.id}price" value="${tbPartInfo.costPrice}"   />
					</s:if>
					<s:if test="#request.typeName == '调出'">
						<input type="text"  maxlength="80" id="${tbPartInfo.id}price"  value="${tbPartInfo.sellPrice}" />
					</s:if>
				</e3t:column>
			</s:if>
			<e3t:column property="no" title="是否添加" hidden="true"
				sortable="false">
				<input type="checkbox" name="chpartinfo" onclick="checkedPartInfo(this,'${tbPartInfo.id}');" value="${tbPartInfo.id}"/>
				<input type="hidden" id="${tbPartInfo.id}tsh" value="${tbPartInfo.tmStoreHouse.houseName}" />
				<input type="hidden" id="${tbPartInfo.id}tshId" value="${tbPartInfo.tmStoreHouse.id}" />
				<input type="hidden" id="${tbPartInfo.id}pn" value="${tbPartInfo.partName}" />
				<input type="hidden" id="${tbPartInfo.id}tn" value="${tbPartInfo.tmPartType.typeName}" />
				<input type="hidden" id="${tbPartInfo.id}sq" value="${tbPartInfo.storeQuantity}" />
				<input type="hidden" id="${tbPartInfo.id}costprice" value="${tbPartInfo.costPrice}"  />
				<input type="hidden" id="${tbPartInfo.id}sellPrice" value="${tbPartInfo.sellPrice}"  />
			</e3t:column>
		</e3t:table>
		
		
	</body> 
</html>
