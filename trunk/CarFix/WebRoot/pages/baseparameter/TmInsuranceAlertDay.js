
function tmInsuranceAlertDayTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmInsuranceAlertDayTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmInsuranceAlertDayTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmInsuranceAlertDayFindAction";
	pConfig.showLoadingMsg = true;
}