package com.handler.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.data.bean.BookBean;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.DateConverter;
import com.gen.util.LoadProperties;
import com.logic.service.BookService;
import com.logic.service.Implementation.BookServiceImpl;

public class BookCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	private LoadProperties properties;
	
	private BookService bookService;
	private BookBean bookBean;
	List<BookBean> bookBeans;
	
	private HttpSession session;

	public BookCreationServlet() {
		super();
	}
	
    public void init() {
    	logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}  

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	logger.info(properties.getPropertyForValue("servletEntry") + BookCreationServlet.class);
    	session = request.getSession(false);
    	bookBean = new BookBean();
    	bookService = new BookServiceImpl();
    	bookBeans = new ArrayList<BookBean>();

    	try {
    		if (request.getParameter("cancelOperation").equals(properties.getPropertyForValue("goBack"))) {
    			if (session != null) {
    				session.removeAttribute("genderListCreation");
    			}

    			request.setAttribute("page", properties.getPropertyForValue("goBack"));
    		} else {
    			bookBean.setBookName(request.getParameter("bookName"));
    			bookBean.setAuthorName(request.getParameter("authorName"));
    			bookBean.setAvailabilityDate(DateConverter.stringToDate(request.getParameter("availabilityDate")));
    			bookBean.setGenreId(Integer.parseInt(request.getParameter("genreDropDownValues")));
    			bookBeans.add(bookBean);
    			if (session != null) {
    				session.setAttribute("NoOfBookUploaded", bookService.saveBookInfo(bookBeans).size());
    			}
    			request.setAttribute("page", BookCreationServlet.class);
    			request.getServletContext().setAttribute("bookCreated", new Boolean(true));
    		}
    	} catch (ServiceException | ParseException serviceException) {
    		logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
    		throw new ServletException(serviceException);
    	} finally {
    		if (session != null) {
    		}
    	}

    	logger.info(properties.getPropertyForValue("servletExit") + BookCreationServlet.class);
    	request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
    }
}
