package com.selfsoft.business.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.dao.ITmStockinDetailDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.service.ITmRemoveStockService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.IPrintReport;
import com.selfsoft.framework.common.PrintReport;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.file.ReportFileFromStream;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class PrintReportAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 4964065034898584290L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private IPrintReport printReport;

	/**
	 * 打印采购入库
	 * @throws Exception
	 */
	public void printCGRK() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putCGRKParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印采购退货
	 * @throws Exception
	 */
	public void printCGTH() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putCGTHParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印借出登记
	 * @throws Exception
	 */
	public void printJCDJ() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putJCDJParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	/**
	 * 打印借出归还
	 * @throws Exception
	 */
	public void printJCGH() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putJCGHParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印借进登记
	 * @throws Exception
	 */
	public void printJJDJ() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putJJDJParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印借进归还
	 * @throws Exception
	 */
	public void printJJGH() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putJJGHParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印移库出仓
	 * @throws Exception
	 */
	public void printYKCC() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putYKCCParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印移库进仓
	 * @throws Exception
	 */
	public void printYKJC() throws Exception{
		
		String id = request.getParameter("id");
		
		Map map = printReport.putYKJCParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
		
	}
	
	/**
	 * 打印维修发料
	 * @throws Exception
	 */
	public void printWXFL() throws Exception{
		String maintainCode = request.getParameter("id");
		
		Map map = printReport.putWXFLParamMap(maintainCode);
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
	/**
	 * 打印调拨出库
	 * @throws Exception
	 */
	public void printDBCK() throws Exception{
		String id = request.getParameter("id");
		
		Map map = printReport.putDBCKParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
	/**
	 * 打印调拨入库
	 * @throws Exception
	 */
	public void printDBJC() throws Exception{
		String id = request.getParameter("id");
		
		Map map = printReport.putDBJCParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
	/**
	 * 打印配件报损
	 * @throws Exception
	 */
	public void printPJBS() throws Exception{
		String id = request.getParameter("id");
		
		Map map = printReport.putPJBSParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
	
	/**
	 * 打印配件报溢
	 * @throws Exception
	 */
	public void printPJBY() throws Exception{
		String id = request.getParameter("id");
		
		Map map = printReport.putPJBYParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
	/**
	 * 打印配件销售
	 * @throws Exception
	 */
	public void printPJXS() throws Exception{
		String id = request.getParameter("id");
		
		Map map = printReport.putPJXSParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
	/**
	 * 打印领用出库
	 * @throws Exception
	 */
	public void printLYCK() throws Exception{
		String id = request.getParameter("id");
		
		Map map = printReport.putLYCKParamMap(Long.valueOf(id));
		
		ReportFileFromStream.parsePdfFromStream(request, response,map);
	}
	
}
