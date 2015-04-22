package com.selfsoft.business.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.business.dao.ITbBusinessSpecialBalanceDao;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbBusinessSpecialBalance;
import com.selfsoft.business.model.TbBusinessSpecialBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.model.TbSpecialPartContent;
import com.selfsoft.business.model.TbSpecialWorkingContent;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceItemService;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITbSpecialPartContentService;
import com.selfsoft.business.service.ITbSpecialWorkingContentService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.service.ITmCompanyService;
import com.selfsoft.secrity.service.ITmUserService;

@Service("tbBusinessSpecialBalanceService")
public class TbBusinessSpecialBalanceServiceImpl implements
		ITbBusinessSpecialBalanceService {

	@Autowired
	private ITbBusinessSpecialBalanceDao tbBusinessSpecialBalanceDao;

	@Autowired
	private ITbSpecialWorkingContentService tbSpecialWorkingContentService;

	@Autowired
	private ITbSpecialPartContentService tbSpecialPartContentService;

	@Autowired
	private ITbBusinessSpecialBalanceItemService tbBusinessSpecialBalanceItemService;

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
	private ITmCarModelTypeService tmCarModelTypeService;

	@Autowired
	private ITmFixTypeService tmFixTypeService;

	@Autowired
	private ITmUserService tmUserService;

	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbBusinessSpecialBalanceDao.deleteById(id);
	}

	public List<TbBusinessSpecialBalance> findAll() {
		// TODO Auto-generated method stub
		return tbBusinessSpecialBalanceDao.findAll();
	}

	public List<TbBusinessSpecialBalance> findByTbBusinessSpecialBalance(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(TbBusinessSpecialBalance.class);

		if (null != tbBusinessSpecialBalance) {

			if (null != tbBusinessSpecialBalance.getEditCode()
					&& !"".equals(tbBusinessSpecialBalance.getEditCode())) {

				detachedCriteria.add(Restrictions.like("editCode", "%"
						+ tbBusinessSpecialBalance.getEditCode() + "%"));
			}
			/*
			 * if(null!=tbBusinessSpecialBalance.getBananceDateStart()){
			 * detachedCriteria.add(Restrictions.ge("bananceDate",
			 * tbBusinessSpecialBalance.getBananceDateStart())); }
			 * if(null!=tbBusinessSpecialBalance.getBananceDateEnd()){
			 * detachedCriteria.add(Restrictions.le("bananceDate",
			 * tbBusinessSpecialBalance.getBananceDateEnd())); }
			 */
			if (null != tbBusinessSpecialBalance.getSpecialType()) {
				detachedCriteria.add(Restrictions.eq("specialType",
						tbBusinessSpecialBalance.getSpecialType()));
			}
			if (null != tbBusinessSpecialBalance.getBalanceCodeDB()
					&& !"".equals(tbBusinessSpecialBalance.getBalanceCodeDB())) {
				detachedCriteria.add(Restrictions.like("balanceCode", "%"
						+ tbBusinessSpecialBalance.getBalanceCodeDB() + "%"));
			}
			if (null != tbBusinessSpecialBalance.getBalanceCode()
					&& !"".equals(tbBusinessSpecialBalance.getBalanceCode())) {

				List<TbBusinessBalance> tbBusinessBalanceList = tbBusinessBalanceService
						.findTbBusinessBalanceByBalanceCode(tbBusinessSpecialBalance
								.getBalanceCode());

				if (null != tbBusinessBalanceList
						&& tbBusinessBalanceList.size() > 0) {

					detachedCriteria.add(Restrictions.eq(
							"tbBusinessBalance.id", tbBusinessBalanceList
									.get(0).getId()));

				}

			}
			if (null != tbBusinessSpecialBalance.getEntrustCodeDB()
					&& !"".equals(tbBusinessSpecialBalance.getEntrustCodeDB())) {
				detachedCriteria.add(Restrictions.like("entrustCode", "%"
						+ tbBusinessSpecialBalance.getEntrustCodeDB() + "%"));
			}
			// if(null!=tbBusinessSpecialBalance.getTbBusinessBalance()){

			/*
			 * detachedCriteria.createAlias("tbBusinessBalance","tbBusinessBalance"
			 * );
			 * 
			 * if(null!=tbBusinessSpecialBalance.getTbBusinessBalance().
			 * getBalanceCode
			 * ()&&!"".equals(tbBusinessSpecialBalance.getTbBusinessBalance
			 * ().getBalanceCode())){ detachedCriteria.add(Restrictions.like(
			 * "tbBusinessBalance.balanceCode",
			 * "%"+tbBusinessSpecialBalance.getTbBusinessBalance
			 * ().getBalanceCode()+"%")); }
			 */

			if (null != tbBusinessSpecialBalance.getEntrustCode()
					&& !"".equals(tbBusinessSpecialBalance.getEntrustCode())) {
				// detachedCriteria.createAlias("tbBusinessBalance.tbFixEntrust","tbFixEntrust");
				TbFixEntrust tbFixEntrust = tbFixEntrustService
						.findByEntrustCode(tbBusinessSpecialBalance
								.getEntrustCode());

				if (null != tbFixEntrust) {

					TbBusinessBalance tbBusinessBalance = tbBusinessBalanceService
							.findByEntrustId(tbFixEntrust.getId());

					if (null != tbBusinessBalance) {

						detachedCriteria.add(Restrictions.or(Restrictions.eq(
								"tbBusinessBalance.id",
								tbBusinessBalance.getId()), Restrictions.like(
								"entrustId", tbFixEntrust.getId())));

					}

					else {

						detachedCriteria.add(Restrictions.like("entrustId",
								tbFixEntrust.getId()));

					}
				}

			}
			if (null != tbBusinessSpecialBalance.getBananceDateStart()) {
				detachedCriteria.add(Restrictions.ge("bananceDate",
						tbBusinessSpecialBalance.getBananceDateStart()));
			}
			if (null != tbBusinessSpecialBalance.getBananceDateEnd()) {
				detachedCriteria.add(Restrictions
						.le("bananceDate",
								CommonMethod.addDate(tbBusinessSpecialBalance
										.getBananceDateEnd(), 1)));
			}

			// }

		}

		return tbBusinessSpecialBalanceDao.findByCriteria(detachedCriteria,
				tbBusinessSpecialBalance);
	}

	public void insert(TbBusinessSpecialBalance tbBusinessSpecialBalance) {
		// TODO Auto-generated method stub
		tbBusinessSpecialBalanceDao.insert(tbBusinessSpecialBalance);
	}

	public void update(TbBusinessSpecialBalance tbBusinessSpecialBalance) {
		// TODO Auto-generated method stub
		tbBusinessSpecialBalanceDao.update(tbBusinessSpecialBalance);
	}

	public TbBusinessSpecialBalance findById(Long id) {
		// TODO Auto-generated method stub
		return tbBusinessSpecialBalanceDao.findById(id);
	}

	// 插入特殊结算单工时
	public void insertTbSpecialWorkingContent(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {

		if (null != tbBusinessSpecialBalance.getTbSpecialWorkingContents()) {

			Set tbSpecialWorkingContents = tbBusinessSpecialBalance
					.getTbSpecialWorkingContents();

			Iterator it = tbSpecialWorkingContents.iterator();

			while (it.hasNext()) {

				TbSpecialWorkingContent tbSpecialWorkingContent = (TbSpecialWorkingContent) it
						.next();

				tbSpecialWorkingContent
						.setTbBusinessSpecialBalance(tbBusinessSpecialBalance);

				tbSpecialWorkingContentService.insert(tbSpecialWorkingContent);
			}

		}

	}

	// 插入特殊结算单修理材料
	public void insertTbSpecialPartContent(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {

		if (null != tbBusinessSpecialBalance.getTbSpecialPartContents()) {

			Set tbSpecialPartContents = tbBusinessSpecialBalance
					.getTbSpecialPartContents();

			Iterator it = tbSpecialPartContents.iterator();

			while (it.hasNext()) {

				TbSpecialPartContent tbSpecialPartContent = (TbSpecialPartContent) it
						.next();

				tbSpecialPartContent
						.setTbBusinessSpecialBalance(tbBusinessSpecialBalance);

				tbSpecialPartContentService.insert(tbSpecialPartContent);
			}
		}

	}

	// 插入结算项目
	public void insertTbBusinessSpecialBalanceItem(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {

		if (null != tbBusinessSpecialBalance.getTbBusinessSpecialBalanceItems()) {

			Set tbBusinessSpecialBalanceItems = tbBusinessSpecialBalance
					.getTbBusinessSpecialBalanceItems();

			Iterator it = tbBusinessSpecialBalanceItems.iterator();

			while (it.hasNext()) {

				TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem = (TbBusinessSpecialBalanceItem) it
						.next();

				tbBusinessSpecialBalanceItem
						.setTbBusinessSpecialBalance(tbBusinessSpecialBalance);

				tbBusinessSpecialBalanceItemService
						.insert(tbBusinessSpecialBalanceItem);
			}
		}

	}

	// 插入特殊结算单，特殊结算单修理工时，特殊结算单修理材料
	public void insertAll(TbBusinessSpecialBalance tbBusinessSpecialBalance) {

		this.insertTbSpecialWorkingContent(tbBusinessSpecialBalance);

		this.insertTbSpecialPartContent(tbBusinessSpecialBalance);

		this.insertTbBusinessSpecialBalanceItem(tbBusinessSpecialBalance);

		this.insert(tbBusinessSpecialBalance);
	}

	public boolean deleteAll(Long id) {

		try {
			this.deleteById(id);

			tbSpecialWorkingContentService.deleteBySpecialId(id);

			tbSpecialPartContentService.deleteBySpecialId(id);

			tbBusinessSpecialBalanceItemService.deleteBySpecialId(id);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	// 更新--先删除后插入
	public void updateAll(TbBusinessSpecialBalance tbBusinessSpecialBalance) {

		this.update(tbBusinessSpecialBalance);

		Long id = tbBusinessSpecialBalance.getId();

		tbSpecialWorkingContentService.deleteBySpecialId(id);

		tbSpecialPartContentService.deleteBySpecialId(id);

		tbBusinessSpecialBalanceItemService.deleteBySpecialId(id);

		this.insertTbSpecialWorkingContent(tbBusinessSpecialBalance);

		this.insertTbSpecialPartContent(tbBusinessSpecialBalance);

		this.insertTbBusinessSpecialBalanceItem(tbBusinessSpecialBalance);
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
	public Double calcItemFavourAmount(
			TbBusinessSpecialBalance tbBusinessSpecialBalance, String itemCode) {

		BigDecimal d = new BigDecimal("0.00");

		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
				.findBySpecialId(tbBusinessSpecialBalance.getId());

		if (null != tbBusinessSpecialBalanceItemList
				&& tbBusinessSpecialBalanceItemList.size() > 0) {

			for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

				if (itemCode.equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					Double favourRate = 0.00D;

					if ("XLGSF".equals(itemCode)) {

						favourRate = tbBusinessSpecialBalance
								.getWorkingHourFavourRate();

					} else if ("XLCLF".equals(itemCode)) {

						favourRate = tbBusinessSpecialBalance
								.getFixPartFavourRate();

					} else if ("XSJE".equals(itemCode)) {

						favourRate = tbBusinessSpecialBalance
								.getSolePartFavourRate();

					}

					d = d.add(new BigDecimal(this.calcFavourAmount(
							tbBusinessSpecialBalanceItem.getBalanceItemTotal(),
							favourRate)));

				}
			}
		}

		return d.doubleValue();
	}

	// 财务录入结算单打印
	public Map putFinanceSpecialBalanceReportParamMap(Long id,
			HttpServletRequest request) {

		Map map = new HashMap();
		// 结算单信息
		TbBusinessSpecialBalance tbBusinessSpecialBalance = this.findById(id);
		// 委托书信息
		// TbFixEntrust tbFixEntrust =
		// tbFixEntrustService.findById(tbBusinessSpecialBalance.getTbBusinessBalance().getTbFixEntrust().getId());
		// 客户信息
		// TbCustomer tbCustomer =
		// tbCustomerService.findById(tbFixEntrust.getTbCustomer().getId());
		// 车辆信息
		// TbCarInfo tbCarInfo =
		// tbCarInfoService.findById(tbFixEntrust.getTbCarInfo().getId());
		// 公司信息
		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		// 修理工时列表
		List<TbSpecialWorkingContent> tbSpecialWorkingContentList = tbSpecialWorkingContentService
				.findBySpecialId(id);
		// 发料配件列表
		List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
				.findBySpecialId(id);

		// 结算项目列表
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
				.findBySpecialId(id);

		Map reportParameters = new HashMap();
		// 结算单号
		reportParameters.put("balanceCode",
				tbBusinessSpecialBalance.getBalanceCodeDB());
		// 委托书号
		reportParameters.put("entrustCode",
				tbBusinessSpecialBalance.getEntrustCodeDB());

		// 销售单号
		reportParameters.put("stockOutCode",
				tbBusinessSpecialBalance.getStockOutCodeDB());

		// 牌照号
		reportParameters.put("licenseCode",
				tbBusinessSpecialBalance.getLicenseCode());

		if (null != tbBusinessSpecialBalance.getTmCarModelTypeId()) {
			// 车型
			reportParameters.put(
					"modelType",
					tmCarModelTypeService.findById(
							tbBusinessSpecialBalance.getTmCarModelTypeId())
							.getModelName());
		}

		// 客户
		reportParameters.put("customerName",
				tbBusinessSpecialBalance.getCustomerName());

		// 结算日期
		reportParameters.put("balanceDate", CommonMethod.parseDateToString(
				tbBusinessSpecialBalance.getBananceDate(), "yyyy-MM-dd"));

		// 付款
		// reportParameters.put("payPatten",tbBusinessSpecialBalance.getTbBusinessBalance().getPayPatternShow());

		// 修理类型
		if (null != tbBusinessSpecialBalance.getTixTypeId()) {

			reportParameters.put(
					"fixType",
					tmFixTypeService.findById(
							tbBusinessSpecialBalance.getTixTypeId())
							.getFixType());
		}

		TbCarInfo tbCarInfo = tbCarInfoService
				.findTbCarInfoBylicenseCode(tbBusinessSpecialBalance
						.getLicenseCode());

		if (null != tbCarInfo) {

			TbCustomer tbCustomer = tbCarInfo.getTbCustomer();

			// 底盘号
			reportParameters.put("chassisCode", tbCarInfo.getChassisCode());

			// 发动机号
			reportParameters.put("engineCode", tbCarInfo.getEngineCode());

			// 购车日期
			reportParameters.put("phurseDate", CommonMethod.parseDateToString(
					tbCarInfo.getProductDate(), "yyyy-MM-dd"));

			// 电话
			reportParameters.put("phone", tbCustomer.getPhone() + "  "
					+ tbCustomer.getTelephone());

			// 地址
			reportParameters.put("address", tbCustomer.getAddress());

			// 公里数
			reportParameters.put("kilo", tbCarInfo.getKilo() == null ? ""
					: String.valueOf(tbCarInfo.getKilo()));

			// 备注说明
			// reportParameters.put("remark",tbBusinessSpecialBalance.getTbBusinessBalance().getRemark());

		}

		// 公司信息
		reportParameters.put("companyName", tmCompany.getCompanyName());

		reportParameters.put("companyAddress", tmCompany.getCompanyAddress());

		reportParameters.put("companyPhone", tmCompany.getCompanyPhone());

		reportParameters.put("companyTaxCode", tmCompany.getTaxCode());

		reportParameters.put("companyAccount", tmCompany.getCompanyAccount());

		reportParameters.put("serviceLeader", tmCompany.getServiceLeader());

		reportParameters.put("companyZipCode", tmCompany.getCompanyZipCode());
		// 公司信息

		// 服务顾问
		if (null != tbBusinessSpecialBalance.getServiceId())
			reportParameters.put(
					"userRealName",
					tmUserService.findById(
							tbBusinessSpecialBalance.getServiceId())
							.getUserRealName());
		// 结算员
		reportParameters.put("jsUserRealName", tbBusinessSpecialBalance
				.getTmUser().getUserRealName());
		// 打印时间
		reportParameters.put("printDate", CommonMethod.parseDateToString(
				new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 子报表路径
		reportParameters.put("SUBREPORT_DIR",
				request.getRealPath("/reportfiles/") + "/");
		// 子报表数据源-工时
		reportParameters.put("subdatasource_0", new JRBeanCollectionDataSource(
				tbSpecialWorkingContentList));
		// 子报表数据源-结算项目
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemListSend = new ArrayList<TbBusinessSpecialBalanceItem>();

		if (null != tbBusinessSpecialBalanceItemList
				&& tbBusinessSpecialBalanceItemList.size() > 0) {

			for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

				if ("XLCLF".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xlclf",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("XLGSF".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xlgsf",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("SE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("taxAmount",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("ZJE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("totalAmount",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}
				if ("XSJE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xsje",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}
				tbBusinessSpecialBalanceItemListSend
						.add(tbBusinessSpecialBalanceItem);

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
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XLCLF"));
		// 工时优惠金额
		reportParameters.put("fixFavourAmount",
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XLGSF"));
		// 销售优惠金额
		reportParameters.put("soleFavourAmount",
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XSJE"));
		// 结算项目数据源
		reportParameters.put("subdatasource_1", new JRBeanCollectionDataSource(
				tbBusinessSpecialBalanceItemListSend));

		map.put("reportParameters", reportParameters);

		map.put("dataSourceList", tbSpecialPartContentList);

		map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");

		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");

		return map;

	}

	// 销售单打印
	public Map putXsdBalanceReportParamMap(Long id, HttpServletRequest request) {

		Map map = new HashMap();
		// 结算单信息
		TbBusinessSpecialBalance tbBusinessSpecialBalance = this.findById(id);
		// 销售单信息
		TmStockOut tmStockOut = null;
		
		if(null != tbBusinessSpecialBalance.getStockOutId()) {
			tmStockOut = tmStockOutService
			.findById(tbBusinessSpecialBalance.getStockOutId());
		}
		else {
			tmStockOut = tmStockOutService
				.findById(tbBusinessSpecialBalance.getTbBusinessBalance()
						.getTmStockOut().getId());
		}
		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tmStockOut
				.getCustomerBill());

		// 公司信息
		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		// 发料配件列表
		List<TbMaintianVo> maintianvos = new ArrayList<TbMaintianVo>();
		// 发料配件列表
		List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
				.findBySpecialId(id);

		// 结算项目列表
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
				.findBySpecialId(id);

		Map reportParameters = new HashMap();
		// 结算单号
		reportParameters.put("balanceCode",
				tbBusinessSpecialBalance.getBalanceCode());

		if (null != tmStockOut) {
			// 销售单号
			reportParameters.put("stockOutCode", tmStockOut.getStockOutCode());
		}

		// 客户
		reportParameters.put("customerName", tbCustomer.getCustomerName());

		// 结算日期
		reportParameters.put("balanceDate", CommonMethod.parseDateToString(
				tbBusinessSpecialBalance.getBananceDate(), "yyyy-MM-dd"));

		// 付款
		if(null != tbBusinessSpecialBalance
				.getTbBusinessBalance()) {
			reportParameters.put("payPatten", tbBusinessSpecialBalance
				.getTbBusinessBalance().getPayPatternShow());
			// 备注说明
			reportParameters.put("remark", tbBusinessSpecialBalance
					.getTbBusinessBalance().getRemark());
		}
		else {
			
			reportParameters.put("payPatten", "");
			reportParameters.put("remark", "");
		}
		// 电话
		reportParameters.put("phone",
				tbCustomer.getPhone() == null ? "" : tbCustomer.getPhone()
						.trim() + "  " + tbCustomer.getTelephone() == null ? ""
						: tbCustomer.getTelephone().trim());

		// 地址
		reportParameters.put("address", tbCustomer.getAddress());

		

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
		reportParameters.put("jsUserRealName", tbBusinessSpecialBalance
				.getTmUser().getUserRealName());
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
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemListSend = new ArrayList<TbBusinessSpecialBalanceItem>();

		if (null != tbBusinessSpecialBalanceItemList
				&& tbBusinessSpecialBalanceItemList.size() > 0) {

			for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

				if ("XLCLF".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xlclf",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("XLGSF".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xlgsf",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("SE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("taxAmount",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("ZJE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("totalAmount",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}
				if ("XSJE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xsje",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}
				tbBusinessSpecialBalanceItemListSend
						.add(tbBusinessSpecialBalanceItem);

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
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XLCLF"));
		// 工时优惠金额
		reportParameters.put("fixFavourAmount",
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XLGSF"));
		// 销售优惠金额
		reportParameters.put("soleFavourAmount",
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XSJE"));
		// 结算项目数据源
		reportParameters.put("subdatasource_1", new JRBeanCollectionDataSource(
				tbBusinessSpecialBalanceItemListSend));

		map.put("reportParameters", reportParameters);

		map.put("dataSourceList", tbSpecialPartContentList);

		// map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");
		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_gxnndz.jrxml");

		} else {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");

		}

		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");

		return map;

	}

	// 委托书结算单打印
	public Map putEntrustSpecialBalanceReportParamMap(Long id,
			HttpServletRequest request) {

		Map map = new HashMap();
		// 结算单信息
		TbBusinessSpecialBalance tbBusinessSpecialBalance = this.findById(id);
		// 委托书信息
		TbFixEntrust tbFixEntrust = null;
		if (null != tbBusinessSpecialBalance.getTbBusinessBalance()) {
			tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessSpecialBalance.getTbBusinessBalance()
							.getTbFixEntrust().getId());
		} else {
			tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessSpecialBalance.getEntrustId());

		}
		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
				.getTbCustomer().getId());
		// 车辆信息
		TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
				.getTbCarInfo().getId());
		// 公司信息
		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		// 修理工时列表
		List<TbSpecialWorkingContent> tbSpecialWorkingContentList = tbSpecialWorkingContentService
				.findBySpecialId(id);
		// 发料配件列表
		List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
				.findBySpecialId(id);
		
		
		
		
		List<TbSpecialPartContent> tbSpecialPartContentTemp = new ArrayList<TbSpecialPartContent>();

		List<TbSpecialPartContent> tbSpecialPartContentAdd = new ArrayList<TbSpecialPartContent>();

		if (tbSpecialPartContentList.size() > 0) {

			for (int i = 0; i < tbSpecialPartContentList.size(); i++) {

				boolean flag = false;

				if (tbSpecialPartContentTemp.size() == 0) {

					tbSpecialPartContentTemp.add(tbSpecialPartContentList.get(i));

				} else {

					if (tbSpecialPartContentTemp.size() > 1) {

						int l = 0;

						for (TbSpecialPartContent _tbSpecialPartContent : tbSpecialPartContentTemp) {

							if (_tbSpecialPartContent.getTbPartInfo().getId().equals(
									tbSpecialPartContentList.get(i).getTbPartInfo().getId())
									&& _tbSpecialPartContent.getIsFree().equals(
											tbSpecialPartContentList.get(i).getIsFree())
									&& _tbSpecialPartContent.getPrice().equals(
											tbSpecialPartContentList.get(i).getPrice())) {

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

					tbSpecialPartContentTemp.add(tbSpecialPartContentList.get(i));

				}

				TbSpecialPartContent temp = tbSpecialPartContentList.get(i);

				BigDecimal d1 = new BigDecimal(temp.getPartQuantity());

				BigDecimal d2 = new BigDecimal(temp.getTotal());

				for (int j = i + 1; j < tbSpecialPartContentList.size(); j++) {

					if (temp.getTbPartInfo().getId().equals(tbSpecialPartContentList.get(j).getTbPartInfo().getId())
							&& temp.getIsFree().equals(
									tbSpecialPartContentList.get(j).getIsFree())
							&& temp.getPrice().equals(
									tbSpecialPartContentList.get(j).getPrice())) {

						temp.setPrice(tbSpecialPartContentList.get(j).getPrice());

						d1 = d1.add(new BigDecimal(tbSpecialPartContentList.get(j)
								.getPartQuantity()));

						d2 = d2.add(new BigDecimal(tbSpecialPartContentList.get(j)
								.getTotal()));

					}

				}

				temp.setPartQuantity(d1.doubleValue());

				temp.setTotal(d2.doubleValue());

				//if (!temp.getIsFree().equals(1L)|| !temp.getPartQuantity().equals(0d)) {
				if (!temp.getPartQuantity().equals(0d)) {

					
					tbSpecialPartContentAdd.add(temp);

				}

			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
		

		// 结算项目列表
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
				.findBySpecialId(id);

		Map reportParameters = new HashMap();
		// 结算单号
		reportParameters.put("balanceCode",
				tbBusinessSpecialBalance.getBalanceCode());
		// 委托书号
		reportParameters.put("entrustCode", tbFixEntrust.getEntrustCode());

		// 销售单号
		// reportParameters.put("stockOutCode",tbBusinessSpecialBalance.getEditCode());

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
				tbBusinessSpecialBalance.getBananceDate(), "yyyy-MM-dd"));

		// 付款

		if (null != tbBusinessSpecialBalance.getTbBusinessBalance()) {
			reportParameters.put("payPatten", tbBusinessSpecialBalance
					.getTbBusinessBalance().getPayPatternShow());
		}

		// 修理类型
		reportParameters.put("fixType", tbFixEntrust.getTmFixType()
				.getFixType());

		// 底盘号
		reportParameters.put("chassisCode", tbCarInfo.getChassisCode());

		// 发动机号
		reportParameters.put("engineCode", tbCarInfo.getEngineCode());

		// 购车日期
		reportParameters.put("phurseDate", CommonMethod.parseDateToString(
				tbCarInfo.getProductDate(), "yyyy-MM-dd"));

		// 电话
		reportParameters.put("phone",
				tbCustomer.getPhone() == null ? "" : tbCustomer.getPhone()
						.trim() + "  " + tbCustomer.getTelephone() == null ? ""
						: tbCustomer.getTelephone().trim());

		// 地址
		reportParameters.put("address", tbCustomer.getAddress());

		// 公里数
		/*
		 * reportParameters.put("kilo",tbCarInfo.getKilo()==null?"":String.valueOf
		 * (tbCarInfo.getKilo()));
		 */
		reportParameters.put(
				"kilo",
				tbFixEntrust.getEnterStationKilo() == null ? "" : String
						.valueOf(tbFixEntrust.getEnterStationKilo()));

		// 备注说明
		String remark = "";

		/*
		 * if(null != tbBusinessSpecialBalance.getTbBusinessBalance()&&null!=
		 * tbBusinessSpecialBalance
		 * .getTbBusinessBalance().getRemark()&&!"".equals
		 * (tbBusinessSpecialBalance.getTbBusinessBalance().getRemark())){
		 * 
		 * 
		 * remark = tbBusinessSpecialBalance.getTbBusinessBalance().getRemark();
		 * }
		 */

		if (null != tbBusinessSpecialBalance
				&& null != tbBusinessSpecialBalance.getRemark()
				&& !"".equals(tbBusinessSpecialBalance.getRemark())) {

			remark = tbBusinessSpecialBalance.getRemark();
		}

		// if(null != tbBusinessSpecialBalance.getTbBusinessBalance() || null !=
		// tbBusinessSpecialBalance.getRemark()){

		reportParameters.put("remark", remark);

		// }

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
		reportParameters.put("jsUserRealName", tbBusinessSpecialBalance
				.getTmUser().getUserRealName());
		// 打印时间
		reportParameters.put("printDate", CommonMethod.parseDateToString(
				new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 子报表路径
		reportParameters.put("SUBREPORT_DIR",
				request.getRealPath("/reportfiles/") + "/");
		
		List<TbSpecialWorkingContent> tbSpecialWorkingContentListReport = new ArrayList<TbSpecialWorkingContent>();
		
		if(null != tbSpecialWorkingContentList && tbSpecialWorkingContentList.size() >0 ){
			
			for(TbSpecialWorkingContent twc : tbSpecialWorkingContentList) {
				
				//if(!twc.getFixHour().equals(0d)&&!twc.getFreesymbol().equals(1L)) {
				if(!twc.getFixHour().equals(0d)) {
					
					tbSpecialWorkingContentListReport.add(twc);
					
				}
				
			}
			
		}
		
		// 子报表数据源-工时
		reportParameters.put("subdatasource_0", new JRBeanCollectionDataSource(
				tbSpecialWorkingContentListReport));
		// 子报表数据源-结算项目
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemListSend = new ArrayList<TbBusinessSpecialBalanceItem>();

		if (null != tbBusinessSpecialBalanceItemList
				&& tbBusinessSpecialBalanceItemList.size() > 0) {

			for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

				if ("XLCLF".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xlclf",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("XLGSF".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xlgsf",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("SE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("taxAmount",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}

				if ("ZJE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("totalAmount",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}
				if ("XSJE".equals(tbBusinessSpecialBalanceItem
						.getBalanceItemCode())) {

					reportParameters.put("xsje",
							tbBusinessSpecialBalanceItem.getBalanceItemTotal());

					continue;
				}
				tbBusinessSpecialBalanceItemListSend
						.add(tbBusinessSpecialBalanceItem);

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
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XLCLF"));
		// 工时优惠金额
		reportParameters.put("fixFavourAmount",
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XLGSF"));
		// 销售优惠金额
		reportParameters.put("soleFavourAmount",
				this.calcItemFavourAmount(tbBusinessSpecialBalance, "XSJE"));
		// 结算项目数据源
		reportParameters.put("subdatasource_1", new JRBeanCollectionDataSource(
				tbBusinessSpecialBalanceItemListSend));

		map.put("reportParameters", reportParameters);

		List<TbSpecialPartContent> tbSpecialPartContentListReport = new ArrayList<TbSpecialPartContent>();
		
		if(null != tbSpecialPartContentAdd && tbSpecialPartContentAdd.size() > 0) {
			
			for(TbSpecialPartContent tpc : tbSpecialPartContentAdd) {
				
				if(!tpc.getPartQuantity().equals(0d) && !tpc.getPartQuantity().equals(1L)) {
					
					tpc.setPartTotal(tpc.acquireTotal());
					
					tbSpecialPartContentListReport.add(tpc);
					
				}
				
			}
		}
		
		map.put("dataSourceList", tbSpecialPartContentListReport);

		if ("南宁市得众汽车维修服务有限公司".equals(tmCompany.getCompanyName().trim())) {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance_gxnndz.jrxml");

		} else {

			map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");

		}
		// map.put("jrxmlPath", "/reportfiles/tbBusinessBalance.jrxml");

		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");

		return map;

	}

	public void printTbBusinessBalanceTemplate(OutputStream os, String tpl,
			Long id, String companyName) {
		// 结算单信息
		TbBusinessSpecialBalance tbBusinessSpecialBalance = this.findById(id);
		// 委托书信息
		TbFixEntrust tbFixEntrust = null;
		if (null != tbBusinessSpecialBalance.getTbBusinessBalance()) {
			tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessSpecialBalance.getTbBusinessBalance()
							.getTbFixEntrust().getId());
		} else {
			tbFixEntrust = tbFixEntrustService
					.findById(tbBusinessSpecialBalance.getEntrustId());

		}
		// 客户信息
		TbCustomer tbCustomer = tbCustomerService.findById(tbFixEntrust
				.getTbCustomer().getId());
		// 车辆信息
		TbCarInfo tbCarInfo = tbCarInfoService.findById(tbFixEntrust
				.getTbCarInfo().getId());
		// 公司信息
		TmCompany tmCompany = tmCompanyService.acquireUniqueTmCompany();

		/*// 修理工时列表
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentService
				.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrust
						.getId());

		// 发料配件列表
		List<TbMaintianVo> maintianvos = tbMaintainPartContentService
				.getTbMaintianDetailVosByEntrustId(tbFixEntrust.getId(),
						Constants.BALANCE_ALL);
		// 销售单列表
		List<TmStockOutDetVo> tmStockOutDetVos = tmStockOutService
				.getSellDetailByEntrustCode(tbFixEntrust.getEntrustCode(),
						Constants.BALANCE_ALL);*/
		// 修理工时列表
		List<TbSpecialWorkingContent> tbSpecialWorkingContentList = tbSpecialWorkingContentService
						.findBySpecialId(id);
		// 发料配件列表
		List<TbSpecialPartContent> tbSpecialPartContentList = tbSpecialPartContentService
						.findBySpecialId(id);
		
		// 结算项目列表
		/*List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService
				.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalance
						.getId());*/
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemService
				.findBySpecialId(id);

		// 发料配件与销售列表合并
		/*if (null == maintianvos) {
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

		}*/

		int fixSize = (tbSpecialWorkingContentList == null ? 0
				: tbSpecialWorkingContentList.size());

		int partSize = (tbSpecialPartContentList == null ? 0 : tbSpecialPartContentList.size());

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
				cell.setCellValue(/* tmCompany.getCompanyName() */"成都经典腾龙");
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
					tbBusinessSpecialBalance.getBananceDate(), "yyyy-MM-dd HH:mm"));

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

			if (null != tbBusinessSpecialBalanceItemList 
					&& tbBusinessSpecialBalanceItemList .size() > 0) {

				for (TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList) {

					if ("XLCLF".equals(tbBusinessSpecialBalanceItem
							.getBalanceItemCode())) {

						row = sheet.getRow(77);

						cell = row.getCell(24);

						cell.setCellValue(new BigDecimal(tbBusinessSpecialBalanceItem
								.getBalanceItemTotal()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						continue;
					}

					if ("XLGSF".equals(tbBusinessSpecialBalanceItem
							.getBalanceItemCode())) {

						row = sheet.getRow(75);

						cell = row.getCell(24);

						cell.setCellValue(new BigDecimal(tbBusinessSpecialBalanceItem
								.getBalanceItemTotal()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						continue;
					}

					if ("ZJE"
							.equals(tbBusinessSpecialBalanceItem.getBalanceItemCode())) {

						row = sheet.getRow(81);

						cell = row.getCell(24);

						cell.setCellValue(new BigDecimal(tbBusinessSpecialBalanceItem
								.getBalanceItemTotal()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						continue;
					}

				}

				row = sheet.getRow(79);

				cell = row.getCell(24);

				cell.setCellValue("0.00");

			}

			Double wt = tbBusinessSpecialBalance.getWorkingHourTotalAll() == null ? 0d
					: tbBusinessSpecialBalance.getWorkingHourTotalAll();

			Double wf = tbBusinessSpecialBalance.getWorkingHourFavourRate() == null ? 0d
					: tbBusinessSpecialBalance.getWorkingHourFavourRate();

			Double pt = tbBusinessSpecialBalance.getFixPartTotalAll() == null ? 0d
					: tbBusinessSpecialBalance.getFixPartTotalAll();

			Double pf = tbBusinessSpecialBalance.getFixPartFavourRate() == null ? 0d
					: tbBusinessSpecialBalance.getFixPartFavourRate();

			Double st = tbBusinessSpecialBalance.getSolePartTotalAll() == null ? 0d
					: tbBusinessSpecialBalance.getSolePartTotalAll();

			Double sf = tbBusinessSpecialBalance.getSolePartFavourRate() == null ? 0d
					: tbBusinessSpecialBalance.getSolePartFavourRate();

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

			cell.setCellValue(new BigDecimal(tbBusinessSpecialBalance.getPayedAmount() == null ? 0d : tbBusinessSpecialBalance.getPayedAmount())
					.divide(new BigDecimal("1.00"), 2, BigDecimal.ROUND_HALF_UP)
					.toString());

			row = sheet.getRow(68);

			cell = row.getCell(40);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(CommonMethod.parseDateToString(tbFixEntrust.getRemindMaintainDate(), "yyyy-MM-dd"));

			row = sheet.getRow(70);

			cell = row.getCell(40);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(tbFixEntrust
					.getRemindMaintainKilo() == null ? ""
					: new BigDecimal(tbFixEntrust
							.getRemindMaintainKilo()).divide(new BigDecimal(
							"1.00"), 0, BigDecimal.ROUND_HALF_UP)
							+ "   Km");

			row = sheet.getRow(77);

			cell = row.getCell(30);

			// cell.setCellStyle(styleCenter);

			cell.setCellValue(tbFixEntrust.getRemark());

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
					(short) 41, 0, (short) 47, 7);

			patriarch.createPicture(anchorLion, workbook.addPicture(
					byteArrayOutImgLion.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_PNG));

			int maxSize = 19;

			int maxFixSize = (fixSize > 19 ? 19 : fixSize);

			int maxPartSize = maxSize - maxFixSize > partSize ? partSize
					: maxSize - maxFixSize;

			int partStartRow = (fixSize == 0 ? 21 : 21 + maxFixSize * 2 + 4);

			int partStartRowClone = ((fixSize - maxFixSize) == 0 ? 21
					: 21 + (fixSize - maxFixSize) * 2 + 4);

			int minBlank = 9;

			if ((fixSize + partSize) > 17) {

				HSSFSheet sheetClone = workbook.cloneSheet(0);

				HSSFRow rowClone = sheetClone.getRow(87);

				HSSFCell cellClone = rowClone.getCell(34);

				cellClone.setCellValue("第2页 ，共 2 页 ");

				HSSFPatriarch patriarchClone = sheetClone
						.createDrawingPatriarch();

				HSSFClientAnchor anchorLionClone = new HSSFClientAnchor(0, 0,
						1023, 200, (short) 41, 0, (short) 47, 8);

				patriarchClone.createPicture(anchorLionClone, workbook
						.addPicture(byteArrayOutImgLion.toByteArray(),
								HSSFWorkbook.PICTURE_TYPE_PNG));

				if ((fixSize - maxFixSize) > 0) {

					/*
					 * sheetClone.addMergedRegion(new Region(21, (short) 0, 21,
					 * (short) 49));
					 * 
					 * sheetClone.addMergedRegion(new Region(21, (short) 0, 21,
					 * (short) 0));
					 */

					sheetClone.addMergedRegion(new CellRangeAddress(21, 21, 0,
							49));

					rowClone = sheetClone.getRow(21);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(style);

					cellClone.setCellValue("维修项目费用明细");

					cellClone.setCellStyle(styleBorderThickLeft);

					rowClone = sheetClone.getRow(21);

					cellClone = rowClone.getCell(0);

					cellClone.setCellStyle(styleBorderThickLeft);

					
					
					rowClone = sheetClone.getRow(24);

					cellClone = rowClone.getCell(0);

					cellClone.setCellStyle(styleBorderThickLeftCenter);
					
					
					rowClone = sheetClone.getRow(23);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(styleCenter);

					cellClone.setCellStyle(styleBorderThickLeftCenter);

					cellClone.setCellValue("序号");



					cellClone = rowClone.getCell(2);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("工时编码");

					cellClone = rowClone.getCell(10);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("维修项目");

					cellClone = rowClone.getCell(21);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("帐类");

					cellClone = rowClone.getCell(24);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("项目类型");

					cellClone = rowClone.getCell(30);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("工时");

					cellClone = rowClone.getCell(34);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("工时单价");

					cellClone = rowClone.getCell(40);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("折扣率");

					cellClone = rowClone.getCell(44);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("工时费用");

					for (int i = 0; i < (fixSize - maxFixSize); i++) {

						TbSpecialWorkingContent tbFixEntrustContent = tbSpecialWorkingContentList
								.get(i);

						rowClone = sheetClone.getRow(25 + i * 2);

						cellClone = rowClone.getCell(0);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(i + 1);

						cellClone = rowClone.getCell(2);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(tbFixEntrustContent
								.getStationCode());

						cellClone = rowClone.getCell(10);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(tbFixEntrustContent
								.getStationName());

						cellClone = rowClone.getCell(21);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("");

						cellClone = rowClone.getCell(24);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("");

						cellClone = rowClone.getCell(30);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(
								tbFixEntrustContent.getFixHour()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cellClone = rowClone.getCell(34);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(
								tbFixEntrustContent.getWorkingHourPrice())
								.divide(new BigDecimal("1.00"), 2,
										BigDecimal.ROUND_HALF_UP).toString());

						cellClone = rowClone.getCell(40);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(tbBusinessSpecialBalance
								.getWorkingHourFavourRate()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cellClone = rowClone.getCell(44);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(
								tbFixEntrustContent.getFixHourAll()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());
					}

				}

				if ((partSize - maxPartSize) > 0) {

					/*
					 * sheetClone.addMergedRegion(new Region(partStartRowClone,
					 * (short) 0, partStartRowClone, (short) 49));
					 * 
					 * sheetClone.addMergedRegion(new Region(partStartRowClone,
					 * (short) 0, partStartRowClone, (short) 49));
					 */

					sheetClone.addMergedRegion(new CellRangeAddress(
							partStartRowClone, partStartRowClone, 0, 49));

					rowClone = sheetClone.getRow(partStartRowClone);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(style);

					cellClone.setCellStyle(styleBorderThickLeft);

					cellClone.setCellValue("维修零件费用明细");

					rowClone = sheetClone.getRow(partStartRowClone + 1);

					cellClone = rowClone.getCell(0);

					cellClone.setCellStyle(styleBorderThickLeft);

					
					rowClone = sheetClone.getRow(partStartRowClone + 2 + 1);

					cellClone = rowClone.getCell(0);

					cellClone.setCellStyle(styleBorderThickLeftCenter);
					
					
					rowClone = sheetClone.getRow(partStartRowClone + 2);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(styleCenter);
					cellClone.setCellStyle(styleBorderThickLeftCenter);

					cellClone.setCellValue("序号");

					

					cellClone = rowClone.getCell(2);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("零件编号");

					cellClone = rowClone.getCell(10);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("零件名称");

					cellClone = rowClone.getCell(21);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("帐类");

					cellClone = rowClone.getCell(24);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("维修项目类型");

					cellClone = rowClone.getCell(30);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("数量");

					cellClone = rowClone.getCell(34);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("单价");

					cellClone = rowClone.getCell(40);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("折扣率");

					cellClone = rowClone.getCell(44);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("零件费用");

					for (int i = 0; i < (partSize - maxPartSize); i++) {

						TbSpecialPartContent tbMaintianVo = tbSpecialPartContentList.get(i);

						rowClone = sheetClone.getRow(partStartRowClone + 4 + i
								* 2);

						cellClone = rowClone.getCell(0);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(i + 1);

						cellClone = rowClone.getCell(2);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(tbMaintianVo.getPartCode());

						cellClone = rowClone.getCell(10);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(tbMaintianVo.getPartName());

						cellClone = rowClone.getCell(21);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("");

						cellClone = rowClone.getCell(24);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("");

						cellClone = rowClone.getCell(30);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(tbMaintianVo
								.getPartQuantity()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cellClone = rowClone.getCell(34);

						// cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(tbMaintianVo
								.getPrice()).divide(new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cellClone = rowClone.getCell(40);

						// .setCellStyle(styleCenter);

						cellClone.setCellValue(new BigDecimal(tbBusinessSpecialBalance
								.getFixPartFavourRate()).divide(
								new BigDecimal("1.00"), 2,
								BigDecimal.ROUND_HALF_UP).toString());

						cellClone = rowClone.getCell(44);

						// cellClone.setCellStyle(styleCenter);

						cellClone
								.setCellValue(new BigDecimal(tbMaintianVo
										.getPartQuantity())
										.multiply(
												new BigDecimal(tbMaintianVo
														.getPrice()))
										.divide(new BigDecimal("1.00"), 2,
												BigDecimal.ROUND_HALF_UP)
										.toString());
					}

				}

				int attachStartRowClone = 21;

				if ((fixSize - maxFixSize + partSize - maxPartSize) <= 17) {

					if ((fixSize - maxFixSize) != 0
							&& (partSize - maxPartSize) == 0
							&& (fixSize - maxFixSize) < 18) {

						attachStartRowClone = 21 + 2 + 2
								+ (fixSize - maxFixSize) * 2;

					}

					if ((partSize - maxPartSize) != 0
							&& (fixSize - maxFixSize) == 0
							&& (partSize - maxPartSize) < 18) {

						attachStartRowClone = 21 + 2 + 2 + 2
								+ (partSize - maxPartSize) * 2;
					}

					if ((fixSize - maxFixSize) != 0
							&& (partSize - maxPartSize) != 0
							&& (fixSize - maxFixSize + partSize - maxPartSize < 18)) {

						attachStartRowClone = 21 + 2 + 2 + 2 + 2
								+ (fixSize - maxFixSize) * 2
								+ (partSize - maxPartSize) * 2;

					}

					/*
					 * sheetClone.addMergedRegion(new
					 * Region(attachStartRowClone, (short) 0,
					 * attachStartRowClone, (short) 49));
					 * 
					 * sheetClone.addMergedRegion(new
					 * Region(attachStartRowClone, (short) 0,
					 * attachStartRowClone, (short) 49));
					 */

					sheetClone.addMergedRegion(new CellRangeAddress(
							attachStartRowClone, attachStartRowClone, 0, 49));

					rowClone = sheetClone.getRow(attachStartRowClone);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(style);

					cellClone.setCellStyle(styleBorderThickLeft);

					cellClone.setCellValue("附件费用明细");

					rowClone = sheetClone.getRow(attachStartRowClone + 1);

					cellClone = rowClone.getCell(0);

					cellClone.setCellStyle(styleBorderThickLeft);
					
					
					rowClone = sheetClone.getRow(attachStartRowClone + 2 + 1);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(styleCenter);
					cellClone.setCellStyle(styleBorderThickLeftCenter);
					
					

					rowClone = sheetClone.getRow(attachStartRowClone + 2);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(styleCenter);
					cellClone.setCellStyle(styleBorderThickLeftCenter);

					cellClone.setCellValue("序号");

					

					if ("dfbz".equals(companyName)) {

						cellClone = rowClone.getCell(10);

						cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("零件名称");

						cellClone = rowClone.getCell(21);

						cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("帐类");

						cellClone = rowClone.getCell(24);

						cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("维修项目类型");

						cellClone = rowClone.getCell(30);

						cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("数量");

						cellClone = rowClone.getCell(34);

						cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("单价");

						cellClone = rowClone.getCell(40);

						cellClone.setCellStyle(styleCenter);

					} else if ("xtl".equals(companyName)) {

						/*
						 * sheetClone.addMergedRegion(new
						 * Region(attachStartRowClone + 2, (short) 2,
						 * attachStartRowClone + 2, (short) 39));
						 * 
						 * sheetClone.addMergedRegion(new
						 * Region(attachStartRowClone + 2, (short) 2,
						 * attachStartRowClone + 2, (short) 39));
						 */

						sheetClone.addMergedRegion(new CellRangeAddress(
								attachStartRowClone + 2,
								attachStartRowClone + 2, 2, 39));

						cellClone = rowClone.getCell(2);

						cellClone.setCellStyle(styleCenter);

						cellClone.setCellValue("附加项目");

					}

					cellClone = rowClone.getCell(40);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("折扣率");

					cellClone = rowClone.getCell(44);

					cellClone.setCellStyle(styleCenter);

					cellClone.setCellValue("零件费用");

					rowClone = sheetClone.getRow(attachStartRowClone + 4);

					cellClone = rowClone.getCell(0);

					// cellClone.setCellStyle(styleCenter);

					cellClone.setCellStyle(styleBorderThickLeftCenter);

					cellClone.setCellValue("1");

					rowClone = sheetClone.getRow(attachStartRowClone + 4 + 1);

					cellClone = rowClone.getCell(0);

					cellClone.setCellStyle(styleBorderThickLeftCenter);

					/*
					 * sheetClone.addMergedRegion(new Region(attachStartRowClone
					 * + 6, (short) 0, 66, (short) 49));
					 * 
					 * sheetClone.addMergedRegion(new Region(attachStartRowClone
					 * + 6, (short) 0, 66, (short) 49));
					 */

					sheetClone.addMergedRegion(new CellRangeAddress(
							attachStartRowClone + 6, 66, 0, 49));

				}

			} else {

				row = sheet.getRow(87);

				cell = row.getCell(34);

				cell.setCellValue("第 1 页 ，共 1 页 ");

			}

			if (fixSize > 0) {

				/*
				 * sheet.addMergedRegion(new Region(21, (short) 0, 21, (short)
				 * 49));
				 * 
				 * sheet.addMergedRegion(new Region(21, (short) 0, 21, (short)
				 * 49));
				 */

				sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 49));

				row = sheet.getRow(21);

				cell = row.getCell(0);

				// cell.setCellStyle(style);

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

				// cell.setCellStyle(styleCenter);

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

				for (int i = 0; i < maxFixSize; i++) {

					TbSpecialWorkingContent tbFixEntrustContent = tbSpecialWorkingContentList
							.get(i);

					row = sheet.getRow(25 + i * 2);

					cell = row.getCell(0);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(i + 1);

					cell = row.getCell(2);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(tbFixEntrustContent.getStationCode());

					cell = row.getCell(10);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(tbFixEntrustContent.getStationName());

					cell = row.getCell(21);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue("");

					cell = row.getCell(24);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue("");

					cell = row.getCell(30);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(new BigDecimal(tbFixEntrustContent
							.getFixHour()).divide(new BigDecimal("1.00"), 2,
							BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(34);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(new BigDecimal(tbFixEntrustContent
							.getWorkingHourPrice())
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(40);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(new BigDecimal((1 - tbBusinessSpecialBalance
							.getWorkingHourFavourRate()) * 100)
							.divide(new BigDecimal("1.00"), 2,
									BigDecimal.ROUND_HALF_UP).toString());

					cell = row.getCell(44);

					// cell.setCellStyle(styleCenter);

					cell.setCellValue(new BigDecimal(tbFixEntrustContent
							.getFixHourAll()).divide(new BigDecimal("1.00"), 2,
							BigDecimal.ROUND_HALF_UP).toString());
				}

			}

			if ((fixSize <= maxSize - 1) && partSize > 0) {

				/*
				 * sheet.addMergedRegion(new Region(partStartRow, (short) 0,
				 * partStartRow, (short) 49));
				 * 
				 * sheet.addMergedRegion(new Region(partStartRow, (short) 0,
				 * partStartRow, (short) 49));
				 */

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

				if (partSize > 0) {

					for (int i = 0; i < maxPartSize; i++) {

						TbSpecialPartContent tbMaintianVo = tbSpecialPartContentList .get(i);

						row = sheet.getRow(partStartRow + 4 + i * 2);

						cell = row.getCell(0);

						// cell.setCellStyle(styleCenter);

						cell.setCellValue(i + 1);

						cell = row.getCell(2);

						// cell.setCellStyle(styleCenter);

						cell.setCellValue(tbMaintianVo.getPartCode());

						cell = row.getCell(10);

						// cell.setCellStyle(styleCenter);

						cell.setCellValue(tbMaintianVo.getPartName());

						cell = row.getCell(21);

						// cell.setCellStyle(styleCenter);

						cell.setCellValue("");

						cell = row.getCell(24);

						// cell.setCellStyle(styleCenter);

						cell.setCellValue("");

						cell = row.getCell(30);

						// cell.setCellStyle(styleCenter);

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

						cell.setCellValue(new BigDecimal((1 - tbBusinessSpecialBalance
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

			}

			int attachStartRow = 21;

			if ((fixSize + partSize) <= 17) {

				if (fixSize != 0 && partSize == 0 && fixSize < 18) {

					attachStartRow = 19 + 2 + 2 + 2 + fixSize * 2;

				}

				if (partSize != 0 && fixSize == 0 && partSize < 18) {

					attachStartRow = 19 + 2 + 2 + 2 + partSize * 2;
				}

				if (fixSize != 0 && partSize != 0 && (fixSize + partSize < 18)) {

					attachStartRow = 19 + 2 + 2 + 2 + 2 + 2 + fixSize * 2
							+ partSize * 2;

				}

				/*
				 * sheet.addMergedRegion(new Region(attachStartRow, (short) 0,
				 * attachStartRow, (short) 49));
				 * 
				 * sheet.addMergedRegion(new Region(attachStartRow, (short) 0,
				 * attachStartRow, (short) 49));
				 */

				sheet.addMergedRegion(new CellRangeAddress(attachStartRow,
						attachStartRow, 0, 49));

				row = sheet.getRow(attachStartRow);

				cell = row.getCell(0);

				// cell.setCellStyle(style);

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

					/*
					 * sheet.addMergedRegion(new Region(attachStartRow + 2,
					 * (short) 2, attachStartRow + 2, (short) 39));
					 * 
					 * sheet.addMergedRegion(new Region(attachStartRow + 2,
					 * (short) 2, attachStartRow + 2, (short) 39));
					 */

					sheet.addMergedRegion(new CellRangeAddress(
							attachStartRow + 2, attachStartRow + 2, 2, 39));

					cell = row.getCell(2);

					cell.setCellStyle(styleCenter);

					cell.setCellValue("附加项目");

				}

				/*
				 * sheet.addMergedRegion(new Region(attachStartRow + 2, (short)
				 * 2, attachStartRow + 2, (short) 39));
				 * 
				 * sheet.addMergedRegion(new Region(attachStartRow + 2, (short)
				 * 2, attachStartRow + 2, (short) 39));
				 * 
				 * cell = row.getCell(2);
				 * 
				 * cell.setCellStyle(styleCenter);
				 * 
				 * cell.setCellValue("附加项目");
				 */

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

					/*
					 * sheet.addMergedRegion(new Region(attachStartRow + 6,
					 * (short) 0, 66, (short) 49));
					 * 
					 * sheet.addMergedRegion(new Region(attachStartRow + 6,
					 * (short) 0, 66, (short) 49));
					 */
					try{
						sheet.addMergedRegion(new CellRangeAddress(
								attachStartRow + 6, 66, 0, 49));
					}catch(Exception e){
						sheet.addMergedRegion(new CellRangeAddress(
								attachStartRow + 6, 67, 0, 49));
						e.printStackTrace();
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

}
