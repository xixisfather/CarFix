var win;
var partInfoChooseUrl;
//增加对象方法 传入跳转的ACTION 页面宽度 页面高度
function addObject(url,width,height) {
	Ext.onReady( function() {

		win = new Ext.Window( {
			title : '操作',
			width : width,
			height : height,
			modal : true,
			maximizable: true,
			resizable : true,
			autoScroll:true,
			contentEl : Ext.DomHelper.append(document.body, {
				tag : 'iframe',
				style : "border 0px none;scrollbar:true",
				src : url,
				height : "100%",
				width : "100%"
			}),
			listeners: {
				close:function(w){
					//关键部分：关闭窗口前先还原,滚动条才不会消失
					w.restore();
				},
				maximize:function(w){   
					//关键部分：最大化后需要将窗口重新定位，否则窗口会从最顶端开始最大化                       
					w.setPosition(document.body.scrollLeft,document.body.scrollTop);
				}
			}
			
		})
		win.show();
		//窗口最大化
		win.maximize();
	});

}
//修改对象方法 传入对象ID 跳转的ACTION 页面宽度 页面高度
function editObject(id,url,width,height) {
	var date = new Date();
	var time = date.getTime();
	Ext.onReady( function() {
		win = new Ext.Window( {
			title : '操作',
			width : width,
			height : height,
			modal : true,
			maximizable: true,
			resizable : true,
			autoScroll:true,
			contentEl : Ext.DomHelper.append(document.body, {
				tag : 'iframe',
				style : "border 0px none;scrollbar:true",
				src : url+'?id='+id+'&time='+time,
				height : "100%",
				width : "100%"
			}),
			listeners: {
				close:function(w){
					//关键部分：关闭窗口前先还原,滚动条才不会消失
					w.restore();
				},
				maximize:function(w){   
					//关键部分：最大化后需要将窗口重新定位，否则窗口会从最顶端开始最大化                       
					w.setPosition(document.body.scrollLeft,document.body.scrollTop);
				}
			}
		})
		win.show();
		//窗口最大化
		win.maximize();
	});
}

// 删除 传入ID与action地址
function deleteObject(id, actionUrl) {

	Ext.MessageBox.confirm('信息', '您确定要删除？', function(btn) {
		if (btn == 'yes') {

			var date = new Date();
			var time = date.getTime();
			var url = actionUrl + '?id=' + id + '&time=' + time;
			var deleteAjax = new Ajax.Request(url, {
				method : 'post',

				onComplete : getDeleteInfo,

				asynchronous : true

			});
		}

	});

}

function getDeleteInfo(originalRequest) {

	var deleteInfo = originalRequest.responseText.split(',');

	if (deleteInfo[1] == 'success') {
		//Ext.MessageBox.alert('信息', '删除成功');
	} else if (deleteInfo[1] == 'failure') {
		Ext.MessageBox.alert('信息', '删除失败,操作异常或数据已被引用');
	} else {
		Ext.MessageBox.alert('信息', '删除失败,操作异常或数据已被引用');
	}
	eval('refresh_' + deleteInfo[0] + '()');
}

//删除 传入ID与action地址
function operateObject(id, actionUrl,msg) {

	Ext.MessageBox.confirm('信息', msg, function(btn) {
		if (btn == 'yes') {

			var date = new Date();
			var time = date.getTime();
			var url = actionUrl + '?id=' + id + '&time=' + time;
			var operateAjax = new Ajax.Request(url, {
				method : 'post',

				onComplete : getOperateInfo,

				asynchronous : true

			});
		}

	});

}

function getOperateInfo(originalRequest) {

	var operateInfo = originalRequest.responseText.split(',');

	if (operateInfo[1] == 'success') {
		//Ext.MessageBox.alert('信息', '删除成功');
	} else if (operateInfo[1] == 'failure') {
		Ext.MessageBox.alert('信息', '操作异常或数据已被引用');
	} else {
		Ext.MessageBox.alert('信息', '操作异常或数据已被引用');
	}
	eval('refresh_' + operateInfo[0] + '()');
}

//跳转页面方法 传入对象ID 跳转的ACTION 跳转标识  页面宽度 页面高度
function forwardPage(id,url,flag,width,height) {
	var date = new Date();
	var time = date.getTime();
	Ext.onReady( function() {
		win = new Ext.Window( {
			title : '操作',
			width : width,
			height : height,
			modal : true,
			maximizable: true,
			resizable : true,
			autoScroll:true,
			contentEl : Ext.DomHelper.append(document.body, {
				tag : 'iframe',
				style : "border 0px none;scrollbar:true",
				src : url+'?id='+id+'&flag='+flag+'&time='+time,
				height : "100%",
				width : "100%"
			})
		})
		win.show();
		//窗口最大化
		win.maximize();
	});
}

//跳转页面方法 传入对象ID 跳转的ACTION 跳转标识  页面宽度 页面高度
function forwardPageDefine(id,url,flag,width,height) {
	var date = new Date();
	var time = date.getTime();
	Ext.onReady( function() {
		win = new Ext.Window( {
			title : '操作',
			width : width,
			height : height,
			modal : true,
			maximizable: true,
			resizable : true,
			autoScroll:true,
			contentEl : Ext.DomHelper.append(document.body, {
				tag : 'iframe',
				style : "border 0px none;scrollbar:true",
				src : url+'?id='+id+'&flag='+flag+'&time='+time,
				height : "100%",
				width : "100%"
			})
		})
		win.show();
	});
}

//将汉字发送至ACTION 参数componentChnId为要发送到汉字的控件ID,componentPinYinId拼音控件ID
function sendChn(componentChnId,componentPinYinId){
	var date = new Date();
	var time = date.getTime();
	var chn = document.getElementById(componentChnId);
	
	if(null==chn){
		alert('汉字控件的ID不存在');
		return;
	}
	if(null==chn.value||''==chn.value){
		return;
	}
	var url = 'acquirePinYinAction.action'+ '?chn=' + chn.value + '&pinYinId='+ componentPinYinId + '&time=' + time;
	var pinYinAjax = new Ajax.Request(url, {
		method : 'post',

		onComplete : acquirePinYin,

		asynchronous : true

	});
}
//返回拼音
function acquirePinYin(originalRequest){
	
	var pinYin = originalRequest.responseText.split(',');
	
	if(null==pinYin||pinYin.length < 2){
		
		Ext.MessageBox.alert('信息', '拼音获取失败,请输入正确的汉字');
	}
	else{
		
		var pinYinId = document.getElementById(pinYin[0]);
		
		pinYinId.value = pinYin[1];
	}
}

//替换字符  参数1-需替换的字符串   参数2-需替换的字符  参数3-替换为字符
function replaceAll(findStr,fromChar,toChar)
{
	var returnStr = '';
	
	for(var i = 0 ; i < findStr.length ; i ++)
	{
		var str = findStr.substr(i,1);
		
		if(str == fromChar)
		{
			str = toChar;
		}
		
		returnStr += str;
	}
	
	return returnStr;
}

//去除空格
function trim(s)
{
	return s.replace(/^\s*/,"").replace(/\s*$/,"");
}

//1.判断select选项中 是否存在Value="paraValue"的Item        
function jsSelectIsExitItem(objSelect, objItemValue) {        
    var isExit = false;        
    for (var i = 0; i < objSelect.options.length; i++) {        
        if (objSelect.options[i].value == objItemValue) {        
            isExit = true;        
            break;        
        }        
    }        
    return isExit;        
}         
   
// 2.向select选项中 加入一个Item        
function jsAddItemToSelect(objSelect, objItemText, objItemValue) {        
	var varItem = new Option(objItemText, objItemValue);      
	objSelect.options.add(varItem);   
}        
   
// 3.从select选项中 删除一个Item        
function jsRemoveItemFromSelect(objSelect, objItemValue) {        
    //判断是否存在        
    if (jsSelectIsExitItem(objSelect, objItemValue)) {        
        for (var i = 0; i < objSelect.options.length; i++) {        
            if (objSelect.options[i].value == objItemValue) {        
                objSelect.options.remove(i);        
                break;        
            }        
        }              
    }    
}    
   
   
// 4.删除select中选中的项    
function jsRemoveSelectedItemFromSelect(objSelect) {        
    var length = objSelect.options.length - 1;    
    for(var i = length; i >= 0; i--){    
        if(objSelect[i].selected == true){    
            objSelect.options[i] = null;    
        }    
    }    
}      
   
// 5.修改select选项中 value="paraValue"的text为"paraText"        
function jsUpdateItemToSelect(objSelect, objItemText, objItemValue) {        
    //判断是否存在        
    if (jsSelectIsExitItem(objSelect, objItemValue)) {        
        for (var i = 0; i < objSelect.options.length; i++) {        
            if (objSelect.options[i].value == objItemValue) {        
                objSelect.options[i].text = objItemText;        
                break;        
            }        
        }           
    }        
}        
   
// 6.设置select中text="paraText"的第一个Item为选中        
function jsSelectItemByValue(objSelect, objItemText) {            
    //判断是否存在        
    var isExit = false;        
    for (var i = 0; i < objSelect.options.length; i++) {        
        if (objSelect.options[i].text == objItemText) {        
            objSelect.options[i].selected = true;        
            isExit = true;        
            break;        
        }        
    }              
    return isExit;  
}   

//清空SELECT项
function jsRemoveAllOption(objSelect){
	 var length = objSelect.options.length - 1;    
	    for(var i = length; i >= 0; i--){    
	        
	    	objSelect.options[i] = null;    
	       
	    }   
}

//选中SELECT中所有ITEM
function jsSelectAllItem(objSelect)
{
	 var length = objSelect.options.length - 1;    
	   
	 for(var i = length; i >= 0; i--){    
	      
	    	objSelect[i].selected = true;  
	        
	 }    
}

//SELECT中是否有ITEM
function jsIsSelectEmpty(objSelect)
{
	var isEmpty = true;
	
	var length = objSelect.options.length;
	
	if(length>0)
	{
		
		isEmpty = false;
	}
	
	return isEmpty;
}

//一个SELECT中的选项赋值给另外个SELECT
function jsSelectSetAll(objSelectFrom,objSelectTo)
{	
	for (var j = 0; objSelectFrom.options.length > j; j++) {
	 	
	 	var varItem = new Option(objSelectFrom.options[j].text,objSelectFrom.options[j].value);      
		
	 	objSelectTo.options.add(varItem);     
	 }
}

//根据车牌号获取车辆信息以及车主信息    车牌号的ID为licenseCode
function sendCarLicense()
{
	var date = new Date();

	var time = date.getTime();
	
	var licenseCode = document.getElementById("licenseCode");
	
	if(null!=licenseCode.value&&''!=licenseCode.value){
		
		var url = 'tbCarInfoFindBylicenseCodeAction.action?licenseCode=' + licenseCode.value + '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : acquireCarInfo,

			asynchronous : true

		});
	}
}

function acquireCarInfo(originalRequest)
{
	var carInfo = originalRequest.responseText.split(',');
	
	if('success'==carInfo[0])
	{
		//车牌号
		var licenseCode = document.getElementById('licenseCode');
		//底盘号
		var chassisCode = document.getElementById('chassisCode');
		//车型
		var tmCarModelTypeId = document.getElementById('tmCarModelTypeId');
		//购车日期
		var purchaseDate = document.getElementById('purchaseDate');
		//发动机号
		var engineCode = document.getElementById('engineCode'); 
		//车主姓名
		var customerName = document.getElementById('customerName');
		//联系人
		var linkMan = document.getElementById('linkMan');
		//电话
		var phone = document.getElementById('phone');
		//手机
		var telphone = document.getElementById('telphone');
		//住址
		var address = document.getElementById('address');
		//客户号
		var customerCode = document.getElementById('customerCode');
		//车辆id
		var tbCarInfoId = document.getElementById('tbCarInfoId');
		//客户ID
		var tbCustomerId = document.getElementById('tbCustomerId');
		
		if(null!=licenseCode){
			licenseCode.value = carInfo[1];
		}
		
		if(null!=chassisCode){
			chassisCode.value = carInfo[2];
		}
		
		if(null!=tmCarModelTypeId){
			tmCarModelTypeId.value = carInfo[3];
		}
		
		if(null!=purchaseDate){
			purchaseDate.value = carInfo[4];
		}
		
		if(null!=engineCode){
			engineCode.value = carInfo[5];
		}
		
		if(null!=customerName){
			customerName.value = carInfo[6];
		}
		
		if(null!=linkMan){
			linkMan.value = carInfo[7];
		}
		
		if(null!=phone){
			phone.value = carInfo[8];
		}
		
		if(null!=telphone){
			telphone.value = carInfo[9];
		}
		
		if(null!=address){
			address.value = carInfo[10];
		}
		
		if(null!=customerCode){
			customerCode.value = carInfo[11];
		}
		
		if(null!=tbCarInfoId){
			tbCarInfoId.value = carInfo[12];
		}
		
		if(null!=tbCustomerId){
			tbCustomerId.value = carInfo[13];
		}
	}
}

//EXT TABPANEL定义
TabPanel=new Object();
TabPanel.create=function(renderToId,height,tabpageArray){
	var itemsArray=new Array(tabpageArray.length);
	
	for(var i=0,len=tabpageArray.length;i<len;i++){
		var tabpage=tabpageArray[i];
		var item=new Object();
		item.title=tabpage.title;
		item.disabled=tabpage.disabled,
		item.layout="fit";
		if(tabpage.id!=null&&tabpage.id!=""){
			item.id = tabpage.id + 'Tab';
			item.html="<iframe name="+tabpage.id+" id="+tabpage.id+" src="+tabpage.url+" padding=0 margin=0 width=100% height=100% frameBorder=0 />";
		}else{
			item.html="<iframe src="+tabpage.url+" padding=0 margin=0 width=100% height=100% frameBorder=0 />";
		}
		itemsArray[i]=item;
	}
	return new Ext.TabPanel({
        renderTo: renderToId,
        activeTab: 0,
        plain:true,
		height:height,
        defaults:{autoScroll: true},
	items:itemsArray});
};
	
//how to use like follows
//<script language="javascript">
//Ext.onReady(function(){
//	TabPanel.create('tabs1',200,[{id:'iframid1',title:'EWO Basic',url:'ewo_basic.html'},{id:'iframid2',title:'Part',url:'ewo_part.html'}]);
//	});
//</script>
//<div id='tabs1' align='center'></div>
//TAB页面获取父页面的表单控件        parent.document.getElementById('表单控件ID')
//父页面获取TAB页面的表单控件        document.getElementById('iframeId').contentWindow.document.getElementById(iframe里面的元素)

//保留小数  val值  p小数位数
function formatFloat(val,p){
	with(Math){  
			return round(val*pow(10,p))/pow(10,p).toFixed(2);  
	}  
}




var extWindow;
/*
*	Ext弹出窗口
*	url:	ActionURL
*	title:  标题
*	width:  窗口宽度
*	height: 窗口高度
*   e3Table: e3table
*/
function showCommonWin(url,title,width,height,properties,e3Table){
	
	url = url+"?properties="+properties+"&e3Table="+e3Table;
	extWindow = new Ext.Window({
	   title : title,
	   width : width,	
	   height : height,
	   autoScroll : true,
	   bodyBorder : true,
	   draggable : true,
	   maximizable: true,
	   modal : true,
	   resizable : true,
	   autoScroll:true,
	   contentEl : Ext.DomHelper.append(document.body, {
	    tag : 'iframe',
	    style : "border 0px none;scrollbar:true",
	    src : url,
	    height : "100%",
	    width : "100%"
	   }),
	   listeners: {
			close:function(w){
				//关键部分：关闭窗口前先还原,滚动条才不会消失
				w.restore();
			},
			maximize:function(w){   
				//关键部分：最大化后需要将窗口重新定位，否则窗口会从最顶端开始最大化                       
				w.setPosition(document.body.scrollLeft,document.body.scrollTop);
			},
			hide:function(w){
				//关键部分：关闭窗口前先还原,滚动条才不会消失
				w.restore();
			}
		}
	 })
	
	 extWindow.show();
	
	//窗口最大化
	extWindow.maximize();
}
//如果不是数字清楚掉，可以设置单位 超出范围讲提示
function clearNoNum22(obj,minVal,maxVal){    
 	//先把非数字的都替换掉，除了数字和.    
     obj.value = obj.value.replace(/[^\d.]/g,"");    
     //必须保证第一个为数字而不是.    
     obj.value = obj.value.replace(/^\./g,"");    
     //保证只有出现一个.而没有多个.    
     obj.value = obj.value.replace(/\.{2,}/g,".");    
     //保证.只出现一次，而不能出现两次以上    
     obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
     
      //保证只能出现两位小数
    var dotPos=obj.value.indexOf(".")+1;
	if(dotPos>0&&(obj.value.length-dotPos>2)){
		obj.value = obj.value.substr(0,dotPos+2);
	}
	var val=parseFloat(obj.value);
	if(!isNaN(val)&&val>maxVal){
		alert("输入数值不能超出："+minVal+"~"+maxVal);
		obj.value=maxVal;
		return false;
	}
	
	return true;
} 

//是否为数字
function isNumber(num){
	
	if(''==trim(num))
	{
		return false;
	}
	if(!isNaN(num)){
		return true;
	}
	else{
		return false;
	}
}

//保存P位小数
function formatFloat(val,p){
	with(Math){  
			return round(val*pow(10,p))/pow(10,p);  
	}  
}

//验证---------------两位正小数
function validatePositiveNumOfTwo(paramValue)
{
	var pattern=/^[0-9]+(.[0-9]{2})?$/;
	
	if(!pattern.test(paramValue))
	{
		return false;
	}
	
	return true;
}

//验证---------------非负小数
function validateNoPositveFloatNum(paramValue)
{
	var pattern=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;

	if(!pattern.test(paramValue))
	{
		return false;
	}
	
	return true;
}
//验证是否为手机号码
function validateTelephone(paramValue)
{
	if(!/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(paramValue))
	{
		return false;
	}
	
	return true;
}
//正实数
function validatePositiveNum(paramValue){
	
	var pattern=/^\d+$/;

	if(!pattern.test(paramValue))
	{
		return false;
	}
	
	return true;
}

//非负小数
function validateNonPositiveNum(paramValue){
	
	var pattern=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;

	if(!pattern.test(paramValue))
	{
		return false;
	}
	
	return true;
}

//正数(包括小数，正数)
function validateAllPositiveNum(paramValue){
	var pattern=/^(([0-9]+[\.]?[0-9]+)|[1-9])$/;

	if(!pattern.test(paramValue))
	{
		return false;
	}
	
	return true;
}

function hasCarAlertContent(carInfoId,alertType){
	var url = 'getCarAlertContentAction.action?carInfoId='+carInfoId+'&alertType=' + alertType;
	var LicenseAjax = new Ajax.Request(url, {
		
		method : 'post',

		onComplete : carAlertCallback,

		asynchronous : true

	});
}

function carAlertCallback(originalRequest){
	var responseTxt = originalRequest.responseText;
	if( responseTxt != null && responseTxt != ""  ){
		//Ext.MessageBox.alert('特殊车辆提醒：',  responseTxt, null);
		 var caWin =new Ext.Window({title:"特殊车辆提醒",
                            width:500,
                            height:550,
                            maximizable:true,
                            html:responseTxt,
                            modal:true
                      });
     	caWin.show();		
	}
	
}

//格式化日期对象
Date.prototype.format = function(format){
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    	format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    	if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

function getFreeVal(freeShowName){
	var freeValue ;
	switch(freeShowName){
		case "无":
	 		freeValue = 1;
	    	break;
	   	case "首保":
	    	freeValue = 2;
	    	break;
	    case "索赔":
	    	freeValue = 3;
	    	break;
	    case "返工":
	    	freeValue = 4;
	    	break;
	    case "服务活动":
	    	freeValue = 5;
	    	break;
	 }
	return freeValue;
}
/*构建免费标志下拉框*/
function buildFreeFlagSelect(id,event,value,disable,name){
	var html = "<select id= \""+id+"\"";
	
	if(event != null && event != "")
		html +=  " onchange=\""+event+"\" ";
	if(disable)
		html += " disabled";
	if(name != null && name != "")
		html +=  " name=\""+name+"\" ";
		
	html += ">";
		
	if(value == 1)
		html+="<option selected=selected value=1 >无</option>";
	else
		html+="<option value=1>无</option>";
	if(value == 2)
		html+="<option selected=selected value=2>首保</option>";
	else
		html+="<option value=2>首保</option>";
	if(value == 3)
		html+="<option selected=selected value=3>索赔</option>"	
	else
		html+="<option value=3>索赔</option>";
	if(value == 4)	
		html+="<option selected=selected value=4>返工</option>";
	else
		html+="<option value=4>返工</option>";
	if(value == 5)
		html+="<option selected=selected value=5>服务活动</option>"	
	else
		html+="<option value=5>服务活动</option>";
	html+="</select>";
	return html;
}

//关闭窗口
function closeWindow()
{
	parent.win.hide();
}


//每张页面初始化操作
Ext.onReady(function(){
		document.onkeydown = check;
});

//禁用回退，刷新 按钮
function check(e) {
    var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    if (((event.keyCode == 8) &&                                                    //BackSpace 
         ((event.srcElement.type != "text" && 
         event.srcElement.type != "textarea" && 
         event.srcElement.type != "password") || 
         event.srcElement.readOnly == true)) || 
        ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||    //CtrlN,CtrlR 
        (event.keyCode == 116) ) {                                                   //F5 
        event.keyCode = 0; 
        event.returnValue = false; 
    }
    return true;
}

function EnterTab()
{
	
	if(event.keyCode==13)
	{
		
		event.keyCode=9;
		
	}   
}

function buildzl(index){
		var zlHtml = "";
		zlHtml += "<select id='zl"+index+"' name='zl"+index+"'>";
		zlHtml += "<option value=''></option>";
		zlHtml += "<option value='索赔W'>索赔W</option>";
		zlHtml += "<option value='保险P'>保险P</option>";
		zlHtml += "<option value='内部I'>内部I</option>";
		zlHtml += "<option value='用户付费C'>用户付费C</option>";
		zlHtml += "</select>";
	
	return zlHtml;
}

function buildxmlx(index){
  		var xmlxHtml = "";
  		xmlxHtml = "<select id='xmlx"+index+"' name='xmlx"+index+"'>";
  		//xmlxHtml += "<option value=''></option><option value='新车准备'>新车准备</option><option value='首保'>首保</option><option value='定期保养'>定期保养</option><option value='保修'>保修</option><option value='正常维修'>正常维修</option><option value='保险维修'>保险维修</option><option value='召回'>召回</option><option value='服务营销'>服务营销</option><option value='网点内部返工'>网点内部返工</option><option value='索赔帐续保'>索赔帐续保</option><option value='新车PDI'>新车PDI</option><option value='非索赔帐续保'>非索赔帐续保</option><option value='其他'>其他</option><option value='自理事故'>自理事故</option>";
  		xmlxHtml += "<option value=''></option><option value='钣金'>钣金</option><option value='油漆'>油漆</option><option value='机修'>机修</option><option value='电修'>电修</option><option value='外包'>外包</option><option value='美容装饰'>美容装饰</option><option value='新车PDI'>新车PDI</option>";
  		xmlxHtml += "</select>";
   	
   		return xmlxHtml;
	
}