package com.itle.schoolhelp.service.impl;

import com.itle.schoolhelp.mapper.UserMapper;
import com.itle.schoolhelp.pojo.User;
import com.itle.schoolhelp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther Luler
 * @date 2020/1/17
 */
@Service
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByStudentId(String studentId) {
        return userMapper.findByStudentId(studentId);
    }


    @Override
    public int getAccountByStudent(String studentId) {
        User user = userMapper.findByStudentId(studentId);
        if (user == null) {
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public boolean insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User findUserByStuId(Integer stuId) {
        return userMapper.findUserByStuId(stuId);
    }
}
