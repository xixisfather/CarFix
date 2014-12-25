
function tmWorkingHourPriceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmWorkingHourPriceForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmWorkingHourPriceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmWorkingHourPriceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmWorkingHourPriceFindAction";
	pConfig.showLoadingMsg = true;
}

function formValidate()
{
	var price = document.getElementById('price');
	
	var errorMsg = '';
	
	if(!validatePositiveNumOfTwo(price.value)){
		errorMsg += '工时单价格式为0.00\n';
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
	
}
