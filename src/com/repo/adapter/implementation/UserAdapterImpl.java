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
import com.repo.adapter.UserAdapter;
import com.repo.dao.UserDao;

public class UserAdapterImpl implements UserAdapter{
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public UserAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}
	
	@Override
	public Integer save(UserDao userDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		Integer insertionCompletionStatus;
		Integer newUserId;
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			newUserId = null;
			insertionCompletionStatus = null;
			String sqlQuery = properties.getPropertyForValue("getUserSequence");
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.executeQuery();
			ResultSet userSequenceResultSet = preparedStatement.getResultSet();
			if (userSequenceResultSet.next()) {
				newUserId = userSequenceResultSet.getInt(ApplicationConstants.VALUE_ONE);
			}
			
			String sqlInsertQuery = properties.getPropertyForValue("userInsert");
			preparedStatement = con.prepareStatement(sqlInsertQuery);
			
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, newUserId);
			if (userDao != null) {
				preparedStatement.setString(ApplicationConstants.VALUE_TWO, userDao.getFirstName());
				preparedStatement.setString(ApplicationConstants.VALUE_THREE, userDao.getLastName());
				preparedStatement.setString(ApplicationConstants.VALUE_FOUR, userDao.getAddress());
				preparedStatement.setString(ApplicationConstants.VALUE_FIVE, userDao.getMobile());
				preparedStatement.setString(ApplicationConstants.VALUE_SIX, userDao.getEmail());
				preparedStatement.setInt(ApplicationConstants.VALUE_SEVEN, userDao.getAge());
				preparedStatement.setString(ApplicationConstants.VALUE_EIGHT, userDao.getGender().toString());
			}
			
			insertionCompletionStatus = preparedStatement.executeUpdate();
			if (insertionCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Creation Failed." + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
				throw new DBException();
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User Successfully Created. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return newUserId;
	}

	@Override
	public Integer update(UserDao userDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		Integer updationCompletionStatus;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			updationCompletionStatus = null;
			String sqlUpdateQuery = properties.getPropertyForValue("userUpdate");
			preparedStatement = con.prepareStatement(sqlUpdateQuery);
			
			if (userDao != null) {
				preparedStatement.setString(ApplicationConstants.VALUE_ONE, userDao.getFirstName());
				preparedStatement.setString(ApplicationConstants.VALUE_TWO, userDao.getLastName());
				preparedStatement.setString(ApplicationConstants.VALUE_THREE, userDao.getAddress());
				preparedStatement.setString(ApplicationConstants.VALUE_FOUR, userDao.getMobile());
				preparedStatement.setString(ApplicationConstants.VALUE_FIVE, userDao.getEmail());
				preparedStatement.setInt(ApplicationConstants.VALUE_SIX, userDao.getAge());
				preparedStatement.setString(ApplicationConstants.VALUE_SEVEN, userDao.getGender().toString());
				preparedStatement.setInt(ApplicationConstants.VALUE_EIGHT, userDao.getUserId());
			}
			
			updationCompletionStatus = preparedStatement.executeUpdate();
			
			if ( updationCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Updation Failed." + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
				throw new DBException();
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User Successfully Updated. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return updationCompletionStatus;
	}
	
	@Override
	public Integer delete(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		Integer deletionCompletionStatus;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			deletionCompletionStatus = null;
			String sqlDeleteQuery = properties.getPropertyForValue("userDelete");
			preparedStatement = con.prepareStatement(sqlDeleteQuery);
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, userId);
			
			deletionCompletionStatus = preparedStatement.executeUpdate();
			
			if (deletionCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Deletion Failed." + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
				throw new DBException();
			}
			
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		
		logger.info("User Successfully Deleted. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return deletionCompletionStatus;
	}

	@Override
	public List<UserDao> getAllUsers() throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		List<UserDao> userDaos = new ArrayList<UserDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			UserDao userDao;
			
			String sqlQuery = properties.getPropertyForValue("userSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				userDao = new UserDao();
				
				userDao.setUserId(rs.getInt(ApplicationConstants.VALUE_ONE));
				userDao.setFirstName(rs.getString(ApplicationConstants.VALUE_TWO));
				userDao.setLastName(rs.getString(ApplicationConstants.VALUE_THREE));
				userDao.setAddress(rs.getString(ApplicationConstants.VALUE_FOUR));
				userDao.setMobile(rs.getString(ApplicationConstants.VALUE_FIVE));
				userDao.setEmail(rs.getString(ApplicationConstants.VALUE_SIX));
				userDao.setAge(rs.getInt(ApplicationConstants.VALUE_SEVEN));
				userDao.setGender(rs.getString(ApplicationConstants.VALUE_EIGHT).charAt(ApplicationConstants.VALUE_ZERO));
				
				userDaos.add(userDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User data fatched. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return userDaos;
	}
	
	@Override
	public UserDao getUserById(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		UserDao userDao = null;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			String sqlQuery = properties.getPropertyForValue("userLookupSelect");
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, userId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				userDao = new UserDao();
				
				userDao.setUserId(rs.getInt(ApplicationConstants.VALUE_ONE));
				userDao.setFirstName(rs.getString(ApplicationConstants.VALUE_TWO));
				userDao.setLastName(rs.getString(ApplicationConstants.VALUE_THREE));
				userDao.setAddress(rs.getString(ApplicationConstants.VALUE_FOUR));
				userDao.setMobile(rs.getString(ApplicationConstants.VALUE_FIVE));
				userDao.setEmail(rs.getString(ApplicationConstants.VALUE_SIX));
				userDao.setAge(rs.getInt(ApplicationConstants.VALUE_SEVEN));
				userDao.setGender(rs.getString(ApplicationConstants.VALUE_EIGHT).charAt(ApplicationConstants.VALUE_ZERO));
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User dropdown data fatched. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return userDao;
	}
	
	@Override
	public List<UserDao> getAllUserNamesWithIds() throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		List<UserDao> userDaos = new ArrayList<UserDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			UserDao userDao;
			
			String sqlQuery = properties.getPropertyForValue("userDropDownSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				userDao = new UserDao();
				
				userDao.setUserId(rs.getInt(ApplicationConstants.VALUE_ONE));
				userDao.setFirstName(rs.getString(ApplicationConstants.VALUE_TWO));
				userDao.setLastName(rs.getString(ApplicationConstants.VALUE_THREE));
				
				userDaos.add(userDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User dropdown data fatched. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return userDaos;
	}
}
