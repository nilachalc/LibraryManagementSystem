package com.logic.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.data.bean.GenreDropDownBean;
import com.gen.exception.DBException;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.logic.mapper.GenreDataMapper;
import com.logic.mapper.UserDataMapper;
import com.logic.service.GenreService;
import com.repo.adapter.GenreAdapter;
import com.repo.adapter.implementation.GenreAdapterImpl;
import com.repo.dao.GenreDao;

public class GenreServiceImpl implements GenreService {
	private Logger logger = null;
	private LoadProperties properties;
	
	private GenreDataMapper genreDataMapper;
	private GenreAdapter genreAdapter;
	
	public GenreServiceImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}
	
	@Override
	public List<GenreDropDownBean> readAllGenreNames() throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + GenreServiceImpl.class);
		List<GenreDropDownBean> dropDownBeans = new ArrayList<GenreDropDownBean>();
		genreDataMapper = new GenreDataMapper();
		genreAdapter = new GenreAdapterImpl();
		
		try {
			List<GenreDao> genres = genreAdapter.getAllGenreNamesWithIds();
			GenreDropDownBean genreDropDownBean;
			
			for (GenreDao dao : genres) {
				genreDropDownBean = genreDataMapper.mapDaoDataToDropDownBean(dao);
				dropDownBeans.add(genreDropDownBean);
			}
			
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + GenreServiceImpl.class);
		return dropDownBeans;
	}
}
