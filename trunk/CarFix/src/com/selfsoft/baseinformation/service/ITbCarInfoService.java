package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbCarInfo;

public interface ITbCarInfoService {
	public List<TbCarInfo> findByTbCarInfo(TbCarInfo tbCarInfo);
	
	public void insertTbCarInfo(TbCarInfo tbCarInfo);
	
	public void updateTbCarInfo(TbCarInfo tbCarInfo);
	
	public TbCarInfo findById(Long id);
	
	public boolean deleteById(Long id);
	
	public TbCarInfo findTbCarInfoBylicenseCode(String licenseCode);
	
	public TbCarInfo findTbCarInfoById(Long id);
	
	public List<TbCarInfo> findMaintainCarInfo();
	
	public List<TbCarInfo> findLostCar(TbCarInfo tbCarInfo);
	
	/**
	 * 需要维修回访列表
	 * @return
	 */
	public List<TbCarInfo> findMaintainHFCar(TbCarInfo tbCarInfo);
	
	public List<TbCarInfo> findInsuranceCarInfo();
	
	/**
	 * 通过底盘号查找车辆信息，包含车主信息
	 */
	public TbCarInfo findTbCarInfoByChassisCode(String chassisCode);
}
