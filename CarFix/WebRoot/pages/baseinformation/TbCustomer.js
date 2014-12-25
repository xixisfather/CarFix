function tbCustomerTableConfigHandler(pConfig) {

	pConfig.tbar = [

			{
				text : '创建客户',
				iconCls : 'addIcon',
				handler : function() {
					addObject('tbCustomerForwardPageAction!forwardPage.action',
							600, 300);
				}
			}, '', '-', '', 
			
			{
				text : '导入',
				iconCls : 'editIcon',
				handler : function() {
					
					var date = new Date();
				
					var time = date.getTime();
				
					addObject('tbCustomerForwardPageAction!forwardPage.action?flag=importXlsPage1'+'&timeId='+time,600, 300);
				}
			}, '', '-', '',
			{	
				text : '导出',
				iconCls : 'viewIcon',
				handler : function() {
					
					var date = new Date();
					
					var time = date.getTime();
					
					window.open('tbCustomerExportXlsAction.action?timeId='+time,'_blank');
				}
			}, '', '-', '', 
			
			{
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbCustomerTable();
				}
			} ];

	// pConfig.autoExpandColumn='no';
}

function tbCustomerTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbCustomerFindAction";
	pConfig.showLoadingMsg = true;
}

// 表格显示前,通常在这注册单击，双击事件
function tbCustomerTableRenderBeforeHandler(pGrid) {
	pGrid.on("rowclick", function(pGrid, pRowIndex, pEventObj) {
		var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
			var e3CustomerCode = record.get("customerCode");// 获取单元格值
			var e3CustomerName = record.get("customerName");// 获取单元格值
			var customerCode = document.getElementById('customerCode');
			var customerName = document.getElementById('customerName');
			customerCode.value = e3CustomerCode;
			customerName.value = e3CustomerName;
			refresh_tbCarInfoTable();
		});

}

// 根据客户号来获取用户信息
function pickTbCustomer() {
	var customerCode = document.getElementById('tbCustomerCode');
	var actionUrl = 'tbCustomerPickAction.action';
	var date = new Date();
	var time = date.getTime();
	var url = actionUrl + '?customerCode=' + customerCode.value + '&time=' + time;
	var deleteAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : getTbCustomerInfo,

		asynchronous : true

	});

}
//获取客户信息
function getTbCustomerInfo(originalRequest) {

	var tbCustomerInfo = originalRequest.responseText.split(',');

	if('success'==tbCustomerInfo[0])
	{
		var tbCustomerId = document.getElementById('tbCustomerId');
		
		var tbCustomerCode = document.getElementById('tbCustomerCode');
		
		var tbCustomerName = document.getElementById('tbCustomerName');
		
		tbCustomerId.value =tbCustomerInfo[1];
		
		tbCustomerCode.value = tbCustomerInfo[2];
		
		tbCustomerName.value = tbCustomerInfo[3];
	}
	else
	{
		Ext.MessageBox.alert('信息', '客户号不存在,请重新输入');
	}
}

//车辆过户值设置
function transferCar(){
	
	var tbCustomerId = document.getElementById('tbCustomerId');
	
	var tbCustomerCode = document.getElementById('tbCustomerCode');
	
	var tbCustomerName = document.getElementById('tbCustomerName');
	
	var parentCustomerCode = parent.document.getElementById("tbCarInfoUpdateAction_tbCarInfo_tbCustomer_customerCode");
	
	var parentCustomerId = parent.document.getElementById("tbCarInfoUpdateAction_tbCarInfo_tbCustomer_id");
	
	var parentCustomerName = parent.document.getElementById("tbCarInfoUpdateAction_tbCarInfo_tbCustomer_customerName");
	
	parentCustomerCode.value = tbCustomerCode.value;
	
	parentCustomerId.value = tbCustomerId.value;
	
	parentCustomerName.value = tbCustomerName.value;
	parent.win.hide();
}

function customerFormValidate()
{
	
	var customerName = document.getElementById('customerName');
	
	var pinyinCode = document.getElementById('pinyinCode');
	
	var telephone = document.getElementById('telephone');
	
	var contractPerson = document.getElementById('contractPerson');
	
	var errorMsg = '';
	
	if(''==trim(customerName.value))
	{
		errorMsg += '客户名称不能为空\n';
	}
	if(''==trim(pinyinCode.value))
	{
		errorMsg += '拼音代码不能为空\n';
	}
	
	
	
	if(''==trim(contractPerson.value))
	{
		errorMsg += '联系人不能为空\n';
	}
	
	if(''==trim(telephone.value))
	{
		errorMsg += '手机号码不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}