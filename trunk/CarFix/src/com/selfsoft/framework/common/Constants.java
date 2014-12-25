package com.selfsoft.framework.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 定义全局性常量
 * 
 *
 */
public class Constants {
	/**
	 * 表主键
	 */
	public static final String PRIMARY_KEY="ID";
	/**
	 * 表数据有效标识字段
	 */
	public static final String ISVALID = "ISVALID";
	/**
	 * 有效
	 */
	public static final Long ISVALIDVALUE = 1L;
	/**
	 * 无效
	 */
	public static final Long NOTVALIDVALUE = 0L;
	/**
	 * 是否类型 是
	 */
	public static final Long ISTRUE=1L;
	
	public static final String ISTRUESHOW="是";
	/**
	 * 是否类型 否
	 */
	public static final Long NOTTRUE=0L;
	
	public static final String NOTTRUESHOW="否";
	
	/**
	 * 未定义
	 */
	public static final String NOTDIFINE="未定义";
	/**
	 * 操作成功
	 */
	public static final String SUCCESS="success";
	/**
	 * 操作失败
	 */
	public static final String FAILURE="failure";
	/**
	 * 操作异常
	 */
	public static final String EXCEPTION="exception";
	/**
	 * 数据异常
	 */
	public static final String DATAEXCEPTION="dataException";
	
	/**
	 * 增加页面
	 */
	public static final String ADDPAGE="addPage";
	/**
	 * 修改页面
	 */
	public static final String EDITPAGE="editPage";
	/**
	 * 其他页面
	 */
	public static final String OTHERPAGE="otherPage";
	/**
	 * 查看页面
	 */
	public static final String VIEWPAGE = "viewPage";
	/**
	 * 文件导入页面
	 */
	public static final String IMPORTXLS = "importXlsPage";
	/**
	 * 文件导出页面
	 */
	public static final String EXPORTXLS = "exportXlsPage";
	
	public static final String TIMEFORMATOFMINUTE = "yyyy-MM-dd HH:mm";
	
	/**
	 * 是否类型MAP
	 */
	public static final Map<Long, String> getIsNoMap(){
		
		Map<Long, String> isNoMap=new LinkedHashMap<Long, String>();
		
		isNoMap.put(ISTRUE,ISTRUESHOW);
		
		isNoMap.put(NOTTRUE,NOTTRUESHOW);
		
		return isNoMap;
	}
	/**
	 * 是否类型MAP
	 */
	public static final Map<String, Long> getIsNoValueMap(){
		
		Map<String, Long> isNoMap=new LinkedHashMap<String, Long>();
		
		isNoMap.put(ISTRUESHOW,ISTRUE);
		
		isNoMap.put(NOTTRUESHOW,NOTTRUE);
		
		return isNoMap;
	}
	/**
	 * 客户类别-客户
	 */
	public static final Long CUSTOMERVAL = 1L;
	
	public static final String CUSTOMERSHOW = "客户";
	/**
	 * 客户类别-供应商
	 */
	public static final Long SUPPLIERVAL = 2L;
	
	public static final String SUPPLIERSHOW = "供应商";
	/**
	 * 客户类别-客户且供应商
	 */
	public static final Long CUSTOMERANDSUPPLIERVAL = 3L;
	
	public static final String CUSTOMERANDSUPPLIERSHOW = "客户且供应商";
	/**
	 * 客户类别MAP
	 */
	public static final Map<Long,String> getCustomerPropertyMap(){
		
		Map<Long,String>customerPropertyMap = new LinkedHashMap<Long, String>();
		
		customerPropertyMap.put(CUSTOMERVAL,CUSTOMERSHOW);
		
		customerPropertyMap.put(SUPPLIERVAL,SUPPLIERSHOW);
		
		customerPropertyMap.put(CUSTOMERANDSUPPLIERVAL,CUSTOMERANDSUPPLIERSHOW);
		
		return customerPropertyMap;
	}
	
	/**
	 * 客户类别MAP
	 */
	public static final Map<String,Long> getCustomerPropertyValueMap(){
		
		Map<String,Long>customerPropertyMap = new LinkedHashMap<String,Long>();
		
		customerPropertyMap.put(CUSTOMERSHOW,CUSTOMERVAL);
		
		customerPropertyMap.put(SUPPLIERSHOW,SUPPLIERVAL);
		
		customerPropertyMap.put(CUSTOMERANDSUPPLIERSHOW,CUSTOMERANDSUPPLIERVAL);
		
		return customerPropertyMap;
	}
	
	/**
	 * 车辆性质 私车
	 */
	public static final Long PERSONALCAR = 1L;
	
	public static final String PERSONALCARSHOW = "私车";
	
	/**
	 * 车辆性质 公车
	 */
	public static final Long PUBLICCAR = 2L;
	
	public static final String PUBLICCARSHOW = "公车";
	
	/**
	 * 车辆性质 MAP
	 */
	public  static final Map<Long,String>getCarPropertyMap(){
		
		Map<Long,String> carPropertyMap = new LinkedHashMap<Long,String>();
		
		carPropertyMap.put(PERSONALCAR, PERSONALCARSHOW);
		
		carPropertyMap.put(PUBLICCAR, PUBLICCARSHOW);
		
		return carPropertyMap;
		
	}
	
	/**
	 * 车辆性质 MAP
	 */
	public  static final Map<String,Long>getCarPropertyValueMap(){
		
		Map<String,Long> carPropertyMap = new LinkedHashMap<String,Long>();
		
		carPropertyMap.put(PERSONALCARSHOW,PERSONALCAR);
		
		carPropertyMap.put(PUBLICCARSHOW,PUBLICCAR);
		
		return carPropertyMap;
		
	}
	/**
	 * 车辆用途 公务用车
	 */
	public static final Long PUBLICUSE = 1L;
	
	public static final String PUBLICUSESHOW = "公务用车";
	
	/**
	 * 车辆用途 家庭用车
	 */
	public static final Long FAMILYUSE = 2L;
	
	public static final String FAMILYUSESHOW = "家庭用车";
	
	/**
	 * 车辆用途 商务用车
	 */
	public static final Long BUSINESSUSE = 3L;
	
	public static final String BUSINESSUSESHOW = "商务用车";
	
	/**
	 * 车辆用途 经营用车
	 */
	public static final Long TRADEUSE= 4L;
	
	public static final String TRADEUSESHOW = "经营用车";
	
	/**
	 * 车辆用途 MAP
	 */
	public static final Map<Long, String> getCarUsedMap(){
		
		Map<Long, String> carUsedMap = new LinkedHashMap<Long, String>();
		
		carUsedMap.put(PUBLICUSE, PUBLICUSESHOW);
		
		carUsedMap.put(FAMILYUSE, FAMILYUSESHOW);
		
		carUsedMap.put(BUSINESSUSE, BUSINESSUSESHOW);
		
		carUsedMap.put(TRADEUSE, TRADEUSESHOW);
		
		return carUsedMap;
	
	}
	
	/**
	 * 车辆用途 MAP
	 */
	public static final Map<String,Long> getCarUsedValueMap(){
		
		Map<String,Long> carUsedMap = new LinkedHashMap<String,Long>();
		
		carUsedMap.put(PUBLICUSESHOW,PUBLICUSE);
		
		carUsedMap.put(FAMILYUSESHOW,FAMILYUSE);
		
		carUsedMap.put(BUSINESSUSESHOW,BUSINESSUSE);
		
		carUsedMap.put(TRADEUSESHOW,TRADEUSE);
		
		return carUsedMap;
	
	}
	//修理销售结算
	public static final Long BALANCEFIX = 1L;
	
	public  static final String BALANCEFIXSHOW = "修理销售结算";
	
	//单独销售结算
	public static final Long BALANCEALONE = 2L;
	
	public  static final String BALANCEALONESHOW = "单独销售结算";
	
	//项目结算类型MAP
	public static final Map<Long, String> getBalanceTypeMap(){
		
		Map<Long, String> balanceTypeMap = new LinkedHashMap<Long, String>();
		
		balanceTypeMap.put(BALANCEFIX, BALANCEFIXSHOW);
		
		balanceTypeMap.put(BALANCEALONE, BALANCEALONESHOW);
		
		return balanceTypeMap;
	}
	
	public static String costTypeBXLP = "保险理赔";
	
	public static final Map<String, String> getCostTypeMap() {
		
		Map<String, String> costTypeMap = new LinkedHashMap<String, String>();
		
		costTypeMap.put(costTypeBXLP, costTypeBXLP);
		
		return costTypeMap;
	}
	
    public static final Long FREESYMBOL_NO = 1L;
    
    public static final Long FREESYMBOL_SB = 2L;
    
    public static final Long FREESYMBOL_SP = 3L;
    
    public static final Long FREESYMBOL_FG = 4L;
    
    public static final Long FREESYMBOL_FW = 5L;
    
    public static final Long FREESYMBOL_BX = 6L;
    
    public static final Long FREESYMBOL_NF = 7L;
    
    public static final String FREESYMBOL_NO_SHOW = "无";
    
    public static final String FREESYMBOL_SB_SHOW = "首保";
    
    public static final String FREESYMBOL_SP_SHOW = "索赔";
    
    public static final String FREESYMBOL_FG_SHOW = "返工";
    
    public static final String FREESYMBOL_FW_SHOW = "服务活动";
    
    public static final String FREESYMBOL_BX_SHOW = "保险理赔";
    
    public static final String FREESYMBOL_NF_SHOW = "暂不维修";
    
    public static final Map<Long,String>getFreeSymbolMap(){
    	
    	Map<Long,String> symbolMap = new LinkedHashMap<Long, String>();
    	
    	symbolMap.put(FREESYMBOL_NO, FREESYMBOL_NO_SHOW);
    	
    	symbolMap.put(FREESYMBOL_SB, FREESYMBOL_SB_SHOW);
    	
    	symbolMap.put(FREESYMBOL_SP, FREESYMBOL_SP_SHOW);
    	
    	symbolMap.put(FREESYMBOL_FG, FREESYMBOL_FG_SHOW);
    	
    	symbolMap.put(FREESYMBOL_FW, FREESYMBOL_FW_SHOW);
    	
    	symbolMap.put(FREESYMBOL_BX, FREESYMBOL_BX_SHOW);
    	
    	symbolMap.put(FREESYMBOL_NF, FREESYMBOL_NF_SHOW);
    	
    	return symbolMap;
    }
    
    public static final Long DEALTYPECHANGE = 1L;
    
    public static final Long DEALTYPEFIX = 2L;
    
    public static final String DEALTYPECHANGESHOW = "更换";
    
    public static final String DEALTYPEFIXSHOW = "维修";
    
    public static final Map<Long,String> getDealTypeMap(){
    	Map dealTypeMap = new LinkedHashMap<Long,String>();
    	
    	dealTypeMap.put(DEALTYPECHANGE, DEALTYPECHANGESHOW);
    	
    	dealTypeMap.put(DEALTYPEFIX, DEALTYPEFIXSHOW);
    	
    	return dealTypeMap;
    }
 /*********************************委托书状态*************************************************************/   
    /**
     * 未竣工
     */
    public static final Long NOFINISH = 0L;
    
    public static final String NOFINISHSHOW = "未竣工";
    
    /**
     * 已发料
     */
    public static final Long SENDPART = 1L;
    
    public static final String SENDPARTSHOW = "已发料";
    /**
     * 已竣工
     */
    public static final Long ISFINISH = 2L;
    
    public static final String ISFINISHSHOW = "已竣工";
    
    /**
     * 已结算
     */
    public static final Long ISBALANCE = 3L;
    
    public static final String ISBALANCESHOW = "已结算";
    /**
     * 再结算
     */
    public static final Long REBALANCE = 4L;
    
    public static final String REBALANCESHOW = "再结算";
    
    /**
     * 未发料再结算
     */
    public static final Long NOTMAINTAINREBALANCE = 5L;
    
    public static final String NOTMAINTAINREBALANCESHOW = "未发料再结算";
 

/*********************************委托书状态*************************************************************/    
    
    public static final Map<Long,String> getFinishMap(){
    	
    	Map<Long,String> finishMap = new LinkedHashMap<Long,String>();
    	
    	finishMap.put(NOFINISH, NOFINISHSHOW);
    	
    	finishMap.put(ISFINISH, ISFINISHSHOW);
    	
    	return finishMap;
    }
    /**
     * 允许负出库值
     */
    public static final Long ISMINUS= -1L;
    /**
     * 不允许负出库值
     */
    public static final Long NOMINUS = 1L;
    /**
     * 负出库类型
     */
    public static final String MINUSSTOCKOUT_KEY = "MINUSSTOCKOUT";
    
    /**
     * 支付方式 现金
     */
    public static final Long PAYCASH = 1L;
    
    public static final String PAYCASHSHOW = "现金";
    /**
     * 支付方式 支票
     */
    public static final Long PAYCHEQUE = 2L;
    
    public static final String PAYCHEQUESHOW = "支票";
    
    /**
     * 支付方式 挂账
     */
    public static final Long PAYACCOUNT = 3L;
    
    public static final String PAYACCOUNTSHOW = "挂账";
    
    /**
     * 支付方式 刷卡
     */
    public static final Long PAYCARD = 4L;
    
    public static final String PAYCARDSHOW = "刷卡";
    
    public static final Map<Long,String> getPayMap(){
    	Map<Long,String> map = new LinkedHashMap<Long, String>();
    	
    	map.put(PAYCASH, PAYCASHSHOW);
    	
    	map.put(PAYCHEQUE, PAYCHEQUESHOW);
    	
    	map.put(PAYACCOUNT, PAYACCOUNTSHOW);
    	
    	map.put(PAYCARD, PAYCARDSHOW);
    	
    	return map;
    }

    /**
     * 无效权限
     */
    public static final String PERMISSIONFAILED = "permissionFailed"; 
    /**
     * 确认状态
     */
    public static final Long CONFIRM = 8002L;
    /**
     * 保存状态
     */
    public static final Long NOT_CONFIRM = 8000L;
    
    /**
     * 已结算状态
     */
    public static final Long FINSH_BALANCE = 8004L;
	/**
	 * 再结算状态
	 */
    public static final Long RE_BALANCE = 8006L;
    /**
     * 已归还
     */
    public static final Long RETURN = 1L;
    /**
     * 未归还
     */
    public static final Long NOT_RETURN = 0L;
    
    /**
     * 旧件处理--旧配件已确认,并由客户回收
     */
    public static final Long CUSTOMERDEAL = 1L;
    
    public static final String CUSTOMERDEALSHOW = "旧配件已确认,并由客户回收";
    
    /**
     * 旧件处理--旧配件已确认,并由经销商处理
     */
    public static final Long SHOPDEAL = 2L;
    
    public static final String SHOPDEALSHOW = "旧配件已确认,并由经销商处理";
    
    /**
     * 旧件处理--无旧配件
     */
    public static final Long NODEAL = 3L;
    
    public static final String NODEALSHOW = "无旧配件";
    
    public static Map<Long,String> getDealMap(){
    	
    	Map map = new LinkedHashMap<Long,String>();
    	
    	map.put(CUSTOMERDEAL, CUSTOMERDEALSHOW);
    	
    	map.put(SHOPDEAL, SHOPDEALSHOW);
    	
    	map.put(NODEAL, NODEALSHOW);
    	
    	return map;
    
    }
    
    //修理工时费
    public static final String XLGSF = "修理工时费";  
    
    //修理材料费
    public static final String XLCLF = "修理材料费";
    
    //销售金额
    public static final String XSJE = "销售金额";
    
    public static final Long SELLENTRUST = 1L;
    
    public static final Long SELLCUSTOMER = 2L;
    
    /**
     * 免费
     */
    public static final Long FREE = 1L;
    
    /**
     * 不免费
     */
    public static final Long NOT_FREE = 0L;

    /**********************************结算单状态****************************************************/
    
    public static final Long JSDHASBALANCE = 8004L;
    
    public static final String JSDHASBALANCESHOW = "已结算";
    
    public static final Long JSDREBALANCE = 8006L;
    
    public static final String JSDREBALANCESHOW = "再结算";

    /**********************************结算单状态*************************************************************/    
    
    
    public static final String LISTPAGE = "listPage";
    /**
     * 得到所有明细 无论是否结算都取出来
     */
    public static final Long BALANCE_ALL = 1L;
    /**
     * 得到未结算的明细
     */
    public static final Long BALANCE_ISNULL = 2L;
    /**
     * 得到已结算的明细
     */
    public static final Long BALANCE_NOTNULL = 3L;
    
    /**
     * 单次提醒
     */
    public static final Long SINGLEALERT = 1L;
    
    /**
     * 多次提醒
     */
    public static final Long MULTIALERT = 2L;
    /**
     * 提醒范围 - 开委托书时
     */
    public static final Long ENTRIST_RANGE_ALERT = 1L;
    /**
     * 提醒范围 - 结算时
     */
    public static final Long BALANCE_RANGE_ALERT = 2L;
    /**
     * 提醒范围 - 竣工时
     */
    public static final Long FINISH_RANGE_ALERT = 3L;
    /**
     *已提醒 
     */
    public static final Long ISALERT = 1L;
    /**
     *还未提醒 
     */
    public static final Long NOALERT = 0L;
    
    /**
     * 到款金额
     */
    public static final Long AMOUNTR = 1L;
    
    public static final String AMOUNTRSHOW = "到款金额";
    
    /**
     * 减免金额
     */
    public static final Long AMOUNTS = 2L;
    
    public static final String AMOUNTSSHOW = "减免金额";
    
    public static final Map<Long,String> getAmountTypeMap(){
    	
    	Map<Long,String> map = new LinkedHashMap<Long,String>();
    	
    	map.put(AMOUNTR, AMOUNTRSHOW);
    	
    	map.put(AMOUNTS, AMOUNTSSHOW);
    	
    	return map;
    	
    }
    
    /**
     * 会员卡状态--正常
     */
    public static final Long CARD_VALID_STATUS = 1L;
    
    public static final String CARD_VALID_STATUS_SHOW = "正常";
    
    /**
     * 会员卡状态--失效
     */
    public static final Long CARD_NO_VALID_STATUS = 2L;
    
    public static final String CARD_NO_VALID_STATUS_SHOW = "失效";
    

    /**
     * 会员卡状态--挂失
     */
    public static final Long CARD_LOST_STATUS = 3L;
    
    public static final String CARD_LOST_STATUS_SHOW = "挂失";
    
    public static final Map<Long,String> getCardStatusMap(){
    	
    	Map<Long,String> map = new LinkedHashMap<Long,String>();
    	
    	map.put(CARD_VALID_STATUS, CARD_VALID_STATUS_SHOW);
    	
    	map.put(CARD_NO_VALID_STATUS, CARD_NO_VALID_STATUS_SHOW);
    	
    	map.put(CARD_LOST_STATUS, CARD_LOST_STATUS_SHOW); 
    	
    	return map;
    	
    } 
    
    
    public static final Long CASH = 201L;		
    
    public static final Long REMIT = 202L;
    
    public static final Long OWE = 203L;
    
    public static final String CASH_SHOW = "现金";
    
    public static final String REMIT_SHOW = "汇款";
    
    public static final String OWE_SHOW = "挂账";
    
    public static final Map<Long,String> getPayMentMap(){
    	
    	Map<Long,String> map = new LinkedHashMap<Long,String>();
    	
    	map.put(CASH, CASH_SHOW);
    	
    	map.put(REMIT, REMIT_SHOW);
    	
    	map.put(OWE, OWE_SHOW); 
    	
    	return map;
    	
    }
    
    public static final Long getPayMentByName(String payMentName){
    	Map<Long,String> map = getPayMentMap();
    	for(Long key : map.keySet()){
    		String val = map.get(key);
    		if(val.equals(payMentName)){
    			return key;
    		}
    	}
    	
    	return null;
    }
    
    public static final String CARD_KK = "开卡";
    
    public static final String CARD_CZ = "充值";
    
    public static final String CARD_CJF = "充积分";
    
    public static final String CARD_JS = "结算";
    
    public static final String CARD_JFXF = "积分消费";
    
    public static Map<String, String> getOperationCardMap(){
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	
    	map.put(CARD_KK, CARD_KK);
    	
    	map.put(CARD_CZ, CARD_CZ);
    	
    	map.put(CARD_CJF, CARD_CJF);
    	
    	map.put(CARD_JS, CARD_JS);
    	
    	map.put(CARD_JFXF, CARD_JFXF);
    
    	return map;
    	
    }
    
    public static final Long GREATERZERO = 40L;		//大于零
    
    public static final Long LESSZERO = 42L;		//小于零
    
    public static final Long EQUALZERO = 44L;		//等于零
    
    public static Map<Long, String> heckTypeMap(){
    	
    	Map<Long, String> map = new LinkedHashMap<Long, String>();
    	
    	map.put(GREATERZERO, "大于零");
    	
    	map.put(LESSZERO, "小于零");
    	
    	map.put(EQUALZERO, "等于零");
    
    	return map;
    	
    }
    
    public static final Long CHECKSAVE = 60L;		//保存状态
    	
    public static final Long CHECKINTO = 62L;		//盘点状态
    
    public static Map<Long, String> checkStatusMap(){
    	
    	Map<Long, String> map = new LinkedHashMap<Long, String>();
    	
    	map.put(CHECKSAVE, "保存");
    	
    	map.put(CHECKINTO, "盘点");
    	
    	return map;
    	
    }
    
    
    public static final Long ZLSP = 300L;				//索赔
    public static final Long ZLBX = 310L;				//保险
    public static final Long ZLNB =	320L;				//内部
    public static final Long ZLYHFF = 330L;				//用户付费
    
    public static Map<String, String> zlMap(){
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("索赔W", "索赔W");
    	map.put("保险P", "保险P");
    	map.put("内部I", "内部I");
    	map.put("用户付费C", "用户付费C");
    	return map;
    }
    
    public static final Long XMLXWX = 500L;				//正常维修	 
    
    public static Map<String, String> xmlxMap(){
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	/*map.put("新车准备", "新车准备");
    	map.put("首保", "首保");
    	map.put("定期保养", "定期保养");
    	map.put("保修", "保修");
    	map.put("正常维修", "正常维修");
    	map.put("保险维修", "保险维修");
    	map.put("召回", "召回");
    	map.put("服务营销", "服务营销");
    	map.put("网点内部返工", "网点内部返工");
    	map.put("索赔帐续保", "索赔帐续保");
    	map.put("新车PDI", "新车PDI");
    	map.put("非索赔帐续保", "非索赔帐续保");
    	map.put("其他", "其他");
    	map.put("自理事故", "自理事故");*/
    	map.put("钣金", "钣金");
    	map.put("油漆", "油漆");
    	map.put("机修", "机修");
    	map.put("电修", "电修");
    	map.put("外包", "外包");
    	map.put("美容装饰", "美容装饰");
    	map.put("新车PDI", "新车PDI");
    	return map;
    }
    
    public static Map<String, String> getCompanyMap(){
    	
    	Map<String, String> map =  new HashMap<String, String>();
    	
    	map.put("xtlCode", "70691M");
    	
    	map.put("dfbzCode", "62640L");
    	
    	map.put("xtlName", "成都精典腾龙汽车销售有限公司");
    	
    	map.put("dfbzName", "成都精典东越汽车销售有限公司");
    	
    	return map;
    }
}
