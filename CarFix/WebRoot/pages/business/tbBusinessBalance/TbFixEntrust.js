
function tbFixEntrustTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbFixEntrustTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustChooseAction";
	pConfig.showLoadingMsg = true;
}



