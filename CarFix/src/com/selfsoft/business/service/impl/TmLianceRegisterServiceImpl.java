package com.selfsoft.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITmLianceRegisterDao;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;
@Service("tmLianceRegisterService")
public class TmLianceRegisterServiceImpl implements ITmLianceRegisterService {

	@Autowired
	private ITmLianceRegisterDao tmLianceRegisterDao;

	public TmLianceRegister findById(Long id) {
		return tmLianceRegisterDao.findById(id);
	}

	public void insert(TmLianceRegister tmLianceRegister) {
		tmLianceRegisterDao.insert(tmLianceRegister);
	}

	public void update(TmLianceRegister tmLianceRegister) {
		tmLianceRegisterDao.update(tmLianceRegister);
		
	}
	
	public List<TmLianceRegDetailVo> getLianceRegDetailVo(Long isConfirm,Long isReturn,Long supplierId,Long lianceRegId){
		return tmLianceRegisterDao.getLianceRegDetailVo(isConfirm,isReturn,supplierId, lianceRegId);
	}
	
	public List<TmLianceRegVo> getLianceRegVo(Long isConfirm , Long isReturn ,TmLianceRegister tmLianceRegister, Long lianceRegId){
		return tmLianceRegisterDao.getLianceRegVo( isConfirm , isReturn ,tmLianceRegister,lianceRegId);
	}
	
	/**
	 * 删除所有借进登记单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteLianceRegister(Long lianceRegId){
		//删除入库明细表
		tmLianceRegisterDao.deleteLianceRegDetail(lianceRegId);
		//删除入库主表
		boolean flag = tmLianceRegisterDao.deleteById(lianceRegId);
		
		return flag;
	}
}
