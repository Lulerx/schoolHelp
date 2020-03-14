package com.itle.schoolhelp.service;

import com.itle.schoolhelp.pojo.User;

/**
 * @auther Luler
 * @date 2020/1/16
 */
public interface IUserService {

    /**
     * 用户登录的service，根据学号id查询用户
     * @param studentId
     * @return
     */
    public User findByStudentId(String studentId);

    /**
     * 根据学号id统计用户个数，查看是否存在该用户
     * @param studentId
     * @return
     */
    int getAccountByStudent(String studentId);

    /**
     * 用户注册，插入一个用户信息
     * @param user
     * @return
     */
    boolean insertUser(User user);


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户id查找用户
     * @param stuId
     * @return
     */
    User findUserByStuId(Integer stuId);
}
