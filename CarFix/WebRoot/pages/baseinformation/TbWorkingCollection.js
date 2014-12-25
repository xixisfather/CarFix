
function tbWorkingCollectionTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			
			var date = new Date();
		
			var time = date.getTime();
		
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
			
			addObject('tbWorkingCollectionForwardPageAction!forwardPage.action?tmCarStationTypeId='+tmCarStationTypeId.value+'&timeId='+time,800,600);
		}
	}, '', '-', '', {
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbWorkingCollectionTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbWorkingCollectionTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbWorkingCollectionFindAction";
	pConfig.showLoadingMsg = true;
}


//根据工位集代码与车型工位验证是否已经存在
function sendWorkingCollectCodeAndTmCarStationTypeId(componentWorkingCollectCode,componentTmCarStationTypeId){
	var date = new Date();
	var time = date.getTime();
	var workingCollectCode = document.getElementById(componentWorkingCollectCode);
	var tmCarStationTypeId = document.getElementById(componentTmCarStationTypeId);
	var url = 'tbWorkingCollectionFindByWorkingCollectionCodeAndTmCarStationTypeIdAction.action'+ '?workingCollectionCode=' + workingCollectCode.value + '&tmCarStationTypeId='+ tmCarStationTypeId.value +'&time=' + time;
	var existAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireIsTbWorkingCollectionExist,

		asynchronous : true

	});
}

function acquireIsTbWorkingCollectionExist(originalRequest){
	
	var existInfo = originalRequest.responseText;
	
	if(null!=existInfo&&''!=existInfo){
		
		if('edit'!=flag)
		{
			Ext.MessageBox.alert('信息', existInfo);
		}
		else
		{
			if(workingCollectionCodeCopy.value == workingCollectionCode.value)
			{
				if(!formValidate())
				{
					return;
				}
				formSubmit();
			}
			else
			{
				Ext.MessageBox.alert('信息', existInfo);
			}
		}
		
		
	}
	else{
		if(!formValidate())
		{
			return;
		}
		formSubmit();
	}
}

function hasAddWorking(objSelect)
{
	var isEmpty = true;
		
	var length = objSelect.options.length;
		
	if(length>1)
	{
			
		isEmpty = false;
	}
		
	return isEmpty;
	
}

function formValidate()
{
	
	var workingCollectionCode = document.getElementById('workingCollectionCode');
	
	var workingCollectionName = document.getElementById('workingCollectionName');
	
	var tbWorkingInfoIdRight = document.getElementById('tbWorkingInfoIdRight');
	
	var errorMsg = '';
	
	if(''==trim(workingCollectionCode.value)){
		
		errorMsg += '工位集代码不能为空\n';
	}
	if(''==trim(workingCollectionName.value)){
		
		errorMsg += '工位集名称不能为空\n';
	}
	if(hasAddWorking(tbWorkingInfoIdRight))
	{
		errorMsg += '未添加工位\n';
	}
	if(''!=errorMsg){
		
		alert(errorMsg);
	
		return false;
	}
	
	return true;

}
