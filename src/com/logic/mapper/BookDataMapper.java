package com.logic.mapper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.data.bean.BookBean;
import com.gen.util.ApplicationConstants;
import com.repo.dao.BookDao;

public class BookDataMapper {
	private BookDao bookDao;
	private Date today;
	
	public BookDao mapBeanDataToDao(BookBean bookBean, Boolean updateFlag) {
		bookDao = new BookDao();
		
		if (updateFlag) {
			bookDao.setBookId(bookBean.getBookId());
		}
		
		bookDao.setBookName(bookBean.getBookName());
		bookDao.setAuthorName(bookBean.getAuthorName());
		if (bookBean.getIssueDate() != null) {
			bookDao.setIssueDate(new Timestamp(bookBean.getIssueDate().getTime()));
		}
		
		if (bookBean.getSubmissionDate() != null) {
			bookDao.setSubmissionDate(new Timestamp(bookBean.getSubmissionDate().getTime()));
		}
		
		bookDao.setAvailabilityDate(new Timestamp(bookBean.getAvailabilityDate().getTime()));
		today = new Date();
		if (today.after(bookBean.getAvailabilityDate())) {
			bookDao.setReadytoreIssue(ApplicationConstants.VALUE_YES);
		} else {
			bookDao.setReadytoreIssue(ApplicationConstants.VALUE_NO);
		}
		
		if (bookBean.getCurrentUserIssued() != null) {
			bookDao.setCurrentUserIssued(bookBean.getCurrentUserIssued());
		}
		
		bookDao.setGenreId(bookBean.getGenreId());
		
		return bookDao;
	}
}
