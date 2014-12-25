package com.selfsoft.framework.common;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.selfsoft.baseparameter.service.ITmStoreDiskService;

public class QuarzDao extends HibernateDaoSupport {

	
	
	public void setHibernateSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public void resetParamValue(){
		System.out.println("所有单号序号清零");
		String types = StringUtils.join(StockTypeElements.getQueryString(), ",");
		String hql = "update TmDictionary t set t.paramvalue = 0 where t.paramtype in ("+types+")";
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	public void backUpDataBase(){
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"classpath:applicationContext*.xml");

		ITmStoreDiskService tmStoreDiskService =  (ITmStoreDiskService) appContext
		.getBean("tmStoreDiskService");
		
		//备份数据库
		String database = CommonMethod.getPropertiesValue(this.getClass().getResourceAsStream("/system.properties"), "database");
		//备份文件夹
		//String backupflod = CommonMethod.getPropertiesValue(this.getClass().getResourceAsStream("/system.properties"), "backupflod");
		
		if(null == tmStoreDiskService.findAll() || tmStoreDiskService.findAll().size() == 0){
			
			return ;
			
		}
		
		String backupflod = tmStoreDiskService.findAll().get(0).getDiskPath();
		
		File disk = new File(backupflod.split(":")[0] + ":\\");
		
		File[] fsDisk = disk.listFiles();
		
		/*int flag = 0;
		
		if(null != fsDisk && fsDisk.length > 0){
			
			for(int i = 0 ; i < fsDisk.length ; i++){
				
				if("selfsoft.txt".equals(fsDisk[i].getName())){
					
					flag ++;
					
				}
				
				
			}
			
		}
		
		if(flag == 0){
			
			System.out.println("disk is error");
			
			return ;
			
		}
		*/
		
		File file = new File(backupflod);
		//如果没有文件夹则创建
		if(!file.exists()){
			
			file.mkdirs();
			
		}
		
		//删除里面所有文件--START
		/*File[] fs = file.listFiles();
		
		for(int i = 0 ; i < fs.length ; i++){
			
			fs[i].delete();
			
		}*/
		//删除里面所有文件--END
		String dateTime = CommonMethod.parseDateToString(new Date(), "yyyyMMdd");
		
		//备份语句
		final String sql = "BACKUP DATABASE " + database + " TO DISK = '" + backupflod + "\\" + database + dateTime +".dat'";
		
		System.out.println(sql);
		
		this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						Query query = session.createSQLQuery(sql); 
						
						query.executeUpdate();
						
						return null;
					}
				});
		
	}
	
	public static void main(String[] args){
		
		QuarzDao q = new QuarzDao();
		
		q.backUpDataBase();
		
	}
}
