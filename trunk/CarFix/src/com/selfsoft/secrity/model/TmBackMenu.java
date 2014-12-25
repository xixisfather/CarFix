package com.selfsoft.secrity.model;

/**
 * TmBackmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmBackMenu implements java.io.Serializable {

	// Fields

	private Long id;
	private Long parentId;
	private String treeName;
	private String url;

	// Constructors

	/** default constructor */
	public TmBackMenu() {
	}

	/** minimal constructor */
	public TmBackMenu(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmBackMenu(Long id, Long parentId, String treeName, String url) {
		this.id = id;
		this.parentId = parentId;
		this.treeName = treeName;
		this.url = url;
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

	public String getTreeName() {
		return this.treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}