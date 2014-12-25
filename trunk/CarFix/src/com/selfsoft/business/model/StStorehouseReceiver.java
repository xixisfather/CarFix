package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.framework.common.StoreHouseReceiverElements;

/**
 * StStorehouseReceiver entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StStorehouseReceiver implements java.io.Serializable {

	// Fields

	private Long id;
	private String stockoutType;
	private Double stockinCostPrice;
	private Double stockoutCostPrice;
	private Double sellTotalPrice;
	private Double QStart;
	private Double QFinal;
	private Date createDate;
	private Double stockinSubTotal;
	private Double stockoutSubTotal;
	private TmStoreHouse tmStoreHouse;

	// Constructors

	public TmStoreHouse getTmStoreHouse() {
		return tmStoreHouse;
	}

	public void setTmStoreHouse(TmStoreHouse tmStoreHouse) {
		this.tmStoreHouse = tmStoreHouse;
	}

	/** default constructor */
	public StStorehouseReceiver() {
	}

	/** minimal constructor */
	public StStorehouseReceiver(Long id) {
		this.id = id;
	}

	/** full constructor */
	public StStorehouseReceiver(Long id, 
			String stockoutType, Double stockinCostPrice,
			Double stockoutCostPrice, Double sellTotalPrice, Double QStart,
			Double QFinal, Date createDate, Double stockinSubTotal,
			Double stockoutSubTotal) {
		this.id = id;
		this.stockoutType = stockoutType;
		this.stockinCostPrice = stockinCostPrice;
		this.stockoutCostPrice = stockoutCostPrice;
		this.sellTotalPrice = sellTotalPrice;
		this.QStart = QStart;
		this.QFinal = QFinal;
		this.createDate = createDate;
		this.stockinSubTotal = stockinSubTotal;
		this.stockoutSubTotal = stockoutSubTotal;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockoutType() {
		return this.stockoutType;
	}

	public void setStockoutType(String stockoutType) {
		this.stockoutType = stockoutType;
	}

	public Double getStockinCostPrice() {
		return this.stockinCostPrice;
	}

	public void setStockinCostPrice(Double stockinCostPrice) {
		this.stockinCostPrice = stockinCostPrice;
	}

	public Double getStockoutCostPrice() {
		return this.stockoutCostPrice;
	}

	public void setStockoutCostPrice(Double stockoutCostPrice) {
		this.stockoutCostPrice = stockoutCostPrice;
	}

	public Double getSellTotalPrice() {
		return this.sellTotalPrice;
	}

	public void setSellTotalPrice(Double sellTotalPrice) {
		this.sellTotalPrice = sellTotalPrice;
	}

	public Double getQStart() {
		return this.QStart;
	}

	public void setQStart(Double QStart) {
		this.QStart = QStart;
	}

	public Double getQFinal() {
		return this.QFinal;
	}

	public void setQFinal(Double QFinal) {
		this.QFinal = QFinal;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getStockinSubTotal() {
		return this.stockinSubTotal;
	}

	public void setStockinSubTotal(Double stockinSubTotal) {
		this.stockinSubTotal = stockinSubTotal;
	}

	public Double getStockoutSubTotal() {
		return this.stockoutSubTotal;
	}

	public void setStockoutSubTotal(Double stockoutSubTotal) {
		this.stockoutSubTotal = stockoutSubTotal;
	}

	
	private String searchBeginDate;
	
	private String searchEndDate;

	public String getSearchBeginDate() {
		return searchBeginDate;
	}

	public void setSearchBeginDate(String searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	

	private String stockoutTypeName;

	public String getStockoutTypeName() {
		return StoreHouseReceiverElements.getElementMap().get(this.stockoutType);
	}
	
	
}