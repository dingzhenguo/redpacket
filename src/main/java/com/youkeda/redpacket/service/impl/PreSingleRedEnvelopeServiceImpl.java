package com.youkeda.redpacket.service.impl;

import com.youkeda.redpacket.dao.PreSingleRedEnvelopeDAO;
import com.youkeda.redpacket.dataobject.PreSingleRedEnvelopeDO;
import com.youkeda.redpacket.model.PreSingleRedEnvelope;
import com.youkeda.redpacket.model.RedEnvelope;
import com.youkeda.redpacket.params.BatchAddPreSingleRedEnvelopeParam;
import com.youkeda.redpacket.params.PreSingleRedEnvelopeQueryParam;
import com.youkeda.redpacket.service.PreSingleRedEnvelopeService;
import com.youkeda.redpacket.service.RedEnvelopeService;
import com.youkeda.redpacket.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PreSingleRedEnvelopeServiceImpl implements PreSingleRedEnvelopeService {
    @Autowired
    private PreSingleRedEnvelopeDAO preSingleRedEnvelopeDAO;
    @Autowired
    private RedEnvelopeService redEnvelopeService;

    @Override
    public PreSingleRedEnvelope save(PreSingleRedEnvelope preSingleRedEnvelope) {
        if (preSingleRedEnvelope == null) {
            return null;
        }
        if (StringUtils.isEmpty(preSingleRedEnvelope.getId())) {
            PreSingleRedEnvelopeDO preSingleRedEnvelopeDO = new PreSingleRedEnvelopeDO(preSingleRedEnvelope);
            preSingleRedEnvelopeDO.setId(UUIDUtils.getUUID());
            int insertSize = preSingleRedEnvelopeDAO.insert(preSingleRedEnvelopeDO);
            if (insertSize < 1) {
                return null;
            }
            preSingleRedEnvelope.setId(preSingleRedEnvelopeDO.getId());
        } else {
            PreSingleRedEnvelopeDO preSingleRedEnvelopeDO = new PreSingleRedEnvelopeDO(preSingleRedEnvelope);
            int updateSize = preSingleRedEnvelopeDAO.updateByPrimaryKey(preSingleRedEnvelopeDO);
            if (updateSize < 1) {
                return null;
            }
        }
        return preSingleRedEnvelope;
    }

    @Override
    public List<PreSingleRedEnvelope> batchAdd(BatchAddPreSingleRedEnvelopeParam batchAddPreSingleRedEnvelopeParam) {
        List<PreSingleRedEnvelope> redEnvelopeList = batchAddPreSingleRedEnvelopeParam.getPreSingleRedEnvelopeList();
        if (CollectionUtils.isEmpty(batchAddPreSingleRedEnvelopeParam.getPreSingleRedEnvelopeList())) {
            return redEnvelopeList;
        }
        List<PreSingleRedEnvelopeDO> preSingleRedEnvelopeDOS = new ArrayList<>();
        for (PreSingleRedEnvelope preSingleRedEnvelope : batchAddPreSingleRedEnvelopeParam
            .getPreSingleRedEnvelopeList()) {
            PreSingleRedEnvelopeDO preSingleRedEnvelopeDO = new PreSingleRedEnvelopeDO(preSingleRedEnvelope);
            preSingleRedEnvelopeDO.setId(UUIDUtils.getUUID());
            preSingleRedEnvelopeDOS.add(preSingleRedEnvelopeDO);
        }
        int batchAddSize = preSingleRedEnvelopeDAO.batchAdd(preSingleRedEnvelopeDOS);
        if (batchAddSize != batchAddPreSingleRedEnvelopeParam.getPreSingleRedEnvelopeList().size()) {
            return redEnvelopeList;
        }
        return redEnvelopeList;
    }

    @Override
    public List<PreSingleRedEnvelope> query(PreSingleRedEnvelopeQueryParam param) {
        List<PreSingleRedEnvelope> preSingleRedEnvelopeList = new ArrayList<>();
        if (StringUtils.isEmpty(param.getId()) && StringUtils.isEmpty(param.getRedEnvelopeId())) {
            return preSingleRedEnvelopeList;
        }
        List<PreSingleRedEnvelopeDO> preSingleRedEnvelopeDOS = preSingleRedEnvelopeDAO.select(param);
        if (CollectionUtils.isEmpty(preSingleRedEnvelopeDOS)) {
            return preSingleRedEnvelopeList;
        }
        RedEnvelope redEnvelope = null;
        if (param.isNeedRedEnvelope()) {
            redEnvelope = redEnvelopeService.get(param.getRedEnvelopeId());
        }
        for (PreSingleRedEnvelopeDO preSingleRedEnvelopeDO : preSingleRedEnvelopeDOS) {
            PreSingleRedEnvelope preSingleRedEnvelope = preSingleRedEnvelopeDO.convertToModel();
            preSingleRedEnvelope.setRedEnvelope(redEnvelope);
            preSingleRedEnvelopeList.add(preSingleRedEnvelope);
        }
        return preSingleRedEnvelopeList;
    }
}
