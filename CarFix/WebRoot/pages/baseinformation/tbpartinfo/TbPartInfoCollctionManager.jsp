<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>TbPartInfoCollctionManager.jsp</title>
	<link rel="stylesheet" type="text/css"
		href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />

	<script type="text/javascript">
		var partInfoArray = new Array() 	//dataGrid 数据容器
		var win = null;						//配件列表窗口对象
		var grid = null;					//dataGrid对象
		var store = null;
		//打开配件列表窗口
		function showWin(){
			var url = "tbPartInfoChooseAction.action";
			
				 win = new Ext.Window({
				   title : '配件列表',
				   // maximizable : true,
				   // maximized : true,
				   width : 750,
				   height : 500,
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
		}
		
		
		function deleteRow(){
			/*
			*/
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
								return "<span style=cursor:pointer onclick=deleteRow() >删除</span>" 
								}
						}
		        ],
		        stripeRows: true,
		        autoExpandColumn: 'id',
		        height:350,
		        width:700,
		        title:'配件集',
		        tbar : [
					{	
						text : '保存配件集',
						iconCls : 'addIcon',
						handler : function() {
							addPartCollection();
						}
					},'', '-', '',{	
						text : '添加配件',
						iconCls : 'addIcon',
						handler : function() {
							showWin();
						}
					},'', '-', '', '', '税率:','<div id=slspan ></div>','', '-', '','','','','','配件集代码:',{
					xtype:'textfield',
					id:'collectionCode',
				    allowBlank :false,
					blankText:'配件集代码不能为空！',
                    invalidText:'输入格式不正确！',
                    msgTarget :'err',
					validator: validateCode
					},'','<span style=color:red id=err ></span>','']
		    });
		    //显示dataGrid
		    grid.render('partinfocollectionDiv');
		}
		
		
		//添加配件集
		function addPartCollection(){
			var partCol = "";
			for(var i=0; i<partInfoArray.length; i++){
				var partInfoId = partInfoArray[i][0];
				var storeHoseId = partInfoArray[i][1];
				var num = partInfoArray[i][partInfoArray[i].length-5];
				//var str = "partInfoId:"+partInfoId+ "\n"+"storeHoseId:"+storeHoseId+"\n"+"num:"+num;
				var paramStr = partInfoId+":"+storeHoseId+":"+num;
				partCol += paramStr+",";
			}
			var collectionCode = document.getElementById("collectionCode");
			if(collectionCode.value == ""){
				alert("配件代码不能为空");
				collectionCode.focus();
				return;
			}
			
			
			document.getElementById("partCol").value = partCol;
			document.getElementById("code").value = collectionCode.value;
			document.forms['insertPartCollectionAction'].submit();
			
		}
		//判断是否为英文字母
		function IsLetter(value){     
	        var str = value.trim();  
	        if(str.length!=0){    
		        reg=/^[a-zA-Z]+$/;     
		        if(!reg.test(str)){    
		            return false;
		        }    
	        }    
	        return true;
		} 
		
		function validateCode(value){
			var bol = IsLetter(value);
			if(bol == false) return false;
			var url = "<%= request.getContextPath() %>/isUniquecollectionCodeAction.action?";
			var pars = "collectionCode="+value;
			var addAjax = new Ajax.Request(
				url,
				{method:'post',parameters: pars, onComplete:callback}
				);
			
		}
		
		function callback(originalRequest){
			
			document.getElementById("err").innerHTML = originalRequest.responseText;
		}		
		
		Ext.onReady(createGrid);
		
		Ext.onReady(showRates);
		
		function showRates(){
			var sl = document.getElementById("sl");
			var slspan = document.getElementById("slspan");
			sl.style.display = "block";
			//slspan.appendChild(sl);
			Ext.DomHelper.insert(slspan,sl);
		}
	</script>
	<body  >
		<s:form action="insertPartCollectionAction.action" >
			<s:hidden name="partCol" id="partCol" ></s:hidden>
			<s:hidden name="code" id="code" ></s:hidden>
		</s:form>
		<div id="partinfocollectionDiv" ></div>
		 合计数量： <span id="totalnumspan" > </span> 税前合计:<SPAN id="totalpricespan" ></SPAN>税后合计:<SPAN id="totalratepricespan" ></SPAN>
		 
		 <select id="sl" style="display:none" >
		 	<s:iterator value="#request.rates" id="r" >
			 	
			 	<s:if test="#r.paramvalue==0.17"><option selected="selected" value="${r.paramvalue+1 }"  >${r.paramvalue }</option></s:if>
		 		<s:if test="#r.paramvalue!=0.17"><option value="${r.paramvalue+1 }" >${r.paramvalue }</option></s:if>
		 	</s:iterator>
		 </select>
	</body> 
</html>
