<%@page import="com.selfsoft.framework.common.Constants"%>
<%@page import="com.selfsoft.baseparameter.model.TmProjectType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修理委托书增加</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
		<script language="javascript">
		document.onkeydown = check;
		function check(e) {
		    var code;
		    if (!e) var e = window.event;
		    if (e.keyCode) code = e.keyCode;
		    else if (e.which) code = e.which;
		    if (((event.keyCode == 8) &&                                                    //BackSpace 
		         ((event.srcElement.type != "text" && 
		         event.srcElement.type != "textarea" && 
		         event.srcElement.type != "password") || 
		         event.srcElement.readOnly == true)) || 
		        ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||    //CtrlN,CtrlR 
		        (event.keyCode == 116) ) {                                                   //F5 
		        event.keyCode = 0; 
		        event.returnValue = false; 
		    }
		    return true;
		}
		
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
	<% 
	
			
	      Map<String,String> companyMap = Constants.getCompanyMap();
	
	      if(companyMap.get("xtlName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	
	    	  request.setAttribute("comName", "xtl");
	    	  
	      }
	      
	      else if(companyMap.get("dfbzName").equals(request.getSession().getAttribute("companyNameMain"))){
	    	  
	    	  request.setAttribute("comName", "dfbz");
	    	  
	      }
	      
	      else{
	    	  
	    	  request.setAttribute("comName", "other");
	      }
	      
	   // request.setAttribute("comName", "dfbz");
	    
	    %>
		<s:form action="tbFixEntrustInsertAction.action">
			<table>
				<tr>
					<td>
						<s:hidden name="tbFixEntrust.isvalid" value="1"></s:hidden>
						<s:hidden name="tbFixEntrust.isFinish" value="0"></s:hidden>
						<s:hidden name="tbFixEntrust.entrustStatus" value="0"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>委托书号</td>
					<td>
						<s:textfield name="tbFixEntrust.entrustCode" readonly="true" size="10"/>
						<font color="blue">#</font>
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
						<s:textfield id="userName" name="tbFixEntrust.tmUser.userRealName" value="%{#session.tmUser.userRealName}" readonly="true" size="10"></s:textfield>
						<s:hidden id="userId" name="tbFixEntrust.tmUser.id" value="%{#session.tmUser.id}"></s:hidden>
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
					<td>提醒保险日期</td>
					<td>
						<s:textfield id="remindInsuranceDate" name="tbFixEntrust.remindInsuranceDate" size="10"></s:textfield>
						<e3c:calendar for="remindInsuranceDate" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				<c:choose>
				<c:when test="${request.comName=='dfbz'}">
				<tr>
					<td>备件组织号</td>
				    <td><s:textfield id="bjzzh" name="tbFixEntrust.bjzzh" size="10"></s:textfield></td>
				    <td>首保日期</td>
					<td>
						<s:textfield id="sbrq" name="tbFixEntrust.sbrq" size="10"></s:textfield>
						<e3c:calendar for="sbrq" dataFmt="yyyy-MM-dd"/>
					</td>
				</tr>
				</c:when>
				</c:choose>
				
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
								
								<td width="70">
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
									<input type="text" id="fixHourCount" disabled="true" value="0" size='10'/>
								</td>
								<td width="70">
									<input type="text" id="fixHourMoneyCount" disabled="true" value="0" size='10'/>
								</td>
								<td width="60">
									<input type="text" id="sendHourCount" disabled="true" value="0" size='10'/>
								</td>
								<td width="60">
									
								</td>
							</tr>
							
						</table>
						</div>
						<s:hidden id="partCol" ></s:hidden>
						<s:hidden id="totalPrice" ></s:hidden>
						<div id="partContent" class="x-hide-display">
							<table id="mainTable"  >
								<tr>
									<th width="100" >仓库</th>
									<th width="120">配件名称</th>
									<th width="70">单位</th>
									<th width="100">发料单价</th>
									<th width="100">数量</th>
									<th width="100">小计</th>
									<th width="100" align="center" >操作</th>
								</tr>
							</table>
						</div>
						<div id="partCurrentContent" class="x-hide-display">
						
						</div>
						</div>
					</td>
				</tr>
				<tr>

					<td align="center" colspan="8">
						
						<input type="button" onclick="addFixEntrust();" value="确定" />
						
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>
						
						&nbsp;&nbsp;
						<input type="button" value="退出" onclick="closeWindow();"/>
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

<%if("xtl".equals(String.valueOf(request.getAttribute("comName")))||"bfbz".equals(String.valueOf(request.getAttribute("comName")))){%>
	
<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingInfoCD.js" charset="UTF-8"></script>
<%}else{%>
<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbWorkingInfo.js" charset="UTF-8"></script>
<%}%>
<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbFixEntrust/TbBespokerPartConent.js" charset="UTF-8"></script>

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
			}, {
				contentEl : 'partContent',
				title : '预发配件'
			}
			 ]
		});
	});
	
	
	Ext.onReady(function(){
			
		TabPanel.create('tabPlaceHolder',350,
		[
			{
				id:'tbWorkingTab' , title:'修理工位',disabled:false,url:'tbWorkingInfoQueryAction.action'
				
			},
			{
				id:'tbWorkingCollectionTab' , title:'修理工位集',disabled:false,url:'tbWorkingCollectionQueryAction.action'
				
			},
			{
				id:'tbPartInfoTab' , title:'当前库存',disabled:false,url:'tabFindPartInfoAction.action'
				
			},
			{
				id:'tbBookInfo' , title:'预约信息',disabled:false,url:'tbBookQueryAction.action'
				
			}
			
		]);
	});
	
</script>
