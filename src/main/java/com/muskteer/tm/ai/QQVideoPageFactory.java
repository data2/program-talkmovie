package com.muskteer.tm.ai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muskteer.tm.common.bean.Film;
import com.muskteer.tm.common.util.StringUtil;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static com.muskteer.tm.ai.BaiduBaikeFactory.baikeFilm;

public class QQVideoPageFactory {

    static final String url = "https://v.qq.com/x/list/movie";

    public static void main(String[] args) throws IOException {
        File dir = new File(".");
        String[] files = dir.list();
//        String[]  files = new String[]{"film.2018-2000.new"};
        for(String fileN : files){
            if (!fileN.contains("film.")){
                continue;
            }
            BufferedReader br = new BufferedReader(new FileReader(new File(fileN)));
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(new File(fileN.replace(".new","")),true)));
            String line;
            Film f = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                try {
                    f = JSONObject.parseObject(line, Film.class);
                    getSource(f);
                    System.out.println(i++);
                    bw.write(JSON.toJSONString(f));
                    bw.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (f != null){
                        bw.write(JSON.toJSONString(f));
                        bw.newLine();
                    }
                }
            }
            br.close();
            bw.flush();
            bw.close();
        }


    }

    public static void getSource(Film film) throws IOException {

        URL urlObj = new URL(film.getVideoUrl());
        URLConnection conn = urlObj.openConnection();
        // 将HTML内容解析成UTF-8格式
        Document doc = Jsoup.parse(conn.getInputStream(), "utf-8", film.getVideoUrl());
        try {
            Element root = doc.select("span.video_score").first();
            film.setDoubanScore(root.child(0).text().concat(root.child(1).text()));
            film.setEnFilmName(doc.select("h1.video_title").first().child(0).text());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Element root = doc.select("div.video_tags").first();
        if (root == null){return;}
        Elements douban = root.select("span.tag_item_douban");
        if (douban.size() >=1 ){
            film.setDoubanTag(douban.first().select("span.icon_text").text());
        }

        String area = root.select("a").first().text();
        film.setArea(!NumberUtils.isNumber(area) ? area : null);
        String year = root.select("a").first().nextElementSibling().text();
        film.setYear(NumberUtils.isNumber(year)? year: null);
        if (StringUtil.isEmpty(film.getFilmType())) {
            film.setFilmType(root.select("a").first().nextElementSibling().nextElementSibling().text());
        }
        if (root.text().contains("院线")){
            film.setReleaseType("院线");
        }else if (root.text().contains("网络")){
            film.setReleaseType("网络");
        }else{
            film.setReleaseType("其他");
        }
        if (StringUtil.isEmpty(film.getFilmIntroduce())) {
            film.setFilmIntroduce(doc.select("div.video_summary").first().select("p.summary").first().text());
        }
    }
}
