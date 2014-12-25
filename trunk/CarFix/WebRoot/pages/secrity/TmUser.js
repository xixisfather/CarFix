
function tmUserTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmUserForwardPageAction!forwardPage.action',800,450);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmUserTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmUserTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmUserFindAction";
	pConfig.showLoadingMsg = true;
}
