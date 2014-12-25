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
public enum StoreHouseReceiverElements {
	
	/** 采购入库. */
	CG {
		public Long getElementValue() {
			return 1L;
		}

		public String getElementString() {
			return "CG";
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
	
	/** 配件销售. */
	SELLSTOCKOUT {
		public Long getElementValue() {
			return 11L;
		}

		public String getElementString() {
			return "STOCKOUT";
		}
		
		public String getElementSplit(){
			return "E";
		}
		public String getElementName(){
			return "配件销售";
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
	/** 油漆辅料. */
	UQFL {
		public Long getElementValue() {
			return 4L;
		}

		public String getElementString() {
			return "UQFL";
		}
		
		public String getElementSplit(){
			return "S";
		}
		public String getElementName(){
			return "油漆辅料";
		}
	},
	/** 其它出库. */
	OTHERSTOCKOUT {
		public Long getElementValue() {
			return 4L;
		}

		public String getElementString() {
			return "OTHERSTOCKOUT";
		}
		
		public String getElementSplit(){
			return "T";
		}
		public String getElementName(){
			return "其它出库";
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
		
	public static Map<String, String> getElementMap(){
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(StoreHouseReceiverElements el:StoreHouseReceiverElements.values()){
			map.put(el.getElementString(),el.getElementName());
		}
		
		return map;
	}
	
	
}
