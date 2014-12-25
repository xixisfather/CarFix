package com.selfsoft.business.vo;

import java.util.Date;

public class TmShiftinStockVo {

	private Long shiftinStockId;				//移进ID
	private String shiftinBill;					//移进单号
	private Date shiftinDate;					//移进日期
	private Long storeHoseId;					//仓库ID
	private Long removeStockId;					//移出ID
	
	private String shiftinHouseCode;			//移进仓库代码
	private String shiftinHouseName;			//移进仓库名称
	
	
	private String removeStockBill;				//移出单号
	private Date removeDate;					//移出日期
	
	private String removeHouseCode;				//移出仓库代码
	private String removeHouseName;				//移出仓库代码
	
	private String userName;					//操作人
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getShiftinStockId() {
		return shiftinStockId;
	}
	public void setShiftinStockId(Long shiftinStockId) {
		this.shiftinStockId = shiftinStockId;
	}
	public String getShiftinBill() {
		return shiftinBill;
	}
	public void setShiftinBill(String shiftinBill) {
		this.shiftinBill = shiftinBill;
	}
	public Date getShiftinDate() {
		return shiftinDate;
	}
	public void setShiftinDate(Date shiftinDate) {
		this.shiftinDate = shiftinDate;
	}
	public Long getStoreHoseId() {
		return storeHoseId;
	}
	public void setStoreHoseId(Long storeHoseId) {
		this.storeHoseId = storeHoseId;
	}
	public Long getRemoveStockId() {
		return removeStockId;
	}
	public void setRemoveStockId(Long removeStockId) {
		this.removeStockId = removeStockId;
	}
	public String getShiftinHouseCode() {
		return shiftinHouseCode;
	}
	public void setShiftinHouseCode(String shiftinHouseCode) {
		this.shiftinHouseCode = shiftinHouseCode;
	}
	public String getShiftinHouseName() {
		return shiftinHouseName;
	}
	public void setShiftinHouseName(String shiftinHouseName) {
		this.shiftinHouseName = shiftinHouseName;
	}
	public String getRemoveStockBill() {
		return removeStockBill;
	}
	public void setRemoveStockBill(String removeStockBill) {
		this.removeStockBill = removeStockBill;
	}
	public Date getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}
	public String getRemoveHouseCode() {
		return removeHouseCode;
	}
	public void setRemoveHouseCode(String removeHouseCode) {
		this.removeHouseCode = removeHouseCode;
	}
	public String getRemoveHouseName() {
		return removeHouseName;
	}
	public void setRemoveHouseName(String removeHouseName) {
		this.removeHouseName = removeHouseName;
	}
	public TmShiftinStockVo(Long shiftinStockId, String shiftinBill,
			Date shiftinDate, Long storeHoseId, Long removeStockId,
			String shiftinHouseCode, String shiftinHouseName,
			String removeStockBill, Date removeDate, String removeHouseCode,
			String removeHouseName,String userName) {
		super();
		this.shiftinStockId = shiftinStockId;
		this.shiftinBill = shiftinBill;
		this.shiftinDate = shiftinDate;
		this.storeHoseId = storeHoseId;
		this.removeStockId = removeStockId;
		this.shiftinHouseCode = shiftinHouseCode;
		this.shiftinHouseName = shiftinHouseName;
		this.removeStockBill = removeStockBill;
		this.removeDate = removeDate;
		this.removeHouseCode = removeHouseCode;
		this.removeHouseName = removeHouseName;
		this.userName = userName;
	}
	
	
}
