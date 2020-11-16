package com.youkeda.redpacket.service;

import com.youkeda.redpacket.model.User;
import com.youkeda.redpacket.params.UserInfoQueryParam;

import java.util.List;

public interface UserService {
    List<User> query(UserInfoQueryParam userInfoQueryParam);
}
