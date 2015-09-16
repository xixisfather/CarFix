package com.selfsoft.business.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCardHis;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.business.dao.ITbBusinessBalanceDao;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbFixEntrustCost;
import com.selfsoft.business.model.TbReceiveFree;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustCostService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITbReceiveFreeService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.file.XlsWriter;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.service.ITmCompanyService;
import com.selfsoft.secrity.service.ITmUserService;

@Service("tbBusinessBalanceService")
public class TbBusinessBalanceServiceImpl implements ITbBusinessBalanceService {

	@Autowired
	private ITbBusinessBalanceDao tbBusinessBalanceDao;

	@Autowired
	private ITbBusinessBalanceItemService tbBusinessBalanceItemService;

	@Autowired
	private ITbFixEntrustService tbFixEntrustService;

	@Autowired
	private ITmStockOutService tmStockOutService;

	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;

	@Autowired
	private ITbFixEntrustContentService tbFixEntrustContentService;

	@Autowired
	private ITbCustomerService tbCustomerService;

	@Autowired
	private ITbCarInfoService tbCarInfoService;

	@Autowired
	private ITmCompanyService tmCompanyService;

	@Autowired
	private ITbReceiveFreeService tbReceiveFreeService;

	@Autowired
	private ITbFixEntrustCostService tbFixEntrustCostService;

	@Autowired
	private ITbMembershipCardService tbMembershipCardService;

	@Autowired
	private ITmUserService tmUserService;

	@Autowired
	private ITbCardHisService tbCardHisService;

	@Autowired
	private IStatisticsStockInOutService statisticsStockInOutService;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbBusinessBalanceDao.deleteById(id);
	}

	public List<TbBusinessBalance> findAll() {
		// TODO Auto-generated method stub
		return tbBusinessBalanceDao.findAll();
	}

	public List<TbBusinessBalance> findByTbBusinessBalance(
			TbBusinessBalance tbBusinessBalance) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(TbBusinessBalance.class);

		if (null != tbBusinessBalance) {

			if (null != tbBusinessBalance.getBalanceCode()
					&& !"".equals(tbBusinessBalance.getBalanceCode())) {
				detachedCriteria.add(Restrictions.like("balanceCode", "%"
						+ tbBusinessBalance.getBalanceCode() + "%"));
			}
			if (null != tbBusinessBalance.getTbFixEntrust()) {

				
				if (null != tbBusinessBalance.getTbFixEntrust()
						.getEntrustCode()
						&& !"".equals(tbBusinessBalance.getTbFixEntrust()
								.getEntrustCode())) {
					detachedCriteria.createAlias("tbFixEntrust", "tbFixEntrust");

					
					detachedCriteria.add(Restrictions.like(
							"tbFixEntrust.entrustCode", "%"
									+ tbBusinessBalance.getTbFixEntrust()
											.getEntrustCode() + "%"));
				}
			}

			if (null != tbBusinessBalance.getLicenseCode()
					&& !"".equals(tbBusinessBalance.getLicenseCode())) {

				if ((null == tbBusinessBalance.getTbFixEntrust()
						.getEntrustCode()||"".equals(tbBusinessBalance.getTbFixEntrust()
								.getEntrustCode())))
					detachedCriteria
							.createAlias("tbFixEntrust", "tbFixEntrust");

				detachedCriteria.createAlias("tbFixEntrust.tbCarInfo",
						"tbCarInfo");

				detachedCriteria.add(Restrictions.like("tbCarInfo.licenseCode",
						"%" + tbBusinessBalance.getLicenseCode() + "%"));

			}

			if (null != tbBusinessBalance.getTmUser()) {

				if (null != tbBusinessBalance.getTmUser().getId()) {

					detachedCriteria.createAlias("tmUser", "tmUser");

					detachedCriteria.add(Restrictions.eq("tmUser.id",
							+tbBusinessBalance.getTmUser().getId()));

				}

			}

			if (null != tbBusinessBalance.getUserId()) {

				if ((null == tbBusinessBalance.getTbFixEntrust()
						.getEntrustCode()||"".equals(tbBusinessBalance.getTbFixEntrust()
								.getEntrustCode()))
						&&(null == tbBusinessBalance.getLicenseCode()
								|| "".equals(tbBusinessBalance.getLicenseCode()))		
						)
					detachedCriteria
							.createAlias("tbFixEntrust", "tbFixEntrust");
				
				if(null == tbBusinessBalance.getTmUser() || null == tbBusinessBalance.getTmUser().getId())

					detachedCriteria.createAlias("tbFixEntrust.tmUser", "tmUser");

				detachedCriteria.add(Restrictions.eq("tmUser.id",
						+tbBusinessBalance.getUserId()));

			}

			if (null != tbBusinessBalance.getBananceDateStart()) {
				detachedCriteria.add(Restrictions.ge("bananceDate",
						tbBusinessBalance.getBananceDateStart()));
			}
			if (null != tbBusinessBalance.getBananceDateEnd()) {
				detachedCriteria.add(Restrictions.le(
						"bananceDate",
						CommonMethod.addDate(
								tbBusinessBalance.getBananceDateEnd(), 1)));
			}
			if (null != tbBusinessBalance.getBalanceStatus()) {
				detachedCriteria.add(Restrictions.eq("balanceStatus",
						tbBusinessBalance.getBalanceStatus()));
			}
			if (null != tbBusinessBalance.getTmModelTypeId()) {

				if (null == tbBusinessBalance.getTbFixEntrust()
						&& (null == tbBusinessBalance.getLicenseCode() || ""
								.equals(tbBusinessBalance.getLicenseCode()))
						&& null == tbBusinessBalance.getUserId())

					detachedCriteria
							.createAlias("tbFixEntrust", "tbFixEntrust");

				detachedCriteria.createAlias("tbFixEntrust.tbCarInfo",
						"tbCarInfo");

				detachedCriteria.createAlias("tbCarInfo.tmCarModelType",
						"tmCarModelType");

				detachedCriteria.add(Restrictions.eq("tmCarModelType.id",
						tbBusinessBalance.getTmModelTypeId()));
			}

		}

		return tbBusinessBalanceDao.findByCriteria(detachedCriteria,
				tbBusinessBalance);

	}
	
	// 查询总单信息
	public List<TbBusinessBalance> findTbBusinessBalanceToGroup(
			TbBusinessBalance tbBusinessBalance) {

		String sql = "select new TbBusinessBalance(max(tbBusinessBalance.id),tbBusinessBalance.balanceCode,max(tbBusinessBalance.bananceDate),sum(tbBusinessBalance.balanceTotalAll),sum(tbBusinessBalance.shouldPayAmount),sum(tbBusinessBalance.workingHourTotalAll),sum(tbBusinessBalance.fixPartTotalAll),sum(tbBusinessBalance.solePartTotalAll)) from TbBusinessBalance tbBusinessBalance WHERE 1=1";

		String sqlConditionSelect = "select tbBusinessBalance from TbBusinessBalance tbBusinessBalance where 1=1";

		String sqlCondition = "";

		String sqlGroup = " group by tbBusinessBalance.balanceCode";

		if (null != tbBusinessBalance) {

			if (null != tbBusinessBalance.getId()) {

				/*
				 * sqlCondition +=" and tbBusinessBalance.id = " +
				 * tbBusinessBalance.getId();
				 */

				TbBusinessBalance t = this.findById(tbBusinessBalance.getId());

				tbBusinessBalance.setBalanceCode(t.getBalanceCode());

			}

			if (null != tbBusinessBalance.getBalanceCode()
					&& !"".equals(tbBusinessBalance.getBalanceCode())) {

				sqlCondition += " and tbBusinessBalance.balanceCode like '%"
						+ tbBusinessBalance.getBalanceCode() + "%'";

			}

			if (null != tbBusinessBalance.getEntrustCode()
					&& !"".equals(tbBusinessBalance.getEntrustCode())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.entrustCode like '%"
						+ tbBusinessBalance.getEntrustCode() + "%'";

			}

			if (null != tbBusinessBalance.getBananceDateStart()) {
				sqlCondition += " and tbBusinessBalance.bananceDate>='"
						+ CommonMethod.parseDateToString(
								tbBusinessBalance.getBananceDateStart(),
								"yyyy-MM-dd") + "'";
			}

			if (null != tbBusinessBalance.getBananceDateEnd()) {
				sqlCondition += " and tbBusinessBalance.bananceDate<='"
						+ CommonMethod.parseDateToString(CommonMethod.addDate(
								tbBusinessBalance.getBananceDateEnd(), 1),
								"yyyy-MM-dd") + "'";
			}

			if (null != tbBusinessBalance.getTmUser()) {

				if (null != tbBusinessBalance.getTmUser().getId()) {

					sqlCondition += " and tbBusinessBalance.tmUser.id="
							+ tbBusinessBalance.getTmUser().getId();

				}

			}

			if (null != tbBusinessBalance.getUserId()) {
				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tmUser.id ="
						+ tbBusinessBalance.getUserId() + "";
			}

			if (null != tbBusinessBalance.getLicenseCode()
					&& !"".equals(tbBusinessBalance.getLicenseCode())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tbCarInfo.licenseCode like '%"
						+ tbBusinessBalance.getLicenseCode() + "%'";

			}
			
			if (null != tbBusinessBalance.getChassisCode()
					&& !"".equals(tbBusinessBalance.getChassisCode())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tbCarInfo.chassisCode like '%"
						+ tbBusinessBalance.getChassisCode() + "%'";

			}
			
			if (null != tbBusinessBalance.getCustomerName()
					&& !"".equals(tbBusinessBalance.getCustomerName())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tbCustomer.customerName = '"
						+ tbBusinessBalance.getCustomerName() + "'";

			}
			
			if (null != tbBusinessBalance.getCustomerCode()
					&& !"".equals(tbBusinessBalance.getCustomerCode())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tbCustomer.customerCode = '"
						+ tbBusinessBalance.getCustomerCode() + "'";

			}
			
			if (null != tbBusinessBalance.getTelephone()
					&& !"".equals(tbBusinessBalance.getTelephone())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tbCustomer.telephone = '"
						+ tbBusinessBalance.getTelephone() + "'";

			}

			if (null != tbBusinessBalance.getTmModelTypeId()) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tbCarInfo.tmCarModelType.id ="
						+ tbBusinessBalance.getTmModelTypeId();

			}
			
			if(null != tbBusinessBalance.getPayPattern()) {
				
				sqlCondition += " and tbBusinessBalance.payPattern = '"
						+ tbBusinessBalance.getPayPattern() + "'";
			}
			
			if(null != tbBusinessBalance.getTmFixTypeId()) {
				sqlCondition += " and tbBusinessBalance.tbFixEntrust.tmFixType.id ="
						+ tbBusinessBalance.getTmFixTypeId() + "";
			}
		}

		List<TbBusinessBalance> listCondition = tbBusinessBalanceDao.findBySQL(
				sqlConditionSelect + sqlCondition, null);

		List<TbBusinessBalance> list = tbBusinessBalanceDao.findBySQL(sql
				+ sqlGroup, null);

		List<TbBusinessBalance> listReturn = new ArrayList<TbBusinessBalance>();

		BigDecimal pjCostAll = new BigDecimal("0.00");

		if (null != listCondition && listCondition.size() > 0 && null != list
				&& list.size() > 0) {

			for (int i = list.size() - 1; i >= 0; i--) {

				TbBusinessBalance ti = list.get(i);

				for (int j = listCondition.size() - 1; j >= 0; j--) {

					TbBusinessBalance tj = listCondition.get(j);

					if (ti.getId().equals(tj.getId())) {
						
						ti.setPayPattern(tj.getPayPattern());

						ti.setTbFixEntrust(tj.getTbFixEntrust());

						ti.setTmStockOut(tj.getTmStockOut());

						ti.setOldPartDeal(tj.getOldPartDeal());

						ti.setRemark(tj.getRemark());

						ti.setTmUser(tj.getTmUser());

						TbCustomer tbCustomer = null;

						if (null != ti.getTbFixEntrust()) {

							tbCustomer = ti.getTbFixEntrust().getTbCarInfo()
									.getTbCustomer();

							ti.setLicenseCode(ti.getTbFixEntrust()
									.getTbCarInfo().getLicenseCode());

							Double pjCost = statisticsStockInOutService
									.sumStockDetailByEntrustId(ti
											.getTbFixEntrust().getId());

							ti.setPjCost(new BigDecimal(pjCost).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP));

							pjCostAll = pjCostAll.add(new BigDecimal(pjCost))
									.divide(new BigDecimal("1.00"), 2,
											BigDecimal.ROUND_HALF_UP);

							ti.setPjCostAll(pjCostAll);

							BigDecimal djCost = tbFixEntrustCostService
									.sumTbFixEntrustCostByTbFixEntrustId(ti
											.getTbFixEntrust().getId());

							ti.setDjCost(djCost.divide(new BigDecimal("1.00"),
									2, BigDecimal.ROUND_HALF_UP));
							
							ti.setUserRealNameServer(ti
											.getTbFixEntrust().getTmUser().getUserRealName());

						} else {
							tbCustomer = tbCustomerService.findById(ti
									.getTmStockOut().getCustomerBill());

							Double pjCost = tmStockOutService
									.sumSingleSellByBanalceCode(ti
											.getBalanceCode());

							ti.setPjCost(new BigDecimal(pjCost).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP));

							pjCostAll = pjCostAll.add(new BigDecimal(pjCost))
									.divide(new BigDecimal("1.00"), 2,
											BigDecimal.ROUND_HALF_UP);

							ti.setPjCostAll(pjCostAll);

						}

						ti.setTbCustomer(tbCustomer);

						ti.setXlgsfFavourAmount(this.calcItemFavourAmount(ti,
								"XLGSF"));

						ti.setXlclfFavourAmount(this.calcItemFavourAmount(ti,
								"XLCLF"));

						ti.setXsjeFavourAmount(this.calcItemFavourAmount(ti,
								"XSJE"));
						
						String bananceDateStart_s = CommonMethod.parseDateToString(
								ti.getBananceDate(), "yyyy-MM-dd HH:ss:mm");

						ti.setBananceDateStart_s(bananceDateStart_s);
						
						
						
						// 当有欠款的时候
						if (ti.getOweAmount() > 0D) {

							BigDecimal d = new BigDecimal("0.00");

							List<TbReceiveFree> tbReceiveFreeList = tbReceiveFreeService
									.findByBalanceId(ti.getId());

							/**
							 * 是否有减免金额
							 */
							if (null != tbReceiveFreeList
									&& tbReceiveFreeList.size() > 0) {

								for (TbReceiveFree tf : tbReceiveFreeList) {

									if (Constants.AMOUNTS.equals(tf.getAmountType())) {

										d = d.add(new BigDecimal(tf.getFeeAmount()));
										
										ti.setFreeAmount(tf.getFeeAmount());
									}

								}

							}
							
						}
						
						
						
						
						
						
						
						

						listReturn.add(ti);

						break;

					}
				}

			}

		}

		return listReturn;

	}

	public TbBusinessBalance findById(Long id) {
		// TODO Auto-generated method stub
		return tbBusinessBalanceDao.findById(id);
	}

	public void insert(TbBusinessBalance tbBusinessBalance) {
		// TODO Auto-generated method stub
		tbBusinessBalanceDao.insert(tbBusinessBalance);
	}

	public void update(TbBusinessBalance tbBusinessBalance) {
		// TODO Auto-generated method stub
		tbBusinessBalanceDao.update(tbBusinessBalance);
	}

	// 插入结算项目清单
	private void insertTbBusinessBalanceItem(TbBusinessBalance tbBusinessBalance) {

		Set tbBusinessBalanceItems = tbBusinessBalance
				.getTbBusinessBalanceItems();

		Iterator it = tbBusinessBalanceItems.iterator();

		while (it.hasNext()) {

			TbBusinessBalanceItem tbBusinessBalanceItem = (TbBusinessBalanceItem) it
					.next();

			tbBusinessBalanceItem.setTbBusinessBalance(tbBusinessBalance);

			tbBusinessBalanceItemService.insert(tbBusinessBalanceItem);
		}
	}

	// 更新委托书--状态置为已经结算--委托书下明细BALANCEID更新--同时更新委托书下销售单的状态---更新发料单状态
	private void updateTbFixEntrust(TbBusinessBalance tbBusinessBalance) {

		TbFixEntrust tbFixEntrust = tbBusinessBalance.getTbFixEntrust();

		if (null != tbFixEntrust && null != tbFixEntrust.getId()) {

			tbFixEntrust = tbFixEntrustService.findById(tbFixEntrust.getId());

			tbFixEntrust.setEntrustStatus(Constants.ISBALANCE);

			tbMaintainPartContentService.updateTbMaintainStatus(
					tbFixEntrust.getId(), Constants.FINSH_BALANCE);

			tmStockOutService.updateTrustBill(tbFixEntrust.getEntrustCode(),
					Constants.FINSH_BALANCE);

			tbFixEntrustService.update(tbFixEntrust);
		}
	}

	// 更新销售单状态--状态为已经结算
	private void updateTmStockOut(TbBusinessBalance tbBusinessBalance) {

		if (null != tbBusinessBalance.getTmStockOut()) {

			tmStockOutService.updateSellStatus(tbBusinessBalance
					.getTmStockOut().getId(), Constants.FINSH_BALANCE);

		}
	}

	// 插入结算单信息、结算单明细信息
	public void insertAll(TbBusinessBalance tbBusinessBalance) {

		this.updateTbFixEntrust(tbBusinessBalance);

		this.updateTmStockOut(tbBusinessBalance);

		this.insertTbBusinessBalanceItem(tbBusinessBalance);

		this.insert(tbBusinessBalance);

		/**
		 * 会员卡信息
		 */

		if (null != tbBusinessBalance.getTbMembershipCard()) {

			TbMembershipCard tbMembershipCard = tbBusinessBalance
					.getTbMembershipCard();

			tbMembershipCard.setBalanceCode(tbBusinessBalance.getBalanceCode());

			tbMembershipCard.setBalanceId(tbBusinessBalance.getId());

			tbMembershipCardService.insertJSTbMembershipCard(tbBusinessBalance
					.getTbMembershipCard(), tmUserService
					.findById(tbBusinessBalance.getTmUser().getId()));

		}

		if (null != tbBusinessBalance.getTbFixEntrust()) {

			tbFixEntrustContentService.updateTbFixEnTrustContentBalanceId(
					tbBusinessBalance.getTbFixEntrust().getId(),
					tbBusinessBalance.getId());

			tbMaintainPartContentService.updateMaintainDetailBalance(
					tbBusinessBalance.getTbFixEntrust().getId(),
					tbBusinessBalance.getId());

			tmStockOutService.updateSellBalance(tbBusinessBalance
					.getTbFixEntrust().getEntrustCode(), tbBusinessBalance
					.getId());

		}

		if (null != tbBusinessBalance.getTmStockOut()) {

			tmStockOutService.updateCustomerSellBalance(tbBusinessBalance
					.getTmStockOut().getId(), tbBusinessBalance.getId());

		}

		// this.updateTbBusinessBalanceStatusByBalanceCode(tbBusinessBalance.getBalanceCode(),
		// Constants.JSDHASBALANCE);

	}

	// 更新委托书--状态置为 再结算并置为未竣工--同时更新委托书下销售单的状态--
	private void updateTbFixEntrustReBalance(TbBusinessBalance tbBusinessBalance) {

		TbFixEntrust tbFixEntrust = tbBusinessBalance.getTbFixEntrust();

		if (null != tbFixEntrust && null != tbFixEntrust.getId()) {

			tbFixEntrust = tbFixEntrustService.findById(tbFixEntrust.getId());

			tbFixEntrust.setIsFinish(Constants.NOFINISH);

			tbFixEntrust.setEntrustStatus(Constants.REBALANCE);

			tbMaintainPartContentService.updateTbMaintainStatus(
					tbFixEntrust.getId(), tbBusinessBalance.getId(),
					Constants.RE_BALANCE);

			tmStockOutService.updateTrustBill(tbFixEntrust.getEntrustCode(),
					Constants.RE_BALANCE);

			tbFixEntrustService.update(tbFixEntrust);

			tbMaintainPartContentService
					.updateNoMaintainNoReBalance(tbFixEntrust.getEntrustCode());
		}
	}

	// 更新销售单状态--状态为再结算
	private void updateTmStockOutReBalance(TbBusinessBalance tbBusinessBalance) {

		if (null != tbBusinessBalance.getTmStockOut()) {

			tmStockOutService.updateSellStatus(tbBusinessBalance
					.getTmStockOut().getId(), Constants.RE_BALANCE);

		}
	}

	// 更新结算单状态--状态为再结算
	public boolean reBabanceTbBusinessBalance(Long id) {

		TbBusinessBalance tbBusinessBalance = this.findById(id);

		tbBusinessBalance.setBalanceStatus(Constants.JSDREBALANCE);

		try {

			this.updateTbFixEntrustReBalance(tbBusinessBalance);

			this.updateTmStockOutReBalance(tbBusinessBalance);

			this.update(tbBusinessBalance);

		} catch (Exception e) {

			return false;

		}

		return true;
	}

	// 根据委托书号查找结算单信息
	public TbBusinessBalance findByEntrustId(Long entrustId) {

		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceDao
				.findBySQL(
						"SELECT tbBusinessBalance FROM TbBusinessBalance tbBusinessBalance where tbBusinessBalance.tbFixEntrust.id=? order by tbBusinessBalance.id desc",
						new Object[] { entrustId });

		if (null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0) {

			return tbBusinessBalanceList.get(0);
		}

		return null;
	}

	// 根据销售单号查找结算单信息
	public TbBusinessBalance findByStockOutId(Long stockOutId) {

		List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceDao
				.findBySQL(
						"SELECT tbBusinessBalance FROM TbBusinessBalance tbBusinessBalance where tbBusinessBalance.tmStockOut.id=?",
						new Object[] { stockOutId });

		if (null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0) {

			return tbBusinessBalanceList.get(0);
		}

		return null;
	}

	// 根据结算单号查找
	public List<TbBusinessBalance> findTbBusinessBalanceByBalanceCode(
			String balanceCode) {

		return tbBusinessBalanceDao
				.findBySQL(
						"select tbBusinessBalance from TbBusinessBalance tbBusinessBalance where tbBusinessBalance.balanceCode=?",
						new Object[] { balanceCode });
	}

	// 通过结算单号更新结算单状态
	public void updateTbBusinessBalanceStatusByBalanceCode(String balanceCode,
			Long status) {

		List<TbBusinessBalance> tbBusinessBalanceList = this
				.findTbBusinessBalanceByBalanceCode(balanceCode);

		if (null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0) {

			for (TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList) {

				tbBusinessBalance.setBalanceStatus(status);

				this.update(tbBusinessBalance);
			}
		}

	}

	// 优惠减免去零查询
	public List<TbBusinessBalance> findTbBusinessBalanceToGroupReceiveFree(
			TbBusinessBalance tbBusinessBalance) {

		String sql = "select new TbBusinessBalance(max(tbBusinessBalance.id),tbBusinessBalance.balanceCode,max(tbBusinessBalance.bananceDate),sum(tbBusinessBalance.balanceTotalAll),sum(tbBusinessBalance.shouldPayAmount),sum(tbBusinessBalance.workingHourTotalAll),sum(tbBusinessBalance.fixPartTotalAll),sum(tbBusinessBalance.solePartTotalAll)) from TbBusinessBalance tbBusinessBalance WHERE 1=1";

		String sqlDone = " and (tbBusinessBalance.workingHourFavourRate!=0 or tbBusinessBalance.fixPartFavourRate!=0 or tbBusinessBalance.solePartFavourRate!=0) or tbBusinessBalance.id in (select ti.tbBusinessBalance.id from TbBusinessBalanceItem ti where ti.balanceItemCode = 'QL' and ti.balanceItemTotal > 0)";

		String sqlConditionSelect = "select tbBusinessBalance from TbBusinessBalance tbBusinessBalance where 1=1";

		String sqlCondition = "";

		String sqlGroup = " group by tbBusinessBalance.balanceCode";

		if (null != tbBusinessBalance) {

			if (null != tbBusinessBalance.getId()) {

				/*
				 * sqlCondition +=" and tbBusinessBalance.id = " +
				 * tbBusinessBalance.getId();
				 */

				TbBusinessBalance t = this.findById(tbBusinessBalance.getId());

				tbBusinessBalance.setBalanceCode(t.getBalanceCode());

			}

			if (null != tbBusinessBalance.getBalanceCode()
					&& !"".equals(tbBusinessBalance.getBalanceCode())) {

				sqlCondition += " and tbBusinessBalance.balanceCode like '%"
						+ tbBusinessBalance.getBalanceCode() + "%'";

			}

			if (null != tbBusinessBalance.getEntrustCode()
					&& !"".equals(tbBusinessBalance.getEntrustCode())) {

				sqlCondition += " and tbBusinessBalance.tbFixEntrust.entrustCode like '%"
						+ tbBusinessBalance.getEntrustCode() + "%'";

			}

			if (null != tbBusinessBalance.getBananceDateStart()) {
				sqlCondition += " and tbBusinessBalance.bananceDate>='"
						+ CommonMethod.parseDateToString(
								tbBusinessBalance.getBananceDateStart(),
								"yyyy-MM-dd") + "'";
			}

			if (null != tbBusinessBalance.getBananceDateEnd()) {
				sqlCondition += " and tbBusinessBalance.bananceDate<='"
						+ CommonMethod.parseDateToString(CommonMethod.addDate(
								tbBusinessBalance.getBananceDateEnd(), 1),
								"yyyy-MM-dd") + "'";
			}

			if (null != tbBusinessBalance.getTmUser()) {

				if (null != tbBusinessBalance.getTmUser().getId()) {

					sqlCondition += " and tbBusinessBalance.tmUser.id="
							+ tbBusinessBalance.getTmUser().getId();

				}

			}
		}

		List<TbBusinessBalance> listCondition = tbBusinessBalanceDao.findBySQL(
				sqlConditionSelect + sqlCondition + sqlDone, null);

		List<TbBusinessBalance> list = tbBusinessBalanceDao.findBySQL(sql
				+ sqlGroup, null);

		List<TbBusinessBalance> listReturn = new ArrayList<TbBusinessBalance>();

		if (null != listCondition && listCondition.size() > 0 && null != list
				&& list.size() > 0) {

			for (int i = list.size() - 1; i >= 0; i--) {

				TbBusinessBalance ti = list.get(i);

				for (int j = listCondition.size() - 1; j >= 0; j--) {

					TbBusinessBalance tj = listCondition.get(j);

					if (ti.getBalanceCode().equals(tj.getBalanceCode())) {

						ti.setTbFixEntrust(tj.getTbFixEntrust());

						ti.setTmStockOut(tj.getTmStockOut());

						ti.setOldPartDeal(tj.getOldPartDeal());

						ti.setRemark(tj.getRemark());

						ti.setTmUser(tj.getTmUser());

						TbCustomer tbCustomer = null;

						if (null != ti.getTbFixEntrust()) {

							tbCustomer = ti.getTbFixEntrust().getTbCarInfo()
									.getTbCustomer();
						} else {
							tbCustomer = tbCustomerService.findById(ti
									.getTmStockOut().getCustomerBill());
						}

						ti.setTbCustomer(tbCustomer);

						ti.setXlgsfFavourAmount(this.calcItemFavourAmount(ti,
								"XLGSF"));

						ti.setXlclfFavourAmount(this.calcItemFavourAmount(ti,
								"XLCLF"));

						ti.setXsjeFavourAmount(this.calcItemFavourAmount(ti,
								"XSJE"));

						List<TbBusinessBalance> tbbList = this
								.findTbBusinessBalanceByBalanceCode(ti
										.getBalanceCode());

						BigDecimal d = new BigDecimal("0.00");

						if (null != tbbList && tbbList.size() > 0) {

							for (TbBusinessBalance te : tbbList) {

								List<TbBusinessBalanceItem> TbBusinessBalanceItemList = tbBusinessBalanceItemService
										.findTbBusinessBalanceItemListByTbBusinessBalanceId(te
												.getId());

								if (null != TbBusinessBalanceItemList
										&& TbBusinessBalanceItemList.size() > 0) {

									for (TbBusinessBalanceItem item : TbBusinessBalanceItemList) {

										if ("QL".equals(item
												.getBalanceItemCode())) {

											d = d.add(new BigDecimal(
													String.valueOf(item
															.getBalanceItemTotal())));

										}
									}

								}
							}
						}

						ti.setQlAmount(d.doubleValue());

						listReturn.add(ti);

						break;

					}
				}

			}

		}

		return listReturn;

	}

	// 汇总各项相加
	public Map<String, BigDecimal> sumGroupList(List<TbBusinessBalance> list) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		// 总金额
		BigDecimal total = new BigDecimal(String.valueOf("0.00"));
		// 收款金额
		BigDecimal payed = new BigDecimal(String.valueOf("0.00"));
		// 修理工时
		BigDecimal fixHour = new BigDecimal(String.valueOf("0.00"));
		// 修理材料
		BigDecimal fixPart = new BigDecimal(String.valueOf("0.00"));
		// 销售材料
		BigDecimal solePart = new BigDecimal(String.valueOf("0.00"));
		// 其他
		BigDecimal other = new BigDecimal(String.valueOf("0.00"));
		// 欠款
		BigDecimal owe = new BigDecimal(String.valueOf("0.00"));
		// 单据成本
		BigDecimal djcb = new BigDecimal(String.valueOf("0.00"));

		if (null != list && list.size() > 0) {

			for (TbBusinessBalance t : list) {

				List<TbReceiveFree> tbReceiveFreeList = tbReceiveFreeService
						.findByBalanceId(t.getId());

				TbFixEntrust tbFixEntrust = t.getTbFixEntrust();

				TbFixEntrustCost tbFixEntrustCost = null;

				if (null != tbFixEntrust) {

					tbFixEntrustCost = new TbFixEntrustCost();

					tbFixEntrustCost.setTbFixEntrust(tbFixEntrust);

				}

				List<TbFixEntrustCost> tbFixEntrustCostList = tbFixEntrustCostService
						.findByTbFixEntrustCost(tbFixEntrustCost);

				if (null != tbFixEntrustCostList
						&& tbFixEntrustCostList.size() > 0) {

					for (TbFixEntrustCost _tbFixEntrustCost : tbFixEntrustCostList) {

						djcb = djcb.add(new BigDecimal(_tbFixEntrustCost
								.getCostPrice() == null ? 0d
								: _tbFixEntrustCost.getCostPrice()));

					}

				}

				total = total.add(
						new BigDecimal(String.valueOf(t.getBalanceTotalAll())))
						.setScale(2, BigDecimal.ROUND_HALF_UP);

				payed = payed.add(
						new BigDecimal(String.valueOf(t.getShouldPayAmount())))
						.setScale(2, BigDecimal.ROUND_HALF_UP);

				fixHour = fixHour.add(
						new BigDecimal(String.valueOf(t
								.getWorkingHourTotalAll()))).setScale(2,
						BigDecimal.ROUND_HALF_UP);

				fixPart = fixPart.add(
						new BigDecimal(String.valueOf(t.getFixPartTotalAll())))
						.setScale(2, BigDecimal.ROUND_HALF_UP);

				solePart = solePart
						.add(new BigDecimal(String.valueOf(t
								.getSolePartTotalAll()))).setScale(2,
								BigDecimal.ROUND_HALF_UP);

				other = other.add(
						new BigDecimal(String.valueOf(t.getBalanceTotalAll()))
								.subtract(
										new BigDecimal(String.valueOf(t
												.getWorkingHourTotalAll())))
								.subtract(
										new BigDecimal(String.valueOf(t
												.getFixPartTotalAll())))
								.subtract(
										new BigDecimal(String.valueOf(t
												.getSolePartTotalAll()))))
						.setScale(2, BigDecimal.ROUND_HALF_UP);

				BigDecimal d = new BigDecimal("0.00");

				if (null != tbReceiveFreeList && tbReceiveFreeList.size() > 0) {

					for (TbReceiveFree tf : tbReceiveFreeList) {

						if (Constants.AMOUNTS.equals(tf.getAmountType())) {

							d = d.add(new BigDecimal(tf.getFeeAmount()));

						}

					}

				}

				owe = owe.add(new BigDecimal(String.valueOf(t.getOweAmount())));
						//.subtract(d);
			}

		}

		map.put("total", total);

		map.put("payed", payed);

		map.put("fixHour", fixHour);

		map.put("fixPart", fixPart);

		map.put("solePart", solePart);

		map.put("other", other);

		map.put("owe", owe);

		map.put("djcb", djcb);

		return map;
	}

	// 查找委托书结算单已付金额
	public Double findEntrustHasPayedAmount(Long entrustId) {

		TbBusinessBalance tbBusinessBalance = this.findByEntrustId(entrustId);

		if (null != tbBusinessBalance) {

			return tbBusinessBalance.getPayedAmount() == null ? 0.00D
					: tbBusinessBalance.getPayedAmount();
		}

		return 0.00D;
	}

	// 查找单独销售单已付金额
	public Double findStockOutHasPayedAmout(Long stockOutId) {

		TbBusinessBalance tbBusinessBalance = this.findByStockOutId(stockOutId);

		if (null != tbBusinessBalance) {

			return tbBusinessBalance.getPayedAmount() == null ? 0.00D
					: tbBusinessBalance.getPayedAmount();
		}

		return 0.00D;
	}

	// 计算优惠金额
	private Double calcFavourAmount(Double itemTotal, Double favourRate) {

		BigDecimal d = new BigDecimal("0.00");

		if (null != itemTotal && null != favourRate
				&& !favourRate.equals(1.00D)) {

			BigDecimal d_itemTotal = new BigDecimal(String.valueOf(itemTotal));

			BigDecimal d_favourRate = new BigDecimal(String.valueOf(favourRate));

			d = d_itemTotal
					.multiply(d_favourRate)
					.divide(new BigDecimal("1.00").subtract(d_favourRate), 2,
							BigDecimal.ROUND_HALF_UP)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		return d.doubleValue();
	}

	// itemCode优惠金额
	public Double calcItemFavourAmount(TbBusinessBalance tbBusinessBalance,
			String itemCode) {

		BigDecimal d = new BigDecimal("0.00");

		List<TbBusinessBalance> tbBusinessBalanceList = this
				.findTbBusinessBalanceByBalanceCode(tbBusinessBalance
						.getBalanceCode());

		if (null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0) {

			for (TbBusinessBalance t : tbBusinessBalanceList) {

				List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
						.findTbBusinessBalanceItemListByTbBusinessBalanceId(t
								.getId());

				if (null != tbBusinessBalanceItemList
						&& tbBusinessBalanceItemList.size() > 0) {

					for (TbBusinessBalanceItem tm : tbBusinessBalanceItemList) {

						if (itemCode.equals(tm.getBalanceItemCode())) {

							Double favourRate = 0.00D;

							if ("XLGSF".equals(itemCode)) {

								favourRate = t.getWorkingHourFavourRate();

							} else if ("XLCLF".equals(itemCode)) {

								favourRate = t.getFixPartFavourRate();

							} else if ("XSJE".equals(itemCode)) {

								favourRate = t.getSolePartFavourRate();

							}

							d = d.add(new BigDecimal(this.calcFavourAmount(
									tm.getBalanceItemTotal(), favourRate)));

						}

					}

				}

			}

		}

		return d.doubleValue();
	}

	// 委托书结算单打印
	public Map putEntrustBalanceReportParamMap(Long id,
			HttpServletRequest request) {

		Map map = new HashMap();
		// 结算单信息
		TbBusinessBalance tbBusinessBalance = this.findById(id);
		// 委托书信息
		TbFixEntrust tbFixEntrust = tbFixEntrustService
				.findById(tbBusinessBalance.getTbFixEntrust().getId());
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

				if (!tempCopy.getFreesymbol().equals(1L)
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
		List<TbMaintianVo> maintianvos = tbMaintainPartContentService
				.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
						Constants.BALANCE_ALL);
						*/
		/*update by baijx 显示需要打印的明细列表*/
		List<TbMaintianVo> maintianvos = tbMaintainPartContentService
		.getTbMaintianDetailVosByEntrustIdPrint(tbFixEntrust.getId(), Constants.BALANCE_ALL);
		// 销售单列表
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
				.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
						Constants.BALANCE_ALL);
		// 结算项目列表
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
				.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance
						.getId());

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

		if (null != tbCardHisService.findCardHisByBalanceId(tbBusinessBalance
				.getId())
				&& tbCardHisService.findCardHisByBalanceId(
						tbBusinessBalance.getId()).size() > 0) {

			tbCardHis = tbCardHisService.findCardHisByBalanceId(
					tbBusinessBalance.getId()).get(0);

		}

		Map reportParameters = new HashMap();
		// 结算单号
		reportParameters.put("balanceCode", tbBusinessBalance.getBalanceCode());
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
		reportParameters.put(
				"balanceDate",
				CommonMethod.parseDateToString(
						tbBusinessBalance.getBananceDate(), "yyyy-MM-dd"));

		// 付款
		reportParameters
				.put("payPatten", tbBusinessBalance.getPayPatternShow());

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
		reportParameters.put("remark", tbBusinessBalance.getRemark());

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
		reportParameters.put("jsUserRealName", tbBusinessBalance.getTmUser()
				.getUserRealName());
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
		List<TbBusinessBalanceItem> tbBusinessBalanceItemListSend = new ArrayList<TbBusinessBalanceItem>();

		if (null != tbBusinessBalanceItemList
				&& tbBusinessBalanceItemList.size() > 0) {

			for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

				if ("XLCLF".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("xlclf",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("XLGSF".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("xlgsf",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("SE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("taxAmount",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("ZJE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("totalAmount",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}
				if ("XSJE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("xsje",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}
				tbBusinessBalanceItemListSend.add(tbBusinessBalanceItem);

			}

		}
		// 营业额
		reportParameters.put(
				"soleAmount",
				new BigDecimal(reportParameters.get("totalAmount").toString())
						.subtract(
								new BigDecimal(reportParameters
										.get("taxAmount").toString()))
						.doubleValue());
		// 材料优惠金额
		reportParameters.put("partFavourAmount",
				this.calcItemFavourAmount(tbBusinessBalance, "XLCLF"));
		// 工时优惠金额
		reportParameters.put("fixFavourAmount",
				this.calcItemFavourAmount(tbBusinessBalance, "XLGSF"));
		// 销售优惠金额
		reportParameters.put("soleFavourAmount",
				this.calcItemFavourAmount(tbBusinessBalance, "XSJE"));
		// 结算项目数据源
		reportParameters.put("subdatasource_1", new JRBeanCollectionDataSource(
				tbBusinessBalanceItemListSend));

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

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");

		}

		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");

		return map;

	}

	// 销售单打印
	public Map putXsdBalanceReportParamMap(Long id, HttpServletRequest request) {

		Map map = new HashMap();
		// 结算单信息
		TbBusinessBalance tbBusinessBalance = this.findById(id);
		// 销售单信息
		TmStockOut tmStockOut = tmStockOutService.findById(tbBusinessBalance
				.getTmStockOut().getId());

		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tmStockOut
				.getCustomerBill());

		// 公司信息
		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		// 发料配件列表
		List<TbMaintianVo> maintianvos = new ArrayList<TbMaintianVo>();
		// 销售单列表
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
				.getCustomerSellDetailByTmStockOutId(tmStockOut.getId(),
						Constants.BALANCE_ALL);

		// 结算项目列表
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
				.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance
						.getId());
		// 用发料的明细类--配置文件可以用一个
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

						for (TbMaintianVo _tbMaintianVo : maintianvosTemp) {

							if (_tbMaintianVo.getPartId().equals(
									maintianvos.get(i).getPartId())
									&& _tbMaintianVo.getIsFree().equals(
											maintianvos.get(i).getIsFree())) {

								flag = true;

								break;

							}

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

					maintianvosAdd.add(temp);

				}

			}

		}

		TbCardHis tbCardHis = null;

		if (null != tbCardHisService.findCardHisByBalanceId(tbBusinessBalance
				.getId())
				&& tbCardHisService.findCardHisByBalanceId(
						tbBusinessBalance.getId()).size() > 0) {

			tbCardHis = tbCardHisService.findCardHisByBalanceId(
					tbBusinessBalance.getId()).get(0);

		}

		Map reportParameters = new HashMap();
		// 结算单号
		reportParameters.put("balanceCode", tbBusinessBalance.getBalanceCode());

		if (null != tmStockOutDetVos && tmStockOutDetVos.size() > 0) {
			// 销售单号
			reportParameters.put("stockOutCode", tmStockOutDetVos.get(0)
					.getStockOutCode());
		}

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

		// 客户
		reportParameters.put("customerName", tbCustomer.getCustomerName());

		// 结算日期
		reportParameters.put(
				"balanceDate",
				CommonMethod.parseDateToString(
						tbBusinessBalance.getBananceDate(), "yyyy-MM-dd"));

		// 付款
		reportParameters
				.put("payPatten", tbBusinessBalance.getPayPatternShow());

		// 电话
		reportParameters.put("phone",
				tbCustomer.getPhone() == null ? "" : tbCustomer.getPhone()
						.trim() + "  " + tbCustomer.getTelephone() == null ? ""
						: tbCustomer.getTelephone().trim());

		// 地址
		reportParameters.put("address", tbCustomer.getAddress());

		// 备注说明
		reportParameters.put("remark", tbBusinessBalance.getRemark());

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

		// 结算员
		reportParameters.put("jsUserRealName", tbBusinessBalance.getTmUser()
				.getUserRealName());
		// 打印时间
		reportParameters.put("printDate", CommonMethod.parseDateToString(
				new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 子报表路径
		reportParameters.put("SUBREPORT_DIR",
				request.getRealPath("/reportfiles/") + "/");
		// 子报表数据源-工时

		reportParameters.put("subdatasource_0", new JRBeanCollectionDataSource(
				null));

		// 子报表数据源-结算项目
		List<TbBusinessBalanceItem> tbBusinessBalanceItemListSend = new ArrayList<TbBusinessBalanceItem>();

		if (null != tbBusinessBalanceItemList
				&& tbBusinessBalanceItemList.size() > 0) {

			for (TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList) {

				if ("XLCLF".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("xlclf",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("XLGSF".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("xlgsf",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("SE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("taxAmount",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("ZJE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("totalAmount",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}
				if ("XSJE".equals(tbBusinessBalanceItem.getBalanceItemCode())) {

					reportParameters.put("xsje",
							tbBusinessBalanceItem.getBalanceItemTotal());

					continue;
				}
				tbBusinessBalanceItemListSend.add(tbBusinessBalanceItem);

			}

		}
		// 营业额
		reportParameters.put(
				"soleAmount",
				new BigDecimal(reportParameters.get("totalAmount").toString())
						.subtract(
								new BigDecimal(reportParameters
										.get("taxAmount").toString()))
						.doubleValue());
		// 材料优惠金额
		reportParameters.put("partFavourAmount",
				this.calcItemFavourAmount(tbBusinessBalance, "XLCLF"));
		// 工时优惠金额
		reportParameters.put("fixFavourAmount",
				this.calcItemFavourAmount(tbBusinessBalance, "XLGSF"));
		// 销售优惠金额
		reportParameters.put("soleFavourAmount",
				this.calcItemFavourAmount(tbBusinessBalance, "XSJE"));
		// 结算项目数据源
		reportParameters.put("subdatasource_1", new JRBeanCollectionDataSource(
				tbBusinessBalanceItemListSend));

		map.put("reportParameters", reportParameters);

		map.put("dataSourceList", maintianvosAdd);

		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_gxnndz.jrxml");

		} else if ("衡水市开发区众悦汽车装饰部".equals(tmCompany.getCompanyName().trim())) {
			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_hbhs.jrxml");
		} else {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");

		}

		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");

		return map;

	}

	/**
	 * 
	 * @param orderType
	 *            -fixCount - balanceTotalAll
	 * @return
	 */
	public List<TbBusinessBalance> fixBusinessBalanceOrder(String orderCode,
			String orderType) {

		if ("customer".equals(orderType)) {

			if ("fixCount".equals(orderCode)) {
				orderCode = "col_2_0_";
			} else {
				orderCode = "col_3_0_";
			}

			String orderSql = " order by " + orderCode + " desc ";

			String sql = " select new TbBusinessBalance(tbBusinessBalance.tbFixEntrust.tbCustomer.customerCode,tbBusinessBalance.tbFixEntrust.tbCustomer.customerName,count(distinct tbBusinessBalance.balanceCode),sum(tbBusinessBalance.balanceTotalAll),sum(tbBusinessBalance.shouldPayAmount),sum(tbBusinessBalance.workingHourTotalAll),sum(tbBusinessBalance.fixPartTotalAll),sum(tbBusinessBalance.solePartTotalAll)) from TbBusinessBalance tbBusinessBalance WHERE 1=1 ";

			String sqlGroup = " group by tbBusinessBalance.tbFixEntrust.tbCustomer.customerCode,tbBusinessBalance.tbFixEntrust.tbCustomer.customerName ";

			List<TbBusinessBalance> list = tbBusinessBalanceDao.findBySQL(sql
					+ sqlGroup + orderSql, null);

			return list;
		} else {

			if ("fixCount".equals(orderCode)) {
				orderCode = "col_3_0_";
			} else {
				orderCode = "col_4_0_";
			}

			String orderSql = " order by " + orderCode + " desc ";

			String sql = " select new TbBusinessBalance(tbBusinessBalance.tbFixEntrust.tbCarInfo.licenseCode,tbBusinessBalance.tbFixEntrust.tbCarInfo.tmCarModelType.modelName,tbBusinessBalance.tbFixEntrust.tbCustomer.customerName,count(distinct tbBusinessBalance.balanceCode),sum(tbBusinessBalance.balanceTotalAll),sum(tbBusinessBalance.shouldPayAmount),sum(tbBusinessBalance.workingHourTotalAll),sum(tbBusinessBalance.fixPartTotalAll),sum(tbBusinessBalance.solePartTotalAll)) from TbBusinessBalance tbBusinessBalance WHERE 1=1 ";

			String sqlGroup = " group by tbBusinessBalance.tbFixEntrust.tbCarInfo.licenseCode,tbBusinessBalance.tbFixEntrust.tbCarInfo.tmCarModelType.modelName,tbBusinessBalance.tbFixEntrust.tbCustomer.customerName ";

			List<TbBusinessBalance> list = tbBusinessBalanceDao.findBySQL(sql
					+ sqlGroup + orderSql, null);

			return list;
		}
	}
	
	public List<TbBusinessBalance> findTbBusinessBalanceOweGroup(
			String licenseCode) {
		
		TbBusinessBalance t = new TbBusinessBalance();
		
		t.setLicenseCode(licenseCode);
		
		return this.findTbBusinessBalanceOweGroup(t);
		
	}

	// 查询总单欠款
	public List<TbBusinessBalance> findTbBusinessBalanceOweGroup(
			TbBusinessBalance tbBusinessBalance) {

		List<TbBusinessBalance> list = this
				.findTbBusinessBalanceToGroup(tbBusinessBalance);

		List<TbBusinessBalance> list_return = new ArrayList<TbBusinessBalance>();

		if (null != list && list.size() > 0) {

			for (TbBusinessBalance t : list) {

				// 当有欠款的时候
				if (t.getOweAmount() > 0D) {

					BigDecimal d = new BigDecimal("0.00");

					List<TbReceiveFree> tbReceiveFreeList = tbReceiveFreeService
							.findByBalanceId(t.getId());

					/**
					 * 是否有减免金额
					 */
					if (null != tbReceiveFreeList
							&& tbReceiveFreeList.size() > 0) {

						for (TbReceiveFree tf : tbReceiveFreeList) {

							if (Constants.AMOUNTS.equals(tf.getAmountType())) {

								d = d.add(new BigDecimal(tf.getFeeAmount()));
								
								t.setFreeAmount(tf.getFeeAmount());
							}

						}

					}

					//if (t.getOweAmount() - d.doubleValue() > 0D) {
					if (t.getOweAmount()>0d) {

						
						
						list_return.add(t);

					}

				}

			}

		}

		return list_return;

	}

	// 查询总单未欠款
	public List<TbBusinessBalance> findTbBusinessBalanceNoOweGroup(
			TbBusinessBalance tbBusinessBalance) {

		List<TbBusinessBalance> list = this
				.findTbBusinessBalanceToGroup(tbBusinessBalance);

		List<TbBusinessBalance> list_return = new ArrayList<TbBusinessBalance>();

		if (null != list && list.size() > 0) {

			for (TbBusinessBalance t : list) {

				// 当没有欠款的时候
				if (t.getOweAmount() <= 0D) {

					list_return.add(t);

				}

			}

		}

		return list_return;

	}

	// 欠款登记
	public void registerReceiveFee(TbReceiveFree tbReceiveFree) {

		tbReceiveFreeService.insert(tbReceiveFree);

		this.update(tbReceiveFree.getTbBusinessBalance());

	}

	// 欠款删除
	public void deleteReceiveFee(TbReceiveFree tbReceiveFree) {

		tbReceiveFreeService.deleteById(tbReceiveFree.getId());

		this.update(tbReceiveFree.getTbBusinessBalance());
	}

	// 付款情况--type 1.结清 2.未结清
	public List<StatisticsTbFixBusinessVo> statisticsPayed(
			TbBusinessBalance tbBusinessBalance, Long type) {

		List<TbBusinessBalance> tbBusinessBalanceList = null;

		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = new ArrayList<StatisticsTbFixBusinessVo>();

		if (type.equals(1L)) {

			tbBusinessBalanceList = this
					.findTbBusinessBalanceNoOweGroup(tbBusinessBalance);

		} else {
			tbBusinessBalanceList = this
					.findTbBusinessBalanceOweGroup(tbBusinessBalance);
		}

		if (null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0) {

			BigDecimal d1 = new BigDecimal("0.00");

			BigDecimal d2 = new BigDecimal("0.00");

			BigDecimal d1_owe = new BigDecimal("0.00");

			BigDecimal d2_owe = new BigDecimal("0.00");

			StatisticsTbFixBusinessVo statisticsTbFixBusinessVo1 = new StatisticsTbFixBusinessVo();

			StatisticsTbFixBusinessVo statisticsTbFixBusinessVo2 = new StatisticsTbFixBusinessVo();

			int i1 = 0;

			int i2 = 0;

			for (TbBusinessBalance t : tbBusinessBalanceList) {

				if (null != t.getTbFixEntrust()) {

					d1 = d1.add(new BigDecimal(String.valueOf(t
							.getBalanceTotalAll())));

					d1_owe = d1_owe.add(new BigDecimal(String.valueOf(t
							.getOweAmount())));

					i1++;
				} else {

					d2 = d2.add(new BigDecimal(String.valueOf(t
							.getBalanceTotalAll())));

					d2_owe = d2_owe.add(new BigDecimal(String.valueOf(t
							.getOweAmount())));

					i2++;
				}

			}

			statisticsTbFixBusinessVo1.setBalanceItemName("修理销售");

			statisticsTbFixBusinessVo1.setCountNum(i1);

			statisticsTbFixBusinessVo1.setBalanceItemAmount(d1.doubleValue());

			statisticsTbFixBusinessVo1.setBalanceItemOweAmount(d1_owe
					.doubleValue());

			statisticsTbFixBusinessVo2.setBalanceItemName("单独销售");

			statisticsTbFixBusinessVo2.setCountNum(i2);

			statisticsTbFixBusinessVo2.setBalanceItemAmount(d2.doubleValue());

			statisticsTbFixBusinessVoList.add(statisticsTbFixBusinessVo1);

			statisticsTbFixBusinessVoList.add(statisticsTbFixBusinessVo2);

			statisticsTbFixBusinessVo2.setBalanceItemOweAmount(d2_owe
					.doubleValue());
		}

		return statisticsTbFixBusinessVoList;
	}

	public List<StatisticsTbFixBusinessVo> statisticsAll(
			TbBusinessBalance tbBusinessBalance) {

		List<TbBusinessBalance> listAll = this
				.findTbBusinessBalanceToGroup(tbBusinessBalance);

		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = new ArrayList<StatisticsTbFixBusinessVo>();

		StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();

		if (null != listAll && listAll.size() > 0) {

			statisticsTbFixBusinessVo.setAllBalance(listAll.size());

			BigDecimal d_all = new BigDecimal("0.00");

			BigDecimal d_payed = new BigDecimal("0.00");

			BigDecimal d_owe = new BigDecimal("0.00");

			for (TbBusinessBalance t : listAll) {

				d_all = d_all.add(new BigDecimal(String.valueOf(t
						.getBalanceTotalAll())));

				d_payed = d_payed.add(new BigDecimal(String.valueOf(t
						.getShouldPayAmount())));

				d_owe = d_owe.add(new BigDecimal(String.valueOf(t
						.getOweAmount())));
			}

			statisticsTbFixBusinessVo.setBalanceItemAmount(d_all.doubleValue());

			statisticsTbFixBusinessVo.setBalanceItemPay(d_payed.doubleValue());

			statisticsTbFixBusinessVo.setBalanceItemOweAmount(d_owe
					.doubleValue());

			StatisticsTbFixBusinessVo st = tbReceiveFreeService
					.staticsTbReceiveFree(listAll);

			statisticsTbFixBusinessVo.setFreeBalance(st.getFreeBalance());

			statisticsTbFixBusinessVo.setBalanceItemFree(st
					.getBalanceItemFree());

			StatisticsTbFixBusinessVo reBalance = this
					.staticsReBalance(tbBusinessBalance);

			statisticsTbFixBusinessVo.setReBalance(reBalance.getCountNum());

			/**
			 * 转换为E3的属性列
			 */
			StatisticsTbFixBusinessVo s_balanceItemAmount_show = new StatisticsTbFixBusinessVo();

			s_balanceItemAmount_show.setStaticsShow("结算总金额");

			s_balanceItemAmount_show.setStaticsVal(statisticsTbFixBusinessVo
					.getBalanceItemAmount());

			// 结算总金额
			ActionContext.getContext().put(
					"totalAll",
					new BigDecimal(statisticsTbFixBusinessVo
							.getBalanceItemAmount()).setScale(2,
							BigDecimal.ROUND_HALF_UP));
			// 被减数
			BigDecimal substract = new BigDecimal(
					statisticsTbFixBusinessVo.getBalanceItemAmount()).divide(
					new BigDecimal(1.17d), 2, BigDecimal.ROUND_HALF_UP);

			StatisticsTbFixBusinessVo s_allBalance_show = new StatisticsTbFixBusinessVo();

			s_allBalance_show.setStaticsShow("共计结算");

			s_allBalance_show.setStaticsVal(statisticsTbFixBusinessVo
					.getAllBalance() + " " + "笔");

			StatisticsTbFixBusinessVo s_balanceItemPay_show = new StatisticsTbFixBusinessVo();

			s_balanceItemPay_show.setStaticsShow("收款金额");

			s_balanceItemPay_show.setStaticsVal(statisticsTbFixBusinessVo
					.getBalanceItemAmount());

			StatisticsTbFixBusinessVo s_balanceItemOweAmount_show = new StatisticsTbFixBusinessVo();

			s_balanceItemOweAmount_show.setStaticsShow("欠款金额");

			s_balanceItemOweAmount_show.setStaticsVal(statisticsTbFixBusinessVo
					.getBalanceItemOweAmount());

			StatisticsTbFixBusinessVo s_freeBalance_show = new StatisticsTbFixBusinessVo();

			s_freeBalance_show.setStaticsShow("减免");

			s_freeBalance_show.setStaticsVal(statisticsTbFixBusinessVo
					.getFreeBalance() + " " + "笔");

			StatisticsTbFixBusinessVo s_balanceItemFree_show = new StatisticsTbFixBusinessVo();

			s_balanceItemFree_show.setStaticsShow("减免金额");

			s_balanceItemFree_show.setStaticsVal(statisticsTbFixBusinessVo
					.getBalanceItemFree());

			StatisticsTbFixBusinessVo s_reBalance_show = new StatisticsTbFixBusinessVo();

			s_reBalance_show.setStaticsShow("再结算");

			s_reBalance_show.setStaticsVal(statisticsTbFixBusinessVo
					.getReBalance() + " " + "笔");

			statisticsTbFixBusinessVoList.add(s_balanceItemAmount_show);

			statisticsTbFixBusinessVoList.add(s_allBalance_show);

			statisticsTbFixBusinessVoList.add(s_balanceItemPay_show);

			statisticsTbFixBusinessVoList.add(s_balanceItemOweAmount_show);

			statisticsTbFixBusinessVoList.add(s_freeBalance_show);

			statisticsTbFixBusinessVoList.add(s_balanceItemFree_show);

			statisticsTbFixBusinessVoList.add(s_reBalance_show);
		}

		return statisticsTbFixBusinessVoList;
	}

	public StatisticsTbFixBusinessVo staticsReBalance(
			TbBusinessBalance tbBusinessBalance) {

		String sql_db = "";

		String sql_db_join = "";

		String sql_condition = " where 1 = 1 ";

		if (null != tbBusinessBalance) {

			if (null != tbBusinessBalance.getBananceDateStart()) {

				sql_condition += " and cast(b.BANANCE_DATE as varchar)>=" + "'"
						+ tbBusinessBalance.getBananceDateStart() + "'";

			}
			if (null != tbBusinessBalance.getBananceDateEnd()) {

				sql_condition += " and cast(b.BANANCE_DATE as varchar)<=" + "'"
						+ tbBusinessBalance.getBananceDateEnd() + "'";

			}

			if (null != tbBusinessBalance.getTmModelTypeId()) {

				sql_db += ",tb_fix_entrust c,tb_car_info d,TM_CAR_MODEL_TYPE e ";

				sql_db_join += " and b.entrust_id = c.id and c.CAR_INFO_ID = d.id and  d.MODEL_TYPE_ID= e.id ";

				sql_condition += " and e.id = "
						+ tbBusinessBalance.getTmModelTypeId();

			}

		}

		String sql = "select count(*) from (select count(*) as countNum from tb_business_balance b"
				+ sql_db
				+ sql_condition
				+ sql_db_join
				+ " group by balance_code ) t where t.countNum > 1";

		List<Object[]> list = tbBusinessBalanceDao.findByOriginSql(sql, null);

		if (null != list && list.size() > 0) {

			StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();

			statisticsTbFixBusinessVo.setCountNum(Integer.valueOf(String
					.valueOf(list.get(0))));

			return statisticsTbFixBusinessVo;
		}
		return null;
	}

	public void printTbBusinessBalanceTemplate(OutputStream os, String tpl,
			Long id, String companyName) {
		// 结算单信息
		TbBusinessBalance tbBusinessBalance = this.findById(id);
		// 委托书信息
		TbFixEntrust tbFixEntrust = tbFixEntrustService
				.findById(tbBusinessBalance.getTbFixEntrust().getId());
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

		// 发料配件列表
		List<TbMaintianVo> maintianvos = tbMaintainPartContentService
				.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
						Constants.BALANCE_ALL);
		// 销售单列表
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
				.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
						Constants.BALANCE_ALL);
		// 结算项目列表
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
				.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance
						.getId());

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

				tbMaintianVo.setProjectType(tmStockOutDetVo.getProjectType());

				tbMaintianVo.setZl(tmStockOutDetVo.getZl());

				tbMaintianVo.setXmlx(tmStockOutDetVo.getXmlx());

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

		int fixSize = (tbFixEntrustContentListAdd == null ? 0
				: tbFixEntrustContentListAdd.size());

		int partSize = (maintianvosAdd == null ? 0 : maintianvosAdd.size());

		try {
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

			HSSFCellStyle styleRight = workbook.createCellStyle();

			styleRight.setWrapText(true);

			styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

			styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleRight.setFont(font);

			HSSFCellStyle styleBorderThinAll = workbook.createCellStyle();

			styleBorderThinAll.setBorderLeft(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAll.setBorderRight(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAll.setBorderTop(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAll.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAll.setWrapText(true);

			styleBorderThinAll.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			styleBorderThinAll
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleBorderThinAll.setFont(font);

			HSSFCellStyle styleBorderThickLeft = workbook.createCellStyle();

			styleBorderThickLeft.setBorderLeft(HSSFCellStyle.BORDER_THICK);

			styleBorderThickLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);

			styleBorderThickLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);

			styleBorderThickLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			styleBorderThickLeft.setWrapText(true);

			styleBorderThickLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			styleBorderThickLeft
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleBorderThickLeft.setFont(font);

			HSSFCellStyle styleBorderThickRight = workbook.createCellStyle();

			styleBorderThickRight.setBorderRight(HSSFCellStyle.BORDER_THICK);

			styleBorderThickRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);

			styleBorderThickRight.setBorderTop(HSSFCellStyle.BORDER_THIN);

			styleBorderThickRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			styleBorderThickRight.setWrapText(true);

			styleBorderThickRight.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			styleBorderThickRight
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleBorderThickRight.setFont(font);

			HSSFCellStyle styleBorderThinAllCenter = workbook.createCellStyle();

			styleBorderThinAllCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAllCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAllCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAllCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			styleBorderThinAllCenter.setWrapText(true);

			styleBorderThinAllCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			styleBorderThinAllCenter
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleBorderThinAllCenter.setFont(font);

			HSSFCellStyle styleBorderThickLeftCenter = workbook
					.createCellStyle();

			styleBorderThickLeftCenter
					.setBorderLeft(HSSFCellStyle.BORDER_THICK);

			styleBorderThickLeftCenter
					.setBorderRight(HSSFCellStyle.BORDER_THIN);

			styleBorderThickLeftCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);

			styleBorderThickLeftCenter
					.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			styleBorderThickLeftCenter.setWrapText(true);

			styleBorderThickLeftCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			styleBorderThickLeftCenter
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleBorderThickLeftCenter.setFont(font);

			HSSFCellStyle styleBorderThickRightCenter = workbook
					.createCellStyle();

			styleBorderThickRightCenter
					.setBorderRight(HSSFCellStyle.BORDER_THICK);

			styleBorderThickRightCenter
					.setBorderLeft(HSSFCellStyle.BORDER_THIN);

			styleBorderThickRightCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);

			styleBorderThickRightCenter
					.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			styleBorderThickRightCenter.setWrapText(true);

			styleBorderThickRightCenter
					.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			styleBorderThickRightCenter
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleBorderThickRightCenter.setFont(font);

			HSSFCellStyle styleCenter = workbook.createCellStyle();

			styleCenter.setWrapText(true);

			styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleCenter.setFont(font);

			HSSFRow row = null;

			HSSFCell cell = null;

			row = sheet.getRow(2);

			cell = row.getCell(4);

			// cell.setCellStyle(style);

			if ("dfbz".equals(companyName)) {
				cell.setCellValue(Constants.getCompanyMap().get("dfbzCode"));
			} else if ("xtl".equals(companyName)) {

				cell.setCellValue(Constants.getCompanyMap().get("xtlCode"));
			}

			row = sheet.getRow(2);

			cell = row.getCell(17);

			if ("xtl".equals(companyName)) {
				cell.setCellValue(/* tmCompany.getCompanyName() */"成都精典腾龙");
			} else {
				cell.setCellValue(tmCompany.getCompanyName());
			}

			row = sheet.getRow(2);

			cell = row.getCell(33);

			cell.setCellValue(tmCompany.getCompanyPhone() == null ? ""
					: tmCompany.getCompanyPhone());

			row = sheet.getRow(4);

			cell = row.getCell(4);

			if ("xtl".equals(companyName)) {
				cell.setCellValue("");
			} else {

				cell.setCellValue(tmCompany.getCompanyZipCode() == null ? ""
						: tmCompany.getCompanyZipCode());
			}

			row = sheet.getRow(4);

			cell = row.getCell(17);

			cell.setCellValue(tmCompany.getCompanyAddress() == null ? ""
					: tmCompany.getCompanyAddress());

			row = sheet.getRow(4);

			cell = row.getCell(33);

			cell.setCellValue(tmCompany.getCompanyFax() == null ? ""
					: tmCompany.getCompanyFax());

			row = sheet.getRow(10);

			cell = row.getCell(2);

			// cell.setCellStyle(style);

			String entrustCode = tbFixEntrust.getEntrustCode();

			String[] es = entrustCode.split("-");

			String newCode = "RO" + es[0].substring(2, 6) + es[1];

			cell.setCellValue(newCode);

			row = sheet.getRow(10);

			cell = row.getCell(24);

			// cell.setCellStyle(style);

			cell.setCellValue((tbFixEntrust.getTmUser().getUserRealName() == null || ""
					.equals(tbFixEntrust.getTmUser().getUserRealName())) ? tbFixEntrust
					.getTmUser().getUserName() : tbFixEntrust.getTmUser()
					.getUserRealName());

			row = sheet.getRow(10);

			cell = row.getCell(40);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbBusinessBalance.getBananceDate(), "yyyy-MM-dd HH:mm"));

			row = sheet.getRow(8);

			cell = row.getCell(40);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbFixEntrust.getFixDate(), "yyyy-MM-dd HH:mm"));

			row = sheet.getRow(12);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getCustomerName());

			row = sheet.getRow(12);

			cell = row.getCell(24);

			cell.setCellValue(tbCarInfo.getLicenseCode());

			row = sheet.getRow(12);

			cell = row.getCell(41);

			cell.setCellValue(tbCarInfo.getTmCarModelType().getModelName());

			row = sheet.getRow(14);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(18);

			cell = row.getCell(12);

			cell.setCellValue(tbCustomer.getTelephone() == null ? ""
					: tbCustomer.getTelephone());

			row = sheet.getRow(14);

			cell = row.getCell(24);

			cell.setCellValue(tbCarInfo.getChassisCode());

			row = sheet.getRow(16);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getAddress() == null ? "" : tbCustomer
					.getAddress());

			row = sheet.getRow(16);

			cell = row.getCell(24);

			cell.setCellValue(CommonMethod.parseDateToString(
					tbCarInfo.getPurchaseDate(), "yyyy-MM-dd"));

			row = sheet.getRow(16);

			cell = row.getCell(41);

			if ("xtl".equals(companyName)) {

				cell.setCellValue(tbFixEntrust.getEnterStationKilo() == null ? ""
						: new BigDecimal(tbFixEntrust.getEnterStationKilo())
								.divide(new BigDecimal("1.00"), 0,
										BigDecimal.ROUND_HALF_UP).toString()
								+ " Km");
			}

			else {

				cell.setCellValue(tbFixEntrust.getEnterStationKilo() == null ? ""
						: new BigDecimal(tbFixEntrust.getEnterStationKilo())
								.divide(new BigDecimal("1.00"), 0,
										BigDecimal.ROUND_HALF_UP).toString()
								+ "   Km");

			}

			row = sheet.getRow(18);

			cell = row.getCell(4);

			cell.setCellValue(tbCustomer.getContractPerson() == null ? ""
					: tbCustomer.getContractPerson());

			row = sheet.getRow(18);

			cell = row.getCell(24);

			cell.setCellValue(tbCarInfo.getEngineCode() == null ? ""
					: tbCarInfo.getEngineCode());

			row = sheet.getRow(18);

			cell = row.getCell(41);

			cell.setCellValue(tbCarInfo.getColor() == null ? "" : tbCarInfo
					.getColor());

			/*
			 * if (null != tbBusinessBalanceItemList &&
			 * tbBusinessBalanceItemList.size() > 0) {
			 * 
			 * for (TbBusinessBalanceItem tbBusinessBalanceItem :
			 * tbBusinessBalanceItemList) {
			 * 
			 * if ("XLCLF".equals(tbBusinessBalanceItem .getBalanceItemCode()))
			 * {
			 * 
			 * row = sheet.getRow(77);
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellValue(new BigDecimal(tbBusinessBalanceItem
			 * .getBalanceItemTotal()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * continue; }
			 * 
			 * if ("XLGSF".equals(tbBusinessBalanceItem .getBalanceItemCode()))
			 * {
			 * 
			 * row = sheet.getRow(75);
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellValue(new BigDecimal(tbBusinessBalanceItem
			 * .getBalanceItemTotal()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * continue; }
			 * 
			 * if ("ZJE" .equals(tbBusinessBalanceItem.getBalanceItemCode())) {
			 * 
			 * row = sheet.getRow(81);
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellValue(new BigDecimal(tbBusinessBalanceItem
			 * .getBalanceItemTotal()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * continue; }
			 * 
			 * }
			 * 
			 * row = sheet.getRow(79);
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellValue("0.00");
			 * 
			 * }
			 */
			BigDecimal d_f_w = new BigDecimal("0.00");

			BigDecimal d_f_p = new BigDecimal("0.00");

			BigDecimal d_f_i = new BigDecimal("0.00");

			BigDecimal d_f_c = new BigDecimal("0.00");

			if (fixSize > 0) {

				for (int i = 0; i < fixSize; i++) {

					TbFixEntrustContent t = tbFixEntrustContentListAdd.get(i);

					if ("W".equals(t.getZl())) {

						d_f_w = d_f_w.add(new BigDecimal(t.getFixHourAll()));

					}

					else if ("P".equals(t.getZl())) {

						d_f_p = d_f_p.add(new BigDecimal(t.getFixHourAll()));

					}

					else if ("I".equals(t.getZl())) {

						d_f_i = d_f_i.add(new BigDecimal(t.getFixHourAll()));

					}

					else if ("C".equals(t.getZl())) {

						d_f_c = d_f_c.add(new BigDecimal(t.getFixHourAll()));

					}
				}

			}

			BigDecimal d_p_w = new BigDecimal("0.00");

			BigDecimal d_p_p = new BigDecimal("0.00");

			BigDecimal d_p_i = new BigDecimal("0.00");

			BigDecimal d_p_c = new BigDecimal("0.00");

			if (partSize > 0) {

				for (int i = 0; i < partSize; i++) {

					TbMaintianVo t = maintianvosAdd.get(i);

					if ("W".equals(t.getZl())) {

						d_p_w = d_p_w.add(new BigDecimal(t.getTotal()));

					}

					else if ("P".equals(t.getZl())) {

						d_p_p = d_p_p.add(new BigDecimal(t.getTotal()));

					}

					else if ("I".equals(t.getZl())) {

						d_p_i = d_p_i.add(new BigDecimal(t.getTotal()));

					}

					else if ("C".equals(t.getZl())) {

						d_p_c = d_p_c.add(new BigDecimal(t.getTotal()));

					}

				}

			}

			row = sheet.getRow(75);

			cell = row.getCell(6);

			cell.setCellValue(d_f_w.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(77);

			cell = row.getCell(6);

			cell.setCellValue(d_p_w.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(79);

			cell = row.getCell(6);

			cell.setCellValue("0.00");

			row = sheet.getRow(81);

			cell = row.getCell(6);

			cell.setCellValue(d_f_w
					.add(d_p_w)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(75);

			cell = row.getCell(12);

			cell.setCellValue(d_f_p.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(77);

			cell = row.getCell(12);

			cell.setCellValue(d_p_p.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(79);

			cell = row.getCell(12);

			cell.setCellValue("0.00");

			row = sheet.getRow(81);

			cell = row.getCell(12);

			cell.setCellValue(d_f_p
					.add(d_p_p)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(75);

			cell = row.getCell(18);

			cell.setCellValue(d_f_i.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(77);

			cell = row.getCell(18);

			cell.setCellValue(d_p_i.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(79);

			cell = row.getCell(18);

			cell.setCellValue("0.00");

			row = sheet.getRow(81);

			cell = row.getCell(18);

			cell.setCellValue(d_f_i
					.add(d_p_i)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(75);

			cell = row.getCell(24);

			cell.setCellValue(d_f_c.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(77);

			cell = row.getCell(24);

			cell.setCellValue(d_p_c.divide(new BigDecimal("1.00"), 2,
					BigDecimal.ROUND_HALF_UP).toString());

			row = sheet.getRow(79);

			cell = row.getCell(24);

			cell.setCellValue("0.00");

			row = sheet.getRow(81);

			cell = row.getCell(24);

			cell.setCellValue(d_f_c
					.add(d_p_c)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			Double wt = tbBusinessBalance.getWorkingHourTotalAll() == null ? 0d
					: tbBusinessBalance.getWorkingHourTotalAll();

			Double wf = tbBusinessBalance.getWorkingHourFavourRate() == null ? 0d
					: tbBusinessBalance.getWorkingHourFavourRate();

			Double pt = tbBusinessBalance.getFixPartTotalAll() == null ? 0d
					: tbBusinessBalance.getFixPartTotalAll();

			Double pf = tbBusinessBalance.getFixPartFavourRate() == null ? 0d
					: tbBusinessBalance.getFixPartFavourRate();

			Double st = tbBusinessBalance.getSolePartTotalAll() == null ? 0d
					: tbBusinessBalance.getSolePartTotalAll();

			Double sf = tbBusinessBalance.getSolePartFavourRate() == null ? 0d
					: tbBusinessBalance.getSolePartFavourRate();

			BigDecimal fixF = new BigDecimal(wt).divide(new BigDecimal(1 - wf),
					2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(wf));

			BigDecimal partF = new BigDecimal(pt).divide(
					new BigDecimal(1 - pf), 2, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(pf));

			BigDecimal soleF = new BigDecimal(st).divide(
					new BigDecimal(1 - sf), 2, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(sf));

			row = sheet.getRow(83);

			cell = row.getCell(4);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(fixF
					.add(partF)
					.add(soleF)
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(83);

			cell = row.getCell(14);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(new BigDecimal(tbBusinessBalance.getPayedAmount())
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(68);

			cell = row.getCell(40);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(CommonMethod.parseDateToString(tbBusinessBalance
					.getTbFixEntrust().getRemindMaintainDate(), "yyyy-MM-dd"));

			row = sheet.getRow(70);

			cell = row.getCell(40);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(tbBusinessBalance.getTbFixEntrust()
					.getRemindMaintainKilo() == null ? ""
					: new BigDecimal(tbBusinessBalance.getTbFixEntrust()
							.getRemindMaintainKilo()).divide(new BigDecimal(
							"1.00"), 0, BigDecimal.ROUND_HALF_UP)
							+ "");

			row = sheet.getRow(77);

			cell = row.getCell(30);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(tbBusinessBalance.getTbFixEntrust().getRemark());

			ByteArrayOutputStream byteArrayOutImgLion = new ByteArrayOutputStream();

			String pic = "";

			if ("dfbz".equals(companyName)) {
				pic = "/lion_jsd.png";
			} else if ("xtl".equals(companyName)) {

				pic = "/xtl_jsd.png";
			}

			BufferedImage bufferImgLion = ImageIO.read(this.getClass()
					.getResourceAsStream(pic));

			ImageIO.write(bufferImgLion, "png", byteArrayOutImgLion);

			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

			HSSFClientAnchor anchorLion = new HSSFClientAnchor(0, 0, 1023, 200,
					(short) 41, 0, (short) 46, 7);

			patriarch.createPicture(anchorLion, workbook.addPicture(
					byteArrayOutImgLion.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_PNG));

			/*
			 * int maxSize = 19;
			 * 
			 * int maxFixSize = (fixSize > 19 ? 19 : fixSize);
			 * 
			 * int maxPartSize = maxSize - maxFixSize > partSize ? partSize :
			 * maxSize - maxFixSize;
			 * 
			 * int partStartRow = (fixSize == 0 ? 21 : 21 + maxFixSize * 2 + 4);
			 * 
			 * int partStartRowClone = ((fixSize - maxFixSize) == 0 ? 21 : 21 +
			 * (fixSize - maxFixSize) * 2 + 4);
			 * 
			 * int minBlank = 9;
			 * 
			 * if ((fixSize + partSize) > 17) {
			 * 
			 * HSSFSheet sheetClone = workbook.cloneSheet(0);
			 * 
			 * HSSFRow rowClone = sheetClone.getRow(87);
			 * 
			 * HSSFCell cellClone = rowClone.getCell(34);
			 * 
			 * cellClone.setCellValue("第2页 ，共 2 页 ");
			 * 
			 * HSSFPatriarch patriarchClone = sheetClone
			 * .createDrawingPatriarch();
			 * 
			 * HSSFClientAnchor anchorLionClone = new HSSFClientAnchor(0, 0,
			 * 1023, 200, (short) 41, 0, (short) 47, 8);
			 * 
			 * patriarchClone.createPicture(anchorLionClone, workbook
			 * .addPicture(byteArrayOutImgLion.toByteArray(),
			 * HSSFWorkbook.PICTURE_TYPE_PNG));
			 * 
			 * if ((fixSize - maxFixSize) > 0) {
			 * 
			 * 
			 * sheetClone.addMergedRegion(new Region(21, (short) 0, 21, (short)
			 * 49));
			 * 
			 * sheetClone.addMergedRegion(new Region(21, (short) 0, 21, (short)
			 * 0));
			 * 
			 * 
			 * sheetClone.addMergedRegion(new CellRangeAddress(21, 21, 0, 49));
			 * 
			 * rowClone = sheetClone.getRow(21);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(style);
			 * 
			 * cellClone.setCellValue("维修项目费用明细");
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeft);
			 * 
			 * rowClone = sheetClone.getRow(21);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeft);
			 * 
			 * rowClone = sheetClone.getRow(24);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * rowClone = sheetClone.getRow(23);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cellClone.setCellValue("序号");
			 * 
			 * cellClone = rowClone.getCell(2);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("工时编码");
			 * 
			 * cellClone = rowClone.getCell(10);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("维修项目");
			 * 
			 * cellClone = rowClone.getCell(21);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("帐类");
			 * 
			 * cellClone = rowClone.getCell(24);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("项目类型");
			 * 
			 * cellClone = rowClone.getCell(30);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("工时");
			 * 
			 * cellClone = rowClone.getCell(34);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("工时单价");
			 * 
			 * cellClone = rowClone.getCell(40);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("折扣率");
			 * 
			 * cellClone = rowClone.getCell(44);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("工时费用");
			 * 
			 * for (int i = 0; i < (fixSize - maxFixSize); i++) {
			 * 
			 * TbFixEntrustContent tbFixEntrustContent =
			 * tbFixEntrustContentListAdd .get(i + maxFixSize);
			 * 
			 * rowClone = sheetClone.getRow(25 + i * 2);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(i + 1);
			 * 
			 * cellClone = rowClone.getCell(2);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbFixEntrustContent .getStationCode());
			 * 
			 * cellClone = rowClone.getCell(10);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbFixEntrustContent .getStationName());
			 * 
			 * cellClone = rowClone.getCell(21);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbFixEntrustContent.getZl());
			 * 
			 * cellClone = rowClone.getCell(24);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbFixEntrustContent .getProjectType());
			 * 
			 * cellClone = rowClone.getCell(30);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(new BigDecimal(
			 * tbFixEntrustContent.getFixHour()).divide( new BigDecimal("1.00"),
			 * 2, BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cellClone = rowClone.getCell(34);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(new BigDecimal(
			 * tbFixEntrustContent.getWorkingHourPrice()) .divide(new
			 * BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cellClone = rowClone.getCell(40);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(new BigDecimal(tbBusinessBalance
			 * .getWorkingHourFavourRate()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cellClone = rowClone.getCell(44);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(new BigDecimal(
			 * tbFixEntrustContent.getFixHourAll()).divide( new
			 * BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP).toString()); }
			 * 
			 * }
			 * 
			 * if ((partSize - maxPartSize) > 0) {
			 * 
			 * 
			 * sheetClone.addMergedRegion(new Region(partStartRowClone, (short)
			 * 0, partStartRowClone, (short) 49));
			 * 
			 * sheetClone.addMergedRegion(new Region(partStartRowClone, (short)
			 * 0, partStartRowClone, (short) 49));
			 * 
			 * 
			 * sheetClone.addMergedRegion(new CellRangeAddress(
			 * partStartRowClone, partStartRowClone, 0, 49));
			 * 
			 * rowClone = sheetClone.getRow(partStartRowClone);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(style);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeft);
			 * 
			 * cellClone.setCellValue("维修零件费用明细");
			 * 
			 * rowClone = sheetClone.getRow(partStartRowClone + 1);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeft);
			 * 
			 * rowClone = sheetClone.getRow(partStartRowClone + 2 + 1);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * rowClone = sheetClone.getRow(partStartRowClone + 2);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cellClone.setCellValue("序号");
			 * 
			 * cellClone = rowClone.getCell(2);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("零件编号");
			 * 
			 * cellClone = rowClone.getCell(10);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("零件名称");
			 * 
			 * cellClone = rowClone.getCell(21);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("帐类");
			 * 
			 * cellClone = rowClone.getCell(24);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("维修项目类型");
			 * 
			 * cellClone = rowClone.getCell(30);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("数量");
			 * 
			 * cellClone = rowClone.getCell(34);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("单价");
			 * 
			 * cellClone = rowClone.getCell(40);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("折扣率");
			 * 
			 * cellClone = rowClone.getCell(44);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("零件费用");
			 * 
			 * for (int i = 0; i < (partSize - maxPartSize); i++) {
			 * 
			 * TbMaintianVo tbMaintianVo = maintianvosAdd.get(i + maxPartSize);
			 * 
			 * rowClone = sheetClone.getRow(partStartRowClone + 4 + i 2);
			 * 
			 * if(null == rowClone) break;
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(i + 1);
			 * 
			 * cellClone = rowClone.getCell(2);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbMaintianVo.getPartCode());
			 * 
			 * cellClone = rowClone.getCell(10);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbMaintianVo.getPartName());
			 * 
			 * cellClone = rowClone.getCell(21);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbMaintianVo.getZl());
			 * 
			 * cellClone = rowClone.getCell(24);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(tbMaintianVo.getProjectType());
			 * 
			 * cellClone = rowClone.getCell(30);
			 * 
			 * cellClone.setCellStyle(styleRight);
			 * 
			 * cellClone.setCellValue(new BigDecimal(tbMaintianVo
			 * .getPartQuantity()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cellClone = rowClone.getCell(34);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(new BigDecimal(tbMaintianVo
			 * .getPrice()).divide(new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cellClone = rowClone.getCell(40);
			 * 
			 * // .setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue(new BigDecimal(tbBusinessBalance
			 * .getFixPartFavourRate()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cellClone = rowClone.getCell(44);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone .setCellValue(new BigDecimal(tbMaintianVo
			 * .getPartQuantity()) .multiply( new BigDecimal(tbMaintianVo
			 * .getPrice())) .divide(new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP) .toString()); }
			 * 
			 * }
			 * 
			 * int attachStartRowClone = 21;
			 * 
			 * if ((fixSize - maxFixSize + partSize - maxPartSize) <= 17) {
			 * 
			 * if ((fixSize - maxFixSize) != 0 && (partSize - maxPartSize) == 0
			 * && (fixSize - maxFixSize) < 18) {
			 * 
			 * attachStartRowClone = 21 + 2 + 2 + (fixSize - maxFixSize) * 2;
			 * 
			 * }
			 * 
			 * if ((partSize - maxPartSize) != 0 && (fixSize - maxFixSize) == 0
			 * && (partSize - maxPartSize) < 18) {
			 * 
			 * attachStartRowClone = 21 + 2 + 2 + 2 + (partSize - maxPartSize) *
			 * 2; }
			 * 
			 * if ((fixSize - maxFixSize) != 0 && (partSize - maxPartSize) != 0
			 * && (fixSize - maxFixSize + partSize - maxPartSize < 18)) {
			 * 
			 * attachStartRowClone = 21 + 2 + 2 + 2 + 2 + (fixSize - maxFixSize)
			 * * 2 + (partSize - maxPartSize) * 2;
			 * 
			 * }
			 * 
			 * 
			 * sheetClone.addMergedRegion(new Region(attachStartRowClone,
			 * (short) 0, attachStartRowClone, (short) 49));
			 * 
			 * sheetClone.addMergedRegion(new Region(attachStartRowClone,
			 * (short) 0, attachStartRowClone, (short) 49));
			 * 
			 * 
			 * sheetClone.addMergedRegion(new CellRangeAddress(
			 * attachStartRowClone, attachStartRowClone, 0, 49));
			 * 
			 * rowClone = sheetClone.getRow(attachStartRowClone);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(style);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeft);
			 * 
			 * cellClone.setCellValue("附件费用明细");
			 * 
			 * rowClone = sheetClone.getRow(attachStartRowClone + 1);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeft);
			 * 
			 * rowClone = sheetClone.getRow(attachStartRowClone + 2 + 1);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * rowClone = sheetClone.getRow(attachStartRowClone + 2);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cellClone.setCellValue("序号");
			 * 
			 * if ("dfbz".equals(companyName)) {
			 * 
			 * cellClone = rowClone.getCell(10);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("零件名称");
			 * 
			 * cellClone = rowClone.getCell(21);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("帐类");
			 * 
			 * cellClone = rowClone.getCell(24);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("维修项目类型");
			 * 
			 * cellClone = rowClone.getCell(30);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("数量");
			 * 
			 * cellClone = rowClone.getCell(34);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("单价");
			 * 
			 * cellClone = rowClone.getCell(40);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * } else if ("xtl".equals(companyName)) {
			 * 
			 * 
			 * sheetClone.addMergedRegion(new Region(attachStartRowClone + 2,
			 * (short) 2, attachStartRowClone + 2, (short) 39));
			 * 
			 * sheetClone.addMergedRegion(new Region(attachStartRowClone + 2,
			 * (short) 2, attachStartRowClone + 2, (short) 39));
			 * 
			 * 
			 * sheetClone.addMergedRegion(new CellRangeAddress(
			 * attachStartRowClone + 2, attachStartRowClone + 2, 2, 39));
			 * 
			 * cellClone = rowClone.getCell(2);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("附加项目");
			 * 
			 * }
			 * 
			 * cellClone = rowClone.getCell(40);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("折扣率");
			 * 
			 * cellClone = rowClone.getCell(44);
			 * 
			 * cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellValue("零件费用");
			 * 
			 * rowClone = sheetClone.getRow(attachStartRowClone + 4);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * // cellClone.setCellStyle(styleCenter);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cellClone.setCellValue("1");
			 * 
			 * rowClone = sheetClone.getRow(attachStartRowClone + 4 + 1);
			 * 
			 * cellClone = rowClone.getCell(0);
			 * 
			 * cellClone.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * 
			 * sheetClone.addMergedRegion(new Region(attachStartRowClone + 6,
			 * (short) 0, 66, (short) 49));
			 * 
			 * sheetClone.addMergedRegion(new Region(attachStartRowClone + 6,
			 * (short) 0, 66, (short) 49));
			 * 
			 * 
			 * sheetClone.addMergedRegion(new CellRangeAddress(
			 * attachStartRowClone + 6, 66, 0, 49));
			 * 
			 * }
			 * 
			 * } else {
			 * 
			 * row = sheet.getRow(87);
			 * 
			 * cell = row.getCell(34);
			 * 
			 * cell.setCellValue("第 1 页 ，共 1 页 ");
			 * 
			 * }
			 * 
			 * if (fixSize > 0) {
			 * 
			 * 
			 * sheet.addMergedRegion(new Region(21, (short) 0, 21, (short) 49));
			 * 
			 * sheet.addMergedRegion(new Region(21, (short) 0, 21, (short) 49));
			 * 
			 * 
			 * sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 49));
			 * 
			 * row = sheet.getRow(21);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(style);
			 * 
			 * cell.setCellValue("维修项目费用明细");
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * row = sheet.getRow(22);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * row = sheet.getRow(24);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * row = sheet.getRow(23);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("序号");
			 * 
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cell = row.getCell(2);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("工时编码");
			 * 
			 * cell = row.getCell(10);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("维修项目");
			 * 
			 * cell = row.getCell(21);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("帐类");
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("项目类型");
			 * 
			 * cell = row.getCell(30);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("工时");
			 * 
			 * cell = row.getCell(34);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("工时单价");
			 * 
			 * cell = row.getCell(40);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("折扣率");
			 * 
			 * cell = row.getCell(44);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("工时费用");
			 * 
			 * for (int i = 0; i < maxFixSize; i++) {
			 * 
			 * TbFixEntrustContent tbFixEntrustContent =
			 * tbFixEntrustContentListAdd .get(i);
			 * 
			 * row = sheet.getRow(25 + i * 2);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(i + 1);
			 * 
			 * cell = row.getCell(2);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbFixEntrustContent.getStationCode());
			 * 
			 * cell = row.getCell(10);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbFixEntrustContent.getStationName());
			 * 
			 * cell = row.getCell(21);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbFixEntrustContent.getZl());
			 * 
			 * cell = row.getCell(24);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbFixEntrustContent.getProjectType());
			 * 
			 * cell = row.getCell(30);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal(tbFixEntrustContent
			 * .getFixHour()).divide(new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cell = row.getCell(34);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal(tbFixEntrustContent
			 * .getWorkingHourPrice()) .divide(new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cell = row.getCell(40);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal((1 - tbBusinessBalance
			 * .getWorkingHourFavourRate()) * 100) .divide(new
			 * BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cell = row.getCell(44);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal(tbFixEntrustContent
			 * .getFixHourAll()).divide(new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString()); }
			 * 
			 * }
			 * 
			 * if ((fixSize <= maxSize - 1) && partSize > 0) {
			 * 
			 * 
			 * sheet.addMergedRegion(new Region(partStartRow, (short) 0,
			 * partStartRow, (short) 49));
			 * 
			 * sheet.addMergedRegion(new Region(partStartRow, (short) 0,
			 * partStartRow, (short) 49));
			 * 
			 * 
			 * sheet.addMergedRegion(new CellRangeAddress(partStartRow,
			 * partStartRow, 0, 49));
			 * 
			 * row = sheet.getRow(partStartRow);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(style);
			 * 
			 * cell.setCellValue("维修零件费用明细");
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * row = sheet.getRow(partStartRow + 1);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * row = sheet.getRow(partStartRow + 2 + 1);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * row = sheet.getRow(partStartRow + 2);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cell.setCellValue("序号");
			 * 
			 * cell = row.getCell(2);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("零件编号");
			 * 
			 * cell = row.getCell(10);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("零件名称");
			 * 
			 * cell = row.getCell(21);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("帐类");
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("维修项目类型");
			 * 
			 * cell = row.getCell(30);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("数量");
			 * 
			 * cell = row.getCell(34);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("单价");
			 * 
			 * cell = row.getCell(40);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("折扣率");
			 * 
			 * cell = row.getCell(44);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("零件费用");
			 * 
			 * if (partSize > 0) {
			 * 
			 * for (int i = 0; i < maxPartSize; i++) {
			 * 
			 * TbMaintianVo tbMaintianVo = maintianvosAdd.get(i);
			 * 
			 * row = sheet.getRow(partStartRow + 4 + i * 2);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(i + 1);
			 * 
			 * cell = row.getCell(2);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbMaintianVo.getPartCode());
			 * 
			 * cell = row.getCell(10);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbMaintianVo.getPartName());
			 * 
			 * cell = row.getCell(21);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbMaintianVo.getZl());
			 * 
			 * cell = row.getCell(24);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(tbMaintianVo.getProjectType());
			 * 
			 * cell = row.getCell(30);
			 * 
			 * cell.setCellStyle(styleRight);
			 * 
			 * cell.setCellValue(new BigDecimal(tbMaintianVo
			 * .getPartQuantity()).divide( new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cell = row.getCell(34);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal(tbMaintianVo
			 * .getPrice()).divide(new BigDecimal("1.00"), 2,
			 * BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cell = row.getCell(40);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal((1 - tbBusinessBalance
			 * .getFixPartFavourRate()) * 100).divide( new BigDecimal("1.00"),
			 * 2, BigDecimal.ROUND_HALF_UP).toString());
			 * 
			 * cell = row.getCell(44);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue(new BigDecimal(tbMaintianVo .getPartQuantity())
			 * .multiply( new BigDecimal(tbMaintianVo.getPrice())) .divide(new
			 * BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP).toString()); }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * int attachStartRow = 21;
			 * 
			 * if ((fixSize + partSize) <= 17) {
			 * 
			 * if (fixSize != 0 && partSize == 0 && fixSize < 18) {
			 * 
			 * attachStartRow = 19 + 2 + 2 + 2 + fixSize * 2;
			 * 
			 * }
			 * 
			 * if (partSize != 0 && fixSize == 0 && partSize < 18) {
			 * 
			 * attachStartRow = 19 + 2 + 2 + 2 + partSize * 2; }
			 * 
			 * if (fixSize != 0 && partSize != 0 && (fixSize + partSize < 18)) {
			 * 
			 * attachStartRow = 19 + 2 + 2 + 2 + 2 + 2 + fixSize * 2 + partSize
			 * * 2;
			 * 
			 * }
			 * 
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow, (short) 0,
			 * attachStartRow, (short) 49));
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow, (short) 0,
			 * attachStartRow, (short) 49));
			 * 
			 * 
			 * sheet.addMergedRegion(new CellRangeAddress(attachStartRow,
			 * attachStartRow, 0, 49));
			 * 
			 * row = sheet.getRow(attachStartRow);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(style);
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * cell.setCellValue("附件费用明细");
			 * 
			 * row = sheet.getRow(attachStartRow + 1);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeft);
			 * 
			 * row = sheet.getRow(attachStartRow + 2 + 1);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * row = sheet.getRow(attachStartRow + 2);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cell.setCellValue("序号");
			 * 
			 * if ("dfbz".equals(companyName)) {
			 * 
			 * cell = row.getCell(10);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("零件名称");
			 * 
			 * cell = row.getCell(21);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("帐类");
			 * 
			 * cell = row.getCell(24);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("维修项目类型");
			 * 
			 * cell = row.getCell(30);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("数量");
			 * 
			 * cell = row.getCell(34);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("单价");
			 * 
			 * cell = row.getCell(40);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * } else if ("xtl".equals(companyName)) {
			 * 
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow + 2, (short) 2,
			 * attachStartRow + 2, (short) 39));
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow + 2, (short) 2,
			 * attachStartRow + 2, (short) 39));
			 * 
			 * 
			 * sheet.addMergedRegion(new CellRangeAddress( attachStartRow + 2,
			 * attachStartRow + 2, 2, 39));
			 * 
			 * cell = row.getCell(2);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("附加项目");
			 * 
			 * }
			 * 
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow + 2, (short) 2,
			 * attachStartRow + 2, (short) 39));
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow + 2, (short) 2,
			 * attachStartRow + 2, (short) 39));
			 * 
			 * cell = row.getCell(2);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("附加项目");
			 * 
			 * 
			 * cell = row.getCell(40);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("折扣率");
			 * 
			 * cell = row.getCell(44);
			 * 
			 * cell.setCellStyle(styleCenter);
			 * 
			 * cell.setCellValue("零件费用");
			 * 
			 * row = sheet.getRow(attachStartRow + 4);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * // cell.setCellStyle(styleCenter);
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * cell.setCellValue("1");
			 * 
			 * row = sheet.getRow(attachStartRow + 4 + 1);
			 * 
			 * cell = row.getCell(0);
			 * 
			 * cell.setCellStyle(styleBorderThickLeftCenter);
			 * 
			 * if ("xtl".equals(companyName)) {
			 * 
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow + 6, (short) 0,
			 * 66, (short) 49));
			 * 
			 * sheet.addMergedRegion(new Region(attachStartRow + 6, (short) 0,
			 * 66, (short) 49));
			 * 
			 * if (attachStartRow + 6 < 66) {
			 * 
			 * sheet.addMergedRegion(new CellRangeAddress( attachStartRow + 6,
			 * 66, 0, 49)); }
			 * 
			 * } }
			 */

			int pageSize = 0;

			if ((fixSize > 0 && partSize == 0)
					|| (partSize > 0 && fixSize == 0)) {

				if ((fixSize + partSize) <= 18) {

					pageSize = 1;

				}

				else {

					pageSize = (fixSize + partSize - 19) / 21 + 2;

				}
				
				
				
				for (int p = pageSize - 1; p >= 0; p--) {

					int maxSize = (p == pageSize - 1) ? (fixSize + partSize - p * 21) : 21;

					int startRow = 21;

					if(p == 0){
						
						sheet = workbook.getSheetAt(0);
						
						for(int k = 1 ; k <= pageSize/2 ; k++){
							
							workbook.setSheetOrder(workbook.getSheetName(k), pageSize - k);
						
						}
						
						
						for(int k = 1 ; k < pageSize ; k++){
							
							workbook.setSheetName(k, "明细" + (k + 1) + "");
							
							
							
						}
						
						workbook.setSheetName(0, "明细" +1 + "");
						
					}
					else{
						sheet = workbook.cloneSheet(0);
						
						//this.clearSheet(sheet, 21, 0, 66, 49);
					}
					
					if (fixSize > 0) {

						sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 49));

						row = sheet.getRow(21);

						cell = row.getCell(0);

						cell.setCellValue("维修项目费用明细");

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(22);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(24);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(23);

						cell = row.getCell(0);

						cell.setCellValue("序号");

						cell.setCellStyle(styleBorderThickLeftCenter);

						cell = row.getCell(2);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时编码");

						cell = row.getCell(10);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("维修项目");

						cell = row.getCell(21);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("帐类");

						cell = row.getCell(24);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("项目类型");

						cell = row.getCell(30);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时");

						cell = row.getCell(34);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时单价");

						cell = row.getCell(40);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("折扣率");

						cell = row.getCell(44);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时费用");

						for (int i = 0; i < maxSize; i++) {

							TbFixEntrustContent tbFixEntrustContent = tbFixEntrustContentListAdd
									.get(p * 21 + i);

							row = sheet.getRow(25 + i * 2);

							cell = row.getCell(0);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(p * 21 + i + 1);

							cell = row.getCell(2);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getStationCode());

							cell = row.getCell(10);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getStationName());

							cell = row.getCell(21);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getZl());

							cell = row.getCell(24);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getProjectType());

							cell = row.getCell(30);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbFixEntrustContent
									.getFixHour()).divide(new BigDecimal("1.00"),
									2, BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(34);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbFixEntrustContent
									.getWorkingHourPrice()).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(40);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal((1 - tbBusinessBalance
									.getWorkingHourFavourRate()) * 100).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(44);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbFixEntrustContent
									.getFixHourAll()).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());
						}

					}

					else if (partSize > 0) {

						sheet.addMergedRegion(new CellRangeAddress(startRow,
								startRow, 0, 49));

						row = sheet.getRow(startRow);

						cell = row.getCell(0);

						// cell.setCellStyle(style);

						cell.setCellValue("维修零件费用明细");

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(startRow + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(startRow + 2 + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						row = sheet.getRow(startRow + 2);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						cell.setCellValue("序号");

						cell = row.getCell(2);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件编号");

						cell = row.getCell(10);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件名称");

						cell = row.getCell(21);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("帐类");

						cell = row.getCell(24);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("维修项目类型");

						cell = row.getCell(30);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("数量");

						cell = row.getCell(34);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("单价");

						cell = row.getCell(40);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("折扣率");

						cell = row.getCell(44);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件费用");

						for (int i = 0 ; i < maxSize; i++) {

							TbMaintianVo tbMaintianVo = maintianvosAdd.get(p * 21 + i);

							row = sheet.getRow(startRow + 4 + i * 2);

							cell = row.getCell(0);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(p * 21 + i + 1);

							cell = row.getCell(2);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getPartCode());

							cell = row.getCell(10);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getPartName());

							cell = row.getCell(21);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getZl());

							cell = row.getCell(24);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getProjectType());

							cell = row.getCell(30);

							cell.setCellStyle(styleRight);

							cell.setCellValue(new BigDecimal(tbMaintianVo
									.getPartQuantity()).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(34);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbMaintianVo
									.getPrice()).divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(40);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal((1 - tbBusinessBalance
									.getFixPartFavourRate()) * 100).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(44);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbMaintianVo
									.getPartQuantity())
									.multiply(
											new BigDecimal(tbMaintianVo.getPrice()))
									.divide(new BigDecimal("1.00"), 2,
											BigDecimal.ROUND_HALF_UP).toString());
						}

					}
					
					row = sheet.getRow(87);

					cell = row.getCell(34);

					cell.setCellValue("第 " + (p + 1)+" 页 ，共  "+ pageSize + " 页 ");
					
					if(p == pageSize - 1){
						
						int attachStartRow = 21 + 2 + 2 + (fixSize + partSize - (pageSize - 1) * 21) * 2;

						
						sheet.addMergedRegion(new CellRangeAddress(attachStartRow,
								attachStartRow, 0, 49));

						row = sheet.getRow(attachStartRow);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						cell.setCellValue("附件费用明细");

						row = sheet.getRow(attachStartRow + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(attachStartRow + 2 + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						row = sheet.getRow(attachStartRow + 2);

						cell = row.getCell(0);

						// cell.setCellStyle(styleCenter);
						cell.setCellStyle(styleBorderThickLeftCenter);

						cell.setCellValue("序号");

						if ("dfbz".equals(companyName)) {

							cell = row.getCell(10);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("零件名称");

							cell = row.getCell(21);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("帐类");

							cell = row.getCell(24);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("维修项目类型");

							cell = row.getCell(30);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("数量");

							cell = row.getCell(34);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("单价");

							cell = row.getCell(40);

							cell.setCellStyle(styleCenter);

						} else if ("xtl".equals(companyName)) {


							cell = row.getCell(2);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("附加项目");

						}


						cell = row.getCell(40);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("折扣率");

						cell = row.getCell(44);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件费用");

						row = sheet.getRow(attachStartRow + 4);

						cell = row.getCell(0);

						// cell.setCellStyle(styleCenter);
						cell.setCellStyle(styleBorderThickLeftCenter);

						cell.setCellValue("1");

						row = sheet.getRow(attachStartRow + 4 + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						if ("xtl".equals(companyName)) {

							if (attachStartRow + 6 < 66) {

								sheet.addMergedRegion(new CellRangeAddress(
										attachStartRow + 6, 66, 0, 49));
							}

						}
						
						
					}
					
					

				}

			}

			
			
			
			
			
			
			
			
			if (fixSize > 0 && partSize > 0) {

				if ((fixSize + partSize) <= 16) {

					pageSize = 1;

				}

				else {

					pageSize = (fixSize + partSize - 17) / 19 + 2;

				}
				
				
				int fixPageSize = fixSize/pageSize;
				
				int partPageSize = partSize/pageSize;
				
				int fixLastPageSize = fixSize - (pageSize - 1) * fixPageSize;
				
				int partLastPageSize = partSize - (pageSize - 1) * partPageSize;
				
				if((fixPageSize + partPageSize) < 19 && fixPageSize != 0){
					
					//if(fixLastPageSize >= partSize){
					if(fixPageSize >= partPageSize){
						
						fixPageSize = (19 - partPageSize) > fixSize ? fixSize : (19 - partPageSize);
						
					}
					
					else{
						
						partPageSize = (19 - fixPageSize) > partSize ? partSize : (19 - fixPageSize);
						
					}
					
				}
				
				fixLastPageSize = fixSize - (pageSize - 1) * fixPageSize;
				
				partLastPageSize = (partSize - (pageSize - 1) * partPageSize) < 0 ? 0 : (partSize - (pageSize - 1) * partPageSize);
				
				for (int p = pageSize - 1; p >= 0; p--) {

					int partStartRow = 21 + 2 + 2 + ((p == pageSize - 1) ? fixLastPageSize : fixPageSize) * 2;

					if(p == 0){
						
						sheet = workbook.getSheetAt(0);
						
						if(pageSize > 1){
							
							
							
							
							for(int k = 1 ; k <= pageSize/2 ; k++){
								
								workbook.setSheetOrder(workbook.getSheetName(k), pageSize - k);
							
							}
							
							
							for(int k = 1 ; k < pageSize ; k++){
								
								workbook.setSheetName(k, "明细" + (k + 1) + "");
								
								
								
							}
						}
						
						
						workbook.setSheetName(0, "明细" +1 + "");
						
					}
					else{
						
						sheet = workbook.cloneSheet(0);
						
					}

					if (fixSize > 0) {

						sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 49));

						row = sheet.getRow(21);

						cell = row.getCell(0);

						cell.setCellValue("维修项目费用明细");

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(22);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(24);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(23);

						cell = row.getCell(0);

						cell.setCellValue("序号");

						cell.setCellStyle(styleBorderThickLeftCenter);

						cell = row.getCell(2);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时编码");

						cell = row.getCell(10);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("维修项目");

						cell = row.getCell(21);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("帐类");

						cell = row.getCell(24);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("项目类型");

						cell = row.getCell(30);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时");

						cell = row.getCell(34);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时单价");

						cell = row.getCell(40);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("折扣率");

						cell = row.getCell(44);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("工时费用");

						for (int i = 0; i < ((p == pageSize - 1) ? fixLastPageSize : fixPageSize); i++) {

							TbFixEntrustContent tbFixEntrustContent = tbFixEntrustContentListAdd
									.get(p * fixPageSize + i);

							row = sheet.getRow(25 + i * 2);

							cell = row.getCell(0);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(p * fixPageSize + i + 1);

							cell = row.getCell(2);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getStationCode());

							cell = row.getCell(10);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getStationName());

							cell = row.getCell(21);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getZl());

							cell = row.getCell(24);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbFixEntrustContent.getProjectType());

							cell = row.getCell(30);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbFixEntrustContent
									.getFixHour()).divide(new BigDecimal("1.00"),
									2, BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(34);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbFixEntrustContent
									.getWorkingHourPrice()).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(40);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal((1 - tbBusinessBalance
									.getWorkingHourFavourRate()) * 100).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(44);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbFixEntrustContent
									.getFixHourAll()).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());
						}

					}

					if (partSize > 0) {

						sheet.addMergedRegion(new CellRangeAddress(partStartRow,
								partStartRow, 0, 49));

						row = sheet.getRow(partStartRow);

						cell = row.getCell(0);

						// cell.setCellStyle(style);

						cell.setCellValue("维修零件费用明细");

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(partStartRow + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(partStartRow + 2 + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						row = sheet.getRow(partStartRow + 2);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						cell.setCellValue("序号");

						cell = row.getCell(2);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件编号");

						cell = row.getCell(10);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件名称");

						cell = row.getCell(21);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("帐类");

						cell = row.getCell(24);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("维修项目类型");

						cell = row.getCell(30);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("数量");

						cell = row.getCell(34);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("单价");

						cell = row.getCell(40);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("折扣率");

						cell = row.getCell(44);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件费用");

						for (int i = 0 ; i < ((p == pageSize - 1) ? partLastPageSize : partPageSize); i++) {

							if(p * partPageSize + i > partSize - 1){
								
								break;
							}
							
							TbMaintianVo tbMaintianVo = maintianvosAdd.get(p * partPageSize + i);

							row = sheet.getRow(partStartRow + 4 + i * 2);

							cell = row.getCell(0);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(p * partPageSize + i + 1);

							cell = row.getCell(2);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getPartCode());

							cell = row.getCell(10);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getPartName());

							cell = row.getCell(21);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getZl());

							cell = row.getCell(24);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(tbMaintianVo.getProjectType());

							cell = row.getCell(30);

							cell.setCellStyle(styleRight);

							cell.setCellValue(new BigDecimal(tbMaintianVo
									.getPartQuantity()).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(34);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbMaintianVo
									.getPrice()).divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(40);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal((1 - tbBusinessBalance
									.getFixPartFavourRate()) * 100).divide(
									new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

							cell = row.getCell(44);

							// cell.setCellStyle(styleCenter);

							cell.setCellValue(new BigDecimal(tbMaintianVo
									.getPartQuantity())
									.multiply(
											new BigDecimal(tbMaintianVo.getPrice()))
									.divide(new BigDecimal("1.00"), 2,
											BigDecimal.ROUND_HALF_UP).toString());
						}

					}
					
					row = sheet.getRow(87);

					cell = row.getCell(34);

					cell.setCellValue("第 " + (p + 1)+" 页 ，共  "+ pageSize + " 页 ");
					
					if(p == pageSize - 1){
						
						int attachStartRow = 21 + 2 + 2 + 2 + 2 + (fixLastPageSize + partLastPageSize) * 2;

						
						sheet.addMergedRegion(new CellRangeAddress(attachStartRow,
								attachStartRow, 0, 49));

						row = sheet.getRow(attachStartRow);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						cell.setCellValue("附件费用明细");

						row = sheet.getRow(attachStartRow + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeft);

						row = sheet.getRow(attachStartRow + 2 + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						row = sheet.getRow(attachStartRow + 2);

						cell = row.getCell(0);

						// cell.setCellStyle(styleCenter);
						cell.setCellStyle(styleBorderThickLeftCenter);

						cell.setCellValue("序号");

						if ("dfbz".equals(companyName)) {

							cell = row.getCell(10);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("零件名称");

							cell = row.getCell(21);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("帐类");

							cell = row.getCell(24);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("维修项目类型");

							cell = row.getCell(30);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("数量");

							cell = row.getCell(34);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("单价");

							cell = row.getCell(40);

							cell.setCellStyle(styleCenter);

						} else if ("xtl".equals(companyName)) {


							cell = row.getCell(2);

							cell.setCellStyle(styleCenter);

							cell.setCellValue("附加项目");

						}


						cell = row.getCell(40);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("折扣率");

						cell = row.getCell(44);

						cell.setCellStyle(styleCenter);

						cell.setCellValue("零件费用");

						row = sheet.getRow(attachStartRow + 4);

						cell = row.getCell(0);

						// cell.setCellStyle(styleCenter);
						cell.setCellStyle(styleBorderThickLeftCenter);

						cell.setCellValue("1");

						row = sheet.getRow(attachStartRow + 4 + 1);

						cell = row.getCell(0);

						cell.setCellStyle(styleBorderThickLeftCenter);

						if ("xtl".equals(companyName)) {

							if (attachStartRow + 6 < 66) {

								sheet.addMergedRegion(new CellRangeAddress(
										attachStartRow + 6, 66, 0, 49));
							}

						}
						
						
					}
					
					

				}

			}

			
			
			
			

			workbook.write(os);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 8 41

	}

	public void exportTbBusinessBalanceGroupXls(OutputStream os, String tpl) {

		WritableWorkbook wwb = null;

		List<TbBusinessBalance> tbBusinessBalanceList = (List<TbBusinessBalance>) ActionContext
				.getContext().getSession().get("tbBusinessBalanceList");

		List<TbBusinessBalance> tbBusinessBalanceXlsList = new ArrayList<TbBusinessBalance>();

		TbBusinessBalance tbBusinessBalance = (TbBusinessBalance) ActionContext
				.getContext().getSession().get("tbBusinessBalance");

		if (null != tbBusinessBalanceList && tbBusinessBalanceList.size() > 0) {

			for (int i = 0; i < tbBusinessBalanceList.size(); i++) {

				TbBusinessBalance temp = tbBusinessBalanceList.get(i);

				temp.setBusinessBalanceIndex(i);

				String djh = temp.getEntrustCode() == null ? temp
						.getStockOutCode() : temp.getEntrustCode();

				temp.setDjh(djh);

				String tmCarModelType = temp.getTbFixEntrust() == null ? ""
						: temp.getTbFixEntrust().getTbCarInfo()
								.getTmCarModelType().getModelCode();

				temp.setTmCarModelType(tmCarModelType);

				String chassisCode = temp.getTbFixEntrust() == null ? "" : temp
						.getTbFixEntrust().getTbCarInfo().getChassisCode();

				temp.setChassisCode(chassisCode);

				String telephone = temp.getTbFixEntrust() == null ? "" : temp
						.getTbFixEntrust().getTbCarInfo().getChassisCode();

				temp.setTelephone(temp.getTbCustomer()== null?"":temp.getTbCustomer().getTelephone());

				String bananceDateStart_s = CommonMethod.parseDateToString(
						temp.getBananceDate(), "yyyy-MM-dd HH:ss:mm");

				temp.setBananceDateStart_s(bananceDateStart_s);

				tbBusinessBalanceXlsList.add(temp);
			}

		}

		try {

			wwb = Workbook.createWorkbook(os);

			WritableSheet ws = wwb.createSheet("Sheet1", 0);

			ws.mergeCells(0, 0, 1, 0);

			Label label = new Label(0, 0,
					"结算日期          "
							+ CommonMethod.parseDateToString(
									tbBusinessBalance.getBananceDateStart(),
									"yyyy-MM-dd")
							+ "  至       "
							+ CommonMethod.parseDateToString(
									tbBusinessBalance.getBananceDateEnd(),
									"yyyy-MM-dd"));

			ws.addCell(label);

			label = new Label(0, tbBusinessBalanceXlsList.size() + 2,
					"业务笔数:     "
							+ ActionContext.getContext().getSession()
									.get("totalCount"));

			ws.addCell(label);

			label = new Label(1, tbBusinessBalanceXlsList.size() + 2,
					"总金额:     "
							+ ActionContext.getContext().getSession()
									.get("total"));

			ws.addCell(label);

			label = new Label(2, tbBusinessBalanceXlsList.size() + 2,
					"收款金额:     "
							+ ActionContext.getContext().getSession()
									.get("payed"));

			ws.addCell(label);

			label = new Label(3, tbBusinessBalanceXlsList.size() + 2,
					"工时费:     "
							+ ActionContext.getContext().getSession()
									.get("fixHour"));

			ws.addCell(label);

			label = new Label(4, tbBusinessBalanceXlsList.size() + 2,
					"材料费:     "
							+ ActionContext.getContext().getSession()
									.get("fixPart"));

			ws.addCell(label);

			label = new Label(0, tbBusinessBalanceXlsList.size() + 3,
					"销售额:     "
							+ ActionContext.getContext().getSession()
									.get("solePart"));

			ws.addCell(label);

			label = new Label(1, tbBusinessBalanceXlsList.size() + 3,
					"其他费:     "
							+ ActionContext.getContext().getSession()
									.get("other"));

			ws.addCell(label);

			label = new Label(2, tbBusinessBalanceXlsList.size() + 3,
					"欠款金额:     "
							+ ActionContext.getContext().getSession()
									.get("owe"));

			ws.addCell(label);

			label = new Label(3, tbBusinessBalanceXlsList.size() + 3,
					"材料成本费:     "
							+ ActionContext.getContext().getSession()
									.get("costTotal"));

			ws.addCell(label);

			label = new Label(4, tbBusinessBalanceXlsList.size() + 3,
					"毛利:     "
							+ ActionContext.getContext().getSession()
									.get("djcb"));

			ws.addCell(label);

			XlsWriter xlsWriter = new XlsWriter(this.getClass()
					.getResourceAsStream(tpl));

			if (xlsWriter.isSuccess()) {

				ws = xlsWriter.exportXls(ws, tbBusinessBalanceXlsList);

				wwb.write();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 
	private void clearSheet(HSSFSheet sheet,int startRow,int startCol,int endRow,int endCol){
		
		for(int i = startRow ; i < endRow ; i++ ){
			
			HSSFRow row = sheet.getRow(i);
			
			for(int j = startCol ; j < endCol ; j++){
				
				HSSFCell cell = row.getCell(j);

				cell.setCellValue("");
			}
			
		}
		
	}
	
	public void exportOweXls(OutputStream os,String tpl,List<TbBusinessBalance> tbBusinessBalanceList){
		
		WritableWorkbook wwb = null;
		
		try{
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			XlsWriter xlsWriter = new XlsWriter(this.getClass().getResourceAsStream(tpl));
			
			if(xlsWriter.isSuccess()){
				
				ws = xlsWriter.exportXls(ws, tbBusinessBalanceList);
				
				wwb.write();
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				wwb.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean updateTbBusinessBalanceReback(String balanceCode){
		
		try{
			
			List<TbBusinessBalance> tbBusinessBalanceList = this.findTbBusinessBalanceByBalanceCode(balanceCode);
			
			for(TbBusinessBalance tbBusinessBalance : tbBusinessBalanceList) {
				
				tbBusinessBalanceItemService.deleteByBalanceId(tbBusinessBalance.getId());
			
				tbReceiveFreeService.deleteByBalanceId(tbBusinessBalance.getId());
				
				this.deleteById(tbBusinessBalance.getId());
				
				TbFixEntrust tbFixEntrust = tbBusinessBalance.getTbFixEntrust();

				if (null != tbFixEntrust && null != tbFixEntrust.getId()) {

					tbFixEntrust = tbFixEntrustService.findById(tbFixEntrust.getId());

					tbFixEntrust.setEntrustStatus(Constants.NOFINISH);

					tbMaintainPartContentService.updateTbMaintainStatusNoBalance(
							tbFixEntrust.getId(), Constants.CONFIRM);

					tmStockOutService.updateTrustBillNotBalance(tbFixEntrust.getEntrustCode(),
							Constants.CONFIRM);

					tbFixEntrustService.update(tbFixEntrust);
					
					tbFixEntrustContentService.updateTbFixEntrustContentUnBalance(tbFixEntrust.getId());
				}
				
				if (null != tbBusinessBalance.getTmStockOut()) {

					tmStockOutService.updateSellStatusNotBalance(tbBusinessBalance
							.getTmStockOut().getId(), Constants.CONFIRM);

				}
			}
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
		
		
		return true;
	}
}
