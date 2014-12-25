package com.selfsoft.business.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.business.dao.ITbBookDao;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.model.TbBookFixPart;
import com.selfsoft.business.model.TbBookFixStation;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.service.ITbBookFixPartService;
import com.selfsoft.business.service.ITbBookFixStationService;
import com.selfsoft.business.service.ITbBookService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
@Service("tbBookService")
public class TbBookServiceImpl implements ITbBookService{

	@Autowired
	private ITbBookDao tbBookDao;
	@Autowired
	private ITbBookFixStationService tbBookFixStationService;
	@Autowired
	private ITbBookFixPartService tbBookFixPartService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbBookDao.deleteById(id);
	}

	public TbBook findById(Long id) {
		// TODO Auto-generated method stub
		return tbBookDao.findById(id);
	}

	public List<TbBook> findByTbBook(TbBook tbBook) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbBook.class);
		
		if(null!=tbBook){
			if(null!=tbBook.getId()){
				detachedCriteria.add(Restrictions.eq("id",tbBook.getId()));
			}
			if(null!=tbBook.getBookCode()){
				detachedCriteria.add(Restrictions.like("bookCode", "%"+tbBook.getBookCode()+"%"));
			}
			if(null!=tbBook.getRegisterDateStart()){
				detachedCriteria.add(Restrictions.ge("registerDate", tbBook.getRegisterDateStart()));
			}
			if(null!=tbBook.getRegisterDateEnd()){
				detachedCriteria.add(Restrictions.le("registerDate", tbBook.getRegisterDateEnd()));
			}
			if(null!=tbBook.getLicenseCode()&&!"".equals(tbBook.getLicenseCode())){
				detachedCriteria.add(Restrictions.like("licenseCode", "%"+tbBook.getLicenseCode()+"%"));
			}
			if(null!=tbBook.getIsCome())
			{
				detachedCriteria.add(Restrictions.eq("isCome",tbBook.getIsCome()));
			}
			if(null!=tbBook.getPlanFixTimeStart()){
				detachedCriteria.add(Restrictions.ge("planFixTime", tbBook.getPlanFixTimeStart()));
			}
			if(null!=tbBook.getPlanFixTimeEnd()){
				detachedCriteria.add(Restrictions.le("planFixTime", tbBook.getPlanFixTimeEnd()));
			}
			if(null!=tbBook.getTmUser()){
				
				if(null!=tbBook.getTmUser().getId()){
					
					detachedCriteria.createAlias("tmUser","tmUser");
					
					detachedCriteria.add(Restrictions.eq("tmUser.id",tbBook.getTmUser().getId()));
					
				}
			}
		}
		
		return tbBookDao.findByCriteria(detachedCriteria, tbBook);
	}

	public void insert(TbBook tbBook) {
		// TODO Auto-generated method stub
		tbBookDao.insert(tbBook);
	}

	public void update(TbBook tbBook) {
		// TODO Auto-generated method stub
		tbBookDao.update(tbBook);
	}
	//插入维修预约信息、修理工位、配件信息
	public void insertAll(TbBook tbBook){
		
		this.inserTbBookFixStation(tbBook);
		
		this.insertTbBookFixPart(tbBook);
		
		this.insert(tbBook);
	}
	//插入修理工位
	private void inserTbBookFixStation(TbBook tbBook){
		List<String> tbBookFixStationKeys = tbBook.getTbBookFixStationKeys();
		
		if(null!=tbBookFixStationKeys&&tbBookFixStationKeys.size()>0){
			for(String key : tbBookFixStationKeys){
				
				String[] tbBookFixStationArray = key.split(",");
				
				Long tbWorkingInfoId  = Long.valueOf(tbBookFixStationArray[0]);
				
				Long freeSymbol = Long.valueOf(tbBookFixStationArray[1]);
				
				TbBookFixStation tbBookFixStation = new TbBookFixStation();
				
				TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();
				
				tbWorkingInfo.setId(tbWorkingInfoId);
				
				tbBookFixStation.setTbBook(tbBook);
				
				tbBookFixStation.setTbWorkingInfo(tbWorkingInfo);
				
				tbBookFixStation.setFreeSymbol(freeSymbol);
				
				tbBookFixStationService.insert(tbBookFixStation);
			}
		}
	}
	//插入修理配件信息
	private void insertTbBookFixPart(TbBook tbBook){
		
		List<String> tbBookFixPartKeys = tbBook.getTbBookFixPartKeys();
		
		if(null!=tbBookFixPartKeys&&tbBookFixPartKeys.size()>0){
			for(String key : tbBookFixPartKeys){
				
				String[] tbBookFixPartArray = key.split(",");
				
				Long tbPartInfoId = Long.valueOf(tbBookFixPartArray[0]);
				
				Long freeSymbol = Long.valueOf(tbBookFixPartArray[1]);
				
				Double quantity = 0d;
				
				if(null != tbBookFixPartArray[2] && !"".equals(tbBookFixPartArray[2])){
					
					quantity = Double.valueOf(tbBookFixPartArray[2]);
					
				}
					
				Long dealType = Long.valueOf(tbBookFixPartArray[3]);
				
				TbBookFixPart tbBookFixPart = new TbBookFixPart();
				
				TbPartInfo tbPartInfo = new TbPartInfo();
				
				tbPartInfo.setId(tbPartInfoId);
				
				tbBookFixPart.setTbPartInfo(tbPartInfo);
				
				tbBookFixPart.setTbBook(tbBook);
				
				tbBookFixPart.setFreeSymbol(freeSymbol);
				
				tbBookFixPart.setPartQuantity(quantity);
				
				tbBookFixPart.setDealType(dealType);
				
				tbBookFixPartService.insert(tbBookFixPart);
			}
		}
	}
	
	//删除该预约单的修理工位
	private void deleteTbBookFixStation(TbBook tbBook){
		
		List<TbBookFixStation> tbBookFixStationList = tbBookFixStationService.findTbBookFixStationListByTbBookId(tbBook.getId());
		
		if(null!=tbBookFixStationList&&tbBookFixStationList.size()>0){
			
			for(TbBookFixStation tbBookFixStation : tbBookFixStationList){
				
				tbBookFixStationService.deleteById(tbBookFixStation.getId());
			
			}
		}
	}
	//删除预约单中配件信息
	private void deleteTbBookFixPart(TbBook tbBook){
		
		List<TbBookFixPart> tbBookFixPartList = tbBookFixPartService.findTbBookFixPartListByTbBookId(tbBook.getId());
		
		if(null!=tbBookFixPartList&&tbBookFixPartList.size()>0){
			
			for(TbBookFixPart tbBookFixPart : tbBookFixPartList){
				tbBookFixPartService.deleteById(tbBookFixPart.getId());
			}
		}
	}
	
	//更新预约单信息，包括工位信息
	public void updateAll(TbBook tbBook){
		
		this.update(tbBook);
		
		this.deleteTbBookFixStation(tbBook);
		
		this.deleteTbBookFixPart(tbBook);
		
		this.inserTbBookFixStation(tbBook);
		
		this.insertTbBookFixPart(tbBook);
	}
	
	/**
	 * 删除预约单，包括预约单信息，工时工位信息，配件信息
	 * @param id
	 */
	public boolean deleteAll(Long id){
		
		TbBook tbBook = this.findById(id);
		
		this.deleteTbBookFixPart(tbBook);
		
		this.deleteTbBookFixStation(tbBook);
		
		this.deleteById(id);
		
		return true;
	}
	
	public List<TbBook> findCurrentDayTbBook(String licenseCode){
		
		Date currentDay = new Date();
		
		String format = "yyyy-MM-dd 00:00:00";
		
		Date currentDayFrom = CommonMethod.parseStringToDate(CommonMethod.parseDateToString(currentDay, format),format);
		
		Date currentDayTo = CommonMethod.parseStringToDate(CommonMethod.parseDateToString(currentDay, format),format);
		
		currentDayTo = CommonMethod.addDate(currentDayTo, 1);
		
		return tbBookDao.findBySQL("SELECT tbBook FROM TbBook tbBook where tbBook.planFixTime>=? and tbBook.planFixTime<=? and tbBook.licenseCode=?",new Object[]{currentDayFrom,currentDayTo,licenseCode});
	}
	
	public List<TbBook> findCurrentDayTbBookAll(){
		
		Date currentDay = new Date();
		
		String format = "yyyy-MM-dd 00:00:00";
		
		Date currentDayFrom = CommonMethod.parseStringToDate(CommonMethod.parseDateToString(currentDay, format),format);
		
		Date currentDayTo = CommonMethod.parseStringToDate(CommonMethod.parseDateToString(currentDay, format),format);
		
		currentDayTo = CommonMethod.addDate(currentDayTo, 1);
		
		return tbBookDao.findBySQL("SELECT tbBook FROM TbBook tbBook where tbBook.planFixTime>=? and tbBook.planFixTime<=?",new Object[]{currentDayFrom,currentDayTo});
	}
	
	/**
	 * 每天晚上12点更新预约信息 更新客户是否预约  就约定修理当天有没有开委托书  修理日期
	 */
	public void updateTbBookCustomer(){
		
		List<TbBook> tbBookList = tbBookDao.findAll();
		
		if(null!=tbBookList){
			
			for(TbBook tbBook : tbBookList){
				
				List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService.findTbFixEntrustByFixDate(tbBook.getPlanFixTime());
				
				if(null!=tbFixEntrustList){
					
					for(TbFixEntrust tbFixEntrust : tbFixEntrustList){
						
						String licenseCode = tbFixEntrust.getTbCarInfo().getLicenseCode();
					
						List<TbBook> list = this.findCurrentDayTbBook(licenseCode);
						
						List<TbBook> listAll = this.findCurrentDayTbBookAll();
						
						if(null!=list){
							
							for(TbBook tk : list){
								
								tk.setIsCome(Constants.ISTRUE);
								
								this.update(tk);
							
							}
							
							for(TbBook tkAll : listAll){
								boolean flag = false;
								
								for(TbBook tk : list){
									
									if(tk.getId().equals(tkAll.getId())){
										flag = true;
										break;
									}
									
								}
								
								if(!flag){
									
									tkAll.setIsCome(Constants.NOTTRUE);
								
									this.update(tkAll);
								}
							}
							
						}
					}
				}
			
			}
			
		}
	}
	
}
