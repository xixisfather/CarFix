package com.selfsoft.baseparameter.model;

import java.util.Date;



/**
 * TmDictionary entity. @author MyEclipse Persistence Tools
 */

public class TmDictionary  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String paramtype;
     private String paramvalue;
     private Date zeroMonth;
     private Long zeroStatus;


    // Constructors

    /** default constructor */
    public TmDictionary() {
    }

	/** minimal constructor */
    public TmDictionary(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public TmDictionary(Long id, String paramtype, String paramvalue) {
        this.id = id;
        this.paramtype = paramtype;
        this.paramvalue = paramvalue;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getParamtype() {
        return this.paramtype;
    }
    
    public void setParamtype(String paramtype) {
        this.paramtype = paramtype;
    }

    public String getParamvalue() {
        return this.paramvalue;
    }
    
    public void setParamvalue(String paramvalue) {
        this.paramvalue = paramvalue;
    }

	public Date getZeroMonth() {
		return zeroMonth;
	}

	public void setZeroMonth(Date zeroMonth) {
		this.zeroMonth = zeroMonth;
	}

	public Long getZeroStatus() {
		return zeroStatus;
	}

	public void setZeroStatus(Long zeroStatus) {
		this.zeroStatus = zeroStatus;
	}
   








}