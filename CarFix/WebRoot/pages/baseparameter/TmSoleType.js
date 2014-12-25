
function tmSoleTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmSoleTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmSoleTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmSoleTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmSoleTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var soleName = document.getElementById('soleName');
	
	var errorMsg = '';
	
	if(''==trim(soleName.value))
	{
		errorMsg += '售价名称不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}

