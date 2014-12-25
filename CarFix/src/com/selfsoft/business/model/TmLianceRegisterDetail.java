package com.selfsoft.business.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbPartInfo;

/**
 * TmLianceRegisterDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLianceRegisterDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long lianceId;								//借进登记id
	private Double lianceQuantity;						//借进数量
	private Double liancePrice;							//借进价格
	private Double oldCostPrice;						//借进时成本价
	private Double returnQuantity;						//归还数量
	private Long isReturn;								//是否都已归还
	//借进归还
	private Set<TmLianceReturnDetail> returnDetails = new HashSet<TmLianceReturnDetail>();
	private TbPartInfo tbPartInfo;						//配件

	// Constructors

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	/** default constructor */
	public TmLianceRegisterDetail() {
	}

	/** minimal constructor */
	public TmLianceRegisterDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLianceRegisterDetail(Long id, Long lianceId,
			Double lianceQuantity, Double liancePrice, Double oldCostPrice,
			Double returnQuantity, Long isReturn) {
		this.id = id;
		this.lianceId = lianceId;
		this.lianceQuantity = lianceQuantity;
		this.liancePrice = liancePrice;
		this.oldCostPrice = oldCostPrice;
		this.returnQuantity = returnQuantity;
		this.isReturn = isReturn;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLianceId() {
		return this.lianceId;
	}

	public void setLianceId(Long lianceId) {
		this.lianceId = lianceId;
	}

	public Double getLianceQuantity() {
		return this.lianceQuantity;
	}

	public void setLianceQuantity(Double lianceQuantity) {
		this.lianceQuantity = lianceQuantity;
	}

	public Double getLiancePrice() {
		return this.liancePrice;
	}

	public void setLiancePrice(Double liancePrice) {
		this.liancePrice = liancePrice;
	}

	public Double getOldCostPrice() {
		return this.oldCostPrice;
	}

	public void setOldCostPrice(Double oldCostPrice) {
		this.oldCostPrice = oldCostPrice;
	}

	public Double getReturnQuantity() {
		return this.returnQuantity;
	}

	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public Long getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(Long isReturn) {
		this.isReturn = isReturn;
	}


	public Set<TmLianceReturnDetail> getReturnDetails() {
		return returnDetails;
	}

	public void setReturnDetails(Set<TmLianceReturnDetail> returnDetails) {
		this.returnDetails = returnDetails;
	}

	

	
}