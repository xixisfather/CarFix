var partInfoArray = new Array() 			//dataGrid 数据容器
var win = null;								//配件列表窗口对象
var grid = null;							//dataGrid对象
var store = null;							//数据仓库
var stockInId = "";							//入库单ID
//打开配件列表窗口
function showWin(){
	var sl = document.getElementById("sl");
	var url = "tbPartInfoChooseAction.action?sl="+sl.value;
	
		 win = new Ext.Window({
		   title : '配件列表',
		   maximizable : true,
		   maximized : true,
		   width : 1100,
		   height : 400,
		   // autoScroll : true,
		   // bodyBorder : true,
		   // draggable : true,
		   isTopContainer : true,
		   modal : true,
		   resizable : false,
		   contentEl : Ext.DomHelper.append(document.body, {
		    tag : 'iframe',
		    style : "border 0px none;scrollbar:true",
		    src : url,
		    height : "100%",
		    width : "100%"
		   })
		  })
	
	  win.show();
	  
	  partInfoChooseUrl = url;
}

// 删除grid某行
function deleteRow(){
	
	var selectedRow = grid.getSelectionModel().getSelected();
	var id = selectedRow.get("id");
	if (selectedRow){
		store.remove(selectedRow);
	} 
	var ttArr = new Array();
	for(var i=0; i<partInfoArray.length; i++){
		var partInfoId = partInfoArray[i][0];
		if(partInfoId == id) continue;
		ttArr.push(partInfoArray[i])
	}
	
	partInfoArray = ttArr;
}

//创建dataGrid组件
function createGrid(){
	document.getElementById("partinfocollectionDiv").innerHTML = "";
	// 创建dataGrid存储箱
	store = new Ext.data.SimpleStore({
        fields: [
           {name: 'id', type: 'string'},
           {name:'stortHouseId',type:'string'},
           {name: 'houseName', type: 'string'},
           {name: 'partName', type: 'string'},
           {name: 'typeName', type: 'string'},
           {name: 'storeQuantity', type: 'string'},
           {name: 'num', type: 'float'},
           {name: 'costprice', type: 'float'},
           {name: 'costtotal', type: 'float'},
           {name: 'rateprice', type: 'float'},
           {name: 'ratetotal', type: 'float'}
        ]
    });
    //装载数据
    store.loadData(partInfoArray);

   	// 创建dataGrid
    grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {id:'id',header: "id", sortable: true, hidden:true,	dataIndex: 'id'},
            {header: "仓库id",  hidden:true,sortable: true, dataIndex:'storeHouseId'},
            {header: "仓库", width: 100, sortable: true, dataIndex:'houseName'},
            {header: "配件名称", width: 100, sortable: true, dataIndex:'partName'},
            {header: "配件类型", width: 100, sortable: true, dataIndex:'typeName'},
            {header: "库存", width: 50, sortable: true, dataIndex:'storeQuantity'},
            {header: "入库数量", width: 100, sortable: true, dataIndex:'num'},
            {header: "税前单价", width: 100, sortable: true, dataIndex:'costprice'},
            {header: "税前小计", width: 100, sortable: true, dataIndex:'costtotal'},
            {header: "税后单价", width: 100, sortable: true, dataIndex:'rateprice'},
            {header: "税后小计", width: 100, sortable: true, dataIndex:'ratetotal'},
            {
				header : "操作",
				width : 50,
				dataIndex : 'operation',
				align : 'center',
				renderer : function(value, cellmeta, record, rowIndex, columnIndex,st) {
						return "<span style=cursor:pointer onclick=deleteRow() ><font color='blue'>删除</font></span>" 
						}
				}
        ],
        stripeRows: true,
        autoExpandColumn: 'id',
        height:350,
        width:950,
        title:gtitle,
        tbar : [
			{	
				id:'savdj',
				text : "保存单据",
				iconCls : 'addIcon',
				handler : function() {
					addPartCollection("8000");
				}
			},'', '-', '',{	
				text : "确认入库",
				iconCls : 'addIcon',
				handler : function() {
					addPartCollection("8002");
				}
			},'', '-', '',{	
				id : 'barpj',
				text : '添加配件',
				iconCls : 'addIcon',
				handler : function() {
					showWin();
				}
			}]
    });
    //显示dataGrid
    grid.render('partinfocollectionDiv');
}


//添加入库单
function addPartCollection(confirm){
	
	var partCol = "";
	for(var i=0; i<partInfoArray.length; i++){
		var partInfoId = partInfoArray[i][0];							//配件id
		var price = partInfoArray[i][partInfoArray[i].length-4];		//税前单价
		var num = partInfoArray[i][partInfoArray[i].length-5];			//配件数量
		var paramStr = partInfoId+":"+price+":"+num;
		partCol += paramStr+",";
	}
	
	document.getElementById("partCol").value = partCol;
	if(partInfoArray.length < 1){
		Ext.MessageBox.alert('温馨提示：', '配件内容不能为空.', null);
		return;
	}
	
	if( document.getElementById("customerCode").value == ""){
		Ext.MessageBox.alert('温馨提示：', '必须选择供应商.', null);
		return;
	}
	
	if( document.getElementById("arriveDate").value == ""){
		Ext.MessageBox.alert('温馨提示：', '到货日期不能为空.', null);
		return;
	}
	
	document.forms[0].action += "?isConfirm="+confirm;
	document.forms[0].submit();
	
	/*
	if(confirm ==  "8000"){
		//保存单据
		Ext.getCmp('savdj').disable();
		Ext.getCmp('barpj').disable();
		savestock(confirm);
	}
	if(confirm ==  "8002"){
		//单据入库 -- 如果之前保存过了单据则进行更新库存操作
		if(stockInId == "")
			savestock(confirm);
		else
			updatestock();
		
	}
	*/
}

/*保存入库*/
function savestock(confirm){
	var url = ctx+"/"+insertAction+".action?";
	var pars = "isConfirm="+confirm+"&";
	pars+=Form.serialize($(insertAction));
	var addAjax = new Ajax.Request(
	url,
	{method:'post',parameters: pars, onComplete:savecallback}
	);
	
}

function savecallback(originalRequest){
	var tminfo = originalRequest.responseText.split(',');
	stockInId  = tminfo[0];
	var suc = tminfo[1];
	
	if(suc == parseInt("8002")){
		window.location.href = ctx+"/"+initPageAction+".action";
	}
}
/*更新入库*/
function updatestock(){
	var url = ctx+"/"+updateAction+".action?";
	var pars = "id="+stockInId;
	var addAjax = new Ajax.Request(
	url,
	{method:'post',parameters: pars, onComplete:updatecallback}
	);
}

function updatecallback(originalRequest){
	stockInId = "";
	window.location.href = ctx+"/"+initPageAction+".action";
}

Ext.onReady(createGrid);		//初始化操作