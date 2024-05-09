package com.logic.mapper;

import com.data.bean.GenreDropDownBean;
import com.repo.dao.GenreDao;

public class GenreDataMapper {
	private GenreDropDownBean genreDropDownBean;
	
	public GenreDropDownBean mapDaoDataToDropDownBean(GenreDao genreDao) {
		genreDropDownBean = new GenreDropDownBean();
		
		genreDropDownBean.setGenreId(genreDao.getGenreId());
		genreDropDownBean.setGenreName(genreDao.getGenreName());
		
		return genreDropDownBean;
	}
}
