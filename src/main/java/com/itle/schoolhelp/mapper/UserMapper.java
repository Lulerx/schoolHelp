package com.itle.schoolhelp.mapper;

import com.itle.schoolhelp.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther Luler
 * @date 2020/1/17
 */
@Mapper
public interface UserMapper {

    /**
     *根据学生学号查询用户
     * @param studentId
     * @return
     */
    public User findByStudentId(String studentId);

    /**
     * 用户注册
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
     * 根据用户id查询用户信息
     * @param stuId
     * @return
     */
    User findUserByStuId(Integer stuId);
}
