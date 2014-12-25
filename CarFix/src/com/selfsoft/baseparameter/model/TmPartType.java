package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbPartInfo;

/**
 * 配件类型
 */

public class TmPartType implements java.io.Serializable {

	// Fields

	private Long id;
	private String typeCode;
	private String typeName;
	private String typeRemark;
	private Set<TbPartInfo> tbPartInfos = new HashSet<TbPartInfo>();

	// Constructors

	/** default constructor */
	public TmPartType() {
	}

	/** full constructor */
	public TmPartType(String typeCode, String typeName, String typeRemark) {
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.typeRemark = typeRemark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeRemark() {
		return this.typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

	public Set<TbPartInfo> getTbPartInfos() {
		return tbPartInfos;
	}

	public void setTbPartInfos(Set<TbPartInfo> tbPartInfos) {
		this.tbPartInfos = tbPartInfos;
	}
	
}