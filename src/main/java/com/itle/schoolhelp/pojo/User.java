package com.itle.schoolhelp.pojo;

import java.util.Date;

/**
 * 学生用户信息vo
 * @auther Luler
 * @date 2020/1/16
 */
public class User {

    private Integer stuId;  //学生id

    private String studentId;   //学号

    private String password;    //密码

    private Integer schoolId;   //学校id

    private Short sex;      //性别

    private String name;    //学生姓名

    private Date registerTime;  //注册时间

    private Double money;       //账户余额

    private Short state;    //账户状态

    public User() {
    }

    public User(Integer stuId, String studentId, String password,
                Integer schoolId, Short sex, String name,
                Date registerTime, Double money, Short state) {
        this.stuId = stuId;
        this.studentId = studentId;
        this.password = password;
        this.schoolId = schoolId;
        this.sex = sex;
        this.name = name;
        this.registerTime = registerTime;
        this.money = money;
        this.state = state;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "stuId=" + stuId +
                ", studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", schoolId=" + schoolId +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                ", registerTime=" + registerTime +
                ", money=" + money +
                ", state=" + state +
                '}';
    }
}
