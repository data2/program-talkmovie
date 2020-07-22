/**
 * FileName:   ArticleDaoImpl.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.dao.impl;

import com.muskteer.tm.common.BusinessException;
import com.muskteer.tm.common.Constant;
import com.muskteer.tm.common.bean.ArticleInfoBean;
import com.muskteer.tm.common.bean.UserTougaoInfoBean;
import com.muskteer.tm.common.util.ArticleFactory;
import com.muskteer.tm.dao.ArticelDao;
import com.muskteer.tm.dao.mapper.ArticleMapper;
import com.muskteer.tm.dao.mapper.UserMapper;
import com.muskteer.tm.dao.mapper.UserTougaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 */
@Component
@Transactional
public class ArticleDaoImpl implements ArticelDao {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTougaoMapper userTougaoMapper;

    public ArticleInfoBean queryArticleById(String id) {
        try {
            return articleMapper.queryArticleById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Transactional
    @Override
    public boolean tougaoArticle(ArticleInfoBean bean) {
        try {
            if (articleMapper.insert(bean) == 1) {
                System.out.println(bean.getAuthor());
                userMapper.updateUserAfterTougao(bean.getAuthor());
                System.out.println("tougao Article success ");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return false;
    }


    @Override
    public ArticleInfoBean[] queryArticleByDate(Date date) {
        try {
            List<ArticleInfoBean> beans = articleMapper.queryArticleByDate(date);
            System.out.println(beans);
            return beans.toArray(new ArticleInfoBean[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Transactional
    @Override
    public boolean shenheArticle(ArticleInfoBean bean) {
        ArticleInfoBean b = null;
        try {
            b = articleMapper.queryArticleById(bean.getArticleID());
            if (bean.getIsShoulu().equals("yes")) {
                b.setShouluTime(new Date());
                articleMapper.shenheArticle(bean);
                userMapper.updateUserAfterShoulu(bean);
                userTougaoMapper.insertUserTougaoInfoAfterShenhe(new UserTougaoInfoBean(
                        bean.getAuthor(), b.getTougaoTime(), bean.getArticleID()));
                if (b != null) {
                    ArticleFactory.createArticle(b);
                }
            } else if (bean.getIsShoulu().equals("no")) {
                articleMapper.deleteUnShenheArticle(bean.getArticleID());
                File image = new File(Constant.Webapp_Image + URLDecoder.decode(b.getStorePath(),"UTF-8"));
                image.delete();
            }
            System.out.println("shenhe Article success ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            articleMapper.deleteUnShenheArticle(bean.getArticleID());
            File image = null;
            try {
                image = new File(Constant.Webapp_Image + URLDecoder.decode(b.getStorePath(),"UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            image.delete();
            throw new BusinessException("审核异常");
        }
    }

    @Override
    public boolean createYingpingHtml() {
        try {
            ArticleFactory.createYingpingHtml(
                    articleMapper.queryArticleByArea("us"),
                    articleMapper.queryArticleByArea("europe"),
                    articleMapper.queryArticleByArea("eastasia"),
                    articleMapper.queryArticleByArea("china"));
            System.out.println("createYingpingHtml  success ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public String search(String keyword, String msg) {
        try {
            return ArticleFactory.search(articleMapper.queryArticleBySearchName("%" + keyword + "%"), msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @Override
    public List<ArticleInfoBean> search(String keyword){
        return articleMapper.queryArticleBySearchName(keyword);
    }

    @Override
    public boolean voteArticle(String state, String articleId) {
        try {
            if (state.equals("good")) {
                articleMapper.voteArticleGood(articleId);
            }
            if (state.equals("bad")) {
                articleMapper.voteArticleBad(articleId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public ArticleInfoBean[] queryArticleByDateShoulu(Date time) {
        try {
            List<ArticleInfoBean> beans = articleMapper.queryArticleByDateShoulu(time);
            return beans.toArray(new ArticleInfoBean[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
