package com.itle.schoolhelp.pojo;

import java.util.Date;

/**
 * @auther Luler
 * @date 2020/2/16
 */
public class Admin {

    private Integer aId;

    private String account;

    private String password;

    private String name;

    private Date addTime;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
