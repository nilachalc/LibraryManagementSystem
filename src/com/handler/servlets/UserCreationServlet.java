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
import com.gen.util.LoadProperties;
import com.logic.service.UserService;
import com.logic.service.Implementation.UserServiceImpl;

public class UserCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;
	
	private UserService userService;
	private UserBean userBean;
	
	private HttpSession session;

	public UserCreationServlet() {
		super();
	}
	
    public void init() {
    	logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}  

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	logger.info(properties.getPropertyForValue("servletEntry") + UserCreationServlet.class);
    	session = request.getSession(false);
    	userBean = new UserBean();
    	userService = new UserServiceImpl();

    	try {
    		if (request.getParameter("cancelOperation").equals(properties.getPropertyForValue("goBack"))) {
    			if (session != null) {
    				session.removeAttribute("genderListCreation");
    			}

    			request.setAttribute("page", properties.getPropertyForValue("goBack"));
    		} else {
    			userBean.setFirstName(request.getParameter("userFirstName"));
    			userBean.setLastName(request.getParameter("userLastName"));
    			userBean.setAddress(request.getParameter("userAddress"));
    			userBean.setMobile(request.getParameter("userMobile"));
    			userBean.setEmail(request.getParameter("userEmail"));
    			userBean.setAge(Integer.parseInt(request.getParameter("userAge")));
    			userBean.setGender(request.getParameter("userGender"));


    			if (session != null) {
    				userBean = userService.saveUserInfo(userBean);
    			}


    			if (userBean.getUserId() != null) {
    				request.getServletContext().setAttribute("userCreated", new Boolean(true));
    			}
    			request.setAttribute("page", UserCreationServlet.class);
    		}
    	} catch (ServiceException serviceException) {
    		logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
    		throw new ServletException(serviceException);
    	} finally {
    		if (session != null) {
    			session.removeAttribute("genderListCreation");
    		}
    	}

    	logger.info(properties.getPropertyForValue("servletExit") + UserCreationServlet.class);
    	request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
    }
}
