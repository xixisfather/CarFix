function payMethodChange()
{
	var payPattern = document.getElementById("payPattern");

	var chequeCode = document.getElementById("chequeCode");
	
	if('1'==payPattern.value){
		chequeCode.value = '';
		chequeCode.disabled = true;
	}
	else{
		chequeCode.disabled = false;
	}
	
}

function setZJE()
{
	var ZJE = parent.document.getElementById('ZJE');
	
	var payedAmountParent = parent.document.getElementById('payedAmount');
	
	var balanceTotalAll = document.getElementById("balanceTotalAll");
	
	var payedAmount = document.getElementById("payedAmount");

	balanceTotalAll.value = ZJE.value;
	
	payedAmount.value = payedAmountParent.value;
}

function calcPay()
{
	
	//总金额
	var balanceTotalAll = document.getElementById("balanceTotalAll");

	//本次用结余款
	var usedSaveAmount = document.getElementById("usedSaveAmount");
	
	//本次收款金额
	var shouldPayAmount = document.getElementById("shouldPayAmount");
	
	clearNoNum22(usedSaveAmount,0.00,balanceTotalAll.value);
	
	shouldPayAmount.value = balanceTotalAll.value - usedSaveAmount.value;
	
	parentValueSet();
}

function calPayValidate()
{
	//总金额
	var balanceTotalAll = document.getElementById("balanceTotalAll");

	//本次用结余款
	//var usedSaveAmount = document.getElementById("usedSaveAmount");
	
	//本次收款金额
	var shouldPayAmount = document.getElementById("shouldPayAmount");
	
	clearNoNum22(shouldPayAmount,0.00,balanceTotalAll.value);
	
}

function parentValueSet()
{
	//TAB页
	//var usedSaveAmount = document.getElementById("usedSaveAmount");
	
	var shouldPayAmount = document.getElementById("shouldPayAmount");
	
	var payPattern = document.getElementById("payPattern");
	
	var chequeCode = document.getElementById("chequeCode");
	
	var balanceDeadTime = document.getElementById("balanceDeadTime");
	
	var remark = document.getElementById("remark");
	
	//主页面
	//var usedSaveAmountParent = parent.document.getElementById("usedSaveAmount");
	
	var shouldPayAmountParent = parent.document.getElementById("shouldPayAmount");
	
	var payPatternParent = parent.document.getElementById("payPattern");
	
	var chequeCodeParent = parent.document.getElementById("chequeCode");
	
	var balanceDeadTimeParent = parent.document.getElementById("balanceDeadTime");
	
	var remarkParent = parent.document.getElementById("remark");
	
	//设置值
	//usedSaveAmountParent.value = usedSaveAmount.value ;
	
	shouldPayAmountParent.value = shouldPayAmount.value;
	
	payPatternParent.value = payPattern.value;
	
	chequeCodeParent.value = chequeCode.value;
	
	balanceDeadTimeParent.value = balanceDeadTime.value;
	
	remarkParent.value = remark.value;
}

function initShouldPay()
{
	var balanceTotalAll = document.getElementById('balanceTotalAll');
	
	var shouldPayAmount = document.getElementById("shouldPayAmount");
	
	shouldPayAmount.value = balanceTotalAll.value;
}

function cardZFJEValidate(){
	
	var parentCardZFJE = parent.document.getElementById('cardZFJE');  
	
	var cardZFJE = document.getElementById('cardZFJE');
	
	var aftCardSaving = document.getElementById('aftCardSaving');
	
	var cardSaving = document.getElementById('cardSaving');
	
	var shouldPayAmount = document.getElementById('shouldPayAmount');
	
	if('' == cardZFJE.value){
		return ;
	}
	
	if(!clearNoNum22(cardZFJE,0.00,shouldPayAmount.value)){
		
		return;
		
	}
	
	if(clearNoNum22(cardZFJE,0.00,cardSaving.value)){
		
		var aftCardSavingValue = parseFloat(cardSaving.value) - parseFloat(cardZFJE.value);
		
		aftCardSaving.value = aftCardSavingValue;
		
		parentCardZFJE.value = cardZFJE.value;
	}
	
	
}

function sendMemberCard(){
	
	var date = new Date();

	var time = date.getTime();
	
	var cardNo = document.getElementById('cardNo');
	
	if(null!=cardNo.value&&''!=cardNo.value){
		
		var url = 'tbMembershipCardFindByCardNoAction.action?cardNo=' + cardNo.value + '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : acquireMemberCard,

			asynchronous : true

		});
	}
}

function acquireMemberCard(originalRequest)
{
	var info = originalRequest.responseText.split(',');
	
	//var cardId = document.getElementById('cardId');
	
	var cardZFJE = document.getElementById('cardZFJE');
	
	//var cardType = document.getElementById('cardType');
	
	//var cardSaving = document.getElementById('cardSaving');
	
	//var aftCardSaving = document.getElementById('aftCardSaving');
	
	//var cardPoint = document.getElementById('cardPoint');
	
	var cardNoReal = document.getElementById('cardNoReal');
	
	if(info[0]=='success'){
		
		//cardId.value = info[1];
		
		if(info[2] == cardNoReal.value){
			
			cardZFJE.disabled = false;
			
		}
		else{
			
			cardZFJE.value = 0;
			
			cardZFJE.disabled = true;
			
			alert('会员卡号不匹配');
			
		}
		
		
		//cardType.value = info[2];
		
		//cardSaving.value = info[3];
		
		//cardPoint.value = info[4];
	}
	else{
		
		//cardId.value = ''; 
		
		cardZFJE.disabled = true;
		
		cardZFJE.value = 0;
		
		alert('会员卡号错误');
		
		//cardZFJE.value = '';
		
		//cardType.value = '';
	
		//cardSaving.value = '';
		
		//aftCardSaving.value = '';
		
		//cardPoint.value = '';
	}
}

function sendCardNo(pass)
{
	var date = new Date();

	var time = date.getTime();
	
	var cardNo = document.getElementById('cardNo');
	
	//var cardPassword = document.getElementById('cardPassword');
	
	if(null!=cardNo.value&&''!=cardNo.value&&null!=pass&&''!=pass)
	{
		
		var url = unescape(parent.window.location.href);
		
		//alert(url);
       
		var urlSite = url.split("?")[0] + '?';
		
		var allargs = url.split("?")[1];

		var args = allargs.split("&");
		 
		for(var i=0; i<args.length; i++)
		{
			var arg = args[i].split("=");

			if(arg[0] == 'cardNo' || arg[0] == 'pass'){
				
				continue;
				
			}
			
			else{
				
				urlSite = urlSite + "&" + args[i];
				
			}
		}
		
		parent.window.location.href = urlSite + "&cardNo=" + cardNo.value + "&pass=" + pass;
		
	}

}

function sendCardPassToEncrypt(){
	
	var date = new Date();

	var time = date.getTime();
	
	var cardPassword = document.getElementById('cardPassword');
	
	if(null!=cardPassword.value&&''!=cardPassword.value){
		
		var url = 'tbMembershipCardEncryptAction.action?pass=' + cardPassword.value + '&time=' + time;
		
		var LicenseAjax = new Ajax.Request(url, {
			
			method : 'post',

			onComplete : acquireCardPass,

			asynchronous : true

		});
	}
	
}

function acquireCardPass(originalRequest){
	
	var info = originalRequest.responseText.split(',');
	
	var cardPassword = document.getElementById('cardPassword');
	
	if(info[0] == 'success'){
		
		sendCardNo(info[1]);
	}
	else{
		
		alert("验证会员卡失败,请重新输入密码");
		
		cardPassword.value = '';
	}
	
}

function dhMoneyClick() {
	
	var dhMoney = document.getElementById("dhMoney");
	var dhMoneyShow = document.getElementById("dhMoneyShow");
	var shouldPayAmount = document.getElementById("shouldPayAmount");
	var shouldPayAmountParent = parent.document.getElementById("shouldPayAmount");
	
	if(dhMoney.checked) {
		
		var p_m = shouldPayAmount.value-dhMoneyShow.value;
		
		if(p_m >= 0 ) {
			
			shouldPayAmount.value=shouldPayAmount.value-dhMoneyShow.value;
			
			parent.document.getElementById('dhMoney').value=dhMoneyShow.value;
			
		}
		
		else {
			
			parent.document.getElementById('dhMoney').value=parseInt(shouldPayAmount.value);
			
			shouldPayAmount.value = 0;
			
			
			
		}
		
		
		  
	}
	else {
		shouldPayAmount.value=parseFloat(shouldPayAmount.value)+parseInt(dhMoneyShow.value);
		parent.document.getElementById('dhMoney').value=0;
	}
	
}
