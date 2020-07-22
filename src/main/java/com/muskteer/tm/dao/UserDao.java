/**
 * FileName:   UserDao.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.dao;


import com.muskteer.tm.common.bean.UserInfoBean;

/**
 * @author wanglei
 *
 */
public interface UserDao {
    public UserInfoBean queryUserByName(String username);

    public boolean registUser(UserInfoBean bean);

}
