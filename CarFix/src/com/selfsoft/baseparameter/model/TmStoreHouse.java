package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.business.model.StStorehouseReceiver;
import com.selfsoft.framework.common.Constants;
/**
 * 
 * 仓库
 *
 */
public class TmStoreHouse implements java.io.Serializable {

	private Long id;
	private String houseCode;
	private String houseName;
	private String houseRemark;
	private Long isMixed;
	private Set<TbPartInfo> tbPartInfos = new HashSet<TbPartInfo>();
	private Set<StStorehouseReceiver> stStorehouseReceivers = new HashSet<StStorehouseReceiver>();
	
	

	public TmStoreHouse() {
	}

	/** minimal constructor */
	public TmStoreHouse(String houseCode) {
		this.houseCode = houseCode;
	}

	/** full constructor */
	public TmStoreHouse(String houseCode, String houseName, String houseRemark,
			Long isValid) {
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.houseRemark = houseRemark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseCode() {
		return this.houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getHouseName() {
		return this.houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getHouseRemark() {
		return this.houseRemark;
	}

	public void setHouseRemark(String houseRemark) {
		this.houseRemark = houseRemark;
	}

	public Long getIsMixed() {
		return isMixed;
	}

	public void setIsMixed(Long isMixed) {
		this.isMixed = isMixed;
	}
   
	private String isMixedShow;

	public String getIsMixedShow() {
		if(Constants.ISTRUE.equals(this.isMixed)){
			this.isMixedShow=Constants.ISTRUESHOW;
		}
		else if(Constants.NOTTRUE.equals(this.isMixed)){
			this.isMixedShow=Constants.NOTTRUESHOW;
		}
		else{
			this.isMixedShow=Constants.NOTDIFINE;
		}
		return isMixedShow;
	}

	public void setIsMixedShow(String isMixedShow) {
		this.isMixedShow = isMixedShow;
	}

	public Set<TbPartInfo> getTbPartInfos() {
		return tbPartInfos;
	}

	public void setTbPartInfos(Set<TbPartInfo> tbPartInfos) {
		this.tbPartInfos = tbPartInfos;
	}
	

	public Set<StStorehouseReceiver> getStStorehouseReceivers() {
		return stStorehouseReceivers;
	}

	public void setStStorehouseReceivers(
			Set<StStorehouseReceiver> stStorehouseReceivers) {
		this.stStorehouseReceivers = stStorehouseReceivers;
	}
	
}