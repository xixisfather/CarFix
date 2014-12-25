package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.framework.dao.IDao;

public interface ITmLianceRegisterDao extends IDao<TmLianceRegister> {

	public List<TmLianceRegDetailVo> getLianceRegDetailVo(Long isConfirm , Long isReturn , Long supplierId,Long lianceRegId);
	
	public List<TmLianceRegVo> getLianceRegVo(Long isConfirm , Long isReturn ,TmLianceRegister tmLianceRegister, Long lianceRegId);
	
	public void deleteLianceRegDetail(Long lianceRegId);
}
