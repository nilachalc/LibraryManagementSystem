package com.logic.service.Implementation;

import org.apache.log4j.Logger;

import com.data.bean.AdminBean;
import com.gen.exception.DBException;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.logic.mapper.AdminDataMapper;
import com.logic.service.AdminService;
import com.repo.adapter.AdminAdapter;
import com.repo.adapter.implementation.AdminAdapterImpl;
import com.repo.dao.AdminDao;

public class AdminServiceImpl implements  AdminService {
	private Logger logger = null;
	private LoadProperties properties;
	
	private AdminDataMapper adminDataMapper;
	private AdminDao adminDao;
	private AdminAdapter adminAdapter;
	
	public AdminServiceImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public AdminBean readLogInInfo(AdminBean adminBean) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + AdminServiceImpl.class);
		adminDataMapper = new AdminDataMapper();
		adminAdapter = new AdminAdapterImpl();
		
		try {
			adminDao = adminDataMapper.mapBeanDataToDao(adminBean);
			adminAdapter.getAdminLookupById(adminDao);
			adminBean = adminDataMapper.mapDaoDataToBean(adminDao);
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + AdminServiceImpl.class);
		return adminBean;
	}
}
