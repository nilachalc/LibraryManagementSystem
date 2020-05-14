package com.repo.adapter.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gen.exception.DBException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.DataBaseConnection;
import com.gen.util.LoadProperties;
import com.repo.adapter.AdminAdapter;
import com.repo.dao.AdminDao;

public class AdminAdapterImpl implements AdminAdapter {
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public AdminAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public void getAdminLookupById(AdminDao adminDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + AdminAdapterImpl.class);
		Boolean adminSearchFlag = ApplicationConstants.VALUE_FALSE;
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			
			String sqlQuery = properties.getPropertyForValue("logInSelect");
			
			preparedStatement = con.prepareStatement(sqlQuery); 
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				if (adminDao.getAdminName().equalsIgnoreCase(rs.getString(ApplicationConstants.VALUE_TWO))
						&& adminDao.getPassword().equals(rs.getString(ApplicationConstants.VALUE_THREE))) {
					logger.info("User exits. " + AdminAdapterImpl.class);
					
					adminDao.setAdminId(rs.getInt(ApplicationConstants.VALUE_ONE));
					adminDao.setAdminName(rs.getString(ApplicationConstants.VALUE_TWO));
					adminSearchFlag = ApplicationConstants.VALUE_TRUE;
					break;
				}
			}
			
			if(!adminSearchFlag) {
				logger.info("User does not exits. " + properties.getPropertyForValue("adapterExit") + AdminAdapterImpl.class);	
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
	}
}