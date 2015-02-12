package com.selfsoft.business.xls.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.baseparameter.service.ITmUnitService;
import com.selfsoft.business.model.StPartReceiver;
import com.selfsoft.business.model.StStockin;
import com.selfsoft.business.model.StStockout;
import com.selfsoft.business.model.StStorehouseSurvey;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.service.IStPartReceiverService;
import com.selfsoft.business.service.IStStockinService;
import com.selfsoft.business.service.IStStockoutService;
import com.selfsoft.business.service.IStStorehouseSurveyService;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TbPartInfoImportVo;
import com.selfsoft.business.vo.TbPartInfoStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TbStoreHouseSurveyVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInImportVo;
import com.selfsoft.business.xls.IStockXLSImportService;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.file.XlsReader;
import com.selfsoft.framework.file.XlsWriter;
import com.selfsoft.framework.file.XlsReader.XlsException;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;
@Service("stockXLSImportService")
public class StockXLSImportServiceImpl implements IStockXLSImportService{

	@Autowired
	private IStStockinService stStockinService;
	@Autowired
	private IStStorehouseSurveyService stStorehouseSurveyService;
	@Autowired
	private IStPartReceiverService stPartReceiverService;
	@Autowired
	private IStStockoutService stStockoutService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmUnitService tmUnitService;
	@Autowired
	private ITmPartTypeService tmPartTypeService;
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmSoleTypeService tmSoleTypeService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	/**
	 * 配件每日出库xls导入
	 */
	public List pjmrckImportXls(InputStream in , String tpl) throws IOException{
		//返回信息
		String flag = "";
		
		List result = new ArrayList();
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		List<DailyStockOutVo> dailyStockOutList = null;
		
		DailyStockOutVo countVo = new DailyStockOutVo();
		
		Set<String> outTypeSet = new HashSet<String>();
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			dailyStockOutList = (List<DailyStockOutVo>) reader.getBeans("DailyStockOutVo");
			
					
			if(null!=dailyStockOutList&&dailyStockOutList.size()>0){
			
				//插入标志
				int j = 0;
				
				Double totalQuantity = 0D;
				Double totalSellPrice = 0D;
				Double totalCostPrice = 0D;

				for(DailyStockOutVo dailyStockOutVo : dailyStockOutList){
					totalQuantity += dailyStockOutVo.getQuantity();
					totalCostPrice += dailyStockOutVo.getCostPrice();
					totalSellPrice += dailyStockOutVo.getSellPrice();
					outTypeSet.add(dailyStockOutVo.getXlsStockOutTypeName());
					
					/**入库**/
					StStockout stStockout = new StStockout();
					stStockout.setHouseName(dailyStockOutVo.getHouseName());
					stStockout.setPartCode(dailyStockOutVo.getPartCode());
					stStockout.setPartName(dailyStockOutVo.getPartName());
					stStockout.setStockoutCode(dailyStockOutVo.getStockOutCode());
					stStockout.setStockOutDate(CommonMethod.parseStringToDate(dailyStockOutVo.getXlsStockOutDate(),"dd/MM/yyyy"));
					stStockout.setQuantity(dailyStockOutVo.getQuantity());
					stStockout.setCostPrice(dailyStockOutVo.getCostPrice());
					stStockout.setSellPrice(dailyStockOutVo.getSellPrice());
					stStockout.setStockoutType(dailyStockOutVo.getXlsStockOutTypeName());
					stStockoutService.insert(stStockout);
					/**入库**/
					dailyStockOutVo.setXlsPK(stStockout.getId()+"");
					j++;
				}
				
				countVo.setTotalCostPrice(totalCostPrice);
				countVo.setTotalQuantity(totalQuantity);
				countVo.setTotalSellPrice(totalSellPrice);
				countVo.setPartCategory(Long.valueOf(outTypeSet.size()));
				flag += "success-成功导入数据"+j+"条";
			}
			//如果文件中无数据
			else{
				flag = "fail-文件中无数据";
			}
		}else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
			
				flag = "fail-文件类型或者数据不匹配,请选择正确的文件";
				
				e.printStackTrace();
				
			}
		}
		
		result.add(dailyStockOutList);
		result.add(countVo);
		result.add(flag);
		return result;
	}
	
	/**
	 * 配件每日出库删除
	 */
	public boolean pjmrckDeleteXls(List<DailyStockOutVo> dailyStockOutList, String xlsPK){
		stStockoutService.deleteById(new Long(xlsPK));
		
		for(DailyStockOutVo dailyStockOutVo : dailyStockOutList){
			
			if(dailyStockOutVo.getXlsPK().equals(xlsPK)){
				dailyStockOutList.remove(dailyStockOutVo);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 配件每日出库结果统计
	 */
	public DailyStockOutVo pjmrckCountObj(List<DailyStockOutVo>  dailyStockOutList){
		DailyStockOutVo countVo = new DailyStockOutVo();
		
		Set<String> outTypeSet = new HashSet<String>();
		
		Double totalQuantity = 0D;
		Double totalSellPrice = 0D;
		Double totalCostPrice = 0D;

		for(DailyStockOutVo dailyStockOutVo : dailyStockOutList){
			totalQuantity += dailyStockOutVo.getQuantity();
			totalCostPrice += dailyStockOutVo.getCostPrice();
			totalSellPrice += dailyStockOutVo.getSellPrice();
			outTypeSet.add(dailyStockOutVo.getXlsStockOutTypeName());
			
		}
		
		countVo.setTotalCostPrice(totalCostPrice);
		countVo.setTotalQuantity(totalQuantity);
		countVo.setTotalSellPrice(totalSellPrice);
		countVo.setPartCategory(Long.valueOf(outTypeSet.size()));
		
		return countVo;
	}
	
	/**
	 * 配件期间收发存xls导入
	 */
	public List pjqjsfcImportXls(InputStream in , String tpl) throws IOException{
		//返回信息
		String flag = "";
		
		List result = new ArrayList();
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		List<TbPartReceiverStatVo> partReceiverStatList = null;
		
		TbPartReceiverStatVo countVo = new TbPartReceiverStatVo();
		
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			partReceiverStatList = (List<TbPartReceiverStatVo>) reader.getBeans("TbPartReceiverStatVo");
			
					
			if(null!=partReceiverStatList&&partReceiverStatList.size()>0){
			
				//插入标志
				int j = 0;
				
				Double totalStockInQuantity = 0D;
				Double totalStockOutQuantity = 0D;
				Double totalStockInPrice = 0D;
				Double totalStockOutPrice = 0D;
				
				
				for(TbPartReceiverStatVo tbPartReceiverStatVo : partReceiverStatList){
					totalStockInQuantity += tbPartReceiverStatVo.getStockInQuantity();
					totalStockOutQuantity += tbPartReceiverStatVo.getStockOutQuantity();
					totalStockInPrice += tbPartReceiverStatVo.getStockInPrice();
					totalStockOutPrice += tbPartReceiverStatVo.getStockOutPrice();
					
					
					/**入库**/
					StPartReceiver stPartReceiver = new StPartReceiver();
					stPartReceiver.setHouseName(tbPartReceiverStatVo.getStoreHouseName());
					stPartReceiver.setPartCode(tbPartReceiverStatVo.getPartCode());
					stPartReceiver.setPartName(tbPartReceiverStatVo.getPartName());
					stPartReceiver.setStockinPrice(tbPartReceiverStatVo.getStockInPrice());
					stPartReceiver.setStockinQuantity(tbPartReceiverStatVo.getStockInQuantity());
					stPartReceiver.setStockoutPrice(tbPartReceiverStatVo.getStockOutPrice());
					stPartReceiver.setStockoutQuantity(tbPartReceiverStatVo.getStockOutQuantity());
					stPartReceiver.setStorePrice(tbPartReceiverStatVo.getStorePrice());
					stPartReceiver.setStoreQuantity(tbPartReceiverStatVo.getStoreQuantity());
					stPartReceiver.setCreateDate(new Date());
					stPartReceiverService.insert(stPartReceiver);
					/**入库**/
					
					tbPartReceiverStatVo.setXlsPK(stPartReceiver.getId()+"");
					j++;
				}
				
				countVo.setTotalStockInQuantity(CommonMethod.convertRadixPoint(totalStockInQuantity, 2));
				countVo.setTotalStockOutQuantity(CommonMethod.convertRadixPoint(totalStockOutQuantity, 2));
				countVo.setTotalStockInPrice(CommonMethod.convertRadixPoint(totalStockInPrice, 2));
				countVo.setTotalStockOutPrice(CommonMethod.convertRadixPoint(totalStockOutPrice, 2));
				
				
				
				flag += "success-成功导入数据"+j+"条";
			}
			//如果文件中无数据
			else{
				flag = "fail-文件中无数据";
			}
		}else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
			
				flag = "fail-文件类型或者数据不匹配,请选择正确的文件";
				
				e.printStackTrace();
				
			}
		}
		
		result.add(partReceiverStatList);
		result.add(countVo);
		result.add(flag);
		return result;
	}
	
	
	/**
	 * 配件期间收发存删除
	 */
	public boolean pjqjsfcDeleteXls(List<TbPartReceiverStatVo> partReceiverStatList, String xlsPK){
		stPartReceiverService.deleteById(new Long(xlsPK));
		
		for(TbPartReceiverStatVo tbPartReceiverStatVo : partReceiverStatList){
			
			if(tbPartReceiverStatVo.getXlsPK().equals(xlsPK)){
				partReceiverStatList.remove(tbPartReceiverStatVo);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 配件期间收发存结果统计
	 */
	public TbPartReceiverStatVo pjqjsfcCountObj(List<TbPartReceiverStatVo>  partReceiverStatList){
		TbPartReceiverStatVo countVo = new TbPartReceiverStatVo();
		
		
		Double totalStockInQuantity = 0D;
		Double totalStockOutQuantity = 0D;
		Double totalStockInPrice = 0D;
		Double totalStockOutPrice = 0D;
		
		
		for(TbPartReceiverStatVo tbPartReceiverStatVo : partReceiverStatList){
			totalStockInQuantity += tbPartReceiverStatVo.getStockInQuantity();
			totalStockOutQuantity += tbPartReceiverStatVo.getStockOutQuantity();
			totalStockInPrice += tbPartReceiverStatVo.getStockInPrice();
			totalStockOutPrice += tbPartReceiverStatVo.getStockOutPrice();
			
		}
		countVo.setTotalStockInQuantity(CommonMethod.convertRadixPoint(totalStockInQuantity, 2));
		countVo.setTotalStockOutQuantity(CommonMethod.convertRadixPoint(totalStockOutQuantity, 2));
		countVo.setTotalStockInPrice(CommonMethod.convertRadixPoint(totalStockInPrice, 2));
		countVo.setTotalStockOutPrice(CommonMethod.convertRadixPoint(totalStockOutPrice, 2));
		
		return countVo;
	}
	

	/**
	 * 仓库概要xls导入
	 */
	public List ckgyImportXls(InputStream in , String tpl) throws IOException{
		//返回信息
		String flag = "";
		
		List result = new ArrayList();
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		List<TbStoreHouseSurveyVo> storeHouseSurveyList = null;
		
		TbStoreHouseSurveyVo countVo = new TbStoreHouseSurveyVo();
		
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			storeHouseSurveyList = (List<TbStoreHouseSurveyVo>) reader.getBeans("TbStoreHouseSurveyVo");
			
					
			if(null!=storeHouseSurveyList&&storeHouseSurveyList.size()>0){
			
				//插入标志
				int j = 0;
				
				for(TbStoreHouseSurveyVo tbPartReceiverStatVo : storeHouseSurveyList){
					
					/**入库**/
					StStorehouseSurvey stStorehouseSurvey = new StStorehouseSurvey();
					stStorehouseSurvey.setHouseName(tbPartReceiverStatVo.getHouseName());
					stStorehouseSurvey.setPartCategory(Long.valueOf(tbPartReceiverStatVo.getXlsPjZl().trim()));
					stStorehouseSurvey.setCostPrice(tbPartReceiverStatVo.getCostPrice());
					stStorehouseSurvey.setSellPrice(tbPartReceiverStatVo.getSellPrice());
					stStorehouseSurvey.setLinacePrice(tbPartReceiverStatVo.getLiancePrice());
					stStorehouseSurvey.setLoanPrice(tbPartReceiverStatVo.getLoanPrice());
					stStorehouseSurvey.setCreateDate(new Date());
					stStorehouseSurveyService.insert(stStorehouseSurvey);
					/**入库**/
					
					tbPartReceiverStatVo.setXlsPK(stStorehouseSurvey.getId()+"");
					j++;
				}
				
				flag += "success-成功导入数据"+j+"条";
			}
			//如果文件中无数据
			else{
				flag = "fail-文件中无数据";
			}
		}else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
			
				flag = "fail-文件类型或者数据不匹配,请选择正确的文件";
				
				e.printStackTrace();
				
			}
		}
		
		result.add(storeHouseSurveyList);
		result.add(countVo);
		result.add(flag);
		return result;
	}
	
	
	

	/**
	 * 仓库概要删除  
	 */
	public boolean ckgyDeleteXls(List<TbStoreHouseSurveyVo> storeHouseSurveyList, String xlsPK){
		stStorehouseSurveyService.deleteById(new Long(xlsPK));
		
		for(TbStoreHouseSurveyVo tbStoreHouseSurveyVo : storeHouseSurveyList){
			
			if(tbStoreHouseSurveyVo.getXlsPK().equals(xlsPK)){
				storeHouseSurveyList.remove(tbStoreHouseSurveyVo);
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * 采购入库xls导入
	 */
	public List cgrkImportXls(InputStream in , String tpl) throws IOException{
		//返回信息
		String flag = "";
		
		List result = new ArrayList();
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		List<TmStockInDetailVo> stockInDetailList = null;
		
		TmStockInDetailVo countVo = new TmStockInDetailVo();
		
		Map<String, Double> customerMap = new LinkedHashMap<String, Double>();
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			stockInDetailList = (List<TmStockInDetailVo>) reader.getBeans("TmStockInDetailVo");
			
			Double stockTotalPrice = 0D;	
			
			
			if(null!=stockInDetailList&&stockInDetailList.size()>0){
			
				//插入标志
				int j = 0;
				
				for(TmStockInDetailVo tmStockInDetailVo : stockInDetailList){
					
					/** 采购内容入库 **/
					StStockin stStockin = new StStockin();
					stStockin.setStockInCode(tmStockInDetailVo.getStockInCode());
					Date stockInDate  = CommonMethod.parseStringToDate(tmStockInDetailVo.getXlsArriveDate(), "yyyy-MM-dd");
					stStockin.setStockInDate(stockInDate);
					stStockin.setHouseName(tmStockInDetailVo.getHouseName());
					stStockin.setPartCode(tmStockInDetailVo.getPartCode());
					stStockin.setPartName(tmStockInDetailVo.getPartName());
					stStockin.setQuantity(tmStockInDetailVo.getQuantity());
					stStockin.setPrice(tmStockInDetailVo.getCostPrice());
					stStockin.setCustomerCode(tmStockInDetailVo.getCustomerCode());
					stStockin.setCustomerName(tmStockInDetailVo.getCustomerName());
					stStockin.setOucherCode(tmStockInDetailVo.getIndent());
					stStockinService.insert(stStockin); 
					/** 采购内容入库 **/
					
					stockTotalPrice += tmStockInDetailVo.getQuantity()* tmStockInDetailVo.getCostPrice();
					String key = tmStockInDetailVo.getCustomerCode() + "," + tmStockInDetailVo.getCustomerName();
					if(customerMap.containsKey(key)){
						Double customerPrice = customerMap.get(key);
						customerMap.put(key, customerPrice+tmStockInDetailVo.getQuantity()* tmStockInDetailVo.getCostPrice());
						
					}else{
						customerMap.put(key, tmStockInDetailVo.getQuantity()* tmStockInDetailVo.getCostPrice());
					}
					
					tmStockInDetailVo.setXlsPK(stStockin.getId()+"");
					j++;
				}
				countVo.setStockTotalPrice(CommonMethod.convertRadixPoint(stockTotalPrice, 2));
				flag += "success-成功导入数据"+j+"条";
			}
			//如果文件中无数据
			else{
				flag = "fail-文件中无数据";
			}
		}else{
			
			List<XlsException> errs = reader.getExceptionStack();
			
			for(XlsException e: errs){
			
				flag = "fail-文件类型或者数据不匹配,请选择正确的文件";
				
				e.printStackTrace();
				
			}
		}
		
		List<TmStockInDetailVo> customerCountList = new ArrayList<TmStockInDetailVo>();
		for(String key : customerMap.keySet()){
			String customerCode = key.split(",")[0];
			String customerName = key.split(",")[1];
			
			TmStockInDetailVo tmStockInDetailVo = new TmStockInDetailVo();
			tmStockInDetailVo.setCustomerCode(customerCode);
			tmStockInDetailVo.setCustomerName(customerName);
			tmStockInDetailVo.setTotal(customerMap.get(key));
			
			customerCountList.add(tmStockInDetailVo);
		}
		
		result.add(stockInDetailList);
		result.add(countVo);
		result.add(flag);
		result.add(customerCountList);
		return result;
	}
	
	
	/**
	 * 采购入库删除  
	 */
	public boolean cgrkDeleteXls(List<TmStockInDetailVo> stockInDetailList, String xlsPK){

		stStockinService.deleteById(new Long(xlsPK));
		
		for(TmStockInDetailVo tmStockInDetailVo : stockInDetailList){
			
			if(tmStockInDetailVo.getXlsPK().equals(xlsPK)){
				stockInDetailList.remove(tmStockInDetailVo);
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * 配件信息xls导入
	 * @param in
	 * @param tpl
	 * @return
	 * @throws IOException
	 */
	public String pjxxImportXls(InputStream in , String tpl) throws IOException{
		//返回信息
		String flag = "";
		
		List result = new ArrayList();
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		List<TbPartInfoImportVo> tbPartInfoImportVoList = null;
		
		List<TbPartInfo> partInfoList = new ArrayList<TbPartInfo>();
		
		Set<String> outTypeSet = new HashSet<String>();
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			tbPartInfoImportVoList = (List<TbPartInfoImportVo>) reader.getBeans("DailyStockOutVo");
			
					
			if(null!=tbPartInfoImportVoList&&tbPartInfoImportVoList.size()>0){
				//插入标志
				int j = 2;
				//拼接错误数据字符串
				String flagError="";
				
				
				for(TbPartInfoImportVo partInfoVo : tbPartInfoImportVoList){
					String errorMessage = "";
					/** 验证 数据是否为空 **/
					boolean blankCodeFlag = false;
					if(StringUtils.isBlank(partInfoVo.getStoreHouseCode())){
						errorMessage += "仓库代码不能为空 \n";
						blankCodeFlag = true;
						
					}
					/*
					if(StringUtils.isBlank(partInfoVo.getCarModelTypeCode())){
						errorMessage += "车辆类型代码不能为空 \n";
						blankCodeFlag = true;
					}
					*/
					if(StringUtils.isBlank(partInfoVo.getPartName())){
						errorMessage += "配件名称不能为空 \n";
						blankCodeFlag = true;
					}
					
					if(StringUtils.isBlank(partInfoVo.getPartCode())){
						errorMessage += "配件代码不能为空 \n";
						blankCodeFlag = true;
					}
					
					if(partInfoVo.getCostPrice()== null){
						errorMessage += "成本价不能为空 \n";
						blankCodeFlag = true;
					}
					/** 验证 数据是否为空 **/
					TmStoreHouse tmStoreHouse = null;
					TmCarModelType tmCarModelType = null;
					TmUnit tmUnit = null;
					TmPartType tmPartType = null;
					
					if(!blankCodeFlag){
						/** 验证 数据是否存在 **/
						String storeHouseCode = partInfoVo.getStoreHouseCode().trim();
						String carModelTypeCode = partInfoVo.getCarModelTypeCode().trim();
						String unitName = partInfoVo.getUnitName().trim();
						String partTypeCode = partInfoVo.getPartTypeCode().trim();
						
						tmStoreHouse =  tmStoreHouseService.findByHouseCode(storeHouseCode);
						tmCarModelType = tmCarModelTypeService.findByModelCode(carModelTypeCode);
						tmUnit = tmUnitService.findByUnitName(unitName);
						tmPartType = tmPartTypeService.findByTypeCode(partTypeCode);
						
						
						if(tmStoreHouse == null)
							errorMessage += "仓库代码在系统中不存在 \n";
						if(tmCarModelType == null)
							errorMessage += "车辆类型代码在系统中不存在 \n";
						if(tmUnit == null)
							errorMessage += "计量单位名称在系统中不存在 \n";
						if(StringUtils.isNotBlank(partTypeCode) && tmPartType == null){
							errorMessage += "配件类型代码在系统中不存在 \n ";
						}
						/** 验证 数据是否存在 **/
					}
					
					
					/** 封装数据 **/
					TbPartInfo tbPartInfo = new TbPartInfo();
					tbPartInfo.setTmStoreHouse(tmStoreHouse);
					tbPartInfo.setPartCode(partInfoVo.getPartCode());
					tbPartInfo.setPartName(partInfoVo.getPartName());
					tbPartInfo.setPinyinCode(CommonMethod.tranferToPinYin(partInfoVo.getPartName()));
					tbPartInfo.setTmUnit(tmUnit);
					tbPartInfo.setTmPartType(tmPartType);
					tbPartInfo.setPartBroadType(partInfoVo.getPartBroadType());
					tbPartInfo.setStoreLocation(partInfoVo.getStoreLocation());
					tbPartInfo.setFactoryCode(partInfoVo.getFactoryCode());
					tbPartInfo.setDangerCode(partInfoVo.getDangerCode());
					tbPartInfo.setMaxStoreQuantity(partInfoVo.getMaxStoreQuantity());
					tbPartInfo.setMinStoreQuantity(partInfoVo.getMinStoreQuantity());
					tbPartInfo.setLianceQuantity(partInfoVo.getLianceQuantity());
					tbPartInfo.setLoanQuantity(partInfoVo.getLoanQuantity());
					tbPartInfo.setCostPrice(partInfoVo.getCostPrice());
					tbPartInfo.setStoreQuantity(0D);
					tbPartInfo.setSellPrice(partInfoVo.getSellPrice());
					tbPartInfo.setStockMoney(0D);
					/**
					 * add by ccr
					 */
					tbPartInfo.setTmCarModelType(tmCarModelType);
					partInfoList.add(tbPartInfo);
					/** 封装数据 **/
					
					if(!"".equals(errorMessage)){
						flagError +="第"+j+"行数据出错:"+errorMessage+",";
					}
					
					j++;
				}
				
				if(StringUtils.isNotBlank(flagError)){
					flag +="failError-"+flagError;
					return flag;
				}
				
				
				List<TmSoleType> soleTypeList = tmSoleTypeService.findAll();
				
				for(TbPartInfo tbPartInfo : partInfoList){
					String soleTypes = "";
					for(TmSoleType solePrice : soleTypeList){
						if(solePrice.getIsDefault().equals(1L))
							soleTypes += solePrice.getId()+":"+tbPartInfo.getSellPrice()+",";
						else 
							soleTypes += solePrice.getId()+":"+0D+",";
					}
					
					tbPartInfoService.insert(soleTypes, tbPartInfo);
				}
				
				flag += "success-成功导入数据"+(j-2)+"条";
				
			}else{
				flag = "fail-文件中无数据";
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
	
	
	/**
	 * 配件信息xls导出
	 * @param os
	 * @param tpl
	 * @param tbPartInfoList
	 */
	public void pjxxExportXls(OutputStream os,String tpl,List<TbPartInfo> tbPartInfoList) {
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, tbPartInfoList);
				
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
	 * 采购入库导入
	 * @param in
	 * @param tpl
	 * @return
	 * @throws IOException
	 * @throws MinusException 
	 */
	public String cgrkV2ImportXls(InputStream in , String tpl) throws IOException, MinusException{
		//返回信息
		String flag = "";
		
		List result = new ArrayList();
		
		XlsReader reader = new XlsReader(in,this.getClass().getResourceAsStream(tpl));
		
		List<TmStockInImportVo> tmStockInImportVoList = null;
		
		Set<String> outTypeSet = new HashSet<String>();
		if(reader.isSuccess()){
			
			Properties props = new Properties();
			
			props.load(this.getClass().getResourceAsStream(tpl));
		
			tmStockInImportVoList = (List<TmStockInImportVo>) reader.getBeans("TmStockInImportVo");
			
			Map<Integer, List<TmStockInImportVo>> stockDetilMap = new LinkedHashMap<Integer,  List<TmStockInImportVo>>();
			
			Map<Integer, TmStockInImportVo> stockMap = new LinkedHashMap<Integer,  TmStockInImportVo>();
			
			if(null!=tmStockInImportVoList&&tmStockInImportVoList.size()>0){
				//插入标志
				int j = 2;
				//拼接错误数据字符串
				String flagError="";
				
				int mapIdx = 0;
				
				for(TmStockInImportVo tmStockInImportVo : tmStockInImportVoList){
					String errorMessage = "";
					/** 验证 数据是否为空 **/
					boolean blankCodeFlag = false;
					
//					if(StringUtils.isBlank(tmStockInImportVo.getSupplierCode())){
//						errorMessage += "供应商代码不能为空 \n";
//						blankCodeFlag = true; 
//					}
					if(StringUtils.isBlank(tmStockInImportVo.getPartCode())){
						errorMessage += "配件代码不能为空 \n";
						blankCodeFlag = true;
					}
					/** 验证 数据是否为空 **/
					
					if(!"".equals(errorMessage)){
						flagError +="第"+j+"行数据出错:"+errorMessage+",";
						flagError +="failError-"+flagError;
						return flagError;
					}else{
						
						/** 封装数据 **/
						
						if(StringUtils.isNotBlank(tmStockInImportVo.getSupplierCode())){
							
							
							
							/** 验证 数据是否存在 **/
							
							TbPartInfo tbpartinfo = tbPartInfoService.findByCode(tmStockInImportVo.getPartCode());
	 						TbCustomer tbCustomer = tbCustomerService.findByCode(tmStockInImportVo.getSupplierCode());
	 						TmUser tmUser = tmUserService.findByUserName(tmStockInImportVo.getUserName());
							if(tbpartinfo == null)
								errorMessage += "配件代码在系统中不存在 \n";
							if(tbCustomer == null)
								errorMessage += "供应商代码在系统中不存在 \n";
							if(tmUser == null)
								errorMessage += "操作员在系统中不存在 \n";
							
							if(tbpartinfo != null)
								tmStockInImportVo.setPartInfoId(tbpartinfo.getId());
							if(tbCustomer != null)
								tmStockInImportVo.setSupplierId(tbCustomer.getId());
							if(tmUser != null)
								tmStockInImportVo.setUserId(tmUser.getId());
							Long payMent = Constants.getPayMentByName(tmStockInImportVo.getPayMentName());
							tmStockInImportVo.setPayMent(payMent);
								
							/** 验证 数据是否存在 **/
							
							
							mapIdx ++ ;
							/** 入库主表  **/
							TmStockInImportVo stcokInImportVo = stockMap.get(mapIdx);
							if(stcokInImportVo == null){
								stockMap.put(mapIdx, tmStockInImportVo);
 							}
							/** 入库主表  **/ 
							
							/** 入库明细表  **/
							List<TmStockInImportVo> importList = stockDetilMap.get(mapIdx);
							if(importList == null){
								importList = new ArrayList<TmStockInImportVo>();
								importList.add(tmStockInImportVo);
								stockDetilMap.put(mapIdx, importList);
							}
							/** 入库明细表  **/
						}else{
							
							TbPartInfo tbpartinfo = tbPartInfoService.findByCode(tmStockInImportVo.getPartCode());
							if(tbpartinfo == null){
								errorMessage += "配件代码在系统中不存在 \n";
								
							}else{
								tmStockInImportVo.setPartInfoId(tbpartinfo.getId());
								stockDetilMap.get(mapIdx).add(tmStockInImportVo);
							}
						}
						
						/** 封装数据 **/
					}
					
					if(!"".equals(errorMessage)){
						flagError +="第"+j+"行数据出错:"+errorMessage+",";
					}
					
					j++;
				}
				
				if(StringUtils.isNotBlank(flagError)){
					flag +="failError-"+flagError;
					return flag;
				}
				
				
				/** 构建数据 **/
				for(int key : stockMap.keySet()){
					TmStockInImportVo mastStockVo = stockMap.get(key);
					List<TmStockInImportVo> detailImportVos = stockDetilMap.get(key);
					System.out.println();
					
					this.insertStockIn(mastStockVo, detailImportVos);
				}
				/** 构建数据 **/
				
				flag += "success-成功导入数据"+(j-2)+"条,其中采购入库主单有:"+stockMap.size()+"条";
				
			}else{
				flag = "fail-文件中无数据";
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
	
	public void insertStockIn(TmStockInImportVo mastStockVo ,List<TmStockInImportVo> detailImportVos ) throws MinusException{
		TmStockIn tmStockIn = new TmStockIn();
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.STOCK.getElementString()));
		tmStockIn.setIndent(mastStockVo.getIndent());
		tmStockIn.setOucherCode(mastStockVo.getOucherCode());
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setArriveDate(new Date());
		tmStockIn.setIsConfirm(Constants.NOT_CONFIRM);
		tmStockIn.setInType(StockTypeElements.STOCK.getElementValue());
		tmStockIn.setUserId(mastStockVo.getUserId());
		tmStockIn.setSupplierId(mastStockVo.getSupplierId());
		tmStockIn.setPayMent(mastStockVo.getPayMent());
		tmStockInService.insert(tmStockIn);
		
		for(TmStockInImportVo detailStockVo : detailImportVos){
			
			TmStockinDetail detail = new TmStockinDetail();
			detail.setPartinfoId(detailStockVo.getPartInfoId());
			detail.setPrice(detailStockVo.getPrice());
			detail.setQuantity(detailStockVo.getQuantity());
			detail.setStockId(tmStockIn.getId());
			detail.setProductArea(detailStockVo.getProductArea());
			
			tmStockinDetailService.insert(detail);
			
			tmStockinDetailService.partInfoStorkIn(detail.getPartinfoId(),detail.getQuantity(),detail.getPrice());
		}
	}
	
	
	/**
	 *  收发单据采购入库导出
	 * @param os
	 * @param tpl
	 * @param stockInDetails
	 */
	public void cgrkStatisticsExportXls(OutputStream os,String tpl,List<TmStockInDetailVo> stockInDetails) {
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, stockInDetails);
				
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
	 *  收发单据维修发料导出
	 * @param os
	 * @param tpl
	 * @param maintainDetails
	 */
	public void wxflStatisticsExportXls(OutputStream os,String tpl,List<TbMaintianVo> maintainDetails) {
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, maintainDetails);
				
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
	 *  采购入库-采购明细导出
	 * @param os
	 * @param tpl
	 * @param maintainDetails
	 */
	public void stockInDetailsExportXls(OutputStream os,String tpl,List<TmStockInDetailVo> stockInDetails) {
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, stockInDetails);
				
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
	 * 配件出库排行榜-采购明细导出
	 * @param os
	 * @param tpl
	 * @param maintainDetails
	 */
	public void topPartStockoutExportXls(OutputStream os,String tpl,List<TbPartInfoStockOutVo> stockOutDetails) {
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, stockOutDetails);
				
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
