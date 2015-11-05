/**
 * 
 */
package com.selfsoft.framework.common;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Enum ObjectElements.
 * 
 * @author Administrator
 */
public enum StockTypeElements {
	
	/** 采购入库. */
	STOCK {
		public Long getElementValue() {
			return 1L;
		}

		public String getElementString() {
			return "STOCK";
		}
		public String getElementSplit(){
			return "A";
		}
		public String getElementName(){
			return "采购入库";
		}
	},
	
	/** 调拨入库. */
	ALLOT {
		public Long getElementValue() {
			return 2L;
		}

		public String getElementString() {
			return "ALLOT";
		}
		public String getElementSplit(){
			return "B";
		}
		public String getElementName(){
			return "调拨入库";
		}
		
	},
	
	/** 配件报溢. */
	OVERFLOW {
		public Long getElementValue() {
			return 3L;
		}
		
		public String getElementString() {
			return "OVERFLOW";
		}
		
		public String getElementSplit(){
			return "C";
		}
		public String getElementName(){
			return "配件报溢";
		}
		
	},
	
	/** 其它入库. */
	OTHERSTOCKIN {
		public Long getElementValue() {
			return 4L;
		}

		public String getElementString() {
			return "OTHERSTOCKIN";
		}
		
		public String getElementSplit(){
			return "D";
		}
		public String getElementName(){
			return "其它入库";
		}
	},
	
	/** 销售出库. */
	SELLSTOCKOUT {
		public Long getElementValue() {
			return 11L;
		}

		public String getElementString() {
			return "SELLSTOCKOUT";
		}
		
		public String getElementSplit(){
			return "E";
		}
		public String getElementName(){
			return "销售出库";
		}
	},
	
	/** 领用出库. */
	DRAWSTOCKOUT {
		public Long getElementValue() {
			return 12L;
		}

		public String getElementString() {
			return "DRAWSTOCKOUT";
		}
		
		public String getElementSplit(){
			return "F";
		}
		public String getElementName(){
			return "领用出库";
		}
	},
	/**报损出库. */
	SHATTERSTOCKOUT {
		public Long getElementValue() {
			return 13L;
		}

		public String getElementString() {
			return "SHATTERSTOCKOUT";
		}
		
		public String getElementSplit(){
			return "G";
		}
		public String getElementName(){
			return "报损出库";
		}
	},
	/** 调拨出库. */
	ALLOTSTOCKOUT {
		public Long getElementValue() {
			return 14L;
		}

		public String getElementString() {
			return "ALLOTSTOCKOUT";
		}
		
		public String getElementSplit(){
			return "H";
		}
		public String getElementName(){
			return "调拨出库";
		}
	},
	/** 采购退货. */
	STOCKRETURN {
		public Long getElementValue() {
			return 15L;
		}

		public String getElementString() {
			return "STOCKRETURN";
		}
		
		public String getElementSplit(){
			return "I";
		}
		public String getElementName(){
			return "采购退货";
		}
		
	},
	/**销售退货**/
	SELLRETURN{
		public Long getElementValue() {
			return 5L;
		}

		public String getElementString() {
			return "SELLRETURN";
		}
		
		public String getElementSplit(){
			return "J";
		}
		public String getElementName(){
			return "销售退货";
		}
		
	},
	/**借进登记**/
	LIANCEREGISTER{
		public Long getElementValue() {
			return 6L;
		}

		public String getElementString() {
			return "LIANCEREGISTER";
		}
		
		public String getElementSplit(){
			return "K";
		}
		public String getElementName(){
			return "借进登记";
		}
	},
	/**借进归还**/
	LIANCERETURN{
		public Long getElementValue() {
			return 7L;
		}

		public String getElementString() {
			return "LIANCERETURN";
		}
		
		public String getElementSplit(){
			return "L";
		}
		public String getElementName(){
			return "借进归还";
		}
	},
	/**借出登记**/
	LOANREGISTER{
		public Long getElementValue() {
			return 16L;
		}

		public String getElementString() {
			return "LOANREGISTER";
		}
		
		public String getElementSplit(){
			return "M";
		}
		public String getElementName(){
			return "借出登记";
		}
	},
	/**借出归还**/
	LOANRETURN{
		public Long getElementValue() {
			return 17L;
		}

		public String getElementString() {
			return "LOANRETURN";
		}
		
		public String getElementSplit(){
			return "N";
		}
		public String getElementName(){
			return "借出归还";
		}
	},
	/**移库出仓**/
	REMOVESTOCK{
		public Long getElementValue() {
			return 18L;
		}

		public String getElementString() {
			return "REMOVESTOCK";
		}
		
		public String getElementSplit(){
			return "O";
		}
		public String getElementName(){
			return "移库出仓";
		}
	},
	/**移库进仓**/
	SHIFTSTOCK{
		public Long getElementValue() {
			return 19L;
		}

		public String getElementString() {
			return "SHIFTSTOCK";
		}
		
		public String getElementSplit(){
			return "P";
		}
		public String getElementName(){
			return "移库进仓";
		}
	},
	/**维修发料**/
	MAINTAIN{
		public Long getElementValue() {
			return 20L;
		}

		public String getElementString() {
			return "MAINTAIN";
		}
		
		public String getElementSplit(){
			return "R";
		}
		public String getElementName(){
			return "维修发料";
		}
	},
	/** 预约单. */
	TBBOOK{
		public Long getElementValue() {
			return 30L;
		}

		public String getElementString() {
			return "TBBOOK";
		}
		public String getElementSplit(){
			return "-";
		}
		public String getElementName(){
			return "预约单";
		}
	},
	
	/** 委托书. */
	TBENTRUST{
		public Long getElementValue() {
			return 31L;
		}

		public String getElementString() {
			return "TBENTRUST";
		}
		public String getElementSplit(){
			return "-";
		}
		public String getElementName(){
			return "委托书";
		}
		
	},
	/** 预付款. */
	TBANVANCEPAY{
		public Long getElementValue() {
			return 32L;
		}

		public String getElementString() {
			return "TBANVANCEPAY";
		}
		public String getElementSplit(){
			return "-";
		}
		public String getElementName(){
			return "预付款";
		}

	},
	/**结算单. */
	TBBUSINESSBALANCE{
		public Long getElementValue() {
			return 33L;
		}

		public String getElementString() {
			return "TBBUSINESSBALANCE";
		}
		public String getElementSplit(){
			return "-";
		}
		public String getElementName(){
			return "结算单";
		}

	},
	/**客户号*/
	TBCUSTOMER{
		public Long getElementValue() {
			return 34L;
		}

		public String getElementString() {
			return "TBCUSTOMER";
		}
		public String getElementSplit(){
			return "";
		}
		public String getElementName(){
			return "客户号";
		}

	},
	
	/**特殊结算单. */
	TBBUSINESSSPECIALBALANCE{
		public Long getElementValue() {
			return 35L;
		}

		public String getElementString() {
			return "TBBUSINESSSPECIALBALANCE";
		}
		public String getElementSplit(){
			return "-";
		}
		public String getElementName(){
			return "特殊结算单";
		}

	},
	/**财务录入. */
	TBBUSINESSSPECIALBALANCEFINANCE{
		public Long getElementValue() {
			return 35L;
		}

		public String getElementString() {
			return "TBBUSINESSSPECIALBALANCEFINANCE";
		}
		public String getElementSplit(){
			return "-";
		}
		public String getElementName(){
			return "财务录入结算单";
		}

	},
	/**内部领用出库**/
	INNERSTOCKOUT{
		public Long getElementValue() {
			return 21L;
		}

		public String getElementString() {
			return "INNERSTOCKOUT";
		}
		
		public String getElementSplit(){
			return "S";
		}
		public String getElementName(){
			return "内部领用";
		}
	},
	/** 盘点. */
	CHECK {
		public Long getElementValue() {
			return 51L;
		}

		public String getElementString() {
			return "CHECK";
		}
		public String getElementSplit(){
			return "T";
		}
		public String getElementName(){
			return "盘点";
		}
	},
	;
	
	
	
	
	/**
	 * Gets the element value.
	 * 
	 * @return the element value
	 */
	public abstract Long getElementValue();

	/**
	 * Gets the element string.
	 * 
	 * @return the element string
	 */
	public abstract String getElementString();
	
	public abstract String getElementSplit();
	
	public abstract String getElementName();

	/**
	 * Gets the map.
	 * 
	 * @return the map
	 */
	public static Map<String, StockTypeElements> getMap(){
		Map<String, StockTypeElements> map=new LinkedHashMap<String, StockTypeElements>();
		for(StockTypeElements el:StockTypeElements.values()){
			map.put(el.getElementString(), el);
		}
		return map;
	}
	
	public static String[] getQueryString(){
		StockTypeElements[] typeElements = StockTypeElements.values();
		String[] result = new String[StockTypeElements.values().length];
		int i=0;
		for(StockTypeElements el: typeElements){
			String queryString = "'"+el.getElementString()+"'";
			result[i] = queryString;
			i++;
		}
		
		return result;
	}
	
	public static Map<Long, String> getElementMap(){
		Map<Long, String> map=new LinkedHashMap<Long, String>();
		for(StockTypeElements el:StockTypeElements.values()){
			map.put(el.getElementValue(),el.getElementName());
		}
		
		return map;
	}
	
	
	public static Long getElementTypeVal(Long val){
		if(val.equals(StockTypeElements.STOCK.getElementValue()) ||
				val.equals(StockTypeElements.ALLOT.getElementValue()) ||
				val.equals(StockTypeElements.OTHERSTOCKIN.getElementValue()) ||
				val.equals(StockTypeElements.OVERFLOW.getElementValue()) 
				//||val.equals(StockTypeElements.STOCKRETURN.getElementValue()) 
		
		){
			return 1L;
			
		}else if(val.equals(StockTypeElements.SELLSTOCKOUT.getElementValue()) ||
				val.equals(StockTypeElements.ALLOTSTOCKOUT.getElementValue()) ||
				val.equals(StockTypeElements.MAINTAIN.getElementValue()) ||
				val.equals(StockTypeElements.DRAWSTOCKOUT.getElementValue()) ||
				val.equals(StockTypeElements.SHIFTSTOCK.getElementValue()) 
				||val.equals(StockTypeElements.STOCKRETURN.getElementValue()) 
		){
			return 2L;
			
		}else{
			
			return 3L;
		}
	}
}
