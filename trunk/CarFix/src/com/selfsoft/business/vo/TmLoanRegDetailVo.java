package com.selfsoft.business.vo;

import java.util.Date;

import com.selfsoft.framework.common.CommonMethod;

public class TmLoanRegDetailVo {

	private Long loanRegId;					//借出登记ID
	private Long loanRegDtlId;				//借出登记详细ID
	private String loanBill;				//借出登记单号
	private Date loanDate;					//借出日期
	private String partCode;				//配件代码
	private String partName;				//配件名称
	private String unitName;				//单位
	private Double loanQuantity;			//借出数量
	private Double loanPrice;				//借出价格
	private Double oldCostPrice;			//当时成本价
	private Double returnQuantity;			//已还数量
	private String houseName;				//仓库
	private Double nowCosePrice;			//现成本价
	private Long partinfoId;				//配件ID
	private Double total;					//金额小计
	private String storeLocation;			//仓位
	
	public Double getTotal() {
		return CommonMethod.convertRadixPoint(loanQuantity*loanPrice, 2);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public TmLoanRegDetailVo() {
		super();
	}
	
	public TmLoanRegDetailVo(Long loanRegId, Long loanRegDtlId,
			String loanBill, Date loanDate, String partCode,
			String partName, String unitName, Double loanQuantity,
			Double loanPrice, Double oldCostPrice, Double returnQuantity,
			String houseName,Double nowCosePrice,Long partinfoId,String storeLocation) {
		super();
		this.loanRegId = loanRegId;
		this.loanRegDtlId = loanRegDtlId;
		this.loanBill = loanBill;
		this.loanDate = loanDate;
		this.partCode = partCode;
		this.partName = partName;
		this.unitName = unitName;
		this.loanQuantity = loanQuantity;
		this.loanPrice = loanPrice;
		this.oldCostPrice = oldCostPrice;
		this.returnQuantity = returnQuantity;
		this.houseName = houseName;
		this.nowCosePrice = nowCosePrice;
		this.partinfoId = partinfoId;
		this.storeLocation = storeLocation;
	}


	public Long getloanRegDtlId() {
		return loanRegDtlId;
	}

	public void setloanRegDtlId(Long loanRegDtlId) {
		this.loanRegDtlId = loanRegDtlId;
	}

	public Long getloanRegId() {
		return loanRegId;
	}
	public void setloanRegId(Long loanRegId) {
		this.loanRegId = loanRegId;
	}
	public String getloanBill() {
		return loanBill;
	}
	public void setloanBill(String loanBill) {
		this.loanBill = loanBill;
	}
	public Date getloanDate() {
		return loanDate;
	}
	public void setloanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Double getloanQuantity() {
		return loanQuantity;
	}
	public void setloanQuantity(Double loanQuantity) {
		this.loanQuantity = loanQuantity;
	}
	public Double getloanPrice() {
		return loanPrice;
	}
	public void setloanPrice(Double loanPrice) {
		this.loanPrice = loanPrice;
	}
	public Double getOldCostPrice() {
		return oldCostPrice;
	}
	public void setOldCostPrice(Double oldCostPrice) {
		this.oldCostPrice = oldCostPrice;
	}
	public Double getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Double getNowCosePrice() {
		return nowCosePrice;
	}

	public void setNowCosePrice(Double nowCosePrice) {
		this.nowCosePrice = nowCosePrice;
	}

	public Long getLoanRegId() {
		return loanRegId;
	}

	public void setLoanRegId(Long loanRegId) {
		this.loanRegId = loanRegId;
	}

	public Long getLoanRegDtlId() {
		return loanRegDtlId;
	}

	public void setLoanRegDtlId(Long loanRegDtlId) {
		this.loanRegDtlId = loanRegDtlId;
	}

	public String getLoanBill() {
		return loanBill;
	}

	public void setLoanBill(String loanBill) {
		this.loanBill = loanBill;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Double getLoanQuantity() {
		return loanQuantity;
	}

	public void setLoanQuantity(Double loanQuantity) {
		this.loanQuantity = loanQuantity;
	}

	public Double getLoanPrice() {
		return loanPrice;
	}

	public void setLoanPrice(Double loanPrice) {
		this.loanPrice = loanPrice;
	}

	public Long getPartinfoId() {
		return partinfoId;
	}

	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}
	
	
}
