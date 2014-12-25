
function tmCarModelTypeTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmCarModelTypeForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '导入',
		iconCls : 'editIcon',
		handler : function() {
			
			var date = new Date();
		
			var time = date.getTime();
		
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
	
			addObject('tmCarModelTypeForwardPageAction!forwardPage.action?&flag=importXlsPage'+'&timeId='+time,600,300);
	
		
		}
	}
	
	, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmCarModelTypeTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmCarModelTypeTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmCarModelTypeFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var modelCode = document.getElementById('modelCode');
	
	var modelName = document.getElementById('modelName');
	
	var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
	
	var tmWorkingHourPriceId = document.getElementById('tmWorkingHourPriceId');
	
	var errorMsg = '';
	
	if(''==trim(modelCode.value))
	{
		
		errorMsg += '车型代码不能为空\n';
		
	}
	
	if(''==trim(modelName.value))
	{
		
		errorMsg += '车型名称不能为空\n';
		
	}
	
	if(''==trim(tmCarStationTypeId.value))
	{
		
		errorMsg += '车型工位标志不能为空\n';
		
	}
	
	if(''==trim(tmWorkingHourPriceId.value))
	{
		
		errorMsg += '工时单价不能为空\n';
		
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}
