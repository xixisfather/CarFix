<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.selfsoft.business.model.TbFixEntrustContent"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.selfsoft.baseparameter.model.TmProjectType"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理委托书修改</title>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbBespokerPartConent.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script>
		var pt = '';
		
		<%List<TmProjectType> listPT = (List<TmProjectType>)request.getAttribute("tmProjectTypeList");
		String ppv = "";
		if(null != listPT && listPT.size() > 0){
			for(TmProjectType t : listPT){
			ppv += t.getId() + "&" + t.getProjectType() + "#";	
		
			}
		}
		%>
		
		pt = "<%=ppv%>";
		</script>
	</head>

	<body>
		<s:form action="tbFixEntrustReBalanceUpdateAction.action">
			<table>
				<tr>
					<td>
						<s:hidden name="tbFixEntrust.id"></s:hidden>
					</td>
				</tr>
				</tr>
					<tr>
					<td>委托书号</td>
					<td>
						<s:textfield name="tbFixEntrust.entrustCode" readonly="true" size="10"/>
					</td>
					<td>修理类型</td>
					<td>
						<s:select id="tmFixTypeId" name="tbFixEntrust.tmFixType.id"
							list="#request.tmFixTypeMap" listKey="key" listValue="value" cssStyle="width:100;"/>
					</td>
					<td>
						接待员
					</td>
					<td>
						<!--
						<s:textfield id="userName" name="tbFixEntrust.tmUser.userRealName" value="%{#session.tmUser.userRealName}" readonly="true" size="10"></s:textfield>
						<s:hidden id="userId" name="tbFixEntrust.tmUser.id" value="%{#session.tmUser.id}"></s:hidden>
				    -->
				        <s:textfield id="userName" name="tbFixEntrust.tmUser.userRealName" readonly="true" size="10"></s:textfield>
						<s:hidden id="userId" name="tbFixEntrust.tmUser.id"></s:hidden>
					</td>
				
					<td>修理时间</td>
					<td>
						<s:bean name="java.util.Date" id="date"></s:bean>
						<s:textfield id="fixDate" name="tbFixEntrust.fixDate" size="15">
							<s:param name="value"><s:date name="date" format="yyyy-MM-dd HH:mm:ss"/></s:param>
						</s:textfield>
						<font color="blue">#</font>
					</td>
				</tr>
				<tr>	
					<td>预计竣工时间</td>
					<td>
						
						<s:textfield id="estimateDate" name="tbFixEntrust.estimateDate" size="15"/> 
						<font color="red">*</font>
						<e3c:calendar for="estimateDate" minDay="0" dataFmt="yyyy-MM-dd HH:mm:ss"/>
						
					</td>
				
					<td>车牌号</td>
					<td>
						<s:hidden id="tbCarInfoId" name="tbFixEntrust.tbCarInfo.id"></s:hidden>
						<s:textfield id="licenseCode" name="tbFixEntrust.tbCarInfo.licenseCode" onblur="getInfoByCarLicense();" size="10"/>
						<font color="red">*</font>
						<img src="<%= request.getContextPath() %>/images/icons/find.gif" style="cursor: pointer;" onclick="openWin();"/>
						
					</td>
					<td>底盘号</td>
					<td>
						<s:textfield id="chassisCode" name="tbFixEntrust.tbCarInfo.chassisCode" readonly="true" size="17"/>
					</td>
					<td>车型</td>
					<td>
						<s:select id="tmCarModelTypeId" name="tbFixEntrust.tbCarInfo.tmCarModelType.id"
							list="#request.tmCarModelTypeMap" listKey="key" listValue="value"
							emptyOption="true" disabled="true" cssStyle="width:100;"/>
					</td>
				</tr>
				<tr>	
					<td>购车日期</td>
					<td>
						
						<s:textfield id="purchaseDate" name="tbFixEntrust.tbCarInfo.purchaseDate" readonly="true" size="10">
							<s:param name="value"><s:date name="tbFixEntrust.tbCarInfo.purchaseDate" format="yyyy-MM-dd"/></s:param>
						</s:textfield>
					</td>
					<td>发动机号</td>
					<td>
						<s:textfield id="engineCode" name="tbFixEntrust.tbCarInfo.engineCode" readonly="true" size="15"></s:textfield>
					</td>
				
					<td>客户号</td>
					<td>
						<s:hidden id="tbCustomerId" name="tbFixEntrust.tbCustomer.id"></s:hidden>
						<s:textfield id="customerCode" name="tbFixEntrust.tbCustomer.customerCode" readonly="true" size="10"/>
					</td>
					<td>车主</td>
					<td>
						<s:textfield id="customerName" name="tbFixEntrust.tbCustomer.customerName" readonly="true" size="10"></s:textfield>
					</td>
				</tr>
				<tr>	
					<td>
						地址
					</td>
					<td>
						<s:textfield id="address" name="tbFixEntrust.tbCustomer.address" readonly="true" size="15"></s:textfield>
					</td>
					<td>电话</td>
					<td>
						<s:textfield id="phone" name="tbFixEntrust.tbCustomer.phone" readonly="true" size="10"></s:textfield>
					</td>
					<td>手机</td>
					<td>
						<s:textfield id="telphone" name="tbFixEntrust.tbCustomer.telephone" readonly="true" size="11"></s:textfield>
					</td>
						<td>油表指示</td>
					
					<td>
						<s:textfield id="oilMeter" name="tbFixEntrust.oilMeter" size="10" maxlength="10" onblur="oilValidate();"></s:textfield>
					</td>
				</tr>
				<tr>	
					<td>进站里程</td>
					<td>
						<s:textfield id="enterStationKilo" name="tbFixEntrust.enterStationKilo" size="10" maxlength="10" onblur="milterValidate('enterStationKilo');"></s:textfield>
					</td>
					<td>提醒保养公里数</td>
					<td>
						<s:textfield id="remindMaintainKilo" name="tbFixEntrust.remindMaintainKilo" size="10" maxlength="10" onblur="milterValidate('remindMaintainKilo');"></s:textfield>
					</td>
					<td>提醒保养日期</td>
					<td>
						<s:textfield id="remindMaintainDate" name="tbFixEntrust.remindMaintainDate" size="10"></s:textfield>
						<e3c:calendar for="remindMaintainDate" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>客户故障描述</td>
					<td>
						<s:textarea id="wrongDescribe" name="tbFixEntrust.wrongDescribe" rows="2" cols="15"></s:textarea>
					</td>
					<td>送修症状</td>
					<td>
						<s:textarea id="beforeFixState" name="tbFixEntrust.beforeFixState" rows="2" cols="15"></s:textarea>
					</td>
					<td>检修结果</td>
					<td>
						<s:textarea id="checkResult" name="tbFixEntrust.checkResult" rows="2" cols="15"></s:textarea>
					</td>
					<td>备注</td>
					<td>
						<s:textarea id="remark" name="tbFixEntrust.remark" rows="2" cols="15"></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="10"><hr/></td>
				</tr>
				<tr>
					<td>修理项目:</td>
					<td></td>
					<td>工时单价：</td>
					<td>
						
						<s:textfield id="workingHourPrice" name="tbFixEntrust.workingHourPrice" readonly="true"/>
						<input type="hidden" id="count" name="count" value="0"/>
					</td>
					<td>
					    调价基数:
					</td>
					<td>
						<input id="tjjs"  size="10"/>
						<input type="button" value="确定" onclick="changeFixHour();"/>
					</td>
				</tr>
				<tr>
					
					<td colspan="10">
						<div id="content">
							<div id="fixWorkingContent" class="x-hide-display">
						<table id="tbWorkingInfoTable" border="0">
							<tr>
								<td width="90">
									操作
								</td>
								
								<td width="60">
									免费标志
								</td>
								<td width="90">
									修理工位
								</td>
								<td width="140">
									修理内容
								</td>
								<td width="60">
									修理工时
								</td>
								<td width="70">
									修理工时费
								</td>
								<td width="60">
									派工工时
								</td>
								<td width="60">
									修理工
								</td>
								<td>
								    帐类
								</td>
								<td>
								    维修项目类型
								</td>
								<td>
								    维修类型
								</td>
								
							</tr>
							
							<tr>
								<td width="90">
									统计行:
								</td>
								
								<td width="60">
									
								</td>
								<td width="90">
									
								</td>
								<td width="140">
									
								</td>
								<td width="60">
									<input type="text" id="fixHourCount" disabled="true" value="0" size="10"/>
								</td>
								<td width="80">
									<input type="text" id="fixHourMoneyCount" disabled="true" value="0.00" size="10"/>
								</td>
								<td width="70">
									<input type="text" id="sendHourCount" disabled="true" value="0" size="10"/>
								</td>
								<td width="60">
									
								</td>
							</tr>
						</table>
						</div>
						
						<div id="partContent" class="x-hide-display">
						<s:hidden id="partCol" ></s:hidden>
						<s:hidden id="totalPrice" ></s:hidden>
							<table id="mainTable"  >
								<tr id="thHead" >
									<td width="100" >仓库</td>
									<td width="120">配件名称</td>
									<td width="70">单位</td>
									<td width="100">发料单价</td>
									<td width="100">数量</td>
									<td width="100">小计</td>
									<td width="100" align="center" >操作</td>
								</tr>
								<s:iterator value="#request.partConents" id="part" >
									<tr id="TR${part.tbPartInfo.id}" >
										<td>${part.tbPartInfo.tmStoreHouse.houseName}</td>
										<td>${part.tbPartInfo.partName}</td>
										<td>${part.tbPartInfo.tmUnit.unitName}</td>
										<td><input id="price${part.tbPartInfo.id}" name="pirces" value="${part.price }" onchange="jsprice('${part.tbPartInfo.id}');" /></td>
										<td><input id="partQuantity${part.tbPartInfo.id}" value="${part.partQuantity }" name="quantities" onchange="jsprice('${part.tbPartInfo.id}');"  /></td>		
										<td id="xj${part.tbPartInfo.id}" >
											<fmt:formatNumber maxFractionDigits="2"  value="${part.partQuantity * part.price }"  ></fmt:formatNumber>
										</td>
										<td align="center" ><input type="button" value="删除" onclick="deleteRow('${part.tbPartInfo.id}');" /></td>
									</tr>
									<script type="text/javascript">
				
										var tbmainObj = {};
									    tbmainObj.tbPartInfoId = "${part.tbPartInfo.id}";
									    tbmainObj.partQuantity = "${part.partQuantity}";
									    tbmainObj.price = "${part.price}";
									    tbmainObj.totalPrice = parseFloat(document.getElementById("xj${part.tbPartInfo.id}").innerHTML);
									    tbmainArray.push(tbmainObj);
							    
							    	</script>
									
								</s:iterator>
							</table>
						</div>
						<div id="partCurrentContent" class="x-hide-display">
						
						</div>
						
						</div>
					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="button" onclick="addFixEntrust();"  value="修改" />
					</td>
					
				</tr>
			</table>
		</s:form>
		<div id='tabPlaceHolder'></div>
	</body>
</html>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>

<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbFixEntrust.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingInfo.js" charset="UTF-8"></script>


<script language="javascript">
	
	Ext.onReady( function() {

		var tabs = new Ext.TabPanel( {
			renderTo : 'content',
			tabWidth : 500,
			width : 1000,
			activeTab : 0,
			frame : true,
			defaults : {
				autoHeight : true
			},
			items : [ {
				contentEl : 'fixWorkingContent',
				title : '委托书工位'
			},{
				contentEl : 'partContent',
				title : '预发材料'
			}
			
			]
		});
	});
	
	
	
	Ext.onReady(function(){
	
		var licenseCode = document.getElementById('licenseCode');
				
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbWorkingTab' , title:'修理工位',disabled:false,url:'tbWorkingInfoQueryAction.action'
				
			},
			{
				id:'tbWorkingCollectionTab' , title:'修理工位集',disabled:false,url:'tbWorkingCollectionQueryAction.action'
				
			},
			{
				id:'tbPartInfoTab' , title:'库存配件',disabled:false,url:'tabFindPartInfoAction.action'
				
			},
			{
				id:'tbBookInfo' , title:'预约信息',disabled:false,url:'tbBookQueryAction.action?licenseCode='+licenseCode.value
				
			}
			
		]);
	});
	
	
	
	function init(){
		var index = 0;
		<%
		List tbFixEntrustContentList = (List)request.getAttribute("tbFixEntrustContentList");
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0)
		{
			for(Object o : tbFixEntrustContentList){
				
				TbFixEntrustContent tbFixEntrustContent = (TbFixEntrustContent)o;
		%>
				
		
				
		var workingHourPrice = document.getElementById("workingHourPrice").value;
					
		var tbWorkingInfoTable = document.getElementById('tbWorkingInfoTable');
					
		var tbody_1 = document.createElement("tbody");
					
		var tr_1= document.createElement("tr");
			            
		var td_1_0= document.createElement("td");
		//add the balanceId 2010-12-19
		td_1_0.innerHTML="<input type='hidden' id='balanceId"+index+"' name='balanceId"+index+"' value='"+<%=tbFixEntrustContent.getBalanceId()%>+"'/><input type='hidden' id='tbFixEntrustContentId"+index+"' name='tbFixEntrustContentId"+index+"' value='"+<%=tbFixEntrustContent.getId()%>+"'/><input type='hidden' id='tbWorkingInfoId"+index+"' name='tbWorkingInfoId"+index+"' value='<%=tbFixEntrustContent.getTbWorkingInfo().getId()%>'/>";

		var td_1_1= document.createElement("td");
		            
		td_1_1.innerHTML = "<select id='freeSymbol"+index+"' name='freeSymbol"+index+"' disabled='true'><option value='1'>无</option><option value='2'>首保</option><option value='3'>索赔</option><option value='4'>返工</option><option value='5'>服务活动</option><option value='6'>保险理赔</option><option value='7'>暂不维修</option></select>";
		            
		var td_1_2= document.createElement("td");
		            
		td_1_2.innerHTML = '<%=tbFixEntrustContent.getTbWorkingInfo().getStationCode()%>';	
		            
		var td_1_3= document.createElement("td");
		            
		td_1_3.innerHTML =  '<%=tbFixEntrustContent.getTbWorkingInfo().getStationName()%>';
		            
		var td_1_4= document.createElement("td");
		            
		td_1_4.innerHTML = "<input type='text' id='fixHour"+index+"' name='fixHour"+index+"' value='<%=tbFixEntrustContent.getFixHour()%>' disabled='true' size='10'/>";
		            
		var td_1_5= document.createElement("td");
		            
		td_1_5.innerHTML = "<input type='text' id='workingHourTotal"+index+"' name='workingHourTotal"+index+"' value='<%=tbFixEntrustContent.getFixHourAll()%>' disabled='true' size='10'/>";
		            
		var td_1_6= document.createElement("td");
		            
		td_1_6.innerHTML = "<input type='text' id='sendHour"+index+"' name='sendHour"+index+"' value='<%=tbFixEntrustContent.getSendHour()%>' disabled='true' size='10'/>";
		            
		var td_1_7= document.createElement("td");
		            
		td_1_7.innerHTML = "<input type='hidden' id='fixPersonIds"+index+"' name='fixPersonIds"+index+"' value='<%=tbFixEntrustContent.getFixPersonIds()%>'/>"+"<input type='text' id='fixPersons"+index+"' name='fixPersons"+index+"' disabled='true' value='<%=tbFixEntrustContent.getFixPersons()%>'/>";
		            	
		
		var td_1_8= document.createElement("td");
	    
    	var s_8 = "";
    
    	if(''!=pt){
    	
    		s_8 = "<select id='projectType"+index+"' name='projectType"+index+"'><option value=''></option>";
    	
    		var s_8s = pt.split('#');
    	
    		for(var p = 0 ; p < s_8s.length; p++){
    		
    			if(''==s_8s[p]) continue;
    		
    			var id = s_8s[p].split("&")[0];
    		
    			var ptv = s_8s[p].split("&")[1];
    			
    			if(ptv == '<%=tbFixEntrustContent.getProjectType()%>'){
    				s_8 += "<option value=" + id + " selected='true'>" + ptv+ "</option>";
    			}else{
    		
    				s_8 += "<option value=" + id + ">" + ptv+ "</option>";
    			}
    		}
    	
    	
    		s_8 += "</select>";
    	}
    	
    	td_1_8.innerHTML = s_8;  
    	
    	var td_1_9= document.createElement("td");
	    
		//td_1_9.innerHTML = "<select id='xmlx"+index+"' name='xmlx"+index+"'><option value=''></option><option value='新车准备' <%if("新车准备".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>新车准备</option><option value='首保' <%if("首保".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>首保</option><option value='定期保养' <%if("定期保养".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>定期保养</option><option value='保修' <%if("保修".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>保修</option><option value='正常维修' <%if("正常维修".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>正常维修</option><option value='保险维修' <%if("保险维修".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>保险维修</option><option value='召回' <%if("召回".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>召回</option><option value='服务营销' <%if("服务营销".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>服务营销</option><option value='网点内部返工' <%if("网点内部返工".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>网点内部返工</option><option value='索赔帐续保' <%if("索赔帐续保".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>索赔帐续保</option><option value='新车PDI' <%if("新车PDI".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>新车PDI</option><option value='非索赔帐续保' <%if("非索赔帐续保".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>非索赔帐续保</option><option value='其他' <%if("其他".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>其他</option><option value='自理事故' <%if("自理事故".equals(tbFixEntrustContent.getXmlx())){%>selected='true'<%}%>>自理事故</option></select>";
		td_1_9.innerHTML = "<select id='wxlx"+index+"' name='wxlx"+index+"'><option value=''></option><option value='钣金' <%if("钣金".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>钣金</option><option value='油漆' <%if("油漆".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>油漆</option><option value='机修' <%if("机修".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>机修</option><option value='电修' <%if("电修".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>电修</option><option value='外包' <%if("外包".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>外包</option><option value='美容装饰' <%if("美容装饰".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>美容装饰</option><option value='新车PDI' <%if("新车PDI".equals(tbFixEntrustContent.getWxlx())){%>selected='true'<%}%>>新车PDI</option></select>";    
		
		    
		
		
		tbWorkingInfoTable.appendChild(tbody_1);
		            
		tbody_1.appendChild(tr_1);
		            
		tr_1.appendChild(td_1_0);
		            
		tr_1.appendChild(td_1_1);
		            
		tr_1.appendChild(td_1_2);
		            
		tr_1.appendChild(td_1_3);
		            
		tr_1.appendChild(td_1_4);
		            
		tr_1.appendChild(td_1_5);
		            
		tr_1.appendChild(td_1_6);
		            
		tr_1.appendChild(td_1_7);
		
		tr_1.appendChild(td_1_8);
	    
		tr_1.appendChild(td_1_9);
		            
		setReadOnly();
		
		document.getElementById('freeSymbol'+index).value = '<%=tbFixEntrustContent.getFreesymbol()%>';
		            
		var count = document.getElementById('count');
		            
		var index = count.value;
		            
		index++;
		            
		count.value = index;
		<%	
			}
		}
		%>
		
		//初始统计数据
		
		var count = document.getElementById('count');
		
		var fixHourCount = document.getElementById('fixHourCount'); 
	
		var fixHourMoneyCount = document.getElementById('fixHourMoneyCount'); 
	
		var sendHourCount = document.getElementById('sendHourCount'); 
	
		var fixHourCountValue = 0.0;
	
		var fixHourMoneyCountValue = 0.0;
	
		var sendHourCountValue = 0.0;
	
		for(var i =  0 ; i < count.value ; i++){
		
			var fixHour = document.getElementById('fixHour'+i);
		
			var workingHourTotal = document.getElementById('workingHourTotal'+i);
		
			var sendHour = document.getElementById('sendHour'+i);
		
			if(null==fixHour){
				continue;
			}
		
			fixHourCountValue += parseFloat(fixHour.value);
		
			fixHourMoneyCountValue += parseFloat(workingHourTotal.value)
		
			sendHourCountValue += parseFloat(sendHour.value)
		
		}
	
		fixHourCount.value = fixHourCountValue;
	
		fixHourMoneyCount.value = fixHourMoneyCountValue;
	
		sendHourCount.value = sendHourCountValue;
	}
	init();
	setTimeout('acquireStationTypeByCarModel()',2000);
</script>
