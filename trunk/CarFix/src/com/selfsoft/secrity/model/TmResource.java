package com.selfsoft.secrity.model;

import java.util.List;

/**
 * TmResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmResource implements java.io.Serializable, Cloneable {

	// Fields

	private Long id;
	private Long parentId;
	private String resourceName;
	private String resourcePath;
	private String resourceDesc;
	private Long isLeaf;
	private List<TmResource> children;
	private boolean isChecked;
	private int level;

	// Constructors

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public List<TmResource> getChildren() {
		return children;
	}

	public void setChildren(List<TmResource> children) {
		this.children = children;
	}

	/** default constructor */
	public TmResource() {
	}

	/** minimal constructor */
	public TmResource(Long id, Long parentId, Long isLeaf) {
		this.id = id;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
	}

	/** full constructor */
	public TmResource(Long id, Long parentId, String resourceName,
			String resourcePath, String resourceDesc, Long isLeaf) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.resourceName = resourceName;
		this.resourcePath = resourcePath;
		this.resourceDesc = resourceDesc;
		this.isLeaf = isLeaf;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourcePath() {
		return this.resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public Long getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Long isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Object clone() {
		TmResource o = null;
		try {
			o = (TmResource) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}