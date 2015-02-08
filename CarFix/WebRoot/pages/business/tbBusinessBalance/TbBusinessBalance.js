
function tbBusinessBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessBalanceFindAction";
	pConfig.showLoadingMsg = true;
}

function showElement()
{
	var wts_tb=document.getElementById('wts_tb');
	
	var xsd_tb=document.getElementById('xsd_tb');
	
	var js_wts=document.getElementById('js_wts');
	
	var js_xsd=document.getElementById('js_xsd');
	
	if(js_wts.checked)
	{
		wts_tb.style.display = "block";
		
		xsd_tb.style.display = "none";
	}
	
	else if(js_xsd.checked)
	{
		wts_tb.style.display = "none";
		
		xsd_tb.style.display = "block";
	}
}

function sendValue()
{
	var js_wts = document.getElementById('js_wts');
	
	var js_xsd = document.getElementById('js_xsd'); 
	
	var type = '';
	
	var selectValue = '';
	
	if(js_wts.checked)
	{
		type = 'entrust' ;
		
		for(var i = 1 ;i <= countEntrust; i++)
		{
			var selectObject = document.getElementById( type + '' + i);
			
			if(selectObject.checked)
			{
				selectValue = selectObject.value;
				
				break;
			}
			
		}
		
		if(''==selectValue)
		{
			alert('请选择要结算的委托书');
			
			return ;
		}
		
		var date = new Date();
		
		var time = date.getTime();
		
		window.location.href = 'tbBusinessBalanceChooseAction.action?flag=' + type + '&objectId=' + selectValue + '&time='+time;
	}
	
	else if(js_xsd.checked)
	{
		type = 'stockOut';
		
		for(var i = 1; i <= stockOutListCount ; i++)
		{
			var selectObject = document.getElementById( type + '' + i);
			
			if(selectObject.checked)
			{
				selectValue = selectObject.value;
				
				break;
			}
			
		}
		if(''==selectValue)
		{
			alert('请选择要结算的销售单');
			
			return ;
		}
		var date = new Date();
		
		var time = date.getTime();
		
		window.location.href = 'tbBusinessBalanceChooseAction.action?flag=' + type + '&objectId=' + selectValue + '&time='+time;
	}
	
	
	
}

function initTab(type)
{
	var tbFixEntrustId = document.getElementById('tbFixEntrustId');
	
	var tmBalanceId = document.getElementById('tmBalanceId');
		
	var workingHourFavourRate = document.getElementById('workingHourFavourRate');
	
	var fixPartFavourRate = document.getElementById('fixPartFavourRate');
	
	var solePartFavourRate = document.getElementById('solePartFavourRate');
	
	var customerId = document.getElementById('customerId');
	
	Ext.get('tabPlaceHolder').on('click',function(){
		
		if('tmBalanceItemTab'==type)
		{
			if(null!=document.getElementById('tmBalanceItemTab'))
			{	
				
				
				document.getElementById('tmBalanceItemTab').contentWindow.location.href='tmBalanceItemCalcAction.action?tmBalanceId='+tmBalanceId.value+'&tbFixEntrustId='+tbFixEntrustId.value+'&workingHourFavourRate='+workingHourFavourRate.value+'&fixPartFavourRate='+fixPartFavourRate.value+'&solePartFavourRate='+solePartFavourRate.value+'&customerId='+ customerId.value;
				
			}
			
		}
		
		
	});
}

function calcAjax(itemCode)
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tbFixEntrustId = document.getElementById('tbFixEntrustId').value;
	
	var tmBalanceId = document.getElementById('tmBalanceId').value;

	var itemVal = document.getElementById(itemCode).value;
	
	if(''==itemVal)
	{
		
		return;
	}
	
	var url = 'tbBusinessBalanceCalcAction.action?tbFixEntrustId=' + tbFixEntrustId + '&tmBalanceId='+ tmBalanceId + '&itemCode=' + itemCode + '&itemVal=' + itemVal + '&time=' + time;
	
	var calcAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : getCalcInfo,

		asynchronous : true

	});
}

function getCalcInfo(originalRequest)
{
	var calcInfo = originalRequest.responseText.split(',');
	
	for(var i = 0 ; calcInfo.length-1; i++)
	{
		var itemInfo = calcInfo[i].split(':');
		
		var itemCode = document.getElementById(itemInfo[0]);
		
		var itemValue = itemInfo[1];
		
		itemCode.value = itemValue;
		
	}
	
}

function formValidate()
{
	
	var shouldPayAmount = document.getElementById("shouldPayAmount");
	
	var errorMsg = '';
	
	if(''==shouldPayAmount.value){
		
		errorMsg += '结算清单未填写';
		
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}


///////////////////////销售单////////////////////////////////////////////
function initTabXsd(type)
{
	var tmStockOutId = document.getElementById('tmStockOutId');
	
	var tmBalanceId = document.getElementById('tmBalanceId');
		
	var workingHourFavourRate = document.getElementById('workingHourFavourRate');
	
	var fixPartFavourRate = document.getElementById('fixPartFavourRate');
	
	var solePartFavourRate = document.getElementById('solePartFavourRate');
	
	Ext.get('tabPlaceHolder').on('click',function(){
		
		if('tmBalanceItemTab'==type)
		{
			if(null!=document.getElementById('tmBalanceItemTab'))
			{	
				
				
				document.getElementById('tmBalanceItemTab').contentWindow.location.href='tmBalanceItemCalcXsdAction.action?tmBalanceId='+tmBalanceId.value+'&tmStockOutId='+tmStockOutId.value+'&workingHourFavourRate='+workingHourFavourRate.value+'&fixPartFavourRate='+fixPartFavourRate.value+'&solePartFavourRate='+solePartFavourRate.value;
				
			}
			
		}
		
		
	});
}

function calcAjaxXsd(itemCode)
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tmStockOutId = document.getElementById('tmStockOutId').value;
	
	var tmBalanceId = document.getElementById('tmBalanceId').value;

	var itemVal = document.getElementById(itemCode).value;
	
	if(''==itemVal)
	{
		
		return;
	}
	
	var url = 'tbBusinessBalanceCalcXsdAction.action?tmStockOutId=' + tmStockOutId + '&tmBalanceId='+ tmBalanceId + '&itemCode=' + itemCode + '&itemVal=' + itemVal + '&time=' + time;
	
	var calcAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : getCalcInfoXsd,

		asynchronous : true

	});
}

function getCalcInfoXsd(originalRequest)
{
	var calcInfo = originalRequest.responseText.split(',');
	
	for(var i = 0 ; calcInfo.length-1; i++)
	{
		var itemInfo = calcInfo[i].split(':');
		
		var itemCode = document.getElementById(itemInfo[0]);
		
		var itemValue = itemInfo[1];
		
		itemCode.value = itemValue;
		
	}
	
}

function prePrint(){
	
	var date = new Date();
	var time = date.getTime();
	var entrustCode = document.getElementById('entrustCode');
	
	addObject('tbBusinessBalanceEditAction.action?entrustCode=' + entrustCode.value + '&time='+time + '&flag=wts',100,100);
	
	
}


function confirmBalance() {
	
	if(confirm('结算金额请仔细核对,确定要结算吗?')){
		
		document.getElementById('balanceForm').submit();
	}
	
	
}
