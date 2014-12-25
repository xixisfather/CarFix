package com.selfsoft.business.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TmLianceReturn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLianceReturn implements java.io.Serializable {

	// Fields

	private Long id;
	private String lianceReturnBill;
	private String userId;
	private Date createDate;
	private Date returnDate;
	private Long isConfirm;
	private Set<TmLianceReturnDetail> lianceReturnDetails = new HashSet<TmLianceReturnDetail>();
	
	
	// Constructors

	public Set<TmLianceReturnDetail> getLianceReturnDetails() {
		return lianceReturnDetails;
	}

	public void setLianceReturnDetails(Set<TmLianceReturnDetail> lianceReturnDetails) {
		this.lianceReturnDetails = lianceReturnDetails;
	}

	/** default constructor */
	public TmLianceReturn() {
	}

	/** minimal constructor */
	public TmLianceReturn(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLianceReturn(Long id, String lianceReturnBill, String userId,
			Date createDate) {
		this.id = id;
		this.lianceReturnBill = lianceReturnBill;
		this.userId = userId;
		this.createDate = createDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getLianceReturnBill() {
		return lianceReturnBill;
	}

	public void setLianceReturnBill(String lianceReturnBill) {
		this.lianceReturnBill = lianceReturnBill;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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