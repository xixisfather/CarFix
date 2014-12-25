package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;

public interface ITmLianceRegisterService {

	public void insert(TmLianceRegister tmLianceRegister);
	
	public TmLianceRegister findById(Long id);
	
	public void update(TmLianceRegister tmLianceRegister);
	
	public List<TmLianceRegDetailVo> getLianceRegDetailVo(Long isConfirm,Long isReturn,Long supplierId,Long lianceRegId);
	
	public List<TmLianceRegVo> getLianceRegVo(Long isConfirm , Long isReturn , TmLianceRegister tmLianceRegister, Long lianceRegId);
	
	public boolean deleteLianceRegister(Long lianceRegId);
}
