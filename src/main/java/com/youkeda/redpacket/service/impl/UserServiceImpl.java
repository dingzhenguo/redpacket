package com.youkeda.redpacket.service.impl;

import com.youkeda.redpacket.dao.UserDAO;
import com.youkeda.redpacket.dataobject.UserDO;
import com.youkeda.redpacket.model.User;
import com.youkeda.redpacket.params.UserInfoQueryParam;
import com.youkeda.redpacket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> query(UserInfoQueryParam userInfoQueryParam) {
        List<UserDO> userDOs= userDAO.selectByIds(userInfoQueryParam.getIdList());
        List<User> result = new ArrayList<>();
        for (UserDO userDO : userDOs) {
            result.add(userDO.convertToModel());
        }
        return result;
    }
}
