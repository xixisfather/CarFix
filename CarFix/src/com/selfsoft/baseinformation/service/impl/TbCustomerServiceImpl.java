package com.selfsoft.baseinformation.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbCustomerDao;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.file.XlsReader;
import com.selfsoft.framework.file.XlsWriter;
import com.selfsoft.framework.file.XlsReader.XlsException;
@Service("tbCustomerService")
public class TbCustomerServiceImpl implements ITbCustomerService{

	@Autowired
	private ITbCustomerDao tbCustomerDao;
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	public List<TbCustomer> findByTbCustomer(TbCustomer tbCustomer,String types) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbCustomer.class);
		
		if(null!=tbCustomer){
			if(null!=tbCustomer.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbCustomer.getId()));
			}
			if(null!=tbCustomer.getCustomerCode()&&!"".equals(tbCustomer.getCustomerCode())){
				detachedCriteria.add(Restrictions.like("customerCode", "%"+tbCustomer.getCustomerCode()+"%"));
			}
			if(null!=tbCustomer.getCustomerName()&&!"".equals(tbCustomer.getCustomerName())){
				detachedCriteria.add(Restrictions.like("customerName", "%"+tbCustomer.getCustomerName()+"%"));
			}
			if(null!=tbCustomer.getPinyinCode()&&!"".equals(tbCustomer.getPinyinCode())){
				detachedCriteria.add(Restrictions.like("pinyinCode", "%"+tbCustomer.getPinyinCode()+"%"));
			}
			if(null!=tbCustomer.getTelephone()&&!"".equals(tbCustomer.getTelephone())){
				detachedCriteria.add(Restrictions.like("telephone", "%"+tbCustomer.getTelephone()+"%"));
			}
			if(null!=tbCustomer.getCustomerProperty()){
				detachedCriteria.add(Restrictions.eq("customerProperty", tbCustomer.getCustomerProperty()));
			}
			if(null!=tbCustomer.getTmCustomerType()){
				detachedCriteria.setFetchMode("tmCustomerType", FetchMode.JOIN);
				if(null!=tbCustomer.getTmCustomerType().getId()){
					detachedCriteria.add(Restrictions.eq("tmCustomerType.id",tbCustomer.getTmCustomerType().getId()));
				}
			}
			
		}
		if(StringUtils.isNotBlank(types)){
			List<Long> list  = new ArrayList<Long>();
			for(String type : types.split(",")){
				list.add(new Long(type));
			}
			
			detachedCriteria.add(Restrictions.in("customerProperty",list));
		}
		return tbCustomerDao.findByCriteria(detachedCriteria, tbCustomer);
	}

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbCustomerDao.deleteById(id);
	}

	public TbCustomer findById(Long id) {
		// TODO Auto-generated method stub
		return tbCustomerDao.findById(id);
	}

	public void insertTbCustomer(TbCustomer tbCustomer) {
		// TODO Auto-generated method stub
		tbCustomerDao.insert(tbCustomer);
	}

	public void updateTbCustomer(TbCustomer tbCustomer) {
		// TODO Auto-generated method stub
		tbCustomerDao.update(tbCustomer);
	}

	public TbCustomer findTbCustomerByCustomerCode(String customerCode){
		
		List<TbCustomer>tbCustomerList = tbCustomerDao.findBySQL("FROM TbCustomer tbCustomer where tbCustomer.customerCode = ?",new Object[]{customerCode} );
		
		if(null!=tbCustomerList&&tbCustomerList.size()>0){
			return tbCustomerList.get(0);
		}
		
		return null;
	}
	
	public void insertAll(TbCustomer tbCustomer,TbCarInfo tbCarInfo){
		
		this.insertTbCustomer(tbCustomer);
		
		tbCarInfo.setTbCustomer(tbCustomer);
		
		tbCarInfoService.insertTbCarInfo(tbCarInfo);
	}
	
	public List<TbCustomer> findNotComeCustomer(Date date){
		
		if(null!=date){
			
			String hql = "select tbCustomer from TbCustomer tbCustomer where tbCustomer.id not in (select tbFixEntrust.tbCustomer.id from TbFixEntrust tbFixEntrust where tbFixEntrust.fixDate>=?) and tbCustomer.customerProperty != 2";
			
			return tbCustomerDao.findBySQL(hql, new Object[]{date});
		}
		
		return null;
	}
	
	public String tbCustomerImportXls(InputStream in , String tpl){
		
		final String FORMAT = "yyyy-MM-dd";
		
		String errorMsg = "";
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		if(reader.isSuccess()){
			
			List<TbCustomer> tbCustomerList = (List<TbCustomer>) reader.getBeans("TbCustomer");
			
			List<TbCustomer> tbCustomerList_add = new ArrayList<TbCustomer>();
			
			if(null==tbCustomerList||tbCustomerList.size()==0){
				
				errorMsg += "客户信息为空,";
				
			}
			
			else{
				
				int row = 0 ;
				
				for(TbCustomer _tbCustomer : tbCustomerList){
					
					++row;
					
					if(null==_tbCustomer.getCustomerName()||"".equals(_tbCustomer.getCustomerName())){
						
						errorMsg += "("+row+")客户姓名为空,";
						
					}
					
					else{
						
						_tbCustomer.setPinyinCode(CommonMethod.tranferToPinYin(_tbCustomer.getCustomerName()));
						
					}
					
					if(null==_tbCustomer.getContractPerson()||"".equals(_tbCustomer.getContractPerson())){
						
						errorMsg += "("+row+")联系人为空,";
						
					}
					
					if(null!=_tbCustomer.getCustomerPropertyShow()&&!"".equals(_tbCustomer.getCustomerPropertyShow())){
						
						Map<String,Long> customerPropertyValueMap = Constants.getCustomerPropertyValueMap();
						
						Long customerProperty = customerPropertyValueMap.get(_tbCustomer.getCustomerPropertyShow().trim());
						
						if(null!=customerProperty){
							
							_tbCustomer.setCustomerProperty(customerProperty);
							
						}
						
					}
					
					tbCustomerList_add.add(_tbCustomer);
				}
				
				
			}
			
			List<TbCarInfo> tbCarInfoList = (List<TbCarInfo>) reader.getBeans("TbCarInfo");
			
			List<TbCarInfo> tbCarInfoList_add = new ArrayList<TbCarInfo>();
			
			if(null!=tbCarInfoList&&tbCarInfoList.size()>0){
				
				int row = 0 ;
				
				for(TbCarInfo tbCarInfo : tbCarInfoList){
					
					++row;
					
					if(null==tbCarInfo.getCustomerIndex()){
						
						errorMsg += "("+row+")客户代号为空,";
						
					}
					
					if(null==tbCarInfo.getLicenseCode()||"".equals(tbCarInfo.getLicenseCode())){
						
						errorMsg += "("+row+")车牌号为空,";
						
					}
					
					/*else{
						
						TbCarInfo t = tbCarInfoService.findTbCarInfoBylicenseCode(tbCarInfo.getLicenseCode().trim());
						
						if(null!=t){
							
							errorMsg += "("+row+")车牌号 " + tbCarInfo.getLicenseCode() + " 在系统中已经存在,";
							
						}
					}*/
					
					if(null==tbCarInfo.getChassisCode()||"".equals(tbCarInfo.getChassisCode())){
						
						errorMsg += "("+row+")底盘号为空,";
						
					}
					
					if(null==tbCarInfo.getModelCode()||"".equals(tbCarInfo.getModelCode())){
						
						errorMsg += "("+row+")车型代码为空,";
						
					}
					
					else{
						
						TmCarModelType tmCarModelType = tmCarModelTypeService.findByModelCode(tbCarInfo.getModelCode());
					
						if(null==tmCarModelType){
							
							errorMsg += "("+row+")车型代码不存在,";
							
						}
						
						else{
							
							tbCarInfo.setTmCarModelType(tmCarModelType);
							
						}
					}
					
					if(null!=tbCarInfo.getPurchaseDate_s()&&!"".equals(tbCarInfo.getPurchaseDate_s())){
						
						tbCarInfo.setPurchaseDate(CommonMethod.parseStringToDate(tbCarInfo.getPurchaseDate_s(), FORMAT));
						
					}
					
					if(null!=tbCarInfo.getProductDate_s()&&!"".equals(tbCarInfo.getProductDate_s())){
						
						tbCarInfo.setProductDate(CommonMethod.parseStringToDate(tbCarInfo.getProductDate_s(), FORMAT));
						
					}
					
					if(null!=tbCarInfo.getInsureDeadlineDate_s()&&!"".equals(tbCarInfo.getInsureDeadlineDate_s())){
						
						tbCarInfo.setInsureDeadlineDate(CommonMethod.parseStringToDate(tbCarInfo.getInsureDeadlineDate_s(), FORMAT));
						
					}
					
					if(null!=tbCarInfo.getYearCheckDeadline_s()&&!"".equals(tbCarInfo.getYearCheckDeadline_s())){
						
						tbCarInfo.setYearCheckDeadline(CommonMethod.parseStringToDate(tbCarInfo.getYearCheckDeadline_s(), FORMAT));
						
					}
					
					Map<String, Long> isNoValueMap = Constants.getIsNoValueMap();
					
					if(null!=tbCarInfo.getHasInsured_s()&&!"".equals(tbCarInfo.getHasInsured_s())){
						
						Long isNoValue = isNoValueMap.get(tbCarInfo.getHasInsured_s().trim());
						
						if(null!=isNoValue){
							
							tbCarInfo.setHasInsured(isNoValue);
							
						}
						
					}
					
					if(null!=tbCarInfo.getHasCliam_s()&&!"".equals(tbCarInfo.getHasCliam_s())){
						
						Long isNoValue = isNoValueMap.get(tbCarInfo.getHasCliam_s().trim());
						
						if(null!=isNoValue){
							
							tbCarInfo.setHasCliam(isNoValue);
							
						}
						
					}
					
					if(null!=tbCarInfo.getHasFixed_s()&&!"".equals(tbCarInfo.getHasFixed_s())){
						
						Long isNoValue = isNoValueMap.get(tbCarInfo.getHasFixed_s().trim());
						
						if(null!=isNoValue){
							
							tbCarInfo.setHasFixed(isNoValue);
							
						}
						
					}
					
					if(null!=tbCarInfo.getLicenseDate_s()&&!"".equals(tbCarInfo.getLicenseDate_s())){
						
						
						tbCarInfo.setLicenseDate(CommonMethod.parseStringToDate(tbCarInfo.getLicenseDate_s(), FORMAT));
						
					}
					
					if(null!=tbCarInfo.getCarProperty_s()&&!"".equals(tbCarInfo.getCarProperty_s())){
						
						Map<String, Long> carPropertyValueMap = Constants.getCarPropertyValueMap();
						
						Long carProperty = carPropertyValueMap.get(tbCarInfo.getCarProperty_s().trim());
						
						if(null!=carProperty){
							
							tbCarInfo.setCarProperty(carProperty);
							
						}
						
					}
					
					if(null!=tbCarInfo.getCarUsed_s()&&!"".equals(tbCarInfo.getCarUsed_s())){
						
						Map<String, Long> carUsedValueMap = Constants.getCarUsedValueMap();
						
						Long carUsed = carUsedValueMap.get(tbCarInfo.getCarUsed_s().trim());
						
						if(null!=carUsed){
							
							tbCarInfo.setCarUsed(carUsed);
							
						}
						
					}
					
					tbCarInfoList_add.add(tbCarInfo);
					
				}
				
			}
			
			if("".equals(errorMsg)){
				String success_flag = "success,";
				int row = 0;
				
				for(TbCustomer tbCustomer : tbCustomerList_add){
					
					++row;
					
					tbCustomer.setCustomerCode(tmDictionaryService.GenerateCode(StockTypeElements.TBCUSTOMER.getElementString()));
					
					this.insertTbCustomer(tbCustomer);
					
					for(TbCarInfo tbCarInfo : tbCarInfoList_add){
						
						if(row==tbCarInfo.getCustomerIndex()){
							
							tbCarInfo.setTbCustomer(tbCustomer);
							
							tbCarInfoService.insertTbCarInfo(tbCarInfo);
							
						}
						
						
						
					}
					
					success_flag += "成功导入第    " + row + "  位客户       ";
					
				}
				
				
				
				return success_flag;
				
			}
			
		}
		else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
				
				e.printStackTrace();
				
			}
			
			errorMsg = "文件类型或者数据不匹配,请选择正确的文件";
		}
		
		return errorMsg;
	}
	
	public List<TbCustomer> findByTbCustomer(TbCustomer tbCustomer) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbCustomer.class);
		
		if(null!=tbCustomer){
			if(null!=tbCustomer.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbCustomer.getId()));
			}
			if(null!=tbCustomer.getCustomerCode()&&!"".equals(tbCustomer.getCustomerCode())){
				detachedCriteria.add(Restrictions.like("customerCode", "%"+tbCustomer.getCustomerCode()+"%"));
			}
			if(null!=tbCustomer.getCustomerName()&&!"".equals(tbCustomer.getCustomerName())){
				detachedCriteria.add(Restrictions.like("customerName", "%"+tbCustomer.getCustomerName()+"%"));
			}
			if(null!=tbCustomer.getPinyinCode()&&!"".equals(tbCustomer.getPinyinCode())){
				detachedCriteria.add(Restrictions.like("pinyinCode", "%"+tbCustomer.getPinyinCode()+"%"));
			}
			if(null!=tbCustomer.getTelephone()&&!"".equals(tbCustomer.getTelephone())){
				detachedCriteria.add(Restrictions.like("telephone", "%"+tbCustomer.getTelephone()+"%"));
			}
			if(null!=tbCustomer.getCustomerProperty()){
				detachedCriteria.add(Restrictions.eq("customerProperty", tbCustomer.getCustomerProperty()));
			}
			if(null!=tbCustomer.getTmCustomerType()){
				detachedCriteria.setFetchMode("tmCustomerType", FetchMode.JOIN);
				if(null!=tbCustomer.getTmCustomerType().getId()){
					detachedCriteria.add(Restrictions.eq("tmCustomerType.id",tbCustomer.getTmCustomerType().getId()));
				}
			}
		}
		
		return tbCustomerDao.findByCriteria(detachedCriteria, tbCustomer);
	}
	
	
	public TbCustomer findTbCustomerByStockInDetailId(Long stockInDetailId){
		String hql = "FROM TbCustomer c where c.id in ( select si.supplierId from TmStockIn si ,TmStockinDetail sid where si.id = sid.stockId and c.id = si.supplierId and sid.id = ?)";
		List<TbCustomer>tbCustomerList = tbCustomerDao.findBySQL(hql,new Object[]{stockInDetailId} );
		
		if(null!=tbCustomerList&&tbCustomerList.size()>0){
			return tbCustomerList.get(0);
		}
		
		return null;
	}
	
	public TbCustomer findByCode(String customerCode){
		
		List<TbCustomer> tbCustomerList = this.tbCustomerDao.findBySQL("from TbCustomer t where t.customerCode = ?", new String[]{customerCode});
		
		if(null != tbCustomerList && tbCustomerList.size() > 0){
			return tbCustomerList.get(0);
		}
		
		return null;
	}
	
	public void tbCustomerInfoExportXls(OutputStream os,String tpl,List<TbCustomer> tbCustomerList){
		
		WritableWorkbook wwb = null;
		
		List<TbCustomer> tbCustomerListXls = new ArrayList<TbCustomer>();
		
		if(null != tbCustomerList && tbCustomerList.size() > 0){
			
			int i = 1;
			
			for(TbCustomer tbCustomer : tbCustomerList){
				
				TbCarInfo tq = new TbCarInfo();
				
				tq.setTbCustomer(tbCustomer);
				
				List<TbCarInfo> tc = tbCarInfoService.findByTbCarInfo(tq);
				
				String licenseCode = "";
				
				if(null !=tc && tc.size() > 0){
					
					for(TbCarInfo tt : tc)
						licenseCode += tt.getLicenseCode() + " 、";
					
				}
				
				tbCustomer.setLicenseCode(licenseCode);
				
				tbCustomer.setCustomerIndex(i);
				
				tbCustomerListXls.add(tbCustomer);
				
				i++;
				
			}
			
		}
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, tbCustomerListXls);
				
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
}
