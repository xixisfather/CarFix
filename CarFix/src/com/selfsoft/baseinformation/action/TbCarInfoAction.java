package com.selfsoft.baseinformation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbReturnVisit;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbReturnVisitService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;

public class TbCarInfoAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -2934952191362363384L;
	
	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	
	private TbCarInfo tbCarInfo;
	
	private TbCustomer tbCustomer;
	
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;

	@Autowired
	private ITbCustomerService tbCustomerService;
	
	@Autowired
	private ITbReturnVisitService tbReturnVisitService;
	
	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}
	
	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	public String findTbCarInfo() throws Exception{
		
		List<TbCarInfo>tbCarInfoList = tbCarInfoService.findByTbCarInfo(tbCarInfo);
		
		ActionContext.getContext().put("tbCarInfoList", tbCarInfoList);
		
		return Constants.SUCCESS;
	}
	
	public String insertTbCarInfo() throws Exception{
		
		if(null!=tbCarInfo){
			tbCarInfo.setTbCustomer(tbCustomer);
		}
		
		tbCarInfoService.insertTbCarInfo(tbCarInfo);

		return Constants.SUCCESS;
		
	}

	public String updateTbCarInfo() throws Exception{
		
		tbCarInfoService.updateTbCarInfo(tbCarInfo);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbCarInfo() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {
			
			boolean flag = tbCarInfoService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbCarInfoTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbCarInfoTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbCarInfoTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");
		
		String flag = request.getParameter("flag");
		//是否MAP
		ActionContext.getContext().put("isNoMap",Constants.getIsNoMap());
		//车辆性质MAP
		ActionContext.getContext().put("carPropertyMap", Constants.getCarPropertyMap());
		//车辆用途MAP
		ActionContext.getContext().put("carUsedMap", Constants.getCarUsedMap());
		//车型MAP
		ActionContext.getContext().put("tmCarModelTypeMap", tmCarModelTypeService.findAllTmCarModelTypeMap());
		

		if (null != id && !"".equals(id)) {

			tbCarInfo = tbCarInfoService.findById(Long.valueOf(id));
			
			tbCustomer = tbCustomerService.findById(tbCarInfo.getTbCustomer().getId());
			
			if(null!=flag&&!"".equals(flag)){
				return Constants.OTHERPAGE;
			}

			return Constants.EDITPAGE;
		}
		return Constants.ADDPAGE;
	}
	
	public String findTbCarInfoBylicenseCode() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String licenseCode = CommonMethod.TransferToEncoding(request.getParameter("licenseCode").trim(), "ISO-8859-1", "GBK");
		
		TbCarInfo t = tbCarInfoService.findTbCarInfoBylicenseCode(licenseCode);
		
		if(null!=t){
			
			response.getWriter().print("success,"+t.getLicenseCode()+","+t.getChassisCode()+","+t.getTmCarModelType().getId()+","+CommonMethod.parseDateToString(t.getPurchaseDate(), "yyyy-MM-dd")+","+t.getEngineCode()+","+t.getTbCustomer().getCustomerName()+","+t.getTbCustomer().getContractPerson()+","+t.getTbCustomer().getPhone()+","+t.getTbCustomer().getTelephone()+","+t.getTbCustomer().getAddress()+","+t.getTbCustomer().getCustomerCode()+','+t.getId()+','+t.getTbCustomer().getId());
		}
		else{
			response.getWriter().print("fail,");
		}
		return null;
	}
	
	public String findMaintainAlertCar(){
		
		List<TbCarInfo> maintailCarList = tbCarInfoService.findMaintainCarInfo();
		
		ActionContext.getContext().put("maintailCarList", maintailCarList);
		
		return Constants.SUCCESS;
	}
	
	public String findInsuranceAlertCar(){
		
		List<TbCarInfo> insuranceCarList = tbCarInfoService.findInsuranceCarInfo();
		
		ActionContext.getContext().put("insuranceCarList", insuranceCarList);
		
		return Constants.SUCCESS;
	}
	
	public String lostTbCarInfo(){
		
		List<TbCarInfo> tbCarInfoLost = tbCarInfoService.findLostCar(tbCarInfo);
		
		ActionContext.getContext().put("tbCarInfoLost", tbCarInfoLost);
		
		TbReturnVisit tbReturnVisit = new TbReturnVisit();
		
		tbReturnVisit.setReturnType("1");

		List<TbReturnVisit> tbReturnVisitList = tbReturnVisitService.findByTbReturnVisit(tbReturnVisit);
		
		ActionContext.getContext().put("tbReturnVisitList", tbReturnVisitList);
		
		return Constants.SUCCESS;
	}
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	public String wxhfTbCarInfo() {
		
		List<TbCarInfo> tbCarInfoWXHF = tbCarInfoService.findMaintainHFCar(tbCarInfo);
		
		ActionContext.getContext().put("tbCarInfoWXHF", tbCarInfoWXHF);
		
		TbReturnVisit tbReturnVisit = new TbReturnVisit();
		
		tbReturnVisit.setReturnType("4");

		List<TbReturnVisit> tbReturnVisitList = tbReturnVisitService.findByTbReturnVisit(tbReturnVisit);
		
		List<TbReturnVisit> tbReturnVisitListPage = new ArrayList<TbReturnVisit>();
		
		if(null != tbReturnVisitList && tbReturnVisitList.size() > 0){
			
			for(TbReturnVisit t : tbReturnVisitList){
				
				if(null != t.getEntrustId()){
					
					TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(t.getEntrustId());
					
					t.setTbFixEntrust(tbFixEntrust);
				
				}
				
				tbReturnVisitListPage.add(t);
			}
		}
		ActionContext.getContext().put("tbReturnVisitList", tbReturnVisitListPage);
		
		return Constants.SUCCESS;
		
	}
	
	public String validationTbCarInfo() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String licenseCode = request.getParameter("licenseCode") == null ? "" : CommonMethod.TransferToEncoding(request.getParameter("licenseCode").trim(), "ISO-8859-1", "GBK");
		
		String chassisCode = request.getParameter("chassisCode") == null ? "" : request.getParameter("chassisCode");
		
		String id = request.getParameter("id");
		
		if(null == id || "".equals(id)){
			
			if(null != licenseCode && !"".equals(licenseCode)){
				
				TbCarInfo t = tbCarInfoService.findTbCarInfoBylicenseCode(licenseCode);
		
				if(null!=t){
					
					response.getWriter().print("licenseExist,");
				}
				else{
					response.getWriter().print("licenseNotExist,");
				}
				
			}
			
//            if(null != chassisCode && !"".equals(chassisCode)){
//				
//				TbCarInfo t = tbCarInfoService.findTbCarInfoByChassisCode(chassisCode);
//		
//				if(null!=t){
//					
//					response.getWriter().print("chassisExist,");
//				}
//				else{
//					response.getWriter().print("chassisNotExist,");
//				}
//				
//			}
			
		}
		
		else{
			
			TbCarInfo tt = tbCarInfoService.findById(Long.valueOf(id));
			
			if(!tt.getLicenseCode().equals(licenseCode)){
				
				if(null != licenseCode && !"".equals(licenseCode)){
					
					TbCarInfo t = tbCarInfoService.findTbCarInfoBylicenseCode(licenseCode);
			
					if(null!=t){
						
						response.getWriter().print("licenseExist,");
					}
					else{
						response.getWriter().print("licenseNotExist,");
					}
					
				}
				
			}
			
			if(!tt.getChassisCode().equals(chassisCode)){
				
				if(null != chassisCode && !"".equals(chassisCode)){
					
					TbCarInfo t = tbCarInfoService.findTbCarInfoByChassisCode(chassisCode);
			
					if(null!=t){
						
						response.getWriter().print("chassisExist,");
					}
					else{
						response.getWriter().print("chassisNotExist,");
					}
					
				}
				
			}
			
		}
		
		return null;
	}
}	
