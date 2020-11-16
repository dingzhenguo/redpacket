package com.youkeda.redpacket.service.impl;

import com.youkeda.redpacket.dao.PreSingleRedEnvelopeDAO;
import com.youkeda.redpacket.dao.UserRedEnvelopeRecordDAO;
import com.youkeda.redpacket.dataobject.PreSingleRedEnvelopeDO;
import com.youkeda.redpacket.dataobject.UserRedEnvelopeRecordDO;
import com.youkeda.redpacket.model.UserRedEnvelopeRecord;
import com.youkeda.redpacket.params.UserRedEnvelopeRecordQueryParam;
import com.youkeda.redpacket.service.UserRedEnvelopeRecordService;
import com.youkeda.redpacket.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRedEnvelopeRecordServiceImpl implements UserRedEnvelopeRecordService {
    @Autowired
    private UserRedEnvelopeRecordDAO userRedEnvelopeRecordDAO;

    @Autowired
    private PreSingleRedEnvelopeDAO preSingleRedEnvelopeDAO;

    @Override
    public UserRedEnvelopeRecord save(UserRedEnvelopeRecord userRedEnvelopeRecord) {
        if (userRedEnvelopeRecord == null) {
            return null;
        }
        if (StringUtils.isEmpty(userRedEnvelopeRecord.getId())) {
            UserRedEnvelopeRecordDO userRedEnvelopeRecordDO = new UserRedEnvelopeRecordDO(userRedEnvelopeRecord);
            userRedEnvelopeRecordDO.setId(UUIDUtils.getUUID());
            int insertSize = userRedEnvelopeRecordDAO.insert(userRedEnvelopeRecordDO);
            if (insertSize < 1) {
                return null;
            }
            userRedEnvelopeRecord.setId(userRedEnvelopeRecordDO.getId());
        } else {
            UserRedEnvelopeRecordDO userRedEnvelopeRecordDO = new UserRedEnvelopeRecordDO(userRedEnvelopeRecord);
            int updateSize = userRedEnvelopeRecordDAO.updateByPrimaryKey(userRedEnvelopeRecordDO);
            if (updateSize < 1) {
                return null;
            }
        }
        return userRedEnvelopeRecord;
    }

    @Override
    public List<UserRedEnvelopeRecord> query(UserRedEnvelopeRecordQueryParam param) {
        List<UserRedEnvelopeRecord> userRedEnvelopeRecords = new ArrayList<>();
        List<UserRedEnvelopeRecordDO> userRedEnvelopeRecordDOS = userRedEnvelopeRecordDAO.select(param);
        if (CollectionUtils.isEmpty(userRedEnvelopeRecordDOS)) {
            return userRedEnvelopeRecords;
        }
        userRedEnvelopeRecordDOS.forEach(userRedEnvelopeRecordDO -> {
            UserRedEnvelopeRecord userRedEnvelopeRecord = userRedEnvelopeRecordDO.convertToModel();
            userRedEnvelopeRecords.add(userRedEnvelopeRecord);
        });
        return userRedEnvelopeRecords;
    }

    @Override
    public UserRedEnvelopeRecord get(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        UserRedEnvelopeRecordDO userRedEnvelopeRecordDO = userRedEnvelopeRecordDAO.selectByPrimaryKey(id);
        if (userRedEnvelopeRecordDO == null) {
            return null;
        }
        return userRedEnvelopeRecordDO.convertToModel();
    }
}
