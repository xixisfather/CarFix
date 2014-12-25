
function tmStoreHouseTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmStoreHouseForwardPageAction!forwardPage.action',700,500);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmStoreHouseTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmStoreHouseTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmStoreHouseFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var houseCode = document.getElementById('houseCode');
	
	var houseName = document.getElementById('houseName');
	
	var errorMsg = '';
	
	if(''==trim(houseCode.value))
	{
		errorMsg += '仓库代码不能为空\n';
	}
	
	if(''==trim(houseName.value))
	{
		errorMsg += '仓库名称不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}

