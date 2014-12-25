package com.selfsoft.business.model;

import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.framework.common.Constants;

/**
 * TbBookFixStation entity. @author MyEclipse Persistence Tools
 */

public class TbBookFixStation implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBook tbBook;
	private TbWorkingInfo tbWorkingInfo;
	private TbWorkingCollection tbWorkingCollection;
	private Long freeSymbol;

	// Constructors

	/** default constructor */
	public TbBookFixStation() {
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

	public TbWorkingInfo getTbWorkingInfo() {
		return tbWorkingInfo;
	}

	public void setTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		this.tbWorkingInfo = tbWorkingInfo;
	}


	public TbWorkingCollection getTbWorkingCollection() {
		return tbWorkingCollection;
	}

	public void setTbWorkingCollection(TbWorkingCollection tbWorkingCollection) {
		this.tbWorkingCollection = tbWorkingCollection;
	}

	public Long getFreeSymbol() {
		return freeSymbol;
	}

	public void setFreeSymbol(Long freeSymbol) {
		this.freeSymbol = freeSymbol;
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