package com.repo.adapter;

import com.gen.exception.DBException;
import com.repo.dao.AdminDao;

public interface AdminAdapter {

	public void getAdminLookupById(AdminDao logInDao) throws DBException;
	
}
