package com.muskteer.tm.dao.mapper.test;

import com.muskteer.tm.dao.mapper.ArticleMapper;
import com.muskteer.tm.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wanglei on 2018/2/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Test
    public void test() {
//        System.out.println(userMapper == null);
//        UserInfoBean b = new UserInfoBean();
//        b.setUserName("test");
//        b.setPwd("test");
//        b.setEmail("139824@126.com");
//        userMapper.registUser(b);
        Date t = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(t);
        c.set(Calendar.HOUR, -2);
        System.out.println(articleMapper.queryArticleByDate(c.getTime()));
    }
}
