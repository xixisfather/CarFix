package com.selfsoft.business.vo;

import java.util.Date;

public class TmRemStockVo {
	
	private Long tmstockId;						//移库出仓单ID
	private Date removeDate;					//移出日期
	private Long userId;						//操作人
	private Date createDate;					//创建日期
	private String removeStockBill;				//移库出仓单号
	private Long houseId;						//仓库ID
	private String houseCode;					//仓库代码
	private String houseName;					//仓库名称
	private String userName;					//操作人
	
	public Long getTmstockId() {
		return tmstockId;
	}
	public void setTmstockId(Long tmstockId) {
		this.tmstockId = tmstockId;
	}
	public Date getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemoveStockBill() {
		return removeStockBill;
	}
	public void setRemoveStockBill(String removeStockBill) {
		this.removeStockBill = removeStockBill;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public TmRemStockVo(Long tmstockId, Date removeDate, Long userId,
			Date createDate, String removeStockBill, Long houseId,
			String houseCode, String houseName,String userName) {
		super();
		this.tmstockId = tmstockId;
		this.removeDate = removeDate;
		this.userId = userId;
		this.createDate = createDate;
		this.removeStockBill = removeStockBill;
		this.houseId = houseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
