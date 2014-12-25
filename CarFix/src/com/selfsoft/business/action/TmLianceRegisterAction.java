package com.selfsoft.business.action;

import java.io.IOException;
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
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.service.ITmLianceRegisterDetailService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TmLianceRegisterAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 506216537328796527L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmLianceRegisterService tmLianceRegisterService;
	@Autowired
	private ITmLianceRegisterDetailService tmLianceRegisterDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITmUserService tmUserService;
	
	
	private TmLianceRegister tmLianceRegister;
	
	public TmLianceRegister getTmLianceRegister() {
		return tmLianceRegister;
	}

	public void setTmLianceRegister(TmLianceRegister tmLianceRegister) {
		this.tmLianceRegister = tmLianceRegister;
	}

	/**
	 * 进入借进登记页面
	 * @Date      2010-6-2
	 * @Function  
	 * @return
	 */
	public String initLianceRegisterPage(){
		
		
		request.setAttribute("insertAction", "insertLianceRegisterAction");
		request.setAttribute("initPageAction", "initLianceRegisterPageAction");
		request.setAttribute("updateAction", "updatePartInfoLianceAction");
		request.setAttribute("gtitle", "配件借进登记");
		request.setAttribute("lianceRegTypeName", "进货");
		return Constants.SUCCESS;
	}
	
	/**
	 * 新增借进登记单
	 * @throws IOException 
	 * @Date      2010-6-2
	 * @Function
	 */
	public String insertLianceRegister() throws IOException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmLianceRegister.setLianceBill(tmDictionaryService.GenerateCode(StockTypeElements.LIANCEREGISTER.getElementString()));
		tmLianceRegister.setCreateDate(new Date());
		tmLianceRegister.setIsConfirm(new Long(isConfirm));
		tmLianceRegister.setIsReturn(0L);
		tmLianceRegister.setUserId(new Long(userId));
		//保存借进登记
		tmLianceRegisterService.insert(tmLianceRegister);
		//保存借进详细内容
		tmLianceRegisterDetailService.insertBatchLianceDetail(new Long(isConfirm), tmLianceRegister.getId(), partCol);
		
//		response.getWriter().println(tmLianceRegister.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成借进登记单号:" + tmLianceRegister.getLianceBill());
		return Constants.SUCCESS;
	}
	
	/**
	 * 更新借进登记单里借进数
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updatePartInfoLiance(){
		String id =request.getParameter("id");
		tmLianceRegister = tmLianceRegisterService.findById(new Long(id));
		tmLianceRegister.setIsConfirm(8002L);
		//更新借进登记单确认状态
		tmLianceRegisterService.update(tmLianceRegister);
		//更新借进登记单下所有配件借进量
		tmLianceRegisterDetailService.updateBatchPartInfoLiance(new Long(id));
	}
	
	/**
	 * 查询借进登记单
	 * @return
	 */
	public String findLianceRegister(){
		List<TmLianceRegVo> registerVos = tmLianceRegisterService.getLianceRegVo(Constants.NOT_CONFIRM, Constants.NOT_RETURN, tmLianceRegister,null);
		request.setAttribute("registerVos", registerVos);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入修改借进登记页面
	 * @return
	 */
	public String initEditLianceRegisterPage(){
		String id = request.getParameter("id");
		tmLianceRegister = tmLianceRegisterService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmLianceRegister.getSupplierId());
		TmUser tmUser = tmUserService.findById(tmLianceRegister.getDutyPeople());
		List<TmLianceRegDetailVo> registerDetailVos = tmLianceRegisterService.getLianceRegDetailVo(Constants.NOT_CONFIRM, Constants.NOT_RETURN,null,tmLianceRegister.getId());
		request.setAttribute("registerDetailVos", registerDetailVos);
		request.setAttribute("customer", customer);
		request.setAttribute("tmUser", tmUser);
		
		request.setAttribute("insertAction", "updateLianceRegisterAction");
		request.setAttribute("initPageAction", "findLianceRegisterAction");
		request.setAttribute("updateAction", "updatePartInfoLianceAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新借进登记
	 * @return
	 * @throws IOException 
	 */
	public String updateLianceRegister() throws IOException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmLianceRegister.setIsConfirm(new Long(isConfirm));
		tmLianceRegisterDetailService.updateBatchLianceDetail(tmLianceRegister, partCol);
//		response.getWriter().println(tmLianceRegister.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 删除所有借进登记单据
	 */
	public void deleteLianceRegister() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmLianceRegisterService.deleteLianceRegister(new Long(id));
			if(flag)
				response.getWriter().print("tmLianceRegisterTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("tmLianceRegisterTable," + Constants.FAILURE);
		}
	}
}