function tmBalanceItemTableConfigHandler(pConfig) {

	
}

function tmBalanceItemTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmBalanceItemCalcAction";
	pConfig.showLoadingMsg = true;
}

//初始化设置值
function setValues(itemCode,itemValue)
{
	var itemObject = document.getElementById(itemCode);
	
	itemObject.value = itemValue;
}

function calcAjaxXsd(itemCode)
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tmStockOutId = parent.document.getElementById('tmStockOutId').value;
	
	var tmBalanceId = parent.document.getElementById('tmBalanceId').value;

	var itemVal = document.getElementById(itemCode).value;
	
	if(!isNumber(itemVal))
	{
		
		Ext.MessageBox.alert('信息', '请输入正确的数字!');
		
		return ;
		
	}
	
	if(''==itemVal)
	{
		
		return;
	}
	
	var url = 'tmBalanceItemCalcXsdAjaxAction.action?tmStockOutId=' + tmStockOutId + '&tmBalanceId='+ tmBalanceId + '&itemCode=' + itemCode + '&itemVal=' + itemVal + '&time=' + time;
	
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
		
		var parentItemCode = parent.document.getElementById(itemInfo[0]); 
		
		var itemValue = itemInfo[1];
		
		itemCode.value = itemValue;
		
		parentItemCode.value = itemValue;
	}
	
}


