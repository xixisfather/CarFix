
function tbWorkingCollectionTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			
			var date = new Date();
		
			var time = date.getTime();
		
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
			
			addObject('tbWorkingCollectionForwardPageAction!forwardPage.action?tmCarStationTypeId='+tmCarStationTypeId.value+'&timeId='+time,800,600);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbWorkingCollectionTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbWorkingCollectionTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbWorkingCollectionQueryAction";
	pConfig.showLoadingMsg = true;
}


//根据工位集代码与车型工位验证是否已经存在
function sendWorkingCollectCodeAndTmCarStationTypeId(componentWorkingCollectCode,componentTmCarStationTypeId){
	var date = new Date();
	var time = date.getTime();
	var workingCollectCode = document.getElementById(componentWorkingCollectCode);
	var tmCarStationTypeId = document.getElementById(componentTmCarStationTypeId);
	var url = 'tbWorkingCollectionFindByWorkingCollectionCodeAndTmCarStationTypeIdAction.action'+ '?workingCollectionCode=' + workingCollectCode.value + '&tmCarStationTypeId='+ tmCarStationTypeId.value +'&time=' + time;
	var existAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireIsTbWorkingCollectionExist,

		asynchronous : true

	});
}

function acquireIsTbWorkingCollectionExist(originalRequest){
	
	var existInfo = originalRequest.responseText;
	
	if(null!=existInfo&&''!=existInfo){
		
		if('edit'!=flag)
		{
			Ext.MessageBox.alert('信息', existInfo);
		}
		else
		{
			if(workingCollectionCodeCopy.value == workingCollectionCode.value)
			{
				formSubmit();
			}
			else
			{
				Ext.MessageBox.alert('信息', existInfo);
			}
		}
		
		
	}
	else{
		
		formSubmit();
	}
}

//根据车型查找车型工位信息
function acquireStationTypeByCarModel() {

	var date = new Date();

	var time = date.getTime();

	var tmCarModelTypeId = parent.document.getElementById('tmCarModelTypeId');
	
	if ('' != tmCarModelTypeId.value) {

		var url = 'tmCarStationTypeFindByCarModelIdAction.action?tmCarModelTypeId='
				+ tmCarModelTypeId.value + '&time=' + time;

		var carModelAjax = new Ajax.Request(url, {

			method : 'post',

			onComplete : acquireTmCarStationTypeInfo,

			asynchronous : true

		});

	}

}
// 根据车型获取车型工位信息，并设置到E3的查询条件中
function acquireTmCarStationTypeInfo(originalRequest) {
	
	var tmCarStationTypeInfo = originalRequest.responseText.split(',');

	var tmCarStationTypeId_gwj = document.getElementById('tmCarStationTypeId');
	
	var tmCarStationTypeIdSend_gwj = document.getElementById('tmCarStationTypeIdSend');
	
	if ('success' == tmCarStationTypeInfo[0]) {
		tmCarStationTypeId_gwj.value = tmCarStationTypeInfo[1];
		tmCarStationTypeIdSend_gwj.value = tmCarStationTypeInfo[1];
		refresh_tbWorkingCollectionTable();
	} else {
		tmCarStationTypeId_gwj.value ='';
		tmCarStationTypeIdSend_gwj.value = '';
		refresh_tbWorkingCollectionTable();
	}
}


function findTbWorkingCollectionDetail(tbWorkingCollectionId) {
	
	var date = new Date();

	var time = date.getTime();

	var url = 'tbWorkingCollectionDetailAction.action?tbWorkingCollectionId='
			+ tbWorkingCollectionId + '&time=' + time;

	var tbWorkingCollectionAjax = new Ajax.Request(url, {

		method : 'post',

		onComplete : acquireTmWorkingListDetail,

		asynchronous : true

	});
}

function acquireTmWorkingListDetail(originalRequest){
	
	var DetailInfo = originalRequest.responseText.split(';');

	for(var i = 0 ; i < DetailInfo.length-1 ; i++){
		
		var DetailInfoArray = DetailInfo[i].split(",");
	
		var tbWorkingInfoId = DetailInfoArray[0];
		
		var tbWorkingInfoCode = DetailInfoArray[1];
		
		var tbWorkingInfoName = DetailInfoArray[2];
		
		var tbWorkingInfoFixHour = DetailInfoArray[3];
		
		var tbWorkingInfoSendHour = DetailInfoArray[4];
		
		appendTab(tbWorkingInfoId,tbWorkingInfoCode,tbWorkingInfoName,tbWorkingInfoFixHour,tbWorkingInfoSendHour);
	
		countHour();
	}
	
}

function tbWorkingCollectionTableRenderBeforeHandler(pGrid) {
	pGrid
			.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); 
				var tbWorkingCollectionId = record.get("id");// 获取单元格值
				
				findTbWorkingCollectionDetail(tbWorkingCollectionId);
			});
}		