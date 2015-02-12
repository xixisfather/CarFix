package com.selfsoft.business.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.model.TmMemberShipService;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseinformation.service.ITmMemberShipServiceService;
import com.selfsoft.business.model.TbReturnVisit;
import com.selfsoft.business.model.TbSmartBalance;
import com.selfsoft.business.service.ITbReturnVisitService;
import com.selfsoft.business.service.ITbSmartBalanceService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;

public class TbSmartBalanceAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbSmartBalanceService tbSmartBalanceService;
	
	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	
	@Autowired
	private ITmMemberShipServiceService tmMemberShipServiceService;
	
	private TbSmartBalance tbSmartBalance;

	public ITbSmartBalanceService getTbSmartBalanceService() {
		return tbSmartBalanceService;
	}

	public void setTbSmartBalanceService(
			ITbSmartBalanceService tbSmartBalanceService) {
		this.tbSmartBalanceService = tbSmartBalanceService;
	}

	public TbSmartBalance getTbSmartBalance() {
		return tbSmartBalance;
	}

	public void setTbSmartBalance(TbSmartBalance tbSmartBalance) {
		this.tbSmartBalance = tbSmartBalance;
	}
	

	public String getMemberShipCardInfo() throws Exception{
		
		String cardPassword = request.getParameter("cardPassword");
		
		String cardNo = request.getParameter("cardNo");
		
		TbMembershipCard tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
		
		if(null != tbMembershipCard) {
			
			if(tbMembershipCard.getCardPassword().equals(CommonMethod.encryptBASE64(cardPassword).toString())) {
				
				if(!Constants.CARD_VALID_STATUS.equals(tbMembershipCard.getCardStatus())) {
					
					request.setAttribute("errorMsg", "会员卡非正常状态");
					
					return Constants.FAILURE;
				}
				
				request.setAttribute("tbMembershipCard", tbMembershipCard);
				
				List<TmMemberShipService> tmMemberShipServiceList = tmMemberShipServiceService.findByMemberShipId(tbMembershipCard.getId());
				
				request.setAttribute("tmMemberShipServiceList", tmMemberShipServiceList);
				
				
				return Constants.SUCCESS;
			}
			
			else {
				
				request.setAttribute("errorMsg", "密码错误");
				
				return Constants.FAILURE;
			}
			
		}
		
		else {
			
			request.setAttribute("errorMsg", "会员卡号错误");
			
			return Constants.FAILURE;
		}
		
		
		
	}
	
	public String tbSmartBalanceCreate() throws Exception {
		
		
		try{
			
			tbSmartBalance = new TbSmartBalance();
			
			String payPatten = request.getParameter("tbSmartBalance.payPatten"); 
			
			tbSmartBalance.setPayPatten(Long.valueOf(payPatten));
			
			tbSmartBalance.setBalanceDate(new Date());
			
			tbSmartBalance.setUserId(((TmUser)request.getSession().getAttribute("tmUser")).getId());
			
			String cardNo = request.getParameter("cardNo"); 
			
			String licenseCode = request.getParameter("licenseCode");
			
			tbSmartBalance.setLicenseCode(licenseCode);
			
			String workerName = request.getParameter("workerName");
			
			tbSmartBalance.setWorkerName(workerName);
			
			if(Constants.PAYMEMBERCARD.equals(Long.valueOf(payPatten))) {
				
				String cardSaving = request.getParameter("cardSaving");
				
				if(null == cardSaving || "".equals(cardSaving)) {
					
					request.setAttribute("errorMsg", "请输入正确的卡号密码!");
					
					return Constants.FAILURE;
				}
				
				else {
					
					tbSmartBalance.setCardNo(cardNo); 
					
					TbMembershipCard tbMembershipCard = tbMembershipCardService.findByCardNo(cardNo);
					
					String serviceName = "";
					
					for(int i = 0 ; i < 20 ; i++) {
						
						serviceName = request.getParameter("serviceName" +i);
						
						if(null != serviceName && !"".equals(serviceName)) {
							
							tbSmartBalance.setServiceName(serviceName.split("_")[0]);
							
							tbMembershipCard.setServiceName(serviceName.split("_")[0]);
							
							if(Integer.valueOf(serviceName.split("_")[1]) <= 0) {
								
								request.setAttribute("errorMsg", "无赠送服务");
								
								return Constants.FAILURE;
							}
							
							tbMembershipCard.setJexf(0d);
							
							tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
							
							tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
							
							tbMembershipCard.setCardSaving(Double.valueOf(cardSaving));
							
							tbSmartBalance.setCardSaving(Double.valueOf(cardSaving));
							
							tbSmartBalance.setServiceMoney(0d);
							
							tbSmartBalance.setUseCardService(1L);
							
							break;
						}
						
					}
					
					if(null == serviceName || "".equals(serviceName)) {
						
						serviceName = request.getParameter("serviceName");
						
						tbSmartBalance.setServiceName(serviceName);
						
						String serviceMoney = request.getParameter("serviceMoney");
						
						tbSmartBalance.setServiceMoney(Double.valueOf(serviceMoney));
						
						if(Double.valueOf(serviceMoney) > Double.valueOf(cardSaving)) {
							
							request.setAttribute("errorMsg", "卡内余额不够!");
							
							return Constants.FAILURE;
							
						}
						
						else {
							
							BigDecimal serviceMoney_d = new BigDecimal(serviceMoney);
							
							BigDecimal cardSaving_d = new BigDecimal(cardSaving); 
							
							tbSmartBalance.setCardSaving(cardSaving_d.subtract(serviceMoney_d).doubleValue());
						
							tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
						
							tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
							
							tbMembershipCard.setCardSaving(tbSmartBalance.getCardSaving());
							
							tbMembershipCard.setJexf(serviceMoney_d.doubleValue());
						}
						
						tbMembershipCard.setServiceName(serviceName);
						
						
						tbSmartBalance.setUseCardService(0L);
					}
					
					
					
					tbSmartBalance.setTbMembershipCard(tbMembershipCard);
					
					
				}
				
				
			} else {
				
				tbSmartBalance.setServiceName(request.getParameter("serviceName"));
				
				tbSmartBalance.setServiceMoney(Double.valueOf(request.getParameter("serviceMoney")));
			}
			
			tbSmartBalanceService.insertTbSmartBalance(tbSmartBalance,(TmUser)request.getSession().getAttribute("tmUser"));
			
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "结算错误!");
			
			return Constants.FAILURE;
		}
		
		return Constants.SUCCESS;
	}
	
	public String tbSmartBalanceFind() throws Exception {
		
		Map<Long,String> payPattenMap = Constants.getPayMap();

		request.setAttribute("payPattenMap", payPattenMap);
		
		List<TbSmartBalance> tbSmartBalanceList = tbSmartBalanceService.findByTbSmartBalance(tbSmartBalance);
		
		request.setAttribute("tbSmartBalanceList", tbSmartBalanceList);
		
		return Constants.SUCCESS;
	}
	
	public String tbSmartBalancePrint() throws Exception {
		
		String id = request.getParameter("id");
		
		tbSmartBalance = tbSmartBalanceService.findTbsmartBalancePrint(Long.valueOf(id));

		request.setAttribute("tbSmartBalance", tbSmartBalance);
		
		return Constants.SUCCESS;
	}
	
	
}
