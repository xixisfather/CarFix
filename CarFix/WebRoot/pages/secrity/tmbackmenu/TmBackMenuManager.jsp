<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="e3t" uri="http://www.jcreate.net/e3/table"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
 	<script type="text/javascript" src="<%= request.getContextPath() %>/js/prototype.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/css/tableIcon.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-base.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-all.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/ext/js/ext-lang-zh_CN.js" charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
	<script type="text/javascript" >
	function openUrl(pURL){
	  document.getElementById("mainFrame").src = pURL;
	}
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
			
			
			
			 
			
		function treeRenderBeforeHandler(pTree){
			 pTree.on('click', function(node){
			    alert("dddd"); 
		     	alert(node.attributes.url);
		     	//document.getElementById("mainFrame").src = " <%= request.getContextPath() %>/tmResourceViewAction.action?&id="+node.attributes.id;
		     });
		
		}
			
			
	</script>
</HEAD>
<BODY> 

<div id="bodyFrame" style="display:block">
 <iframe name="mainFrame"  scrolling="auto" src="" frameborder="0" width="100%" height="100%"></iframe>
</div>
 
<div id="tree" >
	<%= request.getAttribute("treeScript") %>
</div>


</BODY>


</HTML>  


