/**
 * FileName:   UserService.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MukteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.service;

import com.muskteer.tm.common.bean.ArticleInfoBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 */

public interface ArticleService {
    String tougao(HttpServletRequest request,
                  HttpServletResponse response, ArticleInfoBean bean, MultipartFile file);

    String showArticlesBeforeShenhe(HttpServletRequest request, String time);

    String shenheArticle(HttpServletRequest request, ArticleInfoBean bean);

    String createYingpingHtml(HttpServletRequest request);

    void search(String keyword, HttpServletResponse response);
    List<ArticleInfoBean> search(String keyword);

    String voteArticle(HttpServletRequest request, String articleId, String articleId2);

    String createAllOldArticle(HttpServletRequest request);
}
