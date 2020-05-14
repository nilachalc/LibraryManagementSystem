package com.repo.adapter.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gen.exception.DBException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.DataBaseConnection;
import com.gen.util.LoadProperties;
import com.repo.adapter.BookAdapter;
import com.repo.dao.BookDao;

public class BookAdapterImpl implements BookAdapter {
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public BookAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public Integer save(BookDao bookDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + BookAdapterImpl.class);
		Integer newBookId;
		String sqlInsertQuery = null;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			newBookId = null;
			Integer insertionCompletionStatus = null;
			String sqlQuery = properties.getPropertyForValue("getBookSequence");
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.executeQuery();
			ResultSet bookSequenceResultSet = preparedStatement.getResultSet();
			if (bookSequenceResultSet.next()) {
				newBookId = bookSequenceResultSet.getInt(ApplicationConstants.VALUE_ONE);
			}
			sqlInsertQuery = properties.getPropertyForValue("bookInsert");
			preparedStatement = con.prepareStatement(sqlInsertQuery);
			
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, newBookId);
			if (bookDao != null) {
				preparedStatement.setString(ApplicationConstants.VALUE_TWO, bookDao.getBookName());
				preparedStatement.setString(ApplicationConstants.VALUE_THREE, bookDao.getAuthorName());
				preparedStatement.setTimestamp(ApplicationConstants.VALUE_FOUR, bookDao.getAvailabilityDate());
				preparedStatement.setString(ApplicationConstants.VALUE_FIVE, bookDao.getReadytoreIssue().toString());
				preparedStatement.setInt(ApplicationConstants.VALUE_SIX, bookDao.getGenreId());
			}
			
			insertionCompletionStatus = preparedStatement.executeUpdate();
			if (insertionCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("Book Creation Failed." + properties.getPropertyForValue("adapterExit") + BookAdapterImpl.class);
				throw new DBException();
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("Book Successfully Created. " + properties.getPropertyForValue("adapterExit") + BookAdapterImpl.class);
		return newBookId;
	}
	
	@Override
	public List<BookDao> getAllBooksByUserId(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + BookAdapterImpl.class);
		List<BookDao> bookDaos = new ArrayList<BookDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			BookDao bookDao;
			
			String sqlQuery = properties.getPropertyForValue("bookSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, userId);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bookDao = new BookDao();
				
				bookDao.setBookId(rs.getInt(ApplicationConstants.VALUE_ONE));
				bookDao.setBookName(rs.getString(ApplicationConstants.VALUE_TWO));
				bookDao.setAuthorName(rs.getString(ApplicationConstants.VALUE_THREE));
				bookDao.setCurrentUserIssued(rs.getInt(ApplicationConstants.VALUE_FOUR));
				bookDao.setIssueDate(rs.getTimestamp(ApplicationConstants.VALUE_FIVE));
				bookDao.setSubmissionDate(rs.getTimestamp(ApplicationConstants.VALUE_SIX));
				bookDao.setAvailabilityDate(rs.getTimestamp(ApplicationConstants.VALUE_SEVEN));
				bookDao.setReadytoreIssue(rs.getString(ApplicationConstants.VALUE_EIGHT).charAt(ApplicationConstants.VALUE_ZERO));
				bookDao.setGenreId(rs.getInt(ApplicationConstants.VALUE_NINE));
				
				bookDaos.add(bookDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		
		logger.info("Book Data Populated. " + properties.getPropertyForValue("adapterExit") + BookAdapterImpl.class);
		return bookDaos;
	}

	@Override
	public Boolean getBookLookUpByUserId(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + BookAdapterImpl.class);
		Boolean IsBookIssuedForUser = ApplicationConstants.VALUE_FALSE;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			
			String sqlQuery = properties.getPropertyForValue("bookForAUserLookup");
			preparedStatement = con.prepareStatement(sqlQuery); 
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, userId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				logger.info("Atleast one Book is ussed to this users." + AdminAdapterImpl.class);
				IsBookIssuedForUser = ApplicationConstants.VALUE_TRUE;
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		
		logger.info("User does not exits. " + properties.getPropertyForValue("adapterExit") + BookAdapterImpl.class);
		return IsBookIssuedForUser;
	}
}
