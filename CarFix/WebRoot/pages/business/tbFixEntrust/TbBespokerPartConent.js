var tbmainArray = new Array();


function addPartInfo(tbmainObj){
	var mainTable = document.getElementById("mainTable");
    var mainRow = mainTable.insertRow(); 
    mainRow.id = "TR"+tbmainObj.tbPartInfoId;
    var houseNameCell = mainRow.insertCell();
    var partNameCell = mainRow.insertCell();
    var unitNameCell = mainRow.insertCell();
    var costPriceCell = mainRow.insertCell();
    var quantityCell = mainRow.insertCell();
    var xjCell = mainRow.insertCell();
    xjCell.id = "xj"+tbmainObj.tbPartInfoId;
    var opCell = mainRow.insertCell();
    opCell.align = "center";
    
    houseNameCell.innerHTML = tbmainObj.houseName;
    partNameCell.innerHTML = tbmainObj.partName;
    unitNameCell.innerHTML = tbmainObj.unitName;
    costPriceCell.innerHTML = "<input id='price"+tbmainObj.tbPartInfoId+"' name=pirces value="+tbmainObj.price+" onchange=\"jsprice("+tbmainObj.tbPartInfoId+");\" />";
    quantityCell.innerHTML = "<input id='partQuantity"+tbmainObj.tbPartInfoId+"' name=quantities onchange=\"jsprice("+tbmainObj.tbPartInfoId+");\"  />";
    opCell.innerHTML = "<input type=button value=删除 onclick=\"deleteRow('"+tbmainObj.tbPartInfoId+"');\" />";
    xjCell.innerHTML = "0.00";
}


function jsprice(partId){
		
	var costPrice = document.getElementById("price"+partId);
	var partQuantity = document.getElementById("partQuantity"+partId);
	var xjVal = Math.round(parseFloat(costPrice.value) * parseFloat(partQuantity.value)*100)/100;
	var xj = document.getElementById("xj"+partId);
	xj.innerHTML = xjVal;
	
	
	for(var i=0; i<tbmainArray.length; i++){
		var obj = tbmainArray[i];
		if(obj.tbPartInfoId == partId){
			obj.partQuantity = partQuantity.value;
			obj.price = costPrice.value;
			obj.totalPrice = xjVal;
		}
	}
}


function addFixEntrust(){
	var flag = validate();
	if(!flag)return;
	var str = "";
	var totalPrice = 0;
	for(var i=0; i<tbmainArray.length; i++){
		var obj = tbmainArray[i];
		totalPrice = totalPrice + parseFloat(obj.totalPrice);
		
		str += obj.tbPartInfoId + ":" + obj.partQuantity + ":" + obj.price + ",";
	}
	
	var tp = document.getElementById("totalPrice");
	tp.value = totalPrice;
	
	var partCol = document.getElementById("partCol");
	partCol.value = str;
	document.forms[0].action += "?partCol="+partCol.value+"&totalPrice="+tp.value
	document.forms[0].submit();
}


function validate(){
	var pirces = document.getElementsByName("pirces");
	var quantities = document.getElementsByName("quantities");
	var peoples = document.getElementsByName("peoples");
	for(var i=0; i<pirces.length; i++){
		var pce = pirces[i];
		if(!isNumber(pce.value) || parseFloat(pce.value) <=0 || pce.value == ""){
			pce.focus();
			Ext.MessageBox.alert('温馨提示：', '配件金额输入有误!', null);
			return false;
		} 
	}
	
	for(var i=0; i<quantities.length; i++){
		var pce = quantities[i];
		if(!isNumber(pce.value) || parseFloat(pce.value) <=0 || pce.value == ""){
			pce.focus();
			Ext.MessageBox.alert('温馨提示：', '配件数量输入有误!', null);
			return false;
		} 
	}
	
	var estimateDate = document.getElementById('estimateDate');
	
	if(''==trim(estimateDate.value)){
		Ext.MessageBox.alert('温馨提示：', '预计竣工日期不能为空!', null);
		return false;
	}
	
	var tbCarInfoId = document.getElementById('tbCarInfoId');
	
	if(''==trim(tbCarInfoId.value)){
		Ext.MessageBox.alert('温馨提示：', '请选择正确的车辆信息!', null);
		return false;
	}
	
	
	
	return true;
}


function deleteRow(partInfoId){
	var mainTable = document.getElementById("mainTable");
	var trRow = document.getElementById("TR"+partInfoId);
	mainTable.deleteRow(trRow.rowIndex);
	
	var newArr = new Array();
	
	for(var i=0; i<tbmainArray.length; i++){
		var obj = tbmainArray[i];
		if(obj.tbPartInfoId == partInfoId){
			continue;
		}
		newArr.push(obj);
	}
	
	tbmainArray = newArr;
}