package com.selfsoft.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;

public class TestWorkingInfo {

	/**
	 * @param args
	 */
	private static ITbWorkingInfoService tbWorkingInfoService;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
	
		tbWorkingInfoService = (ITbWorkingInfoService) appContext.getBean("tbWorkingInfoService");
	
		testFindByCollectionId();
	}
	
	public static void testOriginSql(){
		List<Object[]>list = tbWorkingInfoService.findByStationCodeAndCarStationTypeId("01020000", 5L);
		
		if(list!=null&&list.size()>0){
			for(Object[] object : list){
				System.out.println(object[0]);
			}
		}
		else{
			System.out.println("has exist");
		}
	}
	
	public static void testFindByCollectionId(){
		
		List<TbWorkingInfo>list = tbWorkingInfoService.findTbWorkingInfoListByTbWorkingCollectionId(18L);
		
		for(TbWorkingInfo t : list){
			System.out.println(t.getId());
		}
	}

}
