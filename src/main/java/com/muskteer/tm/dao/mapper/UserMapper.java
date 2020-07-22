package com.muskteer.tm.dao.mapper;

import com.muskteer.tm.common.bean.ArticleInfoBean;
import com.muskteer.tm.common.bean.UserInfoBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by wanglei on 2018/2/28.
 */
@Mapper
public interface UserMapper {

    @Select("select * from tm_user_info where userName=#{userName}")
    UserInfoBean queryUserByName(String userName);

    @Insert("insert into tm_user_info (userName, pwd, email, registTime) values (#{userName},\n" +
            "\t#{pwd}, #{email}, sysdate())")
    boolean registUser(UserInfoBean userInfoBean);

    @Update("update tm_user_info set tougaoCount = tougaoCount + 1  where userName = #{author}")
    void updateUserAfterTougao(String author);

    @Update("update tm_user_info set shouluCount = shouluCount + 1  where userName = #{author}")
    void updateUserAfterShoulu(ArticleInfoBean articleInfoBean);


}
