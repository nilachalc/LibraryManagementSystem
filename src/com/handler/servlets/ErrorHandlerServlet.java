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

public class ErrorHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Logger logger = null;
	private LoadProperties properties;
	
	public void init() {
    	logger = AppLogger.getLogger();
    	properties = new LoadProperties();
    	
	}
    public ErrorHandlerServlet() {
        super();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	logger.info(properties.getPropertyForValue("servletEntry") + ErrorHandlerServlet.class);
    	HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
    	logger.info(properties.getPropertyForValue("servletExit") + ErrorHandlerServlet.class);
		request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
}
