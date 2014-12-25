
function tmCarStationTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmCarStationTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmCarStationTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmCarStationTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmCarStationTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var errorMsg = '';
	
	var stationCode = document.getElementById('stationCode');
	
	if(''==trim(stationCode.value))
	{
		
		errorMsg += '车型工位不能为空\n';
		
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}
