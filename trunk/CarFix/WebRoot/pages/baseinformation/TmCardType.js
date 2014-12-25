
function tmCardTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmCardTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmCardTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmCardTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmCardTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var cardDesc = document.getElementById('cardDesc');
	
	var errorMsg = '';
	
	if(''==trim(cardDesc.value))
	{
		errorMsg += '卡种不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}
