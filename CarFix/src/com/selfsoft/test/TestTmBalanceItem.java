package com.selfsoft.test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;

public class TestTmBalanceItem {

	/**
	 * @param args
	 */
	
	private static ITmBalanceItemService tmBalanceItemService;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		
		tmBalanceItemService = (ITmBalanceItemService) appContext.getBean("tmBalanceItemService");
		
		//testFindByHql();
		
		formulaCalculateByTmBalanceIdAndTmBalanceItemName();
	}

	
	public static void testFindByHql(){
		TmBalanceItem t = tmBalanceItemService.findTmBalanceItemByTmBalanceIdAndTmBalanceItemName(3L, "总价");
	
		if(null!=t){
			System.out.println(t.getItemName());
		}
		else{
			System.out.println(t);
		}
		
	}
	
	public static void formulaCalculateByTmBalanceIdAndTmBalanceItemName()
	{
		
		//模拟实际中各个金额传值 不如：实际中 总价为1000 那么 Map.put("总价","1000");
		
		List<TmBalanceItem>tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(3L));
		
		Map<String,BigDecimal> map = new LinkedHashMap<String, BigDecimal>();
		
		for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
			
			map.put(tmBalanceItem.getItemName().trim(), new BigDecimal("1000"));
		
		}
		
		//查出 项目定义中     一般修理 ID=3  中项目明细总价的价格计算出   
		List<Object> list = tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(3L, "修理工时费", map);
	
		System.out.println(list.get(0));
	}
	
}
