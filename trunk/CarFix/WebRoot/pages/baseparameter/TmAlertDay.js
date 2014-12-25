
function tmAlertDayTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmAlertDayTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmAlertDayTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmAlertDayFindAction";
	pConfig.showLoadingMsg = true;
}