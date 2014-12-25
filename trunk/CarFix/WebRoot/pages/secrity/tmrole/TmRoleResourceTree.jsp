<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@page import="com.selfsoft.secrity.model.TmUser"%><HTML>
<HEAD>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
 	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
 	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/common.js" charset="UTF-8"></script>
 	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
 	<title>思易汽修管理系统   www.siesoft.net</title>
 	</head>
	<style>
	 * { margin:0 auto; padding:0; border:0;}
	 body { font:12px "宋体"; color:#FFF;}
	 .lable { background:url(<%= request.getContextPath() %>/images/indexpage/top_lable.jpg) no-repeat; width:126px; height:28px; float:left;}
	 .lable p { margin-top:8px;}
	 .menu { background:url(<%= request.getContextPath() %>/images/indexpage/top_lable.jpg) no-repeat; width:126px; height:28px; float:right;}
	 .menu ul { margin:8px 0 0 45px; list-style:none;}
	 .menu li { display:inline;}
	 .menu a { float:left; text-decoration:none; padding-left:2px;}
	 .menu a:hover {text-decoration:none;}
	 .menu a span { display:block; padding-right:10px; color:#FFF; }
	 .menu a:hover span { color:#004c7e; }
	 .menu_left { background:url(<%= request.getContextPath() %>/images/images/menu_left.jpg) no-repeat; width:155px; height:22px; float:left; margin:3px 0 0 6px; text-align:center;}
	 .menu_left p { margin-top:5px;} 
	 .menu_list { width:100%; height:25px;}
	 .menu_list ul { margin:0; padding:4px 0 0 70px; list-style:none; }
	 .menu_list li { display:inline; }
	 .menu_list a { float:left; text-decoration:none; }
	 .menu_list a span { display:block; padding:4px 10px 0 10px; color:#004c7e; }
	 .menu_list a:hover span { color:#FFF; border:1px solid #004c7e;}
	 .menu_list a:hover { background:url(<%= request.getContextPath() %>/images/images/menu_list.jpg) repeat-x; }
	
	
	
	</style>
	
<script>
	var tabPanel;
	var tabIndex = 0;
	function changeTime(){
	    var curdate=new Date();
	    var year = curdate.getFullYear();
	    var mouth = curdate.getMonth()+1; //month
        var date = curdate.getDate();
        var hour = curdate.getHours();
        var minute  = curdate.getMinutes();
        var second = curdate.getSeconds();
	    var week = curdate.getDay();     
		var weekname = "星期"+"天一二三四五六".split('')[week];
		var formatDate = year+"年"+mouth+"月"+date+"日"+"  "+weekname+"  "+hour+"时"+minute+"分"+second+"秒";
		<%
			String deadDate = (String)request.getSession().getAttribute("deadDateTime");
		
			String companyName = (String)request.getSession().getAttribute("companyNameMain");
			
			TmUser tmUser = (TmUser)request.getSession().getAttribute("tmUser");
		%>

		var deadTime = "<%=companyName%>  操作员:<%=tmUser.getUserName()%> 系统有效期至  <%=deadDate%>";
		document.getElementById("timerTd").innerText = formatDate + '     ' + deadTime;

	}
	setInterval( "changeTime() ",1000);


	var tmResourceVar = {};
    function treeRenderAfterHandler(pTree){
    
    /*
       var viewport = new Ext.Viewport({
            layout:'border',
            items:[
            	{
            		id:"topArea",
                
	 				title:"头部广告位",
	       			region:"north",
	       			height:100,
	       			autoScroll: true,
					margins: "5 5 5 5"
            	},
                {
                    id : 'treeDiv',
                    titlebar: true,
                    title: 'aa' ,
                    contentEl : 'tree',
                    region:'west',
                    split:true,
                    autoScroll:true,
                    collapsible: true,
                    width: 200,
					minSize: 175,
					maxSize: 400,
					margins:'0 0 0 0',
                },
                {
                    region:'center',
                    layout:'border',
                    items : [
                     {
	                    region:'center',
	                    contentEl: 'bodyFrame',
	                    split:true,
	                    autoScroll:true,
	                    collapsible: true,
	                    margins:'0 0 0 0'
                     
                     }
                   ]
                }
            ]});
            
            */
            
            
         var viewport = new Ext.Viewport({

			layout:"border",
			
			items:[
				{
            		id:"topArea",
                	contentEl: 'banner',
	       			region:"north",
	       			height:60,
	       			autoScroll: true,
					margins: "5 5 5 5"
            	},
				{
					region:"center",
					height:500,
					layout:'border',
					items : [
                     {
	                    region:'center',
	                    contentEl: 'bodyFrame',
	                    split:true,
	                    autoScroll:true,
	                    collapsible: true,
	                    margins:'0 0 0 0'
                     
                     }]
					
				},
			
				{
					region:"west",
					title:"功能列表",
					id : 'treeDiv',
                    contentEl : 'tree',
                    split:true,
                    autoScroll:false,
                    width: 150,
					minSize: 10,
					maxSize: 400,
					margins:'0 0 0 0'
				}
			
			]}); 
			
            
            
            
            
            
            
            
            
            
            
            
            

           // Ext.getCmp("treeDiv").doLayout();
            Ext.getCmp("treeDiv").on("resize",function(pPanel,pWidth,pHeight){
              pTree.setHeight(pHeight-3);
              pTree.setWidth(pWidth-2);
            });
            pTree.setHeight(Ext.getCmp("treeDiv").getInnerHeight()-3);
            pTree.setWidth(Ext.getCmp("treeDiv").getInnerWidth()-2);
            //所有节点加载完之后展开所有节点
            //pTree.expandAll();
            //展开第一层节点
            pTree.getRootNode().expandChildNodes();
            
            
            
           var clheight = document.body.clientHeight - 100;
             tabPanel=new Ext.TabPanel({
	            renderTo:"bodyFrame",
	            width:"100%",
	            height:clheight,
	            activeTab:0,//当前激活标签
	            frame:true
	     	});
	     	
	     	addTab(tabPanel,"index","首页", "pages/welcome.jsp",false);
	     	
        }
        
        
        
       	//当节点对象和树对象构造完了、执行render之前，会调用该方法
		//所以可以在这做函数注册处理.
		function treeConfigHandler(pConfig){
		/*
		 
		  pConfig.containerScroll = false;
		  pConfig.autoScroll = false;
		  pConfig.bodyStyle = '';
		  pConfig.ddScroll = false;
		  */
	      pConfig.rootVisible = false;
		  pConfig.autoHeight = false;
		  pConfig.autoWidth = false;
		  //pConfig.height = 400;  
		  //pConfig.width = 400;
		}
		
		//构建右键菜单
		function treeRenderBeforeHandler(pTree){
		  
		  var selectedNode;//用来记录当前右键选种的书节点  
		  
		    pTree.on('beforerender', function(pTree){ 
		      pTree.setTitle("请选择节点!:");
		    });
		
		    pTree.on('click', function(node){ 
		    
		      pTree.setTitle("当前节点:" + node.text);
		      //alert(node.attributes.resourcePath + "---" + node.attributes.id);
		      //document.getElementById("mainFrame").src = " <%= request.getContextPath() %>/tmResourceViewAction.action?&id="+node.attributes.id;
		      if(node.attributes.isLeaf == 1){
		      	 if(node.attributes.resourcePath == ""){
					alert("尊敬的用户，该功能即将开发，敬请期待！")	      	 
		      	 }else{
		    	 	 //document.getElementById("mainFrame").src = " <%= request.getContextPath() %>/pages/"+node.attributes.resourcePath;
		    	 	  addTab(tabPanel,node.attributes.id,node.text, node.attributes.resourcePath,true);
		      	}
		      }
		    });  
		    
		    
		    //双击收起或展开节点
		    pTree.on('dblclick', function(node){ 
		    	node.toggle();
		    });
		    
		}
		
		
		
		//tree是树对象名.当然这个名字是可以修改的，调用builder的setTreeID(String)方法
		//可以修改
		function showSelectedNode(){
		 var selectModel= tree.getSelectionModel();
		 var selectNode = selectModel.getSelectedNode();
		 if ( selectNode == null ){
		   alert('没有选种任何节点！');
		   return;
		 }
		 
		 alert(selectNode.text + selectNode.id );   
		}
		
		//刷新当前节点
		function refreshNode(){
		 var selectModel= tree.getSelectionModel();
		 var selectNode = selectModel.getSelectedNode();
		 if ( selectNode == null ){
		   return;
		 }
		 selectNode.reload();
		}
		
		//刷新当前父辈节点
		function refreshParentNode(){
		 var selectModel= tree.getSelectionModel();
		 var selectNode = selectModel.getSelectedNode();
		 if ( selectNode == null ){
		   return;
		 }
		 var parentNode = selectNode.parentNode;
		 if ( parentNode == null ){
		   return;
		 }
		 parentNode.reload();
		 parentNode.select();
		
		}
		
		//遍历所有节点
		function visitAllNodes(){
		  var root = tree.getRootNode() ;
		  visitNode( root );
		}
		function visitNode(pNode){
			alert( pNode.text );
			var children =  pNode.childNodes;//获取儿子节点
			for(var i=0; i<children.length; i++){
			   var child = children[i];
			   visitNode(child);
			}
		 
		}
		
		function deleteNode(id){
			var url = "<%= request.getContextPath() %>/tmResourceDeleteAction.action?";
			var pars = "id="+id;
			var addAjax = new Ajax.Request(
				url,
				{method:'post',parameters: pars, onComplete:deleteCallback}
				);
			
		}
		
		function deleteCallback(originalRequest){
			if(originalRequest.responseText){
				refreshParentNode();
			}
		}
		
		
		
		//动态添加标签
	    function addTab(tabPanel,tarid,tabTitle, targetUrl,closable) {
	    	if(tabPanel.getItem(tarid) != undefined && tarid != 93){
	    		tabPanel.setActiveTab(tabPanel.getItem(tarid));
	    	}else{
	    		if(tarid == 93){
	    			tarid += tabIndex; 
	    			tabIndex++;
	    		}
		        commAddTab(tabPanel,tarid,tabTitle, targetUrl,closable);
		        var newTab = tabPanel.getItem(tarid);
		        tabPanel.setActiveTab(newTab);
	    	}
	        
	    }
	    
	    function commAddTab(tabPanel,tarid,tabTitle, targetUrl,closable){
	    	tabPanel.add({
	            id:tarid,
	            title: tabTitle,
	            iconCls: 'tabs',
	            html: "<iframe width=100% height=100% src=" + targetUrl + "></iframe>",
	            closable: closable
	        });
	    }
		
		function returnIndex(){
			var newTab = tabPanel.getItem("index");
		    tabPanel.setActiveTab(newTab);
		}
		
</script>

<BODY onload="changeTime();" > 

<!--显示loding区域-->
<DIV id=loading-mask></DIV>
<DIV class=loading-indicator>
 </DIV>

<div id="banner">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="<%= request.getContextPath() %>/images/indexpage/top_bg.jpg" width="289" height="57">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="<%= request.getContextPath() %>/images/indexpage/logo.jpg" /></td>
        </tr>
      </table>
    </td>
    <td background="<%= request.getContextPath() %>/images/indexpage/top_bg.jpg">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
          <td height="29" style="font:12px 宋体" id="timerTd" width="500"></td>
        </tr>
        <tr>
          <td width="50%" height="28">
            <div class="lable">
             
            </div>
          </td>
          <td>
            <div class="menu" style="font:12px 宋体"  >
              <ul>
                <li><a href="#"><span onclick="returnIndex();" >首页</span></a></li>
                <li><a href="#"><span onclick="window.location.href= '<%= request.getContextPath() %>'" >退出</span></a></li>
              </ul>
            </div>
          </td>
        
        </tr>
      </table>
    </td>
  </tr>
</table>

</div>

<div id="bodyFrame" style="display:block">
	<!-- 
	<iframe name="mainFrame"  scrolling="auto" src="<%= request.getContextPath() %>/pages/welcome.jsp" frameborder="0" width="100%" height="100%"></iframe>
	 -->
</div>
<div>
<%= request.getAttribute("treeScript") %>
</div>

<div id="bottom" style="display:block">

</div>

</BODY>
</HTML>  


