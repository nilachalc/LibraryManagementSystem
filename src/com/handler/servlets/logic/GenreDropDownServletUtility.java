package com.handler.servlets.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.data.bean.GenreDropDownBean;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.handler.servlets.UserDeletionServlet;
import com.logic.service.GenreService;
import com.logic.service.Implementation.GenreServiceImpl;

public class GenreDropDownServletUtility {

	private static Logger logger = AppLogger.getLogger();;
	private static LoadProperties properties = new LoadProperties();
	private static GenreService genreService;
	
	public GenreDropDownServletUtility() {
		super();
	}

	public static void loadGenreDropDown(HttpServletRequest request, HttpServletResponse response) {
		logger.info(properties.getPropertyForValue("servletUtilEntry") + GenreDropDownServletUtility.class);
		List<GenreDropDownBean> genreDropDownBeans = new ArrayList<GenreDropDownBean>();
		genreService = new GenreServiceImpl();

		try {
			genreDropDownBeans = genreService.readAllGenreNames();
			request.setAttribute("genreDropDownBeans", genreDropDownBeans);
		} catch (ServiceException serviceException) {
			logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
		}

		logger.info(properties.getPropertyForValue("servletUtilExit") + UserDeletionServlet.class);
	}
}
