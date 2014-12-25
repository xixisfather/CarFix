
function tbBusinessBalanceTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbBusinessBalanceTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbBusinessBalanceTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbBusinessReBalanceFindAction";
	pConfig.showLoadingMsg = true;
}

function reTbBusinessBalance(id)
{
	Ext.MessageBox.confirm('信息', '您确定要再结算？', function(btn) {
		if (btn == 'yes') {

			var date = new Date();
			
			var time = date.getTime();
			
			var url = 'tbBusinessBalanceReBalanceAction.action?id=' + id + '&time=' + time;
			
			var reTbBusinessBalanceAjax = new Ajax.Request(url, {
				method : 'post',

				onComplete : getReTbBusinessBalanceInfo,

				asynchronous : true

			});
		}

	});
}

function getReTbBusinessBalanceInfo(originalRequest){
	
	var reBalanceInfo = originalRequest.responseText.split(',');

	if (reBalanceInfo[1] == 'success') {
		//Ext.MessageBox.alert('信息', '删除成功');
	} else if (reBalanceInfo[1] == 'failure') {
		Ext.MessageBox.alert('信息', '操作失败,操作异常或已被再结算');
	} else {
		Ext.MessageBox.alert('信息', '操作失败,操作异常或已被再结算');
	}
	eval('refresh_' + reBalanceInfo[0] + '()');
}