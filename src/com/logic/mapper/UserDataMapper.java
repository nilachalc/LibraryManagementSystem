package com.logic.mapper;

import com.data.bean.UserBean;
import com.data.bean.UserDropDownBean;
import com.gen.util.ApplicationConstants;
import com.repo.dao.UserDao;

public class UserDataMapper {
	private UserDao userDao;
	private UserBean userBean;
	private UserDropDownBean userDropDownBean;
	
	public UserDao mapBeanDataToDao(UserBean userBean, Boolean updateFlag) {
		userDao = new UserDao();
		
		if (updateFlag) {
			userDao.setUserId(userBean.getUserId());
		}
		userDao.setFirstName(userBean.getFirstName());
		userDao.setLastName(userBean.getLastName());
		userDao.setAddress(userBean.getAddress());
		userDao.setMobile(userBean.getMobile());
		userDao.setEmail(userBean.getEmail());
		userDao.setAge(userBean.getAge());
		
		switch (userBean.getGender()) {
		case ApplicationConstants.GENDER_MALE:
					userDao.setGender(ApplicationConstants.GENDER_LITERAL_MALE);
					break;
			
		case ApplicationConstants.GENDER_FEMALE:
					userDao.setGender(ApplicationConstants.GENDER_LITERAL_FEMALE);
					break;

		default:
					userDao.setGender(ApplicationConstants.GENDER_LITERAL_OTHER);
					break;
		}
		
		return userDao;
	}
	
	public UserBean mapDaoDataToBean(UserDao userDao, Boolean updateFlag) {
		userBean = new UserBean();

		if (updateFlag) {
			userBean.setFirstName(userDao.getFirstName());
			userBean.setLastName(userDao.getLastName());
		} else {
			userBean.setUserName(userDao.getFirstName() + ApplicationConstants.BLANKSPACE + userDao.getLastName());
		}
		userBean.setUserId(userDao.getUserId());
		userBean.setAddress(userDao.getAddress());
		userBean.setMobile(userDao.getMobile());
		userBean.setEmail(userDao.getEmail());
		userBean.setAge(userDao.getAge());
		if (userDao.getGender() != null) {
			if (userDao.getGender().equals(ApplicationConstants.GENDER_LITERAL_MALE)) {
				userBean.setGender(ApplicationConstants.GENDER_MALE);
			} else if (userDao.getGender().equals(ApplicationConstants.GENDER_LITERAL_FEMALE)) {
				userBean.setGender(ApplicationConstants.GENDER_FEMALE);
			} else {
				userBean.setGender(ApplicationConstants.GENDER_OTHER);
			}
		}
		
		return userBean;
	}
	
	public UserDropDownBean mapDaoDataToDropDownBean(UserDao userDao) {
		userDropDownBean = new UserDropDownBean();
		
		userDropDownBean.setUserId(userDao.getUserId());
		userDropDownBean.setUserFullName(userDao.getFirstName() + ApplicationConstants.BLANKSPACE + userDao.getLastName());
		
		return userDropDownBean;
	}
}
