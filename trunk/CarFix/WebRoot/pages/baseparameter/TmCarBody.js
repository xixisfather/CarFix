
function tmCarBodyTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmCarBodyForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmCarBodyTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmCarBodyTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmCarBodyFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var bodyName = document.getElementById('bodyName');
	
	var errorMsg = '';
	
	if(''==trim(bodyName.value))
	{
		errorMsg += '部位名称不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}
