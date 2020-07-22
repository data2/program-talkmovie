/**
 * FileName:   UserDaoImpl.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.dao.impl;


import com.muskteer.tm.common.bean.UserInfoBean;
import com.muskteer.tm.dao.UserDao;
import com.muskteer.tm.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    public UserInfoBean queryUserByName(String username) {
        try {
            return userMapper.queryUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean registUser(UserInfoBean bean) {
        try {
            return userMapper.registUser(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
