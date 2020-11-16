package com.youkeda.redpacket.dao;

import com.youkeda.redpacket.dataobject.UserRedEnvelopeRecordDO;
import com.youkeda.redpacket.params.UserRedEnvelopeRecordQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRedEnvelopeRecordDAO {
    int deleteByPrimaryKey(String id);

    int insert(UserRedEnvelopeRecordDO record);

    UserRedEnvelopeRecordDO selectByPrimaryKey(String id);

    List<UserRedEnvelopeRecordDO> selectAll();

    List<UserRedEnvelopeRecordDO> select(UserRedEnvelopeRecordQueryParam param);

    int updateByPrimaryKey(UserRedEnvelopeRecordDO record);
}