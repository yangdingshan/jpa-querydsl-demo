package com.example.spring.jpa.demo.model;

import java.util.Date;

/**
 * @Author: yangdingshan
 * @Date: 2019/7/13 17:33
 * @Description:
 */
public class UserDeptDTO {

    //用户基础信息
    private String username;    //用户名
    private Date birthday;    //用户生日
    //用户的部门信息
    private String deptName;    //用户所属部门

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "UserDeptDTO{" +
                "username='" + username + '\'' +
                ", birthday='" + birthday + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
