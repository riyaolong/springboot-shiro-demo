package com.xlzhou.service.impl;

import com.xlzhou.mapper.UserMapper;
import com.xlzhou.model.User;
import com.xlzhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zxl
 * @Date: 2020/9/9 - 09 - 09 - 22:10
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
