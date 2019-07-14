package com.example.spring.jpa.demo.service.impl;

import com.example.spring.jpa.demo.model.QUser;
import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.repository.UserRepository;
import com.example.spring.jpa.demo.service.UpdateUserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 11:51
 * @Description:
 */
@Service
public class UpdateUserServiceImpl implements UpdateUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //////////////jap更新///////////////////

    /**
     * save方法有主键就会更新，没有就会新增
     * @param userId
     */
    @Override
    public void updateUserByJpa(int userId) {
        User user = userRepository.findOne(userId);
        user.setUsername("aaa");
        userRepository.save(user);
    }

    /**
     * 通过@Query注解更新用户名
     * @param username
     * @param userId
     */
    @Override
    public void updateUserByJpaQuery(String username, int userId) {
        userRepository.updateUser(username, userId);
    }

    //////////////////QueryDSL更新//////////////////

    /**
     * 使用QueryDSL更新用户
     * @param username
     * @param userId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserByQueryDSL(String username, int userId) {
        QUser user = QUser.user;
        jpaQueryFactory
                .update(user)
                .set(user.username, username)
                .where(user.userId.eq(userId))
                .execute();
    }
}
