/**
 * FileName:   UserInfoBean.java
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
public class UserInfoBean {
    private String userName;
    private String nickName;
    private String sex;
    private int age;
    private String pwd;
    private int tougaoCount;
    private int shouluCount;
    private Date registTime;
    private String touxiangPath;
    private String email;
    private String area;
    private String TOKEN;

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String tOKEN) {
        TOKEN = tOKEN;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getTougaoCount() {
        return tougaoCount;
    }

    public void setTougaoCount(int tougaoCount) {
        this.tougaoCount = tougaoCount;
    }

    public int getShouluCount() {
        return shouluCount;
    }

    public void setShouluCount(int shouluCount) {
        this.shouluCount = shouluCount;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getTouxiangPath() {
        return touxiangPath;
    }

    public void setTouxiangPath(String touxiangPath) {
        this.touxiangPath = touxiangPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "UserInfoBean [userName=" + userName + ", nickName=" + nickName + ", sex=" + sex + ", age=" + age
                + ", pwd=" + pwd + ", tougaoCount=" + tougaoCount + ", shouluCount=" + shouluCount + ", registTime="
                + registTime + ", touxiangPath=" + touxiangPath + ", email=" + email + ", area=" + area + ", TOKEN="
                + TOKEN + "]";
    }


}
