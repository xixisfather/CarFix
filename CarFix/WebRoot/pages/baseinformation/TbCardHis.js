function tbCardHisTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbCardHisTable();
		}
	} 
	, '', '-', '', 
	{
		text : '明细打印预览',
		iconCls : 'editIcon',
		handler : function() {
			addObject('tbCardHisPrintAction.action',600,300);
		}
	} 
	
	];

	// pConfig.autoExpandColumn='no';
}

function tbCardHisTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbCardHisFindAction";
	pConfig.showLoadingMsg = true;
}

function openWin() {
	var props = "customerId,customerCode,customerName";
	showCommonWin('findAllTmTbCustomerAction.action', '客户列表', 650, 350, props,
			"tbCustomerTable");
}