package com.logic.service;

import java.util.List;

import com.data.bean.BookBean;
import com.gen.exception.ServiceException;

public interface BookService {
	public BookBean saveBookInfo(BookBean bookBean) throws ServiceException;
	
	public List<Integer> saveBookInfo(List<BookBean> bookBeans) throws ServiceException;
	
	public Boolean bookIssuedForUserCheck(Integer userId) throws ServiceException;
}
