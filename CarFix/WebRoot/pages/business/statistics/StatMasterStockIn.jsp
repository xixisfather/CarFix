<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="e3c" uri="/e3/calendar/E3Calendar.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>采购入库查询</title>
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
		function stockMasterTableConfigHandler(pConfig) {
			
			// pConfig.autoExpandColumn='no';
		}
		
		function stockMasterTableE3ConfigHandler(pConfig) {
			pConfig.emptyReload = false;
			// 参数form,pConfig指定form的参数会提交到后台
			pConfig.form = "stockInMasterBillByStatAction";
			pConfig.showLoadingMsg = true;
		}
		
		function udpatePayMent(id,payId, actionUrl) {

			Ext.MessageBox.confirm('信息', '您确定要更新状态？', function(btn) {
				if (btn == 'yes') {
		
					var date = new Date();
					var time = date.getTime();
					var url = actionUrl + '?id=' + id + '&time=' + time + '&payMent='+payId;
					var deleteAjax = new Ajax.Request(url, {
						method : 'post',
		
						onComplete : getPanMentInfo,
		
						asynchronous : true
		
					});
				}
		
			});
		
		}
		
		function getPanMentInfo(originalRequest) {
		
			var info = originalRequest.responseText.split(',');
		
			if (info[1] == 'success') {
				//Ext.MessageBox.alert('信息', '删除成功');
			} else if (info[1] == 'failure') {
				Ext.MessageBox.alert('信息', '更新失败,操作异常或数据已被引用');
			} else {
				Ext.MessageBox.alert('信息', '更新失败,操作异常或数据已被引用');
			}
			eval('refresh_' + info[0] + '()');
		}
		
		function detailSubmit(){
			document.forms["stockInMasterBillByStatAction"].submit();
		}
	</script>
	<body>
		<s:form action="stockInMasterBillByStatAction.action" >
			<s:hidden name="tmStockIn.partCode" id="iframe_partCode" ></s:hidden>
			<s:hidden name="tmStockIn.beginDate" id="iframe_beginDate" ></s:hidden>
			<s:hidden name="tmStockIn.endDate" id="iframe_endDate" ></s:hidden>
			<s:hidden name="tmStockIn.supplierId" id="iframe_supplierId" ></s:hidden>
			<s:hidden name="tmStockIn.partName" id="iframe_partName" ></s:hidden>
			<s:hidden name="tmStockIn.busType" id="iframe_busType" ></s:hidden>
		</s:form>
		<e3t:table id="stockMasterTable" uri="stockInMasterBillByStatAction.action" var="stockMaster"
			scope="request" items="stockDetailList" mode="ajax" 
			toolbarPosition="bottom" skin="E3002" pageSize="10" width="1000"
			height="320" >
			<s:if test="#request.tmStockIn.busType == 1">
				<e3t:column property="stockInCode" title="入库单号" />
				<e3t:column property="arriveDate" title="入库日期" >
					<fmt:formatDate pattern="yyyy-MM-dd" value="${stockMaster.arriveDate}"  />
				</e3t:column>
				<e3t:column property="payMentShow" title="支付方式" />
				<e3t:column property="supplierCode" title="供应商号" />
				<e3t:column property="supplierName" title="供应商名" />
				<e3t:column property="totalPrice" title="供货金额" />
				<e3t:column property="no" title="更改支付方式" sortable="false">
					<a href="javascript:udpatePayMent('${stockMaster.stockInId}','201','updateStockPanMentAction.action');">
						<font color="blue">
							现金
						</font>
					</a>
					  &nbsp;&nbsp;
	  				<a
						href="javascript:udpatePayMent('${stockMaster.stockInId}','202','updateStockPanMentAction.action');">
						<font color="blue">
							汇款
					    </font>
					</a>
					  &nbsp;&nbsp;
	  				<a
						href="javascript:udpatePayMent('${stockMaster.stockInId}','203','updateStockPanMentAction.action');">
						<font color="blue">
							挂账
					    </font>
					</a>
					  &nbsp;&nbsp;
	  				<a
						href="javascript:editObject('${stockMaster.stockInId}','viewStockInAction.action',500,500)">
						<font color="blue">
							查看明细
					    </font>
					</a>
				</e3t:column>
			</s:if>
			
			
			<s:if test="#request.tmStockIn.busType == 2">
				<e3t:column property="stockOutCode" title="出库单号" />
				<e3t:column property="stockOutDate" title="退货日期" >
					<fmt:formatDate pattern="yyyy-MM-dd" value="${stockMaster.stockOutDate}"  />
				</e3t:column>
				<e3t:column property="userRealName" title="供应商号" />
				<e3t:column property="customerName" title="供应商名" />
				<e3t:column property="totalPrice" title="退货金额" />
			</s:if>
			<s:if test="#request.tmStockIn.busType == 3">
				<e3t:column property="stockInCode" title="入库单号" />
				<e3t:column property="arriveDate" title="入库日期" >
					<fmt:formatDate pattern="yyyy-MM-dd" value="${stockMaster.arriveDate}"  />
				</e3t:column>
				<e3t:column property="payMentShow" title="支付方式" />
				<e3t:column property="supplierCode" title="供应商号" />
				<e3t:column property="supplierName" title="供应商名" />
				<e3t:column property="totalPrice" title="供货金额" />
				<e3t:column property="no" title="更改支付方式" sortable="false">
				<c:if test="${stockMaster.payMent != null}">
						<a href="javascript:udpatePayMent('${stockMaster.stockInId}','201','updateStockPanMentAction.action');">
							<font color="blue">
								现金
							</font>
						</a>
						  &nbsp;&nbsp;
		  				<a
							href="javascript:udpatePayMent('${stockMaster.stockInId}','202','updateStockPanMentAction.action');">
							<font color="blue">
								汇款
						    </font>
						</a>
						  &nbsp;&nbsp;
		  				<a
							href="javascript:udpatePayMent('${stockMaster.stockInId}','203','updateStockPanMentAction.action');">
							<font color="blue">
								挂账
						    </font>
						</a>
						  &nbsp;&nbsp;
		  				<a
							href="javascript:editObject('${stockMaster.stockInId}','viewStockInAction.action',500,500)">
							<font color="blue">
								查看明细
						    </font>
						</a>
					</c:if>
				</e3t:column>
			</s:if>
		</e3t:table>
		
	
		
	</body> 
</html>
