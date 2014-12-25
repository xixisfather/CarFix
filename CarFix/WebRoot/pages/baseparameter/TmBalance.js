
function tmBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			addObject('tmBalanceForwardPageAction!forwardPage.action',600,300);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tmBalanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tmBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tmBalanceFindAction";
	pConfig.showLoadingMsg = true;
}

//表格显示前,通常在这注册单击，双击事件
function tmBalanceTableRenderBeforeHandler(pGrid) {
	pGrid.on("rowclick", function(pGrid, pRowIndex, pEventObj) {
		var record = pGrid.getStore().getAt(pRowIndex); // Get the Record
			var e3tmBalanceId = record.get('id');// 获取单元格值
			var tmBalanceId = document.getElementById('tmBalanceId');
			tmBalanceId.value = e3tmBalanceId;
			refresh_tmBalanceItemTable();
			Ext.onReady(function(){
				Ext.getCmp('addTmBalanceItem').enable();
			});
		});

}

function formValidate()
{
	var errorMsg = '';
	
	var balanceName = document.getElementById('balanceName');
	
	if(''==trim(balanceName.value))
	{
		
		errorMsg += '项目名称不能为空\n';
		
	}
	
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;
}