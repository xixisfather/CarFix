package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.vo.WorkTypeHourPriceVo;
import com.selfsoft.framework.dao.IDao;

public interface ITbFixShareDao extends IDao<TbFixShare>{

	public List<TbFixShare> getTbFixShareByGroupFixPerson(TbFixShare tbFixShare);
	
	public List<WorkTypeHourPriceVo> getWorkTypeStat(TbFixShare tbFixShare);
}
