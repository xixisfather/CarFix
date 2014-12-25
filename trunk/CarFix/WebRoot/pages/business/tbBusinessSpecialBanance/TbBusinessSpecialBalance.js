
function tbBusinessSpecialBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbBusinessBalanceSelectAction.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBusinessSpecialBalanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessSpecialBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessSpecialBalanceFindAction";
	pConfig.showLoadingMsg = true;
}