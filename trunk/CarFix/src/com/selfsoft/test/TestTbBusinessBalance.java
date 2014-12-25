package com.selfsoft.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;

public class TestTbBusinessBalance {

	
	private static ITbBusinessBalanceService tbBusinessBalanceService;
	
	private static ITbBusinessBalanceItemService tbBusinessBalanceItemService;
	
	public static void main(String[] args) {
		
		ApplicationContext appContext= new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");	
		
		tbBusinessBalanceService = (ITbBusinessBalanceService) appContext.getBean("tbBusinessBalanceService");
		
		tbBusinessBalanceItemService = (ITbBusinessBalanceItemService) appContext.getBean("tbBusinessBalanceItemService");
		
		//testFindTbBusinessBalanceToGroup();
	
		//testFindGroupTbBusinessBalanceItemListByTbBusinessBalanceId();
	
		//testFavourAmount();
		
		//testFindOrder();
		
		//testUpdate();
		
		//testStaticItem();
		
		testFindBusinessBalance();
	}

	private static void testFindBusinessBalance(){
		
		
		TbBusinessBalance t = new TbBusinessBalance();
		
		t.setTmModelTypeId(1L);
		
		tbBusinessBalanceService.findByTbBusinessBalance(t);
	}
	
	private static void testFindOrder(){
		
		tbBusinessBalanceService.fixBusinessBalanceOrder("fixCount","car");
		
	}
	private static void testFindTbBusinessBalanceToGroup(){
		
		TbBusinessBalance tbBusinessBalanceTemp = new TbBusinessBalance();

		tbBusinessBalanceTemp.setId(Long.valueOf(54));
		
		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService.findTbBusinessBalanceToGroup(tbBusinessBalanceTemp);
	
		for(TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList){
			
			System.out.println(tbBusinessBalance.getBalanceCode()+ " " + tbBusinessBalance.getBananceDate() + " " + tbBusinessBalance.getTmUser().getUserRealName() + " " +tbBusinessBalance.getShouldPayAmount());
			
		}
	}
	
	private static void testFindGroupTbBusinessBalanceItemListByTbBusinessBalanceId(){
		
		List<TbBusinessBalanceItem> list =  tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(54L);
		
		for(TbBusinessBalanceItem tbBusinessBalanceItem : list){
			
			System.out.println(tbBusinessBalanceItem.getBalanceItemCode() + " " + tbBusinessBalanceItem.getBalanceItemTotal());
		
		}
	}
	
	private static void testFavourAmount(){
		
		//Double d = tbBusinessBalanceService.findFixContentFavourAmount(100D, 1D);
		
		//System.out.println(d);
	}
	
	private static void testUpdate(){
		
		TbBusinessBalance t = new TbBusinessBalance();
		
		t.setId(8L);
		
		t.setBalanceTotalAll(1500D);
		
		tbBusinessBalanceService.update(t);
	}
	
	private static void testStaticItem(){
		
		TbBusinessBalance t = new TbBusinessBalance();
		
		t.setBananceDateStart(new Date());
		
		t.setBananceDateEnd(new Date());
		
		t.setTmModelTypeId(1L);
		
		List<StatisticsTbFixBusinessVo> list = tbBusinessBalanceItemService.staticsBalanceItem(t);
	
		/*for(StatisticsTbFixBusinessVo s : list){
			
			System.out.println(s.getBalanceItemName() + " " + s.getBalanceItemAmount());
			
		}*/
	}

}
