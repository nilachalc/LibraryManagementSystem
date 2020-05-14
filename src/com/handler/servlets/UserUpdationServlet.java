package com.handler.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.data.bean.UserBean;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.LoadProperties;
import com.logic.service.UserService;
import com.logic.service.Implementation.UserServiceImpl;

public class UserUpdationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;

	private UserService userService;
	private UserBean userBean;

	private HttpSession session;

	public UserUpdationServlet() {
		super();
	}

	public void init() {
		logger = AppLogger.getLogger();
		properties = new LoadProperties();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + UserUpdationServlet.class);
		session = request.getSession(false); 
		userService  = new UserServiceImpl();
		Integer userId = Integer.parseInt(request.getParameter("userDropDownValuesForUpdation"));
		if (session != null) {
			try {
				userBean = userService.readUserInfoById(userId);
			} catch (ServiceException serviceException) {
				logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
				throw new ServletException(serviceException);
			}
			session.setAttribute("userValue", userBean);
		}
		request.setAttribute("page", UserUpdationServlet.class); 
		request.getServletContext().setAttribute("userUpdationReady", new Boolean(true));
		
		logger.info(properties.getPropertyForValue("servletExit") + UserUpdationServlet.class);
		request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + UserUpdationServlet.class); session = request.getSession(false); 
		userService  = new UserServiceImpl();
		userBean = new UserBean();

		if (request.getParameter("cancelOperation").equals(properties.getPropertyForValue("goBack"))) {
			if (session != null) {
				session.removeAttribute("userValue");
				session.removeAttribute("genderListUpdation");
				session.removeAttribute("userDropDownValuesForUpdation");
			}
			
			request.setAttribute("page", properties.getPropertyForValue("goBack")); 
		} else {
			String userCSV = request.getParameter("userUpdatedValue");
			
			try { 
				if (session != null) { 
					String[] userValues = userCSV.split(ApplicationConstants.COMMA);
					
					userBean.setUserId(((UserBean)session.getAttribute("userValue")).getUserId());
					userBean.setFirstName(userValues[ApplicationConstants.VALUE_ZERO]);
					userBean.setLastName(userValues[ApplicationConstants.VALUE_ONE]);
					userBean.setAddress(userValues[ApplicationConstants.VALUE_TWO]);
					userBean.setMobile(userValues[ApplicationConstants.VALUE_THREE]);
					userBean.setEmail(userValues[ApplicationConstants.VALUE_FOUR]);
					userBean.setAge(Integer.parseInt(userValues[ApplicationConstants.VALUE_FIVE]));
					userBean.setGender(userValues[ApplicationConstants.VALUE_SIX]);

					userService.updateUserInfo(userBean);
					request.getServletContext().setAttribute("userUpdated", new Boolean(true));
				} 
			} catch (ServiceException serviceException) {
				logger.error((serviceException.toString() + "\n" + serviceException.getMessage())); 
				throw new ServletException(serviceException);
			} finally {
				session.removeAttribute("userValue");
				session.removeAttribute("genderListUpdation");
				session.removeAttribute("userDropDownValuesForUpdation");
			}
			request.setAttribute("page", UserUpdationServlet.class); 
		}
		
		logger.info(properties.getPropertyForValue("servletExit") + UserUpdationServlet.class);
		request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
	}
}