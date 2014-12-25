
function tbBusinessSpecialBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [
	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbBusinessSpecialBalanceFinanceForwardPageAction.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBusinessSpecialBalanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessSpecialBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessSpecialBalanceFinanceFindAction";
	pConfig.showLoadingMsg = true;
}



//根据车型查找车型工位信息
function acquireStationTypeByCarModel() {

	var date = new Date();

	var time = date.getTime();

	var tmCarModelTypeId = document.getElementById('tmCarModelTypeId');
	
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
//根据车型获取车型工位信息，并设置到E3的查询条件中
function acquireTmCarStationTypeInfo(originalRequest) {
	
	var tmCarStationTypeInfo = originalRequest.responseText.split(',');

	var tmCarStationTypeId =  document.getElementById('tbWorkingTab').contentWindow.document.getElementById('tmCarStationTypeId');
		
	var tmCarStationTypeIdSend =  document.getElementById('tbWorkingTab').contentWindow.document.getElementById('tmCarStationTypeIdSend');
	
	var tmCarStationTypeId_gwj;
	
	var tmCarStationTypeIdSend_gwj;
	
	var flag = 'noloaded';
	
	if(null!=document.getElementById('tbWorkingCollectionTab'))
	{
		
		tmCarStationTypeId_gwj =  document.getElementById('tbWorkingCollectionTab').contentWindow.document.getElementById('tmCarStationTypeId');
		
		tmCarStationTypeIdSend_gwj =  document.getElementById('tbWorkingCollectionTab').contentWindow.document.getElementById('tmCarStationTypeIdSend');
	
		flag = 'loaded';
	}
	
	if ('success' == tmCarStationTypeInfo[0]) {

		tmCarStationTypeId.value = tmCarStationTypeInfo[1];
		tmCarStationTypeIdSend.value = tmCarStationTypeInfo[1];
		
		if('loaded'==flag){
			
			tmCarStationTypeId_gwj.value = tmCarStationTypeInfo[1];
			
			tmCarStationTypeIdSend_gwj.value = tmCarStationTypeInfo[1];
			
			document.getElementById('tbWorkingCollectionTab').contentWindow.refresh_tbWorkingCollectionTable();
		}
		document.getElementById('tbWorkingTab').contentWindow.refresh_tbWorkingInfoTable();
		
	} else {

		tmCarStationTypeId.value = "";
		tmCarStationTypeIdSend.value = "";
		//tmCarStationTypeId_gwj.value = "";
		//tmCarStationTypeIdSend_gwj.value = "";
		document.getElementById('tbWorkingTab').contentWindow.refresh_tbWorkingInfoTable();
		//document.getElementById('tbWorkingCollectionTab').contentWindow.refresh_tbWorkingCollectionTable();
	}
}


//根据车牌号获取车辆信息以及车主信息    车牌号的ID为licenseCode
function getInfoByCarLicense()
{
	var date = new Date();

	var time = date.getTime();
	
	var licenseCode = document.getElementById("licenseCode");
	
	if(null!=licenseCode.value&&''!=licenseCode.value){
		
		var url = 'tbCarInfoFindBylicenseCodeAction.action?licenseCode=' + licenseCode.value + '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : returnCarInfo,

			asynchronous : true

		});
	}
}

function returnCarInfo(originalRequest)
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
		
		//hasCarAlertContent(tbCarInfoId.value,1);
	}

}

//根据车型查找车型工位信息
function acquireStationTypeByCarModel() {

	var date = new Date();

	var time = date.getTime();

	var tmCarModelTypeId = document.getElementById('tmCarModelTypeId');
	
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

function openWin(){
	
	var props = "carInfoId,licenseCode,null,null,null,null,null";
	
	showCommonWin('findAllCarInfoAction.action','车辆列表',575,355,props,"licenseCodeFocus");
}
