package com.example.spring.jpa.demo.service.impl;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.repository.UserRepository;
import com.example.spring.jpa.demo.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 11:40
 * @Description: create interface Impl
 */
@Service
public class CreateUserServiceImpl implements CreateUserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 新增用户，并返回用户信息，拿到主键
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
