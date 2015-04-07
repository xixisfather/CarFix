

function tbFixEntrustTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '创建委托书',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbFixEntrustForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbFixEntrustTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbFixEntrustTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbFixEntrustFindAction";
	pConfig.showLoadingMsg = true;
}

//设置系统时间--年月日
function setSysDate(){
	 var   d,s = "";                       //   声明变量。 
     d   =   new   Date(); //   创建   Date   对象。
     s   +=   d.getYear();
     s   +=   (d.getMonth()   +   1)   +   "-";                         //   获取月份。 
     s   +=   d.getDate()   +   "-";                                       //   获取日。 
                                                       //   获取年份。 
     return s;        
}
//设置系统时间--小时分秒
function setSysTime(){
	var   d,   s   =   ""; 
	var   c   =   ":"; 
	d   =   new   Date(); 
	s   +=   d.getHours()   +   c; 
	s   +=   d.getMinutes()   +   c; 
	s   +=   d.getSeconds()   +   c; 
	s   +=   d.getMilliseconds(); 
	return s; 
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
		
		hasCarAlertContent(tbCarInfoId.value,1);
		
		if('1' == carInfo[14]) {
			
			alert('该车有欠款金额,请注意');
		}
	}
	else{
		
		Ext.MessageBox.alert('信息', '该车牌号在系统中不存在,请先增加车辆信息!');
		
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
// 根据车型获取车型工位信息，并设置到E3的查询条件中
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

function openWin(){
	
	var props = "carInfoId,licenseCode,null,null,null,null,null";
	
	showCommonWin('findAllCarInfoAction.action','车辆列表',575,355,props,"licenseCodeFocus");
}

function licenseCodeFocus(){
	
	var licenseCode = document.getElementById("licenseCode");
	
	licenseCode.focus();
	//车辆id
	var tbCarInfoId = document.getElementById('tbCarInfoId');
	//特殊车辆提醒
	hasCarAlertContent(tbCarInfoId.value,1);
}

function noFinishEntrust(id)
{
	Ext.MessageBox.confirm('信息', '您确定要取消竣工？', function(btn) {
		if (btn == 'yes') {

			var date = new Date();
			
			var time = date.getTime();
			
			var url = 'tbFixEntrustCancelFinishAction.action?id=' + id + '&time=' + time;
			
			var finishAjax = new Ajax.Request(url, {
				method : 'post',

				onComplete : getNoFinishInfo,

				asynchronous : true

			});
		}

	});
}

function getNoFinishInfo(originalRequest)
{
	var finishInfo = originalRequest.responseText;
	
	if (finishInfo== 'success') {
		
	} else if (finishInfo== 'failure') {
		
		Ext.MessageBox.alert('信息', '无法取消竣工,该委托书已被竣工或者结算');
	
	} else {
		
		Ext.MessageBox.alert('信息', '操作失败,操作异常或数据已被引用');
	
	}
	
	refresh_tbFixEntrustTable();
	
}


function oilValidate()
{
	
	var oilMeter = document.getElementById('oilMeter');
	
	if(''!=oilMeter.value){
		clearNoNum22(oilMeter,0,1);
	}
}

function milterValidate(compid){
	
	var oj = document.getElementById(compid);
	
	if(oj.value!=''&&!isNumber(oj.value))
	{
		alert('请填写正确的数字!');
		
		oj.focus();
		
	}
}

function forwardPage2(id,carId,url,flag,width,height) {
	
	var date = new Date();
	
	var time = date.getTime();
	
	hasCarAlertContent(carId,3);
	
	Ext.onReady( function() {
		win = new Ext.Window( {
			title : '操作',
			width : width,
			height : height,
			isTopContainer : true,
			modal : true,
			maximizable: true,
			resizable : true,
			autoScroll:true,
			contentEl : Ext.DomHelper.append(document.body, {
				tag : 'iframe',
				style : "border 0px none;scrollbar:true",
				src : url+'?id='+id+'&flag='+flag+'&time='+time,
				height : "100%",
				width : "100%"
			})
		})
		win.show();
		//窗口最大化
		win.maximize();
	});
}
