package com.selfsoft.business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TbStoreHouseSurveyVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.xls.IStockXLSImportService;
import com.selfsoft.framework.common.Constants;

public class  StockXLSImportAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -4077408788527093637L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Autowired
	private IStockXLSImportService stockXLSImportService;
	
	
	private File fileXls;
	
	
	public File getFileXls() {
		return fileXls;
	}

	public void setFileXls(File fileXls) {
		this.fileXls = fileXls;
	}

	/**
	 * 初始化配件每日出库页面
	 * @return
	 * @throws Exception
	 */
	public String initPjmrckImportXlsPage() throws Exception{
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件每日出库xls导入
	 * @return
	 * @throws Exception
	 */
	public String pjmrckImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		List list = stockXLSImportService.pjmrckImportXls(in, "/stockxlsimport/DailyStockOutVo_import_xls_tpl.properties");
		
		List<DailyStockOutVo> dailyStockOutList  = (List<DailyStockOutVo>) list.get(0);
		
		DailyStockOutVo countVo =  (DailyStockOutVo) list.get(1);
		
		String flag = (String) list.get(2);
		
		return this.handlerFlag(flag, "pjmrckList", dailyStockOutList, "pjmrckCount", countVo);
	}
	

	/**
	 * 配件每日出库列表
	 * @return
	 * @throws Exception
	 */
	public String pjmrckImportXlsResult() throws Exception{
		List<DailyStockOutVo> objList = (List<DailyStockOutVo>) request.getSession().getAttribute("pjmrckList");
		DailyStockOutVo objCountVo =  (DailyStockOutVo) request.getSession().getAttribute("pjmrckCount");
		
		if(objList != null ){
			ActionContext.getContext().put("results", objList);
			ActionContext.getContext().put("countVo", objCountVo);
		}
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件每日出库删除
	 * @return
	 * @throws IOException
	 */
	public String pjmrckDelete() throws IOException{
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {
			
			List<DailyStockOutVo> objList = (List<DailyStockOutVo>) request.getSession().getAttribute("pjmrckList");
			
			boolean flag = stockXLSImportService.pjmrckDeleteXls(objList, id);
			
			DailyStockOutVo countVo = stockXLSImportService.pjmrckCountObj(objList);
			
			request.getSession().setAttribute("pjmrckCount" , countVo);
			
			ActionContext.getContext().put("countVo", countVo);
			
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPartInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPartInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPartInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	/**
	 * 初始化配件期间收发存页面
	 * @return
	 * @throws Exception
	 */
	public String initPjqjsfcmportXlsPage() throws Exception{
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件期间收发存xls导入
	 * @return
	 * @throws Exception
	 */
	public String pjqjsfcImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		List list =  stockXLSImportService.pjqjsfcImportXls(in, "/stockxlsimport/TbPartReceiverStatVo_import_xls_tpl.properties");
		
		List<TbPartReceiverStatVo> tbPartReceiverStatVoList  = (List<TbPartReceiverStatVo>) list.get(0);
		
		TbPartReceiverStatVo countVo =  (TbPartReceiverStatVo) list.get(1);
		
		String flag = (String) list.get(2);
		
		
		return this.handlerFlag(flag, "pjqjsfcList", tbPartReceiverStatVoList, "pjqjsfcCount", countVo);
	}
	
	/**
	 * 配件期间收发存导入列表
	 * @return
	 * @throws Exception
	 */
	public String pjqjsfcImportXlsResult() throws Exception{
		List<TbPartReceiverStatVo> objList = (List<TbPartReceiverStatVo>) request.getSession().getAttribute("pjqjsfcList");
		TbPartReceiverStatVo objCountVo =  (TbPartReceiverStatVo) request.getSession().getAttribute("pjqjsfcCount");
		
		if(objList != null ){
			ActionContext.getContext().put("results", objList);
			ActionContext.getContext().put("countVo", objCountVo);
		}
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件期间收发存删除
	 * @return
	 * @throws IOException
	 */
	public String pjqjsfcDelete() throws IOException{
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {
			
			List<TbPartReceiverStatVo> objList = (List<TbPartReceiverStatVo>) request.getSession().getAttribute("pjqjsfcList");
			
			
			boolean flag = stockXLSImportService.pjqjsfcDeleteXls(objList, id);
			
			TbPartReceiverStatVo countVo = stockXLSImportService.pjqjsfcCountObj(objList);
			
			request.getSession().setAttribute("pjqjsfcCount" , countVo);
			
			ActionContext.getContext().put("countVo", countVo);
			
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPartInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPartInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPartInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	/**
	 * 初始化仓库概要页面
	 * @return
	 * @throws Exception
	 */
	public String initCkgmImportXlsPage() throws Exception{
		
		return Constants.SUCCESS;
	}

	/**
	 * 仓库概要xls导入
	 * @return
	 * @throws Exception
	 */
	public String ckgmImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		List list = stockXLSImportService.ckgyImportXls(in, "/stockxlsimport/TbStoreHouseSurveyVo_import_xls_tpl.properties");
		
		List<TbStoreHouseSurveyVo> tbStoreHouseSurveyList  = (List<TbStoreHouseSurveyVo>) list.get(0);
		
		TbStoreHouseSurveyVo countVo =  (TbStoreHouseSurveyVo) list.get(1);
		
		String flag = (String) list.get(2);
		
		return this.handlerFlag(flag, "ckgyList", tbStoreHouseSurveyList, "ckgyCount", countVo);
	}
	
	
	/**
	 * 仓库概要导入列表
	 * @return
	 * @throws Exception
	 */
	public String ckgmImportXlsResult() throws Exception{
		List<TbStoreHouseSurveyVo> objList = (List<TbStoreHouseSurveyVo>) request.getSession().getAttribute("ckgyList");
		
		if(objList != null ){
			ActionContext.getContext().put("results", objList);
		}
		return Constants.SUCCESS;
	}
	
	public String initCgrkImportXlsPage() throws Exception{
		
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 仓库概要删除
	 * @return
	 * @throws IOException
	 */
	public String ckgmDelete() throws IOException{
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {
			
			List<TbStoreHouseSurveyVo> objList = (List<TbStoreHouseSurveyVo>) request.getSession().getAttribute("ckgyList");
			
			
			boolean flag = stockXLSImportService.ckgyDeleteXls(objList, id);
			
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPartInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPartInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPartInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}

	/**
	 * 采购入库xls导入
	 * @return
	 * @throws Exception
	 */
	public String cgrkImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		List list = stockXLSImportService.cgrkImportXls(in, "/stockxlsimport/TmStockInDetailVo_import_xls_tpl.properties");
		
		List<TmStockInDetailVo> tmStockInDetailVoList  = (List<TmStockInDetailVo>) list.get(0);
		
		TmStockInDetailVo countVo =  (TmStockInDetailVo) list.get(1);
		
		String flag = (String) list.get(2);
		
		List<TmStockInDetailVo> customerVoList = (List<TmStockInDetailVo>) list.get(3);
		
		request.getSession().setAttribute("customerList", customerVoList);
		
		return this.handlerFlag(flag, "cgrkList", tmStockInDetailVoList, "cgrkCount", countVo);
	}
	
	
	/**
	 * 采购入库导入列表
	 * @return
	 * @throws Exception
	 */
	public String cgrkImportXlsResult() throws Exception{
		List<TmStockInDetailVo> objList = (List<TmStockInDetailVo>) request.getSession().getAttribute("cgrkList");
		TmStockInDetailVo objCountVo =  (TmStockInDetailVo) request.getSession().getAttribute("cgrkCount");
		List<TmStockInDetailVo> customerList = (List<TmStockInDetailVo>) request.getSession().getAttribute("customerList");
		if(objList != null ){
			ActionContext.getContext().put("results", objList);
			ActionContext.getContext().put("countVo", objCountVo);
			ActionContext.getContext().put("customerList", customerList);
			
		}
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 采购入库删除
	 * @return
	 * @throws IOException
	 */
	public String cgrkDelete() throws IOException{
		String id = request.getParameter("id");

		if (StringUtils.isNotBlank(id)) {
			
			List<TmStockInDetailVo> objList = (List<TmStockInDetailVo>) request.getSession().getAttribute("cgrkList");
			
			boolean flag = stockXLSImportService.cgrkDeleteXls(objList, id);
			
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbPartInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbPartInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbPartInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	
	private String handlerFlag(String flag,String listKey,Object listValue,String countKey,Object countValue){
		
		if(null!=flag){
			
			String xlsMessage[] = flag.split("-");
			List<String> xlsMessageList = null;
			if("success".equals(xlsMessage[0])){
				
				String[] successMessage = xlsMessage[1].split(",");
				
				
				ActionContext.getContext().put("xlsMessage", successMessage[0]);
				
				if(successMessage.length==2){
					
					xlsMessageList = new ArrayList<String>();
					
					String[] repeatMessage = successMessage[1].split(";");
					
					for(int i = 0 ; i < repeatMessage.length ; i++){
						xlsMessageList.add(repeatMessage[i]);
					}
					ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				}
				request.getSession().setAttribute(listKey, listValue);
				request.getSession().setAttribute(countKey, countValue);
				return Constants.SUCCESS;
			}
			else if("failError".equals(xlsMessage[0])){
				xlsMessageList = new ArrayList<String>();
				String[] errorMessage = xlsMessage[1].split(",");
				for(int i = 0 ; i < errorMessage.length ; i++){
					xlsMessageList.add(errorMessage[i]);
				}
				ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				return Constants.FAILURE;
			}
			else{
				ActionContext.getContext().put("xlsMessage", xlsMessage[1]);
				return Constants.FAILURE;
			}
			
			
		}
		
		ActionContext.getContext().put("xlsMessage", "文件中数据类型不符合要求");
		
		return Constants.FAILURE;
	}
	
}
