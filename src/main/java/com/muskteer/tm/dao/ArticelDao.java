/**
 * FileName:   UserDao.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.dao;


import com.muskteer.tm.common.bean.ArticleInfoBean;

import java.util.Date;
import java.util.List;

public interface ArticelDao {
    public ArticleInfoBean queryArticleById(String id);

    boolean tougaoArticle(ArticleInfoBean bean);

    public ArticleInfoBean[] queryArticleByDate(Date date);

    public boolean shenheArticle(ArticleInfoBean bean);

    public boolean createYingpingHtml();

    String search(String keyword, String msg);
    List<ArticleInfoBean> search(String keyword);

    public boolean voteArticle(String articleId, String articleId2);

    ArticleInfoBean[] queryArticleByDateShoulu(Date time);
}
