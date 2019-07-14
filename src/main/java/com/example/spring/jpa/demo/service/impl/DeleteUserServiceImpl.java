package com.example.spring.jpa.demo.service.impl;

import com.example.spring.jpa.demo.model.QUser;
import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.repository.UserRepository;
import com.example.spring.jpa.demo.service.DeleteUserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 10:13
 * @Description:
 */
@Service
public class DeleteUserServiceImpl implements DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    /////////////////////使用jpa删除////////////////////////
    /**
     * 删除单条记录
     * @param id
     */
    @Override
    public void deleteOneByJpa(int id) {
        userRepository.delete(id);
    }

    /**
     * 根据实体删除单条记录
     * @param user
     */
    @Override
    public void deleteOneByJpa(User user) {
        userRepository.delete(user);
    }

    /**
     * 删除年龄在某个范围的用户
     * @param start
     * @param end
     */
    @Override
    public void deleteByAgeBetween(int start, int end) {
        userRepository.deleteByAgeIsBetween(start, end);
    }

    /**
     * sql删除
     * @param userId
     */
    @Override
    public void deleteByNative(int userId) {
        userRepository.deleteUserById(userId);
    }

    ///////////////////QueryDSL删除/////////////////////

    /**
     * QueryDSL根据属性删除
     * @param userId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByQueryDSL(int userId, String username) {
        QUser user = QUser.user;
        jpaQueryFactory
                .delete(user)
                .where(
                        user.userId.eq(userId),
                        user.username.eq(username)
                ).execute();
    }

    /**
     * 删除用户名为username用户年龄大于24的用户
     * @param username
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByAgeWithQueryDSL(String username) {
        QUser user = QUser.user;
        jpaQueryFactory
                .delete(user)
                .where(
                        user.username.eq(username),
                        user.age.gt(24)
                ).execute();
    }


}
