package com.example.spring.jpa.demo.service.impl;

import com.example.spring.jpa.demo.model.QDept;
import com.example.spring.jpa.demo.model.QUser;
import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.model.UserDTO;
import com.example.spring.jpa.demo.model.UserDeptDTO;
import com.example.spring.jpa.demo.repository.UserRepository;
import com.example.spring.jpa.demo.service.ReadUserService;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/13 10:19
 * @Description: 查询实现类
 */
@Service
public class ReadUserServiceImpl implements ReadUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    /////////////////////基础查询/////////////////////////

    @Override
    public User queryByUsername(String username) {
        return userRepository.queryByUsername(username);
    }

    @Override
    public Optional<User> findOptionalByUsername(String username) {
        Optional<User> optional = userRepository.findOptionalByUsername(username);
        return optional.map(user -> optional).orElse(Optional.empty());
    }

    @Override
    public User findByUsernameToQuery(String username) {
        return userRepository.findByUsernameToQuery(username);
    }

    @Override
    public User findByUsernameToQuery2(String username) {
        return userRepository.findByUsernameToQuery2(username);
    }

    @Override
    public List<User> findByUsernameEndsWith(String username) {
        return userRepository.findByUsernameEndsWith(username);
    }

    @Override
    public User findByUsernameNative(String username) {
        return userRepository.findByUsernameNative(username);
    }

    @Override
    public List<User> findByUsernameSort(String username) {
        Sort sort = new Sort(Sort.Direction.ASC, "uIndex");
        return userRepository.findByUsernameSort(username, sort);
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 分页排序查询
     * @return
     */
    @Override
    public List<User> findUserByPageSort(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "uIndex");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }

    /////////////////通过Example查询////////////////////////

    /**
     * 通过Example查询
     * @return
     */
    @Override
    public List<User> findUserByExample() {
        User user = new User();
        user.setUsername("Tony");
        List<User> list = userRepository.findAll(Example.of(user));
        return list;
    }

    /**
     * 自定义ExampleMatcher查询
     * @return
     */
    @Override
    public List<User> findUserByExampleMatcher() {
        User uer = new User();
        uer.setUsername("To");
        uer.setPassword("1");
        ExampleMatcher match = ExampleMatcher.matching()
                .withMatcher("username", matcher -> matcher.startsWith())
                .withMatcher("password", matcher -> matcher.contains());
        Example<User> example = Example.of(uer, match);
        List<User> list = userRepository.findAll(example);
        return list;
    }


    //////////////////单独使用QueryDSL查询////////////////////

    /**
     * 通过用户名和密码进行查找
     * @param username
     * @param password
     * @return
     */
    @Override
    public List<User> findByUsernameAndPassword(String username, String password) {
        QUser user = QUser.user;
        return jpaQueryFactory.selectFrom(user).where(
                user.username.eq(username),
                user.password.eq(password)
        ).fetch();
    }

    /**
     * dsl排序查询
     * @return
     */
    @Override
    public List<User> findAllSort() {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .orderBy(
                    user.uIndex.desc()
                ).fetch();
    }

    /**
     * dsl排序分页查询
     * @return
     */
    @Override
    public QueryResults<User> findAllPageSort(Pageable pageable) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .orderBy(
                        user.uIndex.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    /**
     * dsl查询时间区间
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<User> findByBirthdayBetween(Date start, Date end) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .where(
                        user.birthday.between(start, end)
                ).fetch();
    }

    /**
     * dsl模糊查询
     * @param username
     * @return
     */
    @Override
    public List<User> findByUsernamLike(String username) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .where(
                        user.username.like("%" + username + "%")
                ).fetch();
    }

    /**
     * dsl查询部分字段映射
     * @param pageable
     * @return
     */
    @Override
    public List<UserDTO> findAllUserDto(Pageable pageable) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .orderBy(
                        user.uIndex.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(model -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(model, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());
    }

    /**
     * 动态条件排序、分组查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public List<User> findByUserPropertiesGroupByUIndex(String username, String password) {
        QUser user = QUser.user;
        Predicate predicate = user.isNotNull().or(user.isNull());
        //执行动态条件拼装
        predicate = username == null ? predicate : ExpressionUtils.and(predicate, user.username.eq(username));
        predicate = password == null ? predicate : ExpressionUtils.and(predicate, user.password.eq(password));
        return jpaQueryFactory
                .selectFrom(user)
                .where(predicate)
                .orderBy(user.userId.desc())
                .groupBy(user.uIndex)
                .having(user.uIndex.max().lt(5))
                .fetch();
    }

    ////////////////多表查询/////////////////////

    /**
     * 根据部门的id查询用户的基本信息+用户所属部门信息
     * @param deptId
     * @return
     */
    @Override
    public List<UserDeptDTO> findByDepatmentIdDTO(int deptId) {
        QUser user = QUser.user;
        QDept dept = QDept.dept;
        return jpaQueryFactory
                .select(
                        user.username,
                        user.birthday,
                        dept.deptName
                )
                .from(user, dept)
                .where(
                        user.deptId.eq(dept.deptId).and(dept.deptId.eq(deptId))
                )
                .fetch()
                .stream()
                .map(model -> {
                    UserDeptDTO dto = new UserDeptDTO();
                    dto.setUsername(model.get(user.username));
                    dto.setBirthday(model.get(user.birthday));
                    dto.setDeptName(model.get(dept.deptName));
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 根据部门的id查询用户的基本信息+用户所属部门信息
     * @param deptId
     * @return
     */
    @Override
    public List<UserDeptDTO> findByDepatmentIdDTO2(int deptId) {
        QUser user = QUser.user;
        QDept dept = QDept.dept;
        return jpaQueryFactory
                .select(
                        // 直接转成想要的DTO
                        Projections.bean(UserDeptDTO.class, user.username, user.birthday, dept.deptName)
                )
                .from(user, dept)
                .where(
                        user.deptId.eq(dept.deptId).and(dept.deptId.eq(deptId))
                ).fetch();
    }

    /**
     * QueryDSL子查询，查询年龄最大的
     * @return
     */
    @Override
    public List<User> findByMaxindex() {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .where(
                    user.age.eq(
                            JPAExpressions.select(
                                    user.age.max()
                            )
                            .from(user)
                    )
                ).fetch();
    }



    ///////////////spring data jpa + QueryDSL结合////////////////////////////

    /**
     * 根据usernmae模糊查询，并根据uIndex排序
     * @param username
     * @return
     */
    @Override
    public List<User> findByUsernameLikeOrderByuIndex(String username) {
        QUser user = QUser.user;
        List<User> list = (List<User>)userRepository.findAll(
                user.username.like("%" + username + "%"),
                user.uIndex.desc()
        );
        return list;
    }

    /**
     * 动态条件拼接，jpa+QueryDSL结合使用
     * @param username
     * @param password
     * @return
     */
    @Override
    public Page<User> findByUserProperties(Pageable pageable, String username, String password) {
        QUser user = QUser.user;
        Predicate predicate = user.isNotNull().or(user.isNull());
        //执行动态条件拼装
        predicate = username == null ? predicate : ExpressionUtils.and(predicate, user.username.eq(username));
        predicate = password == null ? predicate : ExpressionUtils.and(predicate, user.password.eq(password));
        return userRepository.findAll(predicate, pageable);
    }


    //////////拼接查询条件Specification查询///////////////
    

}


