
function tmStoreDiskTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmStoreDiskTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmStoreDiskTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmStoreDiskFindAction";
	pConfig.showLoadingMsg = true;
}