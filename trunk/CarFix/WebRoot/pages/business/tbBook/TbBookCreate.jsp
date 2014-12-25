<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="e3c" uri="/e3/calendar/E3Calendar.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>维修预约增加</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	</head>

	<body>
		<s:form id="formId" action="tbBookInsertAction.action">
			<table>
				<tr>
					<td>
						预约单号
					</td>
					<td>
						<s:textfield name="tbBook.bookCode" readonly="true" />
						<font color="blue">#</font>
					</td>
					<td>
						登记时间
					</td>
					<td>

						<s:textfield id="registerDate" name="tbBook.registerDate"
							/>
						<e3c:calendar for="registerDate" dataFmt="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						牌照号
					</td>
					<td>
						<s:textfield id="licenseCode" name="tbBook.licenseCode" onblur="sendCarLicenseBook();"/>
					</td>
				</tr>
				<tr>
					<td>
						底盘号
					</td>
					<td>
						<s:textfield id="chassisCode" name="tbBook.chassisCode" />
					</td>

					<td>
						车主
					</td>
					<td>
						<s:textfield id="customerName" name="tbBook.customerName" />
					</td>
					<td>
						联系人
					</td>
					<td>
						<s:textfield id="linkMan" name="tbBook.linkMan" />
					</td>
				</tr>
				<tr>
					<td>
						联系电话
					</td>
					<td>
						<s:textfield id="phone" name="tbBook.phone" />
					</td>

					<td>
						手机号码
					</td>
					<td>
						<s:textfield id="telphone" name="tbBook.telphone" />
					</td>
					<td>
						约定修理时间
					</td>
					<td>
						<s:textfield id="planFixTime" name="tbBook.planFixTime"
							/>
						<e3c:calendar for="planFixTime" dataFmt="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td>
						修理类型
					</td>
					<td>
						<s:select id="tmFixTypeId" name="tbBook.tmFixType.id"
							list="#request.tmFixTypeMap" listKey="key" listValue="value" />
					</td>

					<td>
						车型
					</td>
					<td>
						<s:select id="tmCarModelTypeId" name="tbBook.tmCarModelType.id"
							list="#request.tmCarModelTypeMap" listKey="key" listValue="value"
							emptyOption="true" onchange="acquireStationTypeByCarModel();" />
					</td>
					<td>
						服务顾问
					</td>
					<td>
						<s:select id="tmUserId" name="tbBook.tmUser.id"
							list="#request.tmUserMap" listKey="key" listValue="value"
							emptyOption="true" />
					</td>
				</tr>
				<tr>
					<td>
						维修内容
					</td>
					<td>
						<s:textarea id="fixContent" name="tbBook.fixContent" cols="20"
							rows="3">

						</s:textarea>
					</td>
					<td>
						备注说明
					</td>
					<td>
						<s:textarea id="bookRemark" name="tbBook.bookRemark" cols="20"
							rows="3">

						</s:textarea>
					</td>
				</tr>
				<tr>
					<td>
						取送车地址
					</td>
					<td>
						<s:textarea id="carAddress" name="tbBook.carAddress" cols="20"
							rows="3">

						</s:textarea>
					</td>
					<td>
						故障描述
					</td>
					<td>
						<s:textarea id="wrongDescribe" name="tbBook.wrongDescribe"
							cols="20" rows="3">

						</s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						选中工位
					
						<s:select id="tbBookFixStations"
							name="tbBook.tbBookFixStationKeys"
							ondblclick="deleteSelectedOption();"
							list="#request.tbBookFixStationMap" listKey="key"
							listValue="value" multiple="true"
							cssStyle="width:440;height:150;">

						</s:select>
					</td>
					<td colspan="2">
						 选中配件
					
						<s:select id="tbBookFixParts"
							name="tbBook.tbBookFixPartKeys"
							ondblclick="deleteSelectedPartOption();"
							list="#request.tbBookFixPartMap" listKey="key"
							listValue="value" multiple="true"
							cssStyle="width:440;height:150;">
						</s:select>
					</td>
				</tr>
				<tr>

					<td align="center" colspan="2">
						<input type="button" value="增加" onclick="submitForm();"/>
						
					</td>

				</tr>
			</table>
		</s:form>
		<div id="content">
			<div id="fixWorkingContent" class="x-hide-display">
				<s:form action="tbWorkingInfoSelectAction.action">
					<table>
						<tr>
							<td>
								<s:hidden id="tmCarStationTypeId"
									name="tbWorkingInfo.tmCarStationTypeId" />
								工位代码
							</td>
							<td>
								<s:textfield name="tbWorkingInfo.stationCode" />
							</td>
							<td>
								工位名称
							</td>
							<td>
								<s:textfield name="tbWorkingInfo.stationName" />
							</td>
							<td>
								拼音代码
							</td>
							<td>
								<s:textfield name="tbWorkingInfo.pinyinCode" />
							</td>
							<td>
								<input type="button" value="查询" onclick="tbWorkingInfoTableQuery();"/>
							</td>
						</tr>
					</table>


				</s:form>

				<e3t:table id="tbWorkingInfoTable"
					uri="tbWorkingInfoSelectAction.action" var="tbWorkingInfo"
					scope="request" items="tbWorkingInfoList" mode="ajax"
					toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
					height="300" caption="工时工位" statusVar="status">
					<e3t:column property="no_1" title="序号" sortable="false">
        				${status.globalCount}
      				</e3t:column>
					<e3t:column property="id" title="ID" hidden="true" />
					<e3t:column property="no_2" title="免费标志" sortable="false">

						<select id="freeSymbol${status.globalCount}">
							<s:iterator id="freeSymbol"
								value="#request.freeSymbolMap.keySet()">
								<option value="${freeSymbol}">
									<s:iterator id="freeSymbolShow"
										value="#request.freeSymbolMap.get(#freeSymbol)">
										${freeSymbolShow}
									</s:iterator>
								</option>
							</s:iterator>
						</select>
					</e3t:column>
					<e3t:column property="stationCode" title="工位代码" />
					<e3t:column property="stationName" title="工位名称" />
					<e3t:column property="pinyinCode" title="拼音代码" />
					<e3t:column property="workName" title="工种" />
					<e3t:column property="bodyName" title="车身部位" />
					<e3t:column property="fixHour" title="修理工时" />
					<e3t:column property="sendHour" title="派工工时" />
					<e3t:column property="tmCarStationNames" title="车型工位" />


				</e3t:table>
			</div>
			<div id="fixWorkingCollectionContent" class="x-hide-display">
				<s:form action="tbWorkingCollectionSelectAction.action">
					<table>
						<tr>
							<td>
								<s:hidden id="collection_tmCarStationTypeId" name="tbWorkingCollection.tmCarStationType.id"/>
								工位集代号
							</td>
							<td>
								<s:textfield name="tbWorkingCollection.workingCollectionCode"></s:textfield>
							</td>
							<td>
								工位集名称
							</td>
							<td>
								<s:textfield name="tbWorkingCollection.workingCollectionName"></s:textfield>
							</td>
							<td>
								<input type="button" value="查询" onclick="tbWorkingCollectionTableQuery()"/>
							</td>
						</tr>

					</table>
				</s:form>
				<e3t:table id="tbWorkingCollectionTable"
					uri="tbWorkingCollectionSelectAction.action"
					var="tbWorkingCollection" scope="request"
					items="tbWorkingCollectionList" mode="ajax"
					toolbarPosition="bottom" skin="E3002" pageSize="10" width="900"
					height="320" caption="工位集" varStatus="status">
					<e3t:column property="id" title="ID" hidden="true" />
					<e3t:column property="no_1" title="序号" sortable="false">
        				${status.globalCount}
      				</e3t:column>
      				<e3t:column property="no_2" title="免费标志" sortable="false">

						<select id="collection_freeSymbol${status.globalCount}">
							<s:iterator id="freeSymbol"
								value="#request.freeSymbolMap.keySet()">
								<option value="${freeSymbol}">
									<s:iterator id="freeSymbolShow"
										value="#request.freeSymbolMap.get(#freeSymbol)">
										${freeSymbolShow}
									</s:iterator>
								</option>
							</s:iterator>
						</select>
					</e3t:column>
					<e3t:column property="workingCollectionCode" title="工位集代号" />
					<e3t:column property="workingCollectionName" title="工位集名称" />
					<e3t:column property="tmCarStationTypeName" title="车型工位" />
					<e3t:column property="no" title="操作" sortable="false">
						
					<a href="javascript:forwardPage('${tbWorkingCollection.id}','tbWorkingCollectionForwardPageAction!forwardPage.action','viewPage',800,600);">
						<font color="blue"> 查看 </font> 
					</a>
					</e3t:column>
				</e3t:table>
			</div>
			<div id="partContent" class="x-hide-display">
				<s:form action="tbPartInfoSelectAction.action">
					<table>
						<tr>
							<td><s:hidden id="part_tmCarModelTypeId" name="tbPartInfo.tmCarModelType.id"/></td>
							<td>仓库</td>
							<td><s:select name="tbPartInfo.tmStoreHouse.id" list="#request.stroeHouseList" emptyOption="true" listKey="id" listValue="houseName"/></td>
							<td>配件名称</td>
							<td><s:textfield name="tbPartInfo.partName" /></td>
							<td>配件代码</td>
							<td><s:textfield  name="tbPartInfo.partCode" ></s:textfield></td>
							<td>拼音名称</td>
							<td><s:textfield  name="tbPartInfo.pinyinCode" ></s:textfield></td>
							<td><input type="button" value="查询" onclick="tbPartInfoTableQuery();" /></td>
						</tr>
					</table>
					
				</s:form>
				<e3t:table id="tbPartInfoTable" uri="tbPartInfoSelectAction.action" var="tbPartInfo"
					scope="request" items="tbPartInfoList" mode="ajax" 
					toolbarPosition="bottom" skin="E3002" pageSize="10" width="1100"
					height="320" varStatus="status">
						<e3t:column property="no_1" title="序号" sortable="false">
        					${status.globalCount}
      					</e3t:column>
      					<e3t:column property="id" title="ID" hidden="true"/>
      					<e3t:column property="no_2" title="免费标志" sortable="false">

							<select id="part_freeSymbol${status.globalCount}">
								<s:iterator id="freeSymbol"
									value="#request.freeSymbolMap.keySet()">
									<option value="${freeSymbol}">
										<s:iterator id="freeSymbolShow"
											value="#request.freeSymbolMap.get(#freeSymbol)">
											${freeSymbolShow}
										</s:iterator>
									</option>
								</s:iterator>
						</select>
						</e3t:column>
						<e3t:column property="no_3" title="数量" sortable="false">
							<input id="partQuantity${status.globalCount}" type="text"/>
						</e3t:column>
						<e3t:column property="no_4" title="处理方式" sortable="false">
							<select id="dealType${status.globalCount}">
								<s:iterator id="dealType"
									value="#request.dealTypeMap.keySet()">
									<option value="${dealType}">
										<s:iterator id="dealTypeShow"
											value="#request.dealTypeMap.get(#dealType)">
											${dealTypeShow}
										</s:iterator>
									</option>
								</s:iterator>
							</select>	
						</e3t:column>
						<e3t:column property="houseName" title="仓库" />
						<e3t:column property="modelName" title="车辆类型" />
						<e3t:column property="partCode" title="配件代码" />
						<e3t:column property="partName" title="配件名称" />
						<e3t:column property="pinyinCode" title="拼音代码" />
						<e3t:column property="unitName" title="计量单位" />
						<e3t:column property="typeName" title="配件类型" />
						<e3t:column property="partBroadType" title="配件大类" />
						<e3t:column width="50" property="maxStoreQuantity" title="最大库存" />
						<e3t:column width="50" property="minStoreQuantity" title="最小库存" />
						<e3t:column width="50" property="storeQuantity" title="库存数量" />
						
				</e3t:table>
			</div>
		</div>




	</body>
</html>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/business/tbBook/TbBook.js" charset="UTF-8"></script>
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
				title : '修理工位'
			}, {
				contentEl : 'fixWorkingCollectionContent',
				title : '修理工位集'
			}, {
				contentEl : 'partContent',
				title : '修理配件'
			} ]
		});
	});
	
</script>