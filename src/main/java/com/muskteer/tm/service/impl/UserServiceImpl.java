/**
 * FileName:   UserServiceImpl.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muskteer.tm.common.bean.UserInfoBean;
import com.muskteer.tm.common.util.MD5Util;
import com.muskteer.tm.common.util.StringUtil;
import com.muskteer.tm.dao.UserDao;
import com.muskteer.tm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String login(HttpServletResponse response, UserInfoBean bean) {
        String ajaxResult = "fail";
        String errmsg = "";
        UserInfoBean queryBean = null;
        String tokenValue = null;
        try {
            queryBean = userDao.queryUserByName(bean.getUserName());
            if (queryBean != null && queryBean.getPwd().equals(bean.getPwd())) {
                tokenValue = bean.getUserName()
                        + "|"
                        + MD5Util.MD5encript(bean.getPwd() == null ? "" : bean.getPwd())
                        + "|"
                        + new SimpleDateFormat("yyyyMMddhhmmss")
                        .format(new Date());
                ajaxResult = "success";
            } else {
                ajaxResult = "wrong";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("errmsg", errmsg);
        map.put("status", ajaxResult);
        map.put("reqParams", bean.toString());
        map.put("token", tokenValue);

        return "judgeLogin(" + JSON.toJSONString(map)
                + ")";
    }

    @Override
    public String regist(UserInfoBean bean) {
        String ajaxResult = "fail";
        String errmsg = "";
        try {
            if (userDao.queryUserByName(bean.getUserName()) != null) {
                ajaxResult = "exist";
            } else {
                if (!StringUtil.isAllNotEmpty(bean.getUserName(),
                        bean.getPwd(), bean.getEmail())) {
                    errmsg = "regist info null";
                } else {
                    if (userDao.registUser(bean)) {
                        ajaxResult = "success";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("errmsg", errmsg);
        map.put("status", ajaxResult);
        map.put("reqParams", bean.toString());
        return "judgeRegist(" + JSON.toJSONString(map)
                + ")";

    }

    @Override
    public String forgotpwd() {
        return null;
    }

    @Override
    public String resetpwd() {
        return null;
    }


}
