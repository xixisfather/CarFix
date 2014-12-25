package com.selfsoft.framework.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLianceReturnDetail;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmLoanReturnDetail;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmLianceReturnService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.service.ITmLoanReturnService;
import com.selfsoft.business.service.ITmRemoveStockDetailService;
import com.selfsoft.business.service.ITmRemoveStockService;
import com.selfsoft.business.service.ITmShiftinStockService;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.business.vo.TmShiftinStockVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.file.ReportFileFromStream;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;
@Service("printReport")
public class PrintReport implements IPrintReport {
	
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmLoanRegisterService tmLoanRegisterService;
	@Autowired
	private ITmLianceRegisterService tmLianceRegisterService;
	@Autowired
	private ITmLianceReturnService tmLianceReturnService;
	@Autowired
	private ITmLoanReturnService tmLoanReturnService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	@Autowired
	private ITmRemoveStockService tmRemoveStockService;
	@Autowired
	private ITmShiftinStockService tmShiftinStockService;
	
	/**
	 * jsp上
	 * <e3t:column property="no" title="操作" sortable="false" width="200">
		
				<a href="javascript:editObject('${XXX.id}','XXXXAction.action',600,300);">
					<font color="blue">
						打印
					</font>
				</a>
				
		</e3t:column>
	 */
	/**
	 * ACTION中使用方法
	 * public String printXXXX() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = PrintReport.putCGRKParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
		return null;
		}
	 */
	
	/**
	 * 打印采购入库参数设置
	 * id为采购入库单ID
	 */
	public Map putCGRKParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询出采购入库明细  具体字段看下 cgrk.jrxml或者tmStockInDetailVo_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		List<TmStockInDetailVo> tmStockInDetailVoList = this.tmStockinDetailService.getStockInDetVo(StockTypeElements.STOCK.getElementValue(),id,null);
		TmStockIn tmStockIn = tmStockInService.findById(id);
		TmStockInVo tmStockInVo  = tmStockinDetailService.getStockInVo(StockTypeElements.STOCK.getElementValue(), null, id).get(0);
		
		Double rate =  tmStockIn.getRate()==null?1.17:tmStockIn.getRate();
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 采购入库单号
		 */
		reportParameters.put("stockInCode",tmStockInVo.getStockInCode());
		
		/**
		 * 供货商名字
		 */
		reportParameters.put("customerName",tmStockInVo.getSupplierName());
		
		/**
		 * 入库日期
		 */
		reportParameters.put("arriveDate",CommonMethod.parseDateToString(tmStockInVo.getArriveDate(),"yyyy-MM-dd"));
		
		/**
		 * 入库数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockInVo.getTotalQuantity()+"");
		
		/**
		 * 税前合计--所有明细中税前金额之和
		 */
		reportParameters.put("totalBeforeTax",tmStockInVo.getTotalPrice()+"");
		
		/**
		 * 税后合计--所有明细中税后金额之和
		 */
		
		
		reportParameters.put("totalAfterTax",CommonMethod.convertRadixPoint(tmStockInVo.getTotalPrice()*rate, 2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockInVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中  cgrk.jrxml中DETAIL区域中的内容
		map.put("dataSourceList", tmStockInDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/cgrk.jrxml");
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockInDetailVo_CGRK_pdf_tpl.properties");
		
		return map;
	}
	
	
	/**
	 * 打印采购退货参数设置
	 * id为采购退货单ID
	 */
	public Map putCGTHParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询出采购退货明细  具体字段看下 cgth.jrxml或者tmStockOutDetVo_CGTH_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		List<TmStockOutDetVo> tmStockOutDetVoList = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, id, StockTypeElements.STOCKRETURN.getElementValue());
		
		TmStockOutVo tmStockOutVo = tmStockOutService.getStockOutVos(null, StockTypeElements.STOCKRETURN.getElementValue(), null, id).get(0);
		
		TmStockInVo tmStockInVo  = tmStockinDetailService.getStockInVo(StockTypeElements.STOCK.getElementValue(), null, tmStockOutVo.getStockInId()).get(0);
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 入库单号
		 */
		reportParameters.put("stockInCode",tmStockInVo.getStockInCode());
		
		/**
		 * 退货单号
		 */
		reportParameters.put("stockOutCode",tmStockOutVo.getStockOutCode());
		
		/**
		 * 供货商单号
		 */
		reportParameters.put("customerCode",tmStockInVo.getSupplierCode());
		
		/**
		 * 供货商名字
		 */
		reportParameters.put("customerName",tmStockInVo.getSupplierName());
		
		/**
		 * 退货日期
		 */
		reportParameters.put("stockOutDate",CommonMethod.parseDateToString(tmStockOutVo.getStockOutDate(), "yyyy-MM-dd "));
		
		/**
		 * 入库日期
		 */
		reportParameters.put("stockInDate",CommonMethod.parseDateToString(tmStockInVo.getArriveDate(), "yyyy-MM-dd "));
		
		/**
		 * 入库数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockOutVo.getTotalQuantity()+"");
		
		/**
		 * 税前合计--所有明细中税前金额之和
		 */
		reportParameters.put("totalBeforeTax",tmStockOutVo.getTotalPrice()+"");
		
		/**
		 * 税后合计--所有明细中税后金额之和
		 */
		reportParameters.put("totalAfterTax",CommonMethod.convertRadixPoint(tmStockOutVo.getTotalPrice()*1.17, 2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockInVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中
		map.put("dataSourceList", tmStockOutDetVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/cgth.jrxml");
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockOutDetVo_CGTH_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * 打印借出登记参数设置
	 * id为借出登记单ID
	 */
	public Map putJCDJParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询出借出登记明细  具体字段看下 jcdj.jrxml或者tmStockOutDetVo_JCDJ_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmLoanRegDetailVo> tmLoanRegDetailVoList = tmLoanRegisterService.getLoanRegDetailVo(null, null, null, id);
		
		TmLoanRegVo tmLoanRegVo = tmLoanRegisterService.getloanRegVo(null, null, null, id).get(0);
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 借出单号
		 */
		reportParameters.put("stockOutCode",tmLoanRegVo.getLoanBill());
		
		/**
		 * 供货商名字
		 */
		reportParameters.put("customerName",tmLoanRegVo.getCustomerName());
		
		/**
		 * 借出日期
		 */
		reportParameters.put("stockOutDate",CommonMethod.parseDateToString(tmLoanRegVo.getLoanDate(), "yyyy-MM-dd "));
		
		/**
		 * 借出数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmLoanRegVo.getTotalQuantity()+"");
		
		/**
		 * 税后合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmLoanRegVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmLoanRegVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中
		map.put("dataSourceList", tmLoanRegDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/jcdj.jrxml");
		
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmLoanRegDetailVo_JCDJ_pdf_tpl.properties");
		
		
		return map;
	}
	
	/**
	 * 打印借出归还参数设置
	 * id为借出归还单ID
	 */
	public Map putJCGHParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询出借出归还明细  具体字段看下 jcgh.jrxml或者tmLoanReturnDetail_JCGH_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		TmLoanReturn queryEntity = new TmLoanReturn();
		queryEntity.setId(id);
		//借出归还对象
		TmLoanReturn tmLoanReturn = tmLoanReturnService.findByEntity(queryEntity).get(0);
		
		//借出归还明细
		List<TmLoanReturnDetail> tmLoanReturnDetailList = new ArrayList<TmLoanReturnDetail>();
		tmLoanReturnDetailList.addAll(tmLoanReturn.getLoanReturnDetails());
		
		//归还总数量
		Double totalReturnQuantity = 0D;
		//归还总金额
		Double totalReturnPrice = 0D;
		for(TmLoanReturnDetail detail : tmLoanReturnDetailList){
			totalReturnQuantity += detail.getReturnQuantity();
			totalReturnPrice += detail.getCostPrice();
		}
		//借出登记对象
		TmLoanRegVo tmLoanRegVo = null;
		if(tmLoanReturnDetailList != null && tmLoanReturnDetailList.size()>0){
			Long loanRegId = tmLoanReturnDetailList.get(0).getTmLoanRegisterDetail().getLoanId();
			tmLoanRegVo = tmLoanRegisterService.getloanRegVo(null, null, null, loanRegId).get(0);
		}
		//操作者
		TmUser tmUser =  tmUserService.findById(tmLoanReturn.getUserId());
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 借出归还单据号
		 */
		reportParameters.put("loanReturnBill",tmLoanReturn.getLoanReturnBill());
		
		/**
		 * 归还日期
		 */
		reportParameters.put("returnDate",CommonMethod.parseDateToString(tmLoanReturn.getReturnDate(), "yyyy-MM-dd"));
		
		/**
		 * 借出单据号
		 */
		reportParameters.put("loanBill",tmLoanRegVo.getLoanBill());
		
		/**
		 * 供货商名字
		 */
		reportParameters.put("customerName",tmLoanRegVo.getCustomerName());
		
		/**
		 * 归还数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",CommonMethod.convertRadixPoint(totalReturnQuantity,2)+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",CommonMethod.convertRadixPoint(totalReturnPrice,2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmUser.getUserRealName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmLoanReturnDetailList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/jcgh.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmLoanReturnDetail_JCGH_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * select sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation , 
lrd.return_quantity as returnQuantity , lrd.cost_price as costPrice , lrd.return_quantity * lrd.cost_price as total
from tm_liance_return lr , tm_liance_return_detail lrd , tm_liance_register_detail ld ,tb_part_info pi , tm_store_house sh , tm_unit ui
where lr.id = lrd.liance_return_id and pi.id = ld.partinfo_id and ld.id = lrd.liance_regdetail_id 
and pi.store_house_id = sh.id and ui.id = pi.unit_id

	 */
	/**
	 * 打印借进归还参数设置
	 * id为借进归还单ID
	 */
	public Map putJJGHParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询出借进归还明细  具体字段看下 jjgh.jrxml或者tmLianceReturnDetail_JJGH_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		TmLianceReturn queryEntity = new TmLianceReturn();
		queryEntity.setId(id);
		//借进归还对象
		TmLianceReturn tmLianceReturn = tmLianceReturnService.findByEntity(queryEntity).get(0);
		
		//借进归还明细
		List<TmLianceReturnDetail> tmLianceReturnDetailList = new ArrayList<TmLianceReturnDetail>();
		tmLianceReturnDetailList.addAll(tmLianceReturn.getLianceReturnDetails());
		
		//归还总数量
		Double totalReturnQuantity = 0D;
		//归还总金额
		Double totalReturnPrice = 0D;
		for(TmLianceReturnDetail detail : tmLianceReturnDetailList){
			totalReturnQuantity += detail.getReturnQuantity();
			totalReturnPrice += detail.getCostPrice();
		}
		//借进登记对象
		TmLianceRegVo tmLianceRegVo = null;
		if(tmLianceReturnDetailList != null && tmLianceReturnDetailList.size()>0){
			Long lianceRegId = tmLianceReturnDetailList.get(0).getTmLianceRegisterDetail().getLianceId();
			tmLianceRegVo = tmLianceRegisterService.getLianceRegVo(null, null, null,lianceRegId).get(0);
		}
		//操作者
		TmUser tmUser =  tmUserService.findById(new Long(tmLianceReturn.getUserId()));
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 借进归还单据号
		 */
		reportParameters.put("lianceReturnBill",tmLianceReturn.getLianceReturnBill());
		
		/**
		 * 归还日期
		 */
		reportParameters.put("returnDate",CommonMethod.parseDateToString(tmLianceReturn.getReturnDate(), "yyyy-MM-dd"));
		
		/**
		 * 借进单据号
		 */
		reportParameters.put("lianceBill",tmLianceRegVo.getLianceBill());
		
		/**
		 * 供货商名字
		 */
		reportParameters.put("customerName",tmLianceRegVo.getCustomerName());
		
		/**
		 * 归还数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",CommonMethod.convertRadixPoint(totalReturnQuantity, 2)+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",CommonMethod.convertRadixPoint(totalReturnPrice, 2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmUser.getUserRealName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmLianceReturnDetailList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/jjgh.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmLianceReturnDetail_JJGH_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * select sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation ,
lrd.liance_quantity as lianceQuantity , lrd.liance_price as liancePrice , lrd.liance_quantity  * lrd.liance_price as total
from tm_liance_register lr , tm_liance_register_detail lrd , tb_part_info pi , tm_store_house sh , tm_unit ui
where lr.id = lrd.liance_id and pi.id = lrd.partinfo_id 
and pi.store_house_id = sh.id and ui.id = pi.unit_id

	 */
	
	/**
	 * 打印借进登记参数设置
	 * id为借进登记单ID
	 */
	public Map putJJDJParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询出借进登记明细  具体字段看下 jjdj.jrxml或者tmLianceRegVo_JJDJ_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmLianceRegDetailVo> tmLinaceRegDetailVoList = tmLianceRegisterService.getLianceRegDetailVo(null, null, null, id);
		
		TmLianceRegVo  tmLianceRegVo = tmLianceRegisterService.getLianceRegVo(null, null, null,id).get(0);
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 借进登记单据号
		 */
		reportParameters.put("lianceBill",tmLianceRegVo.getLianceBill());
		
		/**
		 * 借进日期
		 */
		reportParameters.put("lianceDate",CommonMethod.parseDateToString(tmLianceRegVo.getLianceDate(),"yyyy-MM-dd"));
		
		/**
		 * 供货商名字
		 */
		reportParameters.put("customerName",tmLianceRegVo.getCustomerName()+"");
		
		/**
		 * 归还数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmLianceRegVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmLianceRegVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmLianceRegVo.getOperationName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmLinaceRegDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/jjdj.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmLianceRegVo_JJDJ_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 *select sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
rsd.quantity as quantity ,rsd.cost_price as costPrice ,rsd.quantity*rsd.cost_price as total
from tm_remove_stock rs , tm_remove_stock_detail rsd ,  tb_part_info pi , tm_store_house sh , tm_unit ui
where rs.id = rsd.remove_stock_id and pi.id = rsd.partinfo_id
and pi.store_house_id = sh.id and ui.id = pi.unit_id

	 * 
	 */
	/**
	 * 打印移库出仓参数设置
	 * id为移库出仓单ID
	 */
	public Map putYKCCParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询移库出仓明细  具体字段看下 ykcc.jrxml或者TmRemStockVo_YKCC_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		List<TmRemStockDetailVo>  tmRemStockDetailVoList = tmRemoveStockService.getRemStockDetVos(null, null, id);
		
		TmRemStockVo tmRemStockVo = tmRemoveStockService.getAllRemStockVos(null, null, null, id).get(0);
		
		Double totalQuantity = 0D;
		
		Double totalPrice = 0D;
		
		for(TmRemStockDetailVo detailVo : tmRemStockDetailVoList){
			totalPrice += detailVo.getPrice();
			totalQuantity += detailVo.getQuantity();
		}
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 移库出仓单据号
		 */
		reportParameters.put("removeStockBill",tmRemStockVo.getRemoveStockBill());
		
		/**
		 * 源仓库
		 */
		reportParameters.put("fromStoreHouse",tmRemStockVo.getHouseName());
		
		/**
		 * 目标仓库
		 */
		reportParameters.put("toStoreHouse","");
		
		/**
		 * 移出日期
		 */
		reportParameters.put("removeDate",CommonMethod.parseDateToString(tmRemStockVo.getRemoveDate(),"yyyy-MM-dd"));
		
		/**
		 * 移出数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",CommonMethod.convertRadixPoint(totalQuantity, 2)+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",CommonMethod.convertRadixPoint(totalPrice, 2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmRemStockVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmRemStockDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/ykcc.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmRemStockVo_YKCC_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * select sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
rsd.quantity as quantity ,rsd.cost_price as costPrice ,rsd.quantity*rsd.cost_price as total
from tm_shiftin_stock sis ,tm_remove_stock rs, tm_remove_stock_detail rsd ,  tb_part_info pi , tm_store_house sh , tm_unit ui
where sis.remove_stock_id = rs.id  and rs.id = rsd.remove_stock_id and pi.id = rsd.partinfo_id
and pi.store_house_id = sh.id and ui.id = pi.unit_id

	 */
	/**
	 * 打印移库进仓参数设置
	 * id为移库进仓单ID
	 */
	public Map putYKJCParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询移库进仓明细  具体字段看下 ykjc.jrxml或者tmShiftinStockVo_YKJC_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		TmShiftinStockVo tmShiftinStockVo = tmShiftinStockService.getTmshiftinStockVos(null, null,id).get(0);
		
		List<TmRemStockDetailVo>  tmRemStockDetailVoList = tmRemoveStockService.getRemStockDetVos(null, null, tmShiftinStockVo.getRemoveStockId());
		
		Double totalQuantity = 0D;
		
		Double totalPrice = 0D;
		
		for(TmRemStockDetailVo detailVo : tmRemStockDetailVoList){
			totalPrice += detailVo.getPrice();
			totalQuantity += detailVo.getQuantity();
		}
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 移库进仓单据号
		 */
		reportParameters.put("shiftinBill",tmShiftinStockVo.getShiftinBill());
		
		/**
		 * 移库出仓单据号
		 */
		reportParameters.put("removeStockBill",tmShiftinStockVo.getRemoveStockBill());
		
		/**
		 * 源仓库
		 */
		reportParameters.put("removeHouseName",tmShiftinStockVo.getRemoveHouseName());
		
		/**
		 * 目标仓库
		 */
		reportParameters.put("shiftinHouseName",tmShiftinStockVo.getShiftinHouseName());
		
		/**
		 * 进仓日期
		 */
		reportParameters.put("shiftinDate",CommonMethod.parseDateToString(tmShiftinStockVo.getShiftinDate(),"yyyy-MM-dd"));
		
		/**
		 * 移进数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",CommonMethod.convertRadixPoint(totalQuantity, 2)+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",CommonMethod.convertRadixPoint(totalPrice, 2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmShiftinStockVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmRemStockDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/ykjc.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmShiftinStockVo_YKJC_pdf_tpl.properties");
		
		return map;
	}
	/**
	 * select sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
m.part_quantity as partQuantity , m.price as price , m.part_quantity  * m.price as total
from tb_maintain_part_content m  , tb_part_info pi , tm_store_house sh , tm_unit ui
where m.part_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id

	 */
	
	/**
	 * 打印维修发料参数设置
	 * id为维修发料单ID
	 */
	public Map putWXFLParamMap(String maintainCode){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询维修发料明细  具体字段看下 wxfl.jrxml或者tmShiftinStockVo_YKJC_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		List<TbMaintianVo> tbmList = tbMaintainPartContentService.getTbMaintianDetailVos(maintainCode);
		List<TbMaintianVo> tbMaintainVoList = new ArrayList<TbMaintianVo>(); 
		for(TbMaintianVo maintainVo : tbmList){
			if(maintainVo.getIsPrint() == null || maintainVo.getIsPrint().equals("Y")){
				tbMaintainVoList.add(maintainVo);
			}
		}
		
		TbMaintianVo tbMaintianVo = null;
		if(tbMaintainVoList != null && tbMaintainVoList.size() >0){
			tbMaintianVo = tbMaintainVoList.get(0);
		}
		
		//发料总数量
		Double totalQuantity = 0D;
		//发料总金额
		Double totalPrice = 0D;
		for(TbMaintianVo detail : tbMaintainVoList){
			totalQuantity += detail.getPartQuantity();
			if(detail.getIsFree().equals(Constants.FREESYMBOL_NO))
				totalPrice += detail.getPartQuantity()*detail.getPrice();
		} 
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 发料单号
		 */
		reportParameters.put("maintainCode",tbMaintianVo.getMaintainCode());
		
		/**
		 * 客户名称
		 */
		reportParameters.put("customerName",tbMaintianVo.getCustomerName());
		
		/**
		 * 委托书号
		 */
		reportParameters.put("entrustCode",tbMaintianVo.getEntrustCode());
		
		/**
		 * 牌照号
		 */
		reportParameters.put("licenseCode",tbMaintianVo.getLicenseCode());
		
		/**
		 * 修理类型
		 */
		reportParameters.put("fixType",tbMaintianVo.getFixType()+"");
		
		/**
		 * 修理日期
		 */
		reportParameters.put("fixDate",CommonMethod.parseDateToString(tbMaintianVo.getFixDate(),"yyyy-MM-dd"));
		
		/**
		 * 发料数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",CommonMethod.convertRadixPoint(totalQuantity, 2)+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",CommonMethod.convertRadixPoint(totalPrice, 2)+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tbMaintianVo.getUserServiceName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tbMaintainVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/wxfl.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tbMaintianVo_WXFL_pdf_tpl.properties");
		
		return map;
	}
	/**
	 * select  sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
sod.quantity as quantity , sod.price as price ,sod.quantity * sod.price as total
from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi , tm_store_house sh , tm_unit ui
where so.id = sod.stockout_id and sod.partinfo_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id and so.out_type = 14 

	 */
	/**
	 * 打印调拨出库参数设置
	 * id为调拨出库单ID
	 */
	public Map putDBCKParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询调拨出库明细  具体字段看下 dbck.jrxml或者tmStockOutDetVo_DBCK_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmStockOutDetVo> tmStockOutDetVoList = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, id, StockTypeElements.ALLOTSTOCKOUT.getElementValue());
		
		TmStockOutVo tmStockOutVo = tmStockOutService.getStockOutVos(null, StockTypeElements.ALLOTSTOCKOUT.getElementValue(),null,id).get(0);

		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 调拨单号
		 */
		reportParameters.put("stockOutCode",tmStockOutVo.getStockOutCode());
		
		/**
		 * 客户名称
		 */
		reportParameters.put("customerName",tmStockOutVo.getCustomerName());
		
		/**
		 * 调拨日期
		 */
		reportParameters.put("stockOutDate",CommonMethod.parseDateToString(tmStockOutVo.getStockOutDate(),"yyyy-MM-dd"));
		
		/**
		 * 调拨数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockOutVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmStockOutVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockOutVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmStockOutDetVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/dbck.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockOutDetVo_DBCK_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * select  sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
sid.quantity as quantity , sid.price as costPrice , sid.quantity * sid.price as  total
from tm_stock_in si , tm_stockin_detail sid , tb_part_info pi , tm_store_house sh , tm_unit ui
where si.id = sid.stock_id and sid.partinfo_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id and si.in_type = 2

	 */
	/**
	 * 打印调拨进仓参数设置
	 * id为调拨进仓单ID
	 */
	public Map putDBJCParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询调拨进仓明细  具体字段看下 dbjc.jrxml或者tmStockInDetailVo_DBJC_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmStockInDetailVo> tmStockInDetailVoList = tmStockinDetailService.getStockInDetVo(StockTypeElements.ALLOT.getElementValue(), id, null);
		
		TmStockInVo tmStockInVo = tmStockinDetailService.getStockInVo(StockTypeElements.ALLOT.getElementValue(), null, id).get(0);
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 调拨进仓单号
		 */
		reportParameters.put("stockInCode",tmStockInVo.getStockInCode());
		
		/**
		 * 客户名称
		 */
		reportParameters.put("customerName",tmStockInVo.getSupplierName());
		
		/**
		 * 调拨日期
		 */
		reportParameters.put("arriveDate",CommonMethod.parseDateToString(tmStockInVo.getArriveDate(),"yyyy-MM-dd"));
		
		/**
		 * 调拨数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockInVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmStockInVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockInVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmStockInDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/dbjc.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockInDetailVo_DBJC_pdf_tpl.properties");
		
		return map;
	}
	/**
	 * select  sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
sod.quantity as quantity , sod.price as price ,sod.quantity * sod.price as total
from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi , tm_store_house sh , tm_unit ui
where so.id = sod.stockout_id and sod.partinfo_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id and so.out_type = 13 

	 */
	/**
	 * 打印配件报损参数设置
	 * id为配件报损单ID
	 */
	public Map putPJBSParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询配件报损明细  具体字段看下 pjbs.jrxml或者tmStockOutDetVo_PJBS_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		
		List<TmStockOutDetVo> tmStockOutDetVoList = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, id, StockTypeElements.SHATTERSTOCKOUT.getElementValue());
		
		TmStockOutVo tmStockOutVo = tmStockOutService.getStockOutVos(null, StockTypeElements.SHATTERSTOCKOUT.getElementValue(),null,id).get(0);



		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 报损单号
		 */
		reportParameters.put("stockOutCode",tmStockOutVo.getStockOutCode());
		
		/**
		 * 报损日期
		 */
		reportParameters.put("stockOutDate",CommonMethod.parseDateToString(tmStockOutVo.getStockOutDate(), "yyyy-MM-dd"));
		
		/**
		 * 报损数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockOutVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmStockOutVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockOutVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmStockOutDetVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/pjbs.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockOutDetVo_PJBS_pdf_tpl.properties");
		
		return map;
	}
	/**
	 * select  sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
sid.quantity as quantity , sid.price as costPrice , sid.quantity * sid.price as  total
from tm_stock_in si , tm_stockin_detail sid , tb_part_info pi , tm_store_house sh , tm_unit ui
where si.id = sid.stock_id and sid.partinfo_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id and si.in_type = 3

	 */
	/**
	 * 打印配件报溢参数设置
	 * id为配件报溢单ID
	 */
	public Map putPJBYParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询配件报溢明细  具体字段看下 pjby.jrxml或者tmStockInDetailVo_PJBY_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmStockInDetailVo> tmStockInDetailVoList = tmStockinDetailService.getStockInDetVo(StockTypeElements.OVERFLOW.getElementValue(), id, null);
		
		TmStockInVo tmStockInVo = tmStockinDetailService.getStockInVo(StockTypeElements.OVERFLOW.getElementValue(), null, id).get(0);
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 报溢单号
		 */
		reportParameters.put("stockInCode",tmStockInVo.getStockInCode());
		
		/**
		 * 报溢日期
		 */
		reportParameters.put("stockInDate",CommonMethod.parseDateToString(tmStockInVo.getArriveDate(), "yyyy-MM-dd"));
		
		/**
		 * 报溢数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockInVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmStockInVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockInVo.getUserName()+"");
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmStockInDetailVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/pjby.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockInDetailVo_PJBY_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * select  sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
sod.quantity as quantity , sod.price as price ,sod.quantity * sod.price as total
from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi , tm_store_house sh , tm_unit ui
where so.id = sod.stockout_id and sod.partinfo_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id and so.out_type = 11

	 */
	/**
	 * 打印配件销售参数设置
	 * id为配件销售单ID
	 */
	public Map putPJXSParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询配件销售明细  具体字段看下 pjxs.jrxml或者tmStockOutDetVo_PJXS_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmStockOutDetVo> tmStockOutDetVoList = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, id, StockTypeElements.SELLSTOCKOUT.getElementValue());
		
		TmStockOutVo tmStockOutVo = tmStockOutService.getStockOutVos(null, StockTypeElements.SELLSTOCKOUT.getElementValue(),null,id).get(0);
		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 销售单号
		 */
		reportParameters.put("stockOutCode",tmStockOutVo.getStockOutCode());
		
		/**
		 * 销售类型
		 */
		reportParameters.put("soleType",tmStockOutVo.getSoleTypeName());
		
		/**
		 * 委托书号
		 */
		reportParameters.put("entrustCode",tmStockOutVo.getTrustBill());
		
		/**
		 * 客户号
		 */
		reportParameters.put("customerCode",tmStockOutVo.getCustomerCode());
		
		/**
		 * 客户名称
		 */
		reportParameters.put("customerName",tmStockOutVo.getCustomerName());
		
		/**
		 * 销售日期
		 */
		reportParameters.put("stockOutDate",CommonMethod.parseDateToString(tmStockOutVo.getStockOutDate(), "yyyy-MM-dd"));
		
		/**
		 * 销售数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockOutVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmStockOutVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockOutVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmStockOutDetVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/pjxs.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockOutDetVo_PJXS_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * select  sh.house_name as houseName , pi.part_code as partCode, pi.part_name as partName , ui.unit_name as unitName , pi.store_location as storeLocation,
sod.quantity as quantity , sod.price as price ,sod.quantity * sod.price as total
from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi , tm_store_house sh , tm_unit ui
where so.id = sod.stockout_id and sod.partinfo_id = pi.id and  pi.store_house_id = sh.id and ui.id = pi.unit_id and so.out_type = 12 

	 */
	/**
	 * 打印领用出库参数设置
	 * id为领用出库单ID
	 */
	public Map putLYCKParamMap(Long id){
		
		Map map =  new HashMap();
		
		/**
		 * 这里需要改--查询领用出库明细  具体字段看下 lyck.jrxml或者tmStockOutDetVo_LYCK_pdf_tpl.properties 仓库、配件代码、名称.....有表格的那块
		 */
		
		List<TmStockOutDetVo> tmStockOutDetVoList = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, id, StockTypeElements.DRAWSTOCKOUT.getElementValue());
		
		TmStockOutVo tmStockOutVo = tmStockOutService.getStockOutVos(null, StockTypeElements.DRAWSTOCKOUT.getElementValue(),null,id).get(0);

		
		//报表参数VALUE类型全为java.Lang.String
		Map reportParameters = new HashMap();
		
		/**
		 * 领用单号
		 */
		reportParameters.put("stockOutCode",tmStockOutVo.getStockOutCode());
		
		/**
		 * 领用人
		 */
		reportParameters.put("cliamPerson",tmStockOutVo.getUserRealName());
		
		/**
		 * 领用日期
		 */
		reportParameters.put("stockOutDate",CommonMethod.parseDateToString(tmStockOutVo.getStockOutDate(), "yyyy-MM-dd"));

		/**
		 * 销售数量--所有明细中数量相加之和
		 */
		reportParameters.put("totalQuantity",tmStockOutVo.getTotalQuantity()+"");
		
		/**
		 * 合计--所有明细中金额之和
		 */
		reportParameters.put("totalCount",tmStockOutVo.getTotalPrice()+"");
		
		/**
		 * 操作员名字--增加入库单的人员(谁操作的那张单子)
		 */
		reportParameters.put("userRealName",tmStockOutVo.getUserName());
		
		//打印时间
		reportParameters.put("printDate",CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd "));
		//打印参数--主要是报表中PARAMETERS($P{XX})
		map.put("reportParameters", reportParameters);
		
		//这里不用改--将需要打印出来的LIST放进MAP中 
		map.put("dataSourceList", tmStockOutDetVoList);
		//打印配置文件
		map.put("jrxmlPath", "/reportfiles/lyck.jrxml");
		
		//打印DETAIL区域类
		map.put("reportTpl", "/tmStockOutDetVo_LYCK_pdf_tpl.properties");
		
		return map;
	}
}
