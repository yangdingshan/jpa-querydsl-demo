package com.example.spring.jpa.demo.repository;

import com.example.spring.jpa.demo.model.UserDeptDTO;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/14 9:32
 * @Description:
 */
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserDeptDTO> findByNative(int deptId) {
        // sql不能带 ; 号
        String sql = "select u.username, u.birthday, d.dept_name FROM t_dept d, t_user u where u.dept_id = d.dept_id and d.dept_id = :deptId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("deptId", deptId);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> tempResult = query.getResultList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!CollectionUtils.isEmpty(tempResult)) {
            List<UserDeptDTO> userDeptDTOList = tempResult.stream().map(map -> {
                UserDeptDTO dto = new UserDeptDTO();
                dto.setUsername((String) map.get("username"));
                dto.setDeptName((String) map.get("dept_name"));
                try {
                    dto.setBirthday(format.parse(map.get("birthday").toString()));
                } catch (ParseException e) {
                    System.out.println("...");
                }
                return dto;
            }).collect(Collectors.toList());
            return userDeptDTOList;
        } else {
            return null;
        }
    }
}
