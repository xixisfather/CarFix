
function tbFixEntrustCostTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '录入成本',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbFixEntrustCostForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbFixEntrustCostTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustCostTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustCostFindAction";
	pConfig.showLoadingMsg = true;
}