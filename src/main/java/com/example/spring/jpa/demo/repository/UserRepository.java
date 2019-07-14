package com.example.spring.jpa.demo.repository;

import com.example.spring.jpa.demo.model.User;
import com.example.spring.jpa.demo.model.UserDeptDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/13 9:38
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, QueryDslPredicateExecutor<User> {

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
    Optional<User>  findOptionalByUsername(String username);

    /**
     * @Query 位置对应
     * @param username
     * @return
     */
    @Query("select u from User u where u.username = ?1")
    User findByUsernameToQuery(String username);

    /**
     * @Query 使用命名参数
     * @param username
     * @return
     */
    @Query("select u from User u where u.username = :username")
    User findByUsernameToQuery2(@Param("username") String username);

    /**
     * like@Query
     * @param username
     * @return
     */
    @Query("select u from User u where u.username like %?1%")
    List<User> findByUsernameEndsWith(String username);

    /**
     * @Query 在查询方法中声明本机查询
     * @param username
     * @return
     */
    @Query(value = "SELECT * FROM t_user WHERE username = ?1", nativeQuery = true)
    User findByUsernameNative(String username);

    /**
     * @Query 排序
     * @param username
     * @param sort
     * @return
     */
    @Query("select u from User u where u.username like %?1%")
    List<User> findByUsernameSort(String username, Sort sort);

    /**
     * 使用SpEL表达式
     * @param username
     * @return
     */
    @Query("select u from #{#entityName} u where u.username = ?1")
    List<User> findByUsername(String username);

    /**
     * 用sql查询一个部门下人员的信息
     * @param deptId
     * @return
     */
    List<UserDeptDTO> findByNative(int deptId);

    /**
     * 删除年龄某个范围的人员
     * @param start
     * @param ent
     */
    @Transactional
    void deleteByAgeIsBetween(int start, int ent);

    /**
     * 根据用户名删除
     * @param username
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByUsername(String username);

    /**
     * 删除用户
     * @param userId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from t_user where user_id = ?1",nativeQuery = true)
    void deleteUserById(int userId);

    /**
     * 修改用户姓名
     * @param username
     * @param userId
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackFor = Exception.class)
    @Query("update User set username=?1 where userId = ?2")
    void updateUser(String username, int userId);


}
