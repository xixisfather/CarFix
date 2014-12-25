package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLianceRegisterDetail;
import com.selfsoft.business.vo.TmLianceRegDetailVo;

public interface ITmLianceRegisterDetailService {

	public void insertBatchLianceDetail(Long isConfirm ,Long lianceRegId,String partCol);
	
	public void updateBatchPartInfoLiance(Long lianceId);
	
	public void updateBatchLianceDetail(TmLianceRegister tmLianceRegister,String partCol);
	
	public List<TmLianceRegisterDetail> findByEntity(TmLianceRegisterDetail tmLianceRegisterDetail);
	
}
