function tbBookTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbBookForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBookTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBookTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBookFindAction";
	pConfig.showLoadingMsg = true;
}

// 客户履约
function tbBookeepAppoint(id, actionUrl) {

	Ext.MessageBox.confirm('信息', '您确定客户已经履约？', function(btn) {
		if (btn == 'yes') {

			var date = new Date();
			var time = date.getTime();
			var url = actionUrl + '?id=' + id + '&time=' + time;
			var keepAjax = new Ajax.Request(url, {
				method : 'post',

				onComplete : keepAppointInfo,

				asynchronous : true

			});
		}

	});
}

function keepAppointInfo(originalRequest) {
	var keepAppointInfo = originalRequest.responseText.split(',');

	if (keepAppointInfo[1] == 'success') {
		// Ext.MessageBox.alert('信息', '删除成功');
	} else if (keepAppointInfo[1] == 'failure') {
		Ext.MessageBox.alert('信息', '操作失败,请刷新后重试');
	} else {
		Ext.MessageBox.alert('信息', '操作失败,请刷新后重试');
	}
	eval('refresh_' + keepAppointInfo[0] + '()');
}

function tbWorkingInfoTableConfigHandler(pConfig) {

	pConfig.tbar = [

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
	pConfig.form = "tbWorkingInfoSelectAction";
	pConfig.showLoadingMsg = true;
}
// 从工位的E3表中提取工位信息，设置到维修预约的主单中
function tbWorkingInfoTableRenderBeforeHandler(pGrid) {
	pGrid
			.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the
																// Record
					var e3Num = record.get("no_1");// 获取单元格值
					var e3TbWorkingInfoId = record.get("id");// 获取单元格值
					var e3StationCode = record.get("stationCode");// 获取单元格值
					var e3StationName = record.get('stationName');// 获取单元格值
					var freeSymbol = document
							.getElementById('freeSymbol' + trim(e3Num));
					var freeSymbolValue = freeSymbol.value;
					var freeSymbolText = freeSymbol.options[freeSymbol.selectedIndex].text;
					var tbBookFixStations = document
							.getElementById('tbBookFixStations');

					if (jsSelectIsExitItem(tbBookFixStations, e3TbWorkingInfoId
							+ ',' + freeSymbolValue)) {

						if (confirm('工位号:' + e3StationCode + '已经添加,您确定继续添加？')) {
							jsAddItemToSelect(tbBookFixStations,
									freeSymbolText + ' '
											+ e3StationCode
											+ ' '
											+ e3StationName, e3TbWorkingInfoId
											+ ',' + freeSymbolValue);
						}

					} else {
						jsAddItemToSelect(tbBookFixStations,
								freeSymbolText + ' '
										+ e3StationCode + ' '
										+ e3StationName, e3TbWorkingInfoId
										+ ',' + freeSymbolValue);
					}

				});

}

function sendCarLicenseBook()
{
	var date = new Date();

	var time = date.getTime();
	
	var licenseCode = document.getElementById("licenseCode");
	
	if(null!=licenseCode.value&&''!=licenseCode.value){
		
		var url = 'tbCarInfoFindBylicenseCodeAction.action?licenseCode=' + licenseCode.value + '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : acquireCarInfoBook,

			asynchronous : true

		});
	}
}

function acquireCarInfoBook(originalRequest)
{
	var carInfo = originalRequest.responseText.split(',');
	
	if('success'==carInfo[0])
	{
		//车牌号
		var licenseCode = document.getElementById('licenseCode');
		//底盘号
		var chassisCode = document.getElementById('chassisCode');
		//车型
		var tmCarModelTypeId = document.getElementById('tmCarModelTypeId');
		//购车日期
		var purchaseDate = document.getElementById('purchaseDate');
		//发动机号
		var engineCode = document.getElementById('engineCode'); 
		//车主姓名
		var customerName = document.getElementById('customerName');
		//联系人
		var linkMan = document.getElementById('linkMan');
		//电话
		var phone = document.getElementById('phone');
		//手机
		var telphone = document.getElementById('telphone');
		//住址
		var address = document.getElementById('address');
		//客户号
		var customerCode = document.getElementById('customerCode');
		//车辆id
		var tbCarInfoId = document.getElementById('tbCarInfoId');
		//客户ID
		var tbCustomerId = document.getElementById('tbCustomerId');
		
		if(null!=licenseCode){
			licenseCode.value = carInfo[1];
		}
		
		if(null!=chassisCode){
			chassisCode.value = carInfo[2];
		}
		
		if(null!=tmCarModelTypeId){
			tmCarModelTypeId.value = carInfo[3];
		}
		
		if(null!=purchaseDate){
			purchaseDate.value = carInfo[4];
		}
		
		if(null!=engineCode){
			engineCode.value = carInfo[5];
		}
		
		if(null!=customerName){
			customerName.value = carInfo[6];
		}
		
		if(null!=linkMan){
			linkMan.value = carInfo[7];
		}
		
		if(null!=phone){
			phone.value = carInfo[8];
		}
		
		if(null!=telphone){
			telphone.value = carInfo[9];
		}
		
		if(null!=address){
			address.value = carInfo[10];
		}
		
		if(null!=customerCode){
			customerCode.value = carInfo[11];
		}
		
		if(null!=tbCarInfoId){
			tbCarInfoId.value = carInfo[12];
		}
		
		if(null!=tbCustomerId){
			tbCustomerId.value = carInfo[13];
		}
		
		acquireStationTypeByCarModel();
	}
}

// 根据车型查找车型工位信息
function acquireStationTypeByCarModel(status) {

	var date = new Date();

	var time = date.getTime();

	var tmCarModelTypeId = document.getElementById('tmCarModelTypeId');

	var tbBookFixStations = document.getElementById('tbBookFixStations');
	
	var part_tmCarModelTypeId = document.getElementById('part_tmCarModelTypeId');

	var tbBookFixParts = document.getElementById('tbBookFixParts');
	
	if ('init' != status && !jsIsSelectEmpty(tbBookFixStations)) {
		if(confirm('车型已变更,是否需要删除当前工时工位？')){
			
			jsRemoveAllOption(tbBookFixStations);
			
			
		}
		
	}
	if ('init' != status && !jsIsSelectEmpty(tbBookFixParts)) {
		if(confirm('车型已变更,是否需要删除当前配件？')){
			
			jsRemoveAllOption(tbBookFixParts);
			
			
		}
		
	}
	
	
	if ('' != tmCarModelTypeId.value) {

		part_tmCarModelTypeId.value = tmCarModelTypeId.value;
		
		refresh_tbPartInfoTable();
		
		var url = 'tmCarStationTypeFindByCarModelIdAction.action?tmCarModelTypeId='
				+ tmCarModelTypeId.value + '&time=' + time;

		var carModelAjax = new Ajax.Request(url, {

			method : 'post',

			onComplete : acquireTmCarStationTypeInfo,

			asynchronous : true

		});

	}

	else {
		var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
		var collection_tmCarStationTypeId = document
				.getElementById('collection_tmCarStationTypeId');
		tmCarStationTypeId.value = "";
		collection_tmCarStationTypeId.value = "";
		part_tmCarModelTypeId.value = '';
		refresh_tbWorkingInfoTable();
		refresh_tbWorkingCollectionTable();
		refresh_tbPartInfoTable();
	}

}
// 根据车型获取车型工位信息，并设置到E3的查询条件中
function acquireTmCarStationTypeInfo(originalRequest) {
	var tmCarStationTypeInfo = originalRequest.responseText.split(',');

	var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');

	var collection_tmCarStationTypeId = document
			.getElementById('collection_tmCarStationTypeId');
	
	refresh_tbPartInfoTable();
	
	if ('success' == tmCarStationTypeInfo[0]) {

		tmCarStationTypeId.value = tmCarStationTypeInfo[1];

		collection_tmCarStationTypeId.value = tmCarStationTypeInfo[1];

		refresh_tbWorkingInfoTable();

		refresh_tbWorkingCollectionTable();
	} else {

		tmCarStationTypeId.value = "";

		collection_tmCarStationTypeId.value = "";
	}
}
// 双击SELECT事件 双击把选中的工位删除
function deleteSelectedOption(objSelect) {
	Ext.MessageBox.confirm('信息', '您确定要删除选定的工位？', function(btn) {
		if (btn == 'yes') {

			var tbBookFixStations = document
					.getElementById('tbBookFixStations');

			jsRemoveSelectedItemFromSelect(tbBookFixStations);
		}
	});

}

function strAppandSpace(str) {
	var strLength = str.length;
	for ( var i = 0; i < 8 - strLength; i++) {

		str += '  ';
	}
	return str;
}
// 提交表单
function submitForm() {

	var tbBookFixStations = document.getElementById('tbBookFixStations');
	
	var tbBookFixParts = document.getElementById('tbBookFixParts');

	var formId = document.getElementById('formId');

	jsSelectAllItem(tbBookFixStations);
	
	jsSelectAllItem(tbBookFixParts);

	formId.submit();
}

function tbWorkingCollectionTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{

		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbWorkingCollectionTable();
		}
	} ];

}

function tbWorkingCollectionTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbWorkingCollectionSelectAction";
	pConfig.showLoadingMsg = true;
}

// 从工位的E3表中提取工位信息，设置到维修预约的主单中
function tbWorkingCollectionTableRenderBeforeHandler(pGrid) {
	pGrid
			.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the
																// Record
					var e3Num = record.get("no_1");// 获取单元格值
					var e3TbWorkingCollectionId = record.get("id");// 获取单元格值
					var e3WorkingCollectionCode = record
							.get("workingCollectionCode");// 获取单元格值
					var e3WorkingCollectionName = record
							.get('workingCollectionName');// 获取单元格值

					var freeSymbol = document
							.getElementById('collection_freeSymbol' + trim(e3Num));
					var freeSymbolValue = freeSymbol.value;
					var freeSymbolText = freeSymbol.options[freeSymbol.selectedIndex].text;

					sendTbWorkingCollectionId(e3TbWorkingCollectionId,
							freeSymbolValue, freeSymbolText);

				});

}

function sendTbWorkingCollectionId(tbWorkingCollectionId, freeSymbolValue,
		freeSymbolText) {
	var date = new Date();

	var time = date.getTime();

	var url = 'tbWorkingInfoFindByTmWorkingCollectionIdAction.action?tbWorkingCollectionId='
			+ tbWorkingCollectionId
			+ '&freeSymbolValue='
			+ freeSymbolValue
			+ '&freeSymbolText=' + freeSymbolText + '&time=' + time;

	var tbWorkingCollectionAjax = new Ajax.Request(url, {

		method : 'post',

		onComplete : acquireTmWorkingListInfo,

		asynchronous : true

	});
}
// 获取工位集下工位的信息,格式为(工位ID,免费标示-免费标示中文,工位号,工位名;)
function acquireTmWorkingListInfo(originalRequest) {
	var tmWorkingListInfo = originalRequest.responseText;

	var tmWorkingList = tmWorkingListInfo.split(';');

	var tbBookFixStations = document.getElementById('tbBookFixStations');

	for ( var i = 0; i < tmWorkingList.length - 1; i++) {

		var tmWorkingInfoKey = tmWorkingList[i].split('-');

		var selectKey = tmWorkingInfoKey[0];

		var selectVal = strAppandSpace(tmWorkingInfoKey[1].split(',')[0])
				+ tmWorkingInfoKey[1].split(',')[1] + '                '
				+ tmWorkingInfoKey[1].split(',')[2];

		if (jsSelectIsExitItem(tbBookFixStations, selectKey)) {

			if (confirm('工位号:' + tmWorkingInfoKey[1].split(',')[1] + '已经添加 ,您确定继续添加？')) {
				jsAddItemToSelect(tbBookFixStations, selectVal, selectKey);
			}

		} else {

			jsAddItemToSelect(tbBookFixStations, selectVal, selectKey);
		}
	}
}



function tbPartInfoTableConfigHandler(pConfig) {
	
	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbPartInfoTable();
		}
	} ];
	
	// pConfig.autoExpandColumn='no';
}

function tbPartInfoTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbPartInfoSelectAction";
	pConfig.showLoadingMsg = true;
}

//从工位的E3表中提取工位信息，设置到维修预约的主单中
function tbPartInfoTableRenderBeforeHandler(pGrid) {
	pGrid
			.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the
																// Record
					var e3Num = record.get("no_1");// 获取单元格值
					var e3PartInfoId = record.get("id");// 获取单元格值
					var e3PartCode = record.get("partCode");// 获取单元格值
					var e3PartName = record.get('partName');// 获取单元格值
					
					var freeSymbol = document
							.getElementById('part_freeSymbol' + trim(e3Num));
					var freeSymbolValue = freeSymbol.value;
					var freeSymbolText = freeSymbol.options[freeSymbol.selectedIndex].text;
					var quantity = document.getElementById("partQuantity"+trim(e3Num)).value;
					var dealType = document.getElementById('dealType' + trim(e3Num));
					var dealTypeValue = dealType.value;
					var dealTypeText = dealType.options[dealType.selectedIndex].text;
					var tbBookFixParts = document
							.getElementById('tbBookFixParts');

					var tbBookFixPartsKey = e3PartInfoId+ ',' + freeSymbolValue + ',' + quantity + ',' + dealTypeValue;
					
					var tbBookFixPartsValue = freeSymbolText + ' ' + quantity + ' '+ dealTypeText +' '+ e3PartCode +' '+ e3PartName;
					
					jsAddItemToSelect(tbBookFixParts,tbBookFixPartsValue,tbBookFixPartsKey);
					
				});

}

//双击SELECT事件 双击把选中的配件删除
function deleteSelectedPartOption() {
	Ext.MessageBox.confirm('信息', '您确定要删除选定的配件？', function(btn) {
		if (btn == 'yes') {

			var tbBookFixParts = document
			.getElementById('tbBookFixParts');

			jsRemoveSelectedItemFromSelect(tbBookFixParts);
		}
	});

}
