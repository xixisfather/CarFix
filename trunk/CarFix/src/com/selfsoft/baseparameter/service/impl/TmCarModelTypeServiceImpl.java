package com.selfsoft.baseparameter.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmCarModelTypeDao;
import com.selfsoft.baseparameter.dao.ITmWorkingHourPriceDao;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.model.TmWorkingHourPrice;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.framework.file.XlsReader;
import com.selfsoft.framework.file.XlsReader.XlsException;
@Service("tmCarModelTypeService")
public class TmCarModelTypeServiceImpl implements ITmCarModelTypeService{

	@Autowired
	private ITmCarModelTypeDao tmCarModelTypeDao;
	
	@Autowired
	private ITmWorkingHourPriceDao tmWorkingHourPriceDao;
	
	@Autowired
	private ITmCarStationTypeService tmCarStationTypeService;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmCarModelTypeDao.deleteById(id);
	}

	public TmCarModelType findById(Long id) {
		// TODO Auto-generated method stub
		return tmCarModelTypeDao.findById(id);
	}

	public List<TmCarModelType> findByTmCarModelType(
			TmCarModelType tmCarModelType) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmCarModelType.class);
		
		if(null!=tmCarModelType){
			
			if(null!=tmCarModelType.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmCarModelType.getId()));
			}
			if(null!=tmCarModelType.getModelCode()&&!"".equals(tmCarModelType.getModelCode())){
				detachedCriteria.add(Restrictions.like("modelCode", "%"+tmCarModelType.getModelCode()+"%"));
			}
			if(null!=tmCarModelType.getModelName()&&!"".equals(tmCarModelType.getModelName())){
				detachedCriteria.add(Restrictions.like("modelName", "%"+tmCarModelType.getModelName()+"%"));
			}
			if(null!=tmCarModelType.getTmCarStationType()){
				//内连接
				detachedCriteria.createAlias("tmCarStationType","tmCarStationType");
				//左连接
				//detachedCriteria.setFetchMode("tmCarStationType", FetchMode.JOIN);
				if(null!=tmCarModelType.getTmCarStationType().getId()){
					detachedCriteria.add(Restrictions.eq("tmCarStationType.id",tmCarModelType.getTmCarStationType().getId()));
				}
			}
		}
		return tmCarModelTypeDao.findByCriteria(detachedCriteria, tmCarModelType);
	}

	public boolean insert(TmCarModelType tmCarModelType) {
		// TODO Auto-generated method stub
		return tmCarModelTypeDao.insert(tmCarModelType);
	}

	public boolean update(TmCarModelType tmCarModelType) {
		// TODO Auto-generated method stub
		return tmCarModelTypeDao.update(tmCarModelType);
	}

	public List<TmCarModelType> findAll() {
		// TODO Auto-generated method stub
		return tmCarModelTypeDao.findAll();
	}
	/**
	 * 获取所有车辆类型的MAP,供选择车辆类型的下拉列表使用
	 */
	public Map<Long, String> findAllTmCarModelTypeMap() {
		// TODO Auto-generated method stub
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		List<TmCarModelType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			for(TmCarModelType t : list){
				map.put(t.getId(), t.getModelCode() +"  "+t.getModelName());
			}
		}
		return map;
	}
	
	public TmCarModelType findByModelCode(String modelCode){
		
		List<TmCarModelType> list = tmCarModelTypeDao.findBySQL("select tmCarModelType from TmCarModelType tmCarModelType where tmCarModelType.modelCode=?",new Object[]{modelCode});
		
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
	public String importTmCarModelType(InputStream in , String tpl){
		
		String errorMsg = "";
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		if(reader.isSuccess()){
			
			List<TmCarModelType> tmCarModelTypeList = (List<TmCarModelType>) reader.getBeans("TmCarModelType");
			
			List<TmCarStationType> tmCarStationTypeList = null;
			
			TmWorkingHourPrice tmWorkingHourPrice = tmWorkingHourPriceDao.findAll().get(0);
			
			if(null!=tmCarModelTypeList&&tmCarModelTypeList.size()>0){
				
				int i = 0;
				
				for(TmCarModelType _tmCarModelType : tmCarModelTypeList){
					
					String stationCode = _tmCarModelType.getStationCode_xls();
					
					if(null==stationCode||"".equals(stationCode)){
						
						errorMsg += "第" + i + "行车型代码为空," ;
						
					}
					else{
						
						TmCarStationType tmCarStationType = new TmCarStationType();
						
						tmCarStationType.setStationCode(stationCode);
						
						tmCarStationTypeList = tmCarStationTypeService.findTmCarStationTypeList(tmCarStationType);
						
						if(null==tmCarStationTypeList||tmCarStationTypeList.size()==0){
							
							errorMsg += "第" + i + "行  " + stationCode + " 不存在,";
							
						}
						
					}
					
					i++;
				}
				
			}
			
			if("".equals(errorMsg)){
				
				String success_flag = "success,";
				
				for(TmCarModelType _tmCarModelType : tmCarModelTypeList){
					
					String stationCode = _tmCarModelType.getStationCode_xls();
					
					TmCarStationType tmCarStationType = new TmCarStationType();
					
					tmCarStationType.setStationCode(stationCode);
					
					tmCarStationTypeList = tmCarStationTypeService.findTmCarStationTypeList(tmCarStationType);
					
					_tmCarModelType.setTmCarStationType(tmCarStationTypeList.get(0));
					
					_tmCarModelType.setTmWorkingHourPrice(tmWorkingHourPrice);
					
					this.insert(_tmCarModelType);
					
					success_flag += "成功导入车型  " + _tmCarModelType.getModelCode() ;
				}
				
				return success_flag;
			}
			
			
			
			
		}else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
				
				e.printStackTrace();
				
			}
			
			errorMsg = "文件类型或者数据不匹配,请选择正确的文件";
		}
		
		return errorMsg;
	}
	
}
