package com.selfsoft.baseinformation.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;

public interface ITbCustomerService {

	public List<TbCustomer> findByTbCustomer(TbCustomer tbCustomer,String types);
	
	public void insertTbCustomer(TbCustomer tbCustomer);
	
	public void updateTbCustomer(TbCustomer tbCustomer);
	
	public TbCustomer findById(Long id);
	
	public boolean deleteById(Long id);
	
	public TbCustomer findTbCustomerByCustomerCode(String customerCode);
	
	public void insertAll(TbCustomer tbCustomer,TbCarInfo tbCarInfo);
	
	public List<TbCustomer> findNotComeCustomer(Date date);
	
	public String tbCustomerImportXls(InputStream in , String tpl);
	
	public TbCustomer findTbCustomerByStockInDetailId(Long stockInDetailId);
	
	public TbCustomer findByCode(String customerCode);
	
	public void tbCustomerInfoExportXls(OutputStream os,String tpl,List<TbCustomer> tbCustomerList);
}
