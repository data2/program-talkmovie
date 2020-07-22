package com.muskteer.tm.dao.mapper;

import com.muskteer.tm.common.bean.ArticleInfoBean;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglei on 2018/2/28.
 */
@Mapper
public interface ArticleMapper {

    @Select("select * from tm_article_info where articleID=#{id}")
    ArticleInfoBean queryArticleById(String id);

    @Select("select * from tm_article_info where tougaoTime  > #{date} and isShoulu is  null")
    List<ArticleInfoBean> queryArticleByDate(Date date);

    @Select("select * from tm_article_info where tougaoTime  > #{date} and isShoulu = 'yes'")
    List<ArticleInfoBean> queryArticleByDateShoulu(Date date);

    @Select("select * from tm_article_info where filmArea=#{area} order by tougaoTime desc limit 7")
    List<ArticleInfoBean> queryArticleByArea(String area);

    @Select("select * from tm_article_info where filmName like #{param} \n")
    List<ArticleInfoBean> queryArticleBySearchName(String param);


    @Insert("insert into tm_article_info (articleID, filmName, filmArea, title, author, content, tougaoTime, shouluTime, readAmount, storePath) \n" +
            "    values (#{articleID}, #{filmName}, #{filmArea}, #{title}, #{author}, #{content}, #{tougaoTime}, #{shouluTime}, #{readAmount}, #{storePath})")
    int insert(ArticleInfoBean articleInfoBean);

    @Update("update tm_article_info set isShoulu = #{isShoulu} ,shouluTime = #{shouluTime}\n" +
            "    where articleID = #{articleID}")
    void shenheArticle(ArticleInfoBean articleInfoBean);

    @Delete("delete from tm_article_info where articleID = #{articleID}")
    void deleteUnShenheArticle(String articleID);


    @Update("update tm_article_info set goodScore = goodScore + 1  where articleID = #{articleId}\n")
    void voteArticleGood(String articleId);

    @Update("update tm_article_info set badScore = badScore + 1  where articleID = #{articleId}\n")
    void voteArticleBad(String articleId);



}
