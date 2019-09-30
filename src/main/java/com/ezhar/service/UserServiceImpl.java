package com.ezhar.service;

import com.ezhar.dao.UserRepository;
import com.ezhar.domain.User;
import com.ezhar.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user =userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));


        return user;
    }

}
