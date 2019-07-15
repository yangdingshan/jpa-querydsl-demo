package com.example.spring.jpa.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/15 20:46
 * @Description:
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.REMOVE )
    private Score score;

    //一对多映射
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Chinese=" + score.getChinese() +
                ", math=" + score.getMath() +
                '}';
    }
}
