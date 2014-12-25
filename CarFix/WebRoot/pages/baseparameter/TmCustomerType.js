
function tmCustomerTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmCustomerTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmCustomerTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmCustomerTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmCustomerTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var typeName = document.getElementById('typeName');
	
	var errorMsg = '';
	
	if(''==trim(typeName.value))
	{
		errorMsg += '客户类型不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}