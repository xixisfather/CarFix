package com.selfsoft.framework.common;

public enum DictionaryTypeElements {

	/** 税率. */
	RATE {
		public String getElementValue() {
			return "RATE";
		}

		public String getElementString() {
			return "税率";
		}
	},
	
	/** 负出库. */
	minusSTOCKOUT {
		public String getElementValue() {
			return "minusSTOCKOUT";
		}

		public String getElementString() {
			return "负出库";
		}
	},
	
	/** 预约单. */
	TBBOOK{
		public String getElementValue() {
			return "tbBook";
		}

		public String getElementString() {
			return "预约单";
		}
	},
	
	/** 委托书. */
	TBENTRUST{
		public String getElementValue() {
			return "tbEntrust";
		}

		public String getElementString() {
			return "委托书";
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
}
