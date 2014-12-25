var partInfoArray = new Array() 			//dataGrid 数据容器
var win = null;								//配件列表窗口对象
var grid = null;							//dataGrid对象
var store = null;							//数据仓库
var stockId = "";							//出库单ID
//打开配件列表窗口
function showWin(){
	var url = "chooseTbPartInfoSellAction.action?typeName="+encodeURIComponent(stockOutTypeName);
	var customerId = document.getElementById("customerId");
	if(customerId.value != ""){
		url += "&customerId="+customerId.value;
	}
	
		 win = new Ext.Window({
		   title : '配件列表',
		    maximizable : true,
		    maximized : true,
		   width : 750,
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
	

	//计算总数量和总金额
	var totalnum = 0 ;			//合计数量
	var totalprice = 0;			//合计金额
	for(var i=0; i<partInfoArray.length; i++){
		var pi = partInfoArray[i];
		//配件数量
		var num = parseFloat(partInfoArray[i][pi.length-8]);
		//累加配件数量
		totalnum =parseFloat(totalnum)+ num;
		//免费标志则不进行总金额的累加
		if(partInfoArray[i][pi.length-1] == '无'){
			//报出库价格小计累加
			totalprice = parseFloat(partInfoArray[i][pi.length-4])+totalprice;
		
		}
		
	}
	document.getElementById("totalnumspan").innerHTML = Math.round(totalnum*100)/100;
	document.getElementById("totalpricespan").innerHTML = Math.round(totalprice*100)/100;
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
           {name: 'price', type: 'float'},
           {name: 'pricetotal', type: 'float'},
           {name: 'projectTypeShow', type: 'string'},
           {name: 'projectType', type: 'string'},
           {name: 'isfree', type: 'string'}
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
            {header: "数量", width: 100, sortable: true, dataIndex:'num'},
            {header: "成本价", width: 100, sortable: true, dataIndex:'costprice'},
            {header: "成本金额", width: 100, sortable: true, dataIndex:'costtotal'},
            {header: stockOutTypeName+"价", width: 100, sortable: true, dataIndex:'price'},
            {header: stockOutTypeName+"金额", width: 100, sortable: true, dataIndex:'pricetotal'},
            {header: "维修项目类型", width: 100, sortable: true,dataIndex:'projectTypeShow'},
            {header: "维修项目类型ID", width: 100, sortable: true,hidden:true, dataIndex:'projectType'},
            {header: "免费标志", width: 100, sortable: true, dataIndex:'isfree'},
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
        width:1100,
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
				text : "确认出库",
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
	var flag = vaildate();
	if(!flag)
		return;
	var partCol = "";
	for(var i=0; i<partInfoArray.length; i++){
		var partInfoId = partInfoArray[i][0];
		var num = partInfoArray[i][partInfoArray[i].length-8];
		var price = partInfoArray[i][partInfoArray[i].length-5];
		var isFree = partInfoArray[i][partInfoArray[i].length-1];
		//var freeVal = isFree == '是'?true:false;
		var freeVal = getFreeVal(isFree);
		var projectType = partInfoArray[i][partInfoArray[i].length-2]!=""?partInfoArray[i][partInfoArray[i].length-2]:"null";
		var paramStr = partInfoId+":"+num+":"+price+":true:"+freeVal+":"+projectType+":";
		partCol += paramStr+",";
	}
	document.getElementById("partCol").value = partCol;
	if(partInfoArray.length < 1){
		Ext.MessageBox.alert('温馨提示：', '配件内容不能为空.', null);
		return;
	}
	if( document.getElementById("stockOutDate").value == ""){
		Ext.MessageBox.alert('温馨提示：', '销售日期不能为空.', null);
		return;
	}
	document.forms[0].action += "?isConfirm="+confirm;
	document.forms[0].submit();
	
}

Ext.onReady(createGrid);		//初始化操作