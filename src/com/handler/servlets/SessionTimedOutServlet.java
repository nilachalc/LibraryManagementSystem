package com.handler.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;

public class SessionTimedOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;
	
    public SessionTimedOutServlet() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + SessionTimedOutServlet.class);
		try {
			request.getSession(false).invalidate();
			request.setAttribute("page", SessionTimedOutServlet.class);
			
			logger.info(properties.getPropertyForValue("servletExit") + SessionTimedOutServlet.class);
			request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
		} catch (Exception exception) {
			logger.error((exception.toString() + "\n" + exception.getMessage()));
			throw new ServletException(exception);
		}
	}
}
