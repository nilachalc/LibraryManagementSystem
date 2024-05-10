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
import com.repo.adapter.GenreAdapter;
import com.repo.dao.GenreDao;

public class GenreAdapterImpl implements GenreAdapter {
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public GenreAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public void udateGenreHit(Integer genreId, Integer genreHit) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + GenreAdapterImpl.class);
		Integer updationCompletionStatus;
		try {
			updationCompletionStatus = null;
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			String sqlQuery = properties.getPropertyForValue("genreHitUpdate");
			preparedStatement = con.prepareStatement(sqlQuery);
			
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, genreHit);
			preparedStatement.setInt(ApplicationConstants.VALUE_TWO, genreId);
			
			updationCompletionStatus = preparedStatement.executeUpdate();			
			if ( updationCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Updation Failed." + properties.getPropertyForValue("adapterExit") + GenreAdapterImpl.class);
				throw new DBException();
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("Genre Successfully Updated with Status : " + updationCompletionStatus + " " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
	}

	@Override
	public Integer getGenreHitById(Integer genreId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + GenreAdapterImpl.class);
		Integer genreHit = null;
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			
			String sqlQuery = properties.getPropertyForValue("genreHitForAGenreLookup");
			
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(ApplicationConstants.VALUE_ONE, genreId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null && rs.next()) {
				genreHit= rs.getInt(ApplicationConstants.VALUE_ONE);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("Genre hit data fatched. " + properties.getPropertyForValue("adapterExit") + GenreAdapterImpl.class);
		return genreHit;
	}
	
	@Override
	public List<GenreDao> getAllGenreNamesWithIds() throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		List<GenreDao> genreDaos = new ArrayList<GenreDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			GenreDao genreDao;
			
			String sqlQuery = properties.getPropertyForValue("genreDropDownSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				genreDao = new GenreDao();
				
				genreDao.setGenreId(rs.getInt(ApplicationConstants.VALUE_ONE));
				genreDao.setGenreName(rs.getString(ApplicationConstants.VALUE_TWO));
				
				genreDaos.add(genreDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("Genre dropdown data fatched. " + properties.getPropertyForValue("adapterExit") + GenreAdapterImpl.class);
		return genreDaos;
	}
}