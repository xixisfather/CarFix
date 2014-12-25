package com.selfsoft.business.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLianceReturnDetail;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmLoanReturnDetail;
import com.selfsoft.business.service.ITmLoanRegisterDetailService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.service.ITmLoanReturnDetailService;
import com.selfsoft.business.service.ITmLoanReturnService;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmLoanReturnAction extends ActionSupport implements
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
	private ITmLoanRegisterService tmLoanRegisterService;
	@Autowired
	private ITmLoanReturnService tmLoanReturnService;
	@Autowired
	private ITmLoanReturnDetailService tmLoanReturnDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	
	private TmLoanReturn tmLoanReturn;
	
	private Long customerId ;
	
	private Long loanRegId ;

	public TmLoanReturn getTmLoanReturn() {
		return tmLoanReturn;
	}

	public void setTmLoanReturn(TmLoanReturn tmLoanReturn) {
		this.tmLoanReturn = tmLoanReturn;
	}
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getLoanRegId() {
		return loanRegId;
	}

	public void setLoanRegId(Long loanRegId) {
		this.loanRegId = loanRegId;
	}
	
	/**
	 * 借进归还
	 * @Date      2010-6-3
	 * @Function  
	 * @return
	 */
	public String findLoanRegDetail(){
		List<TmLoanRegDetailVo> collctionList = null;
		if(customerId != null || loanRegId != null)
			collctionList = tmLoanRegisterService.getLoanRegDetailVo(Constants.CONFIRM, Constants.NOT_RETURN,customerId, loanRegId);
		request.setAttribute("collctionList", collctionList);
		return Constants.SUCCESS;
	}
	
	/**
	 * 保存借进归还单及其明细
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @Date      2010-6-3
	 * @Function
	 */
	public String insertLoanReturnDetail() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmLoanReturn.setLoanReturnBill(tmDictionaryService.GenerateCode(StockTypeElements.LOANRETURN.getElementString()));
		tmLoanReturn.setUserId(new Long(userId));
		tmLoanReturn.setCreateDate(new Date());
		tmLoanReturn.setIsConfirm(new Long(isConfirm));
		
		//保存采购借出归还单和借出归还明细
		tmLoanReturnDetailService.insertBatchloanDetail(tmLoanReturn, partCol);
//		response.getWriter().println(tmLoanReturn.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成借出归还单号:" + tmLoanReturn.getLoanReturnBill());
		return Constants.SUCCESS;
		
	}
	
	public void updateLoanReturn() throws MinusException{
		String id =request.getParameter("id");
		tmLoanReturn  = tmLoanReturnService.findById(new Long(id));
		tmLoanReturn.setIsConfirm(8002L);
		tmLoanReturnService.update(tmLoanReturn);
		tmLoanReturnDetailService.updateBathLRDetal(tmLoanReturn.getId());
	}
	
	/**
	 * 查询借出归还列表
	 * @Date      2010-6-18
	 * @Function  
	 * @return
	 */
	public String findTmLoanReturn(){
		if(tmLoanReturn == null)
			tmLoanReturn = new TmLoanReturn();
		
		tmLoanReturn.setIsConfirm(Constants.NOT_CONFIRM);
		
		List<TmLoanReturn> loanReturnList = tmLoanReturnService.findByEntity(tmLoanReturn);
		request.setAttribute("loanReturnList", loanReturnList);
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入修改借出归还页面
	 * @Date      2010-6-18
	 * @Function  
	 * @return
	 */
	public String initEditLoanReturnPage(){
		String id = request.getParameter("id");
		tmLoanReturn = tmLoanReturnService.findById(new Long(id)); 
		Set<TmLoanReturnDetail> returnDetails = tmLoanReturn.getLoanReturnDetails();
		
		request.setAttribute("returnDetails", returnDetails);
		request.setAttribute("insertAction", "updateLoanReturnDetailAction");
		request.setAttribute("initPageAction", "findTmLoanReturnAction");
		request.setAttribute("updateAction", "updateLoanReturnAction");
		
		return Constants.EDITPAGE;
	}

	/**
	 * 更新借出归还主表，明细表
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateLoanReturnDetail() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmLoanReturn.setIsConfirm(new Long(isConfirm));
		tmLoanReturnDetailService.updateBatchloanDetail(tmLoanReturn, partCol);
//		response.getWriter().println(tmLoanReturn.getId()+","+isConfirm);
		return Constants.SUCCESS;
	} 
	
	/**
	 * 删除所有借出归还单据
	 */
	public void deleteLoanReturn() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmLoanReturnService.deleteLoanReturn(new Long(id));
			if(flag)
				response.getWriter().print("loanReturnTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("loanReturnTable," + Constants.FAILURE);
		}
	}
}