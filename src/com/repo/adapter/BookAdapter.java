package com.repo.adapter;

import java.util.List;

import com.gen.exception.DBException;
import com.repo.dao.BookDao;

public interface BookAdapter {
	public Integer save(BookDao bookDao) throws DBException;
	
	public List<BookDao> getAllBooksByUserId(Integer userId) throws DBException;
	
	public Boolean getBookLookUpByUserId(Integer userId) throws DBException;
}
