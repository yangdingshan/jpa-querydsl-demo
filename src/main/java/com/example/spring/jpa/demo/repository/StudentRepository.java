package com.example.spring.jpa.demo.repository;

import com.example.spring.jpa.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/15 20:53
 * @Description:
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
