package com.selfsoft.baseinformation.dao;

import java.util.List;

import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.vo.TbPartCollectionVo;
import com.selfsoft.framework.dao.IDao;

public interface ITbPartCollectionDao extends IDao<TbPartCollection>{
	
	public List<TbPartCollectionVo> getTbPartCollection();
	
	public int deleteByCollectionCode(String collectionCode);
}
