package com.muskteer.tm.common.bean;

public class Film {
    private long filmNum;//序号
    private String filmName;//中文名
    private String enFilmName;//英文名
    private String filmType;//类型
    private String filmLength;//长度 分钟
    private String area;//地区
    private String doubanScore;//评分
    private String doubanTag;//标签
    private String releaseType;//规格：院线 网络
    private String director;//导演
    private String actor;//演员
    private String year;//年代
    private String releaseTime;//上映时间
    private String filmCompany;//出品公司

    private String filmDesc;//一句话简述
    private String filmIntroduce;//基本信息
    private String filmStory;//剧情
    private String filmComment;//评论

    private String imageUrl;//海报
    private String videoUrl;//观看地址

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDoubanScore() {
        return doubanScore;
    }

    public void setDoubanScore(String doubanScore) {
        this.doubanScore = doubanScore;
    }

    public String getDoubanTag() {
        return doubanTag;
    }

    public void setDoubanTag(String doubanTag) {
        this.doubanTag = doubanTag;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public long getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(long filmNum) {
        this.filmNum = filmNum;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getEnFilmName() {
        return enFilmName;
    }

    public void setEnFilmName(String enFilmName) {
        this.enFilmName = enFilmName;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getFilmCompany() {
        return filmCompany;
    }

    public void setFilmCompany(String filmCompany) {
        this.filmCompany = filmCompany;
    }

    public String getFilmDesc() {
        return filmDesc;
    }

    public void setFilmDesc(String filmDesc) {
        this.filmDesc = filmDesc;
    }

    public String getFilmIntroduce() {
        return filmIntroduce;
    }

    public void setFilmIntroduce(String filmIntroduce) {
        this.filmIntroduce = filmIntroduce;
    }

    public String getFilmStory() {
        return filmStory;
    }

    public void setFilmStory(String filmStory) {
        this.filmStory = filmStory;
    }

    public String getFilmComment() {
        return filmComment;
    }

    public void setFilmComment(String filmComment) {
        this.filmComment = filmComment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmNum=" + filmNum +
                ", filmName='" + filmName + '\'' +
                ", enFilmName='" + enFilmName + '\'' +
                ", filmType='" + filmType + '\'' +
                ", filmLength='" + filmLength + '\'' +
                ", area='" + area + '\'' +
                ", doubanScore='" + doubanScore + '\'' +
                ", doubanTag='" + doubanTag + '\'' +
                ", releaseType='" + releaseType + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", year='" + year + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", filmCompany='" + filmCompany + '\'' +
                ", filmDesc='" + filmDesc + '\'' +
                ", filmIntroduce='" + filmIntroduce + '\'' +
                ", filmStory='" + filmStory + '\'' +
                ", filmComment='" + filmComment + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
