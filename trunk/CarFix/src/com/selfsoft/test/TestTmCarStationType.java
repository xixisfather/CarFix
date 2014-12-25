package com.selfsoft.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;

public class TestTmCarStationType {
	
	private static ITmCarStationTypeService  tmCarStationTypeService;
	
	public static void main(String[] args){
		
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		
		tmCarStationTypeService = (ITmCarStationTypeService) appContext.getBean("tmCarStationTypeService");
	
		testFindByCarModelId();
	}
	
	private static void testFindByCarModelId(){
		TmCarStationType t = tmCarStationTypeService.findTmCarStationTypeByTmCarModelTypeId(1L);
		
		System.out.println(t.getStationCode() + t.getStationRemark());
	}
}
