package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.vo.TbPartCollectionVo;

public interface ITbPartCollectionService {
	
	public List<TbPartCollectionVo> getTbPartCollection();
	
	public void insertTbPartCollection(TbPartCollection tbPartCollection);
	
	public List<TbPartCollection> findByEntity(TbPartCollection tbPartCollection);
	
	public boolean isUniquecollectionCode(String collectionCode);
	
	public void deleteByCollectionCode(String collectionCode);
	
	public void deleteCollectionRelation(TbPartCollection collection);
}
