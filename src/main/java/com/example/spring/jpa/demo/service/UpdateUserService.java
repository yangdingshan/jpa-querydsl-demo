package com.example.spring.jpa.demo.service;

import com.example.spring.jpa.demo.model.User;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 11:51
 * @Description: update user_table interface
 */
public interface UpdateUserService {

    /**
     * save方法有主键就会更新，没有就会新增
     * @param userId
     */
    void updateUserByJpa(int userId);

    /**
     * 通过@Query注解更新用户名
     * @param username
     * @param userId
     */
    void updateUserByJpaQuery(String username, int userId);

    /**
     * 使用QueryDSL更新用户
     * @param username
     * @param userId
     */
    void updateUserByQueryDSL(String username, int userId);
}
