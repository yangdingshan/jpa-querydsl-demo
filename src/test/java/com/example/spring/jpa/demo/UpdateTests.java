package com.example.spring.jpa.demo;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.service.ReadUserService;
import com.example.spring.jpa.demo.service.UpdateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 12:02
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTests {

    @Autowired
    private UpdateUserService updateUserService;

    @Test
    public void updateUserByJpaTest() {
        updateUserService.updateUserByJpa(11);
    }

    @Test
    public void updateUserByJpaQueryTest() {
        updateUserService.updateUserByJpaQuery("ttt", 11);
    }

    @Test
    public void updateUserByQueryDSLTest() {
        updateUserService.updateUserByQueryDSL("qqq", 11);
    }
}
