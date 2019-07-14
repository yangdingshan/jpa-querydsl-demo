package com.example.spring.jpa.demo;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.service.CreateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 11:45
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateTests {

    @Autowired
    private CreateUserService createUserService;

    @Test
    public void createUserTest() {
        User user = new User();
        user.setUsername("hack");
        user.setPassword("123");
        user.setAge(23);
        user.setBirthday(new Date());
        user.setDeptId(1);
        user.setuIndex(9);
        User u = createUserService.createUser(user);
        System.out.println("新增用户Id=" + u.getUserId());
    }
}
