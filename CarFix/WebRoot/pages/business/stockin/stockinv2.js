
	var grid;			//数据表格
	
	var pageSize = 10;	//分页大小
	
	var ptr;			//分页对象
	
	var sl ;		    //税率对象
	
	var tbmainArray = new  Array();
	
	Ext.onReady(function(){createGrid();});
	
	function createGrid(){
		var sm = new Ext.grid.RowSelectionModel();
		var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
			{header:'配件ID',dataIndex:'tbPartInfoId',hidden:true},
			{header:'配件名称',dataIndex:'partName',sortable:true},
			{header:'配件代码',dataIndex:'partCode',sortable:true},
			{header:'仓库名称',dataIndex:'houseName',sortable:true},
			{header:'车型',dataIndex:'modelName',sortable:true},
			{header:'产地',dataIndex:'productArea',editor : new Ext.form.TextField({allowBlank: false}),sortable:true},
			{header:'计量单位',dataIndex:'unitName',sortable:true},
	        {header:'数量',dataIndex:'partQuantity',editor : new Ext.form.NumberField({allowBlank: false}),sortable:true},
	        {header:'不含税成本价',dataIndex:'price',editor : new Ext.form.NumberField({allowBlank: false}),sortable:true},
	        {header:'含税单价',dataIndex:'sellPrice',editor : new Ext.form.NumberField({allowBlank: false}),sortable:true},
			{header:'操作',dataIndex:'operation',renderer:function(value){ 
				return "<span style='color:blue;style:cursor;' onclick=removeObj(); >删除</span>"
				}
			}
	    ]);
		
				
		
		ptr = new Ext.PagingToolbar({
			pageSize : pageSize,   	//每页显示多少条数据
			store : ds,				//分页控件对应的Store
			displayMsg : '显示第 {0} 条到第 {1} 条记录,一共 {2} 条记录',
			displayInfo : true //非要为true，不然不会显示下面的分页按钮
	    });
	
		grid = new Ext.grid.EditorGridPanel({
		  store : ds,
		  sm : sm,
		  title:'入库配件列表',
		  height:360,
		  width:1100,
		  animCollapse : true,
		  allowDomMove : true,
		  colModel : cm ,
		  clicksToEdit : 1,				//1 代表单击触发 2 代表双击触发
		  bbar : ptr,
		  tbar : [
			{	
				id:'savdj',
				text : "保存单据",
				iconCls : 'addIcon',
				handler : function() {
					insertAction('8000');
				}
			},'', '-', '',{	
				text : "确认入库",
				iconCls : 'addIcon',
				handler : function() {
					insertAction('8002');
				}
			}]
		 });
		
		grid.on({
			afteredit : function(e){
				//alert(e.field+" "+e.row+"  "+e.column+"  "+e.originalValue+"  "+e.value)
				
				var record = ds.getAt(e.row);
				var tbPartInfoId = record.get("tbPartInfoId");
				
				for(var v in data){
					if( data[v][0] != undefined && data[v][0] == tbPartInfoId){
						
						if(e.field == "partQuantity"){
							data[v][7] = e.value;
						}
						if(e.field == "price"){
							data[v][8] = e.value;
							data[v][9] = formatFloat(data[v][8]*sl.value,2);
						}
						if(e.field == "productArea"){
							data[v][6] = e.value;
						}
						if(e.field == "sellPrice"){
							data[v][9] = e.value;
							data[v][8] = formatFloat(data[v][8]/sl.value,2);
						}
						
						break;
					}
				}
				
				/** 计算总金额和数量 **/
				jsTotal();
				
				//ds.load({params:{start:0,limit:pageSize}});
				
				ds.reload();
			}
		});
	
		document.getElementById("partinfocollectionDiv").innerHTML = "";
		
	    ds.load({params:{start:0,limit:pageSize}});
	
		grid.render("partinfocollectionDiv");
		
		sl = document.getElementById("sl");
		
		
		function insertAction(confirm){
			if(ds.getTotalCount() < 1){
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
		
		
			ds.load({params:{start:0,limit:ds.getTotalCount()}});
			for(var i=0 ; i< ds.getCount() ; i++){
				var record = ds.getAt(i);
				var tbPartInfoId = record.get("tbPartInfoId");
				var partQuantity = record.get("partQuantity");
				var productArea = record.get("productArea");
				var price = record.get("price");
				/*
				alert("tbPartInfoId :" + tbPartInfoId +"\n" +
					   "partQuantity :" + partQuantity +"\n" +
					   "price :" + price +"\n"
					 );
					 
					 */
					 
				if(parseFloat(partQuantity) <= 0){
					Ext.MessageBox.alert('温馨提示：', '配件数量比较大于0.', null);
					ds.load({params:{start:0,limit:pageSize}});
					return;
				}
				var inputName = "formMap.pi_"+tbPartInfoId;
				var inputValue = partQuantity+"_"+price+"_"+productArea;
				buildInput("fatherId","hidden",inputName,inputValue);
			}
			
			jsTotal();
			
			document.forms[0].action += "?isConfirm="+confirm;
			document.forms[0].submit();
			
		}
		
	}
	
	
	var data = new Array();
	var ds = new Ext.data.Store({
		proxy: new Ext.ux.data.PagingMemoryProxy(data),//这个地方是扩展的Proxy
		reader: new Ext.data.ArrayReader({}, [
			{name: 'tbPartInfoId',mapping:0},
			{name: 'partName',mapping:1},
			{name: 'partCode',mapping:2},
			{name: 'houseName',mapping:3},
			{name: 'modelName',mapping:4},
			{name: 'unitName',mapping:5},
			{name: 'productArea',mapping:6},
			{name: 'partQuantity',mapping:7},
			{name: 'price',mapping:8},
			{name: 'sellPrice',mapping:9},
			{name: 'operation',mapping:10}
		])
	});
	
	/**添加配件入购物车**/
	function addObj(partInfoObj){
		//data.unshift(['100','test100','tttt100','desss100']);
		
		
		/** 防止重复添加 **/
		for(var i=0 ; i< ds.getCount() ; i++){
			var record = ds.getAt(i);
			var tbPartInfoId = record.get("tbPartInfoId");
			if(partInfoObj.tbPartInfoId == tbPartInfoId){
				Ext.MessageBox.alert('温馨提示：', '配件不能重复添加', null);
				return;
			}
		}
		
		/** 添加配件 **/
		data.unshift([
		   partInfoObj.tbPartInfoId,									//配件ID
	       partInfoObj.partName,										//配件名称
	       partInfoObj.partCode,										//配件代码
	       partInfoObj.houseName,										//仓库名称
	       partInfoObj.modelName,										//车型
	       partInfoObj.unitName,										//计量单位
	       partInfoObj.productArea,										//产地
	       partInfoObj.partQuantity,									//入库数量
	       partInfoObj.costPrice,										//成本价
	       formatFloat(partInfoObj.costPrice*sl.value,2)				//含税价
	     ]
	    );
		
		/** 数据重载 **/
		ds.load({params:{start:0,limit:pageSize}});
	}
	
	
	function removeObj(){
	
		var selectedRow = grid.getSelectionModel().getSelected();
		var tbPartInfoId = selectedRow.get("tbPartInfoId");
		
		for(var v in data){
			if( data[v][0] != undefined &&  data[v][0] == tbPartInfoId ){
				data.remove(data[v]);
				break;
			}
			
		}
		
		ds.load({params:{start:0,limit:pageSize}});
	
		
	}
	
	
	
	function buildInput(fatherId,type,name,value){
		
		
		var fatherDiv = document.getElementById(fatherId);
		
		var newIpt = document.createElement("input");
		
		newIpt.type = type;
		
		newIpt.name = name;
		
		newIpt.value = value;
		
		fatherDiv.appendChild(newIpt);
		
	}
	
	
	
	function addPartInfo(partInfoObj , flag){
		
		addObj(partInfoObj);
	}
	
	
	function jsTotal(){
	
		if(sl == undefined)
			sl = document.getElementById("sl");
	
		var totalQuantity = 0;
		var totalPrice = 0;
		
		for(var v in data){
			if( data[v][0] != undefined ){
			
				var partQuantity =  data[v][7] ;
				var price = data[v][8] ;
				
				totalQuantity += parseFloat(partQuantity);
				totalPrice += parseFloat(partQuantity) * price;
			}
		}
		
		document.getElementById("totalQuantity").value = formatFloat(totalQuantity,2);
		document.getElementById("totalPrice").value = formatFloat(totalPrice,2);
		
		document.getElementById("totalnumspan").innerHTML = formatFloat(totalQuantity,2);
		document.getElementById("totalpricespan").innerHTML = formatFloat(totalPrice,2);
		document.getElementById("totalratepricespan").innerHTML = formatFloat(totalPrice*sl.value,2);
		
	}
	
	function openPeoWin(userId,userRealName){
		var props = userId+","+userRealName;
		showCommonWin('findAllTmUserAction.action','职工列表',575,355,props,null);
	}
	
	