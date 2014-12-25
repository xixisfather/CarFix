package com.selfsoft.business.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TmLoanReturn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLoanReturn implements java.io.Serializable {

	// Fields

	private Long id;
	private String loanReturnBill;
	private Date returnDate;
	private Long userId;
	private Date createDate;
	private Long isConfirm;
	
	private Set<TmLoanReturnDetail> loanReturnDetails = new HashSet<TmLoanReturnDetail>();

	// Constructors

	/** default constructor */
	public TmLoanReturn() {
	}

	/** minimal constructor */
	public TmLoanReturn(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLoanReturn(Long id, String loanReturnBill, Date returnDate,
			Long userId, Date createDate, Long isConfirm) {
		this.id = id;
		this.loanReturnBill = loanReturnBill;
		this.returnDate = returnDate;
		this.userId = userId;
		this.createDate = createDate;
		this.isConfirm = isConfirm;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanReturnBill() {
		return this.loanReturnBill;
	}

	public void setLoanReturnBill(String loanReturnBill) {
		this.loanReturnBill = loanReturnBill;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Set<TmLoanReturnDetail> getLoanReturnDetails() {
		return loanReturnDetails;
	}

	public void setLoanReturnDetails(Set<TmLoanReturnDetail> loanReturnDetails) {
		this.loanReturnDetails = loanReturnDetails;
	}

	
	private String beginDate;
	
	private String endDate;


	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

}