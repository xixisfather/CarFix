package com.selfsoft.baseinformation.vo;

import java.util.Date;

public class TbPartCollectionVo {
	
	private String collectionCode;
	private Date createDate;
	public TbPartCollectionVo(String collectionCode){
		this.collectionCode = collectionCode;
	}
	public TbPartCollectionVo(String collectionCode, Date createDate) {
		super();
		this.collectionCode = collectionCode;
		this.createDate = createDate;
	}
	public TbPartCollectionVo() {
		super();
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
