package com.selfsoft.business.model;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.framework.common.Constants;

/**
 * TbBookFixPart entity. @author MyEclipse Persistence Tools
 */

public class TbBookFixPart implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBook tbBook;
	private TbPartInfo tbPartInfo;
	private Long dealType;
	private Double partQuantity;
	private Long freeSymbol;
	// Constructors

	/** default constructor */
	public TbBookFixPart() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbBook getTbBook() {
		return tbBook;
	}

	public void setTbBook(TbBook tbBook) {
		this.tbBook = tbBook;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	public Long getDealType() {
		return this.dealType;
	}

	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}

	public Double getPartQuantity() {
		return this.partQuantity;
	}

	public void setPartQuantity(Double partQuantity) {
		this.partQuantity = partQuantity;
	}
	
	public Long getFreeSymbol() {
		return freeSymbol;
	}

	public void setFreeSymbol(Long freeSymbol) {
		this.freeSymbol = freeSymbol;
	}

	private String dealTypeShow;

	public String getDealTypeShow() {
		if(Constants.DEALTYPECHANGE.equals(this.dealType)){
			dealTypeShow = Constants.DEALTYPECHANGESHOW;
		}
		else if(Constants.DEALTYPEFIX.equals(this.dealType)){
			dealTypeShow = Constants.DEALTYPEFIXSHOW;
		}
		return dealTypeShow;
	}

	public void setDealTypeShow(String dealTypeShow) {
		this.dealTypeShow = dealTypeShow;
	}
	private String freeSymbolShow;

	public String getFreeSymbolShow() {
		
		if(Constants.FREESYMBOL_NO.equals(this.freeSymbol)){
			freeSymbolShow = Constants.FREESYMBOL_NO_SHOW;
		}
		else if(Constants.FREESYMBOL_SP.equals(this.freeSymbol)){
			freeSymbolShow = Constants.FREESYMBOL_SP_SHOW;
		}
		else if(Constants.FREESYMBOL_SB.equals(this.freeSymbol)){
			freeSymbolShow = Constants.FREESYMBOL_SB_SHOW;
		}
		else if(Constants.FREESYMBOL_FG.equals(this.freeSymbol)){
			freeSymbolShow = Constants.FREESYMBOL_FG_SHOW;
		}
		else if(Constants.FREESYMBOL_FW.equals(this.freeSymbol)){
			freeSymbolShow = Constants.FREESYMBOL_FW_SHOW;
		}
		return freeSymbolShow;
	}

	public void setFreeSymbolShow(String freeSymbolShow) {
		this.freeSymbolShow = freeSymbolShow;
	}
	
	
}