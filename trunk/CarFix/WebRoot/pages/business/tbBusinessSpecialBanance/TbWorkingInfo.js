//全局参数 设置修理工时 派工工时是否可修改
var isReadOnly = false ;

function tbWorkingInfoTableConfigHandler(pConfig) {

	pConfig.tbar = [

	];

	// pConfig.autoExpandColumn='no';
}

function tbWorkingInfoTableE3ConfigHandler(pConfig) {
	pConfig.emptyReload = false;
	// 参数form,pConfig指定form的参数会提交到后台
	pConfig.form = "tbWorkingInfoShowAction";
	pConfig.showLoadingMsg = true;
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
					appendTab(e3TbWorkingInfoId,e3StationCode,e3StationName,e3FixHour);
		            parent.initValue();
			});
}		

function appendTab(e3TbWorkingInfoId,e3StationCode,e3StationName,e3FixHour)
{
	var index = parent.document.getElementById('w_count').value;
	
	var workingHourPrice = parent.document.getElementById("workingHourPrice").value;
				
	var tbWorkingInfoTable = parent.document.getElementById('tbWorkingInfoTable');
	
	var tbody_1 = document.createElement("tbody");
	
	var tr_1= document.createElement("tr");
        
    var td_1_0= document.createElement("td");
  
    td_1_0.innerHTML="<input type='hidden' id='tbWorkingInfoId"+index+"' name='tbWorkingInfoId"+index+"' value='"+e3TbWorkingInfoId+"'/>"+"<font color=\"blue\" onclick=\"deleteTd(this);\" style=\"cursor: pointer;\">删除</font>";

    var td_1_1= document.createElement("td");
    
    td_1_1.innerHTML = "<input type='checkbox' id='freesymbol"+index+"' name='freesymbol"+index+"' onclick='initValue()'/>";
    
    var td_1_2= document.createElement("td");
    
    td_1_2.innerHTML = e3StationCode;	
    
    var td_1_3= document.createElement("td");
    
    //td_1_3.innerHTML = e3StationName;
    td_1_3.innerHTML = "<input type='text' name='stationName"+index+"' id='stationName"+index+"' value='"+e3StationName+"'/>";
    
    var td_1_4= document.createElement("td");
    
    td_1_4.innerHTML = "<input type='text' id='fixHour"+index+"' name='fixHour"+index+"' value='"+e3FixHour+"' onblur='initValue();'/>";
    
    var td_1_5= document.createElement("td");
    
    td_1_5.innerHTML = workingHourPrice;
    
    var td_1_6= document.createElement("td");
    
    td_1_6.innerHTML = "<input type='text' id='fixHourAll"+index+"' name='fixHourAll"+index+"' value='"+formatFloat(parseFloat(workingHourPrice)*parseFloat(e3FixHour),2)+"' readonly='true'/>";
    
    tbWorkingInfoTable.appendChild(tbody_1);
    
    tbody_1.appendChild(tr_1);
    
    tr_1.appendChild(td_1_0);
    
    tr_1.appendChild(td_1_1);
    
    tr_1.appendChild(td_1_2);
    
    tr_1.appendChild(td_1_3);
    
    tr_1.appendChild(td_1_4);
    
    tr_1.appendChild(td_1_5);
    
    tr_1.appendChild(td_1_6);
    
    index++;
    
    var count = parent.document.getElementById('w_count');
    
    count.value = index;
}

function deleteTd(obj)
{
	if(confirm('确定删除该条工位？')){
		
		obj.parentNode.parentNode.removeNode(true);

		parent.initValue();
	}
}
