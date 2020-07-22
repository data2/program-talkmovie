/**
 * FileName:   ArticleServiceImpl.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.muskteer.tm.common.Constant;
import com.muskteer.tm.common.Idworker;
import com.muskteer.tm.common.bean.ArticleInfoBean;
import com.muskteer.tm.common.bean.UserInfoBean;
import com.muskteer.tm.common.util.*;
import com.muskteer.tm.dao.ArticelDao;
import com.muskteer.tm.dao.UserDao;
import com.muskteer.tm.service.ArticleService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticelDao articleDao;
    @Autowired
    private UserDao userDao;


    @Override
    public String tougao(HttpServletRequest request,
        HttpServletResponse response, ArticleInfoBean bean, MultipartFile file) {
        String ajaxResult = "fail";
        String errormsg = "";
        try {
            String token = bean.getTOKEN();
            if (token == null || StringUtil.isEmpty(token)) {
                ajaxResult = "unlogin";
                return ReturnValueUtils.returnTalkmovieAjaxString(ajaxResult, null, null);
            }
            UserInfoBean user = userDao.queryUserByName(token.split("\\|")[0]);
            if (user == null || !MD5Util.MD5encript(user.getPwd())
                    .equals(token.split("\\|")[1].trim())) {
                errormsg = "cookie content is error";
                ajaxResult = "unlogin";
                return ReturnValueUtils.returnTalkmovieAjaxString(ajaxResult, errormsg, null);
            }

            System.out.println("user:" + user.getUserName() + " login success");
            if (file.isEmpty()) {
                errormsg = "article file is null";
                return ReturnValueUtils.returnTalkmovieAjaxString(ajaxResult, errormsg, bean);
            }
            //获得文件类型（可以判断如果不是图片，禁止上传）  
            String contentType = file.getContentType();
            //获得文件后缀名称  
            String imageName = contentType.substring(contentType.indexOf("/") + 1);
            String date = new SimpleDateFormat("yyyyMM").format(new Date());

            //封面文件夹检查
            File dir = new File(Constant.Webapp_Image_Article + date);
            if (!dir.exists()) {
                dir.mkdir();
            }

            //封面图片存储
            long id = Idworker.nextId();
            String path = dir.getPath() + "/" + bean.getAuthor() + "_" + id + "." + imageName;
            file.transferTo(new File(path));

            //入库
            bean.setArticleID(id + "");
            bean.setTougaoTime(new Date());
            bean.setStorePath(URLEncoder.encode(path.substring(18), "UTF-8"));
            ajaxResult = articleDao.tougaoArticle(bean) == true ? "success" : "fail";
        } catch (Exception e) {
            e.printStackTrace();
            errormsg = "system exception" + e.getMessage();
        }
        return ReturnValueUtils.returnTalkmovieAjaxString(ajaxResult, errormsg, bean);
    }

    @Override
    public String showArticlesBeforeShenhe(HttpServletRequest request, String time) {
        String ajaxResult = "fail";
        ArticleInfoBean[] articles = null;
        try {
            String token = CookieUtils.getCookieParam(request, "TOKEN");
            if (token == null || StringUtil.isEmpty(token)) {
                ajaxResult = "unlogin";
                return ReturnValueUtils.returnTalkmovieAjaxString(ajaxResult, null, null);
            }

            if (time == null || StringUtil.isEmpty(time)
                    || !ArrayUtils.contains(new String[]{"today", "threeday", "tenday"}, time)) {
                return null;
            }
            Date date = null;
            if (time.endsWith("today")) {
                date = new Date(System.currentTimeMillis() - 24 * 3600 * 1000);
            }
            if (time.endsWith("threeday")) {
                date = new Date(System.currentTimeMillis() - 3 * 24 * 3600 * 1000);
            }
            if (time.endsWith("tenday")) {
                date = new Date(System.currentTimeMillis() - 10 * 24 * 3600 * 1000);
            }
            articles = articleDao.queryArticleByDate(date);
            ajaxResult = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status", ajaxResult);
        map.put("articles", articles);
        JSONObject json = (JSONObject) JSONObject.toJSON(map);

        return "judgeShowBeforeShenhe(" + json.toString()
                + ")";
    }

    @Override
    public String shenheArticle(HttpServletRequest request, ArticleInfoBean bean) {
        String ajaxResult = "fail";
        try {
            String token = bean.getTOKEN();
            System.out.println(token + " " + bean.getArticleID() + " " + bean.getIsShoulu());
            if (token == null || StringUtil.isEmpty(token)) {
                ajaxResult = "unlogin";
                return ReturnValueUtils.returnTalkmovieAjaxString(ajaxResult, null, null);
            }

            if (bean.getArticleID() != null && !StringUtil.isEmpty(bean.getArticleID())
                    && org.apache.commons.lang3.ArrayUtils.contains(new String[]{"yes", "no"}, bean.getIsShoulu())) {
                boolean t = articleDao.shenheArticle(bean);
                ajaxResult = t ? "success" : "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status", ajaxResult);
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        return "judgeShenhe(" + json.toString()
                + ")";
    }

    @Override
    public String createYingpingHtml(HttpServletRequest request) {
        String ajaxResult = "fail";
        String token = CookieUtils.getCookieParam(request, "TOKEN");
        try {
            if (token == null || StringUtil.isEmpty(token)) {
                ajaxResult = "unlogin";
            } else {
                ajaxResult = articleDao.createYingpingHtml() ? "success" : ajaxResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status", ajaxResult);
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        return "judgeCreateYingpingHtml(" + json.toString()
                + ")";
    }

    @Override
    public void search(String keyword, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            response.setContentType("text/html; charset=utf-8");
            writer = response.getWriter();
            String msg = "";
            if (keyword.length() < 2) {
                msg = "请输入大于等于两个汉字";
            }
            String html = articleDao.search(keyword, msg);
            writer.print(html);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e2) {
            }
        }

    }

    @Override
    public List<ArticleInfoBean> search(String keyword) {
        try {
            if (keyword.length() < 2) {
                return null;
            }
            return articleDao.search(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String voteArticle(HttpServletRequest request, String state, String articleId) {
        String ajaxResult = "fail";
        String token = CookieUtils.getCookieParam(request, "TOKEN");
        try {
            if (token == null || StringUtil.isEmpty(token)) {
                ajaxResult = "unlogin";
            } else {
                if (StringUtil.isNotEmpty(articleId) && ArrayUtils.contains(new String[]{"good", "bad"}, state)) {
                    ajaxResult = articleDao.voteArticle(state, articleId) ? "success" : ajaxResult;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status", ajaxResult);
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        return "judgeVoteArticle(" + json.toString()
                + ")";
    }

    @Override
    public String createAllOldArticle(HttpServletRequest request) {
        String ajaxResult = "fail";
        String token = CookieUtils.getCookieParam(request, "TOKEN");
        try {
            if (token == null || StringUtil.isEmpty(token)) {
                ajaxResult = "unlogin";
            }else {
                Calendar c = new GregorianCalendar();
                c.add(Calendar.DAY_OF_YEAR,-800);
                System.out.println("from " + c.getTime());
                ajaxResult = ArticleFactory.createAllOldArticle(articleDao.queryArticleByDateShoulu(c.getTime())) ? "success" : ajaxResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status", ajaxResult);
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        return "judgeCreateAllOldArticle(" + json.toString()
                + ")";
    }


}
