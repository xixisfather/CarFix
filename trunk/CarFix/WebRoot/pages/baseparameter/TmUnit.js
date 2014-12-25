
function tmUnitTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmUnitForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmUnitTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmUnitTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmUnitFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var unitName = document.getElementById('unitName');
	
	var errorMsg = '';
	
	if(''==trim(unitName.value))
	{
		errorMsg += '单位名称不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}