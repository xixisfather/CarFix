package com.selfsoft.baseinformation.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmCustomerTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TbCustomerAction extends ActionSupport implements
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
	private ITbCustomerService tbCustomerService;
	
	private TbCustomer tbCustomer;
	
	private TbCarInfo tbCarInfo;
	
	@Autowired
	private ITmCustomerTypeService tmCustomerTypeService;
	
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	
	@Autowired
	private ITmSoleTypeService tmSoleTypeService;

	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	//客户类型
	private String types;
	//页面对象ID
	private String properties;
	
	private TbFixEntrust tbFixEntrust;
	
	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}

	public String findTbCustomer() throws Exception{
		
		List<TbCustomer>tbCustomerList = tbCustomerService.findByTbCustomer(tbCustomer,null);
		
		ActionContext.getContext().put("tbCustomerList", tbCustomerList);
		
		//客户类别MAP
		ActionContext.getContext().put("customerPropertyMap", Constants.getCustomerPropertyMap());
		//客户类型MAP
		ActionContext.getContext().put("customerTypeMap",tmCustomerTypeService.findAllTmCustomerTypeMap());
		//车型MAP
		ActionContext.getContext().put("tmCarModelTypeMap", tmCarModelTypeService.findAllTmCarModelTypeMap());
		
		List<TbCarInfo>tbCarInfoList = tbCarInfoService.findByTbCarInfo(null);
		
		ActionContext.getContext().put("tbCarInfoList", tbCarInfoList);
		
		ActionContext.getContext().getSession().put("tbCustomerListSession",tbCustomerList);
		
		return Constants.SUCCESS;
	}
	
	public String findRunOffTbCustomer() throws Exception{
		
		if(null==tbFixEntrust){
			
			tbFixEntrust = new TbFixEntrust();
		
		}
		
		Date fixDate = tbFixEntrust.getFixDate();
		
		List<TbCustomer>tbCustomerList = tbCustomerService.findNotComeCustomer(fixDate);
		
		ActionContext.getContext().put("tbCustomerList", tbCustomerList);
		
		return Constants.SUCCESS;
	}
	public String insertTbCustomer() throws Exception{
		
		if(null == tbCustomer.getTmCustomerType().getId()){
			tbCustomer.setTmCustomerType(null);
		}
		if(null == tbCustomer.getTmSoleType().getId()){
			tbCustomer.setTmSoleType(null);
		}
		
		String customerCode = tmDictionaryService.GenerateCode(StockTypeElements.TBCUSTOMER.getElementString());
		
		tbCustomer.setCustomerCode(customerCode);
		
		ActionContext.getContext().put("msg","生成客户号:" + customerCode);
		
		tbCustomerService.insertTbCustomer(tbCustomer);
		
		
		if(StringUtils.isNotBlank(this.types) ){
			return Constants.LISTPAGE;
		}
		

		return Constants.SUCCESS;
		
	}

	public String updateTbCustomer() throws Exception{
		
		if(null == tbCustomer.getTmCustomerType().getId()){
			tbCustomer.setTmCustomerType(null);
		}
		if(null == tbCustomer.getTmSoleType().getId()){
			tbCustomer.setTmSoleType(null);
		}
		tbCustomerService.updateTbCustomer(tbCustomer);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbCustomer() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {
			
			TbCarInfo car = new TbCarInfo();
			
			TbCustomer tbCustomer = new TbCustomer();
			
			tbCustomer.setId(Long.valueOf(id));
			
			car.setTbCustomer(tbCustomer);
			
			List<TbCarInfo> carList = tbCarInfoService.findByTbCarInfo(car);
			
			if(null!=carList&&carList.size()>0){
				
				response.getWriter().print("tbCustomerTable," + Constants.FAILURE);
			
				return null;
			}
			
			boolean flag = tbCustomerService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbCustomerTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbCustomerTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbCustomerTable," + Constants.EXCEPTION);
		}
		return null;
	}
	
	public String pickTbCustomer() throws Exception{
		String customerCode = request.getParameter("customerCode");
		response.setCharacterEncoding("UTF-8");
		if(null!=customerCode&&!"".equals(customerCode)){
			tbCustomer = tbCustomerService.findTbCustomerByCustomerCode(customerCode);
			
			if(null!=tbCustomer){
				response.getWriter().print(Constants.SUCCESS+","+tbCustomer.getId()+","+tbCustomer.getCustomerCode()+","+tbCustomer.getCustomerName());
			}
			else{
				response.getWriter().print(Constants.FAILURE+",");
			}
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		String flag = request.getParameter("flag");
		//客户类别MAP
		ActionContext.getContext().put("customerPropertyMap", Constants.getCustomerPropertyMap());
		//客户类型MAP
		ActionContext.getContext().put("customerTypeMap",tmCustomerTypeService.findAllTmCustomerTypeMap());
		//是否MAP
		ActionContext.getContext().put("isNoMap",Constants.getIsNoMap());
		//车辆性质MAP
		ActionContext.getContext().put("carPropertyMap", Constants.getCarPropertyMap());
		//车辆用途MAP
		ActionContext.getContext().put("carUsedMap", Constants.getCarUsedMap());
		//车型MAP
		ActionContext.getContext().put("tmCarModelTypeMap", tmCarModelTypeService.findAllTmCarModelTypeMap());
		//销售类型
		ActionContext.getContext().put("tmSoleTypeMap",tmSoleTypeService.findAllTmSoleTypeMap());
		if (null != id && !"".equals(id)) {

			tbCustomer = tbCustomerService.findById(Long.valueOf(id));
			
			if(null!=flag&&!"".equals(flag)){
				return Constants.OTHERPAGE;
			}

			return Constants.EDITPAGE;
		}
		//委托书处创建
		if("wtsAdd".equals(flag)){
			
			return Constants.VIEWPAGE;
			
		}
		
		if("importXlsPage1".equals(flag)){
			
			return Constants.IMPORTXLS+"1";
			
		}
		if("importXlsPage2".equals(flag)){
			
			return Constants.IMPORTXLS+"2";
			
		}
		
		return Constants.ADDPAGE;
	}
	
	public String insertTbCustomerCar() throws Exception{
		
		if(null == tbCustomer.getTmCustomerType().getId()){
			tbCustomer.setTmCustomerType(null);
		}
		if(null == tbCustomer.getTmSoleType().getId()){
			tbCustomer.setTmSoleType(null);
		}
		
		String customerCode = tmDictionaryService.GenerateCode(StockTypeElements.TBCUSTOMER.getElementString());
		
		tbCustomer.setCustomerCode(customerCode);
		
		ActionContext.getContext().put("msg","生成客户号:" + customerCode);
		
		tbCustomerService.insertAll(tbCustomer, tbCarInfo);
		
		return Constants.SUCCESS;
	}
	
	private File fileXls;
	
	public File getFileXls() {
		return fileXls;
	}

	public void setFileXls(File fileXls) {
		this.fileXls = fileXls;
	}

	public String tbCustomerImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		String flag = tbCustomerService.tbCustomerImportXls(in, "/tbCustomerInfo_import_xls_tpl.properties");
		
		String e3Table = request.getParameter("e3Table");
		
		ActionContext.getContext().put("e3Table",e3Table);
		
		String[] forword = flag.split(",");
		
		if(null!=forword&&forword.length>0){
			
			if("success".equals(forword[0])){
				
				ActionContext.getContext().put("xlsMessage", "生成客户号: " + forword[1]);
				
				return Constants.SUCCESS;
				
			}
			
			else{
				
				List<String> xlsMessageList = new ArrayList<String>();
				
				for(int i = 0 ; i < forword.length ; i++){
					xlsMessageList.add(forword[i]);
				}
				ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				
			}
			
		}
		
		return Constants.FAILURE;
	}
	
	public String tbCustomerExportXls() throws Exception{
	
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "客户信息";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();
		
		List<TbCustomer> tbCustomerList = (List<TbCustomer>) ActionContext.getContext().getSession().get("tbCustomerListSession");
	
		tbCustomerService.tbCustomerInfoExportXls(os, "/tbCustomerInfo_export_xls_tpl.properties", tbCustomerList);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	
	public String selectTbCustomerForCard() throws Exception{
		
		List<TbCarInfo> tbCarInfoList = tbCarInfoService.findByTbCarInfo(tbCarInfo);
		
		ActionContext.getContext().put("tbCarInfoList", tbCarInfoList);
		
		return Constants.SUCCESS;
	}
}	
