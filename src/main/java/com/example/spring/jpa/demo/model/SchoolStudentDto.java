package com.example.spring.jpa.demo.model;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/15 21:12
 * @Description:
 */
public class SchoolStudentDto {

    private String stuName;

    private String schName;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    @Override
    public String toString() {
        return "SchoolStudentDto{" +
                "stuName='" + stuName + '\'' +
                ", schName='" + schName + '\'' +
                '}';
    }
}
