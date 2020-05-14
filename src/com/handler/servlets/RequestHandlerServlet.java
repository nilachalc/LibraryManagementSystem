package com.handler.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.data.bean.AdminBean;
import com.gen.jsp.utility.GenderDropDownUtility;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.LoadProperties;
import com.handler.servlets.logic.UserDropDownServletUtility;
import com.handler.servlets.logic.UserViewServletUtility;

public class RequestHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;
       
    public RequestHandlerServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + RequestHandlerServlet.class);
		Object pageAttribute = request.getAttribute("page");
		HttpSession session = request.getSession(false);
		
		try {
			if (session != null) {
				if (pageAttribute != null) {
					if (pageAttribute instanceof String) {
						if (((String)pageAttribute).equals(properties.getPropertyForValue("goBack"))) {
							response.sendRedirect("Jsps/LogInHome.jsp");
						} else if (((String)pageAttribute).equals(properties.getPropertyForValue("createAUser"))) {
							session.setAttribute("genderListCreation", GenderDropDownUtility.genderList);
							response.sendRedirect("Jsps/UserCreation.jsp");
						} else if (((String)pageAttribute).equals(properties.getPropertyForValue("deleteAUser"))) {
							UserDropDownServletUtility.loadUserDropDown(request, response);
							session.setAttribute("userDropDownValuesForDeletion", request.getAttribute("userDropDownBeans"));
							response.sendRedirect("Jsps/UserDeletion.jsp");
						} else if (((String)pageAttribute).equals(properties.getPropertyForValue("viewAllUsers"))) {
							UserViewServletUtility.loadUsers(request, response);
							session.setAttribute("userValuesForView", request.getAttribute("userBeans"));
							response.sendRedirect("Jsps/UserView.jsp");
						} else if (((String)pageAttribute).equals(properties.getPropertyForValue("updateAUser"))) {
							UserDropDownServletUtility.loadUserDropDown(request, response);
							session.setAttribute("userDropDownValuesForUpdation", request.getAttribute("userDropDownBeans"));
							response.sendRedirect("Jsps/UserUpdation.jsp");
						} else if (((String)pageAttribute).equals(properties.getPropertyForValue("bookBulkUploadFromAFile"))) {
							response.sendRedirect("Jsps/BookBulkUpload.jsp");
						}
					} else {
						if (((Class<LogInServlet>)pageAttribute).equals(LogInServlet.class)) {
							if (((AdminBean) request.getAttribute("adminInfo")).getAdminId() != null) {
								response.sendRedirect("Jsps/LogInHome.jsp");
							} else {
								response.sendRedirect("Jsps/LogInFailed.jsp");
							}
						} else if (((Class<LogInFailedServlet>)pageAttribute).equals(LogInFailedServlet.class)) {
							response.sendRedirect("LogIn.jsp");
						} else if (((Class<LogInHomeServlet>)pageAttribute).equals(LogInHomeServlet.class)) {
							response.sendRedirect("Jsps/LogOut.jsp");
						} else if (((Class<UserCreationServlet>)pageAttribute).equals(UserCreationServlet.class)) {
							response.sendRedirect("Jsps/LogInHome.jsp");
						}  else if (((Class<UserViewServlet>)pageAttribute).equals(UserViewServlet.class)) {
							response.sendRedirect("Jsps/LogInHome.jsp");
						} else if (((Class<UserDeletionServlet>)pageAttribute).equals(UserDeletionServlet.class)
								&& (((Boolean)request.getServletContext().getAttribute("userDeletionImpossible")).equals(ApplicationConstants.VALUE_FALSE))) {
							response.sendRedirect("Jsps/LogInHome.jsp");
						} else if (((Class<UserDeletionServlet>)pageAttribute).equals(UserDeletionServlet.class)
								&& (((Boolean)request.getServletContext().getAttribute("userDeletionImpossible")).equals(ApplicationConstants.VALUE_TRUE))) {
							response.sendRedirect("Jsps/UserDeletion.jsp");
						} else if (((Class<UserUpdationServlet>)pageAttribute).equals(UserUpdationServlet.class)
								&& (((Boolean)request.getServletContext().getAttribute("userUpdationReady")).equals(ApplicationConstants.VALUE_TRUE))) {
							session.setAttribute("genderListUpdation", GenderDropDownUtility.genderList);
							response.sendRedirect("Jsps/UserUpdation.jsp");
						} else if (((Class<UserUpdationServlet>)pageAttribute).equals(UserUpdationServlet.class)
								&& (((Boolean)request.getServletContext().getAttribute("userUpdated")).equals(ApplicationConstants.VALUE_TRUE))) {
							response.sendRedirect("Jsps/LogInHome.jsp");
						} else if (((Class<BookBulkUploadServlet>)pageAttribute).equals(BookBulkUploadServlet.class)) {
							response.sendRedirect("Jsps/LogInHome.jsp");
						}
					}
				} else {
					logger.error(properties.getPropertyForValue("navigationError"));
				}
			} else {
				if (pageAttribute instanceof String) {
					if (((String)pageAttribute).equals(properties.getPropertyForValue("goBack"))) {
						response.sendRedirect("LogIn.jsp");
					} else {
						response.sendRedirect("Jsps/SessionTimedOut.jsp");
					}
				} else {
					if (((Class<LogOutServlet>)pageAttribute).equals(LogOutServlet.class)) {
						response.sendRedirect("LogIn.jsp");
					} else if (((Class<LogOutServlet>)pageAttribute).equals(LogInServlet.class)) {
						response.sendRedirect("LogIn.jsp");
					} else if (((Class<SessionTimedOutServlet>)pageAttribute).equals(SessionTimedOutServlet.class)) {
						response.sendRedirect("LogIn.jsp");
					} else {
						response.sendRedirect("Jsps/SessionTimedOut.jsp");
					}
				}
			}
		} catch (Exception exception) {
			logger.error((exception.toString() + "\n" + exception.getMessage()));
			throw new ServletException(exception);
		}

		logger.info(properties.getPropertyForValue("servletExit") + RequestHandlerServlet.class);
	}
}
