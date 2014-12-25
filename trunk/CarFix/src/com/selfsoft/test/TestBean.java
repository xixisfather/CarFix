package com.selfsoft.test;

import java.util.Date;

public class TestBean {
	
	private Integer id;
	private String name;
	private Long count;
	private Float price;
	private Double total;
	private Date reportDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	public String toString(){
		return String.format("TestBean@%1$s[id=%2$s; name=%3$s; count=%4$s; price=%5$s; total=%6$s reportDate=%7$s]", 
				hashCode(), id,name,count, price, total, reportDate);
	}
}
