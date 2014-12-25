
function tbAnvancePayTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tbAnvancePayForwardPageAction!forwardPage.action',800,600);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbAnvancePayTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbAnvancePayTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbAnvancePayFindAction";
	pConfig.showLoadingMsg = true;
}

function setChequeCodeDisable()
{
	var payPattern = document.getElementById("payPattern");

	var chequeCode = document.getElementById("chequeCode");
	
	if('1'==payPattern.value){
		chequeCode.value = '';
		chequeCode.disabled = true;
	}
	else{
		chequeCode.disabled = false;
	}
}

function openWin(){
	
	var props = "carInfoId,licenseCode,null,null,null,null,null";
	
	showCommonWin('findAllCarInfoAction.action','车辆列表',575,355,props,"licenseCodeFocus");
}

function licenseCodeFocus(){
	
	var licenseCode = document.getElementById("licenseCode");
	
	licenseCode.focus();
}
