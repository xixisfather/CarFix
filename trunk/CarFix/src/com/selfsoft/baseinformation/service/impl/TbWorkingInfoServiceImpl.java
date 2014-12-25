package com.selfsoft.baseinformation.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbWorkingInfoDao;
import com.selfsoft.baseinformation.model.TbCarStationWorkingRelation;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbCarStationWorkingRelationService;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.baseparameter.model.TmCarBody;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.service.ITmCarBodyService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.file.XlsReader;
import com.selfsoft.framework.file.XlsWriter;
import com.selfsoft.framework.file.XlsReader.XlsException;
import com.selfsoft.secrity.model.TmWorkType;
import com.selfsoft.secrity.service.ITmWorkTypeService;
@Service("tbWorkingInfoService")
public class TbWorkingInfoServiceImpl implements ITbWorkingInfoService{

	@Autowired
	private ITbWorkingInfoDao tbWorkingInfoDao;
	
	@Autowired
	private ITbCarStationWorkingRelationService tbCarStationWorkingRelationService;
	
	@Autowired
	private ITmCarStationTypeService tmCarStationTypeService;
	
	@Autowired
	private ITmWorkTypeService tmWorkTypeService;
	
	@Autowired
	private ITmCarBodyService tmCarBodyService;
	
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbWorkingInfoDao.deleteById(id);
	}

	public TbWorkingInfo findById(Long id) {
		// TODO Auto-generated method stub
		return tbWorkingInfoDao.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<TbWorkingInfo> findByTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbWorkingInfo.class);
		
		if(null!=tbWorkingInfo){
			if(null!=tbWorkingInfo.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbWorkingInfo.getId()));
			}
			if(null!=tbWorkingInfo.getStationCode()){
				detachedCriteria.add(Restrictions.like("stationCode", "%"+tbWorkingInfo.getStationCode()+"%"));
			}
			if(null!=tbWorkingInfo.getStationName()){
				detachedCriteria.add(Restrictions.like("stationName", "%"+tbWorkingInfo.getStationName()+"%"));
			}
			if(null!=tbWorkingInfo.getPinyinCode()){
				detachedCriteria.add(Restrictions.like("pinyinCode", "%"+tbWorkingInfo.getPinyinCode()+"%"));
			}
			if(null!=tbWorkingInfo.getTmCarStationTypeId()){
				
				detachedCriteria.createAlias("tbCarStationWorkingRelations","tbCarStationWorkingRelations");
			
				detachedCriteria.add(Restrictions.eq("tbCarStationWorkingRelations.tmCarStationType.id",tbWorkingInfo.getTmCarStationTypeId()));
			}
			
		}
		//获取所有车型名称
		List<TbWorkingInfo>list = tbWorkingInfoDao.findByCriteria(detachedCriteria, tbWorkingInfo);
		List<TbWorkingInfo>list_show = null;
		if(null!=list&&list.size()>0){
			list_show  = new ArrayList<TbWorkingInfo>();
			
			for(TbWorkingInfo t : list){
				
				Set tbCarStationWorkingRelations = t.getTbCarStationWorkingRelations();
				
				Iterator it = tbCarStationWorkingRelations.iterator();
				
				String tmCarStationNames = "";
				while(it.hasNext()){
					TbCarStationWorkingRelation tbCarStationWorkingRelation = (TbCarStationWorkingRelation) it.next();
				
					tbCarStationWorkingRelation  = tbCarStationWorkingRelationService.findById(tbCarStationWorkingRelation.getId());
					
					if(null!=tbCarStationWorkingRelation){
						TmCarStationType stationType = tmCarStationTypeService.findById(tbCarStationWorkingRelation.getTmCarStationType().getId());
						
						if(null!=stationType){
							tmCarStationNames+= stationType.getStationRemark()==null?"":stationType.getStationRemark();
						}
					}
				}
				
				t.setTmCarStationNames(tmCarStationNames);
				
				list_show.add(t);
			}
		}
		return list_show;
	}

	public void insertTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		// TODO Auto-generated method stub
		tbWorkingInfoDao.insert(tbWorkingInfo);
	}

	public void updateTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		// TODO Auto-generated method stub
		tbWorkingInfoDao.update(tbWorkingInfo);
	}

	/**
	 * 同时插入工时工位表与车型工时关系表
	 */
	public void insertRelation(TbWorkingInfo tbWorkingInfo) {
		
		this.insertTbCarStationWorkingRelation(tbWorkingInfo);
		
		this.insertTbWorkingInfo(tbWorkingInfo);
	}
	
	/**
	 * 同时更新工时工位表与车型工时关系表
	 */
	public void updateRelation(TbWorkingInfo tbWorkingInfo){
		
		this.updateTbWorkingInfo(tbWorkingInfo);
		
		//this.deleteTbCarStationWorkingRelation(tbWorkingInfo);
		
		//this.insertTbCarStationWorkingRelation(tbWorkingInfo);
		
	}
	
	/**
	 * 车型工位-工位工时关系插入
	 */
	private void insertTbCarStationWorkingRelation(TbWorkingInfo tbWorkingInfo){
		
		Set tmCarStationTypes = tbWorkingInfo.getTmCarStationTypes();
		
		Iterator it = tmCarStationTypes.iterator();
		
		while(it.hasNext()){
			
			String tbCarStationWorkingRelation_tmCarStationType_id = (String) it.next();
			
			TmCarStationType tmCarStationType = new TmCarStationType();
			
			tmCarStationType.setId(Long.valueOf(tbCarStationWorkingRelation_tmCarStationType_id));
			
			TbCarStationWorkingRelation tbCarStationWorkingRelation = new TbCarStationWorkingRelation();
			
			tbCarStationWorkingRelation.setTbWorkingInfo(tbWorkingInfo);
			
			tbCarStationWorkingRelation.setTmCarStationType(tmCarStationType);
			
			tbCarStationWorkingRelationService.insert(tbCarStationWorkingRelation);
		}
		
	}
	
	/**
	 * 改工位工时对应的车型工位关系删除
	 */
	private void deleteTbCarStationWorkingRelation(TbWorkingInfo tbWorkingInfo){
		
		TbCarStationWorkingRelation tbCarStationWorkingRelation = new TbCarStationWorkingRelation();
		
		tbCarStationWorkingRelation.setTbWorkingInfo(tbWorkingInfo);
		//这里主要根据TbWorkingInfo的ID来查找工位工时对应的车型工位关系集，并把它们的关系删除
		List<TbCarStationWorkingRelation>list = tbCarStationWorkingRelationService.findByTbCarStationWorkingRelation(tbCarStationWorkingRelation);
		
		if(null!=list&&list.size()>0){
			
			for(TbCarStationWorkingRelation t : list){
				
				tbCarStationWorkingRelationService.delete(t.getId());
			
			}
		}
		
	}

	//根据工位号查询
	public List<TbWorkingInfo>findByStationCode(String stationCode){
		
		return tbWorkingInfoDao.findBySQL("FROM TbWorkingInfo tbWorkingInfo where tbWorkingInfo.stationCode = ?", new Object[]{stationCode});
	
	}
	
	public List<Object[]>findByStationCodeAndCarStationTypeId(String stationCode,Long carStationTypeId){
		
		return tbWorkingInfoDao.findByOriginSql("select * from TB_WORKING_INFO w inner join TB_CAR_STATION_WORKING_RELATION r on w.id = r.WORKING_ID where w.STATION_CODE = ? and r.STATION_ID = ?", new Object[]{stationCode,carStationTypeId});
		
	}
	
	//删除工位时应将对应的关系也同时删除
	public boolean deleteRelation(Long id){
		
		TbWorkingInfo tbWorkingInfo = this.findById(id);
		
		this.deleteTbCarStationWorkingRelation(tbWorkingInfo);
		
		this.deleteById(id);
		
		return true;
	}
	
	//导入EXCEL中工位信息
	public String tbWorkingInfoImportXls(InputStream in , String tpl, String tmCarStationTypeId) throws IOException{
		//返回信息
		String flag = "";
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		Set tmCarStationTypes = new HashSet();
		
		tmCarStationTypes.add(tmCarStationTypeId);
		
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			List<TbWorkingInfo> tbWorkingInfoList = (List<TbWorkingInfo>) reader.getBeans("TbWorkingInfo");
		
			if(null!=tbWorkingInfoList&&tbWorkingInfoList.size()>0){
				//检查标志
				int i = 0;
				//插入标志
				int j = 0;
				//重复标志
				int k = 0;
				//行标志
				int p = 0;
				
				//拼接错误数据字符串
				String flagError="";
				//拼接重复数据字符串
				String falgRepeat="";
				//数据错误检查
				for(TbWorkingInfo tbWorkingInfo : tbWorkingInfoList){
					String errorMessage = "";
					i++;
					if(null==tbWorkingInfo.getStationCode()||"".equals(tbWorkingInfo.getStationCode())){
						errorMessage +="工位代码为空;";
					}
					if(null==tbWorkingInfo.getStationName()||"".equals(tbWorkingInfo.getStationName())){
						errorMessage +="工位名称为空;";
					}
					
					if(!"".equals(errorMessage)){
						flagError +="第"+i+"行数据出错:"+errorMessage+",";
					}
				}
				
				if(!"".equals(flagError)){
					flag +="failError-"+flagError;
					return flag;
				}
				
				for(TbWorkingInfo tbWorkingInfo : tbWorkingInfoList){
					
					p++;
					
					List<Object[]> list = this.findByStationCodeAndCarStationTypeId(tbWorkingInfo.getStationCode(), Long.valueOf(tmCarStationTypeId));
					
					if(null!=list&&list.size()>0){
						k++;
						falgRepeat+="第"+p+"行数据    工位号: "+tbWorkingInfo.getStationCode()+" 已存在;";
						continue;
					}
					//设置车辆工位类型
					tbWorkingInfo.setTmCarStationTypes(tmCarStationTypes);
					
					if(null!=tbWorkingInfo.getWorkName()&&!"".equals(tbWorkingInfo.getWorkName())){
						//根据工种名字查找工种对象
						List<TmWorkType> tmWorkTypeList = tmWorkTypeService.findTmWorkTypeByWorkName(tbWorkingInfo.getWorkName());
					
						if(null!=tmWorkTypeList&&tmWorkTypeList.size()>0){
							//设置工种
							tbWorkingInfo.setTmWorkType(tmWorkTypeList.get(0));
						}
					}
					
					if(null!=tbWorkingInfo.getBodyName()&&!"".equals(tbWorkingInfo.getBodyName())){
						//根据车身名字查找车身对象
						List<TmCarBody> tmCarBodyList = tmCarBodyService.findTmCarBodyByBodyName(tbWorkingInfo.getBodyName());
					
						if(null!=tmCarBodyList&&tmCarBodyList.size()>0){
							//设置车身
							tbWorkingInfo.setTmCarBody(tmCarBodyList.get(0));
						}
					}
					
					if(null==tbWorkingInfo.getPinyinCode()||"".equals(tbWorkingInfo.getPinyinCode())){
						tbWorkingInfo.setPinyinCode(CommonMethod.tranferToPinYin(tbWorkingInfo.getStationName()));
					}
					
					this.insertXlsRelation(tbWorkingInfo);
					
					j++;
				}
				
				flag += "success-成功导入数据"+j+"条";
				if(!"".equals(falgRepeat))
				flag += ",已存在工时工位数据"+k+"条;"+falgRepeat;
			}
			//如果文件中无数据
			else{
				flag = "fail-文件中无数据,请确保文件中有数据且工位代码不能为空";
			}
		}else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
			
				flag = "fail-文件类型或者数据不匹配,请选择正确的文件";
				
				e.printStackTrace();
				
			}
		}
		return flag;
	}
	//导入EXCEL文件时插入关系
	private void insertXlsRelation(TbWorkingInfo tbWorkingInfo){
		
		this.insertTbWorkingInfo(tbWorkingInfo);
		
		this.insertTbCarStationWorkingRelation(tbWorkingInfo);
		
	}
	
	public void tbWorkingInfoExportXls(OutputStream os,String tpl,List<TbWorkingInfo> tbWorkingInfoList) {
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, tbWorkingInfoList);
				
				wwb.write();
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				wwb.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 根据车型工位ID查找工时工位LIST
	 */
	public List<TbWorkingInfo> findTbWorkingInfoListByTmCarStationTypeId(Long tmCarStationTypeId){
		
		TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();
		
		tbWorkingInfo.setTmCarStationTypeId(tmCarStationTypeId);
		
		return this.findByTbWorkingInfo(tbWorkingInfo);
	}
	
	/**
	 * 根据车型工位ID查找工时工位MAP
	 */
	public Map<Long,String> findTbWorkingInfoMapByTmCarStationTypeId(Long tmCarStationTypeId){
		
		TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();
		
		tbWorkingInfo.setTmCarStationTypeId(tmCarStationTypeId);
		
		List<TbWorkingInfo> tbWorkingInfoList = this.findByTbWorkingInfo(tbWorkingInfo);
		
		Map<Long,String> tbWorkingInfoMap = new LinkedHashMap<Long,String>();;
		
		if(null!=tbWorkingInfoList&&tbWorkingInfoList.size()>0){
			
			for(TbWorkingInfo tbWorkingInfoParam : tbWorkingInfoList){
				tbWorkingInfoMap.put(tbWorkingInfoParam.getId(), tbWorkingInfoParam.getStationCode() +"   |   "+tbWorkingInfoParam.getStationName());
			}
		}
		
		return tbWorkingInfoMap;
	}
	
	/**
	 * 根据车型ID来获取对应的工时工位LIST
	 */
	public List<TbWorkingInfo> findTbWorkingInfoListByTmCarModelTypeId(Long tmCarModelTypeId){
		
		TmCarStationType tmCarStationType = tmCarStationTypeService.findTmCarStationTypeByTmCarModelTypeId(tmCarModelTypeId);
		
		if(null!=tmCarStationType){
			return this.findTbWorkingInfoListByTmCarStationTypeId(tmCarStationType.getId());
		}
		
		return null;
	}
	
	/**
	 * 根据车型ID来获取对应的工时工位MAP
	 */
	public Map<Long,String> findTbWorkingInfoMapByTmCarModelTypeId(Long tmCarModelTypeId){
		
		Map map = new LinkedHashMap<Long,String>();
		
		TmCarStationType tmCarStationType = tmCarStationTypeService.findTmCarStationTypeByTmCarModelTypeId(tmCarModelTypeId);
		
		if(null!=tmCarStationType){
			return this.findTbWorkingInfoMapByTmCarStationTypeId(tmCarStationType.getId());
		}
		
		return map;
	}
	/**
	 * 工位集ID获取工时工位LIST
	 */
	public List<TbWorkingInfo> findTbWorkingInfoListByTbWorkingCollectionId(Long tbWorkingCollectionId){
		
		return tbWorkingInfoDao.findBySQL("SELECT tbWorkingInfo FROM TbWorkingInfo tbWorkingInfo inner join tbWorkingInfo.tbWorkingRelations tbWorkingRelations where tbWorkingRelations.tbWorkingCollection.id=?", new Object[]{tbWorkingCollectionId});
		
	}
}
