package com.selfsoft.business.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;


/**
 * TbBusinessBalance entity. @author MyEclipse Persistence Tools
 */

public class TbBusinessBalance implements java.io.Serializable {

	// Fields

	private Long id;
	private String balanceCode;
	private TbFixEntrust tbFixEntrust;
	private Date bananceDate;
	private Date bananceDateStart;
	private Date bananceDateEnd;
	private TmStockOut tmStockOut;
	private Double workingHourFavourRate;
	private Double workingHourTotalAll;
	private Double fixPartFavourRate;
	private Double fixPartTotalAll;
	private Double solePartFavourRate;
	private Double solePartTotalAll;
	private Double balanceTotalAll;
	private TmUser tmUser;
	private Long oldPartDeal;
	private Long payPattern;
	private String chequeCode;
	private Date balanceDeadTime;
	private String remark;
	private Double saveAmount;
	private Double usedSaveAmount;
	private Double cashAmount;
	private Double payedAmount;
	private Double shouldPayAmount;
	private Long balanceStatus;
	private Integer snNo;
	
	private Set tbBusinessBalanceItems = new HashSet();
	
	private Set tbBusinessSpecialBalances = new HashSet();
	
	private Set tbReceiveFrees = new HashSet();

	/** default constructor */
	public TbBusinessBalance() {
	}

	
	public TbBusinessBalance(Long id,String balanceCode,Date bananceDate,Double balanceTotalAll,Double shouldPayAmount,Double workingHourTotalAll,Double fixPartTotalAll,Double solePartTotalAll) {
		
		this.id = id;
		
		this.balanceCode = balanceCode;
		
		this.bananceDate = bananceDate;
		
		this.balanceTotalAll = balanceTotalAll;
		
		this.shouldPayAmount = shouldPayAmount;
		
		this.workingHourTotalAll = workingHourTotalAll;
		
		this.fixPartTotalAll = fixPartTotalAll;
		
		this.solePartTotalAll = solePartTotalAll;
		
	}


	public Set getTbReceiveFrees() {
		return tbReceiveFrees;
	}


	public void setTbReceiveFrees(Set tbReceiveFrees) {
		this.tbReceiveFrees = tbReceiveFrees;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBalanceCode() {
		return this.balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public Date getBananceDate() {
		return this.bananceDate;
	}

	public void setBananceDate(Date bananceDate) {
		this.bananceDate = bananceDate;
	}

	public Date getBananceDateStart() {
		return bananceDateStart;
	}

	public void setBananceDateStart(Date bananceDateStart) {
		this.bananceDateStart = bananceDateStart;
	}

	public Date getBananceDateEnd() {
		return bananceDateEnd;
	}

	public void setBananceDateEnd(Date bananceDateEnd) {
		this.bananceDateEnd = bananceDateEnd;
	}

	public TmStockOut getTmStockOut() {
		return tmStockOut;
	}

	public void setTmStockOut(TmStockOut tmStockOut) {
		this.tmStockOut = tmStockOut;
	}

	public Double getWorkingHourFavourRate() {
		return this.workingHourFavourRate;
	}

	public void setWorkingHourFavourRate(Double workingHourFavourRate) {
		this.workingHourFavourRate = workingHourFavourRate;
	}

	public Double getWorkingHourTotalAll() {
		return this.workingHourTotalAll;
	}

	public void setWorkingHourTotalAll(Double workingHourTotalAll) {
		this.workingHourTotalAll = workingHourTotalAll;
	}

	public Double getFixPartFavourRate() {
		return this.fixPartFavourRate;
	}

	public void setFixPartFavourRate(Double fixPartFavourRate) {
		this.fixPartFavourRate = fixPartFavourRate;
	}

	public Double getFixPartTotalAll() {
		return this.fixPartTotalAll;
	}

	public void setFixPartTotalAll(Double fixPartTotalAll) {
		this.fixPartTotalAll = fixPartTotalAll;
	}

	public Double getSolePartFavourRate() {
		return this.solePartFavourRate;
	}

	public void setSolePartFavourRate(Double solePartFavourRate) {
		this.solePartFavourRate = solePartFavourRate;
	}

	public Double getSolePartTotalAll() {
		return this.solePartTotalAll;
	}

	public void setSolePartTotalAll(Double solePartTotalAll) {
		this.solePartTotalAll = solePartTotalAll;
	}

	public Double getBalanceTotalAll() {
		return this.balanceTotalAll;
	}

	public void setBalanceTotalAll(Double balanceTotalAll) {
		this.balanceTotalAll = balanceTotalAll;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	public Long getOldPartDeal() {
		return this.oldPartDeal;
	}

	public void setOldPartDeal(Long oldPartDeal) {
		this.oldPartDeal = oldPartDeal;
	}

	public Long getPayPattern() {
		return this.payPattern;
	}

	public void setPayPattern(Long payPattern) {
		this.payPattern = payPattern;
	}

	public String getChequeCode() {
		return this.chequeCode;
	}

	public void setChequeCode(String chequeCode) {
		this.chequeCode = chequeCode;
	}

	public Date getBalanceDeadTime() {
		return this.balanceDeadTime;
	}

	public void setBalanceDeadTime(Date balanceDeadTime) {
		this.balanceDeadTime = balanceDeadTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getSaveAmount() {
		return this.saveAmount;
	}

	public void setSaveAmount(Double saveAmount) {
		this.saveAmount = saveAmount;
	}

	public Double getUsedSaveAmount() {
		return this.usedSaveAmount;
	}

	public void setUsedSaveAmount(Double usedSaveAmount) {
		this.usedSaveAmount = usedSaveAmount;
	}

	public Double getCashAmount() {
		return this.cashAmount;
	}

	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Double getPayedAmount() {
		return this.payedAmount;
	}

	public void setPayedAmount(Double payedAmount) {
		this.payedAmount = payedAmount;
	}

	public Double getShouldPayAmount() {
		return this.shouldPayAmount;
	}

	public void setShouldPayAmount(Double shouldPayAmount) {
		this.shouldPayAmount = shouldPayAmount;
	}

	public Set getTbBusinessBalanceItems() {
		return tbBusinessBalanceItems;
	}

	public void setTbBusinessBalanceItems(Set tbBusinessBalanceItems) {
		this.tbBusinessBalanceItems = tbBusinessBalanceItems;
	}

	public Set getTbBusinessSpecialBalances() {
		return tbBusinessSpecialBalances;
	}

	public void setTbBusinessSpecialBalances(Set tbBusinessSpecialBalances) {
		this.tbBusinessSpecialBalances = tbBusinessSpecialBalances;
	}

	public Integer getSnNo() {
		return snNo;
	}

	public void setSnNo(Integer snNo) {
		this.snNo = snNo;
	}

	//委托书号
	private String entrustCode;
	//结算员
	private String userRealName;
	//销售单号
	private String stockOutCode;

	public String getEntrustCode() {
		
		if(null!=this.tbFixEntrust){
			entrustCode = this.tbFixEntrust.getEntrustCode();
		}
		
		return entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}

	public String getUserRealName() {
		if(null!=this.tmUser){
			userRealName = this.tmUser.getUserRealName();
		}
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getStockOutCode() {
		if(null!=this.tmStockOut){
			stockOutCode = this.tmStockOut.getStockOutCode();
		}
		return stockOutCode;
	}

	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}

	public Long getBalanceStatus() {
		return balanceStatus;
	}

	public void setBalanceStatus(Long balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
	
	public String balanceStatusShow;
	
	private String payPatternShow;

	public String getPayPatternShow() {
		
		if(null!=this.payPattern){
			
			if(Constants.PAYCASH.equals(this.payPattern)){
				payPatternShow = Constants.PAYCASHSHOW;
			}
			
			else if(Constants.PAYCHEQUE.equals(this.payPattern)){
				payPatternShow = Constants.PAYCHEQUESHOW;
			}
			
			else if(Constants.PAYACCOUNT.equals(this.payPattern)){
				payPatternShow = Constants.PAYACCOUNTSHOW;
			}
			
			else if(Constants.PAYCARD.equals(this.payPattern)){
				payPatternShow = Constants.PAYCARDSHOW;
			}
			
		}
		
		return payPatternShow;
	}


	public void setPayPatternShow(String payPatternShow) {
		this.payPatternShow = payPatternShow;
	}
	
	//欠款金额
	private Double oweAmount;

	public Double getOweAmount() {
		
		if(null!=this.balanceTotalAll&&null!=this.shouldPayAmount){
			
			BigDecimal d_balanceTotalAll = new BigDecimal(String.valueOf(this.balanceTotalAll));
			
			BigDecimal d_shouldPayAmount = new BigDecimal(String.valueOf(this.shouldPayAmount));
			
			BigDecimal d_oweAmount = new BigDecimal("0.00");
			
			d_oweAmount = d_balanceTotalAll.subtract(d_shouldPayAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
		
			return d_oweAmount.doubleValue();
		}
		
		return oweAmount;
	}


	public void setOweAmount(Double oweAmount) {
		this.oweAmount = oweAmount;
	}
	
	private TbCustomer tbCustomer;
	
	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}


	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	private String customerCode;
	
	private String customerName;
	//修理次数
	private Integer fixCount;

	public String getCustomerCode() {
		
		if(null!=this.tbCustomer){
			customerCode = this.tbCustomer.getCustomerCode();
		}
		
		return customerCode;
	}


	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


	public String getCustomerName() {
		if(null!=this.tbCustomer){
			customerName = this.tbCustomer.getCustomerName();
		}
		
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Integer getFixCount() {
		return fixCount;
	}


	public void setFixCount(Integer fixCount) {
		this.fixCount = fixCount;
	}
	
	public TbBusinessBalance(String customerCode,String customerName,Object fixCount,Double balanceTotalAll,Double shouldPayAmount,Double workingHourTotalAll,Double fixPartTotalAll,Double solePartTotalAll){
		
		this.customerCode = customerCode;
		
		this.customerName = customerName;
		
		this.fixCount = Integer.valueOf(String.valueOf(fixCount));
		
		this.balanceTotalAll = balanceTotalAll;
		
		this.shouldPayAmount = shouldPayAmount;
		
		this.workingHourTotalAll = workingHourTotalAll;
		
		this.fixPartTotalAll = fixPartTotalAll;
		
		this.solePartTotalAll = solePartTotalAll;
	}
	
	private String licenseCode;
	
	private String modelTypeName;
	
	public String getLicenseCode() {
		return licenseCode;
	}


	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}


	public String getModelTypeName() {
		return modelTypeName;
	}


	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}


	public TbBusinessBalance(String licenseCode,String modelTypeName,String customerName,Object fixCount,Double balanceTotalAll,Double shouldPayAmount,Double workingHourTotalAll,Double fixPartTotalAll,Double solePartTotalAll){
		
		this.licenseCode = licenseCode;
		
		this.modelTypeName = modelTypeName;
		
		this.customerName = customerName;
		
		this.fixCount = Integer.valueOf(String.valueOf(fixCount));
		
		this.balanceTotalAll = balanceTotalAll;
		
		this.shouldPayAmount = shouldPayAmount;
		
		this.workingHourTotalAll = workingHourTotalAll;
		
		this.fixPartTotalAll = fixPartTotalAll;
		
		this.solePartTotalAll = solePartTotalAll;
	}
	
	//工时费优惠金额
	private Double xlgsfFavourAmount;
	//材料费优惠金额
	private Double xlclfFavourAmount;
	//销售材料优惠金额
	private Double xsjeFavourAmount;
	//总计优惠金额
	private Double totalFavourAmount;
	//减免金额
	private Double freeAmount;
	//去零金额
	private Double qlAmount;

	public Double getFreeAmount() {
		return freeAmount;
	}


	public void setFreeAmount(Double freeAmount) {
		this.freeAmount = freeAmount;
	}


	public Double getQlAmount() {
		return qlAmount;
	}


	public void setQlAmount(Double qlAmount) {
		this.qlAmount = qlAmount;
	}


	public Double getXlgsfFavourAmount() {
		return xlgsfFavourAmount;
	}


	public void setXlgsfFavourAmount(Double xlgsfFavourAmount) {
		this.xlgsfFavourAmount = xlgsfFavourAmount;
	}


	public Double getXlclfFavourAmount() {
		return xlclfFavourAmount;
	}


	public void setXlclfFavourAmount(Double xlclfFavourAmount) {
		this.xlclfFavourAmount = xlclfFavourAmount;
	}


	public Double getXsjeFavourAmount() {
		return xsjeFavourAmount;
	}


	public void setXsjeFavourAmount(Double xsjeFavourAmount) {
		this.xsjeFavourAmount = xsjeFavourAmount;
	}


	public Double getTotalFavourAmount() {
		
		if(null==this.xlgsfFavourAmount){
			this.xlgsfFavourAmount = 0.00D;
		}
		if(null==this.xlclfFavourAmount){
			this.xlclfFavourAmount = 0.00D;
		}
		if(null==this.xsjeFavourAmount){
			this.xsjeFavourAmount = 0.00D;
		}
		
		BigDecimal d = new BigDecimal("0.00");
		
		d = d.add(new BigDecimal(String.valueOf(this.xlgsfFavourAmount))).add(new BigDecimal(String.valueOf(this.xlclfFavourAmount))).add(new BigDecimal(String.valueOf(this.xsjeFavourAmount))).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		totalFavourAmount = d.doubleValue();
		
		return totalFavourAmount;
	}


	public void setTotalFavourAmount(Double totalFavourAmount) {
		this.totalFavourAmount = totalFavourAmount;
	}
	
	private Long tmModelTypeId;

	public Long getTmModelTypeId() {
		return tmModelTypeId;
	}


	public void setTmModelTypeId(Long tmModelTypeId) {
		this.tmModelTypeId = tmModelTypeId;
	}
	
	private Double qtfy;

	public Double getQtfy() {
		
		BigDecimal total = new BigDecimal(String.valueOf(this.balanceTotalAll));
		
		BigDecimal fixHour = new BigDecimal(String.valueOf(this.workingHourTotalAll));
		
		BigDecimal fixPart = new BigDecimal(String.valueOf(this.fixPartTotalAll));
		
		BigDecimal solePart = new BigDecimal(String.valueOf(this.solePartTotalAll));
		
		BigDecimal d = new BigDecimal("0.00");
		
		d = total.subtract(fixHour).subtract(fixPart).subtract(solePart);
		
		qtfy = d.doubleValue();
		
		return qtfy;
	}


	public void setQtfy(Double qtfy) {
		this.qtfy = qtfy;
	}
	
	private String bananceDateStart_s;
	private String bananceDateEnd_s;

	public String getBananceDateStart_s() {
		return bananceDateStart_s;
	}


	public void setBananceDateStart_s(String bananceDateStart_s) {
		this.bananceDateStart_s = bananceDateStart_s;
	}


	public String getBananceDateEnd_s() {
		return bananceDateEnd_s;
	}


	public void setBananceDateEnd_s(String bananceDateEnd_s) {
		this.bananceDateEnd_s = bananceDateEnd_s;
	}
	
	private Double cardZFJE;

	public Double getCardZFJE() {
		return cardZFJE;
	}


	public void setCardZFJE(Double cardZFJE) {
		this.cardZFJE = cardZFJE;
	}
	
	private TbMembershipCard tbMembershipCard;

	public TbMembershipCard getTbMembershipCard() {
		return tbMembershipCard;
	}

	public void setTbMembershipCard(TbMembershipCard tbMembershipCard) {
		this.tbMembershipCard = tbMembershipCard;
	}
	
	private Long cardId;

	public Long getCardId() {
		return cardId;
	}


	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	private Integer businessBalanceIndex;
	
	private String tmCarModelType;
	
	private String chassisCode;
	
	private String telephone;
	
	private String djh;

	public Integer getBusinessBalanceIndex() {
		return businessBalanceIndex;
	}


	public void setBusinessBalanceIndex(Integer businessBalanceIndex) {
		this.businessBalanceIndex = businessBalanceIndex;
	}


	public String getTmCarModelType() {
		return tmCarModelType;
	}


	public void setTmCarModelType(String tmCarModelType) {
		this.tmCarModelType = tmCarModelType;
	}


	public String getChassisCode() {
		return chassisCode;
	}


	public void setChassisCode(String chassisCode) {
		this.chassisCode = chassisCode;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getDjh() {
		return djh;
	}


	public void setDjh(String djh) {
		this.djh = djh;
	}
	
	private BigDecimal pjCost;

	public BigDecimal getPjCost() {
		return pjCost;
	}


	public void setPjCost(BigDecimal pjCost) {
		this.pjCost = pjCost;
	}

	
	private BigDecimal pjCostAll;

	public BigDecimal getPjCostAll() {
		return pjCostAll;
	}


	public void setPjCostAll(BigDecimal pjCostAll) {
		this.pjCostAll = pjCostAll;
	}
	
	private BigDecimal djCost;
	
	
	

	public BigDecimal getDjCost() {
		return djCost;
	}


	public void setDjCost(BigDecimal djCost) {
		this.djCost = djCost;
	}

	private String userRealNameServer;

	public String getUserRealNameServer() {
		return userRealNameServer;
	}


	public void setUserRealNameServer(String userRealNameServer) {
		this.userRealNameServer = userRealNameServer;
	}
	
	
	
	
}