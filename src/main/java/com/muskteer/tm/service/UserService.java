/**
 * FileName:   UserService.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MukteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.service;

import com.muskteer.tm.common.bean.UserInfoBean;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wanglei
 *
 */
public interface UserService {
    public String login(HttpServletResponse response, UserInfoBean bean);

    public String regist(UserInfoBean bean);

    public String forgotpwd();

    public String resetpwd();
}
