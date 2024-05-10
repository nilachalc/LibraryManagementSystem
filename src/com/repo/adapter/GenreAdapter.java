package com.repo.adapter;

import java.util.List;

import com.gen.exception.DBException;
import com.repo.dao.GenreDao;

public interface GenreAdapter {
	
	public void udateGenreHit(Integer genreId, Integer genreHit) throws DBException;
	
	public Integer getGenreHitById(Integer genreId) throws DBException;
	
	public List<GenreDao> getAllGenreNamesWithIds() throws DBException;
	
}
