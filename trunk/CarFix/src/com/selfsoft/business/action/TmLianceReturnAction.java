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
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmLianceReturnDetailService;
import com.selfsoft.business.service.ITmLianceReturnService;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmLianceReturnAction extends ActionSupport implements
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
	private ITmLianceReturnService tmLianceReturnService;
	
	@Autowired
	private ITmLianceRegisterService tmLianceRegisterService;
	
	
	@Autowired
	private ITmLianceReturnDetailService tmLianceReturnDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	
	private TmLianceReturn tmLianceReturn;
	
	private Long supplierId;
	
	private Long lianceRegId;
	
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getLianceRegId() {
		return lianceRegId;
	}

	public void setLianceRegId(Long lianceRegId) {
		this.lianceRegId = lianceRegId;
	}

	public TmLianceReturn getTmLianceReturn() {
		return tmLianceReturn;
	}

	public void setTmLianceReturn(TmLianceReturn tmLianceReturn) {
		this.tmLianceReturn = tmLianceReturn;
	}

	
	
	/**
	 * 借进归还
	 * @Date      2010-6-3
	 * @Function  
	 * @return
	 */
	public String findLinaceRegDetail(){
		List<TmLianceRegDetailVo> collctionList = null;
		if(supplierId != null || lianceRegId != null )
			collctionList = tmLianceRegisterService.getLianceRegDetailVo(Constants.CONFIRM,Constants.NOT_RETURN,supplierId, lianceRegId);
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
	public String insertLinaceReturnDetail() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmLianceReturn.setLianceReturnBill(tmDictionaryService.GenerateCode(StockTypeElements.LIANCERETURN.getElementString()));
		tmLianceReturn.setUserId(userId+"");
		tmLianceReturn.setCreateDate(new Date());
		tmLianceReturn.setIsConfirm(new Long(isConfirm));
		
		//保存借进归主表 和归还明细表
		tmLianceReturnDetailService.insertBatchLianceDetail(tmLianceReturn, partCol);
		
//		response.getWriter().println(tmLianceReturn.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成借进归还单号:" + tmLianceReturn.getLianceReturnBill());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 更新配件借出量
	 * @throws MinusException 
	 * @Date      2010-6-18
	 * @Function
	 */
	public void updateLianceReturn() throws MinusException{
		String id =request.getParameter("id");
		tmLianceReturn  = tmLianceReturnService.findById(new Long(id));
		tmLianceReturn.setIsConfirm(Constants.CONFIRM);
		tmLianceReturnService.update(tmLianceReturn);
		tmLianceReturnDetailService.updateBathLRDetal(tmLianceReturn.getId());
	}
	
	
	/**
	 * 查询借进归还列表
	 * @Date      2010-6-18
	 * @Function  
	 * @return
	 */
	public String findTmLianceReturn(){
		if(tmLianceReturn == null)
			tmLianceReturn = new TmLianceReturn();
		
		tmLianceReturn.setIsConfirm(Constants.NOT_CONFIRM);
		
		List<TmLianceReturn> lianceReturnList = tmLianceReturnService.findByEntity(tmLianceReturn);
		request.setAttribute("lianceReturnList", lianceReturnList);
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入修改借进归还页面
	 * @Date      2010-6-18
	 * @Function  
	 * @return
	 */
	public String initEditLianceReturnPage(){
		String id = request.getParameter("id");
		tmLianceReturn = tmLianceReturnService.findById(new Long(id)); 
		Set<TmLianceReturnDetail> returnDetails = tmLianceReturn.getLianceReturnDetails();
		
		request.setAttribute("returnDetails", returnDetails);
		request.setAttribute("insertAction", "updateLianceReturnDetailAction");
		request.setAttribute("initPageAction", "findTmLianceReturnAction");
		request.setAttribute("updateAction", "updateLianceReturnAction");
		
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新借进归还主表，明细表
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateLianceReturnDetail() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmLianceReturn.setIsConfirm(new Long(isConfirm));
		tmLianceReturnDetailService.updateBatchLianceDetail(tmLianceReturn, partCol);
//		response.getWriter().println(tmLianceReturn.getId()+","+isConfirm);
		return Constants.SUCCESS;
	} 
	
	
	/**
	 * 删除所有借进归还单据
	 */
	public void deleteLianceReturn() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmLianceReturnService.deleteLianceReturn(new Long(id));
			if(flag)
				response.getWriter().print("lianceReturnTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("lianceReturnTable," + Constants.FAILURE);
		}
	}
}