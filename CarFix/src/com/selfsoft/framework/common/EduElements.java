package com.selfsoft.framework.common;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EduElements {
	/**博士**/
	DOCTOR {
		public Long getElementValue() {
			return 1L;
		}

		public String getElementString() {
			return "博士";
		}
		
	},
	/**硕士**/
	MASTER {
		public Long getElementValue() {
			return 2L;
		}

		public String getElementString() {
			return "硕士";
		}
		
	},
	/**本科**/
	UNDERGRADUATE {
		public Long getElementValue() {
			return 3L;
		}

		public String getElementString() {
			return "本科";
		}
		
	},
	/**大专**/
	JUNIOR {
		public Long getElementValue() {
			return 4L;
		}

		public String getElementString() {
			return "大专";
		}
		
	},
	/**中专**/
	TECHNICAL {
		public Long getElementValue() {
			return 5L;
		}

		public String getElementString() {
			return "中专";
		}
		
	},;
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
		for(EduElements el:EduElements.values()){
			map.put(el.getElementValue(), el.getElementString());
		}
		return map;
	}
}
