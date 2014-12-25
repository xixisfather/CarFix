	var partArray = new Array();
	
	function jsprice(partinfoid){
		var checkquantity = document.getElementById("checkquantity"+partinfoid).value;
		var checkprice = document.getElementById("checkprice"+partinfoid);
		var divquantity = document.getElementById("divquantity"+partinfoid);
		var divprice = document.getElementById("divprice"+partinfoid);
		var partinfo = document.getElementById("partinfo"+partinfoid);
		var costPrice = partinfo.value.split(",")[1];
		var storeQuantity = partinfo.value.split(",")[2];
		
		checkprice.innerHTML = formatFloat(checkquantity*costPrice,2);
		divquantity.innerHTML = formatFloat(storeQuantity-checkquantity,2);
		divprice.innerHTML = formatFloat((storeQuantity-checkquantity)*costPrice,2);
	}
	
	
	function savecheck(confirm){
		
		for(var v=0 ;v<partArray.length; v++ ){
			var partinfoid = partArray[v].id;
			var inputName = "formMap.pi_"+partinfoid;
			var checkquantity = document.getElementById("checkquantity"+partinfoid).value;
			var divquantity = document.getElementById("divquantity"+partinfoid).innerText;
			var inputValue =checkquantity+"_"+divquantity;
			if(inputValue!=null && inputValue != ""){
				buildInput("fatherId","hidden",inputName,inputValue);
			}
		}
		document.forms[0].action += "?isConfirm="+confirm;
		document.forms[0].submit();
	}
	
	function delcheckrow(rowpartid){
		var mainTable = document.getElementById("mainTable");
		var trRow = document.getElementById("TR"+rowpartid);
		mainTable.deleteRow(trRow.rowIndex);
		
		var newArr = new Array();
			
		for(var i=0; i<partArray.length; i++){
			var obj = partArray[i];
			if(obj.id == rowpartid){
				continue;
			}
			newArr.push(obj);
		}
		
		partArray = newArr;
	}
	
	function buildInput(fatherId,type,name,value){
	
	
		var fatherDiv = document.getElementById(fatherId);
		
		var newIpt = document.createElement("input");
		
		newIpt.type = type;
		
		newIpt.name = name;
		
		newIpt.value = value;
		
		fatherDiv.appendChild(newIpt);
		
	}
	
	function openWin(){
		showCommonWin('findAllTbPartInfoAction.action','配件列表',575,355,null,'addPartInfo');
	}
	

	function addPartInfo(){
		var mainTable = document.getElementById("mainTable");
		
	    var mainRow = mainTable.insertRow(); 
	    mainRow.id = "TR"+tbPartInfoObj.tbPartInfoId;
	   
	    var houseNameCell = mainRow.insertCell();
	    var storeLocationCell = mainRow.insertCell();
	    var partCodeCell = mainRow.insertCell();
	    var partNameCell = mainRow.insertCell();
	    var unitNameCell = mainRow.insertCell();
	    var costPriceCell = mainRow.insertCell();
	    var storeQuantityCell = mainRow.insertCell();
	    var factPriceCell = mainRow.insertCell();
	    var checkquantityCell = mainRow.insertCell();
	    var checkpriceCell = mainRow.insertCell();
	    var divquantityCell = mainRow.insertCell();
	    var divpriceCell = mainRow.insertCell();
	    var opCell = mainRow.insertCell();
	    
	    
        houseNameCell.innerHTML = tbPartInfoObj.houseName;
        storeLocationCell.innerHTML = tbPartInfoObj.storeLocation;
        partCodeCell.innerHTML = tbPartInfoObj.partCode;
        partNameCell.innerHTML = tbPartInfoObj.partName;
        unitNameCell.innerHTML = tbPartInfoObj.unitName;
        costPriceCell.innerHTML = tbPartInfoObj.price;
        storeQuantityCell.innerHTML = tbPartInfoObj.storeQuantity;
        factPriceCell.innerHTML = tbPartInfoObj.factPrice;
        
        var ckq = "<input type=\"text\" style=\"width:80px\" onchange=\"jsprice('"+tbPartInfoObj.tbPartInfoId+"');\" id=\"checkquantity"+tbPartInfoObj.tbPartInfoId+"\"  />"
        var ckp = "<span id=\"checkprice"+tbPartInfoObj.tbPartInfoId+"\" >&nbsp;</span>";
        var dqc = "<span id=\"divquantity"+tbPartInfoObj.tbPartInfoId+"\" style=\"color:red\" >&nbsp;</span>";
        var dpc = "<span id=\"divprice"+tbPartInfoObj.tbPartInfoId+"\" style=\"color:red\" >&nbsp;</span> ";
        
        checkquantityCell.innerHTML = ckq;
        checkpriceCell.innerHTML = ckp;
        divquantityCell.innerHTML = dqc;
        divpriceCell.innerHTML = dpc;
        var val = tbPartInfoObj.tbPartInfoId+","+tbPartInfoObj.price+","+tbPartInfoObj.storeQuantity;
        var op = "<span id=\"op"+tbPartInfoObj.tbPartInfoId+"\" style=\"cursor:pointer;color:blue\" onclick=\"delcheckrow('"+tbPartInfoObj.tbPartInfoId+"')\" >删除</span>";
		op  +="<input type=\"hidden\" style=\"width:80px\" id=\"partinfo"+tbPartInfoObj.tbPartInfoId+"\" value=\""+val+"\" />";
		opCell.align= "right";
		opCell.innerHTML += op;
        
        
        var partinfovo = {};
		partinfovo.id = tbPartInfoObj.tbPartInfoId;
		partinfovo.costPrice =  tbPartInfoObj.price;
		partinfovo.storeQuantity =  tbPartInfoObj.storeQuantity;
		partArray.push(partinfovo);
        
	}
	
	
	var tbPartInfoObj = {};