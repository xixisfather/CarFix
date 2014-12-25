<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css" />
<script>
function openURL(pURL){
 parent.rightFrame.location.href = pURL;
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
  //pConfig.width = 100;
  //pConfig.height = 100;  
}

//tree是树对象名.当然这个名字是可以修改的，调用builder的setTreeID(String)方法
//可以修改
function treeRenderBeforeHandler(pTree){

   pTree.on('click', function(node){ 
    alert(node.attributes.resourcePath + "---" + node.attributes.id);
    pTree.setTitle("当前节点:" + node.text);
   });  
    
  pTree.on('onChecked', function(node){ 
     alert( "选种" + node.text);
   });      
  pTree.on('onUnchecked', function(node){ 
     alert( "反选" + node.text);
   });      


  
  Ext.get("checkedNodeBtn").on("click",function(){
  
     /*复选树
     var checkedNodes = pTree.getChecked();//获取所有选种节点
     var texts = "";
	 for(var i = 0; i < checkedNodes.length; i++) {
	   var checkNode = checkedNodes[i];
       texts = texts + checkNode.text + "," ;   
       //alert("viewOrder=" + checkNode.attributes.viewOrder);//checkNode.attributes可以取节点扩展属性
       //alert("checked=" + checkNode.attributes.checked);	   
	 }
	 alert(texts);
      */
  
   /* 普通树*/
    var selectModel= tree.getSelectionModel();
	var selectNode = selectModel.getSelectedNode();
	if ( selectNode == null ){
	  alert('没有选种任何节点！');
	  return;
	}
	alert(selectNode.text + selectNode.id );   
 
  
   });
  

}
function treeRenderAfterHandler(pTree){
 
}
 

</script>
</HEAD>
<BODY> 
<input type="button" value="选种节点" id="checkedNodeBtn" />

<%= request.getAttribute("treeScript") %>

</BODY>
</HTML>



