package com.selfsoft.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TestTmUser {
	private static ITmUserService tmUserService;
	
	public static void main(String[] args) {
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		
		tmUserService=(ITmUserService) appContext.getBean("tmUserService");
		
		//testFindAll();
		//testFindById();
		//testDelete();
		testInsert();
		//testFindByObject();
		//testUpdateObjectValid();
		//testFindBySQL();
	}
	
	private static void testFindAll(){
		System.out.println(tmUserService.findAll());
	}
	
	private static void testFindById(){
		System.out.println(tmUserService.findById(3L));
	}
	
	private static void testDelete(){
		System.out.println(tmUserService.deleteById(3L));
	}
	
	private static void testInsert(){
		TmUser tmUser=new TmUser();
		tmUser.setUserName("admindddd");
		tmUser.setPassword("123456");
		
		System.out.println(tmUserService.insert(tmUser));
	
		
	}
	
	private static void testFindByObject(){
		TmUser tmUser=new TmUser();
		
		tmUser.setUserName("a");
		List<TmUser>tmUserList= tmUserService.findByTmUser(tmUser);
		for(TmUser temp:tmUserList){
			System.out.println(temp.getId()+" "+temp.getUserName());
		}
	}
	
	private static void testUpdateObjectValid(){
		//System.out.println(tmUserService.updateTmUserNotValid(28L));
		
	}
	
	private static void testFindBySQL(){
		TmUser tmUser=new TmUser();
		tmUser.setUserName("c");
		List<TmUser>tmUserList= tmUserService.findBySQL(tmUser);
		for(TmUser temp:tmUserList){
			System.out.println(temp.getId()+" "+temp.getUserName());
		}
	}
}
