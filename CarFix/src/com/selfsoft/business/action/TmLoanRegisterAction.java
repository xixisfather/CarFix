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
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.service.ITbBookService;
import com.selfsoft.business.service.ITmLoanRegisterDetailService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TmLoanRegisterAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = -8222571829690983781L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmLoanRegisterService tmLoanRegisterService;
	@Autowired
	private ITmLoanRegisterDetailService tmLoanRegisterDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	
	private TmLoanRegister tmLoanRegister;

	public TmLoanRegister getTmLoanRegister() {
		return tmLoanRegister;
	}

	public void setTmLoanRegister(TmLoanRegister tmLoanRegister) {
		this.tmLoanRegister = tmLoanRegister;
	}
	
	/**
	 * 进入借出登记页面
	 * @Date      2010-6-2
	 * @Function  
	 * @return
	 */
	public String initLoanRegisterPage(){
		
		
		request.setAttribute("insertAction", "insertLoanRegisterAction");
		request.setAttribute("initPageAction", "initLoanRegisterPageAction");
		request.setAttribute("updateAction", "updatePartInfoLoanAction");
		request.setAttribute("gtitle", "配件借出登记");
		request.setAttribute("loanRegTypeName", "借出");
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 新增借出登记单
	 * @throws IOException 
	 * @Date      2010-6-2
	 * @Function
	 */
	public String insertLoanRegister() throws IOException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		
		tmLoanRegister.setLoanBill(tmDictionaryService.GenerateCode(StockTypeElements.LOANREGISTER.getElementString()));
		tmLoanRegister.setCreateDate(new Date());
		tmLoanRegister.setIsConfirm(new Long(isConfirm));
		tmLoanRegister.setIsReturn(0L);
		tmLoanRegister.setUserId(new Long(userId));
		
		
		//保存借出主表和借出详细内容
		tmLoanRegisterDetailService.insertBatchLoanDetail(tmLoanRegister, partCol);
		
		//response.getWriter().println(tmLoanRegister.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成借出登记单号:" + tmLoanRegister.getLoanBill());
		return Constants.SUCCESS;
	}
	
	/**
	 * 更新借出登记单里借进数
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updatePartInfoLoan(){
		String id =request.getParameter("id");
		tmLoanRegister = tmLoanRegisterService.findById(new Long(id));
		tmLoanRegister.setIsConfirm(8002L);
		//更新借进登记单确认状态
		tmLoanRegisterService.update(tmLoanRegister);
		//更新借进登记单下所有配件借进量
		tmLoanRegisterDetailService.updateBatchPartInfoLoan(new Long(id));
	}
	
	/**
	 * 查询借出登记单
	 * @return
	 */
	public String findLoanRegister(){
		List<TmLoanRegVo> registerVos = tmLoanRegisterService.getloanRegVo(Constants.NOT_CONFIRM, Constants.NOT_RETURN, tmLoanRegister,null);
		request.setAttribute("registerVos", registerVos);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入修改借出登记页面
	 * @return
	 */
	public String initEditLoanRegisterPage(){
		String id = request.getParameter("id");
		tmLoanRegister = tmLoanRegisterService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmLoanRegister.getCustomerId());
		List<TmLoanRegDetailVo> registerDetailVos = tmLoanRegisterService.getLoanRegDetailVo(Constants.NOT_CONFIRM,Constants.NOT_RETURN, null,tmLoanRegister.getId());
		request.setAttribute("registerDetailVos", registerDetailVos);
		request.setAttribute("customer", customer);
		
		request.setAttribute("insertAction", "updateLoanRegisterAction");
		request.setAttribute("initPageAction", "findLoanRegisterAction");
		request.setAttribute("updateAction", "updatePartInfoLoanAction");
		return Constants.EDITPAGE;
	}
	
	
	/**
	 * 更新借出登记
	 * @return
	 * @throws IOException 
	 */
	public String updateLoanRegister() throws IOException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmLoanRegister.setIsConfirm(new Long(isConfirm));
		tmLoanRegisterDetailService.updateBatchLoanDetail(tmLoanRegister, partCol);
//		response.getWriter().println(tmLoanRegister.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	/**
	 * 删除所有借出登记单据
	 */
	public void deleteLoanRegister() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmLoanRegisterService.deleteLoanRegister(new Long(id));
			if(flag)
				response.getWriter().print("TmLoanRegisterTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("TmLoanRegisterTable," + Constants.FAILURE);
		}
	}
}
