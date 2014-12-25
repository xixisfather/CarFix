package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbSpecialPartContentDao;
import com.selfsoft.business.model.TbSpecialPartContent;
import com.selfsoft.business.model.TbSpecialWorkingContent;
import com.selfsoft.business.service.ITbSpecialPartContentService;
import com.selfsoft.framework.common.CommonMethod;

@Service("tbSpecialPartContentService")
public class TbSpecialPartContentServiceImpl implements ITbSpecialPartContentService{

	@Autowired
	private ITbSpecialPartContentDao tbSpecialPartContentDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbSpecialPartContentDao.deleteById(id);
	}

	public void insert(TbSpecialPartContent tbSpecialPartContent) {
		// TODO Auto-generated method stub
		tbSpecialPartContentDao.insert(tbSpecialPartContent);
	}

	public void update(TbSpecialPartContent tbSpecialPartContent) {
		// TODO Auto-generated method stub
		tbSpecialPartContentDao.update(tbSpecialPartContent);
	}
	
	public List<TbSpecialPartContent> findBySpecialId(Long specialId){
		
		return tbSpecialPartContentDao.findBySQL("SELECT tbSpecialPartContent FROM TbSpecialPartContent tbSpecialPartContent where tbSpecialPartContent.tbBusinessSpecialBalance.id=?",new Object[]{specialId});
	}
	
	public void deleteBySpecialId(Long specialId){
		
		List<TbSpecialPartContent> tbSpecialPartContentList = this.findBySpecialId(specialId);
		
		if(null!=tbSpecialPartContentList&&tbSpecialPartContentList.size()>0){
			
			for(TbSpecialPartContent tbSpecialPartContent : tbSpecialPartContentList){
				
				tbSpecialPartContentDao.delete(tbSpecialPartContent);
				
			}
		}
	}
	
	public BigDecimal countTbSpecialPartContent(Long specialId){
		
		BigDecimal d = new BigDecimal("0.00");
		
		List<TbSpecialPartContent> list = this.findBySpecialId(specialId);
		
		if(null!=list&&list.size()>0){
			
			for(TbSpecialPartContent t : list){
				
				d = d.add(new BigDecimal(String.valueOf(t.getPartTotal())));
				
			}
			
		}
		
		return d;
	}

	
	public Double getTotalCostPriceBySpecialPartList(List<TbSpecialPartContent> specialPartContentList){
		if(specialPartContentList == null || specialPartContentList.size() == 0)
			return 0D;
		Double totalCostPrice = 0D;
		for(TbSpecialPartContent tbSpecialPartContent : specialPartContentList){
			totalCostPrice += tbSpecialPartContent.getTbPartInfo().getCostPrice() * tbSpecialPartContent.getPartQuantity();
		}
		
		return CommonMethod.convertRadixPoint(totalCostPrice, 2);
	}

	public BigDecimal countTbSpecialPartContentCost(Long specialId){
		
		List<TbSpecialPartContent> specialPartContentList = this.findBySpecialId(specialId);
		
		return new BigDecimal(String.valueOf(this.getTotalCostPriceBySpecialPartList(specialPartContentList)));
		
	}
	

}
