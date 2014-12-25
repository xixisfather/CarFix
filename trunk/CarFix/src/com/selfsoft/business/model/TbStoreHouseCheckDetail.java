package com.selfsoft.business.model;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseparameter.model.TmStoreHouse;

public class TbStoreHouseCheckDetail {

	private Long id;
	
	private TbPartInfo tbPartInfo;
	
	private TmStoreHouse tmStoreHouse;
	
	private Double storeQuantity;
	
	private Double costPrice;
	
	private Double jsPrice;
	
	private Double checkQuantity;
	
	private Double checkPrice;
	
	private Double divQuantity;
	
	private Double divPrice;
	
	private TbStoreHouseCheck tbStoreHouseCheck;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	public TmStoreHouse getTmStoreHouse() {
		return tmStoreHouse;
	}

	public void setTmStoreHouse(TmStoreHouse tmStoreHouse) {
		this.tmStoreHouse = tmStoreHouse;
	}

	public Double getStoreQuantity() {
		return storeQuantity;
	}

	public void setStoreQuantity(Double storeQuantity) {
		this.storeQuantity = storeQuantity;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getJsPrice() {
		return jsPrice;
	}

	public void setJsPrice(Double jsPrice) {
		this.jsPrice = jsPrice;
	}

	public Double getCheckQuantity() {
		return checkQuantity;
	}

	public void setCheckQuantity(Double checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	public Double getCheckPrice() {
		return checkPrice;
	}

	public void setCheckPrice(Double checkPrice) {
		this.checkPrice = checkPrice;
	}

	public Double getDivQuantity() {
		return divQuantity;
	}

	public void setDivQuantity(Double divQuantity) {
		this.divQuantity = divQuantity;
	}

	public Double getDivPrice() {
		return divPrice;
	}

	public void setDivPrice(Double divPrice) {
		this.divPrice = divPrice;
	}

	public TbStoreHouseCheck getTbStoreHouseCheck() {
		return tbStoreHouseCheck;
	}

	public void setTbStoreHouseCheck(TbStoreHouseCheck tbStoreHouseCheck) {
		this.tbStoreHouseCheck = tbStoreHouseCheck;
	}
}
