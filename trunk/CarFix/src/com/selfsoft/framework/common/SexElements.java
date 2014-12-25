package com.selfsoft.framework.common;

import java.util.LinkedHashMap;
import java.util.Map;

public enum SexElements {
	/**男**/
	MALE {
		public Long getElementValue() {
			return 1L;
		}

		public String getElementString() {
			return "男";
		}
		
	},
	/**女**/
	FEMALE {
		public Long getElementValue() {
			return 0L;
		}

		public String getElementString() {
			return "女";
		}
		
	};
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
	

	/**
	 * Gets the map.
	 * 
	 * @return the map
	 */
	public static Map<Long, String> getMap(){
		Map<Long, String> map=new LinkedHashMap<Long, String>();
		for(SexElements el:SexElements.values()){
			map.put(el.getElementValue(), el.getElementString());
		}
		return map;
	}
}
