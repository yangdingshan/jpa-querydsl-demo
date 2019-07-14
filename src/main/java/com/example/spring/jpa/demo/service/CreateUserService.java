package com.example.spring.jpa.demo.service;

import com.example.spring.jpa.demo.model.User;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 11:40
 * @Description: 增加接口
 */
public interface CreateUserService {

    /**
     * 新增用户，并返回用户信息，拿到主键
     * @param user
     * @return
     */
    User createUser(User user);
}
