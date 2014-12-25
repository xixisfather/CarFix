
function tmMaintainAlertDayTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmMaintainAlertDayTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmMaintainAlertDayTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmMaintainAlertDayFindAction";
	pConfig.showLoadingMsg = true;
}