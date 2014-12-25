package com.selfsoft.baseparameter.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.baseparameter.service.ITmWorkingHourPriceService;
import com.selfsoft.framework.common.Constants;

public class TmCarModelTypeAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -4767684487511768042L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	@Autowired
	private ITmCarStationTypeService tmCarStationTypeService;
	
	@Autowired
	private ITmWorkingHourPriceService tmWorkingHourPriceService;
	
	private TmCarModelType tmCarModelType;

	public TmCarModelType getTmCarModelType() {
		return tmCarModelType;
	}

	public void setTmCarModelType(TmCarModelType tmCarModelType) {
		this.tmCarModelType = tmCarModelType;
	}
	
	public String findTmCarModelType() throws Exception{
		
		List<TmCarModelType>tmCarModelTypeList = tmCarModelTypeService.findByTmCarModelType(tmCarModelType);
		
		ActionContext.getContext().put("tmCarModelTypeList", tmCarModelTypeList);
		//车辆类型下拉列表
		//ActionContext.getContext().put("tmCarModelTypeMap", tmCarModelTypeService.findAllTmCarModelTypeMap());
		//车型工位类型下拉列表
		ActionContext.getContext().put("tmCarStationTypeMap",tmCarStationTypeService.findAllTmCarStationTypeMap());

		return Constants.SUCCESS;
	}
	
	public String insertTmCarModelType() throws Exception{
		boolean flag = tmCarModelTypeService.insert(tmCarModelType);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}

	public String updateTmCarModelType() throws Exception{
		boolean flag = tmCarModelTypeService.update(tmCarModelType);

		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	
	public String deleteTmCarModelType() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmCarModelTypeService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmCarModelTypeTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmCarModelTypeTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmCarModelTypeTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		
		String id = request.getParameter("id");
		
		String flag = request.getParameter("flag");
		//车型工位类型下拉列表
		ActionContext.getContext().put("tmCarStationTypeMap",tmCarStationTypeService.findAllTmCarStationTypeMap());
		//工时单价
		ActionContext.getContext().put("tmWorkingHourPriceMap", tmWorkingHourPriceService.findAllTmWorkingHourPriceMap());
		
		if (null != id && !"".equals(id)) {

			tmCarModelType = tmCarModelTypeService.findById(Long.valueOf(id));

			return Constants.EDITPAGE;
		}
		
		if("importXlsPage".equals(flag)){
			
			return Constants.IMPORTXLS;
			
		}
		
		return Constants.ADDPAGE;
	}
	
	private File fileXls;
	
	public File getFileXls() {
		return fileXls;
	}

	public void setFileXls(File fileXls) {
		this.fileXls = fileXls;
	}
	
	public String tmCarModelTypeImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		String flag = tmCarModelTypeService.importTmCarModelType(in, "/tmCarModelType_import_xls_tpl.properties");
		
		String[] forword = flag.split(",");
		
		if(null!=forword&&forword.length>0){
			
			if("success".equals(forword[0])){
				
				ActionContext.getContext().put("xlsMessage", forword[1]);
				
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
}
