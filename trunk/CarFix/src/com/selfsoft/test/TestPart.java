package com.selfsoft.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseinformation.service.ITbPartInfoService;

public class TestPart {
	
	private static ITbPartInfoService tbPartInfoService;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		
		tbPartInfoService=(ITbPartInfoService) appContext.getBean("tbPartInfoService");
		
		tbPartInfoService.updateAllNotRightStoreQuantity();
		
	}

}
