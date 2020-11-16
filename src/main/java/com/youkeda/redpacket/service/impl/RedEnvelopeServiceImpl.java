package com.youkeda.redpacket.service.impl;

import com.youkeda.redpacket.dao.RedEnvelopeDAO;
import com.youkeda.redpacket.dataobject.RedEnvelopeDO;
import com.youkeda.redpacket.model.RedEnvelope;
import com.youkeda.redpacket.service.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RedEnvelopeServiceImpl implements RedEnvelopeService {
    @Autowired
    private RedEnvelopeDAO redEnvelopeDAO;

    @Override
    public RedEnvelope save(@RequestBody RedEnvelope redEnvelope) {
        if (redEnvelope == null) {
            return null;
        }
        if (StringUtils.isEmpty(redEnvelope.getId())) {
            RedEnvelopeDO redEnvelopeDO = new RedEnvelopeDO(redEnvelope);
            int insertSize = redEnvelopeDAO.insert(redEnvelopeDO);
            if (insertSize < 1) {
                return null;
            }
            redEnvelope.setId(redEnvelopeDO.getId());
        } else {
            RedEnvelopeDO redEnvelopeDO = new RedEnvelopeDO(redEnvelope);
            int updateSize = redEnvelopeDAO.updateByPrimaryKey(redEnvelopeDO);
            if (updateSize < 1) {
                return null;
            }
        }
        return redEnvelope;
    }

    @Override
    public RedEnvelope get(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        RedEnvelopeDO redEnvelopeDO = redEnvelopeDAO.selectByPrimaryKey(id);
        if (redEnvelopeDO == null) {
            return null;
        }
        RedEnvelope redEnvelope = redEnvelopeDO.convertToModel();
        return redEnvelope;
    }

}
