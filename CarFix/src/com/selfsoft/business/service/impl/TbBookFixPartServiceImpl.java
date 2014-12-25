package com.selfsoft.business.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.business.dao.ITbBookFixPartDao;
import com.selfsoft.business.model.TbBookFixPart;
import com.selfsoft.business.service.ITbBookFixPartService;

@Service("tbBookFixPartService")
public class TbBookFixPartServiceImpl implements ITbBookFixPartService{
	
	@Autowired
	private ITbBookFixPartDao tbBookFixPartDao;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbBookFixPartDao.deleteById(id);
	}

	public TbBookFixPart findById(Long id) {
		// TODO Auto-generated method stub
		return tbBookFixPartDao.findById(id);
	}

	public void insert(TbBookFixPart tbBookFixPart) {
		// TODO Auto-generated method stub
		tbBookFixPartDao.insert(tbBookFixPart);
	}

	public void update(TbBookFixPart tbBookFixPart) {
		// TODO Auto-generated method stub
		tbBookFixPartDao.update(tbBookFixPart);
	}
	
	/**
	 * 根据预约单ID查找预约配件信息LIST
	 */
	public List<TbBookFixPart> findTbBookFixPartListByTbBookId(Long tbBookId){
		
		return tbBookFixPartDao.findBySQL("SELECT tbBookFixPart FROM TbBookFixPart tbBookFixPart where tbBookFixPart.tbBook.id=?", new Object[]{tbBookId});
	}
	/**
	 * 根据预约单ID查找预约配件信息MAP
	 */
	public Map<String,String>  findTbBookFixPartMapByTbBookId(Long tbBookId){
		
		Map<String,String> tbBookFixPartMap = new LinkedHashMap<String,String>();
		
		List<TbBookFixPart> tbBookFixPartList = this.findTbBookFixPartListByTbBookId(tbBookId);
		
		if(null!=tbBookFixPartList&&tbBookFixPartList.size()>0){
			for(TbBookFixPart tbBookFixPart : tbBookFixPartList){
				
				TbPartInfo tbPartInfo = tbPartInfoService.findById(tbBookFixPart.getTbPartInfo().getId());
				
				tbBookFixPart.setTbPartInfo(tbPartInfo);
				
				tbBookFixPartMap.put(tbBookFixPart.getTbPartInfo().getId()+","+tbBookFixPart.getFreeSymbol()+","+tbBookFixPart.getPartQuantity()+","+tbBookFixPart.getDealType(), tbBookFixPart.getFreeSymbolShow()+" "+tbBookFixPart.getPartQuantity()+" "+tbBookFixPart.getDealTypeShow()+" "+tbBookFixPart.getTbPartInfo().getPartCode()+" "+tbBookFixPart.getTbPartInfo().getPartName());
			}
		}
		
		return tbBookFixPartMap;
	}
}
