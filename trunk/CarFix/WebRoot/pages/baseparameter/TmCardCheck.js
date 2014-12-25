
function tmCardCheckTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmCardCheckForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmCardCheckTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmCardCheckTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmCardCheckFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var cardNo = document.getElementById('cardNo');
	
	var errorMsg = '';
	
	if(''==trim(cardNo.value))
	{
		errorMsg += '内码不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}