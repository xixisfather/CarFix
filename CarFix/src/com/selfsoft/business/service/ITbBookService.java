package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbBook;

public interface ITbBookService {
	
	public void insert(TbBook tbBook);
	
	public void update(TbBook tbBook);
	
	public boolean deleteById(Long id);
	
	public TbBook findById(Long id);
	
	public List<TbBook> findByTbBook(TbBook tbBook);
	
	public void insertAll(TbBook tbBook);
	
	public void updateAll(TbBook tbBook);
	
	public boolean deleteAll(Long id);
	
	public List<TbBook> findCurrentDayTbBook(String licenseCode);
	
	public void updateTbBookCustomer();
}
