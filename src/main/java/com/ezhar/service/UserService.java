package com.ezhar.service;

import com.ezhar.domain.User;

/**
 * 用户服务接口
 */
public interface UserService {
    /*查询用户*/
    User checkUser(String  username, String password);


}
