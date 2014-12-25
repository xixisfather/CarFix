//全局参数 设置修理工时 派工工时是否可修改
var isReadOnly = false ;

function tbWorkingInfoTableConfigHandler(pConfig) {

	pConfig.tbar = [

	{	
		text : '新增',
		iconCls : 'addIcon',
		handler : function() {
			
			var date = new Date();
		
			var time = date.getTime();
		
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
			
			addObject('tbWorkingInfoWtsForwardPageAction!forwardPageWts.action?tmCarStationTypeId='+tmCarStationTypeId.value+'&timeId='+time,600,300);
		}
	}, '', '-', '', 
	{	
		text : '导入',
		iconCls : 'editIcon',
		handler : function() {
		
			var date = new Date();
		
			var time = date.getTime();
			
			var tmCarStationTypeId = document.getElementById('tmCarStationTypeId');
		
			addObject('tbWorkingInfoWtsForwardPageAction!forwardPageWts.action?tmCarStationTypeId='+tmCarStationTypeId.value+'&flag=importXlsPage'+'&timeId='+time,600,300);
		
		}
	}, '', '-', '',
	{	
		text : '导出',
		iconCls : 'viewIcon',
		handler : function() {
			
			var date = new Date();
			
			var time = date.getTime();
			
			window.open('tbWorkingInfoExportXlsAction.action?timeId='+time,'_blank');
		}
	}, '', '-', '',
	{
		text : '刷新',
		iconCls : 'refreshIcon',
		handler : function() {
			refresh_tbWorkingInfoTable();
		}
	} ];

	// pConfig.autoExpandColumn='no';
}

function tbWorkingInfoTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbWorkingInfoQueryAction";
	pConfig.showLoadingMsg = true;
}

//根据工位代号获取工位信息
function sendStationCode(componentStationCodeId){
	var date = new Date();
	var time = date.getTime();
	var stationCode = document.getElementById(componentStationCodeId);
	var url = 'tbWorkingInfoFindByStationCodeAction.action?stationCode=' + stationCode.value + '&time=' + time;
	var stationCodeAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireTmWorkingInfo,

		asynchronous : true

	});
}

function acquireTmWorkingInfo(originalRequest){
	
	var tmWorkingInfo = originalRequest.responseText.split(',');
	
	if(null!=tmWorkingInfo&&tmWorkingInfo.length>1){
		
		var stationName = document.getElementById('stationName');
		
		stationName.value = tmWorkingInfo[0];
		
		var tmWorkTypeId = document.getElementById('tmWorkTypeId');
		
		tmWorkTypeId.value = tmWorkingInfo[1];
		
		var pinyinCode = document.getElementById('pinyinCode'); 
		
		pinyinCode.value = tmWorkingInfo[2];
		
		var tmCarBodyId = document.getElementById('tmCarBodyId'); 
		
		tmCarBodyId.value = tmWorkingInfo[3];
		
		var fixHour = document.getElementById('fixHour'); 
		
		fixHour.value = tmWorkingInfo[4]; 
		
		var sendHour = document.getElementById('sendHour'); 
		
		sendHour.value = tmWorkingInfo[5]; 
	}
}

//根据工位代码与车型工位验证是否已经存在
function sendStationCodeAndCarTypeId(componentStationCodeId,componentCarStationTypeId){
	var date = new Date();
	var time = date.getTime();
	var stationCode = document.getElementById(componentStationCodeId);
	var carStationTypeId = document.getElementById(componentCarStationTypeId);
	var url = 'tbWorkingInfoFindByStationCodeAndCarStationTypeIdAction.action'+ '?stationCode=' + stationCode.value + '&carStationTypeId='+ carStationTypeId.value +'&time=' + time;
	var existAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquireIsTmWorkingInfoExist,

		asynchronous : true

	});
}

function acquireIsTmWorkingInfoExist(originalRequest){
	
	var tmWorkingInfo = originalRequest.responseText;
	
	if(null!=tmWorkingInfo&&''!=tmWorkingInfo){
		
		Ext.MessageBox.alert('信息', tmWorkingInfo);
		
	}
	else{
		
		var formId = document.getElementById('formId');
		
		formId.submit();
	}
}





function tbWorkingInfoTableRenderBeforeHandler(pGrid) {
	pGrid
			.on("rowdblclick", function(pGrid, pRowIndex, pEventObj) {
				var record = pGrid.getStore().getAt(pRowIndex); // Get the
												// Record
					var e3TbWorkingInfoId = record.get("id");// 获取单元格值
					var e3StationCode = record.get("stationCode");// 获取单元格值
					var e3StationName = record.get('stationName');// 获取单元格值
					var e3FixHour = record.get('fixHour');
					var e3SendHour = record.get('sendHour');
					
					appendTab(e3TbWorkingInfoId,e3StationCode,e3StationName,e3FixHour,e3SendHour);
		            countHour();
			});
}		

function appendTab(e3TbWorkingInfoId,e3StationCode,e3StationName,e3FixHour,e3SendHour)
{
	var index = parent.document.getElementById('count').value;
	
	if('is'==checkTbworkingInfoExist(e3TbWorkingInfoId)){
		
		return ;
	}
		
	
	var workingHourPrice = parent.document.getElementById("workingHourPrice").value;
	
	var tbWorkingInfoTable = parent.document.getElementById('tbWorkingInfoTable');
	
	var tbody_1 = document.createElement("tbody");
	
	var tr_1= document.createElement("tr");
        
    var td_1_0= document.createElement("td");
  
    td_1_0.innerHTML="<input type='hidden' id='tbWorkingInfoId"+index+"' name='tbWorkingInfoId"+index+"' value='"+e3TbWorkingInfoId+"'/>"+"<font color=\"blue\" onclick=\"deleteTd(this);\" style=\"cursor: pointer;\">删除</font>"+"&nbsp;&nbsp;"+"<font color=\"blue\" onclick=\"workingInfoShare('"+index+"')\" style=\"cursor: pointer;\">派工</font>";

    var td_1_1= document.createElement("td");
    
    td_1_1.innerHTML = "<select id='freeSymbol"+index+"' name='freeSymbol"+index+"' onchange='parentCountHour();'><option value='1'>无</option><option value='2'>首保</option><option value='3'>索赔</option><option value='4'>返工</option><option value='5'>服务活动</option><option value='6'>保险理赔</option><option value='7'>暂不维修</option></select>";
    
    var td_1_2= document.createElement("td");
    
    td_1_2.innerHTML = e3StationCode;	
    
    var td_1_3= document.createElement("td");
    
    td_1_3.innerHTML = "<input type='text' id='stationName"+index+"' name='stationName"+index+"' value='"+e3StationName+"' size='10' maxlength='20' onfocus='this.select();'/>"; 
    
    var td_1_4= document.createElement("td");
    
    td_1_4.innerHTML = "<input type='text' id='fixHour"+index+"' name='fixHour"+index+"' value='"+e3FixHour+"' onblur='parentCountHour();' size='10'/>";
    
    var td_1_5= document.createElement("td");
    
    td_1_5.innerHTML = "<input type='text' id='workingHourTotal"+index+"' name='workingHourTotal"+index+"' value='"+formatFloat(parseFloat(workingHourPrice)*parseFloat(e3FixHour),2)+"' readonly='true' size='10'/>";
    
    var td_1_6= document.createElement("td");
    
    td_1_6.innerHTML = "<input type='text' id='sendHour"+index+"' name='sendHour"+index+"' value='"+e3SendHour+"' onblur='parentCountHour();' size='10'/>";
    
    var td_1_7= document.createElement("td");
    
    td_1_7.innerHTML = "<input type='hidden' id='fixPersonIds"+index+"' name='fixPersonIds"+index+"'/>"+"<input type='text' id='fixPersons"+index+"' name='fixPersons"+index+"' readonly='true'/>";
    
    
    //var td_1_8= document.createElement("td");
    
    //td_1_8.innerHTML = "<select id='zl"+index+"' name='zl"+index+"'><option value=''></option><option value='W'>索赔W</option><option value='P'>保险P</option><option value='I'>内部I</option><option value='C'>用户付费C</option></select>";
    
    
    var td_1_9= document.createElement("td");
    
    //td_1_9.innerHTML = "<select id='xmlx"+index+"' name='xmlx"+index+"'><option value=''></option><option value='新车准备'>新车准备</option><option value='首保'>首保</option><option value='定期保养'>定期保养</option><option value='保修'>保修</option><option value='正常维修'>正常维修</option><option value='保险维修'>保险维修</option><option value='召回'>召回</option><option value='服务营销'>服务营销</option><option value='网点内部返工'>网点内部返工</option><option value='索赔帐续保'>索赔帐续保</option><option value='新车PDI'>新车PDI</option><option value='非索赔帐续保'>非索赔帐续保</option><option value='其他'>其他</option><option value='自理事故'>自理事故</option></select>";
    td_1_9.innerHTML = "<select id='wxlx"+index+"' name='wxlx"+index+"'><option value=''></option><option value='钣金'>钣金</option><option value='油漆'>油漆</option><option value='机修'>机修</option><option value='电修'>电修</option><option value='外包'>外包</option><option value='美容装饰'>美容装饰</option><option value='新车PDI'>新车PDI</option></select>";
    
    var td_1_8= document.createElement("td");
    
    var s_8 = "";
    
    if(''!=pt){
    	
    	s_8 = "<select id='projectType"+index+"' name='projectType"+index+"'><option value=''></option>";
    	
    	var s_8s = pt.split('#');
    	
    	for(var p = 0 ; p < s_8s.length; p++){
    		
    		if(''==s_8s[p]) continue;
    		
    		var id = s_8s[p].split("&")[0];
    		
    		var ptv = s_8s[p].split("&")[1];
    		
    		s_8 += "<option value=" + id + ">" + ptv+ "</option>";
    		
    	}
    	
    	
    	s_8 += "</select>";
    }
    
    td_1_8.innerHTML = s_8;
    
    
    tbWorkingInfoTable.appendChild(tbody_1);
    
    tbody_1.appendChild(tr_1);
    
    tr_1.appendChild(td_1_0);
    
    tr_1.appendChild(td_1_1);
    
    tr_1.appendChild(td_1_2);
    
    tr_1.appendChild(td_1_3);
    
    tr_1.appendChild(td_1_4);
    
    tr_1.appendChild(td_1_5);
    
    tr_1.appendChild(td_1_6);
    
    tr_1.appendChild(td_1_7);
    
    tr_1.appendChild(td_1_8);
    
    tr_1.appendChild(td_1_9);

    setReadOnly();
    
    index++;
    
    var count = parent.document.getElementById('count');
    
    count.value = index;
}

function checkTbworkingInfoExist(currentId)
{
	var count = parent.document.getElementById('count').value;
	
	var flag = 'no';
	
	for(var i =  0 ; i < count ; i++){
		
		var tbWorkingInfoId = parent.document.getElementById('tbWorkingInfoId'+i); 
		
		if(null==tbWorkingInfoId){
			continue;
		}
		
		if(currentId==tbWorkingInfoId.value){
			flag = 'is'; 
			break;
		}
	}
	
	if(flag=='is'){
		if(confirm('该工位已经添加,确定继续添加？')){
			return 'no';
		}
		else{
			return 'is';
		}
	}
	
	return 'no';
}
//主单统计信息
function parentCountHour(){
	
	var count = document.getElementById('count').value;
	
	var workingHourPrice = document.getElementById("workingHourPrice");
	
	var fixHourCount = document.getElementById('fixHourCount'); 
	
	var fixHourMoneyCount = document.getElementById('fixHourMoneyCount'); 
	
	var sendHourCount = document.getElementById('sendHourCount'); 
	
	var fixHourCountValue = 0.0;
	
	var fixHourMoneyCountValue = 0.0;
	
	var sendHourCountValue = 0.0;
	
	for(var i =  0 ; i < count ; i++){
		
		var freeSymbol = document.getElementById('freeSymbol'+i);
		
		var fixHour = document.getElementById('fixHour'+i);
		
		var workingHourTotal = document.getElementById('workingHourTotal'+i);
		
		var sendHour = document.getElementById('sendHour'+i);
		
		if(null==fixHour){
			continue;
		}
		
		if(!isNumber(fixHour.value))
		{
			alert('请填写正确的工时信息');
			
			fixHour.focus;
			
			break;
		}
		if(!isNumber(sendHour.value))
		{
			alert('请填写正确的派工信息');
			
			sendHour.focus;
			
			break;
		}
		
		
		
		workingHourTotal.value = formatFloat(parseFloat(workingHourPrice.value)*parseFloat(fixHour.value),2)
		
		fixHourCountValue += parseFloat(fixHour.value);
		
		if(freeSymbol.value==1){
			fixHourMoneyCountValue += parseFloat(workingHourTotal.value)
		}
		
		sendHourCountValue += parseFloat(sendHour.value)
		
	}
	
	fixHourCount.value = fixHourCountValue;
	
	fixHourMoneyCount.value = fixHourMoneyCountValue;
	
	sendHourCount.value = sendHourCountValue;
}

//统计信息--工时,派工
function countHour(){
	
	var count = parent.document.getElementById('count').value;
	
	var fixHourCount = parent.document.getElementById('fixHourCount'); 
	
	var fixHourMoneyCount = parent.document.getElementById('fixHourMoneyCount'); 
	
	var sendHourCount = parent.document.getElementById('sendHourCount'); 
	
	var fixHourCountValue = 0.0;
	
	var fixHourMoneyCountValue = 0.0;
	
	var sendHourCountValue = 0.0;
	
	for(var i =  0 ; i < count ; i++){
		
		var fixHour = parent.document.getElementById('fixHour'+i);
		
		var workingHourTotal = parent.document.getElementById('workingHourTotal'+i);
		
		var sendHour = parent.document.getElementById('sendHour'+i);
		
		if(null==fixHour){
			continue;
		}
		if(!isNumber(fixHour.value))
		{
			alert('请填写正确的工时信息');
			
			fixHour.focus;
			
			break;
		}
		if(!isNumber(sendHour.value))
		{
			alert('请填写正确的派工信息');
			
			sendHour.focus;
			
			break;
		}
		fixHourCountValue += parseFloat(fixHour.value);
		
		fixHourMoneyCountValue += parseFloat(workingHourTotal.value)
		
		sendHourCountValue += parseFloat(sendHour.value)
		
	}
	
	fixHourCount.value = fixHourCountValue;
	
	fixHourMoneyCount.value = fixHourMoneyCountValue;
	
	sendHourCount.value = sendHourCountValue;
}


function deleteTd(obj)
{
	if(confirm('确定删除该条工位？')){
		
		obj.parentNode.parentNode.removeNode(true);
		parentCountHour();
	}
}

function setReadOnly()
{
	for(var i = 0 ; ; i++)
	{
		var fixHour = parent.document.getElementById('fixHour'+i);
		
		//var workingHourPrice = parent.document.getElementById('workingHourTotal'+i);
		
		var sendHour = parent.document.getElementById('sendHour'+i);
		
		if(null==fixHour||null==sendHour){
			break;
		}
		
		fixHour.readOnly = isReadOnly;
		
		//workingHourPrice.readOnly = isReadOnly;
		
		sendHour.readOnly = isReadOnly;
	}
}

function workingInfoShare(index){
	
	var freeSymbol = document.getElementById("freeSymbol"+index);
	
	var fixHour = document.getElementById("fixHour"+index);
	
	var workingHourPrice = document.getElementById("workingHourTotal"+index);
	
	var sendHour = document.getElementById("sendHour"+index);
	
	var fixPersons = document.getElementById("fixPersons"+index);
	
	var fixPersonIds = document.getElementById("fixPersonIds"+index);
	
	var tbWorkingInfoId = document.getElementById("tbWorkingInfoId"+index);
	
	if(sendHour.value == ''){
		
		alert('请填写正确的派工工时');
	
		return ;
	}
	
	var flag = fixHour.value+';'+workingHourPrice.value+';'+sendHour.value+';'+fixPersons.value+';'+fixPersonIds.value+";"+index;
	
	forwardPageDefine(tbWorkingInfoId.value,'tbFixShareWorkingInfoShareAction.action',flag,'700','700');
}

function changeFixHour(){
	
	var tjjs = document.getElementById("tjjs").value;
	
	if(!isNumber(tjjs)){
		alert('请填写正确的调价基数');
		return;
	}
	
	var count = document.getElementById('count').value;
	
	for(var i =  0 ; i < count ; i++){
		
		var fixHour = document.getElementById('fixHour'+i);
		
		var workingHourTotal = document.getElementById('workingHourTotal'+i);
		
		if(null==fixHour){
			continue;
		}
		if(!isNumber(fixHour.value))
		{
			alert('请填写正确的工时信息');
			
			fixHour.focus;
			
			break;
		}
		
		fixHour.value = formatFloat(parseFloat(fixHour.value) * parseFloat(tjjs),2);
		
		
		workingHourTotal.value = formatFloat(parseFloat(workingHourTotal.value) * parseFloat(tjjs),2);
	}
	
	
	countHour2();
	
	
}

function countHour2(){
	
	var count = document.getElementById('count').value;

	var fixHourCount = document.getElementById('fixHourCount'); 
	
	var fixHourMoneyCount = document.getElementById('fixHourMoneyCount'); 
	
	var sendHourCount = document.getElementById('sendHourCount'); 
	
	var fixHourCountValue = 0.0;
	
	var fixHourMoneyCountValue = 0.0;
	
	var sendHourCountValue = 0.0;
	
	for(var i =  0 ; i < count ; i++){
		
		var fixHour = document.getElementById('fixHour'+i);
		
		var workingHourTotal = document.getElementById('workingHourTotal'+i);
		
		var sendHour = document.getElementById('sendHour'+i);
		
		if(null==fixHour){
			continue;
		}
		if(!isNumber(fixHour.value))
		{
			alert('请填写正确的工时信息');
			
			fixHour.focus;
			
			break;
		}
		if(!isNumber(sendHour.value))
		{
			alert('请填写正确的派工信息');
			
			sendHour.focus;
			
			break;
		}
		fixHourCountValue += parseFloat(fixHour.value);
		
		fixHourMoneyCountValue += parseFloat(workingHourTotal.value)
		
		sendHourCountValue += parseFloat(sendHour.value)
		
	}
	
	fixHourCount.value = fixHourCountValue;
	
	fixHourMoneyCount.value = fixHourMoneyCountValue;
	
	sendHourCount.value = sendHourCountValue;
}