package com.selfsoft.business.service.impl;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbSpecialWorkingContentDao;
import com.selfsoft.business.model.TbSpecialWorkingContent;
import com.selfsoft.business.service.ITbSpecialWorkingContentService;

@Service("tbSpecialWorkingContentService")
public class TbSpecialWorkingContentServiceImpl implements ITbSpecialWorkingContentService{

	@Autowired
	private ITbSpecialWorkingContentDao tbSpecialWorkingContentDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbSpecialWorkingContentDao.deleteById(id);
	}

	public void insert(TbSpecialWorkingContent tbSpecialWorkingContent) {
		// TODO Auto-generated method stub
		tbSpecialWorkingContentDao.insert(tbSpecialWorkingContent);
	}

	public void update(TbSpecialWorkingContent tbSpecialWorkingContent) {
		// TODO Auto-generated method stub
		tbSpecialWorkingContentDao.update(tbSpecialWorkingContent);
	}
	
	public List<TbSpecialWorkingContent> findBySpecialId(Long specialId){
		
		return tbSpecialWorkingContentDao.findBySQL("SELECT tbSpecialWorkingContent FROM TbSpecialWorkingContent tbSpecialWorkingContent where tbSpecialWorkingContent.tbBusinessSpecialBalance.id=?",new Object[]{specialId});
	}
	
	public void deleteBySpecialId(Long specialId){
		
		List<TbSpecialWorkingContent> tbSpecialWorkingContentList = this.findBySpecialId(specialId);
		
		if(null!=tbSpecialWorkingContentList&&tbSpecialWorkingContentList.size()>0){
			
			for(TbSpecialWorkingContent tbSpecialWorkingContent : tbSpecialWorkingContentList){
				
				tbSpecialWorkingContentDao.delete(tbSpecialWorkingContent);
			}
		}
		
	}
	
	public BigDecimal countTbSpecialWorkingContent(Long specialId){
		
		BigDecimal d = new BigDecimal("0.00");
		
		List<TbSpecialWorkingContent> list = this.findBySpecialId(specialId);
		
		if(null!=list&&list.size()>0){
			
			for(TbSpecialWorkingContent t : list){
				
				d = d.add(new BigDecimal(String.valueOf(t.getFixHourAll())));
				
			}
			
		}
		
		return d;
	}
	
	
}
