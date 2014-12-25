package com.selfsoft.baseinformation.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.selfsoft.baseinformation.model.TbWorkingInfo;

public interface ITbWorkingInfoService {
	public List<TbWorkingInfo> findByTbWorkingInfo(TbWorkingInfo tbWorkingInfo);
	
	public void insertTbWorkingInfo(TbWorkingInfo tbWorkingInfo);
	
	public void updateTbWorkingInfo(TbWorkingInfo tbWorkingInfo);
	
	public TbWorkingInfo findById(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean deleteRelation(Long id);
	
	public void insertRelation(TbWorkingInfo tbWorkingInfo);
	
	public void updateRelation(TbWorkingInfo tbWorkingInfo);
	
	public List<TbWorkingInfo>findByStationCode(String stationCode);
	
	public List<Object[]>findByStationCodeAndCarStationTypeId(String stationCode,Long carStationTypeId);

	public String tbWorkingInfoImportXls(InputStream in , String tpl, String tmCarStationTypeId) throws IOException;

	public void tbWorkingInfoExportXls(OutputStream os,String tpl,List<TbWorkingInfo> tbWorkingInfoList);

	public List<TbWorkingInfo> findTbWorkingInfoListByTmCarStationTypeId(Long tmCarStationTypeId);
	
	public Map<Long,String> findTbWorkingInfoMapByTmCarStationTypeId(Long tmCarStationTypeId);

	public List<TbWorkingInfo> findTbWorkingInfoListByTmCarModelTypeId(Long tmCarModelTypeId);

	public Map<Long,String> findTbWorkingInfoMapByTmCarModelTypeId(Long tmCarModelTypeId);
	
	public List<TbWorkingInfo> findTbWorkingInfoListByTbWorkingCollectionId(Long tbWorkingCollectionId);
}


