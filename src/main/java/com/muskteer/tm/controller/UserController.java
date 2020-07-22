package com.muskteer.tm.controller;

import com.muskteer.tm.common.bean.UserInfoBean;
import com.muskteer.tm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 */
@RestController
@RequestMapping({"/user/*"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletResponse response, UserInfoBean bean) {
        return userService.login(response, bean);
    }

    @RequestMapping(value = "/regist")
    @ResponseBody
    public String regist(UserInfoBean bean) {
        return userService.regist(bean);
    }

    @RequestMapping(value = "/forgotpwd")
    @ResponseBody
    public String forgotpwd() {
        return userService.forgotpwd();
    }

    @RequestMapping(value = "/resetpwd")
    @ResponseBody
    public String resetpwd() {
        return userService.resetpwd();
    }

}
