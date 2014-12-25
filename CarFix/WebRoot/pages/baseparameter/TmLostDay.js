
function tmLostDayTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmLostDayTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmLostDayTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmLostDayFindAction";
	pConfig.showLoadingMsg = true;
}