package com.selfsoft.secrity.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TmUser implements java.io.Serializable {
	//ID
	private Long id;
	//用户名
	private String userName;
	//密码
	private String password;
	//注册日期
	private Date createDate;
	//姓名
	private String userRealName;
	//工号
	private String jobCode;
	//性别
	private Integer sex;
	//学历
	private Integer edu;
	//技术等级
	private String tecLevel;
	//是否是修理人员
	private Integer isFixPerson;
	//电话
	private String phone;
	//手机
	private String telephone;
	//生日
	private Date birthday;
	//邮编
	private String zipCode;
	//基本工资
	private Double salary;
	//工作时间
	private Date startWorkTime;
	//入站时间
	private Date enterStationTime;
	//住址
	private String address;
	//简历
	private String cv;
	//培训信息
	private String trainInfo;
	//部门
	private TmDepartment tmDepartment;
	//工种
	private TmWorkType tmWorkType;
	//用户状态
	private String userStatus;
	
	//维修预约单
	private Set tbBooks = new HashSet();
	//委托书
	private Set tbFixEntrusts = new HashSet();
	//修理人员工时分配
	private Set tbFixShares = new HashSet();
	//预付款登记
	private Set tbAnvancePays = new HashSet();
	//结算处理
	private Set tbBusinessBalances = new HashSet();
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public TmUser() {
	}

	public TmUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getTbBooks() {
		return tbBooks;
	}

	public void setTbBooks(Set tbBooks) {
		this.tbBooks = tbBooks;
	}

	public Set getTbFixEntrusts() {
		return tbFixEntrusts;
	}

	public void setTbFixEntrusts(Set tbFixEntrusts) {
		this.tbFixEntrusts = tbFixEntrusts;
	}

	public Set getTbFixShares() {
		return tbFixShares;
	}

	public void setTbFixShares(Set tbFixShares) {
		this.tbFixShares = tbFixShares;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getEdu() {
		return edu;
	}

	public void setEdu(Integer edu) {
		this.edu = edu;
	}

	public String getTecLevel() {
		return tecLevel;
	}

	public void setTecLevel(String tecLevel) {
		this.tecLevel = tecLevel;
	}

	public Integer getIsFixPerson() {
		return isFixPerson;
	}

	public void setIsFixPerson(Integer isFixPerson) {
		this.isFixPerson = isFixPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(Date startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public Date getEnterStationTime() {
		return enterStationTime;
	}

	public void setEnterStationTime(Date enterStationTime) {
		this.enterStationTime = enterStationTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getTrainInfo() {
		return trainInfo;
	}

	public void setTrainInfo(String trainInfo) {
		this.trainInfo = trainInfo;
	}

	public TmDepartment getTmDepartment() {
		return tmDepartment;
	}

	public void setTmDepartment(TmDepartment tmDepartment) {
		this.tmDepartment = tmDepartment;
	}

	public TmWorkType getTmWorkType() {
		return tmWorkType;
	}

	public void setTmWorkType(TmWorkType tmWorkType) {
		this.tmWorkType = tmWorkType;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Set getTbAnvancePays() {
		return tbAnvancePays;
	}

	public void setTbAnvancePays(Set tbAnvancePays) {
		this.tbAnvancePays = tbAnvancePays;
	}



	public Set getTbBusinessBalances() {
		return tbBusinessBalances;
	}

	public void setTbBusinessBalances(Set tbBusinessBalances) {
		this.tbBusinessBalances = tbBusinessBalances;
	}



	private String deptName;
	
	private String workName;

	public String getDeptName() {
		return this.tmDepartment.getDeptName();
	}

	public String getWorkName() {
		return this.tmWorkType.getWorkName();
	}
	
	
	
}