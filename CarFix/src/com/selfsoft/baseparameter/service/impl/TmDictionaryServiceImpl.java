package com.selfsoft.baseparameter.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmDictionaryDao;
import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.DictionaryTypeElements;
import com.selfsoft.framework.common.StockTypeElements;
@Service("tmDictionaryService")
public class TmDictionaryServiceImpl implements ITmDictionaryService {

	@Autowired
	private ITmDictionaryDao tmDictionaryDao;
	
	public List<TmDictionary> getRateTypes(){
		List<TmDictionary> result = tmDictionaryDao.findByDictionaryType(DictionaryTypeElements.RATE.getElementValue());
		if(result == null || result.size() == 0){
			List<TmDictionary> rates = initRateType();
			return rates;
		}
		
		return result;
	}
	
	/**
	 * 初始化所有税率
	 * @return
	 */
	public List<TmDictionary> initRateType(){
		TmDictionary td0 = new TmDictionary();
		td0.setParamtype(DictionaryTypeElements.RATE.getElementValue());
		td0.setParamvalue("0.00");
		TmDictionary td3 = new TmDictionary();
		td3.setParamtype(DictionaryTypeElements.RATE.getElementValue());
		td3.setParamvalue("0.03");
		TmDictionary td4 = new TmDictionary();
		td4.setParamtype(DictionaryTypeElements.RATE.getElementValue());
		td4.setParamvalue("0.04");
		TmDictionary td13 = new TmDictionary();
		td13.setParamtype(DictionaryTypeElements.RATE.getElementValue());
		td13.setParamvalue("0.13");
		TmDictionary td17 = new TmDictionary();
		td17.setParamtype(DictionaryTypeElements.RATE.getElementValue());
		td17.setParamvalue("0.17");
		tmDictionaryDao.insert(td0);
		tmDictionaryDao.insert(td3);
		tmDictionaryDao.insert(td4);
		tmDictionaryDao.insert(td13);
		tmDictionaryDao.insert(td17);
		
		return tmDictionaryDao.findByDictionaryType(DictionaryTypeElements.RATE.getElementValue());
		
	}
	
	
	public TmDictionary getTmDictionaryByType(String paramType){
		List<TmDictionary> result = tmDictionaryDao.findByDictionaryType(paramType);
		
		if(result != null && result.size() >0)
			return result.get(0);
		
		return initBill(paramType);
	}
	
	/**
	 * 单号初始化
	 * @Date      2010-6-8
	 * @Function  
	 * @return
	 */
	public TmDictionary initBill(String paramType){
	
		TmDictionary tmDictionary = new TmDictionary();
		tmDictionary.setParamtype(paramType);
		tmDictionary.setParamvalue("0");
		tmDictionaryDao.insert(tmDictionary);
		
		return tmDictionary;
	}
	
	/**
	 * 初始化所有单号
	 * @Date      2010-6-8
	 * @Function
	 */
	public void initAllBills(){
		for(StockTypeElements el : StockTypeElements.values()){
			TmDictionary tmDictionary = new TmDictionary();
			tmDictionary.setParamtype(el.getElementString());
			tmDictionary.setParamvalue("0");
			tmDictionaryDao.insert(tmDictionary);
		}
	}
	
	/**
	 * 生成单号
	 */
	public synchronized String GenerateCode(String type){
		String split = StockTypeElements.getMap().get(type).getElementSplit();
		TmDictionary tmDictionary = this.getTmDictionaryByType(type);
		if(tmDictionary != null){
			StringBuilder result  = new StringBuilder(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String pval = new Long(tmDictionary.getParamvalue()) +1 +"";
			if (pval.length() == 1) {
				pval = "000"+pval;
			}else if(pval.length() == 2) {
				pval = "00"+pval;
			}else if(pval.length() == 3) {
				pval = "0"+pval;
			}
				
			
			//单号格式 日期+单号字母+当天序列号
			result.append(sdf.format(new Date())).append(split).append(pval);
			
			//序列号累加
			int index = Integer.valueOf(tmDictionary.getParamvalue());
			index++;
			tmDictionary.setParamvalue(index+"");
			tmDictionaryDao.update(tmDictionary);
			return result.toString();
		}
		
		return null;
	}
	
	/**
	 * 是否允许负出库
	 * @return true 允许负出库  false 不允许负出库
	 */
	public boolean isMinusStockOnt(){
		List<TmDictionary> dictionarys = tmDictionaryDao.findByDictionaryType(Constants.MINUSSTOCKOUT_KEY);
		if(dictionarys != null && dictionarys.size() >0){
			Long pValue = new Long(dictionarys.get(0).getParamvalue());
			if(pValue.equals(Constants.ISMINUS))
				return true;
			
		}
		return false;
	}
	
	/**
	 * 得到负出库配置参数常量值
	 * @return
	 */
	public TmDictionary getMinusVal(){
		TmDictionary tmDictionary = null;
		List<TmDictionary> dictionarys = tmDictionaryDao.findByDictionaryType(Constants.MINUSSTOCKOUT_KEY);
		if(dictionarys != null && dictionarys.size() >0){
			tmDictionary = dictionarys.get(0);
		}else{
			tmDictionary = new TmDictionary();
			tmDictionary.setParamtype(Constants.MINUSSTOCKOUT_KEY+"");
			tmDictionary.setParamvalue(Constants.ISMINUS+"");
			tmDictionaryDao.insert(tmDictionary);
		}
		return tmDictionary;
	}
	
	/**
	 *判断本月单号是否清零处理 
	 */
	public void clearZeroStatus(){
		
		List<TmDictionary> dicList =  tmDictionaryDao.findNullStatus();
		/**如果2个字段都是为null  ==》更新为当前月，已清零状态（针对已经安装系统的用户）**/
		if(dicList != null  && dicList.size() > 0){
			//更新状态
			tmDictionaryDao.updateCurrMonthStatus();
		}else{
			
			List<TmDictionary> currMonthDicList = tmDictionaryDao.findByZeroMounth();
			/** 判断不是当前月份 **/
			if(currMonthDicList == null || currMonthDicList.size() == 0){
				//清零处理，更新状态
				tmDictionaryDao.updateCurrMonthStatus();
				tmDictionaryDao.resetParamValue();
			}else{
				
				String currDateString = CommonMethod.parseDateToString(new Date(), "yyyy-MM");
				String sysDateString = CommonMethod.parseDateToString(currMonthDicList.get(0).getZeroMonth(), "yyyy-MM");
				
				/** 判断是当前月份 && 未清零 ===》 清零处理 **/
				if(currDateString.equals(sysDateString) && currMonthDicList.get(0).getZeroStatus().equals(0L)){
					//清零处理，更新状态
					tmDictionaryDao.updateCurrMonthStatus();
					tmDictionaryDao.resetParamValue();
				}
			}
			
		}
		
	}

	@Override
	public void insertTmDictionary(TmDictionary tmDictionary) {
		tmDictionaryDao.insert(tmDictionary);
	}
	
	@Override
	public void updateTmDictionary(TmDictionary tmDictionary) {
		tmDictionaryDao.update(tmDictionary);
	}
	
}
