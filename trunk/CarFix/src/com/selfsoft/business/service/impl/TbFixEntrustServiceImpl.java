package com.selfsoft.business.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCardHis;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.dao.ITmAlertDayDao;
import com.selfsoft.baseparameter.service.ITmAlertDayService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmInsuranceAlertDayService;
import com.selfsoft.business.dao.ITbFixEntrustDao;
import com.selfsoft.business.model.TbBespokePartContent;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.service.ITbBespokePartContentService;
import com.selfsoft.business.service.ITbBookService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbFixShareService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.BalanceSellCountVo;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmCompanyService;
import com.selfsoft.secrity.service.ITmUserService;

@Service("tbFixEntrustService")
public class TbFixEntrustServiceImpl implements ITbFixEntrustService {

	@Autowired
	private ITbFixEntrustDao tbFixEntrustDao;

	@Autowired
	private ITbFixEntrustContentService tbFixEntrustContentService;

	@Autowired
	private ITbFixShareService tbFixShareService;

	@Autowired
	private ITbBespokePartContentService tbBespokePartContentService;

	@Autowired
	private ITbPartInfoService tbPartInfoService;

	@Autowired
	private ITbBookService tbBookService;

	@Autowired
	private ITmCompanyService tmCompanyService;

	@Autowired
	private ITmUserService tmUserService;

	@Autowired
	private ITbCustomerService tbCustomerService;

	@Autowired
	private ITbCarInfoService tbCarInfoService;

	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;

	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;

	@Autowired
	private ITmStockOutService tmStockOutService;

	@Autowired
	private ITmAlertDayService tmAlertDayService;

	@Autowired
	private ITmInsuranceAlertDayService tmInsuranceAlertDayService;

	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;

	@Autowired
	private ITbCardHisService tbCardHisService;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbFixEntrustDao.deleteById(id);
	}

	public TbFixEntrust findById(Long id) {
		// TODO Auto-generated method stub
		return tbFixEntrustDao.findById(id);
	}

	public List<TbFixEntrust> findByTbFixEntrust(TbFixEntrust tbFixEntrust) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(TbFixEntrust.class);

		if (null != tbFixEntrust) {

			if (null != tbFixEntrust.getId()) {
				detachedCriteria
						.add(Restrictions.eq("id", tbFixEntrust.getId()));
			}

			if (null != tbFixEntrust.getEntrustCode()
					&& !"".equals(tbFixEntrust.getEntrustCode())) {
				detachedCriteria.add(Restrictions.like("entrustCode", "%"
						+ tbFixEntrust.getEntrustCode() + "%"));
			}
			if (null != tbFixEntrust.getFixDateStart()) {
				detachedCriteria.add(Restrictions.ge("fixDate",
						tbFixEntrust.getFixDateStart()));
			}
			if (null != tbFixEntrust.getFixDateEnd()) {
				detachedCriteria.add(Restrictions.le("fixDate",
						CommonMethod.addDate(tbFixEntrust.getFixDateEnd(), 1)));
			}

			if (null != tbFixEntrust.getMinutes()) {

				Date newDate = new Date();

				tbFixEntrust.setEstimateDateStart(newDate);

				tbFixEntrust.setEstimateDateEnd(CommonMethod.addMinute(
						new Date(), tbFixEntrust.getMinutes()));

				if (null != tbFixEntrust.getEstimateDateStart()) {
					detachedCriteria.add(Restrictions.ge("estimateDate",
							tbFixEntrust.getEstimateDateStart()));
				}
				if (null != tbFixEntrust.getEstimateDateEnd()) {
					detachedCriteria.add(Restrictions.le("estimateDate",
							tbFixEntrust.getEstimateDateEnd()));
				}
			}

			if (null != tbFixEntrust.getTbCustomer()) {

				boolean customerCodeBoolean = (null != tbFixEntrust.getTbCustomer().getCustomerCode()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getCustomerCode()));
				
				boolean customerNameBoolean = (null != tbFixEntrust.getTbCustomer().getCustomerName()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getCustomerName()));
				
				boolean telephoneBoolean = (null != tbFixEntrust.getTbCustomer().getTelephone()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getTelephone()));
				
				if(customerCodeBoolean||customerNameBoolean||telephoneBoolean){
				
					detachedCriteria.createAlias("tbCustomer", "tbCustomer");
				}
				
				if (null != tbFixEntrust.getTbCustomer().getCustomerCode()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getCustomerCode())) {
					detachedCriteria.add(Restrictions.like(
							"tbCustomer.customerCode", "%"
									+ tbFixEntrust.getTbCustomer()
											.getCustomerCode() + "%"));
				}

				if (null != tbFixEntrust.getTbCustomer().getCustomerName()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getCustomerName())) {
					detachedCriteria.add(Restrictions.like(
							"tbCustomer.customerName", "%"
									+ tbFixEntrust.getTbCustomer()
											.getCustomerName() + "%"));
				}

				if (null != tbFixEntrust.getTbCustomer().getTelephone()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getTelephone())) {
					detachedCriteria.add(Restrictions.like(
							"tbCustomer.telephone", "%"
									+ tbFixEntrust.getTbCustomer()
											.getTelephone() + "%"));
				}
			}

			if (null != tbFixEntrust.getTbCarInfo()) {
				detachedCriteria.createAlias("tbCarInfo", "tbCarInfo");

				if (null != tbFixEntrust.getTbCarInfo().getLicenseCode()) {
					detachedCriteria.add(Restrictions.like(
							"tbCarInfo.licenseCode", "%"
									+ tbFixEntrust.getTbCarInfo()
											.getLicenseCode() + "%"));
				}
				
				if (null != tbFixEntrust.getTbCarInfo().getLicenseCode()) {
					detachedCriteria.add(Restrictions.like(
							"tbCarInfo.chassisCode", "%"
									+ tbFixEntrust.getTbCarInfo()
											.getChassisCode() + "%"));
				}
				
				if (null != tbFixEntrust.getTbCarInfo().getTmCarModelType()) {
					detachedCriteria.createAlias("tbCarInfo.tmCarModelType",
							"tmCarModelType");

					if (null != tbFixEntrust.getTbCarInfo().getTmCarModelType()
							.getId()) {
						detachedCriteria.add(Restrictions.eq(
								"tmCarModelType.id", tbFixEntrust
										.getTbCarInfo().getTmCarModelType()
										.getId()));
					}
				}
			}

			if (null != tbFixEntrust.getIsvalid()) {
				detachedCriteria.add(Restrictions.eq("isvalid",
						tbFixEntrust.getIsvalid()));
			}

			if (null != tbFixEntrust.getIsFinish()) {
				detachedCriteria.add(Restrictions.eq("isFinish",
						tbFixEntrust.getIsFinish()));
			}

			if (null != tbFixEntrust.getEntrustStatus()) {
				detachedCriteria.add(Restrictions.eq("entrustStatus",
						tbFixEntrust.getEntrustStatus()));
			}

			if (null != tbFixEntrust.getWjg()
					&& !"".equals(tbFixEntrust.getWjg())) {

				if ("wjg".equals(tbFixEntrust.getWjg())) {

					detachedCriteria.add(Restrictions.ne("entrustStatus", 2L));

					detachedCriteria.add(Restrictions.ne("entrustStatus", 3L));

				}

				else if ("yjg".equals(tbFixEntrust.getWjg())) {

					detachedCriteria.add(Restrictions.or(
							Restrictions.eq("entrustStatus", 2L),
							Restrictions.eq("entrustStatus", 3L)));

				}
			}
			
			if (null != tbFixEntrust.getJsqk()
					&& !"".equals(tbFixEntrust.getJsqk())) {

				if ("wjs".equals(tbFixEntrust.getWjg())) {

					detachedCriteria.add(Restrictions.ne("entrustStatus", 3L));

				}

				else if ("yjs".equals(tbFixEntrust.getWjg())) {

					detachedCriteria.add(Restrictions.eq("entrustStatus", 3L));

				}
			}

			if (null != tbFixEntrust.getTmUser() && null != tbFixEntrust.getTmUser().getId()) {

				detachedCriteria.add(Restrictions.eq("tmUser.id", tbFixEntrust
						.getTmUser().getId()));

			}

		}
		return tbFixEntrustDao.findByCriteria(detachedCriteria, tbFixEntrust);
	}

	public void insert(TbFixEntrust tbFixEntrust) {
		// TODO Auto-generated method stub
		tbFixEntrustDao.insert(tbFixEntrust);
	}

	public void update(TbFixEntrust tbFixEntrust) {
		// TODO Auto-generated method stub
		tbFixEntrustDao.update(tbFixEntrust);
	}

	// 作废方法 ISVALID字段设置为0
	public boolean updateTbFixEntrustNotValid(Long id) {

		TbFixEntrust tbFixEntrust = tbFixEntrustDao.findById(id);

		tbFixEntrust.setIsvalid(0L);

		tbFixEntrust.setEntrustStatus(Constants.NOFINISH);

		return tbFixEntrustDao.update(tbFixEntrust);
	}

	// 插入委托修理内容
	private void insertTbFixEntrustContent(TbFixEntrust tbFixEntrust) {

		Set tbFixEntrustContents = tbFixEntrust.getTbFixEntrustContents();

		Iterator it = tbFixEntrustContents.iterator();

		//
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrust
						.getId());

		//
		while (it.hasNext()) {

			boolean flag = false;

			TbFixEntrustContent tbFixEntrustContent = (TbFixEntrustContent) it
					.next();

			tbFixEntrustContent.setTbFixEntrust(tbFixEntrust);
			//
			if (null != tbFixEntrustContentList) {

				/*
				 * for(TbFixEntrustContent _tbFixEntrustContent:
				 * tbFixEntrustContentList){
				 * 
				 * if(_tbFixEntrustContent.getFixHour()==tbFixEntrustContent.
				 * getFixHour
				 * ()&&_tbFixEntrustContent.getFreesymbol()==tbFixEntrustContent
				 * .getFreesymbol()&&_tbFixEntrustContent.getStationName()==
				 * tbFixEntrustContent.getStationName()){
				 * 
				 * flag = true;
				 * 
				 * break; }
				 * 
				 * }
				 * 
				 * if(!flag){
				 */
				this.insertTbFixShare(tbFixEntrustContent);

				tbFixEntrustContentService.insert(tbFixEntrustContent);

				// }

			} else {

				this.insertTbFixShare(tbFixEntrustContent);

				tbFixEntrustContentService.insert(tbFixEntrustContent);
			}
			//
			// this.insertTbFixShare(tbFixEntrustContent);

			// tbFixEntrustContentService.insert(tbFixEntrustContent);
		}
	}

	// 插入分配工时信息
	private void insertTbFixShare(TbFixEntrustContent tbFixEntrustContent) {

		Set tbFixShares = tbFixEntrustContent.getTbFixShares();

		Iterator it = tbFixShares.iterator();

		while (it.hasNext()) {

			TbFixShare tbFixShare = (TbFixShare) it.next();

			tbFixShare.setTbFixEntrustContent(tbFixEntrustContent);

			tbFixShareService.insert(tbFixShare);
		}
	}

	// 插入委托书信息 修理内容信息 工时分配信息
	public void insertAll(TbFixEntrust tbFixEntrust, String partCol,
			Double totalPrice) {

		this.insertTbFixEntrustContent(tbFixEntrust);

		this.insert(tbFixEntrust);

		this.insertBespokerPartContent(tbFixEntrust, partCol, totalPrice);
	}

	// 删除修理内容
	private void deleteTbFixEntrustContent(TbFixEntrust tbFixEntrust) {

		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrust
						.getId());

		if (null != tbFixEntrustContentList) {

			for (TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList) {

				/**
				 * edit by ccr 2010-11-24 如果一起删除的话 balanid也将被删除
				 */
				/*
				 * if(null==tbFixEntrustContent.getBalanceId()){
				 * 
				 * this.deleteTbFixShare(tbFixEntrustContent);
				 * 
				 * tbFixEntrustContentService.deleteById(tbFixEntrustContent.getId
				 * ());
				 * 
				 * }
				 */
				// edit 2010-12-19 增加了BALANCEID的值赋予了tbFixEntrustContent
				this.deleteTbFixShare(tbFixEntrustContent);

				tbFixEntrustContentService.deleteById(tbFixEntrustContent
						.getId());

			}
		}
	}

	// 删除派工信息
	private void deleteTbFixShare(TbFixEntrustContent tbFixEntrustContent) {

		List<TbFixShare> tbFixShareList = tbFixShareService
				.findTbFixShareListByTbFixEntrustContentId(tbFixEntrustContent
						.getId());

		if (null != tbFixShareList) {

			for (TbFixShare tbFixShare : tbFixShareList) {

				tbFixShareService.deleteById(tbFixShare.getId());

			}
		}
	}

	// 更新 委托书 修理内容 派工信息
	public void updateAll(TbFixEntrust tbFixEntrust, String partCol,
			Double totalPrice) {

		this.update(tbFixEntrust);

		this.deleteTbFixEntrustContent(tbFixEntrust);

		this.insertTbFixEntrustContent(tbFixEntrust);

		this.deleteBespokerPartContent(tbFixEntrust.getId());

		this.insertBespokerPartContent(tbFixEntrust, partCol, totalPrice);

	}

	// 更新再结算状态委托书
	public void updateTbFixEntrustReBalance(TbFixEntrust tbFixEntrust) {

		this.insertTbFixEntrustContent(tbFixEntrust);

	}

	/**
	 * 根据委托书号查找委托书
	 * 
	 * @param entrustCode
	 * @return
	 */
	public TbFixEntrust findByEntrustCode(String entrustCode) {

		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustDao
				.findBySQL(
						"SELECT tbFixEntrust FROM TbFixEntrust tbFixEntrust where tbFixEntrust.entrustCode=?",
						new Object[] { entrustCode });

		if (null != tbFixEntrustList && tbFixEntrustList.size() > 0) {

			return tbFixEntrustList.get(0);
		}
		return null;
	}

	/**
	 * 设置委托书为已经发料状态
	 * 
	 * @param entrustCode
	 */
	public void updateTbFixEntrustHasSendPart(String entrustCode) {

		TbFixEntrust tbFixEntrust = this.findByEntrustCode(entrustCode);

		tbFixEntrust.setEntrustStatus(Constants.SENDPART);

		this.update(tbFixEntrust);
	}

	/**
	 * 该委托书是否已经发料
	 */
	public boolean isTbFixEntrustHasSendPart(String entrustCode) {

		TbFixEntrust tbFixEntrust = this.findByEntrustCode(entrustCode);

		if (Constants.ISFINISH.equals(tbFixEntrust.getEntrustStatus())
				|| Constants.ISBALANCE.equals(tbFixEntrust.getEntrustStatus())) {

			return true;

		}

		return false;

	}

	/**** add by BaiJx *****/
	// 添加预约配件
	public void insertBespokerPartContent(TbFixEntrust tbFixEntrust,
			String partCol, Double totalPrice) {

		if (StringUtils.isNotBlank(partCol)) {
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				String tbPartInfoId = parts.split(":")[0]; // 配件id
				String partQuantity = parts.split(":")[1]; // 配件数量
				String price = parts.split(":")[2]; // 单价

				TbBespokePartContent tbBespokePartContent = new TbBespokePartContent();
				TbPartInfo partInfo = tbPartInfoService.findById(new Long(
						tbPartInfoId));
				tbBespokePartContent.setTbPartInfo(partInfo);
				tbBespokePartContent.setPartQuantity(new Double(partQuantity));
				tbBespokePartContent.setPrice(new Double(price));
				tbBespokePartContent.setTbFixEntrust(tbFixEntrust);
				tbBespokePartContent.setTotalPrice(totalPrice);
				tbBespokePartContentService.insert(tbBespokePartContent);
			}

		}
	}

	// 删除预约配件
	public void deleteBespokerPartContent(Long entrustId) {

		tbBespokePartContentService.deleteByEntrustId(entrustId);
	}

	/**** add by BaiJx *****/

	public List<TbFixEntrust> findTbMainFixEntrust(TbFixEntrust tbFixEntrust) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from TbFixEntrust t where 1=1 ");

		if (null != tbFixEntrust) {

			if (null != tbFixEntrust.getEntrustCode()
					&& !"".equals(tbFixEntrust.getEntrustCode())) {
				hql.append(" and t.entrustCode like '%"
						+ tbFixEntrust.getEntrustCode() + "%'");
			}

			if (null != tbFixEntrust.getTbCustomer()) {

				if (null != tbFixEntrust.getTbCustomer().getCustomerCode()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getCustomerCode())) {
					hql.append(" and t.tbCustomer.customerCode like '%"
							+ tbFixEntrust.getTbCustomer().getCustomerCode()
							+ "%'");
				}

				if (null != tbFixEntrust.getTbCustomer().getCustomerName()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getCustomerName())) {
					hql.append(" and t.tbCustomer.customerName like '%"
							+ tbFixEntrust.getTbCustomer().getCustomerName()
							+ "%'");
				}

				if (null != tbFixEntrust.getTbCustomer().getTelephone()
						&& !"".equals(tbFixEntrust.getTbCustomer()
								.getTelephone())) {
					hql.append(" and t.tbCustomer.telephone like '%"
							+ tbFixEntrust.getTbCustomer().getTelephone()
							+ "%'");
				}
			}

			if (null != tbFixEntrust.getTbCarInfo()) {

				if (StringUtils.isNotBlank(tbFixEntrust.getTbCarInfo()
						.getLicenseCode())) {
					hql.append(" and t.tbCarInfo.licenseCode like '%"
							+ tbFixEntrust.getTbCarInfo().getLicenseCode()
							+ "%'");
				}
			}

			if (null != tbFixEntrust.getIsvalid()) {

				hql.append(" and t.isvalid = " + tbFixEntrust.getIsvalid());
			}

			if (null != tbFixEntrust.getIsFinish()) {
				hql.append(" and t.isFinish = " + tbFixEntrust.getIsFinish());
			}

			if (null != tbFixEntrust.getEntrustStatus()) {
				hql.append(" and t.entrustStatus = "
						+ tbFixEntrust.getEntrustStatus());
			}

			if (StringUtils.isNotBlank(tbFixEntrust
					.getEntrustStatusCollection())) {
				hql.append(" and t.entrustStatus in ( "
						+ tbFixEntrust.getEntrustStatusCollection() + " )");
			}

			if (tbFixEntrust.getEntrustType() != null) {
				if (tbFixEntrust.getEntrustType().equals(1L))
					hql.append(" and not exists (select m.entrustId from TbMaintainPartContent m where m.entrustId = t.id ) ");
				if (tbFixEntrust.getEntrustType().equals(2L))
					hql.append(" and not exists (select so.trustBill from TmStockOut so where so.trustBill = t.entrustCode and so.outType ="
							+ StockTypeElements.SELLSTOCKOUT.getElementValue()
							+ " )");
			}
		}

		List<TbFixEntrust> result = tbFixEntrustDao.findBySQL(hql.toString(),
				new Object[] {});

		return result;
	}

	public boolean cancelTbFixEntrustFinish(Long id) {

		try {

			TbFixEntrust tbFixEntrust = this.findById(id);

			TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService
					.findByEntrustId(id);

			tbFixEntrust.setIsFinish(Constants.NOFINISH);

			if (null == tbBusinessBalance) {
				tbFixEntrust.setEntrustStatus(Constants.NOFINISH);
			} else {
				tbFixEntrust.setEntrustStatus(Constants.REBALANCE);
			}

			this.update(tbFixEntrust);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	public List<TbFixEntrust> findTbFixEntrustByFixDate(Date planFixTime) {

		if (null != planFixTime) {

			Date dateStart = CommonMethod.parseStringToDate(
					CommonMethod.parseDateToString(planFixTime, "yyyy-MM-dd")
							+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");

			Date dateEnd = CommonMethod.addDate(CommonMethod.parseStringToDate(
					CommonMethod.parseDateToString(planFixTime, "yyyy-MM-dd")
							+ " 00:00:00", "yyyy-MM-dd HH:mm:ss"), 1);

			return tbFixEntrustDao
					.findBySQL(
							"SELECT tbFixEntrust FROM TbFixEntrust tbFixEntrust WHERE tbFixEntrust.fixDate>=? AND tbFixEntrust.fixDate<?",
							new Object[] { dateStart, dateEnd });
		}

		return null;
	}

	/**
	 * 打印参数设置方法
	 * 
	 * @param id
	 * @return
	 */
	public Map putReportParamMap(Long id) {

		Map map = new HashMap();

		TbFixEntrust tbFixEntrust = this.findById(id);

		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(id);

		List<TbFixEntrustContent> tbFixEntrustContentListAdd = new ArrayList<TbFixEntrustContent>();

		if (null != tbFixEntrustContentList
				&& tbFixEntrustContentList.size() > 0) {

			for (TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList) {

				List<TbFixShare> tbFixShareList = tbFixShareService
						.findTbFixShareListByTbFixEntrustContentId(tbFixEntrustContent
								.getId());

				String fixPersons = "";

				if (null != tbFixShareList && tbFixShareList.size() > 0) {

					for (TbFixShare tbFixShare : tbFixShareList) {

						if (null != tbFixShare.getTmUser()) {

							TmUser tmUser = tmUserService.findById(tbFixShare
									.getTmUser().getId());

							fixPersons += tmUser.getUserRealName() + " ";
						}

					}
				}

				tbFixEntrustContent.setFixPersons(fixPersons);

				tbFixEntrustContentListAdd.add(tbFixEntrustContent);
			}

		} else {

			TbFixEntrustContent tbFixEntrustContent = new TbFixEntrustContent();

			tbFixEntrustContent.setStationCode("");

			tbFixEntrustContentListAdd.add(tbFixEntrustContent);
		}

		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		Map reportParameters = new HashMap();

		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
				.getTbCustomer().getId());

		TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
				.getTbCarInfo().getId());

		reportParameters.put("customerCode", tbCustomer.getCustomerCode());

		reportParameters.put("customerName", tbCustomer.getCustomerName());

		reportParameters.put("contractPerson", tbCustomer.getContractPerson());

		reportParameters.put("address", tbCustomer.getAddress());

		reportParameters.put("phone",
				tbCustomer.getPhone() == null ? "" : tbCustomer.getPhone()
						.trim() + "  " + tbCustomer.getTelephone() == null ? ""
						: tbCustomer.getTelephone().trim());

		// reportParameters.put("phone", tbCustomer.getPhone() + "  " +
		// tbCustomer.getTelephone());

		// 车辆信息
		reportParameters.put("licenseCode", tbCarInfo.getLicenseCode());

		reportParameters.put("modelName", tbCarInfo.getTmCarModelType()
				.getModelName());

		reportParameters.put("carProperty", tbCarInfo.getCarPropertyShow());

		reportParameters.put("phurseDate", CommonMethod.parseDateToString(
				tbCarInfo.getProductDate(), "yyyy-MM-dd"));

		reportParameters.put("chassisCode", tbCarInfo.getChassisCode());

		reportParameters.put("engineCode", tbCarInfo.getEngineCode());

		// reportParameters.put("kilo",tbCarInfo.getKilo()==null?"":String.valueOf(tbCarInfo.getKilo()));
		reportParameters.put(
				"kilo",
				tbFixEntrust.getEnterStationKilo() == null ? "" : String
						.valueOf(tbFixEntrust.getEnterStationKilo()));
		// 委托书
		reportParameters.put("entrustCode", tbFixEntrust.getEntrustCode());

		reportParameters.put("fixType", tbFixEntrust.getTmFixType()
				.getFixType());

		reportParameters.put("fixDate", CommonMethod.parseDateToString(
				tbFixEntrust.getFixDate(), "yyyy-MM-dd HH:mm:ss"));

		reportParameters.put("estimateDate", CommonMethod.parseDateToString(
				tbFixEntrust.getEstimateDate(), "yyyy-MM-dd HH:mm:ss"));

		reportParameters.put("userRealName", tbFixEntrust.getTmUser()
				.getUserRealName());

		reportParameters
				.put("beforeFixState", tbFixEntrust.getBeforeFixState());

		reportParameters.put("wrongDescribe", tbFixEntrust.getWrongDescribe());

		reportParameters.put("checkResult", tbFixEntrust.getCheckResult());

		// 公司信息
		reportParameters.put("companyName", tmCompany.getCompanyName());

		reportParameters.put("companyAddress", tmCompany.getCompanyAddress());

		reportParameters.put("companyPhone", tmCompany.getCompanyPhone());

		reportParameters.put("companyZipCode", tmCompany.getCompanyZipCode());

		reportParameters.put("companyAccount", tmCompany.getCompanyAccount());

		reportParameters.put("serviceLeader", tmCompany.getServiceLeader());

		// 打印时间
		reportParameters.put("printDate", CommonMethod.parseDateToString(
				new Date(), "yyyy-MM-dd HH:mm:ss"));

		map.put("reportParameters", reportParameters);

		map.put("dataSourceList", tbFixEntrustContentListAdd);

		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/tbFixEntrustReport_gxnndz.jrxml");

		}

		else if ("衡水市开发区众悦汽车装饰部".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/tbFixEntrustReport_hbhs.jrxml");

		} else {

			map.put("jrxmlPath", "/reportfiles/tbFixEntrustReport.jrxml");

		}

		map.put("reportTpl", "/tbFixEntrustContent_pdf_tpl.properties");

		return map;
	}

	/**
	 * 打印参数设置方法
	 * 
	 * @param id
	 * @return
	 */
	public Map putSendPersonReportParamMap(Long id) {

		Map map = new HashMap();

		TbFixEntrust tbFixEntrust = this.findById(id);

		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(id);

		List<TbFixEntrustContent> tbFixEntrustContentListAdd = new ArrayList<TbFixEntrustContent>();

		if (null != tbFixEntrustContentList
				&& tbFixEntrustContentList.size() > 0) {

			for (TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList) {

				List<TbFixShare> tbFixShareList = tbFixShareService
						.findTbFixShareListByTbFixEntrustContentId(tbFixEntrustContent
								.getId());

				String fixPersons = "";

				if (null != tbFixShareList && tbFixShareList.size() > 0) {

					for (TbFixShare tbFixShare : tbFixShareList) {

						if (null != tbFixShare.getTmUser()) {

							TmUser tmUser = tmUserService.findById(tbFixShare
									.getTmUser().getId());

							fixPersons += tmUser.getUserRealName() + " ";
						}

					}
				}

				tbFixEntrustContent.setFixPersons(fixPersons);

				tbFixEntrustContentListAdd.add(tbFixEntrustContent);
			}

		} else {

			TbFixEntrustContent tbFixEntrustContent = new TbFixEntrustContent();

			tbFixEntrustContent.setStationCode("");

			tbFixEntrustContentListAdd.add(tbFixEntrustContent);
		}

		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		Map reportParameters = new HashMap();

		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
				.getTbCustomer().getId());

		TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
				.getTbCarInfo().getId());

		reportParameters.put("customerCode", tbCustomer.getCustomerCode());

		reportParameters.put("customerName", tbCustomer.getCustomerName());

		reportParameters.put("contractPerson", tbCustomer.getContractPerson());

		reportParameters.put("address", tbCustomer.getAddress());

		reportParameters.put("phone",
				tbCustomer.getPhone() == null ? "" : tbCustomer.getPhone()
						.trim() + "  " + tbCustomer.getTelephone() == null ? ""
						: tbCustomer.getTelephone().trim());

		// reportParameters.put("phone", tbCustomer.getPhone() + "  " +
		// tbCustomer.getTelephone());

		// 车辆信息
		reportParameters.put("licenseCode", tbCarInfo.getLicenseCode());

		reportParameters.put("modelName", tbCarInfo.getTmCarModelType()
				.getModelName());

		reportParameters.put("carProperty", tbCarInfo.getCarPropertyShow());

		reportParameters.put("phurseDate", CommonMethod.parseDateToString(
				tbCarInfo.getProductDate(), "yyyy-MM-dd"));

		reportParameters.put("chassisCode", tbCarInfo.getChassisCode());

		reportParameters.put("engineCode", tbCarInfo.getEngineCode());

		// reportParameters.put("kilo",tbCarInfo.getKilo()==null?"":String.valueOf(tbCarInfo.getKilo()));
		reportParameters.put(
				"kilo",
				tbFixEntrust.getEnterStationKilo() == null ? "" : String
						.valueOf(tbFixEntrust.getEnterStationKilo()));
		// 委托书
		reportParameters.put("entrustCode", tbFixEntrust.getEntrustCode());

		reportParameters.put("fixType", tbFixEntrust.getTmFixType()
				.getFixType());

		reportParameters.put("fixDate", CommonMethod.parseDateToString(
				tbFixEntrust.getFixDate(), "yyyy-MM-dd HH:mm:ss"));

		reportParameters.put("estimateDate", CommonMethod.parseDateToString(
				tbFixEntrust.getEstimateDate(), "yyyy-MM-dd HH:mm:ss"));

		reportParameters.put("userRealName", tbFixEntrust.getTmUser()
				.getUserRealName());

		reportParameters
				.put("beforeFixState", tbFixEntrust.getBeforeFixState());

		reportParameters.put("wrongDescribe", tbFixEntrust.getWrongDescribe());

		reportParameters.put("checkResult", tbFixEntrust.getCheckResult());

		// 公司信息
		reportParameters.put("companyName", tmCompany.getCompanyName());

		reportParameters.put("companyAddress", tmCompany.getCompanyAddress());

		reportParameters.put("companyPhone", tmCompany.getCompanyPhone());

		reportParameters.put("companyZipCode", tmCompany.getCompanyZipCode());

		reportParameters.put("companyAccount", tmCompany.getCompanyAccount());

		reportParameters.put("serviceLeader", tmCompany.getServiceLeader());

		// 打印时间
		reportParameters.put("printDate", CommonMethod.parseDateToString(
				new Date(), "yyyy-MM-dd HH:mm:ss"));

		map.put("reportParameters", reportParameters);

		map.put("dataSourceList", tbFixEntrustContentListAdd);

		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/send_person_gxnndz.jrxml");

		} else if ("衡水市开发区众悦汽车装饰部".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/send_person_hbhs.jrxml");

		} else {

			map.put("jrxmlPath", "/reportfiles/send_person.jrxml");

		}

		map.put("reportTpl", "/tbFixEntrustContent_pdf_tpl.properties");

		return map;
	}

	// 统计各个车型的进站台次
	public List<StatisticsTbFixBusinessVo> statisticsTbFixEntrust(
			TbFixEntrust tbFixEntrust) {

		List<TbFixEntrust> tbFixEntrustList = this
				.findByTbFixEntrust(tbFixEntrust);

		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = new ArrayList<StatisticsTbFixBusinessVo>();

		Map<String, Integer> countMap = new HashMap<String, Integer>();

		if (null != tbFixEntrustList && tbFixEntrustList.size() > 0) {

			for (int i = 0; i < tbFixEntrustList.size(); i++) {

				TbFixEntrust ti = tbFixEntrustList.get(i);

				boolean flag = true;

				if (!countMap.containsKey(ti.getModelTypeCode())) {

					countMap.put(ti.getModelTypeCode(), 0);

				}

				for (int j = i + 1; j < tbFixEntrustList.size(); j++) {

					TbFixEntrust tj = tbFixEntrustList.get(j);

					if (ti.getModelTypeCode().equals(tj.getModelTypeCode())) {

						if (CommonMethod.isSameDate(ti.getFixDate(),
								tj.getFixDate())
								&& ti.getLicenseCode().equals(
										tj.getLicenseCode())) {

							flag = false;

							break;

						}

					}

				}

				if (flag) {

					Integer count = countMap.get(ti.getModelTypeCode());

					countMap.put(ti.getModelTypeCode(), count + 1);
				}

			}

			for (String m : countMap.keySet()) {

				StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();

				statisticsTbFixBusinessVo.setModelTypeCode(m);

				statisticsTbFixBusinessVo
						.setModelTypeName(tmCarModelTypeService
								.findByModelCode(m).getModelName());

				statisticsTbFixBusinessVo.setCountNum(countMap.get(m));

				statisticsTbFixBusinessVoList.add(statisticsTbFixBusinessVo);
			}
		}

		return statisticsTbFixBusinessVoList;
	}

	// 统计修理类型次数
	public List<StatisticsTbFixBusinessVo> statisticsFixType(
			TbFixEntrust tbFixEntrust) {

		List<TbFixEntrust> tbFixEntrustList = this
				.findByTbFixEntrust(tbFixEntrust);

		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = new ArrayList<StatisticsTbFixBusinessVo>();

		Map<String, Integer> countMap = new HashMap<String, Integer>();

		if (null != tbFixEntrustList && tbFixEntrustList.size() > 0) {

			for (int i = 0; i < tbFixEntrustList.size(); i++) {

				TbFixEntrust ti = tbFixEntrustList.get(i);

				if (!countMap.containsKey(ti.getTmFixType().getFixType())) {

					countMap.put(ti.getTmFixType().getFixType(), 1);

				}

				else {

					Integer count = countMap
							.get(ti.getTmFixType().getFixType());

					countMap.put(ti.getTmFixType().getFixType(), count + 1);
				}
			}

			for (String m : countMap.keySet()) {

				StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();

				statisticsTbFixBusinessVo.setFixType(m);

				statisticsTbFixBusinessVo.setCountNum(countMap.get(m));

				statisticsTbFixBusinessVoList.add(statisticsTbFixBusinessVo);
			}

		}

		return statisticsTbFixBusinessVoList;
	}

	/**
	 * 
	 * @param entrustStatus
	 *            3 已结算
	 * @param tbFixEntrust
	 * @return
	 */
	public Integer statisticsBalance(Long entrustStatus,
			TbFixEntrust tbFixEntrust) {

		String sql = "select count(*) from tb_fix_entrust b,tb_car_info c,TM_CAR_MODEL_TYPE d where 1=1 and b.CAR_INFO_ID = c.id and c.MODEL_TYPE_ID= d.id ";

		String sqlCondition = "";

		if (null != entrustStatus) {
			// 已结算
			if (Constants.ISBALANCE.equals(entrustStatus)) {
				sqlCondition = "and b.ENTRUST_STATUS =" + Constants.ISBALANCE;
			} else {
				sqlCondition = "and b.ENTRUST_STATUS !=" + Constants.ISBALANCE;
			}
		}

		if (null != tbFixEntrust) {

			if (null != tbFixEntrust.getFixDateStart()) {
				sqlCondition += " and cast(b.fix_date as varchar)>='"
						+ tbFixEntrust.getFixDateStart() + "'";
			}
			if (null != tbFixEntrust.getFixDateEnd()) {
				sqlCondition += " and cast(b.fix_date as varchar)<='"
						+ CommonMethod.addDate(tbFixEntrust.getFixDateEnd(), 1)
						+ "'";
			}
			if (null != tbFixEntrust.getTbCarInfo()) {
				if (null != tbFixEntrust.getTbCarInfo().getTmCarModelType())
					if (null != tbFixEntrust.getTbCarInfo().getTmCarModelType()
							.getId()) {
						sqlCondition += " and d.id = "
								+ tbFixEntrust.getTbCarInfo()
										.getTmCarModelType().getId();
					}
			}
		}

		List list = tbFixEntrustDao.findByOriginSql(sql + sqlCondition, null);

		return Integer.valueOf((String.valueOf(list.get(0) == null ? 0.00D
				: list.get(0))));
	}

	/**
	 * 
	 * @param tbFixEntrust
	 * @return
	 */
	public List<StatisticsTbFixBusinessVo> statisticsBalanceShow(
			TbFixEntrust tbFixEntrust) {

		StatisticsTbFixBusinessVo statisticsTbFixBusinessVo_fix = new StatisticsTbFixBusinessVo();

		StatisticsTbFixBusinessVo statisticsTbFixBusinessVo_sole = new StatisticsTbFixBusinessVo();

		List<StatisticsTbFixBusinessVo> list = new ArrayList<StatisticsTbFixBusinessVo>();

		/*
		 * Integer all = this.statisticsBalance(null, tbFixEntrust);
		 * 
		 * Integer balance = this.statisticsBalance(Constants.ISBALANCE,
		 * tbFixEntrust);
		 * 
		 * Integer noBalance = all - balance;
		 * 
		 * statisticsTbFixBusinessVo_fix.setSoleType("修理销售");
		 * 
		 * statisticsTbFixBusinessVo_fix.setAllBalance(all);
		 * 
		 * statisticsTbFixBusinessVo_fix.setHasBalance(balance);
		 * 
		 * statisticsTbFixBusinessVo_fix.setNoBalance(noBalance);
		 * 
		 * List<StatisticsTbFixBusinessVo> list = new
		 * ArrayList<StatisticsTbFixBusinessVo>();
		 */

		String format = "yyyy-MM-dd";

		BalanceSellCountVo balanceSellCountVo = tmStockOutService
				.getBalanceSellCountVo(CommonMethod.parseDateToString(
						tbFixEntrust.getFixDateStart(), format),
						CommonMethod.parseDateToString(
								tbFixEntrust.getFixDateEnd(), format),
						tbFixEntrust.getTbCarInfo() == null ? null
								: tbFixEntrust.getTbCarInfo()
										.getTmCarModelType().getId());

		statisticsTbFixBusinessVo_fix.setSoleType("修理销售");

		statisticsTbFixBusinessVo_fix.setAllBalance(balanceSellCountVo
				.getFixSellAllCount().intValue());

		statisticsTbFixBusinessVo_fix.setHasBalance(balanceSellCountVo
				.getFixSellIsBalanceCount().intValue());

		statisticsTbFixBusinessVo_fix.setNoBalance(balanceSellCountVo
				.getFixSellNoBalanceCount().intValue());

		statisticsTbFixBusinessVo_sole.setSoleType("单独销售");

		statisticsTbFixBusinessVo_sole.setAllBalance(balanceSellCountVo
				.getAloneSellAllCount().intValue());

		statisticsTbFixBusinessVo_sole.setHasBalance(balanceSellCountVo
				.getAloneSellIsBalanceCount().intValue());

		statisticsTbFixBusinessVo_sole.setNoBalance(balanceSellCountVo
				.getAloneSellNoBalanceCount().intValue());

		list.add(statisticsTbFixBusinessVo_fix);

		list.add(statisticsTbFixBusinessVo_sole);

		return list;
	}

	public BigDecimal getTotalCostPriceByEntrustList(
			List<TbFixEntrust> tbfixEntrustList) {
		if (tbfixEntrustList == null || tbfixEntrustList.size() == 0)
			return new BigDecimal("0.00");

		String entrustIds = "";
		for (TbFixEntrust tbFixEntrust : tbfixEntrustList) {
			entrustIds += tbFixEntrust.getId() + ",";
		}
		entrustIds = tbfixEntrustList.size() > 0 ? entrustIds.substring(0,
				entrustIds.length() - 1) : null;

		if (entrustIds != null) {

			StringBuilder sql = new StringBuilder();
			sql.append("select sum(pi.cost_price*m.part_quantity) from tb_fix_entrust fe , tb_maintain_part_content m  , tb_part_info pi");
			sql.append(" where fe.id = m.entrust_id and pi.id = m.part_id and m.is_free = 1 and m.is_confirm not in (8000) ");
			sql.append(" and fe.id in ( " + entrustIds + ")");
			sql.append(" union all");
			sql.append(" select sum(pi.cost_price*sod.quantity) from tb_fix_entrust fe , tm_stock_out so  , tm_stockout_detail sod , tb_part_info pi");
			sql.append(" where fe.entrust_code = so.trust_bill and pi.id = sod.partinfo_id and sod.is_free = 1 and so.id = sod.stockout_id and so.is_confirm not in (8000)");
			sql.append(" and fe.id in ( " + entrustIds + ")");

			List<BigDecimal> objList = tbFixEntrustDao.findByOriginSql(
					sql.toString(), null);

			BigDecimal wxCostPrice = objList.get(0) != null ? new BigDecimal(
					objList.get(0).toString()) : new BigDecimal("0.00");

			BigDecimal xsCostPrice = objList.get(1) != null ? new BigDecimal(
					objList.get(1).toString()) : new BigDecimal("0.00");

			BigDecimal result = wxCostPrice.add(xsCostPrice).divide(
					new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP);

			return result;
		}
		return new BigDecimal("0.00");
	}

	public BigDecimal getTotalSellPriceByEntrustList(
			List<TbFixEntrust> tbfixEntrustList) {
		if (tbfixEntrustList == null || tbfixEntrustList.size() == 0)
			return new BigDecimal("0.00");

		String entrustIds = "";
		for (TbFixEntrust tbFixEntrust : tbfixEntrustList) {
			entrustIds += tbFixEntrust.getId() + ",";
		}
		entrustIds = tbfixEntrustList.size() > 0 ? entrustIds.substring(0,
				entrustIds.length() - 1) : null;

		if (entrustIds != null) {

			StringBuilder sql = new StringBuilder();
			sql.append("select sum(m.price*m.part_quantity) from tb_fix_entrust fe , tb_maintain_part_content m ");
			sql.append(" where fe.id = m.entrust_id and m.is_free = 1 and m.is_confirm not in (8000)");
			sql.append(" and fe.id in ( " + entrustIds + ")");
			sql.append(" union all");
			sql.append(" select sum(sod.price*sod.quantity) from tb_fix_entrust fe , tm_stock_out so  , tm_stockout_detail sod");
			sql.append(" where fe.entrust_code = so.trust_bill  and sod.is_free = 1 and so.id = sod.stockout_id and so.is_confirm not in (8000)");
			sql.append(" and fe.id in ( " + entrustIds + ")");

			List<BigDecimal> objList = tbFixEntrustDao.findByOriginSql(
					sql.toString(), null);

			BigDecimal wxSellPrice = objList.get(0) != null ? new BigDecimal(
					objList.get(0).toString()) : new BigDecimal("0.00");

			BigDecimal xsSellPrice = objList.get(1) != null ? new BigDecimal(
					objList.get(1).toString()) : new BigDecimal("0.00");

			BigDecimal result = wxSellPrice.add(xsSellPrice).divide(
					new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP);

			return result;
		}
		return new BigDecimal("0.00");
	}

	/**
	 * 查询要保养的委托书信息
	 */
	public List<TbFixEntrust> findMaintainCarFixEntrust() {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(TbFixEntrust.class);

		detachedCriteria.add(Restrictions.ge(
				"remindMaintainDate",
				CommonMethod.addDate(new Date(), 0 - tmAlertDayService
						.findAll().get(0).getContinueDay())));

		detachedCriteria.add(Restrictions.le(
				"remindMaintainDate",
				CommonMethod.addDate(new Date(), (0 + tmAlertDayService
						.findAll().get(0).getAlertDay()))));

		return tbFixEntrustDao.findByCriteria(detachedCriteria, null);
	}

	public List<TbFixEntrust> findInsuranceCarFixEntrust() {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(TbFixEntrust.class);

		detachedCriteria.add(Restrictions.ge(
				"remindInsuranceDate",
				CommonMethod.addDate(new Date(), 0 - tmInsuranceAlertDayService
						.findAll().get(0).getContinueDay())));

		detachedCriteria.add(Restrictions.le("remindInsuranceDate",
				CommonMethod.addDate(new Date(),
						(0 + tmInsuranceAlertDayService.findAll().get(0)
								.getAlertDay()))));

		return tbFixEntrustDao.findByCriteria(detachedCriteria, null);
	}

	public void printTbFixEntrustTemplate(OutputStream os, String tpl,
			Long tbFixEntrustId) {

		/*
		 * Workbook wb = null;
		 * 
		 * WritableWorkbook wwb = null;
		 * 
		 * WritableSheet ws = null;
		 * 
		 * try {
		 * 
		 * wb = Workbook.getWorkbook(this.getClass().getResourceAsStream(tpl));
		 * 
		 * wwb = Workbook.createWorkbook(os, wb);
		 * 
		 * ws = wwb.getSheet(0);
		 * 
		 * WritableCellFormat wcf = new WritableCellFormat();
		 * 
		 * wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		 * 
		 * wcf.setAlignment(Alignment.CENTRE);
		 * 
		 * wcf.setWrap(true);
		 * 
		 * TbFixEntrust tbFixEntrust = this.findById(tbFixEntrustId);
		 * 
		 * TbCustomer tbCustomer =
		 * tbCustomerService.findById(tbFixEntrust.getTbCustomer().getId());
		 * 
		 * TbCarInfo tbCarInfo =
		 * tbCarInfoService.findById(tbFixEntrust.getTbCarInfo().getId());
		 * 
		 * Label label = null;
		 * 
		 * label = new Label(0, 10, tbFixEntrust.getEntrustCode(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(8, 10, tbCarInfo.getPurchaseDate()== null ? "" :
		 * CommonMethod.parseDateToString(tbCarInfo.getPurchaseDate(),
		 * "yyyy-MM-dd"), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(14, 10, tbFixEntrust.getTmUser().getUserRealName(),
		 * wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(20, 10, tbCarInfo.getLicenseCode(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(27, 10,
		 * tbCarInfo.getTmCarModelType().getModelName(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(32, 10, tbFixEntrust.getEnterStationKilo() == null
		 * ? "" : tbFixEntrust.getEnterStationKilo().toString(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(41, 10, tbCarInfo.getColor() == null ? "" :
		 * tbCarInfo.getColor().toString(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(45, 10, tbFixEntrust.getFixDate() == null ? "" :
		 * CommonMethod.parseDateToString(tbFixEntrust.getFixDate(),
		 * "yyyy-MM-dd HH:mm:ss"), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(0, 15, tbCustomer.getCustomerName() == null ? "" :
		 * tbCustomer.getCustomerName(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(8, 15, tbCustomer.getTelephone() == null ? "" :
		 * tbCustomer.getTelephone(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(14, 15, tbCustomer.getPhone() == null ? "" :
		 * tbCustomer.getPhone(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(20, 15, tbCarInfo.getChassisCode() == null ? "" :
		 * tbCarInfo.getChassisCode(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(32, 15, tbCarInfo.getEngineCode() == null ? "" :
		 * tbCarInfo.getEngineCode(),wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(45, 15, tbFixEntrust.getEstimateDate() == null ? ""
		 * : CommonMethod.parseDateToString(tbFixEntrust.getEstimateDate(),
		 * "yyyy-MM-dd HH:mm:ss"),wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(8, 18, tbCustomer.getCustomerName() == null ? "" :
		 * tbCustomer.getCustomerName(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(0, 22, tbCustomer.getAddress() == null ? "" :
		 * tbCustomer.getAddress(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(8, 25, tbCustomer.getZipCode() == null ? "" :
		 * tbCustomer.getZipCode(), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * label = new Label(20, 20, (tbFixEntrust.getWrongDescribe() ==
		 * null?"":tbFixEntrust.getWrongDescribe()) +";" +
		 * (tbFixEntrust.getBeforeFixState
		 * ()==null?"":tbFixEntrust.getBeforeFixState()) + ";" +
		 * (tbFixEntrust.getCheckResult
		 * ()==null?"":tbFixEntrust.getCheckResult()) + ";" +
		 * (tbFixEntrust.getRemark()==null?"":tbFixEntrust.getRemark()), wcf);
		 * 
		 * ws.addCell(label);
		 * 
		 * wwb.write();
		 * 
		 * } catch (BiffException e) {
		 * 
		 * e.printStackTrace(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); } catch (RowsExceededException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (WriteException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } finally {
		 * 
		 * if (null != wb) {
		 * 
		 * wb.close();
		 * 
		 * }
		 * 
		 * if (null != wwb) {
		 * 
		 * try { wwb.close(); } catch (WriteException e) {
		 * 
		 * e.printStackTrace(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); } }
		 * 
		 * }
		 */

		try {

			TbFixEntrust tbFixEntrust = this.findById(tbFixEntrustId);

			TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
					.getTbCustomer().getId());

			TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
					.getTbCarInfo().getId());

			List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
					.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId);

			/*
			 * List<TbMaintainPartContent> maintainList =
			 * tbMaintainPartContentService
			 * .getViewEntrustMaintianContent(tbFixEntrustId);
			 * 
			 * 
			 * List<TmStockOutDetVo> solePartList =
			 * tmStockOutService.getSellByEntrustCode
			 * (tbFixEntrust.getEntrustCode());
			 */

			/**
			 * 结算单中维修发料
			 */
			List<TbMaintianVo> maintianvos = tbMaintainPartContentService
					.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
							Constants.BALANCE_ALL);

			/**
			 * 结算单中维修销售
			 */
			List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
					.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
							Constants.BALANCE_ALL);

			/**
			 * 将配件销售汇总到维修发料中
			 */

			List<TbMaintianVo> partAll = new ArrayList<TbMaintianVo>();

			if (null != maintianvos && maintianvos.size() > 0) {

				for (TbMaintianVo tbMaintianVo : maintianvos) {

					partAll.add(tbMaintianVo);

				}
			}

			if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {

				for (TmStockOutDetVo tmStockOutDetVo : tmStockOutDetVos) {

					TbMaintianVo tbMaintianVo = new TbMaintianVo();

					tbMaintianVo.setPartId(tmStockOutDetVo.getPartinfoId());

					tbMaintianVo.setHouseName(tmStockOutDetVo.getHouseName());

					tbMaintianVo.setPartCode(tmStockOutDetVo.getPartCode());

					tbMaintianVo.setPartName(tmStockOutDetVo.getPartName());

					tbMaintianVo.setUnitName(tmStockOutDetVo.getUnitName());

					tbMaintianVo.setPrice(tmStockOutDetVo.getPrice());

					tbMaintianVo.setPartQuantity(tmStockOutDetVo.getQuantity());

					tbMaintianVo.setTotal(tmStockOutDetVo.getTotal());

					tbMaintianVo.setIsFree(tmStockOutDetVo.getIsFree());

					tbMaintianVo.setProjectType(tmStockOutDetVo
							.getProjectType());

					tbMaintianVo.setZl(tmStockOutDetVo.getZl());

					tbMaintianVo.setXmlx(tmStockOutDetVo.getXmlx());

					partAll.add(tbMaintianVo);
				}
			}

			int fixSize = (tbFixEntrustContentList == null ? 0
					: tbFixEntrustContentList.size());

			int maintainSize = (maintianvos == null ? 0 : maintianvos.size());

			int solePartSize = (tmStockOutDetVos == null ? 0 : tmStockOutDetVos
					.size());

			int partAllSize = (partAll == null ? 0 : partAll.size());

			HSSFWorkbook workbook = new HSSFWorkbook(this.getClass()
					.getResourceAsStream(tpl));

			HSSFSheet sheet = workbook.getSheetAt(0);

			sheet.setMargin(HSSFSheet.LeftMargin, (double) 0.5);

			sheet.setMargin(HSSFSheet.RightMargin, (double) 0.5);

			HSSFCellStyle style = workbook.createCellStyle();

			style.setWrapText(true);

			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font = workbook.createFont();

			font.setFontName("宋体");

			font.setFontHeightInPoints((short) 9);

			style.setFont(font);

			HSSFRow row = null;

			HSSFCell cell = null;

			ByteArrayOutputStream byteArrayOutImgLion = new ByteArrayOutputStream();

			ByteArrayOutputStream byteArrayOutDBZF = new ByteArrayOutputStream();

			BufferedImage bufferImgLion = ImageIO.read(this.getClass()
					.getResourceAsStream("/lion.png"));

			BufferedImage bufferImgDFBZ = ImageIO.read(this.getClass()
					.getResourceAsStream("/dfbz.png"));

			ImageIO.write(bufferImgLion, "png", byteArrayOutImgLion);

			ImageIO.write(bufferImgDFBZ, "png", byteArrayOutDBZF);

			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

			HSSFClientAnchor anchorLion = new HSSFClientAnchor(0, 0, 1023, 200,
					(short) 48, 0, (short) 53, 7);

			HSSFClientAnchor anchorDBZF = new HSSFClientAnchor(0, 0, 1023, 200,
					(short) 0, 0, (short) 8, 7);

			patriarch.createPicture(anchorLion, workbook.addPicture(
					byteArrayOutImgLion.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_PNG));

			patriarch.createPicture(anchorDBZF, workbook.addPicture(
					byteArrayOutDBZF.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_PNG));

			row = sheet.getRow(10);

			cell = row.getCell(0);

			String entrustCode = tbFixEntrust.getEntrustCode();

			String[] es = entrustCode.split("-");

			String newCode = "RO" + es[0].substring(2, 6) + es[1];

			// cell.setCellValue(tbFixEntrust.getEntrustCode());

			cell.setCellValue(newCode);

			row = sheet.getRow(10);

			cell = row.getCell(8);

			cell.setCellValue(tbCarInfo.getPurchaseDate() == null ? ""
					: CommonMethod.parseDateToString(
							tbCarInfo.getPurchaseDate(), "yyyy-MM-dd"));

			row = sheet.getRow(10);

			cell = row.getCell(14);

			cell.setCellValue(tbFixEntrust.getTmUser().getUserRealName() == null ? tbFixEntrust
					.getTmUser().getUserName() : tbFixEntrust.getTmUser()
					.getUserRealName());

			row = sheet.getRow(10);

			cell = row.getCell(19);

			cell.setCellValue(tbCarInfo.getLicenseCode());

			row = sheet.getRow(10);

			cell = row.getCell(27);

			cell.setCellStyle(style);

			cell.setCellValue(tbCarInfo.getTmCarModelType().getModelName());

			row = sheet.getRow(10);

			cell = row.getCell(32);

			cell.setCellValue(tbFixEntrust.getEnterStationKilo() == null ? ""
					: new BigDecimal(tbFixEntrust.getEnterStationKilo()
							.toString()).divide(new BigDecimal("1.00"), 0,
							BigDecimal.ROUND_HALF_UP).toString()
							+ "   Km");

			row = sheet.getRow(10);

			cell = row.getCell(41);

			cell.setCellValue(tbCarInfo.getColor() == null ? "" : tbCarInfo
					.getColor().toString());

			row = sheet.getRow(10);

			cell = row.getCell(45);

			cell.setCellValue(tbFixEntrust.getFixDate() == null ? ""
					: CommonMethod.parseDateToString(tbFixEntrust.getFixDate(),
							Constants.TIMEFORMATOFMINUTE));

			row = sheet.getRow(15);

			cell = row.getCell(0);

			cell.setCellValue(tbCustomer.getCustomerName() == null ? ""
					: tbCustomer.getContractPerson());

			row = sheet.getRow(15);

			cell = row.getCell(8);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(15);

			cell = row.getCell(14);

			cell.setCellValue(tbCustomer.getPhone() == null ? "" : tbCustomer
					.getPhone());

			row = sheet.getRow(15);

			cell = row.getCell(20);

			cell.setCellValue(tbCarInfo.getChassisCode() == null ? ""
					: tbCarInfo.getChassisCode());

			row = sheet.getRow(15);

			cell = row.getCell(32);

			// cell.setCellStyle(style);

			cell.setCellValue(tbCarInfo.getEngineCode() == null ? ""
					: tbCarInfo.getEngineCode());

			row = sheet.getRow(15);

			cell = row.getCell(45);

			// cell.setCellStyle(style);

			cell.setCellValue(tbFixEntrust.getEstimateDate() == null ? ""
					: CommonMethod.parseDateToString(
							tbFixEntrust.getEstimateDate(),
							Constants.TIMEFORMATOFMINUTE));

			row = sheet.getRow(18);

			cell = row.getCell(8);

			cell.setCellValue(tbCustomer.getCustomerName() == null ? ""
					: tbCustomer.getCustomerName());

			row = sheet.getRow(20);

			cell = row.getCell(8);

			cell.setCellStyle(style);

			cell.setCellValue(tbCustomer.getAddress() == null ? "" : tbCustomer
					.getAddress());

			row = sheet.getRow(25);

			cell = row.getCell(8);

			cell.setCellValue(tbCustomer.getZipCode() == null ? "" : tbCustomer
					.getZipCode());

			row = sheet.getRow(20);

			cell = row.getCell(23);

			cell.setCellStyle(style);

			cell.setCellValue((tbFixEntrust.getWrongDescribe() == null
					|| "".equals(tbFixEntrust.getWrongDescribe()) ? ""
					: tbFixEntrust.getWrongDescribe() + ";")
					+ (tbFixEntrust.getBeforeFixState() == null
							|| "".equals(tbFixEntrust.getBeforeFixState()) ? ""
							: tbFixEntrust.getBeforeFixState() + ";"));

			Double fixCount = tbFixEntrustContentService
					.countTbFixEnTrustContentByTbFixEntrustId(tbFixEntrustId);

			row = sheet.getRow(70);

			cell = row.getCell(32);

			cell.setCellStyle(style);

			cell.setCellValue(new BigDecimal(fixCount).divide(
					new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(70);

			cell = row.getCell(48);

			cell.setCellStyle(style);

			cell.setCellValue("0.00");

			BigDecimal partPriceAll = new BigDecimal("0.00");

			if (partAllSize > 0) {

				for (int i = 0; i < partAllSize; i++) {

					TbMaintianVo tbMaintianVo = partAll.get(i);

					BigDecimal total = new BigDecimal(tbMaintianVo.getPrice())
							.multiply(new BigDecimal(tbMaintianVo
									.getPartQuantity()));

					partPriceAll = partPriceAll.add(total);

				}

			}

			row = sheet.getRow(73);

			cell = row.getCell(33);

			cell.setCellStyle(style);

			cell.setCellValue(partPriceAll.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			BigDecimal total = new BigDecimal(fixCount)
					.add(partPriceAll)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP);

			row = sheet.getRow(76);

			cell = row.getCell(34);

			cell.setCellStyle(style);

			cell.setCellValue(total.toString());

			int page = 1;

			if (fixSize / 5 >= (maintainSize + solePartSize) / 13) {

				page = fixSize / 5;

			}

			else {

				page = (maintainSize + solePartSize) / 13;

			}

			for (int i = 0; i < page; i++) {

				int p = 0;

				int k = 0;

				HSSFSheet sheetClone = workbook.cloneSheet(0);

				byteArrayOutImgLion = new ByteArrayOutputStream();

				byteArrayOutDBZF = new ByteArrayOutputStream();

				bufferImgLion = ImageIO.read(this.getClass()
						.getResourceAsStream("/lion.png"));

				bufferImgDFBZ = ImageIO.read(this.getClass()
						.getResourceAsStream("/dfbz.png"));

				ImageIO.write(bufferImgLion, "png", byteArrayOutImgLion);

				ImageIO.write(bufferImgDFBZ, "png", byteArrayOutDBZF);

				patriarch = sheetClone.createDrawingPatriarch();

				anchorLion = new HSSFClientAnchor(0, 0, 1023, 200, (short) 48,
						0, (short) 53, 7);

				anchorDBZF = new HSSFClientAnchor(0, 0, 1023, 200, (short) 0,
						0, (short) 8, 7);

				patriarch.createPicture(anchorLion, workbook.addPicture(
						byteArrayOutImgLion.toByteArray(),
						HSSFWorkbook.PICTURE_TYPE_PNG));

				patriarch.createPicture(anchorDBZF, workbook.addPicture(
						byteArrayOutDBZF.toByteArray(),
						HSSFWorkbook.PICTURE_TYPE_PNG));

				if (fixSize > 5) {

					int printFixSize = (fixSize > (i + 2) * 5 ? (i + 2) * 5
							: fixSize);

					for (int j = 5 * (i + 1); j < printFixSize; j++) {

						TbFixEntrustContent content = tbFixEntrustContentList
								.get(j);

						List<TbFixShare> tbFixShareList = tbFixShareService
								.findTbFixShareListByTbFixEntrustContentId(content
										.getId());

						String fixPersons = "";

						if (null != tbFixShareList && tbFixShareList.size() > 0) {

							for (TbFixShare tbFixShare : tbFixShareList) {

								if (null != tbFixShare.getTmUser()) {

									TmUser tmUser = tmUserService
											.findById(tbFixShare.getTmUser()
													.getId());

									fixPersons += (tmUser.getUserRealName() == null
											|| "".equals(tmUser
													.getUserRealName()) ? tmUser
											.getUserName() : tmUser
											.getUserRealName())
											+ " ";
								}

							}
						}

						row = sheetClone.getRow(31 + p * 6);

						cell = row.getCell(0);

						cell.setCellStyle(style);

						cell.setCellValue(content.getStationCode() + "    "
								+ content.getStationName());

						cell = row.getCell(20);

						cell.setCellStyle(style);

						cell.setCellValue(content.getXmlx() == null ? ""
								: content.getXmlx());

						cell = row.getCell(26);

						cell.setCellStyle(style);

						cell.setCellValue(fixPersons);

						p++;

					}

				}

				if (partAllSize > 13) {

					int prinPartSize = (partAllSize > (i + 2) * 13 ? (i + 2) * 13
							: partAllSize);

					for (int j = 13 * (i + 1); j < prinPartSize; j++) {

						TbMaintianVo t = partAll.get(j);

						row = sheetClone.getRow(31 + k * 3);

						cell = row.getCell(32);

						cell.setCellStyle(style);

						cell.setCellValue(t.getPartName());

						cell = row.getCell(40);

						cell.setCellStyle(style);

						cell.setCellValue(t.getPartQuantity());

						cell = row.getCell(44);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(t.getPrice()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						k++;

					}
				}

			}

			if (fixSize > 0) {

				int printFixSize = (fixSize > 5 ? 5 : fixSize);

				for (int j = 0; j < printFixSize; j++) {

					TbFixEntrustContent content = tbFixEntrustContentList
							.get(j);

					List<TbFixShare> tbFixShareList = tbFixShareService
							.findTbFixShareListByTbFixEntrustContentId(content
									.getId());

					String fixPersons = "";

					if (null != tbFixShareList && tbFixShareList.size() > 0) {

						for (TbFixShare tbFixShare : tbFixShareList) {

							if (null != tbFixShare.getTmUser()) {

								TmUser tmUser = tmUserService
										.findById(tbFixShare.getTmUser()
												.getId());

								fixPersons += (tmUser.getUserRealName() == null
										|| "".equals(tmUser.getUserRealName()) ? tmUser
										.getUserName() : tmUser
										.getUserRealName())
										+ " ";
							}

						}
					}

					row = sheet.getRow(31 + j * 6);

					cell = row.getCell(0);

					cell.setCellStyle(style);

					cell.setCellValue(content.getStationCode() + "    "
							+ content.getStationName());

					cell = row.getCell(20);

					cell.setCellStyle(style);

					cell.setCellValue(content.getXmlx() == null ? "" : content
							.getXmlx());

					cell = row.getCell(26);

					cell.setCellStyle(style);

					cell.setCellValue(fixPersons);

				}

			}

			if (partAllSize > 0) {

				int printPartSize = (partAllSize > 13 ? 13 : partAllSize);

				for (int j = 0; j < printPartSize; j++) {

					TbMaintianVo t = partAll.get(j);

					row = sheet.getRow(31 + j * 3);

					cell = row.getCell(32);

					cell.setCellStyle(style);

					cell.setCellValue(t.getPartName());

					cell = row.getCell(40);

					cell.setCellStyle(style);

					cell.setCellValue(t.getPartQuantity());

					cell = row.getCell(44);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(t.getPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

				}

			}

			workbook.write(os);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printTbFixEntrustTemplateBlank(OutputStream os, String tpl,
			Long tbFixEntrustId) {

		try {

			TbFixEntrust tbFixEntrust = this.findById(tbFixEntrustId);

			TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
					.getTbCustomer().getId());

			TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
					.getTbCarInfo().getId());

			List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
					.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId);

			/*
			 * List<TbMaintainPartContent> maintainList =
			 * tbMaintainPartContentService
			 * .getViewEntrustMaintianContent(tbFixEntrustId);
			 * 
			 * 
			 * List<TmStockOutDetVo> solePartList =
			 * tmStockOutService.getSellByEntrustCode
			 * (tbFixEntrust.getEntrustCode());
			 */

			/**
			 * 结算单中维修发料
			 */
			List<TbMaintianVo> maintianvos = tbMaintainPartContentService
					.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
							Constants.BALANCE_ALL);

			/**
			 * 结算单中维修销售
			 */
			List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
					.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
							Constants.BALANCE_ALL);

			/**
			 * 将配件销售汇总到维修发料中
			 */

			List<TbMaintianVo> partAll = new ArrayList<TbMaintianVo>();

			if (null != maintianvos && maintianvos.size() > 0) {

				for (TbMaintianVo tbMaintianVo : maintianvos) {

					partAll.add(tbMaintianVo);

				}
			}

			if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {

				for (TmStockOutDetVo tmStockOutDetVo : tmStockOutDetVos) {

					TbMaintianVo tbMaintianVo = new TbMaintianVo();

					tbMaintianVo.setPartId(tmStockOutDetVo.getPartinfoId());

					tbMaintianVo.setHouseName(tmStockOutDetVo.getHouseName());

					tbMaintianVo.setPartCode(tmStockOutDetVo.getPartCode());

					tbMaintianVo.setPartName(tmStockOutDetVo.getPartName());

					tbMaintianVo.setUnitName(tmStockOutDetVo.getUnitName());

					tbMaintianVo.setPrice(tmStockOutDetVo.getPrice());

					tbMaintianVo.setPartQuantity(tmStockOutDetVo.getQuantity());

					tbMaintianVo.setTotal(tmStockOutDetVo.getTotal());

					tbMaintianVo.setIsFree(tmStockOutDetVo.getIsFree());

					tbMaintianVo.setProjectType(tmStockOutDetVo
							.getProjectType());

					tbMaintianVo.setZl(tmStockOutDetVo.getZl());

					tbMaintianVo.setXmlx(tmStockOutDetVo.getXmlx());

					partAll.add(tbMaintianVo);
				}
			}

			int fixSize = (tbFixEntrustContentList == null ? 0
					: tbFixEntrustContentList.size());

			int maintainSize = (maintianvos == null ? 0 : maintianvos.size());

			int solePartSize = (tmStockOutDetVos == null ? 0 : tmStockOutDetVos
					.size());

			int partAllSize = (partAll == null ? 0 : partAll.size());

			HSSFWorkbook workbook = new HSSFWorkbook(this.getClass()
					.getResourceAsStream(tpl));

			HSSFSheet sheet = workbook.getSheetAt(0);

			HSSFCellStyle style = workbook.createCellStyle();

			style.setWrapText(true);

			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font = workbook.createFont();

			font.setFontName("宋体");

			font.setFontHeightInPoints((short) 9);

			// style.setFont(font);

			HSSFCellStyle style11 = workbook.createCellStyle();

			style11.setWrapText(true);

			style11.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style11.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font11 = workbook.createFont();

			font11.setFontName("宋体");

			font11.setFontHeightInPoints((short) 11);

			// style11.setFont(font11);

			HSSFCellStyle style10 = workbook.createCellStyle();

			style10.setWrapText(true);

			style10.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style10.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font10 = workbook.createFont();

			font10.setFontName("宋体");

			font10.setFontHeightInPoints((short) 10);

			// style10.setFont(font10);

			HSSFRow row = null;

			HSSFCell cell = null;

			row = sheet.getRow(10);

			cell = row.getCell(0);

			String entrustCode = tbFixEntrust.getEntrustCode();

			String[] es = entrustCode.split("-");

			String newCode = "RO" + es[0].substring(2, 6) + es[1];

			// cell.setCellValue(tbFixEntrust.getEntrustCode());

			cell.setCellValue(newCode);

			row = sheet.getRow(10);

			cell = row.getCell(8);

			cell.setCellValue(tbCarInfo.getPurchaseDate() == null ? ""
					: CommonMethod.parseDateToString(
							tbCarInfo.getPurchaseDate(), "yyyy-MM-dd"));

			row = sheet.getRow(10);

			cell = row.getCell(14);

			cell.setCellValue(tbFixEntrust.getTmUser().getUserRealName() == null ? tbFixEntrust
					.getTmUser().getUserName() : tbFixEntrust.getTmUser()
					.getUserRealName());

			row = sheet.getRow(10);

			cell = row.getCell(19);

			cell.setCellValue(tbCarInfo.getLicenseCode());

			row = sheet.getRow(10);

			cell = row.getCell(27);

			// cell.setCellStyle(style);

			cell.setCellValue(tbCarInfo.getTmCarModelType().getModelName());

			row = sheet.getRow(10);

			cell = row.getCell(32);

			cell.setCellValue(tbFixEntrust.getEnterStationKilo() == null ? ""
					: new BigDecimal(tbFixEntrust.getEnterStationKilo()
							.toString()).divide(new BigDecimal("1.00"), 0,
							BigDecimal.ROUND_HALF_UP).toString()
							+ "   Km");

			row = sheet.getRow(10);

			cell = row.getCell(41);

			cell.setCellValue(tbCarInfo.getColor() == null ? "" : tbCarInfo
					.getColor().toString());

			row = sheet.getRow(10);

			cell = row.getCell(45);

			cell.setCellValue(tbFixEntrust.getFixDate() == null ? ""
					: CommonMethod.parseDateToString(tbFixEntrust.getFixDate(),
							Constants.TIMEFORMATOFMINUTE));

			row = sheet.getRow(15);

			cell = row.getCell(0);

			cell.setCellValue(tbCustomer.getCustomerName() == null ? ""
					: tbCustomer.getContractPerson());

			row = sheet.getRow(15);

			cell = row.getCell(8);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(15);

			cell = row.getCell(14);

			cell.setCellValue(tbCustomer.getPhone() == null ? "" : tbCustomer
					.getPhone());

			row = sheet.getRow(15);

			cell = row.getCell(20);

			cell.setCellValue(tbCarInfo.getChassisCode() == null ? ""
					: tbCarInfo.getChassisCode());

			row = sheet.getRow(15);

			cell = row.getCell(32);

			// cell.setCellStyle(style);

			cell.setCellValue(tbCarInfo.getEngineCode() == null ? ""
					: tbCarInfo.getEngineCode());

			row = sheet.getRow(15);

			cell = row.getCell(45);

			// cell.setCellStyle(style);

			cell.setCellValue(tbFixEntrust.getEstimateDate() == null ? ""
					: CommonMethod.parseDateToString(
							tbFixEntrust.getEstimateDate(),
							Constants.TIMEFORMATOFMINUTE));

			row = sheet.getRow(18);

			cell = row.getCell(8);

			cell.setCellValue(tbCustomer.getCustomerName() == null ? ""
					: tbCustomer.getCustomerName());

			row = sheet.getRow(20);

			cell = row.getCell(8);

			// cell.setCellStyle(style);

			cell.setCellValue(tbCustomer.getAddress() == null ? "" : tbCustomer
					.getAddress());

			row = sheet.getRow(25);

			cell = row.getCell(8);

			cell.setCellValue(tbCustomer.getZipCode() == null ? "" : tbCustomer
					.getZipCode());

			row = sheet.getRow(20);

			cell = row.getCell(23);

			// cell.setCellStyle(style);

			cell.setCellValue((tbFixEntrust.getWrongDescribe() == null
					|| "".equals(tbFixEntrust.getWrongDescribe()) ? ""
					: tbFixEntrust.getWrongDescribe() + ";")
					+ (tbFixEntrust.getBeforeFixState() == null
							|| "".equals(tbFixEntrust.getBeforeFixState()) ? ""
							: tbFixEntrust.getBeforeFixState() + ";"));

			Double fixCount = tbFixEntrustContentService
					.countTbFixEnTrustContentByTbFixEntrustId(tbFixEntrustId);

			row = sheet.getRow(70);

			cell = row.getCell(32);

			// cell.setCellStyle(style);

			cell.setCellValue(new BigDecimal(fixCount).divide(
					new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(70);

			cell = row.getCell(48);

			// cell.setCellStyle(style);

			cell.setCellValue("0.00");

			BigDecimal partPriceAll = new BigDecimal("0.00");

			if (partAllSize > 0) {

				for (int i = 0; i < partAllSize; i++) {

					TbMaintianVo tbMaintianVo = partAll.get(i);

					BigDecimal total = new BigDecimal(tbMaintianVo.getPrice())
							.multiply(new BigDecimal(tbMaintianVo
									.getPartQuantity()));

					partPriceAll = partPriceAll.add(total);

				}

			}

			row = sheet.getRow(73);

			cell = row.getCell(34);

			// cell.setCellStyle(style);

			cell.setCellValue(partPriceAll.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			BigDecimal total = new BigDecimal(fixCount)
					.add(partPriceAll)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP);

			row = sheet.getRow(76);

			cell = row.getCell(35);

			// cell.setCellStyle(style);

			cell.setCellValue(total.toString());

			int page = 1;

			if (fixSize / 5 >= (maintainSize + solePartSize) / 13) {

				page = fixSize / 5;

			}

			else {

				page = (maintainSize + solePartSize) / 13;

			}

			for (int i = 0; i < page; i++) {

				int p = 0;

				int k = 0;

				HSSFSheet sheetClone = workbook.cloneSheet(0);

				if (fixSize > 5) {

					int printFixSize = (fixSize > (i + 2) * 5 ? (i + 2) * 5
							: fixSize);

					for (int j = 5 * (i + 1); j < printFixSize; j++) {

						TbFixEntrustContent content = tbFixEntrustContentList
								.get(j);

						List<TbFixShare> tbFixShareList = tbFixShareService
								.findTbFixShareListByTbFixEntrustContentId(content
										.getId());

						String fixPersons = "";

						if (null != tbFixShareList && tbFixShareList.size() > 0) {

							for (TbFixShare tbFixShare : tbFixShareList) {

								if (null != tbFixShare.getTmUser()) {

									TmUser tmUser = tmUserService
											.findById(tbFixShare.getTmUser()
													.getId());

									fixPersons += (tmUser.getUserRealName() == null
											|| "".equals(tmUser
													.getUserRealName()) ? tmUser
											.getUserName() : tmUser
											.getUserRealName())
											+ " ";
								}

							}
						}

						row = sheetClone.getRow(31 + p * 6);

						cell = row.getCell(0);

						// cell.setCellStyle(style11);

						cell.setCellValue(content.getStationCode() + "    "
								+ content.getStationName());

						cell = row.getCell(20);

						// cell.setCellStyle(style11);

						cell.setCellValue(content.getWxlx() == null ? ""
								: content.getWxlx());

						cell = row.getCell(26);

						// cell.setCellStyle(style11);

						cell.setCellValue(fixPersons);

						p++;

					}

				}

				if (partAllSize > 13) {

					int prinPartSize = (partAllSize > (i + 2) * 13 ? (i + 2) * 13
							: partAllSize);

					for (int j = 13 * (i + 1); j < prinPartSize; j++) {

						TbMaintianVo t = partAll.get(j);

						row = sheetClone.getRow(31 + k * 3);

						cell = row.getCell(32);

						// cell.setCellStyle(style10);

						cell.setCellValue(t.getPartName());

						cell = row.getCell(40);

						// cell.setCellStyle(style10);

						cell.setCellValue(new BigDecimal(t.getPartQuantity())
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(44);

						// cell.setCellStyle(style10);

						cell.setCellValue(new BigDecimal(t.getPrice()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						k++;

					}
				}

			}

			if (fixSize > 0) {

				int printFixSize = (fixSize > 5 ? 5 : fixSize);

				for (int j = 0; j < printFixSize; j++) {

					TbFixEntrustContent content = tbFixEntrustContentList
							.get(j);

					List<TbFixShare> tbFixShareList = tbFixShareService
							.findTbFixShareListByTbFixEntrustContentId(content
									.getId());

					String fixPersons = "";

					if (null != tbFixShareList && tbFixShareList.size() > 0) {

						for (TbFixShare tbFixShare : tbFixShareList) {

							if (null != tbFixShare.getTmUser()) {

								TmUser tmUser = tmUserService
										.findById(tbFixShare.getTmUser()
												.getId());

								fixPersons += (tmUser.getUserRealName() == null
										|| "".equals(tmUser.getUserRealName()) ? tmUser
										.getUserName() : tmUser
										.getUserRealName())
										+ " ";
							}

						}
					}

					row = sheet.getRow(31 + j * 6);

					cell = row.getCell(0);

					// cell.setCellStyle(style11);

					cell.setCellValue(content.getStationCode() + "    "
							+ content.getStationName());

					cell = row.getCell(20);

					// cell.setCellStyle(style11);

					cell.setCellValue(content.getWxlx() == null ? "" : content
							.getWxlx());

					cell = row.getCell(26);

					// cell.setCellStyle(style11);

					cell.setCellValue(fixPersons);

				}

			}

			if (partAllSize > 0) {

				int printPartSize = (partAllSize > 13 ? 13 : partAllSize);

				for (int j = 0; j < printPartSize; j++) {

					TbMaintianVo t = partAll.get(j);

					row = sheet.getRow(31 + j * 3);

					cell = row.getCell(32);

					// cell.setCellStyle(style10);

					cell.setCellValue(t.getPartName());

					cell = row.getCell(40);

					// cell.setCellStyle(style10);

					cell.setCellValue(new BigDecimal(t.getPartQuantity())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(44);

					// cell.setCellStyle(style10);

					cell.setCellValue(new BigDecimal(t.getPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

				}

			}

			workbook.write(os);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printTbFixEntrustTemplateXTL(OutputStream os, String tpl,
			Long tbFixEntrustId) {

		try {

			TbFixEntrust tbFixEntrust = this.findById(tbFixEntrustId);

			TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
					.getTbCustomer().getId());

			TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
					.getTbCarInfo().getId());

			List<TbFixEntrustContent> tbFixEntrustContentList = this
					.composeContent(tbFixEntrustContentService
							.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId));

			// 公司信息
			TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

			/*
			 * List<TbMaintainPartContent> maintainList =
			 * tbMaintainPartContentService
			 * .getViewEntrustMaintianContent(tbFixEntrustId);
			 * 
			 * 
			 * List<TmStockOutDetVo> solePartList =
			 * tmStockOutService.getSellByEntrustCode
			 * (tbFixEntrust.getEntrustCode());
			 */

			/**
			 * 结算单中维修发料
			 */
			List<TbMaintianVo> maintianvos = tbMaintainPartContentService
					.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
							Constants.BALANCE_ALL);

			/**
			 * 结算单中维修销售
			 */
			List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
					.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
							Constants.BALANCE_ALL);

			/**
			 * 将配件销售汇总到维修发料中
			 */

			List<TbMaintianVo> partAll = new ArrayList<TbMaintianVo>();

			if (null != maintianvos && maintianvos.size() > 0) {

				for (TbMaintianVo tbMaintianVo : maintianvos) {

					partAll.add(tbMaintianVo);

				}
			}

			if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {

				for (TmStockOutDetVo tmStockOutDetVo : tmStockOutDetVos) {

					TbMaintianVo tbMaintianVo = new TbMaintianVo();

					tbMaintianVo.setPartId(tmStockOutDetVo.getPartinfoId());

					tbMaintianVo.setHouseName(tmStockOutDetVo.getHouseName());

					tbMaintianVo.setPartCode(tmStockOutDetVo.getPartCode());

					tbMaintianVo.setPartName(tmStockOutDetVo.getPartName());

					tbMaintianVo.setUnitName(tmStockOutDetVo.getUnitName());

					tbMaintianVo.setPrice(tmStockOutDetVo.getPrice());

					tbMaintianVo.setPartQuantity(tmStockOutDetVo.getQuantity());

					tbMaintianVo.setTotal(tmStockOutDetVo.getTotal());

					tbMaintianVo.setIsFree(tmStockOutDetVo.getIsFree());

					tbMaintianVo.setProjectType(tmStockOutDetVo
							.getProjectType());

					tbMaintianVo.setZl(tmStockOutDetVo.getZl());

					tbMaintianVo.setXmlx(tmStockOutDetVo.getXmlx());

					partAll.add(tbMaintianVo);
				}
			}

			partAll = this.composePart(partAll);

			int fixSize = (tbFixEntrustContentList == null ? 0
					: tbFixEntrustContentList.size());

			int maintainSize = (maintianvos == null ? 0 : maintianvos.size());

			int solePartSize = (tmStockOutDetVos == null ? 0 : tmStockOutDetVos
					.size());

			int partAllSize = (partAll == null ? 0 : partAll.size());

			HSSFWorkbook workbook = new HSSFWorkbook(this.getClass()
					.getResourceAsStream(tpl));

			HSSFSheet sheet = workbook.getSheetAt(0);

			HSSFCellStyle style = workbook.createCellStyle();

			style.setWrapText(true);

			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font = workbook.createFont();

			font.setFontName("宋体");

			font.setFontHeightInPoints((short) 9);

			style.setFont(font);

			HSSFRow row = null;

			HSSFCell cell = null;

			row = sheet.getRow(3);

			cell = row.getCell(0);

			cell.setCellValue("  名称:"
					+ "成都精典腾龙"
					+ " (网点编码:70691M)  地址:"
					+ tmCompany.getCompanyAddress()
					+ "  电话: "
					+ (tmCompany.getCompanyPhone() == null ? "" : tmCompany
							.getCompanyPhone()));

			row = sheet.getRow(0);

			cell = row.getCell(31);

			cell.setCellStyle(style);

			cell.setCellValue("*车主编号:" + tbCustomer.getCustomerCode() + "\n"
					+ "接车卡号:");

			row = sheet.getRow(6);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getCustomerName() == null ? ""
					: tbCustomer.getCustomerName());

			row = sheet.getRow(6);

			cell = row.getCell(24);

			cell.setCellStyle(style);

			cell.setCellValue(tbCustomer.getAddress() == null ? "" : tbCustomer
					.getAddress());

			row = sheet.getRow(6);

			cell = row.getCell(41);

			cell.setCellValue(tbCustomer.getContractPerson() == null ? ""
					: tbCustomer.getContractPerson());

			row = sheet.getRow(8);

			cell = row.getCell(41);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(8);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(12);

			cell = row.getCell(0);

			String entrustCode = tbFixEntrust.getEntrustCode();

			String[] es = entrustCode.split("-");

			String newCode = "RO" + es[0].substring(2, 6) + es[1];

			cell.setCellValue(/* tbFixEntrust.getEntrustCode() */newCode);

			row = sheet.getRow(12);

			cell = row.getCell(9);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbFixEntrust.getFixDate(), "yyyy-MM-dd HH:mm"));

			row = sheet.getRow(12);

			cell = row.getCell(18);

			cell.setCellValue(tbCarInfo.getLicenseCode());

			row = sheet.getRow(12);

			cell = row.getCell(26);

			cell.setCellStyle(style);

			cell.setCellValue(tbCarInfo.getTmCarModelType().getModelName());

			row = sheet.getRow(12);

			cell = row.getCell(34);

			cell.setCellValue(tbCarInfo.getChassisCode());

			// 备件组织号
			row = sheet.getRow(16);

			cell = row.getCell(0);

			cell.setCellValue("");

			row = sheet.getRow(16);

			cell = row.getCell(9);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbCarInfo.getPurchaseDate(), "yyyy-MM-dd"));

			// 首保日期
			row = sheet.getRow(16);

			cell = row.getCell(18);

			cell.setCellValue("");

			row = sheet.getRow(16);

			cell = row.getCell(26);

			cell.setCellValue(tbFixEntrust.getEnterStationKilo() == null ? ""
					: new BigDecimal(tbFixEntrust.getEnterStationKilo()
							.toString()).divide(new BigDecimal("1.00"), 0,
							BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(16);

			cell = row.getCell(34);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbFixEntrust.getEstimateDate(), "yyyy-MM-dd HH:mm"));

			Double fixCount = tbFixEntrustContentService
					.countTbFixEnTrustContentByTbFixEntrustId(tbFixEntrustId);

			row = sheet.getRow(75);

			cell = row.getCell(30);

			cell.setCellValue(new BigDecimal(fixCount).divide(
					new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			BigDecimal partPriceAll = new BigDecimal("0.00");

			if (partAllSize > 0) {

				for (int i = 0; i < partAllSize; i++) {

					TbMaintianVo tbMaintianVo = partAll.get(i);

					BigDecimal total = new BigDecimal(tbMaintianVo.getPrice())
							.multiply(new BigDecimal(tbMaintianVo
									.getPartQuantity()));

					partPriceAll = partPriceAll.add(total);

				}

			}

			row = sheet.getRow(77);

			cell = row.getCell(30);

			cell.setCellValue(partPriceAll.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			BigDecimal total = new BigDecimal(fixCount)
					.add(partPriceAll)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP);

			row = sheet.getRow(81);

			cell = row.getCell(30);

			cell.setCellValue(total.toString());

			row = sheet.getRow(79);

			cell = row.getCell(30);

			cell.setCellValue("0.00");

			ByteArrayOutputStream byteArrayOutImgLion = new ByteArrayOutputStream();

			ByteArrayOutputStream byteArrayOutDBZF = new ByteArrayOutputStream();

			BufferedImage bufferImgLion = ImageIO.read(this.getClass()
					.getResourceAsStream("/xtl.png"));

			BufferedImage bufferImgDFBZ = ImageIO.read(this.getClass()
					.getResourceAsStream("/xtl_logo.png"));

			ImageIO.write(bufferImgLion, "png", byteArrayOutImgLion);

			ImageIO.write(bufferImgDFBZ, "png", byteArrayOutDBZF);

			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

			HSSFClientAnchor anchorLion = new HSSFClientAnchor(0, 0, 1023, 200,
					(short) 40, 0, (short) 47, 2);

			HSSFClientAnchor anchorDBZF = new HSSFClientAnchor(0, 0, 1023, 100,
					(short) 0, 0, (short) 3, 3);

			patriarch.createPicture(anchorLion, workbook.addPicture(
					byteArrayOutImgLion.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_PNG));

			patriarch.createPicture(anchorDBZF, workbook.addPicture(
					byteArrayOutDBZF.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_PNG));

			int page = 1;

			if (fixSize / 8 >= (maintainSize + solePartSize) / 12) {

				page = fixSize / 8;

			}

			else {

				page = (maintainSize + solePartSize) / 12;

			}

			for (int i = 0; i < page; i++) {

				int p = 0;

				int k = 0;

				HSSFSheet sheetClone = workbook.cloneSheet(0);

				byteArrayOutImgLion = new ByteArrayOutputStream();

				byteArrayOutDBZF = new ByteArrayOutputStream();

				bufferImgLion = ImageIO.read(this.getClass()
						.getResourceAsStream("/xtl.png"));

				bufferImgDFBZ = ImageIO.read(this.getClass()
						.getResourceAsStream("/xtl_logo.png"));

				ImageIO.write(bufferImgLion, "png", byteArrayOutImgLion);

				ImageIO.write(bufferImgDFBZ, "png", byteArrayOutDBZF);

				patriarch = sheetClone.createDrawingPatriarch();

				anchorLion = new HSSFClientAnchor(0, 0, 1023, 200, (short) 40,
						0, (short) 47, 2);

				anchorDBZF = new HSSFClientAnchor(0, 0, 1023, 200, (short) 0,
						0, (short) 3, 3);

				patriarch.createPicture(anchorLion, workbook.addPicture(
						byteArrayOutImgLion.toByteArray(),
						HSSFWorkbook.PICTURE_TYPE_PNG));

				patriarch.createPicture(anchorDBZF, workbook.addPicture(
						byteArrayOutDBZF.toByteArray(),
						HSSFWorkbook.PICTURE_TYPE_PNG));

				if (fixSize > 8) {

					int printFixSize = (fixSize > (i + 2) * 8 ? (i + 2) * 8
							: fixSize);

					for (int j = 8 * (i + 1); j < printFixSize; j++) {

						TbFixEntrustContent content = tbFixEntrustContentList
								.get(j);

						List<TbFixShare> tbFixShareList = tbFixShareService
								.findTbFixShareListByTbFixEntrustContentId(content
										.getId());

						String fixPersons = "";

						String workTypes = "";

						if (null != tbFixShareList && tbFixShareList.size() > 0) {

							for (TbFixShare tbFixShare : tbFixShareList) {

								if (null != tbFixShare.getTmUser()) {

									TmUser tmUser = tmUserService
											.findById(tbFixShare.getTmUser()
													.getId());

									fixPersons += (tmUser.getUserRealName() == null
											|| "".equals(tmUser
													.getUserRealName()) ? tmUser
											.getUserName() : tmUser
											.getUserRealName())
											+ " ";

									workTypes += (tmUser.getTmWorkType() == null ? ""
											: tmUser.getTmWorkType()
													.getWorkName())
											+ " ";
								}

							}
						}

						row = sheetClone.getRow(23 + p * 2);

						cell = row.getCell(2);

						cell.setCellStyle(style);

						cell.setCellValue(content.getStationName());

						cell = row.getCell(20);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(content.getFixHour())
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(24);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(content
								.getWorkingHourPrice()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(28);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(content
								.getFixHourAll()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(34);

						cell.setCellStyle(style);

						cell.setCellValue(content.getProjectType() == null ? ""
								: content.getProjectType());

						cell = row.getCell(40);

						cell.setCellStyle(style);

						cell.setCellValue(workTypes);

						cell = row.getCell(45);

						cell.setCellStyle(style);

						cell.setCellValue(fixPersons);

						p++;

					}

				}

				if (partAllSize > 12) {

					int prinPartSize = (partAllSize > (i + 2) * 12 ? (i + 2) * 12
							: partAllSize);

					for (int j = 12 * (i + 1); j < prinPartSize; j++) {

						TbMaintianVo t = partAll.get(j);

						row = sheetClone.getRow(41 + k * 2);

						cell = row.getCell(2);

						cell.setCellStyle(style);

						cell.setCellValue(t.getPartCode());

						cell = row.getCell(11);

						cell.setCellStyle(style);

						cell.setCellValue(t.getPartName());

						cell = row.getCell(20);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(t.getPartQuantity())
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(24);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(t.getPrice()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(28);

						cell.setCellStyle(style);

						cell.setCellValue(new BigDecimal(t.getPartQuantity())
								.multiply(new BigDecimal(t.getPrice()))
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(34);

						cell.setCellStyle(style);

						cell.setCellValue(t.getProjectType() == null ? "" : t
								.getProjectType());

						// 备注
						cell = row.getCell(40);

						cell.setCellStyle(style);

						cell.setCellValue("");

						k++;

					}
				}

			}

			if (fixSize > 0) {

				int printFixSize = (fixSize > 8 ? 8 : fixSize);

				for (int j = 0; j < printFixSize; j++) {

					TbFixEntrustContent content = tbFixEntrustContentList
							.get(j);

					List<TbFixShare> tbFixShareList = tbFixShareService
							.findTbFixShareListByTbFixEntrustContentId(content
									.getId());

					String fixPersons = "";

					String workTypes = "";

					if (null != tbFixShareList && tbFixShareList.size() > 0) {

						for (TbFixShare tbFixShare : tbFixShareList) {

							if (null != tbFixShare.getTmUser()) {

								TmUser tmUser = tmUserService
										.findById(tbFixShare.getTmUser()
												.getId());

								fixPersons += (tmUser.getUserRealName() == null
										|| "".equals(tmUser.getUserRealName()) ? tmUser
										.getUserName() : tmUser
										.getUserRealName())
										+ " ";

								workTypes += (tmUser.getTmWorkType() == null ? ""
										: tmUser.getTmWorkType().getWorkName())
										+ " ";
							}

						}
					}

					row = sheet.getRow(23 + j * 2);

					cell = row.getCell(2);

					cell.setCellStyle(style);

					cell.setCellValue(content.getStationName());

					cell = row.getCell(20);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(content.getFixHour())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(24);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(content
							.getWorkingHourPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(28);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(content.getFixHourAll())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(34);

					cell.setCellStyle(style);

					cell.setCellValue(content.getProjectType() == null ? ""
							: content.getProjectType());

					cell = row.getCell(40);

					cell.setCellStyle(style);

					cell.setCellValue(workTypes);

					cell = row.getCell(45);

					cell.setCellStyle(style);

					cell.setCellValue(fixPersons);

				}

			}

			if (partAllSize > 0) {

				int printPartSize = (partAllSize > 12 ? 12 : partAllSize);

				for (int j = 0; j < printPartSize; j++) {

					TbMaintianVo t = partAll.get(j);

					row = sheet.getRow(41 + j * 2);

					cell = row.getCell(2);

					cell.setCellStyle(style);

					cell.setCellValue(t.getPartCode());

					cell = row.getCell(11);

					cell.setCellStyle(style);

					cell.setCellValue(t.getPartName());

					cell = row.getCell(20);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(t.getPartQuantity())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(24);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(t.getPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(28);

					cell.setCellStyle(style);

					cell.setCellValue(new BigDecimal(t.getPartQuantity())
							.multiply(new BigDecimal(t.getPrice()))
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(34);

					cell.setCellStyle(style);

					cell.setCellValue(t.getProjectType() == null ? "" : t
							.getProjectType());

					// 备注
					cell = row.getCell(40);

					cell.setCellStyle(style);

					cell.setCellValue("");

				}

			}

			workbook.write(os);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printTbFixEntrustTemplateBlankXTL(OutputStream os, String tpl,
			Long tbFixEntrustId) {

		try {

			TbFixEntrust tbFixEntrust = this.findById(tbFixEntrustId);

			TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
					.getTbCustomer().getId());

			TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
					.getTbCarInfo().getId());

			List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
					.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId);

			// 公司信息
			TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

			/*
			 * List<TbMaintainPartContent> maintainList =
			 * tbMaintainPartContentService
			 * .getViewEntrustMaintianContent(tbFixEntrustId);
			 * 
			 * 
			 * List<TmStockOutDetVo> solePartList =
			 * tmStockOutService.getSellByEntrustCode
			 * (tbFixEntrust.getEntrustCode());
			 */

			/**
			 * 结算单中维修发料
			 */
			List<TbMaintianVo> maintianvos = tbMaintainPartContentService
					.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
							Constants.BALANCE_ALL);

			/**
			 * 结算单中维修销售
			 */
			List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
					.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
							Constants.BALANCE_ALL);

			/**
			 * 将配件销售汇总到维修发料中
			 */

			List<TbMaintianVo> partAll = new ArrayList<TbMaintianVo>();

			if (null != maintianvos && maintianvos.size() > 0) {

				for (TbMaintianVo tbMaintianVo : maintianvos) {

					partAll.add(tbMaintianVo);

				}
			}

			if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {

				for (TmStockOutDetVo tmStockOutDetVo : tmStockOutDetVos) {

					TbMaintianVo tbMaintianVo = new TbMaintianVo();

					tbMaintianVo.setPartId(tmStockOutDetVo.getPartinfoId());

					tbMaintianVo.setHouseName(tmStockOutDetVo.getHouseName());

					tbMaintianVo.setPartCode(tmStockOutDetVo.getPartCode());

					tbMaintianVo.setPartName(tmStockOutDetVo.getPartName());

					tbMaintianVo.setUnitName(tmStockOutDetVo.getUnitName());

					tbMaintianVo.setPrice(tmStockOutDetVo.getPrice());

					tbMaintianVo.setPartQuantity(tmStockOutDetVo.getQuantity());

					tbMaintianVo.setTotal(tmStockOutDetVo.getTotal());

					tbMaintianVo.setIsFree(tmStockOutDetVo.getIsFree());

					tbMaintianVo.setProjectType(tmStockOutDetVo
							.getProjectType());

					tbMaintianVo.setZl(tmStockOutDetVo.getZl());

					tbMaintianVo.setXmlx(tmStockOutDetVo.getXmlx());

					partAll.add(tbMaintianVo);
				}
			}

			int fixSize = (tbFixEntrustContentList == null ? 0
					: tbFixEntrustContentList.size());

			int maintainSize = (maintianvos == null ? 0 : maintianvos.size());

			int solePartSize = (tmStockOutDetVos == null ? 0 : tmStockOutDetVos
					.size());

			int partAllSize = (partAll == null ? 0 : partAll.size());

			int pz = (fixSize / 8 > partAllSize / 12 ? (fixSize / 8 + 1)
					: (partAllSize / 12 + 1));

			HSSFWorkbook workbook = new HSSFWorkbook(this.getClass()
					.getResourceAsStream(tpl));

			HSSFSheet sheet = workbook.getSheetAt(0);

			HSSFCellStyle style = workbook.createCellStyle();

			style.setWrapText(true);

			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font = workbook.createFont();

			font.setFontName("宋体");

			font.setFontHeightInPoints((short) 9);

			style.setFont(font);

			HSSFCellStyle style10_5 = workbook.createCellStyle();

			style10_5.setWrapText(true);

			style10_5.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			style10_5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			HSSFFont font10_5 = workbook.createFont();

			font10_5.setFontName("宋体");

			font10_5.setFontHeightInPoints((short) 11);

			style10_5.setFont(font10_5);

			HSSFRow row = null;

			HSSFCell cell = null;

			row = sheet.getRow(3);

			cell = row.getCell(0);

			cell.setCellValue("  名称:"
					+ "成都精典腾龙"
					+ " (网点编码:70691M)  地址:"
					+ tmCompany.getCompanyAddress()
					+ "  电话: "
					+ (tmCompany.getCompanyPhone() == null ? "" : tmCompany
							.getCompanyPhone()));

			row = sheet.getRow(0);

			cell = row.getCell(40);

			// cell.setCellStyle(style);

			cell.setCellValue("*车主编号:" + tbCustomer.getCustomerCode() + "\n"
					+ "接车卡号:");

			row = sheet.getRow(6);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getCustomerName() == null ? ""
					: tbCustomer.getCustomerName());

			row = sheet.getRow(6);

			cell = row.getCell(24);

			// cell.setCellStyle(style);

			cell.setCellValue(tbCustomer.getAddress() == null ? "" : tbCustomer
					.getAddress());

			row = sheet.getRow(6);

			cell = row.getCell(41);

			cell.setCellValue(tbCustomer.getContractPerson() == null ? ""
					: tbCustomer.getContractPerson());

			row = sheet.getRow(8);

			cell = row.getCell(41);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(8);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(12);

			cell = row.getCell(0);

			String entrustCode = tbFixEntrust.getEntrustCode();

			String[] es = entrustCode.split("-");

			String newCode = "RO" + es[0].substring(2, 6) + es[1];

			cell.setCellValue(/* tbFixEntrust.getEntrustCode() */newCode);

			row = sheet.getRow(12);

			cell = row.getCell(9);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbFixEntrust.getFixDate(), "yyyy-MM-dd HH:mm"));

			row = sheet.getRow(12);

			cell = row.getCell(18);

			cell.setCellValue(tbCarInfo.getLicenseCode());

			row = sheet.getRow(12);

			cell = row.getCell(26);

			// cell.setCellStyle(style);

			cell.setCellValue(tbCarInfo.getTmCarModelType().getModelName());

			row = sheet.getRow(12);

			cell = row.getCell(34);

			cell.setCellValue(tbCarInfo.getChassisCode());

			// 备件组织号
			row = sheet.getRow(16);

			cell = row.getCell(0);

			cell.setCellValue(tbFixEntrust.getBjzzh() == null ? ""
					: tbFixEntrust.getBjzzh());

			row = sheet.getRow(16);

			cell = row.getCell(9);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbCarInfo.getPurchaseDate(), "yyyy-MM-dd"));

			// 首保日期
			row = sheet.getRow(16);

			cell = row.getCell(18);

			cell.setCellValue(tbFixEntrust.getSbrq() == null ? ""
					: tbFixEntrust.getSbrq());

			row = sheet.getRow(16);

			cell = row.getCell(26);

			cell.setCellValue(tbFixEntrust.getEnterStationKilo() == null ? ""
					: new BigDecimal(tbFixEntrust.getEnterStationKilo()
							.toString()).divide(new BigDecimal("1.00"), 0,
							BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(16);

			cell = row.getCell(34);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbFixEntrust.getEstimateDate(), "yyyy-MM-dd HH:mm"));

			Double fixCount = tbFixEntrustContentService
					.countTbFixEnTrustContentByTbFixEntrustId(tbFixEntrustId);

			row = sheet.getRow(75);

			cell = row.getCell(30);

			cell.setCellValue(new BigDecimal(fixCount).divide(
					new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			BigDecimal partPriceAll = new BigDecimal("0.00");

			if (partAllSize > 0) {

				for (int i = 0; i < partAllSize; i++) {

					TbMaintianVo tbMaintianVo = partAll.get(i);

					BigDecimal total = new BigDecimal(tbMaintianVo.getPrice())
							.multiply(new BigDecimal(tbMaintianVo
									.getPartQuantity()));

					partPriceAll = partPriceAll.add(total);

				}

			}

			row = sheet.getRow(77);

			cell = row.getCell(30);

			cell.setCellValue(partPriceAll.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			BigDecimal total = new BigDecimal(fixCount)
					.add(partPriceAll)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP);

			row = sheet.getRow(81);

			cell = row.getCell(30);

			cell.setCellValue(total.toString());

			row = sheet.getRow(79);

			cell = row.getCell(30);

			cell.setCellValue("0.00");

			row = sheet.getRow(89);

			cell = row.getCell(6);

			cell.setCellValue("1");

			row = sheet.getRow(89);

			cell = row.getCell(1);

			cell.setCellValue(pz + "");

			int page = 1;

			if (fixSize / 8 >= (maintainSize + solePartSize) / 12) {

				page = fixSize / 8;

			}

			else {

				page = (maintainSize + solePartSize) / 12;

			}

			for (int i = 0; i < page; i++) {

				int p = 0;

				int k = 0;

				HSSFSheet sheetClone = workbook.cloneSheet(0);

				row = sheetClone.getRow(89);

				cell = row.getCell(1);

				cell.setCellValue((i + 1) + "");

				row = sheetClone.getRow(89);

				cell = row.getCell(6);

				cell.setCellValue(pz + "");

				if (fixSize > 8) {

					int printFixSize = (fixSize > (i + 2) * 8 ? (i + 2) * 8
							: fixSize);

					for (int j = 8 * (i + 1); j < printFixSize; j++) {

						TbFixEntrustContent content = tbFixEntrustContentList
								.get(j);

						List<TbFixShare> tbFixShareList = tbFixShareService
								.findTbFixShareListByTbFixEntrustContentId(content
										.getId());

						String fixPersons = "";

						String workTypes = "";

						if (null != tbFixShareList && tbFixShareList.size() > 0) {

							for (TbFixShare tbFixShare : tbFixShareList) {

								if (null != tbFixShare.getTmUser()) {

									TmUser tmUser = tmUserService
											.findById(tbFixShare.getTmUser()
													.getId());

									fixPersons += (tmUser.getUserRealName() == null
											|| "".equals(tmUser
													.getUserRealName()) ? tmUser
											.getUserName() : tmUser
											.getUserRealName())
											+ " ";

									workTypes += (tmUser.getTmWorkType() == null ? ""
											: tmUser.getTmWorkType()
													.getWorkName())
											+ " ";
								}

							}
						}

						row = sheetClone.getRow(23 + p * 2);

						cell = row.getCell(2);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(content.getStationName());

						cell = row.getCell(20);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(new BigDecimal(content.getFixHour())
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(24);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(new BigDecimal(content
								.getWorkingHourPrice()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(28);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(new BigDecimal(content
								.getFixHourAll()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(34);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(content.getProjectType() == null ? ""
								: content.getProjectType());

						cell = row.getCell(40);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(workTypes);

						cell = row.getCell(45);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(fixPersons);

						p++;

					}

				}

				if (partAllSize > 12) {

					int prinPartSize = (partAllSize > (i + 2) * 12 ? (i + 2) * 12
							: partAllSize);

					for (int j = 12 * (i + 1); j < prinPartSize; j++) {

						TbMaintianVo t = partAll.get(j);

						row = sheetClone.getRow(41 + k * 2);

						cell = row.getCell(2);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(t.getPartCode());

						cell = row.getCell(11);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(t.getPartName());

						cell = row.getCell(20);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(new BigDecimal(t.getPartQuantity())
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(24);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(new BigDecimal(t.getPrice()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(28);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(new BigDecimal(t.getPartQuantity())
								.multiply(new BigDecimal(t.getPrice()))
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cell = row.getCell(34);

						// cell.setCellStyle(style10_5);

						cell.setCellValue(t.getProjectType() == null ? "" : t
								.getProjectType());

						// 备注
						cell = row.getCell(40);

						// cell.setCellStyle(style10_5);

						cell.setCellValue("");

						k++;

					}
				}

			}

			if (fixSize > 0) {

				int printFixSize = (fixSize > 8 ? 8 : fixSize);

				for (int j = 0; j < printFixSize; j++) {

					TbFixEntrustContent content = tbFixEntrustContentList
							.get(j);

					List<TbFixShare> tbFixShareList = tbFixShareService
							.findTbFixShareListByTbFixEntrustContentId(content
									.getId());

					String fixPersons = "";

					String workTypes = "";

					if (null != tbFixShareList && tbFixShareList.size() > 0) {

						for (TbFixShare tbFixShare : tbFixShareList) {

							if (null != tbFixShare.getTmUser()) {

								TmUser tmUser = tmUserService
										.findById(tbFixShare.getTmUser()
												.getId());

								fixPersons += (tmUser.getUserRealName() == null
										|| "".equals(tmUser.getUserRealName()) ? tmUser
										.getUserName() : tmUser
										.getUserRealName())
										+ " ";

								workTypes += (tmUser.getTmWorkType() == null ? ""
										: tmUser.getTmWorkType().getWorkName())
										+ " ";
							}

						}
					}

					row = sheet.getRow(23 + j * 2);

					cell = row.getCell(2);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(content.getStationName());

					cell = row.getCell(20);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(new BigDecimal(content.getFixHour())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(24);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(new BigDecimal(content
							.getWorkingHourPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(28);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(new BigDecimal(content.getFixHourAll())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(34);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(content.getProjectType() == null ? ""
							: content.getProjectType());

					cell = row.getCell(40);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(workTypes);

					cell = row.getCell(45);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(fixPersons);

				}

			}

			if (partAllSize > 0) {

				int printPartSize = (partAllSize > 12 ? 12 : partAllSize);

				for (int j = 0; j < printPartSize; j++) {

					TbMaintianVo t = partAll.get(j);

					row = sheet.getRow(41 + j * 2);

					cell = row.getCell(2);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(t.getPartCode());

					cell = row.getCell(11);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(t.getPartName());

					cell = row.getCell(20);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(new BigDecimal(t.getPartQuantity())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(24);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(new BigDecimal(t.getPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(28);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(new BigDecimal(t.getPartQuantity())
							.multiply(new BigDecimal(t.getPrice()))
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(34);

					// cell.setCellStyle(style10_5);

					cell.setCellValue(t.getProjectType() == null ? "" : t
							.getProjectType());

					// 备注
					cell = row.getCell(40);

					// cell.setCellStyle(style10_5);

					cell.setCellValue("");

				}

			}

			workbook.write(os);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<TbFixEntrustContent> composeContent(
			List<TbFixEntrustContent> tbFixEntrustContentList) {

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
									&& _tbFixEntrustContent
											.getTbWorkingInfo()
											.getId()
											.equals(tbFixEntrustContentList
													.get(i).getTbWorkingInfo()
													.getId())
									&& _tbFixEntrustContent.getFreesymbol()
											.equals(tbFixEntrustContentList
													.get(i).getFreesymbol())

							) {

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
							&& temp.getTbWorkingInfo()
									.getId()
									.equals(tbFixEntrustContentList.get(j)
											.getTbWorkingInfo().getId())
							&& temp.getFreesymbol().equals(
									tbFixEntrustContentList.get(j)
											.getFreesymbol())) {

						d = d.add(new BigDecimal(tbFixEntrustContentList.get(j)
								.getFixHourAll()));

						d2 = d2.add(new BigDecimal(tbFixEntrustContentList.get(
								j).getFixHour()));

					}

				}

				temp.setFixHourAll(d.doubleValue());

				temp.setFixHour(d2.doubleValue());

				if (!temp.getFreesymbol().equals(1d)
						|| !temp.getFixHourAll().equals(0d)) {

					tbFixEntrustContentListAdd.add(temp);

				}

			}

		}

		if (tbFixEntrustContentListAdd.size() == 0) {

			TbFixEntrustContent t = new TbFixEntrustContent();

			t.setStationName("");

			tbFixEntrustContentListAdd.add(t);

		}

		return tbFixEntrustContentListAdd;
	}

	private List<TbMaintianVo> composePart(List<TbMaintianVo> maintianvos) {

		List<TbMaintianVo> maintianvosTemp = new ArrayList<TbMaintianVo>();

		List<TbMaintianVo> maintianvosAdd = new ArrayList<TbMaintianVo>();

		if (maintianvos.size() > 0) {

			for (int i = 0; i < maintianvos.size(); i++) {

				boolean flag = false;

				if (maintianvosTemp.size() == 0) {

					maintianvosTemp.add(maintianvos.get(i));

				} else {

					if (maintianvosTemp.size() > 1) {

						int l = 0;

						for (TbMaintianVo _tbMaintianVo : maintianvosTemp) {

							if (_tbMaintianVo.getPartId().equals(
									maintianvos.get(i).getPartId())
									&& _tbMaintianVo.getIsFree().equals(
											maintianvos.get(i).getIsFree())) {

								// maintianvosTemp.set(l, maintianvos.get(i));

								flag = true;

								break;

							}

							l++;

						}

					}

				}

				if (flag) {

					continue;

				} else {

					maintianvosTemp.add(maintianvos.get(i));

				}

				TbMaintianVo temp = maintianvos.get(i);

				BigDecimal d1 = new BigDecimal(temp.getPartQuantity());

				BigDecimal d2 = new BigDecimal(temp.getTotal());

				for (int j = i + 1; j < maintianvos.size(); j++) {

					if (temp.getPartId().equals(maintianvos.get(j).getPartId())
							&& temp.getIsFree().equals(
									maintianvos.get(j).getIsFree())) {

						temp.setPrice(maintianvos.get(j).getPrice());

						d1 = d1.add(new BigDecimal(maintianvos.get(j)
								.getPartQuantity()));

						d2 = d2.add(new BigDecimal(maintianvos.get(j)
								.getTotal()));

					}

				}

				temp.setPartQuantity(d1.doubleValue());

				temp.setTotal(d2.doubleValue());

				if (!temp.getIsFree().equals(1D)
						|| !temp.getPartQuantity().equals(0d)) {

					/*
					 * temp.setPrice(new BigDecimal(temp.getTotal()).divide(new
					 * BigDecimal(temp.getPartQuantity()),2,
					 * BigDecimal.ROUND_HALF_UP).setScale(2,
					 * BigDecimal.ROUND_HALF_UP).doubleValue());
					 */

					maintianvosAdd.add(temp);

				}

			}

		}

		return maintianvosAdd;
	}

	// 委托书结算单打印
	public Map putEntrustBalanceReportParamMap(Long id,
			HttpServletRequest request) {

		Map map = new HashMap();
		// 委托书信息
		TbFixEntrust tbFixEntrust = this.findById(id);

		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
				.getTbCustomer().getId());
		// 车辆信息
		TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
				.getTbCarInfo().getId());
		// 公司信息
		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		// 修理工时列表
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrust
						.getId());

		/**
		 * add 2010-12-17 ccr
		 */
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
									&& _tbFixEntrustContent
											.getTbWorkingInfo()
											.getId()
											.equals(tbFixEntrustContentList
													.get(i).getTbWorkingInfo()
													.getId())
									&& _tbFixEntrustContent.getFreesymbol()
											.equals(tbFixEntrustContentList
													.get(i).getFreesymbol())

							) {

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

				TbFixEntrustContent tempCopy = new TbFixEntrustContent();

				BigDecimal d = new BigDecimal(temp.getFixHourAll());

				BigDecimal d2 = new BigDecimal(temp.getFixHour());

				for (int j = i + 1; j < tbFixEntrustContentList.size(); j++) {

					if (temp.getStationName().equals(
							tbFixEntrustContentList.get(j).getStationName())
							&& temp.getTbWorkingInfo()
									.getId()
									.equals(tbFixEntrustContentList.get(j)
											.getTbWorkingInfo().getId())
							&& temp.getFreesymbol().equals(
									tbFixEntrustContentList.get(j)
											.getFreesymbol())) {

						d = d.add(new BigDecimal(tbFixEntrustContentList.get(j)
								.getFixHourAll()));

						d2 = d2.add(new BigDecimal(tbFixEntrustContentList.get(
								j).getFixHour()));

					}

				}

				try {
					BeanUtils.copyProperties(tempCopy, temp);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				tempCopy.setFixHourAll(d.doubleValue());

				tempCopy.setFixHour(d2.doubleValue());

				if (!tempCopy.getFreesymbol().equals(1d)
						|| !tempCopy.getFixHourAll().equals(0d)) {

					tbFixEntrustContentListAdd.add(tempCopy);

				}

			}

		}

		if (tbFixEntrustContentListAdd.size() == 0) {

			TbFixEntrustContent t = new TbFixEntrustContent();

			t.setStationName("");

			tbFixEntrustContentListAdd.add(t);

		}

		// 发料配件列表
		/*
		 * List<TbMaintianVo> maintianvos = tbMaintainPartContentService
		 * .getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
		 * Constants.BALANCE_ALL);
		 */
		/* update by baijx 显示需要打印的明细列表 */
//		List<TbMaintianVo> maintianvos = tbMaintainPartContentService
//				.getTbMaintianDetailVosByEntrustIdPrint(tbFixEntrust.getId(),
//						Constants.BALANCE_ALL);
		List<TbMaintainPartContent> result = tbMaintainPartContentService.getViewEntrustMaintianContent(tbFixEntrust.getId());
		List<TbMaintianVo> maintianvos  = null;
		
		if(result != null && result.size()>0){
			maintianvos  = tbMaintainPartContentService.getTbMaintianDetailVos(result.get(0).getMaintainCode());
			
			
		}
		
		// 销售单列表
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
				.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
						Constants.BALANCE_ALL);
		// 结算项目列表
		// List<TbBusinessBalanceItem> tbBusinessBalanceItemList =
		// tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance.getId());
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = new ArrayList<TbBusinessBalanceItem>();
		// 发料配件与销售列表合并
		if (null == maintianvos) {
			maintianvos = new ArrayList<TbMaintianVo>();
		}

		if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {

			for (TmStockOutDetVo tmStockOutDetVo : tmStockOutDetVos) {

				TbMaintianVo tbMaintianVo = new TbMaintianVo();

				tbMaintianVo.setPartId(tmStockOutDetVo.getPartinfoId());

				tbMaintianVo.setHouseName(tmStockOutDetVo.getHouseName());

				tbMaintianVo.setPartCode(tmStockOutDetVo.getPartCode());

				tbMaintianVo.setPartName(tmStockOutDetVo.getPartName());

				tbMaintianVo.setUnitName(tmStockOutDetVo.getUnitName());

				tbMaintianVo.setPrice(tmStockOutDetVo.getPrice());

				tbMaintianVo.setPartQuantity(tmStockOutDetVo.getQuantity());

				tbMaintianVo.setTotal(tmStockOutDetVo.getTotal());

				tbMaintianVo.setIsFree(tmStockOutDetVo.getIsFree());

				maintianvos.add(tbMaintianVo);
			}

		}

		/**
		 * add by ccr 2010-12-18
		 */
		List<TbMaintianVo> maintianvosTemp = new ArrayList<TbMaintianVo>();

		List<TbMaintianVo> maintianvosAdd = new ArrayList<TbMaintianVo>();

		if (maintianvos.size() > 0) {

			for (int i = 0; i < maintianvos.size(); i++) {

				boolean flag = false;

				if (maintianvosTemp.size() == 0) {

					maintianvosTemp.add(maintianvos.get(i));

				} else {

					if (maintianvosTemp.size() > 1) {

						int l = 0;

						for (TbMaintianVo _tbMaintianVo : maintianvosTemp) {

							if (_tbMaintianVo.getPartId().equals(
									maintianvos.get(i).getPartId())
									&& _tbMaintianVo.getIsFree().equals(
											maintianvos.get(i).getIsFree())
									&& _tbMaintianVo.getPrice().equals(
											maintianvos.get(i).getPrice())) {

								// maintianvosTemp.set(l, maintianvos.get(i));

								flag = true;

								break;

							}

							l++;

						}

					}

				}

				if (flag) {

					continue;

				} else {

					maintianvosTemp.add(maintianvos.get(i));

				}

				TbMaintianVo temp = maintianvos.get(i);

				BigDecimal d1 = new BigDecimal(temp.getPartQuantity());

				BigDecimal d2 = new BigDecimal(temp.getTotal());

				for (int j = i + 1; j < maintianvos.size(); j++) {

					if (temp.getPartId().equals(maintianvos.get(j).getPartId())
							&& temp.getIsFree().equals(
									maintianvos.get(j).getIsFree())
							&& temp.getPrice().equals(
									maintianvos.get(j).getPrice())) {

						temp.setPrice(maintianvos.get(j).getPrice());

						d1 = d1.add(new BigDecimal(maintianvos.get(j)
								.getPartQuantity()));

						d2 = d2.add(new BigDecimal(maintianvos.get(j)
								.getTotal()));

					}

				}

				temp.setPartQuantity(d1.doubleValue());

				temp.setTotal(d2.doubleValue());

				if (!temp.getIsFree().equals(1L)
						|| !temp.getPartQuantity().equals(0d)) {

					/*
					 * temp.setPrice(new BigDecimal(temp.getTotal()).divide(new
					 * BigDecimal(temp.getPartQuantity()),2,
					 * BigDecimal.ROUND_HALF_UP).setScale(2,
					 * BigDecimal.ROUND_HALF_UP).doubleValue());
					 */

					maintianvosAdd.add(temp);

				}

			}

		}

		TbCardHis tbCardHis = null;

		// if (null != tbCardHisService.findCardHisByBalanceId(tbBusinessBalance
		// .getId())
		// && tbCardHisService.findCardHisByBalanceId(
		// tbBusinessBalance.getId()).size() > 0) {
		//
		// tbCardHis = tbCardHisService.findCardHisByBalanceId(
		// tbBusinessBalance.getId()).get(0);
		//
		// }

		Map reportParameters = new HashMap();
		// 结算单号
		reportParameters.put("balanceCode", "");
		// 委托书号
		reportParameters.put("entrustCode", tbFixEntrust.getEntrustCode());

		if (null != tbCardHis) {

			reportParameters.put("oriCardSaving", tbCardHis.getOriCardSaving());

			reportParameters.put("giveMoney",
					Double.valueOf(tbCardHis.getGiveMoney()));

			reportParameters.put("aftCardSaving", tbCardHis.getAftCardSaving());

		} else {

			reportParameters.put("oriCardSaving", 0.00D);

			reportParameters.put("giveMoney", 0.00D);

			reportParameters.put("aftCardSaving", 0.00D);
		}

		if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {
			// 销售单号
			reportParameters.put("stockOutCode", tmStockOutDetVos.get(0)
					.getStockOutCode());
		}

		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			reportParameters.put("stockOutCode", CommonMethod
					.parseDateToString(tbFixEntrust.getFixDate(),
							"yyyy-MM-dd HH:mm:ss"));
		}

		// 牌照号
		reportParameters.put("licenseCode", tbCarInfo.getLicenseCode());

		if (null != tbCarInfo.getTmCarModelType()) {
			// 车型
			reportParameters.put("modelType", tbCarInfo.getTmCarModelType()
					.getModelName());
		}

		// 客户
		reportParameters.put("customerName", tbCustomer.getCustomerName());

		// 结算日期
		reportParameters.put("balanceDate", CommonMethod.parseDateToString(
				tbFixEntrust.getFixDate(), "yyyy-MM-dd"));

		// 付款
		reportParameters.put("payPatten", "");

		// 修理类型
		reportParameters.put("fixType", tbFixEntrust.getTmFixType()
				.getFixType());

		// 底盘号
		reportParameters.put("chassisCode", tbCarInfo.getChassisCode());

		// 发动机号
		reportParameters.put("engineCode", tbCarInfo.getEngineCode());

		// 购车日期
		reportParameters.put("purchaseDate", CommonMethod.parseDateToString(
				tbCarInfo.getPurchaseDate(), "yyyy-MM-dd"));

		// 电话
		reportParameters.put("phone",
				tbCustomer.getPhone() == null ? "" : tbCustomer.getPhone()
						.trim() + "  " + tbCustomer.getTelephone() == null ? ""
						: tbCustomer.getTelephone().trim());

		// 地址
		reportParameters.put("address", tbCustomer.getAddress());

		// 公里数
		/*
		 * reportParameters.put("kilo", tbCarInfo.getKilo() == null ? "" :
		 * String .valueOf(tbCarInfo.getKilo()));
		 */
		reportParameters.put(
				"kilo",
				tbFixEntrust.getEnterStationKilo() == null ? "" : String
						.valueOf(tbFixEntrust.getEnterStationKilo()));

		// 备注说明
		reportParameters.put("remark", tbFixEntrust.getRemark());

		// 公司信息
		reportParameters.put("companyName", tmCompany.getCompanyName());

		reportParameters.put("companyAddress", tmCompany.getCompanyAddress());

		reportParameters.put("companyPhone", tmCompany.getCompanyPhone());

		reportParameters.put("companyTaxCode", tmCompany.getTaxCode());

		reportParameters.put("companyAccount", tmCompany.getCompanyAccount());

		reportParameters.put("serviceLeader", tmCompany.getServiceLeader());

		reportParameters.put("companyZipCode", tmCompany.getCompanyZipCode());

		reportParameters.put("companyBankName", tmCompany.getBankName());
		// 公司信息

		// 服务顾问
		reportParameters.put("userRealName", tbFixEntrust.getTmUser()
				.getUserRealName());
		// 结算员
		// reportParameters.put("jsUserRealName",
		// tbBusinessBalance.getTmUser().getUserRealName());
		// 打印时间
		reportParameters.put("printDate", CommonMethod.parseDateToString(
				new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 子报表路径
		reportParameters.put("SUBREPORT_DIR",
				request.getRealPath("/reportfiles/") + "/");
		// 子报表数据源-工时

		reportParameters.put("subdatasource_0", new JRBeanCollectionDataSource(
				tbFixEntrustContentListAdd));

		// 子报表数据源-结算项目
		// List<TbBusinessBalanceItem> tbBusinessBalanceItemListSend = new
		// ArrayList<TbBusinessBalanceItem>();
		//
		// if (null != tbBusinessBalanceItemList
		// && tbBusinessBalanceItemList.size() > 0) {
		//
		// for (TbBusinessBalanceItem tbBusinessBalanceItem :
		// tbBusinessBalanceItemList) {
		//
		// if ("XLCLF".equals(tbBusinessBalanceItem.getBalanceItemCode())) {
		//
		// reportParameters.put("xlclf",
		// tbBusinessBalanceItem.getBalanceItemTotal());
		//
		// continue;
		// }
		//
		// if ("XLGSF".equals(tbBusinessBalanceItem.getBalanceItemCode())) {
		//
		// reportParameters.put("xlgsf",
		// tbBusinessBalanceItem.getBalanceItemTotal());
		//
		// continue;
		// }
		//
		// if ("SE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {
		//
		// reportParameters.put("taxAmount",
		// tbBusinessBalanceItem.getBalanceItemTotal());
		//
		// continue;
		// }
		//
		// if ("ZJE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {
		//
		// reportParameters.put("totalAmount",
		// tbBusinessBalanceItem.getBalanceItemTotal());
		//
		// continue;
		// }
		// if ("XSJE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {
		//
		// reportParameters.put("xsje",
		// tbBusinessBalanceItem.getBalanceItemTotal());
		//
		// continue;
		// }
		// tbBusinessBalanceItemListSend.add(tbBusinessBalanceItem);
		//
		// }
		//
		// }
		
		List<TbMaintainPartContent> tcList =  tbMaintainPartContentService.getViewEntrustMaintianContent(tbFixEntrust.getId());
		
		if(null!=tcList&&tcList.size()>0){
			
			tbFixEntrust.setStockOutPartTotal(new BigDecimal(tcList.get(0).getTotalPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
			
		}
		
		tbFixEntrust.setFixHourTotal(new BigDecimal(tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustId(tbFixEntrust.getId())).setScale(2,BigDecimal.ROUND_HALF_UP));
		
		tbFixEntrust.setSolePartTotal(new BigDecimal(tmStockOutService.getTotalPriceByEntrustCode(tbFixEntrust.getEntrustCode())).setScale(2,BigDecimal.ROUND_HALF_UP));
		
		reportParameters.put("xlclf",tbFixEntrust.getStockOutPartTotal().doubleValue());

		reportParameters.put("xlgsf",tbFixEntrust.getFixHourTotal().doubleValue());

		reportParameters.put("xsje",tbFixEntrust.getSolePartTotal().doubleValue());

		tbFixEntrust.setAllTotal(tbFixEntrust.getFixHourTotal().add(tbFixEntrust.getStockOutPartTotal().add(tbFixEntrust.getSolePartTotal())));
		
		// 营业额
		reportParameters.put("totalAmount", tbFixEntrust.getAllTotal().doubleValue());
		// 材料优惠金额
		// reportParameters.put("partFavourAmount",this.calcItemFavourAmount(tbBusinessBalance,
		// "XLCLF"));
		// 工时优惠金额
		// reportParameters.put("fixFavourAmount",this.calcItemFavourAmount(tbBusinessBalance,
		// "XLGSF"));
		// 销售优惠金额
		// reportParameters.put("soleFavourAmount",this.calcItemFavourAmount(tbBusinessBalance,
		// "XSJE"));
		// 结算项目数据源
		//reportParameters.put("subdatasource_1", new JRBeanCollectionDataSource(tbBusinessBalanceItemListSend));

		map.put("reportParameters", reportParameters);

		map.put("dataSourceList", maintianvosAdd);

		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			reportParameters.put("purchaseDate",
					CommonMethod.parseDateToString(tbCarInfo.getProductDate(),
							"yyyy-MM-dd"));

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_gxnndz.jrxml");

		} else if ("衡水市开发区众悦汽车装饰部".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_hbhs.jrxml");
		}

		else {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_pre.jrxml");

		}

		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");

		return map;

	}
}
