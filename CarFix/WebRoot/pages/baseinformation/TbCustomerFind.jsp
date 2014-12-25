<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>客户信息查询</title>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCustomer.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/pages/baseinformation/TbCarInfo.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	</head>

	<body>
		<s:form action="tbCustomerFindAction.action">
			<table>
				<tr>
					<td>
						客户号
					</td>
					<td>	
						<s:textfield name="tbCustomer.customerCode"/>
					</td>
					<td>
						客户姓名
					</td>
					<td>	
						<s:textfield name="tbCustomer.customerName"/>
					</td>
					<td>
						客户拼音
					</td>
					<td>	
						<s:textfield name="tbCustomer.pinyinCode"/>
					</td>
				</tr>
				
				<tr>
					<td>
						手机号码
					</td>
					<td>	
						<s:textfield name="tbCustomer.telephone"/>
					</td>
					<td>
						客户类别
					</td>
					<td>
						<s:select name="tbCustomer.customerProperty" list="#request.customerPropertyMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
					<td>
						客户类型
					</td>
					<td>
						<s:select name="tbCustomer.tmCustomerType.id" list="#request.customerTypeMap" emptyOption="true" listKey="key" listValue="value"/>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="button" value="查询"
							onclick="tbCustomerTableQuery();" />
						&nbsp;&nbsp;
						<input type="reset" value="重置"/>			
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbCustomerTable" uri="tbCustomerFindAction.action" var="tbCustomer"
			scope="request" items="tbCustomerList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="200" caption="客户信息">
			<e3t:column property="no" title="操作" sortable="false" width="150">
				<a href="javascript:editObject('${tbCustomer.id}','tbCustomerForwardPageAction!forwardPage.action',600,300);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a href="javascript:deleteObject('${tbCustomer.id}','tbCustomerDeleteAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
				 &nbsp;&nbsp;
				
				<a href="javascript:forwardPage('${tbCustomer.id}','tbCustomerForwardPageAction!forwardPage.action','addCar',800,500);">
					<font color="blue">
						添加车辆
				    </font>
				</a>
			</e3t:column>	
			<e3t:column property="id" title="id" hidden="true"/>
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户名称"/>
			<e3t:column property="pinyinCode" title="拼音代码"/>
			<e3t:column property="phone" title="固定电话"/>
			<e3t:column property="telephone" title="手机号码"/>
			<e3t:column property="address" title="联系地址"/>
			<e3t:column property="zipCode" title="邮政编码"/>
			<e3t:column property="companyAccountCode" title="单位帐号"/>
			<e3t:column property="companyTaxCode" title="单位税号"/>
			<e3t:column property="lawPresent" title="法人代表"/>
			<e3t:column property="contractPerson" title="联系人"/>
			<e3t:column property="customerPropertyShow" title="客户类别"/>
			<e3t:column property="customerTypeShow" title="客户类型"/>
			<e3t:column property="soleTypeShow" title="销售类型"/>
			
			
			
		</e3t:table>
		
		<s:form action="tbCarInfoFindAction.action">
			<table>
				<tr>
					<td>客户号</td>
					<td>
						<s:textfield id="customerCode" name="tbCarInfo.tbCustomer.customerCode"/>
					</td>
					<td>客户名称</td>
					<td><s:textfield id="customerName" name="tbCarInfo.tbCustomer.customerName"/></td>
					<td>车牌号</td>
					<td><s:textfield name="tbCarInfo.licenseCode"/></td>
				</tr>
				<tr>
					<td>底盘号</td>
					<td><s:textfield name="tbCarInfo.chassisCode"/></td>
					<td>车型</td>
					<td><s:select name="tbCarInfo.tmCarModelType.id" list="#request.tmCarModelTypeMap" emptyOption="true" listKey="key" listValue="value"/></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="查询"
							onclick="tbCarInfoTableQuery();" />
					</td>
				</tr>
			</table>
		</s:form>
		<e3t:table id="tbCarInfoTable" uri="tbCarInfoFindAction.action" var="tbCarInfo"
			scope="request" items="tbCarInfoList" mode="ajax"
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="200" caption="车辆信息">
			<e3t:column property="no" title="操作" sortable="false">
				<a href="javascript:editObject('${tbCarInfo.id}','tbCarInfoForwardPageAction!forwardPage.action',800,500);">
					<font color="blue">
						修改
					</font>
				</a>
				  &nbsp;&nbsp;
  				<a
					href="javascript:deleteObject('${tbCarInfo.id}','tbCarInfoDeleteAction.action');">
					<font color="blue">
						删除
				    </font>
				</a>
			</e3t:column>
			<e3t:column property="customerCode" title="客户号" />
			<e3t:column property="customerName" title="客户名称" />
			<e3t:column property="licenseCode" title="牌照号" />
			<e3t:column property="chassisCode" title="底盘号" />
			<e3t:column property="insureCardCode" title="保修卡号" />
			<e3t:column property="factory" title="厂商" />
			<e3t:column property="modelTypeName" title="车型" />
			<e3t:column property="color" title="颜色" />
			<e3t:column property="engineCode" title="发动机号" />
			<e3t:column property="engineType" title="发动机型号" />
			<e3t:column property="changeBoxType" title="变速箱型号" />
			<e3t:column property="purchaseDate" title="购车日期" />
			<e3t:column property="productDate" title="生产日期" />
			<e3t:column property="insureDeadlineDate" title="保险到期" />
			<e3t:column property="kilo" title="公里数" />
			<e3t:column property="yearCheckDeadline" title="年检到期" />
			<e3t:column property="firstWrongKilo" title="首次故障里程" />
			<e3t:column property="soleCompany" title="经销单位" />
			<e3t:column property="middleFactory" title="中桥生产厂" />
			<e3t:column property="backFactory" title="后桥生产厂" />
			<e3t:column property="hasInsuredShow" title="是否做过首保" />
			<e3t:column property="hasCliamShow" title="是否做过索赔" />
			<e3t:column property="hasFixedShow" title="是否做过修理" />
			<e3t:column property="carPropertyShow" title="车辆性质" />
			<e3t:column property="carUsedShow" title="车辆用途" />
			

		</e3t:table>
		
		
	</body>
</html>
