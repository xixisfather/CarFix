
function tmPartTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmPartTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmPartTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmPartTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmPartTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var typeCode = document.getElementById('typeCode');
	
	var typeName = document.getElementById('typeName');
	
	var errorMsg = '';
	
	if(''==trim(typeCode.value))
	{
		errorMsg += '类型代码不能为空\n';
	}
	
	if(''==trim(typeName.value))
	{
		errorMsg += '类型名称不能为空\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}
