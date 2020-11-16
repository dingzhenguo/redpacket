package com.youkeda.redpacket.dao;

import com.youkeda.redpacket.dataobject.PreSingleRedEnvelopeDO;
import com.youkeda.redpacket.params.BatchAddPreSingleRedEnvelopeParam;
import com.youkeda.redpacket.params.PreSingleRedEnvelopeQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PreSingleRedEnvelopeDAO {

    int insert(PreSingleRedEnvelopeDO record);

    int batchAdd(@Param("preSingleRedEnvelopeDOList") List<PreSingleRedEnvelopeDO> preSingleRedEnvelopeDOS);

    PreSingleRedEnvelopeDO selectByPrimaryKey(String id);

    List<PreSingleRedEnvelopeDO> select(PreSingleRedEnvelopeQueryParam param);

    int updateByPrimaryKey(PreSingleRedEnvelopeDO record);
}