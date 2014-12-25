package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbPartInfo;

/**
 * 
 * 车辆类型
 *
 */

public class TmCarModelType implements java.io.Serializable {

	// Fields

	private Long id;
	private String modelCode;
	private String modelName;
	private TmCarStationType tmCarStationType;
	private TmWorkingHourPrice tmWorkingHourPrice;
	private Set<TbPartInfo> tbPartInfos = new HashSet<TbPartInfo>();
	private Set tbCarInfos = new HashSet();
	private Set tbBooks = new HashSet();
	// Constructors

	/** default constructor */
	public TmCarModelType() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelCode() {
		return this.modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public TmCarStationType getTmCarStationType() {
		return tmCarStationType;
	}

	public void setTmCarStationType(TmCarStationType tmCarStationType) {
		this.tmCarStationType = tmCarStationType;
	}

	public TmWorkingHourPrice getTmWorkingHourPrice() {
		return tmWorkingHourPrice;
	}

	public void setTmWorkingHourPrice(TmWorkingHourPrice tmWorkingHourPrice) {
		this.tmWorkingHourPrice = tmWorkingHourPrice;
	}

	
	public Set getTbBooks() {
		return tbBooks;
	}

	public void setTbBooks(Set tbBooks) {
		this.tbBooks = tbBooks;
	}


	private String stationCode;
	
	private String stationRemark;
	
	private Double price;
	
	private String priceRemark;

	public String getStationCode() {
		
		return this.tmCarStationType.getStationCode();
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationRemark() {
		return this.tmCarStationType.getStationRemark();
	}

	public void setStationRemark(String stationRemark) {
		this.stationRemark = stationRemark;
	}

	public Double getPrice() {
		return this.tmWorkingHourPrice.getPrice();
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceRemark() {
		return this.tmWorkingHourPrice.getPriceRemark();
	}

	public void setPriceRemark(String priceRemark) {
		this.priceRemark = priceRemark;
	}

	public Set<TbPartInfo> getTbPartInfos() {
		return tbPartInfos;
	}

	public void setTbPartInfos(Set<TbPartInfo> tbPartInfos) {
		this.tbPartInfos = tbPartInfos;
	}

	public Set getTbCarInfos() {
		return tbCarInfos;
	}

	public void setTbCarInfos(Set tbCarInfos) {
		this.tbCarInfos = tbCarInfos;
	}
	
	private String stationCode_xls;

	public String getStationCode_xls() {
		return stationCode_xls;
	}

	public void setStationCode_xls(String stationCode_xls) {
		this.stationCode_xls = stationCode_xls;
	}
	
	
	
}