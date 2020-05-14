package com.logic.service;

import java.util.List;

import com.data.bean.UserBean;
import com.data.bean.UserDropDownBean;
import com.gen.exception.ServiceException;

public interface UserService {
	public UserBean saveUserInfo(UserBean userBean) throws ServiceException;
	
	public void updateUserInfo(UserBean userBean) throws ServiceException;
	
	public Boolean deleteUserInfo(Integer userId) throws ServiceException;
	
	public List<UserBean> readUserInfo() throws ServiceException;
	
	public UserBean readUserInfoById(Integer userId) throws ServiceException;
	
	public List<UserDropDownBean> readAllUserNames() throws ServiceException;
}
