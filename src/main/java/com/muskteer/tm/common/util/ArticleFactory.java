package com.muskteer.tm.common.util;

import com.muskteer.tm.common.Constant;
import com.muskteer.tm.common.bean.ArticleInfoBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author wanglei
 *
 */
public class ArticleFactory {

    public static void createArticle(ArticleInfoBean b) throws IOException {
        String short_url = new SimpleDateFormat("yyyyMM").format(b.getTougaoTime()) + "/" + b.getArticleID() + ".html";
        String article =
                "<!DOCTYPE html>" +
                        "<html lang='zh-cmn-Hans'>" +
                        "<head>" +
                        "<meta property='articleinfo' content='" + b.getArticleID() + "-" + b.getAuthor() + "-" + b.getTitle() + "-" + b.getFilmName() + "-" + short_url + "' />" +
                        "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
                        "<meta name='keywords' content='香蕉皮影评网|电影评论|影视资讯|影评| " + b.getFilmName() + "|" + b.getTitle() + " '>" +
                        "<meta name='description' content='" + b.getTitle() + "&nbsp;" + b.getContent().substring(0, 100) + "&nbsp;香蕉皮影评网' >" +
                        "<meta name='viewport' content='width=1002' >" +
                        "<title  class=''> " + b.getTitle() + "&nbsp;香蕉皮影评网</title>" +
                        "<link rel='icon' href='https://talkmovie.cn/favicon.ico' type='image/x-icon' />" +
                        "<link rel='shortcut icon' href='https://talkmovie.cn/favicon.ico' type='image/x-icon' />" +
                        "<link href='https://talkmovie.cn/css/common.css' rel='stylesheet' type='text/css'  class=''>" +
                        "" +
                        "<input type='hidden' name='articleUrl' value='https://talkmovie.cn/article/" + short_url + "'/>" +
                        "<div style='text-align: center;width: 100%;margin: 0 auto;width: 1340px;'>" +
                        "" +
                        "<div class='divide-moviemenu' ></div>" +
                        "" +
                        "<!-- Start moviemenu -->" +
                        "<div class='moviemenu' style='width: 1340px;width: 100%;text-align: center;' >" +
                        "<!--<div class='moviemenu' style='width: 1340px;width: 100%;text-align: center;' >" +
                        "-->" +
                        "    <div class='content ' >" +
                        "        <h1 class='fl ' >" +
                        "            <a href='https://talkmovie.cn/' class='logo statisEle ' title='香蕉皮影评网'  >香蕉皮影评网 - Logo</a>" +
                        "        </h1>" +
                        "        <ul class='menus fl ' >" +
                        "            <li class='menu hoverhandler ' style='margin: 0px 6px;' >" +
                        "                <a href='https://talkmovie.cn/yingping.html' class='link statisEle ' statis-event='click' statis-value='SY_ZDH_mv' ><span class='fl ' style='*margin-top:-1px;' >影评</span><b class='fl '></b></a>" +
                        "                <ul class='dropdownmenu fillet ' >" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#us_area' class='fillet statisEle ' statis-event='click' statis-value='SY_ZDH_hl' >美国电影</a>" +
                        "                    </li>" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#europe_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_hy' >欧洲电影</a>" +
                        "                    </li>" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#eastasia_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_om' >日韩电影</a>" +
                        "                    </li>" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#china_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_ry' >华语电影</a>" +
                        "                    </li>" +
                        "                </ul>" +
                        "            </li>" +
                        "" +
                        "" +
                        "            <li class='menu hoverhandler ' style='margin: 0px 6px;'>" +
                        "                <a href='https://talkmovie.cn/tougao.html' class='link statisEle ' statis-event='click' statis-value='SY_ZDH_ent'>" +
                        "                    <span class='fl '>投稿</span>" +
                        "                </a>" +
                        "                " +
                        "            </li>" +
                        "" +
                        "            " +
                        "" +
                        "        </ul>" +
                        "        " +
                        "        <div class='login panel ' >" +
                        "            <a  id='menu_login'  style='display: none;' class='loginBtn J_login statisEle ' href='https://talkmovie.cn/login.html' statis-event='click' statis-value='SY_ZDH_dl' >登录</a>" +
                        "            <a  id='menu_regist' style='display: none;' class='J_register statisEle ' href='https://talkmovie.cn/regist.html' statis-event='click' statis-value='SY_ZDH_zc' >注册</a>" +
                        "" +
                        "            <a id='menu_username' style='display: none;' class='loginBtn J_login statisEle ' href='javascript:void(0);' statis-event='click' statis-value='SY_ZDH_dl' ></a>" +
                        "            <a id='menu_quit' style='display: none;' class='J_register statisEle ' href='javascript:void(0);' statis-event='click' statis-value='SY_ZDH_zc' >退出</a>" +
                        "        </div>" +
                        "" +
                        "    </div>" +
                        "</div>" +
                        "<!-- End moviemenu -->" +
                        "<div class='square_auto square_m' style='width: 1340px;width: 100%;text-align: center;background: #FCFCFC;' >" +
                        "" +
                        "    <div class='square_content ' style='border-left: 1px solid #d3d3d3;border-right: 1px solid #d3d3d3;background-color: white;margin-right:auto;margin-left:auto;width: 900px;min-height:500px;text-align: center;'>" +
                        "" +
                        "    <div style='padding-top: 10px;font-size: 32px;'>" +
                        "        <h2 style=''>" + b.getTitle() + "</h2>" +
                        "    </div>" +
                        "    <div style='padding-top: 10px;font-size: 16px !important;margin-left: auto;margin-right: auto;width: 600px;'>" +
                        "       <div style='text-align: center;height: 48px;margin-bottom:10px;'>" +
                        "           <span style='font-size: 14px;line-height:48px;float:left;'>" + new SimpleDateFormat("yyyyMM").format(b.getShouluTime()) + "<span>作者：" + b.getAuthor() + "</span></span>" +
                        "           <div style='float:right;margin-left:5px;'>" +
                        "               <a href='####'><img style='width:100%;height:100%;width:45px;height:40px;' src='https://talkmovie.cn/img/qzone.png' id='qzone_share'></a>" +
                        "                            </div>" +
                        "           <div style='float:right;'>" +
                        "                <a href='####'><img style='width:100%;height:100%;width:45px;height:39px;' src='https://talkmovie.cn/img/weibo.png' id='weibo_share'></a>" +
                        "                            </div>" +
                        "           <div style='float:right;'>" +
                        "               <span style='font-size: 14px;line-height:48px;'>分享：</span>" +
                        "                  </div>" +
                        "        </div>" +
                        "        <textarea id='symptomTxt' style='width: 660px;font-size:10pt;line-height: 2em;overflow:hidden;'>" + b.getContent().replaceAll("<br/>", "\r\n") + "</textarea>" +
                        "        <div style='width: 200px;height: 300px;margin-left: auto;margin-right: auto;'> " +
                        "          <img id='articleImg' style='width:100%;height:100%;' src='https://talkmovie.cn/image" + b.getStorePath() + "'/></div>  " +
                        "        </div>" +
                        "        <div id='voteDiv' style='padding: 0px 0px 40px; text-align: center; margin: 0px;'>" +
                        "           <p style='margin:0px;padding:0px;padding-bottom:30px;color:rgb(157, 157, 157);font-size:14px;'></p>" +
                        "           <span style='padding:0px;line-height:0;margin:0px;'>" +
                        "             <a id='article_bad' href='####' style='color:rgb(235, 115, 80);text-decoration:none;-webkit-tap-highlight-color:rgba(0, 0, 0, 0);display:inline-block;background:url(&quot;https://talkmovie.cn/img/article_bad.png&quot;) no-repeat;overflow:hidden;text-indent:-999px;width:120px;height:120px;'></a></span>&nbsp;&nbsp;&nbsp;&nbsp; " +
                        "          <span style='padding:0px;line-height:0;margin:0px;'>" +
                        "            <a id='article_good' href='####' style='color:rgb(235, 115, 80);text-decoration:none;-webkit-tap-highlight-color:rgba(0, 0, 0, 0);display:inline-block;background:url(&quot;https://talkmovie.cn/img/article_good.png&quot;) no-repeat;overflow:hidden;text-indent:-999px;width:120px;height:120px;'></a></span>" +
                        "           <p style='margin:0px;padding:0px;color:rgb(128, 128, 128);text-decoration:none;background:url('https://talkmovie.cn/img/reward_line.png') center center no-repeat;margin-top:10px;font-size:15px;'>" +
                        "        不喜欢&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;喜欢" +
                        "           </p>" +
                        "       </div>" +
                        "       <div id='moneyDiv' style='padding: 0px 0px 40px; text-align: center; margin: 0px;display: none;'>" +
                        "           <p style='margin:0px;padding:0px;padding-bottom:16px;color:rgb(157, 157, 157);font-size:14px;'></p>" +
                        "           <p style='padding:0px;line-height:0;margin:0px;'>" +
                        "           <a id='money' href='####' style='color:rgb(235, 115, 80);text-decoration:none;-webkit-tap-highlight-color:rgba(0, 0, 0, 0);display:inline-block;background:url('https://talkmovie.cn/img/article_money.png') no-repeat;overflow:hidden;text-indent:-999px;width:80px;height:90px;'>赏</a>" +
                        "           </p>" +
                        "       </div>" +
                        "    </div>" +
                        "</div>" +
                        "" +
                        "<!-- Strat Footer-->" +
                        "<div class='footer ' >" +
                        "    <p class='footer_link ' >" +
                        "        <a href='https://talkmovie.cn/about.html' target='_blank' title='关于我们'  class=''>关于我们</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "        <a href='https://talkmovie.cn/contact.html' target='_blank' title='联系我们'  class=''>联系我们</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "        <a href='https://talkmovie.cn/' target='_blank' title='友情链接'  class=''>友情链接</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "        <a href='https://talkmovie.cn/declare.html' target='_blank' title='版权声明'  class=''>版权声明</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "    </p>" +
                        "" +
                        "    <p class='footer_copy ' >" +
                        "        " +
                        "        Copyright © 2017 -  香蕉皮影评网 <br  class=''>" +
                        "                京ICP备17045521号-1" +
                        "" +
                        "        " +
                        "    </p>" +
                        "</div>" +
                        "<!-- End Footer -->" +
                        "" +
                        "</div>" +
                        "</html>" +
                        "<script type='text/javascript' src='https://talkmovie.cn/js/jquery-1.7.2.min.js'></script>" +
                        "<script type='text/javascript' src='https://talkmovie.cn/js/common.js'></script>" +
                        "<script type='text/javascript'>" +
                        "uaredirect('http://wap.talkmovie.cn/" + short_url + "');" +
                        "function autoTextAreaHeight(o) {  " +
                        "   o.style.height = o.scrollTop + o.scrollHeight + 'px';" +
                        "}" +
                        "var ele = document.getElementById('symptomTxt');  " +
                        "autoTextAreaHeight(ele);  " +
                        "</script>";
        File d = new File(Constant.Webapp_Article + new SimpleDateFormat("yyyyMM").format(b.getTougaoTime()) + "/");
        if (!d.exists()) {
            d.mkdir();
        }
        File f = new File(Constant.Webapp_Article + short_url);
        if (f.exists()) {
            f.delete();
        }
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(f), "UTF-8"));
        bw.write(article);
        bw.flush();
        bw.close();


    }

    /**
     * @param us
     * @param europe
     * @param eastasia
     * @param china
     * @throws IOException
     */
    public static void createYingpingHtml(List<ArticleInfoBean> us, List<ArticleInfoBean> europe,
                                          List<ArticleInfoBean> eastasia, List<ArticleInfoBean> china) throws IOException {

        String us_str = "";
        String europe_str = "";
        String eastasia_str = "";
        String china_str = "";
        for (ArticleInfoBean b : us) {
            us_str = us_str +
                    "                            <li class='list_item ' data-id='4bmuc0qi971ebw5'  style='display: ;'>" +
                    "                                <a href='" + Constant.Article_root + new SimpleDateFormat("yyyyMM").format(b.getTougaoTime()) + "/" + b.getArticleID() + ".html' class='figure '  target='_blank'  >" +
                    "                                    <img class='figure_pic ' alt='" + b.getTitle() + "' onerror='picerr(this,'v')' src='" + Constant.Image_root + b.getStorePath() + "' style='visibility: visible;'  width='167' height='234'>" +
                    "                                    <div class='figure_count ' >" +
                    "                                    </div>" +
                    "                                    <div class='figure_score ' >" +
                    "                                        <!--<em class='score_l ' >7</em><em class='score_s ' >.1</em>-->" +
                    "                                    </div>  " +
                    "                                </a>" +
                    "                                <div class='figure_detail figure_detail_two_row ' >" +
                    "                                    <strong class='figure_title figure_title_two_row ' >" +
                    "                                        <a  title='" + b.getFilmName() + "'  >" + b.getFilmName() + "</a>" +
                    "                                    </strong>" +
                    "                                    <div class='figure_desc ' >" + b.getTitle() +
                    "                                    </div>" +
                    "                                </div>" +
                    "                            </li>";



        }
        for (ArticleInfoBean b : europe) {
            europe_str = europe_str +
                    "                            <li class='list_item ' data-id='4bmuc0qi971ebw5'  style='display: ;'>" +
                    "                                <a href='" + Constant.Article_root + new SimpleDateFormat("yyyyMM").format(b.getTougaoTime()) + "/" + b.getArticleID() + ".html' class='figure '  target='_blank'  >" +
                    "                                    <img class='figure_pic ' alt='" + b.getTitle() + "' onerror='picerr(this,'v')' src='" + Constant.Image_root + b.getStorePath() + "' style='visibility: visible;'  width='167' height='234'>" +
                    "                                    <div class='figure_count ' >" +
                    "                                    </div>" +
                    "                                    <div class='figure_score ' >" +
                    "                                        <!--<em class='score_l ' >7</em><em class='score_s ' >.1</em>-->" +
                    "                                    </div>  " +
                    "                                </a>" +
                    "                                <div class='figure_detail figure_detail_two_row ' >" +
                    "                                    <strong class='figure_title figure_title_two_row ' >" +
                    "                                        <a  title='" + b.getFilmName() + "'  >" + b.getFilmName() + "</a>" +
                    "                                    </strong>" +
                    "                                    <div class='figure_desc ' >" + b.getTitle() +
                    "                                    </div>" +
                    "                                </div>" +
                    "                            </li>";



        }

        for (ArticleInfoBean b : eastasia) {
            eastasia_str = eastasia_str +
                    "                            <li class='list_item ' data-id='4bmuc0qi971ebw5'  style='display: ;'>" +
                    "                                <a href='" + Constant.Article_root + new SimpleDateFormat("yyyyMM").format(b.getTougaoTime()) + "/" + b.getArticleID() + ".html' class='figure '  target='_blank'  >" +
                    "                                    <img class='figure_pic ' alt='" + b.getTitle() + "' onerror='picerr(this,'v')' src='" + Constant.Image_root + b.getStorePath() + "' style='visibility: visible;'  width='167' height='234'>" +
                    "                                    <div class='figure_count ' >" +
                    "                                    </div>" +
                    "                                    <div class='figure_score ' >" +
                    "                                        <!--<em class='score_l ' >7</em><em class='score_s ' >.1</em>-->" +
                    "                                    </div>  " +
                    "                                </a>" +
                    "                                <div class='figure_detail figure_detail_two_row ' >" +
                    "                                    <strong class='figure_title figure_title_two_row ' >" +
                    "                                        <a  title='" + b.getFilmName() + "'  >" + b.getFilmName() + "</a>" +
                    "                                    </strong>" +
                    "                                    <div class='figure_desc ' >" + b.getTitle() +
                    "                                    </div>" +
                    "                                </div>" +
                    "                            </li>";



        }

        for (ArticleInfoBean b : china) {
            china_str = china_str +
                    "                            <li class='list_item ' data-id='4bmuc0qi971ebw5'  style='display: ;'>" +
                    "                                <a href='" + Constant.Article_root + new SimpleDateFormat("yyyyMM").format(b.getTougaoTime()) + "/" + b.getArticleID() + ".html' class='figure '  target='_blank'  >" +
                    "                                    <img class='figure_pic ' alt='" + b.getTitle() + "' onerror='picerr(this,'v')' src='" + Constant.Image_root + b.getStorePath() + "' style='visibility: visible;'  width='167' height='234'>" +
                    "                                    <div class='figure_count ' >" +
                    "                                    </div>" +
                    "                                    <div class='figure_score ' >" +
                    "                                        <!--<em class='score_l ' >7</em><em class='score_s ' >.1</em>-->" +
                    "                                    </div>  " +
                    "                                </a>" +
                    "                                <div class='figure_detail figure_detail_two_row ' >" +
                    "                                    <strong class='figure_title figure_title_two_row ' >" +
                    "                                        <a  title='" + b.getFilmName() + "'  >" + b.getFilmName() + "</a>" +
                    "                                    </strong>" +
                    "                                    <div class='figure_desc ' >" + b.getTitle() +
                    "                                    </div>" +
                    "                                </div>" +
                    "                            </li>";



        }
        String s =
                "<!DOCTYPE html>" +
                        "<html lang='zh-cmn-Hans'>" +
                        "<head>" +
                        "<meta property='wb:webmaster' content='1f4e5a42764fd2eb' />" +
                        "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
                        "<meta name='keywords' content='香蕉皮影评网|电影评论|影视资讯|影评' >" +
                        "<meta name='description' content='香蕉皮影评网，看最好看的影评，影评人的聚集地。提供最新影评文章电影资讯， 致力于传播优秀电影文化,弘扬电影精神' >" +
                        "<meta name='viewport' content='width=1002' >" +
                        "<title  class=''>香蕉皮影评网 | 让影评飞起来</title>" +
                        "<link href='https://talkmovie.cn/css/common.css' rel='stylesheet' type='text/css'  class=''>" +
                        "<link href='https://talkmovie.cn/css/square.css' rel='stylesheet' type='text/css'  class=''>" +
                        "<link href='https://talkmovie.cn/css2/yingping.css' rel='stylesheet' type='text/css' class=''>" +
                        "" +
                        "" +
                        "<div style='text-align: center;width: 100%;margin: 0 auto;width: 1340px;'>" +
                        "" +
                        "" +
                        "<div class='divide-moviemenu' ></div>" +
                        "" +
                        "<!-- Start moviemenu -->" +
                        "<div class='moviemenu' style='width: 1340px;width: 100%;text-align: center;' >" +
                        "    <div class='content ' >" +
                        "        <h1 class='fl ' >" +
                        "            <a href='https://talkmovie.cn/' class='logo statisEle ' title='香蕉皮影评网'  >香蕉皮影评网 - Logo</a>" +
                        "        </h1>" +
                        "        <ul class='menus fl ' >" +
                        "            <li class='menu hoverhandler ' style='margin: 0px 6px;' >" +
                        "                <a href='https://talkmovie.cn/yingping.html' class='link statisEle ' statis-event='click' statis-value='SY_ZDH_mv' ><span class='fl ' style='*margin-top:-1px;' >影评</span><b class='fl '></b></a>" +
                        "                <ul class='dropdownmenu fillet ' >" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#us_area' class='fillet statisEle ' statis-event='click' statis-value='SY_ZDH_hl' >美国电影</a>" +
                        "                    </li>" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#europe_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_hy' >欧洲电影</a>" +
                        "                    </li>" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#eastasia_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_om' >日韩电影</a>" +
                        "                    </li>" +
                        "                    <li  class=''>" +
                        "                        <a href='https://talkmovie.cn/yingping.html#china_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_ry' >华语电影</a>" +
                        "                    </li>" +
                        "                </ul>" +
                        "            </li>" +
                        "" +
                        "            <li class='menu hoverhandler ' style='margin: 0px 6px;' >" +
                        "                <a href='https://talkmovie.cn/tougao.html' class='link statisEle ' statis-event='click' statis-value='SY_ZDH_ent' >" +
                        "                    <span class='fl ' >投稿</span>" +
                        "                </a>" +
                        "                " +
                        "            </li>" +
                        "        </ul>" +
                        "        " +
                        "        <div class='login panel ' >" +
                        "            <a  id='menu_login'  style='display: none;' class='loginBtn J_login statisEle ' href='https://talkmovie.cn/login.html' statis-event='click' statis-value='SY_ZDH_dl' >登录</a>" +
                        "            <a  id='menu_regist' style='display: none;' class='J_register statisEle ' href='https://talkmovie.cn/regist.html' statis-event='click' statis-value='SY_ZDH_zc' >注册</a>" +
                        "" +
                        "            <a id='menu_username' style='display: none;' class='loginBtn J_login statisEle ' href='javascript:void(0);' statis-event='click' statis-value='SY_ZDH_dl' ></a>" +
                        "            <a id='menu_quit' style='display: none;' class='J_register statisEle ' href='javascript:void(0);' statis-event='click' statis-value='SY_ZDH_zc' >退出</a>" +
                        "        </div>" +
                        "        " +
                        "        " +
                        "        <div class='search panel ' style='margin-right: 30px;' >" +
                        "            <div class='left_park_side ' ></div>" +
                        "            <div class='right_park_side ' ></div>" +
                        "            <form action='https://talkmovie.cn/talkmovie/article/search' target='_blank'  class=''>" +
                        "                <div class='searchbody statisEle ' statis-event='click' statis-value='ZDH_Search_start' >" +
                        "                    " +
                        "                    " +
                        "                        <input type='text' name='keyword' autocomplete='off' data-url='https://talkmovie.cn/' placeholder='搜索电影名'  class=''>" +
                        "                    " +
                        "                </div>" +
                        "                <button type='submit' class='statisEle logStatisEle ' statis-event='click' statis-value='ZDH_Search_button' >搜索</button>" +
                        "            </form>" +
                        "            <ul class='autocomplete autocompletehide ' ></ul>" +
                        "        </div>" +
                        "    </div>" +
                        "</div>" +
                        "<!-- End moviemenu -->" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "<div class='square_auto square_m' style='width: 1340px;width: 100%;text-align: center;' >" +
                        "" +
                        "    <div class='square_content ' >" +
                        "        <!-- Start 广告位 -->" +
                        "        <div class='focus_bottom_banner ' style='overflow: hidden; text-align: center;width:100%;display: ;' >" +
                        "        </div>" +
                        "        <!-- End 广告位 -->" +
                        "" + refreshHtml()+
                        "" +
                        "        <div class='mod_row_box ' id='us_area' data-pager='' data-pagectx='page=1' data-dfrom='suc' __expose='channel-movie' >" +
                        "            <div class='mod_hd ' >" +
                        "                <div class='mod_title ' >" +
                        "                    <h2 class='title ' >" +
                        "                      <a href='https://talkmovie.cn/yingping.html#us_area' class='title_pic_link ' target='' _stat='channel-movie:boxtitle' >" +
                        "                        <span class='title_pic ' ><img src='./img/movie.png' alt=''  class='' width='36' height='36'></span>" +
                        "                        美国电影</a>" +
                        "                      <span class='subtitle ' ></span>" +
                        "                    </h2>" +
                        "                    <div class='title_action ' >" +
                        "                        <ul class='mod_link_list ' >" +
                        "                            <li class='item ' >" +
                        "                                <!--<a href='' target='' class='link ' ><i class='icon_text ' ></i><i class='icon_sm icon_right_sm ' ><svg class='svg_icon ' viewBox='0 0 16 16' width='16' height='16' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#svg_icon_right_sm'  class=''></use></svg></i></a>-->" +
                        "                                <i class='icon_text link ' style='display:none'></i>" + "                            </li>" +
                        "                        " +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "            " +
                        "            <div class='mod_bd ' >" +
                        "                <div class='figure_scroll figure_scroll_v_default ' >" +
                        "                    <div class='mod_figure mod_figure_v mod_figure_v_default ' >" +
                        "                        <ul class='figure_list ' data-seqnum='q0dble31bp_1500196012998_x2bk' >" +
                        us_str +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "        </div>" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "        <div class='mod_row_box ' id='europe_area' data-pager='' data-pagectx='page=1' data-dfrom='suc' __expose='channel-movie' >" +
                        "            <div class='mod_hd ' >" +
                        "                <div class='mod_title ' >" +
                        "                    <h2 class='title ' >" +
                        "                      <a href='https://talkmovie.cn/yingping.html#europe_area' class='title_pic_link ' target='' _stat='channel-movie:boxtitle' >" +
                        "                        <span class='title_pic ' ><img src='./img/movie.png' alt=''  class='' width='36' height='36'></span>" +
                        "                        欧洲电影</a>" +
                        "                      <span class='subtitle ' ></span>" +
                        "                    </h2>" +
                        "                    <div class='title_action ' >" +
                        "                        <ul class='mod_link_list ' >" +
                        "                            <li class='item ' >" +
                        "                                <!--<a href='' target='' class='link ' ><i class='icon_text ' ></i><i class='icon_sm icon_right_sm ' ><svg class='svg_icon ' viewBox='0 0 16 16' width='16' height='16' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#svg_icon_right_sm'  class=''></use></svg></i></a>-->" +
                        "                                <i class='icon_text link '></i>" + "                            </li>" +
                        "                        " +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "            " +
                        "            <div class='mod_bd ' >" +
                        "                <div class='figure_scroll figure_scroll_v_default ' >" +
                        "                    <div class='mod_figure mod_figure_v mod_figure_v_default ' >" +
                        "                        <ul class='figure_list ' data-seqnum='q0dble31bp_1500196012998_x2bk' >" +
                        europe_str +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "        </div>" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "        " +
                        "" +
                        "" +
                        "        <div class='mod_row_box ' id='eastasia_area' data-pager='' data-pagectx='page=1' data-dfrom='suc' __expose='channel-movie' >" +
                        "            <div class='mod_hd ' >" +
                        "                <div class='mod_title ' >" +
                        "                    <h2 class='title ' >" +
                        "                      <a href='https://talkmovie.cn/yingping.html#eastasia_area' class='title_pic_link ' target='' _stat='channel-movie:boxtitle' >" +
                        "                        <span class='title_pic ' ><img src='./img/movie.png' alt=''  class='' width='36' height='36'></span>" +
                        "                        日韩电影</a>" +
                        "                      <span class='subtitle ' ></span>" +
                        "                    </h2>" +
                        "                    <div class='title_action ' >" +
                        "                        <ul class='mod_link_list ' >" +
                        "                            <li class='item ' >" +
                        "                                <!--<a href='' target='' class='link ' ><i class='icon_text ' ></i><i class='icon_sm icon_right_sm ' ><svg class='svg_icon ' viewBox='0 0 16 16' width='16' height='16' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#svg_icon_right_sm'  class=''></use></svg></i></a>-->" +
                        "                                <i class='icon_text link '></i>" + "                            </li>" +
                        "                        " +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "            " +
                        "            <div class='mod_bd ' >" +
                        "                <div class='figure_scroll figure_scroll_v_default ' >" +
                        "                    <div class='mod_figure mod_figure_v mod_figure_v_default ' >" +
                        "                        <ul class='figure_list ' data-seqnum='q0dble31bp_1500196012998_x2bk' >" +
                        eastasia_str +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "        </div>" +
                        "" +
                        "" +
                        "" +
                        "" +

                        "" +
                        "        <div class='mod_row_box ' id='china_area' data-pager='' data-pagectx='page=1' data-dfrom='suc' __expose='channel-movie' >" +
                        "            <div class='mod_hd ' >" +
                        "                <div class='mod_title ' >" +
                        "                    <h2 class='title ' >" +
                        "                      <a href='https://talkmovie.cn/yingping.html#china_area' class='title_pic_link ' target='' _stat='channel-movie:boxtitle' >" +
                        "                        <span class='title_pic ' ><img src='./img/movie.png' alt=''  class='' width='36' height='36'></span>" +
                        "                        华语电影</a>" +
                        "                      <span class='subtitle ' ></span>" +
                        "                    </h2>" +
                        "                    <div class='title_action ' >" +
                        "                        <ul class='mod_link_list ' >" +

                        "                            <li class='item ' >" +
                        "                                <!--<a href='' target='' class='link ' ><i class='icon_text ' ></i><i class='icon_sm icon_right_sm ' ><svg class='svg_icon ' viewBox='0 0 16 16' width='16' height='16' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#svg_icon_right_sm'  class=''></use></svg></i></a>-->" +
                        "                                <i class='icon_text link '></i>" +
                        "                            </li>" +
                        "                        " +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "            " +
                        "            <div class='mod_bd ' >" +
                        "                <div class='figure_scroll figure_scroll_v_default ' >" +
                        "                    <div class='mod_figure mod_figure_v mod_figure_v_default ' >" +
                        "                        <ul class='figure_list ' data-seqnum='q0dble31bp_1500196012998_x2bk' >" +
                        china_str +
                        "                        </ul>" +
                        "                    </div>" +
                        "                </div>" +
                        "            </div>" +
                        "        </div>" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "    </div>" +
                        "</div>" +
                        "" +
                        "" +
                        "" +

                        "<!--推广弹窗 start-->" +
                        "<div class='promotion-box ' style='display: block;' >" +
                        "    <i class='pclose ' ></i>" +
                        "    <a class='promotion-img ' href='https://talkmovie.cn' target='_blank' ></a>  " +
                        "</div>" +
                        "<!--推广弹窗 end-->" +
                        "" +
                        "" +
                        "" +
                        "<!-- Strat Footer-->" +
                        "<div class='footer ' >" +
                        "    <p class='footer_link ' >" +
                        "        <a href='https://talkmovie.cn/about.html' target='_blank' title='关于我们'  class=''>关于我们</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "        <a href='https://talkmovie.cn/contact.html' target='_blank' title='联系我们'  class=''>联系我们</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "        <a href='https://talkmovie.cn/' target='_blank' title='友情链接'  class=''>友情链接</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "        <a href='https://talkmovie.cn/declare.html' target='_blank' title='版权声明'  class=''>版权声明</a>" +
                        "        <span class='ico_line ' ></span>" +
                        "    </p>" +
                        "" +
                        "    <p class='footer_copy ' >" +
                        "        " +
                        "        Copyright © 2017 -  香蕉皮影评网 <br  class=''>" +
                        "                京ICP备17045521号-1" +
                        "" +
                        "        " +
                        "    </p>" +
                        "</div>" +
                        "<!-- End Footer -->" +
                        "" +
                        "</div>" +
                        "</html>" +
                        "<script type='text/javascript' src='https://talkmovie.cn/js/jquery-1.7.2.min.js'></script>" +
                        "<script type='text/javascript' src='https://talkmovie.cn/js/common.js'></script>" +
                        "<script type='text/javascript'>" +
                        "uaredirect('http://wap.talkmovie.cn/');" +
                        "</script>";

        File f = new File(Constant.Webapp + "yingping.html");
        if (f.exists()) {
            f.delete();
        }
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(f), "UTF-8"));
        bw.write(s);
        bw.flush();
        bw.close();

        File f2 = new File(Constant.Webapp + "index.html");
        if (f2.exists()) {
            f2.delete();
        }
        BufferedWriter bw2 = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(f2), "UTF-8"));
        bw2.write(s);
        bw2.flush();
        bw2.close();



    }

    public static String search(List<ArticleInfoBean> o, String msg) {
        ArticleInfoBean bean = null;
        StringBuffer temp = new StringBuffer("");
        try {
            if (msg.isEmpty()) {
                if (o.size() == 0) {
                    temp.append("<li><span>没有对应的搜索结果，请换个关键词</span></li>");
                } else {
                    for (ArticleInfoBean m : o) {
                        if (m != null) {
                            temp.append("<li><a href='https://talkmovie.cn/article/" +
                                    new SimpleDateFormat("yyyyMM").format(m.getTougaoTime()) + "/" + m.getArticleID() + ".html'>" +
                                    m.getTitle()
                                    + "</a></li>");
                        }
                    }
                }
            } else {
                temp.append("<li><span>" + msg + "</span></li");
            }

            return "<!DOCTYPE html>" +
                    "<html lang='zh-cmn-Hans'>" +
                    "<head>" +
                    "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
                    "<meta name='keywords' content='香蕉皮影评网' >" +
                    "<meta name='description' content='香蕉皮影评网，看最好看的影评，影评人的聚集地。提供最新影评文章电影资讯，以及用户注册登录投稿功能。 致力于传播优秀电影文化,弘扬电影精神' >" +
                    "<meta name='viewport' content='width=1002' >" +
                    "<title  class=''>香蕉皮影评网 影评人的社区</title>" +
                    "<link rel='icon' href='https://talkmovie.cn/favicon.ico' type='image/x-icon' />" +
                    " <link rel='shortcut icon' href='https://talkmovie.cn/favicon.ico' type='image/x-icon' />" +
                    "<link href='https://talkmovie.cn/css/common.css' rel='stylesheet' type='text/css'  class=''>" +
                    "<link href='https://talkmovie.cn/css/square.css' rel='stylesheet' type='text/css'  class=''>" +
                    "<link href='https://talkmovie.cn/css/style.css' rel='stylesheet' type='text/css'>" +
                    "<div style='text-align: center;width: 100%;margin: 0 auto;width: 1340px;'>" +
                    "" +
                    "" +
                    "<div class='divide-moviemenu' ></div>" +
                    "" +
                    "<!-- Start moviemenu -->" +
                    "<div class='moviemenu' style='width: 1340px;width: 100%;text-align: center;' >" +
                    "    <div class='content ' >" +
                    "        <h1 class='fl ' >" +
                    "            <a href='https://talkmovie.cn/' class='logo statisEle ' title='香蕉皮影评网'  >香蕉皮影评网 - Logo</a>" +
                    "        </h1>" +
                    "        <ul class='menus fl ' >" +
                    "            <li class='menu ' style='margin: 0px 6px;' >" +
                    "                <a href='https://talkmovie.cn/' class='link statisEle current ' statis-event='click' statis-value='SY_ZDH_index' >首页</a>" +
                    "            </li>" +
                    "            <li class='menu hoverhandler ' style='margin: 0px 6px;' >" +
                    "                <a href='https://talkmovie.cn/yingping.html' class='link statisEle ' statis-event='click' statis-value='SY_ZDH_mv' ><span class='fl ' style='*margin-top:-1px;' >影评</span><b class='fl '></b></a>" +
                    "                <ul class='dropdownmenu fillet ' >" +
                    "                    <li  class=''>" +
                    "                        <a href='https://talkmovie.cn/yingping.html#us_area' class='fillet statisEle ' statis-event='click' statis-value='SY_ZDH_hl' >美国电影</a>" +
                    "                    </li>" +
                    "                    <li  class=''>" +
                    "                        <a href='https://talkmovie.cn/yingping.html#europe_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_hy' >欧洲电影</a>" +
                    "                    </li>" +
                    "                    <li  class=''>" +
                    "                        <a href='https://talkmovie.cn/yingping.html#eastasia_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_om' >日韩电影</a>" +
                    "                    </li>" +
                    "                    <li  class=''>" +
                    "                        <a href='https://talkmovie.cn/yingping.html#china_area' class='statisEle ' statis-event='click' statis-value='SY_ZDH_ry' >华语电影</a>" +
                    "                    </li>" +
                    "                </ul>" +
                    "            </li>" +
                    "            <li class='menu hoverhandler ' style='margin: 0px 6px;'>" +
                    "                <a href='https://talkmovie.cn/tougao.html' class='link statisEle ' statis-event='click' statis-value='SY_ZDH_ent'>" +
                    "                    <span class='fl '>投稿</span>" +
                    "                </a>" +
                    "                " +
                    "            </li>" +
                    "        </ul>" +
                    "        " +
                    "        <div class='login panel ' >" +
                    "            <a  id='menu_login'  style='display: none;' class='loginBtn J_login statisEle ' href='login.html' statis-event='click' statis-value='SY_ZDH_dl' >登录</a>" +
                    "            <a  id='menu_regist' style='display: none;' class='J_register statisEle ' href='regist.html' statis-event='click' statis-value='SY_ZDH_zc' >注册</a>" +
                    "            <a id='menu_username' style='display: none;' class='loginBtn J_login statisEle ' href='javascript:void(0);' statis-event='click' statis-value='SY_ZDH_dl' ></a>" +
                    "            <a id='menu_quit' style='display: none;' class='J_register statisEle ' href='javascript:void(0);' statis-event='click' statis-value='SY_ZDH_zc' >退出</a>" +
                    "        </div>" +
                    "        <div class='search panel ' style='margin-right: 30px;' >" +
                    "            <div class='left_park_side ' ></div>" +
                    "            <div class='right_park_side ' ></div>" +
                    "            <div  target='_blank'  class=''>" +
                    "                <div class='searchbody statisEle ' statis-event='click' statis-value='ZDH_Search_start' >" +
                    "                    " +
                    "                    " +
                    "                        <input type='text' name='keyword' autocomplete='off' data-url='https://talkmovie.cn/' placeholder='搜索电影名'  class='' onkeypress='updateSearch(this,event)'>" +
                    "                    " +
                    "                </div>" +
                    "                <button type='submit' class='statisEle logStatisEle ' statis-event='click' statis-value='ZDH_Search_button' >搜索</button>" +
                    "            </div>" +
                    "            <ul class='autocomplete autocompletehide ' ></ul>" +
                    "        </div>" +
                    "    </div>" +
                    "</div>" +
                    "<!-- End moviemenu -->" +
                    "<div class='square_auto square_m' style='width: 1340px;width: 100%;height: 480px;' >" +
                    "" +
                    "<div style='padding-left: 500px;text-align: left;'>" +
                    "    <ul style='list-style: none;padding-top: 50px;font-size: 30px;'>" +
                    "       <li><span style='color:green;' >搜索如下</span></li>" + temp +
                    "       <li><a  style='color:green;' href='https://talkmovie.cn/tougao.html'>投稿</a></li>" +
                    "    </ul>" +
                    "</div>      " +
                    "" +
                    "" +
                    "</div>" +
                    "" +
                    "<!-- Strat Footer-->" +
                    "<div class='footer ' >" +
                    "    <p class='footer_link ' >" +
                    "        <a href='https://talkmovie.cn/about.html' target='_blank' title='关于我们'  class=''>关于我们</a>" +
                    "        <span class='ico_line ' ></span>" +
                    "        <a href='https://talkmovie.cn/contact.html' target='_blank' title='联系我们'  class=''>联系我们</a>" +
                    "        <span class='ico_line ' ></span>" +
                    "        <a href='https://talkmovie.cn/' target='_blank' title='友情链接'  class=''>友情链接</a>" +
                    "        <span class='ico_line ' ></span>" +
                    "        <a href='https://talkmovie.cn/declare.html' target='_blank' title='版权声明'  class=''>版权声明</a>" +
                    "        <span class='ico_line ' ></span>" +
                    "    </p>" +
                    "" +
                    "    <p class='footer_copy ' >" +
                    "        " +
                    "        Copyright © 2017 -  香蕉皮影评网 <br  class=''>" +
                    "        京ICP备17045521号" +
                    "        " +
                    "    </p>" +
                    "</div>" +
                    "<!-- End Footer -->" +
                    "" +
                    "</div>" +
                    "</html>" +
                    "<script type='text/javascript' src='https://talkmovie.cn/js/jquery-1.7.2.min.js'></script>" +
                    "<script type='text/javascript' src='https://talkmovie.cn/js/common.js'></script>";

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public static boolean createAllOldArticle(ArticleInfoBean[] articleInfoBeens) {
        if (articleInfoBeens == null || articleInfoBeens.length == 0){
            return true;
        }
        System.out.println("重新生成文章个数" + articleInfoBeens.length);
        for (ArticleInfoBean bean:
                articleInfoBeens) {
            try {
                createArticle(bean);
                System.out.println("重新生成文章" + bean.getFilmName());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static String refreshHtml() {
        try{
            URL url = new URL("https://v.qq.com/x/rank/");
            URLConnection conn = url.openConnection();
            Document doc = Jsoup.parse(conn.getInputStream(), "UTF-8","https://v.qq.com/x/rank/");
            Elements elements = doc.select("ol.hotlist").first().select("li");

            String insert = "<div class='mod_row_box ' style='margin-top: 25px;height:25px;' id='us_area' data-pager='' data-pagectx='page=1' data-dfrom='suc' __expose='channel-movie'>"+
                    ""+
                    "    <div class='mod_bd '>"+
                    "        <div class='figure_scroll figure_scroll_v_default '>"+
                    "            <div class='mod_figure mod_figure_v mod_figure_v_default '>"+
                    "                <ul class='figure_list ' data-seqnum='q0dble31bp_1500196012998_x2bk'>"+
                    "                    <li class='list_item ' data-id='4bmuc0qi971ebw5'>"+
                    "                        <div class='figure_detail figure_detail_two_row '><strong"+
                    "                                class='figure_title figure_title_two_row '> <a title='' >院线热门</a> </strong>"+
                    "                        </div>"+
                    "                    </li>";
            for (int i = 0; i < (elements.size() >= 6? 6:elements.size()); i++) {
                insert = insert.concat("                    <li class='list_item ' data-id='4bmuc0qi971ebw5'>"+
                        "                        <div class='figure_detail figure_detail_two_row '><strong"+
                        "                                class='figure_title figure_title_two_row '> <a title='' target='_blank' href="  + "https:" + elements.get(i).select("a").first().attr("href")+ ">" +elements.get(i).select("a").first().attr("title")+ "</a> </strong>"+
                        ""+
                        "                        </div>"+
                        "                    </li>");
            }
            insert = insert.concat(
                            "                </ul>"+
                            "            </div>"+
                            "        </div>"+
                            "    </div>"+
                            "</div>");
            System.out.println(insert);
            return insert;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        refreshHtml();
    }
}
