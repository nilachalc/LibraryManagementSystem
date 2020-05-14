package com.logic.service;

import com.data.bean.AdminBean;
import com.gen.exception.ServiceException;

public interface AdminService {
	
	public AdminBean readLogInInfo(AdminBean logInBean) throws ServiceException;
}
