package com.example.spring.jpa.demo.service;

import com.example.spring.jpa.demo.model.User;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 10:13
 * @Description: 删除接口
 */
public interface DeleteUserService {

    /**
     * 删除单条记录
     * @param id
     */
    void deleteOneByJpa(int id);

    /**
     * 删除单条记录
     * @param user
     */
    void deleteOneByJpa(User user);

    /**
     * 删除年龄在某个范围的用户
     * @param start
     * @param end
     */
    void deleteByAgeBetween(int start, int end);

    /**
     * sql删除
     * @param userId
     */
    void deleteByNative(int userId);

    /**
     * QueryDSL根据属性删除
     * @param userId
     * @param username
     */
    void deleteByQueryDSL(int userId, String username);

    /**
     * 删除用户名为username用户年龄大于24的用户
     * @param username
     */
    void deleteByAgeWithQueryDSL(String username);
}
