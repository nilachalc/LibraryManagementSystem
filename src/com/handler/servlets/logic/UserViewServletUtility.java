package com.handler.servlets.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.data.bean.UserBean;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.handler.servlets.UserDeletionServlet;
import com.logic.service.UserService;
import com.logic.service.Implementation.UserServiceImpl;

public class UserViewServletUtility {

	private static Logger logger = AppLogger.getLogger();;
	private static LoadProperties properties = new LoadProperties();
	private static UserService userService;
	
	public UserViewServletUtility() {
		super();
	}

	public static void loadUsers(HttpServletRequest request, HttpServletResponse response) {
		logger.info(properties.getPropertyForValue("servletUtilEntry") + UserDeletionServlet.class);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		userService = new UserServiceImpl();

		try {
			userBeans = userService.readUserInfo();
			request.setAttribute("userBeans", userBeans);
		} catch (ServiceException serviceException) {
			logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
		}

		logger.info(properties.getPropertyForValue("servletUtilExit") + UserDeletionServlet.class);
	}
}
