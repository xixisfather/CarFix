function tbMembershipCardTableConfigHandler(pConfig) {

	pConfig.tbar = [

			{
				text : '开卡',
				iconCls : 'addIcon',
				handler : function() {
					addObject(
							'tbMembershipCardForwardPageAction!forwardPage.action',
							600, 300);
				}
			}, '', '-', '',

			{
				text : '刷新',
				iconCls : 'refreshIcon',
				handler : function() {
					refresh_tbMembershipCardTable();
				}
			} ];

	// pConfig.autoExpandColumn='no';
}

function tbMembershipCardTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbMembershipCardFindAction";
	pConfig.showLoadingMsg = true;
}

function tbMembershipCardFormValidate() {

	var cardPoint = document.getElementById('cardPoint');

	var cardSaving = document.getElementById('cardSaving');
	
	var tbCustomerId = document.getElementById('customerId');
	
	var cardPassword = document.getElementById('cardPassword');
	
	var cardPasswordConfirm = document.getElementById('cardPasswordConfirm');

	var errorMsg = '';

	if (!isNumber(cardPoint.value)) {

		errorMsg += '初始积分必须为正整数\n';
	}

	if (!isNumber(cardSaving.value)) {

		errorMsg += '初始卡内金额必须为正数\n';
	}
	
	if(''==tbCustomerId.value){
		
		errorMsg += '请选择客户\n';
		
	}
	
	if(null != cardPassword){
		if('' == cardPassword.value){
			
			errorMsg += '请客户输入密码\n';
			
		}
		else
		{
			
			if(cardPassword.value != cardPasswordConfirm.value){
				
				errorMsg += '两次密码输入不一致\n';
			}
			
		}
	}
	
	if ('' != errorMsg) {

		alert(errorMsg);

		return false;

	}

	return true;
}

function openWin() {
	var props = "customerId,customerCode,customerName";
	showCommonWin('findAllTmTbCustomerAction.action', '客户列表', 650, 350, props,
			"tbCustomerTable");
}

function formSubmit(){
	
	if(confirm('确定提交操作？'))
	{	
		
		if(tbMembershipCardFormValidate())
		
			document.forms[0].submit();
	}
	else
	{
		
		return false;
	}
}

function sendCZJE()
{
	var date = new Date();

	var time = date.getTime();
	
	var tbMembershipCardId = document.getElementById('tbMembershipCardId');
	
	var czje = document.getElementById("czje");
	
	if(clearNoNum22(czje,0.00,100000.00))
	{
		
		var url = 'tmCardTypeFindByCZJEAction.action?czje=' + czje.value + '&tbMembershipCardId=' + tbMembershipCardId.value + '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : acquireCZJEInfo,

			asynchronous : true

		});
		
	}
}

function acquireCZJEInfo(originalRequest)
{
	var czjeInfo = originalRequest.responseText.split(',');
	
	var cGiveMoney = czjeInfo[0];
	
	var cGivePoint = czjeInfo[1];
	
	var aftJe = czjeInfo[2];
	
	var aftJf = czjeInfo[3];
	
	var zsje = document.getElementById('zsje');
	
	var zsjf = document.getElementById('zsjf');
	
	var aftJeObj = document.getElementById('aftJe');
	
	var aftJfObj = document.getElementById('aftJf');
	
	zsje.value = cGiveMoney;
	
	zsjf.value = cGivePoint;
	
	aftJeObj.value = aftJe;
	
	aftJfObj.value = aftJf;
}

function sendCJF(flag)
{
	var date = new Date();

	var time = date.getTime();
	
	var tbMembershipCardId = document.getElementById('tbMembershipCardId');
	
	var czjf = document.getElementById("czjf");
	
	if('' == czjf.value){
		
		return;
	}
	
	var pass = document.getElementById('pass');
	
	if(clearNoNum22(czjf,0.00,100000.00))
	{
		
		var url = 'tmCardTypeFindByCJFAction.action?czjf=' + czjf.value + '&tbMembershipCardId=' + tbMembershipCardId.value + '&flag=' + flag + '&pass='+ pass.value+ '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : acquireCJFInfo,

			asynchronous : true

		});
		
	}
}

function acquireCJFInfo(originalRequest)
{
	var czjeInfo = originalRequest.responseText.split(',');
	
	var aftJf = czjeInfo[0];
	
	var czjf = document.getElementById("czjf");
	
	var aftJfObj = document.getElementById('aftJf');
	
	var pass = document.getElementById('pass');
	
	if('passFail' == aftJf){
		
		czjf.value = '';
		
		aftJfObj.value = '';
		
		pass.value = '';
		
		alert('密码错误,请重新输入');
		
		return;
	}
	
	aftJfObj.value = aftJf;
	
	if(aftJf < 0){
		
		czjf.value = '';
		
		aftJfObj.value = '';
		
		alert('卡内积分不够!');
	}
}