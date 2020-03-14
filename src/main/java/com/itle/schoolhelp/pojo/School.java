package com.itle.schoolhelp.pojo;

import java.util.Date;

/**
 * @auther Luler
 * @date 2020/2/16
 */
public class School {

    private Integer schoolId;

    private String name;

    private Date addTime;

    private Integer state;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", addTime=" + addTime +
                ", state=" + state +
                '}';
    }
}
