package com.selfsoft.business.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbPartInfo;

/**
 * TmLoanRegisterDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLoanRegisterDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long loanId;
	private Double loanPrice;
	private Double loanQuantity;
	private Double oldCostPrice;
	private Double returnQuantity;
	private Long isReturn;
	
	//借出归还
	private Set<TmLoanReturnDetail>  returnDetails = new HashSet<TmLoanReturnDetail>();
	private TbPartInfo tbPartInfo;

	// Constructors

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	public Set<TmLoanReturnDetail> getReturnDetails() {
		return returnDetails;
	}

	public void setReturnDetails(Set<TmLoanReturnDetail> returnDetails) {
		this.returnDetails = returnDetails;
	}

	/** default constructor */
	public TmLoanRegisterDetail() {
	}

	/** minimal constructor */
	public TmLoanRegisterDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLoanRegisterDetail(Long id, Long loanId,
			Double loanPrice, Double loanQuantity, Double oldCostPrice,
			Double returnQuantity, Long isReturn) {
		this.id = id;
		this.loanId = loanId;
		this.loanPrice = loanPrice;
		this.loanQuantity = loanQuantity;
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

	public Long getLoanId() {
		return this.loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Double getLoanPrice() {
		return this.loanPrice;
	}

	public void setLoanPrice(Double loanPrice) {
		this.loanPrice = loanPrice;
	}

	public Double getLoanQuantity() {
		return this.loanQuantity;
	}

	public void setLoanQuantity(Double loanQuantity) {
		this.loanQuantity = loanQuantity;
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

}