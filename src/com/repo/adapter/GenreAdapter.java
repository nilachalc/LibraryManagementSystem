package com.repo.adapter;

import java.util.List;

import com.gen.exception.DBException;
import com.repo.dao.GenreDao;

public interface GenreAdapter {
	
	public void udateGenreHit(Integer genreId, String genreHit) throws DBException;
	
	public String getGenreHitById(Integer genreId) throws DBException;
	
	public List<GenreDao> getAllGenreNamesWithIds() throws DBException;
	
}
