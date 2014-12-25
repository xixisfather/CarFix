package com.selfsoft.secrity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbFixEntrustDao;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.secrity.dao.ITmSysRegeditDao;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.model.TmSysRegedit;
import com.selfsoft.secrity.service.ITmCompanyService;
import com.selfsoft.secrity.service.ITmSysRegeditService;
import com.selfsoft.util.regedit.RegeditForSystem;

@Service("tmSysRegeditService")
public class TmSysRegeditServiceImpl implements ITmSysRegeditService{

	@Autowired
	private ITmSysRegeditDao tmSysRegeditDao;
	
	@Autowired
	private ITmCompanyService tmCompanyService;
	
	@Autowired
	private ITbFixEntrustDao tbFixEntrustDao;

	public TmSysRegedit findById(Long id) {
		// TODO Auto-generated method stub
		return tmSysRegeditDao.findById(id);
	}

	public void insert(TmSysRegedit tmSysRegedit) {
		// TODO Auto-generated method stub
		tmSysRegeditDao.insert(tmSysRegedit);
	}

	public void update(TmSysRegedit tmSysRegedit) {
		// TODO Auto-generated method stub
		tmSysRegeditDao.update(tmSysRegedit);
	}

	public List<TmSysRegedit> findAll() {
		// TODO Auto-generated method stub
		return tmSysRegeditDao.findAll();
	}

	public TmSysRegedit showUniqueSysRegedit() {
		// TODO Auto-generated method stub
		if(null!=this.findAll()&&this.findAll().size()>0){
			
			return this.findAll().get(0);
			
		}
		
		return null;
	}
	
	public void setDeadDate(){
		try{
			TmSysRegedit tmSysRegedit = this.showUniqueSysRegedit();
			
			if(null==tmSysRegedit){
				
				TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();
				
				String companyNamePINYIN = CommonMethod.tranferToPinYin(tmCompany.getCompanyName());
				
				//String companyAddressPINYIN = CommonMethod.tranferToPinYin(tmCompany.getCompanyAddress());
				String macAddress = CommonMethod.getMACAddress();
				
				
				System.out.println("公司名字拼音:"+companyNamePINYIN);
				
				//System.out.println("公司地址拼音:"+companyAddressPINYIN);
				System.out.println("机器码:"+macAddress);
				
				if(null!=companyNamePINYIN&&null!=macAddress){
					
					String sysSn = RegeditForSystem.doEncrypt(companyNamePINYIN, macAddress, CommonMethod.parseDateToString(CommonMethod.addDate(new Date(), 30), "yyyy-MM-dd"));
					
					System.out.println("生成序列号:"+sysSn);
					
					TmSysRegedit t = new TmSysRegedit();
					
					t.setSysSn(sysSn);
					
					if(tbFixEntrustDao.findAll()==null||tbFixEntrustDao.findAll().size()==0)
					
						this.insert(t);
					
				}
				
				
			}
		}catch(Exception e){
			
			e.printStackTrace();
		
		}
		
	}
	
	public String getDeadDate(){
		String deadDate = null;
		try{
			
			TmSysRegedit tmSysRegedit = this.showUniqueSysRegedit();
			
			TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();
			
			String companyNamePINYIN = CommonMethod.tranferToPinYin(tmCompany.getCompanyName());
			
			//String companyAddressPINYIN = CommonMethod.tranferToPinYin(tmCompany.getCompanyAddress());
			String macAddress = CommonMethod.getMACAddress();
			
			
			System.out.println("公司名字拼音:"+companyNamePINYIN);
			
			//System.out.println("公司地址拼音:"+companyAddressPINYIN);
			System.out.println("机器码:"+macAddress);
			
			System.out.println("序列号:"+tmSysRegedit.getSysSn());
			
			deadDate = RegeditForSystem.deDecrypt(companyNamePINYIN, macAddress, tmSysRegedit.getSysSn());
			
			return deadDate;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			deadDate = null;
		}
		
		return deadDate;
	}
}
