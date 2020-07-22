package com.muskteer.tm.dao.mapper;

import com.muskteer.tm.common.bean.UserTougaoInfoBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wanglei on 2018/2/28.
 */
@Mapper
public interface UserTougaoMapper {

    @Insert("insert into tm_user_tougao_info (userName,tougaoTime,articleID) " +
            " values(#{userName},#{tougaoTime},#{articleID})")
    void insertUserTougaoInfoAfterShenhe(UserTougaoInfoBean userTougaoInfo);
}
