package com.selfsoft.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.service.ITbBookService;

public class TestTbBook {

	/**
	 * @param args
	 */
	private static ITbBookService tbBookService;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		
		tbBookService = (ITbBookService) appContext.getBean("tbBookService");
		
		testUpdate();
	}

	public static void testUpdate(){
		TbBook t = tbBookService.findById(5L);
		t.setIsCome(1L);
		tbBookService.update(t);
	}
}
