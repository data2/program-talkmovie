package com.muskteer.tm.ai;

import com.alibaba.fastjson.JSONObject;
import com.muskteer.tm.common.bean.Film;
import com.muskteer.tm.common.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class BaiduBaikeFactory {
    final static String BASEURL = "https://baike.baidu.com/item/";

    public static void main(String[] args) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1979.new"),true)));
//            BufferedWriter bw9 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1989.new"), true)));
//            BufferedWriter bw8 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1988.new"), true)));
//            BufferedWriter bw7 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1987.new"), true)));
//            BufferedWriter bw6 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1986.new"), true)));
//            BufferedWriter bw5 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1985.new"), true)));
//            BufferedWriter bw4 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1984.new"), true)));
//            BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1983.new"), true)));
//            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1982.new"), true)));
//            BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1981.new"), true)));
//            BufferedWriter bw0 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("film.1980.new"), true)));

            String[] files = {"film.1979"};
            for(String fileN : files){
                BufferedReader br = new BufferedReader(new FileReader(new File(fileN)));
                String line;
                Film f = null;
                int i = 0;
                while ((line = br.readLine()) != null) {
                    try {
                        System.out.println(i++);
                        f = JSONObject.parseObject(line, Film.class);
                        baikeFilm(f);
                        System.out.println(f.getFilmName() + ","  +f.getYear());
//                        if (StringUtil.isNotEmpty(f.getYear())) {
//                            if (f.getYear().contains("1980")) {
//                                bw0.write(JSONObject.toJSONString(f));
//                                bw0.newLine();
//                            } else if (f.getYear().contains("1981")) {
//                                bw1.write(JSONObject.toJSONString(f));
//                                bw1.newLine();
//                            } else if (f.getYear().contains("1982")) {
//                                bw2.write(JSONObject.toJSONString(f));
//                                bw2.newLine();
//                            } else if (f.getYear().contains("1983")) {
//                                bw3.write(JSONObject.toJSONString(f));
//                                bw3.newLine();
//                            } else if (f.getYear().contains("1984")) {
//                                bw4.write(JSONObject.toJSONString(f));
//                                bw4.newLine();
//                            } else if (f.getYear().contains("1985")) {
//                                bw5.write(JSONObject.toJSONString(f));
//                                bw5.newLine();
//                            } else if (f.getYear().contains("1986")) {
//                                bw6.write(JSONObject.toJSONString(f));
//                                bw6.newLine();
//                            } else if (f.getYear().contains("1987")) {
//                                bw7.write(JSONObject.toJSONString(f));
//                                bw7.newLine();
//                            } else if (f.getYear().contains("1988")) {
//                                bw8.write(JSONObject.toJSONString(f));
//                                bw8.newLine();
//                            } else if (f.getYear().contains("1989")) {
//                                bw9.write(JSONObject.toJSONString(f));
//                                bw9.newLine();
//                            } else {
//                                bw.write(JSONObject.toJSONString(f));
//                                bw.newLine();
//                            }
//                        }else {
                            bw.write(JSONObject.toJSONString(f));
                            bw.newLine();
//                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        if (f != null){
                            bw.write(JSONObject.toJSONString(f));
                            bw.newLine();
                        }

                    }
                }
                br.close();
            }

            bw.flush();
            bw.close();
//            bw0.flush();
//            bw0.close();
//            bw1.flush();
//            bw1.close();
//            bw2.flush();
//            bw2.close();
//            bw3.flush();
//            bw3.close();
//            bw4.flush();
//            bw4.close();
//            bw5.flush();
//            bw5.close();
//            bw6.flush();
//            bw6.close();
//            bw7.flush();
//            bw7.close();
//            bw8.flush();
//            bw8.close();
//            bw9.flush();
//            bw9.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getContent()函数主要实现爬取输入文字的百度百科的介绍部分
    public static String getContent(String keyword) throws IOException {
        // 利用URL解析网址
        URL urlObj = null;
        urlObj = new URL(BASEURL + keyword);

        // URL连接
        URLConnection urlCon = null;
        urlCon = urlObj.openConnection(); // 打开URL连接
        // 将HTML内容解析成UTF-8格式
        Document doc = Jsoup.parse(urlCon.getInputStream(), "utf-8", BASEURL + keyword);
        // 刷选需要的网页内容
        String contentText = doc.select("div.lemma-summary").first().text();
        // 利用正则表达式去掉字符串中的"[数字]"
        contentText = contentText.replaceAll("\\[\\d+\\]", "");
        return contentText;

    }

    public static Film baikeFilm(Film film) throws IOException {
        if (film == null || StringUtil.isEmpty(film.getFilmName())) {
            return film;
        }
        URL urlObj = new URL(BASEURL + film.getFilmName());
        URLConnection urlCon = urlObj.openConnection(); // 打开URL连接
        Document doc = Jsoup.parse(urlCon.getInputStream(), "utf-8", BASEURL + film.getFilmName());

        Elements elements = doc.select("dl.basicInfo-block").select("dt");
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).text().trim().replaceAll("\\s", "").equals("中文名")) {
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("外文名")) {
                film.setEnFilmName(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("出品公司")) {
                film.setFilmCompany(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("导演")) {
                film.setDirector(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("类型")) {
                film.setFilmType(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("主演")) {
                film.setActor(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("片长")) {
                film.setFilmLength(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
            } else if (elements.get(i).text().trim().replaceAll("\\s", "").equals("上映时间")) {
                film.setReleaseTime(elements.get(i).nextElementSibling().text().replaceAll("\\[\\d+\\]", ""));
                film.setYear(film.getReleaseTime() == null ?"":film.getReleaseTime().substring(0,4));
            }
        }


        // 刷选需要的网页内容
        Elements elemes = doc.select("div.lemma-summary");
        if (elemes.size() > 1 && elemes.first().hasText()) {
            String contentText = doc.select("div.lemma-summary").first().text();
            // 利用正则表达式去掉字符串中的"[数字]"
            contentText = contentText.replaceAll("\\[\\d+\\]", "");
            film.setFilmIntroduce(contentText);
        }

        Elements elements1 = doc.select("div.para-title");
        for (int i = 0; i < elements1.size(); i++) {
            if (elements1.get(i).hasClass("level-2")
                    && elements1.get(i).hasText()
                    && elements1.get(i).text().contains("剧情简介")) {
                String s = "";
                Element element = elements1.get(i).nextElementSibling();
                if (element == null ){
                    break;
                }
                while (element.hasClass("para")) {
                    s = s + element.text();
                    element = element.nextElementSibling();
                }
                film.setFilmStory(s.replaceAll("\\[\\d+\\]", ""));
                break;
            }
        }

        Elements elements2 = doc.select("div.para-title");
        for (int i = 0; i < elements2.size(); i++) {
            if (elements2.get(i).hasClass("level-2")
                    && elements2.get(i).hasText()
                    && elements2.get(i).text().contains("影片评价")) {
                String s = "";
                Element element = elements2.get(i).nextElementSibling();

                while (element.hasClass("para")) {
                    s = s + element.text();
                    element = element.nextElementSibling();
                }
                film.setFilmComment(s.replaceAll("\\[\\d+\\]", ""));
                break;
            }
        }

        return film;

    }
}