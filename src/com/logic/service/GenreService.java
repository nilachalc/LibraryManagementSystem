package com.logic.service;

import java.util.List;

import com.data.bean.GenreDropDownBean;
import com.gen.exception.ServiceException;

public interface GenreService {
	
	public List<GenreDropDownBean> readAllGenreNames() throws ServiceException;
}
