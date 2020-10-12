package com.xlzhou.service;

import com.xlzhou.model.User;

/**
 * @Auther: zxl
 * @Date: 2020/9/9 - 09 - 09 - 22:09
 */
public interface UserService {

    public User queryUserByName(String username);
}
