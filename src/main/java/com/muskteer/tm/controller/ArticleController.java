package com.muskteer.tm.controller;

import com.muskteer.tm.common.bean.ArticleInfoBean;
import com.muskteer.tm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 */
@RestController
@RequestMapping({"/article/*"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/tougao", method = RequestMethod.POST)
    @ResponseBody
    public String tougao(HttpServletRequest request, HttpServletResponse response, ArticleInfoBean bean,
                         @RequestParam MultipartFile articleImgFile) {
        try {
            System.out.println(request.getParameter("TOKEN"));
            System.out.println("tougao params : " + bean.toString());
            System.out.println(bean.getTitle());
            System.out.println(new String(bean.getTitle().getBytes("iso-8859-1"), "utf-8"));

//            bean.setTitle(new String(bean.getTitle().getBytes("iso-8859-1"), "utf-8"));
//            bean.setFilmName(new String(bean.getFilmName().getBytes("iso-8859-1"), "utf-8"));
//            bean.setAuthor(new String(bean.getAuthor().getBytes("iso-8859-1"), "utf-8"));
//            bean.setContent(new String(bean.getContent().getBytes("iso-8859-1"), "utf-8"));
        } catch (Exception e) {
        }
        return articleService.tougao(request, response, bean, articleImgFile);
    }

    @RequestMapping(value = "/showArticlesBeforeShenhe")
    @ResponseBody
    public String showArticlesBeforeShenhe(HttpServletRequest request, String time) {
        return articleService.showArticlesBeforeShenhe(request, time);
    }

    @RequestMapping(value = "/shenheArticle")
    @ResponseBody
    public String shenheArticle(HttpServletRequest request, ArticleInfoBean bean) {
        return articleService.shenheArticle(request, bean);
    }

    @RequestMapping(value = "/createAllOldArticle")
    @ResponseBody
    public String createAllOldArticle(HttpServletRequest request) {
        return articleService.createAllOldArticle(request);
    }

    @RequestMapping(value = "/createYingpingHtml")
    @ResponseBody
    public String createYingpingHtml(HttpServletRequest request) {
        return articleService.createYingpingHtml(request);
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public void search(String keyword, HttpServletResponse response) {
        articleService.search(keyword, response);
    }

    @RequestMapping(value = "/voteArticle")
    @ResponseBody
    public String voteArticle(HttpServletRequest request, String state, String articleId) {
        return articleService.voteArticle(request, state, articleId);
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("test okay");
        pw.flush();
        pw.close();
    }

}
