package com.example.spring.jpa.demo.service;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.model.UserDTO;
import com.example.spring.jpa.demo.model.UserDeptDTO;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/13 10:17
 * @Description: 查询接口
 */
public interface ReadUserService {

    /**
     * 通过jpa名字查询
     * @param username
     * @return
     */
    User queryByUsername(String username);

    /**
     * 返回值封装Optional
     * @param username
     * @return
     */
    Optional<User> findOptionalByUsername(String username);

    /**
     * @Query 位置对应
     * @param username
     * @return
     */
    User findByUsernameToQuery(String username);

    /**
     * @Query 使用命名参数
     * @param username
     * @return
     */
    User findByUsernameToQuery2(String username);

    /**
     * like@Query
     * @param username
     * @return
     */
    List<User> findByUsernameEndsWith(String username);

    /**
     * @Query 在查询方法中声明本机查询
     * @param username
     * @return
     */
    User findByUsernameNative(String username);

    /**
     * @Query 排序
     * @param username
     * @return
     */
    List<User> findByUsernameSort(String username);

    /**
     * 使用SpEL表达式
     * @param username
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * 分页排序查询
     * @param page
     * @param size
     * @return
     */
    List<User> findUserByPageSort(int page, int size);

    //////////////////单独使用QueryDsl查询////////////////////

    /**
     * 通过用户名和密码进行查找
     * @param username
     * @param password
     * @return
     */
    List<User> findByUsernameAndPassword(String username, String password);

    /**
     * dsl排序查询
     * @return
     */
    List<User> findAllSort();

    /**
     * dsl排序分页查询
     * @param pageable
     * @return
     */
    QueryResults<User> findAllPageSort(Pageable pageable);

    /**
     * dsl查询时间区间
     * @param start
     * @param end
     * @return
     */
    List<User> findByBirthdayBetween(Date start, Date end);

    /**
     * dsl模糊查询
     * @param username
     * @return
     */
    List<User> findByUsernamLike(String username);

    /**
     * dsl查询部分字段映射
     * @param pageable
     * @return
     */
    List<UserDTO> findAllUserDto(Pageable pageable);

    /**
     * 动态条件排序、分组查询
     * @param username
     * @param password
     * @return
     */
    List<User> findByUserPropertiesGroupByUIndex(String username, String password);

    /**
     * 根据部门的id查询用户的基本信息+用户所属部门信息
     * @param deptId
     * @return
     */
    List<UserDeptDTO> findByDepatmentIdDTO(int deptId);

    /**
     * 根据部门的id查询用户的基本信息+用户所属部门信息
     * @param deptId
     * @return
     */
    List<UserDeptDTO> findByDepatmentIdDTO2(int deptId);

    /**
     * 根据usernmae模糊查询，并根据uIndex排序
     * @param username
     * @return
     */
    List<User> findByUsernameLikeOrderByuIndex(String username);

    /**
     * 动态条件拼接，jpa+QueryDSL结合使用
     * @param username
     * @param password
     * @return
     */
    Page<User> findByUserProperties(Pageable pageable, String username, String password);

    /**
     * QueryDSL子查询，查询年龄最大的
     * @return
     */
    List<User> findByMaxindex();

    /**
     * 通过Example查询
     * @return
     */
    List<User> findUserByExample();

    /**
     * 自定义ExampleMatcher查询
     * @return
     */
    List<User> findUserByExampleMatcher();

    /**
     * Specification分页查询
     * @param username
     * @param password
     * @param age
     * @param pageable
     * @return
     */
    Page<User> findByUserSpecification(String username, String password, int age, Pageable pageable);

    /**
     * 使用sql查询
     * @param deptId
     * @return
     */
    List<UserDeptDTO> findNative(int deptId);
}
