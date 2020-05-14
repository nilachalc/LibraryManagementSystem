package com.logic.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.data.bean.UserBean;
import com.data.bean.UserDropDownBean;
import com.gen.exception.DBException;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.LoadProperties;
import com.logic.mapper.UserDataMapper;
import com.logic.service.BookService;
import com.logic.service.UserService;
import com.repo.adapter.UserAdapter;
import com.repo.adapter.implementation.UserAdapterImpl;
import com.repo.dao.UserDao;

public class UserServiceImpl implements UserService {
	private Logger logger = null;
	private LoadProperties properties;
	
	private UserDataMapper userDataMapper;
	private UserAdapter userAdapter;
	
	public UserServiceImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}
	
	@Override
	public UserBean saveUserInfo(UserBean userBean) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + UserServiceImpl.class);
		userDataMapper = new UserDataMapper();
		userAdapter = new UserAdapterImpl();
		UserDao userDao;
		
		try {
			userDao = userDataMapper.mapBeanDataToDao(userBean, ApplicationConstants.VALUE_FALSE);
			userBean.setUserId(userAdapter.save(userDao));
			
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + UserServiceImpl.class);
		return userBean;
	}

	@Override
	public void updateUserInfo(UserBean userBean) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + UserServiceImpl.class);
		userDataMapper = new UserDataMapper();
		userAdapter = new UserAdapterImpl();
		UserDao userDao;
		
		try {
			userDao = userDataMapper.mapBeanDataToDao(userBean, ApplicationConstants.VALUE_TRUE);
			
			Integer updationCompletionStatus = userAdapter.update(userDao);
			if (updationCompletionStatus == ApplicationConstants.VALUE_ZERO) {
				throw new ServiceException();
			}
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + UserServiceImpl.class);
	}
	
	public Boolean deleteUserInfo(Integer userId) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + UserServiceImpl.class);
		userAdapter = new UserAdapterImpl();
		BookService bookService = new BookServiceImpl();
		Boolean isDeletionPossible = ApplicationConstants.VALUE_FALSE;
		
		try {
			isDeletionPossible = !(bookService.bookIssuedForUserCheck(userId));
			
			if (isDeletionPossible) {
				Integer deletionCompletionStatus = userAdapter.delete(userId);
				if (deletionCompletionStatus == ApplicationConstants.VALUE_ZERO) {
					throw new ServiceException();
				}
			}
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + UserServiceImpl.class);
		return isDeletionPossible;
	}

	@Override
	public List<UserBean> readUserInfo() throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + UserServiceImpl.class);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		userDataMapper = new UserDataMapper();
		userAdapter = new UserAdapterImpl();
		
		try {
			List<UserDao> users = userAdapter.getAllUsers();
			UserBean userBean;
			
			for (UserDao dao : users) {
				userBean = userDataMapper.mapDaoDataToBean(dao, ApplicationConstants.VALUE_FALSE);
				userBeans.add(userBean);
			}
			
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + UserServiceImpl.class);
		return userBeans;
	}
	
	@Override
	public UserBean readUserInfoById(Integer userId) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + UserServiceImpl.class);
		UserBean userBean = null;
		userDataMapper = new UserDataMapper();
		userAdapter = new UserAdapterImpl();
		
		try {
			UserDao user = userAdapter.getUserById(userId);
			
			userBean = userDataMapper.mapDaoDataToBean(user, ApplicationConstants.VALUE_TRUE);
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + UserServiceImpl.class);
		return userBean;
	}
	
	@Override
	public List<UserDropDownBean> readAllUserNames() throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + UserServiceImpl.class);
		List<UserDropDownBean> dropDownBeans = new ArrayList<UserDropDownBean>();
		userDataMapper = new UserDataMapper();
		userAdapter = new UserAdapterImpl();
		
		try {
			List<UserDao> users = userAdapter.getAllUserNamesWithIds();
			UserDropDownBean userDropDownBean;
			
			for (UserDao dao : users) {
				userDropDownBean = userDataMapper.mapDaoDataToDropDownBean(dao);
				dropDownBeans.add(userDropDownBean);
			}
			
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + UserServiceImpl.class);
		return dropDownBeans;
	}
}
