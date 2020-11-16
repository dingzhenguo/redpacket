package com.youkeda.redpacket.dao;

import com.youkeda.redpacket.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    int insert(UserDO userDO);

    List<UserDO> selectByIds(@Param("ids") List<String> ids);
}
