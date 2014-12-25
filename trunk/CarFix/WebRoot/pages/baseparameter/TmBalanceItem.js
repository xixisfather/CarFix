function tmBalanceItemTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		id : 'addTmBalanceItem',
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
		
			var date = new Date();
			var time = date.getTime();
			var tmBalanceId = document.getElementById('tmBalanceId');
			addObject('tmBalanceItemForwardPageAction!forwardPage.action?tmBalanceId='+tmBalanceId.value+'&time='+time,800,500);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmBalanceItemTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmBalanceItemTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmBalanceItemFindAction";
	pConfig.showLoadingMsg = true;
}

//公式组合
function combineFormula()
{
	var updownselect = document.getElementById('tmBalanceItemList');
	//选中的项目明细VALUE ID的值
	var updownselectValue = updownselect.value;
	//选中的项目明细TEXT 
	var updownselectText = updownselect.options[updownselect.selectedIndex].text;
	//选中的项目明细名称
	var updownselectTextName = updownselectText;
	//显示的公式
	var formula = document.getElementById('formula');
	
	var formulaString = '';
	
	formulaString += formula.value + '[' + updownselectTextName +']';
	
	formula.value = formulaString;
}

function validateFormula(componentFormulaId)
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tmBalanceId = document.getElementById('tmBalanceId');
	
	var formula = document.getElementById(componentFormulaId);
	
	if(null==formula.value||''==formula.value){
		
		Ext.MessageBox.alert('信息', "公式不能为空");
		
		return;
	}
	
	var formulaValue = replaceAll(formula.value,'+','@');
	
	var url = 'tmBalanceItemValidateFormulaAction.action?tmBalanceId='+tmBalanceId.value+'&formula=' + formulaValue + '&time=' + time;
	
	var validateAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireValidateInfo,

		asynchronous : true

	});
}

function acquireValidateInfo(originalRequest)
{
	var validateInfo = originalRequest.responseText.split(',');
	
	var btn = document.getElementById('btn');
	
	var formId = document.getElementById('formId');
	
	if('success'==validateInfo[0])
	{
		if(!document.getElementById('itemName').disabled){
			validateItemName();
		}
		else{
			formId.submit();
		}
		
		
	}
	else
	{
		Ext.MessageBox.alert('信息', validateInfo[1]);
		
		formId.onsubmit = function()
		{
			return false;
		}
	}
	
	
}

//验证结算明细单名字是否已经存在
function validateItemName()
{
	var date = new Date();
	
	var time = date.getTime();
	
	var tmBalanceId = document.getElementById('tmBalanceId');
	
	var itemName = document.getElementById('itemName');
	
	var url = 'tmBalanceItemvalidateItemNameAction.action?itemName=' + itemName.value + '&tmBalanceId='+ tmBalanceId.value + '&time=' + time;
	
	var validateAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireValidateItemNameInfo,

		asynchronous : true

	});
}

function acquireValidateItemNameInfo(originalRequest)
{
	var validateInfo = originalRequest.responseText.split(',');
	
	var btn = document.getElementById('btn');
	
	var formId = document.getElementById('formId');
	
	if('success'==validateInfo[0])
	{
		formId.submit();
	}
	else
	{
		Ext.MessageBox.alert('信息', validateInfo[1]);
		
		formId.onsubmit = function()
		{
			return false;
		}
	}
	
	
}


function validateSubmit()
{
	var errorMsg = '';
	
	var tmBalanceId = document.getElementById('tmBalanceId');
	
	var itemName = document.getElementById('itemName');
	
	if(null==tmBalanceId.value||''==tmBalanceId.value){
		
		errorMsg += '项目名字为空\n'; 
		
	}
	
	if(null==itemName.value||''==itemName.value){
		
		errorMsg += '明细名称为空\n'; 
		
	}
	
	if(''!=errorMsg)
	{
		Ext.MessageBox.alert('信息', errorMsg);
		
		return false;
	}
	
	var formula = document.getElementById('formula');
	
	if(null==formula.value||''==formula.value){
		
		if(!itemName.disabled){
			validateItemName();
		}
		else
		{
			var formId = document.getElementById('formId');
			
			formId.submit();
		}
	}
	else
	{
		validateFormula('formula');
	}
}
