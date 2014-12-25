/**
 * 
 */
package com.selfsoft.framework.common;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * 权限枚举
 */
public enum ImpermissionElements {
	
	/** 采购入库. */
	ADD {
		public String getElementValue() {
			return "ADD";
		}

		public String getElementString() {
			return "新增";
		}
		
	},
	UPDATE {
		public String getElementValue() {
			return "UPDATE";
		}

		public String getElementString() {
			return "修改";
		}
		
	},
	DELETE {
		public String getElementValue() {
			return "DELETE";
		}

		public String getElementString() {
			return "删除";
		}
		
	},
	VIEW {
		public String getElementValue() {
			return "VIEW";
		}

		public String getElementString() {
			return "查看";
		}
		
	},
	EXPORT {
		public String getElementValue() {
			return "EXPORT";
		}

		public String getElementString() {
			return "导出";
		}
		
	},
	IMPORT {
		public String getElementValue() {
			return "IMPORT";
		}

		public String getElementString() {
			return "导入";
		}
		
	};
	
	
	
	
	/**
	 * Gets the element value.
	 * 
	 * @return the element value
	 */
	public abstract String getElementValue();

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
	public static Map<String, String> getMap(){
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(ImpermissionElements el:ImpermissionElements.values()){
			map.put(el.getElementValue(), el.getElementString());
			
		}
		return map;
	}
	

	
}
