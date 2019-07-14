package com.example.spring.jpa.demo;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.service.DeleteUserService;
import org.hibernate.sql.Delete;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 10:27
 * @Description: 删除测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTests {

    @Autowired
    private DeleteUserService deleteUserService;

    @Test
    public void deleteOneByJpaTest() {
       // deleteUserService.deleteOneByJpa(10);
        User user = new User();
        user.setUserId(5);
        deleteUserService.deleteOneByJpa(user);
    }

    @Test
    public void deleteByAgeBetweenTest() {
        deleteUserService.deleteByAgeBetween(32, 35);
    }

    @Test
    public void deleteByQueryDSLTest() {
        deleteUserService.deleteByQueryDSL(1, "Tony");
    }

    @Test
    public void deleteByAgeWithQueryDSLTest() {
        deleteUserService.deleteByAgeWithQueryDSL("tom");
    }
}
