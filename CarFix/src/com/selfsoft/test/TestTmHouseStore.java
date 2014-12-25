package com.selfsoft.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.service.ITbBookService;

public class TestTmHouseStore {
	private static ITmStoreHouseService tmStoreHouseService;
	
	private static ITbBookService tbBookService;
	public static void main(String[] args) {
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		tmStoreHouseService=(ITmStoreHouseService) appContext.getBean("tmStoreHouseService");
		//testInsertTmHouseStore();
		//testFindTmHouseStore();
		tbBookService = (ITbBookService) appContext.getBean("tbBookService");
		
		tbBookService.updateTbBookCustomer();
	}
	
	private static void testInsertTmHouseStore(){
		TmStoreHouse tmStoreHouse=new TmStoreHouse();
		tmStoreHouse.setHouseCode("HQ");
		tmStoreHouse.setHouseName("红旗");
		tmStoreHouse.setHouseRemark("红旗配件");
		tmStoreHouse.setIsMixed(0L);
		System.out.println(tmStoreHouseService.insert(tmStoreHouse));
	}
	
	private static void testFindTmHouseStore(){
		TmStoreHouse tmStoreHouse=new TmStoreHouse();
		tmStoreHouse.setHouseCode("HQ");
		tmStoreHouse.setHouseName("红旗");
		tmStoreHouse.setHouseRemark("红旗配件");
		tmStoreHouse.setIsMixed(0L);
		List<TmStoreHouse>list=tmStoreHouseService.findByTmStoreHouse(tmStoreHouse);
		System.out.println(list);
	}

}
