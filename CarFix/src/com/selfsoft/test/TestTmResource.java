package com.selfsoft.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.service.ITmResourceService;

public class TestTmResource {

	private static ITmResourceService service;
	
	public static void main(String[] args){
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		service= (ITmResourceService) appContext.getBean("tmResourceService");
		
		TmResource tmResource = service.findById(1L);
//		service.getTmResourceTree(tmResource);
	}
}
