package com.muskteer.tm.ai;

import com.alibaba.fastjson.JSONObject;
import com.muskteer.tm.common.bean.Film;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class QQVideoFactory {

    static final String url = "https://v.qq.com/x/list/movie";

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 58; i++) {
                getSource("film.1979",4338, i * 30);
                Thread.currentThread().sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getSource(String fileName, int year, int sortKey) throws IOException {

        URL urlObj = new URL(url + "?iyear=" + year + "&offset=" + sortKey);
        URLConnection conn = urlObj.openConnection();
        // 将HTML内容解析成UTF-8格式
        Document doc = Jsoup.parse(conn.getInputStream(), "utf-8", url + "?iyear=" + year + "&offset=" + sortKey);
        Elements elements = doc.select("li.list_item");
        Element element;
        Film film;

        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
        System.out.println(elements.size());
        for (int i = 0; i < elements.size(); i++) {
            element = elements.get(i);
            film = new Film();
            film.setImageUrl("https:" + element.select("a.figure").first().select("img").first().attr("r-lazyload"));//imgUrl
            film.setFilmName(element.select("a.figure").first().select("img").first().attr("alt"));//filmName
            film.setFilmDesc(element.select("span.figure_info").text());//filmDesc
            film.setVideoUrl(element.select("div.figure_title_score").select("a").first().attr("href"));//videoUrl
            film.setActor(element.select("div.figure_desc").select("a").text());
            System.out.println(JSONObject.toJSONString(film));
            bw.write(JSONObject.toJSONString(film));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        return null;
    }
}
