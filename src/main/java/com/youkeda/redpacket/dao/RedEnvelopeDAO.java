package com.youkeda.redpacket.dao;

import com.youkeda.redpacket.dataobject.RedEnvelopeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RedEnvelopeDAO {

    int insert(RedEnvelopeDO record);

    RedEnvelopeDO selectByPrimaryKey(String id);

    int updateByPrimaryKey(RedEnvelopeDO record);
}