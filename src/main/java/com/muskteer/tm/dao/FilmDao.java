/**
 * FileName:   UserDao.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.dao;


import com.muskteer.tm.common.bean.Film;
import com.muskteer.tm.common.bean.UserInfoBean;

/**
 * @author wanglei
 *
 */
public interface FilmDao {
    Film queryFilmByName(String filmName);

    void updateFilm(Film film);

    boolean inserFilm(Film film);

}
