
function tbCarInfoTableConfigHandler(pConfig) {

	pConfig.tbar = [

	//{	
	//	text : '新增',
	//	iconCls : 'addIcon',
	//	handler : function() {
	//		addObject('tbCarInfoForwardPageAction!forwardPage.action',600,300);
	//	}
	//}, '', '-', '', 
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
	pConfig.form = "tbCarInfoFindAction";
	pConfig.showLoadingMsg = true;
}

function carInfoFormValidate()
{
	var licenseCode = document.getElementById('licenseCode');
	
	var chassisCode = document.getElementById('chassisCode');
	
	var errorMsg = '';
	
	if(''==trim(licenseCode.value))
	{
		errorMsg += '牌照号不能为空\n';
	}
	
	if(''==trim(chassisCode.value))
	{
		errorMsg += '底盘号不能为空\n';
	}
	else{
		
		if(chassisCode.value.length < 17){
		
			errorMsg += '底盘号应为17位\n';
		
		}
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}

function validateAll(){
	
	
var customerName = document.getElementById('customerName');
	
	var pinyinCode = document.getElementById('pinyinCode');
	
	var telephone = document.getElementById('telephone');
	
	var contractPerson = document.getElementById('contractPerson');
	
	var errorMsg = '';
	
	if(''==trim(customerName.value))
	{
		errorMsg += '客户名称不能为空\n';
	}
	if(''==trim(pinyinCode.value))
	{
		errorMsg += '拼音代码不能为空\n';
	}
	
	
	
	if(''==trim(contractPerson.value))
	{
		errorMsg += '联系人不能为空\n';
	}
	if(''==trim(telephone.value))
	{
		errorMsg += '手机号码不能为空\n';
	}
	
    var licenseCode = document.getElementById('licenseCode');
	
	var chassisCode = document.getElementById('chassisCode');
	
	if(''==trim(licenseCode.value))
	{
		errorMsg += '牌照号不能为空\n';
	}
	
	if(''==trim(chassisCode.value))
	{
		errorMsg += '底盘号不能为空\n';
	}
	else{
		
		if(chassisCode.value.length < 17){
			
			errorMsg += '底盘号应为17位\n';
			
		}
		
	}
	
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}


function carInfoValidation(){
	
	var licenseCode = document.getElementById('licenseCode');
	var chassisCode = document.getElementById('chassisCode');
	var id = document.getElementById('carId');
	var date = new Date();
	var time = date.getTime();
	var url = 'tbCarInfoValidationAction.action?licenseCode=' + licenseCode.value + '&chassisCode=' + chassisCode.value + '&id=' + id.value + '&time=' + time;
	var vlAjax = new Ajax.Request(url, {
		
		method : 'post',

		onComplete : getVlInfo,

		asynchronous : true

	});
	
}

function getVlInfo(originalRequest){
	
	var vlInfo = originalRequest.responseText.split(',');

	if('licenseExist'==vlInfo[0]){
		
		alert('车牌号已存在,请重新输入');
		
		document.getElementById('licenseCode').focus();
		
	}
	
	if('chassisExist'==vlInfo[0]){
		
		alert('底盘号已存在,请重新输入');
		
		document.getElementById('chassisCode').focus();
	}
	
}

