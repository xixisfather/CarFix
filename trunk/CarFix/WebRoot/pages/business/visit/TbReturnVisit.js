function tbCarInfoTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbCarInfoTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbCarInfoTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbCarInfoLostAction";
	pConfig.showLoadingMsg = true;
}

function tbReturnVisitTableConfigHandler(pConfig) {
	var flag = document.getElementById('returnType');

	pConfig.tbar = [

			{
				text : '进行回访',
				iconCls : 'addIcon',
				handler : function() {
					addObject(
							'tbReturnVisitForwardPageAction!forwardPage.action?flag=' + flag.value,
							600, 300);
				}
			}, '', '-', '',

			{
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbReturnVisitTable();
				}
			} ];

	// pConfig.autoExpandColumn='no';
}

function tbReturnVisitTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbReturnVisitFindAction";
	pConfig.showLoadingMsg = true;
}


function tbReturnVisit2TableConfigHandler(pConfig) {
	var flag = document.getElementById('returnType');

	pConfig.tbar = [

			
			{
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbReturnVisit2Table();
				}
			} ];

	// pConfig.autoExpandColumn='no';
}

function tbReturnVisit2TableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbReturnVisitFindAction";
	pConfig.showLoadingMsg = true;
}

function tbCarInfoWXHFTableRenderBeforeHandler(pGrid) {
	pGrid.on("rowclick", function(pGrid, pRowIndex, pEventObj) {
			var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
			var e3licenseCode = record.get("licenseCode");// 获取单元格值
			var licenseCode2 = document.getElementById('licenseCode2');
			licenseCode2.value = e3licenseCode;
			refresh_tbReturnVisit2Table();
		});

}
