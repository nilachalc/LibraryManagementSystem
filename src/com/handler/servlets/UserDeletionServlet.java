package com.handler.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.logic.service.UserService;
import com.logic.service.Implementation.UserServiceImpl;

public class UserDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;
	
	private UserService userService;

	private HttpSession session;
	
	public UserDeletionServlet() {
		super();
	}
	
	public void init() {
    	logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + UserDeletionServlet.class);
		session = request.getSession(false);
		userService = new UserServiceImpl();
		
		if (request.getParameter("cancelOperation").equals(properties.getPropertyForValue("goBack"))) {
			if (session != null) {
				session.removeAttribute("userDropDownValuesForDeletion");
			}
			
			request.setAttribute("page", properties.getPropertyForValue("goBack"));
		} else {
			int userId = Integer.parseInt(request.getParameter("userDropDownValuesForDeletion"));
			try {
				if (session != null) {
					if (userService.deleteUserInfo(userId)) {
						request.getServletContext().setAttribute("userDeleted", new Boolean(true));
						session.removeAttribute("userDropDownValuesForDeletion");
					} else {
						request.getServletContext().setAttribute("userDeletionImpossible", new Boolean(true));
					}
				}
			} catch (ServiceException serviceException) {
				logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
				throw new ServletException(serviceException);
			}
			
			request.setAttribute("page", UserDeletionServlet.class);
		}
		
		logger.info(properties.getPropertyForValue("servletExit") + UserDeletionServlet.class);
		request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
	}
}
