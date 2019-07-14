package com.example.spring.jpa.demo;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.model.UserDTO;
import com.example.spring.jpa.demo.model.UserDeptDTO;
import com.example.spring.jpa.demo.repository.UserRepository;
import com.example.spring.jpa.demo.service.ReadUserService;
import com.querydsl.core.QueryResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadTests {

    @Autowired
    private ReadUserService readUserService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 基础查询
     */
    @Test
    public void baseRead() {
        String username = "Tony";
        User user = readUserService.queryByUsername(username);
        System.out.println("jpa命名规则查询：" + user);
        List<User> list = readUserService.findByUsername(username);
        System.out.println("使用SpEL表达式:" + list);
        List<User> list1 = readUserService.findByUsernameEndsWith("T");
        System.out.println("模糊查询：" + list1);
        User byUsernameNative = readUserService.findByUsernameNative(username);
        System.out.println("使用原生sql查询：" + byUsernameNative);
        List<User> sortList = readUserService.findByUsernameSort("To");
        System.out.println("使用排序查询：" + sortList);
        Optional<User> optional = readUserService.findOptionalByUsername(username);
        optional.ifPresent((u) -> {
            System.out.println("返回Optional：" + u);
        });
        User byUsernameToQuery = readUserService.findByUsernameToQuery(username);
        System.out.println("使用@Query查询：" + byUsernameToQuery);
    }

    @Test
    public void pageSortRead() {
        List<User> list = readUserService.findUserByPageSort(1, 2);
        System.out.println(list);
    }


    ////////////测试单独QueryDsl查询///////////////////////
    @Test
    public void findByUsernameAndPasswordTest() {
        List<User> list = readUserService.findByUsernameAndPassword("Tom", "345");
        System.out.println(list);
    }

    @Test
    public void findAllSortTest() {
        List<User> allSort = readUserService.findAllSort();
        System.out.println(allSort);
    }

    @Test
    public void findAllPageSortTest() {
        Pageable pageRequest = new PageRequest(1, 2);
        QueryResults<User> pageResult = readUserService.findAllPageSort(pageRequest);
        System.out.println("总条数：" + pageResult.getTotal());
        System.out.println("当前页内容：" + pageResult.getResults());
    }

    @Test
    public void findByBirthdayBetweenTest() throws ParseException {
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date end = format.parse("2019-10-10");
        List<User> list = readUserService.findByBirthdayBetween(start, end);
        System.out.println(list);
    }

    @Test
    public void findByUsernamLikeTest() {
        List<User> list = readUserService.findByUsernamLike("tom");
        System.out.println(list);
    }

    @Test
    public void findAllUserDtoTest() {
        Pageable pageRequest = new PageRequest(1, 2);
        List<UserDTO> allUserDto = readUserService.findAllUserDto(pageRequest);
        System.out.println(allUserDto);
    }

    @Test
    public void findByUserPropertiesGroupByUIndexTest() {
        List<User> list = readUserService.findByUserPropertiesGroupByUIndex("Tony", "123");
        System.out.println(list);
    }

    @Test
    public void findByDepatmentIdDTOTest() {
        List<UserDeptDTO> list = readUserService.findByDepatmentIdDTO(1);
        System.out.println(list);
    }

    @Test
    public void findByDepatmentIdDTO2Test() {
        List<UserDeptDTO> list = readUserService.findByDepatmentIdDTO2(1);
        System.out.println(list);
    }

    @Test
    public void findByUsernameLikeOrderByuIndexTest() {
        List<User> tony = readUserService.findByUsernameLikeOrderByuIndex("Tony");
        System.out.println(tony);
    }

    @Test
    public void findByUserPropertiesTest() {
        Pageable pageRequest = new PageRequest(0, 2);
        Page<User> page = readUserService.findByUserProperties(pageRequest, "Tony", "123");
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("内容：" + page.getContent());
    }

    @Test
    public void findByMaxindexTest() {
        List<User> list = readUserService.findByMaxindex();
        System.out.println(list);
    }

    @Test
    public void findUserByExampleTest() {
        List<User> userByExample = readUserService.findUserByExample();
        System.out.println(userByExample);
    }

    @Test
    public void findUserByExampleMatcherTest() {
        List<User> list = readUserService.findUserByExampleMatcher();
        System.out.println(list);
    }

    @Test
    public void findByUserSpecificationTest() {
        Pageable pageRequest = new PageRequest(0, 2);
        Page<User> page = readUserService.findByUserSpecification("Tony", "123", 60, pageRequest);
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("内容：" + page.getContent());
    }

    @Test
    public void findNativeTest() {
        List<UserDeptDTO> list = readUserService.findNative(1);
        System.out.println(list);
    }
}
