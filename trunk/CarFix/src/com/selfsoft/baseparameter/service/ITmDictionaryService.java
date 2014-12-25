package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmDictionary;

public interface ITmDictionaryService {

	/**
	 * 获取税率
	 * @return
	 */
	public List<TmDictionary> getRateTypes();
	/**
	 * 根据类型获取对象
	 * @Date      2010-6-8
	 * @Function  
	 * @param paramType
	 * @return
	 */
	public TmDictionary getTmDictionaryByType(String paramType);
	
	
	/**
	 * 根据各种业务类型生成单号
	 * @Date      2010-6-8
	 * @Function  
	 * @param type
	 * @return
	 */
	public String GenerateCode(String type);
	
	
	/**
	 * 是否允许负出库
	 * @return true 允许负出库  false 不允许负出库
	 */
	public boolean isMinusStockOnt();	
	
	/**
	 *判断本月单号是否清零处理 
	 */
	public void clearZeroStatus();
	
	/**
	 * 得到负出库配置参数常量值
	 * @return
	 */
	public TmDictionary getMinusVal();
	
	public void insertTmDictionary(TmDictionary tmDictionary);
	
	public void updateTmDictionary(TmDictionary tmDictionary);
}
