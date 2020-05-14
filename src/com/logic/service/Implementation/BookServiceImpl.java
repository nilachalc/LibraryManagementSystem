package com.logic.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.data.bean.BookBean;
import com.gen.exception.DBException;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.LoadProperties;
import com.logic.mapper.BookDataMapper;
import com.logic.service.BookService;
import com.repo.adapter.BookAdapter;
import com.repo.adapter.implementation.BookAdapterImpl;
import com.repo.dao.BookDao;

public class BookServiceImpl implements  BookService {
	private Logger logger = null;
	private LoadProperties properties;
	
	private BookDataMapper bookDataMapper;
	private BookAdapter bookAdapter;
	
	public BookServiceImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public BookBean saveBookInfo(BookBean bookBean) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + BookServiceImpl.class);
		bookDataMapper = new BookDataMapper();
		bookAdapter = new BookAdapterImpl();
		BookDao bookDao;
		
		try {
			bookDao = bookDataMapper.mapBeanDataToDao(bookBean, ApplicationConstants.VALUE_FALSE);
			bookBean.setBookId(bookAdapter.save(bookDao));
			
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + BookServiceImpl.class);
		return bookBean;
	}
	
	@Override
	public List<Integer> saveBookInfo(List<BookBean> bookBeans) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + BookServiceImpl.class);
		bookDataMapper = new BookDataMapper();
		bookAdapter = new BookAdapterImpl();
		List<Integer> userIds = new ArrayList<Integer>();
		BookDao bookDao;
		
		try {
			for (BookBean bookBean : bookBeans) {
				bookDao = bookDataMapper.mapBeanDataToDao(bookBean, ApplicationConstants.VALUE_FALSE);
				userIds.add(bookAdapter.save(bookDao));
			}
						
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + BookServiceImpl.class);
		return userIds;
	}
	
	@Override
	public Boolean bookIssuedForUserCheck(Integer userId) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + BookServiceImpl.class);
		Boolean IsBookIssuedForUser = ApplicationConstants.VALUE_FALSE;
		bookAdapter = new BookAdapterImpl();
		
		try {
			IsBookIssuedForUser = bookAdapter.getBookLookUpByUserId(userId);
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + BookServiceImpl.class);
		return IsBookIssuedForUser;
	}
}
