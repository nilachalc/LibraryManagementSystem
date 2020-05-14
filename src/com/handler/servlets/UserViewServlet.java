package com.handler.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;

public class UserViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;
	
	private HttpSession session;
	
    public UserViewServlet() {
        super();
    }

    public void init() {
    	logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + UserCreationServlet.class);
		try {
			session = request.getSession(false);
			request.setAttribute("page", UserViewServlet.class);
			if (session != null) {
				session.removeAttribute("userValuesForView");
			}
		} catch (Exception exception) {
			logger.error((exception.toString() + "\n" + exception.getMessage()));
			throw new ServletException(exception);
		}
		
		logger.info(properties.getPropertyForValue("servletExit") + UserCreationServlet.class);
		request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
	}
}
