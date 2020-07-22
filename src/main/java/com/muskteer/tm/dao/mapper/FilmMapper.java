package com.muskteer.tm.dao.mapper;

import com.muskteer.tm.common.bean.ArticleInfoBean;
import com.muskteer.tm.common.bean.Film;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglei on 2018/2/28.
 */
@Mapper
public interface FilmMapper {

    @Select("select * from tm_db_film where filmNum=#{filmName}")
    Film queryFilmByName(String filmName);

    @Insert("insert into tm_db_film (filmNum, filmName, enFilmName, filmType, filmLength, area, doubanScore, doubanTag, releaseType, director, actor, year, releaseTime, filmCompany, filmDesc, filmIntroduce,filmStory,filmComment,imageUrl,videoUrl) \n" +
            "    values (#{filmNum}, #{filmName}, #{enFilmName}, #{filmType}, #{filmLength}, #{area}, #{doubanScore}, #{doubanTag}, #{releaseType}, #{director}, #{actor}, #{year}, #{releaseTime}, #{filmCompany}, #{filmDesc}, #{filmIntroduce}, #{filmStory}, #{filmComment}, #{imageUrl}, #{videoUrl})")
    int insert(Film film);

    @Update("update tm_db_film set enFilmName = #{enFilmName} ,filmName = #{filmName}\n" +
            "    where filmNum = #{filmNum}")
    void updateTest(Film film);



}
