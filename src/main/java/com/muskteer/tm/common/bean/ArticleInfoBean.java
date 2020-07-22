/**
 * FileName:   ArticleBean.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.common.bean;

import java.util.Date;

/**
 * @author wanglei
 *
 */
public class ArticleInfoBean {
    private String articleID;
    private String title;
    private String author;
    private String content;
    private Date tougaoTime;
    private Date shouluTime;
    private String filmName;
    private String filmArea;
    private String goodScore;
    private String badScore;
    private int readAmount = 0;
    private String storePath;
    private String isShoulu;
    private String TOKEN;

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String tOKEN) {
        TOKEN = tOKEN;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTougaoTime() {
        return tougaoTime;
    }

    public void setTougaoTime(Date tougaoTime) {
        this.tougaoTime = tougaoTime;
    }

    public Date getShouluTime() {
        return shouluTime;
    }

    public void setShouluTime(Date shouluTime) {
        this.shouluTime = shouluTime;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmArea() {
        return filmArea;
    }

    public void setFilmArea(String filmArea) {
        this.filmArea = filmArea;
    }

    public int getReadAmount() {
        return readAmount;
    }

    public void setReadAmount(int read) {
        this.readAmount = read;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getIsShoulu() {
        return isShoulu;
    }

    public void setIsShoulu(String isShoulu) {
        this.isShoulu = isShoulu;
    }

    public String getGoodScore() {
        return goodScore;
    }

    public void setGoodScore(String goodScore) {
        this.goodScore = goodScore;
    }

    public String getBadScore() {
        return badScore;
    }

    public void setBadScore(String badScore) {
        this.badScore = badScore;
    }

    @Override
    public String toString() {
        return "ArticleInfoBean [articleID=" + articleID + ", title=" + title + ", author=" + author + ", content="
                + content + ", tougaoTime=" + tougaoTime + ", shouluTime=" + shouluTime + ", filmName=" + filmName
                + ", filmArea=" + filmArea + ", goodScore=" + goodScore + ", badScore=" + badScore + ", readAmount="
                + readAmount + ", storePath=" + storePath + ", isShoulu=" + isShoulu + ", TOKEN=" + TOKEN + "]";
    }


}
