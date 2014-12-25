package com.selfsoft.secrity.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.EduElements;
import com.selfsoft.framework.common.SexElements;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.model.TmRole;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.model.TmUserRole;
import com.selfsoft.secrity.service.ITmCompanyService;
import com.selfsoft.secrity.service.ITmDepartmentService;
import com.selfsoft.secrity.service.ITmRoleService;
import com.selfsoft.secrity.service.ITmSysRegeditService;
import com.selfsoft.secrity.service.ITmUserRoleService;
import com.selfsoft.secrity.service.ITmUserService;
import com.selfsoft.secrity.service.ITmWorkTypeService;

import freemarker.template.SimpleDate;

public class TmUserAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 1205230997758090096L;
	/**
	 * 获取request,response对象
	 */
	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmUserService tmUserService;
	
	@Autowired
	private ITmUserRoleService tmUserRoleService;
	
	@Autowired
	private ITmRoleService tmRoleService;
	@Autowired
	private ITmDepartmentService tmDepartmentService;
	@Autowired
	private ITmWorkTypeService tmWorkTypeService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;

	private TmUser tmUser;
	
	private TmUserRole tmUserRole;
	
	private Long roleId;
	
	@Autowired
	private ITmSysRegeditService tmSysRegeditService;
	
	@Autowired
	private ITmCompanyService tmCompanyService;
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public TmUserRole getTmUserRole() {
		return tmUserRole;
	}

	public void setTmUserRole(TmUserRole tmUserRole) {
		this.tmUserRole = tmUserRole;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	/**
	 * 
	 * 动态查找用户
	 */
	public String findTmUser() throws Exception {

		List<TmUser> tmUserList = tmUserService.findByTmUser(tmUser);

		ActionContext.getContext().put("tmUserList", tmUserList);

		return Constants.SUCCESS;
	}

	/**
	 * 
	 * 插入用户
	 */
	public String insertTmUser() throws Exception {

		boolean flag = tmUserService.insert(tmUser);
		if (flag) {
			TmUserRole tmUserRole = new TmUserRole();
			tmUserRole.setRoleId(roleId);
			tmUserRole.setUserId(tmUser.getId());
			tmUserRoleService.insert(tmUserRole);
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}

	/**
	 * 修改用户
	 */
	public String updateTmUser() throws Exception {
		boolean flag = tmUserService.update(tmUser);
		
		tmUserRole.setUserId(tmUser.getId());
		
		if(tmUserRole.getId() != null)
			tmUserRoleService.update(tmUserRole);
		else
			tmUserRoleService.insert(tmUserRole);
		
		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}

	/**
	 * 删除用户
	 */
	public String deleteTmUser() throws Exception {

		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmUserService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmUserTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmUserTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmUserTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			tmUser = tmUserService.findById(Long.valueOf(id));
			
			Map<Long, String> sexMap = SexElements.getMap();
			Map<Long, String> eduMap = EduElements.getMap();
			request.setAttribute("depts", tmDepartmentService.findAll());
			request.setAttribute("workTypes",tmWorkTypeService.findAll());
			request.setAttribute("roles", tmRoleService.findAll());
			request.setAttribute("sexMap", sexMap);
			request.setAttribute("eduMap", eduMap);
			
			
			
			Long roleId = tmUserRoleService.getUserRoleRelationByUserId(new Long(id));
			tmUserRole = tmUserRoleService.findTmUserRoleByUserIdRoleId(roleId, new Long(id));
			List<TmRole> roleList = tmRoleService.findAll();
			request.setAttribute("roleList", roleList);

			return Constants.EDITPAGE;
		}
		
		Map<Long, String> sexMap = SexElements.getMap();
		Map<Long, String> eduMap = EduElements.getMap();
		request.setAttribute("depts", tmDepartmentService.findAll());
		request.setAttribute("workTypes",tmWorkTypeService.findAll());
		request.setAttribute("roles", tmRoleService.findAll());
		request.setAttribute("sexMap", sexMap);
		request.setAttribute("eduMap", eduMap);
		return Constants.ADDPAGE;
	}
	
	
	public String login() throws ParseException, Exception{
		//设置session永不过期
		request.getSession().setMaxInactiveInterval(-1);
		
		//判断本月单号是否清零处理
		tmDictionaryService.clearZeroStatus();
		
		ActionContext.getContext().getSession().put("companyNamePinYin","");
		
		ActionContext.getContext().getSession().put("companyAddressPinYin","");
		
		ActionContext.getContext().getSession().put("macAddress",CommonMethod.getMACAddress());
		//系统使用时，如果公司信息为空,则跳转到公司信息填写页面
		if(null==tmCompanyService.findAll()||tmCompanyService.findAll().size()==0){
			/**
			 * 这里有点尴尬，跳转到你的ACTION后自动添加个公司，ID不为NULL，其他都为空。
			 * 这样的话不填信息，上面的验证也能通过。
			 * 所以在ELSE里也增加判断
			 */
			return Constants.ADDPAGE;
		}
		else{
			
			TmCompany t = tmCompanyService.findAll().get(0);
			/**
			 * 如果公司名字与公司地址没填的话将不能成功登陆，继续跳转到增加公司信息的页面
			 */
			if(t.getCompanyName()==null||"".equals(t.getCompanyName())||null==t.getCompanyAddress()||"".equals(t.getCompanyAddress())){
				
				return Constants.ADDPAGE;
				
			}
			
		}
		
		//如果序列号为空，插入值。默认30天
		tmSysRegeditService.setDeadDate();
		//add by ccr 最后期限为 yyyy-MM-dd格式
		String deadDate = tmSysRegeditService.getDeadDate();
		
		System.out.println("最后期限为:"+deadDate);
		//如果最后期限获取错误，将跳转页面
		
		TmCompany t = tmCompanyService.findAll().get(0);
		
		String companyNamePinYin = "";
		
		String companyAddressPinYin = "";
		
		if(CommonMethod.tranferToPinYin(t.getCompanyName()).length()<16){
			
			companyNamePinYin = CommonMethod.tranferToPinYin(t.getCompanyName());
			
		}
		else{
			
			companyNamePinYin = CommonMethod.tranferToPinYin(t.getCompanyName()).substring(0,16);
		}
		
		if(CommonMethod.tranferToPinYin(t.getCompanyAddress()).length()<16){
			
			companyAddressPinYin = CommonMethod.tranferToPinYin(t.getCompanyAddress());
			
		}
		else{
			
			companyAddressPinYin = CommonMethod.tranferToPinYin(t.getCompanyAddress()).substring(0,16);
		}
		
		if(null==deadDate){
			
			ActionContext.getContext().put("msg","序列号错误! 公司拼音 : " + companyNamePinYin +"  " + "公司地址拼音 : " + companyAddressPinYin + " " + "机器码：" + CommonMethod.getMACAddress());
			
			
			ActionContext.getContext().put("updateSys","updateSys");
			
			return Constants.FAILURE;
		}
		//增加代码 --如果大于最大期限 则返回过去的错误
		Date nowDate = CommonMethod.parseStringToDate(CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd");
		
		Date deadDateTime = CommonMethod.parseStringToDate(deadDate,"yyyy-MM-dd");
		
		ActionContext.getContext().getSession().put("deadDateTime", deadDate);
		
		if(CommonMethod.compareDate(nowDate, deadDateTime)==1){
			
			System.out.println("序列号已经过期");

			ActionContext.getContext().put("msg","序列号错误! 公司拼音 : " + companyNamePinYin +"  " + "公司地址拼音 : " + companyAddressPinYin + " " + "机器码：" + CommonMethod.getMACAddress());
			
			ActionContext.getContext().put("updateSys","updateSys");
			
			return Constants.FAILURE;
		
		}
		
		//单机版公司
		/*if("南宁市得众汽车维修服务有限公司".equals(t.getCompanyName().trim())){
			
			if(!request.getLocalAddr().equals(request.getRemoteAddr())){
				ActionContext.getContext().put("msg","登陆失败!单机版不支持远程登录!");
				return Constants.FAILURE;
			}
			
		}*/
		
		
		
//		System.out.println("系统正常使用中………………");
//		
//		ActionContext.getContext().getSession().put("companyNamePinYin",companyNamePinYin);
//		
//		ActionContext.getContext().getSession().put("companyAddressPinYin",companyAddressPinYin);
//		
		ActionContext.getContext().getSession().put("companyNameMain", t.getCompanyName());
		
		List<TmUser> userList = tmUserService.findByTmUser(tmUser);
		if(userList != null && userList.size()>0){
			if(StringUtils.isBlank(tmUser.getUserName()) || StringUtils.isBlank(tmUser.getPassword()) ){
				ActionContext.getContext().put("msg","登陆失败!");
				return Constants.FAILURE;
			}
			request.getSession().setAttribute("tmUser", userList.get(0));
			ActionContext.getContext().getSession().put("tmUser", userList.get(0));
			ActionContext.getContext().getSession().put("userId", userList.get(0).getId());
			request.setAttribute("userId", tmUser.getId());
			Cookie cookie=new Cookie(request.getRemoteAddr() + "usernameCookie",URLEncoder.encode(tmUser.getUserName(),"UTF-8"));
			cookie.setMaxAge(365*24*60*60); //保存365天
			response.addCookie(cookie); 
			return Constants.SUCCESS;
		}
		request.setAttribute("msg", "用户名密码错误或账号已停用");
		
		return Constants.FAILURE;
	}
	
	public String validTmUser() throws Exception{
		
		String flag = request.getParameter("flag");
		
		String id = request.getParameter("id");
		
		TmUser tmUser = tmUserService.findById(Long.valueOf(id));
		
		if("qy".equals(flag)){
		
			tmUser.setUserStatus("正常");
			
		}
		else if("ty".equals(flag)){
			
			tmUser.setUserStatus("停用");
			
		}
		
		tmUserService.update(tmUser);
		
		response.getWriter().print("tmUserTable," + Constants.SUCCESS);
		
		return null;
	}
	
	public String resetTmUser() throws Exception{
		
		response.setCharacterEncoding("utf-8");
		
		String userName = CommonMethod.TransferToEncoding(request.getParameter("userName").trim(), "ISO-8859-1", "GBK");
		
		String oldPass = request.getParameter("oldPass") == null ? "" : request.getParameter("oldPass");
		
		String newPass = request.getParameter("newPass")== null ? "" : request.getParameter("newPass");
		
		String confirmPass = request.getParameter("confirmPass") == null ? "" : request.getParameter("confirmPass");
		
		tmUser = new TmUser();
		
		tmUser.setUserName(userName);
		
		tmUser.setPassword(oldPass);
		
		List<TmUser> userList = tmUserService.findByTmUser(tmUser);
		
		if(null == userList || userList.size() == 0){
			
			response.getWriter().print("fail,passFail");
			
		}
		
		else if(!newPass.equals(confirmPass)){
			response.getWriter().print("fail,newPassFail");
		}
		
		else {
			
			TmUser t = userList.get(0);
			
			t.setPassword(newPass);
			
			tmUserService.update(t);
			
			response.getWriter().print("success,");
		}
		
		
		
		return null;
	}
}
