function tbWorkingInfoTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			
			var date = new Date();
		
			var time = date.getTime();
		
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
			
			addObject('tbWorkingInfoForwardPageAction!forwardPage.action?tmCarStationTypeId='+tmCarStationTypeId.value+'&timeId='+time,600,300);
		}
	}, '', '-', '', 
	{	
		text : '导入',
		iconCls : 'editIcon',
		handler : function() {
		
			var date = new Date();
		
			var time = date.getTime();
			
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
		
			addObject('tbWorkingInfoForwardPageAction!forwardPage.action?tmCarStationTypeId='+tmCarStationTypeId.value+'&flag=importXlsPage'+'&timeId='+time,600,300);
		
		}
	}, '', '-', '',
	{	
		text : '导出',
		iconCls : 'viewIcon',
		handler : function() {
			
			var date = new Date();
			
			var time = date.getTime();
			
			window.open('tbWorkingInfoExportXlsAction.action?timeId='+time,'_blank');
		}
	}, '', '-', '',
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbWorkingInfoTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbWorkingInfoTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbWorkingInfoFindAction";
	pConfig.showLoadingMsg = true;
}

//根据工位代号获取工位信息
function sendStationCode(componentStationCodeId){
	var date = new Date();
	var time = date.getTime();
	var stationCode = document.getElementById(componentStationCodeId);
	var url = 'tbWorkingInfoFindByStationCodeAction.action?stationCode=' + stationCode.value + '&time=' + time;
	var stationCodeAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireTmWorkingInfo,

		asynchronous : true

	});
}

function acquireTmWorkingInfo(originalRequest){
	
	var tmWorkingInfo = originalRequest.responseText.split(',');
	
	if(null!=tmWorkingInfo&&tmWorkingInfo.length>1){
		
		var stationName = document.getElementById('stationName');
		
		stationName.value = tmWorkingInfo[0];
		
		var tmWorkTypeId = document.getElementById('tmWorkTypeId');
		
		tmWorkTypeId.value = tmWorkingInfo[1];
		
		var pinyinCode = document.getElementById('pinyinCode'); 
		
		pinyinCode.value = tmWorkingInfo[2];
		
		var tmCarBodyId = document.getElementById('tmCarBodyId'); 
		
		tmCarBodyId.value = tmWorkingInfo[3];
		
		var fixHour = document.getElementById('fixHour'); 
		
		fixHour.value = tmWorkingInfo[4]; 
		
		var sendHour = document.getElementById('sendHour'); 
		
		sendHour.value = tmWorkingInfo[5]; 
	}
}

//根据工位代码与车型工位验证是否已经存在
function sendStationCodeAndCarTypeId(componentStationCodeId,componentCarStationTypeId){
	var date = new Date();
	var time = date.getTime();
	var stationCode = document.getElementById(componentStationCodeId);
	var carStationTypeId = document.getElementById(componentCarStationTypeId);
	var url = 'tbWorkingInfoFindByStationCodeAndCarStationTypeIdAction.action'+ '?stationCode=' + stationCode.value + '&carStationTypeId='+ carStationTypeId.value +'&time=' + time;
	var existAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireIsTmWorkingInfoExist,

		asynchronous : true

	});
}

function acquireIsTmWorkingInfoExist(originalRequest){
	
	var tmWorkingInfo = originalRequest.responseText;
	
	if(null!=tmWorkingInfo&&''!=tmWorkingInfo){
		
		Ext.MessageBox.alert('信息', tmWorkingInfo);
		
	}
	else{
		
		var formId = document.getElementById('formId');
		
		if(!formValidate())
		{
			return ;
		}
		formId.submit();
	}
}

function formValidate()
{
	var stationCode = document.getElementById('stationCode');
	
	var stationName = document.getElementById('stationName');
	
	var fixHour = document.getElementById('fixHour');
	
	var sendHour = document.getElementById('sendHour');
	
	var errorMsg = '';
	
	if(''==trim(stationCode.value))
	{
		errorMsg += '工位代码不能为空\n';
	}
	if(''==trim(stationName.value))
	{
		errorMsg += '工位名称不能为空\n';
	}
	if(!validateNoPositveFloatNum(fixHour.value))
	{
		errorMsg += '修理工时输入不正确\n';
	}
	if(!validateNoPositveFloatNum(sendHour.value))
	{
		errorMsg += '派工工时输入不正确\n';
	}
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}
