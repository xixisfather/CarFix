package com.selfsoft.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;

public class TestTmCarModelType {

	private static ITmCarModelTypeService tmCarModelTypeService;
	public static void main(String[] args) {
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		tmCarModelTypeService = (ITmCarModelTypeService) appContext.getBean("tmCarModelTypeService");
		findByTmCarModelType();
	}

	private static void findByTmCarModelType(){
		TmCarModelType t = new TmCarModelType();
		t.setModelCode("AUDI");
		TmCarStationType tc = new TmCarStationType();
		tc.setId(1L);
		t.setTmCarStationType(tc);
		tmCarModelTypeService.findByTmCarModelType(t);
	}
}
