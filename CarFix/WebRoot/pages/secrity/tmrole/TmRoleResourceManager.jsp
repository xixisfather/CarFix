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
</HEAD>
	<script type="text/javascript" >
	        var isModify = false;	//用户是否对节点操作过  
			
			Ext.onReady(function(){
				new Ext.Viewport({
					enableTabScroll:true,
					layout:"border",
		 			items:[
		 				{
		 					id:"topArea",
			 				title:"角色信息",
			       			region:"north",
			       			height:100,
			       			contentEl: "roleinfo",
			       			autoScroll: true,
							margins: "5 5 5 5",
							visable:true
			       			
		       			},
		       			{
		       				region:'center',
		       				autoScroll: true,
	                   		contentEl : 'tree'
		       			}
		       			]
				});
			
			});
			
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
			
			
			function treeRenderAfterHandler(pTree){
			 	//所有节点加载完之后展开所有节点
            	pTree.expandAll();
			}
			 
			
			function treeRenderBeforeHandler(pTree){
				pTree.on('onChecked', function(node){ 
			     	//alert( "选种" + node.text);
			     	var spanim = document.getElementById("span"+node.id);
			     	spanim.style.display = "block";
			     	isModify = true;	
			    });      
			  	pTree.on('onUnchecked', function(node){ 
			     	//alert( "反选" + node.text);
			     	var spanim = document.getElementById("span"+node.id);
			     	spanim.style.display = "none";
			     	isModify = true;
			    });
			
				Ext.get("checkedNodeBtn").on("click",function(){
					var url = "<%= request.getContextPath() %>/tmRoleUpdateAction.action?";
					var formpars = Form.serialize($(tmRoleUpdateAction));
					var addAjax = new Ajax.Request(
						url,
						{ method:'post',parameters: formpars,onComplete:function callback(originalRequest){
																			if(originalRequest.responseText){
																		     	var selectIds = "";
																		     	var beginLevelIds = "";
																				if(isModify){
																				     var checkedNodes = pTree.getChecked();//获取所有选种节点
																				     var level  ;
																					 for(var i = 0; i < checkedNodes.length; i++) {
																					   var imstr = "";
																					   var checkNode = checkedNodes[i];
																			   		   var im = document.getElementsByName("im"+checkNode.attributes.id);
																				   		for(var j=0; j<im.length; j++){
																				   			if(im[j].checked)
																				   				imstr += im[j].value+":";	
																				   		}
																					   if(i==0)level = checkNode.attributes.level
																					   if(checkNode.attributes.level <= level ){
																					   		//beginLevelIds += checkNode.attributes.id + ";" +imstr+",";
																					   		beginLevelIds += checkNode.attributes.id + ","
																						    level = checkNode.attributes.level;
																					   }else{
																					       //selectIds += checkNode.attributes.id + ";" +imstr+ "," ;
																					        selectIds += checkNode.attributes.id + ","   
																					   }
																					  
																					 }
																					 //alert(beginLevelIds);
																					 //alert(selectIds);
																					var url = "<%=request.getContextPath() %>/tmRoleResourceInsertAction.action?&topLevelIds="+beginLevelIds+"&levelIds="+selectIds+"&roleId=${param.id}";
																					
																					//alert(url);
																					window.location.href = url;
																				}else{
																					back();
																				}
																				
																			}
																		 }
						}
					);
				
			    
			  });
			}
			
			function back(){
				window.location.href ='<%=request.getContextPath() %>/tmRoleFindAction.action';
			}
			 
	</script>

<BODY> 



<div id="roleinfo" style="display:block" >
	<s:form action="tmRoleUpdateAction.action">
		<s:hidden name="tmRole.id" ></s:hidden>
		<table width="70%"  >
			<tr>
				<td>
					角色代码：
					<s:textfield name="tmRole.roleCode" />
				</td>
				<td>
					角色名称：
					<s:textfield name="tmRole.roleName" />
				</td>
				<td>
					角色说明：
					<s:textfield name="tmRole.roleNote" />
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="button" value="修改" id="checkedNodeBtn" />
					<input type="button" value="返回" onclick="javascript:back();" />
				</td>	
				<td>
				</td>
			</tr>
		</table>
	</s:form>
</div>
<div id="tree" >
	<%= request.getAttribute("treeScript") %>
</div>

</BODY>


</HTML>  


