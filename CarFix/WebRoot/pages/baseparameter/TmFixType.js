
function tmFixTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmFixTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmFixTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmFixTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmFixTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var fixType = document.getElementById('fixType');
	
	var errorMsg = '';
	
	if(''==trim(fixType.value))
	{
		errorMsg += '修理类型不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}
