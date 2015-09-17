package com.selfsoft.business.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;

/**
 * TbFixEntrust entity. @author MyEclipse Persistence Tools
 */

public class TbFixEntrust implements java.io.Serializable {

	// Fields

	private Long id;
	private String entrustCode;
	private Date fixDate;
	private Date fixDateStart;
	private Date fixDateEnd;
	private Date transferCarDate;
	private Date estimateDate;
	private Date estimateDateStart;
	private Date estimateDateEnd;
	private TmUser tmUser;
	private TmFixType tmFixType;
	private TbCustomer tbCustomer;
	private TbCarInfo tbCarInfo;
	private Double oilMeter;
	private Double enterStationKilo;
	private Double outStationKilo;
	private Double remindMaintainKilo;
	private Date remindMaintainDate;
	private Date remindInsuranceDate;
	private String wrongDescribe;
	private String beforeFixState;
	private String checkResult;
	private String remark;
	private Long isFinish;
	private Long isvalid;
	private Double workingHourPrice;
	private Long entrustStatus;
	private String bjzzh;
	private String sbrq;
	private Set tbFixEntrustContents = new LinkedHashSet();
	private Set tbBusinessBalances = new HashSet();
	private Set tbBespokePartContents = new HashSet();
	// Constructors

	/** default constructor */
	public TbFixEntrust() {
	}

	// Property accessors

	public TbFixEntrust(Long id, Date fixDate, TbCarInfo tbCarInfo) {
		super();
		this.id = id;
		this.fixDate = fixDate;
		this.tbCarInfo = tbCarInfo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntrustCode() {
		return this.entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}

	public Date getFixDate() {
		return this.fixDate;
	}

	public void setFixDate(Date fixDate) {
		this.fixDate = fixDate;
	}

	public Date getFixDateStart() {
		return fixDateStart;
	}

	public void setFixDateStart(Date fixDateStart) {
		this.fixDateStart = fixDateStart;
	}

	public Date getFixDateEnd() {
		return fixDateEnd;
	}

	public void setFixDateEnd(Date fixDateEnd) {
		this.fixDateEnd = fixDateEnd;
	}

	public Date getEstimateDateStart() {
		return estimateDateStart;
	}

	public void setEstimateDateStart(Date estimateDateStart) {
		this.estimateDateStart = estimateDateStart;
	}

	public Date getEstimateDateEnd() {
		return estimateDateEnd;
	}

	public void setEstimateDateEnd(Date estimateDateEnd) {
		this.estimateDateEnd = estimateDateEnd;
	}

	public Date getTransferCarDate() {
		return this.transferCarDate;
	}

	public void setTransferCarDate(Date transferCarDate) {
		this.transferCarDate = transferCarDate;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}
	
	public TmFixType getTmFixType() {
		return tmFixType;
	}

	public void setTmFixType(TmFixType tmFixType) {
		this.tmFixType = tmFixType;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}

	public Double getOilMeter() {
		return this.oilMeter;
	}

	public void setOilMeter(Double oilMeter) {
		this.oilMeter = oilMeter;
	}

	public Double getEnterStationKilo() {
		return this.enterStationKilo;
	}

	public void setEnterStationKilo(Double enterStationKilo) {
		this.enterStationKilo = enterStationKilo;
	}

	public Double getOutStationKilo() {
		return this.outStationKilo;
	}

	public void setOutStationKilo(Double outStationKilo) {
		this.outStationKilo = outStationKilo;
	}

	public Double getRemindMaintainKilo() {
		return this.remindMaintainKilo;
	}

	public void setRemindMaintainKilo(Double remindMaintainKilo) {
		this.remindMaintainKilo = remindMaintainKilo;
	}

	public Date getRemindMaintainDate() {
		return this.remindMaintainDate;
	}

	public void setRemindMaintainDate(Date remindMaintainDate) {
		this.remindMaintainDate = remindMaintainDate;
	}

	public Date getRemindInsuranceDate() {
		return remindInsuranceDate;
	}

	public void setRemindInsuranceDate(Date remindInsuranceDate) {
		this.remindInsuranceDate = remindInsuranceDate;
	}

	public String getWrongDescribe() {
		return this.wrongDescribe;
	}

	public void setWrongDescribe(String wrongDescribe) {
		this.wrongDescribe = wrongDescribe;
	}

	public String getBeforeFixState() {
		return this.beforeFixState;
	}

	public void setBeforeFixState(String beforeFixState) {
		this.beforeFixState = beforeFixState;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Long isFinish) {
		this.isFinish = isFinish;
	}

	public Long getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	public Double getWorkingHourPrice() {
		return workingHourPrice;
	}

	public void setWorkingHourPrice(Double workingHourPrice) {
		this.workingHourPrice = workingHourPrice;
	}

	public Long getEntrustStatus() {
		return entrustStatus;
	}

	public void setEntrustStatus(Long entrustStatus) {
		this.entrustStatus = entrustStatus;
	}

	public Set getTbFixEntrustContents() {
		return tbFixEntrustContents;
	}

	public void setTbFixEntrustContents(Set tbFixEntrustContents) {
		this.tbFixEntrustContents = tbFixEntrustContents;
	}

	public Set getTbBusinessBalances() {
		return tbBusinessBalances;
	}

	public void setTbBusinessBalances(Set tbBusinessBalances) {
		this.tbBusinessBalances = tbBusinessBalances;
	}

	private String userRealName;
	
	private String fixType;
	
	private String customerName;
	
	private String licenseCode;
	
	private String isFinishShow;

	public String getIsFinishShow() {
		if(Constants.NOFINISH.equals(this.isFinish)){
			isFinishShow = Constants.NOFINISHSHOW;
		}
		else if(Constants.ISFINISH.equals(this.isFinish)){
			isFinishShow = Constants.ISFINISHSHOW;
		}
		
		return isFinishShow;
	}

	public void setIsFinishShow(String isFinishShow) {
		this.isFinishShow = isFinishShow;
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

	public String getFixType() {
		if(null!=this.tmFixType){
			fixType = this.tmFixType.getFixType();
		}
		return fixType;
	}

	public void setFixType(String fixType) {
		this.fixType = fixType;
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

	public String getLicenseCode() {
		if(null!=this.tbCarInfo){
			licenseCode = this.tbCarInfo.getLicenseCode();
		}
		
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	//add by baijx
	
	private String stationCode;

	public String getStationCode() {
		
		return this.tbCarInfo.getTmCarModelType().getTmCarStationType().getStationCode();
	}
	
	//委托书下工时总价--数据库中没有字段
	private Double workingHourTotalAll;

	public Double getWorkingHourTotalAll() {
		return workingHourTotalAll;
	}

	public void setWorkingHourTotalAll(Double workingHourTotalAll) {
		this.workingHourTotalAll = workingHourTotalAll;
	}

	public Set getTbBespokePartContents() {
		return tbBespokePartContents;
	}

	public void setTbBespokePartContents(Set tbBespokePartContents) {
		this.tbBespokePartContents = tbBespokePartContents;
	}

	//预计时间
	private Integer minutes;

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	
	//结算状态查询
	private String entrustStatusCollection;

	public String getEntrustStatusCollection() {
		return entrustStatusCollection;
	}

	public void setEntrustStatusCollection(String entrustStatusCollection) {
		this.entrustStatusCollection = entrustStatusCollection;
	}
	
	//委托书类型  1-维修发料  2-委托书销售
	private Long entrustType;

	public Long getEntrustType() {
		return entrustType;
	}

	public void setEntrustType(Long entrustType) {
		this.entrustType = entrustType;
	}
	
	
	private Long carId;

	public Long getCarId() {
		
		if(null!=this.tbCarInfo){
			
			carId = this.tbCarInfo.getId();
		
		}
		
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}
	
	private String modelTypeCode;
	
	public String getModelTypeCode() {
		
		if(null!=this.tbCarInfo){
			
			if(null!=this.tbCarInfo.getTmCarModelType()){
				
				modelTypeCode = this.tbCarInfo.getTmCarModelType().getModelCode();
				
			}
		}
		
		
		return modelTypeCode;
	}

	public void setModelTypeCode(String modelTypeCode) {
		this.modelTypeCode = modelTypeCode;
	}

	private String modelTypeName;

	public String getModelTypeName() {
		
		if(null!=this.tbCarInfo){
			
			if(null!=this.tbCarInfo.getTmCarModelType()){
				
				modelTypeName = this.tbCarInfo.getTmCarModelType().getModelName();
				
			}
		}
		
		return modelTypeName;
	}

	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}
	
	private String fixDateStart_s;
	
	private String fixDateEnd_s;

	public String getFixDateStart_s() {
		return fixDateStart_s;
	}

	public void setFixDateStart_s(String fixDateStart_s) {
		this.fixDateStart_s = fixDateStart_s;
	}

	public String getFixDateEnd_s() {
		return fixDateEnd_s;
	}

	public void setFixDateEnd_s(String fixDateEnd_s) {
		this.fixDateEnd_s = fixDateEnd_s;
	}
	
	//委托书查询页面新增字段
	//工时费总计
	private BigDecimal fixHourTotal = new BigDecimal("0.00");
	//发料配件金额总计
	private BigDecimal stockOutPartTotal = new BigDecimal("0.00");
	//销售配件金额总计
	private BigDecimal solePartTotal = new BigDecimal("0.00");
	//所有总金额
	private BigDecimal allTotal = new BigDecimal("0.00");

	public BigDecimal getFixHourTotal() {
		return fixHourTotal;
	}

	public void setFixHourTotal(BigDecimal fixHourTotal) {
		this.fixHourTotal = fixHourTotal;
	}

	public BigDecimal getStockOutPartTotal() {
		return stockOutPartTotal;
	}

	public void setStockOutPartTotal(BigDecimal stockOutPartTotal) {
		this.stockOutPartTotal = stockOutPartTotal;
	}

	public BigDecimal getSolePartTotal() {
		return solePartTotal;
	}

	public void setSolePartTotal(BigDecimal solePartTotal) {
		this.solePartTotal = solePartTotal;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public BigDecimal getAllTotal() {
		return allTotal;
	}

	public void setAllTotal(BigDecimal allTotal) {
		this.allTotal = allTotal;
	}
	
	/**
	 * 原价
	 */
	private Double workingHourTotalAllOri;

	public Double getWorkingHourTotalAllOri() {
		return workingHourTotalAllOri;
	}

	public void setWorkingHourTotalAllOri(Double workingHourTotalAllOri) {
		this.workingHourTotalAllOri = workingHourTotalAllOri;
	}
	
	private String wjg;

	public String getWjg() {
		return wjg;
	}

	public void setWjg(String wjg) {
		this.wjg = wjg;
	}

	public String getBjzzh() {
		return bjzzh;
	}

	public void setBjzzh(String bjzzh) {
		this.bjzzh = bjzzh;
	}

	public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	
	private Date balanceDateBegin;
	private Date balanceDateEnd;

	public Date getBalanceDateBegin() {
		return balanceDateBegin;
	}

	public void setBalanceDateBegin(Date balanceDateBegin) {
		this.balanceDateBegin = balanceDateBegin;
	}

	public Date getBalanceDateEnd() {
		return balanceDateEnd;
	}

	public void setBalanceDateEnd(Date balanceDateEnd) {
		this.balanceDateEnd = balanceDateEnd;
	}
	
	private BigDecimal pjcb;

	public BigDecimal getPjcb() {
		return pjcb;
	}

	public void setPjcb(BigDecimal pjcb) {
		this.pjcb = pjcb;
	}
	
	private String jsqk;

	public String getJsqk() {
		return jsqk;
	}

	public void setJsqk(String jsqk) {
		this.jsqk = jsqk;
	}
	
	private BigDecimal zlr;

	public BigDecimal getZlr() {
		return zlr;
	}

	public void setZlr(BigDecimal zlr) {
		this.zlr = zlr;
	}
	
	
	
}