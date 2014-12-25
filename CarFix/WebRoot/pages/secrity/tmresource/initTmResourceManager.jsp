<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
 	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
<script>
function openURL(pURL){
  parent.mainFrame.location.href  = pURL;
}
function goHome(pURL){
 top.location.href = pURL;
}  


	var tmResourceVar = {};
    function treeRenderAfterHandler(pTree){
       var viewport = new Ext.Viewport({
            layout:'border',
            items:[
                {
                    id : 'treeDiv',
                    contentEl : 'tree',
                    region:'west',
                    split:true,
                    autoScroll:true,
                    collapsible: true,
                    width: 200,
					minSize: 175,
					maxSize: 400,
					margins:'0 0 0 0',
					layout:'accordion'
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

           // Ext.getCmp("treeDiv").doLayout();
            Ext.getCmp("treeDiv").on("resize",function(pPanel,pWidth,pHeight){
              pTree.setHeight(pHeight-3);
              pTree.setWidth(pWidth-2);
            });
            pTree.setHeight(Ext.getCmp("treeDiv").getInnerHeight()-3);
            pTree.setWidth(Ext.getCmp("treeDiv").getInnerWidth()-2);
             
        }
        
        
        
       	//当节点对象和树对象构造完了、执行render之前，会调用该方法
		//所以可以在这做函数注册处理.
		function treeConfigHandler(pConfig){
		/*
		  pConfig.rootVisible = true;
		  pConfig.containerScroll = false;
		  pConfig.autoScroll = false;
		  pConfig.bodyStyle = '';
		  pConfig.ddScroll = false;
		  */
		  
		  pConfig.autoHeight = false;
		  pConfig.autoWidth = false;
		  //pConfig.height = 400;  
		  //pConfig.width = 400;
		}
		
		//构建右键菜单
		function treeRenderBeforeHandler(pTree){
		 // pTree.expandAll();
		
		  var selectedNode;//用来记录当前右键选种的书节点  
		  var rightClick = new Ext.menu.Menu( {
		                id : 'rightClickCont',
		                items : [ 
		                {
		                    id:'addNode',
		                    text : '添加节点',
		                    handler : function(){
		                    	document.getElementById("mainFrame").src = " <%= request.getContextPath() %>/tmResourceForwardPageAction.action?&nodeName="+encodeURIComponent(tmResourceVar.resourceName)+"&parentId="+tmResourceVar.id;
		                    }
		                    
		                },
		                {
		                    id:'editNode',
		                    text : '修改节点',
		                    handler : function(){
		                      document.getElementById("mainFrame").src = " <%= request.getContextPath() %>/tmResourceForwardPageAction.action?&id="+tmResourceVar.id;
		                    }
		                    
		                },                  
		                {
		                    id:'delNode',
		                    text : '删除节点',
		                    handler : function(){
		                    	Ext.MessageBox.confirm("提示","是否删除该节点以及该节点下所有儿子节点?",function (id){
                  					 if(id == "yes")
                  					 	deleteNode(tmResourceVar.id);
		                    	});
		                    }
		                },
		                {
		                    id:'refreshNode',
		                    text : '刷新节点',
		                    handler : function(){
		                    	refreshNode();
		                    }
		                }
		                
		                ]
		            });
		            
		      pTree.on('contextmenu',function(node,pEventObj){  
			      pEventObj.preventDefault();
			      rightClick.showAt(pEventObj.getXY());
			      selectedNode = node;
			      //选中当前节点
			      selectedNode.select();
			      //对象赋值
			      tmResourceVar.id = node.attributes.id;
			      tmResourceVar.parentId = node.attributes.parentId;
			      tmResourceVar.resourceName = node.attributes.resourceName;
			      tmResourceVar.resourcePath = node.attributes.resourcePath;
			      tmResourceVar.resourceDesc = node.attributes.resourceDesc;
			  });
		      
		    pTree.on('beforerender', function(pTree){ 
		      pTree.setTitle("请选择节点!:");
		    });
		
		    pTree.on('click', function(node){ 
		      pTree.setTitle("当前节点:" + node.text);
		      //alert(node.attributes.resourcePath + "---" + node.attributes.id);
		      document.getElementById("mainFrame").src = " <%= request.getContextPath() %>/tmResourceViewAction.action?&id="+node.attributes.id;
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
		
		
		
</script>

</HEAD>
<BODY> 

<!--显示loding区域-->
<DIV id=loading-mask></DIV>
<DIV class=loading-indicator>
 </DIV>

<div id="banner" style="display:block">

</div>

<div id="bodyFrame" style="display:block">
 <iframe name="mainFrame"  scrolling="auto" src="" frameborder="0" width="100%" height="100%"></iframe>
</div>
 
<div id="tree" >
	<%= request.getAttribute("treeScript") %>
</div>




</BODY>
</HTML>  


