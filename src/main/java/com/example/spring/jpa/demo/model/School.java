package com.example.spring.jpa.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/15 21:07
 * @Description:
 */
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;


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
}
