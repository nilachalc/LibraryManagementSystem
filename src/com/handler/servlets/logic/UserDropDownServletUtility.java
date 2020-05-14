package com.handler.servlets.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.data.bean.UserDropDownBean;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.handler.servlets.UserDeletionServlet;
import com.logic.service.UserService;
import com.logic.service.Implementation.UserServiceImpl;

public class UserDropDownServletUtility {

	private static Logger logger = AppLogger.getLogger();;
	private static LoadProperties properties = new LoadProperties();
	private static UserService userService;
	
	public UserDropDownServletUtility() {
		super();
	}

	public static void loadUserDropDown(HttpServletRequest request, HttpServletResponse response) {
		logger.info(properties.getPropertyForValue("servletUtilEntry") + UserDeletionServlet.class);
		List<UserDropDownBean> userDropDownBeans = new ArrayList<UserDropDownBean>();
		userService = new UserServiceImpl();

		try {
			userDropDownBeans = userService.readAllUserNames();
			request.setAttribute("userDropDownBeans", userDropDownBeans);
		} catch (ServiceException serviceException) {
			logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
		}

		logger.info(properties.getPropertyForValue("servletUtilExit") + UserDeletionServlet.class);
	}
}
