package com.muskteer.tm.dao.impl;

import com.muskteer.tm.common.bean.Film;
import com.muskteer.tm.dao.FilmDao;
import com.muskteer.tm.dao.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FilmDaoImpl implements FilmDao {
    @Autowired
    private FilmMapper filmMapper;
    @Override
    public Film queryFilmByName(String filmName) {
        return null;
    }

    @Override
    public void updateFilm(Film film) {

    }

    @Override
    public boolean inserFilm(Film film) {
        try{
            return filmMapper.insert(film) == 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
