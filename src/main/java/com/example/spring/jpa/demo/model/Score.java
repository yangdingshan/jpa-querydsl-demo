package com.example.spring.jpa.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/15 20:48
 * @Description:
 */
@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "chinese_score")
    private Integer chinese;

    @Column(name = "math_score")
    private Integer math;

    @OneToOne(mappedBy = "score")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }
}
