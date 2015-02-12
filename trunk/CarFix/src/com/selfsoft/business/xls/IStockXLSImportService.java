package com.selfsoft.business.xls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TbPartInfoStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TbStoreHouseSurveyVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.common.exception.MinusException;

public interface IStockXLSImportService {

	public List pjmrckImportXls(InputStream in , String tpl) throws IOException;
	
	public List pjqjsfcImportXls(InputStream in , String tpl) throws IOException;
	
	/**
	 * 仓库概要xls导入
	 */
	public List ckgyImportXls(InputStream in , String tpl) throws IOException;
	/**
	 * 采购入库xls导入
	 */
	public List cgrkImportXls(InputStream in , String tpl) throws IOException;
	/**
	 * 配件每日出库删除
	 */
	public boolean pjmrckDeleteXls(List<DailyStockOutVo> dailyStockOutList, String xlsPK);
	/**
	 * 配件每日出库结果统计
	 */
	public DailyStockOutVo pjmrckCountObj(List<DailyStockOutVo>  dailyStockOutList);
	/**
	 * 仓库概要删除  
	 */
	public boolean ckgyDeleteXls(List<TbStoreHouseSurveyVo> storeHouseSurveyList, String xlsPK);
	/**
	 * 采购入库删除  
	 */
	public boolean cgrkDeleteXls(List<TmStockInDetailVo> stockInDetailList, String xlsPK);
	/**
	 * 配件期间收发存结果统计
	 */
	public TbPartReceiverStatVo pjqjsfcCountObj(List<TbPartReceiverStatVo>  partReceiverStatList);
	/**
	 * 配件期间收发存删除
	 */
	public boolean pjqjsfcDeleteXls(List<TbPartReceiverStatVo> partReceiverStatList, String xlsPK);
	/**
	 * 配件信息xls导入
	 * @param in
	 * @param tpl
	 * @return
	 * @throws IOException
	 */
	public String pjxxImportXls(InputStream in , String tpl) throws IOException;
	/**
	 * 配件信息xls导出
	 * @param os
	 * @param tpl
	 * @param tbPartInfoList
	 */
	public void pjxxExportXls(OutputStream os,String tpl,List<TbPartInfo> tbPartInfoList);
	/**
	 * 采购入库导入
	 * @param in
	 * @param tpl
	 * @return
	 * @throws IOException
	 * @throws MinusException 
	 */
	public String cgrkV2ImportXls(InputStream in , String tpl) throws IOException, MinusException;
	/**
	 * 收发单据采购入库导出
	 * @param os
	 * @param tpl
	 * @param stockInDetails
	 */
	public void cgrkStatisticsExportXls(OutputStream os,String tpl,List<TmStockInDetailVo> stockInDetails);
	/**
	 *  收发单据维修发料导出
	 * @param os
	 * @param tpl
	 * @param maintainDetails
	 */
	public void wxflStatisticsExportXls(OutputStream os,String tpl,List<TbMaintianVo> maintainDetails);
	
	
	/**
	 *  采购入库-采购明细导出
	 * @param os
	 * @param tpl
	 * @param maintainDetails
	 */
	public void stockInDetailsExportXls(OutputStream os,String tpl,List<TmStockInDetailVo> stockInDetails);
	
	/**
	 * 配件出库排行榜-采购明细导出
	 * @param os
	 * @param tpl
	 * @param maintainDetails
	 */
	public void topPartStockoutExportXls(OutputStream os,String tpl,List<TbPartInfoStockOutVo> stockOutDetails);
}
