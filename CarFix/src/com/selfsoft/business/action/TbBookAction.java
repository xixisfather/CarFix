package com.selfsoft.business.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.business.service.ITbBookFixPartService;
import com.selfsoft.business.service.ITbBookFixStationService;
import com.selfsoft.business.service.ITbBookService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.service.ITmUserService;

public class TbBookAction extends ActionSupport implements
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
	private ITbBookService tbBookService;
	
	private TbBook tbBook;
	
	@Autowired
	private ITmFixTypeService tmFixTypeService;

	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;
	@Autowired
	private ITbBookFixStationService tbBookFixStationService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITbBookFixPartService tbBookFixPartService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	public TbBook getTbBook() {
		return tbBook;
	}

	public void setTbBook(TbBook tbBook) {
		this.tbBook = tbBook;
	}
	
	public String findTbBook() throws Exception {
		
		List<TbBook> tbBookList = tbBookService.findByTbBook(tbBook);

		ActionContext.getContext().put("tbBookList", tbBookList);
		
		ActionContext.getContext().put("isNoMap",Constants.getIsNoMap());
		
		
		//服务顾问
		ActionContext.getContext().put("tmUserMap",tmUserService.findAllTmUserMap());
		
		return Constants.SUCCESS;
	}

	public String queryTbBook() throws Exception{
		
		if(null!=tbBook){
			String licenseCode = tbBook.getLicenseCode();
			
			if(null!=licenseCode&&!"".equals(licenseCode)){
				
				List<TbBook> tbBookList = tbBookService.findCurrentDayTbBook(licenseCode);

				ActionContext.getContext().put("tbBookList", tbBookList);
			}
		}
		
		return Constants.SUCCESS;
	}
	
	public String insertTbBook() throws Exception{
		
		if(null==tbBook.getTmFixType()||null==tbBook.getTmFixType().getId()){
			tbBook.setTmFixType(null);
		}
		if(null==tbBook.getTmCarModelType()||null==tbBook.getTmCarModelType().getId()){
			tbBook.setTmCarModelType(null);
		}
		if(null==tbBook.getTmUser()||null==tbBook.getTmUser().getId()){
			tbBook.setTmUser(null);
		}
		
		String bookCode = tmDictionaryService.GenerateCode(StockTypeElements.TBBOOK.getElementString());
		
		tbBook.setBookCode(bookCode);
		
		tbBookService.insertAll(tbBook);
		
		ActionContext.getContext().put("msg","生成预约单号:"+bookCode);

		return Constants.SUCCESS;
	}

	public String updateTbBook() throws Exception{
		
		if(null==tbBook.getTmFixType()||null==tbBook.getTmFixType().getId()){
			tbBook.setTmFixType(null);
		}
		if(null==tbBook.getTmCarModelType()||null==tbBook.getTmCarModelType().getId()){
			tbBook.setTmCarModelType(null);
		}
		if(null==tbBook.getTmUser()||null==tbBook.getTmUser().getId()){
			tbBook.setTmUser(null);
		}
		
		tbBookService.updateAll(tbBook);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTbBook() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tbBookService.deleteAll(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tbBookTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tbBookTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tbBookTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {

		String id = request.getParameter("id");
		//修理类型
		ActionContext.getContext().put("tmFixTypeMap",tmFixTypeService.findAllTmFixTypeMap());
		//车型
		ActionContext.getContext().put("tmCarModelTypeMap",tmCarModelTypeService.findAllTmCarModelTypeMap());
		//服务顾问
		ActionContext.getContext().put("tmUserMap",tmUserService.findAllTmUserMap());
		//免费标识
		ActionContext.getContext().put("freeSymbolMap", Constants.getFreeSymbolMap());
		//仓库LIST
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);	
		//处理方式
		ActionContext.getContext().put("dealTypeMap", Constants.getDealTypeMap());
		//选中工位工时
		Map<String,String> tbBookFixStationMap = new LinkedHashMap<String, String>();
		//选中修理配件
		Map<String,String> tbBookFixPartMap = new LinkedHashMap<String, String>();
		
		if (null != id && !"".equals(id)) {

			tbBookFixStationMap = tbBookFixStationService.findTbBookFixStationMapByTbBookId(Long.valueOf(id));
			
			tbBookFixPartMap = tbBookFixPartService.findTbBookFixPartMapByTbBookId(Long.valueOf(id));
			
			tbBook = tbBookService.findById(Long.valueOf(id));

			ActionContext.getContext().put("tbBookFixStationMap", tbBookFixStationMap);
			
			ActionContext.getContext().put("tbBookFixPartMap", tbBookFixPartMap);
			
			return Constants.EDITPAGE;
		}
		
		ActionContext.getContext().put("tbBookFixStationMap", tbBookFixStationMap);
		
		ActionContext.getContext().put("tbBookFixPartMap", tbBookFixPartMap);
		
		return Constants.ADDPAGE;
	}
	/**
	 * 是否履约
	 * @return
	 * @throws Exception
	 */
	public String keepAppoint() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tbBook = tbBookService.findById(Long.valueOf(id));
			
			tbBook.setIsCome(Constants.ISTRUE);
			
			try{
				tbBookService.update(tbBook);
				response.getWriter().print("tbBookTable," + Constants.SUCCESS);
			}catch(Exception e){
				e.printStackTrace();
				response.getWriter().print("tbBookTable," + Constants.FAILURE);
			}
			
		} else {
			response.getWriter().print("tbBookTable," + Constants.EXCEPTION);
		}
		return null;
	}
}
