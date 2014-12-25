package com.selfsoft.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.framework.common.CommonMethod;

public class TestTbFixEntrust {

	private static ITbFixEntrustService tbFixEntrustService;

	private static ITbFixEntrustContentService tbFixEntrustContentService;
	
	private static ITbCarInfoService tbCarInfoService;
	
	private static ITmCardTypeService tmCardTypeService;

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"classpath:applicationContext*.xml");

		tbFixEntrustService = (ITbFixEntrustService) appContext
				.getBean("tbFixEntrustService");

		tbFixEntrustContentService = (ITbFixEntrustContentService) appContext
				.getBean("tbFixEntrustContentService");

		tbCarInfoService = (ITbCarInfoService) appContext.getBean("tbCarInfoService");
		
		tmCardTypeService = (ITmCardTypeService) appContext.getBean("tmCardTypeService");
		// test();

		// testStaticsContent();

		//testSumFixEntrust();
		
		//findCarLostList();
		
		testCalc();
	}

	private static void test() {
		TbFixEntrust t = new TbFixEntrust();

		t.setIsvalid(1L);

		List<StatisticsTbFixBusinessVo> list = tbFixEntrustService
				.statisticsTbFixEntrust(t);

		for (StatisticsTbFixBusinessVo statisticsTbFixBusinessVo : list) {

			System.out.println(statisticsTbFixBusinessVo.getModelTypeName()
					+ " " + statisticsTbFixBusinessVo.getCountNum());

		}

	}

	private static void testStaticsContent() {

		Double d = tbFixEntrustContentService.staticsTbFixEntrustContent(0L,
				null);

		System.out.println(d);
	}

	private static void testSumFixEntrust() {

		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(15L);

		List<TbFixEntrustContent> tbFixEntrustContentListPage = new ArrayList<TbFixEntrustContent>();

		List<TbFixEntrustContent> tbFixEntrustContentListTemp = new ArrayList<TbFixEntrustContent>();

		List<TbFixEntrustContent> tbFixEntrustContentListAdd = new ArrayList<TbFixEntrustContent>();

		if (null != tbFixEntrustContentList
				&& tbFixEntrustContentList.size() > 0) {

			for (int i = 0; i < tbFixEntrustContentList.size(); i++) {

				boolean flag = false;

				if (tbFixEntrustContentListTemp.size() == 0) {

					tbFixEntrustContentListTemp.add(tbFixEntrustContentList
							.get(i));

				} else {
					if (tbFixEntrustContentListTemp.size() > 1) {

						for (TbFixEntrustContent _tbFixEntrustContent : tbFixEntrustContentListTemp) {

							if (_tbFixEntrustContent.getStationName().equals(
									tbFixEntrustContentList.get(i)
											.getStationName())
									&& _tbFixEntrustContent.getTbWorkingInfo()
											.getId().equals(
													tbFixEntrustContentList
															.get(i)
															.getTbWorkingInfo()
															.getId())) {

								flag = true;

								break;

							}

						}

					}

				}

				if (flag) {

					continue;

				} else {

					tbFixEntrustContentListTemp.add(tbFixEntrustContentList
							.get(i));

				}

				TbFixEntrustContent temp = tbFixEntrustContentList.get(i);

				BigDecimal d = new BigDecimal(temp.getFixHourAll());
				
				BigDecimal d2 = new BigDecimal(temp.getFixHour());

				for (int j = i + 1; j < tbFixEntrustContentList.size(); j++) {

					if (temp.getStationName().equals(
							tbFixEntrustContentList.get(j).getStationName())
							&& temp.getTbWorkingInfo().getId().equals(
									tbFixEntrustContentList.get(j)
											.getTbWorkingInfo().getId())) {

						d = d.add(new BigDecimal(tbFixEntrustContentList.get(j)
								.getFixHourAll()));
						
						d2 = d2.add(new BigDecimal(tbFixEntrustContentList.get(j)
								.getFixHour()));

					}

				}

				temp.setFixHourAll(d.doubleValue());
				
				temp.setFixHour(d2.doubleValue());
				
				//if(!temp.getFixHourAll().equals(0d)){
					
					tbFixEntrustContentListAdd.add(temp);
					
				//}
				
				
			}

		}

		for (TbFixEntrustContent t : tbFixEntrustContentListAdd) {

			System.out.println(t.getTbWorkingInfo().getId() + " "
					+ t.getFixHour() + " " + t.getFixHourAll() + " "
					+ t.getStationName());

		}

	}
	
	public static void findCarLostList(){
		
		TbCarInfo t = new TbCarInfo();
		
		//t.setLicenseCode("000");
		
		t.setLastFixDayFrom(CommonMethod.parseStringToDate("2010-11-27", "yyyy-MM-dd"));
		
		t.setLastFixDayTo(CommonMethod.parseStringToDate("2010-11-27", "yyyy-MM-dd"));
		
		tbCarInfoService.findLostCar(t);
	}
	
	public static void testCalc(){
		
		System.out.println(tmCardTypeService.calcGSGiveMoney(299.99D, 2L));
		
	}
}
