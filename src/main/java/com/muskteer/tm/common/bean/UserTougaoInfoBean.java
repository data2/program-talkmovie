package com.muskteer.tm.common.bean;

import java.util.Date;

/**
 * Created by wanglei on 2018/2/28.
 */
public class UserTougaoInfoBean {
    private String userName;
    private Date tougaoTime;
    private String articleID;

    public UserTougaoInfoBean(String userName, Date tougaoTime, String articleID) {
        this.userName = userName;
        this.tougaoTime = tougaoTime;
        this.articleID = articleID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTougaoTime() {
        return tougaoTime;
    }

    public void setTougaoTime(Date tougaoTime) {
        this.tougaoTime = tougaoTime;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }
}
